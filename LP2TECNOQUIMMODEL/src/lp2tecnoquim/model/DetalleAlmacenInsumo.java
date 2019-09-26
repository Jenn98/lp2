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
public class DetalleAlmacenInsumo {
    private int id;
    private Insumo insumo;
    private int nLote;
    private Date periodo; 
    private int stock;
    private Almacen almacen;

    public DetalleAlmacenInsumo() {
    }

    public DetalleAlmacenInsumo(Insumo insumo, int nLote, Date periodo, int stock, Almacen almacen) {
        this.insumo = insumo;
        this.nLote = nLote;
        this.periodo = periodo;
        this.stock = stock;
        this.almacen = almacen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public int getnLote() {
        return nLote;
    }

    public void setnLote(int nLote) {
        this.nLote = nLote;
    }

    public Date getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    
    
    
}
