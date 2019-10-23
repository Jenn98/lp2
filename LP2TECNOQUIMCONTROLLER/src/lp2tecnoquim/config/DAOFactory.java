/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.config;

import lp2tecnoquim.dao.*;

/**
 *
 * @author alulab14
 */
public abstract class DAOFactory {
    
    public abstract AlmacenDAO getAlmacenDAO();
    public abstract DetalleAlmacenInsumoDAO getDetalleAlmacenInsumoDAO();
    public abstract DetalleAlmacenProductoDAO getDetalleAlmacenProductoDAO();
    public abstract DetalleMaquinariaDAO getDetalleMaquinariaDAO();
    public abstract InstructivoDAO getInstructivoDAO();
    public abstract InsumoDAO getInsumoDAO();
    public abstract LineaInsumoDAO getLineaInsumoDAO();
    public abstract LineaOrdenDAO getLineaOrdenDAO();
    public abstract LineaProyeccionDAO getLineaProyeccionDAO();
    public abstract MaquinariaDAO getMaquinariaDAO();
    public abstract MensajeDAO getMensajeDAO();
    public abstract OrdenProduccionDAO getOrdenProduccionDAO();
    public abstract PlanMaestroProduccionDAO getPlanMaestroProduccionDAO();
    public abstract PoliticaStockDAO getPoliticaStockDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract ProyeccionVentaDAO getProyeccionVentaDAO();
    public abstract RolDAO getRolDAO();
    public abstract TrabajadorDAO getTrabajadorDAO();
    public abstract UsuarioDAO getUsuarioDAO();
    
    public static DAOFactory getDAOFactory(){
        return new MySQLDAOFactory();
        
    }
    
}
