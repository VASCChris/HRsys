package hr.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hr.model.InfoSecurityLvBean;
import hr.model.InfoSecurityLvDAO;
import hr.model.InfoServiceTypeBean;

@Repository("infoSecurityLvDAOHibernate")
public class InfoSecurityLvDAOHibernate implements InfoSecurityLvDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	public InfoSecurityLvBean select(int lv) {
		return (InfoSecurityLvBean) this.getSession().get(InfoSecurityLvBean.class, lv);
	}

	
	public List<InfoSecurityLvBean> select() {
		return this.getSession().createQuery("from InfoSecurityLvBean").list();
	}

}
