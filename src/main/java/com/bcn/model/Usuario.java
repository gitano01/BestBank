package com.bcn.model;

import java.sql.Timestamp;

public class Usuario {
    private int usuario_id;
    private String usuario;
    private String contrasenia;
    private String email;
    private boolean activo;
    private int rol_id;
    private int permiso_id;
    private int empleado_id;
    private Timestamp fecha_alta;
    private Timestamp fecha_baja;
    private Timestamp fecha_modificacion;    
    private int cliente_id;
    
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
    public void setPermiso_id(int permiso_id) {
        this.permiso_id = permiso_id;
    }
    public void setFecha_alta(Timestamp fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
    public void setFecha_baja(Timestamp fecha_baja) {
        this.fecha_baja = fecha_baja;
    }
    public void setFecha_modificacion(Timestamp fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }
    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }
    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }
    public int getUsuario_id() {
        return usuario_id;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public String getEmail() {
        return email;
    }
    public boolean getActivo() {
        return activo;
    }
    public int getRol_id() {
        return rol_id;
    }
    public int getPermiso_id() {
        return permiso_id;
    }
    public Timestamp getFecha_alta() {
        return fecha_alta;
    }
    public Timestamp getFecha_baja() {
        return fecha_baja;
    }
    public Timestamp getFecha_modificacion() {
        return fecha_modificacion;
    }
    public int getEmpleado_id() {
        return empleado_id;
    }
    public int getCliente_id() {
        return cliente_id;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }    

}
