package com.sblm.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import com.sblm.model.Perfil;
import com.sblm.service.IPerfilService;

@ManagedBean(name = "perfilMB")
@RequestScoped
public class PerfilManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{perfilService}")
	private transient IPerfilService perfilService;

	private Perfil perfil;
	private List<Perfil> perfiles;

	public void onRowSelect(SelectEvent event) {
		
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::");

		
	}

	public void registrarPerfil() {
		System.out.println(":::::registrar  Perfil  MB:::::");
		
		getPerfilService().registrarPerfil(perfil);
		
	}

	public IPerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(IPerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public Perfil getPerfil() {
		if (perfil == null) {
			perfil = new Perfil();
		}
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfiles() {
		perfiles = getPerfilService().listarPerfiles();
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

}
