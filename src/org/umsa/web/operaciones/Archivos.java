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
public class Archivos implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String cod_transaccion =request.getParameter("cod_transaccion");
        
        Transaccion trans= new Transaccion();
        trans.setCod_transaccion(Integer.parseInt(cod_transaccion));
        
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        
        Iterator i=this.adqui.archivos(trans).listIterator();
        
        JSONObject JSNArchivo;
        response.setContentType("application/json; charset=UTF-8");
        
        //response.setContentType("text/xml");
        //response.setCharacterEncoding("UTF-8");
        //out.println("<listado>");
        Transaccion aux;
        while( i.hasNext() ) {
            JSNArchivo = new JSONObject();
            aux=(Transaccion) i.next();
            /*out.println("<archivo>");
            out.println("<nombre_archivo>" + aux.getTerminos_ref() + "</nombre_archivo>");
            out.println("</archivo>");*/
            JSNArchivo.put("nombre_archivo", aux.getTerminos_ref());
            System.out.println("========== Para Analizar ======"+JSNArchivo);
            datos.add(JSNArchivo);
        }
        //out.println("</listado>");
        Listado.put("Transaccion", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        return null;
    }      
}