package org.umsa.domain.logic;

import java.util.List;
import org.umsa.dao.ClientesDao;
import org.umsa.dao.ItemsDao;
import org.umsa.dao.ProveedorDao;
import org.umsa.dao.TransaccionDao;
import org.umsa.dao.RolesDao;
import org.umsa.dao.OperacionesDao;
import org.umsa.dao.AdjudicadosDao;
import org.umsa.domain.Clientes;
import org.umsa.domain.Items;
import org.umsa.domain.Proveedor;
import org.umsa.domain.Transaccion;
import org.umsa.domain.Operaciones;
import org.umsa.domain.Adjudicado;

public class MiImpl implements MiFacade {

  private ClientesDao clientesDao;  
  private RolesDao rolesDao;
  private TransaccionDao transaccionDao;
  private ItemsDao itemsDao;
  private ProveedorDao proveedorDao;
  private OperacionesDao operacionesDao;
  private AdjudicadosDao adjudicadosDao;

  public List RestriccionAdjuntos(Transaccion transaccion) {
      return this.transaccionDao.RestriccionAdjuntos(transaccion);
  }
  public List RestriccionItems(Operaciones operaciones) {
      return this.operacionesDao.RestriccionItems(operaciones);
  }
  public void generaCUCE(String cod_transaccion){
      this.transaccionDao.generaCUCE(cod_transaccion);
  }
  /********Operaciones*************/
    
    public List GetDescPartida(Operaciones o){
        return this.operacionesDao.GetDescPartida(o);
    }        
    public List ListaAdjuntos(Operaciones o){
        return this.operacionesDao.ListaAdjuntos(o);
    } 
            
    public List Gestiones() {
        return this.operacionesDao.Gestiones();
    }
    public List getUnidadesMedida() {
        return this.operacionesDao.getUnidadesMedida();
    }
    public List GetTiposDoc(){ 
        return this.operacionesDao.GetTiposDoc();
    }
    public List GetTiposADJ(){ 
        return this.operacionesDao.GetTiposADJ();
    }
    public List GetApertProg(){ 
        return this.operacionesDao.GetApertProg();
    }
    public List GetClasesBeneficiario(){ 
        return this.operacionesDao.GetClasesBeneficiario();
    }
    public List GetTipoItems(){ 
        return this.operacionesDao.GetTipoItems();
    }
    public List GetPartidas(Operaciones o){
        return this.operacionesDao.GetPartidas(o);
    }
    public List GetPartidastotales(){
        System.out.println("esta adentro");
        return this.operacionesDao.GetPartidastotales();
//        return null;
    }
    public List GetItems(Operaciones o){
        return this.operacionesDao.GetItems(o);
    } 
    public List GetCuantias(){
        return this.operacionesDao.GetCuantias();
    }
    public List GetTipos(){
        return this.operacionesDao.GetTipos();
    }
               
    public int getMaxCodTram() {
        return this.transaccionDao.getMaxCodTram();
    }
    public List getEnviados(Transaccion transaccion) {
        return this.transaccionDao.getEnviados(transaccion);
    }
    public List getEnviados2(Transaccion transaccion) {
        return this.transaccionDao.getEnviados2(transaccion);
    }
    public List getEnviadosDetalle(Transaccion transaccion) {
        return this.transaccionDao.getEnviadosDetalle(transaccion);
    }
  /*********************/
    public OperacionesDao getOperacionesDao() {
        return operacionesDao;
    }

    public void setOperacionesDao(OperacionesDao operacionesDao) {
        this.operacionesDao = operacionesDao;
    }

    public ProveedorDao getProveedorDao() {
        return proveedorDao;
    }

    public void setProveedorDao(ProveedorDao proveedorDao) {
        this.proveedorDao = proveedorDao;
    }
  
  public List BuscaProveedor(Proveedor p) {
      return this.proveedorDao.BuscaProveedor(p);
  }
  public Proveedor ProveedorInfo(Proveedor p) {
      return this.proveedorDao.ProveedorInfo(p);
  }
  public List BuscaItems(Items i) {
      System.out.println("llega a items");
      return this.itemsDao.BuscaItems(i);
  }
  public List BuscaAdjudicados(Adjudicado i) {
      System.out.println("llega" + i.getMax());
      return this.adjudicadosDao.BuscaAdjudicados(i);
      //System.out.println(a.get(1));
      //return ;
  }
  public List DetalleAdju(Adjudicado i) {
      System.out.println("llega a Det");
      return this.adjudicadosDao.DetalleAdju(i);
  }
  public List InfAdju(Adjudicado i) {
      System.out.println("llega a Det");
      return this.adjudicadosDao.InforAdju(i);
  }
  
