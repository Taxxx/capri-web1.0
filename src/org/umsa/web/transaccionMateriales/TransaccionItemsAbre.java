/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.transaccionMateriales;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.Items;
import org.umsa.domain.Operaciones;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author julian
 */

public class TransaccionItemsAbre implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {        

        String cod_transaccion =request.getParameter("cod_transaccion");
        String tipo_tramite =request.getParameter("tipo_tramite");
        String cod_tramite =request.getParameter("cod_tramite");
        String cod_w =request.getParameter("cod_w");
        String nro =request.getParameter("nro");
        
        Clientes cliente=new Clientes();
        cliente=(Clientes) request.getSession().getAttribute("__sess_cliente");
        
        
        
        
        Date hoy =new Date();
        Transaccion trans = new Transaccion();        
        trans.setCod_transaccion(Integer.parseInt(cod_transaccion));
        trans.setCod_tramite(Integer.parseInt(cod_tramite));
        trans.setCod_almacen(cliente.getCod_almacen());
        Transaccion items = this.adqui.getTransaccionMaterial(trans);
        
        System.out.println("1============================ El codigo del usuario es: "+cliente.getId_usuario());
        trans.setCod_usuario(cliente.getId_usuario());
        String apertura_padre=this.adqui.getAperturaPadre(trans);
        apertura_padre=apertura_padre.substring(5, apertura_padre.length());
//        System.out.println("2============================ La apertura muchachada es :"+apertura_padre);
        
        
        
        Operaciones o = new Operaciones();
        o.setGestion("2014");
        o.setCod_transaccion(cod_transaccion);
        
        
        System.out.println("La fechinga es --> -->"+items.getNro_gestion());

        Map modelo = new HashMap();        

        //PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(Integer.parseInt(cod_transaccion)));
        PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos2(Integer.parseInt(cod_transaccion)));
        listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
       //listaTransaccionArticulos.getNrOfElements()
        modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);
        
        PagedListHolder ListaUM = new PagedListHolder(this.adqui.getUnidadesMedida());
        ListaUM.setPageSize(ListaUM.getNrOfElements());
        modelo.put("ListaUM",ListaUM);
        
        PagedListHolder listaDocs = new PagedListHolder(this.adqui.getAdjuntos(Integer.parseInt(cod_transaccion)));
        listaDocs.setPageSize(listaDocs.getNrOfElements());
        modelo.put("listaDocs",listaDocs);
        
        System.out.println("Docs Existentes --> "+listaDocs.getNrOfElements());
        
        PagedListHolder listaItemTipo = new PagedListHolder(this.adqui.getItemTipo());
        listaItemTipo.setPageSize(listaItemTipo.getNrOfElements());            
        modelo.put("listaItemTipo",listaItemTipo);
        
        PagedListHolder listaTipoAdj = new PagedListHolder(this.adqui.GetTiposADJ());
        listaTipoAdj.setPageSize(listaTipoAdj.getNrOfElements());            
        modelo.put("listaTipoAdj",listaTipoAdj);
        
        
        PagedListHolder listaAdjuntos = new PagedListHolder(this.adqui.ListaAdjuntos(o));
        listaAdjuntos.setPageSize(listaAdjuntos.getNrOfElements());            
        modelo.put("listaAdjuntos",listaAdjuntos);
        
        
        //String gestion =request.getParameter("gestion");
        //if(gestion.length()==0){gestion="%";}
        
        PagedListHolder lista_partidas = new PagedListHolder(this.adqui.GetPartidas(o));
        lista_partidas.setPageSize(lista_partidas.getNrOfElements());            
        modelo.put("lista_partidas",lista_partidas);
        
        /*
        if (tipo==1){
            PagedListHolder listaDetInsumo = new PagedListHolder(this.adqui.getItemTipo());
            listaDetInsumo.setPageSize(listaDetInsumo.getNrOfElements());
            
            ruta="pedidoMateriales/PedidoInsumos";
            modelo.put("listaDetInsumo",listaDetInsumo);            
        }
        else if (tipo==2){
            PagedListHolder listaDetActivos = new PagedListHolder(this.adqui.getDetalleActivos());
            listaDetActivos.setPageSize(listaDetActivos.getNrOfElements());
            modelo.put("listaDetActivos",listaDetActivos);
            ruta="pedidoMateriales/PedidoActivos";
        }
        else {
            PagedListHolder listaDetServicios = new PagedListHolder(this.adqui.getDetalleServicios());
            listaDetServicios.setPageSize(listaDetServicios.getNrOfElements());
            modelo.put("listaDetServicios",listaDetServicios);
            ruta="pedidoMateriales/PedidoServicios";
        }*/        
        
        String ruta ="";
        if (Integer.parseInt(cod_w)==3){            
            List item = null;
            //if (listaTransaccionArticulos.getNrOfElements()==0 )
                item = this.adqui.getBuscaConsultoresObras(4);
            modelo.put("items",item);
            //ruta="transaccionMateriales/TransaccionConsultoresDetalle";
            ruta="transaccionMateriales/Transaccion2";
        }
        else if (Integer.parseInt(cod_w)==4){            
            List item = null;
           // if (listaTransaccionArticulos.getNrOfElements()==0 )
                item = this.adqui.getBuscaConsultoresObras(5);
            modelo.put("items",item);
            //ruta="transaccionMateriales/TransaccionObrasDetalle";
            ruta="transaccionMateriales/Transaccion2";
        }
        else {
            //ruta="transaccionMateriales/TransaccionItems";
            ruta="transaccionMateriales/Transaccion2";
        }
        
        modelo.put("tipo_tramite",tipo_tramite );
        modelo.put("cod_tramite",cod_tramite );
        modelo.put("cod_w",cod_w );
        modelo.put("usuario_sol",items.getUsuario_sol() );
        modelo.put("detalle",items.getDetalle() );
        modelo.put("ue_solicitante",items.getUnidad_sol() );
        //System.out.println("3====================== apertura ue_sol: "+items.getUnidad_sol());
        modelo.put("ue_padre",apertura_padre);
        modelo.put("ue_destino",items.getUnidad_des() );
        modelo.put("cod_transaccion",items.getCod_transaccion());
        modelo.put("nro_gestion",items.getNro_gestion());
        modelo.put("cuantia",items.getCuantia());
        
        return new ModelAndView(ruta, modelo);
    }
}
