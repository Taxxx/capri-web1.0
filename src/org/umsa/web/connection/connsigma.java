/*
 * i_connection.java
 *
 * Created on 1 de diciembre de 2005, 22:55
 *
 * To change this template, choose Tools | Options and locate the template under
 *
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.umsa.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connsigma {
    private Connection conConection = null;
    private Statement staSentencia = null;
    private String strDriver = null;
    private String strUrl = null;
    private String strUser = null;
    private String strPassword = null;
    private String strUrlServer = null;
    private String strDataBase = null;
    
    /** Creates a new instance of i_conection */
    
    public connsigma() {

        this.strDriver = "oracle.jdbc.driver.OracleDriver";
        /*this.strUser = "dumsa";
        this.strPassword = "dumsaf2";
        this.strUrlServer = "jdbc:oracle:thin:@200.7.160.21:1521:";
        this.strDataBase = "help";*/
        this.strUser = "umsa";
        this.strPassword = "schedule2578";
        this.strUrlServer = "jdbc:oracle:thin:@200.7.160.18:1521:";
        this.strDataBase = "prod";
        this.strUrl = this.strUrlServer+this.strDataBase;
    }
    
    public connsigma(String strDriver, String strUrl, String strUser, String strPassword) {
        this.strDriver = strDriver;
        this.strUrl = strUrl;
        this.strUser = strUser;
        this.strPassword = strPassword;
    }    
    
    /** Methods of i_conection*/
    public void connection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(strDriver);
            conConection = DriverManager.getConnection(strUrl, strUser, strPassword);
            staSentencia = conConection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public Connection getConnection() {
        return conConection;
    }
    
    public void setUrlServer(String strUrlServer) {
        this.strUrlServer = strUrlServer;
        this.strUrl = this.strUrlServer+this.strDataBase;
    }
    
    public void setDataBase(String strDataBase) {
        this.strDataBase = strDataBase;
        this.strUrl = this.strUrlServer+this.strDataBase;
    }

    public ResultSet executeQuery(String strSql) throws SQLException {
        ResultSet resResultado = null;
        
        try {
            resResultado = staSentencia.executeQuery(strSql);
        } catch (SQLException e) {
            throw e;
        }
        return resResultado;
    }
    
    public int executeUpdate(String strSql) throws SQLException {
        ResultSet resResultado = null;
        Integer intColumnas = 0;
        
        try {
            intColumnas = staSentencia.executeUpdate(strSql);
        } catch (SQLException e) {
            throw e;
        }
        return intColumnas;
    }
    
    public void close() throws SQLException {
        try {
            if(conConection != null) conConection.close();
            if(staSentencia != null) staSentencia.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /** Module primary */
    /*public static void main(String arg[]) {
        // conexion uno
        ResultSet rsResultado = null;
        connsigma conenxion = new connsigma();
        
        try {
            conenxion.connection();
            rsResultado = conenxion.executeQuery("SELECT * FROM sigma.ordenadores_del_gasto t1 ");
            while(rsResultado.next()) {
                System.out.println(rsResultado.getString("cargo_ordenador"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("error: "+e);
        } catch (SQLException e) {
            System.out.println("error: "+e);
        } finally {
            try {
                conenxion.close();
                if(rsResultado != null) rsResultado.close();
            } catch (SQLException e) {
            }
        }
        
        // conexion dos
        ResultSet rsResultado2 = null;
        connsigma conenxion2 = new connsigma();
        conenxion2.setDataBase("dbvalores2006");
        
        try {
            conenxion2.connection();
            rsResultado2 = conenxion2.executeQuery("SELECT * FROM usuario");
            while(rsResultado2.next()) {
                System.out.println(rsResultado2.getString("nombre"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("error: "+e);
        } catch (SQLException e) {
            System.out.println("error: "+e);
        } finally {
            try {
                conenxion2.close();
                if(rsResultado2 != null) rsResultado2.close();
            } catch (SQLException e) {
            }
        }
    }*/
}
