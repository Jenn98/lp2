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
import lp2tecnoquim.dao.PoliticaStockDAO;
import lp2tecnoquim.model.PoliticaStock;
import lp2tecnoquim.model.Producto;


/**
 *
 * @author alulab14
 */
public class PoliticaStockMySQL implements PoliticaStockDAO {
    
    Connection con = null;
    Statement st = null;

    @Override
    public void insertar(PoliticaStock politica) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            Producto producto = politica.getProducto();
            st.executeUpdate("INSERT INTO POLITICA_DE_STOCK (FK_ID_PROD ,CANT_MAX ,CANT_MIN ) VALUES('"+producto.getIdProducto()+"','"+politica.getCantMax()+"','"+politica.getCantMin()+"')");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(PoliticaStock politica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            st.executeUpdate("DELETE FROM POLITICA_DE_STOCK WHERE ID_PLT_STOCK = '"+id+"'");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<PoliticaStock> listar() {
        ArrayList<PoliticaStock> politicas = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.urlMySQL, DBManager.userMySQL, DBManager.passwordMySQL);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM POLITICA_DE_STOCK");
            while(rs.next()){
                PoliticaStock e = new PoliticaStock();
                e.setId(rs.getInt("ID_PLT_STOCK"));
                e.setCantMax(rs.getInt("CANT_MAX"));
                e.setCantMin(rs.getInt("CANT_MIN"));
                politicas.add(e);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return politicas;
    }
    
}
