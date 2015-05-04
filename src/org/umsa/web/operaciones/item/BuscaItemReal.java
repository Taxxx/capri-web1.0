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
public class BuscaItemReal implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
             response.setContentType("application/json; charset=UTF-8");
             PrintWriter out = response.getWriter();
             
             JSONObject Listado = new JSONObject();
             JSONArray datos= new JSONArray();
             
             
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             String articulo = request.getParameter("articulo").trim();
             
//             int cod_trans_detalle = Integer.parseInt(request.getParameter("cod_trans_detalle"));
//             System.out.println("******************* El cod_trans_detalle es --> "+cod_trans_detalle);
             //int gestion = Integer.parseInt(request.getParameter("gestion")); //revisar tal vez seria mejor jalar de la BD
             
             //System.out.println(":P "+cod_transaccion+" - "+gestion);
            
//             Map[] e = puerto.loadDetalle(cod_trans_detalle);
             System.out.println("El articulo es --> "+articulo);
             Map[] e = puerto.getBuscaItemsx(2015,articulo);
             if (e!=null){
                 JSONObject JSNDetalle;
                 for (int f=0;f<e.length;f++){
                     JSNDetalle = new JSONObject();
//                     System.out.println("1 - "+e[f].get("COD_COMPLEMENTO").toString());
//                     System.out.println("2 - "+e[f].get("COD_TRANS_DETALLE").toString());
//                     System.out.println("3 - "+e[f].get("DETALLE_SOLICITUD").toString());
//                     System.out.println("4 - "+e[f].get("DETALLE_ADQUI").toString());
//                     System.out.println("5 - "+e[f].get("DETALLE_ALM").toString());
                     
                     JSNDetalle.put("cod_item", e[f].get("COD_ITEM").toString());
                     JSNDetalle.put("partida", e[f].get("PARTIDA").toString());
                     JSNDetalle.put("articulo", e[f].get("ARTICULO").toString());
                    
                     
                     datos.add(JSNDetalle);
                 }
             }
             //System.out.println("el cod_trans_detalle es "+cod_trans_detalle);
             
             Listado.put("ListaItemReal", datos);
             System.out.println("===================== Yeah ======================");
             System.out.println(Listado.toJSONString());
             out.print(Listado);
             
            } catch (Exception e) {
                System.out.println("Error -> "+e);
        }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
