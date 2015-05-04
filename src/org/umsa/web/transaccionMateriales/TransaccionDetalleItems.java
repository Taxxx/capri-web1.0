package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.Items;
import org.umsa.domain.Operaciones;

import org.umsa.domain.logic.MiFacade;

public class TransaccionDetalleItems implements Controller {
        
  private MiFacade adqui;
 
  public void setAdqui(MiFacade adqui) {
    this.adqui = adqui;
  } 

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map modelo = new HashMap();

//    Recuperamos las variables
    request.setCharacterEncoding("UTF-8");
    String tipo_tramite =request.getParameter("tipo_tramite");
    String cod_tramite =request.getParameter("cod_tramite");
    String usuario_sol =request.getParameter("usuario_sol");
    String detalle =request.getParameter("detalle");
    String ue_solicitante =request.getParameter("ue_solicitante");
    String ue_destino =request.getParameter("ue_destino");
    String cod_transaccion =request.getParameter("cod_transaccion");
    String nro_gestion =request.getParameter("nro_gestion");
    String cuantia =request.getParameter("cuantia");
    
    
    int gestion=(Integer) request.getSession().getAttribute("__sess_gestion");
    System.out.println("La gestion viene a ser --> "+gestion);
    
    String tipo_item =request.getParameter("tipo_item");
    String grupo =request.getParameter("grupo");
    String rubro =request.getParameter("rubro");
    String articulo =request.getParameter("articulo");
    /*String unidad_medida =request.getParameter("unidad_medida");
    String cantidad =request.getParameter("cantidad");*/
    String search = request.getParameter("search");
    String cod_w= request.getParameter("cod_w");
    
    Clientes cliente=new Clientes();
    cliente=(Clientes) request.getSession().getAttribute("__sess_cliente");
    
    Operaciones o = new Operaciones();
    o.setGestion("2014");
    o.setCod_transaccion(cod_transaccion);
    
    search = "%"+search.toUpperCase()+"%";
    
    /*    
    //TIPO ITEM
    PagedListHolder listaItemTipo = new PagedListHolder(this.adqui.getItemTipo());
    listaItemTipo.setPageSize(listaItemTipo.getNrOfElements());            
    
    int cod_tipo_item=0;
    
    String [] campos = tipo_item.split(" - ");  
    try { cod_tipo_item=Integer.parseInt(campos[0]); }
    catch(NumberFormatException e){}
    Items ti = new Items();
    ti.setCod_tipo_item(cod_tipo_item);    
    
    PagedListHolder listaItemGrupo=null;
    PagedListHolder listaItemRubro=null;
    PagedListHolder listaItemArticulo=null;
    String unidad_medida="";
    
    //GRUPO
    if (!"--".equals(tipo_item)){                
        listaItemGrupo = new PagedListHolder(this.adqui.getItemGrupo(cod_tipo_item));
        listaItemGrupo.setPageSize(listaItemGrupo.getNrOfElements());
    }
    //RUBRO
    if (!"--".equals(grupo)){        
        ti.setGrupo(grupo);
        listaItemRubro = new PagedListHolder(this.adqui.getItemRubro(ti));
        listaItemRubro.setPageSize(listaItemRubro.getNrOfElements());
    }
    //ARTICULO
    if (!"--".equals(rubro)){        
        ti.setRubro(rubro);
        listaItemArticulo = new PagedListHolder(this.adqui.getItemArticulo(ti));
        listaItemArticulo.setPageSize(listaItemArticulo.getNrOfElements());
    }           
    //DATOS COMPLEMENTARIOS DEL ARTICULO
    if (!"--".equals(articulo)){        
        ti.setArticulo(articulo);
        unidad_medida = this.adqui.getItemArticuloUnidadMedida(ti);        
    }           
    */
    PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(Integer.parseInt(cod_transaccion)));
    listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
    
    PagedListHolder listaDocs = new PagedListHolder(this.adqui.getAdjuntos(Integer.parseInt(cod_transaccion)));
    listaDocs.setPageSize(listaDocs.getNrOfElements());
    
    System.out.println("el codigo del wf es --> "+cod_w);
    Items ti = new Items();
    ti.setArticulo(search);
    
    //if()
    //ti.setCod_tipo_item("%");
    List items;
    if(cod_w.equals("6")){
        ti.setCod_tipo_item(3);
        System.out.println("Buscara solo servicios");
        items = this.adqui.getBuscaItems(ti);
    }
    else{
        if(cod_w.equals("7")){
            System.out.println("Buscara solo bienes");
            ti.setCod_tipo_item(1);
            List L1 = this.adqui.getBuscaItems(ti);
            ti.setCod_tipo_item(2);
            List L2 = this.adqui.getBuscaItems(ti);
            items =ListUtils.union(L1,L2);
        }
        else{
            System.out.println("Buscara todo");
            ti.setCod_tipo_item(1);
            List L1 = this.adqui.getBuscaItems(ti);
            ti.setCod_tipo_item(2);
            List L2 = this.adqui.getBuscaItems(ti);
            ti.setCod_tipo_item(3);
            List L3 = this.adqui.getBuscaItems(ti);
            items =ListUtils.union(ListUtils.union(L1,L2),L3);
            //items = this.adqui.getBuscaItems(ti);
        }
    }
        
    PagedListHolder listaTipoAdj = new PagedListHolder(this.adqui.GetTiposADJ());
    listaTipoAdj.setPageSize(listaTipoAdj.getNrOfElements());            
   
    PagedListHolder listaAdjuntos = new PagedListHolder(this.adqui.ListaAdjuntos(o));
    listaAdjuntos.setPageSize(listaAdjuntos.getNrOfElements());            
    modelo.put("listaAdjuntos",listaAdjuntos);
    
    PagedListHolder lista_partidas = new PagedListHolder(this.adqui.GetPartidas(o));
    lista_partidas.setPageSize(lista_partidas.getNrOfElements());            
    modelo.put("lista_partidas",lista_partidas);
    //PASO DE PARAMETROS A LA VISTA
    modelo.put("listaTipoAdj",listaTipoAdj);
    modelo.put("tipo_tramite",tipo_tramite);
    modelo.put("cod_tramite",cod_tramite);
    modelo.put("cod_w",cod_w);
    modelo.put("usuario_sol",usuario_sol);
    modelo.put("detalle",detalle);
    modelo.put("ue_solicitante",ue_solicitante);
    modelo.put("ue_destino",ue_destino);
    modelo.put("cod_transaccion",cod_transaccion);
    modelo.put("nro_gestion",nro_gestion);
    modelo.put("cuantia",cuantia);
    modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);
    System.out.println("Guardoooooo");
    modelo.put("listaDocs",listaDocs);
    modelo.put("items",items);
    /*modelo.put("listaItemTipo",listaItemTipo); 
    modelo.put("listaItemGrupo",listaItemGrupo);
    modelo.put("listaItemRubro",listaItemRubro);
    modelo.put("listaItemArticulo",listaItemArticulo);
    modelo.put("unidad_medida",unidad_medida);*/
    
    modelo.put("tipo_item",tipo_item);
    modelo.put("grupo",grupo);
    modelo.put("rubro",rubro);
    modelo.put("articulo",articulo);

    
    //return new ModelAndView("transaccionMateriales/TransaccionItems", modelo);
    return new ModelAndView("transaccionMateriales/Transaccion2", modelo);
  }
}