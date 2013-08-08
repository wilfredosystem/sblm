package com.sblm.dao;

import java.util.List;

import com.sblm.model.Pagina;

public interface IPaginaDAO {

	public void registrarPagina(Pagina pagina);

	public void actualizarPagina(Pagina pagina);

	public void eliminarPagina(Pagina pagina);

	public Pagina listarPaginaPorId(int id);

	public List<Pagina> listarPaginas();
	public List<Pagina> listarPaginasModulos(int idmodulo);
	public int obtenerNumeroPaginas();
	public String obtenerUltimaPagina();
}
