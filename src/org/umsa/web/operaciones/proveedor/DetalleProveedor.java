/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.proveedor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Items;
import org.umsa.domain.Proveedor;
import org.umsa.domain.logic.MiFacade;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 *
 * @author UMSA-JES
 */
public class DetalleProveedor implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        
        String endpoint = "http://200.7.160.26/axis/CAPRICORNIO/SigmaCapriWS.jws?wsdl";
        
            String tipo_id =request.getParameter("tipo_id");
            String documento =request.getParameter("documento");
            Proveedor p= new Proveedor();
            System.out.println("tipo_id: "+tipo_id+" documento: "+documento);
            p.setTipo_id(tipo_id);
            p.setDocumento(documento);
            
            Service services = new Service();
            Call call = (Call) services.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName("getBeneficiariosDetalleCapriSigma");
            //Proveedor aux= (Proveedor)call.invoke(new Object[]{"4277626", "C"});
            Map[] result = (Map[])call.invoke(new Object[]{documento, tipo_id});
            //Map[] result = (Map[])call.invoke(new Object[]{"8442181", "C"});
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            JSONObject JSNProveedor;
            JSNProveedor = new JSONObject();
            for (Map e : result) {
                
                //System.out.println(e.size());
                System.out.println("Yeahhh --> "+e.get("ADH_DOCUMENTO"));
                System.out.println(e.values());
                JSNProveedor.put("adh_nombre", e.get("ADH_NOMBRE"));
                JSNProveedor.put("adh_tipo_id", e.get("ADH_TIPO_ID"));
                JSNProveedor.put("adh_documento", e.get("ADH_DOCUMENTO"));

                JSNProveedor.put("dir_lugar", e.get("DIR_LUGAR"));
                JSNProveedor.put("dir_direccion", e.get("DIR_DIRECCION"));
                JSNProveedor.put("dir_telefono", e.get("DIR_TELEFONO"));
                JSNProveedor.put("dir_email", e.get("DIR_EMAIL"));
                
                
                /*Iterator it = e.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry aux = (Map.Entry)it.next();
                    //System.out.println(x.getKey() + " " + x.getValue());
                    
                }*/
            }
            
            System.out.println("===================== Yeah ======================");
            System.out.println(JSNProveedor.toJSONString());
            out.print(JSNProveedor);
            
            //aux.get(0);
            System.out.println("Holaaaaaaaaaaaaaaaa que tallllllllllll :D -->"+result.toString());
            
            
            
            
            //Proveedor aux= (Proveedor)this.adqui.ProveedorInfo(p);
            

            /*JSNProveedor.put("adh_nombre", aux.getAdh_nombre());
            JSNProveedor.put("adh_tipo_id", aux.getAdh_tipo_id());
            JSNProveedor.put("adh_documento", aux.getAdh_documento());

            JSNProveedor.put("dir_lugar", aux.getDir_lugar());
            JSNProveedor.put("dir_direccion", aux.getDir_direccion());
            JSNProveedor.put("dir_telefono", aux.getDir_telefono());
            JSNProveedor.put("dir_email", aux.getDir_email());*/

            
            
       
        /********************************/
        
        /*String tipo_id =request.getParameter("tipo_id");
        String documento =request.getParameter("documento");
        Proveedor p= new Proveedor();
        
        System.out.println("tipo_id: "+tipo_id+" documento: "+documento);
        
        p.setTipo_id(tipo_id);
        p.setDocumento(documento);
        Proveedor aux= (Proveedor)this.adqui.ProveedorInfo(p);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        JSONObject JSNProveedor = new JSONObject();
                          
        JSNProveedor.put("adh_nombre", aux.getAdh_nombre());
        JSNProveedor.put("adh_tipo_id", aux.getAdh_tipo_id());
        JSNProveedor.put("adh_documento", aux.getAdh_documento());

        JSNProveedor.put("dir_lugar", aux.getDir_lugar());
        JSNProveedor.put("dir_direccion", aux.getDir_direccion());
        JSNProveedor.put("dir_telefono", aux.getDir_telefono());
        JSNProveedor.put("dir_email", aux.getDir_email());
        
        System.out.println("===================== Yeah ======================");
        System.out.println(JSNProveedor.toJSONString());
        out.print(JSNProveedor);*/
        
        
        
        
        return null;
    }
}
