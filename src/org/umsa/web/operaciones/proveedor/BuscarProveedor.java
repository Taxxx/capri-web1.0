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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Proveedor;
import org.umsa.domain.logic.MiFacade;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 *
 * @author UMSA-JES
 */
public class BuscarProveedor implements Controller {
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
        String tipo_id =request.getParameter("tipo_id");
        String nombre =request.getParameter("nombre");
        String nombre_comercial =request.getParameter("nombre_comercial");
        String documento =request.getParameter("documento");
        
        String clase_beneficiario =request.getParameter("clase_beneficiario");
        
        System.out.println("Valor Documento: "+documento);
        /*******Paginacion**********/
        /*String min = "0";
        String max = "50";*/
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        /***************/
        if(tipo_id.length()==0){tipo_id="%";}
        if(clase_beneficiario.length()==0){clase_beneficiario="%";}
        if(nombre.length()==0){nombre="%";}
        if(nombre_comercial.length()==0){nombre_comercial="%";}
        if(documento.length()==0){documento="%";}
               
        Proveedor prove= new Proveedor();
        prove.setTipo_id(tipo_id);
        prove.setNombre(nombre);
        prove.setNombre_comercial(nombre_comercial);
        prove.setDocumento(documento);
        prove.setClase_beneficiario(clase_beneficiario);
        
        prove.setMin(min);
        prove.setMax(max);
        //System.out.println("Alto alto: "+prove.getMin());
        
        Service services = new Service();
        Call call = (Call) services.createCall();
        call.setTargetEndpointAddress(new java.net.URL(endpoint));
        call.setOperationName("getBeneficiariosCapriSigma");             
        
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        //Listado.put("total_filas", "115"); 
        
        //Iterator i=this.adqui.BuscaProveedor(prove).listIterator();
        //Map[] result = (Map[])call.invoke(new Object[]{documento, tipo_id, clase_beneficiario, nombre, nombre_comercial, 0, 50});
        
        Map[] result = (Map[])call.invoke(new Object[]{documento, tipo_id, clase_beneficiario, nombre, nombre_comercial, Integer.parseInt(min), Integer.parseInt(max)});
        Proveedor aux;
        JSONObject JSNProveedor, aux2;
        
        boolean sw=true;
        String total_filas="0";
        /*while( i.hasNext() ) {
            
            JSNProveedor = new JSONObject();
            if(sw){
                aux=(Proveedor) i.next();
                total_filas=aux.getTotal_filas();
                sw=false;
            }else{aux=(Proveedor) i.next();}
                        
            JSNProveedor.put("tipo_id", aux.getTipo_id());
            JSNProveedor.put("descripcion", aux.getDescripcion());
            JSNProveedor.put("nombre", aux.getNombre());
            JSNProveedor.put("clase_beneficiario", aux.getClase_beneficiario());
            JSNProveedor.put("nombre_comercial", aux.getNombre_comercial());
            JSNProveedor.put("documento", aux.getDocumento());
                        
            System.out.println("========== Para Analizar ======"+JSNProveedor);
            datos.add(JSNProveedor);
        }
        Listado.put("total_filas",total_filas); */
        try {
            for (Map e : result) {
            /*System.out.println(e.size());
            System.out.println("Yeahhh --> "+e.get("ADH_DOCUMENTO"));
            System.out.println(e.values());*/
            
            /*while(it.hasNext()){
                Map.Entry x = (Map.Entry)it.next();
                System.out.println(x.getKey() + " " + x.getValue());
            }*/
            
                JSNProveedor = new JSONObject();
                if(sw){
                    //aux=(Proveedor) i.next();
                    total_filas=e.get("TOTAL_FILAS").toString();
                    sw=false;
                }
                //else{aux=(Proveedor) i.next();}

                JSNProveedor.put("tipo_id", e.get("TIPO_ID"));
                JSNProveedor.put("descripcion", e.get("DESCRIPCION"));
                JSNProveedor.put("nombre", e.get("NOMBRE"));
                JSNProveedor.put("clase_beneficiario", e.get("CLASE_BENEFICIARIO"));
                JSNProveedor.put("nombre_comercial", e.get("NOMBRE_COMERCIAL"));
                JSNProveedor.put("documento", e.get("DOCUMENTO"));

                System.out.println("========== Para Analizar ======"+JSNProveedor);
                datos.add(JSNProveedor);
            
        
        }
        } catch (Exception e) {
            
        }
        
        Listado.put("total_filas",total_filas);
        //
        /****************/
        Listado.put("Proveedores", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
              
        return null;
    }      
}