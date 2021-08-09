/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package proyectoestructuras;

/**
 *
 * @author 904ed
 */
public abstract class Usuario {

    private String cedulaRuc;
    private String nombApell;
    private String direccion;
    private String telefono;

    public Usuario(String cedulaRuc, String nombApell, String direccion, String telefono) {
        this.cedulaRuc = cedulaRuc;
        this.nombApell = nombApell;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getCedulaRuc() {
        return cedulaRuc;
    }

    public void setCedulaRuc(String cedulaRuc) {
        this.cedulaRuc = cedulaRuc;
    }

    public String getNombApell() {
        return nombApell;
    }

    public void setNombApell(String nombApell) {
        this.nombApell = nombApell;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
     
    @Override
    public String toString(){
        return "1. Nombre:"+ this.nombApell + "\n2. Cedula: "+ this.cedulaRuc
                +"\n3. Direccion: " + this.direccion + "\n4. Teléfono: "+this.telefono;
    }
    
}
