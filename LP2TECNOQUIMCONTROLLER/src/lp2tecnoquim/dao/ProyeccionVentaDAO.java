/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.ProyeccionVenta;


/**
 *
 * @author alulab14
 */
public interface ProyeccionVentaDAO {
    
    void insertar(ProyeccionVenta proyeccion);
    void actualizar(ProyeccionVenta proyeccion);
    void eliminar(int id);
    ArrayList<ProyeccionVenta> listar();
    
}
