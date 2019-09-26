/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.model;

/**
 *
 * @author alulab14
 */
public class Almacen {
    
    private int idAlmacen;
    private Trabajador trabajador;
    private String direccion;
    private String tipo;

    public Almacen(){
        
        
    }
    
    public Almacen( Trabajador t, String d,  String c){
        
        trabajador=t;
        direccion =d;
        tipo=c;
    }
    
    
    
    public int getIdAlmacen() {
        return idAlmacen;
    }
    
    public Trabajador getTrabajador() {
        return trabajador;
    }
    
    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
