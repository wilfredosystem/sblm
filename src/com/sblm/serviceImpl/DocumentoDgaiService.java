package com.sblm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sblm.dao.IDocumentoDgaiDAO;
import com.sblm.model.Documento;
import com.sblm.model.Modulo;
import com.sblm.service.IDocumentoDgaiService;

@Transactional(readOnly = true)
@Service(value="documentodgaiService")
public class DocumentoDgaiService implements IDocumentoDgaiService {

	@Autowired
	private IDocumentoDgaiDAO documentodgaiDAO;

	@Transactional(readOnly = false)
	@Override
	public void registrarDocumentoDgai(Documento documento) {
		getDocumentodgaiDAO().registrarDocumentoDgai(documento);
		
	}
	@Transactional(readOnly = false)
	@Override
	public void actualizarDocumentoDgai(Documento documento) {
		// TODO Auto-generated method stub
		
	}
	@Transactional(readOnly = false)
	@Override
	public void eliminarDocumentoDgai(Documento documento) {
		getDocumentodgaiDAO().eliminarDocumentoDgai(documento);
	}

	@Override
	public Modulo listarDocumentoDgaiPorId(int id) {
		return getDocumentodgaiDAO().listarDocumentoDgaiPorId(id);
	}

	@Override
	public List<Documento> listarDocumentosDgai() {
		return getDocumentodgaiDAO().listarDocumentosDgai();
	}
	public IDocumentoDgaiDAO getDocumentodgaiDAO() {
		return documentodgaiDAO;
	}
	public void setDocumentodgaiDAO(IDocumentoDgaiDAO documentodgaiDAO) {
		this.documentodgaiDAO = documentodgaiDAO;
	}
	@Override
	public int totalPendientesDerivacion() {
		return getDocumentodgaiDAO().totalPendientesDerivacion();
	}
	@Override
	public Documento obtenerUltimodocumento() {
		return getDocumentodgaiDAO().obtenerUltimodocumento();
	}
	


}
