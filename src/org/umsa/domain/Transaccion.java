/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author julian
 */
public class Transaccion implements Serializable {
    
    private String ciudad;
    private String lugar;
    private String hora_nc;
    private String fecha_nc;
    
    private int ver;
    
    private String cod_dea;
    private String cuce;
    private String min, max;
    private String total_filas;
    
    private int max_cod;
    
    private String ubicacion,descripcion;
    private String nro_sol_comp;

    //private String cod_adjunto;
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHora_nc() {
        return hora_nc;
    }

    public void setHora_nc(String hora_nc) {
        this.hora_nc = hora_nc;
    }

    public String getFecha_nc() {
        return fecha_nc;
    }

    public void setFecha_nc(String fecha_nc) {
        this.fecha_nc = fecha_nc;
    }

    public String getNro_sol_comp() {
        return nro_sol_comp;
    }

    public void setNro_sol_comp(String nro_sol_comp) {
        this.nro_sol_comp = nro_sol_comp;
    }
    

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getMax_cod() {
        return max_cod;
    }

    public void setMax_cod(int max_cod) {
        this.max_cod = max_cod;
    }

    public String getTotal_filas() {
        return total_filas;
    }

    public void setTotal_filas(String total_filas) {
        this.total_filas = total_filas;
    }
    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getCuce() {
        return cuce;
    }

    public void setCuce(String cuce) {
        this.cuce = cuce;
    }

    public String getCod_dea() {
        return cod_dea;
    }

