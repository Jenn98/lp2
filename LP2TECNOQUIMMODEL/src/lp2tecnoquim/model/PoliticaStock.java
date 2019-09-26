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
public class PoliticaStock {
    private int id;
    private Producto producto;
    private int cantMax;
    private int cantMin;

    public PoliticaStock() {
    }

    public PoliticaStock(Producto producto, int cantMax, int cantMin) {
        this.producto = producto;
        this.cantMax = cantMax;
        this.cantMin = cantMin;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantMax() {
        return cantMax;
    }

    public void setCantMax(int cantMax) {
        this.cantMax = cantMax;
    }

    public int getCantMin() {
        return cantMin;
    }

    public void setCantMin(int cantMin) {
        this.cantMin = cantMin;
    }
    
    

}
