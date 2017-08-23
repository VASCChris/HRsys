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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name = "job")
public class JobInfoBean implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
	private int no;
	@Column(name = "name")
	private String name;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="jobInfoBean")
	private Set<EmpInfoBean> emps = new HashSet<EmpInfoBean>();
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
	public Set<EmpInfoBean> getEmps() {
		return emps;
	}
	public void setEmps(Set<EmpInfoBean> emps) {
		this.emps = emps;
	}
	
	public JSONObject toJSONObject(){
		JSONObject jobInfoBean = new JSONObject();
		jobInfoBean.put("no", no);
		jobInfoBean.put("name", name);
		
		return jobInfoBean;
	}
	
	@Override
	public String toString() {
		return "JobInfoBean [no=" + no + ", name=" + name + "]";
	}
	
		

}
