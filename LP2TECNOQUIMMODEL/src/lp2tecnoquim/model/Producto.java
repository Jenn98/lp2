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
public class Producto {
    
    
    private int idProducto;
    private String nombre;
    private float granularidad;
    private String presentacion;
    private Instructivo instructivo;
    
    public Producto(){
        
        
    }
    public Producto(  String n,  float g, String p, Instructivo i){
        nombre=n;
        granularidad=g;
        presentacion=p;
        instructivo=i;
    }
    

    public int getIdProducto() {
        return idProducto;
    }


    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 
    public float getGranularidad() {
        return granularidad;
    }


    public void setGranularidad(float granularidad) {
        this.granularidad = granularidad;
    }

    public String getPresentacion() {
        return presentacion;
    }


    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }


    public Instructivo getInstructivo() {
        return instructivo;
    }

 
    public void setInstructivo(Instructivo instructivo) {
        this.instructivo = instructivo;
    }

    public void mostrar(){
        
    };
    
}
