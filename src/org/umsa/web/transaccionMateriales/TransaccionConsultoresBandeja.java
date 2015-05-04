package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import org.umsa.domain.Clientes;
import org.umsa.domain.Roles;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

public class TransaccionConsultoresBandeja implements Controller {
        
  private MiFacade adqui;
 
  public void setAdqui(MiFacade adqui) {
    this.adqui = adqui;
  } 

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
//    Recuperamos las variables

    int gestion=(Integer) request.getSession().getAttribute("__sess_gestion");
    Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
    Roles rol = (Roles) request.getSession().getAttribute("__sess_rol");

    String cod_w =request.getParameter("cod_w");
    String cod_tramite =request.getParameter("cod_tramite");
        
    Transaccion trans= new Transaccion();
    trans.setCod_usuario(cliente.getId_usuario());
    trans.setCod_w(Integer.parseInt(cod_w)); 
    trans.setGestion(gestion);

    PagedListHolder listaBandeja = new PagedListHolder(this.adqui.getTransaccionConsultores(trans));
    listaBandeja.setPageSize(listaBandeja.getNrOfElements());    
    PagedListHolder listaEnviados = new PagedListHolder(this.adqui.getEnviados(trans));
    listaEnviados.setPageSize(listaEnviados.getNrOfElements());
    System.out.println("OOOOOOOOOO nro elementos: "+listaEnviados.getNrOfElements());
    
    
    Map modelo = new HashMap();
    
    modelo.put("listaEnviados",listaEnviados);
    modelo.put("ListBandeja",listaBandeja);
    modelo.put("cod_tramite",cod_tramite);
    modelo.put("cod_w",cod_w);
    modelo.put("tipo_tramite","CONSULTORIAS");
    //return new ModelAndView("transaccionMateriales/TransaccionConsultoresBandeja", modelo);
    return new ModelAndView("transaccionMateriales/TransaccionBandeja", modelo);

  }
}