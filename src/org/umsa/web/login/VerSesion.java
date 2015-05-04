package org.umsa.web.login;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.umsa.domain.Clientes;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Roles;
import org.umsa.domain.logic.MiFacade;

public class VerSesion implements Controller {

   private MiFacade mi;
 
  public void setAdqui(MiFacade mi) {
    this.mi = mi;
  }
    
    
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
       String id_rol = request.getParameter("id_rol"); 
    if (null == (Clientes) request.getSession().getAttribute("__sess_cliente")) {
      // Primera conexiï¿½n con el sistema
      return new ModelAndView("login/LoginEntrada", null);
    }
    // Mostramos el menï¿½ correspondiente
    
    
      Map modelo = new HashMap();
      Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");    
      Roles roles =new Roles();
      roles.setId_rol(Integer.parseInt(id_rol));

                   
        /*PagedListHolder listaRoles = new PagedListHolder(this.mi.getEnlacesUsuario(roles));
        listaRoles.setPageSize(listaRoles.getNrOfElements());
        modelo.put("lRoles", listaRoles);*/
	/*modelo.put("ue", ue);
         * 
	modelo.put("pg", pg);
	modelo.put("spg", spg);
	modelo.put("py", py);
	modelo.put("act", act);*/
	//modelo.put("ue", ue);

    return new ModelAndView("index/AdicionaDoc", modelo);   
    //return new ModelAndView("Distro", "url", "/menu.umsa");
  }
}