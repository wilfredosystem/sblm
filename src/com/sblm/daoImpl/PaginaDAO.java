package com.sblm.daoImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sblm.dao.IPaginaDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Pagina;
import com.sblm.model.Paginamodulo;
import com.sblm.model.Perfil;
import com.sblm.model.Usuario;
import com.sblm.util.FuncionesHelper;

@Repository(value = "paginaDAO")
public class PaginaDAO implements IPaginaDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void registrarPagina(Pagina pagina) {
		getSessionFactory().getCurrentSession().merge(pagina);
		try {

			settingLog((Integer) (FuncionesHelper.getUsuario()), 1, 4, 4,
					new Date(), FuncionesHelper.getTerminal().toString(),
					FuncionesHelper.getURL().toString(), true, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarPagina(Pagina pagina) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPagina(Pagina pagina) {

		// Session session = getSessionFactory().openSession();
		// session.delete(pagina);

		try {
			getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"delete from PAGINA WHERE idpagina='"
									+ pagina.getIdpagina() + "'")
					.executeUpdate();
			// getSessionFactory().getCurrentSession().delete(modulo);
		} catch (Exception e) {
			System.out.println("error en dao eliminar pagina:::"
					+ e.getMessage());
		}

	}

	@Override
	public Pagina listarPaginaPorId(int id) {
		Session session = getSessionFactory().openSession();
		return (Pagina) session.load(Pagina.class, id);
	}

	@Override
	public List<Pagina> listarPaginas() {
		Session session = getSessionFactory().openSession();

		try {
			return session.createQuery("from Pagina").list();
		} catch (HibernateException e) {
			System.out.println("error listado pagina dao:::" + e);
			throw e;
		}
		// finally{
		// session.close();
		// }

	}

	@Override
	public int obtenerNumeroPaginas() {
		Long count = (Long) getSessionFactory().openSession()
				.createQuery("select count(*) from Pagina").uniqueResult();

		return count.intValue();
	}

	@Override
	public String obtenerUltimaPagina() {
		return (String) getSessionFactory()
				.openSession()
				.createQuery(
						"select p.descripcionpagina from Pagina p where idpagina=( select max(idpagina) from Pagina)")
				.uniqueResult();

	}

	@Override
	public Pagina verificarPaginaEnModulo(String descripcionpagina, int idmodulo) {
		return (Pagina) getSessionFactory()
				.openSession()
				.createQuery(
						"select  p  from  Paginamodulo  pm inner join pm.pagina p  inner join pm.modulo  m  where p.descripcionpagina= LTRIM( RTRIM('"+descripcionpagina+"')) and m.idmodulo="+idmodulo+" ")
				.uniqueResult();

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pagina> listarPaginasModulos(int idmodulo) {

		return getSessionFactory()
				.openSession()
				.createQuery(
						"select  p  from  Paginamodulo  pm inner join pm.pagina p  inner join pm.modulo  m  where m.idmodulo="
								+ idmodulo + " ").list();
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
	public Date obtenerFechaUltimaPagina() {
		return (Date) getSessionFactory()
				.openSession()
				.createQuery(
						"select t.feccre from Pagina t where idpagina=( select max(idpagina) from Pagina)")
				.uniqueResult();

	}

	@Override
	public void registrarPaginamodulo(Paginamodulo paginamodulo) {
		getSessionFactory().getCurrentSession().save(paginamodulo);

	}

	@Override
	public void actualizarPaginamodulo(Paginamodulo paginamodulo) {

		String updateQuery = "UPDATE Paginamodulo  SET [idmodulo] ='"
				+ paginamodulo.getModulo().getIdmodulo()
				+ "' WHERE idpaginamodulo=" + paginamodulo.getIdpaginamodulo()
				+ " ";

		getSessionFactory().getCurrentSession().createSQLQuery(updateQuery)
				.executeUpdate();

	}

	@Override
	public void eliminarPaginamodulo(Paginamodulo paginamodulo) {
		try {
			getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"delete from Paginamodulo WHERE idpaginamodulo='"
									+ paginamodulo.getIdpaginamodulo() + "'")
					.executeUpdate();
		} catch (Exception e) {
			System.out.println("error en dao eliminar paginamodulo:::"
					+ e.getMessage());
		}

	}

	@Override
	public Pagina obtenerUltimaPaginaCreada() {
		return (Pagina) getSessionFactory()
				.openSession()
				.createQuery(
						" from Pagina t where idpagina=( select max(idpagina) from Pagina)")
				.uniqueResult();

	}

	@Override
	public List<Paginamodulo> listarPaginamodulos() {
		Session session = getSessionFactory().openSession();

		try {
			return session.createQuery("from Paginamodulo pm order by pm.pagina.feccre desc").list();
		} catch (HibernateException e) {
			System.out.println("error listado Paginamodulo dao:::" + e);
			throw e;
		}
	}
}
