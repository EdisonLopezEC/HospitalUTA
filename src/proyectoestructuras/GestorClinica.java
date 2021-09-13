/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 904ed
 */
public class GestorClinica {

    Consola c = new Consola();
    BaseDeDatos db = new BaseDeDatos();
    private int apertura = 8;
    private int cierre = 17;

    Persistencia p = new Persistencia();

    ArrayList<Doctor> doctores = new ArrayList<>();
    ArrayList<Paciente> pacientes = new ArrayList<>();
    ArrayList<Enfermera> enfermeras = new ArrayList<>();
    ArrayList<Cita> citas = new ArrayList<>();
    ArrayList<Cargo> cargos = db.devolverCargo();
    ArrayList<Especialidad> especialidades = db.devolverEspecialidades();

    public GestorClinica() throws FileNotFoundException, ParseException, SQLException {

        Persistencia p = new Persistencia();

//        doctores = p.leerDoctores();
//        enfermeras = p.leerEnfermeras();
//        pacientes = p.leerPacientes();
//        citas = p.leerCitas(doctores, pacientes);
    }

    public void crearPaciente(String cedula, String nombre, String direccion,
            String telefono) {
        db.insertarPaciente(new Paciente(0, cedula, nombre, direccion, telefono));

//        p.guardarPacinetes(pacientes);
    }

    public void crearDoctor(String cedula, String nombre, String direccion,
            String telefono, int cargoID, int especialidadID) {
        db.insertarDoctor(new Doctor(0, cedula, nombre, direccion,
                telefono,
                cargos.stream().filter(p -> p.getId() == cargoID)
                        .findFirst().get(),
                especialidades.stream().
                        filter(p -> p.getId() == especialidadID)
                        .findFirst().get()));
    }

    public void crearEnfermera(String cedula, String nombre, String direccion,
            String telefono) {
        db.insertarEnfermera(new Enfermera(0, cedula, nombre, direccion, telefono));
    }

    public void actualizarDatosDoctor(int atributo, Doctor doc) {
        switch (atributo) {
            case 1:
                String nombre = c.preguntar("Ingrese el nuevo nombre");
                doc.setNombApell(nombre);
                db.actualizarDoctor(doc);

//                p.guardarDoctores(doctores);
                break;
            case 2:
                String cedula = c.preguntar("Ingrese la nueva cedula");
                doc.setCedulaRuc(cedula);
                db.actualizarDoctor(doc);
                break;
            case 3:
                String direc = c.preguntar("Ingrese la nueva dirección");
                doc.setDireccion(direc);
                db.actualizarDoctor(doc);

                break;
            case 4:
                String tel = c.preguntar("Ingrese el nuevo teléfono");
                doc.setTelefono(tel);
                db.actualizarDoctor(doc);
                break;
            case 5:
                int cargo = Integer.valueOf(c.preguntar("Ingrese el nuevo cargo"));
                doc.setCargo(cargos.stream().filter(p -> p.getId() == cargo).findFirst().get());
                db.actualizarDoctor(doc);
                break;
            case 6:
                int especialidad = Integer.valueOf(c.preguntar("Ingresar la nuevo especialidad"));
                doc.setEspecialidad(especialidades.stream().filter(p -> p.getId() == especialidad).findFirst().get());
                db.actualizarDoctor(doc);
                break;
        }
    }

    public void actualizarDatosPaciente(int atributo, Paciente pac) {
        switch (atributo) {
            case 1:
                String nombre = c.preguntar("Ingrese el nuevo nombre");
                pac.setNombApell(nombre);
                db.actualizarPaciente(pac);
                break;
            case 2:
                String cedula = c.preguntar("Ingrese la nueva cedula");
                pac.setCedulaRuc(cedula);
                db.actualizarPaciente(pac);

                break;
            case 3:
                String direc = c.preguntar("Ingrese la nueva dirección");
                pac.setDireccion(direc);
                db.actualizarPaciente(pac);

                break;
            case 4:
                String tel = c.preguntar("Ingrese el nuevo teléfono");
                pac.setTelefono(tel);
                db.actualizarPaciente(pac);
                break;
        }

        System.out.println("-----||-----");
        c.listarPaciente(pacientes);
    }

