/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.generadores;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class GeneraOrdenCompra implements Controller{

    private MiFacade adqui;
 
    public void setAdqui(MiFacade adqui) {
      this.adqui = adqui;
    }
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cod_transaccion =request.getParameter("cod_transaccion");
        String cod_trans_nro =request.getParameter("cod_trans_nro");
        
        Map modelo = new HashMap();        
        PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos(Integer.parseInt(cod_transaccion)));
        listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
        modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);
        modelo.put("cod_transaccion", cod_transaccion);
        modelo.put("cod_trans_nro", cod_trans_nro);
        try {
            AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            String nombre="";
            String casa_comercial="";
            String direccion="";
            String telefono="";
            String nit="";
            Map[] e = puerto.getProponenteADJ2(Integer.parseInt(cod_transaccion));
            if(e!=null){
                nombre=e[0].get("NOMBRE").toString();
                casa_comercial=e[0].get("NOMBRE_COMERCIAL").toString();
                direccion=e[0].get("DIRECCION").toString();
                telefono=e[0].get("TELEFONO").toString();
                nit=e[0].get("COD_PROVEEDOR").toString();
            }
            modelo.put("nombre", nombre);
            modelo.put("casa_comercial", casa_comercial);
            modelo.put("direccion", direccion);
            modelo.put("telefono", telefono);
            modelo.put("nit", nit);
        } catch (Exception e) {
        }
        return new ModelAndView("nuevos/GeneraOrdenCompra", modelo);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
