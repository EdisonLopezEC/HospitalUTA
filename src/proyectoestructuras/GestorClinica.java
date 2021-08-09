/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 904ed
 */
public class GestorClinica {

    Consola c = new Consola();
    private int apertura = 8;
    private int cierre = 17;

    Persistencia p = new Persistencia();

    ArrayList<Doctor> doctores = new ArrayList<>();
    ArrayList<Paciente> pacientes = new ArrayList<>();
    ArrayList<Enfermera> enfermeras = new ArrayList<>();
    ArrayList<Cita> citas = new ArrayList<>();

    public GestorClinica() throws FileNotFoundException, ParseException {

        Persistencia p = new Persistencia();
        doctores = p.leerDoctores();
        enfermeras = p.leerEnfermeras();
        pacientes = p.leerPacientes();
        citas = p.leerCitas(doctores, pacientes);

    }

    public void crearPaciente(String cedula, String nombre, String direccion,
            String telefono) {
        pacientes.add(new Paciente(cedula, nombre, direccion, telefono));

        p.guardarPacinetes(pacientes);

    }

    public void crearDoctor(String cedula, String nombre, String direccion,
            String telefono, String cargo, String especialidad) {
        doctores.add(new Doctor(cedula, nombre, direccion,
                telefono, cargo, especialidad));
        p.guardarDoctores(doctores);
    }

    public void crearEnfermera(String cedula, String nombre, String direccion,
            String telefono) {
        enfermeras.add(new Enfermera(cedula, nombre, direccion, telefono));
        p.guardarEnfermeras(enfermeras);
    }

    public void actualizarDatosDoctor(int atributo, int op) {
        switch (atributo) {
            case 1:
                String nombre = c.preguntar("Ingrese el nuevo nombre");
                doctores.get(op).setNombApell(nombre);
                p.guardarDoctores(doctores);
                break;
            case 2:
                String cedula = c.preguntar("Ingrese la nueva cedula");
                doctores.get(op).setCedulaRuc(cedula);
                p.guardarDoctores(doctores);
                break;
            case 3:
                String direc = c.preguntar("Ingrese la nueva dirección");
                doctores.get(op).setDireccion(direc);
                p.guardarDoctores(doctores);

                break;
            case 4:
                String tel = c.preguntar("Ingrese el nuevo teléfono");
                doctores.get(op).setTelefono(tel);
                p.guardarDoctores(doctores);
                break;
            case 5:
                String cargo = c.preguntar("Ingrese el nuevo cargo");
                doctores.get(op).setCargo(cargo);
                p.guardarDoctores(doctores);
                break;
            case 6:
                String especialidad = c.preguntar("Ingresar la nuevo especialidad");
                doctores.get(op).setEspecialidad(especialidad);
                p.guardarDoctores(doctores);
                break;
        }
    }

    public void actualizarDatosPaciente(int atributo, int op) {
        switch (atributo) {
            case 1:
                String nombre = c.preguntar("Ingrese el nuevo nombre");
                pacientes.get(op).setNombApell(nombre);
                p.guardarPacinetes(pacientes);
                break;
            case 2:
                String cedula = c.preguntar("Ingrese la nueva cedula");
                pacientes.get(op).setCedulaRuc(cedula);
                p.guardarPacinetes(pacientes);
                break;
            case 3:
                String direc = c.preguntar("Ingrese la nueva dirección");
                pacientes.get(op).setCedulaRuc(direc);
                p.guardarPacinetes(pacientes);
                break;
            case 4:
                String tel = c.preguntar("Ingrese el nuevo teléfono");
                pacientes.get(op).setTelefono(tel);
                p.guardarPacinetes(pacientes);
                break;
        }
        c.mostrarDatos(op, pacientes);
    }

    public void actualizarDatosEnfermera(int atributo, int op) {

        switch (atributo) {
            case 1:
                String nombre = c.preguntar("Ingrese el nuevo nombre");
                enfermeras.get(op).setNombApell(nombre);
                p.guardarEnfermeras(enfermeras);
                break;
            case 2:
                String cedula = c.preguntar("Ingrese la nueva cedula");
                enfermeras.get(op).setCedulaRuc(cedula);
                p.guardarEnfermeras(enfermeras);

                break;
            case 3:
                String direc = c.preguntar("Ingrese la nueva dirección");
                enfermeras.get(op).setCedulaRuc(direc);
                p.guardarEnfermeras(enfermeras);
                break;
            case 4:
                String tel = c.preguntar("Ingrese el nuevo teléfono");
                enfermeras.get(op).setTelefono(tel);
                p.guardarEnfermeras(enfermeras);
                break;
        }
        c.mostrarDatos(op, enfermeras);
    }

    public ArrayList historiaClinica(String cedula) {

        ArrayList<Cita> historiaClinica = new ArrayList<>();

        Paciente paciente = paciente(cedula);

        ArrayList<Cita> citasPaciente = paciente.getCitas();

        for (int i = 0; i < citasPaciente.size(); i++) {

            Cita citasEncontradas = citasPaciente.get(i);

            if (citasEncontradas.getEstado().equals(Estado.ATENDIDO)) {
                historiaClinica.add(citasEncontradas);
            }
        }

        return historiaClinica;

    }

