package com.sblm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sblm.dao.IFlujoDocumentoDAO;
import com.sblm.model.Flujodocumento;
import com.sblm.model.Modulo;
import com.sblm.service.IFlujoDocumentoService;

@Transactional(readOnly = true)
@Service(value="flujodocumentoService")
public class FlujoDocumentoService implements IFlujoDocumentoService{

	@Autowired
	private IFlujoDocumentoDAO flujodocumentoDAO;

	@Transactional(readOnly = false)
	@Override
	public void registrarFlujoDocumento(Flujodocumento flujodocumento) {
		getFlujodocumentoDAO().registrarFlujoDocumento(flujodocumento);
		
	}
	@Transactional(readOnly = false)
	@Override
	public void actualizarFlujoDocumento(Flujodocumento flujodocumento) {
		// TODO Auto-generated method stub
		
	}
	@Transactional(readOnly = false)
	@Override
	public void eliminarFlujoDocumento(Flujodocumento flujodocumento) {
		getFlujodocumentoDAO().eliminarFlujoDocumento(flujodocumento);
		
	}

	@Override
	public Modulo listarFlujoDocumentoPorId(int id) {
		return getFlujodocumentoDAO().listarFlujoDocumentoPorId(id);
	}

	@Override
	public List<Flujodocumento> listarFlujoDocumento() {
		return getFlujodocumentoDAO().listarFlujoDocumento();
	}

	@Override
	public void actualizarRespuestaToAtendido(int iddoc) {
		getFlujodocumentoDAO().actualizarRespuestaToAtendido(iddoc);
		
	}

	public IFlujoDocumentoDAO getFlujodocumentoDAO() {
		return flujodocumentoDAO;
	}

	public void setFlujodocumentoDAO(IFlujoDocumentoDAO flujodocumentoDAO) {
		this.flujodocumentoDAO = flujodocumentoDAO;
	}
	@Override
	public int obtenerNumeroDespachados() {
		return getFlujodocumentoDAO().obtenerNumeroDespachados();
	}
	@Override
	public int obtenerNumeroPendientes() {
		return getFlujodocumentoDAO().obtenerNumeroPendientes();
	}
	@Override
	public int obtenerNumeroRechazados() {
		return getFlujodocumentoDAO().obtenerNumeroRechazados();
	}

}
