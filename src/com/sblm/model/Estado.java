package com.sblm.model;

// Generated 05-ago-2013 16:25:24 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Estado generated by hbm2java
 */
@Entity
@Table(name = "ESTADO", schema = "dbo", catalog = "beneficenciadba")
public class Estado implements java.io.Serializable {

	private int idestado;
	private String codigoestado;
	private String descripcion;

	public Estado() {
	}

	public Estado(int idestado) {
		this.idestado = idestado;
	}

	public Estado(int idestado, String codigoestado, String descripcion) {
		this.idestado = idestado;
		this.codigoestado = codigoestado;
		this.descripcion = descripcion;
	}

	@Id @GeneratedValue
	@Column(name = "IDESTADO", unique = true, nullable = false)
	public int getIdestado() {
		return this.idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	@Column(name = "CODIGOESTADO", length = 4)
	public String getCodigoestado() {
		return this.codigoestado;
	}

	public void setCodigoestado(String codigoestado) {
		this.codigoestado = codigoestado;
	}

	@Column(name = "DESCRIPCION", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
