package com.sblm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.sblm.model.Modulo;
import com.sblm.model.Perfil;
import com.sblm.model.Perfilmodulo;
import com.sblm.model.Permiso;
import com.sblm.service.IModuloService;
import com.sblm.service.IPerfilModuloService;
import com.sblm.service.IPerfilService;
import com.sblm.util.PerfilModuloPermiso;

@ManagedBean(name = "perfilmoduloMB")
@ViewScoped
public class PerfilModuloManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{perfilmoduloService}")
	private transient IPerfilModuloService perfilmoduloService;

	@ManagedProperty(value = "#{moduloService}")
	private transient IModuloService moduloService;

	@ManagedProperty(value = "#{perfilService}")
	private transient IPerfilService perfilService;

	private Perfil perfil;
	private List<Perfil> perfiles;

	private Perfilmodulo perfilmodulo;
	private List<Perfilmodulo> perfilmodulos;
	// lsita para capturar valores de la tabla
	private Modulo modulo;
	private List<Modulo> modulos;

	private String valor;

	// para capturar el valor de estado de pagina perfil
	private boolean miestado;
	private List<Boolean> miestados;
	ArrayList<Boolean> estadosCapturados = new ArrayList<Boolean>();
	// /
	private int codestado;
	private int codpermiso;

	private boolean activoperfil = false;// para mostrar modulos a actualizar

	Map estadomodulo = new HashMap();
	Map permisomodulo = new HashMap();

	private boolean bandera = true;
	private boolean activo = false;
	private boolean actualizado = false;
	private boolean modifico = false;
	private List<PerfilModuloPermiso> listapmp;

	public void cambioBandera(SelectEvent event) {
		bandera = true;
		System.out.println("###banderita");
	}

	public void onRowSelect(SelectEvent event) {
		bandera = false;
		activo = true;
		actualizado = true;
		modifico = false;

		activoperfil = true;
		 estadomodulo.clear();
		 permisomodulo.clear();
		System.out
				.println("::::::::::::::::::::onRowSelect:::::::::::::::::::::::::");
		List<Perfilmodulo> listapermod = new ArrayList<Perfilmodulo>();
		listapermod = getPerfilmoduloService().listarPerfilModuloPorIdPerfil(
				perfil.getIdperfil());

		List<PerfilModuloPermiso> milista = new ArrayList<PerfilModuloPermiso>();

		for (Perfilmodulo perfilmodulo : listapermod) {
			miestado = perfilmodulo.getEstado();
			codpermiso = perfilmodulo.getPermiso().getIdpermiso();

			System.out.println("miestado:" + miestado);
			System.out.println("codpermiso :" + codpermiso);
			System.out.println("modulo :"
					+ perfilmodulo.getModulo().getNombremodulo());

			PerfilModuloPermiso pmp = new PerfilModuloPermiso();
			pmp.setEstado(miestado);
			pmp.setIdpermiso(codpermiso);
			pmp.setNombremodulo(perfilmodulo.getModulo().getNombremodulo());
			milista.add(pmp);

		}
		listapmp = milista;
		miestado = false;
		codpermiso = 0;
		modulos = null;
		modulo = null;
		System.out.println("#########3pasoooo onRowSelect final##########");

	}

	public void capturarEstado(int id) {
		estadomodulo.put(id, miestado);

		Iterator it = estadomodulo.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("SALIDA::::" + e.getKey() + " " + e.getValue());
		}

	}

	public void capturarPermiso(int id) {
		permisomodulo.put(id, codpermiso);
		Iterator it = permisomodulo.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("SALIDA::::" + e.getKey() + " " + e.getValue());
		}
	}

	public void registrarPerfilModulo() {

		if (!actualizado) {// REGISTRAR
			
			registrarPerfil();
			guardadoPerfilModulo();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se Registro el perfil   correctamente."); 
			
			FacesContext.getCurrentInstance().addMessage(null, msg);  

		} else {// ACTUALIZAR

			perfilmoduloService.eliminarPerfilModuloId(perfil.getIdperfil());
			registrarPerfil();
			guardadoPerfilModulo();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se Actualizo el perfil  correctamente."); 
			
			FacesContext.getCurrentInstance().addMessage(null, msg);  

		}

	}

	public void guardadoPerfilModulo() {

		List<Object> listaPermisoModulo = new ArrayList<Object>();
		List<List> listageneral = new ArrayList<List>();
		Map mapaObjetos = new HashMap();
		PerfilModuloPermiso permodper;

		PerfilModuloPermiso permodp = new PerfilModuloPermiso();
		// llenado de los valores capturados
		List<Modulo> listamod = new ArrayList<Modulo>();
		listamod = getModuloService().listarModulos();

		List<PerfilModuloPermiso> milista = new ArrayList<PerfilModuloPermiso>();

		Iterator itEstado = estadomodulo.entrySet().iterator();
		while (itEstado.hasNext()) {
			Map.Entry eEstado = (Map.Entry) itEstado.next();

			Iterator itPermiso = permisomodulo.entrySet().iterator();
			while (itPermiso.hasNext()) {
				Map.Entry ePermiso = (Map.Entry) itPermiso.next();

				if (eEstado.getKey().equals(ePermiso.getKey())) {
					permodper = new PerfilModuloPermiso();
					// convertimos object a string y luego a boolean
					permodper.setEstado(new Boolean(eEstado.getValue()
							.toString()));
					permodper.setIdmodulo(Integer.parseInt(eEstado.getKey()
							.toString()));
					permodper.setIdpermiso(Integer.parseInt(ePermiso.getValue()
							.toString()));
					mapaObjetos.put(eEstado.getKey(), permodper);

				}

			}
		}

		// seteado de valores capturados
		List<Integer> listacompleta = new ArrayList<Integer>();
		for (Modulo mdl : modulos) {
			listacompleta.add(mdl.getIdmodulo());
		}

		Perfilmodulo pf = new Perfilmodulo();
		Iterator itx = mapaObjetos.entrySet().iterator();
		while (itx.hasNext()) {
			Map.Entry e = (Map.Entry) itx.next();

			permodp = (PerfilModuloPermiso) e.getValue();

			List<Integer> listaexisten = new ArrayList<Integer>();
			for (Modulo mo : modulos) {
				if (mo.getIdmodulo() == permodp.getIdmodulo()) {
					listaexisten.add(mo.getIdmodulo());
				}

			}
			List<Modulo> modu = new ArrayList<Modulo>();
			modu = modulos;
			for (Modulo mo : modulos) {

				for (int n = 0; n < listaexisten.size(); n++) {
					if (listaexisten.get(n) == mo.getIdmodulo()) {
						listacompleta.remove(listaexisten.get(n));

					}
				}
			}

			Modulo mod = new Modulo();
			Perfil perf = new Perfil();
			Permiso permi = new Permiso();
			mod.setIdmodulo(permodp.getIdmodulo());
			if(!actualizado){
				perf.setIdperfil(getPerfilService().obtenerUltimoIdPerfil());
			}else{
				perf.setIdperfil(getPerfil().getIdperfil());
			}
			
			permi.setIdpermiso(permodp.getIdpermiso());

			pf.setModulo(mod);
			pf.setPerfil(perf);// corregir
			pf.setPermiso(permi);
			pf.setEstado(permodp.isEstado());
			// pf.setUsrcre(usrcre);
			getPerfilmoduloService().registrarPerfilModulo(pf);
		}
//		for (Integer idmodulito : listacompleta) {
//			Modulo mod = new Modulo();
//			Perfil perf = new Perfil();
//			Permiso permi = new Permiso();
//			mod.setIdmodulo(idmodulito);
//			perf.setIdperfil(getPerfilService().obtenerUltimoIdPerfil());
//			permi.setIdpermiso(1);
//
//			pf.setModulo(mod);
//			pf.setPerfil(perf);// corregir
//			pf.setPermiso(permi);
//			pf.setEstado(false);
//			getPerfilmoduloService().registrarPerfilModulo(pf);
//		}

	}

	// //////////////////

	public void registrarPerfil() {
		System.out.println(":::::registrar  Perfil  MB:::::");
		System.out.println("id perfil:" + perfil.getIdperfil());
		System.out.println("nombre perfil:" + perfil.getNombreperfil());
	
		getPerfilService().registrarPerfil(perfil);
		
	}

	public void limpiarCampos() {
		activoperfil = false;
		perfilmodulo = null;
		modulo = null;
		perfil = null;
		// modulos=null;
		// miestado = false;
		// codpermiso = 0;
		bandera = true;
		estadomodulo.clear();
		permisomodulo.clear();
	}

	public IPerfilModuloService getPerfilmoduloService() {
		return perfilmoduloService;
	}

	public void setPerfilmoduloService(IPerfilModuloService perfilmoduloService) {
		this.perfilmoduloService = perfilmoduloService;
	}

	public Perfilmodulo getPerfilmodulo() {
		if (perfilmodulo == null) {
			perfilmodulo = new Perfilmodulo();
		}
		return perfilmodulo;
	}

	public void setPerfilmodulo(Perfilmodulo perfilmodulo) {
		this.perfilmodulo = perfilmodulo;
	}

	public List<Perfilmodulo> getPerfilmodulos() {
		
		perfilmodulos = getPerfilmoduloService().listarPerfilmodulos();
		return perfilmodulos;
	}

	public void setPerfilmodulos(List<Perfilmodulo> perfilmodulos) {
		this.perfilmodulos = perfilmodulos;
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

	public List<Modulo> getModulos() {
		modulos = getModuloService().listarModulos();
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public IModuloService getModuloService() {
		return moduloService;
	}

	public void setModuloService(IModuloService moduloService) {
		this.moduloService = moduloService;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isMiestado() {
		return miestado;
	}

	public void setMiestado(boolean miestado) {
		this.miestado = miestado;
	}

	public List<Boolean> getMiestados() {
		return miestados;
	}

	public void setMiestados(List<Boolean> miestados) {
		this.miestados = miestados;
	}

	public ArrayList<Boolean> getEstadosCapturados() {
		return estadosCapturados;
	}

	public void setEstadosCapturados(ArrayList<Boolean> estadosCapturados) {
		this.estadosCapturados = estadosCapturados;
	}

	public int getCodpermiso() {
		return codpermiso;
	}

	public void setCodpermiso(int codpermiso) {
		this.codpermiso = codpermiso;
	}

	public int getCodestado() {
		return codestado;
	}

	public void setCodestado(int codestado) {
		this.codestado = codestado;
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

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public List<PerfilModuloPermiso> getListapmp() {

		return listapmp;

	}

	public void setListapmp(List<PerfilModuloPermiso> listapmp) {
		this.listapmp = listapmp;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isActualizado() {
		return actualizado;
	}

	public void setActualizado(boolean actualizado) {
		this.actualizado = actualizado;
	}

	public boolean isModifico() {
		return modifico;
	}

	public void setModifico(boolean modifico) {
		this.modifico = modifico;
	}

	public boolean isActivoperfil() {
		return activoperfil;
	}

	public void setActivoperfil(boolean activoperfil) {
		this.activoperfil = activoperfil;
	}

}
