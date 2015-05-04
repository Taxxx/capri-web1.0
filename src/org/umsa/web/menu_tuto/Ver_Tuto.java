package org.umsa.web.menu_tuto;

import org.umsa.web.verCuerpo.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
 
public class Ver_Tuto implements Controller {

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
      int tipo = Integer.parseInt(request.getParameter("tipo"));
      //System.out.println(tipo+" Oky");
      //String apodo = request.getParameter("apodo").trim();
      //String clave = request.getParameter("clave").trim();
      switch(tipo){
          case 1:
              return new ModelAndView("inicio/tuto_docu", null); //enviar "modelo" a "menu.jsp"
              
          case 2: 
              return new ModelAndView("inicio/tuto_tuto", null); //enviar "modelo" a "menu.jsp"
              
          case 3: 
              return new ModelAndView("inicio/tuto_otro", null); //enviar "modelo" a "menu.jsp"
          default:
              return null;
              
      }
      
      
  }

}