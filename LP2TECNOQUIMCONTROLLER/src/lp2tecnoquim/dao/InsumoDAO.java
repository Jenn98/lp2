/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.Insumo;


/**
 *
 * @author alulab14
 */
public interface InsumoDAO {
    void insertar(Insumo insumo);
    void actualizar(Insumo insumo);
    void eliminar(int id);
    ArrayList<Insumo> listar();
    
}
