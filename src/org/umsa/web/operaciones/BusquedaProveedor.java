/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class BusquedaProveedor implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String cod_transaccion =request.getParameter("cod_transaccion");
        
                
        Transaccion trans= new Transaccion();
        trans.setCod_transaccion(Integer.parseInt(cod_transaccion));
        
        Iterator i=this.adqui.rastreo(trans).listIterator();
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        out.println("<listado>");
        Transaccion aux;
        while( i.hasNext() ) {
            aux=(Transaccion) i.next();
            out.println("<transaccion>");
            out.println("<cuce>" + aux.getCuce() + "</cuce>");
            out.println("<cod_detalle>" + aux.getCod_trans_detalle() + "</cod_detalle>");
            out.println("<cod_orden_transaccion>" + aux.getNro_transaccion() + "</cod_orden_transaccion>");
            out.println("<estado>" + aux.getEstado() + "</estado>");
            out.println("<fecha>" + aux.getFecha() + "</fecha>");
            out.println("</transaccion>");
        }
        out.println("</listado>");
        
        return null;
    }      
}