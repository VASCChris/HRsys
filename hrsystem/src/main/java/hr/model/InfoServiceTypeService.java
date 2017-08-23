package hr.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;

@Service("infoServiceTypeService")
public class InfoServiceTypeService implements Serializable{

	@Autowired
	private InfoServiceTypeDAO infoServiceTypeDAOHibernate;
	
	public JSONArray iSTList(){
		JSONArray iSTList = new JSONArray();
		List<InfoServiceTypeBean> iST = infoServiceTypeDAOHibernate.select();
		for (InfoServiceTypeBean infoServiceTypeBean : iST) {
			iSTList.add(infoServiceTypeBean.toJSONObject());
        }
		return iSTList;
	}
	
	
}
