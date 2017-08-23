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
@Table(name = "info_service_type")
public class InfoServiceTypeBean implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
	private int no;
	@Column(name = "name")
	private String name;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="infoServiceTypeBean")
	private Set<InfoServiceFormBean> is = new HashSet<InfoServiceFormBean>();
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
	public Set<InfoServiceFormBean> getIs() {
		return is;
	}
	public void setIs(Set<InfoServiceFormBean> is) {
		this.is = is;
	}
	
	public JSONObject toJSONObject(){
		JSONObject infoServiceTypeBean = new JSONObject();
		infoServiceTypeBean.put("no", no);
		infoServiceTypeBean.put("name", name);
		
		return infoServiceTypeBean;
	}
	@Override
	public String toString() {
		return "InfoServiceTypeBean [no=" + no + ", name=" + name + "]";
	}

	
}
