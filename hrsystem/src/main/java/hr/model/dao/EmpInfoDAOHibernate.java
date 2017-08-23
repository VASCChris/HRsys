package hr.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import hr.model.DepInfoBean;
import hr.model.EmpInfoBean;
import hr.model.EmpInfoDAO;
import hr.model.JobInfoBean;

@Repository("empInfoDAOHibernate")
public class EmpInfoDAOHibernate implements EmpInfoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
//	public EmpInfoDAOHibernate(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	
	public EmpInfoBean select(int id) {
		return (EmpInfoBean) this.getSession().get(EmpInfoBean.class, id);
	}
	
	public EmpInfoBean selectByCharactor(String charactor) {
		Query query = this.getSession().createQuery("from EmpInfoBean where character=?");
		query.setParameter(0, charactor);
		return (EmpInfoBean) query.uniqueResult();
	}
	
	public EmpInfoBean selectByName(String name) {
		Query query = this.getSession().createQuery("from EmpInfoBean where name=?");
		query.setParameter(0, name);
		return (EmpInfoBean) query.uniqueResult();
	}

	public EmpInfoBean selectByAccountAndPwd(String account ,String password) {
		Query query = this.getSession().createQuery("from EmpInfoBean where account=? and password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return (EmpInfoBean) query.uniqueResult();
	}
	
	public List<EmpInfoBean> select() {
		return this.getSession().createQuery("from EmpInfoBean").list();
	}
	
    public List<EmpInfoBean> selectByDep(int depNo){
    	Query query = this.getSession().createQuery("from EmpInfoBean where dep_no=?");
		query.setParameter(0, depNo);
		return query.list();
	}

	public Boolean insert(EmpInfoBean bean) {
		if(bean!=null){
			EmpInfoBean temp = this.select(bean.getId());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	
	
	public EmpInfoBean update(EmpInfoBean bean) {
		if(bean!=null){
			EmpInfoBean temp = this.select(bean.getId());
			if(temp!=null){
				temp.setAccount(bean.getAccount());
				temp.setPassword(bean.getPassword());
				temp.setEmpNo(bean.getEmpNo());
				temp.setName(bean.getName());
				temp.setEngName(bean.getEngName());
				temp.setExt(bean.getExt());
				temp.setDepInfoBean(bean.getDepInfoBean());
				temp.setJobInfoBean(bean.getJobInfoBean());
				temp.setCharacter(bean.getCharacter());
			}
			return temp;
		}
		return null;
	}

	public Boolean delete(int id) {
		EmpInfoBean bean = this.select(id);
		if(bean!=null){
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	public static void main(String[] arg){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		
		EmpInfoDAOHibernate dao = (EmpInfoDAOHibernate)context.getBean("empInfoDAOHibernate");
		EmpInfoBean bean = new EmpInfoBean();
		DepInfoBean depInfoBean = new DepInfoBean();
		JobInfoBean jobInfoBean = new JobInfoBean();
		depInfoBean.setNo(1);
		jobInfoBean.setNo(1);
//		bean.setId(105);
		bean.setAccount("YEE");
		bean.setPassword("QQQ");
		bean.setEmpNo("A01");
		bean.setName("xxx");
		bean.setEngName("xxx");
		bean.setExt(1);
		bean.setDepInfoBean(depInfoBean);
		bean.setJobInfoBean(jobInfoBean);
		bean.setCharacter("xxx");
		EmpInfoBean xxx = dao.selectByCharactor("superadmin");
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}

}
