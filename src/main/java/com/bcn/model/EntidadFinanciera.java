package com.bcn.model;

public class EntidadFinanciera {
	public int entidadId;
	private String numeroAbm;
	private String nombreAbreviado;

	public int getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(int entidadId) {
		this.entidadId = entidadId;
	}

	public String getNumeroAbm() {
		return numeroAbm;
	}

	public void setNumeroAbm(String numeroAbm) {
		this.numeroAbm = numeroAbm;
	}

	public String getNombreAbreviado() {
		return nombreAbreviado;
	}

	public void setNombreAbreviado(String nombreAbreviado) {
		this.nombreAbreviado = nombreAbreviado;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	private String nombreInstitucion;
}
