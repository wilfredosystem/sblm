package com.sblm.daoImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sblm.dao.IFlujoDocumentoDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Flujodocumento;
import com.sblm.model.Modulo;
import com.sblm.model.Usuario;
import com.sblm.util.FuncionesHelper;

@Repository(value = "flujodocumentoDAO")
public class FlujoDocumentoDAO implements IFlujoDocumentoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void registrarFlujoDocumento(Flujodocumento flujodocumento) {
		getSessionFactory().getCurrentSession().merge(flujodocumento);
		
		
		try {
			settingLog(1, 9, flujodocumento.getUsuario().getIdusuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void actualizarFlujoDocumento(Flujodocumento flujodocumento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarFlujoDocumento(Flujodocumento flujodocumento) {
		try {
			getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"delete from Flujodocumento WHERE idflujodocumento='"
									+ flujodocumento.getIdflujodocumento()
									+ "'").executeUpdate();
		} catch (Exception e) {
			System.out.println("error en dao eliminar perfil usuario:::"
					+ e.getMessage());
		}
	}

	@Override
	public Modulo listarFlujoDocumentoPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flujodocumento> listarFlujoDocumento() {

		Session session = getSessionFactory().openSession();

		try {
			return session.createQuery("from Flujodocumento").list();
		} catch (HibernateException e) {
			System.out.println("error:::" + e);
			throw e;
		}
		// finally{
		// session.close();
		// }

	}

	@Override
	public void actualizarRespuestaToAtendido(int iddoc) {
		String updateQuery = "UPDATE DOCUMENTO SET RESPUESTA='recibido' WHERE IDDOCUMENTO='"
				+ iddoc + "'";

		getSessionFactory().getCurrentSession().createSQLQuery(updateQuery)
				.executeUpdate();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int obtenerNumeroDespachados() {
	
		Long count = (Long) getSessionFactory().openSession()
				.createQuery("select COUNT(IDDOCUMENTO) from Documento  where RESPUESTA ='recibido' and estado='atendido'").uniqueResult();
		return count.intValue();
	}

	@Override
	public int obtenerNumeroPendientes() {
		
		Long count = (Long) getSessionFactory().openSession()
		.createQuery("select COUNT(IDDOCUMENTO) from Documento where RESPUESTA ='en proceso' and estado='atendido'").uniqueResult();
return count.intValue();
	}

	@Override
	public int obtenerNumeroRechazados() {
		
		Long count = (Long) getSessionFactory().openSession()
		.createQuery("select COUNT(IDDOCUMENTO) from Documento where RESPUESTA ='rechazado' and estado='atendido'").uniqueResult();
return count.intValue();
	}
	
	
public  void settingLog(int idestadoauditoria, int ideventoauditoria, int idusuariodestino){
		
		String url=FuncionesHelper.getURL().toString();
	
		try {
			int index = url.indexOf("pages/");
			url=url.substring(index+6, url.length());
			url=url.substring(0, url.length()-4);
		} catch (Exception e) {e.getMessage();	}

				Session session = getSessionFactory().openSession();
				Auditoria Adt= new Auditoria();
				Usuario usr= new Usuario();
				usr.setIdusuario((Integer)(FuncionesHelper.getUsuario()));
				
				Usuario usrdes= new Usuario();
				usrdes.setIdusuario(idusuariodestino);
				
				Modulo mod= new Modulo();
				mod.setIdmodulo((Integer) session.createQuery("select M.idmodulo from Pagina P inner join P.modulo M where P.nombrepagina='"+url+"'").uniqueResult());
				Estadoauditoria esa= new Estadoauditoria();
				esa.setIdestadoauditoria(idestadoauditoria);
				Eventoauditoria eva= new Eventoauditoria();
				eva.setIdeventoauditoria(ideventoauditoria);
				Adt.setUsuario(usr);
				Adt.setUsuariodestino(usrdes);
				Adt.setModulo(mod);
				Adt.setEstadoauditoria(esa);
				Adt.setEventoauditoria(eva);
				Adt.setFecentrada( new Date());
				Adt.setNompantalla(url);
				Adt.setUrl(FuncionesHelper.getURL().toString());
				Adt.setIp(FuncionesHelper.getTerminal().toString());
				Adt.setEstado(true);
				Adt.setCodauditoria(1);
				
		try {
			getSessionFactory().getCurrentSession().save(Adt);
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	
	}
	
	

}
