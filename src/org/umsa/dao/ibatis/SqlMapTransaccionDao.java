/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao.ibatis;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.umsa.dao.TransaccionDao;
import org.umsa.domain.Clientes;
import org.umsa.domain.Transaccion;

/**
 *
 * @author julian
 */

public class SqlMapTransaccionDao extends SqlMapClientDaoSupport implements TransaccionDao{
    
    public List RestriccionAdjuntos(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("RestriccionAdjuntos", transaccion);
    }
    public void generaCUCE(String cod_transaccion) throws DataAccessException {
        getSqlMapClientTemplate().queryForObject("generaCUCE", cod_transaccion);
    }
    /*COD UMSA**/
    public String getCodUmsa() throws DataAccessException{
     String unidad="";
        try{
            return ((String)getSqlMapClientTemplate().queryForObject("getCodUmsa", null)).toString();                
        }
        catch(NullPointerException e){
            return unidad;
        }
    }
    public String getAperturaPadre(Transaccion transaccion) throws DataAccessException{
        return ((String)getSqlMapClientTemplate().queryForObject("getAperturaPadre", transaccion)).toString();
    }
    public String getMaxNroTramite(Transaccion transaccion) throws DataAccessException{
        return ((String)getSqlMapClientTemplate().queryForObject("getMaxNroTramite", transaccion)).toString();
    }
    /*=======================BUSQUEDA=====================*/
    public List busqueda(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("busqueda", transaccion);
    }
    public List rastreo(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("rastreo", transaccion);
    }
    public List archivos(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("archivos", transaccion);
    }
    public List formu(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("formu", transaccion);
    }
    public List formuSol(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("formuSol", transaccion);
    }
    public List rastreoDetalle(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("rastreoDetalle", transaccion);
    }
    
