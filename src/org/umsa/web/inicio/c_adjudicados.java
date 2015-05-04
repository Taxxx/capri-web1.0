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
 * @author alex
 */
public class c_adjudicados extends AbstractController
{
    private MiFacade adqui;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        System.out.println("entro a c_adju::::: ");
        PagedListHolder gestiones = new PagedListHolder(this.adqui.Gestiones());
        PagedListHolder partidas = new PagedListHolder(this.adqui.GetPartidastotales());
        partidas.setPageSize(partidas.getNrOfElements());
        System.out.println("--***-size partidas: "+this.adqui.GetPartidastotales().size());
        //PagedListHolder tipos_items = new PagedListHolder(this.adqui.GetTipoItems());
        Map modelo = new HashMap();
        modelo.put("gestiones", gestiones);
        //modelo.put("tipos_items", tipos_items);
        modelo.put("partidas", partidas);
        //System.out.println(modelo.);
        return new ModelAndView("inicio/adjudicados", modelo);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
