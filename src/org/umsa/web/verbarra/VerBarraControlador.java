package org.umsa.web.verbarra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.umsa.domain.logic.MiFacade;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import org.umsa.domain.Roles;

public class VerBarraControlador implements Controller {
        
  private MiFacade adqui;
 
  public void setAdqui(MiFacade adqui) {
    this.adqui = adqui;
  }

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map modelo = new HashMap();    
    Roles rol = new Roles();
    rol= (Roles) request.getSession().getAttribute("__sess_rol");
    if (rol==null) modelo.put("rol", "");
    else modelo.put("rol", rol.getRol());
    return new ModelAndView("verbarra/VerBarra", modelo);
  }
}