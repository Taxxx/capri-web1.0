package org.umsa.dao;

import org.springframework.dao.DataAccessException;
import org.umsa.domain.Clientes;

public interface ClientesDao {    
  Clientes getCodigoUsuario(Clientes clientes) throws DataAccessException;  
}