package org.umsa.domain.logic;

import java.util.List;
import org.umsa.domain.Clientes;
import org.umsa.domain.Items;
import org.umsa.domain.Operaciones;
import org.umsa.domain.Proveedor;
import org.umsa.domain.Transaccion;
import org.umsa.domain.Adjudicado;


public interface MiFacade {
    List RestriccionAdjuntos(Transaccion t);
    List RestriccionItems(Operaciones operaciones);
    void generaCUCE(String cod_transaccion);
  /********OPERACIONES*************/
    List GetDescPartida(Operaciones o);
    List ListaAdjuntos(Operaciones o);
    
    List Gestiones();
    List getUnidadesMedida();
    List GetTiposDoc();
    List GetClasesBeneficiario();
    List GetTipoItems();
    List GetPartidas(Operaciones o);
    List GetPartidastotales();
    List GetItems(Operaciones o);
    List GetCuantias();
    List GetTipos();
    List GetTiposADJ();
    List GetApertProg();
    int getMaxCodTram();
    
    List getEnviados(Transaccion t);
    List getEnviados2(Transaccion t);
    List getEnviadosDetalle(Transaccion t);
  /******************/  
  List BuscaItems(Items i);
  
  List BuscaAdjudicados(Adjudicado i);
  List DetalleAdju(Adjudicado i);
  List InfAdju(Adjudicado i);
  
  List BuscaProveedor(Proveedor p);
  Proveedor ProveedorInfo(Proveedor p);
  //cod UMSA
    String getCodUmsa();
  //
   String getAperturaPadre(Transaccion transaccion);
   String getMaxNroTramite(Transaccion transaccion);
  //Busqueda
  List busqueda(Transaccion transaccion);
  List rastreo(Transaccion transaccion);
  List archivos(Transaccion transaccion);
  List formu(Transaccion transaccion);
  List formuSol(Transaccion transaccion);
  List rastreoDetalle(Transaccion transaccion);
    
  //Clientes
  Clientes getCodigoUsuario(Clientes cliente);
  // Rol
  List getRolUsuario(Clientes cliente);
    //Transaccion
  String getTipoTramite(Items tipo_tram);
  List getCuantia();  
  List getTransaccionMateriales(Transaccion transaccion);  
  List getTransaccionSolicitudes(Transaccion transaccion);  
  Transaccion getTransaccionMaterial(Transaccion transaccion);
  List getUnidadEjecutora(Clientes cliente);
  int getNroTransaccion(Clientes cliente );
  int setTransaccion(Transaccion transaccion);
  void setTramiteNro(Transaccion transaccion);
  int getNroTramite(Transaccion transaccion);
  int getCodTransaccionNro(Transaccion transaccion);
  List getTransaccionComplemento(int cod_trans_detalle); 
  void setTransaccionEliminaComplemento(int cod_complemento);
  void setTransaccionComplemento(Transaccion transaccion); 
  void setTransaccionArticuloElimina(Transaccion transaccion);
  void delTransaccionEliminaDocs(int cod_transaccion);
  void setTransaccionEliminaComplementoArticulos(Transaccion transaccion);
  void setTransaccionEliminaRegistroFechaArticulos(Transaccion transaccion);
  void setTransaccionEliminaDetalleNroArticulos(Transaccion transaccion);
  void setTransaccionAvanza(Transaccion transaccion);
  void setTransaccionDetalleNro(Transaccion transaccion);
  void addTransDetalle(Transaccion transaccion);
  void addNroPedido(Transaccion transaccion);  
  List getTransaccionListaSolicitudes(Transaccion transaccion);     
  List getTransaccionListaPedidos(Transaccion transaccion);     
  int getTransaccionNroItems(int cod_transaccion);  
  void setEliminaTransaccion(int cod_transaccion);
  
  int getCodAlmacen(int cod_transaccion);
  
  void nroSolicitud(Transaccion transaccion);  
  //transaccion de los items y sus complementos
  List getItemTipo();
  List getItemGrupo(int cod_tipo_item);
  List getItemRubro(Items tipo_item);
  List getItemArticulo(Items tipo_item);
  String getItemArticuloUnidadMedida(Items tipo_item);  
  Transaccion getTransaccionCodItem(Transaccion item);
  void setTransaccionArticulo(Transaccion transaccion);
  
  List getTransaccionArticulos(int cod_transaccion);
  List getTransaccionArticulos2(int cod_transaccion);
  List getTransaccionDetalle(int cod_transaccion); 
  List getAdjuntos(int cod_transaccion);
  
  
  List getBuscaItems(Items item);  
  List getBuscaConsultoresObras(int cod_tipo_item);  
  
  // consultores
  List getTransaccionConsultores(Transaccion transaccion);
  void setTransaccionTerminos(Transaccion transaccion);
  void delTransaccionTerminos(int cod_docs);
}