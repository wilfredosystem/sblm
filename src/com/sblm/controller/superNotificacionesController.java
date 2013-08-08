package com.sblm.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;


import com.sblm.model.Auditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Pagina;
import com.sblm.model.Perfilusuario;
import com.sblm.model.Tipocambio;
import com.sblm.model.Usuario;
import com.sblm.service.IAuditoriaService;
import com.sblm.service.INotificacionesService;
import com.sblm.service.IUsuarioService;
import com.sblm.util.Almanaque;
import com.sblm.util.CompDataModel;


@ManagedBean(name = "cnotificaciones")
@ViewScoped
public class superNotificacionesController implements Serializable {
	private static final long serialVersionUID = 5524190003746598593L;

	@ManagedProperty(value = "#{panelNotificacionesServiceImpl}")
	private transient INotificacionesService panelNotificacionesServiceImpl;
 
	private int contadorFecha;
	private String selectIdRegistroAuditoria="0";
	private String estadoNotificacion="0";
	private List<Auditoria> listNotificacionesPendientesInit;
	private Auditoria selectRegistroAuditoria;
	private CompDataModel compDataModel;
	private String nroRevisado;
	private String nroCancelado;
	private String nroPendiente;
	private String nroNotificacionesMes;
	private ArrayList<String> fechaSinFormato;
	private String mesActual;
	private String nroPendienteTotal;
	private List<Almanaque> listMeses;
	private List<String> listAnio;
	private String mesSeleccionado="";
	private String anioSeleccionado="";
	private Date instanteFecha;
	private String mensajedeNotificaciones;

	public superNotificacionesController(){
		listMeses= new ArrayList<Almanaque>();
		listAnio =new ArrayList<String>();
		
		
		 for(int i=0;i<12;i++){
			 Almanaque A= new Almanaque();
			 A.setIdmes(i);
			 A.setNombreMes(A.obtenerNombreMes(i));
			 listMeses.add(A);
			 listAnio.add(String.valueOf(2002+i));
		 }
	}
	
	
	@PostConstruct
	public void initObjects(){
		listarNotificacionesInit();
		setNroPendiente(countPendiente());
		setNroCancelado(countCancelado());
		setNroRevisado(countRevisado());
		setNroNotificacionesMes(countNroTotalMes());
		setNroPendienteTotal(countPendienteTotal());
		contadorFecha=0;
		Almanaque almanaque= new Almanaque();
		mesActual=almanaque.obtenerMesActual();
		
		instanteFecha=new Date();
		mensajeNotificacion();
	}
	




