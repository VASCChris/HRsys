package controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.model.DepInfoBean;
import hr.model.DepInfoDAO;
import hr.model.EmpInfoBean;
import hr.model.EmpInfoDAO;
import hr.model.EmpInfoService;
import hr.model.InfoSecurityLvBean;
import hr.model.InfoServiceFormBean;
import hr.model.InfoServiceFormService;
import hr.model.InfoServiceTypeBean;
import mail.MailService;
import mail.MailThread;
import net.sf.json.JSONObject;

@Controller
@Scope("session")
@RequestMapping("/infoservice")
public class InfoServiceFormController implements Serializable{
	
	@Autowired
    private InfoServiceFormService infoServiceFormService;
	@Autowired
    private EmpInfoService empInfoService;
	@Autowired
    private MailService mailService;
	@Autowired
	private EmpInfoDAO empInfoDAOHibernate;
	@Autowired
	private DepInfoDAO depInfoDAOHibernate;
	
	
	@RequestMapping(value = "/get/applicantdep", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getApplicantDep(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		
		EmpInfoBean emp = (EmpInfoBean)session.getAttribute("loginToken");
		
		int depNo = emp.getDepInfoBean().getNo();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("applicantDepNo", Integer.toString(depNo));
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String list() throws Exception {
		return infoServiceFormService.iSFList().toString();
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findById(String num) throws Exception {
		int id = 0;
		try {
			id = Integer.parseInt(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infoServiceFormService.iSF(id).toString();
	}
	
	@RequestMapping(value = "/list/receiver", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findByReceiver(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		EmpInfoBean emp = (EmpInfoBean)session.getAttribute("loginToken");
		int empId = emp.getId();
		return infoServiceFormService.iSFListByReceiver(empId).toString();
	}
	
	
	@RequestMapping(value = "/list/stage/six", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findByStage6() throws Exception {
        
		return infoServiceFormService.iSFListByStage(6.0).toString();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String save(String type, String applicant, String applicantDep, String contractorDep, String demand) throws Exception {

		
		InfoServiceFormBean bean = new InfoServiceFormBean();
		EmpInfoBean applicantBean = new EmpInfoBean();
		DepInfoBean applicantDepBean = new DepInfoBean();
		DepInfoBean contractorDepBean = new DepInfoBean();
		EmpInfoBean receiverBean = new EmpInfoBean();
		
		int applicantId = 0;
		try {
			applicantId = Integer.parseInt(applicant);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int applicantDepNo = 0;
		try {
			applicantDepNo = Integer.parseInt(applicantDep);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int contractorDepNo = 0;
		try {
			contractorDepNo = Integer.parseInt(contractorDep);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		applicantBean.setId(applicantId);
		applicantDepBean.setNo(applicantDepNo);
		contractorDepBean.setNo(contractorDepNo);
		receiverBean.setId(empInfoService.getSupervisorId(applicantDepNo));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		bean.setId(0);
		bean.setType(type);
		bean.setApplicationTime(date);
		bean.setApplicant(applicantBean);
		bean.setApplicantDep(applicantDepBean);
		bean.setContractorDep(contractorDepBean);
		bean.setDemand(demand);
		bean.setStage(1.0);
		bean.setReceiver(receiverBean);
		
		
		infoServiceFormService.insert(bean);
		String mailTo = empInfoDAOHibernate.select(receiverBean.getId()).getAccount();
		String receiveName = empInfoDAOHibernate.select(receiverBean.getId()).getName();
		String applicantName = empInfoDAOHibernate.select(applicantId).getName();
		int applicantExt = empInfoDAOHibernate.select(applicantId).getExt();
		String depName = depInfoDAOHibernate.select(applicantDepNo).getName();
		String mailTitle = "資訊服務申請單簽核通知";
		String content = receiveName+" 您好!\r\n" + 
				"有一張"+ applicantName +"申請的資訊服務申請單等待您的簽核，請盡快處理喔！\r\n" + 
				"類別 : "+type+"\r\n" + 
				"階段 : 1\r\n" + 
				"申請時間 : "+date+"\r\n" + 
				"申請部門 : "+depName+"\r\n" + 
				"申請人 : "+applicantName+"\r\n" + 
				"分機 : "+applicantExt+"\r\n" + 
				"需求 : "+demand+"\r\n \r\n \r\n" + 
				"http://192.168.0.9:8080/hrsystem/index.jsp";
		Thread thread = new MailThread(mailTo, content, mailTitle, mailService);
		thread.start();
		
		return infoServiceFormService.iSFList().toString();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String update(String id,String applicationTime,String fileNo,String type,String applicantId,String applicantDepNo,String contractorId,String contractorDepNo,String demandContent,String infoServiceTypeNo,String event,String eventRemark,String securityLv,String processWay,String pStartTime,String pEndTime,String correction,String cEstimated,String cActual,String improvement,String iEstimated,String iActual,String verificationId,String returnReason,String remark,String stage) throws Exception {
        
        
		InfoServiceFormBean bean = new InfoServiceFormBean();
		EmpInfoBean applicantBean = new EmpInfoBean();
		DepInfoBean applicantDepBean = new DepInfoBean();
		EmpInfoBean contractorBean = new EmpInfoBean();
		DepInfoBean contractorDepBean = new DepInfoBean();
		InfoServiceTypeBean infoServiceTypeBean = new InfoServiceTypeBean();
		InfoSecurityLvBean infoSecurityLvBean = new InfoSecurityLvBean();
		EmpInfoBean verificationBean = new EmpInfoBean();
		EmpInfoBean receiverBean = new EmpInfoBean();
		EmpInfoBean superAdmin = empInfoDAOHibernate.selectByCharactor("superadmin");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		//資料轉換
		int idNo = 0;
		try {
			idNo = Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Date applicationTimeSDF = sdf.parse(applicationTime);
		
		Integer applicantIdNo = null;
		try {
			applicantIdNo = Integer.parseInt(applicantId);
		} catch (Exception e) {
			applicantIdNo = null;
		}
		
		Integer applicantDepNum = null;
		try {
			applicantDepNum = Integer.parseInt(applicantDepNo);
		} catch (Exception e2) {
			applicantDepNum = null;
		}
		
		Integer contractorIdNo = null;
		try {  
			contractorIdNo = Integer.parseInt(contractorId);
		} catch (Exception e1) {
			contractorIdNo = null;
		}
		
		Integer contractorDepNum = null;
		try {
			contractorDepNum = Integer.parseInt(contractorDepNo);
		} catch (Exception e) {
			contractorDepNum = null;
		}
		
		Integer infoServiceTypeNum = null;
		try {
			infoServiceTypeNum = Integer.parseInt(infoServiceTypeNo);
		} catch (Exception e) {
			infoServiceTypeNum = null;
		}
		
		Integer securityLevel = null;
		try {
			securityLevel = Integer.parseInt(securityLv);
		} catch (Exception e) {
			securityLevel = null;
		}
		
		Double stageNo = null;
		try {
			stageNo = Double.parseDouble(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Date pStart = null;
		try {
			pStart = sdf.parse(pStartTime);
		} catch (NullPointerException e1) {
			if(stageNo==2.0 || stageNo==3.0 || stageNo==99.0) {
				pStart = null;
			}else {
				e1.printStackTrace();
			}
		}
		Date pEnd = null;
		try {
			pEnd = sdf.parse(pEndTime);
		} catch (NullPointerException e1) {
			if(stageNo==2.0 || stageNo==3.0|| stageNo==99.0) {
				pEnd = null;
			}else {
				e1.printStackTrace();
			}
		}
		long pTotal = 0;
		try {
			pTotal = (pEnd.getTime() - pStart.getTime())/(1000*60);
		} catch (NullPointerException e2) {
			pTotal = 0;
		}
		
		Date cEstComplete = null;
		try {
			cEstComplete = sdf.parse(cEstimated);
		} catch (NullPointerException e1) {
			if(stageNo==2.0 || stageNo==3.0 || stageNo==99.0) {
				cEstComplete = null;
			}else {
				e1.printStackTrace();
			}
		}
		Date cActComplete = null;
		try {
			cActComplete = sdf.parse(cActual);
		} catch (NullPointerException e1) {
			if(stageNo==2.0 || stageNo==3.0 || stageNo==99.0) {
				cActComplete = null;
			}else {
				e1.printStackTrace();
			}
		}
		long cTotal = 0;
		try {
			cTotal = (cActComplete.getTime() - cEstComplete.getTime())/(1000*60);
		} catch (NullPointerException e2) {
			cTotal = 0;
		}
		
		Date iEstComplete = null;
		try {
			iEstComplete = sdf.parse(iEstimated);
		} catch (NullPointerException e1) {
			if(stageNo==2.0 || stageNo==3.0 || stageNo==99.0) {
				iEstComplete = null;
			}else {
				e1.printStackTrace();
			}
		}
		Date iActComplete = null;
		try {
			iActComplete = sdf.parse(iActual);
		} catch (NullPointerException e1) {
			if(stageNo==2.0 || stageNo==3.0 || stageNo==99.0) {
				iActComplete = null;
			}else {
				e1.printStackTrace();
			}
		}
		long iTotal = 0;
		try {
			iTotal = (iActComplete.getTime() - iEstComplete.getTime())/(1000*60);
		} catch (NullPointerException e1) {
			iTotal = 0;
		}
		
		Integer verificationIdNo = null;
		try {
			verificationIdNo = Integer.parseInt(verificationId);
		} catch (Exception e) {
			verificationIdNo = null;
		}
		
		
		
		applicantBean.setId(applicantIdNo);
		applicantDepBean.setNo(applicantDepNum);
		try {
			contractorBean.setId(contractorIdNo);
		} catch (NullPointerException e3) {
			contractorBean = null;
		}
		contractorDepBean.setNo(contractorDepNum);
		try {
			infoServiceTypeBean.setNo(infoServiceTypeNum);
		} catch (NullPointerException e2) {
			infoServiceTypeBean = null;
		}
		try {
			infoSecurityLvBean.setLv(securityLevel);
		} catch (NullPointerException e1) {
			infoSecurityLvBean = null;
		}
		try {
			verificationBean.setId(verificationIdNo);
		} catch (NullPointerException e) {
			verificationBean = null;
		}
		//指定下一階段接收者
		if(stageNo==2.0) {
			receiverBean.setId(empInfoService.getSupervisorId(contractorDepNum));
		}else if(stageNo==3.0) {
			receiverBean.setId(contractorIdNo);
		}else if(stageNo==4.0) {
			receiverBean.setId(verificationIdNo);
		}else if(stageNo==4.5) {
			receiverBean.setId(contractorIdNo);
		}else if(stageNo==5.0) {
			//資訊服務申請單管理人員,目前是Dori(12)
			receiverBean.setId(superAdmin.getId());
		}else {
			//跑完全部流程後,不會有接收人
			receiverBean = null;
		}
		
		
		
		bean.setId(idNo);
		bean.setApplicationTime(applicationTimeSDF);
		bean.setFileNo(fileNo);
		bean.setType(type);
		bean.setApplicant(applicantBean);
		bean.setApplicantDep(applicantDepBean);
		bean.setContractor(contractorBean);
		bean.setContractorDep(contractorDepBean);
		bean.setDemand(demandContent);
		bean.setInfoServiceTypeBean(infoServiceTypeBean);
		bean.setEventType(event);
		bean.setEventRemark(eventRemark);
		bean.setInfoSecurityLvBean(infoSecurityLvBean);
		bean.setProcessWay(processWay);
		bean.setpStartTime(pStart);
		bean.setpEndTime(pEnd);
		bean.setpTotalTime(pTotal);
		bean.setCorrection(correction);
		bean.setcEstimated(cEstComplete);
		bean.setcActual(cActComplete);
		bean.setcTotal(cTotal);
		bean.setImprovement(improvement);
		bean.setiEstimated(iEstComplete);
		bean.setiActual(iActComplete);
		bean.setiTotal(iTotal);
		bean.setVerification(verificationBean);
		bean.setReturnReason(returnReason);
		bean.setRemark(remark);
		bean.setStage(stageNo);
		bean.setReceiver(receiverBean);
		
		if(stageNo==99.0) {
			infoServiceFormService.delete(bean.getId());
			return infoServiceFormService.iSFList().toString();
		}
		
		infoServiceFormService.update(bean);
		
		String mailTo = null; //主要收件人
		try {
			mailTo = empInfoDAOHibernate.select(receiverBean.getId()).getAccount();
		} catch (NullPointerException e) {
			mailTo = null;
		}
		String applicantMail = null; 
		try {
			applicantMail = empInfoDAOHibernate.select(applicantIdNo).getAccount();
		} catch (NullPointerException e1) {
			applicantMail = null;
		}
		String applicantSupervisorMail = null;
		try {
			applicantSupervisorMail = empInfoDAOHibernate.select(empInfoService.getSupervisorId(applicantDepNum)).getAccount();
		
		} catch (Exception e1) {
			applicantSupervisorMail = null;
		}
		String contractorMail = null;
		try {
			contractorMail = empInfoDAOHibernate.select(contractorIdNo).getAccount();
		} catch (NullPointerException e1) {
			contractorMail = null;
		}
		String contractorSupervisorMail = null;
		try {
			contractorSupervisorMail = empInfoDAOHibernate.select(empInfoService.getSupervisorId(contractorDepNum)).getAccount();
		} catch (Exception e1) {
			contractorSupervisorMail = null;
		}
		String receiveName = null;
		try {
			receiveName = empInfoDAOHibernate.select(receiverBean.getId()).getName();
		} catch (NullPointerException e) {
			receiveName = null;
		}
		String contractorName=null;
		try {
			contractorName = empInfoDAOHibernate.select(contractorIdNo).getName();
		} catch (NullPointerException e) {
			contractorName=null;
		}
		String applicantName = empInfoDAOHibernate.select(applicantIdNo).getName();
		int applicantExt = empInfoDAOHibernate.select(applicantIdNo).getExt();
		String depName = depInfoDAOHibernate.select(applicantDepNum).getName();
		String content1 = receiveName+" 您好!\r\n" + 
				"有一張"+applicantName+"申請的資訊服務申請單等待您的簽核，請盡快處理喔！\r\n" + 
				"類別 : "+type+"\r\n" + 
				"表單流水號 : "+id+"\r\n" + 
				"階段 : "+stageNo+"\r\n" + 
				"申請時間 : "+applicationTime+"\r\n" + 
				"申請部門 : "+depName+"\r\n" + 
				"申請人 : "+applicantName+"\r\n" + 
				"分機 : "+applicantExt+"\r\n" + 
				"需求 : "+demandContent+"\r\n \r\n \r\n" + 
				"http://192.168.0.9:8080/hrsystem/index.jsp";
		
		String content2 = "您好!\r\n" + 
				"有一張"+applicantName+"申請的資訊服務申請單進行到第"+stageNo+"階段！\r\n" + 
				"類別 : "+type+"\r\n" + 
				"表單流水號 : "+id+"\r\n" + 
				"申請時間 : "+applicationTime+"\r\n" + 
				"申請部門 : "+depName+"\r\n" + 
				"申請人 : "+applicantName+"\r\n" + 
				"分機 : "+applicantExt+"\r\n" + 
				"承辦人 : "+contractorName+"\r\n" + 
				"需求 : "+demandContent+"\r\n \r\n \r\n";
		
		if(receiverBean!=null) {
			String mailTitle = "資訊服務申請單簽核通知";
			Thread thread = new MailThread(mailTo, content1, mailTitle, mailService);
			thread.start();
		}
		if(stageNo==3.0) {                                    //資訊服務申請單管理人員,目前是Dori(12)
			String mailTitle = "資訊服務申請單進度通知";
			Thread thread = new MailThread(superAdmin.getAccount(), content2, mailTitle, mailService);
			thread.start();
		}else if(stageNo==5.0) {
			String mailTitle = "資訊服務申請單進度通知";
			Thread thread1 = new MailThread(applicantMail, content2, mailTitle, mailService);
			Thread thread2 = new MailThread(applicantSupervisorMail, content2, mailTitle, mailService);
			Thread thread3 = new MailThread(contractorMail, content2, mailTitle, mailService);
			Thread thread4 = new MailThread(contractorSupervisorMail, content2, mailTitle, mailService);
			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();
		}
		
		return infoServiceFormService.iSFList().toString();
	}
}
