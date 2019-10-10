/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.LineaOrden;


/**
 *
 * @author alulab14
 */
public interface LineaOrdenDAO {
    
    void insertar(LineaOrden lineaOrden, int idOrden);
    void actualizar(LineaOrden lineaOrden, int idOrden);
    void eliminar(int id);
    ArrayList<LineaOrden> listar(int idOrden);
    
}
