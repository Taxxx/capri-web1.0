/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

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
public class AddUnidadMedida implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             response.setContentType("application/json; charset=UTF-8");
             PrintWriter out = response.getWriter();
             
             JSONObject aux = new JSONObject();
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             
             String unidad_medida = request.getParameter("unidad_medida");
             System.out.println("unidad_medida -> "+unidad_medida);
             
             String cod_unidad_medida = puerto.secUnidadMedida();
             
             System.out.println("-*-> cod_unidad_medida: "+Integer.parseInt(cod_unidad_medida));
             
             Map[] e = puerto.addUnidadMedida("SET-addUnidadMedida",Integer.parseInt(cod_unidad_medida) ,unidad_medida);
             
             aux.put("cod_unidad_medida",cod_unidad_medida);          
             out.print(aux);           
            } catch (Exception e) {
                System.out.println("Error -> "+e);
            }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
