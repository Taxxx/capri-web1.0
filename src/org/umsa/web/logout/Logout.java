package org.umsa.web.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Logout implements Controller {

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().removeAttribute("__sess_cliente");
    request.getSession().removeAttribute("__sess_roles");
    request.getSession().removeAttribute("__sess_apodo");
    request.getSession().removeAttribute("__sess_clave");
    request.getSession().invalidate();
    /*return new ModelAndView("Distro", "url", ".");*/
    return new ModelAndView("login/LoginSalida1");    
  }
}