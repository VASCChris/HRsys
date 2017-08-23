package hr.model;

import java.util.List;

public interface DepInfoDAO {
	DepInfoBean select(int no); 
	
	List<DepInfoBean> select();
	
	Boolean insert(DepInfoBean bean);
	
	DepInfoBean update(DepInfoBean bean);
	
	Boolean delete(int no);

}
