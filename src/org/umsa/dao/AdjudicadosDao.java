/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao;

/**
 *
 * @author alex
 */

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.umsa.domain.Adjudicado;

/**
 *
 * @author alex
 */
public interface AdjudicadosDao {
    
    List BuscaAdjudicados(Adjudicado i) throws DataAccessException;
    List DetalleAdju(Adjudicado i) throws DataAccessException;
    List InforAdju(Adjudicado i) throws DataAccessException;
    //List getBuscaAdjudicados(adjudicados item) throws DataAccessException;
    /*public List BuscarAdjudicados(adjudicados i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
}

