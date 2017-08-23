package hr.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import net.sf.json.JSONArray;

@Service("depInfoService")
public class DepInfoService implements Serializable{
	@Autowired
	private DepInfoDAO depInfoDAOHibernate;
//	public DepInfoService(DepInfoDAO depInfoDAOHibernate) {
//		this.depInfoDAOHibernate = depInfoDAOHibernate;
//	}
	
	public JSONArray depList(){
		JSONArray depList = new JSONArray();
		List<DepInfoBean> dList = depInfoDAOHibernate.select();
		for (DepInfoBean depInfoBean : dList) {
			depList.add(depInfoBean.toJSONObject());
        }
		return depList;
	}
	
//	public List<DepInfoBean> depList(){
//		return depInfoDAOHibernate.select();
//	}
	
	public Boolean insert(DepInfoBean bean){
		Boolean result = false;
		if(bean!=null){
			result = depInfoDAOHibernate.insert(bean);
		}
		return result;
	}
	
	public Boolean update(DepInfoBean bean){
		DepInfoBean result = null;
		if(bean!=null && bean.getNo()!=0){
			result = depInfoDAOHibernate.update(bean);
			if(result!=null){
				return true;
			}
		}
		return false;
	}
	
	public Boolean delete(Integer id){
		Boolean result = false;
		if(id!=0){
			result = depInfoDAOHibernate.delete(id);
		}
		return result;
	}
	
	public static void main(String[] arg){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		DepInfoService depInfoService = (DepInfoService)context.getBean("depInfoService");
		
		DepInfoBean bean = new DepInfoBean();
		bean.setNo(4);
		bean.setName("HAHAHA");
		
		JSONArray xxx = depInfoService.depList();
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
		
	}

}
