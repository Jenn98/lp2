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
import lp2tecnoquim.dao.DetalleAlmacenProductoDAO;
import lp2tecnoquim.model.DetalleAlmacenProducto;
import lp2tecnoquim.model.EstadoMaterial;

/**
 *
 * @author Carlos Sosa
 */
public class DetalleAlmacenProductoMySQL implements DetalleAlmacenProductoDAO {
    Connection con = null;
    CallableStatement cs;
    private int estado;

    @Override
    public void insertar(DetalleAlmacenProducto detalleAlmacenProducto) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_DETALLE_ALMACEN_PRODUCTO(?,?,?,?,?,?)}");
            cs.setInt("_FK_ID_PRODUCTO", detalleAlmacenProducto.getProducto().getIdProducto());
            cs.setInt("_FK_ID_ALMACEN", detalleAlmacenProducto.getAlmacen().getIdAlmacen());
            cs.setInt("_NUM_LOTE", detalleAlmacenProducto.getnLote());
            cs.setDate("_PERIODO", new java.sql.Date(detalleAlmacenProducto.getPeriodo().getTime()));
            cs.setInt("_STOCK", detalleAlmacenProducto.getStock());
            cs.setInt("_CALIDAD", 0);
            
            cs.registerOutParameter("_ID_DET_ALM_PROD", java.sql.Types.INTEGER);
            
            cs.executeUpdate();
            detalleAlmacenProducto.setId(cs.getInt("_ID_DET_ALM_PROD"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(DetalleAlmacenProducto detalleAlmacenProducto) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_DETALLE_ALMACEN_PRODUCTO(?,?,?,?,?,?)}");
            cs.setInt("_ID_DET_ALM_PROD", detalleAlmacenProducto.getId());
            cs.setInt("_FK_ID_PRODUCTO", detalleAlmacenProducto.getProducto().getIdProducto());
            cs.setInt("_FK_ID_ALMACEN", detalleAlmacenProducto.getAlmacen().getIdAlmacen());
            cs.setInt("_NUM_LOTE", detalleAlmacenProducto.getnLote());
            cs.setDate("_PERIODO", new java.sql.Date(detalleAlmacenProducto.getPeriodo().getTime()));
            cs.setInt("_STOCK", detalleAlmacenProducto.getStock());
            cs.setInt("_CALIDAD", detalleAlmacenProducto.getEstado().ordinal());
                    
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
            cs = con.prepareCall("{call ELIMINAR_DETALLE_ALMACEN_PRODUCTO(?)}");
            cs.setInt("_ID_DET_ALM_PROD", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<DetalleAlmacenProducto> listar() {
        ArrayList<DetalleAlmacenProducto> detalleAlmacenProducto = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_DETALLE_ALMACEN_PRODUCTO()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                DetalleAlmacenProducto  d = new DetalleAlmacenProducto();
                d.setId(rs.getInt("ID_DET_ALM_PROD"));
                /* Guardar insumo */
                d.getProducto().setIdProducto(rs.getInt("ID_PRODUCTO"));
                d.getProducto().setNombre(rs.getString("NOMBRE"));
                d.getProducto().setGranularidad(rs.getFloat("GRANULARIDAD"));
                d.getProducto().setPresentacion(rs.getString("PRESENTACION"));
                /* Fin guardar insumo */
                d.setnLote(rs.getInt("NUM_LOTE"));
                d.setPeriodo(new java.util.Date(rs.getDate("PERIODO").getTime()));
                d.setStock(rs.getInt("STOCK"));
                /* Guardar Almacen */
                d.getAlmacen().setIdAlmacen(rs.getInt("ID_ALMACEN"));
                d.getAlmacen().setTipo(rs.getString("TIPO"));
                d.getAlmacen().setDireccion(rs.getString("DIRECCION"));
                /* --- Guardar Trabajador */
                d.getAlmacen().getTrabajador().setId(rs.getInt("ID_TRABAJADOR"));
                d.getAlmacen().getTrabajador().setNombres(rs.getString("NOMBRES"));
                d.getAlmacen().getTrabajador().setApellidos(rs.getString("APELLIDOS"));
                d.getAlmacen().getTrabajador().setDni(rs.getString("DNI"));
                d.getAlmacen().getTrabajador().setCorreo(rs.getString("CORREO"));
                /* ........ Guardar Rol */
                d.getAlmacen().getTrabajador().getRol().setIdRol(rs.getInt("ID_ROL"));
                d.getAlmacen().getTrabajador().getRol().setDescripcion(rs.getString("DESCRIPCION"));
                /* ........ Fin Guardar Rol */
                /* --- Fin Guardar Trabajador */
                /* Fin Guardar Almacen */
                estado = rs.getInt("CALIDAD");
                
                switch (estado) {
                    case 0:
                        d.setEstado(EstadoMaterial.Bueno);
                        break;
                    case 1:
                        d.setEstado(EstadoMaterial.Corregido);
                        break;
                    default:
                        d.setEstado(EstadoMaterial.Pendiente);
                        break;
                }
                
                detalleAlmacenProducto.add(d);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return detalleAlmacenProducto;
    }
}
