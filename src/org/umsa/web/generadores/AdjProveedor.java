/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.generadores;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;
import org.umsa.domain.Clientes;

/**
 *
 * @author UMSA-JES
 */
public class AdjProveedor implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String adh_nombre = request.getParameter("adh_nombre");;
        String dir_lugar = request.getParameter("dir_lugar");;
        String dir_telefono = request.getParameter("dir_telefono");;
        String dir_direccion = request.getParameter("dir_direccion");;
        
        String cod_transaccion = request.getParameter("cod_transaccion");
        String cod_trans_nro = request.getParameter("cod_trans_nro");
        String documento =request.getParameter("documento");
        String tipo_id =request.getParameter("tipo_id");
        String clase_beneficiario = request.getParameter("clase_beneficiario");
        String nombre = request.getParameter("nombre");
        String nombre_comercial = request.getParameter("nombre_comercial");
       
        System.out.println("Hola que Tal.. documento: "+documento+" tipo_id: "+tipo_id);
        
        System.out.println("adh_nombre: "+adh_nombre);
        System.out.println("dir_lugar: "+dir_lugar);
        System.out.println("dir_telefono: "+dir_telefono);
        System.out.println("dir_direccion: "+dir_direccion);
        System.out.println("cod_transaccion: "+cod_transaccion);
        System.out.println("clase_beneficiario: "+clase_beneficiario);
        System.out.println("nombre: "+nombre);
        System.out.println("nombre_comercial: "+nombre_comercial);
        
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
               
        JSONObject Listado = new JSONObject();
        JSONArray datos= new JSONArray();
        
        
        Clientes cliente=new Clientes();
        cliente=(Clientes) request.getSession().getAttribute("__sess_cliente");
        try {
            AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            //Map[] e = puerto.getBeneficiariosDetalleCapriSigma(documento, tipo_id);
            //Map[] e = puerto.getBeneficiariosDetalleCapriSigma(documento, tipo_id);
            
                        
            //System.out.println(datos.toJSONString());
            JSONObject JSNProveedor;
//            if (e!=null){
                System.out.println("Entro....");
//                for (int f=0;f<e.length;f++){
                    
//                }
//                TblProveedores.setValueAt(datos[c].get("DOCUMENTO"),c,0);
//                TblProveedores.setValueAt(datos[c].get("TIPO_ID"),c,1);                    
//                TblProveedores.setValueAt(datos[c].get("CLASE_BENEFICIARIO"),c,2);
//                TblProveedores.setValueAt(datos[c].get("NOMBRE"),c,3);
//                TblProveedores.setValueAt(datos[c].get("NOMBRE_COMERCIAL"),c,4);
//                TblProveedores.setValueAt(datos[c].get("ADH_NOMBRE"),c,5);
//                TblProveedores.setValueAt(datos[c].get("ADH_DOCUMENTO"),c,6);
//                TblProveedores.setValueAt(datos[c].get("DIR_LUGAR"),c,7);
//                TblProveedores.setValueAt(datos[c].get("DIR_DIRECCION"),c,8);
//                TblProveedores.setValueAt(datos[c].get("DIR_TELEFONO"),c,9);
//                TblProveedores.setValueAt(datos[c].get("DIR_EMAIL"),c,10);
                
//                JSNProveedor = new JSONObject();
//                JSNProveedor.put("adh_nombre", e[0].get("ADH_NOMBRE").toString());
//                JSNProveedor.put("adh_documento", e[0].get("ADH_DOCUMENTO").toString());
//                JSNProveedor.put("dir_lugar", e[0].get("DIR_LUGAR").toString());
//                JSNProveedor.put("dir_direccion", e[0].get("DIR_DIRECCION").toString());
//                JSNProveedor.put("dir_telefono", e[0].get("DIR_TELEFONO").toString());
//                JSNProveedor.put("dir_email", e[0].get("DIR_EMAIL").toString());
//                datos.add(JSNProveedor);
                  Map[] e = null;
                  String cod_trans_detalle;
                  int cod_trans_nro_nuevo = 0;
                  
                  e = puerto.getNroOrdenCompra(Integer.parseInt(cod_transaccion));
                  if (e == null) {
                      //System.out.println("Sin Orden de compra - Tamaño: "+e[0].get("NRO").toString());
                      e = puerto.setCreaNroTramite("SET-upDateGeneraTramite", Integer.parseInt(cod_transaccion), cliente.getCod_almacen(), 2, 2015, Integer.parseInt(cod_trans_nro),0);
                      if (e != null) {
                         cod_trans_nro_nuevo = Integer.parseInt(e[0].get("COD_TRANS_NRO").toString());
                         System.out.println("El nuevo cod_trans_nro es: "+cod_trans_nro);
                      }
                      Map[] items=puerto.getItems2(Integer.parseInt(cod_transaccion));
                      if (items!=null){
                          System.out.println("entro!!!");
                          for (int c=0;c<items.length;c++){
                             cod_trans_detalle = items[c].get("COD_TRANS_DETALLE").toString();
                             System.out.println("El cod_trans_detalle es: "+cod_trans_detalle);
                             e = puerto.setTransaccionDetalleNro("SET-upDateTransDetNro", Integer.parseInt(cod_trans_detalle), cod_trans_nro_nuevo);
                          }
                      }
                      e=puerto.addProponenteDescargo(documento,Integer.parseInt(cod_transaccion) ,cod_trans_nro_nuevo,tipo_id,clase_beneficiario,nombre,nombre_comercial,dir_direccion,dir_telefono,adh_nombre);

                  }else{
                      System.out.println("cod_trans_nro: "+e[0].get("COD_TRANS_NRO").toString());
                      System.out.println("Nro: "+e[0].get("NRO").toString());
                      puerto.updateProponenteDescargo(Integer.parseInt(cod_transaccion), Integer.parseInt(e[0].get("COD_TRANS_NRO").toString()), documento, tipo_id, clase_beneficiario, nombre, nombre_comercial, dir_direccion, dir_telefono, adh_nombre);
//                      puerto.downProveedor(Integer.parseInt(e[0].get("COD_TRANS_NRO").toString()));
//                      e=puerto.addProponenteDescargo(documento,Integer.parseInt(cod_transaccion) ,cod_trans_nro_nuevo,tipo_id,clase_beneficiario,nombre,nombre_comercial,dir_lugar,dir_telefono,adh_nombre);
                  }
                  
                JSNProveedor = new JSONObject();
                JSNProveedor.put("nombre", nombre);
                JSNProveedor.put("casa_comercial", nombre_comercial);
//                JSNProveedor.put("dir_lugar", dir_lugar);
                JSNProveedor.put("dir_direccion", dir_direccion);
                JSNProveedor.put("dir_telefono", dir_telefono);
                JSNProveedor.put("documento", documento);
//                JSNProveedor.put("dir_email", e[f].get("DIR_EMAIL").toString());
                datos.add(JSNProveedor);
                Listado.put("DetPROVEEDOR", datos);
                System.out.println("===================== Yeah ======================");
                System.out.println(Listado.toJSONString());
                out.print(Listado);
              
//        return null;
                //System.out.println("Tiene Datos -> "+e[0].get("NOMBRE").toString());
//            }
            
        } catch (Exception e) {
            System.out.println("Error -> "+e);
        }
        
        
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}