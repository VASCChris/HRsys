package hr.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import hr.model.DepInfoBean;
import hr.model.EmpInfoBean;
import hr.model.InfoSecurityLvBean;
import hr.model.InfoServiceFormBean;
import hr.model.InfoServiceFormDAO;
import hr.model.InfoServiceTypeBean;

@Repository("infoServiceFormDAOHibernate")
public class InfoServiceFormDAOHibernate implements InfoServiceFormDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public InfoServiceFormBean select(int id) {
		return (InfoServiceFormBean)this.getSession().get(InfoServiceFormBean.class, id);
	}
	
	public List<Integer> selectByCondition(DepInfoBean depNo,Date start,Date over,EmpInfoBean contractor,String fileNo,Integer firstRow,Integer maxRows){
		StringBuilder hql = new StringBuilder("select id from InfoServiceFormBean where file_no is not null");
		if (null != depNo) {
            hql.append(" and applicantDep= :depNo");
        }
		if(null!=start && null!=over) {
			hql.append(" and applicationTime>= :start and applicationTime< :over");
		}
		if(null != contractor) {
			hql.append(" and contractor= :contractor");
		}
		if(null != fileNo && !"".equals(fileNo)) {
			hql.append(" and fileNo= :fileNo");
		}
		hql.append(" order by id desc"); 
		Query query = this.getSession().createQuery(hql.toString());
		if (null != depNo) {
			query.setParameter("depNo", depNo);
        }
		if(null!=start && null!=over) {
			query.setParameter("start", start);
			query.setParameter("over", over);
		}
		if(null != contractor) {
			query.setParameter("contractor", contractor);
		}
		if(null != fileNo && !"".equals(fileNo)) {
			query.setParameter("fileNo", fileNo);
		}
		if (null != firstRow) {
            query.setFirstResult(firstRow);
        }
        if (null != maxRows) {
            query.setMaxResults(maxRows);
        }
		
		
		return query.list();
	}
	
	public List<Integer> selectByUndone(){
		StringBuilder hql = new StringBuilder("select id from InfoServiceFormBean where file_no is null order by id desc");
		Query query = this.getSession().createQuery(hql.toString());
		
		return query.list();
	}

	public List<InfoServiceFormBean> select() {
		return this.getSession().createQuery("from InfoServiceFormBean").list();
	}
	
	public List<InfoServiceFormBean> selectByReceiver(EmpInfoBean receiverId){
		Query query = this.getSession().createQuery("from InfoServiceFormBean where receiver=?");
		query.setParameter(0, receiverId);
		return query.list();
	}
	
	public List<InfoServiceFormBean> selectByStage(Double stage) {
		Query query = this.getSession().createQuery("from InfoServiceFormBean where stage=?");
		query.setParameter(0, stage);
		return query.list();
	}
	
	public List<InfoServiceFormBean> selectByContractor(EmpInfoBean contractor){
		Query query = this.getSession().createQuery("from InfoServiceFormBean where contractor=? and stage=2");
		query.setParameter(0, contractor);
		return query.list();
	}
	
    public List<InfoServiceFormBean> selectByApplicantDep(DepInfoBean depNo){
    	Query query = this.getSession().createQuery("from InfoServiceFormBean where applicantDep=? and stage=1");
		query.setParameter(0, depNo);
		return query.list();
    }
	
	public List<InfoServiceFormBean> selectByVerification(EmpInfoBean id){
		Query query = this.getSession().createQuery("from InfoServiceFormBean where verification=? and stage=3");
		query.setParameter(0, id);
		return query.list();
	}

	public Boolean insert(InfoServiceFormBean bean) {
		if(bean!=null){
			InfoServiceFormBean temp = this.select(bean.getId());
			if(temp==null){
				this.getSession().save(bean);
				return true;
			}
		}
		return false;
	}

	public Boolean update(InfoServiceFormBean bean) {
		if(bean!=null){
			InfoServiceFormBean temp = this.select(bean.getId());
			if(temp!=null){
				temp.setApplicationTime(bean.getApplicationTime());
				temp.setFileNo(bean.getFileNo());
				temp.setType(bean.getType());
				temp.setApplicant(bean.getApplicant());
				temp.setApplicantDep(bean.getApplicantDep());
				temp.setContractor(bean.getContractor());
				temp.setContractorDep(bean.getContractorDep());
				temp.setDemand(bean.getDemand());
				temp.setInfoServiceTypeBean(bean.getInfoServiceTypeBean());
				temp.setEventType(bean.getEventType());
				temp.setEventRemark(bean.getEventRemark());
				temp.setInfoSecurityLvBean(bean.getInfoSecurityLvBean());
				temp.setProcessWay(bean.getProcessWay());
				temp.setpStartTime(bean.getpStartTime());
				temp.setpEndTime(bean.getpEndTime());
				temp.setpTotalTime(bean.getpTotalTime());
				temp.setCorrection(bean.getCorrection());
				temp.setcEstimated(bean.getcEstimated());
				temp.setcActual(bean.getcActual());
				temp.setcTotal(bean.getcTotal());
				temp.setImprovement(bean.getImprovement());
				temp.setiEstimated(bean.getiEstimated());
				temp.setiActual(bean.getiActual());
				temp.setiTotal(bean.getiTotal());
				temp.setVerification(bean.getVerification());
				temp.setReturnReason(bean.getReturnReason());
				temp.setRemark(bean.getRemark());
				temp.setStage(bean.getStage());
				temp.setReceiver(bean.getReceiver());
			}
			return true;
		}
		return false;
	}

	public Boolean delete(int id) {
		InfoServiceFormBean bean = this.select(id);
		if(bean!=null){
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}
	
	public Integer count() {
		Query query = this.getSession().createQuery("SELECT COUNT(*) FROM InfoServiceFormBean");
		return ((Long)query.uniqueResult()).intValue();
	}
	
	public Integer count(EmpInfoBean receiver) {
		Query query = this.getSession().createQuery("SELECT COUNT(*) FROM InfoServiceFormBean  WHERE receiver=?");
		query.setParameter(0, receiver);
		return ((Long)query.uniqueResult()).intValue();
	}

	public static void main(String[] arg){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		
		InfoServiceFormDAOHibernate dao = (InfoServiceFormDAOHibernate)context.getBean("infoServiceFormDAOHibernate");
		InfoServiceFormBean temp = new InfoServiceFormBean();
		EmpInfoBean empInfoBean = new EmpInfoBean();
		empInfoBean.setId(1);
		DepInfoBean depInfoBean = new DepInfoBean();
		depInfoBean.setNo(1);
		InfoServiceTypeBean infoServiceTypeBean = new InfoServiceTypeBean();
		infoServiceTypeBean.setNo(1);
		InfoSecurityLvBean infoSecurityLvBean = new InfoSecurityLvBean();
		infoSecurityLvBean.setLv(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		Date zzz = null;
		try {
			zzz = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		temp.setId(1);
		temp.setApplicationTime(zzz);
//		temp.setFileNo("XXX");
		temp.setType("急件");
		temp.setApplicant(empInfoBean);
		temp.setApplicantDep(depInfoBean);
//		temp.setContractor(empInfoBean);
		temp.setContractorDep(depInfoBean);
		temp.setDemand("123");
//		temp.setInfoServiceTypeBean(infoServiceTypeBean);
//		temp.setEventType("非資安事件");
//		temp.setInfoSecurityLvBean(infoSecurityLvBean);
//		temp.setProcessWay("XXX");
//		temp.setpStartTime(zzz);
//		temp.setpEndTime(zzz);
//		temp.setpTotalTime(zzz);
//		temp.setCorrection(null);
//		temp.setcEstimated(null);
//		temp.setcActual(null);
//		temp.setcTotal(null);
//		temp.setImprovement(null);
//		temp.setiEstimated(null);
//		temp.setiActual(null);
//		temp.setiTotal(null);
//		temp.setVerification(null);
//		temp.setRemark(null);
		temp.setStage(1.0);
		temp.setReceiver(empInfoBean);
	    
//		EmpInfoBean xxx = new EmpInfoBean();
//		xxx.setId(8);
		DepInfoBean depInfoBean2 = new DepInfoBean();
		depInfoBean2.setNo(5);
		Date start = null;
		try {
			start = sdf.parse("2017-09-01 00:55");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date over = null;
		try {
			over = sdf.parse("2017-09-03 00:55");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EmpInfoBean empInfoBean2 = new EmpInfoBean();
		empInfoBean2.setId(1);
		
//		List<Integer> ooo2 = dao.selectByCondition(null, null, null, null, null, 0, 10);
//		List<Integer> ooo2 = dao.selectByCondition(depInfoBean2, start, over, empInfoBean2, null, 0, 10);
		Integer ooo2 = dao.count();
		System.out.println(ooo2);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext)context).close();
	}
}
