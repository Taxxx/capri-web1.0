/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao.ibatis;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.umsa.dao.OperacionesDao;
import org.umsa.domain.Operaciones;

/**
 *
 * @author UMSA-JES
 */
public class SqlMapOperacionesDao extends SqlMapClientDaoSupport implements OperacionesDao{
    
    public List RestriccionItems(Operaciones operaciones) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("RestriccionItems", operaciones);
    }
    public List GetDescPartida(Operaciones o) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetDescPartida", o);
    }
    public List ListaAdjuntos(Operaciones o) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("ListaAdjuntos", o);
    }
    public List Gestiones() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("Gestiones", null);
    }
    public List getUnidadesMedida() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getUnidadesMedida", null);
    }
    public List GetTiposDoc() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetTiposDoc", null);
    }
    public List GetClasesBeneficiario() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetClasesBeneficiario", null);
    }
    public List GetTipoItems() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetTipoItems", null);
    }
    public List GetPartidas(Operaciones o) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetPartidas", o);
    }
    public List GetPartidastotales() throws DataAccessException {
        System.out.println("tambien aqui");
        return getSqlMapClientTemplate().queryForList("GetPartidastotales", null);
    }
    public List GetItems(Operaciones o) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetItems", o);
    }
    public List GetCuantias() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetCuantias", null);
    }
    public List GetTipos() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetTipos", null);
    }
    public List GetTiposADJ() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetTiposADJ", null);
    }
    public List GetApertProg() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("GetApertProg", null);
    }
    /*public Proveedor ProveedorInfo(Proveedor  p) throws DataAccessException {
        return (Proveedor)getSqlMapClientTemplate().queryForObject("ProveedorInfo", p);
    }*/
}
