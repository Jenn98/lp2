package lp2tecnoquim.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.ProductoDAO;
import lp2tecnoquim.model.Producto;

public class ProductoMySQL implements ProductoDAO{

    Connection con = null;
    CallableStatement cs;

    @Override
    public void insertar(Producto producto) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PRODUCTO(?,?,?,?)}");
            cs.setString("_NOMBRE", producto.getNombre());
            cs.setFloat("_GRANULARIDAD",producto.getGranularidad());
            cs.setString("_PRESENTACION",producto.getPresentacion());
            cs.setInt("_FK_ID_INSTRUCTIVO",producto.getInstructivo().getId());
            
            cs.registerOutParameter("_ID_PROD", java.sql.Types.INTEGER);
            cs.executeUpdate();
            producto.setIdProducto(cs.getInt("_ID_PROD"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(Producto producto) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PRODUCTO(?,?,?,?,?)}");
            cs.setInt("_ID_PROD", producto.getIdProducto());
            cs.setString("_NOMBRE", producto.getNombre());
            cs.setFloat("_GRANULARIDAD",producto.getGranularidad());
            cs.setString("_PRESENTACION",producto.getPresentacion());
            cs.setInt("_FK_ID_INSTRUCTIVO",producto.getInstructivo().getId());
                    
            cs.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void eliminar(int idProducto) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_PRODUCTO(?)}");
            cs.setInt("_ID_PROD", idProducto);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<Producto> listar() {
        ArrayList<Producto> producto = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PRODUCTOS()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Producto  a = new Producto();
                a.setIdProducto(rs.getInt("ID_PROD"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setGranularidad(rs.getFloat("GRANULARIDAD"));
                a.setPresentacion(rs.getString("PRESENTACION"));
                a.getInstructivo().setId(rs.getInt("FK_ID_INSTRUCTIVO"));
                ///////////////////////////////////////////////
                
                producto.add(a);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return producto;
    }
    
}
