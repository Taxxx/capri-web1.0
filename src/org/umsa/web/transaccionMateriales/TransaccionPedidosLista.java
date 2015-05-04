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
import org.umsa.domain.Roles;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

public class TransaccionPedidosLista implements Controller {
        
  private MiFacade adqui;
 
  public void setAdqui(MiFacade adqui) {
    this.adqui = adqui;
  } 

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
//    Recuperamos las variables

    int gestion=(Integer) request.getSession().getAttribute("__sess_gestion");
    Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
    Roles rol = (Roles) request.getSession().getAttribute("__sess_rol");

    String cod_tramite =request.getParameter("cod_tramite");
        
    Transaccion trans= new Transaccion();
    trans.setCod_usuario(cliente.getId_usuario());
    trans.setCod_tramite(Integer.parseInt(cod_tramite)); 
    trans.setGestion(gestion);

    Items tt = new Items();
    tt.setCod_tramite(Integer.parseInt(cod_tramite));
    tt.setCod_almacen(cliente.getCod_almacen());
            
    String tipo_tramite=this.adqui.getTipoTramite(tt);

    PagedListHolder listaPedidos = new PagedListHolder(this.adqui.getTransaccionListaPedidos(trans));
    listaPedidos.setPageSize(listaPedidos.getNrOfElements());    
            
    Map modelo = new HashMap();
    modelo.put("ListPedidos",listaPedidos);
    modelo.put("tipo_tramite",tipo_tramite);
    modelo.put("cod_tramite",cod_tramite);
    return new ModelAndView("transaccionMateriales/TransaccionListaPedidos", modelo);

  }
}