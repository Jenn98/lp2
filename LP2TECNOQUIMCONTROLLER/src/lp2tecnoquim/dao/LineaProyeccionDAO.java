/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.LineaProyeccion;


/**
 *
 * @author alulab14
 */
public interface LineaProyeccionDAO {
    
    void insertar(LineaProyeccion linea);
    void actualizar(LineaProyeccion linea);
    void eliminar(int id);
    ArrayList<LineaProyeccion> listar();
    
}
