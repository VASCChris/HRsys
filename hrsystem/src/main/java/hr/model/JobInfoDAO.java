package hr.model;

import java.util.List;

public interface JobInfoDAO {

	JobInfoBean select(int no);

	List<JobInfoBean> select();

	Boolean insert(JobInfoBean bean);

	JobInfoBean update(JobInfoBean bean);

	Boolean delete(int no);
}
