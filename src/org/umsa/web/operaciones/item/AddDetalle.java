/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.item;

import java.io.PrintWriter;
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
public class AddDetalle implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             response.setContentType("application/json; charset=UTF-8");
             PrintWriter out = response.getWriter();
             
             JSONObject aux = new JSONObject();
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             
             int cod_complemento = Integer.parseInt(puerto.secTransDetComplemento());
             int cod_trans_detalle = Integer.parseInt(request.getParameter("cod_trans_detalle"));
             
             //String detalle = request.getParameter("detalle");
             System.out.println(":P :P el cod_trans_detalle es: "+cod_trans_detalle+", detalle --> "+"");
             //puerto.delItemx("SET-delItem", cod_trans_detalle);
             puerto.addDetalle("SET-addDetalle",cod_complemento ,cod_trans_detalle, "");
             //System.out.println("el tipo de accion es "+tipo);
                       
             //aux.put("detalle",detalle);
             aux.put("cod_complemento",cod_complemento);
             out.print(aux);           
            } catch (Exception e) {
                System.out.println("Error -> "+e);
            }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
