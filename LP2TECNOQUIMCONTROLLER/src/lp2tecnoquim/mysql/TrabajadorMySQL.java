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
import lp2tecnoquim.dao.TrabajadorDAO;
import lp2tecnoquim.model.Trabajador;

/**
 *
 * @author alulab14
 */
public class TrabajadorMySQL implements TrabajadorDAO{

    Connection con = null;
    CallableStatement cs;
    
    @Override
    public void insertar(Trabajador trabajador) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_TRABAJADOR(?,?,?)}");
            cs.setString("_NOMBRES", trabajador.getNombres());
            cs.setString("_APELLIDOS",trabajador.getApellidos());
            cs.setString("_DNI",trabajador.getDni());
            cs.setString("_CORREO",trabajador.getCorreo());
            cs.setInt("_FK_ID_ROL",trabajador.getRol().getIdRol());
            cs.setInt("_FK_ID_USUARIO",trabajador.getUsuario().getIdUsuario());
            
            cs.registerOutParameter("_ID_TRABAJADOR  ", java.sql.Types.INTEGER);
            cs.executeUpdate();
            trabajador.setId(cs.getInt("_ID_TRABAJADOR  "));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Trabajador trabajador) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_TRABAJADOR(?,?,?,?,?,?,?)}");
            cs.setInt("_ID_TRABAJADOR", trabajador.getId());
            cs.setString("_NOMBRES", trabajador.getNombres());
            cs.setString("_APELLIDOS",trabajador.getApellidos());
            cs.setString("_DNI",trabajador.getDni());
            cs.setString("_CORREO",trabajador.getCorreo());
            cs.setInt("_FK_ID_ROL", trabajador.getRol().getIdRol());
            cs.setInt("_FK_ID_USUARIO", trabajador.getUsuario().getIdUsuario());
                    
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
            cs = con.prepareCall("{call ELIMINAR_TRABAJADOR(?)}");
            cs.setInt("_ID_TRABAJADOR", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<Trabajador> listar() {
        ArrayList<Trabajador> trabajador = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_USUARIO()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Trabajador  a = new Trabajador();
                a.setId(rs.getInt("ID_TRABAJADOR"));
                a.setNombres(rs.getString("NOMBRES"));
                a.setApellidos(rs.getString("APELLIDOS"));
                a.setDni(rs.getString("DNI"));
                a.setCorreo(rs.getString("CORREO"));
                a.getRol().setIdRol(rs.getInt("FK_ID_ROL"));
                a.getUsuario().setIdUsuario(rs.getInt("FK_ID_USUARIO"));
                ///////////////////////////////////////////////
                
                
                
                
                
                trabajador.add(a);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return trabajador;
    }
    
}
