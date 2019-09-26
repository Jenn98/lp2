/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.DetalleAlmacenProducto;


/**
 *
 * @author alulab14
 */
public interface DetalleAlmacenProductoDAO {
    
    void insertar(DetalleAlmacenProducto detalle);
    void actualizar(DetalleAlmacenProducto detalle);
    void eliminar(int id);
    ArrayList<DetalleAlmacenProducto> listar();
    
}
