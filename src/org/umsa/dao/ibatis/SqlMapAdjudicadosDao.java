/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.dao.ibatis;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.umsa.dao.AdjudicadosDao;
import org.umsa.domain.Adjudicado;
import org.umsa.dao.OperacionesDao;
//import org.umsa.domain.adjudicados;
/**
 *
 * @author alex
 */
public class SqlMapAdjudicadosDao extends SqlMapClientDaoSupport implements AdjudicadosDao
{

    @Override
    public List BuscaAdjudicados(Adjudicado i) throws DataAccessException {
        System.out.println("entra a aqui jejeje");
        return getSqlMapClientTemplate().queryForList("BuscaAdjudicados", i);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public List getBuscaAdjudicados(adjudicados item) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getBuscaAdjudicados", item);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public List DetalleAdju(Adjudicado i) throws DataAccessException {
        System.out.println("Si logro entrar");
        return getSqlMapClientTemplate().queryForList("DetalleAdju", i);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
