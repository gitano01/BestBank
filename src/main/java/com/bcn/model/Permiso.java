package com.bcn.model;

public class Permiso {
    private int permiso_id;
    private String permiso;
    private String descripcion;

    public int getPermiso_id() {
        return permiso_id;
    }
    public void setPermiso_id(int permiso_id) {
        this.permiso_id = permiso_id;
    }
    public String getPermiso() {
        return permiso;
    }
    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Permiso [permiso_id=" + permiso_id + ", permiso=" + permiso + ", descripcion=" + descripcion + "]";
    }    
    
}