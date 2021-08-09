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
public class Doctor extends Usuario{
    
    private String cargo;
    private String especialidad;
    private ArrayList<Cita> citas;
    
    public Doctor(String cedulaRuc, String nombApell, String direccion, String telefono,String cargo, String especialidad) {
        super(cedulaRuc, nombApell, direccion, telefono);
        this.cargo = cargo;
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
    @Override
    public String toString(){
        return super.toString() + "\n5. Cargo: " + this.cargo + "\n6. Especialidad: "+this.especialidad;
    }
     public void agregarCita(Cita cita){
    
        citas.add(cita);
    
    }
}
