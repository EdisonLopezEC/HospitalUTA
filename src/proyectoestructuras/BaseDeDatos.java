/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 904ed
 */
public class BaseDeDatos {

    public static final String URL = "jdbc:mysql://localhost:3306/hospital";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin1";
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ArrayList<Cargo> cargos;
    ArrayList<Especialidad> especialidades;

    public BaseDeDatos() throws SQLException {
        con = getConection();
        this.cargos = devolverCargo();
        this.especialidades = devolverEspecialidades();
    }

    public static Connection getConection() {
        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public void insertarDoctor(Doctor d) {

        try {

            ps = con.prepareStatement("INSERT INTO doctores (id,nombresApell"
                    + ",direccion,nTelefono,cedula,especialidad_id,cargo_id) "
                    + "values (?,?,?,?,?,?,?)");
            ps.setString(1, "0");
            ps.setString(2, d.getNombApell());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getCedulaRuc());
            ps.setString(6, d.getEspecialidad().getId() + "");
            ps.setString(7, d.getCargo().getId() + "");

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Doctor Creado");
            } else {
                System.out.println("Hubo un error");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void insertarCita(Cita d) {
        java.sql.Date date = new java.sql.Date(0000 - 00 - 00);

        try {

            ps = con.prepareStatement("INSERT INTO citas (id,fecha"
                    + ",hora,pacientes_id,doctores_id,estado_id) "
                    + "values (?,?,?,?,?,?)");
            ps.setString(1, "0");
            ps.setDate(2, new java.sql.Date(d.getFecha().getTime()));
            ps.setInt(3, d.getHora());
            ps.setInt(4, d.getPaciente().getId());
            ps.setInt(5, d.getDoctor().getId());
            ps.setInt(6, d.getEstado().getId());

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Cita Creada");
            } else {
                System.out.println("Hubo un error");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertarPaciente(Paciente d) {

        try {

            ps = con.prepareStatement("INSERT INTO pacientes (id,nombresApell"
                    + ",direccion,nTelefono,cedula) "
                    + "values (?,?,?,?,?)");
            ps.setString(1, "0");
            ps.setString(2, d.getNombApell());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getCedulaRuc());

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Paciente Creada/o");
            } else {
                System.out.println("Hubo un error");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void insertarEnfermera(Enfermera d) {

        try {

            ps = con.prepareStatement("INSERT INTO enfermeras (id,nombresApell"
                    + ",direccion,nTelefono,cedula) "
                    + "values (?,?,?,?,?)");
            ps.setString(1, "0");
            ps.setString(2, d.getNombApell());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getCedulaRuc());

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Enfermera Creada/o");
            } else {
                System.out.println("Hubo un error");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void mostrarEspecialidades() throws SQLException {

        try {
            ps = con.prepareStatement("SELECT * FROM especialidades");
            rs = ps.executeQuery();
            System.out.println("ID\t\tNombre");
            while (rs.next()) {
                System.out.print(rs.getString("id"));
                System.out.println("\t\t" + rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList doctoresPorEspecialidad(int especialidad) {

        ArrayList<Doctor> doctores = new ArrayList<>();

        try {
            con = getConection();
            ps = con.prepareStatement("SELECT * FROM doctores WHERE especialidad_id=?"
            );
            ps.setString(1, especialidad + "");

            rs = ps.executeQuery();

            if (rs.next()) {
                int cargoID = rs.getInt("cargo_id");
                int especialidadID = rs.getInt("especialidad_id");
                doctores.add(new Doctor(Integer.valueOf(rs.getString("id")),
                        rs.getString("nombresApell"),
                        rs.getString("direccion"),
                        rs.getString("nTelefono"),
                        rs.getString("cedula"),
                        cargos.stream()
                                .filter(p -> p.getId() == cargoID)
                                .findFirst().get(),
                        especialidades.stream()
                                .filter(p -> p.getId() == especialidadID)
                                .findFirst().get()));
            } else {
                System.out.println("No existen medicos disponibles");
            }

            return doctores;

        } catch (Exception e) {
            System.out.println(e);
        }

        return doctores;
    }

    public void mostrarCargos() {

        try {
            con = getConection();
            ps = con.prepareStatement("SELECT * FROM cargos");
            rs = ps.executeQuery();
            System.out.println("ID\t\tCargo");
            while (rs.next()) {
                System.out.print(rs.getString("id"));
                System.out.println("\t\t" + rs.getString("nombreDelCargo"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList obtenerDoctores() throws SQLException {

        ArrayList<Doctor> doctores = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM doctores");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCargo = rs.getInt("cargo_id");
                int idEspecialidad = rs.getInt("especialidad_id");

                Cargo cargo = cargos.stream().filter(p -> p.getId() == idCargo).findFirst().get();
                Especialidad especialidad = especialidades.stream().filter(p -> p.getId() == idEspecialidad).findFirst().get();
                doctores.add(new Doctor(Integer.valueOf(rs.getString("id")),
                        rs.getString("cedula"),
                        rs.getString("nombresApell"),
                        rs.getString("direccion"),
                        rs.getString("nTelefono"),
                        cargo,
                        especialidad));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return doctores;
    }

    public ArrayList devolverEspecialidades() throws SQLException {

        ArrayList<Especialidad> especialidades = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM especialidades");
            rs = ps.executeQuery();
            while (rs.next()) {
                especialidades.add(
                        new Especialidad(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return especialidades;

    }

    public ArrayList devolverCargo() throws SQLException {

        ArrayList<Cargo> cargos = new ArrayList<>();
        try {

            ps = con.prepareStatement("SELECT * FROM cargos");
            rs = ps.executeQuery();
            while (rs.next()) {
                cargos.add(new Cargo(rs.getInt("id"), rs.getString("nombreDelCargo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return cargos;

    }

    public ArrayList devolverCitas() throws SQLException {

        ArrayList<Cita> citas = new ArrayList<>();
        try {
            ArrayList<Paciente> pacientes = obtenerPacientes();
            ArrayList<Doctor> doctores = obtenerDoctores();
            ArrayList<Estado> estados = obtenerEstados();
            ps = con.prepareStatement("SELECT * FROM citas");
            rs = ps.executeQuery();
            while (rs.next()) {
                int pacID = rs.getInt("pacientes_id");
                int docID = rs.getInt("doctores_id");
                int estID = rs.getInt("estado_id");
                citas.add(new Cita(rs.getInt("id"),
                        (Date) rs.getDate("fecha"),
                        pacientes.stream().filter(p -> p.getId() == pacID).findFirst().get(),
                        doctores.stream().filter(p -> p.getId() == docID).findFirst().get(),
                        rs.getInt("hora"),
                        estados.stream().filter(p -> p.getId() == estID).findFirst().get()));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return citas;
    }

    public void actualizarDoctor(Doctor d) {
        try {

            ps = con.prepareStatement("UPDATE doctores SET  nombresApell=?, direccion=?, nTelefono=?, cedula=?, especialidad_id=?, cargo_id=? WHERE id=? ");

            ps.setString(1, d.getNombApell());
            ps.setString(2, d.getDireccion());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getCedulaRuc());
            ps.setInt(5, d.getEspecialidad().getId());
            ps.setInt(6, d.getCargo().getId());
            ps.setInt(7, d.getId());

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Actualizacion Exitos");
            } else {
                System.out.println("No se pudo actualizar");
            }

            System.out.println("Doctor Actualizado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actualizarPaciente(Paciente d) {
        try {

            ps = con.prepareStatement("UPDATE pacientes SET  nombresApell=?, direccion=?, nTelefono=? cedula=? WHERE id=? ");

            ps.setString(1, d.getNombApell());
            ps.setString(2, d.getDireccion());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getCedulaRuc());
            ps.setInt(5, d.getId());

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Actualizacion Exitosa");
            } else {
                System.out.println("No se pudo actualizar");
            }
            System.out.println("Doctor Actualizado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actualizarEnfermera(Enfermera d) {
        try {

            ps = con.prepareStatement("UPDATE enfermeras SET  nombresApell=?, direccion=?, nTelefono=? cedula=? WHERE id=? ");

            ps.setString(1, d.getNombApell());
            ps.setString(2, d.getDireccion());
            ps.setString(3, d.getTelefono());
            ps.setString(4, d.getCedulaRuc());
            ps.setInt(5, d.getId());

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Actualizacion Exitos");
            } else {
                System.out.println("No se pudo actualizar");
            }

            System.out.println("Enfermero/a Actualizado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList obtenerPacientes() {

        ArrayList<Paciente> pacientes = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM pacientes");
            rs = ps.executeQuery();

            while (rs.next()) {
                pacientes.add(new Paciente(Integer.valueOf(rs.getString("id")),
                        rs.getString("cedula"),
                        rs.getString("nombresApell"),
                        rs.getString("direccion"),
                        rs.getString("nTelefono")
                ));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return pacientes;

    }

    public ArrayList obtenerEstados() {

        ArrayList<Estado> estados = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM estados");
            rs = ps.executeQuery();

            while (rs.next()) {
                estados.add(new Estado(Integer.valueOf(rs.getString("id")),
                        rs.getString("nombreEstado")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return estados;

    }

    public ArrayList obtenerEnfermeras() {
        ArrayList<Enfermera> enfermeras = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM enfermeras");
            rs = ps.executeQuery();

            while (rs.next()) {
                enfermeras.add(new Enfermera(Integer.valueOf(rs.getString("id")),
                        rs.getString("cedula"),
                        rs.getString("nombresApell"),
                        rs.getString("direccion"),
                        rs.getString("nTelefono")
                ));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return enfermeras;
    }

    public ArrayList obtenerCitasDeUnPaciente(Paciente d) throws SQLException {

        ArrayList<Cita> citas = new ArrayList<>();
        ArrayList<Paciente> pacientes = obtenerPacientes();
        ArrayList<Doctor> doctores = obtenerDoctores();
        ArrayList<Estado> estados = obtenerEstados();

        try {

            ps = con.prepareStatement("SELECT * FROM citas WHERE pacientes_id=?");
            ps.setString(1, d.getId() + "");
            rs = ps.executeQuery();

            while (rs.next()) {
                int pacienteID = rs.getInt("pacientes_id");
                int doctorID = rs.getInt("doctores_id");
                int estadoID = rs.getInt("estado_id");

                citas.add(new Cita((rs.getInt("id")),
                        rs.getDate("fecha"),
                        pacientes.stream().filter(p -> p.getId() == pacienteID).findFirst().get(),
                        doctores.stream().filter(p -> p.getId() == doctorID).findFirst().get(),
                        rs.getInt("hora"),
                        estados.stream().filter(p -> p.getId() == estadoID).findFirst().get()
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return estados;

    }

    public ArrayList obtenerCitasDeUnDoctor(Doctor d) throws SQLException {

        ArrayList<Cita> citas = new ArrayList<>();
        ArrayList<Paciente> pacientes = obtenerPacientes();
        ArrayList<Doctor> doctores = obtenerDoctores();
        ArrayList<Estado> estados = obtenerEstados();

        try {

            ps = con.prepareStatement("SELECT * FROM citas WHERE doctores_id=?");
            ps.setString(1, d.getId() + "");
            rs = ps.executeQuery();

            while (rs.next()) {
                int pacienteID = rs.getInt("pacientes_id");
                int doctorID = rs.getInt("doctores_id");
                int estadoID = rs.getInt("estado_id");

                citas.add(new Cita((rs.getInt("id")),
                        (Date) rs.getDate("fecha"),
                        pacientes.stream().filter(p -> p.getId() == pacienteID).findFirst().get(),
                        doctores.stream().filter(p -> p.getId() == doctorID).findFirst().get(),
                        rs.getInt("hora"),
                        estados.stream().filter(p -> p.getId() == estadoID).findFirst().get()
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return citas;

    }

    public void actualizarCita(Cita d, int estado) {
        try {

            ps = con.prepareStatement("UPDATE citas SET diagnostico=?, tratamiento=?, costo=?, estado_id=? WHERE id=?");
            ps.setString(1, d.getDiagnostico());
            ps.setString(2, d.getTratamiento());
            ps.setFloat(3, d.getPrecio());
            ps.setInt(4, estado);
            ps.setInt(5, d.getId());

            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Actualizacion Exitos");
            } else {
                System.out.println("No se pudo actualizar");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

//try {
//
//            ps = con.prepareStatement("UPDATE enfermeras SET  nombresApell=?, direccion=?, nTelefono=? cedula=? WHERE id=? ");
//
//            ps.setString(1, d.getNombApell());
//            ps.setString(2, d.getDireccion());
//            ps.setString(3, d.getTelefono());
//            ps.setString(4, d.getCedulaRuc());
//            ps.setInt(5, d.getId());
//
//            int res = ps.executeUpdate();
//
//            if (res > 0) {
//                System.out.println("Actualizacion Exitos");
//            } else {
//                System.out.println("No se pudo actualizar");
//            }
//
//            System.out.println("Enfermero/a Actualizado");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
