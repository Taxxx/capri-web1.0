package org.umsa.web.transaccionMateriales;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.umsa.domain.Clientes;
import org.umsa.domain.Operaciones;
import org.umsa.domain.Transaccion;
import org.umsa.domain.logic.MiFacade;

/**
 * A Java servlet that handles file upload from client.
 * 
 * @author TAXxX
 */
public class FileUploadServlet implements Controller  {
    private static final long serialVersionUID = 1L;
	
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "prueba";

    // upload settings
    private static final int MEMORY_THRESHOLD 	= 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE 	= 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE	= 1024 * 1024 * 50; // 50MB
    String adjunto;
    private MiFacade adqui;

    public void setAdqui(MiFacade adqui) {
      this.adqui = adqui;
    }
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        
        request.getRequestURL();
        System.out.println("========================== Tipo transaccion: "+request.getParameter("tipo_transaccion")+" ------ "+request.getRequestURL());
        
        Map modelo = new HashMap();
        
        System.out.print("aqui va :"+request.getParameter("cod_tramite")+" y: "+request.getParameter("cod_tramite"));

        
        // checks if the request actually contains upload file
        /*if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            //return new ModelAndView("transaccionMateriales/TransaccionConsultoresDetalle", modelo);
        }*/
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk 
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        // IMPORTANTE :D
        String uploadPath = request.getSession().getServletContext().getRealPath("/../")
                        + File.separator + UPLOAD_DIRECTORY;
        System.out.println("--------------------"+uploadPath+"--------------------");
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
                uploadDir.mkdir();
        }
        /**************/
        
        String tipo_transaccion=request.getParameter("tipo_transaccion");
        
        String tipo_tramite = request.getParameter("tipo_tramite");
        String cod_tramite = request.getParameter("cod_tramite");
        String cod_w =request.getParameter("cod_w");
        String cod_transaccion =request.getParameter("cod_transaccion");
        
        String descripcion_doc=request.getParameter("descripcion_doc");
        
        String tipo_doc = request.getParameter("tipo_doc");
        
        
        Operaciones o = new Operaciones();
        o.setGestion("2015");
        o.setCod_transaccion(cod_transaccion);
        
        
        System.out.println("Datos --> "+tipo_tramite);
        System.out.println("Datos --> "+cod_tramite);
        System.out.println("Datos --> "+cod_w);
        System.out.println("Datos --> "+cod_transaccion);
        System.out.println("Datos --> "+descripcion_doc);
        System.out.println("Tipo Doc  --> "+tipo_doc);
        
        Clientes cliente=new Clientes();
        cliente=(Clientes) request.getSession().getAttribute("__sess_cliente");

        Transaccion trans = new Transaccion();        
        trans.setCod_transaccion(Integer.parseInt(cod_transaccion));
        trans.setCod_tramite(Integer.parseInt(cod_tramite));
        trans.setCod_almacen(cliente.getCod_almacen());        
        Transaccion items = this.adqui.getTransaccionMaterial(trans);  
      

            modelo.put("tipo_tramite",tipo_tramite );           
            modelo.put("cod_transaccion",cod_transaccion);
            modelo.put("cod_w",cod_w);
            modelo.put("cod_tramite",cod_tramite);

            modelo.put("usuario_sol",items.getUsuario_sol() );
            modelo.put("detalle",items.getDetalle());
            modelo.put("ue_solicitante",items.getUnidad_sol() );
            modelo.put("ue_destino",items.getUnidad_des() );
            modelo.put("cod_transaccion",items.getCod_transaccion());
            modelo.put("nro_gestion",items.getNro_gestion());
            
            PagedListHolder listaTipoAdj = new PagedListHolder(this.adqui.GetTiposADJ());
            listaTipoAdj.setPageSize(listaTipoAdj.getNrOfElements());            
            modelo.put("listaTipoAdj",listaTipoAdj);
        /*************/
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        
                        
                        int auxiliar = fileName.lastIndexOf('.');
                        String extencion = fileName.substring(auxiliar+1,fileName.length());
                        System.out.println("La extencion del archivo es: "+extencion);
                        
                        
                        System.out.println("Seguimiento 1");
                        //System.out.println("El nombre seria: "+fileName);
                        //System.out.println("Probandoooooooooooooo --> "+this.adqui.getMaxCodTram());
                        try {
                            fileName=this.adqui.getCodUmsa()+"_"+items.getCod_transaccion()+"_"+(this.adqui.getMaxCodTram()+1)+"."+extencion;
                        } catch (Exception e) {
                            fileName=this.adqui.getCodUmsa()+"_"+items.getCod_transaccion()+"_1."+extencion;
                        }
                        
                        //fileName="prueba."+extencion;
                        //System.out.println("-------------- Cod_umsa: "+this.adqui.getCodUmsa());
                        //System.out.println("-------------- getCod_transaccion: "+items.getCod_transaccion());
                        //System.out.println("-------------- getMaxCodTram: "+this.adqui.getMaxCodTram()+1);
                        //fileName=this.adqui.getCodUmsa()+"_"+items.getCod_transaccion()+"."+extencion;*/
                        System.out.println("Seguimiento 2");
                        this.adjunto=fileName;
                        //System.out.println("Nuevo Codigo --> "+this.adqui.getCodUmsa()+"_"+items.getCod_transaccion()+"_"+(this.adqui.getMaxCodTram()+1)+"."+extencion);
                        System.out.print("=====================NOMBRE================="+this.adjunto);
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message","Upload has been done successfully!");
                        System.out.println("Listo :D");
                    }
                }
            }
            } catch (Exception ex) {
                request.setAttribute("message","There was an error: " + ex.getMessage());
                System.out.print("==================================ERROR==============================");
                           
                

               
            }
            
                   
           
            trans.setTerminos_ref(adjunto);
            String ubi="/opt/tomcat/webapps/prueba";
            trans.setUbicacion(ubi);
            trans.setDescripcion(descripcion_doc);
            trans.setEstado("V");
            System.out.println("Es :D:D--> "+Integer.parseInt(tipo_doc));
            trans.setCod_adjunto(Integer.parseInt(tipo_doc));
            this.adqui.setTransaccionTerminos(trans);
                

        
                PagedListHolder listaAdjuntos = new PagedListHolder(this.adqui.ListaAdjuntos(o));
                listaAdjuntos.setPageSize(listaAdjuntos.getNrOfElements());            
                modelo.put("listaAdjuntos",listaAdjuntos);

                PagedListHolder lista_partidas = new PagedListHolder(this.adqui.GetPartidas(o));
                lista_partidas.setPageSize(lista_partidas.getNrOfElements());            
                modelo.put("lista_partidas",lista_partidas);

                PagedListHolder listaTransaccionArticulos = new PagedListHolder(this.adqui.getTransaccionArticulos2(Integer.parseInt(cod_transaccion)));
                listaTransaccionArticulos.setPageSize(listaTransaccionArticulos.getNrOfElements());
//               listaTransaccionArticulos.getNrOfElements()
                modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);

                PagedListHolder ListaUM = new PagedListHolder(this.adqui.getUnidadesMedida());
                ListaUM.setPageSize(ListaUM.getNrOfElements());
                modelo.put("ListaUM",ListaUM);

                PagedListHolder listaDocs = new PagedListHolder(this.adqui.getAdjuntos(Integer.parseInt(cod_transaccion)));
                listaDocs.setPageSize(listaDocs.getNrOfElements());
                modelo.put("listaDocs",listaDocs);
                modelo.put("listaTransaccionArticulos",listaTransaccionArticulos);
//                modelo.put("error","Por Favor Adjunte un documento" );
           
            
            return new ModelAndView("transaccionMateriales/Transaccion2", modelo);
            
		
	}
}