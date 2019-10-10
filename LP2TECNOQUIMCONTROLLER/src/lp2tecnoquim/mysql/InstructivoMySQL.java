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
import lp2tecnoquim.dao.InstructivoDAO;
import lp2tecnoquim.model.Instructivo;

/**
 *
 * @author Carlos Sosa
 */
public class InstructivoMySQL implements InstructivoDAO {
    Connection con = null;
    CallableStatement cs;
    
    @Override
    public void insertar(Instructivo instructivo) {
         try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_INSTRUCTIVO(?,?)}");
            
            cs.setString("_ACTIVIDADES", instructivo.getActividades());
            
            cs.registerOutParameter("_ID_INSTRUCTIVO", java.sql.Types.INTEGER);
            cs.executeUpdate();
            instructivo.setId(cs.getInt("_ID_INSTRUCTIVO"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Instructivo instructivo) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_INSTRUCTIVO(?,?)}");
            cs.setInt("_ID_INSTRUCTIVO", instructivo.getId());
            cs.setString("_ACTIVIDADES", instructivo.getActividades());          
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
            cs = con.prepareCall("{call ELIMINAR_INSTRUCTIVO(?)}");
            cs.setInt("_ID_INSTRUCTIVO", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }


    @Override
    public ArrayList<Instructivo> listar() {
       ArrayList<Instructivo> instructivos = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_INSTRUCTIVO()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Instructivo  i = new Instructivo();
                
                i.setId(cs.getInt("ID_INSTRUCTIVO"));
                i.setActividades(cs.getString("ACTIVIDADES"));
     
                instructivos.add(i);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return instructivos;
    }
}
