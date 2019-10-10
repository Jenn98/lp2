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
import lp2tecnoquim.dao.LineaInsumoDAO;
import lp2tecnoquim.model.LineaInsumo;

/**
 *
 * @author Carlos Sosa
 */
public class LineaInsumoMySQL implements LineaInsumoDAO {
    Connection con = null;
    CallableStatement cs;
    
    @Override
    public void insertar(LineaInsumo lineaInsumo, int idInstructivo) {
         try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_LINEA_INSUMO(?,?,?,?,?)}");
            cs.setInt("_FK_ID_INSUMO", lineaInsumo.getInsumo().getId());
            cs.setInt("_FK_ID_INSTRUCTIVO", idInstructivo);
            cs.setInt("_CANTIDAD", lineaInsumo.getCantInsumo());
            //cs.setFloat("_CANTIDAD", lineaInsumo.getCantInsumo());
            cs.setBoolean("_ESTADO", true);
            
            cs.registerOutParameter("_ID_LINEA_INS", java.sql.Types.INTEGER);
            cs.executeUpdate();
            
            lineaInsumo.setIdLineaI(cs.getInt("_ID_LINEA_INS"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(LineaInsumo lineaInsumo, int idInstructivo) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_LINEA_INSUMO(?,?,?,?,?)}");
            cs.setInt("_ID_LINEA_INS", lineaInsumo.getIdLineaI());
            cs.setInt("_FK_ID_INSUMO", lineaInsumo.getInsumo().getId());
            cs.setInt("_FK_ID_INSTRUCTIVO", idInstructivo);
            cs.setInt("_CANTIDAD", lineaInsumo.getCantInsumo()); 
            //cs.setFloat("_CANTIDAD", lineaInsumo.getCantInsumo());
            cs.setBoolean("_ESTADO", lineaInsumo.isEstado());
            cs.executeUpdate();
            
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
            cs = con.prepareCall("{call ELIMINAR_LINEA_INSUMO(?)}");
            cs.setInt("_ID_LINEA_INS", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }


    @Override
    public ArrayList<LineaInsumo> listar(int idInstructivo) {
       ArrayList<LineaInsumo> lineaInsumos = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_LINEA_INSUMO(?)}");
            cs.setInt("_FK_ID_INSTRUCTIVO", idInstructivo);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                LineaInsumo  l = new LineaInsumo();
                
                l.setIdLineaI(cs.getInt("ID_LINEA_INS"));
                l.setCantInsumo(cs.getInt("CANTIDAD"));
                //l.setCantInsumo(cs.getFloat("CANTIDAD"));
                l.setEstado(cs.getBoolean("ESTADO"));
                l.getInsumo().setId(cs.getInt("ID_INSUMO"));
                l.getInsumo().setNombre(cs.getString("NOMBRE"));
                l.getInsumo().setColor(cs.getString("COLOR"));
                l.getInsumo().setGranularidad(cs.getFloat("GRANULARIDAD"));
                l.getInsumo().setUnidad(cs.getString("UNIDAD"));
     
                lineaInsumos.add(l);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return lineaInsumos;
    }
}
