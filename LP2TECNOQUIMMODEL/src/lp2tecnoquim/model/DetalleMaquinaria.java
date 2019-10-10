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
    private boolean estado;
    private Maquinaria maquinaria;
    private Date fecha;

    public DetalleMaquinaria(int idDetalleM, boolean estado, Maquinaria maquinaria, Date fecha) {
        this.idDetalleM = idDetalleM;
        this.estado = estado;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
