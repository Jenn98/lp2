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
public class Insumo {

    private int id;
    private String nombre;
    private float granularidad;
    private String color;
    private float cantidad;
    private String unidad;
    
   
    public Insumo(){
        
    }
    
    public Insumo( String n, float g, String c, float ca,String u){
        nombre=n;
        granularidad=g;
        color=c;
        cantidad=ca;
        unidad=u;
    }
    
  
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

  
    public void setColor(String color) {
        this.color = color;
    }

    public float getCantidad() {
        return cantidad;
    }

   
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void mostrar(){
        
    };
}
