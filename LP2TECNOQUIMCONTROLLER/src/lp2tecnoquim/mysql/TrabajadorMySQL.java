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
import lp2tecnoquim.dao.TrabajadorDAO;
import lp2tecnoquim.model.Rol;

import lp2tecnoquim.model.Trabajador;
import lp2tecnoquim.model.Usuario;

/**
 *
 * @author alulab14
 */
public class TrabajadorMySQL implements TrabajadorDAO {

    Connection con = null;
    Statement st = null;
    
    
    @Override
    public void insertar(Trabajador trabajador) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            Rol rol= trabajador.getRol();
            Usuario usuario = trabajador.getUsuario();
            st.executeUpdate("INSERT INTO TRABAJADOR (  NOMBRES, APELLIDOS, DNI ,CORREO, FK_ID_ROL, FK_ID_USUARIO ) VALUES('"+trabajador.getNombres()+"',"
                    + "'"+trabajador.getApellidos()+"', '"+trabajador.getDni()+"', '"+trabajador.getCorreo()+"', '"+rol.getIdRol()+"','"+usuario.getIdUsuario()+"')");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Trabajador trabajador) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("UPDATE TRABAJADOR SET NOMBRES = '"+trabajador.getNombres()+"', APELLIDOS = '"+trabajador.getApellidos()+"' , DNI= '"+trabajador.getDni()+"'  ,"
                    + " CORREO =  '"+trabajador.getCorreo()+"'    WHERE ID_TRABAJADOR = '"+trabajador.getId()+"'");
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
            st.executeUpdate("DELETE FROM TRABAJADOR WHERE ID_TRABAJADOR = '"+id+"'");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<Trabajador> listar() {
               ArrayList<Trabajador> trabajadores = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM TRABAJADOR");
            while(rs.next()){
                Trabajador e = new Trabajador();
                e.setId(rs.getInt("ID_TRABAJADOR"));
                e.setNombres(rs.getString("NOMBRES"));
                e.setApellidos(rs.getString("APELLIDOS"));
                e.setDni(rs.getString("DNI"));
                e.setCorreo(rs.getString("CORREO"));
                
                trabajadores.add(e);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return trabajadores;
    }
    
}
