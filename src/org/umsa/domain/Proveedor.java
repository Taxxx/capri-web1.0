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
public class Proveedor {
    private String tipo_id, descripcion;

    private String nombre;
    private String nombre_comercial;
    private String documento;
    
    private String min, max;
    
    private String clase_beneficiario;
    
    private String adh_nombre;
    private String adh_tipo_id;
    private String adh_documento;
    
    private String dir_lugar, dir_direccion, dir_telefono, dir_email;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDir_lugar() {
        return dir_lugar;
    }

    public void setDir_lugar(String dir_lugar) {
        this.dir_lugar = dir_lugar;
    }
    
    private String total_filas;

    public String getTotal_filas() {
        return total_filas;
    }

    public void setTotal_filas(String total_filas) {
        this.total_filas = total_filas;
    }

    public String getDir_email() {
        return dir_email;
    }

    public void setDir_email(String dir_email) {
        this.dir_email = dir_email;
    }

    public String getDir_direccion() {
        return dir_direccion;
    }

    public void setDir_direccion(String dir_direccion) {
        this.dir_direccion = dir_direccion;
    }

    public String getDir_telefono() {
        return dir_telefono;
    }

    public void setDir_telefono(String dir_telefono) {
        this.dir_telefono = dir_telefono;
    }

    public String getAdh_tipo_id() {
        return adh_tipo_id;
    }

    public void setAdh_tipo_id(String adh_tipo_id) {
        this.adh_tipo_id = adh_tipo_id;
    }

    public String getAdh_documento() {
        return adh_documento;
    }

    public void setAdh_documento(String adh_documento) {
        this.adh_documento = adh_documento;
    }
    
    

    public String getAdh_nombre() {
        return adh_nombre;
    }

    public void setAdh_nombre(String adh_nombre) {
        this.adh_nombre = adh_nombre;
    }

    public String getClase_beneficiario() {
        return clase_beneficiario;
    }

    public void setClase_beneficiario(String clase_beneficiario) {
        this.clase_beneficiario = clase_beneficiario;
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

    

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
}