	@SuppressWarnings("unchecked")
	public void listarNotificacionesInit(){
		
		System.out.println(estadoNotificacion+" "+mesSeleccionado+" "+anioSeleccionado);
		
		if(estadoNotificacion.equals("0") || estadoNotificacion.equals("1")){
			
			listNotificacionesPendientesInit = new ArrayList<Auditoria>();
			
			try {
				listNotificacionesPendientesInit = panelNotificacionesServiceImpl.listarNotificaciones(1,getMesSeleccionado(),getAnioSeleccionado());
				
				compDataModel = new CompDataModel(listNotificacionesPendientesInit);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(estadoNotificacion.equals("3")){
			
					listNotificacionesPendientesInit = new ArrayList<Auditoria>();
					
					try {
						listNotificacionesPendientesInit = panelNotificacionesServiceImpl.listarNotificaciones(3,getMesSeleccionado(),getAnioSeleccionado());
						
						compDataModel = new CompDataModel(listNotificacionesPendientesInit);
						
					} catch (Exception e) {
						e.printStackTrace();
					}							}
				
		if(estadoNotificacion.equals("2")){
					
			System.out.println("---->2");
						listNotificacionesPendientesInit = new ArrayList<Auditoria>();
					
						try {
							listNotificacionesPendientesInit = panelNotificacionesServiceImpl.listarNotificaciones(2,getMesSeleccionado(),getAnioSeleccionado());
							
							compDataModel = new CompDataModel(listNotificacionesPendientesInit);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
			
					}
		//Settiamos el nuevo numero de pendientes
		setNroPendienteTotal(countPendienteTotal());
	System.out.println("----------------------->"+getNroPendienteTotal());
	}
	
	
	
	public void mensajeNotificacion(){
		System.out.println("entro metodo mensaje notificacion");

		if(getNroPendienteTotal()!=null){
			if(Integer.parseInt(getNroPendienteTotal())==1){
				setMensajedeNotificaciones("tiene una notificación pendiente");
			}
			else{
				if(Integer.parseInt(getNroPendienteTotal())>10 ){
					setMensajedeNotificaciones("tiene más de 10 notificaciones pendientes");
				}
					else {
					setMensajedeNotificaciones("tiene "+getNroPendienteTotal()+" notificaciones pendientes");
					}
			}
		}else{
			System.out.println("valor NroPendiente igual a null");
		}

		
		
	}
	
	
	public void actualizarPendiente(){
		
		panelNotificacionesServiceImpl.actualizarPendienteToRevisado(selectRegistroAuditoria.getIdauditoria());
		listarNotificacionesInit();
		actualizarValores();
		mensajeNotificacion();
		
		
	}
	
	public void actualizarCancelado(){
		
		panelNotificacionesServiceImpl.actualizarPendienteToCancelado(selectRegistroAuditoria.getIdauditoria());
		listarNotificacionesInit();
		actualizarValores();
		mensajeNotificacion();
	}
	
	public void actualizar(){
		
		panelNotificacionesServiceImpl.actualizarPendienteToRevisado(selectRegistroAuditoria.getIdauditoria());
		listarNotificacionesInit();
		actualizarValores();
		mensajeNotificacion();
	}
	
	public String countRevisado(){
		
		Object nroRevisado=panelNotificacionesServiceImpl.nroNotificacionesRevisado();
		
		return nroRevisado.toString();
	}
	
	public String countPendiente(){
		
		Object nroPendiente=panelNotificacionesServiceImpl.nroNotificacionesPendiente();
		
		return nroPendiente.toString();
	}
	
	public String countNroTotalMes(){
		
		Object nroDelMes=panelNotificacionesServiceImpl.nroNotificacionesDelMes();
		
		return nroDelMes.toString();
	}
	
	public String countCancelado(){
		
		Object nroCancelado=panelNotificacionesServiceImpl.nroNotificacionesCancelado();
		
		return nroCancelado.toString();
	}
	
	private String countPendienteTotal() {
		Object nroTotal=panelNotificacionesServiceImpl.nroNotificacionesTotal();
		
		return nroTotal.toString();
	}

	
	
	public void seleccionIdAuditoria() {
	

		try {
			setSelectIdRegistroAuditoria(((Integer.toString(selectRegistroAuditoria.getIdauditoria()))));
			System.out.println("Id "+getSelectIdRegistroAuditoria());
		} catch (Exception e) {
		}
				
	} 
	
	
	public void actualizarValores(){
		System.out.println("Actualiza valores");
		setNroPendiente(countPendiente());
		setNroCancelado(countCancelado());
		setNroRevisado(countRevisado());
		setNroNotificacionesMes(countNroTotalMes());
		setNroPendienteTotal(countPendienteTotal());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public INotificacionesService getPanelNotificacionesServiceImpl() {
		return panelNotificacionesServiceImpl;
	}

	public void setPanelNotificacionesServiceImpl(
			INotificacionesService panelNotificacionesServiceImpl) {
		this.panelNotificacionesServiceImpl = panelNotificacionesServiceImpl;
	}

	public List<Auditoria> getListNotificacionesPendientesInit() {
		return listNotificacionesPendientesInit;
	}

	public void setListNotificacionesPendientesInit(
			List<Auditoria> listNotificacionesPendientesInit) {
		this.listNotificacionesPendientesInit = listNotificacionesPendientesInit;
	}

	public String getSelectIdRegistroAuditoria() {
		return selectIdRegistroAuditoria;
	}

	public void setSelectIdRegistroAuditoria(String selectIdRegistroAuditoria) {
		this.selectIdRegistroAuditoria = selectIdRegistroAuditoria;
	}

	public Auditoria getSelectRegistroAuditoria() {
		return selectRegistroAuditoria;
	}

	public void setSelectRegistroAuditoria(Auditoria selectRegistroAuditoria) {
		
		this.selectRegistroAuditoria = selectRegistroAuditoria;

	}

	public CompDataModel getCompDataModel() {
		return compDataModel;
	}

	public void setCompDataModel(CompDataModel compDataModel) {
		this.compDataModel = compDataModel;
	}

	public String getEstadoNotificacion() {
		return estadoNotificacion;
	}

	public void setEstadoNotificacion(String estadoNotificacion) {
		this.estadoNotificacion = estadoNotificacion;
	}


		
	public int getContadorFecha() {
		return contadorFecha;
	}


	public void setContadorFecha(int contadorFecha) {
		this.contadorFecha = contadorFecha;
	}


	public ArrayList<String> getFechaSinFormato() {
		return fechaSinFormato;
	}


	public void setFechaSinFormato(ArrayList<String> fechaSinFormato) {
		this.fechaSinFormato = fechaSinFormato;
	}


	public String getNroPendiente() {
		return nroPendiente;
	}


	public void setNroPendiente(String nroPendiente) {
		this.nroPendiente = nroPendiente;
	}


	public String getNroRevisado() {
		return nroRevisado;
	}


	public void setNroRevisado(String nroRevisado) {
		this.nroRevisado = nroRevisado;
	}


	public String getNroCancelado() {
		return nroCancelado;
	}


	public void setNroCancelado(String nroCancelado) {
		this.nroCancelado = nroCancelado;
	}


	public String getNroNotificacionesMes() {
		return nroNotificacionesMes;
	}


	public void setNroNotificacionesMes(String nroNotificacionesMes) {
		this.nroNotificacionesMes = nroNotificacionesMes;
	}


	public String getMesActual() {
		return mesActual;
	}


	public void setMesActual(String mesActual) {
		this.mesActual = mesActual;
	}


	public String getNroPendienteTotal() {
		return nroPendienteTotal;
	}


	public void setNroPendienteTotal(String nroPendienteTotal) {
		this.nroPendienteTotal = nroPendienteTotal;
	}


	public List<Almanaque> getListMeses() {
		return listMeses;
	}


	public void setListMeses(List<Almanaque> listMeses) {
		this.listMeses = listMeses;
	}


	public String getMesSeleccionado() {
		return mesSeleccionado;
	}


	public void setMesSeleccionado(String mesSeleccionado) {
		this.mesSeleccionado = mesSeleccionado;
	}


	public List<String> getListAnio() {
		return listAnio;
	}


	public void setListAnio(List<String> listAnio) {
		this.listAnio = listAnio;
	}


	public String getAnioSeleccionado() {
		return anioSeleccionado;
	}


	public void setAnioSeleccionado(String anioSeleccionado) {
		this.anioSeleccionado = anioSeleccionado;
	}


	public Date getInstanteFecha() {
		return instanteFecha;
	}


	public void setInstanteFecha(Date instanteFecha) {
		this.instanteFecha = instanteFecha;
	}


	public String getMensajedeNotificaciones() {
		return mensajedeNotificaciones;
	}


	public void setMensajedeNotificaciones(String mensajedeNotificaciones) {
		this.mensajedeNotificaciones = mensajedeNotificaciones;
	}
	
	
	
}
