/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.item;

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
public class DelDetalle implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             
             int cod_complemento = Integer.parseInt(request.getParameter("cod_complemento"));
             int cod_trans_detalle = Integer.parseInt(request.getParameter("cod_trans_detalle"));
             //System.out.println(":P :P el cod_complemento es: "+cod_complemento+" -- "+cod_trans_detalle);
             puerto.delDetalle("SET-delDetalle", cod_complemento,cod_trans_detalle);
             //System.out.println("el tipo de accion es "+tipo);
                       
                        
            } catch (Exception e) {
                System.out.println("Error -> "+e);
            }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
