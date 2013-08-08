package com.sblm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sblm.dao.IModuloDAO;
import com.sblm.dao.IPerfilDAO;
import com.sblm.model.Modulo;
import com.sblm.service.IModuloService;

@Transactional(readOnly = true)
@Service(value="moduloService")
public class ModuloService implements IModuloService {

	@Autowired
	private IModuloDAO moduloDAO;
	
	@Transactional(readOnly = false)
	@Override
	public void registrarModulo(Modulo modulo) {
		getModuloDAO().registrarModulo(modulo);
		
	}

	@Transactional(readOnly = false)
	@Override
	public void actualizarModulo(Modulo modulo) {
		// TODO Auto-generated method stub
		
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarModulo(Modulo modulo) {
		getModuloDAO().eliminarModulo(modulo);
		
	}

	@Override
	public Modulo listarModuloPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Modulo> listarModulos() {
		return getModuloDAO().listarModulos();
	}

	public IModuloDAO getModuloDAO() {
		return moduloDAO;
	}

	public void setModuloDAO(IModuloDAO moduloDAO) {
		this.moduloDAO = moduloDAO;
	}

	@Override
	public int obtenerNumeroModulos() {
		return getModuloDAO().obtenerNumeroModulos();
		
	}

	@Override
	public String obtenerUltimoModulo() {
		return getModuloDAO().obtenerUltimoModulo();
	}
	

}
