package org.umsa.domain;

import java.io.Serializable;

public class Clientes implements Serializable {

  private int id_usuario;    
  private String clave;
  private String apodo;
  private String usuario;
  private int gestion; 
  private int id_rol;
  private String ruta_firm;
  private int cod_almacen;
  private String cod_apertura;

    public String getCod_apertura() {
        return cod_apertura;
    }

    public void setCod_apertura(String cod_apertura) {
        this.cod_apertura = cod_apertura;
    }
  
  /* JavaBeans Properties */

  public int getId_usuario() {
    return id_usuario;
  }
  public void setId_usuario(int id_usuario) {
    this.id_usuario = id_usuario;
  }
 
  public String getClave(){
    return clave;
  }
  public void setClave(String clave){
    this.clave = clave;
  }

  public String getApodo(){
      return apodo;
  }
  public void setApodo(String apodo){
      this.apodo=apodo;
  }
  
  public String getUsuario(){
      return usuario;
  }
  public void setUsuario(String usuario){
      this.usuario=usuario;
  }
  
  public int getGestion(){
      return gestion;
  }
  
  public void setGestion(int gestion){
      this.gestion=gestion;
  }

  public int getId_rol(){return id_rol;  }
  public void setId_rol(int id_rol){ this.id_rol=id_rol; }

  public String getRuta_firm(){
      return ruta_firm;
  }
  public void setRuta_firm(String ruta_firm){
      this.ruta_firm=ruta_firm;
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
}

