package com.sblm.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sblm.model.Area;
import com.sblm.model.Documento;
import com.sblm.model.Flujodocumento;
import com.sblm.model.Perfilusuario;
import com.sblm.model.Usuario;
import com.sblm.service.IFlujoDocumentoService;
import com.sblm.service.IPerfilUsuarioService;
import com.sblm.util.Correo;

@ManagedBean(name = "flujodocumentoMB")
@ViewScoped
public class FlujoDocumentoManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{flujodocumentoService}")
	private transient IFlujoDocumentoService flujodocumentoService;
	private Flujodocumento flujodocumento;
	private List<Flujodocumento> flujodocumentos;
	private Documento documentoenviado;
	
	private Perfilusuario perfilusuarioenviado;
	@ManagedProperty(value = "#{usuarioMB}")
	UsuarioManagedBean userMB;
	
	@ManagedProperty(value = "#{documentodgaiMB}")
	DocumentoDgaiManagedBean documentodgaiMB;
	
	@ManagedProperty(value = "#{perfilusuarioService}")
	private transient IPerfilUsuarioService perfilusuarioService;
	
//	@ManagedProperty(value = "#{cmonitoreomesapartes}")
//	 monitoreoMesaPartesController cmonitoreomesapartes;
	Usuario []listadousuarios;
	int numeroDespachados;
	int numeroPendientes;
	int numeroRechazados;