    public ArrayList doctoresPorEspecialidad(String especialidad) {
        ArrayList<Doctor> doctoresEspecialidad = new ArrayList();
        for (Doctor d : doctores) {
            if (d.getEspecialidad().equals(especialidad)) {
                doctoresEspecialidad.add(d);
            }
        }
        return doctoresEspecialidad;
    }

    public ArrayList especialidadesDelHospital() {

        if (doctores.isEmpty()) {
            return null;
        }
        ArrayList<String> especialidades = new ArrayList();
        especialidades.add(doctores.get(0).getEspecialidad());
        for (Doctor doctor : this.doctores) {
            if (!especialidades.contains(doctor.getEspecialidad())) {
                especialidades.add(doctor.getEspecialidad());
            }
        }
        return especialidades;
    }

    public boolean verificarSiPacienteExiste(String cedula) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCedulaRuc().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList citasPorEstado(Estado estado) {
        ArrayList<Cita> citasEstado = new ArrayList();
        for (Cita cita : this.citas) {
            if (cita.getEstado().equals(estado)) {
                citasEstado.add(cita);
            }
        }
        return citasEstado;
    }

    ////////////
    public ArrayList filtrarDoctor(String cedula, Estado estado) {

        Doctor doctor = doctor(cedula);

        ArrayList<Cita> citasDoctor = doctor.getCitas();

        ArrayList<Cita> citasFiltradas = new ArrayList<>();

        for (int i = 0; i < citasDoctor.size(); i++) {

            Cita cita = citasDoctor.get(i);

            if (cita.getEstado().equals(estado)) {

                citasFiltradas.add(cita);

            }
        }

        return citasFiltradas;
    }

//    public int horaSeleccionada(){
//         Date fechaActual = new Date(System.currentTimeMillis());
//         fechaActual.getHours();
//         
//    }



    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(ArrayList<Doctor> doctores) {
        this.doctores = doctores;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Enfermera> getEnfermeras() {
        return enfermeras;
    }

    public void setEnfermeras(ArrayList<Enfermera> enfermeras) {
        this.enfermeras = enfermeras;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }

    public Consola getC() {
        return c;
    }

    public void setC(Consola c) {
        this.c = c;
    }

    public int getApertura() {
        return apertura;
    }

    public void setApertura(int apertura) {
        this.apertura = apertura;
    }

    public int getCierre() {
        return cierre;
    }

    public void setCierre(int cierre) {
        this.cierre = cierre;
    }

    public void actualizarCita(Cita cita, String tratamiento, String diagnostico,
            float precio) throws FileNotFoundException {
        cita.setTratamiento(tratamiento);
        cita.setDiagnostico(diagnostico);
        cita.setPrecio(precio);
        p.guardarCitas(citas);
    }

    public ArrayList<Doctor> doctoresDisponibles(String especialidadSeleccionada, Date fecha, int horaSeleccionada) {
        ArrayList<Doctor> doctoresDisponibles = new ArrayList<>();

        for (Doctor doctor : doctores) {
            if (especialidadSeleccionada.equals(doctor.getEspecialidad()) && doctorDisponible(doctor, fecha, horaSeleccionada)) {
                doctoresDisponibles.add(doctor);
            }
        }

        return doctoresDisponibles;
    }

    public boolean mismoDia(Date fecha1, Date fecha2) {
        return fecha1.getDate() == fecha2.getDate() && fecha1.getYear() == fecha2.getYear() && fecha1.getMonth() == fecha2.getMonth();
    }

    public boolean doctorDisponible(Doctor doctor, Date fecha, int hora) {

        ArrayList<Cita> citasDoctor = doctor.getCitas();
        for (Cita cita : citasDoctor) {
            if (mismoDia(fecha, cita.getFecha()) && hora == cita.getHora()) {
                return false;
            }
        }

        return true;
    }

    public void agendar(String cedulaDoc, String cedulaPac, Date fecha, int hora) throws FileNotFoundException {

        Doctor doctor = this.doctor(cedulaDoc);

        Paciente paciente = this.paciente(cedulaPac);

        Cita cita = new Cita(fecha, paciente, doctor, hora);

        citas.add(cita);
        paciente.agregarCita(cita);
        doctor.agregarCita(cita);
        p.guardarCitas(citas);
    }

    public Paciente paciente(String cedula) {

        for (int i = 0; i < pacientes.size(); i++) {

            Paciente p = pacientes.get(i);

            if (p.getCedulaRuc().equals(cedula)) {
                return p;
            }

        }

        return null;

    }

    public Doctor doctor(String cedula) {

        for (int i = 0; i < doctores.size(); i++) {

            Doctor d = doctores.get(i);

            if (d.getCedulaRuc().equals(cedula)) {
                return d;
            }

        }

        return null;

    }

}
