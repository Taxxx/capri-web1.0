/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class Formu implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String cod_transaccion =request.getParameter("cod_transaccion");
            
        Transaccion trans= new Transaccion();
        
        trans.setCod_transaccion(Integer.parseInt(cod_transaccion));
          
        
        
        //Iterator i1=this.adqui.formuSol(trans).listIterator();
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        
        Iterator i=this.adqui.formu(trans).listIterator();
        //response.setContentType("text/xml");
        //response.setCharacterEncoding("UTF-8");
        //out.println("<listado>");
        JSONObject JSNFormu;
        response.setContentType("application/json; charset=UTF-8");
        Transaccion aux;
        //this.adqui.formuSol(trans);
        try {
            
            //int aux2=this.adqui.formu(trans).size();
            int aux3=this.adqui.formuSol(trans).size();
            //this.adqui.formu(trans);
            /*Transaccion aux3 = (Transaccion) this.adqui.formuSol(trans);
            aux3.getCod_transaccion();*/
            System.out.println("WHAAAAAAAAAAAAAAATxx -->");
            System.out.println("WHAAAAAAAAAAAAAAAT -->"+aux3);
            if(aux3>0)
            {
                //out.println("<formulario-soliditud>");
                //out.println("<codigo>" + cod_transaccion + "</codigo>");
                //out.println("</formulario-soliditud>");
                
                Listado.put("codigoS", cod_transaccion);
            }
            else{
                Listado.put("codigoS", null);
            }
            
            while( i.hasNext() ) {
                JSNFormu = new JSONObject();
                aux=(Transaccion) i.next();
                //out.println("<formulario-orden>");
                //out.println("<codigo>" + aux.getCod_trans_nro() + "</codigo>");
                //JSNFormu.put("formulario-orden", aux.getTerminos_ref());
                JSNFormu.put("codigoO", aux.getCod_trans_nro());
                if(aux.getCod_w()>=3){
                    //out.println("<codigo-w>" + "JUR" + "</codigo-w>");
                    JSNFormu.put("codigoW", "JUR");
                }else{
                    //out.println("<codigo-w>" + "ALM1" + "</codigo-w>");
                    JSNFormu.put("codigoW", "ALM1");
                }
                //out.println("</formulario-orden>");
                System.out.println("========== Para Analizar ======"+JSNFormu);
                datos.add(JSNFormu);
            }
        } catch (Exception e) {
            
        }
        Listado.put("Transaccion", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        //out.println("</listado>");
        return null;
    }      
}