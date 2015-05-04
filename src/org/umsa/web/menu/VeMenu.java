package org.umsa.web.menu;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.umsa.domain.logic.MiFacade;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import org.umsa.domain.Roles;

public class VeMenu implements Controller {
        
  private MiFacade adqui;
 
  public void setAdqui(MiFacade adqui) {
    this.adqui = adqui;
  }

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map modelo = new HashMap();    
    Roles rol = new Roles();
    rol= (Roles) request.getSession().getAttribute("__sess_rol");      
    modelo.put("id_rol", rol.getId_rol());
    return new ModelAndView("menu/MenuPrincipal", modelo);
  }
}