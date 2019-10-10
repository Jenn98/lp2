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
public class LineaInsumo {

    private int idLineaI;
    private Insumo insumo;
    private int cantInsumo;
    private boolean estado;

    public int getIdLineaI() {
        return idLineaI;
    }

 
    public void setIdLineaI(int idLineaI) {
        this.idLineaI = idLineaI;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public int getCantInsumo() {
        return cantInsumo;
    }

    public void setCantInsumo(int cantInsumo) {
        this.cantInsumo = cantInsumo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}
