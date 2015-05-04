package org.umsa.domain;

import java.io.Serializable;

//public class Roles extends Clientes {
public class Roles implements Serializable{

  /* Private Fields */
  
  private String rol;  
  private int id_rol;

     
  /* JavaBeans Properties */

  public String getRol() { return rol; }
  public void setRol(String rol) { this.rol = rol; } 

  public int getId_rol() { return id_rol; }
  public void setId_rol(int id_rol) { this.id_rol = id_rol; }

}