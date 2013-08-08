package com.sblm.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.sblm.model.Modulo;
import com.sblm.service.IModuloService;

@ManagedBean(name = "moduloMB")
@ViewScoped
public class ModuloManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{moduloService}")
	private transient IModuloService moduloService;
	private Modulo modulo;
	private List<Modulo> modulos;
	private Modulo objetomodulo;

	private int numModulos;
	private String ultimoModulo;
	private Modulo modu;
	
	@ManagedProperty(value = "#{usuarioMB}")
	UsuarioManagedBean userMB;
	
	 public void onEdit(RowEditEvent event) {  
	       System.out.println("paso edicion dddddddddddddddddd");
	     event.getObject().getClass().getName();
	       final Modulo mod=(Modulo) event.getObject();
	      // final Modulo modu= event.getClass();  
	       System.out.println("modulo:"+modulo.getNombremodulo());
	       System.out.println("idmodulo:"+mod.getIdmodulo());
	       mod.setNombremodulo(modulo.getNombremodulo());
	       getModuloService().registrarModulo(mod);
	       
//	       DataTable s = (DataTable) event.getSource();
//	       System.out.println("vaorr###:::"+s.getClientId(FacesContext.getCurrentInstance()));
//	       System.out.println("vaorr###:::"+event.getRowIndex() );
	       
	      // RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance() + ":" + event.getRowIndex() + ":isAutomatic");
	       
	       Object o = event.getObject();
	       if (o != null) {
	    	   Modulo d = (Modulo)o;
	    	   Modulo d1 = modulos.get(1);
	    	   System.out.println("hhhhhhhhhhhhhhh:::"+d1.getNombremodulo());
	    	   System.out.println("hhhhhhhhhhhhhhh:::"+d.getNombremodulo());
	       }
	       
	  	
	    } 
	 

	public void onRowSelect(SelectEvent event) {
		
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::");
		objetomodulo = modulo;
		
	}

	public void limpiarCampos() {
		modulo = null;
	}

	public void eliminarModulo() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se elimino el modulo "+modulo.getNombremodulo()+ " Correctamente."); 
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		
		
		getModuloService().eliminarModulo(modulo);
		numModulos = getModuloService().obtenerNumeroModulos();
		ultimoModulo = getModuloService().obtenerUltimoModulo();
		getUserMB().obtenerMenu();
		limpiarCampos();
	}

	public void registrarModulo() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se creó el modulo "+modulo.getNombremodulo()+ " Correctamente."); 
		getModuloService().registrarModulo(modulo);
		ultimoModulo = getModuloService().obtenerUltimoModulo();//lanzamos llamdo de nuemro modulos
		numModulos = getModuloService().obtenerNumeroModulos();
		
		getUserMB().obtenerMenu();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@PostConstruct
	public void initObjects() {

		try {
			numModulos = getModuloService().obtenerNumeroModulos();
			ultimoModulo = getModuloService().obtenerUltimoModulo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Modulo getModulo() {
		if (modulo == null) {
			modulo = new Modulo();
		}
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public IModuloService getModuloService() {
		return moduloService;
	}

	public void setModuloService(IModuloService moduloService) {
		this.moduloService = moduloService;
	}

	public List<Modulo> getModulos() {
		modulos = getModuloService().listarModulos();
		return modulos;
	}

	public Modulo getObjetomodulo() {
		if (objetomodulo == null) {
			objetomodulo = new Modulo();
		}

		return objetomodulo;
	}

	public void setObjetomodulo(Modulo objetomodulo) {
		this.objetomodulo = objetomodulo;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public int getNumModulos() {
		return numModulos;
	}

	public void setNumModulos(int numModulos) {
		this.numModulos = numModulos;
	}

	public String getUltimoModulo() {
		return ultimoModulo;
	}

	public void setUltimoModulo(String ultimoModulo) {
		this.ultimoModulo = ultimoModulo;
	}


	public Modulo getModu() {
		return modu;
	}


	public void setModu(Modulo modu) {
		this.modu = modu;
	}


	public UsuarioManagedBean getUserMB() {
		return userMB;
	}


	public void setUserMB(UsuarioManagedBean userMB) {
		this.userMB = userMB;
	}

}
