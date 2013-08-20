package com.sblm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import com.sblm.model.Documento;
import com.sblm.model.Tipodocumento;
import com.sblm.model.Usuario;
import com.sblm.modelMP.Flujodoc;
import com.sblm.service.IAuditoriaService;
import com.sblm.service.IMonitoreoMesaPartesService;
import com.sblm.util.Almanaque;
import com.sblm.util.CompDataModelDocumento;


@ManagedBean(name = "cmonitoreomesapartes")
@ViewScoped
public class monitoreoMesaPartesController implements Serializable {
	@ManagedProperty(value = "#{panelDocumentoMesaPartesServiceImpl}")
	private transient IMonitoreoMesaPartesService panelDocumentoMesaPartesServiceImpl;
	
	@ManagedProperty(value = "#{panelAuditoriaServiceImpl}")
	private transient IAuditoriaService panelAuditoriaServiceImpl;
	
	private static final long serialVersionUID = 5524190003746598593L;
	private List<Flujodoc> listFLujoDocumentosRegInit;
	private List<Documento> listDocumentosRegInit;
	private List<Documento> listDocumentosRegInitAtendido;
	private Documento[] selectRegistrosDocumentos;
	private Documento  selectRegistroDocumento;
	private Usuario  selectRegistroUsuario;
	private CompDataModelDocumento compDataModelDocumento;
	private Object nroExternal=0;
	private Object nroInternal=0;
	private String valCommandLink;
	private Boolean valDirectora=true;
	String nroPendiente;
	String nroAtendido;
	String nroRechazado;
	private String mesActual;
	private int iddocumento;
	private String descripcionDocumento;
	private List<Usuario> listaUsuarioSeleccionados;
	private List<Usuario> listUsuarios;
	private int indicesalvador;
	private String nombreCompletoSeleccionado;

	

	
	@PostConstruct
	public void initObjects(){
		listaUsuarioSeleccionados= new ArrayList<Usuario>();
		listaUsuarioSeleccionados.add(panelDocumentoMesaPartesServiceImpl.getDirectorDGAI());
		listaUsuarioSeleccionados.get(0).setEmailusr(listaUsuarioSeleccionados.get(0).getNombres()+" "+listaUsuarioSeleccionados.get(0).getApellidopat()+""+listaUsuarioSeleccionados.get(0).getApellidomat());
		listUsuarios = panelAuditoriaServiceImpl.listUsuariobyNom();
		System.out.println("intro postcontruct");
		Almanaque almanaque= new Almanaque();
		mesActual=almanaque.obtenerMesActual();
		actualizarBeneficenciadb();
		listarGrillaInit();
		valuePendiente();
		
	
	}


