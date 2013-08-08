package com.sblm.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.column.Column;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.sblm.model.Modulo;
import com.sblm.model.Pagina;
import com.sblm.service.IModuloService;
import com.sblm.service.IPaginaService;

@ManagedBean(name = "paginaMB")
@ViewScoped
public class PaginaManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{paginaService}")
	private transient IPaginaService paginaService;
	
	@ManagedProperty(value = "#{moduloService}")
	private transient IModuloService moduloService;
	private MenuModel model; 
	@ManagedProperty(value = "#{usuarioMB}")
	UsuarioManagedBean userMB;
	
	private Pagina pagina;
	private List<Pagina>paginas;
	private String ultimoPagina;
	 
	
	 public PaginaManagedBean() {  
	       
	    }  
		@PostConstruct
		public void initObjects() {

			try {
				
				ultimoPagina = getPaginaService().obtenerUltimaPagina();
				
				 model = new DefaultMenuModel();  
		          
//			      List <Modulo> lstmodulo=  getModuloService().listarModulos();
//			      
//			         for (Modulo modu : lstmodulo) {
//			        	 Submenu submenup = new Submenu();  
//			        	 submenup.setLabel(modu.getNombremodulo());  //menus
//			        	 submenup.setRendered(new Boolean(modu.getEstado() ) );
//			        	 Column colum =new Column();
//			        	 Submenu submenu = new Submenu();  
//						System.out.println("modulo:::"+modu.getNombremodulo());
//						 List <Pagina> lstpagina=  getPaginaService().listarPaginasModulos(modu.getIdmodulo());
//				         for (Pagina pag : lstpagina) {
//				        	  MenuItem  item = new MenuItem();  //items
//				        	    item.setValue(pag.getNombrepagina());  
//						        item.setUrl(pag.getNombrepagina()+".jsf");  
//						        submenu.getChildren().add(item);
//				        	 System.out.println("pagina:::"+pag.getNombrepagina());
//						}
//				         colum.getChildren().add(submenu);
//					        submenup.getChildren().add(colum); 
//					        model.addSubmenu(submenup);  
//					}
			        
			        //First submenu  
//			        Submenu submenup = new Submenu();  
//			        submenup.setLabel("Administracion");  
//			        
//			        Column colum =new Column();
//			        Submenu submenu = new Submenu();  
//			        
//			        MenuItem  item = new MenuItem();  //items
//			        item.setValue("auditoria");  
//			        item.setUrl("#");  
//			        submenu.getChildren().add(item);  
//			        
//			        
//			        colum.getChildren().add(submenu);
//			        submenup.getChildren().add(colum);  
//			          
//			        model.addSubmenu(submenup);  
//			          
//			        //Second submenu  
//			        submenu = new Submenu();  
//			        submenu.setLabel("Mantenimiento");  
//			          
//			        item = new MenuItem();  
//			        item.setValue("TipoCmabio");  
//			        item.setUrl("#");  
//			        submenu.getChildren().add(item);  
//			          
//			        item = new MenuItem();  
//			        item.setValue("TipoCmabio 2");  
//			        item.setUrl("#");  
//			        submenu.getChildren().add(item);  
//			          
//			        model.addSubmenu(submenu);  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	    public MenuModel getModel() {  
	        return model;  
	    }     
	    

	
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Id :" + pagina.getIdpagina());
		
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void registrarPagina(){
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se creó la  pagina "+pagina.getNombrepagina()+ " Correctamente."); 
		getPaginaService().registrarPagina(pagina);
		
		getUserMB().obtenerMenu();
		FacesContext.getCurrentInstance().addMessage(null, msg);  
		
		
		//limpiarCampos();
	}
	public void eliminarPagina() {
		
		getPaginaService().eliminarPagina(pagina);
		getUserMB().obtenerMenu();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Error","Se eliminó la pagina " +pagina.getNombrepagina()+ " correctamente."); 
		
		FacesContext.getCurrentInstance().addMessage(null, msg);  
		limpiarCampos();
	}
	public void limpiarCampos() {
		pagina = null;
	}
	public IPaginaService getPaginaService() {
		return paginaService;
	}
	public void setPaginaService(IPaginaService paginaService) {
		this.paginaService = paginaService;
	}
	public Pagina getPagina() {
		if (pagina == null) {
			pagina = new Pagina	();
		}
		return pagina;
	}
	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}
	public List<Pagina> getPaginas() {
		paginas=getPaginaService().listarPaginas();
		return paginas;
	}
	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}
	public String getUltimoPagina() {
		return ultimoPagina;
	}
	public void setUltimoPagina(String ultimoPagina) {
		this.ultimoPagina = ultimoPagina;
	}

	public IModuloService getModuloService() {
		return moduloService;
	}

	public void setModuloService(IModuloService moduloService) {
		this.moduloService = moduloService;
	}
	public UsuarioManagedBean getUserMB() {
		return userMB;
	}
	public void setUserMB(UsuarioManagedBean userMB) {
		this.userMB = userMB;
	}
	
	
	
}
