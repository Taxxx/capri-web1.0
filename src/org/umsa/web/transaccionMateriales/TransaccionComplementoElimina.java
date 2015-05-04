/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.logic.MiFacade;


/**
 *
 * @author julian
 */
public class TransaccionComplementoElimina implements Controller {
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {                
        request.setCharacterEncoding("UTF-8");        
        String tipo_tramite =request.getParameter("tipo_tramite");
        String cod_tramite =request.getParameter("cod_tramite");
        String cod_w =request.getParameter("cod_w");
        String usuario_sol =request.getParameter("usuario_sol");
        String detalle =request.getParameter("detalle");
        String ue_solicitante =request.getParameter("ue_solicitante");
        String ue_destino =request.getParameter("ue_destino");
        String articulo =request.getParameter("articulo");
        String nro_gestion =request.getParameter("nro_gestion");
        String cod_complemento =request.getParameter("cod_complemento");
        String cod_trans_detalle =request.getParameter("cod_trans_detalle");
        String cod_transaccion =request.getParameter("cod_transaccion");
                
        this.adqui.setTransaccionEliminaComplemento(Integer.parseInt(cod_complemento));
        
        PagedListHolder listaComplemento = new PagedListHolder(this.adqui.getTransaccionComplemento(Integer.parseInt(cod_trans_detalle)));
        listaComplemento .setPageSize(listaComplemento .getNrOfElements());

        Map modelo = new HashMap();                
       
        modelo.put("tipo_tramite",tipo_tramite );
        modelo.put("cod_tramite",cod_tramite );
        modelo.put("cod_w",cod_w );
        modelo.put("usuario_sol",usuario_sol );
        modelo.put("detalle",detalle );
        modelo.put("ue_solicitante",ue_solicitante);
        modelo.put("ue_destino",ue_destino );
        modelo.put("cod_transaccion",cod_transaccion);
        modelo.put("nro_gestion",nro_gestion);

        modelo.put("articulo",articulo);
        modelo.put("cod_trans_detalle",cod_trans_detalle);
        modelo.put("listaComplemento",listaComplemento);
        
        return new ModelAndView("transaccionMateriales/TransaccionComplemento", modelo);
    }
}
