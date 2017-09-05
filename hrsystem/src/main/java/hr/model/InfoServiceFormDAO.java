package hr.model;

import java.util.Date;
import java.util.List;

public interface InfoServiceFormDAO {
	InfoServiceFormBean select(int id);
	
	List<InfoServiceFormBean> select();
	
	List<Integer> selectByCondition(DepInfoBean depNo,Date start,Date over,EmpInfoBean contractor,String fileNo,Integer firstRow,Integer maxRows);
	
	List<Integer> selectByUndone();//查詢未完成申請單
	
	List<InfoServiceFormBean> selectByReceiver(EmpInfoBean receiver);
	
	List<InfoServiceFormBean> selectByStage(Double stage);
	
	List<InfoServiceFormBean> selectByContractor(EmpInfoBean contractor);
	
	List<InfoServiceFormBean> selectByApplicantDep(DepInfoBean depNo);
	
	List<InfoServiceFormBean> selectByVerification(EmpInfoBean id);
	
	Boolean insert(InfoServiceFormBean bean);
	
	Boolean update(InfoServiceFormBean bean);
	
	Boolean delete(int id);

	Integer count(EmpInfoBean receiver);
	
	Integer count();
}
