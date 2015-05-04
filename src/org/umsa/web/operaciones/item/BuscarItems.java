/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.item;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Items;
import org.umsa.domain.logic.MiFacade;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


import org.umsa.domain.Operaciones;
/**
 *
 * @author UMSA-JES
 */
public class BuscarItems implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    /*public String set_total_filas(Iterator i){
        if(i.hasNext()){return i.}
        return "0";
    }*/
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //String tipo_id ="C";
        String codigo =request.getParameter("codigo");
        String partida =request.getParameter("partida");
        
        System.out.println("La partida es:"+partida);
        
        String articulo =request.getParameter("articulo");
        String gestion =request.getParameter("gestion");
        String tipo =request.getParameter("tipo");
        
        String min = request.getParameter("min");
        String max = request.getParameter("max");     
        /***************/
        if(codigo.length()==0){codigo="%";}
        if(partida.length()==0){partida="%";}
        if(articulo.length()==0){articulo="%";}
        if(gestion.length()==0){gestion="%";}
        if(tipo.length()==0){tipo="%";}
        
        System.out.println("codigo: "+codigo);
        System.out.println("Partida: "+partida);
        System.out.println("Articulo: "+articulo);
        System.out.println("Gestion: "+gestion);
        System.out.println("Tipo: "+tipo);
        /***/
        /*Iterator ix=this.adqui.Gestiones().listIterator();
        System.out.println("Es:  -->"+((Operaciones) ix.next()).getGestion());*/
        //System.out.println("ES: "+items.get(0));
        /***/
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        //Listado.put("total_filas", "115"); 
        Items item= new Items();
        item.setCodigo(codigo);
        item.setPartida(partida);
        item.setArticulo(articulo);
        item.setGestion(gestion);
        item.setTipo(tipo);
        
        item.setMin(min);
        item.setMax(max);
        Iterator i=this.adqui.BuscaItems(item).listIterator();
        
        Items aux;
        JSONObject JSNItem, aux2;
        
        boolean sw=true;
        
        String total_filas = "0";
        
        /******PRUEBA******/
        /*JSNItem = new JSONObject();
        JSNItem.put("codigo", "XXX");
        JSNItem.put("partida", "123");
        JSNItem.put("articulo", "PAPEL");
        JSNItem.put("gestion", "2012");
        JSNItem.put("tipo", "cosa");
        datos.add(JSNItem);*/
        
        while( i.hasNext() ) {
            
            JSNItem = new JSONObject();
            if(sw){
                aux=(Items) i.next();
                total_filas=aux.getTotal_filas();
                sw=false;
            }else{aux=(Items) i.next();}
            
            JSNItem.put("codigo", aux.getCod_item());
            JSNItem.put("partida", aux.getPartida());
            JSNItem.put("articulo", aux.getArticulo());
            JSNItem.put("gestion", aux.getGestion());
            JSNItem.put("tipo", aux.getTipo());
                        
            
            //System.out.println("========== Para Analizar ======"+JSNItem);
            datos.add(JSNItem);
        }
        Listado.put("total_filas",total_filas); 
        
        Listado.put("Items", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
       
              
        return null;
    }      
}