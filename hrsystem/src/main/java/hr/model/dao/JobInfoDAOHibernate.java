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
import hr.model.JobInfoBean;
import hr.model.JobInfoDAO;

@Repository("jobInfoDAOHibernate")
public class JobInfoDAOHibernate implements JobInfoDAO {
	@Autowired
    private SessionFactory sessionFactory;
	
//	public JobInfoDAOHibernate(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public JobInfoBean select(int no) {
		return (JobInfoBean) this.getSession().get(JobInfoBean.class, no);
	}

	public List<JobInfoBean> select() {
		return this.getSession().createQuery("from JobInfoBean").list();
	}

	public Boolean insert(JobInfoBean bean) {
		if(bean!=null){
			JobInfoBean temp = this.select(bean.getNo());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	public JobInfoBean update(JobInfoBean bean) {
		if(bean!=null){
			JobInfoBean temp = this.select(bean.getNo());
			if(temp!=null){
				temp.setName(bean.getName());
			}
			return temp;
		}
		return null;
	}

	public Boolean delete(int no) {
		JobInfoBean bean = this.select(no);
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
		
		JobInfoDAOHibernate dao = (JobInfoDAOHibernate)context.getBean("jobInfoDAOHibernate");
		JobInfoBean bean = new JobInfoBean();
		bean.setNo(2);
		bean.setName("HAHAHA");

		List<JobInfoBean> xxx = dao.select();
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}

}
