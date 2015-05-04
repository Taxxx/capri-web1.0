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
import org.umsa.domain.Clientes;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;
//import org.umsa.web.herramientas.i_formatterDate;

/**
 *
 * @author julian
 */
public class TransaccionObrasAbre implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {                      
        String tipo_tramite =request.getParameter("tipo_tramite");
        String cod_tramite =request.getParameter("cod_tramite");
        String cod_w =request.getParameter("cod_w");
        String cod_transaccion =request.getParameter("cod_transaccion");        
        
        Clientes cliente=new Clientes();
        cliente=(Clientes) request.getSession().getAttribute("__sess_cliente");
                
        Transaccion trans = new Transaccion();        
        trans.setCod_transaccion(Integer.parseInt(cod_transaccion));
        trans.setCod_tramite(Integer.parseInt(cod_tramite));
        trans.setCod_almacen(cliente.getCod_almacen());
        Transaccion items = this.adqui.getTransaccionMaterial(trans);                      
                
        Map modelo = new HashMap();
        
        PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(Integer.parseInt(cod_transaccion)));
        listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
        modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);
        
        PagedListHolder listaDocs = new PagedListHolder(this.adqui.getAdjuntos(Integer.parseInt(cod_transaccion)));
        listaDocs.setPageSize(listaDocs.getNrOfElements());
        modelo.put("listaDocs",listaDocs);
        
        PagedListHolder listaTipoAdj = new PagedListHolder(this.adqui.GetTiposADJ());
        listaTipoAdj.setPageSize(listaTipoAdj.getNrOfElements());            
        modelo.put("listaTipoAdj",listaTipoAdj);
        
        List item = null;
        //if (listaTransaccionArticulos.getNrOfElements()==0 )
            item = this.adqui.getBuscaConsultoresObras(5);
        modelo.put("items",item);
        
        modelo.put("tipo_tramite",tipo_tramite );           
        modelo.put("cod_transaccion",cod_transaccion);
        modelo.put("cod_w",cod_w);
        modelo.put("cod_tramite",cod_tramite);        
        
        modelo.put("usuario_sol",items.getUsuario_sol() );
        modelo.put("detalle",items.getDetalle() );
        modelo.put("ue_solicitante",items.getUnidad_sol() );
        modelo.put("ue_destino",items.getUnidad_des() );
        modelo.put("cod_transaccion",items.getCod_transaccion());
        modelo.put("nro_gestion",items.getNro_gestion());                
        modelo.put("cuantia",items.getCuantia());               
        //return new ModelAndView("transaccionMateriales/TransaccionObrasDetalle", modelo);
        return new ModelAndView("transaccionMateriales/Transaccion2", modelo);
    }
}
