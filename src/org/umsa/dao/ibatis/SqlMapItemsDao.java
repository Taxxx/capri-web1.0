/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao.ibatis;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.umsa.dao.ItemsDao;
import org.umsa.domain.Items;
import org.umsa.domain.Transaccion;

/**
 *
 * @author julian
 */
public class SqlMapItemsDao extends SqlMapClientDaoSupport implements ItemsDao {
     
    public String getTipoTramite(Items tipo_tram) throws DataAccessException {
        return (String) getSqlMapClientTemplate().queryForObject("getTipoTramite", tipo_tram);
    }                   
    
    public List getItemTipo() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getItemTipo", null);
    }
    
    public List getItemGrupo(int cod_tipo_item) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getItemGrupo", cod_tipo_item);
    }
    
    public List getItemRubro(Items tipo_item) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getItemRubro", tipo_item);
    }
    
    public List getItemArticulo(Items tipo_item) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getItemArticulo", tipo_item);
    }
    
    public String getItemArticuloUnidadMedida(Items tipo_item) throws DataAccessException {
        String unidad="";
        try{
            return ((String)getSqlMapClientTemplate().queryForObject("getItemArticuloUnidadMedida", tipo_item)).toString();                
        }
        catch(NullPointerException e){
            return unidad;
        }
    }                
    
    public Transaccion getTransaccionCodItem(Transaccion item)  throws DataAccessException {
        return (Transaccion) getSqlMapClientTemplate().queryForObject("getTransaccionCodItem", item);
    }
    
    public void setTransaccionArticulo(Transaccion transaccion) throws DataAccessException {
        getSqlMapClientTemplate().update("setTransaccionArticulo", transaccion);
    }
    
    public List getTransaccionArticulos(int cod_transaccion)  throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTransaccionArticulos", cod_transaccion);
    }
    public List getTransaccionArticulos2(int cod_transaccion)  throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTransaccionArticulos2", cod_transaccion);
    }
    public List getTransaccionDetalle(int cod_transaccion)  throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTransaccionDetalle", cod_transaccion);
    }
    
    public List getAdjuntos(int cod_transaccion)  throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getAdjuntos", cod_transaccion);
    }
    
    public List getBuscaItems(Items item)  throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getBuscaItems", item);
    }
    
    public List getBuscaConsultoresObras(int cod_tipo_item)  throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getBuscaConsultoresObras", cod_tipo_item);
    }
    public List BuscaItems(Items  i) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("BuscaItems", i);
    }
    
}
