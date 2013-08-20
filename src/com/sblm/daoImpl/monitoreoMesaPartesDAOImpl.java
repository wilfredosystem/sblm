package com.sblm.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sblm.dao.IMonitoreoMesaPartesDAO;
import com.sblm.model.Usuario;
import com.sblm.modelMP.Documento;
import com.sblm.modelMP.Flujodoc;

@Repository(value = "monitoreoMesaPartesDAO")
public class monitoreoMesaPartesDAOImpl implements IMonitoreoMesaPartesDAO, Serializable {
	private static final long serialVersionUID = -7132329520845816103L;

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SessionFactory sessionFactory2;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	 
	@Override
	public List<com.sblm.model.Documento> listarDocumentosRegistrados() {
		
		Session session = getSessionFactory().openSession();
		System.out.println("Pendiente");

		
		return session.createQuery("from Documento D where D.estado='ninguno' order by D.fechadocumento  desc").list();
			
	}


	public SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}


	public void setSessionFactory2(SessionFactory sessionFactory2) {
		this.sessionFactory2 = sessionFactory2;
	}


	@Override
	public Object countExternalDB() {
		Session session = getSessionFactory2().openSession();

		String cadena="SELECT count(*) FROM Flujodoc  F inner join  F.documento D " +
				"where  D.idare='10800000' and F.numflj=1 and D.fecdoc>'01/07/13' ";
		
		
		Query numero=session.createQuery(cadena);
		
		
		
	    return numero.list().get(0);
	
	}


	@Override
	public Object countInternalDB() {
		Session session = getSessionFactory().openSession();
		
		String cadena="SELECT count(*) FROM Documento";
		
		
		Query numero=session.createQuery(cadena);
		
		
		
	    return numero.list().get(0);
		
	}


	@Override
	public List<Flujodoc> getNewInserts(int val) {
		
		String A= "SELECT TOP "+val+" F.IDFLJ,F.NUMFOL,D.TITDOC,D.FECDOC,D.DESDOC,D.DESASN,D.DESRMT,D.IDDOC,D.idtipdoc FROM "+
		"Flujodoc AS F inner join  Documento AS D  ON D.IDDOC=F.IDDOC where  D.idare='10800000' and F.NUMFLJ=1 and D.fecdoc>'01/07/13' order by D.fecdoc  desc";

		Session session = getSessionFactory2().openSession();
		 Query Q = session.createSQLQuery(A);
			
			List<Object> objectList = Q.list();
			Iterator iterator = objectList.iterator();
			List<Flujodoc> lista = new ArrayList<Flujodoc>();
			while(iterator.hasNext()){ 
				Object []obj = (Object[])iterator.next();
				
				Documento doc= new Documento();
				doc.setTitdoc((String) obj[2]);
				doc.setFecdoc((Date)obj[3]);
				doc.setDesdoc((String)obj[4]);
				doc.setDesasn((String)obj[5]);
				
				System.out.println((String)obj[6]);
				doc.setDesrmt((String)obj[6]);
				doc.setIddoc((String)obj[7]);
				doc.setIdtipdoc((String)obj[8]);

				Flujodoc flujo = new Flujodoc();
				flujo.setIdflj((String) obj[0]);
				flujo.setNumfol((String) obj[1]);
				

				flujo.setDocumento(doc);
				
							
				lista.add(flujo);
				
				}
			
			
			return lista;
	}


	@Override
	public void save(com.sblm.model.Documento doc) {
		
		try {
			getSessionFactory().getCurrentSession().save(doc);

		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
		
	}


	@Override
	public Object countPendientes() {

		Session session = getSessionFactory().openSession();
		
		Query numero=session.createQuery("select count(*) from Documento D where D.estado='ninguno'");

	      try {
	    	  return numero.list().get(0);
	      } catch (HibernateException e) { 
	    	  throw e;
	      }  finally { 
	           session.close();
	      }
	}


	@Override
	public Object countAtendidos() {
		
		Session session = getSessionFactory().openSession();
		
		Query numero=session.createQuery("select count(*) from Documento D where D.estado='atendido'");

	      try {
	    	  return numero.list().get(0);
	      } catch (HibernateException e) { 
	    	  throw e;
	      }  finally { 
	           session.close();
	      }
	}


	@Override
	public void actualizarEstadoToAtendido(int iddoc) {
		String updateQuery="UPDATE DOCUMENTO SET ESTADO='atendido' WHERE IDDOCUMENTO='"+iddoc+"'";
		
		getSessionFactory().getCurrentSession().createSQLQuery(updateQuery).executeUpdate();
	}


	@Override
	public List<com.sblm.model.Documento> listarDocumentosAtendidos() {
		Session session = getSessionFactory().openSession();
		
		return session.createQuery("from Documento D where D.estado='atendido'").list();
	}


	@Override
	public Usuario getDirectorDGAI() {
		Session session = getSessionFactory().openSession();
		return (Usuario) session.createQuery("from Usuario U where U.cargo='Director DGAI'").uniqueResult();
	}



	
	
}
