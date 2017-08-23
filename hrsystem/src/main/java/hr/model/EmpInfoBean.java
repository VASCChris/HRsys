package hr.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name = "employee")
public class EmpInfoBean implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	@Column(name="account")
	private String account;
	@Column(name="pwd")
	private String password;
	@Column(name="emp_no")
	private String empNo;
	@Column(name="name")
	private String name;
	@Column(name="eng_name")
	private String engName;
	@Column(name="ext")
	private int ext;
	@ManyToOne 
	@JoinColumn(name ="dep_no")
	private DepInfoBean depInfoBean;
	@ManyToOne 
	@JoinColumn(name ="job_no")
	private JobInfoBean jobInfoBean;
	@Column(name="characters")
	private String character;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="applicant")
	private Set<InfoServiceFormBean> applicants = new HashSet<InfoServiceFormBean>();
	@OneToMany(fetch=FetchType.EAGER, mappedBy="contractor")
	private Set<InfoServiceFormBean> contractors = new HashSet<InfoServiceFormBean>();
	@OneToMany(fetch=FetchType.EAGER, mappedBy="verification")
	private Set<InfoServiceFormBean> verifications = new HashSet<InfoServiceFormBean>();
	@OneToMany(fetch=FetchType.EAGER, mappedBy="receiver")
	private Set<InfoServiceFormBean> receivers = new HashSet<InfoServiceFormBean>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public int getExt() {
		return ext;
	}

	public void setExt(int ext) {
		this.ext = ext;
	}

	public DepInfoBean getDepInfoBean() {
		return depInfoBean;
	}

	public void setDepInfoBean(DepInfoBean depInfoBean) {
		this.depInfoBean = depInfoBean;
	}

	public JobInfoBean getJobInfoBean() {
		return jobInfoBean;
	}

	public void setJobInfoBean(JobInfoBean jobInfoBean) {
		this.jobInfoBean = jobInfoBean;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public Set<InfoServiceFormBean> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<InfoServiceFormBean> applicants) {
		this.applicants = applicants;
	}

	public Set<InfoServiceFormBean> getContractors() {
		return contractors;
	}

	public void setContractors(Set<InfoServiceFormBean> contractors) {
		this.contractors = contractors;
	}

	public Set<InfoServiceFormBean> getVerifications() {
		return verifications;
	}

	public void setVerifications(Set<InfoServiceFormBean> verifications) {
		this.verifications = verifications;
	}

	public Set<InfoServiceFormBean> getReceivers() {
		return receivers;
	}

	public void setReceivers(Set<InfoServiceFormBean> receivers) {
		this.receivers = receivers;
	}

	public JSONObject toJSONObject(){
		JSONObject empInfoBean = new JSONObject();
		empInfoBean.put("id", id);
		empInfoBean.put("account", account);
		empInfoBean.put("password", password);
		empInfoBean.put("empNo", empNo);
		empInfoBean.put("name", name);
		empInfoBean.put("engName", engName);
		empInfoBean.put("ext", ext);
		empInfoBean.put("depNo", depInfoBean.getNo());
		empInfoBean.put("depName", depInfoBean.getName());
		empInfoBean.put("jobNo", jobInfoBean.getNo());
		empInfoBean.put("jobName", jobInfoBean.getName());
		empInfoBean.put("character", character);
		
		
		return empInfoBean;
	}
	
	@Override
	public String toString() {
		return "EmpInfoBean [id=" + id + ", account=" + account + ", password=" + password + ", empNo=" + empNo
				+ ", name=" + name + ", engName=" + engName + ", ext=" + ext + ", depNo=" + depInfoBean.getNo()
				+ ", depName=" + depInfoBean.getName()+ ", jobNo=" + jobInfoBean.getNo()
				+ ", jobName=" + jobInfoBean.getName() + ", character=" + character + "]";
	}
	
	
	
}
