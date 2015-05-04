/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;
//import org.umsa.web.herramientas.i_formatterDate;

/**
 *
 * @author julian
 */
public class TransaccionConsultoresGenera implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        String user_maker = request.getParameter("user_maker").trim();
        String usuario_sol =request.getParameter("usuario_sol").trim();
        String detalle =request.getParameter("detalle").trim();
        String ue_solicitante =request.getParameter("ue_solicitante");
        String ue_destino =request.getParameter("ue_destino");
        String cuantia =request.getParameter("cod_cuantia");
        String solicitud =request.getParameter("solicitud");
                
        String tipo_tramite =request.getParameter("tipo_tramite");
        String cod_tramite =request.getParameter("cod_tramite");
        String cod_w =request.getParameter("cod_w");

        
        int gestion=(Integer) request.getSession().getAttribute("__sess_gestion");
        Clientes cliente=new Clientes();
        cliente=(Clientes) request.getSession().getAttribute("__sess_cliente");        
        cliente.setGestion(gestion);
        int nro_trans = this.adqui.getNroTransaccion(cliente);
        nro_trans++;
        
        Transaccion nro_tramite_x_gestion= new Transaccion();        
        nro_tramite_x_gestion.setGestion(gestion);
        nro_tramite_x_gestion.setCod_almacen(cliente.getCod_almacen());
        nro_tramite_x_gestion.setCod_tramite(Integer.parseInt(cod_tramite));
        int nro_tramite=this.adqui.getNroTramite(nro_tramite_x_gestion);
        nro_tramite++;

        Transaccion trans = new Transaccion();
        trans.setUser_maker(user_maker);
        trans.setGestion(gestion);
        trans.setNro_transaccion(nro_trans);
        trans.setNro(Integer.toString(nro_tramite));     
        trans.setUnidad_sol(ue_solicitante);
        trans.setUnidad_des(ue_destino);
        trans.setUsuario_sol(usuario_sol);
        trans.setDetalle(detalle);
        trans.setUsr_reg(cliente.getId_usuario());        
        trans.setCod_w(Integer.parseInt(cod_w));
        trans.setCod_almacen(cliente.getCod_almacen());
        trans.setCod_tramite(Integer.parseInt(cod_tramite));
        trans.setCod_cuantia(Integer.parseInt(cuantia));
        trans.setTipo_sol(Integer.parseInt(solicitud));
        //EN ESTE LUGAR SE DEBE INTRODUCIR EN LA TABLA APERTURAS STOCK ALMACEN
        //SI ES PARA EL STOCK DE ALMACEN
        if (!ue_destino.equals("STOCK ALMACEN"))
            trans.setIngreso_material("UE");
        else
            trans.setIngreso_material("STOCK");
        //System.out.println("Fecha creacion ::"+trans.getFecha());
        int cod_tran=this.adqui.setTransaccion(trans);
        
        trans.setCod_transaccion(cod_tran);        
        //Transaccion ped = this.adqui.getTransaccionMaterial(trans);
        
        Map modelo = new HashMap();
                      
        List item = this.adqui.getBuscaConsultoresObras(4);
        modelo.put("items",item);
        modelo.put("tipo_tramite",tipo_tramite );           
        modelo.put("cod_tramite",cod_tramite );           
        modelo.put("usuario_sol",usuario_sol );
        modelo.put("detalle",detalle );
        modelo.put("ue_solicitante",ue_solicitante );
        modelo.put("ue_destino",ue_destino );
        modelo.put("cod_transaccion",cod_tran);
        modelo.put("cod_w",cod_w);
        //modelo.put("nro_gestion",ped.getNro_gestion());

        PagedListHolder listaItemTipo = new PagedListHolder(this.adqui.getItemTipo());
        listaItemTipo.setPageSize(listaItemTipo.getNrOfElements());            
        modelo.put("listaItemTipo",listaItemTipo);
        //return new ModelAndView("transaccionMateriales/TransaccionConsultoresDetalle", modelo);
        return new ModelAndView("transaccionMateriales/Transaccion2", modelo);
    }
}
