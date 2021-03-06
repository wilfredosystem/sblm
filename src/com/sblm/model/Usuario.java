package com.sblm.model;

// Generated 05-ago-2013 16:25:24 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "USUARIO", schema = "dbo", catalog = "beneficenciadba")
public class Usuario implements java.io.Serializable {

	private int idusuario;
	private String nombres;
	private String apellidopat;
	private String apellidomat;
	private Date fechanacimiento;
	private Boolean estado;
	private String nombreusr;
	private String contrasenausr;
	private Date feccre;
	private String rutaimgusr;
	private Date fechacaducidad;
	private Date fecmod;
	private String usrcre;
	private String emailusr;
	private String usrmod;
	private String cargo;
	private Set<Perfilusuario> perfilusuarios = new HashSet<Perfilusuario>(0);
	private Set<Flujodocumento> flujodocumentos = new HashSet<Flujodocumento>(0);
	private Set<Auditoria> auditoriasForIdusuario = new HashSet<Auditoria>(0);
	private Set<Auditoria> auditoriasForIdusuariodestino = new HashSet<Auditoria>(
			0);

	public Usuario() {
	}

	public Usuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public Usuario(int idusuario, String nombres, String apellidopat,
			String apellidomat, Date fechanacimiento, Boolean estado,
			String nombreusr, String contrasenausr, Date feccre,
			String rutaimgusr, Date fechacaducidad, Date fecmod, String usrcre,
			String emailusr, String usrmod, String cargo,
			Set<Perfilusuario> perfilusuarios,
			Set<Flujodocumento> flujodocumentos,
			Set<Auditoria> auditoriasForIdusuario,
			Set<Auditoria> auditoriasForIdusuariodestino) {
		this.idusuario = idusuario;
		this.nombres = nombres;
		this.apellidopat = apellidopat;
		this.apellidomat = apellidomat;
		this.fechanacimiento = fechanacimiento;
		this.estado = estado;
		this.nombreusr = nombreusr;
		this.contrasenausr = contrasenausr;
		this.feccre = feccre;
		this.rutaimgusr = rutaimgusr;
		this.fechacaducidad = fechacaducidad;
		this.fecmod = fecmod;
		this.usrcre = usrcre;
		this.emailusr = emailusr;
		this.usrmod = usrmod;
		this.cargo = cargo;
		this.perfilusuarios = perfilusuarios;
		this.flujodocumentos = flujodocumentos;
		this.auditoriasForIdusuario = auditoriasForIdusuario;
		this.auditoriasForIdusuariodestino = auditoriasForIdusuariodestino;
	}

	@Id @GeneratedValue
	@Column(name = "IDUSUARIO", unique = true, nullable = false)
	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	@Column(name = "NOMBRES", length = 100)
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "APELLIDOPAT", length = 100)
	public String getApellidopat() {
		return this.apellidopat;
	}

	public void setApellidopat(String apellidopat) {
		this.apellidopat = apellidopat;
	}

	@Column(name = "APELLIDOMAT", length = 100)
	public String getApellidomat() {
		return this.apellidomat;
	}

	public void setApellidomat(String apellidomat) {
		this.apellidomat = apellidomat;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHANACIMIENTO", length = 23)
	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	@Column(name = "ESTADO")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Column(name = "NOMBREUSR", length = 50)
	public String getNombreusr() {
		return this.nombreusr;
	}

	public void setNombreusr(String nombreusr) {
		this.nombreusr = nombreusr;
	}

	@Column(name = "CONTRASENAUSR", length = 50)
	public String getContrasenausr() {
		return this.contrasenausr;
	}

	public void setContrasenausr(String contrasenausr) {
		this.contrasenausr = contrasenausr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECCRE", length = 23)
	public Date getFeccre() {
		return this.feccre;
	}

	public void setFeccre(Date feccre) {
		this.feccre = feccre;
	}

	@Column(name = "RUTAIMGUSR", length = 100)
	public String getRutaimgusr() {
		return this.rutaimgusr;
	}

	public void setRutaimgusr(String rutaimgusr) {
		this.rutaimgusr = rutaimgusr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHACADUCIDAD", length = 23)
	public Date getFechacaducidad() {
		return this.fechacaducidad;
	}

	public void setFechacaducidad(Date fechacaducidad) {
		this.fechacaducidad = fechacaducidad;
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

	@Column(name = "EMAILUSR", length = 100)
	public String getEmailusr() {
		return this.emailusr;
	}

	public void setEmailusr(String emailusr) {
		this.emailusr = emailusr;
	}

	@Column(name = "USRMOD", length = 50)
	public String getUsrmod() {
		return this.usrmod;
	}

	public void setUsrmod(String usrmod) {
		this.usrmod = usrmod;
	}

	@Column(name = "CARGO", length = 50)
	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Perfilusuario> getPerfilusuarios() {
		return this.perfilusuarios;
	}

	public void setPerfilusuarios(Set<Perfilusuario> perfilusuarios) {
		this.perfilusuarios = perfilusuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Flujodocumento> getFlujodocumentos() {
		return this.flujodocumentos;
	}

	public void setFlujodocumentos(Set<Flujodocumento> flujodocumentos) {
		this.flujodocumentos = flujodocumentos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Auditoria> getAuditoriasForIdusuario() {
		return this.auditoriasForIdusuario;
	}

	public void setAuditoriasForIdusuario(Set<Auditoria> auditoriasForIdusuario) {
		this.auditoriasForIdusuario = auditoriasForIdusuario;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariodestino")
	public Set<Auditoria> getAuditoriasForIdusuariodestino() {
		return this.auditoriasForIdusuariodestino;
	}

	public void setAuditoriasForIdusuariodestino(
			Set<Auditoria> auditoriasForIdusuariodestino) {
		this.auditoriasForIdusuariodestino = auditoriasForIdusuariodestino;
	}

}
