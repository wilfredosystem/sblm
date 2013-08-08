package com.sblm.model;

// Generated 05-ago-2013 16:25:24 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Area generated by hbm2java
 */
@Entity
@Table(name = "AREA", schema = "dbo", catalog = "beneficenciadba")
public class Area implements java.io.Serializable {

	private int idarea;
	private String codigoarea;
	private String descripcion;
	private String sigla;
	private String direccion;
	private String telefono;
	private String anexo;
	private String fax;
	private String web;
	private Boolean habarea;
	private Boolean estado;
	private Set<Documento> documentos = new HashSet<Documento>(0);
	private Set<Flujodocumento> flujodocumentos = new HashSet<Flujodocumento>(0);

	public Area() {
	}

	public Area(int idarea) {
		this.idarea = idarea;
	}

	public Area(int idarea, String codigoarea, String descripcion,
			String sigla, String direccion, String telefono, String anexo,
			String fax, String web, Boolean habarea, Boolean estado,
			Set<Documento> documentos, Set<Flujodocumento> flujodocumentos) {
		this.idarea = idarea;
		this.codigoarea = codigoarea;
		this.descripcion = descripcion;
		this.sigla = sigla;
		this.direccion = direccion;
		this.telefono = telefono;
		this.anexo = anexo;
		this.fax = fax;
		this.web = web;
		this.habarea = habarea;
		this.estado = estado;
		this.documentos = documentos;
		this.flujodocumentos = flujodocumentos;
	}

	@Id @GeneratedValue
	@Column(name = "IDAREA", unique = true, nullable = false)
	public int getIdarea() {
		return this.idarea;
	}

	public void setIdarea(int idarea) {
		this.idarea = idarea;
	}

	@Column(name = "CODIGOAREA", length = 8)
	public String getCodigoarea() {
		return this.codigoarea;
	}

	public void setCodigoarea(String codigoarea) {
		this.codigoarea = codigoarea;
	}

	@Column(name = "DESCRIPCION", length = 80)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "SIGLA", length = 24)
	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Column(name = "DIRECCION", length = 100)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "TELEFONO", length = 10)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "ANEXO", length = 10)
	public String getAnexo() {
		return this.anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	@Column(name = "FAX", length = 10)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "WEB", length = 100)
	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Column(name = "HABAREA")
	public Boolean getHabarea() {
		return this.habarea;
	}

	public void setHabarea(Boolean habarea) {
		this.habarea = habarea;
	}

	@Column(name = "ESTADO")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	public Set<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	public Set<Flujodocumento> getFlujodocumentos() {
		return this.flujodocumentos;
	}

	public void setFlujodocumentos(Set<Flujodocumento> flujodocumentos) {
		this.flujodocumentos = flujodocumentos;
	}

}
