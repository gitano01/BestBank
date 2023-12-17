package com.bcn.model;

import java.sql.Timestamp;

public class InventarioTarjetas {
	private int inventarioTarjetaId;
	private String numeroTarjeta;
	private String mesExp;
	private String añoExp;
	private String cvv;
	private String nip;
	private boolean tarjetaAsignada;
	private String proveedor;
	private boolean tarjetaDañada;
	private Timestamp fechaRecepcion;
	private Timestamp fechaModificacion;
	private Timestamp fechaAsignacion;
	private Timestamp fechaBaja;
	private int sucursalId;

	public int getInventarioTarjetaId() {
		return inventarioTarjetaId;
	}

	public void setInventarioTarjetaId(int inventarioTarjetaId) {
		this.inventarioTarjetaId = inventarioTarjetaId;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getMesExp() {
		return mesExp;
	}

	public void setMesExp(String mesExp) {
		this.mesExp = mesExp;
	}

	public String getAñoExp() {
		return añoExp;
	}

	public void setAñoExp(String añoExp) {
		this.añoExp = añoExp;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public boolean isTarjetaAsignada() {
		return tarjetaAsignada;
	}

	public void setTarjetaAsignada(boolean tarjetaAsignada) {
		this.tarjetaAsignada = tarjetaAsignada;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public boolean isTarjetaDañada() {
		return tarjetaDañada;
	}

	public void setTarjetaDañada(boolean tarjetaDañada) {
		this.tarjetaDañada = tarjetaDañada;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Timestamp getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Timestamp fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Timestamp getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Timestamp fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

}
