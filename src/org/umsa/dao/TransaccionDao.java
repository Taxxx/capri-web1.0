/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.umsa.domain.Clientes;
import org.umsa.domain.Operaciones;
import org.umsa.domain.Transaccion;

/**
 *
 * @author julian
 */

public interface TransaccionDao {
    
    
    List RestriccionAdjuntos(Transaccion transaccion) throws DataAccessException;
    void generaCUCE(String cod_transaccion) throws DataAccessException;
    /*COD UMSA*/
    String getCodUmsa()throws DataAccessException;
    String getMaxNroTramite(Transaccion transaccion)throws DataAccessException;
    String getAperturaPadre(Transaccion transaccion)throws DataAccessException;
    /*================BUSQUEDA===================*/
    List busqueda(Transaccion transaccion) throws DataAccessException;
    List rastreo(Transaccion transaccion) throws DataAccessException;
    List archivos(Transaccion transaccion) throws DataAccessException;
    List formu(Transaccion transaccion) throws DataAccessException;
    List formuSol(Transaccion transaccion) throws DataAccessException;
    List rastreoDetalle(Transaccion transaccion) throws DataAccessException;
    int getMaxCodTram() throws DataAccessException;
    /*===========================================*/
    List getTransaccionMateriales(Transaccion transaccion) throws DataAccessException;
    List getTransaccionSolicitudes(Transaccion transaccion) throws DataAccessException;
    List getCuantia() throws DataAccessException;
    Transaccion getTransaccionMaterial(Transaccion transaccion) throws DataAccessException;
    List getUnidadEjecutora(Clientes cliente) throws DataAccessException; 
    int getNroTransaccion(Clientes cliente) throws DataAccessException;
    int setTransaccion(Transaccion transaccion) throws DataAccessException;
    int getCodTransaccionNro(Transaccion transaccion) throws DataAccessException;
    void setTramiteNro(Transaccion transaccion) throws DataAccessException;
    int getNroTramite(Transaccion transaccion)throws DataAccessException;
    List getTransaccionComplemento(int cod_trans_detalle) throws DataAccessException;    
    void setTransaccionEliminaComplemento(int cod_complemento) throws DataAccessException;
    void setTransaccionComplemento(Transaccion transaccion) throws DataAccessException;
    void setTransaccionArticuloElimina(Transaccion transaccion) throws DataAccessException;
    void delTransaccionEliminaDocs(int cod_transaccion) throws DataAccessException;
    void setTransaccionEliminaComplementoArticulos(Transaccion transaccion) throws DataAccessException;
    void setTransaccionEliminaRegistroFechaArticulos(Transaccion transaccion) throws DataAccessException;
    void setTransaccionEliminaDetalleNroArticulos(Transaccion transaccion) throws DataAccessException;
    void setTransaccionAvanza(Transaccion transaccion) throws DataAccessException;
    void addNroPedido(Transaccion transaccion) throws DataAccessException;
    void setTransaccionDetalleNro(Transaccion transaccion) throws DataAccessException;
    List getTransaccionListaSolicitudes(Transaccion transaccion) throws DataAccessException;    
    List getTransaccionListaPedidos(Transaccion transaccion) throws DataAccessException;    
    int getTransaccionNroItems(int cod_transaccion) throws DataAccessException;
    void setEliminaTransaccion(int cod_transaccion) throws DataAccessException;
    
    int getCodAlmacen(int cod_transaccion) throws DataAccessException;
    
    
    void addTransDetalle(Transaccion transaccion) throws DataAccessException;
    void nroSolicitud(Transaccion transaccion)throws DataAccessException;
    /*========================================================================
     *              CONSULTORES
     =======================================================================*/
    List getTransaccionConsultores(Transaccion transaccion) throws DataAccessException;
    void setTransaccionTerminos(Transaccion transaccion) throws DataAccessException;
    void delTransaccionTerminos(int cod_docs) throws DataAccessException;
    
    List getEnviados(Transaccion transaccion) throws DataAccessException;
    List getEnviados2(Transaccion transaccion) throws DataAccessException;
    List getEnviadosDetalle(Transaccion transaccion) throws DataAccessException;
  
}
