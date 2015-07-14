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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;

/**
 *
 * @author Usuario
 */
public class GetItems2 implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        
        try {
           AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            //Map[] e = puerto.getBeneficiariosCapriSigmaII(nit,nombre,nombre_comercial);
            Map[] e = puerto.getItems();
            
                        
            //System.out.println(datos.toJSONString());
            JSONObject JSNProveedor;
            if (e!=null){
                System.out.println("Entro....");
                for (int f=0;f<e.length;f++){
                    JSNProveedor = new JSONObject();
            
                    JSNProveedor.put("cod_item", e[f].get("COD_ITEM").toString());
                    JSNProveedor.put("articulo", e[f].get("ARTICULO").toString());
                    
                    System.err.println("wujuuuu: "+e[f].get("COD_ITEM").toString());
                    System.err.println("wujuuuu2: "+e[f].get("ARTICULO").toString());
                    
                    datos.add(JSNProveedor);
                }
                Listado.put("Items", datos);
                System.out.println("===================== Yeah ======================");
                System.out.println(Listado.toJSONString());
                out.print(Listado);
              
                return null;
                //System.out.println("Tiene Datos -> "+e[0].get("NOMBRE").toString());
            }
            
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}