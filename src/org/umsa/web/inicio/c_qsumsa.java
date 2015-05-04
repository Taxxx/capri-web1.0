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
public class c_qsumsa extends AbstractController{
    private MiFacade adqui;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        PagedListHolder prog_apert = new PagedListHolder(this.adqui.GetApertProg());
        prog_apert.setPageSize(prog_apert.getNrOfElements());
        Map modelo = new HashMap();
        modelo.put("prog_apert", prog_apert);
        System.out.println("Tiene datos: "+prog_apert.getNrOfElements());
        return new ModelAndView("inicio/qsumsa", modelo);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
