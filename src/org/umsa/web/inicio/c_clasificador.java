/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.inicio;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class c_clasificador extends AbstractController{
    private MiFacade adqui;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        PagedListHolder gestiones = new PagedListHolder(this.adqui.Gestiones());
        PagedListHolder tipos_items = new PagedListHolder(this.adqui.GetTipoItems());
        Map modelo = new HashMap();
        modelo.put("gestiones", gestiones);
        modelo.put("tipos_items", tipos_items);
        //modelo.put("saludo", "hola");
        return new ModelAndView("inicio/clasificadores", modelo);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
