/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.OrdenProduccionDAO;
import lp2tecnoquim.model.OrdenProduccion;

/**
 *
 * @author Carlos Sosa
 */
public class OrdenProduccionMySQL implements OrdenProduccionDAO {
    Connection con = null;
    CallableStatement cs;
    
    @Override
    public void insertar(OrdenProduccion ordenProduccion, int idPMP) {
         try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_ORDENPROD(?,?,?,?)}");
            
            cs.setDate("_FECHA", new java.sql.Date(ordenProduccion.getFecha().getTime()));
            cs.setInt("_FK_ID_PMP", idPMP);
            
            cs.registerOutParameter("_ID_ORDENPROD", java.sql.Types.INTEGER);
            cs.executeUpdate();
            ordenProduccion.setId(cs.getInt("_ID_ORDENPROD"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(OrdenProduccion ordenProduccion, int idPMP) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_ORDENPROD(?,?,?)}");
            
            cs.setInt("_ID_ORDENPROD", ordenProduccion.getId());
            cs.setDate("_FECHA", new java.sql.Date(ordenProduccion.getFecha().getTime()));
            cs.setInt("_FK_ID_PMP", idPMP);
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }
    
    
    @Override
    public void eliminar(int id) {
       try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_ORDENPROD(?)}");
            cs.setInt("_ID_ORDENPROD", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }


    @Override
    public ArrayList<OrdenProduccion> listar() {
       ArrayList<OrdenProduccion> ordenProduccions = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_ORDENPROD()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                OrdenProduccion  o = new OrdenProduccion();
                
                o.setId(rs.getInt("ID_ORDENPROD"));
                o.setFecha(new java.util.Date(rs.getDate("FECHA").getTime()));
     
                ordenProduccions.add(o);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return ordenProduccions;
    }
}