	public void listarGrillaInit() {
		
		listDocumentosRegInit = new ArrayList<Documento>();
		
		try {
			listDocumentosRegInit = panelDocumentoMesaPartesServiceImpl.listarDocumentosRegistrados();
			compDataModelDocumento = new CompDataModelDocumento(listDocumentosRegInit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarGrillaInitAtendido() {
		
		listDocumentosRegInitAtendido = new ArrayList<Documento>();
		
		try {
			listDocumentosRegInitAtendido = panelDocumentoMesaPartesServiceImpl.listarDocumentosAtendidos();
			compDataModelDocumento = new CompDataModelDocumento(listDocumentosRegInitAtendido);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void metodo(){
		listarGrillaInit();
	}
	

    public String mensajeEnvio(){  
    	System.out.println("intro method");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",  "Documentos enviados Satisfactoriamente");  
          
        FacesContext.getCurrentInstance().addMessage(null, message);  
        return null;
    } 
    
	
	public void valuePendiente(){
		
		Object pend=panelDocumentoMesaPartesServiceImpl.countPendientes();
		Object aten=panelDocumentoMesaPartesServiceImpl.countAtendidos();
		
		setNroPendiente(pend.toString());
		System.out.println("-------------------->"+getNroPendiente());
		setNroAtendido(aten.toString());
		setNroRechazado("0");
		
		
//		System.out.println("Pendientes:"+pend.toString()); 
//		System.out.println("Atendidos"+aten.toString());
	}
	
	public void actualizarEstado(){
		
		for(int i=0;i<selectRegistrosDocumentos.length;i++){
			panelDocumentoMesaPartesServiceImpl.actualizarEstadoToAtendido(selectRegistrosDocumentos[i].getIddocumento());
		}
		
		listarGrillaInit();
		selectRegistroDocumento=null;
		valuePendiente();
		
	}
	
	public void actualizarBeneficenciadb(){
		
		listFLujoDocumentosRegInit = new ArrayList<Flujodoc>();
		
		setNroExternal(panelDocumentoMesaPartesServiceImpl.countExternalDB());
		
		setNroInternal(panelDocumentoMesaPartesServiceImpl.countInternalDB());
		
		System.out.println("external" + getNroExternal());
		System.out.println("internal" + getNroInternal());
		
		if(getNroExternal().equals(getNroInternal())){
			System.out.println("insert succesfull");
		}else{
			System.out.println("insert en process");
			int val=Integer.parseInt(getNroExternal().toString())-Integer.parseInt(getNroInternal().toString());
			listFLujoDocumentosRegInit=panelDocumentoMesaPartesServiceImpl.getNewInserts(val);
			
			
//	for (Flujodoc f : listDocumentosRegInit) {
//		System.out.println("::::::::::::::::"+f.getDocumento().getIddoc());
//		
//	}
			
			try {
				
				for(int i=listFLujoDocumentosRegInit.size();i>0;i--){
//					System.out.println(i);
//					System.out.println(listFLujoDocumentosRegInit.get(i).getDocumento().getTitdoc());
					Documento doc= new Documento();
					Tipodocumento tipdoc= new Tipodocumento();
					
					tipdoc.setIdtipodoc(listFLujoDocumentosRegInit.get(i-1).getDocumento().getIdtipdoc());
					doc.setCodigodocumento(listFLujoDocumentosRegInit.get(i-1).getDocumento().getIddoc());
					doc.setTitulo(listFLujoDocumentosRegInit.get(i-1).getDocumento().getTitdoc());
					doc.setDescripcion(listFLujoDocumentosRegInit.get(i-1).getDocumento().getDesdoc());
					doc.setFechadocumento(listFLujoDocumentosRegInit.get(i-1).getDocumento().getFecdoc());
					doc.setAsunto(listFLujoDocumentosRegInit.get(i-1).getDocumento().getDesasn());
					doc.setNombreremitente(listFLujoDocumentosRegInit.get(i-1).getDocumento().getDesrmt());
					doc.setNumerofolio(listFLujoDocumentosRegInit.get(i-1).getNumfol());
					doc.setEstado("ninguno");
					doc.setRespuesta("en proceso");
					doc.setFecharegistro(new Date());
					doc.setTipodocumento(tipdoc);
					
					panelDocumentoMesaPartesServiceImpl.save(doc);
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}
	
	public void agregarUsuarioLista(){
		boolean usuarioVacio = false;
//		
//		if (!listaUsuarioSeleccionados.isEmpty()) {
//			for (Usuario Usu : listaUsuarioSeleccionados) {
//				if (Usu.getApellidopat().equals("")) {
//					usuarioVacio = true;
//					break;
//				}
//			}
//		}
		
		if (!usuarioVacio) {
		}
			Usuario Usu = new Usuario();
			Usu.setEmailusr("Escriba Nombre Usuario");
			Usu.setRutaimgusr("default.jpg");
			listaUsuarioSeleccionados.add(Usu);
			
		}
	
    public void onCellEdit(CellEditEvent event) {  
        Object oldValue = event.getOldValue();  
        Object newValue = event.getNewValue(); 
        
       // setNombreCompletoSeleccionado(newValue.toString());
        
        
//        if(newValue != null && !newValue.equals(oldValue)) {  
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);  
//            FacesContext.getCurrentInstance().addMessage(null, msg);  
//        }  
       listUsuarios = panelAuditoriaServiceImpl.listUsuariobyNom();
		for(int i=0;i<listaUsuarioSeleccionados.size();i++){
			
			if((listaUsuarioSeleccionados.get(i).getEmailusr()).equals(newValue)){
				setIndicesalvador(i);	

			}}
		
		
			int f=0;
			for(int k=0;k<listaUsuarioSeleccionados.size();k++){	
				

				if((listUsuarios.get(k).getNombres()+" "+listUsuarios.get(k).getApellidopat() +" "+listUsuarios.get(k).getApellidomat()).equals(newValue)){
					System.out.println("--->"+f);
					listaUsuarioSeleccionados.get(getIndicesalvador()).setRutaimgusr(listUsuarios.get(k).getRutaimgusr());
					listaUsuarioSeleccionados.get(getIndicesalvador()).setCargo(listUsuarios.get(k).getCargo());
				}
        
			}
        

    }

	
		public void eliminarUsuarioDeLista(ActionEvent event) {
			System.out.println("Id Elemento Seleccionado"+getSelectRegistroUsuario());
				try {
					if (getSelectRegistroUsuario() != null) {
						listaUsuarioSeleccionados.remove(getSelectRegistroUsuario());
						System.out.println("Eliminado");
					}
				} catch (Exception e) {
					e.getMessage();
				}
			}
		
		
	
	public void seleccionIdDocumento(){
		
		for(int i=0;i<listDocumentosRegInit.size();i++){
			if(listDocumentosRegInit.get(i).getIddocumento()==getIddocumento()){
				setDescripcionDocumento(listDocumentosRegInit.get(i).getDescripcion());
				System.out.println("Encontrado");
			}
		}
	}
	
	public void setearDescripcion(){
				setDescripcionDocumento(listDocumentosRegInit.get(0).getDescripcion());
	}
	

	public IMonitoreoMesaPartesService getPanelDocumentoMesaPartesServiceImpl() {
		return panelDocumentoMesaPartesServiceImpl;
	}


	public void setPanelDocumentoMesaPartesServiceImpl(
			IMonitoreoMesaPartesService panelDocumentoMesaPartesServiceImpl) {
		this.panelDocumentoMesaPartesServiceImpl = panelDocumentoMesaPartesServiceImpl;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Object getNroExternal() {
		return nroExternal;
	}


	public void setNroExternal(Object nroExternal) {
		this.nroExternal = nroExternal;
	}


	public Object getNroInternal() {
		return nroInternal;
	}


	public void setNroInternal(Object nroInternal) {
		this.nroInternal = nroInternal;
	}


	public List<Flujodoc> getListFLujoDocumentosRegInit() {
		return listFLujoDocumentosRegInit;
	}


	public void setListFLujoDocumentosRegInit(
			List<Flujodoc> listFLujoDocumentosRegInit) {
		this.listFLujoDocumentosRegInit = listFLujoDocumentosRegInit;
	}


	public List<Documento> getListDocumentosRegInit() {
		return listDocumentosRegInit;
	}


	public void setListDocumentosRegInit(List<Documento> listDocumentosRegInit) {
		this.listDocumentosRegInit = listDocumentosRegInit;
	}


	public Documento[] getSelectRegistrosDocumentos() {
		return selectRegistrosDocumentos;
	}


	public void setSelectRegistrosDocumentos(Documento[] selectRegistrosDocumentos) {
		this.selectRegistrosDocumentos = selectRegistrosDocumentos;
	}


	public Documento getSelectRegistroDocumento() {
		return selectRegistroDocumento;
	}


	public void setSelectRegistroDocumento(Documento selectRegistroDocumento) {
		this.selectRegistroDocumento = selectRegistroDocumento;
	}


	public CompDataModelDocumento getCompDataModelDocumento() {
		return compDataModelDocumento;
	}


	public void setCompDataModelDocumento(
			CompDataModelDocumento compDataModelDocumento) {
		this.compDataModelDocumento = compDataModelDocumento;
	}


	public List<Documento> getListDocumentosRegInitAtendido() {
		return listDocumentosRegInitAtendido;
	}


	public void setListDocumentosRegInitAtendido(
			List<Documento> listDocumentosRegInitAtendido) {
		this.listDocumentosRegInitAtendido = listDocumentosRegInitAtendido;
	}


	public String getValCommandLink() {
		return valCommandLink;
	}
	

	public Boolean getValDirectora() {
		return valDirectora;
	}


	public void setValDirectora(Boolean valDirectora) {
		this.valDirectora = valDirectora;
	}


	public void setValCommandLink(String valCommandLink) {
		this.valCommandLink = valCommandLink;
	}


	public String getNroPendiente() {
		return nroPendiente;
	}


	public void setNroPendiente(String nroPendiente) {
		this.nroPendiente = nroPendiente;
	}


	public String getNroAtendido() {
		return nroAtendido;
	}


	public void setNroAtendido(String nroAtendido) {
		this.nroAtendido = nroAtendido;
	}


	public String getNroRechazado() {
		return nroRechazado;
	}


	public void setNroRechazado(String nroRechazado) {
		this.nroRechazado = nroRechazado;
	}


	public String getMesActual() {
		return mesActual;
	}


	public void setMesActual(String mesActual) {
		this.mesActual = mesActual;
	}


	public int getIddocumento() {
		return iddocumento;
	}


	public void setIddocumento(int iddocumento) {
		this.iddocumento = iddocumento;
	}


	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}


	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}


	public List<Usuario> getListaUsuarioSeleccionados() {
		return listaUsuarioSeleccionados;
	}


	public void setListaUsuarioSeleccionados(List<Usuario> listaUsuarioSeleccionados) {
		this.listaUsuarioSeleccionados = listaUsuarioSeleccionados;
	}


	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}


	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}


	public IAuditoriaService getPanelAuditoriaServiceImpl() {
		return panelAuditoriaServiceImpl;
	}


	public void setPanelAuditoriaServiceImpl(
			IAuditoriaService panelAuditoriaServiceImpl) {
		this.panelAuditoriaServiceImpl = panelAuditoriaServiceImpl;
	}


	public int getIndicesalvador() {
		return indicesalvador;
	}


	public void setIndicesalvador(int indicesalvador) {
		this.indicesalvador = indicesalvador;
	}


	public String getNombreCompletoSeleccionado() {
		return nombreCompletoSeleccionado;
	}


	public void setNombreCompletoSeleccionado(String nombreCompletoSeleccionado) {
		this.nombreCompletoSeleccionado = nombreCompletoSeleccionado;
	}


	public Usuario getSelectRegistroUsuario() {
		return selectRegistroUsuario;
	}


	public void setSelectRegistroUsuario(Usuario selectRegistroUsuario) {
		this.selectRegistroUsuario = selectRegistroUsuario;
	}

	

	
}
