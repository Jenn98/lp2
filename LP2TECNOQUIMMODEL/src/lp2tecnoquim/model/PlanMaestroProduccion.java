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
public class PlanMaestroProduccion {
    private int id;
    private Date periodo;
    private Estado estado;
    private List<OrdenProduccion> ordenes;
    private List<Maquinaria> maquinarias;
    private Trabajador responsable;

    public PlanMaestroProduccion() {
    }

    public PlanMaestroProduccion(Date periodo, Estado estado, List<OrdenProduccion> ordenes, List<Maquinaria> maquinarias, Trabajador responsable) {
        this.periodo = periodo;
        this.estado = estado;
        this.ordenes = ordenes;
        this.maquinarias = maquinarias;
        this.responsable = responsable;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<OrdenProduccion> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<OrdenProduccion> ordenes) {
        this.ordenes = ordenes;
    }

    public List<Maquinaria> getMaquinarias() {
        return maquinarias;
    }

    public void setMaquinarias(List<Maquinaria> maquinarias) {
        this.maquinarias = maquinarias;
    }

    public Trabajador getResponsable() {
        return responsable;
    }

    public void setResponsable(Trabajador responsable) {
        this.responsable = responsable;
    }
    
    
    public void generarReporte(){
        
    }
    

    
}