    public void actualizarDatosEnfermera(int atributo, Enfermera enf) {

        switch (atributo) {
            case 1:
                String nombre = c.preguntar("Ingrese el nuevo nombre");
                enf.setNombApell(nombre);
                db.actualizarEnfermera(enf);
                break;
            case 2:
                String cedula = c.preguntar("Ingrese la nueva cedula");
                enf.setCedulaRuc(cedula);
                db.actualizarEnfermera(enf);
                break;
            case 3:
                String direc = c.preguntar("Ingrese la nueva dirección");
                enf.setDireccion(direc);
                db.actualizarEnfermera(enf);
                break;
            case 4:
                String tel = c.preguntar("Ingrese el nuevo teléfono");
                enf.setTelefono(tel);
                db.actualizarEnfermera(enf);
                break;
        }
    }

//    public ArrayList historiaClinica(String cedula) {
//
//        ArrayList<Cita> historiaClinica = new ArrayList<>();
//
//        Paciente paciente = paciente(cedula);
//
//        ArrayList<Cita> citasPaciente = paciente.getCitas();
//
//        for (int i = 0; i < citasPaciente.size(); i++) {a
//
//            Cita citasEncontradas = citasPaciente.get(i);
//
//            if (citasEncontradas.getEstado().equals(Estado.ATENDIDO)) {
//                historiaClinica.add(citasEncontradas);
//            }
//        }
//
//        return historiaClinica;
//
//    }
//    public ArrayList doctoresPorEspecialidad(int especialidad) {
//        ArrayList<Doctor> doctoresEspecialidad = new ArrayList();
//        for (Doctor d : doctores) {
//            if (d.getEspecialidad() == especialidad) {
//                doctoresEspecialidad.add(d);
//            }
//        }
//        return doctoresEspecialidad;
//    }
//    public ArrayList especialidadesDelHospital() {
//
//        if (doctores.isEmpty()) {
//            return null;
//        }
//        ArrayList<String> especialidades = new ArrayList();
//        especialidades.add(doctores.get(0).getEspecialidad());
//        for (Doctor doctor : this.doctores) {
//            if (!especialidades.contains(doctor.getEspecialidad())) {
//                especialidades.add(doctor.getEspecialidad());
//            }
//        }
//        return especialidades;
//    }
    public boolean verificarSiPacienteExiste(String cedula, ArrayList<Paciente> p) {
        for (Paciente paciente : p) {
            if (paciente.getCedulaRuc().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList citasPorEstado(Estado estado) throws SQLException {
        ArrayList<Cita> citas = db.devolverCitas();
        ArrayList<Cita> citasEstado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getEstado().getNombreEstado().equals(estado.getNombreEstado())) {
                citasEstado.add(cita);
            }
        }
        return citasEstado;
    }

    public ArrayList filtrarDoctor(String cedula, Estado estado) throws SQLException {

        ArrayList<Doctor> doctores = db.obtenerDoctores();
        Doctor d = doctores.stream().filter(p -> p.getCedulaRuc()
                .equals(cedula)).findFirst().get();

        ArrayList<Cita> citasDoctor = db.obtenerCitasDeUnDoctor(d);

        ArrayList<Cita> citasFiltradas = new ArrayList<>();

        for (int i = 0; i < citasDoctor.size(); i++) {

            Cita cita = citasDoctor.get(i);

            if (cita.getEstado().getNombreEstado().equals(estado.getNombreEstado())) {
                citasFiltradas.add(cita);
            }
        }

        return citasFiltradas;
    }

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

    public void actualizarCita(int c, String tratamiento, String diagnostico,
            float precio, Estado estado) throws FileNotFoundException, SQLException {
        ArrayList<Cita> allCitas = db.devolverCitas();
        Cita cita = allCitas.stream().filter(p -> p.getId() == c)
                .findFirst().get();
        cita.setTratamiento(tratamiento);
        cita.setDiagnostico(diagnostico);
        cita.setPrecio(precio);
        cita.setEstado(estado);
        db.actualizarCita(cita, 3);
    }
//

    public void actualizarCita(int citaSel, Estado estado) throws FileNotFoundException, SQLException {
        ArrayList<Cita> allCitas = db.devolverCitas();
        Cita cita = allCitas.stream().filter(p -> p.getId() == citaSel).findFirst().get();
        db.actualizarCita(cita, 1);
    }

    public ArrayList<Doctor> doctoresDisponibles(ArrayList<Doctor> doctoresEsp, Date fecha, int horaSeleccionada) throws SQLException {
        ArrayList<Doctor> doctoresDisponibles = new ArrayList<>();

        for (Doctor doctor : doctoresEsp) {
            if (doctorDisponible(doctor, fecha, horaSeleccionada)) {
                doctoresDisponibles.add(doctor);
            }
        }
        return doctoresDisponibles;
    }

//    public ArrayList<Doctor> doctoresDisponibles(String especialidadSeleccionada, Date fecha, int horaSeleccionada) {
//        ArrayList<Doctor> doctoresDisponibles = new ArrayList<>();
//                ArrayList<Doctor> especialidades = new ArrayList<>();
//
//        ArrayList<Doctor> doctoresPorEspecialidad = 
//        for (Doctor doctor : this.doctores) {
//            if (especialidadSeleccionada.equals(doctor.getEspecialidad()) && doctorDisponible(doctor, fecha, horaSeleccionada)) {
//                doctoresDisponibles.add(doctor);
//            }
//        }
//
//        return doctoresDisponibles;
//    }
    public boolean mismoDia(Date fecha1, Date fecha2) {
        return fecha1.getDate() == fecha2.getDate() && fecha1.getYear() == fecha2.getYear() && fecha1.getMonth() == fecha2.getMonth();
    }

    ////////////////////////
    public boolean doctorDisponible(Doctor doctor, Date fecha, int hora) throws SQLException {

        ArrayList<Cita> citasDoctor = db.obtenerCitasDeUnDoctor(doctor);
        for (Cita cita : citasDoctor) {
            if (mismoDia(fecha, cita.getFecha()) && hora == cita.getHora()) {
                return false;
            }
        }

        return true;
    }

    public void agendar(Doctor d, Paciente p, Date fecha, int hora) {
        ArrayList<Estado> estados = new ArrayList<>();
        estados = db.obtenerEstados();
        Cita citaNueva = new Cita(0, fecha, p, d, hora, estados.stream().filter(s -> s.getNombreEstado().equals("Pendiente")).findFirst().get());
        db.insertarCita(citaNueva);
    }

//    public void agendar(String cedulaDoc, String cedulaPac, Date fecha, int hora) throws FileNotFoundException {
//
//        Doctor doctor = this.doctor(cedulaDoc);
//        Paciente paciente = this.paciente(cedulaPac);
//        Cita cita = new Cita(fecha, paciente, doctor, hora, Estado.PENDIENTE);
//        citas.add(cita);
//        paciente.agregarCita(cita);
//        doctor.agregarCita(cita);
//        p.guardarCitas(citas);
//    }
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

    public double ingresosHospital() throws SQLException {

        ArrayList<Cita> c = db.devolverCitas();
        double totalIngresos = 0;
        for (int i = 0; i < c.size(); i++) {
            Cita cita = this.citas.get(i);
            if (cita.getEstado().getNombreEstado().equals("Atendido")) {
                totalIngresos += cita.getPrecio();
            }
        }
        return totalIngresos;
    }
}
