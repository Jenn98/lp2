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
import lp2tecnoquim.dao.LineaProyeccionDAO;
import lp2tecnoquim.model.LineaProyeccion;

/**
 *
 * @author Carlos Sosa
 */
public class LineaProyeccionMySQL implements LineaProyeccionDAO{
    Connection con = null;
    CallableStatement cs;
    
    @Override
    public void insertar(LineaProyeccion lineaProyeccion, int idProyeccion) {
         try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_LINEA_PROYECCION(?,?,?,?)}");
            
            cs.setInt("_FK_ID_PROD", lineaProyeccion.getProducto().getIdProducto());
            cs.setInt("_FK_ID_PROY_VENTA", idProyeccion);
            cs.setInt("_CANTIDAD", lineaProyeccion.getCantidad());
            
            cs.registerOutParameter("_ID_LIN_PROY", java.sql.Types.INTEGER);
            cs.executeUpdate();
            lineaProyeccion.setId(cs.getInt("_ID_LIN_PROY"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(LineaProyeccion lineaProyeccion, int idProyeccion) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_LINEA_PROYECCION(?,?,?,?)}");
            cs.setInt("_ID_LIN_PROY", lineaProyeccion.getId());
            cs.setInt("_FK_ID_PROD", lineaProyeccion.getProducto().getIdProducto());
            cs.setInt("_FK_ID_PROY_VENTA", idProyeccion);
            cs.setInt("_CANTIDAD", lineaProyeccion.getCantidad());
            
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
            cs = con.prepareCall("{call ELIMINAR_LINEA_PROYECCION(?)}");
            cs.setInt("_ID_LIN_PROY", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }


    @Override
    public ArrayList<LineaProyeccion> listar(int idProyeccion) {
       ArrayList<LineaProyeccion> lineasProyeccion = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_LINEA_PROYECCION(?)}");
            cs.setInt("_FK_ID_PROY_VENTA", idProyeccion);
            
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                LineaProyeccion  l = new LineaProyeccion();
                
                l.setId(cs.getInt("ID_LIN_PROY"));
                l.setCantidad(cs.getInt("CANTIDAD"));
                l.getProducto().setIdProducto(cs.getInt("ID_PRODUCTO"));
                l.getProducto().setNombre(cs.getString("NOMBRE"));
                l.getProducto().setPresentacion(cs.getString("PRESENTACION"));
     
                lineasProyeccion.add(l);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return lineasProyeccion;
    }
}
