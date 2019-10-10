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
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.RolDAO;
import lp2tecnoquim.model.Rol;

/**
 *
 * @author alulab14
 */
public class RolMySQL implements RolDAO{
    Connection con = null;
    CallableStatement cs;
    
    @Override
    public void insertar(Rol rol) {
         try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_ROL(?,?)}");
            
            cs.setString("_DESCRIPCION", rol.getDescripcion());
            
            cs.registerOutParameter("_ID_ROL", java.sql.Types.INTEGER);
            cs.executeUpdate();
            rol.setIdRol(cs.getInt("_ID_ROL"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Rol rol) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_ROL(?,?)}");
            cs.setInt("_ID_ROL", rol.getIdRol());
            cs.setString("_DESCRIPCION", rol.getDescripcion());          
            cs.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }



    @Override
    public ArrayList<Rol> listar() {
       ArrayList<Rol> roles = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ROL");
            while(rs.next()){
                Rol e = new Rol();
                e.setIdRol(rs.getInt("ID_ROL"));
                e.setDescripcion(rs.getString("DESCRIPCION"));
                
                roles.add(e);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return roles;
    }
    
}
