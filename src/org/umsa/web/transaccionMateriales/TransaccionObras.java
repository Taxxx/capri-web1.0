/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author julian
 */
public class TransaccionObras implements Controller {

    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String tipo_tramite =request.getParameter("tipo_tramite");
        String cod_w =request.getParameter("cod_w");
        String cod_tramite =request.getParameter("cod_tramite");
        
        Map modelo = new HashMap();        
        Clientes cliente=new Clientes();
        cliente=(Clientes) request.getSession().getAttribute("__sess_cliente");
        PagedListHolder listaUE = new PagedListHolder(this.adqui.getUnidadEjecutora(cliente));
        listaUE.setPageSize(listaUE.getNrOfElements());
        PagedListHolder cuantia = new PagedListHolder(this.adqui.getCuantia());
        cuantia.setPageSize(cuantia.getNrOfElements());
        modelo.put("listaUE", listaUE);
        modelo.put("listaCuantia", cuantia);
        modelo.put("tipo_tramite", tipo_tramite);
        modelo.put("cod_tramite", cod_tramite);
        modelo.put("cod_w", cod_w);
        return new ModelAndView("transaccionMateriales/TransaccionObras", modelo);
    }
}
