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
 * @author UMSA-JES
 */
public class GetItems implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    /*public String set_total_filas(Iterator i){
        if(i.hasNext()){return i.}
        return "0";
    }*/
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String partida =request.getParameter("partida");
        
        
        //System.out.println("La descripcion de la partida es : "+desc_partida);
        
        Operaciones o = new Operaciones();
        o.setPartida(partida);
        //o.setPartida(partida);
        Operaciones aux2=(Operaciones)this.adqui.GetDescPartida(o).get(0);
        //System.out.println("La descripcion de la partida es : "+aux2.getDescripcion());
        //System.out.println("La descripcion de la partida es : "+desc_partida);
        
        /*Iterator ii=this.adqui.GetDescPartida(o).listIterator();
        Operaciones aux2;
        while( ii.hasNext() ) {
            aux2=(Operaciones) ii.next();
            System.out.println("La descripcion de la partida es : "+aux2.getDescripcion());
        }*/
        
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
                
        Iterator i=this.adqui.GetItems(o).listIterator();
        Operaciones aux;
        JSONObject JSNItems;
        
        while( i.hasNext() ) {
            
            JSNItems = new JSONObject();
            aux=(Operaciones) i.next();
            JSNItems.put("cod_item", aux.getCod_item());
            
            JSNItems.put("articulo", aux.getArticulo());
            JSNItems.put("detalle", aux.getDetalle());
            JSNItems.put("unidad_medida", aux.getUnidad_medida());
            
            System.out.println("========== Para Analizar ======"+JSNItems);
            datos.add(JSNItems);
        }
        
        //
        /****************/
        Listado.put("Items", datos);
        Listado.put("Descripcion", aux2.getDescripcion());
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        //System.out.println("La gestión es :"+gestion);
        return null;        
    }
}
