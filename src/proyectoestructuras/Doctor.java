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
    
    private Cargo cargo;
    private Especialidad especialidad;
//    private ArrayList<Cita> citas;
    
    public Doctor(int id, String cedulaRuc, String nombApell, String direccion,
            String telefono,Cargo cargo, Especialidad especialidad) {
        super(id, cedulaRuc, nombApell, direccion, telefono);
        this.cargo = cargo;
        this.especialidad = especialidad;
    }


    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    
    @Override
    public String toString(){
        return super.toString() + "\n5. Cargo: " + this.cargo.getNombre() + "\n6"
                + ". Especialidad: "+this.especialidad.getNombre();
    }

}
