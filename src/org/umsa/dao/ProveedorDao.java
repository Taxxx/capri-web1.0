/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.umsa.domain.Proveedor;
/**
 *
 * @author UMSA-JES
 */
public interface ProveedorDao {
    List BuscaProveedor(Proveedor p) throws DataAccessException;
    Proveedor ProveedorInfo(Proveedor p) throws DataAccessException;
}