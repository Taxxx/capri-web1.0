/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.transaccionMateriales.reportes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;
import org.umsa.domain.Roles;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;
//import org.umsa.web.herramientas.i_formatterDate;
//import static org.umsa.web.herramientas.i_formatterDate.i_conveterStandardToDate;

/**
 *
 * @author Henrry
 */
public class ReporteNotaConformidad2 extends HttpServlet {
//    private Date date_ux;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        } finally {
            out.close();
        }
    }

    private MiFacade adqui;
 
    public void setAdqui(MiFacade adqui) {
        this.adqui = adqui;
    } 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        System.out.println("Holo muchachos!!!");
        try {                        
            //Para enviar los parametros al reporte
            Map parameters = new HashMap();
            byte[] bytes = null;

            String cod_trans_nro = req.getParameter("cod_trans_nro");
            String cod_tramite = req.getParameter("cod_tramite");            
            //String cod_estado = req.getParameter("cod_estado");         
            String cod_estado="C";
            
            ServletContext context = this.getServletConfig().getServletContext();
            
            File reportFile = null;            
            File imagen = new File(context.getRealPath("/Reportes/umsa.jpg"));                        
//            reportFile = new File(context.getRealPath("/Reportes/PedidoMateriales.jasper"));                        
            reportFile = new File(context.getRealPath("/Reportes/NotaConformidad.jasper"));                        
            
            Roles rol=(Roles) req.getSession().getAttribute("__sess_rol");
                        
            List list=new ArrayList();    
            
            AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            
            System.out.println("cod_trans_nro: "+cod_trans_nro);
            System.out.println("cod_Estado: "+cod_estado);
            System.out.println("cod_tramite: "+cod_tramite);
            
            //Map[] datos=puerto.getReportePedidox(Integer.parseInt(cod_trans_nro),cod_estado,Integer.parseInt(cod_tramite));
            System.out.println("Hasta aca sin problemas!!!! :P :P :P :P :P :P :P --> "+Integer.parseInt(cod_trans_nro.trim()));
            Map[] datos=puerto.getReporteNotaConformidadx(Integer.parseInt(cod_trans_nro.trim()),"C",4);            
            //Map[] datos=puerto.getReportePedidox(Integer.parseInt(cod_trans_nro.trim()),"C",3);   
//            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
//            String fecha;
            int i=0;
            String cod_trans_detalle,aux = null;
            if (datos!=null){                 
                Map map = new HashMap();
                for (int f=0;f<datos.length;f++){
                    Transaccion trans = new Transaccion();
                    trans.setNro_gestion(datos[f].get("NRO_GESTION").toString());
                    
                    trans.setCiudad(datos[f].get("CIUDAD").toString());
                    trans.setLugar(datos[f].get("LUGAR").toString());
                    trans.setHora_nc(datos[f].get("HORA_NC").toString());
                    
                    System.out.println("hola");
                    
                    
                    trans.setFecha_nc(datos[f].get("FECHA_NC").toString());
                    
                    trans.setUsuario_sol(datos[f].get("USUARIO_SOL").toString());
                    //trans.setFecha_creacion(i_formatterDate.i_conveterStandardToDate(datos[f].get("FECHA_CREACION").toString()));
                    //trans.setFecha_envio(i_formatterDate.i_conveterStandardToDate(datos[f].get("FECHA_ENVIO").toString()));
                    //trans.setFecha_envio(i_formatterDate.i_conveterStandardToDate("13-04-91"));
                    //trans.setUnidad_sol(datos[f].get("UNIDAD_SOL").toString());
                    //trans.setUnidad_des(datos[f].get("UNIDAD_DES").toString());
                    //trans.setUsuario_sol(datos[f].get("USUARIO_SOL").toString());
                    trans.setDetalle(datos[f].get("DETALLE").toString());
                    trans.setUnidad_medida(datos[f].get("UNIDAD_MEDIDA").toString());
                    trans.setCantidad_pedido(Integer.parseInt(datos[f].get("CANTIDAD_PEDIDO").toString()));
                    //trans.setTipo_item(datos[f].get("TIPO_ITEM").toString());
                    trans.setArticulo(datos[f].get("ARTICULO").toString());
                    trans.setPartida(datos[f].get("PARTIDA").toString());
                    //trans.setPartida("45");
//                    System.out.println("El origen esta en: "+datos[f].get("ORIGEN").toString());
                    //trans.setUnidad_sol(datos[f].get("ORIGEN").toString());
                    //trans.setUnidad_sol("ulala");
                    //trans.setObs(datos[f].get("OBS").toString());
                    //trans.setObs("xx");
                    //trans.setCod_trans_detalle(datos[f].get("COD_TRANS_DETALLE").toString());
                    //System.out.println("________ Cod_trans_detalle: "+trans.getCod_trans_detalle());
                    cod_trans_detalle=datos[f].get("COD_TRANS_DETALLE").toString();
                    if(!cod_trans_detalle.equals(aux)){
                        i++;
                        System.out.println("El indice es wujuuu: "+i);
                        trans.setIndice(String.valueOf(i));
                    }
                    aux=cod_trans_detalle;
                    trans.setCod_trans_detalle(cod_trans_detalle);
                    
                    trans.setDetalle_solicitud(datos[f].get("DETALLE_SOLICITUD").toString());
                    //hoja_ruta,cbte,casa_comercial,direccion,telefono,nit,precio_unit
                    //trans.setHoja_ruta(datos[f].get("HOJA_RUTA").toString());
                    trans.setCbte(datos[f].get("CBTE").toString());
                    trans.setCasa_comercial(datos[f].get("CASA_COMERCIAL").toString());
                    //trans.setCasa_comercial("uta :P");
                    //trans.setDireccion(datos[f].get("DIRECCION").toString());
                    //trans.setTelefono(datos[f].get("TELEFONO").toString());
                    //trans.setNit(datos[f].get("NIT").toString());
                    //trans.setNit("000");
                    trans.setPrecio_unit(Double.parseDouble(datos[f].get("PRECIO_UNIT").toString()));
                    //trans.setSubtotal(Double.parseDouble(datos[f].get("SUBTOTAL").toString()));
                    trans.setSubtotal(0.0);
                    
                    //trans.setNro_orden_compra(Integer.parseInt(datos[f].get("NRO_ORDEN_COMPRA").toString()));
//                    if (!(datos[f].get("FEC_ORDEN_COMPRA")==null || "".equals(datos[f].get("FEC_ORDEN_COMPRA"))))
//                        trans.setFec_orden_compra(i_formatterDate.i_conveterStandardToDate(datos[f].get("FEC_ORDEN_COMPRA").toString()));
//                    trans.setFactura(datos[f].get("FACTURA").toString());
//                    if (!(datos[f].get("FECHA_FACT")==null || "".equals(datos[f].get("FECHA_FACT"))))
//                        trans.setFecha_fact(i_formatterDate.i_conveterStandardToDate(datos[f].get("FECHA_FACT").toString()));
//                    trans.setMemo(datos[f].get("MEMO").toString());
                    trans.setNro_transaccion(Integer.parseInt(datos[f].get("NRO_TRANSACCION").toString()));
                    list.add(trans);
                } 
                //RepTransaccion rep = new RepTransaccion();
                //rep.Reporte(list,"Ingreso Almacen", cod_tramite,cod_trans_nro);
            }                         
//            for (int i = 0; i < list.size(); i++) {
//                Transaccion aux = (Transaccion) list.get(i);            
//                System.out.println(aux.getNro_gestion()+" "+aux.getFecha_creacion()+" "+ aux.getFecha_envio()+" "+aux.getUnidad_sol()+" "+aux.getUnidad_des()+" "+aux.getUsuario_sol()+" "+aux.getUnidad_medida()+" "+aux.getCantidad_pedido()+" "+aux.getTipo_item()+" "+aux.getArticulo()+" "+aux.getDetalle_solicitud());
//            }              
            datos=puerto.getTramite(Integer.parseInt(cod_tramite));
            //String titulo= datos[0].get("TIPO_REPORTE").toString();
            
            parameters.put("imagen", imagen.getPath());
            parameters.put("titulo", "Nota de Conformidad");
            parameters.put("ciudad", "La Paz-Bolivia");
            parameters.put("hora", "13:45");
            parameters.put("fecha", "13/04/91");
            parameters.put("lugar", "Sala de Reuniones PEPO");
            
            JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(list); 
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, ds);
            res.setContentType("application/pdf");
            res.setContentLength(bytes.length);
            ServletOutputStream ouputStream = res.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();            
        } 
        catch (RemoteException exxx){
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JasperReports - Web Application Sample</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<span class=\"bnew\">JasperReports encountered this error1 :</span>");
            out.println(exxx.getMessage());
            out.println("<pre>---- * ---<br>");
            exxx.printStackTrace(out);
            out.println("</pre>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        catch (ServiceException e){ System.out.println(e);}   
        catch (JRException exxx) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JasperReports - Web Application Sample</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<span class=\"bnew\">JasperReports encountered this error2 :</span>");
            out.println(exxx.getMessage());
            out.println("<pre>---- * ---<br>");
            exxx.printStackTrace(out);
            out.println("</pre>");
            out.println("</body>");
            out.println("</html>");
            return;
        } catch (Exception ex) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JasperReports - Web Application Sample</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<span class=\"bnew\">JasperReports encountered this error2 :</span>");
            out.println(ex.getMessage());
            out.println("<pre>---- * ---<br>");
            ex.printStackTrace(out);
            out.println("</pre>");
            out.println("</body>");
            out.println("</html>");
            return;
        } finally {
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
    // </editor-fold>
