package com.sblm.service;

import java.util.List;

import com.sblm.model.Flujodocumento;
import com.sblm.model.Modulo;

public interface IFlujoDocumentoService {

	public void registrarFlujoDocumento(Flujodocumento flujodocumento);

	public void actualizarFlujoDocumento(Flujodocumento flujodocumento);

	public void eliminarFlujoDocumento(Flujodocumento flujodocumento);

	public Modulo listarFlujoDocumentoPorId(int id);

	public List<Flujodocumento> listarFlujoDocumento();
	
	void actualizarRespuestaToAtendido(int iddoc);//recibido dgai
	public int obtenerNumeroDespachados();
	public int obtenerNumeroPendientes();
	public int obtenerNumeroRechazados();
}
