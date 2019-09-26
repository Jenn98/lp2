/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.Producto;


/**
 *
 * @author alulab14
 */
public interface ProductoDAO {
    
    void insertar(Producto producto);
    void actualizar(Producto producto);
    void eliminar(int idProducto);
    ArrayList<Producto> listar();
    
}