    public int getMaxCodTram() throws DataAccessException{
        return ((Integer) getSqlMapClientTemplate().queryForObject("getMaxCodTram", null)).intValue();
    }
    /*====================================================*/
  public List getTransaccionMateriales(Transaccion transaccion) throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getTransaccionMateriales", transaccion);
  }

  public List getTransaccionSolicitudes(Transaccion transaccion) throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getTransaccionSolicitudes", transaccion);
  }

  public List getCuantia()  throws DataAccessException {
      return getSqlMapClientTemplate().queryForList("getCuantia", null);
  }

  public Transaccion getTransaccionMaterial(Transaccion transaccion)  throws DataAccessException {
      return (Transaccion) getSqlMapClientTemplate().queryForObject("getTransaccionMaterial", transaccion);
  }

  public List getUnidadEjecutora(Clientes cliente) throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getUnidadEjecutora", cliente);
  }

  public int getNroTransaccion(Clientes cliente) throws DataAccessException{
     return ((Integer) getSqlMapClientTemplate().queryForObject("getNroTransaccion", cliente)).intValue();
  }
  
  public int setTransaccion(Transaccion transaccion) throws DataAccessException {
      int cod_trans=0;
     getSqlMapClientTemplate().update("setTransaccion", transaccion);
     cod_trans=((Integer)getSqlMapClientTemplate().queryForObject("getCodTransaccion", null)).intValue();
     transaccion.setCod_transaccion(cod_trans);     
     getSqlMapClientTemplate().update("setTramiteNro", transaccion);
     return cod_trans;
  }
  public void setTramiteNro(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setTramiteNro", transaccion);
  }
  public int getNroTramite(Transaccion transaccion) throws DataAccessException{
     return ((Integer) getSqlMapClientTemplate().queryForObject("getNroTramite", transaccion)).intValue();
  }
  
  public List getTransaccionComplemento(int cod_trans_detalle) throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getTransaccionComplemento", cod_trans_detalle);
  }
    
  public void setTransaccionComplemento(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setTransaccionComplemento", transaccion);
  }
  
  public void nroSolicitud(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("nroSolicitud", transaccion);
  }
  
  public void setTransaccionEliminaComplemento(int cod_complemento) throws DataAccessException {
     getSqlMapClientTemplate().update("setTransaccionEliminaComplemento", cod_complemento);
  }
    
  public void setTransaccionArticuloElimina(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setTransaccionArticuloElimina", transaccion);
  }
  
  public void delTransaccionEliminaDocs(int cod_transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("delTransaccionEliminaDocs", cod_transaccion);
  }
  
  public void setTransaccionEliminaComplementoArticulos(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setTransaccionEliminaComplementoArticulos", transaccion);
  }

  public void setTransaccionEliminaRegistroFechaArticulos(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setTransaccionEliminaRegistroFechaArticulos", transaccion);
  }
  public void setTransaccionEliminaDetalleNroArticulos(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setTransaccionEliminaDetalleNroArticulos", transaccion);
  }
  public void setTransaccionAvanza(Transaccion transaccion) throws DataAccessException {
     String destino=(String) getSqlMapClientTemplate().queryForObject("getTransaccionDestino", transaccion).toString();   
     int cod_trans_nro = ((Integer)getSqlMapClientTemplate().queryForObject("getCodTransaccionNro", transaccion)).intValue();
     transaccion.setCod_trans_nro(Integer.toString(cod_trans_nro));
     
     int cantidad_tram = ((Integer)getSqlMapClientTemplate().queryForObject("getNroTransDetNro", transaccion)).intValue();
     if (cantidad_tram==0)
        getSqlMapClientTemplate().update("setTransaccionDetalleNro", transaccion);
     transaccion.setDestino(destino);
     //transaccion.setCod_trans_nro(cod_trans_nro);
     getSqlMapClientTemplate().update("setTransaccionAvanza", transaccion);
     getSqlMapClientTemplate().update("setTransaccionLimpiaObs", transaccion);
  }
  
  public int getCodTransaccionNro(Transaccion transaccion) throws DataAccessException{
     return ((Integer)getSqlMapClientTemplate().queryForObject("getCodTransaccionNro", transaccion)).intValue();
  }
  public void setTransaccionDetalleNro(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setTransaccionDetalleNro", transaccion);
  }
  public void addTransDetalle(Transaccion transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("addTransDetalle", transaccion);
  } 
  public List getTransaccionListaSolicitudes(Transaccion transaccion) throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getTransaccionListaSolicitudes", transaccion);
  }
  
  public List getTransaccionListaPedidos(Transaccion transaccion) throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getTransaccionListaPedidos", transaccion);
  }
  
  public int getTransaccionNroItems(int cod_transaccion) throws DataAccessException {
    return ((Integer) getSqlMapClientTemplate().queryForObject("getTransaccionNroItems", cod_transaccion)).intValue();
  }
  
  public void setEliminaTransaccion(int cod_transaccion) throws DataAccessException {
     getSqlMapClientTemplate().update("setEliminaTransaccion", cod_transaccion);
  }
  
  public int getCodAlmacen(int cod_transaccion) throws DataAccessException {
    return ((Integer) getSqlMapClientTemplate().queryForObject("getCodAlmacen", cod_transaccion)).intValue();
  }
/* ==============================================================================
   *                    CONSULTORES
   =============================================================================*/
    public List getTransaccionConsultores(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTransaccionConsultores", transaccion);
    } 

    public void setTransaccionTerminos(Transaccion transaccion) throws DataAccessException {
        getSqlMapClientTemplate().update("setTransaccionTerminos", transaccion);
    }

    public void delTransaccionTerminos(int cod_docs) throws DataAccessException {
        getSqlMapClientTemplate().update("delTransaccionTerminos", cod_docs);
    }
    
    public void addNroPedido(Transaccion transaccion) throws DataAccessException {
        getSqlMapClientTemplate().update("addNroPedido", transaccion);
    }
    
    public List getEnviados(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getEnviados", transaccion);
    }
    public List getEnviados2(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getEnviados2", transaccion);
    }
    public List getEnviadosDetalle(Transaccion transaccion) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getEnviadosDetalle", transaccion);
    }
}
