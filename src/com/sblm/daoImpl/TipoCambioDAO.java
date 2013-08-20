package com.sblm.daoImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sblm.dao.ITipoCambioDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Tipocambio;
import com.sblm.model.Usuario;
import com.sblm.util.FuncionesHelper;



@Repository(value = "tipocambioDAO")
public class TipoCambioDAO implements ITipoCambioDAO,Serializable{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	Session sesion = null;
	Transaction txt = null;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void registrarTipoCambio(Tipocambio tipoCambio) {
		getSessionFactory().getCurrentSession().merge(tipoCambio);

	}
	


	public Tipocambio obtenerTipoCambio() {

		//sesion = getSessionFactory().openSession();
		//txt = sesion.beginTransaction();
		// recuperamos el obj con el id.
		Tipocambio t= (Tipocambio) getSessionFactory().openSession().createQuery(
				"select t from Tipocambio t where idtipocambio=( select max(idtipocambio) from Tipocambio)").uniqueResult();
		 
		
		 return t;
	}
	public String obtenerMes() {

		//sesion = getSessionFactory().openSession();
		//txt = sesion.beginTransaction();
		// recuperamos el obj con el id.
		String val= (String) getSessionFactory().openSession().createQuery(
				"select DATENAME(month, t.fecha) from Tipocambio t where idtipocambio=( select max(idtipocambio) from Tipocambio)").uniqueResult();
		return val;
		
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

	@Override
	public Tipocambio listarTipoCambioPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tipocambio> listarTipoCambios() {
	
	    
Session session = getSessionFactory().openSession();
		
	    try{
	    	return session.createQuery("from Tipocambio order by feccre desc").list();
	    }
	    catch(HibernateException e){
	    	System.out.println("error:::"+e);
	    	throw e;
	    }
	    finally{
	    	session.close();
	    }
	   
	}

}
