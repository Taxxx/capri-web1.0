/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.domain;

import java.io.Serializable;

/**
 *
 * @author julian
 */

public class Items implements Serializable{
    private String tipo_item;
    private String grupo;
    private String rubro;
    private String articulo;
    private String unidad_medida;

    private int cod_tramite;
    private int cod_almacen;
        
    private int cod_tipo_item;
    private int cod_usuario; 
    
    private int cod_item;
    
    /**********************/
    private String codigo;
    private String partida;
    private String gestion;
    private String tipo;
    private String total_filas;
    private String min, max;
    //private String partida;
    private double precio_unit;

    public double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
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
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTotal_filas() {
        return total_filas;
    }

    public void setTotal_filas(String total_filas) {
        this.total_filas = total_filas;
    }
    /***********************/

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
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
     * @return the unidad_medida
     */
    public String getUnidad_medida() {
        return unidad_medida;
    }

    /**
     * @param unidad_medida the unidad_medida to set
     */
    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
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
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
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
     * @return the cod_tipo_item
     */
    public int getCod_tipo_item() {
        return cod_tipo_item;
    }

    /**
     * @param cod_tipo_item the cod_tipo_item to set
     */
    public void setCod_tipo_item(int cod_tipo_item) {
        this.cod_tipo_item = cod_tipo_item;
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
     * @return the rubro
     */
    public String getRubro() {
        return rubro;
    }

    /**
     * @param rubro the rubro to set
     */
    public void setRubro(String rubro) {
        this.rubro = rubro;
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

}
