/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.inicio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Operaciones;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author UMSA-JES
 */
public class controlador_main implements Controller {
    
    private MiFacade adqui;
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Map modelo = new HashMap();
        
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        //System.out.println(tipo+" Oky");
        //String apodo = request.getParameter("apodo").trim();
        //String clave = request.getParameter("clave").trim();
        switch(tipo){
            case 1:
                return new ModelAndView("inicio/inicio", null); //enviar "modelo" a "menu.jsp"
            case 2:
//                PagedListHolder cuantias = new PagedListHolder(this.adqui.GetCuantias());
//                PagedListHolder tipos = new PagedListHolder(this.adqui.GetTipos());
//                modelo.put("cuantias", cuantias);
//                modelo.put("tipos", tipos);
//                return new ModelAndView("inicio/busqueda", modelo); //enviar "modelo" a "menu.jsp"
            case 3:
//                PagedListHolder tipos_doc = new PagedListHolder(this.adqui.GetTiposDoc());
//                PagedListHolder clases_beneficiario = new PagedListHolder(this.adqui.GetClasesBeneficiario());
//                modelo.put("tipos_doc", tipos_doc);
//                modelo.put("clases_beneficiario", clases_beneficiario);
                
//                return new ModelAndView("inicio/proveedores", modelo); //enviar "modelo" a "menu.jsp"
            case 4:
                return new ModelAndView("inicio/normativa", null); //enviar "modelo" a "menu.jsp"
            case 5:
                //this.adqui.getCuantia();
                //Iterator ix=.listIterator();
//                PagedListHolder gestiones = new PagedListHolder(this.adqui.Gestiones());
//                PagedListHolder tipos_items = new PagedListHolder(this.adqui.GetTipoItems());
//                modelo.put("gestiones", gestiones);
//                modelo.put("tipos_items", tipos_items);
                //modelo.put("saludo", "hola");
//                return new ModelAndView("inicio/clasificadores", modelo); //enviar "modelo" a "menu.jsp"
            case 6:
                return new ModelAndView("inicio/informacion", null); //enviar "modelo" a "menu.jsp"
            case 7:
                return new ModelAndView("inicio/proceso", null); //enviar "modelo" a "menu.jsp"
            case 8:
                return new ModelAndView("inicio/ccumsa", null); //enviar "modelo" a "menu.jsp"
            case 9:
                return new ModelAndView("inicio/cvumsa", null); //enviar "modelo" a "menu.jsp"
            case 10:
//                PagedListHolder prog_apert = new PagedListHolder(this.adqui.GetApertProg());
//                prog_apert.setPageSize(prog_apert.getNrOfElements());
//                modelo.put("prog_apert", prog_apert);
//                System.out.println("Tiene datos: "+prog_apert.getNrOfElements());
//                return new ModelAndView("inicio/qsumsa", modelo); //enviar "modelo" a "menu.jsp"
            default:
                return null;
              
        }
      
      
    }
}
