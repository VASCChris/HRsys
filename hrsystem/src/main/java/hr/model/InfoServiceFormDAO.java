package hr.model;

import java.util.List;

public interface InfoServiceFormDAO {
	InfoServiceFormBean select(int id);
	
	List<InfoServiceFormBean> select();
	
	List<InfoServiceFormBean> selectByReceiver(EmpInfoBean receiver);
	
	List<InfoServiceFormBean> selectByStage(Double stage);
	
	List<InfoServiceFormBean> selectByContractor(EmpInfoBean contractor);
	
	List<InfoServiceFormBean> selectByApplicantDep(DepInfoBean depNo);
	
	List<InfoServiceFormBean> selectByVerification(EmpInfoBean id);
	
	Boolean insert(InfoServiceFormBean bean);
	
	Boolean update(InfoServiceFormBean bean);
	
	Boolean delete(int id);

}
