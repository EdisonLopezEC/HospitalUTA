/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

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
    ArrayList<Cargo> cargos ;
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

    public void listarDoctoresPorEspecialidad(int especialidad) {

        try {
            con = getConection();
            ps = con.prepareStatement("SELECT * FROM doctores WHERE especialidad_id=?"
                    + "ORDER BY nombre");
            ps.setString(1, especialidad + "");

            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("nombresApell"));
                System.out.println(rs.getString("direccion"));
                System.out.println(rs.getString("nTelefono"));
                System.out.println(rs.getString("cedula"));
            } else {
                System.out.println("No existen medicos disponibles");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
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
    
    public ArrayList obtenerEnfermeras(){
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

}
