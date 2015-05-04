package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

public class TransaccionSolicitudAvanza implements Controller {
        
  private MiFacade adqui;
  private String msj_adjuntos;
  public void setAdqui(MiFacade adqui) {
    this.adqui = adqui;
  } 

  public boolean RestriccionAdjuntos(Transaccion t){
      List aux=this.adqui.RestriccionAdjuntos(t);
      Transaccion aux2;
      System.out.println("El tamanio es: "+aux.size());
      if(!aux.isEmpty()){
          Iterator i=aux.listIterator();
          //System.out.println(this.adqui.RestriccionAdjuntos(t));
          this.msj_adjuntos = "No puede avanzar la transaccion sin adjuntar los siguientes archivos: ";
          while( i.hasNext() ) {
              aux2=(Transaccion) i.next();
              System.out.println("--- cod_adjunto: "+aux2.getCod_adjunto());
              System.out.println("--- descripcion: "+aux2.getDescripcion());
              this.msj_adjuntos+=", "+aux2.getDescripcion();
          }
          return false;
      }
      else
          return true;
  }
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
//    Recuperamos las variables

    int gestion=(Integer) request.getSession().getAttribute("__sess_gestion");
    Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
    Roles rol = (Roles) request.getSession().getAttribute("__sess_rol");

    String cod_tramite =request.getParameter("cod_tramite");
    String cod_estado =request.getParameter("cod_estado");
    String cod_transaccion =request.getParameter("cod_transaccion");
    String cod_w =request.getParameter("cod_w");
    String tipo_tramite =request.getParameter("tipo_tramite");
    String cuce = request.getParameter("cuce");
    System.out.println("El cuce: "+cuce);
    
//     int nro_trans = this.adqui.getNroTransaccion(cliente);
//     nro_trans++;
    
    
    Map modelo = new HashMap();
//    if((Integer.parseInt(cod_w)==3)){
//        System.out.println("Hola Camaron!!!, tu cod_w es: "+cod_w); 
//        Transaccion aux= new Transaccion(); 
//        aux.setCod_transaccion(Integer.parseInt(cod_transaccion));
//        aux.setUnidad_medida("6");
//        aux.setCantidad("1");
//        aux.setArticulo("SERVICIO");
//        aux.setGestion(gestion);
//        cod_estado="B";
//        this.adqui.addTransDetalle(aux);
//    }
//    if((Integer.parseInt(cod_w)==4)){
//        System.out.println("Hola Camaron!!!, tu cod_w es: "+cod_w); 
//        Transaccion aux= new Transaccion(); 
//        aux.setCod_transaccion(Integer.parseInt(cod_transaccion));
//        aux.setUnidad_medida("12");
//        aux.setCantidad("1");
//        aux.setArticulo("OBRA");
//        aux.setGestion(gestion);
//        cod_estado="B";
//        this.adqui.addTransDetalle(aux);
//    }
    int nro_items=this.adqui.getTransaccionNroItems(Integer.parseInt(cod_transaccion));
    //if (nro_items>0){
//    System.out.println(":P Llego ----- 0 "+nro_items );
    
    System.out.println("Wujuuuu: cod_w: "+Integer.parseInt(cod_w)+", y nro_items es: "+nro_items);
    if (nro_items>0){
        
        
        Transaccion trans_avanza= new Transaccion();  
        trans_avanza.setCod_usuario(cliente.getId_usuario());
        trans_avanza.setCod_w(Integer.parseInt(cod_w));     
        trans_avanza.setOrigen(cod_estado);    
        System.out.println("Estado: "+cod_estado);
        trans_avanza.setCod_transaccion(Integer.parseInt(cod_transaccion));
        trans_avanza.setCod_tramite(Integer.parseInt(cod_tramite));
        trans_avanza.setGestion(gestion);
        
        if(this.RestriccionAdjuntos(trans_avanza)){
            
                System.out.println("Llego ----- 0");

                
                if(cuce.equals("Sin Generar"))
                {
                    this.adqui.generaCUCE(cod_transaccion);
                    int cod_almacen = this.adqui.getCodAlmacen(Integer.parseInt(cod_transaccion.trim()));
                    AsignaNro(Integer.parseInt(cod_transaccion), Integer.parseInt(cod_tramite), gestion, cod_almacen);

                }
                this.adqui.setTransaccionAvanza(trans_avanza);
                
//            
            
        }
        else
            modelo.put("mensaje",this.msj_adjuntos);
     
    }
    else {
       
        modelo.put("mensaje","NO SE PUEDE AVANZAR LA TRANSACCION QUE ELIJIO PORQUE NO CONTIENE ITEMS EN EL FORMULARIO");
    }

