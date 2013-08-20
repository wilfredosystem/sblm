package com.sblm.service;

import java.util.List;

import com.sblm.model.Documento;
import com.sblm.model.Modulo;

public interface IDocumentoDgaiService {

	public void registrarDocumentoDgai(Documento documento);

	public void actualizarDocumentoDgai(Documento documento);

	public void eliminarDocumentoDgai(Documento documento);

	public Modulo listarDocumentoDgaiPorId(int id);

	public List<Documento> listarDocumentosDgai();
	
	public int totalPendientesDerivacion(); 
	
	public Documento obtenerUltimodocumento();
}
