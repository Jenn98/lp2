/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.InsumoDAO;
import lp2tecnoquim.model.Insumo;


/**
 *
 * @author alulab14
 */
public class InsumoMySQL implements InsumoDAO{

    Connection con = null;
    Statement st = null;
    
    @Override
    public void insertar(Insumo insumo) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO INSUMO (NOMBRE ,GRANULARIDAD ,COLOR ,CANTIDAD,UNIDAD) VALUES('"+insumo.getNombre()+"','"+insumo.getGranularidad()+"'"
                    + "'"+insumo.getColor()+"','"+insumo.getCantidad()+"','"+insumo.getUnidad()+"')");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Insumo insumo) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("UPDATE INSUMO SET NOMBRE = '"+insumo.getNombre()+"', GRANULARIDAD = '"+insumo.getGranularidad()+"'   , COLOR =  '"+insumo.getColor()+"'  ,"
                    + "CANTIDAD =  '"+insumo.getCantidad()+"'   , UNIDAD=  '"+insumo.getUnidad()+"' WHERE ID_INSUMO = '"+insumo.getId()+"'");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void eliminar(int id) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("DELETE FROM INSUMO WHERE ID_INSUMO = '"+id+"'");
        }catch(ClassNotFoundException | SQLException ex){
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
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM INSUMO");
            while(rs.next()){
                Insumo e = new Insumo();
                e.setId(rs.getInt("ID_INSUMO"));
                e.setNombre(rs.getString("NOMBRE"));
                e.setGranularidad(rs.getFloat("GRANULARIDAD"));
                e.setColor(rs.getString("COLOR"));
                e.setCantidad(rs.getFloat("CANTIDAD"));
                e.setUnidad(rs.getString("UNIDAD"));
                insumos.add(e);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return insumos;
    }
    
}
