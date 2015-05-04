/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class EnviadosDetalle implements Controller {
    private MiFacade adqui;
    //String getCodUmsa;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cod_transaccion =request.getParameter("cod_transaccion");
        Transaccion trans= new Transaccion();
        //System.out.println("Eurekaaaa: "+cod_transaccion);
        trans.setCod_transaccion(Integer.parseInt(cod_transaccion));
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        JSONObject JSNDetalle;
        Iterator i=this.adqui.getEnviadosDetalle(trans).listIterator();
        //System.out.println("El tamaño es --> "+this.adqui.busqueda(trans).size());
        Transaccion aux;
        while( i.hasNext() ) {
            JSNDetalle = new JSONObject();
            aux=(Transaccion) i.next();
            /*out.println("<archivo>");
            out.println("<nombre_archivo>" + aux.getTerminos_ref() + "</nombre_archivo>");
            out.println("</archivo>");*/
            JSNDetalle.put("cuce", aux.getCuce());
            JSNDetalle.put("articulo", aux.getArticulo());
            JSNDetalle.put("detalle", aux.getDetalle());
            JSNDetalle.put("estado", aux.getEstado());
            System.out.println("========== Para Analizar ======"+JSNDetalle);
            datos.add(JSNDetalle);
        }
        //out.println("</listado>");
        Listado.put("Transaccion", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        return null;
    }
    
}
