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

import com.sblm.dao.IModuloDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Tipocambio;
import com.sblm.model.Usuario;
import com.sblm.util.FuncionesHelper;

@Repository(value = "moduloDAO")
public class ModuloDAO  implements IModuloDAO,Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override   
	public void registrarModulo(Modulo modulo) {
	
		getSessionFactory().getCurrentSession().merge(modulo);
		
try {
			
			settingLog((Integer)(FuncionesHelper.getUsuario()), 1, 4,5, new Date(),FuncionesHelper.getTerminal().toString(), FuncionesHelper.getURL().toString(), true, 0);
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
		getSessionFactory().getCurrentSession().createSQLQuery("delete from MODULO WHERE idmodulo='"+modulo.getIdmodulo()+"'").executeUpdate();
			//getSessionFactory().getCurrentSession().delete(modulo);
		} catch (Exception e) {
			System.out.println("error en dao eliminar modulo:::"+e.getMessage());
		}
		
		
//		 Session session = HibernateUtil.getSessionFactory().openSession();
//	        try {
//	            session.beginTransaction();
//	            session.delete(integrante);
//	            session.beginTransaction().commit();
//	        } catch (Exception e) {
//	            System.out.println("Error en eliminar: "+e.getMessage());
//	            session.beginTransaction().rollback();
//	        }
		
	}

	@Override
	public Modulo listarModuloPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<Modulo> listarModulos() {
		
		Session session = getSessionFactory().openSession();
		
	    try{
	    	return session.createQuery("from Modulo").list();
	    }
	    catch(HibernateException e){
	    	System.out.println("error:::"+e);
	    	throw e;
	    }
	    finally{
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
	
		Long count = (Long)getSessionFactory().openSession().createQuery("select count(*) from Modulo").uniqueResult();
		
		return count.intValue();
 
	}

	@Override
	public String obtenerUltimoModulo() {
		// TODO Auto-generated method stub
		return (String)getSessionFactory().openSession().createQuery("select t.nombremodulo from Modulo t where idmodulo=( select max(idmodulo) from Modulo)").uniqueResult();
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
