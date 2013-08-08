package com.sblm.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sblm.dao.IAuditoriaDAO;
import com.sblm.dao.IMonitoreoMesaPartesDAO;
import com.sblm.dao.INotificacionesDAO;
import com.sblm.dao.IUsuarioDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Pagina;
import com.sblm.model.Perfilusuario;
import com.sblm.model.Tipocambio;
import com.sblm.model.Usuario;
import com.sblm.modelMP.Documento;
import com.sblm.modelMP.Flujodoc;
import com.sblm.service.IAuditoriaService;
import com.sblm.service.IMonitoreoMesaPartesService;
import com.sblm.service.INotificacionesService;
import com.sblm.service.IUsuarioService;

@Transactional(readOnly = true)
@Service(value="panelDocumentoMesaPartesServiceImpl")
public class MonitoreoMesaPartesServiceImpl implements IMonitoreoMesaPartesService{

	@Autowired
	private IMonitoreoMesaPartesDAO monitoreoMesaPartesDAO;

	@Override
	public List<com.sblm.model.Documento> listarDocumentosRegistrados() {
		// TODO Auto-generated method stub
		return monitoreoMesaPartesDAO.listarDocumentosRegistrados();
	}

	@Override
	public Object countExternalDB() {
		// TODO Auto-generated method stub
		return monitoreoMesaPartesDAO.countExternalDB();
	}

	@Override
	public Object countInternalDB() {
		// TODO Auto-generated method stub
		return monitoreoMesaPartesDAO.countInternalDB();
	}

	@Override
	public List<Flujodoc> getNewInserts(int val) {
		// TODO Auto-generated method stub
		return monitoreoMesaPartesDAO.getNewInserts(val);
	}

	@Override
	public void save(com.sblm.model.Documento doc) {
		// TODO Auto-generated method stub
			
		monitoreoMesaPartesDAO.save(doc);
	}

	@Override
	public Object countPendientes() {
			
		return monitoreoMesaPartesDAO.countPendientes();

	}

	@Override
	public Object countAtendidos() {
		// TODO Auto-generated method stub
		return monitoreoMesaPartesDAO.countAtendidos();	}

	@Override
	public void actualizarEstadoToAtendido(int iddoc) {
		monitoreoMesaPartesDAO.actualizarEstadoToAtendido(iddoc);
		
	}

	@Override
	public List<com.sblm.model.Documento> listarDocumentosAtendidos() {
		// TODO Auto-generated method stub
		return monitoreoMesaPartesDAO.listarDocumentosAtendidos();
	}



}
