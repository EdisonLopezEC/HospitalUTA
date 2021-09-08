/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author 904ed
 */
public class ProyectoEstructuras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException, SQLException {
        Programa programa = new Programa();
        programa.menuPrincipal();
    }
}
