/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.inicio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class c_inicio {
    private MiFacade adqui;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("inicio/inicio", null);
    }
}
