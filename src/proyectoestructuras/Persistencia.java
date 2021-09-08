/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Persistencia {

}
//    public ArrayList<Doctor> leerDoctores() throws FileNotFoundException {
//        ArrayList<Doctor> doctores = new ArrayList<>();
//        File archivo = new File("listaDoctores.txt");
//        if (archivo.exists()) {
//
//            Scanner lector = new Scanner(archivo);
//
//            while (lector.hasNext()) {
//
//                String linea = lector.nextLine();
//                String[] datosDoctore = linea.split(",");
//
//                String cedulaDoctor = datosDoctore[0];
//                String nombreDoctor = datosDoctore[1];
//                String direccionDoctor = datosDoctore[2];
//                String telefonoDoctor = datosDoctore[3];
//                String cargoDoctor = datosDoctore[4];
//                String espacialidadDoctor = datosDoctore[5];
//
//                Doctor doctor = new Doctor(cedulaDoctor, nombreDoctor, direccionDoctor, telefonoDoctor, cargoDoctor, espacialidadDoctor);
//
//                doctores.add(doctor);
//
//            }
//
//        }
//
//        return doctores;
//
//    }
//
//    public void guardarDoctores(ArrayList<Doctor> doctores) {
//
//        File archivo = new File("listaDoctores.txt");
//
//        try {
//            PrintWriter pw = new PrintWriter(archivo);
//
//            for (int i = 0; i < doctores.size(); i++) {
//
//                Doctor doctorGuardar = doctores.get(i);
//
//                String cedulaDoctor = doctorGuardar.getCedulaRuc();
//                String nombreDoctor = doctorGuardar.getNombApell();
//                String direccionDoctor = doctorGuardar.getDireccion();
//                String telefonoDoctor = doctorGuardar.getTelefono();
//                String cargoDoctor = doctorGuardar.getCargo();
//                String espacialidadDoctor = doctorGuardar.getEspecialidad();
//
//                pw.println(cedulaDoctor + "," + nombreDoctor + "," + direccionDoctor + "," + telefonoDoctor + "," + cargoDoctor + "," + espacialidadDoctor);
//
//            }
//
//            pw.close();
//
//        } catch (Exception e) {
//
//            System.out.println("ERROR: No se pudo escribir los datos");
//        }
//
//    }
//
//    public ArrayList<Enfermera> leerEnfermeras() throws FileNotFoundException {
//
//        ArrayList<Enfermera> enfermeras = new ArrayList<>();
//        File archivo = new File("listaEnfermeras.txt");
//        if (archivo.exists()) {
//
//            Scanner lector = new Scanner(archivo);
//
//            while (lector.hasNext()) {
//
//                String linea = lector.nextLine();
//
//                String[] datosEmfermera = linea.split(",");
//
//                String cedulaDoctor = datosEmfermera[0];
//                String nombreDoctor = datosEmfermera[1];
//                String direccionDoctor = datosEmfermera[2];
//                String telefonoDoctor = datosEmfermera[3];
//
//                Enfermera enfermera = new Enfermera(cedulaDoctor, nombreDoctor, direccionDoctor, telefonoDoctor);
//
//                enfermeras.add(enfermera);
//
//            }
//
//        }
//
//        return enfermeras;
//
//    }
//
//    public void guardarEnfermeras(ArrayList<Enfermera> enfermeras) {
//
//        File archivo = new File("listaEnfermeras.txt");
//
//        try {
//            PrintWriter pw = new PrintWriter(archivo);
//
//            for (int i = 0; i < enfermeras.size(); i++) {
//
//                Enfermera enfermeraGuardar = enfermeras.get(i);
//
//                String cedulaEnfermera = enfermeraGuardar.getCedulaRuc();
//                String nombreEnfermera = enfermeraGuardar.getNombApell();
//                String direccionEnfermera = enfermeraGuardar.getDireccion();
//                String telefonoEnfermera = enfermeraGuardar.getTelefono();
//
//                pw.println(cedulaEnfermera + "," + nombreEnfermera + "," + direccionEnfermera + "," + telefonoEnfermera);
//
//            }
//
//            pw.close();
//
//        } catch (Exception e) {
//
//            System.out.println("ERROR: No se pudo escribir los datos");
//        }
//
//    }
//
//    public ArrayList<Paciente> leerPacientes() throws FileNotFoundException {
//
//        ArrayList<Paciente> pacientes = new ArrayList<>();
//        File archivo = new File("listaPacientes.txt");
//
//        if (archivo.exists()) {
//
//            Scanner lector = new Scanner(archivo);
//
//            while (lector.hasNext()) {
//
//                String linea = lector.nextLine();
//
//                String[] datosPaciente = linea.split(",");
//
//                String cedulaDoctor = datosPaciente[0];
//                String nombreDoctor = datosPaciente[1];
//                String direccionDoctor = datosPaciente[2];
//                String telefonoDoctor = datosPaciente[3];
//
//                Paciente paciente = new Paciente(cedulaDoctor, nombreDoctor, direccionDoctor, telefonoDoctor);
//
//                pacientes.add(paciente);
//
//            }
//
//        }
//
//        return pacientes;
//
//    }
//
//    public void guardarPacinetes(ArrayList<Paciente> pacientes) {
//
//        File archivo = new File("listaPacientes.txt");
//
//        try {
//            PrintWriter pw = new PrintWriter(archivo);
//
//            for (int i = 0; i < pacientes.size(); i++) {
//
//                Paciente pacienteGuardar = pacientes.get(i);
//
//                String cedulaEnfermera = pacienteGuardar.getCedulaRuc();
//                String nombreEnfermera = pacienteGuardar.getNombApell();
//                String direccionEnfermera = pacienteGuardar.getDireccion();
//                String telefonoEnfermera = pacienteGuardar.getTelefono();
//
//                pw.println(cedulaEnfermera + "," + nombreEnfermera + "," + direccionEnfermera + "," + telefonoEnfermera);
//
//            }
//
//            pw.close();
//
//        } catch (Exception e) {
//
//            System.out.println("ERROR: No se pudo escribir los datos");
//        }
//
//    }
//
//    public ArrayList<Cita> leerCitas(ArrayList<Doctor> doctores, ArrayList<Paciente> pacientes) throws FileNotFoundException, ParseException {
//
//        ArrayList<Cita> citas = new ArrayList<>();
//        File archivo = new File("listaCitas.txt");
//
//        if (archivo.exists()) {
//
//            Scanner lector = new Scanner(archivo);
//
//            while (lector.hasNext()) {
//
//                String linea = lector.nextLine();
//
//                String[] datosCita = linea.split(",");
//
//                Date fecha = new Date();
//
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
//
//                fecha = sdf.parse(datosCita[0]);
//
//                String cedulaPaciente = datosCita[1];
//
//                String cedulaDoctor = datosCita[2];
//
//                int horaCita = Integer.valueOf(datosCita[3]);
//
//                Doctor doctor = buscarDoctor(doctores, cedulaDoctor);
//                Paciente paciente = buscarPaciente(pacientes, cedulaPaciente);
//                
//                Estado estado;
//                
//                if (datosCita[4].equals("PENDIENTE")) {
//                    
//                     estado = Estado.PENDIENTE;
//                }else if(datosCita[4].equals("ATENDIDO")){
//                 
//                    estado = Estado.ATENDIDO;
//                }else{
//                
//                    estado = Estado.CANCELADO;
//                
//                }
//
//                Cita cita = new Cita(fecha, paciente, doctor, horaCita,estado);
//                
//                paciente.agregarCita(cita);
//                doctor.agregarCita(cita);                
//                citas.add(cita);
//
//            }
//
//        }
//
//        return citas;
//
//    }
//
//    public void guardarCitas(ArrayList<Cita> citas) throws FileNotFoundException {
//
//        File archivo = new File("listaCitas.txt");
//
//
//            PrintWriter pw = new PrintWriter(archivo);
//
//            for (int i = 0; i < citas.size(); i++) {
//
//                Cita citaGuardar = citas.get(i);
//
//                Date fechaCita = citaGuardar.getFecha();
//
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//                String fechaCitaString = sdf.format(fechaCita);
//
//                String cedulaPaciente = citaGuardar.getPaciente().getCedulaRuc();
//
//                String cedulaDoctor = citaGuardar.getDoctor().getCedulaRuc();
//
//                String hora = String.valueOf(citaGuardar.getHora());
//                
//                Estado estado = citaGuardar.getEstado();
//
//                pw.println(fechaCitaString + "," + cedulaPaciente + "," + cedulaDoctor + "," + hora + "," + estado);
//
//            }
//
//            pw.close();       
//
//    }
//
//    public Doctor buscarDoctor(ArrayList<Doctor> doctores, String cedula) {
//
//        for (int i = 0; i < doctores.size(); i++) {
//
//            Doctor doctor = doctores.get(i);
//
//            if (doctor.getCedulaRuc().equals(cedula)) {
//                return doctor;
//            }
//
//        }
//
//        return null;
//
//    }
//
//    public Paciente buscarPaciente(ArrayList<Paciente> pacientes, String cedula) {
//
//        for (int i = 0; i < pacientes.size(); i++) {
//
//            Paciente paciente = pacientes.get(i);
//
//            if (paciente.getCedulaRuc().equals(cedula)) {
//                return paciente;
//            }
//
//        }
//
//        return null;
//
//    }
//
//}
