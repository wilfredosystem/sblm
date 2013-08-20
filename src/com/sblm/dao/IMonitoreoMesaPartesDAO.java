package com.sblm.dao;

import java.util.List;

import com.sblm.model.Usuario;
import com.sblm.modelMP.Documento;
import com.sblm.modelMP.Flujodoc;

public interface IMonitoreoMesaPartesDAO {

	public List<com.sblm.model.Documento> listarDocumentosRegistrados();

	public Object countExternalDB();

	public Object countInternalDB();

	public List<Flujodoc> getNewInserts(int val);

	public void save(com.sblm.model.Documento doc);

	public Object countPendientes();

	public Object countAtendidos();

	public void actualizarEstadoToAtendido(int iddoc);

	public List<com.sblm.model.Documento> listarDocumentosAtendidos();

	public Usuario getDirectorDGAI();



}
