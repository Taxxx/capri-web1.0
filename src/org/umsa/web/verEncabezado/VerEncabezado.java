package org.umsa.web.verEncabezado;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.umsa.domain.Clientes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Roles;



public class VerEncabezado implements Controller {

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
      Map modelo = new HashMap();
      
      Roles rol = new Roles();
      rol= (Roles) request.getSession().getAttribute("__sess_rol");
      Clientes clientes=new Clientes();
      clientes=(Clientes)  request.getSession().getAttribute("__sess_cliente");      
      
      if ( !(rol==null || clientes==null)){                
        modelo.put("rol", rol.getRol());
        modelo.put("cliente", clientes.getUsuario());      
      }
      return new ModelAndView("verCuerpo/VerCuerpo", modelo);
  }
}