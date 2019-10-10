/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.OrdenProduccion;

/**
 *
 * @author alulab14
 */
public interface OrdenProduccionDAO {
    
    void insertar(OrdenProduccion orden, int idPMP);
    void actualizar(OrdenProduccion orden, int idPMP);
    void eliminar(int id);
    ArrayList<OrdenProduccion> listar();
    
}
