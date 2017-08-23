package hr.model;

import java.util.List;

public interface InfoSecurityLvDAO {

	InfoSecurityLvBean select(int lv);
	
	List<InfoSecurityLvBean> select();
}
