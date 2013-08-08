package com.sblm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sblm.dao.IPaginaDAO;
import com.sblm.model.Pagina;
import com.sblm.service.IPaginaService;
@Transactional(readOnly = true)
@Service(value="paginaService")

public class PaginaService implements IPaginaService{

	@Autowired
	private IPaginaDAO paginaDAO;
	
	@Transactional(readOnly = false)
	@Override
	public void registrarPagina(Pagina pagina) {
		getPaginaDAO().registrarPagina(pagina);
		
	}
	@Transactional(readOnly = false)
	@Override
	public void actualizarPagina(Pagina pagina) {
		// TODO Auto-generated method stub
		
	}
	@Transactional(readOnly = false)
	@Override
	public void eliminarPagina(Pagina pagina) {
		getPaginaDAO().eliminarPagina(pagina);
		
	}

	@Override
	public Pagina listarPaginaPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagina> listarPaginas() {
		return getPaginaDAO().listarPaginas();
	}

	@Override
	public int obtenerNumeroPaginas() {
		return getPaginaDAO().obtenerNumeroPaginas();
	}

	@Override
	public String obtenerUltimaPagina() {
		return getPaginaDAO().obtenerUltimaPagina();
	}

	public IPaginaDAO getPaginaDAO() {
		return paginaDAO;
	} 

	public void setPaginaDAO(IPaginaDAO paginaDAO) {
		this.paginaDAO = paginaDAO;
	}
	
	public List<Pagina> listarPaginasModulos(int idmodulo) {
		return getPaginaDAO().listarPaginasModulos(idmodulo);
	}

}
