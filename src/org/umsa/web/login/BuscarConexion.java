package org.umsa.web.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.umsa.domain.Clientes;
import org.umsa.domain.Roles;

import java.util.List;
import org.umsa.domain.logic.MiFacade;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.web.herramientas.i_formatterDate;

public class BuscarConexion implements Controller {

  private MiFacade adqui;
  public void setAdqui(MiFacade adqui) { this.adqui = adqui; }

  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map modelo = new HashMap();

    String apodo = request.getParameter("apodo").trim();
    String clave = request.getParameter("clave").trim();
//    String gestion = request.getParameter("gestion").trim();
    
    System.out.println("Apodo: "+apodo);
    System.out.println("Clave: "+clave);
              
    if (apodo.equals("")||clave.equals(""))
        return new ModelAndView("login/LoginEntrada", "mensaje", "datos incompletos");
        
    Clientes clienteSes = new Clientes();
    clienteSes.setApodo(apodo);
    clienteSes.setClave(clave);


/* NO FUNCIONA
    Map map = new HashMap();
    map.put("JDBC.Driver", "oracle.jdbc.driver.OracleDriver");
    map.put("JDBC.ConnectionURL","jdbc:oracle:thin:@192.168.187.128:1521:ADQUI");
    map.put("JDBC.Username", "ADQUISICIONES");
    map.put("JDBC.Password ", "ADQUI2010");

    SimpleDataSource nuevo = new SimpleDataSource(map);
    nuevo.getConnection("ADQUISICIONES","ADQUI2010");
    System.out.println("el jdbc es :"+nuevo.getJdbcDriver());
  */
    Clientes existeCliente=this.adqui.getCodigoUsuario(clienteSes);

    int gestion=0;
    if (existeCliente==null) {
      System.out.println("================ Nulo !!!! ======================");  
      return new ModelAndView("verCuerpo/VerCuerpo", "mensaje", "Usuario Inexistente o Caduco su ingreso");
    }            
    else { 
//           Date hoy = new Date();
//           gestion= Integer.parseInt(i_formatterDate.getStrYear(hoy));
        gestion = Integer.parseInt(request.getParameter("gestion").trim());
        Roles rol = new Roles();
        List rolCliente= this.adqui.getRolUsuario(existeCliente);
        int i=0;
        for (i = 0; i < rolCliente.size(); i++) {
            Roles aux = (Roles) rolCliente.get(i);            
            rol.setRol(aux.getRol());
            rol.setId_rol(aux.getId_rol());            
        }        
        request.getSession().setAttribute("__sess_cliente", existeCliente);
        request.getSession().setAttribute("__sess_rol",rol );
        request.getSession().setAttribute("__sess_gestion",gestion );
        System.out.println("================ FUNCIONA !!!! ======================");
        System.out.println("Oky Doky "+existeCliente.getUsuario());
        System.out.println("su codigo de apertura es: "+existeCliente.getCod_apertura());
        return new ModelAndView("login/LoginSalida1", modelo);
    }
  }  
}