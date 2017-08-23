package hr.model;

import java.util.List;

public interface EmpInfoDAO {

	EmpInfoBean select(int id);
	
	EmpInfoBean selectByCharactor(String charactor);
	
	EmpInfoBean selectByName(String name);
	
	EmpInfoBean selectByAccountAndPwd(String account ,String password);
	
	List<EmpInfoBean> select();
	
	List<EmpInfoBean> selectByDep(int depNo);
	
	Boolean insert(EmpInfoBean bean);
	 
	EmpInfoBean update(EmpInfoBean bean);
	
	Boolean delete(int id);
}
