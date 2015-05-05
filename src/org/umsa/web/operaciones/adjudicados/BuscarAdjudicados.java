/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.adjudicados;
import java.io.PrintWriter;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//import org.umsa.domain.adjudicados;
import org.umsa.domain.logic.MiFacade;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.umsa.domain.Adjudicado;




/**
 *
 * @author alex
 */
public class BuscarAdjudicados implements Controller
{
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        System.out.println("en mifacede");
        this.adqui = adqui;
    }
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        System.out.println("entro al controlador");
        String codigo=request.getParameter("codigo");
        String nombre=request.getParameter("nombre");
        String partida=request.getParameter("partida");
        String articulo=request.getParameter("articulo");
        String gestion=request.getParameter("gestion");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        //====================================
        if(codigo.length()==0){codigo="%";}
        if(partida.length()==0){partida="%";}
        if(articulo.length()==0){articulo="%";}
        if(gestion.length()==0){gestion="%";}
        if(nombre.length()==0){nombre="%";}
        //========================================
        System.out.println(codigo);
        System.out.println(partida);
        System.out.println(articulo);
        System.out.println(gestion);
        System.out.println(nombre);
        System.out.println(min+"   "+max);
        //==========================================
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        //Listado.put("total_filas", "115"); 
        Adjudicado item= new Adjudicado();
        item.setDocumento(codigo);
        item.setPartida(partida);
        item.setArticulo(articulo);
        item.setGestion(gestion);
        item.setNombre(nombre);
        item.setMin(min);
        item.setMax(max);
        System.out.println(item.getDocumento()+" "+item.getPartida()+" "+item.getArticulo()+" "+item.getGestion()+" "+item.getMax()+" "+item.getMin());
        Iterator i=this.adqui.BuscaAdjudicados(item).listIterator();
        System.out.println(item.getDocumento()+" "+item.getPartida()+" "+item.getArticulo()+" "+item.getGestion()+" "+item.getMax()+" "+item.getMin());
        
        Adjudicado aux;
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
                aux=(Adjudicado) i.next();
                total_filas=aux.getTotal_filas();
                sw=false;
            }else{aux=(Adjudicado) i.next();}
            JSNItem.put("tipo_id", aux.getTipo_id());
            JSNItem.put("codigo", aux.getDocumento());
            JSNItem.put("nombre", aux.getNombre());
            JSNItem.put("nombre_comercial", aux.getNombre_comercial());
            JSNItem.put("gestion", aux.getGestion());
            System.out.println("========== Para Analizar ======"+JSNItem);
            datos.add(JSNItem);
        }
        Listado.put("total_filas",total_filas); 
        
        Listado.put("Adjudicado", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
       
        return null;
    }
    
}
