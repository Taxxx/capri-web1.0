/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.web.transaccionMateriales.reportes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;
import org.umsa.domain.Roles;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 *
 * @author Usuario
 */
public class ReporteItems1 extends HttpServlet {
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

//            String cod_trans_nro = req.getParameter("cod_trans_nro");
//            String cod_tramite = req.getParameter("cod_tramite");            
//            //String cod_estado = req.getParameter("cod_estado");         
//            String cod_estado="C";
            
            ServletContext context = this.getServletConfig().getServletContext();
            
            File reportFile = null;            
            File imagen = new File(context.getRealPath("/Reportes/umsa.jpg"));                        
//            reportFile = new File(context.getRealPath("/Reportes/PedidoMateriales.jasper"));                        
//            reportFile = new File(context.getRealPath("/Reportes/NotaConformidad.jasper"));                        
            
            Roles rol=(Roles) req.getSession().getAttribute("__sess_rol");
                        
//            List list=new ArrayList();    
            
            AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            
//            System.out.println("cod_trans_nro: "+cod_trans_nro);
//            System.out.println("cod_Estado: "+cod_estado);
//            System.out.println("cod_tramite: "+cod_tramite);
            
            
            
//            URL urlMaestro,urlImage,urlMaestro2;
//        try {
            
//            JD_Reporte1 t1 = new JD_Reporte1();
//            Map parameters = new HashMap();
            
//            if(cod_tipo_reporte==1)
//                urlMaestro = t1.getClass().getResource("/umsa/capricornio/gui/reports/ReporteItems3.jasper");
//            else
//                urlMaestro = t1.getClass().getResource("/umsa/capricornio/gui/reports/ReporteItems4.jasper");
            reportFile = new File(context.getRealPath("/Reportes/pruebita.jasper"));
            
//            urlImage=t1.getClass().getResource("/umsa/capricornio/gui/images/umsa.jpg");
//            urlMaestro1 = t1.getClass().getResource("/umsa/capricornio/gui/reports/ReporteCompra2.jasper");
//            urlMaestro2 = t1.getClass().getResource("/umsa/capricornio/gui/reports/ReporteCompra3.jasper");
//            parameters.put("imagen",imagen.getPath());
//            parameters.put("cod_item",cod_item);
////            System.out.println("el cod_usuario es :"+cod_usuario+"aja");
//            parameters.put("cod_usuario",cod_usuario);
//            parameters.put("fecha_ini",fi);
//            parameters.put("fecha_fin",ff);
//            parameters.put("partida",partida);
//            parameters.put("TIPO_SOL",ts);
//            parameters.put("FECHA_INICIO",fi);
//            parameters.put("FECHA_FINAL",ff);
//            parameters.put("DIR", urlMaestro1.toString());
//            parameters.put("DIR1", urlMaestro2.toString());
//            JasperReport reporte = (JasperReport) JRLoader.loadObject(urlMaestro); 
//            System.out.println("realizo el jasper reporte");
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion);
//            System.out.println("realizo el jasper print");
//            JasperViewer.viewReport(jasperPrint, false);  
//            System.out.println("realizo el jasper view");
            
//        } catch (Exception ec) {
//            System.out.println("Error Gravichimo: "+ec);
//        }
            
//            Date fi,ff;
//            fi=new Date("01/01/15");
//            ff=new Date("01/06/15");
//            
//            
//            parameters.put("imagen", imagen.getPath());
//            
//            parameters.put("cod_item"," ");
//            parameters.put("cod_usuario"," ");
//            parameters.put("fecha_ini",fi);
//            parameters.put("fecha_fin",ff);
//            parameters.put("partida"," ");
            
            
            parameters.put("yuhu"," Javi :P");
            
            
//            JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(list);
            Class.forName("oracle.jdbc.driver.OracleDriver");            
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@200.7.160.182:1521:ADQUI", "ADQUISICIONES", "4dqu1_c3n72al");
            
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion);
            res.setContentType("application/pdf");
            System.out.println("Hola Palusa :D");
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
        } finally {}
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
