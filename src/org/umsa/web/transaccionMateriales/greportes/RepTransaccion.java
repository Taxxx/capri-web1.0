/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.umsa.web.transaccionMateriales.greportes;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.rpc.ServiceException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.umsa.ConnectADQUI.AdquiWSServiceLocator;
import org.umsa.ConnectADQUI.AdquiWS_PortType;
//import static org.umsa.menu.FrmMenu.usuario;
import org.umsa.web.herramientas.NumerosTextuales;

/**
 *
 * @author julian
 */
public class RepTransaccion {     
      
    URL urlMaestro,urlImage;
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
    
    public void Reporte (List aux,String titulo,int cod_tramite,int cod_trans_nro)
    {
        this.usuariox = this.generaUsuario(cod_trans_nro);
        System.out.println("Generando Reporte, del tipo --> "+titulo);
        RepTransaccion t1 = new RepTransaccion(); 
        Map parameters = new HashMap();
        if (cod_tramite==1){
            
            urlMaestro = t1.getClass().getResource("/umsa/capricornio/gui/reports/SolicitudCompra.jasper");
            //System.out.println("Entro a la opción 1 y urlmaestro es: "+urlMaestro);
            /*try{ AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
                AdquiWS_PortType puerto = servicio.getAdquiWS();                  
                Map[] datos= puerto.getTotal(cod_trans_nro);
                if (datos!=null)
                    parameters.put("TxtTotal",TotalTexto(datos[0].get("TOTAL").toString()));
                else
                    System.out.println("upsi Vacio¡¡¡");
            }
            catch (RemoteException e){System.out.println(e);
            }
            catch (ServiceException e){ System.out.println(e);}*/
        }
        if (cod_tramite==2){
            //this.usuariox=usuario;
            urlMaestro = t1.getClass().getResource("/umsa/capricornio/gui/reports/OrdenCompra.jasper");
            try{ AdquiWSServiceLocator servicio = new AdquiWSServiceLocator();
                AdquiWS_PortType puerto = servicio.getAdquiWS();                  
                Map[] datos= puerto.getTotal(cod_trans_nro);
                if (datos!=null){
                    //System.out.println("............................ El total es: "+datos[0].get("TOTAL").toString());
                    //System.out.println("............................ El total redondeado es: "+this.Redondear(datos[0].get("TOTAL").toString(),2));
                    parameters.put("TxtTotal",TotalTexto(this.Redondear(datos[0].get("TOTAL").toString(),2)));
                }
                      
            }
            catch (RemoteException e){System.out.println(e);
            }
            catch (ServiceException e){ System.out.println(e);}
        }
        if (cod_tramite==3)
            urlMaestro = t1.getClass().getResource("/umsa/capricornio/gui/reports/IngresoMaterial.jasper");
        if (cod_tramite==4)
            urlMaestro = t1.getClass().getResource("/umsa/capricornio/gui/reports/PedidoMateriales.jasper");
        urlImage=t1.getClass().getResource("/umsa/capricornio/gui/images/umsa.jpg");

        // Recuperamos el fichero fuente el xml para la compilacion interna
        /*File rep = new File(urlMaestro.getFile());
        JasperDesign jd=JRXmlLoader.load(rep)); 
        JasperReport report = JasperCompileManager.compileReport(jd);  
        JasperPrint masterPrint = null;
        try { masterPrint = JasperFillManager.fillReport(report,parameters, ds); } 
        catch (JRException e) { }              
        JasperViewer.viewReport(masterPrint, false);*/
        
        JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(aux);  
                               
        JasperReport masterReport = null;
        try { masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);} 
        catch (JRException e) 
            { System.out.println("Error cargando el reporte maestro: " + e.getMessage());
              System.exit(3);
            }
        
        parameters.put("imagen",urlImage.toString());
        parameters.put("titulo",titulo);
        //parameters.put("titulo",titulo);
        parameters.put("usuario",usuariox);

        JasperPrint masterPrint = null;
        try { masterPrint = JasperFillManager.fillReport(masterReport, parameters,ds);}
        catch (JRException e) {}  
        
        JasperViewer.viewReport(masterPrint, false);  
    }
    public String Redondear(String numero, int pow)
    {
           Double num_deci = Math.pow(10, pow);
           return String.valueOf(Math.rint(Double.parseDouble(numero)*num_deci)/num_deci);
    }
    String TotalTexto(String total){       
       double m=Double.parseDouble(total);                          
                       
        long valor =(long)m;
        double var= m-valor;
        
        DecimalFormat formato_decimal = new DecimalFormat("0.00");        
        String decimal =formato_decimal.format(var);
      
        String montoLetra=NumerosTextuales.NumTextuales(valor);
        
        if ((m>=1000 && m<2000) || (m>=1000000 && m<2000000)){ montoLetra="UN "+montoLetra;}        
        if (var ==0.0) montoLetra=montoLetra+" 00/100";
        else montoLetra=montoLetra+" "+decimal.substring(2, 4)+"/100";
       return montoLetra;
   }
}