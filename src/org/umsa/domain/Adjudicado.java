/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.umsa.domain;

import java.io.Serializable;

/**
 *
 * @author alex
 */
public class Adjudicado implements Serializable
{
    private String telefono;
    private String direccion;
    //====================
    private String tipo_id;
    private String documento;
    private String nombre;
    private String nombre_comercial;
    private String gestion;
    //=====================
    private String partida;
    private String articulo;
    //=====================
    private String min, max;
    private String total_filas;
    //========================
    private String detalle;

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getTipo_id() {
        return tipo_id;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }
    

    public void setTotal_filas(String total_filas) {
        this.total_filas = total_filas;
    }

    public String getTotal_filas() {
        return total_filas;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }
    
    public void setPartida(String partida) {
        this.partida = partida;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getPartida() {
        return partida;
    }

    public String getArticulo() {
        return articulo;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }
    
    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public String getGestion() {
        return gestion;
    }
}
