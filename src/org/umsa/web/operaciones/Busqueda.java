/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.Proveedor;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class Busqueda implements Controller {
    private MiFacade adqui;
    //String getCodUmsa;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {                      
        
        System.out.println("OKEY MICKEY Buscando Procesos......");
        
        String tipo_transaccion =request.getParameter("tipo_transaccion");
        String tipo_cuantia =request.getParameter("tipo_cuantia");
        String detalle =request.getParameter("detalle");
        String cuce =request.getParameter("cuce");
        
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        
        System.out.println("============================RANGO: "+min+" - "+max);
        
        
        if(tipo_transaccion.length()==0){tipo_transaccion="%";}
        if(tipo_cuantia.length()==0){tipo_cuantia="%";}
        if(detalle.length()==0){detalle="%";}
        if(cuce.length()==0){cuce="%";}
        
        int ver =Integer.parseInt(request.getParameter("ver"));
        
        String getCodUmsa=this.adqui.getCodUmsa();
        System.out.println("============================COD UMSA: "+getCodUmsa);
        
        //if(ver.equals("todos"))
            
        System.out.println("============================VERRRR: "+ver);
        Transaccion trans= new Transaccion();
        int gestion=Integer.parseInt(request.getParameter("fecha_publicacion"));
        trans.setGestion(gestion);
        trans.setVer(ver);
        trans.setCuce(cuce);
        
        trans.setTipo_item(tipo_transaccion);
        
        trans.setTipo_cuantia(tipo_cuantia);
        trans.setDetalle(detalle);
        
        trans.setMin(min);
        trans.setMax(max);
        //trans.setCuantia("%");
        
        /*if(detalle.equals("")){
            //detalle="_null_";
            detalle=null;
            System.out.println("============================Vacio y el detalle es: "+detalle);
        }else{
            System.out.println("============================Con Datos");
        }*/
            
        /*trans.setDetalle(detalle);
        
               
        Map modelo = new HashMap();
        
        PagedListHolder lista_resultados = new PagedListHolder(this.adqui.busqueda(trans));
        System.out.println("============================:("+tipo_cuantia);
        System.out.println("============================"+lista_resultados.getNrOfElements());
        lista_resultados.setPageSize(lista_resultados.getNrOfElements());
        
        
        modelo.put("totalFilas",lista_resultados.getNrOfElements());
        modelo.put("lista_resultados",lista_resultados);
        
        modelo.put("tipo_transaccion",tipo_transaccion);
        modelo.put("tipo_cuantia",tipo_cuantia);
        modelo.put("detalle",detalle);
        modelo.put("ver",ver);
        modelo.put("getCodUmsa",getCodUmsa);
                       
        
        return new ModelAndView("inicio/busqueda", modelo);*/
        
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        //Listado.put("total_filas", "115"); 
        
        System.out.println(trans.getVer());
        System.out.println(trans.getCuce());
        System.out.println(trans.getTipo_item());
        System.out.println(trans.getTipo_cuantia());
        System.out.println(trans.getDetalle());
        System.out.println(trans.getMin());
        System.out.println(trans.getMax());
        
        
        Iterator i=this.adqui.busqueda(trans).listIterator();
        System.out.println("El tamaño es --> "+this.adqui.busqueda(trans).size());
        Transaccion aux;
        JSONObject JSNTransaccion, aux2;
        
        boolean sw=true;
        String total_filas="0";
        while( i.hasNext() ) {
            
            JSNTransaccion = new JSONObject();
            if(sw){
                aux=(Transaccion) i.next();
                total_filas=aux.getTotal_filas();
                sw=false;
            }else{aux=(Transaccion) i.next();}
                        
            JSNTransaccion.put("cuce", aux.getCuce());
            JSNTransaccion.put("tipo", aux.getTipo_pedido());
            JSNTransaccion.put("sol", aux.getOrigen());
            JSNTransaccion.put("des", aux.getDestino());
            JSNTransaccion.put("cuantia", aux.getCuantia());
            JSNTransaccion.put("detalle", aux.getDetalle_solicitud());
            JSNTransaccion.put("estado", aux.getEstado());
            JSNTransaccion.put("fecha", aux.getFecha());
            
            JSNTransaccion.put("cod_transaccion", aux.getCod_transaccion());
            
                        
            System.out.println("========== Para Analizar ======"+JSNTransaccion);
            datos.add(JSNTransaccion);
        }
        Listado.put("total_filas",total_filas); 
        //
        /****************/
        Listado.put("Transacciones", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
              
        return null;
    }
}
