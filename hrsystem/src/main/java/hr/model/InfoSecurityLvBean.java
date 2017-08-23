package hr.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name = "info_security_lv")
public class InfoSecurityLvBean implements Serializable{
	@Id
    @Column(name = "lv")
	private int lv;
	@Column(name = "content")
	private String content;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="infoSecurityLvBean")
	private Set<InfoServiceFormBean> is = new HashSet<InfoServiceFormBean>();
	
	
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<InfoServiceFormBean> getIs() {
		return is;
	}
	public void setIs(Set<InfoServiceFormBean> is) {
		this.is = is;
	}
	public JSONObject toJSONObject(){
		JSONObject infoSecurityLvBean = new JSONObject();
		infoSecurityLvBean.put("lv", lv);
		infoSecurityLvBean.put("content", content);
		
		return infoSecurityLvBean;
	}
	@Override
	public String toString() {
		return "InfoSecurityLvBean [lv=" + lv + ", content=" + content + "]";
	}
	
	

}
