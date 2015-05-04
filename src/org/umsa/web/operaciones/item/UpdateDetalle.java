/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.item;

import java.util.Map;
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
public class UpdateDetalle implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             
             
             int cod_complemento=Integer.parseInt(request.getParameter("cod_complemento"));
             String detalle=request.getParameter("detalle");
             
             System.out.println("el cod_complemento: "+cod_complemento+" -- "+detalle);
             //System.out.println("Hay caramba "+cod_trans_detalle+" - "+cantidad_pedido+" - "+detalle+" - "+cod_unidad_medida);
             
             puerto.updateDetallex("SET-updateDetalle", cod_complemento, detalle);
             System.out.println("Exito exitosoooo");
                       
                        
            } catch (Exception e) {
                System.out.println("Error -> "+e);
            }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
