package hr.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import hr.model.dao.EmpInfoDAOHibernate;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Service("infoServiceFormService")
public class InfoServiceFormService {
    @Autowired
	private InfoServiceFormDAO infoServiceFormDAOHibernate;
    @Autowired
    private EmpInfoDAO empInfoDAOHibernate;
    
    
    public JSON iSF(int id) {
    	InfoServiceFormBean iSF = infoServiceFormDAOHibernate.select(id);

    	return iSF.toJSONObject();
    }
    
    public JSONArray iSFList(){
		JSONArray iSFList = new JSONArray();
		List<InfoServiceFormBean> iSFL = infoServiceFormDAOHibernate.select();
		for (InfoServiceFormBean infoServiceFormBean : iSFL) {
			iSFList.add(infoServiceFormBean.toJSONObject());
        }
		return iSFList;
	}
    
    public JSONArray iSFListByReceiver(int receiver){
		JSONArray iSFListByReceiver = new JSONArray();
		EmpInfoBean bean = new EmpInfoBean();
		bean.setId(receiver);
		List<InfoServiceFormBean> iSF = infoServiceFormDAOHibernate.selectByReceiver(bean);
		for (InfoServiceFormBean infoServiceFormBean : iSF) {
			iSFListByReceiver.add(infoServiceFormBean.toJSONObject());
        }
		return iSFListByReceiver;
	}
    
    public JSONArray iSFListByStage(Double stage){
		JSONArray iSFListByStage = new JSONArray();
		List<InfoServiceFormBean> iSF = infoServiceFormDAOHibernate.selectByStage(stage);
		for (InfoServiceFormBean infoServiceFormBean : iSF) {
			iSFListByStage.add(infoServiceFormBean.toJSONObject());
        }
		return iSFListByStage;
	}
    
    public JSONArray iSFListByStage4(int id){
		JSONArray iSFListByStage = new JSONArray();
		EmpInfoBean emp = empInfoDAOHibernate.select(id);
		if(!"admin".equals(emp.getCharacter())) {
			return null;
		}
		List<InfoServiceFormBean> iSF = infoServiceFormDAOHibernate.selectByStage(4.0);
		for (InfoServiceFormBean infoServiceFormBean : iSF) {
			iSFListByStage.add(infoServiceFormBean.toJSONObject());
        }
		return iSFListByStage;
	}
    
    public JSONArray iSFListByContractor(int contractor){
		JSONArray iSFListByContractor = new JSONArray();
		EmpInfoBean bean = new EmpInfoBean();
		bean.setId(contractor);
		List<InfoServiceFormBean> iSF = infoServiceFormDAOHibernate.selectByContractor(bean);
		for (InfoServiceFormBean infoServiceFormBean : iSF) {
			iSFListByContractor.add(infoServiceFormBean.toJSONObject());
        }
		return iSFListByContractor;
	}
    
    public JSONArray iSFListByVerification(int verification){
		JSONArray iSFListByVerification = new JSONArray();
		EmpInfoBean bean = new EmpInfoBean();
		bean.setId(verification);
		List<InfoServiceFormBean> iSF = infoServiceFormDAOHibernate.selectByVerification(bean);
		for (InfoServiceFormBean infoServiceFormBean : iSF) {
			iSFListByVerification.add(infoServiceFormBean.toJSONObject());
        }
		return iSFListByVerification;
	}
    
    public JSONArray iSFListByApplicantSupervisor(int id){//利用員工ID找出部門ID,再透過部門ID查詢
		JSONArray iSFListByApplicantSupervisor = new JSONArray();
		DepInfoBean bean = new DepInfoBean();
		EmpInfoBean emp = empInfoDAOHibernate.select(id);
		
		if(!emp.getName().equals(emp.getDepInfoBean().getSupervisor())) { //驗證是否為主管
			return null;
		}
		
		int depNo = emp.getDepInfoBean().getNo();
		bean.setNo(depNo);
		List<InfoServiceFormBean> iSF = infoServiceFormDAOHibernate.selectByApplicantDep(bean);
		for (InfoServiceFormBean infoServiceFormBean : iSF) {
			iSFListByApplicantSupervisor.add(infoServiceFormBean.toJSONObject());
        }
		return iSFListByApplicantSupervisor;
	}
    
    public Boolean insert(InfoServiceFormBean bean){
		Boolean result = false;
		if(bean!=null){
			result = infoServiceFormDAOHibernate.insert(bean);
		}
		return result;
	}
    
    public Boolean update(InfoServiceFormBean bean){
    	Boolean result = false;
		if(bean!=null && bean.getId()!=0){
			result = infoServiceFormDAOHibernate.update(bean);
		}
		return result;
	}
    
    public Boolean delete(Integer id){
		Boolean result = false;
		if(id!=0){
			result = infoServiceFormDAOHibernate.delete(id);
		}
		return result;
	}
    
    public static void main(String[] arg){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		InfoServiceFormService infoServiceFormService = (InfoServiceFormService)context.getBean("infoServiceFormService");
		
		
		
		JSONArray xxx = infoServiceFormService.iSFListByReceiver(10);
		System.out.println(xxx);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
		
	}
}
