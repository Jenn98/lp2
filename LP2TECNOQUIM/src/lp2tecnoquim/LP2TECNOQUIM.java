/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim;

import java.util.ArrayList;
import lp2tecnoquim.config.DAOFactory;
import lp2tecnoquim.dao.*;
import lp2tecnoquim.model.*;
import lp2tecnoquim.mysql.*;

/**
 *
 * @author alulab14
 */
public class LP2TECNOQUIM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Ingresar Usuario
        
        Usuario u = new Usuario();
        
        u.setUsername("rarias");
        u.setPassword("1234");
        
        DAOFactory.getDAOFactory().getUsuarioDAO().insertar(u);
        
    }
    
}
