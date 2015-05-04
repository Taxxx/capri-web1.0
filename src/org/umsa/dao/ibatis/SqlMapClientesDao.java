package org.umsa.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.umsa.dao.ClientesDao;
import org.umsa.domain.Clientes;


public class SqlMapClientesDao extends SqlMapClientDaoSupport implements ClientesDao {
   
  public Clientes getCodigoUsuario(Clientes clientes)  throws DataAccessException {      
      return (Clientes) getSqlMapClientTemplate().queryForObject("getCodigoUsuario", clientes);
  }  
  
}