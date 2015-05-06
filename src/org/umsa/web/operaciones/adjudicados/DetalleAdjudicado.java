/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.adjudicados;
import java.io.PrintWriter;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//import org.umsa.domain.adjudicados;
import org.umsa.domain.logic.MiFacade;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.umsa.domain.Adjudicado;

/**
 *
 * @author alex
 */
public class DetalleAdjudicado implements Controller
{
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PrintWriter out = response.getWriter();
        String documento =request.getParameter("documento");
        int sw=0;
        Adjudicado adju=new Adjudicado();
        adju.setDocumento(documento);
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        System.out.println("EL codigo trans es --> "+adju.getDocumento());
        Iterator i=this.adqui.DetalleAdju(adju).listIterator();
        JSONObject JSNRastreo;
        response.setContentType("application/json; charset=UTF-8");
        //response.setContentType("text/xml");
        //response.setCharacterEncoding("UTF-8");
        //out.println("<listado>");
        Adjudicado aux;
        while( i.hasNext() ) {
            sw=1;
            System.out.println("entra al while");
            JSNRastreo = new JSONObject();
            aux=(Adjudicado) i.next();
            /*out.println("<transaccion>");
            out.println("<cuce>" + aux.getCuce() + "</cuce>");
            out.println("<cod_detalle>" + aux.getCod_trans_detalle() + "</cod_detalle>");
            out.println("<cod_orden_transaccion>" + aux.getNro_transaccion() + "</cod_orden_transaccion>");
            out.println("<estado>" + aux.getEstado() + "</estado>");
            out.println("<fecha>" + aux.getFecha() + "</fecha>");
            out.println("</transaccion>");*/
            
            
            JSNRastreo.put("partida", aux.getPartida());
            JSNRastreo.put("detalle", aux.getDetalle());
            JSNRastreo.put("gestion", aux.getGestion());
            //JSNRastreo.put("cod_trans_detalle", aux.getCod_trans_detalle());
            //System.out.println("esto es rastrear");         
            System.out.println("========== Para Analizar ======"+JSNRastreo);
            datos.add(JSNRastreo);
        }
        //out.println("</listado>");
        Listado.put("Adjudicado", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        return null;
    }
    
}
