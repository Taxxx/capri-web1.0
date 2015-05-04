/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;
import java.io.IOException;
import java.io.PrintWriter;
//import static java.lang.System.out;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 *
 * @author UMSA-JES
 */
public class Rastrear implements Controller {
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
        System.out.println("EL codigo trans es --> "+trans.getCod_transaccion());
        Iterator i=this.adqui.rastreo(trans).listIterator();
        JSONObject JSNRastreo;
        response.setContentType("application/json; charset=UTF-8");
        //response.setContentType("text/xml");
        //response.setCharacterEncoding("UTF-8");
        //out.println("<listado>");
        Transaccion aux;
        while( i.hasNext() ) {
            JSNRastreo = new JSONObject();
            aux=(Transaccion) i.next();
            /*out.println("<transaccion>");
            out.println("<cuce>" + aux.getCuce() + "</cuce>");
            out.println("<cod_detalle>" + aux.getCod_trans_detalle() + "</cod_detalle>");
            out.println("<cod_orden_transaccion>" + aux.getNro_transaccion() + "</cod_orden_transaccion>");
            out.println("<estado>" + aux.getEstado() + "</estado>");
            out.println("<fecha>" + aux.getFecha() + "</fecha>");
            out.println("</transaccion>");*/
            
            
            JSNRastreo.put("cuce", aux.getCuce());
            JSNRastreo.put("articulo", aux.getArticulo());
            JSNRastreo.put("detalle", aux.getDetalle());
            JSNRastreo.put("estado", aux.getEstado());
            JSNRastreo.put("cod_trans_detalle", aux.getCod_trans_detalle());
                        
            System.out.println("========== Para Analizar ======"+JSNRastreo);
            datos.add(JSNRastreo);
        }
        //out.println("</listado>");
        Listado.put("Transaccion", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        return null;
    }      
}
                    
        
        
