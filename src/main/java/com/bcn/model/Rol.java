package com.bcn.model;

public class Rol {
    private int rol_id;
    private String rol;
    private String descripcion;

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getRol_id() {
        return rol_id;
    }
    public String getRol() {
        return rol;
    }
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return "rol [rol_id=" + rol_id + ", rol=" + rol + ", descripcion=" + descripcion + "]";
    }
    
    
}