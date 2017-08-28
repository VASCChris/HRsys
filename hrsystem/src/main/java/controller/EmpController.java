package controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.model.DepInfoBean;
import hr.model.EmpInfoBean;
import hr.model.EmpInfoDAO;
import hr.model.EmpInfoService;
import hr.model.JobInfoBean;
import net.sf.json.JSONObject;

@Controller
@Scope("session")
@RequestMapping("/emp")
public class EmpController implements Serializable{
	@Autowired
    private EmpInfoService empInfoService;
	@Autowired
	private EmpInfoDAO empInfoDAOHibernate;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String depList() throws Exception {
		return empInfoService.empList().toString();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String empSave(String account, String password,String empNo,String name,String engName,String ext,String depNo,String jobNo,String character) throws Exception {

		DepInfoBean depInfoBean = new DepInfoBean();
		JobInfoBean jobInfoBean = new JobInfoBean();
		
		depInfoBean.setNo(Integer.parseInt(depNo));
		jobInfoBean.setNo(Integer.parseInt(jobNo));
		
		int extNumber = 0;
		try {
			extNumber = Integer.parseInt(ext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmpInfoBean bean = new EmpInfoBean();
		bean.setId(0);
		bean.setAccount(account);
		bean.setPassword(password);
		bean.setEmpNo(empNo);
		bean.setName(name);
		bean.setEngName(engName);
		bean.setExt(extNumber);
		bean.setDepInfoBean(depInfoBean);
		bean.setJobInfoBean(jobInfoBean);
		bean.setCharacter(character);
		Boolean result = empInfoService.insert(bean);
		if(result){
			return empInfoService.empList().toString();
		}
		return "";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String empUpdate(String id, String account,String empNo,String name,String engName,String ext,String depNo,String jobNo,String character) throws Exception {
		
		DepInfoBean depInfoBean = new DepInfoBean();
		JobInfoBean jobInfoBean = new JobInfoBean();
		
		depInfoBean.setNo(Integer.parseInt(depNo));
		jobInfoBean.setNo(Integer.parseInt(jobNo));
		
		String password = empInfoDAOHibernate.select(Integer.parseInt(id)).getPassword();
		
		int extNumber = 0;
		try {
			extNumber = Integer.parseInt(ext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmpInfoBean bean = new EmpInfoBean();
		bean.setId(Integer.parseInt(id));
		bean.setAccount(account);
		bean.setPassword(password);
		bean.setEmpNo(empNo);
		bean.setName(name);
		bean.setEngName(engName);
		bean.setExt(extNumber);
		bean.setDepInfoBean(depInfoBean);
		bean.setJobInfoBean(jobInfoBean);
		bean.setCharacter(character);
		Boolean result = empInfoService.update(bean);
		if(result)
		  return empInfoService.empList().toString();
		else
		  return null;
	}
	
	@RequestMapping(value = "/pw", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String chagePW(HttpServletRequest request,String oldPW,String newPW,String checkPW) throws Exception {
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		
		EmpInfoBean emp = (EmpInfoBean)session.getAttribute("loginToken");
		String originalPW = emp.getPassword();
		System.out.println(oldPW);
		if(oldPW.equals("") || newPW.equals("") || checkPW.equals("")) {
			jsonObject.put("result", "欄位不可有空白");
			return jsonObject.toString();
		}
		
		if(oldPW.equals(originalPW) && newPW.equals(checkPW)) {
			emp.setPassword(newPW);
			empInfoService.update(emp);
			jsonObject.put("result", "OK");
		}else if(!oldPW.equals(originalPW)){
			jsonObject.put("result", "舊密碼錯誤");
		}else if(!newPW.equals(checkPW)){
			jsonObject.put("result", "新密碼不一致");
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String empDel(String id) throws Exception {
		Integer num = Integer.parseInt(id);
		
		Boolean result = empInfoService.delete(num);
		if(result)
		  return empInfoService.empList().toString();
		else
		  return null;
	}
	
	@RequestMapping(value = "/empsfordep", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String empsForDep(String depNum) throws Exception {
		Integer depNo = Integer.parseInt(depNum);
		return empInfoService.empListByDep(depNo).toString();
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String welcome(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		
		EmpInfoBean emp = (EmpInfoBean)session.getAttribute("loginToken");
		try {
			jsonObject.put("name", emp.getName());
			jsonObject.put("character", emp.getCharacter());
		} catch (NullPointerException e) {
			return null;
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String login(HttpServletRequest request,String account ,String password) throws Exception {
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		
		
		if("".equals(account) || "".equals(password)) {
			jsonObject.put("result", "請輸入帳號密碼");
			return jsonObject.toString();
		}
		
		EmpInfoBean result = empInfoService.login(account, password);
		System.out.println(result);
		if(result==null) {
			jsonObject.put("result", "帳號或密碼錯誤");
		}else {
			jsonObject.put("result", "success");
			session.setAttribute("loginToken", result);
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
	public String logOut(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		
		session.removeAttribute("loginToken");
		jsonObject.put("result", "登出成功");
		
		return jsonObject.toString();
	}
}
