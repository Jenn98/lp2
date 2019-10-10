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
public class LineaOrden {
    
    private int idLineaOrden;
    private Producto producto;
    private int cantProducto;
    private EstadoMaterial estadoCalidad;
    
    public LineaOrden(){
        
    }
    
    public LineaOrden(     Producto p,  int c,   EstadoMaterial e){
        producto=p;
        cantProducto=c;
        estadoCalidad=e;
    }

    public void setIdLineaOrden(int idLineaOrden) {
        this.idLineaOrden = idLineaOrden;
    }
    
    
     public int getIdLineaOrden() {
        return idLineaOrden;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public int getCantProducto() {
        return cantProducto;
    }
    
    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }
    
    public EstadoMaterial getEstadoCalidad() {
        return estadoCalidad;
    }
    
    public void setEstadoCalidad(EstadoMaterial estadoCalidad) {
        this.estadoCalidad = estadoCalidad;
    }

    
    
}
