/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.Instructivo;


/**
 *
 * @author alulab14
 */
public interface InstructivoDAO {
    
    void insertar(Instructivo instructivo);
    void actualizar(Instructivo instructivo);
    void eliminar(int id);
    ArrayList<Instructivo> listar();
    
}
