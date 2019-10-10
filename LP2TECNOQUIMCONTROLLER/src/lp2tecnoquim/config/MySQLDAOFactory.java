/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.config;

import lp2tecnoquim.dao.*;
import lp2tecnoquim.mysql.*;

/**
 *
 * @author alulab14
 */
public class MySQLDAOFactory extends DAOFactory {
    
    public MySQLDAOFactory(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public AlmacenDAO getAlmacenDAO() {
        return new AlmacenMySQL();
    }

    @Override
    public DetalleAlmacenInsumoDAO getDetalleAlmacenInsumoDAO() {
        return new DetalleAlmacenInsumoMySQL();    
    }

    @Override
    public DetalleAlmacenProductoDAO getDetalleAlmacenProductoDAO() {
        return new DetalleAlmacenProductoMySQL();
    }

    @Override
    public DetalleMaquinariaDAO getDetalleMaquinariaDAO() {
        return new DetalleMaquinariaMySQL();
    }

    @Override
    public InstructivoDAO getInstructivoDAO() {
        return new InstructivoMySQL();
    }

    @Override
    public InsumoDAO getInsumoDAO() {
        return new InsumoMySQL();
    }

    @Override
    public LineaInsumoDAO getLineaInsumoDAO() {
        return new LineaInsumoMySQL();
    }

    @Override
    public LineaOrdenDAO getLineaOrdenDAO() {
        return new LineaOrdenMySQL();
    }

    @Override
    public LineaProyeccionDAO getLineaProyeccionDAO() {
        return new LineaProyeccionMySQL();
    }

    @Override
    public MaquinariaDAO getMaquinariaDAO() {
        return new MaquinariaMySQL();
    }

    @Override
    public OrdenProduccionDAO getOrdenProduccionDAO() {
        return new OrdenProduccionMySQL();
    }

    @Override
    public PlanMaestroProduccionDAO getPlanMaestroProduccionDAO() {
        return new PlanMaestroProduccionMySQL();
    }

    @Override
    public PoliticaStockDAO getPoliticaStockDAO() {
        return new PoliticaDeStockMySQL();
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoMySQL();
    }

    @Override
    public ProyeccionVentaDAO getProyeccionVentaDAO() {
        return new ProyeccionVentaMySQL();
    }

    @Override
    public RolDAO getRolDAO() {
        return new RolMySQL();
    }

    @Override
    public TrabajadorDAO getTrabajadorDAO() {
        return new TrabajadorMySQL();
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioMySQL();
    }
   
    
}
