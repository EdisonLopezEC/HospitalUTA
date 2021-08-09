/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author 904ed
 */
public class Consola {

    private final Scanner sc;

    public Consola() {
        this.sc = new Scanner(System.in);
    }

    public String preguntar(String mensaje) {
        String respuesta;
        System.out.printf("%52s", mensaje + ": ");
        respuesta = sc.nextLine();
        return respuesta;
    }

    public int pregutarEntero(String mensaje) {
        return Integer.valueOf(preguntar(mensaje));
    }

    public int pregutarEntero(String mensaje, int min, int max) {
        int op;

        do {
            op = pregutarEntero(mensaje);
            if (op < min || op > max) {
                System.out.println("Error: Numero entre " + min + " y " + max);
            }
        } while (op < min || op > max);

        return op;

    }

    public int menu(String opciones) {

        int op = 0;
        int numOpciones = opciones.split(",").length;
        int j = 0;
        System.out.println("");
        do {
            for (String opcion : opciones.split(",")) {
                j++;
                System.out.printf("%45d%s%s\n", j, ".", opcion);
            }

            System.out.printf("%45d%s%s\n", numOpciones + 1, ".", "Salir");
            System.out.printf("\n%53s", "Opcion = ");
            op = Integer.valueOf(sc.nextLine());
            if (op < 1 || op > numOpciones + 1) {
                System.out.printf("\n%72s\n", "ERROR: Solo valores del Menu");
                j = 0;
            }
        } while (op < 1 || op > numOpciones + 1);
        return op;
    }

    public void listarDoctor(ArrayList<Doctor> lista) {
        System.out.printf("\n%s%30s%30s%30s%30s\n", "Nombre", "Cedula", "Cargo", "Especialidad",
                "Teléfono");
        int j = 1;
        if (lista.isEmpty()) {
            System.out.printf("\n%74s\n\n", "-----LISTA VACIA-----");
            return;
        }
        for (Doctor d : lista) {
            System.out.printf("%d%s %24s%30s%30s%30s\n", j, ".-" + d.getNombApell(),
                    d.getCedulaRuc(),
                    d.getCargo(),
                    d.getEspecialidad(),
                    d.getTelefono());
            j++;
        }
    }

    public void listarPaciente(ArrayList<Paciente> lista) {

        System.out.printf("\n%s%30s%30s\n", "Nombre", "Cedula", "Teléfono");
        int j = 1;
        if (lista.isEmpty()) {
            System.out.printf("\n%44s\n\n", "-----LISTA VACIA-----");
            return;
        }
        for (Paciente d : lista) {
            System.out.printf("%d%s%s%27s%30s\n", j, ".-", d.getNombApell(),
                    d.getCedulaRuc(),
                    d.getTelefono());
            j++;
        }

    }

    public void listarEnfermera(ArrayList<Enfermera> lista) {
        System.out.printf("\n%s%30s%30s\n", "Nombre", "Cedula", "Teléfono");
        int j = 1;
        if (lista.isEmpty()) {
            System.out.printf("\n%44s\n\n", "-----LISTA VACIA-----");
            return;
        }
        for (Enfermera d : lista) {
            System.out.printf("%d%s%s%27s%30s\n", j, ".-", d.getNombApell(),
                    d.getCedulaRuc(),
                    d.getTelefono());
            j++;
        }

    }

    public void mostrarDatos(int op, ArrayList d) {
        System.out.println(d.get(op).toString());
    }

    public void imprimirHistorial(ArrayList<Cita> citas) {

        for (int i = 0; i < citas.size(); i++) {

            Cita citaImprimir = citas.get(i);
            Date fecha = citaImprimir.getFecha();
            String formato = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);

            String fechaString = simpleDateFormat.format(fecha);

            String diagnostico = citaImprimir.getDiagnostico();

            String tratamiento = citaImprimir.getTratamiento();

            System.out.printf("%s\n", "Cita del: " + fechaString);

            System.out.printf("%s%s\n %s%s\n\n", "Diagnostico: ", diagnostico, "Tratamiento: ", tratamiento);

        }
    }

    public void imprimirCitas(ArrayList<Cita> citas) {

        if (citas.isEmpty()) {
            System.out.println("\n ****** Lista Vacia ****** ");
            return;
        }

        for (int i = 0; i < citas.size(); i++) {
            System.out.println("\n================= " + (i + 1) + " =================");
            Cita citaImprimir = citas.get(i);

            Date fecha = citaImprimir.getFecha();
            String formato = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
            String fechaString = simpleDateFormat.format(fecha);
            Paciente paciente = citaImprimir.getPaciente();
            Doctor doctor = citaImprimir.getDoctor();
            Float costo = citaImprimir.getPrecio();

            System.out.printf("%s%s\n", "Cita del: ", fechaString + " Horario: " + citaImprimir.getHora() + " - " + (citaImprimir.getHora() + 1));

            System.out.printf("%s%s\n%s%s\n", "Paciente : ", paciente.getNombApell() + " - " + paciente.getCedulaRuc(), "Doctor: ", doctor.getNombApell() + " - " + doctor.getCedulaRuc());
        }

    }

    public boolean mismoDia(Date fecha1, Date fecha2) {
        return fecha1.getDate() == fecha2.getDate() && fecha1.getYear() == fecha2.getYear() && fecha1.getMonth() == fecha2.getMonth();
    }

    public Date preguntarFecha(String pregunta) throws ParseException {
        Date respuesta = new Date();
        boolean fechaCorrecta;

        Date fechaActual = new Date(System.currentTimeMillis());

        do {
            try {
                String stringRespuesta = preguntar(pregunta);
                SimpleDateFormat sdft = new SimpleDateFormat("dd/MM/yyyy");
                sdft.setLenient(false);
                respuesta = sdft.parse(stringRespuesta);
                fechaCorrecta = respuesta.after(fechaActual) || mismoDia(respuesta, fechaActual);
                if (!fechaCorrecta) {
                    System.out.printf("\n%s%s\n", "", "ERROR: Ingrese una fecha posterior a la actual");

                }

            } catch (Exception e) {
                fechaCorrecta = false;
                System.out.printf("\n%s%s\n", "", "ERROR: Ingrese la fecha de esta manera: dd/mm/yyyy");
                System.out.println("");
            }
        } while (!fechaCorrecta);
        return respuesta;
    }

    public void imprimirLista(ArrayList lista) {
        int j = 1;
        if (lista.isEmpty()) {
            return;
        }
        for (Object l : lista) {
            System.out.println(j + ".- " + l);
            j++;
        }
    }

    public int imprimirHoras(int horaINicio, int horaFin) {
        int contador = 1;
        for (int i = horaINicio; i < horaFin; i++) {
            System.out.println(contador + ".-" + i + ":00 - " + (i + 1) + ":00");
            contador++;
        }
        int in;
        do {
            in = Integer.valueOf(preguntar("Eliga la hora de su cita")) - 1;

            if (in < 1 || in > horaFin - horaINicio - 1) {
                System.out.println("ERROR: FUERA DEL RANGO");
            }

        } while (in < 1 || in > horaFin - horaINicio - 1);

        return horaINicio + in;

    }
}
