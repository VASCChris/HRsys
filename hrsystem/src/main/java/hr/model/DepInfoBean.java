package hr.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name = "department")
public class DepInfoBean implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
	private int no;
    @Column(name = "name")
	private String name;
    @Column(name = "supervisor" , nullable = true)
	private String supervisor;
    @OneToMany(fetch=FetchType.EAGER, mappedBy="depInfoBean")
	private Set<EmpInfoBean> emps = new HashSet<EmpInfoBean>();
    @OneToMany(fetch=FetchType.EAGER, mappedBy="applicantDep")
	private Set<InfoServiceFormBean> applicantDeps = new HashSet<InfoServiceFormBean>();
    @OneToMany(fetch=FetchType.EAGER, mappedBy="contractorDep")
	private Set<InfoServiceFormBean> contractorDeps = new HashSet<InfoServiceFormBean>();
    
    
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public Set<EmpInfoBean> getEmps() {
		return emps;
	}
	public void setEmps(Set<EmpInfoBean> emps) {
		this.emps = emps;
	}
	public Set<InfoServiceFormBean> getApplicantDeps() {
		return applicantDeps;
	}
	public void setApplicantDeps(Set<InfoServiceFormBean> applicantDeps) {
		this.applicantDeps = applicantDeps;
	}
	public Set<InfoServiceFormBean> getContractorDeps() {
		return contractorDeps;
	}
	public void setContractorDeps(Set<InfoServiceFormBean> contractorDeps) {
		this.contractorDeps = contractorDeps;
	}
	
	public JSONObject toJSONObject(){
		JSONObject depInfoBean = new JSONObject();
		depInfoBean.put("no", no);
		depInfoBean.put("name", name);
		depInfoBean.put("supervisor", supervisor);
		
		return depInfoBean;
	}
	
	
	@Override
	public String toString() {
		return "DepInfoBean [no=" + no + ", name=" + name + ", supervisor=" + supervisor + "]";
	}
	
	
	
}