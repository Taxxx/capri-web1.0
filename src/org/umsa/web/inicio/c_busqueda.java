/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.inicio;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.umsa.domain.logic.MiFacade;
import org.umsa.web.herramientas.i_formatterDate;

/**
 *
 * @author UMSA-JES
 */
public class c_busqueda extends AbstractController{
    private MiFacade adqui;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        PagedListHolder cuantias = new PagedListHolder(this.adqui.GetCuantias());
                PagedListHolder tipos = new PagedListHolder(this.adqui.GetTipos());
                Map modelo = new HashMap();
                modelo.put("cuantias", cuantias);
                modelo.put("tipos", tipos);
                
                Date hoy = new Date();
                int gestion= Integer.parseInt(i_formatterDate.getStrYear(hoy));
                modelo.put("gestion", gestion);
                return new ModelAndView("inicio/busqueda", modelo);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
