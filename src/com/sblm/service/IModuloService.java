package com.sblm.service;

import java.util.List;

import com.sblm.model.Modulo;

public interface IModuloService {
	public void registrarModulo(Modulo modulo);

	public void actualizarModulo(Modulo modulo);

	public void eliminarModulo(Modulo modulo);

	public Modulo listarModuloPorId(int id);

	public List<Modulo> listarModulos();
	
	public int obtenerNumeroModulos();
	public String obtenerUltimoModulo();

}
