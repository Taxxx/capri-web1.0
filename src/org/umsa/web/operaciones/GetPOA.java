/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Proveedor;
import org.umsa.domain.logic.MiFacade;



/**
 *
 * @author UMSA-JES
 */
public class GetPOA implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    /*public String set_total_filas(Iterator i){
        if(i.hasNext()){return i.}
        return "0";
    }*/
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        
        String endpoint = "http://200.7.160.26/axis/CAPRICORNIO/SigmaCapriWS.jws?wsdl";
        //String tipo_id ="C";
        String destino =request.getParameter("destino");
        String codigo = destino.substring(0, 14);
        System.out.println("El destino es --> "+destino);
        System.out.println("El codigo es --> "+codigo);
        
        String gestion =request.getParameter("gestion");
        System.out.println("La gestion es ----> "+gestion);
        
        String da = codigo.substring(0, 2);
        String ue = codigo.substring(2, 5);
        String programa = codigo.substring(5, 7);
        String proyecto = codigo.substring(7, 11);
        String actividad = codigo.substring(11, 14);
        System.out.println("da: "+da+" ue: "+ue+" programa: "+programa+" proyecto: "+proyecto+" actividad: "+actividad);
        
        Service services = new Service();
        Call call = (Call) services.createCall();
        call.setTargetEndpointAddress(new java.net.URL(endpoint));
        call.setOperationName("getEjecucionPptoSIGMA");             
        
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        //Listado.put("total_filas", "115"); 
        
        //Iterator i=this.adqui.BuscaProveedor(prove).listIterator();
        //Map[] result = (Map[])call.invoke(new Object[]{documento, tipo_id, clase_beneficiario, nombre, nombre_comercial, 0, 50});
        
        //Map[] result = (Map[])call.invoke(new Object[]{"2014", "139", "10", "010", "10", "0000", "001"});
        Map[] result = (Map[])call.invoke(new Object[]{gestion, "139", da, ue, programa, proyecto, actividad });
        Proveedor aux;
        JSONObject JSNProveedor, aux2;
        
        boolean sw=true;
        String total_filas="0";
        
        try {
            for (Map e : result) {
                       
                JSNProveedor = new JSONObject();
                /*if(sw){
                    //aux=(Proveedor) i.next();
                    total_filas=e.get("TOTAL_FILAS").toString();
                    sw=false;
                }*/
                //else{aux=(Proveedor) i.next();}

                JSNProveedor.put("OBJETO_DEL_GASTO", e.get("OBJETO_DEL_GASTO"));
                
                JSNProveedor.put("DESC_OBJETO_DEL_GASTO", e.get("DESC_OBJETO_DEL_GASTO"));
                JSNProveedor.put("FUENTE", e.get("FUENTE"));
                JSNProveedor.put("ORGANISMO", e.get("ORGANISMO"));
                JSNProveedor.put("PPTO_INICIAL", e.get("PPTO_INICIAL"));
                JSNProveedor.put("TRASP", e.get("TRASP"));
                JSNProveedor.put("MODIFICACIONES_PRESUPUESTALES", e.get("MODIFICACIONES_PRESUPUESTALES"));
                
                JSNProveedor.put("DEVENGADO", e.get("DEVENGADO"));
                JSNProveedor.put("SALDO_POR_PREVENIR", e.get("SALDO_POR_PREVENIR"));
                
                System.out.println("========== Para Analizar ======"+JSNProveedor);
                datos.add(JSNProveedor);
            
        
        }
        } catch (Exception e) {
            
        }
        
        //Listado.put("total_filas",total_filas);
        //
        /****************/
        Listado.put("POA", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
              
        return null;
    }      
}