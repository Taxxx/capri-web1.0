/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao.ibatis;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.umsa.dao.ProveedorDao;
import org.umsa.domain.Proveedor;
/**
 *
 * @author UMSA-JES
 */
public class SqlMapProveedorDao extends SqlMapClientDaoSupport implements ProveedorDao{
    public List BuscaProveedor(Proveedor  p) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("BuscaProveedor", p);
    }
    public Proveedor ProveedorInfo(Proveedor  p) throws DataAccessException {
        return (Proveedor)getSqlMapClientTemplate().queryForObject("ProveedorInfo", p);
    }
}
