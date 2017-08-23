package hr.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import hr.model.DepInfoBean;
import hr.model.DepInfoDAO;
import hr.model.EmpInfoBean;

@Repository("depInfoDAOHibernate")
public class DepInfoDAOHibernate implements DepInfoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
//	public DepInfoDAOHibernate(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public DepInfoBean select(int no) {
		return (DepInfoBean) this.getSession().get(DepInfoBean.class, no);
	}

	public List<DepInfoBean> select() {
		return this.getSession().createQuery("from DepInfoBean").list();
	}

	public Boolean insert(DepInfoBean bean) {
		if(bean!=null){
			DepInfoBean temp = this.select(bean.getNo());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	public DepInfoBean update(DepInfoBean bean) {
		if(bean!=null){
			DepInfoBean temp = this.select(bean.getNo());
			if(temp!=null){
				temp.setName(bean.getName());
				temp.setSupervisor(bean.getSupervisor());
			}
			return temp;
		}
		return null;
	}

	public Boolean delete(int no) {
		DepInfoBean bean = this.select(no);
		if(bean!=null){
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		
		DepInfoDAOHibernate dao = (DepInfoDAOHibernate)context.getBean("depInfoDAOHibernate");
		DepInfoBean bean = new DepInfoBean();
//		bean.setNo(3);
		bean.setName("HEHEHE");
		bean.setSupervisor("A101");

		List<DepInfoBean> xxx = dao.select();
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();

	}

}
