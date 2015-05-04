/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class NotaConformidad implements Controller {
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
        String ciudad = request.getParameter("ciudad");
        String lugar = request.getParameter("lugar");
        String fecha = request.getParameter("fecha");
        String proveedor = request.getParameter("proveedor");
        String monto = request.getParameter("monto");
        String rpa = request.getParameter("rpa");
        String cargo_rpa = request.getParameter("cargo_rpa");
        
        
        System.out.println("ciudad: "+ciudad);
        System.out.println("lugar: "+lugar);
        System.out.println("fecha: "+fecha);
        System.out.println("proveedor: "+proveedor);
        System.out.println("monto: "+monto);
        System.out.println("rpa: "+rpa);
        System.out.println("cargo_rpa: "+cargo_rpa);
               
        return null;
    }      
}
