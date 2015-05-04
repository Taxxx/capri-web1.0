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
import org.umsa.domain.Items;

import org.umsa.domain.logic.MiFacade;




public class TransaccionConsultoresDetalle implements Controller {
        
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

    String tipo_item =request.getParameter("tipo_item");
    String grupo =request.getParameter("grupo");
    String rubro =request.getParameter("rubro");
    String articulo =request.getParameter("articulo");
    String search = request.getParameter("search");
    String cod_w= request.getParameter("cod_w");
    
    PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(Integer.parseInt(cod_transaccion)));
    listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());    
    
    PagedListHolder listaDocs = new PagedListHolder(this.adqui.getAdjuntos(Integer.parseInt(cod_transaccion)));
    listaDocs.setPageSize(listaDocs.getNrOfElements());
    
    
    List items = this.adqui.getBuscaConsultoresObras(4);                
    
    //PASO DE PARAMETROS A LA VISTA
    modelo.put("tipo_tramite",tipo_tramite);
    modelo.put("cod_tramite",cod_tramite);
    modelo.put("cod_w",cod_w);
    modelo.put("usuario_sol",usuario_sol);
    modelo.put("detalle",detalle);
    modelo.put("ue_solicitante",ue_solicitante);
    modelo.put("ue_destino",ue_destino);
    modelo.put("cod_transaccion",cod_transaccion);
    modelo.put("nro_gestion",nro_gestion);
    
    modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);
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

    System.out.println("Emtro :P");
    return new ModelAndView("transaccionMateriales/TransaccionConsultoresDetalle", modelo);
  }
}