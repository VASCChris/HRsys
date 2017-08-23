/**
 * 
 */
package hr.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hr.model.InfoServiceTypeBean;
import hr.model.InfoServiceTypeDAO;

/**
 * @author chris.chiu
 *
 */
@Repository("infoServiceTypeDAOHibernate")
public class InfoServiceTypeDAOHibernate implements InfoServiceTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	
	public InfoServiceTypeBean select(int no) {
		return (InfoServiceTypeBean) this.getSession().get(InfoServiceTypeBean.class, no);
	}


	
	public List<InfoServiceTypeBean> select() {
		return this.getSession().createQuery("from InfoServiceTypeBean").list();
	}

}
