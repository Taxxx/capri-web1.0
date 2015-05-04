/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Operaciones;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author julian
 */
public class TransaccionEliminaDoc implements Controller {

    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tipo_tramite =request.getParameter("tipo_tramite");
        String usuario_sol =request.getParameter("usuario_sol");
        String detalle =request.getParameter("detalle");
        String ue_solicitante =request.getParameter("ue_solicitante");
        String ue_destino =request.getParameter("ue_destino");        
        String cod_transaccion =request.getParameter("cod_transaccion");        
        String cod_tramite =request.getParameter("cod_tramite");
        String nro_gestion =request.getParameter("nro_gestion");        
        String cod_w =request.getParameter("cod_w");        
        String cod_docs =request.getParameter("cod_docs");        
             
        Map modelo = new HashMap();

        this.adqui.delTransaccionTerminos(Integer.parseInt(cod_docs));
        
        PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(Integer.parseInt(cod_transaccion)));
        listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
        modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);
        
        PagedListHolder listaDocs = new PagedListHolder(this.adqui.getAdjuntos(Integer.parseInt(cod_transaccion)));
        listaDocs.setPageSize(listaDocs.getNrOfElements());
        modelo.put("listaDocs",listaDocs);
        
        Operaciones o = new Operaciones();
        o.setGestion("2014");
        o.setCod_transaccion(cod_transaccion);
        
        PagedListHolder lista_partidas = new PagedListHolder(this.adqui.GetPartidas(o));
        lista_partidas.setPageSize(lista_partidas.getNrOfElements());            
        modelo.put("lista_partidas",lista_partidas);
        
        PagedListHolder listaAdjuntos = new PagedListHolder(this.adqui.ListaAdjuntos(o));
        listaAdjuntos.setPageSize(listaAdjuntos.getNrOfElements());            
        modelo.put("listaAdjuntos",listaAdjuntos);
            
        String ruta ="";
        if (Integer.parseInt(cod_w)==3){            
            List item = null;
            //if (listaTransaccionArticulos.getNrOfElements()==0 )
                item = this.adqui.getBuscaConsultoresObras(4);
            modelo.put("items",item);
            System.out.println("Sale Consultor --> "+cod_w);
            //ruta="transaccionMateriales/TransaccionConsultoresDetalle";
            ruta="transaccionMateriales/Transaccion2";
        }
        else if (Integer.parseInt(cod_w)==4){               
            List item = null;
            //if (listaTransaccionArticulos.getNrOfElements()==0 )
                item = this.adqui.getBuscaConsultoresObras(5);
            modelo.put("items",item);
            
            System.out.println("Sale Obras --> "+cod_w);
            //ruta="transaccionMateriales/TransaccionObrasDetalle";
            ruta="transaccionMateriales/Transaccion2";
        }
        else {
            System.out.println("Sale Items --> "+cod_w);
            //ruta="transaccionMateriales/TransaccionItems";
            ruta="transaccionMateriales/Transaccion2";
        }                          
        
        modelo.put("tipo_tramite",tipo_tramite );
        modelo.put("usuario_sol",usuario_sol );
        modelo.put("detalle",detalle );
        modelo.put("ue_solicitante",ue_solicitante );
        modelo.put("ue_destino",ue_destino );
        modelo.put("cod_transaccion",cod_transaccion);        
        modelo.put("cod_tramite",cod_tramite);
        modelo.put("cod_w",cod_w);
        modelo.put("nro_gestion",nro_gestion);

        return new ModelAndView(ruta, modelo);
    }
}
