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
import hr.model.dao.DepInfoDAOHibernate;
import hr.model.dao.JobInfoDAOHibernate;

@Controller
@WebServlet("/management/EmpControl.controller")
public class EmpControlServlet extends HttpServlet {
	@Autowired
	private DepInfoService depInfoService;
	@Autowired
	private JobInfoService jobInfoService;
	@Autowired
	private EmpInfoService empInfoService;
	@Autowired
	private DepInfoDAOHibernate depInfoDAOHibernate;
	@Autowired
	private JobInfoDAOHibernate jobInfoDAOHibernate;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		depInfoService = (DepInfoService) context.getBean("depInfoService");
		jobInfoService = (JobInfoService) context.getBean("jobInfoService");
		empInfoService = (EmpInfoService) context.getBean("empInfoService");
		depInfoDAOHibernate = (DepInfoDAOHibernate) context.getBean("depInfoDAOHibernate");
		jobInfoDAOHibernate = (JobInfoDAOHibernate) context.getBean("jobInfoDAOHibernate");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<DepInfoBean> depList = depInfoService.depList();
		List<JobInfoBean> jobList = jobInfoService.jobList();
		List<EmpInfoBean> empList = empInfoService.empInfo(null);
		HttpSession session = request.getSession();
		session.setAttribute("depList", depList);
		session.setAttribute("jobList", jobList);
		session.setAttribute("empList", empList);
		response.sendRedirect(request.getContextPath() + "/management/employee.jsp");
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		//接收資料
		String id = request.getParameter("id");
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
		
		//驗證資料
		if(empNo==null||empNo.trim().length()==0){
			errorMsg.put("empNo", "欄位不可空白!!!");
		}
		if(name==null||name.trim().length()==0){
			errorMsg.put("name", "欄位不可空白!!!");
		}
		if(engName==null||engName.trim().length()==0){
			errorMsg.put("engName", "欄位不可空白!!!");
		}
		if(ext==null||ext.trim().length()==0){
			errorMsg.put("ext", "欄位不可空白!!!");
		}
		//轉換資料
		int idNo = 0;
		if(id!=null && id.trim().length()!=0){
			try {
				idNo = Integer.parseInt(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		int extNo = 0;
		if(ext!=null && ext.trim().length()!=0){
			try {
				extNo = Integer.parseInt(ext);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		int depNum = 0;
		if(depNo!=null && depNo.trim().length()!=0){
			try {
				depNum = Integer.parseInt(depNo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		int jobNum = 0;
		if(jobNo!=null && jobNo.trim().length()!=0){
			try {
				jobNum = Integer.parseInt(jobNo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		if (errorMsg != null && !errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/management/employee.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 呼叫model, 根據Model執行結果呼叫View
		EmpInfoBean bean = new EmpInfoBean();
		DepInfoBean depInfoBean = new DepInfoBean();
		JobInfoBean jobInfoBean = new JobInfoBean();
		depInfoBean.setNo(depNum);
		jobInfoBean.setNo(jobNum);
		//塞個name給bean物件,不然前端抓不到
		depInfoBean.setName(depInfoDAOHibernate.select(depNum).getName());
		jobInfoBean.setName(jobInfoDAOHibernate.select(jobNum).getName());
		
        bean.setId(idNo);
        bean.setAccount(account);
        bean.setPassword(password);
		bean.setEmpNo(empNo);
		bean.setName(name);
		bean.setEngName(engName);
		bean.setExt(extNo);
		bean.setDepInfoBean(depInfoBean);
		bean.setJobInfoBean(jobInfoBean);
		bean.setCharacter(character);
		
		if("update".equals(send)){
			Boolean update = empInfoService.update(bean);
			if(update!=false){
				List<DepInfoBean> depList = depInfoService.depList();
				List<JobInfoBean> jobList = jobInfoService.jobList();
				List<EmpInfoBean> empList = empInfoService.empInfo(null);
				HttpSession session = request.getSession();
				session.setAttribute("depList", depList);
				session.setAttribute("jobList", jobList);
				session.setAttribute("empList", empList);
				request.getRequestDispatcher("/management/employee.jsp").forward(request, response);
				return;
			}else{
				request.getRequestDispatcher("/management/employee.jsp").forward(request, response);
        		return;
			}
		}
		
		if("delete".equals(send)){
			Boolean delete = empInfoService.delete(bean.getId());
			if(delete==true){
				List<DepInfoBean> depList = depInfoService.depList();
				List<JobInfoBean> jobList = jobInfoService.jobList();
				List<EmpInfoBean> empList = empInfoService.empInfo(null);
				HttpSession session = request.getSession();
				session.setAttribute("depList", depList);
				session.setAttribute("jobList", jobList);
				session.setAttribute("empList", empList);
				request.getRequestDispatcher("/management/employee.jsp").forward(request, response);
				return;
			}else{
				request.getRequestDispatcher("/management/employee.jsp").forward(request, response);
        		return;
			}
		}
		
		
		
	}

}
