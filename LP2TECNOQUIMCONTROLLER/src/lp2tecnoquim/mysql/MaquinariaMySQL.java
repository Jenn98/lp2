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
import lp2tecnoquim.dao.MaquinariaDAO;
import lp2tecnoquim.model.Maquinaria;


/**
 *
 * @author alulab14
 */
public class MaquinariaMySQL implements MaquinariaDAO{
    
    Connection con = null;
    Statement st = null;

    @Override
    public void insertar(Maquinaria maquinaria) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO MAQUINARIA (NOMBRE , TIPO) VALUES('"+maquinaria.getNombre()+"','"+maquinaria.getTipo()+"' )");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Maquinaria maquinaria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Maquinaria> listar() {
        ArrayList<Maquinaria> maquinarias = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM MAQUINARIA");
            while(rs.next()){
                Maquinaria e = new Maquinaria();
                e.setId(rs.getInt("ID_MAQUINARIA"));
                e.setNombre(rs.getString("NOMBRE"));
                e.setTipo(rs.getString("TIPO"));
                maquinarias.add(e);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return maquinarias;
    }
    
    
}
