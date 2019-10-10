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
import lp2tecnoquim.dao.InsumoDAO;
import lp2tecnoquim.model.Insumo;

/**
 *
 * @author pukurin
 */
public class InsumoMySQL implements InsumoDAO {
    Connection con = null;
    CallableStatement cs;
    
    @Override
    public void insertar(Insumo insumo) {
         try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_INSUMO(?,?,?,?,?,?)}");
            cs.setString("_NOMBRE", insumo.getNombre());
            cs.setFloat("_GRANULARIDAD", insumo.getGranularidad());
            cs.setString("_COLOR", insumo.getColor());
            cs.setFloat("_CANTIDAD", insumo.getCantidad());
            cs.setString("_UNIDAD", insumo.getUnidad());
            
            cs.registerOutParameter("_ID_INSUMO", java.sql.Types.INTEGER);
            cs.executeUpdate();
            insumo.setId(cs.getInt("_ID_INSUMO"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Insumo insumo) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_INSUMO(?,?,?,?,?,?)}");
            cs.setInt("_ID_INSUMO", insumo.getId());
            cs.setString("_NOMBRE", insumo.getNombre());
            cs.setFloat("_GRANULARIDAD", insumo.getGranularidad());
            cs.setString("_COLOR", insumo.getColor());
            cs.setFloat("_CANTIDAD", insumo.getCantidad());
            cs.setString("_UNIDAD", insumo.getUnidad());
            
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
            cs = con.prepareCall("{call ELIMINAR_INSUMO(?)}");
            cs.setInt("_ID_INSUMO", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }


    @Override
    public ArrayList<Insumo> listar() {
       ArrayList<Insumo> insumos = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_INSUMO()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Insumo  i = new Insumo();
                
                i.setId(rs.getInt("ID_INSUMO"));
                i.setNombre(rs.getString("NOMBRE"));
                i.setColor(rs.getString("COLOR"));
                i.setGranularidad(rs.getFloat("GRANULARIDAD"));
                i.setCantidad(rs.getFloat("CANTIDAD"));
                i.setUnidad(rs.getString("UNIDAD"));
     
                insumos.add(i);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return insumos;
    }
}
