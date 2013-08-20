package com.sblm.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sblm.dao.IModuloDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Pagina;
import com.sblm.model.Paginamodulo;
import com.sblm.model.Perfil;
import com.sblm.model.Perfilmodulo;
import com.sblm.model.Usuario;
import com.sblm.util.FuncionesHelper;
import com.sblm.util.PerfilModuloPermiso;

@Repository(value = "moduloDAO")
public class ModuloDAO implements IModuloDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void registrarModulo(Modulo modulo) {

		getSessionFactory().getCurrentSession().merge(modulo);

		try {

			settingLog((Integer) (FuncionesHelper.getUsuario()), 1, 4, 5,
					new Date(), FuncionesHelper.getTerminal().toString(),
					FuncionesHelper.getURL().toString(), true, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actualizarModulo(Modulo modulo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarModulo(Modulo modulo) {
		try {
			getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"delete from MODULO WHERE idmodulo='"
									+ modulo.getIdmodulo() + "'")
					.executeUpdate();
		} catch (Exception e) {
			System.out.println("error en dao eliminar modulo:::"
					+ e.getMessage());
		}
}
	@Override
	public void eliminarPaginaModulo(int idmodulo) {
		try {
			getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"delete from Paginamodulo WHERE idmodulo='"+idmodulo+"' and  valortabla='modulo' ")
					.executeUpdate();
		} catch (Exception e) {
			System.out.println("error en dao eliminar eliminarPaginaModulo:::"
					+ e.getMessage());
		}
}
	
	
	@Override
	public Perfil listarPerfilPorId(int id) {
		Session session = getSessionFactory().openSession();
		return (Perfil) session.load(Perfil.class, id);
	}
	@Override
	public List<Perfilmodulo>  verficarModuloEnPerfil(int idmodulo) {
		
		
		Session session = getSessionFactory().openSession();

		try {
			System.out.println("aaaaaaaaaaa");
			return session.createQuery("from Perfilmodulo where idmodulo= "+idmodulo+" ").list();
		} catch (HibernateException e) {
			System.out.println("error:::" + e);
			throw e;
		} finally {
			session.close();
		}
}
	
	@Override
	public Modulo listarModuloPorId(int id) {
		Session session = getSessionFactory().openSession();
		return (Modulo) session.load(Modulo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modulo> listarModulos() {

		Session session = getSessionFactory().openSession();

		try {
			return session.createQuery("from Modulo order by feccre desc").list();
		} catch (HibernateException e) {
			System.out.println("error:::" + e);
			throw e;
		} finally {
			session.close();
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int obtenerNumeroModulos() {

		Long count = (Long) getSessionFactory().openSession()
				.createQuery("select count(*) from Modulo").uniqueResult();

		return count.intValue();

	}

	@Override
	public String obtenerUltimoModulo() {
		// TODO Auto-generated method stub
		return (String) getSessionFactory()
				.openSession()
				.createQuery(
						"select t.nombremodulo from Modulo t where idmodulo=( select max(idmodulo) from Modulo)")
				.uniqueResult();
	}

	@Override
	public Modulo obtenerUltimoModulocreado() {
		// TODO Auto-generated method stub
		return (Modulo) getSessionFactory()
				.openSession()
				.createQuery(
						"select t from Modulo t where idmodulo=( select max(idmodulo) from Modulo)")
				.uniqueResult();
	}

	@Override
	public Date obtenerFechaUltimoModulo() {
		// TODO Auto-generated method stub
		return (Date) getSessionFactory()
				.openSession()
				.createQuery(
						"select t.feccre from Modulo t where idmodulo=( select max(idmodulo) from Modulo)")
				.uniqueResult();
	}

	public void settingLog(int idusuario, int idmodulo, int idestadoauditoria,
			int ideventoauditoria, Date fechaentrada, String nombrepantalla,
			String url, Boolean estado, int codauditoria) {

		Auditoria Adt = new Auditoria();
		Usuario usr = new Usuario();
		usr.setIdusuario(idusuario);
		Modulo mod = new Modulo();
		mod.setIdmodulo(idmodulo);
		Estadoauditoria esa = new Estadoauditoria();
		esa.setIdestadoauditoria(idestadoauditoria);
		Eventoauditoria eva = new Eventoauditoria();
		eva.setIdeventoauditoria(ideventoauditoria);
		Adt.setUsuario(usr);
		Adt.setModulo(mod);
		Adt.setEstadoauditoria(esa);
		Adt.setEventoauditoria(eva);
		Adt.setFecentrada(fechaentrada);
		Adt.setNompantalla(nombrepantalla);
		Adt.setUrl(url);
		Adt.setEstado(estado);
		Adt.setCodauditoria(codauditoria);
		try {
			getSessionFactory().getCurrentSession().save(Adt);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void registrarPaginamodulo(Paginamodulo paginamodulo) {
		getSessionFactory().getCurrentSession().save(paginamodulo);
	}

	@Override
	public List<Pagina> listarPaginasDeModulos(int idmodulo) {

		Session session = getSessionFactory().openSession();

		try { 
			return session.createQuery("select pm.pagina  from Paginamodulo pm where pm.modulo.idmodulo="+idmodulo+" and valortabla='modulo'").list();
		} catch (HibernateException e) {
			System.out.println("error DAO listarPaginasDeModulos:::" + e);
			throw e;
		} finally {
			session.close();
		}
	}
	@Override
	public List<Pagina> listarPaginas() {

		Session session = getSessionFactory().openSession();

		try {
			
			List<Pagina> lstpagina= new ArrayList <Pagina>();
			
			lstpagina = session.createQuery("select p from Pagina p").list();
			
			for(int i=0; i<lstpagina.size(); i++) {
				Pagina usrA = (Pagina) lstpagina.get(i);
			    for(int j=i+1; j<lstpagina.size(); j++) {
			    	Pagina usrB = (Pagina) lstpagina.get(j);
			        if (usrA.getNombrepagina().equals(usrB.getNombrepagina())) {
			        	lstpagina.remove(j);
			        	j=j-1;
			        }
			    }
			}
			
//			List<Pagina> lstpagina2= new ArrayList <Pagina>();
//			session.createQuery("select p from Pagina p where").list();
//			for (Pagina pagina : lstpagina) {
//			
//			}
//			
//			
//			
//			for (Iterator<Pagina> iter = lstpagina.iterator(); iter.hasNext();) {
//				Pagina algo = iter.next();
//			  //hacer algo con algo
//			  if (algo.equals(pagina)) {
//			    iter.remove(); //Esto quita el elemento actual de la lista, SIN causar problemas
//			  }
//			}
//			
		
			return lstpagina;
			
		} catch (HibernateException e) {
			System.out.println("error DAO listarPaginas s:::" + e);
			throw e;
		} finally {
			session.close();
		}
	}
	
	
}
