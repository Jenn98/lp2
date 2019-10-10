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
import lp2tecnoquim.dao.LineaOrdenDAO;
import lp2tecnoquim.model.EstadoMaterial;
import lp2tecnoquim.model.LineaOrden;

/**
 *
 * @author pukurin
 */
public class LineaOrdenMySQL implements LineaOrdenDAO {
    Connection con = null;
    CallableStatement cs;
    private int estado;
    
    @Override
    public void insertar(LineaOrden lineaOrden, int idOrden) {
         try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_LINEA_ORDEN_PROD(?,?,?,?,?)}");
            
            cs.setInt("_FK_ID_PROD", lineaOrden.getProducto().getIdProducto());
            cs.setInt("_FK_ORDEN_PROD", idOrden);
            cs.setInt("_CANT_PROD", lineaOrden.getCantProducto());
            cs.setInt("_ESTADO_CALIDAD", 0);
            
            cs.registerOutParameter("_ID_LINEAORD", java.sql.Types.INTEGER);
            cs.executeUpdate();
            lineaOrden.setIdLineaOrden(cs.getInt("_ID_LINEAORD"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(LineaOrden lineaOrden, int idOrden) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_LINEA_ORDEN_PROD(?,?,?,?)}");
            cs.setInt("_ID_LINEAORD", lineaOrden.getIdLineaOrden());
            cs.setInt("_FK_ID_PROD", lineaOrden.getProducto().getIdProducto());
            cs.setInt("_FK_ORDEN_PROD", idOrden);
            cs.setInt("_CANT_PROD", lineaOrden.getCantProducto());
            cs.setInt("_ESTADO_CALIDAD", lineaOrden.getEstadoCalidad().ordinal());
            
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
            cs = con.prepareCall("{call ELIMINAR_LINEA_ORDEN_PROD(?)}");
            cs.setInt("_ID_LINEAORD", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }


    @Override
    public ArrayList<LineaOrden> listar(int idOrden) {
       ArrayList<LineaOrden> lineasOrden = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_LINEA_ORDEN_PROD(?)}");
            cs.setInt("_FK_ID_ORDEN_PROD", idOrden);
            
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                LineaOrden  l = new LineaOrden();
                
                l.setIdLineaOrden(cs.getInt("ID_LINEAORD"));
                l.setCantProducto(cs.getInt("CANT_PROD"));
                l.getProducto().setIdProducto(cs.getInt("ID_PROD"));
                l.getProducto().setNombre(cs.getString("NOMBRE"));
                l.getProducto().setPresentacion(cs.getString("PRESENTACION"));
                l.getProducto().setGranularidad(cs.getFloat("GRANULARIDAD"));
                l.getProducto().getInstructivo().setId(cs.getInt("ID_INSTRUCTIVO"));
                l.getProducto().getInstructivo().setActividades(cs.getString("ACTIVIDADES"));
                
                estado = cs.getInt("ESTADO_CALIDAD");
                
                switch (estado) {
                    case 0:
                        l.setEstadoCalidad(EstadoMaterial.Bueno);
                        break;
                    case 1:
                        l.setEstadoCalidad(EstadoMaterial.Corregido);
                        break;
                    default:
                        l.setEstadoCalidad(EstadoMaterial.Pendiente);
                        break;
                }
                
                lineasOrden.add(l);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return lineasOrden;
    }
}
