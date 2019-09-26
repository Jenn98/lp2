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
import lp2tecnoquim.dao.ProductoDAO;

import lp2tecnoquim.model.Producto;


/**
 *
 * @author alulab14
 */
public class ProductoMySQL implements ProductoDAO{

    Connection con = null;
    Statement st = null;
    @Override
    public void insertar(Producto producto) {
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            
            st.executeUpdate("INSERT INTO PRODUCTO (NOMBRE ,GRANULARIDAD , PRESENTACION ,FK_ID_INSTRUCTIVO) VALUES("+producto.getNombre()+"',"
                    + ""+producto.getGranularidad()+",'"+producto.getPresentacion()+"', '"+producto.getInstructivo().getId()+"')");
            
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Producto producto) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("UPDATE PRODUCTO SET NOMBRE = '"+producto.getNombre()+"',GRANULARIDAD= '"+producto.getGranularidad()+"' , PRESENTACION= '"+producto.getPresentacion()+"'  ,"
                    + "FK_ID_INSTRUCTIVO=  '"+producto.getInstructivo().getId()+"'   WHERE ID_PROD = '"+producto.getIdProducto()+"'");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void eliminar(int idProducto) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("DELETE FROM PRODUCTO WHERE ID_PROD = '"+idProducto+"'");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<Producto> listar() {
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTO");
            while(rs.next()){
                Producto e = new Producto();
                e.setIdProducto(rs.getInt("ID_PROD"));
                e.setNombre(rs.getString("NOMBRE"));
                e.setGranularidad(rs.getFloat("GRANULARIDAD"));
                e.setPresentacion(rs.getString("PRESENTACION"));
                productos.add(e);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return productos;
    }
    
}
