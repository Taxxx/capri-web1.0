/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;
import java.util.Iterator;
import java.util.Locale;


/**
 *
 * @author UMSA-JES
 */
/*public class RastrearDetalle implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        

        return null;
    }
}*/
public class RastrearDetalle implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //System.out.println("Wolassssssssss");
        String clave =request.getParameter("cod_trans_detalle");
        System.out.println("Es clave :"+clave);
        //System.out.println("Esssssssssssssssss: "+clave);
        Transaccion trans= new Transaccion();
        trans.setCod_trans_detalle(clave);
        Iterator i=this.adqui.rastreoDetalle(trans).listIterator();
        //Iterator iterador = listaCuentas.listIterator(); //Le solicito a la lista que me devuelva un iterador con todos los el elementos contenidos en ella
 
        //Mientras que el iterador tenga un proximo elemento
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'a horas' HH:mm:ss", new Locale("es"));
        //Date fechaDate = new Date();
        //String fecha = formateador.format(fechaDate);
        //System.out.println("HURRAAAA: "+fecha);
        
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        out.println("<listado>");
        Transaccion aux;
        while( i.hasNext() ) {
            aux=(Transaccion) i.next();
            out.println("<detalle>");
            out.println("<estado>" + aux.getEstado() + "</estado>");
            out.println("<fecha>" + formateador.format(aux.getFecha_envio()) + "</fecha>");
            out.println("</detalle>");
        }
        out.println("</listado>");
        return null;
    }      
}