//	private String ultimoAsunto;
	private Flujodocumento[] listadocumentosrechazados;
	private Flujodocumento documentocapturado;
	private Date fechaInicio;
	private Date fechaFin;
	
	private List<Usuario> usuariosdgi;
	
	@PostConstruct
	public void initObjects(){
		numeroDespachados=getFlujodocumentoService().obtenerNumeroDespachados();
		numeroPendientes=getFlujodocumentoService().obtenerNumeroPendientes();
		numeroRechazados=getFlujodocumentoService().obtenerNumeroRechazados();
	
		
//		ultimoAsunto= getFlujodocumentoService().obtenerUltimodocumento().getDescripcion();
	}
	
	public FlujoDocumentoManagedBean() {
		
		
	}

	public void notificarRecahazo(){
		

	}
	

	public void registrarFlujoDocumento(ActionEvent event) {
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se derivo El expediente correctamente!!!"); 
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
//		Perfilusuario perfusua=new Perfilusuario();
//		int idusuario =userMB.getUsuariologueado().getIdusuario();
//		perfusua=getPerfilusuarioService().listarPerfilUsuarioPorId(idusuario);
		
		Documento[] listadocume=documentodgaiMB.getListadocumentos();
		
	
		System.out.println("##########################");
		for (Documento documento : listadocume) {
			//envia notif. recib. a mesa partes
			getFlujodocumentoService().actualizarRespuestaToAtendido(documento.getIddocumento());
			for (Usuario user : listadousuarios) {
//				System.out.println("nombre user::::"+user.getNombres());
//				System.out.println("email user::::"+user.getContrasenausr());
			
				Correo correo =new Correo();
				String msj="Sr(a). "+user.getNombres()+" "+user.getApellidopat()+"Ud. tiene notificaciones pendientes";
				correo.enviarCorreo(user.getEmailusr(), "Notificacion de Expediente",msj);
			  Area are=new Area();
		      are.setIdarea(1);
		      Flujodocumento fludoc=new Flujodocumento();
		      fludoc.setUsuario(user);
		      fludoc.setArea(are);
		      fludoc.setDocumento(documento);
			getFlujodocumentoService().registrarFlujoDocumento(fludoc);
		}
		}

		
		numeroDespachados=getFlujodocumentoService().obtenerNumeroDespachados();
		numeroPendientes=getFlujodocumentoService().obtenerNumeroPendientes();
		numeroRechazados=getFlujodocumentoService().obtenerNumeroRechazados();
		
	//	System.out.println("perfusua zzzzzzzzzzz:::"+perfusua.getPerfil());
	    
//		FacesMessage msg = new FacesMessage("Se envió el documento "+flujodocumento.getNumero()+ " Correctamente."); 
//		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
//public void enviarCorreo() {
//		
//		for (Usuario user : listadousuarios) {
//			System.out.println("nombre user::::"+user.getNombres());
//			System.out.println("email user::::"+user.getContrasenausr());
//				Correo correo =new Correo();
//				String msj="Sr(a). "+user.getNombres()+" "+user.getApellidopat()+"Ud. tiene notificaciones pendientes";
//				correo.enviarCorreo(user.getEmailusr(), "Notificacion de Expediente",msj);
//			 
//		}
//		
//}

	
	public IFlujoDocumentoService getFlujodocumentoService() {
		return flujodocumentoService;
	}

	public void setFlujodocumentoService(
			IFlujoDocumentoService flujodocumentoService) {
		this.flujodocumentoService = flujodocumentoService;
	}

	public Flujodocumento getFlujodocumento() {
		if (flujodocumento == null) {
			flujodocumento = new Flujodocumento();
		}
		return flujodocumento;
	}

	public void setFlujodocumento(Flujodocumento flujodocumento) {
		this.flujodocumento = flujodocumento;
	}

	public List<Flujodocumento> getFlujodocumentos() {
		flujodocumentos = getFlujodocumentoService().listarFlujoDocumento();
		return flujodocumentos;
	}

	public void setFlujodocumentos(List<Flujodocumento> flujodocumentos) {
		this.flujodocumentos = flujodocumentos;
	}

	public Documento getDocumentoenviado() {
		return documentoenviado;
	}

	public void setDocumentoenviado(Documento documentoenviado) {
		this.documentoenviado = documentoenviado;
	}

	public UsuarioManagedBean getUserMB() {
		return userMB;
	}

	public void setUserMB(UsuarioManagedBean userMB) {
		this.userMB = userMB;
	}

	public IPerfilUsuarioService getPerfilusuarioService() {
		return perfilusuarioService;
	}

	public void setPerfilusuarioService(IPerfilUsuarioService perfilusuarioService) {
		this.perfilusuarioService = perfilusuarioService;
	}

	

	public void setDocumentodgaiMB(DocumentoDgaiManagedBean documentodgaiMB) {
		this.documentodgaiMB = documentodgaiMB;
	}

	public DocumentoDgaiManagedBean getDocumentodgaiMB() {
		return documentodgaiMB;
	}

	public Perfilusuario getPerfilusuarioenviado() {
		return perfilusuarioenviado;
	}

	public void setPerfilusuarioenviado(Perfilusuario perfilusuarioenviado) {
		this.perfilusuarioenviado = perfilusuarioenviado;
	}


	public Usuario[] getListadousuarios() {
		return listadousuarios;
	}

	public void setListadousuarios(Usuario[] listadousuarios) {
		this.listadousuarios = listadousuarios;
	}

	public int getNumeroPendientes() {
		return this.numeroPendientes;
	}

	public void setNumeroPendientes(int numeroPendientes) {
		this.numeroPendientes = numeroPendientes;
	}

	public int getNumeroRechazados() {
		return this.numeroRechazados ;
	}

	public void setNumeroRechazados(int numeroRechazados) {
		this.numeroRechazados = numeroRechazados;
	}
	public int getNumeroDespachados() {
		
		return this.numeroDespachados ;
	}

	public void setNumeroDespachados(int numeroDespachados) {
		this.numeroDespachados = numeroDespachados;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Flujodocumento[] getListadocumentosrechazados() {
		return listadocumentosrechazados;
	}
	public void setListadocumentosrechazados(
			Flujodocumento[] listadocumentosrechazados) {
		this.listadocumentosrechazados = listadocumentosrechazados;
	}
	public Flujodocumento getDocumentocapturado() {
		return documentocapturado;
	}
	public void setDocumentocapturado(Flujodocumento documentocapturado) {
		this.documentocapturado = documentocapturado;
	}

//	public String getUltimoAsunto() {
//		return ultimoAsunto;
//	}
//
//	public void setUltimoAsunto(String ultimoAsunto) {
//		this.ultimoAsunto = ultimoAsunto;
//	}
//	
//
//	
}
