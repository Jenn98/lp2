/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.DetalleAlmacenInsumoDAO;
import lp2tecnoquim.model.DetalleAlmacenInsumo;

/**
 *
 * @author alulab14
 */
public class DetalleAlmacenInsumoMySQL implements DetalleAlmacenInsumoDAO{

    Connection con = null;
    Statement st = null;
    
    @Override
    public void insertar(DetalleAlmacenInsumo trabajador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(DetalleAlmacenInsumo trabajador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
                st.executeUpdate("DELETE FROM DETALLE_ALMACEN_INSUMO WHERE ID_DET_ALM_INS = '"+id+"'");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<DetalleAlmacenInsumo> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
