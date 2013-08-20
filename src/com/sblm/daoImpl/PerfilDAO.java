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

import com.sblm.dao.IPerfilDAO;
import com.sblm.model.Auditoria;
import com.sblm.model.Estadoauditoria;
import com.sblm.model.Eventoauditoria;
import com.sblm.model.Modulo;
import com.sblm.model.Perfil;
import com.sblm.model.Usuario;
import com.sblm.util.FuncionesHelper;
import com.sblm.util.PerfilModuloPermiso;

@Repository(value = "perfilDAO")
public class PerfilDAO  implements IPerfilDAO,Serializable{

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	Session sesion = null;
	Transaction txt = null;

	@Override
	public void registrarPerfil(Perfil perfil) {
		System.out.println(":::xxx registro perfil DAO xxx:::");
		getSessionFactory().getCurrentSession().merge(perfil);
try {
			
			settingLog((Integer)(FuncionesHelper.getUsuario()), 1, 4, 6, new Date(),FuncionesHelper.getTerminal().toString(), FuncionesHelper.getURL().toString(), true, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarPerfil(Perfil perfil) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPerfil(Perfil perfil) {
		try {
			getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"delete from Perfil WHERE idperfil='"
									+ perfil.getIdperfil() + "'")
					.executeUpdate();
		} catch (Exception e) {
			System.out.println("error en dao eliminar eliminarPerfil:::"
					+ e.getMessage());
		}
	}

	@Override
	public Perfil listarPerfilPorId(int id) {
		Session session=getSessionFactory().openSession();
         return (Perfil) session.load(Perfil.class, id);
	}

	@Override
	public List<Perfil> listarPerfiles() {
		Session session = getSessionFactory().openSession();

	
	    try{
	    	return session.createQuery("from Perfil order by feccre desc").list();
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

	@Override
	public int obtenerUltimoIdPerfil() {
		
			Integer val=(Integer)getSessionFactory().openSession().createQuery("  select max(p.idperfil) from Perfil p").uniqueResult();
//			if(val == null){
//				System.out.println("ultimo perfil#####");
//				return 0;}
//			else
				return val;
		
	}

	@Override
	public List<PerfilModuloPermiso> listarPerfilesModulosPermisos() {
		Session session = getSessionFactory().openSession();

		
	    try{
	    	return session.createQuery("from PerfilModuloPermiso").list();
	    }
	    catch(HibernateException e){
	    	System.out.println("error:::"+e);
	    	throw e;
	    }
	    finally{
	    	session.close();
	    }
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
	public String obtenerUltimoPerfil() {
		return (String)getSessionFactory().openSession().createQuery("select p.nombreperfil from Perfil p where p.idperfil=( select max(idperfil) from Perfil)").uniqueResult();
	}
	@Override
	public Date obtenerFechaUltimoPerfil() {
		Date fecha=(Date) getSessionFactory().openSession().createQuery("select p.feccre from Perfil p where p.idperfil=( select max(idperfil) from Perfil)").uniqueResult();
		return fecha;
	}
	@Override
	public int obtenerNumeroPerfiles() {
		
		Long count = (Long)getSessionFactory().openSession().createQuery("select count(*) from Perfil").uniqueResult();

		return count.intValue();
				
	
	}
}