    public void setCod_dea(String cod_dea) {
        this.cod_dea = cod_dea;
    }

    
    

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }
    /*********************/
    private String indice;
    private int cod_transaccion;
    private int nro_transaccion;
    private int gestion;
    private String nro;
    private String nro_gestion;
    private String fecha;
    private String unidad_sol;
    private String unidad_des;
    private String usuario_sol;
    private String tipo_pedido;
    private String estado;
    private String apert_prog;
    private int usr_reg;
    private String ingreso_material;
    private int cod_almacen;

    private String cod_trans_detalle;    
    private String articulo;
    private String cod_unidad_medida;

    public String getCod_unidad_medida() {
        return cod_unidad_medida;
    }

    public void setCod_unidad_medida(String cod_unidad_medida) {
        this.cod_unidad_medida = cod_unidad_medida;
    }
    private String unidad_medida;

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }
    private String cantidad;
    private int cantidad_pedido;
    private int cantidad_entrega;
    
    private int cod_usuario;
    private int cod_tramite;
    private int cod_item;
    private String item;
    private String tipo_item;
    private int cod_complemento;
    
    private String detalle;

    private String cod_estado;
    private String origen;
    private String destino;
    private int cod_w;
    
    private Date fecha_creacion;
    private Date fecha_envio;
    private String detalle_solicitud;
    private String nro_pedido;
    
    private double precio_unit;
    private double subtotal;
    private String cod_trans_nro;
    private String obs;
    private String terminos_ref;
    
    private String nro_orden;
    
    private String hoja_ruta;
    private String cbte;
    private String nombre;
    private String Casa_comercial;
    private String nombre_comercial;
    private String direccion;
    private String telefono;
    private String nit;
    
    private int nro_orden_compra;
    private Date fec_orden_compra;
    private String factura;
    private Date fecha_fact;
    private String memo;
    
    private int tipo_sol;
    private String cuantia;
    private int cod_cuantia;
    private double del;
    private double hasta;
    
    private String cod_docs;
    
    private String tipo_doc;
    private String partida;
    
   
    private String preventivo;
    private String num_ing;
    
    private String user_maker;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCasa_comercial() {
        return Casa_comercial;
    }

    public void setCasa_comercial(String Casa_comercial) {
        this.Casa_comercial = Casa_comercial;
    }
    
    
    
    public String getUser_maker() {
        return user_maker;
    }

    public void setUser_maker(String user_maker) {
        this.user_maker = user_maker;
    }

    public String getPreventivo() {
        return preventivo;
    }

    public void setPreventivo(String preventivo) {
        this.preventivo = preventivo;
    }

    public String getNum_ing() {
        return num_ing;
    }

    public void setNum_ing(String num_ing) {
        this.num_ing = num_ing;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }
    
    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }
    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }
    
    private int cod_adjunto;

    public int getCod_adjunto() {
        return cod_adjunto;
    }

    public void setCod_adjunto(int cod_adjunto) {
        this.cod_adjunto = cod_adjunto;
    }
    
    

    
    private String tipo_cuantia;

    public String getTipo_cuantia() {
        return tipo_cuantia;
    }

    public void setTipo_cuantia(String tipo_cuantia) {
        this.tipo_cuantia = tipo_cuantia;
    }
    /**
     * @return the gestion
     */
    public int getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(int gestion) {
        this.gestion = gestion;
    }
  

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the apert_prog
     */
    public String getApert_prog() {
        return apert_prog;
    }

    /**
     * @param apert_prog the apert_prog to set
     */
    public void setApert_prog(String apert_prog) {
        this.apert_prog = apert_prog;
    }

    /**
     * @return the unidad_sol
     */
    public String getUnidad_sol() {
        return unidad_sol;
    }

    /**
     * @param unidad_sol the unidad_sol to set
     */
    public void setUnidad_sol(String unidad_sol) {
        this.unidad_sol = unidad_sol;
    }

    /**
     * @return the unidad_des
     */
    public String getUnidad_des() {
        return unidad_des;
    }

    /**
     * @param unidad_des the unidad_des to set
     */
    public void setUnidad_des(String unidad_des) {
        this.unidad_des = unidad_des;
    }

    /**
     * @return the usuario_sol
     */
    public String getUsuario_sol() {
        return usuario_sol;
    }

    /**
     * @param usuario_sol the usuario_sol to set
     */
    public void setUsuario_sol(String usuario_sol) {
        this.usuario_sol = usuario_sol;
    }

    /**
     * @return the usr_reg
     */
    public int getUsr_reg() {
        return usr_reg;
    }

    /**
     * @param usr_reg the usr_reg to set
     */
    public void setUsr_reg(int usr_reg) {
        this.usr_reg = usr_reg;
    }

    /**
     * @return the unidad_medida
     */
    

    /**
     * @return the cantidad_entrega
     */
    public int getCantidad_entrega() {
        return cantidad_entrega;
    }

    /**
     * @param cantidad_entrega the cantidad_entrega to set
     */
    public void setCantidad_entrega(int cantidad_entrega) {
        this.cantidad_entrega = cantidad_entrega;
    }

    /**
     * @return the articulo
     */
    public String getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the cantidad_pedido
     */
    public int getCantidad_pedido() {
        return cantidad_pedido;
    }

    /**
     * @param cantidad_pedido the cantidad_pedido to set
     */
    public void setCantidad_pedido(int cantidad_pedido) {
        this.cantidad_pedido = cantidad_pedido;
    }


    /**
     * @return the nro_gest
     */
    public String getNro_gestion() {
        return nro_gestion;
    }

    /**
     * @param nro_gest the nro_gest to set
     */
    public void setNro_gestion(String nro_gestion) {
        this.nro_gestion = nro_gestion;
    }

    /**
     * @return the tipo_pedido
     */
    public String getTipo_pedido() {
        return tipo_pedido;
    }

    /**
     * @param tipo_pedido the tipo_pedido to set
     */
    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }

    /**
     * @return the cod_usuario
     */
    public int getCod_usuario() {
        return cod_usuario;
    }

    /**
     * @param cod_usuario the cod_usuario to set
     */
    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    /**
     * @return the cod_tramite
     */
    public int getCod_tramite() {
        return cod_tramite;
    }

    /**
     * @param cod_tramite the cod_tramite to set
     */
    public void setCod_tramite(int cod_tramite) {
        this.cod_tramite = cod_tramite;
    }

    /**
     * @return the ingreso_material
     */
    public String getIngreso_material() {
        return ingreso_material;
    }

    /**
     * @param ingreso_material the ingreso_material to set
     */
    public void setIngreso_material(String ingreso_material) {
        this.ingreso_material = ingreso_material;
    }

    /**
     * @return the cod_transaccion
     */
    public int getCod_transaccion() {
        return cod_transaccion;
    }

    /**
     * @param cod_transaccion the cod_transaccion to set
     */
    public void setCod_transaccion(int cod_transaccion) {
        this.cod_transaccion = cod_transaccion;
    }

    /**
     * @return the cod_trans_detalle
     */
    public String getCod_trans_detalle() {
        return cod_trans_detalle;
    }

    /**
     * @param cod_trans_detalle the cod_trans_detalle to set
     */
    public void setCod_trans_detalle(String cod_trans_detalle) {
        this.cod_trans_detalle = cod_trans_detalle;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the tipo_item
     */
    public String getTipo_item() {
        return tipo_item;
    }

    /**
     * @param tipo_item the tipo_item to set
     */
    public void setTipo_item(String tipo_item) {
        this.tipo_item = tipo_item;
    }

    /**
     * @return the cod_complemento
     */
    public int getCod_complemento() {
        return cod_complemento;
    }

    /**
     * @param cod_complemento the cod_complemento to set
     */
    public void setCod_complemento(int cod_complemento) {
        this.cod_complemento = cod_complemento;
    }

    /**
     * @return the cod_estado
     */
    public String getCod_estado() {
        return cod_estado;
    }

    /**
     * @param cod_estado the cod_estado to set
     */
    public void setCod_estado(String cod_estado) {
        this.cod_estado = cod_estado;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the cod_w
     */
    public int getCod_w() {
        return cod_w;
    }

    /**
     * @param cod_w the cod_w to set
     */
    public void setCod_w(int cod_w) {
        this.cod_w = cod_w;
    }

    /**
     * @return the fecha_creacion
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * @return the fecha_envio
     */
    public Date getFecha_envio() {
        return fecha_envio;
    }

    /**
     * @param fecha_envio the fecha_envio to set
     */
    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    /**
     * @return the detalle_solicitud
     */
    public String getDetalle_solicitud() {
        return detalle_solicitud;
    }

    /**
     * @param detalle_solicitud the detalle_solicitud to set
     */
    public void setDetalle_solicitud(String detalle_solicitud) {
        this.detalle_solicitud = detalle_solicitud;
    }

    /**
     * @return the nro_transaccion
     */
    public int getNro_transaccion() {
        return nro_transaccion;
    }

    /**
     * @param nro_transaccion the nro_transaccion to set
     */
    public void setNro_transaccion(int nro_transaccion) {
        this.nro_transaccion = nro_transaccion;
    }

    /**
     * @return the cod_almacen
     */
    public int getCod_almacen() {
        return cod_almacen;
    }

    /**
     * @param cod_almacen the cod_almacen to set
     */
    public void setCod_almacen(int cod_almacen) {
        this.cod_almacen = cod_almacen;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nro_pedido
     */
    public String getNro_pedido() {
        return nro_pedido;
    }

    /**
     * @param nro_pedido the nro_pedido to set
     */
    public void setNro_pedido(String nro_pedido) {
        this.nro_pedido = nro_pedido;
    }

    /**
     * @return the precio_unit
     */
    public double getPrecio_unit() {
        return precio_unit;
    }

    /**
     * @param precio_unit the precio_unit to set
     */
    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the nro
     */
    public String getNro() {
        return nro;
    }

    /**
     * @param nro the nro to set
     */
    public void setNro(String nro) {
        this.nro = nro;
    }

    /**
     * @return the cod_trans_nro
     */
    public String getCod_trans_nro() {
        return cod_trans_nro;
    }

    /**
     * @param cod_trans_nro the cod_trans_nro to set
     */
    public void setCod_trans_nro(String cod_trans_nro) {
        this.cod_trans_nro = cod_trans_nro;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the terminos_ref
     */
    public String getTerminos_ref() {
        return terminos_ref;
    }

    /**
     * @param terminos_ref the terminos_ref to set
     */
    public void setTerminos_ref(String terminos_ref) {
        this.terminos_ref = terminos_ref;
    }

    /**
     * @return the nro_orden
     */
    public String getNro_orden() {
        return nro_orden;
    }

    /**
     * @param nro_orden the nro_orden to set
     */
    public void setNro_orden(String nro_orden) {
        this.nro_orden = nro_orden;
    }

    /**
     * @return the Huja_ruta
     */
    public String getHoja_ruta() {
        return hoja_ruta;
    }

    /**
     * @param Huja_ruta the Huja_ruta to set
     */
    public void setHoja_ruta(String hoja_ruta) {
        this.hoja_ruta = hoja_ruta;
    }

    /**
     * @return the cbte
     */
    public String getCbte() {
        return cbte;
    }

    /**
     * @param cbte the cbte to set
     */
    public void setCbte(String cbte) {
        this.cbte = cbte;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the nro_orden_compra
     */
    public int getNro_orden_compra() {
        return nro_orden_compra;
    }

    /**
     * @param nro_orden_compra the nro_orden_compra to set
     */
    public void setNro_orden_compra(int nro_orden_compra) {
        this.nro_orden_compra = nro_orden_compra;
    }

    /**
     * @return the fec_orden_compra
     */
    public Date getFec_orden_compra() {
        return fec_orden_compra;
    }

    /**
     * @param fec_orden_compra the fec_orden_compra to set
     */
    public void setFec_orden_compra(Date fec_orden_compra) {
        this.fec_orden_compra = fec_orden_compra;
    }

    /**
     * @return the fecha_fact
     */
    public Date getFecha_fact() {
        return fecha_fact;
    }

    /**
     * @param fecha_fact the fecha_fact to set
     */
    public void setFecha_fact(Date fecha_fact) {
        this.fecha_fact = fecha_fact;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the factura
     */
    public String getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(String factura) {
        this.factura = factura;
    }

    /**
     * @return the cantidad
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the cuantia
     */
    public String getCuantia() {
        return cuantia;
    }

    /**
     * @param cuantia the cuantia to set
     */
    public void setCuantia(String cuantia) {
        this.cuantia = cuantia;
    }

    /**
     * @return the cod_cuantia
     */
    public int getCod_cuantia() {
        return cod_cuantia;
    }

    /**
     * @param cod_cuantia the cod_cuantia to set
     */
    public void setCod_cuantia(int cod_cuantia) {
        this.cod_cuantia = cod_cuantia;
    }

    /**
     * @return the del
     */
    public double getDel() {
        return del;
    }

    /**
     * @param del the del to set
     */
    public void setDel(double del) {
        this.del = del;
    }

    /**
     * @return the hasta
     */
    public double getHasta() {
        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(double hasta) {
        this.hasta = hasta;
    }

    /**
     * @return the tipo_sol
     */
    public int getTipo_sol() {
        return tipo_sol;
    }

    /**
     * @param tipo_sol the tipo_sol to set
     */
    public void setTipo_sol(int tipo_sol) {
        this.tipo_sol = tipo_sol;
    }

    /**
     * @return the cod_item
     */
    public int getCod_item() {
        return cod_item;
    }

    /**
     * @param cod_item the cod_item to set
     */
    public void setCod_item(int cod_item) {
        this.cod_item = cod_item;
    }

    /**
     * @return the cod_docs
     */
    public String getCod_docs() {
        return cod_docs;
    }

    /**
     * @param cod_docs the cod_docs to set
     */
    public void setCod_docs(String cod_docs) {
        this.cod_docs = cod_docs;
    }


}
