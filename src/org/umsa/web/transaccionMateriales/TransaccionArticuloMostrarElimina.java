/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author julian
 */
public class TransaccionArticuloMostrarElimina implements Controller {

    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tipo_tramite =request.getParameter("tipo_tramite");
        String usuario_sol =request.getParameter("usuario_sol");
        String detalle =request.getParameter("detalle");
        String ue_solicitante =request.getParameter("ue_solicitante");
        String ue_destino =request.getParameter("ue_destino");        
        String cod_transaccion =request.getParameter("cod_transaccion");
        String cod_trans_detalle =request.getParameter("cod_trans_detalle");        
        String cod_tramite =request.getParameter("cod_tramite");        
        String articulo =request.getParameter("articulo");
        String tipo_item =request.getParameter("tipo_item");
        String unidad_medida =request.getParameter("unidad_medida");
        String cantidad_pedido =request.getParameter("cantidad_pedido");
        String nro_gestion =request.getParameter("nro_gestion");        
        String cod_w =request.getParameter("cod_w");        
        
        Map modelo = new HashMap();

        Transaccion ped = new Transaccion();
        ped.setCod_trans_detalle(cod_trans_detalle);
        
        //Transaccion articulo = this.adqui.getTransaccionArticulo(ped);
        
        modelo.put("tipo_tramite",tipo_tramite );
        modelo.put("usuario_sol",usuario_sol );
        modelo.put("detalle",detalle );
        modelo.put("ue_solicitante",ue_solicitante );
        modelo.put("ue_destino",ue_destino );
        modelo.put("cod_transaccion",cod_transaccion);
        modelo.put("cod_trans_detalle",cod_trans_detalle);
        modelo.put("cod_tramite",cod_tramite);
        modelo.put("cod_w",cod_w);
        modelo.put("nro_gestion",nro_gestion);

        //modelo.put("articulo",articulo.getArticulo());
        //modelo.put("unidad_medida",articulo.getUnidad_medida());
        //modelo.put("cantidad_pedido",articulo.getCantidad_pedido());
        modelo.put("tipo_item",tipo_item);
        modelo.put("articulo",articulo);
        modelo.put("unidad_medida",unidad_medida);
        modelo.put("cantidad_pedido",cantidad_pedido);

        return new ModelAndView("transaccionMateriales/TransaccionItemsMuestraElimina", modelo);
    }
}
