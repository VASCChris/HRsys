package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import hr.model.DepInfoBean;
import hr.model.DepInfoService;
import hr.model.EmpInfoBean;
import hr.model.EmpInfoService;
import hr.model.JobInfoBean;
import hr.model.JobInfoService;

@Controller
@WebServlet("/employee/Register.controller")
public class RegisterServlet extends HttpServlet {
	@Autowired
	private DepInfoService depInfoService;
	@Autowired
	private JobInfoService jobInfoService;
	@Autowired
	private EmpInfoService empInfoService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		depInfoService = (DepInfoService) context.getBean("depInfoService");
		jobInfoService = (JobInfoService) context.getBean("jobInfoService");
		empInfoService = (EmpInfoService) context.getBean("empInfoService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<DepInfoBean> depList = depInfoService.depList();
		List<JobInfoBean> jobList = jobInfoService.jobList();
		HttpSession session = request.getSession();
		session.setAttribute("depList", depList);
		session.setAttribute("jobList", jobList);
		response.sendRedirect(request.getContextPath() + "/employee/empform.jsp");
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		//接收資料
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String empNo = request.getParameter("empNo");
		String name = request.getParameter("name");
		String engName = request.getParameter("engName");
		String ext = request.getParameter("ext");
		String depNo = request.getParameter("dep");
		String jobNo = request.getParameter("job");
		String character = request.getParameter("character");
		String send = request.getParameter("send");
		int x = empNo.charAt(1);
		System.out.println("X="+x);
		//驗證資料
		if("register".equals(send)){
			if (account == null || account.trim().length() == 0) {
				errorMsg.put("account", "請輸入帳號");
			}
			if(password==null || password.trim().length()==0){
				errorMsg.put("password", "請輸入密碼");
			}
			if(empNo==null || empNo.trim().length()==0){
				errorMsg.put("empNo", "請輸入員工編號");
			}else if(empNo.trim().length()>4 || empNo.charAt(0)!=65 || empNo.charAt(1)>57 || empNo.charAt(1)<48 || empNo.charAt(2)>57 || empNo.charAt(2)<48 || empNo.charAt(3)>57 || empNo.charAt(3)<48){
				errorMsg.put("empNo", "員工編號為A+3數字");
			}
			if(name==null || name.trim().length()==0){
				errorMsg.put("name", "請輸入姓名");
			}
			if(engName==null || engName.trim().length()==0){
				errorMsg.put("engName", "請輸入英文姓名");
			}
			if(ext==null || ext.trim().length()==0){
				errorMsg.put("ext", "請輸入分機");
			}
			if ("0".equals(depNo) ) {
				errorMsg.put("depNo", "請選擇部門");
			}
			if ("0".equals(jobNo) ) {
				errorMsg.put("jobNo", "請選擇職務");
			}
			if ("0".equals(character) ) {
				errorMsg.put("character", "請選擇權限");
			}
		}
		
		// 轉換資料
		int extNo = 0;
		if(ext!=null && ext.trim().length()!=0){
			try {
				extNo = Integer.parseInt(ext);
			} catch (NumberFormatException e) {
				errorMsg.put("ext", "請輸入數字");
			}
		}
		int depNum = 0;
		if(depNo!=null && depNo.trim().length()!=0){
			try {
				depNum = Integer.parseInt(depNo);
			} catch (NumberFormatException e) {
				errorMsg.put("depNo", "請選擇部門");
			}
		}
		int jobNum = 0;
		if(jobNo!=null && jobNo.trim().length()!=0){
			try {
				jobNum = Integer.parseInt(jobNo);
			} catch (NumberFormatException e) {
				errorMsg.put("jobNo", "請選擇職務");
			}
		}
		
		
		if (errorMsg != null && !errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/employee/empform.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 呼叫model, 根據Model執行結果呼叫View
		EmpInfoBean bean = new EmpInfoBean();
		DepInfoBean depInfoBean = new DepInfoBean();
		JobInfoBean jobInfoBean = new JobInfoBean();
		depInfoBean.setNo(depNum);
		jobInfoBean.setNo(jobNum);
		
		bean.setAccount(account);
		bean.setPassword(password);
		bean.setEmpNo(empNo);
		bean.setName(name);
		bean.setEngName(engName);
		bean.setExt(extNo);
		bean.setDepInfoBean(depInfoBean);
		bean.setJobInfoBean(jobInfoBean);
		bean.setCharacter(character);
		
		if ("register".equals(send)) {
			Boolean insert = empInfoService.insert(bean);
			if (insert == true) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return;
			}else{
				request.getRequestDispatcher("/employee/empform.jsp").forward(request, response);
				return;
			}
		}


		


		
	}

}
