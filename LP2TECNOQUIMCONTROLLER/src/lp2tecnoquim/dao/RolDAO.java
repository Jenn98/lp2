/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.Rol;
import lp2tecnoquim.model.Usuario;

/**
 *
 * @author alulab14
 */
public interface RolDAO {
    void insertar(Rol rol);
    void actualizar(Rol rol);
    void eliminar(int IdRol);
    ArrayList<Rol> listar();
    
}
