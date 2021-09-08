/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoestructuras;

import java.util.ArrayList;

/**
 *
 * @author 904ed
 */
public class Paciente extends Usuario{
    
    private ArrayList<Cita> citas;
    
    public Paciente(int id, String cedulaRuc, String nombApell, String direccion, String telefono) {
        super(id, cedulaRuc, nombApell, direccion, telefono);
        
        this.citas = new ArrayList<>();
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }
    
    
    public void agregarCita(Cita cita){
    
        citas.add(cita);
    
    }
    
}
