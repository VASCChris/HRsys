package hr.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.sf.json.JSONArray;

@Service("infoSecurityLvService")
public class InfoSecurityLvService implements Serializable{

	@Autowired
	private InfoSecurityLvDAO infoSecurityLvDAOHibernate;
	
	public JSONArray iSLList(){
		JSONArray iSLList = new JSONArray();
		List<InfoSecurityLvBean> iSL = infoSecurityLvDAOHibernate.select();
		for (InfoSecurityLvBean infoSecurityLvBean : iSL) {
			iSLList.add(infoSecurityLvBean.toJSONObject());
        }
		return iSLList;
	}
}
