/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao.ibatis;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.umsa.dao.RolesDao;
import org.umsa.domain.Clientes;
/**
 *
 * @author julian
 */
//public class SqlMapRolesDao extends SqlMapClientDaoSupport implements RolesDao {
public class SqlMapRolesDao extends SqlMapClientDaoSupport implements RolesDao {  

 public List getRolUsuario(Clientes clientes) throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getRolUsuario", clientes);
  } 
    
}
