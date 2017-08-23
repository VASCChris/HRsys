package hr.model;

import java.util.List;

public interface InfoServiceTypeDAO {

	InfoServiceTypeBean select(int no);
	
	List<InfoServiceTypeBean> select();
}
