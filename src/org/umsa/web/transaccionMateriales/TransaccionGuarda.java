/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umsa.web.transaccionMateriales;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.Operaciones;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;
//import org.umsa.web.herramientas.i_formatterDate;

/**
 *
 * @author henrry
 */
public class TransaccionGuarda implements Controller {

    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String user_maker = request.getParameter("user_maker").trim();
        String usuario_sol = request.getParameter("usuario_sol").trim();
        String detalle = request.getParameter("detalle").trim();
        String ue_solicitante = request.getParameter("ue_solicitante");
        String ue_destino = request.getParameter("ue_destino");
        String cod_cuantia = request.getParameter("cod_cuantia");
        String solicitud = request.getParameter("solicitud");

        String cod_tramite = request.getParameter("cod_tramite");
        String tipo_tramite = request.getParameter("tipo_tramite");
        String cod_w = request.getParameter("cod_w");

        System.out.println("************ :D El cod_w es --> " + cod_w);
        String nro_gestion = request.getParameter("nro_gestion");

        String nat = request.getParameter("nat");
        System.out.println("la naturaleza de la solicitud es : " + nat);

        int gestion = (Integer) request.getSession().getAttribute("__sess_gestion");
        Clientes cliente = new Clientes();
        cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        cliente.setGestion(gestion);
        int nro_trans = this.adqui.getNroTransaccion(cliente);
        nro_trans++;
        //System.out.println("El numero de transaccion es : "+nro_trans);
        Transaccion nro_tramite_x_gestion = new Transaccion();
        nro_tramite_x_gestion.setCod_tramite(Integer.parseInt(cod_tramite));
        nro_tramite_x_gestion.setGestion(gestion);
        nro_tramite_x_gestion.setCod_almacen(cliente.getCod_almacen());
     

        Transaccion trans = new Transaccion();
        trans.setGestion(gestion);
        trans.setNro_transaccion(nro_trans);
        //trans.setNro(Integer.toString(nro_tramite));
        //trans.setFecha(i_formatterDate.i_toStringStandard(hoy));
        trans.setUser_maker(user_maker);
        trans.setUnidad_sol(ue_solicitante);
        trans.setUnidad_des(ue_destino);
        trans.setUsuario_sol(usuario_sol);
        trans.setDetalle(detalle);
        trans.setUsr_reg(cliente.getId_usuario());
        trans.setCod_tramite(Integer.parseInt(cod_tramite));
        if(cod_w==null){
            System.out.println("El cod_w es: "+cod_w);
            cod_w="2";
            trans.setCod_w(Integer.parseInt(cod_w));
            trans.setCod_almacen(cliente.getCod_almacen());
            
        }else{
            trans.setCod_w(Integer.parseInt(cod_w));
            trans.setCod_almacen(cliente.getCod_almacen());
        }
        
        
        if (Integer.parseInt(cod_w) != 2) {
            trans.setCod_cuantia(Integer.parseInt(cod_cuantia));
            trans.setTipo_sol(Integer.parseInt(solicitud));
        } else {
            trans.setCod_cuantia(7);
        }

        //EN ESTE LUGAR SE DEBE INTRODUCIR EN LA TABLA APERTURAS STOCK ALMACEN
        //SI ES PARA EL STOCK DE ALMACEN
        if (!ue_destino.equals("STOCK ALMACEN")) {
            trans.setIngreso_material("UE");
        } else {
            trans.setIngreso_material("STOCK");
        }
        //System.out.println("Fecha creacion ::"+trans.getFecha());
        System.out.println("El cod_w es: "+cod_w);
        int cod_transaccion = this.adqui.setTransaccion(trans);
        System.out.println("El cod_transaccion es: "+cod_transaccion);
        trans.setCod_transaccion(cod_transaccion);
        trans.setCod_tramite(Integer.parseInt(cod_tramite));

        trans.setCod_usuario(cliente.getId_usuario());
        String apertura_padre = this.adqui.getAperturaPadre(trans);
        System.out.println("Apertura padre1: "+apertura_padre);
        apertura_padre = apertura_padre.substring(5, apertura_padre.length());
        //Transaccion ped = this.adqui.getTransaccionMaterial(trans);
        System.out.println("Apertura padre2: "+apertura_padre);

