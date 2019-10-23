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
import lp2tecnoquim.dao.MensajeDAO;
import lp2tecnoquim.model.Mensaje;

/**
 *
 * @author alulab14
 */
public class MensajeMySQL implements MensajeDAO{
    
    Connection con = null;
    CallableStatement cs;

    @Override
    public void insertar(Mensaje mensaje) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_MENSAJE(?,?,?,?)}");
            cs.setInt("_ID_EMISOR", mensaje.getEmisor().getId());
            cs.setInt("_ID_RECEPTOR", mensaje.getReceptor().getId());
            cs.setString("_DESCRIPCION",mensaje.getDescripcion());
            cs.setDate("_FECHA_ENVIO", new java.sql.Date(mensaje.getFechaEnvio().getTime()));
            
            cs.registerOutParameter("_ID_MENSAJE", java.sql.Types.INTEGER);
            cs.executeUpdate();
            mensaje.setIdMensaje(cs.getInt("_ID_MENSAJE"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void eliminar(int idMensaje) {
       try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_MENSAJE(?)}");
            cs.setInt("_ID_MENSAJE", idMensaje);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<Mensaje> listar(int idReceptor) {
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_MENSAJES(?)}");
            cs.setInt("_ID_RECEPTOR", idReceptor);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Mensaje  m = new Mensaje();
                m.setIdMensaje(rs.getInt("ID_MENSAJE"));
                m.setFechaEnvio(new java.util.Date(rs.getDate("FECHA_ENVIO").getTime()));
                m.getEmisor().setApellidos(rs.getString("APELLIDO_EMISOR"));
                m.getEmisor().setNombres(rs.getString("NOMBRE_EMISOR"));
                m.getEmisor().getRol().setIdRol(rs.getInt("ID_ROL_EMISOR"));
                m.getEmisor().getRol().setDescripcion(rs.getString("DESCRIPCION_ROL_EMISOR"));
                m.setDescripcion(rs.getString("DESCRIPCION"));
                
                
                mensajes.add(m);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return mensajes;
    }
    
}
