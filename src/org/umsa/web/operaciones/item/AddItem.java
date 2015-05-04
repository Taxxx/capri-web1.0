/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.item;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;

/**
 *
 * @author UMSA-JES
 */
public class AddItem implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             response.setContentType("application/json; charset=UTF-8");
             PrintWriter out = response.getWriter();
             
             JSONObject aux = new JSONObject();
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             int cod_transaccion = Integer.parseInt(request.getParameter("cod_transaccion"));
             int gestion = Integer.parseInt(request.getParameter("gestion")); //revisar tal vez seria mejor jalar de la BD
             
             System.out.println(":P "+cod_transaccion+" - "+gestion);
             String cod_trans_detalle = puerto.secTransaccionDetalle();
             System.out.println("-*-> cod_trans_detalle: "+Integer.parseInt(cod_trans_detalle)+", cod_transaccion: "+cod_transaccion+", gestion: "+gestion);
             Map[] e = puerto.addItem("SET-addItem",Integer.parseInt(cod_trans_detalle) ,cod_transaccion, gestion);
             System.out.println("el cod_trans_detalle es "+cod_trans_detalle);
             aux.put("cod_trans_detalle",cod_trans_detalle);          
             out.print(aux);           
            } catch (Exception e) {
                System.out.println("Error -> "+e);
            }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
