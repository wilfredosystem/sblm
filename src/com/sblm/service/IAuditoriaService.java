package com.sblm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sblm.model.Auditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Pagina;
import com.sblm.model.Perfilusuario;
import com.sblm.model.Usuario;

public interface IAuditoriaService {

	public List<Auditoria> listarAuditoria();

	public List<Usuario> listUsuariobyNom();

	public List<Perfilusuario> listPerfilbyNom();

	public List<Modulo> listModulobyNom();

	public List listAuditoriaFiltro(Date fechaInicio, Date fechaFin,
			String nombreUsuario, String nomPantalla, String nomPerfil,
			String nomModulo);

	public List<Pagina> listRecursobyNom();

	public String nroConectadosDelDia();

	public Usuario listUsuarioTop();

//	public List<Usuario> listUserTop(String string);

	public List listAuditoriaFiltroPerfil(Date fechaInicio,
			Date fechaFin, String nombrePerfil, String recursoBusqueda,
			String nomPerfil, String moduloBusqueda);
	

}
