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
public class GetPartidas implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    /*public String set_total_filas(Iterator i){
        if(i.hasNext()){return i.}
        return "0";
    }*/
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String gestion =request.getParameter("gestion");
        if(gestion.length()==0){gestion="%";}
        Operaciones o = new Operaciones();
        o.setGestion(gestion);
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
                
        Iterator i=this.adqui.GetPartidas(o).listIterator();
        Operaciones aux;
        JSONObject JSNPartida;
        
        while( i.hasNext() ) {
            
            JSNPartida = new JSONObject();
            aux=(Operaciones) i.next();
                        
            JSNPartida.put("partida", aux.getPartida());
            JSNPartida.put("detalle_partida", aux.getDetalle_partida());
            
            System.out.println("========== Para Analizar ======"+JSNPartida);
            datos.add(JSNPartida);
        }
        
        //
        /****************/
        Listado.put("Partidas", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        //System.out.println("La gestión es :"+gestion);
        return null;
    }
}
