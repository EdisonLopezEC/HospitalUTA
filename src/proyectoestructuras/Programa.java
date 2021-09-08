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
public class Programa {

    Consola c = new Consola();
    GestorClinica gestor;
    BaseDeDatos db = new BaseDeDatos();
    Validaciones validar = new Validaciones();
    ArrayList<Doctor> doctores = db.obtenerDoctores();
    ArrayList<Paciente> pacientes = db.obtenerPacientes();
    ArrayList<Especialidad> especialidades = db.devolverEspecialidades();
    ArrayList<Cargo> cargos = db.devolverCargo();

    public Programa() throws FileNotFoundException, ParseException, SQLException {
        this.gestor = new GestorClinica();
    }

    public void menuPrincipal() throws ParseException, FileNotFoundException, SQLException {

        int op;
        System.out.printf("%75s\n\n", "---|||--- G E S T I O N  H O S P I T A L ---|||---");
        do {

            op = c.menu("Doctores,Pacientes,Enfermeras,Citas,Estadisticas");
            subMenu(op);

        } while (op != 6);

    }

    public boolean mismoDia(Date fecha1, Date fecha2) {
        return fecha1.getDate() == fecha2.getDate() && fecha1.getYear() == fecha2.getYear() && fecha1.getMonth() == fecha2.getMonth();
    }

