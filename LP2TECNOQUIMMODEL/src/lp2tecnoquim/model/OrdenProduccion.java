/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author alulab14
 */
public class OrdenProduccion {
    
    private int id;
    private List<LineaOrden> lineasOrden;
    private Date fecha;

    public OrdenProduccion() {
    }

    public OrdenProduccion(List<LineaOrden> lineasOrden, Date fecha) {
        this.lineasOrden = lineasOrden;
        this.fecha = fecha;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public List<LineaOrden> getLineasOrden() {
        return lineasOrden;
    }


    public void setLineasOrden(List<LineaOrden> lineasOrden) {
        this.lineasOrden = lineasOrden;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void mostrar(){
        
    };

    
}