  //Cod_UMSA
  public String getCodUmsa(){
      return this.transaccionDao.getCodUmsa();
  }
  //
  public String getAperturaPadre(Transaccion transaccion){
      return this.transaccionDao.getAperturaPadre(transaccion);
  }
  public String getMaxNroTramite(Transaccion transaccion){
      return this.transaccionDao.getMaxNroTramite(transaccion);
  }
  //Busqueda
   public List busqueda(Transaccion transaccion) {
      return this.transaccionDao.busqueda(transaccion);
  }
  public List rastreo(Transaccion transaccion) {
    return this.transaccionDao.rastreo(transaccion);
  }
  public List archivos(Transaccion transaccion) {
    return this.transaccionDao.archivos(transaccion);
  }
  public List formu(Transaccion transaccion) {
    return this.transaccionDao.formu(transaccion);
  }
  public List formuSol(Transaccion transaccion) {
    return this.transaccionDao.formuSol(transaccion);
  }
  public List rastreoDetalle(Transaccion transaccion) {
    return this.transaccionDao.rastreoDetalle(transaccion);
  }
  //------------------ Clases DAO ---------------
  
  public void setClientesDao(ClientesDao clientesDao) {
    this.clientesDao = clientesDao;
  }
  
  public void setRolesDao(RolesDao rolesDao) {
    this.rolesDao = rolesDao;
  }

  public void setTransaccionDao(TransaccionDao transaccionDao) {
    this.transaccionDao = transaccionDao;
  }

  public void setItemsDao(ItemsDao itemsDao) {
    this.itemsDao = itemsDao;
  }
  
  //----------------- METODOS ---------------------

  
    //Clientes
  
  public Clientes getCodigoUsuario(Clientes cliente) {
      return this.clientesDao.getCodigoUsuario(cliente);
  }
     
  // roles
  
  public List getRolUsuario(Clientes clientes) {
      return this.rolesDao.getRolUsuario(clientes);
  }
   
  //Materiales  
  
  public List getCuantia() {
      return this.transaccionDao.getCuantia();
  }

  public List getTransaccionMateriales(Transaccion transaccion) {
      return this.transaccionDao.getTransaccionMateriales(transaccion);
  }

  public List getTransaccionSolicitudes(Transaccion transaccion) {
      return this.transaccionDao.getTransaccionSolicitudes(transaccion);
  }

  public Transaccion getTransaccionMaterial(Transaccion transaccion) {
      return this.transaccionDao.getTransaccionMaterial(transaccion);
  }

  public List getUnidadEjecutora(Clientes cliente) {
      return this.transaccionDao.getUnidadEjecutora(cliente);
  }
  
  public int getNroTransaccion(Clientes cliente){
     return this.transaccionDao.getNroTransaccion(cliente);
  }
  
  public int setTransaccion(Transaccion transaccion){
      return this.transaccionDao.setTransaccion(transaccion);
  }

  public void setTramiteNro(Transaccion transaccion){
      this.transaccionDao.setTramiteNro(transaccion);
  }
  
  public int getNroTramite(Transaccion transaccion){
      return this.transaccionDao.getNroTramite(transaccion);
  }
  
  public int getCodTransaccionNro(Transaccion transaccion){
      return this.transaccionDao.getCodTransaccionNro(transaccion);
  }
  
  public List getTransaccionComplemento(int cod_trans_detalle) {
      return this.transaccionDao.getTransaccionComplemento(cod_trans_detalle);
  }
    
  public void setTransaccionComplemento(Transaccion transaccion){
      this.transaccionDao.setTransaccionComplemento(transaccion);
  }
    
  public void setTransaccionEliminaComplemento(int cod_complemento){
      this.transaccionDao.setTransaccionEliminaComplemento(cod_complemento);
  }
  
  public void setTransaccionArticuloElimina(Transaccion transaccion){
      this.transaccionDao.setTransaccionArticuloElimina(transaccion);
  }

  public void delTransaccionEliminaDocs(int cod_transaccion){
      this.transaccionDao.delTransaccionEliminaDocs(cod_transaccion);
  }

