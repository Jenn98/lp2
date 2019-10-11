/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.model;

import java.util.Date;

/**
 *
 * @author alulab14
 */
public class DetalleMaquinaria {
    private int idDetalleM;
    private boolean activo;
    private Maquinaria maquinaria;
    private Date fecha;

    public DetalleMaquinaria(int idDetalleM, boolean estado, Maquinaria maquinaria, Date fecha) {
        this.idDetalleM = idDetalleM;
        this.activo = estado;
        this.maquinaria = maquinaria;
        this.fecha = fecha;
    }

    public DetalleMaquinaria() {
    }

    public int getIdDetalleM() {
        return idDetalleM;
    }

    public void setIdDetalleM(int idDetalleM) {
        this.idDetalleM = idDetalleM;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Maquinaria getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(Maquinaria maquinaria) {
        this.maquinaria = maquinaria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