    String ruta="";
    
    
    Transaccion trans= new Transaccion();
    trans.setCod_usuario(cliente.getId_usuario());
    trans.setCod_tramite(Integer.parseInt(cod_tramite));             
    trans.setGestion(gestion);    
    trans.setCod_w(Integer.parseInt(cod_w));
    System.out.println("Aquí se ve cod_w --> "+cod_w);
    PagedListHolder listaBandeja = null;
    PagedListHolder listaEnviados = null;
////    switch (Integer.parseInt(cod_w)){
////        case 2:
            listaBandeja =new PagedListHolder(this.adqui.getTransaccionMateriales(trans));
            listaBandeja.setPageSize(listaBandeja.getNrOfElements());
            
            listaEnviados = new PagedListHolder(this.adqui.getEnviados2(trans));
            listaEnviados.setPageSize(listaEnviados.getNrOfElements());
            

    
    
    modelo.put("tipo_tramite",tipo_tramite);
    ruta="transaccionMateriales/TransaccionBandeja";
    modelo.put("listaEnviados",listaEnviados);
    modelo.put("ListBandeja",listaBandeja);
    modelo.put("cod_tramite",cod_tramite);
    modelo.put("cod_w",cod_w);
    return new ModelAndView(ruta, modelo);

  }
  public int getMaxNroTram(int cod_tramite, int cod_almacen, int gestion){
      Transaccion trans_nro= new Transaccion(); 
      trans_nro.setCod_tramite(cod_tramite);
      trans_nro.setCod_almacen(cod_almacen);
      trans_nro.setGestion(gestion);
      return Integer.parseInt(this.adqui.getMaxNroTramite(trans_nro));
  }
  public void AsignaNro(int cod_transaccion, int cod_tramite, int gestion, int cod_almacen){
//      this.setTramiteNro(cod_transaccion, cod_tramite, this.getNroTramite(cod_tramite, gestion, cod_almacen)+1);
      System.out.println("-***-***-***- El codigo de transaccion es: "+cod_transaccion+", cod_tramite: "+cod_tramite+", gestion: "+gestion+", cod_almacen: "+cod_almacen);
//      System.out.println("El numero es: "+this.getNroTramite(cod_tramite, gestion, cod_almacen)+1);
       this.nroSolicitud(cod_transaccion, this.getNroTramite(cod_tramite, gestion, cod_almacen)+1);
  }
  public int getNroTramite(int cod_tramite, int gestion, int cod_almacen){
      Transaccion transaccion= new Transaccion(); 
      transaccion.setCod_tramite(cod_tramite);
      transaccion.setCod_almacen(cod_almacen);
      transaccion.setGestion(gestion);
      return this.adqui.getNroTramite(transaccion);
  }
  public void nroSolicitud(int cod_transaccion, int nro){
      Transaccion transaccion= new Transaccion(); 
      transaccion.setCod_transaccion(cod_transaccion);
      transaccion.setNro(String.valueOf(nro));
      this.adqui.nroSolicitud(transaccion);
  }
  public void setTramiteNro( int cod_transaccion, int cod_tramite, String nro){
      Transaccion transaccion= new Transaccion(); 
      transaccion.setCod_transaccion(cod_transaccion);
      transaccion.setCod_tramite(cod_tramite);
      transaccion.setNro(nro);
//      return this.adqui.getNroTramite(trans_nro);
  }
}