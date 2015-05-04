package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
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

public class TransaccionBandeja implements Controller {
        
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
    String cod_w =request.getParameter("cod_w");
    
    System.out.println("------------- :D cod_tramite: "+cod_tramite+" cod_w: "+cod_w);
    
    Transaccion trans= new Transaccion();
    System.out.println("Es --> "+cliente.getId_usuario());
    trans.setCod_usuario(cliente.getId_usuario());
    trans.setCod_tramite(Integer.parseInt(cod_tramite)); 
    trans.setGestion(gestion);
    trans.setCod_w(Integer.parseInt(cod_w));

    Items tt = new Items();
    tt.setCod_tramite(Integer.parseInt(cod_tramite));
    tt.setCod_almacen(cliente.getCod_almacen());
            
    String tipo_tramite=this.adqui.getTipoTramite(tt);

    //PagedListHolder listaBandeja = new PagedListHolder(this.adqui.getTransaccionMateriales(trans));
    PagedListHolder listaBandeja = new PagedListHolder(this.adqui.getTransaccionSolicitudes(trans));
    listaBandeja.setPageSize(listaBandeja.getNrOfElements());    
    
    
    PagedListHolder listaEnviados = new PagedListHolder(this.adqui.getEnviados2(trans));
    listaEnviados.setPageSize(listaEnviados.getNrOfElements());
    System.out.println("OOOOOOOOOO nro elementos: "+listaEnviados.getNrOfElements());
    
    Map modelo = new HashMap();

    modelo.put("ListBandeja",listaBandeja);
    modelo.put("listaEnviados",listaEnviados);
    modelo.put("tipo_tramite",tipo_tramite);
    modelo.put("cod_tramite",cod_tramite);
    modelo.put("cod_w",cod_w);
    return new ModelAndView("transaccionMateriales/TransaccionBandeja", modelo);

  }
}