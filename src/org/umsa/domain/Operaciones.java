/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.domain;

/**
 *
 * @author UMSA-JES
 */
public class Operaciones {
    private String gestion;
    private String partida, detalle_partida;
    private String tipo_id, sigla;
    private String clase_beneficiario, descripcion;
    private String cod_tipo_item, detalle,apertura;
    private String articulo,unidad_medida,cod_item;
    private String cod_unidad_medida;
    private String cod_transaccion,cod_cuantia, cuantia, cod_w, detalle_w;
    private String precio,cantidad;

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCod_unidad_medida() {
        return cod_unidad_medida;
    }

    public void setCod_unidad_medida(String cod_unidad_medida) {
        this.cod_unidad_medida = cod_unidad_medida;
    }

    public String getCod_item() {
        return cod_item;
    }

    public void setCod_item(String cod_item) {
        this.cod_item = cod_item;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getApertura() {
        return apertura;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getCod_transaccion() {
        return cod_transaccion;
    }

    public void setCod_transaccion(String cod_transaccion) {
        this.cod_transaccion = cod_transaccion;
    }
    
    private int cod_adjunto;

    public int getCod_adjunto() {
        return cod_adjunto;
    }

    public void setCod_adjunto(int cod_adjunto) {
        this.cod_adjunto = cod_adjunto;
    }

    public String getCod_cuantia() {
        return cod_cuantia;
    }

    public void setCod_cuantia(String cod_cuantia) {
        this.cod_cuantia = cod_cuantia;
    }

    public String getCuantia() {
        return cuantia;
    }

    public void setCuantia(String cuantia) {
        this.cuantia = cuantia;
    }

    public String getCod_w() {
        return cod_w;
    }

    public void setCod_w(String cod_w) {
        this.cod_w = cod_w;
    }

    public String getDetalle_w() {
        return detalle_w;
    }

    public void setDetalle_w(String detalle_w) {
        this.detalle_w = detalle_w;
    }
    
    public String getCod_tipo_item() {
        return cod_tipo_item;
    }

    public void setCod_tipo_item(String cod_tipo_item) {
        this.cod_tipo_item = cod_tipo_item;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getClase_beneficiario() {
        return clase_beneficiario;
    }

    public void setClase_beneficiario(String clase_beneficiario) {
        this.clase_beneficiario = clase_beneficiario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    

    public String getDetalle_partida() {
        return detalle_partida;
    }

    public void setDetalle_partida(String detalle_partida) {
        this.detalle_partida = detalle_partida;
    }
    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }
}
