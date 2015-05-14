/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Operaciones;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author Usuario
 */
public class RestriccionAvanza implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    /*public String set_total_filas(Iterator i){
        if(i.hasNext()){return i.}
        return "0";
    }*/
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String cod_transaccion =request.getParameter("cod_transaccion");
        
        System.out.println("El cod_transaccion es: "+cod_transaccion);
        
                
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        
        //this.adqui.RestriccionItems(o);
        
        Operaciones o = new Operaciones();
        o.setCod_transaccion(cod_transaccion);
        
        Iterator i = this.adqui.RestriccionItems(o).listIterator();
        Operaciones aux;
        JSONObject JSNItems;
        //cantidad,precio,unidad_medida_,detalle,nro_items
        String sw_restriccion = "1,1,1,1,1";
        int sw_cantidad = 1, sw_precio = 1, sw_unidad_medida = 1, sw_detalle = 1,sw_nro_items = 1;
        
        //System.out.println("--->: "+i.hasNext());
        
        if(!i.hasNext())
            sw_nro_items = 0;
        
        while( i.hasNext() ) {
            
//            JSNItems = new JSONObject();
            aux =(Operaciones) i.next();
            
            System.out.println("la cantidad es: "+aux.getCantidad());
            if(aux.getCantidad()==null || aux.getCantidad().equals("0"))
                sw_cantidad = 0;
            
            System.out.println("el precio es: "+aux.getCantidad());            
            if(aux.getPrecio() == null || aux.getPrecio().equals("0"))
                sw_precio = 0;
            
            System.out.println("la unidad de medida es: "+aux.getUnidad_medida());
            if(aux.getUnidad_medida() == null || aux.getUnidad_medida().equals("0"))
                sw_unidad_medida = 0;
            
            System.out.println("El detalle es: "+aux.getDetalle());            
            if(aux.getDetalle()== null || aux.getDetalle().trim().length() < 1)
                sw_detalle = 0;
            
            
             System.out.println("---**---"); 
                        
//            JSNItems.put("cantidad", aux.getCantidad());
//            JSNItems.put("precio", aux.getPrecio());
//            JSNItems.put("unidad_medida", aux.getUnidad_medida());
//            JSNItems.put("detalle", aux.getDetalle());
//            
//            System.out.println("========== Para Analizar ======"+JSNItems);
//            datos.add(JSNItems);
        }
        sw_restriccion = sw_cantidad+","+sw_precio+","+sw_unidad_medida+","+sw_detalle+","+sw_nro_items;        
        System.out.println("sw_restriccion: "+sw_restriccion);
        
        //
        /****************/
        Listado.put("Items", sw_restriccion);
        //Listado.put("Descripcion", aux2.getDescripcion());
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        
        System.out.println("Entro Restriccion!!!!!");
        //out.print("hola");
        return null;
    }
    
}
