package org.umsa.dao;

import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import org.springframework.dao.DataAccessException;
import org.umsa.domain.Clientes;


/**
 *
 * @author julian
 */
public interface RolesDao {
    List getRolUsuario(Clientes clientes) throws DataAccessException;
}
