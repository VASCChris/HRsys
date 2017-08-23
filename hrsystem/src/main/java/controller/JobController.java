package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.model.DepInfoBean;
import hr.model.DepInfoService;
import hr.model.JobInfoBean;
import hr.model.JobInfoService;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/job")
public class JobController {
	@Autowired
    private JobInfoService jobInfoService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String depList() throws Exception {
		return jobInfoService.jobList().toString();
	}
	
	@RequestMapping(value = "/management", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String depSave(String name) throws Exception {

		JobInfoBean bean = new JobInfoBean();
		bean.setNo(0);
		bean.setName(name);
		Boolean result = jobInfoService.insert(bean);
		if(result){
			return jobInfoService.jobList().toString();
		}
		return "";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String jobUpdate(String no, String name) throws Exception {
		System.out.println("no="+no);
		JobInfoBean bean = new JobInfoBean();
		Integer num = Integer.parseInt(no);
		bean.setNo(num);
		bean.setName(name);
		Boolean result = jobInfoService.update(bean);
		if(result)
		  return jobInfoService.jobList().toString();
		else
		  return null;
	}
	
	
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String depDel(String no) throws Exception {
		
		Integer num = Integer.parseInt(no);
		
		Boolean result = jobInfoService.delete(num);
		if(result)
		  return jobInfoService.jobList().toString();
		else
		  return null;
	}
	

}
