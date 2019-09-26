/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim;

import java.util.ArrayList;
import lp2tecnoquim.dao.UsuarioDAO;
import lp2tecnoquim.model.Usuario;
import lp2tecnoquim.mysql.UsuarioMySQL;

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
        UsuarioDAO daoUsuario = new UsuarioMySQL();
        
        Usuario e1 = new Usuario ("Jefe","123456");

        daoUsuario.insertar(e1);
        
        
             
        
        
    }
    
}