        Map modelo = new HashMap();

        modelo.put("tipo_tramite", tipo_tramite);
        modelo.put("cod_tramite", cod_tramite);
        modelo.put("usuario_sol", usuario_sol);
        modelo.put("detalle", detalle);
        modelo.put("ue_solicitante", ue_solicitante);
        modelo.put("ue_padre", apertura_padre);
        modelo.put("ue_destino", ue_destino);
        modelo.put("cod_transaccion", cod_transaccion);
        modelo.put("cod_w", cod_w);
        modelo.put("nat", nat);

//        modelo.put("cuantia",getCuantia(cod_cuant));
        modelo.put("nro_gestion", gestion);
        //modelo.put("nro_gestion",ped.getNro_gestion());
        //System.out.println("");
        PagedListHolder listaItemTipo = new PagedListHolder(this.adqui.getItemTipo());
        listaItemTipo.setPageSize(listaItemTipo.getNrOfElements());
        modelo.put("listaItemTipo", listaItemTipo);

        PagedListHolder listaTipoAdj = new PagedListHolder(this.adqui.GetTiposADJ());
        listaTipoAdj.setPageSize(listaTipoAdj.getNrOfElements());
        modelo.put("listaTipoAdj", listaTipoAdj);
        //return new ModelAndView("transaccionMateriales/TransaccionItems", modelo);
        Operaciones o = new Operaciones();
//        o.setGestion("2015");
        o.setCod_transaccion(String.valueOf(cod_transaccion));
//        PagedListHolder lista_partidas = new PagedListHolder(this.adqui.GetPartidas(o));
//        lista_partidas.setPageSize(lista_partidas.getNrOfElements());
//        modelo.put("lista_partidas", lista_partidas);

        PagedListHolder listaAdjuntos = new PagedListHolder(this.adqui.ListaAdjuntos(o));
        listaAdjuntos.setPageSize(listaAdjuntos.getNrOfElements());
        modelo.put("listaAdjuntos", listaAdjuntos);
        adiciona_item(cod_w,cod_transaccion,gestion);
        PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos2(cod_transaccion));
        listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
       //listaTransaccionArticulos.getNrOfElements()
        modelo.put("listaTransaccionArticulos",listaTransaccionArticulos); 
        
        PagedListHolder ListaUM = new PagedListHolder(this.adqui.getUnidadesMedida());
        ListaUM.setPageSize(ListaUM.getNrOfElements());
        modelo.put("ListaUM",ListaUM);
        
        return new ModelAndView("transaccionMateriales/Transaccion2", modelo);
    }

    private void adiciona_item(String cod_w, int cod_transaccion, int gestion) {
        if ((Integer.parseInt(cod_w) == 3)) {
            System.out.println("Hola Camaron!!!, tu cod_w es: " + cod_w);
            Transaccion aux = new Transaccion();
            aux.setCod_transaccion(cod_transaccion);
            aux.setUnidad_medida("6");
            aux.setCantidad("1");
            aux.setArticulo("CONSULTORIA");
            aux.setGestion(gestion);
            this.adqui.addTransDetalle(aux);
        }
        if ((Integer.parseInt(cod_w) == 4)) {
            System.out.println("Hola Camaron!!!, tu cod_w es: " + cod_w);
            Transaccion aux = new Transaccion();
            aux.setCod_transaccion(cod_transaccion);
            aux.setUnidad_medida("12");
            aux.setCantidad("1");
            aux.setArticulo("OBRA");
            aux.setGestion(gestion);
            this.adqui.addTransDetalle(aux);
        }
    }

    public String getCuantia(int cod_cuantia) {
        String cuantia = "";
        if (cod_cuantia == 1) {
            cuantia = "COMPRA MENOR";
        }
        if (cod_cuantia == 2) {
            cuantia = "ANPE";
        }
        if (cod_cuantia == 3) {
            cuantia = "LICITACION PUBLICA";
        }
        if (cod_cuantia == 4) {
            cuantia = "CONTRATACION POR EXCEPCION";
        }
        if (cod_cuantia == 5) {
            cuantia = "CONTRATACION POR EMERGENCIA";
        }
        if (cod_cuantia == 6) {
            cuantia = "CONTRATACION DIRECTA DE BIENES";
        }

        return cuantia;
    }
}
