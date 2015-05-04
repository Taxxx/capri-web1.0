/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.umsa.domain.Operaciones;
/**
 *
 * @author UMSA-JES
 */
public interface OperacionesDao {
                
    List ListaAdjuntos(Operaciones o) throws DataAccessException;
    List Gestiones() throws DataAccessException;
    List getUnidadesMedida() throws DataAccessException;
    List GetTiposDoc() throws DataAccessException;
    List GetClasesBeneficiario() throws DataAccessException;
    List GetTipoItems() throws DataAccessException;
    List GetPartidas(Operaciones o) throws DataAccessException;
    List GetPartidastotales() throws DataAccessException;
    List GetItems(Operaciones o) throws DataAccessException;
    List GetCuantias() throws DataAccessException;
    List GetTipos() throws DataAccessException;
    List GetTiposADJ() throws DataAccessException;
    List GetApertProg() throws DataAccessException;
    List GetDescPartida(Operaciones o) throws DataAccessException;
    //Operaciones ProveedorInfo( p) throws DataAccessException;
}
