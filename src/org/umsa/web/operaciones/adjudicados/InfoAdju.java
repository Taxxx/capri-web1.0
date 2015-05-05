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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Adjudicado;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author alex
 */
public class InfoAdju implements Controller
{
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public MiFacade getAdqui() {
        return adqui;
    }
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PrintWriter out = response.getWriter();
        String documento =request.getParameter("documento");
        Adjudicado adju=new Adjudicado();
        adju.setDocumento(documento);
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        System.out.println("EL codigo es --> "+adju.getDocumento());
        Iterator i=this.adqui.InfAdju(adju).listIterator();
        JSONObject JSNRastreo;
        response.setContentType("application/json; charset=UTF-8");
        Adjudicado aux;
        while( i.hasNext() ) {
            System.out.println("entra al while");
            JSNRastreo = new JSONObject();
            aux=(Adjudicado) i.next();
            System.out.println(aux.getDocumento());
            System.out.println(aux.getDireccion());
            System.out.println(aux.getTelefono());
            System.out.println(aux.getNombre());
            System.out.println(aux.getTipo_id());
            JSNRastreo.put("adh_documento", aux.getDocumento());
            JSNRastreo.put("dir_direccion", aux.getDireccion());
            JSNRastreo.put("dir_telefono", aux.getTelefono());
            JSNRastreo.put("adh_nombre", aux.getNombre());
            JSNRastreo.put("adh_tipo_id", aux.getTipo_id());
            System.out.println("========== Para Analizar ======"+JSNRastreo);
            datos.add(JSNRastreo);
        }
        Listado.put("Adjudicado", datos);
        System.out.println("===================== Yeah ======================");
        System.out.println(Listado.toJSONString());
        out.print(Listado);
        return null;
    }
    
}
