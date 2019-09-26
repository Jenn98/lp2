/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.model;

import java.util.ArrayList;

/**
 *
 * @author alulab14
 */
public class Instructivo {

    private int id;
    private ArrayList <LineaInsumo> insumos;
    private String actividades;
    
    public Instructivo (){
        
    }

    public Instructivo(ArrayList<LineaInsumo> insumos, String actividades) {
        this.insumos = insumos;
        this.actividades = actividades;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ArrayList <LineaInsumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(ArrayList <LineaInsumo> insumos) {
        this.insumos = insumos;
    }


    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }


    public void mostrar(){
        
    };

    
}
