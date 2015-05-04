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

public class TransaccionElimina implements Controller {
        
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
    String cod_transaccion =request.getParameter("cod_transaccion");
    String tipo_tram=request.getParameter("tipo_tramite");
    
    this.adqui.setEliminaTransaccion(Integer.parseInt(cod_transaccion));
    
    Transaccion trans= new Transaccion();
    trans.setCod_usuario(cliente.getId_usuario());
    trans.setCod_tramite(Integer.parseInt(cod_tramite)); 
    trans.setGestion(gestion);
    trans.setCod_w(Integer.parseInt(cod_w));
    Items tt = new Items();
    tt.setCod_tramite(Integer.parseInt(cod_tramite));
    tt.setCod_almacen(cliente.getCod_almacen());
            
    String tipo_tramite="";
    PagedListHolder listaEnviados = null;
    PagedListHolder listaBandeja = null;
    Map modelo = new HashMap();
    String ruta="";
    
    
//    if (Integer.parseInt(cod_w)==1 || Integer.parseInt(cod_w)==5 || Integer.parseInt(cod_w)==6 || Integer.parseInt(cod_w)==7) {
//        
//        listaBandeja= new PagedListHolder(this.adqui.getTransaccionSolicitudes(trans));
//        listaBandeja.setPageSize(listaBandeja.getNrOfElements());
//        
//        listaEnviados = new PagedListHolder(this.adqui.getEnviados(trans));
//        listaEnviados.setPageSize(listaEnviados.getNrOfElements());
//        System.out.println("OOOOOOOOOO nro elementos: "+listaEnviados.getNrOfElements());
//        
//        tipo_tramite=this.adqui.getTipoTramite(tt);
//        if (Integer.parseInt(cod_tramite)==1)
//            ruta="transaccionMateriales/TransaccionBandeja";
//        else if (Integer.parseInt(cod_tramite)==4){
//            String mensaje="NOTA: NO UTILIZAR ESTA OPCION SI SE TIENE GENERADO LA SOLICITUD DE COMPRA POR SISTEMA";
//            modelo.put("mensaje",mensaje);
//            ruta="transaccionMateriales/TransaccionPedidoBandeja";
//        }
//    }
//    else if (Integer.parseInt(cod_w)==2) {
//        
//        listaBandeja= new PagedListHolder(this.adqui.getTransaccionMateriales(trans));
//        listaBandeja.setPageSize(listaBandeja.getNrOfElements());
//        
//        listaEnviados = new PagedListHolder(this.adqui.getEnviados(trans));
//        listaEnviados.setPageSize(listaEnviados.getNrOfElements());
//        System.out.println("OOOOOOOOOO nro elementos: "+listaEnviados.getNrOfElements());
//    
//        tipo_tramite=this.adqui.getTipoTramite(tt);
//        if (Integer.parseInt(cod_tramite)==1)
//            ruta="transaccionMateriales/TransaccionBandeja";
//        else if (Integer.parseInt(cod_tramite)==4){
//            String mensaje="NOTA: NO UTILIZAR ESTA OPCION SI SE TIENE GENERADO LA SOLICITUD DE COMPRA POR SISTEMA";
//            modelo.put("mensaje",mensaje);
//            ruta="transaccionMateriales/TransaccionPedidoBandeja";
//        }
//    }
//    else if (Integer.parseInt(cod_w)==3) {
//        listaBandeja = new PagedListHolder(this.adqui.getTransaccionConsultores(trans));
//        listaBandeja.setPageSize(listaBandeja.getNrOfElements());
//        
//        listaEnviados = new PagedListHolder(this.adqui.getEnviados(trans));
//        listaEnviados.setPageSize(listaEnviados.getNrOfElements());
//        System.out.println("OOOOOOOOOO nro elementos: "+listaEnviados.getNrOfElements());
//    
//        tipo_tramite=tipo_tram;
//        ruta="transaccionMateriales/TransaccionConsultoresBandeja";
//    }
//    else if (Integer.parseInt(cod_w)==4) {
//        listaBandeja = new PagedListHolder(this.adqui.getTransaccionConsultores(trans));
//        listaBandeja.setPageSize(listaBandeja.getNrOfElements()); 
//        
//        listaEnviados = new PagedListHolder(this.adqui.getEnviados(trans));
//        listaEnviados.setPageSize(listaEnviados.getNrOfElements());
//        System.out.println("OOOOOOOOOO nro elementos: "+listaEnviados.getNrOfElements());
//        
//        tipo_tramite=tipo_tram;
//        ruta="transaccionMateriales/TransaccionObrasBandeja";
//    }
    if (Integer.parseInt(cod_w)==1 || Integer.parseInt(cod_w)==5 || Integer.parseInt(cod_w)==6 || Integer.parseInt(cod_w)==7) {
        listaBandeja= new PagedListHolder(this.adqui.getTransaccionSolicitudes(trans));
        
    }
    else if(Integer.parseInt(cod_w)==2){
        listaBandeja= new PagedListHolder(this.adqui.getTransaccionMateriales(trans));
    }
    else if(Integer.parseInt(cod_w)==3){
        listaBandeja = new PagedListHolder(this.adqui.getTransaccionConsultores(trans));
    }
    else if(Integer.parseInt(cod_w)==4){
        listaBandeja = new PagedListHolder(this.adqui.getTransaccionConsultores(trans));
    }
    listaBandeja.setPageSize(listaBandeja.getNrOfElements());

    listaEnviados = new PagedListHolder(this.adqui.getEnviados(trans));
    listaEnviados.setPageSize(listaEnviados.getNrOfElements());
    System.out.println("OOOOOOOOOO nro elementos: "+listaEnviados.getNrOfElements());

    tipo_tramite=tipo_tram;
    ruta="transaccionMateriales/TransaccionBandeja";
        
    modelo.put("listaEnviados",listaEnviados);    
    modelo.put("ListBandeja",listaBandeja);
    modelo.put("tipo_tramite",tipo_tramite);
    modelo.put("cod_tramite",cod_tramite);
    modelo.put("cod_w",cod_w);
    return new ModelAndView(ruta, modelo);

  }
}