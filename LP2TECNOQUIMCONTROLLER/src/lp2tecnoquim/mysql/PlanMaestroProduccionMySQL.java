package lp2tecnoquim.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lp2tecnoquim.config.DBManager;
import lp2tecnoquim.dao.PlanMaestroProduccionDAO;
import lp2tecnoquim.model.PlanMaestroProduccion;
import lp2tecnoquim.model.Estado;

public class PlanMaestroProduccionMySQL implements PlanMaestroProduccionDAO{

    Connection con = null;
    CallableStatement cs;

    @Override
    public void insertar(PlanMaestroProduccion plan) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_PLAN(?,?,?)}");
            java.sql.Date sqlDate = new java.sql.Date(plan.getPeriodo().getTime());
            cs.setDate("_PERIODO", sqlDate);
            cs.setInt("_ESTADO",1);
            cs.setInt("_FK_ID_TRABAJADOR",plan.getResponsable().getId());
            
            cs.registerOutParameter("_ID_PMP", java.sql.Types.INTEGER);
            cs.executeUpdate();
            plan.setId(cs.getInt("_ID_PMP"));
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public void actualizar(PlanMaestroProduccion plan) {
        try{
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ACTUALIZAR_PLAN(?,?,?,?)}");
            cs.setInt("_ID_PMP", plan.getId());
            java.sql.Date sqlDate = new java.sql.Date(plan.getPeriodo().getTime());
            cs.setDate("_PERIODO", sqlDate);
            cs.setInt("_ESTADO",1);
            cs.setInt("_FK_ID_TRABAJADOR",plan.getResponsable().getId());
                    
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
            cs = con.prepareCall("{call ELIMINAR_PLAN(?)}");
            cs.setInt("_ID_PMP", id);
            
           
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }

    @Override
    public ArrayList<PlanMaestroProduccion> listar() {
        ArrayList<PlanMaestroProduccion> plan = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_PLAN()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                PlanMaestroProduccion  a = new PlanMaestroProduccion();
                a.setId(rs.getInt("_ID_PMP"));
                a.setPeriodo(rs.getDate("PERIODO"));
                a.getResponsable().setId(rs.getInt("FK_ID_TRABAJADOR"));
                ///////////////////////////////////////////////
                
                
                plan.add(a);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return plan;
    }
    
}
