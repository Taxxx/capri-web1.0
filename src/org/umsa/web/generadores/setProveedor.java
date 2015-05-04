/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.generadores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;

/**
 *
 * @author UMSA-JES
 */
public class setProveedor implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cod_transaccion = request.getParameter("cod_transaccion");
               
        String nombre = request.getParameter("nombre");
        String nombre_comercial = request.getParameter("casa_comercial");
                  
        
        
        String direccion=request.getParameter("direccion");
        String telefono=request.getParameter("telefono");
        String nit=request.getParameter("nit");
        try {
            AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            puerto.setProponenteADJ2(Integer.parseInt(cod_transaccion) ,nit,nombre, nombre_comercial, direccion, telefono);
            
        } catch (Exception e) {
        }
        return null;
    }
}
