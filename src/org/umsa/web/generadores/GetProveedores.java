/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.generadores;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import org.umsa.ConnectSIGMA.SigmaCapriWSServiceLocator;
import org.umsa.ConnectSIGMA.SigmaCapriWS_PortType;

/**
 *
 * @author UMSA-JES
 */
public class GetProveedores implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cod_transaccion = request.getParameter("cod_transaccion");
        String cod_trans_nro = request.getParameter("cod_trans_nro");
        String nit =request.getParameter("nit");
        String nombre =request.getParameter("nombre");
        String nombre_comercial =request.getParameter("nombre_comercial");
        if(nit.length()==0){nit="%";}
        if(nombre.length()==0){nombre="%";}
        if(nombre_comercial.length()==0){nombre_comercial="%";}
        
        System.out.println("Hola que Tal.. nit: "+nit+" nombre: "+nombre+" nombre comercial: "+nombre_comercial);
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        
        try {
            SigmaCapriWSServiceLocator servicio = new SigmaCapriWSServiceLocator();
            SigmaCapriWS_PortType puerto = servicio.getSigmaCapriWS();
            Map[] e = puerto.getBeneficiariosCapriSigmaII(nit,nombre,nombre_comercial);
            
                        
            //System.out.println(datos.toJSONString());
            JSONObject JSNProveedor;
            if (e!=null){
                System.out.println("Entro....");
                for (int f=0;f<e.length;f++){
                    JSNProveedor = new JSONObject();
                /*if(sw){
                    //aux=(Proveedor) i.next();
                    total_filas=e.get("TOTAL_FILAS").toString();
                    sw=false;
                }*/
                //else{aux=(Proveedor) i.next();}

                    JSNProveedor.put("documento", e[f].get("DOCUMENTO").toString());
                    JSNProveedor.put("tipo_id", e[f].get("TIPO_ID").toString());
                    JSNProveedor.put("descripcion", e[f].get("DESCRIPCION").toString());
                    JSNProveedor.put("clase_beneficiario", e[f].get("CLASE_BENEFICIARIO").toString());
                    JSNProveedor.put("nombre", e[f].get("NOMBRE").toString());
                    JSNProveedor.put("nombre_comercial", e[f].get("NOMBRE_COMERCIAL").toString());
                    JSNProveedor.put("cod_transaccion", cod_transaccion);
                    JSNProveedor.put("cod_trans_nro", cod_trans_nro);
                    
                    JSNProveedor.put("adh_nombre", e[f].get("ADH_NOMBRE").toString());
//                    JSNProveedor.put("adh_documento", e[f].get("ADH_DOCUMENTO").toString());
                    JSNProveedor.put("dir_lugar", e[f].get("DIR_LUGAR").toString());
                    JSNProveedor.put("dir_direccion", e[f].get("DIR_DIRECCION").toString());
                    JSNProveedor.put("dir_telefono", e[f].get("DIR_TELEFONO").toString());
//                    JSNProveedor.put("dir_email", e[f].get("DIR_EMAIL").toString());
                   
                    
//                    JSNProveedor.put("adh_nombre", e[f].get("NOMBRE").toString());
//                    JSNProveedor.put("adh_tipo", e[f].get("NOMBRE").toString());
                    //System.out.println("Tiene Datos -> "+e[f].get("NOMBRE").toString());
                    //System.out.println("========== Para Analizar ======"+JSNProveedor);
                    datos.add(JSNProveedor);
                }
                Listado.put("PROVEEDORES", datos);
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
