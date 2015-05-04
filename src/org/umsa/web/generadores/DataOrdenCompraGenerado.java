/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.generadores;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class DataOrdenCompraGenerado implements Controller{

    private MiFacade adqui;
 
    public void setAdqui(MiFacade adqui) {
      this.adqui = adqui;
    }
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cod_trans_detalle =request.getParameter("cod_trans_detalle");
        String precio =request.getParameter("precio");
        System.out.println("El cod_trans_detalle: "+cod_trans_detalle+" & precio: "+precio);
        
        try {
            AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            puerto.setDetallePrecioUnit("SET-upDatedetPrecUnit", Integer.parseInt(cod_trans_detalle), Double.parseDouble(precio));
        } catch (Exception e) {
        }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
