/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.operaciones.item;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;

/**
 *
 * @author UMSA-JES
 */
public class UpdateItem implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             System.out.println("Hola");
             AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
             AdquiWS_PortType puerto = servicio.getAdquiWS();
             String cod_trans_detalle=request.getParameter("cod_trans_detalle");
             
             int cantidad_pedido=0;
             try {
                 cantidad_pedido=   Integer.parseInt(request.getParameter("cantidad"));
             } catch (Exception e) {
             }
                  
             String detalle=request.getParameter("detalle");
             if(detalle.length()==0)
                 detalle=" ";
             int cod_unidad_medida = Integer.parseInt(request.getParameter("cod_unidad_medida"));
             String partida = request.getParameter("partida");
             if(partida.length()==0)
                 partida=" ";
             System.out.println("el length de las partida es: "+partida.length());
             Double precio_unitario = 0.0;
             try {
                 precio_unitario= Double.parseDouble(request.getParameter("precio_unitario"));
             } catch (Exception e) {
                 System.out.println("Error: "+e);
             }
             
             int cod_item=0;
             try {
                 cod_item = Integer.parseInt(request.getParameter("cod_item"));
                 System.out.println("el length de las cod_item es: "+cod_item);
             } catch (Exception e) {
             }
             
             
             System.out.println(" -********- El cod_trans_detalle es   -->   "+cod_trans_detalle);
             System.out.println(" -********- El detalle es   -->   "+detalle);
             System.out.println(" -********- El cod_unidad_medida es   -->   "+cod_unidad_medida);
             System.out.println(" -********- El cantidad_pedido es   -->   "+cantidad_pedido);
             System.out.println(" -********- El partida es   -->   "+partida);
             System.out.println(" -********- El precio_unitario es   -->   "+precio_unitario);
             System.out.println(" -********- El cod_item es   -->   "+cod_item);
//             int cod_item = Integer.parseInt(request.getParameter("cod_item"));
//             System.out.println("Hay caramba "+cod_trans_detalle+" - "+cantidad_pedido+" - "+detalle+" - "+cod_unidad_medida+" - "+partida+" - "+cod_item);
             
             puerto.updateItemx2("SET-updateItem", cod_trans_detalle, detalle, cod_unidad_medida, cantidad_pedido, partida,precio_unitario,cod_item);
//             Map[] e = puerto.updateItem("SET-updateItem", cod_trans_detalle, detalle, cod_unidad_medida, cantidad_pedido);
             System.out.println("Exito exitosoooo");
                       
                        
            } catch (Exception e) {
                System.out.println("Error -> "+e);
            }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