    public void subMenu(int op) throws ParseException, FileNotFoundException, SQLException {

        int opc;

        switch (op) {
            case 1:

                do {
                    opc = c.menu("Listar,Crear,Actualizar");
                    switch (opc) {
                        case 1:
                            c.listarDoctor(db.obtenerDoctores());
                            break;
                        case 2:
                            boolean valida;
                            String cedula;
                            do {
                                cedula = c.preguntar("Cedula");
                                valida = validar.validarCedula(cedula);
                                if (!valida) {
                                    System.out.println("Cedula no valida");
                                }
                            } while (!valida);
                            c.imprimirEspecialidaddes(db.devolverEspecialidades());
                            int especialidad = Integer.valueOf(
                                    c.preguntar("Seleccione una Especialidad"));
//validar que este dentro del rango 
                            c.imprimirCargos(db.devolverCargo());
                            int cargo = Integer.valueOf(
                                    c.preguntar("Seleccione una Cargo"));
                            gestor.crearDoctor(cedula,
                                    c.preguntar("Nombre"),
                                    c.preguntar("Direccion"),
                                    c.preguntar("Teléfono"),
                                    cargo,
                                    especialidad);
                            break;
                        case 3:
                            int doc;
                            c.listarDoctor(db.obtenerDoctores());
                            doc = c.pregutarEntero("Escoja el doctor a editar", 1, doctores.size());
                            Doctor d = doctores.stream().filter(p -> p.getId() == doc).findFirst().get();
                            c.mostrarDatos(d);
                            gestor.actualizarDatosDoctor(c.pregutarEntero("Que dato desea modificar?", 1, 6), d);
                            break;
                    }

                } while (opc != 4);

                break;
            case 2:
                do {
                    opc = c.menu("Listar,Actualizar,Historia Clinica");
                    switch (opc) {
                        case 1:
                            c.listarPaciente(pacientes);
                            break;
                        case 2:
                            c.listarPaciente(pacientes);
                            int paciente = Integer.valueOf(c.preguntar("Escoja el/la paciente a editar"));
                            Paciente d = pacientes.stream().filter(p -> p.getId() == paciente).findFirst().get();
                            c.mostrarDatos(d);
                            gestor.actualizarDatosPaciente(
                                    Integer.valueOf(c.preguntar("Que dato desea modificar?")),
                                    d);
                            break;

                        case 3:
                            c.imprimirHistorial(gestor.historiaClinica(c.preguntar("Cedula")));
                            break;
                    }
                } while (opc != 4);
                break;
            case 3:
                do {

                    opc = c.menu("Listar,Actualizar,Crear");
                    switch (opc) {
                        case 1:
                            c.listarEnfermera(gestor.getEnfermeras());
                            break;
                        case 2:
                            ArrayList<Enfermera> enf = gestor.getEnfermeras();
                            c.listarEnfermera(enf);
                            int doc = c.pregutarEntero("Escoja la emfermera a editar", 1, enf.size()) - 1;
                            c.mostrarDatos(doc, enf);
                            int atributo = c.pregutarEntero("Que dato desea modificar?", 1, 4);
                            gestor.actualizarDatosEnfermera(atributo, doc);
                            break;
                        case 3:
                            boolean valida;
                            String cedula;
                            do {
                                cedula = c.preguntar("Cedula");
                                valida = validar.validarCedula(cedula);
                                if (!valida) {
                                    System.out.println("Cedula no valida");
                                }
                            } while (!valida);

                            gestor.crearEnfermera(cedula,
                                    c.preguntar("Nombre"),
                                    c.preguntar("Direccion"),
                                    c.preguntar("Teléfono"));

                            break;
                    }
                } while (opc != 4);
                break;
            case 4:
                do {

                    opc = c.menu("Agendar,Listar Pendientes,Actualizar");
                    switch (opc) {
                        case 1:
                            String cedula = c.preguntar("Cedula");
                            boolean existe = gestor.verificarSiPacienteExiste(cedula);
                            if (!existe) {
                                boolean valida;
                                do {
                                    valida = validar.validarCedula(cedula);
                                    if (!valida) {
                                        System.out.println("Cedula no valida");
                                    }
                                } while (!valida);
                                gestor.crearPaciente(
                                        cedula,
                                        c.preguntar("Nombre"),
                                        c.preguntar("Direccion"),
                                        c.preguntar("Teléfono"));
                            }

                            int horaAbre = gestor.getApertura();
                            int horaCierre = gestor.getCierre();

                            int iniRango;
                            int finRango = horaCierre;

//                            ArrayList<String> especialidades = gestor.especialidadesDelHospital();
//
//                            c.imprimirLista(especialidades);
//                            int opcEspecialidad = c.pregutarEntero("Seleccione la especialidad", 1, especialidades.size());
//                            String especialidadSeleccionada = especialidades.get(opcEspecialidad - 1);
                            Date fecha;

                            int horaSeleccionada;
                            do {
                                fecha = c.preguntarFecha("Ingrese la fecha");

//                                c.listarDoctor(gestor.doctoresPorEspecialidad((String) gestor.especialidadesDelHospital().get(opcEspecialidad - 1)));
                                Date fechaActual = new Date();

                                if (mismoDia(fecha, fechaActual)) {
                                    iniRango = fechaActual.getHours();
                                    if (iniRango > horaCierre - 1) {
                                        iniRango = -1;
                                    }
                                    if (iniRango < horaAbre) {
                                        iniRango = horaAbre;
                                    }
                                } else {
                                    iniRango = horaAbre;
                                }

                                if (iniRango == -1) {
                                    System.out.println("NO HAY CITAS DIPONIBLES EN ESTA FECHA");
                                }

                                horaSeleccionada = c.imprimirHoras(iniRango, finRango);

                                System.out.println("HORA SELECCIONADA: " + horaSeleccionada);

                            } while (iniRango == -1);

//                            ArrayList<Doctor> doctoresDisponibles = gestor.doctoresDisponibles(especialidadSeleccionada, fecha, horaSeleccionada);
                            Doctor doctorSel;
                            int inDocSel;

//                            if (doctoresDisponibles.isEmpty()) {
//                                System.out.println("Error: Sin doctores disponibles");
//                            } else {
//
//                                do {
//                                    c.listarDoctor(doctoresDisponibles);
//
//                                    inDocSel = Integer.valueOf(c.preguntar("Indice doctor => "));
//
//                                } while (inDocSel < -1 || inDocSel > doctoresDisponibles.size());
//
//                                doctorSel = doctoresDisponibles.get(inDocSel - 1);
//
//                                gestor.agendar(doctorSel.getCedulaRuc(), cedula, fecha, horaSeleccionada);
//
//                            }
                            break;

                        case 2:
                            c.imprimirCitas(gestor.citasPorEstado(Estado.PENDIENTE));
                            break;
                        case 3:
                            ArrayList<Cita> pendientes = gestor.citasPorEstado(Estado.PENDIENTE);

                            c.imprimirCitas(pendientes);
                            if (!pendientes.isEmpty()) {

                                int citaACambiar = Integer.valueOf(c.preguntar("Que cita desea cambiar de estado"));

                                int opcion = c.indices("ATENDIDO, CANCELADO");

                                if (opcion == 1) {

                                    gestor.actualizarCita(pendientes.get(citaACambiar - 1), c.preguntar("Tratamiento"), c.preguntar("Diagnostico"),
                                            Float.valueOf(c.preguntar("Precio")), Estado.ATENDIDO);
                                } else {

                                    gestor.actualizarCita(pendientes.get(citaACambiar - 1), Estado.CANCELADO);

                                    System.out.println("La cita se a cancelado.");

                                }

                            }

                            break;
                    }
                } while (opc != 4);
                break;
            case 5:
                do {

                    opc = c.menu("Generar Reportes Atendidos por Doctor,Citas Canceladas por Doctor,Ingresos");
                    switch (opc) {
                        case 1:

                            c.imprimirCitas(
                                    gestor.filtrarDoctor(
                                            c.preguntar("Cedula"),
                                            Estado.ATENDIDO)
                            );
                            break;

                        case 2:
                            c.imprimirCitas(
                                    gestor.filtrarDoctor(
                                            c.preguntar("Cedula"),
                                            Estado.CANCELADO)
                            );
                            break;
                        case 3:

                            System.out.printf("\n%30s %.2f", "Los ingresos del hospital son de: ", gestor.ingresosHospital());

                            break;

                    }
                    break;
                } while (opc != 4);
                break;
        }

    }

}
