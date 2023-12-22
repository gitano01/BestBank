package com.bcn.model;

import java.sql.Timestamp;

public class TarjetasClientes {
	private int tarjetaClienteId;
	private String mesExp;
	private String añoExp;
	private String cvv;
	private String nip;
	private String numeroTarjeta;
	private String tipoTarjeta;
	private String estatusTarjeta;
	private Double balance;
	private boolean tarjetaActiva;
	private boolean tarjetaCancelada;
	private boolean tarjetaMigrada;
	private boolean bloqueoTemporal;
	private boolean bloqueoPermanente;
	private boolean activarTarjeta;
	private boolean bloquearTarjeta;
	private boolean bloqueoPermanenteTarjeta;
	private boolean cancelarTarjeta;
	private boolean migrarTarjeta;
	private Timestamp fechaAlta;
	private Timestamp fechaActivacion;
	private Timestamp fechaModificacion;
	private Timestamp fechaCancelacion;
	private Timestamp fechaMigrado;
	private Timestamp fechaBloqueo;
	private Object cuentaId;
	private Object clienteId;

	public boolean isMigrarTarjeta() {
		return migrarTarjeta;
	}

	public void setMigrarTarjeta(boolean migrarTarjeta) {
		this.migrarTarjeta = migrarTarjeta;
	}

	public boolean isTarjetaCancelada() {
		return tarjetaCancelada;
	}

	public void setTarjetaCancelada(boolean tarjetaCancelada) {
		this.tarjetaCancelada = tarjetaCancelada;
	}

	public boolean isActivarTarjeta() {
		return activarTarjeta;
	}

	public void setActivarTarjeta(boolean activarTarjeta) {
		this.activarTarjeta = activarTarjeta;
	}

	public boolean isBloquearTarjeta() {
		return bloquearTarjeta;
	}

	public void setBloquearTarjeta(boolean bloquearTarjeta) {
		this.bloquearTarjeta = bloquearTarjeta;
	}

	public boolean isBloqueoPermanenteTarjeta() {
		return bloqueoPermanenteTarjeta;
	}

	public void setBloqueoPermanenteTarjeta(boolean bloqueoPermanenteTarjeta) {
		this.bloqueoPermanenteTarjeta = bloqueoPermanenteTarjeta;
	}

	public boolean isCancelarTarjeta() {
		return cancelarTarjeta;
	}

	public void setCancelarTarjeta(boolean cancelarTarjeta) {
		this.cancelarTarjeta = cancelarTarjeta;
	}

	public int getTarjetaClienteId() {
		return tarjetaClienteId;
	}

	public void setTarjetaClienteId(int tarjetaClienteId) {
		this.tarjetaClienteId = tarjetaClienteId;
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

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public String getEstatusTarjeta() {
		return estatusTarjeta;
	}

	public void setEstatusTarjeta(String estatusTarjeta) {
		this.estatusTarjeta = estatusTarjeta;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public boolean isTarjetaActiva() {
		return tarjetaActiva;
	}

	public void setTarjetaActiva(boolean tarjetaActiva) {
		this.tarjetaActiva = tarjetaActiva;
	}

	public boolean isTarjetaMigrada() {
		return tarjetaMigrada;
	}

	public void setTarjetaMigrada(boolean tarjetaMigrada) {
		this.tarjetaMigrada = tarjetaMigrada;
	}

	public boolean isBloqueoTemporal() {
		return bloqueoTemporal;
	}

	public void setBloqueoTemporal(boolean bloqueoTemporal) {
		this.bloqueoTemporal = bloqueoTemporal;
	}

	public boolean isBloqueoPermanente() {
		return bloqueoPermanente;
	}

	public void setBloqueoPermanente(boolean bloqueoPermanente) {
		this.bloqueoPermanente = bloqueoPermanente;
	}

	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Timestamp getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(Timestamp fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public Timestamp getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Timestamp fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Timestamp getFechaMigrado() {
		return fechaMigrado;
	}

	public void setFechaMigrado(Timestamp fechaMigrado) {
		this.fechaMigrado = fechaMigrado;
	}

	public Timestamp getFechaBloqueo() {
		return fechaBloqueo;
	}

	public void setFechaBloqueo(Timestamp fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	public Object getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Object cuentaId) {
		this.cuentaId = cuentaId;
	}

	public Object getClienteId() {
		return clienteId;
	}

	public void setClienteId(Object clienteId) {
		this.clienteId = clienteId;
	}

}
