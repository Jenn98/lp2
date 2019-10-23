/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.model;

import java.util.Date;

/**
 *
 * @author pukurin
 */
public class Mensaje {
    private int idMensaje;
    private Date fechaEnvio;
    private Trabajador emisor;
    private Trabajador receptor;
    private String descripcion;

    public Mensaje(int idMensaje, Date fechaEnvio, Trabajador emisor, Trabajador receptor, String descripcion) {
        this.idMensaje = idMensaje;
        this.fechaEnvio = fechaEnvio;
        this.emisor = emisor;
        this.receptor = receptor;
        this.descripcion = descripcion;
    }

    public Mensaje() {
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Trabajador getEmisor() {
        return emisor;
    }

    public void setEmisor(Trabajador emisor) {
        this.emisor = emisor;
    }

    public Trabajador getReceptor() {
        return receptor;
    }

    public void setReceptor(Trabajador receptor) {
        this.receptor = receptor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }  
}
