package hr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name = "info_service_form")
public class InfoServiceFormBean implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	@Column(name="application_time")
	private Date applicationTime;
	@Column(name="file_no")
	private String fileNo;
	@Column(name="type")
	private String type;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name ="applicant")
	private EmpInfoBean applicant;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name ="applicant_dep_no")
	private DepInfoBean applicantDep;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name ="contractor")
	private EmpInfoBean contractor;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name ="contractor_dep_no")
	private DepInfoBean contractorDep;
	@Column(name="demand")
	private String demand;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name ="is_type")
	private InfoServiceTypeBean infoServiceTypeBean;
	@Column(name="event_type")
	private String eventType;
	@Column(name="event_remark")
	private String eventRemark;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name ="info_security_lv")
	private InfoSecurityLvBean infoSecurityLvBean;
	@Column(name="process_way")
	private String processWay;
	@Column(name="process_start_time")
	private Date pStartTime;
	@Column(name="process_end_time")
	private Date pEndTime;
	@Column(name="process_total_time")
	private Long pTotalTime;
	@Column(name="correction")
	private String correction;
	@Column(name="c_estimated_complete")
	private Date cEstimated;
	@Column(name="c_actual_complete")
	private Date cActual;
	@Column(name="c_total_time")
	private Long cTotal;
	@Column(name="improvement")
	private String improvement;
	@Column(name="i_estimated_complete")
	private Date iEstimated;
	@Column(name="i_actual_complete")
	private Date iActual;
	@Column(name="i_total_time")
	private Long iTotal;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name ="verification")
	private EmpInfoBean verification;
	@Column(name="return_reason")
	private String returnReason;
	@Column(name="remark")
	private String remark;
	@Column(name="stage")
	private Double stage;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="receiver")
	private EmpInfoBean receiver;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EmpInfoBean getApplicant() {
		return applicant;
	}

	public void setApplicant(EmpInfoBean applicant) {
		this.applicant = applicant;
	}

	public DepInfoBean getApplicantDep() {
		return applicantDep;
	}

	public void setApplicantDep(DepInfoBean applicantDep) {
		this.applicantDep = applicantDep;
	}

	public EmpInfoBean getContractor() {
		return contractor;
	}

	public void setContractor(EmpInfoBean contractor) {
		this.contractor = contractor;
	}

	public DepInfoBean getContractorDep() {
		return contractorDep;
	}

	public void setContractorDep(DepInfoBean contractorDep) {
		this.contractorDep = contractorDep;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public InfoServiceTypeBean getInfoServiceTypeBean() {
		return infoServiceTypeBean;
	}

	public void setInfoServiceTypeBean(InfoServiceTypeBean infoServiceTypeBean) {
		this.infoServiceTypeBean = infoServiceTypeBean;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventRemark() {
		return eventRemark;
	}

	public void setEventRemark(String eventRemark) {
		this.eventRemark = eventRemark;
	}

	public InfoSecurityLvBean getInfoSecurityLvBean() {
		return infoSecurityLvBean;
	}

	public void setInfoSecurityLvBean(InfoSecurityLvBean infoSecurityLvBean) {
		this.infoSecurityLvBean = infoSecurityLvBean;
	}

	public String getProcessWay() {
		return processWay;
	}

	public void setProcessWay(String processWay) {
		this.processWay = processWay;
	}

	public Date getpStartTime() {
		return pStartTime;
	}

	public void setpStartTime(Date pStartTime) {
		this.pStartTime = pStartTime;
	}

	public Date getpEndTime() {
		return pEndTime;
	}

	public void setpEndTime(Date pEndTime) {
		this.pEndTime = pEndTime;
	}

	public Long getpTotalTime() {
		return pTotalTime;
	}

	public void setpTotalTime(Long pTotalTime) {
		this.pTotalTime = pTotalTime;
	}

	public String getCorrection() {
		return correction;
	}

	public void setCorrection(String correction) {
		this.correction = correction;
	}

	public Date getcEstimated() {
		return cEstimated;
	}

	public void setcEstimated(Date cEstimated) {
		this.cEstimated = cEstimated;
	}

	public Date getcActual() {
		return cActual;
	}

	public void setcActual(Date cActual) {
		this.cActual = cActual;
	}

	public Long getcTotal() {
		return cTotal;
	}

	public void setcTotal(Long cTotal) {
		this.cTotal = cTotal;
	}

	public String getImprovement() {
		return improvement;
	}

	public void setImprovement(String improvement) {
		this.improvement = improvement;
	}

	public Date getiEstimated() {
		return iEstimated;
	}

	public void setiEstimated(Date iEstimated) {
		this.iEstimated = iEstimated;
	}

	public Date getiActual() {
		return iActual;
	}

	public void setiActual(Date iActual) {
		this.iActual = iActual;
	}

	public Long getiTotal() {
		return iTotal;
	}

	public void setiTotal(Long iTotal) {
		this.iTotal = iTotal;
	}

	public EmpInfoBean getVerification() {
		return verification;
	}

	public void setVerification(EmpInfoBean verification) {
		this.verification = verification;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getStage() {
		return stage;
	}

	public void setStage(Double stage) {
		this.stage = stage;
	}

	public EmpInfoBean getReceiver() {
		return receiver;
	}

	public void setReceiver(EmpInfoBean receiver) {
		this.receiver = receiver;
	}

	public JSONObject toJSONObject(){
		JSONObject InfoServiceFormBean = new JSONObject();
		InfoServiceFormBean.put("id", id);
		InfoServiceFormBean.put("applicationTime", applicationTime);
		InfoServiceFormBean.put("fileNo", fileNo);
		InfoServiceFormBean.put("type", type);
		InfoServiceFormBean.put("applicantId", applicant.getId());
		InfoServiceFormBean.put("applicant", applicant.getName());
		InfoServiceFormBean.put("ext", applicant.getExt());
		InfoServiceFormBean.put("applicantDepNo", applicantDep.getNo());
		InfoServiceFormBean.put("applicantDep", applicantDep.getName());
		InfoServiceFormBean.put("applicantSupervisor", applicantDep.getSupervisor());
		try {
			InfoServiceFormBean.put("contractorId", contractor.getId());
		} catch (Exception e1) {
			InfoServiceFormBean.put("contractorId", contractor);
		}
		try {
			InfoServiceFormBean.put("contractor", contractor.getName());
		} catch (Exception e1) {
			InfoServiceFormBean.put("contractor", contractor);
		}
		InfoServiceFormBean.put("contractorDepNo", contractorDep.getNo());
		InfoServiceFormBean.put("contractorDep", contractorDep.getName());
		InfoServiceFormBean.put("contractorSupervisor", contractorDep.getSupervisor());
		InfoServiceFormBean.put("demand", demand);
		try {
			InfoServiceFormBean.put("infoServiceTypeNo", infoServiceTypeBean.getNo());
		} catch (Exception e2) {
			InfoServiceFormBean.put("infoServiceTypeNo", infoServiceTypeBean);
		}
		try {
			InfoServiceFormBean.put("infoServiceType", infoServiceTypeBean.getName());
		} catch (Exception e2) {
			InfoServiceFormBean.put("infoServiceType", infoServiceTypeBean);
		}
		InfoServiceFormBean.put("eventType", eventType);
		InfoServiceFormBean.put("eventRemark", eventRemark);
		try {
			InfoServiceFormBean.put("infoSecurityLv", infoSecurityLvBean.getLv());
		} catch (Exception e1) {
			InfoServiceFormBean.put("infoSecurityLv", infoSecurityLvBean);
		}
		try {
			InfoServiceFormBean.put("infoSecurityLvContent", infoSecurityLvBean.getContent());
		} catch (Exception e1) {
			InfoServiceFormBean.put("infoSecurityLvContent", infoSecurityLvBean);
		}
		InfoServiceFormBean.put("processWay", processWay);
		InfoServiceFormBean.put("pStartTime", pStartTime);
		InfoServiceFormBean.put("pEndTime", pEndTime);
		InfoServiceFormBean.put("pTotalTime", pTotalTime);
		InfoServiceFormBean.put("correction", correction);
		InfoServiceFormBean.put("cEstimated", cEstimated);
		InfoServiceFormBean.put("cActual", cActual);
		InfoServiceFormBean.put("cTotal", cTotal);
		InfoServiceFormBean.put("improvement", improvement);
		InfoServiceFormBean.put("iEstimated", iEstimated);
		InfoServiceFormBean.put("iActual", iActual);
		InfoServiceFormBean.put("iTotal", iTotal);
		try {//防止null無法get
			InfoServiceFormBean.put("verificationId", verification.getId());
		} catch (Exception e) {
			InfoServiceFormBean.put("verificationId", verification);
		}
		try {
			InfoServiceFormBean.put("verification", verification.getName());
		} catch (Exception e) {
			InfoServiceFormBean.put("verification", verification);
		}
		InfoServiceFormBean.put("returnReason", returnReason);
		InfoServiceFormBean.put("remark", remark);
		InfoServiceFormBean.put("stage", stage);
		
		
		return InfoServiceFormBean;
	}

	@Override
	public String toString() {
		return "InfoServiceFormBean [id=" + id + ", applicationTime=" + applicationTime + ", fileNo=" + fileNo
				+ ", type=" + type + ", applicant=" + applicant + ", applicantDep=" + applicantDep + ", contractor="
				+ contractor + ", contractorDep=" + contractorDep + ", demand=" + demand + ", infoServiceTypeBean="
				+ infoServiceTypeBean + ", eventType=" + eventType + ", eventRemark=" + eventRemark
				+ ", infoSecurityLvBean=" + infoSecurityLvBean + ", processWay=" + processWay + ", pStartTime="
				+ pStartTime + ", pEndTime=" + pEndTime + ", pTotalTime=" + pTotalTime + ", correction=" + correction
				+ ", cEstimated=" + cEstimated + ", cActual=" + cActual + ", cTotal=" + cTotal + ", improvement="
				+ improvement + ", iEstimated=" + iEstimated + ", iActual=" + iActual + ", iTotal=" + iTotal
				+ ", verification=" + verification + ", returnReason=" + returnReason + ", remark=" + remark
				+ ", stage=" + stage + ", receiver=" + receiver + "]";
	}
	
	
	
	
	
}
