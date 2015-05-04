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
import org.umsa.domain.Operaciones;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author julian
 */
public class TransaccionArticuloGuarda implements Controller {

    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String tipo_tramite =request.getParameter("tipo_tramite");
        String cod_tramite =request.getParameter("cod_tramite");
        String usuario_sol =request.getParameter("usuario_sol");
        String detalle =request.getParameter("detalle");
        String ue_solicitante =request.getParameter("ue_solicitante");
        String ue_destino =request.getParameter("ue_destino");
        String cod_transaccion =request.getParameter("cod_transaccion");
        String nro_gestion =request.getParameter("nro_gestion");
        
        String unidad_medida =request.getParameter("unidad_medida");
        String cantidad =request.getParameter("cantidad").trim();
        String cod_w=request.getParameter("cod_w").trim();
        
        String cod_item =request.getParameter("cod_item").trim();
        
        Clientes cliente=new Clientes();
        cliente=(Clientes) request.getSession().getAttribute("__sess_cliente"); 
        int gestion = (Integer)request.getSession().getAttribute("__sess_gestion");
            
        Map modelo = new HashMap();
         /**/
        System.out.println("cod_trans: "+Integer.parseInt(cod_transaccion));
        System.out.println("cod_item1: "+cod_item);
        System.out.println("cod_item2: "+Integer.parseInt(cod_item));
        System.out.println("unidad_medida"+unidad_medida);
        System.out.println("cantidad:"+Integer.parseInt(cantidad));
        System.out.println("gestion: "+gestion);
        /**/
        Transaccion item = new Transaccion();
        item.setCod_transaccion(Integer.parseInt(cod_transaccion));
        item.setCod_item(Integer.parseInt(cod_item));        
        item.setUnidad_medida(unidad_medida);
        item.setCantidad_pedido(Integer.parseInt(cantidad));
        item.setGestion(gestion);

       
        //Transaccion tiene_reg = this.adqui.getTransaccionCodItem(item);

        //if (tiene_reg==null)
            this.adqui.setTransaccionArticulo(item);

        PagedListHolder listaItemTipo = new PagedListHolder(this.adqui.getItemTipo());
        listaItemTipo.setPageSize(listaItemTipo.getNrOfElements());            
        modelo.put("listaItemTipo",listaItemTipo);
        
        PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(item.getCod_transaccion()));
        listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
        modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);               
        
        PagedListHolder listaDocs = new PagedListHolder(this.adqui.getAdjuntos(Integer.parseInt(cod_transaccion)));
        listaDocs.setPageSize(listaDocs.getNrOfElements());
        modelo.put("listaDocs",listaDocs);
        
        PagedListHolder listaTipoAdj = new PagedListHolder(this.adqui.GetTiposADJ());
        listaTipoAdj.setPageSize(listaTipoAdj.getNrOfElements());            
        modelo.put("listaTipoAdj",listaTipoAdj);
        
        Operaciones o = new Operaciones();
        o.setGestion("2014");
        o.setCod_transaccion(cod_transaccion);
        PagedListHolder lista_partidas = new PagedListHolder(this.adqui.GetPartidas(o));
        lista_partidas.setPageSize(lista_partidas.getNrOfElements());            
        modelo.put("lista_partidas",lista_partidas);
        
        PagedListHolder listaAdjuntos = new PagedListHolder(this.adqui.ListaAdjuntos(o));
        listaAdjuntos.setPageSize(listaAdjuntos.getNrOfElements());            
        modelo.put("listaAdjuntos",listaAdjuntos);
        
        modelo.put("tipo_tramite",tipo_tramite );
        modelo.put("cod_tramite",cod_tramite );
        modelo.put("usuario_sol",usuario_sol );
        modelo.put("detalle",detalle );
        modelo.put("ue_solicitante",ue_solicitante );
        modelo.put("ue_destino",ue_destino );
        modelo.put("cod_transaccion",cod_transaccion);
        modelo.put("nro_gestion",nro_gestion);
        modelo.put("cod_w",cod_w);
        String ruta="";
        if (Integer.parseInt(cod_w)==3){
            List itemk = null;
            //if (listaTransaccionArticulos.getNrOfElements()==0 )
            itemk = this.adqui.getBuscaConsultoresObras(4);
            modelo.put("items",itemk);
            //ruta="transaccionMateriales/TransaccionConsultoresDetalle";
            ruta="transaccionMateriales/Transaccion2";
        }
        else if (Integer.parseInt(cod_w)==4){
            List itemk = null;
            //if (listaTransaccionArticulos.getNrOfElements()==0 )
            itemk = this.adqui.getBuscaConsultoresObras(5);
            modelo.put("items",itemk);
            //ruta="transaccionMateriales/TransaccionObrasDetalle";
            ruta="transaccionMateriales/Transaccion2";
        }          
            
        else{
            //ruta="transaccionMateriales/TransaccionItems";
             ruta="transaccionMateriales/Transaccion2";
        }           
            
        return new ModelAndView(ruta, modelo);
    }
}
