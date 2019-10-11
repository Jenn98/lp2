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
import lp2tecnoquim.dao.DetalleMaquinariaDAO;
import lp2tecnoquim.model.DetalleMaquinaria;

/**
 *
 * @author Carlos Sosa
 */
public class DetalleMaquinariaMySQL implements DetalleMaquinariaDAO {
    Connection con = null;
    CallableStatement cs;

    @Override
    public void insertar(DetalleMaquinaria detalleMaquinaria, int idPMP) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_DET_MAQUINARIA(?,?,?,?,?)}");
            cs.setBoolean("_ESTADO", detalleMaquinaria.isActivo());
            cs.setDate("_FECHA", new java.sql.Date(detalleMaquinaria.getFecha().getTime()));
            cs.setInt("_FK_ID_PMP", idPMP);
            cs.setInt("_FK_ID_MAQUINARIA", detalleMaquinaria.getMaquinaria().getId());
            cs.registerOutParameter("_ID_DET_MAQ", java.sql.Types.INTEGER);
            
            cs.executeUpdate();
            
            detalleMaquinaria.setIdDetalleM(cs.getInt("_ID_DET_MAQ"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(DetalleMaquinaria detalleMaquinaria, int idPMP) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_DET_MAQUINARIA(?,?,?,?,?,?)}");
            cs.setInt("_ID_DET_MAQ", detalleMaquinaria.getIdDetalleM());
            cs.setBoolean("_ESTADO", detalleMaquinaria.isActivo());
            cs.setDate("_FECHA", new java.sql.Date(detalleMaquinaria.getFecha().getTime()));
            cs.setInt("_FK_ID_PMP", idPMP);
            cs.setInt("_FK_ID_MAQUINARIA", detalleMaquinaria.getMaquinaria().getId());
                    
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
            cs = con.prepareCall("{call ELIMINAR_DET_MAQUINARIA(?)}");
            cs.setInt("_ID_DET_MAQ", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<DetalleMaquinaria> listar(int idPMP) {
        ArrayList<DetalleMaquinaria> detalleMaquinaria = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_DET_MAQUINARIA(?)}");
            cs.setInt("_FK_ID_PMP", idPMP);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                DetalleMaquinaria  d = new DetalleMaquinaria();
                
                d.setIdDetalleM(rs.getInt("ID_DET_MAQ"));
                d.setFecha(new java.util.Date(rs.getDate("FECHA").getTime()));
                d.setActivo(rs.getBoolean("ESTADO"));
                d.getMaquinaria().setId(rs.getInt("ID_MAQUINARIA"));
                d.getMaquinaria().setNombre(rs.getString("NOMBRE"));
                d.getMaquinaria().setTipo(rs.getString("TIPO"));
                
                detalleMaquinaria.add(d);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return detalleMaquinaria;
    }
}
