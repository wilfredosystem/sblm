package com.sblm.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sblm.model.Documento;
import com.sblm.service.IDocumentoDgaiService;

@ManagedBean(name = "documentodgaiMB")
@ViewScoped
public class DocumentoDgaiManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{documentodgaiService}")
	private transient IDocumentoDgaiService documentodgaiService;
	private Documento documento;
	private List<Documento> documentos;
	
	private Documento documentoseleccionado;
	private List<Documento> documentoscapturados;
	
	private Documento[] listadocumentos;
	private Documento documentocapturado;
	int totalpendientes;
	
	private Documento[] listadocumentosrechazados;
	@PostConstruct
	public void initObjects(){
		totalPendientesDerivacion();
	}
	public void pasarParametros(){
		//documentoscapturados=documentos;
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		 documentocapturado= (Documento) params.get("nombreParametro"); 
	}
	
	
	 public void actualizarDocumentoCapturado() { 
		 
		getDocumentocapturado();
		System.out.println("documentocapturado::"+documentocapturado.getIddocumento());
	 }
	 
	 public void totalPendientesDerivacion() { 
		 
		 totalpendientes= getDocumentodgaiService().totalPendientesDerivacion();
	 }
	 
	public Documento getDocumento() {
		if (documento == null) {
			documento = new Documento();
		}

		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public List<Documento> getDocumentos() {
		documentos = getDocumentodgaiService().listarDocumentosDgai();
		return documentos;
	}
	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	public IDocumentoDgaiService getDocumentodgaiService() {
		return documentodgaiService;
	}
	public void setDocumentodgaiService(IDocumentoDgaiService documentodgaiService) {
		this.documentodgaiService = documentodgaiService;
	}
	public int getTotalpendientes() {
		return totalpendientes;
	}
	public void setTotalpendientes(int totalpendientes) {
		this.totalpendientes = totalpendientes;
	}
	public Documento getDocumentoseleccionado() {
		return documentoseleccionado;
	}
	public void setDocumentoseleccionado(Documento documentoseleccionado) {
		this.documentoseleccionado = documentoseleccionado;
	}
	public List<Documento> getDocumentoscapturados() {
		return documentoscapturados;
	}
	public void setDocumentoscapturados(List<Documento> documentoscapturados) {
		this.documentoscapturados = documentoscapturados;
	}
	public Documento[] getListadocumentos() {
		return listadocumentos;
	}
	public void setListadocumentos(Documento[] listadocumentos) {
		this.listadocumentos = listadocumentos;
	}
	public Documento getDocumentocapturado() {
		return documentocapturado;
	}
	public void setDocumentocapturado(Documento documentocapturado) {
		this.documentocapturado = documentocapturado;
	}
	public Documento[] getListadocumentosrechazados() {
		return listadocumentosrechazados;
	}
	public void setListadocumentosrechazados(Documento[] listadocumentosrechazados) {
		this.listadocumentosrechazados = listadocumentosrechazados;
	}

	
	
	
}
