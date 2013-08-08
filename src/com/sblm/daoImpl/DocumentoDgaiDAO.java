package com.sblm.daoImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sblm.dao.IDocumentoDgaiDAO;
import com.sblm.dao.IModuloDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Documento;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Usuario;

@Repository(value = "documentodgaiDAO")
public class DocumentoDgaiDAO implements IDocumentoDgaiDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void registrarDocumentoDgai(Documento documento) {
		getSessionFactory().getCurrentSession().merge(documento);

	}

	@Override
	public void actualizarDocumentoDgai(Documento documento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarDocumentoDgai(Documento documento) {
		// TODO Auto-generated method stub

	}

	@Override
	public Modulo listarDocumentoDgaiPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Documento> listarDocumentosDgai() {
		Session session = getSessionFactory().openSession();
		try {
			return session.createQuery("from Documento D where D.estado='atendido' and D.respuesta='en proceso' ").list();
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

	@Override
	public int totalPendientesDerivacion() {
	Long count = (Long)getSessionFactory().openSession().createQuery("select count(*) from Documento D where D.estado='atendido'").uniqueResult();
		
		return count.intValue();
 
	}
	public  void settingLog(int idusuario, int idmodulo, int idestadoauditoria, int ideventoauditoria, Date fechaentrada, String nombrepantalla,
			String url,Boolean estado, int codauditoria){


Auditoria Adt= new Auditoria();
Usuario usr= new Usuario();
usr.setIdusuario(idusuario);
Modulo mod= new Modulo();
mod.setIdmodulo(idmodulo);
Estadoauditoria esa= new Estadoauditoria();
esa.setIdestadoauditoria(idestadoauditoria);
Eventoauditoria eva= new Eventoauditoria();
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
e.printStackTrace();		}



}

}
