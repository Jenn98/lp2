package lp2tecnoquim.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.PoliticaStockDAO;
import lp2tecnoquim.model.PoliticaStock;

public class PoliticaDeStockMySQL implements PoliticaStockDAO{

    Connection con = null;
    CallableStatement cs;

    @Override
    public void insertar(PoliticaStock politica) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_POLITICA_DE_STOCK(?,?,?)}");
            cs.setInt("_FK_ID_PROD", politica.getProducto().getIdProducto());
            cs.setInt("_CANT_MAX",politica.getCantMax());
            cs.setInt("_CANT_MIN",politica.getCantMin());
            
            cs.registerOutParameter("_ID_PLT_STOCK", java.sql.Types.INTEGER);
            cs.executeUpdate();
            politica.setId(cs.getInt("_ID_PLT_STOCK"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(PoliticaStock politica) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_POLITICA_DE_STOCK(?,?,?,?)}");
            cs.setInt("_ID_PLT_STOCK", politica.getId());
            cs.setInt("_CANT_MAX", politica.getCantMax());
            cs.setInt("_CANT_MIN", politica.getCantMin());
            cs.setInt("_FK_ID_PROD", politica.getProducto().getIdProducto());
                    
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
            cs = con.prepareCall("{call ELIMINAR_POLITICA_DE_STOCK(?)}");
            cs.setInt("_ID_PLT_STOCK", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<PoliticaStock> listar() {
        ArrayList<PoliticaStock> politica = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_POLITICA_DE_STOCK()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                PoliticaStock  a = new PoliticaStock();
                a.setId(rs.getInt("ID_PLT_STOCK"));
                a.setCantMax(rs.getInt("CANT_MAX"));
                a.setCantMin(rs.getInt("CANT_MIN"));
                a.getProducto().setIdProducto(rs.getInt("FK_ID_PROD"));
                ///////////////////////////////////////////////
                
                politica.add(a);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return politica;
    }
    
}
