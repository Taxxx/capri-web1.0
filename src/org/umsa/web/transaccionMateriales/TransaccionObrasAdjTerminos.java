package org.umsa.web.transaccionMateriales;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;


import java.sql.Timestamp;
import java.util.Iterator;
import java.io.*;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.support.PagedListHolder;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

public class TransaccionObrasAdjTerminos implements Controller {
        
  private MiFacade adqui;
 
  public void setAdqui(MiFacade adqui) {
    this.adqui = adqui;
  } 
  
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map modelo = new HashMap();

   
    int contador = 0;
//aumentado
    Timestamp fecha =new Timestamp(System.currentTimeMillis());
    String adjunto="";
      try {
    //    System.out.println("entro a__"+request.getParameter("auxiliar"));
        DiskFileUpload fu = new DiskFileUpload();
        fu.setSizeMax(2048*8192); // 4Mb
        fu.setSizeThreshold(4096);
        //fu.setRepositoryPath("/tmp");
        fu.setRepositoryPath("c:\\tmp");
        List fileItems = fu.parseRequest(request);
        Iterator i = fileItems.iterator();
        while (i.hasNext()) {
          contador = contador + 1;
          FileItem actual = (FileItem)i.next();
          //String fileName = actual.getName();
          String fieldName = actual.getFieldName();
          String fileName =actual.getName();
	  /*if (fileName == null) {
            return new ModelAndView("reportes/Verupload", modelo);
     	  }*/
          if ("archivo".equals(fieldName)) {
	
          int aux = fileName.lastIndexOf('\\');
	  String nom = fileName.substring(aux+1,fileName.length());
          if (!"".equals(fileName)) {
            File fichero = new File(nom);
  	    adjunto = (fecha.toString()+Integer.toString(contador)).replace(' ','_');
	    adjunto = adjunto.replace(':', '_');
	    adjunto = adjunto.replace('-', '_');
	    adjunto = adjunto.replace('.', '_');
	    int auxiliar = fileName.lastIndexOf('.');
	    String extencion = fileName.substring(auxiliar+1,fileName.length());
            String adjunto_a = adjunto.toString();
            adjunto = adjunto_a+"."+extencion;
	    //fichero = new File("/opt/tomcat/webapps/capri-web/docs/"+adjunto);
            fichero = new File("c:\\opt\\tomcat\\webapps\\capri-web\\docs\\"+adjunto);
        
	    //fichero = new File("/archivos/"+adjunto);
	    //fichero = new File("c:\\archivos\\"+adjunto);
	    
            actual.write(fichero);
	    
	    Date hoy=new Date();
	    //String hora=hoy.getHours()+":"+hoy.getMinutes()+":"+hoy.getSeconds();
            //System.out.println("El archivo se adjunto satisfactoriamente");
          
	  }
        }
        }
      }
      catch(Exception e) {
      }      
      
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
        trans.setTerminos_ref(adjunto);
        this.adqui.setTransaccionTerminos(trans);
                
        PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(Integer.parseInt(cod_transaccion)));
        listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
        modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);

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
        
    //return new ModelAndView(vista, modelo);
    return new ModelAndView("transaccionMateriales/TransaccionObrasDetalle", modelo);
  }
}