/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.umsa.domain.Items;
import org.umsa.domain.Transaccion;

/**
 *
 * @author julian
 */
public interface ItemsDao {
    
    String getTipoTramite(Items tipo_tram)throws DataAccessException;    
    List getItemTipo() throws DataAccessException;
    List getItemGrupo(int cod_tipo_item) throws DataAccessException;
    List getItemRubro(Items tipo_item) throws DataAccessException;
    List getItemArticulo(Items tipo_item) throws DataAccessException;
    String getItemArticuloUnidadMedida(Items tipo_item) throws DataAccessException;    
    Transaccion getTransaccionCodItem(Transaccion item) throws DataAccessException;
    void setTransaccionArticulo(Transaccion transaccion) throws DataAccessException;
    List getTransaccionArticulos(int cod_transaccion) throws DataAccessException;
    List getTransaccionArticulos2(int cod_transaccion) throws DataAccessException;
    List getTransaccionDetalle(int cod_transaccion) throws DataAccessException;
    List getAdjuntos(int cod_transaccion) throws DataAccessException;
    List getBuscaItems(Items item) throws DataAccessException;
    List getBuscaConsultoresObras(int cod_tipo_item) throws DataAccessException;
    /***/
    List BuscaItems(Items i) throws DataAccessException;
    
}

