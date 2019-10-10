/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.LineaInsumo;


/**
 *
 * @author alulab14
 */
public interface LineaInsumoDAO {
    
    void insertar(LineaInsumo linea, int idInstructivo);
    void actualizar(LineaInsumo linea, int idInstructivo);
    void eliminar(int id);
    ArrayList<LineaInsumo> listar();
    
}
