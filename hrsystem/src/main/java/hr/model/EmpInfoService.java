package hr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;


@Service("empInfoService")
public class EmpInfoService implements Serializable{
	@Autowired
	private EmpInfoDAO empInfoDAOHibernate;
	@Autowired
	private DepInfoDAO depInfoDAOHibernate;


	public JSONArray empList(){
		JSONArray empList = new JSONArray();
		List<EmpInfoBean> eList = empInfoDAOHibernate.select();
		for (EmpInfoBean empInfoBean : eList) {
			empList.add(empInfoBean.toJSONObject());
        }
		return empList;
	}
	
	public JSONArray empListByDep(int depNo){
		JSONArray empListByDep = new JSONArray();
		List<EmpInfoBean> list = empInfoDAOHibernate.selectByDep(depNo);
		for (EmpInfoBean empInfoBean : list) {
			empListByDep.add(empInfoBean.toJSONObject());
        }
		return empListByDep;
	}
	
	public EmpInfoBean login(String account ,String password) {
		EmpInfoBean result = null;
		result = empInfoDAOHibernate.selectByAccountAndPwd(account, password);
		if(result!=null) {
			return result;
		}
		return result;
	}
	
	public Integer getSupervisorId(int depNo) { //透過部門編號來查詢主管ID
		String supervisor = depInfoDAOHibernate.select(depNo).getSupervisor();
		int id = empInfoDAOHibernate.selectByName(supervisor).getId();
		return id;
	}

	public List<EmpInfoBean> empInfo(EmpInfoBean bean) {// select單筆or多筆資料
		List<EmpInfoBean> result = null;
		if (bean != null && bean.getId() != 0) {
			EmpInfoBean temp = empInfoDAOHibernate.select(bean.getId());
			if(temp!=null){
				result = new ArrayList<EmpInfoBean>();
				result.add(temp);
			}
		}else{
			result = empInfoDAOHibernate.select();
		}
		return result;
	}
	
	public Boolean insert(EmpInfoBean bean){
		Boolean result = false;
		if(bean!=null){
			result = empInfoDAOHibernate.insert(bean);
		}
		return result;
	}
	
	public Boolean update(EmpInfoBean bean){
		EmpInfoBean result = null;
		if(bean!=null && bean.getId()!=0){
			result = empInfoDAOHibernate.update(bean);
			if(result!=null) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean delete(Integer id){
		Boolean result = false;
		if(id!=0){
			result = empInfoDAOHibernate.delete(id);
		}
		return result;
	}

	
    public static void main(String[] arg){
    	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		EmpInfoService empInfoService = (EmpInfoService)context.getBean("empInfoService");
		
		EmpInfoBean bean = new EmpInfoBean();
		DepInfoBean depInfoBean = new DepInfoBean();
		JobInfoBean jobInfoBean = new JobInfoBean();
		depInfoBean.setNo(11);
		jobInfoBean.setNo(10);
//		bean.setId(3);
		bean.setAccount("FINAL");
		bean.setPassword("QQQ");
		bean.setEmpNo("XXX");
		bean.setName("xxx");
		bean.setEngName("xxx");
		bean.setExt(1);
		bean.setDepInfoBean(depInfoBean);
		bean.setJobInfoBean(jobInfoBean);
		bean.setCharacter("xxx");
		
		EmpInfoBean xxx = empInfoService.login("Chris.Chiu@vascreative.com", "xxx");
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
    }
	
	
}

    