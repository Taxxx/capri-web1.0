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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;

/**
 *
 * @author UMSA-JES
 */
public class ControladorItems implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             int tipo=Integer.parseInt(request.getParameter("tipo"));
             Map[] e;
             System.out.println("el tipo de accion es "+tipo);
            
//             switch(tipo){
//                 case 1:
//                     int cod_transaccion=0;
//                     int gestion=0; //revisar tal vez seria mejor jalar de la BD
//                      e= puerto.addItem("SET-addItem", cod_transaccion, gestion);
//                 case 2:
//                     String cod_trans_detalle="";
//                     String detalle="";
//                     String unidad_medida="";
//                     int cantidad_pedido=0;
//                     e = puerto.updateItem("SET-updateItem", cod_trans_detalle, detalle, unidad_medida, cantidad_pedido);
//                 default:
//                     break;
//             }
                        
            } catch (Exception e) {
                System.out.println("Error -> "+e);
            }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
