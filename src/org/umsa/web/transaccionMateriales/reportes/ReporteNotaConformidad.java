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
import java.util.ArrayList;
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
import org.umsa.web.herramientas.i_formatterDate;

/**
 *
 * @author UMSA-JES
 */
public class ReporteNotaConformidad extends HttpServlet {

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
            System.out.println("Hasta aca sin problemas!!!! :P :P :P :P :P :P :P");
            Map[] datos = puerto.getReporteNotaConformidad(1, "", 0);
            if (datos!=null){                       
                Map map = new HashMap();
                int i=0;
                String cod_trans_detalle,aux = null;
                for (int f=0;f<datos.length;f++){
                    Transaccion trans = new Transaccion();
                    
                    //trans.setNro_gestion(datos[f].get("NRO_GESTION").toString());
                    trans.setCod_trans_detalle(datos[f].get("COD_TRANS_DETALLE").toString());
                    trans.setNro(datos[f].get("NRO").toString());
                    trans.setCod_tramite(Integer.parseInt(datos[f].get("COD_TRAMITE").toString()));
                    trans.setDetalle(datos[f].get("DETALLE").toString());
                    trans.setNombre("ulala");
                    trans.setNombre_comercial(datos[f].get("NOMBRE_COMERCIAL").toString());
                    trans.setDetalle_solicitud(datos[f].get("DETALLE_SOLICITUD").toString());
                    trans.setCod_trans_nro(datos[f].get("COD_TRANS_NRO").toString());
                    
                    
                    list.add(trans);
                }                                                                                            
            }                         
//            for (int i = 0; i < list.size(); i++) {
//                Transaccion aux = (Transaccion) list.get(i);            
//                System.out.println(aux.getNro_gestion()+" "+aux.getFecha_creacion()+" "+ aux.getFecha_envio()+" "+aux.getUnidad_sol()+" "+aux.getUnidad_des()+" "+aux.getUsuario_sol()+" "+aux.getUnidad_medida()+" "+aux.getCantidad_pedido()+" "+aux.getTipo_item()+" "+aux.getArticulo()+" "+aux.getDetalle_solicitud());
//            }              
            datos=puerto.getTramite(Integer.parseInt(cod_tramite));
            String titulo= datos[0].get("TIPO_REPORTE").toString();
            
            parameters.put("logo_umsa", imagen.getPath());
            parameters.put("titulo", titulo);
            
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