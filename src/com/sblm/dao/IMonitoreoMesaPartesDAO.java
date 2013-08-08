package com.sblm.dao;

import java.util.List;

import com.sblm.modelMP.Documento;
import com.sblm.modelMP.Flujodoc;

public interface IMonitoreoMesaPartesDAO {

	List<com.sblm.model.Documento> listarDocumentosRegistrados();

	public Object countExternalDB();

	public Object countInternalDB();

	List<Flujodoc> getNewInserts(int val);

	void save(com.sblm.model.Documento doc);

	Object countPendientes();

	Object countAtendidos();

	void actualizarEstadoToAtendido(int iddoc);

	List<com.sblm.model.Documento> listarDocumentosAtendidos();



}
