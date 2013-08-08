package com.sblm.model3;

// Generated 05-ago-2013 16:25:24 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pagina generated by hbm2java
 */
@Entity
@Table(name = "PAGINA", schema = "dbo", catalog = "beneficenciadba")
public class Pagina implements java.io.Serializable {

	private int idpagina;
	private Modulo modulo;
	private String nombrepagina;
	private String descripcionpagina;
	private Boolean estado;
	private Date feccre;
	private Date fecmod;
	private String usrcre;
	private String usrmod;

	public Pagina() {
	}

	public Pagina(int idpagina) {
		this.idpagina = idpagina;
	}

	public Pagina(int idpagina, Modulo modulo, String nombrepagina,
			String descripcionpagina, Boolean estado, Date feccre, Date fecmod,
			String usrcre, String usrmod) {
		this.idpagina = idpagina;
		this.modulo = modulo;
		this.nombrepagina = nombrepagina;
		this.descripcionpagina = descripcionpagina;
		this.estado = estado;
		this.feccre = feccre;
		this.fecmod = fecmod;
		this.usrcre = usrcre;
		this.usrmod = usrmod;
	}

	@Id
	@Column(name = "IDPAGINA", unique = true, nullable = false)
	public int getIdpagina() {
		return this.idpagina;
	}

	public void setIdpagina(int idpagina) {
		this.idpagina = idpagina;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMODULO")
	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@Column(name = "NOMBREPAGINA", length = 50)
	public String getNombrepagina() {
		return this.nombrepagina;
	}

	public void setNombrepagina(String nombrepagina) {
		this.nombrepagina = nombrepagina;
	}

	@Column(name = "DESCRIPCIONPAGINA", length = 50)
	public String getDescripcionpagina() {
		return this.descripcionpagina;
	}

	public void setDescripcionpagina(String descripcionpagina) {
		this.descripcionpagina = descripcionpagina;
	}

	@Column(name = "ESTADO")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECCRE", length = 23)
	public Date getFeccre() {
		return this.feccre;
	}

	public void setFeccre(Date feccre) {
		this.feccre = feccre;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECMOD", length = 23)
	public Date getFecmod() {
		return this.fecmod;
	}

	public void setFecmod(Date fecmod) {
		this.fecmod = fecmod;
	}

	@Column(name = "USRCRE", length = 50)
	public String getUsrcre() {
		return this.usrcre;
	}

	public void setUsrcre(String usrcre) {
		this.usrcre = usrcre;
	}

	@Column(name = "USRMOD", length = 50)
	public String getUsrmod() {
		return this.usrmod;
	}

	public void setUsrmod(String usrmod) {
		this.usrmod = usrmod;
	}

}