/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2tecnoquim.dao;

import java.util.ArrayList;
import lp2tecnoquim.model.Mensaje;

/**
 *
 * @author pukurin
 */
public interface MensajeDAO {
    
    void insertar(Mensaje mensaje);
    void eliminar(int idMensaje);
    ArrayList<Mensaje> listar(int idReceptor);
}
