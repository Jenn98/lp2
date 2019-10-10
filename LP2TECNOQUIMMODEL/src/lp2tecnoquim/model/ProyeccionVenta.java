/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alulab14
 */
public class ProyeccionVenta {
    
    private int id;
    private Date periodo; 
    private ArrayList<LineaProyeccion >proyecciones;

    public ProyeccionVenta() {
    }

    public ProyeccionVenta(Date periodo, ArrayList<LineaProyeccion> proyecciones) {
        this.periodo = periodo;
        this.proyecciones = proyecciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

    public ArrayList<LineaProyeccion > getProyecciones() {
        return proyecciones;
    }

    public void setProyecciones(ArrayList<LineaProyeccion > proyecciones) {
        this.proyecciones = proyecciones;
    }

    public void generarReporte(){
        
        
    }
}
