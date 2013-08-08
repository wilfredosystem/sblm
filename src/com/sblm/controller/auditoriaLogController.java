package com.sblm.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.sblm.model.Auditoria;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Usuario;
import com.sblm.service.ILogService;

@ManagedBean(name = "cauditoriaLogController")
@ViewScoped
public class auditoriaLogController implements Serializable {

	private static final long serialVersionUID = -4606347305303565152L;
	@ManagedProperty(value = "#{panelLogServiceImpl}")
	private transient ILogService panelLogServiceImpl;
	
	
	
	
	public  void settingLog(int idusuario, int idmodulo, int idestadoauditoria, int ideventoauditoria, Date fechaentrada, String nombrepantalla,
			String url,Boolean estado, int codauditoria){


				Auditoria Adt= new Auditoria();
				
				Usuario usr= new Usuario();
				usr.setIdusuario(idusuario);
				
				Modulo mod= new Modulo();
				mod.setIdmodulo(idmodulo);
				
				Estadoauditoria esa= new Estadoauditoria();
				esa.setIdestadoauditoria(idestadoauditoria);
				
				Eventoauditoria eva= new Eventoauditoria();
				eva.setIdeventoauditoria(ideventoauditoria);
				
				Adt.setUsuario(usr);
				Adt.setModulo(mod);
				Adt.setEstadoauditoria(esa);
				Adt.setEventoauditoria(eva);
				
				Adt.setFecentrada(fechaentrada);
				Adt.setNompantalla(nombrepantalla);
				Adt.setUrl(url);
				Adt.setEstado(estado);
				Adt.setCodauditoria(codauditoria);
				
				
				Auditoria A = new Auditoria();
				A.setFecentrada(new Date());
				
				if(A.equals(null)){
					System.out.println("nulo");
				}else{System.out.println("no nulo");}
				
				System.out.println(Adt.getEstadoauditoria().getIdestadoauditoria()+" ------------------------- "+Adt.getUsuario().getIdusuario());
				try {
					
					getPanelLogServiceImpl().save("sss");

				} catch (Exception e) {
					System.out.println("hello world:::::"+e.getMessage());
				}
				
	
	}



	public ILogService getPanelLogServiceImpl() {
		return panelLogServiceImpl;
	}



	public void setPanelLogServiceImpl(ILogService panelLogServiceImpl) {
		this.panelLogServiceImpl = panelLogServiceImpl;
	}
 


}
