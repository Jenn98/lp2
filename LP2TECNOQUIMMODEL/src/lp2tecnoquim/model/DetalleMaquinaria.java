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
    private Estado estado;
    private Maquinaria maquinaria;
    private Date fecha;

    public DetalleMaquinaria(Estado estado, Maquinaria maquinaria, Date fecha) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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
    
    public void mostrar(){
        
    };
    

    
}
