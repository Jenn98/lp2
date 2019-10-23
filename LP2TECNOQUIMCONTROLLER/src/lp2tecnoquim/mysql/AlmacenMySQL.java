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
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.AlmacenDAO;
import lp2tecnoquim.model.Almacen;

/**
 *
 * @author alulab14
 */
public class AlmacenMySQL implements AlmacenDAO{
    
    Connection con = null;
    CallableStatement cs;

    @Override
    public void insertar(Almacen almacen) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_ALMACEN(?,?,?,?)}");
            cs.setInt("_ID_TRABAJADOR", almacen.getTrabajador().getId());
            cs.setString("_DIRECCION",almacen.getDireccion());
            cs.setString("_TIPO", almacen.getTipo());
            
            cs.registerOutParameter("_ID_ALMACEN", java.sql.Types.INTEGER);
            cs.executeUpdate();
            almacen.setIdAlmacen(cs.getInt("_ID_ALMACEN"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Almacen almacen) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_ALMACEN(?,?,?,?)}");
            cs.setInt("_ID_ALUMNO", almacen.getIdAlmacen());
            cs.setInt("_ID_TRABAJADOR", almacen.getTrabajador().getId());
            cs.setString("_DIRECCION",almacen.getDireccion());
            cs.setString("_TIPO", almacen.getTipo());
                    
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
            cs = con.prepareCall("{call ELIMINAR_ALMACEN(?)}");
            cs.setInt("_ID_TRABAJADOR", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<Almacen> listar() {
        ArrayList<Almacen> almacen = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_ALMACEN()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Almacen  a = new Almacen();
                a.setIdAlmacen(rs.getInt("ID_ALMACEN"));
                a.setDireccion(rs.getString("DIRECCION"));
                a.setTipo(rs.getString("TIPO"));
                a.getTrabajador().setId(rs.getInt("ID_"));
                ///////////////////////////////////////////////
                
                
                
                
                
                almacen.add(a);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return almacen;
    }
    
}
