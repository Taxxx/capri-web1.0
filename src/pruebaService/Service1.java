/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebaService;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.umsa.domain.Proveedor;
/**
 *
 * @author UMSA-JES
 */
public class Service1 implements Controller{
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String endpoint = "http://200.7.160.26/axis/CAPRICORNIO/SigmaCapriWS.jws?wsdl";
        try {
            Service services = new Service();
            Call call = (Call) services.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName("getBeneficiariosCapriSigma");
            //Proveedor aux= (Proveedor)call.invoke(new Object[]{"4277626", "C"});
            Map[] result = (Map[])call.invoke(new Object[]{"%", "%", "%", "%", "%", 0, 50});
            
            
            for (Map e : result) {
                /*System.out.println(e.size());
                System.out.println("Yeahhh --> "+e.get("ADH_DOCUMENTO"));
                System.out.println(e.values());*/
                Iterator it = e.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry x = (Map.Entry)it.next();
                    System.out.println(x.getKey() + " " + x.getValue());
                }
            }
            
            //aux.get(0);
            System.out.println("Holaaaaaaaaaaaaaaaa que tallllllllllll :D -->"+result.toString());
            
        } catch (Exception e) {
        }
        return null;
    }
    /*public static void main(String []Args){
        System.out.println("Hola Qie tal");
        Service1 aux = new Service1();
        aux.handleRequest(null, null);
    }*/
}
