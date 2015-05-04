/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectSIGMA.SigmaCapriWSServiceLocator;
import org.umsa.ConnectSIGMA.SigmaCapriWS_PortType;
import org.umsa.domain.logic.MiFacade;
/**
 *
 * @author UMSA-JES
 */
public class GetPOA2 implements Controller {
    
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
       

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SigmaCapriWSServiceLocator servicio = new SigmaCapriWSServiceLocator();
            SigmaCapriWS_PortType puerto = servicio.getSigmaCapriWS();
            Map[] datos=puerto.getEjecucionPptoSIGMA("2014", "139", "10", "010", "10", "0000", "001");
            if(datos!=null){
                System.out.println("Con Datos...");
            }
            else
                System.out.println("Sin Datos...");
        } catch (Exception e) {
        }
        JSONObject Listado = new JSONObject();
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(Listado);
        
        
        return null;
    }      
}