package org.umsa.web.transaccionMateriales.reportes;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
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

public class ReporteSolicitud extends HttpServlet {

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
    private String usuariox;
    public String generaUsuario(int cod_transaccion){
        try {
            AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
            AdquiWS_PortType puerto = servicio.getAdquiWS();
            return puerto.getNombreUsuario1(String.valueOf(cod_transaccion));
            //System.out.println("El codigo de usuario es: "+cod_usuario);
        } catch (Exception e) {
            System.out.println("El error es: "+e);
            return null;
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            
            //Para enviar los parametros al reporte
            Map parameters = new HashMap();
            byte[] bytes = null;

            String cod_transaccion = req.getParameter("cod_transaccion");
            String cod_estado = req.getParameter("cod_estado");
            String titulo = req.getParameter("tipo_tramite");
            ServletContext context = this.getServletConfig().getServletContext();
            File reportFile = null;
            System.out.println("Esto es --> "+context.getRealPath("/Reportes/umsa.jpg"));
            File imagen = new File(context.getRealPath("/Reportes/umsa.jpg"));
            //File firma1 = new File(context.getRealPath("/Reportes/liliana.jpg"));
            //File firma2 = new File(context.getRealPath("/Reportes/firma_MonicaDiaz.jpg"));
            
            this.usuariox = this.generaUsuario(Integer.parseInt(cod_transaccion));
            parameters.put("imagen", imagen.getPath());
            //parameters.put("firma1", firma1.getPath());
            //parameters.put("firma2", firma2.getPath());
            parameters.put("titulo", titulo);
            parameters.put("usuario",usuariox);
            reportFile = new File(context.getRealPath("/Reportes/SolicitudCompra.jasper"));                        
            
            Roles rol=(Roles) req.getSession().getAttribute("__sess_rol");
                        
            List list=new ArrayList();    
            try{
                AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
                AdquiWS_PortType puerto = servicio.getAdquiWS();
                System.out.println("cod_trans: "+Integer.parseInt(cod_transaccion)+" estado: "+cod_estado+" cod_Tramite cod: "+1);
                Map[] datos=puerto.getReporteSolicitudx(Integer.parseInt(cod_transaccion),cod_estado,1);
                //Map[] datos=puerto.getReporteSolicitud(110,"PPTO",1); 
                //Map[] datos=puerto.getReporteSolicitud2(cod_transaccion);
                //System.out.println("es_____---> "+datos.length);
                if(datos==null)
                    System.out.println("Es NULL");
                if (datos!=null){                 
                    Map map = new HashMap();
                    int i=0;
                    String cod_trans_detalle,aux = null;
                    for (int f=0;f<datos.length;f++){
                        Transaccion trans = new Transaccion();
                        trans.setNro_gestion(datos[f].get("NRO_GESTION").toString());
                        trans.setFecha_creacion(i_formatterDate.i_conveterStandardToDate(datos[f].get("FECHA_CREACION").toString()));
                        trans.setFecha_envio(i_formatterDate.i_conveterStandardToDate(datos[f].get("FECHA_ENVIO").toString()));
                        trans.setUnidad_sol(datos[f].get("UNIDAD_SOL").toString());
                        trans.setUnidad_des(datos[f].get("UNIDAD_DES").toString());
                        trans.setUsuario_sol(datos[f].get("USUARIO_SOL").toString());
                        trans.setDetalle(datos[f].get("DETALLE").toString());
                        trans.setUnidad_medida(datos[f].get("UNIDAD_MEDIDA").toString());
                        trans.setUser_maker(datos[f].get("USER_MAKER").toString());
                        //trans.setCantidad_pedido(datos[f].get("CANTIDAD_PEDIDO").toString());
                        trans.setCantidad_pedido(Integer.parseInt(datos[f].get("CANTIDAD_PEDIDO").toString()));
                        //trans.setTipo_item(datos[f].get("TIPO_ITEM").toString());
                        //System.out.println("Articulo :D :D :D --> "+datos[f].get("ARTICULO").toString());
                        trans.setArticulo(datos[f].get("ARTICULO").toString());
                        trans.setDetalle_solicitud(datos[f].get("DETALLE_SOLICITUD").toString());                    
                        trans.setNro_transaccion(Integer.parseInt(datos[f].get("NRO_TRANSACCION").toString()));
                        
                        cod_trans_detalle=datos[f].get("COD_TRANS_DETALLE").toString();
                        if(!cod_trans_detalle.equals(aux)){
                            i++;
                            System.out.println("El indice es wujuuu: "+i);
                            trans.setIndice(String.valueOf(i));
                        }
                        aux=cod_trans_detalle;
                        trans.setCod_trans_detalle(cod_trans_detalle);
                        //System.out.println("i: "+f+" cod_trans_detalle: "+datos[f].get("COD_TRANS_DETALLE").toString());
                        list.add(trans);
                    }                                                                                            
                }                         
                for (int i = 0; i < list.size(); i++) {
                    Transaccion aux = (Transaccion) list.get(i);            
                    //System.out.println(aux.getNro_gestion()+" "+aux.getFecha_creacion()+" "+ aux.getFecha_envio()+" "+aux.getUnidad_sol()+" "+aux.getUnidad_des()+" "+aux.getUsuario_sol()+" "+aux.getUnidad_medida()+" "+aux.getCantidad_pedido()+" "+aux.getTipo_item()+" "+aux.getArticulo()+" "+aux.getDetalle_solicitud());
                    //System.out.println(i+" -- Es umpa lumpa: "+aux.getArticulo());
                }       
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
         
            JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(list);
            System.out.println("Ya pues chavo :"+reportFile.getPath());
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, ds);
            res.setContentType("application/pdf");
            res.setContentLength(bytes.length);
            ServletOutputStream ouputStream = res.getOutputStream();
            System.out.println("Brenda :P :P ");
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();            
        } catch (JRException exxx) {
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