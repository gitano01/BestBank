package com.bcn.model;

import java.sql.Timestamp;

public class Cuenta {
	private int cuentaId;
	private Timestamp fechaApertura;
	private Timestamp fechaCierre;
	private String numeroCuenta;
	private String clabe;
	private boolean activo;
	private String estatusCuenta;
	private String tipoCuenta;
	private double saldoInicial;
	private double saldoMaximo;
	private double balance;
	private double saldoAnterior;
	private double montoDeposito;
	private double montoRetiro;

	public double getMontoDeposito() {
		return montoDeposito;
	}

	public void setMontoDeposito(double montoDeposito) {
		this.montoDeposito = montoDeposito;
	}

	public double getMontoRetiro() {
		return montoRetiro;
	}

	public void setMontoRetiro(double montoRetiro) {
		this.montoRetiro = montoRetiro;
	}

	public double getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	private int clienteId;

	public int getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(int cuentaId) {
		this.cuentaId = cuentaId;
	}

	public Timestamp getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Timestamp fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Timestamp getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Timestamp fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public double getSaldoMaximo() {
		return saldoMaximo;
	}

	public void setSaldoMaximo(double saldoMaximo) {
		this.saldoMaximo = saldoMaximo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getEstatusCuenta() {
		return estatusCuenta;
	}

	public void setEstatusCuenta(String estatusCuenta) {
		this.estatusCuenta = estatusCuenta;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

}