  public void setTransaccionEliminaComplementoArticulos(Transaccion transaccion){
      this.transaccionDao.setTransaccionEliminaComplementoArticulos(transaccion);
  }
  public void setTransaccionEliminaRegistroFechaArticulos(Transaccion transaccion){
      this.transaccionDao.setTransaccionEliminaRegistroFechaArticulos(transaccion);
  }
  public void setTransaccionEliminaDetalleNroArticulos(Transaccion transaccion){
      this.transaccionDao.setTransaccionEliminaDetalleNroArticulos(transaccion);
  }
  public void setTransaccionAvanza(Transaccion transaccion){
      this.transaccionDao.setTransaccionAvanza(transaccion);
  }
  public void setTransaccionDetalleNro(Transaccion transaccion){
      this.transaccionDao.setTransaccionDetalleNro(transaccion);
  }
   public void addTransDetalle(Transaccion transaccion){
      this.transaccionDao.addTransDetalle(transaccion);
  }
  public List getTransaccionListaSolicitudes(Transaccion transaccion) {
      return this.transaccionDao.getTransaccionListaSolicitudes(transaccion);
  }
  
  public List getTransaccionListaPedidos(Transaccion transaccion) {
      return this.transaccionDao.getTransaccionListaPedidos(transaccion);
  }
  
  public void nroSolicitud(Transaccion transaccion){
    this.transaccionDao.nroSolicitud(transaccion);
  }
  public int getTransaccionNroItems(int cod_transaccion) {
      return this.transaccionDao.getTransaccionNroItems(cod_transaccion);
  }
  
  public void addNroPedido(Transaccion transaccion) {
     this.transaccionDao.addNroPedido(transaccion);
  }
  public void setEliminaTransaccion(int cod_transaccion) {
      this.transaccionDao.setEliminaTransaccion(cod_transaccion);
  }
  
  public int getCodAlmacen(int cod_transaccion) {
      return this.transaccionDao.getCodAlmacen(cod_transaccion);
  }
  /*==================================================
  // TIPOS DE TRAMITES SOLICITUD;ORDEN;PEDIDOS;INGRSOS;
  ====================================================*/
  public String getTipoTramite(Items tipo_tramite){
      return this.itemsDao.getTipoTramite(tipo_tramite);
  }
  
  public List getItemTipo(){
      return this.itemsDao.getItemTipo();
  }
  
  public List getItemGrupo(int cod_tipo_item){
      return this.itemsDao.getItemGrupo(cod_tipo_item);
  }
  
  public List getItemRubro(Items tipo_item){
      return this.itemsDao.getItemRubro(tipo_item);
  }
  
  public List getItemArticulo(Items tipo_item){
      return this.itemsDao.getItemArticulo(tipo_item);
  }
  
  public String getItemArticuloUnidadMedida(Items tipo_item){
      return this.itemsDao.getItemArticuloUnidadMedida(tipo_item);
  }
  
  public Transaccion getTransaccionCodItem(Transaccion item) {
      return this.itemsDao.getTransaccionCodItem(item);
  }
  
  public void setTransaccionArticulo(Transaccion transaccion){
      this.itemsDao.setTransaccionArticulo(transaccion);
  }
    
  public List getTransaccionArticulos(int cod_transaccion) {
      return this.itemsDao.getTransaccionArticulos(cod_transaccion);
  }
  public List getTransaccionArticulos2(int cod_transaccion) {
      return this.itemsDao.getTransaccionArticulos2(cod_transaccion);
  }
  
  public List getTransaccionDetalle(int cod_transaccion) {
      return this.itemsDao.getTransaccionDetalle(cod_transaccion);
  }
  
  public List getAdjuntos(int cod_transaccion) {
      return this.itemsDao.getAdjuntos(cod_transaccion);
  }
  
  public List getBuscaItems(Items item) {
      return this.itemsDao.getBuscaItems(item);
  }
  
  public List getBuscaConsultoresObras(int cod_tipo_item) {
      return this.itemsDao.getBuscaConsultoresObras(cod_tipo_item);
  }
  
  /* =======================================================================
   *                CONSULTORES
  ========================================================================*/
  
    public List getTransaccionConsultores(Transaccion transaccion) {
        return this.transaccionDao.getTransaccionConsultores(transaccion);
    }
    
    public void setTransaccionTerminos(Transaccion transaccion){
        this.transaccionDao.setTransaccionTerminos(transaccion);
    }

    public void delTransaccionTerminos(int cod_docs){
        this.transaccionDao.delTransaccionTerminos(cod_docs);
    }

    public TransaccionDao getTransaccionDao() {
        return transaccionDao;
    }

    public void setAdjudicadosDao(AdjudicadosDao adjudicadosDao) {
        this.adjudicadosDao = adjudicadosDao;
    }
    
    
}

