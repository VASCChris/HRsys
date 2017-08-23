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

@Controller
@WebServlet("/management/DepControl.controller")
public class DepControlServlet extends HttpServlet {
	@Autowired
    private DepInfoService depInfoService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		depInfoService = (DepInfoService) context.getBean("depInfoService");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<DepInfoBean> list = depInfoService.depList();
		if(list!=null){
			HttpSession session = request.getSession();
			session.setAttribute("depList", list);
			response.sendRedirect(request.getContextPath()+"/management/department.jsp");
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		//接收資料
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String supervisor = request.getParameter("supervisor");
		String send = request.getParameter("send");
		System.out.println(no+"/"+name+"/"+send);
		
		//驗證資料
		if(name==null||name.trim().length()==0){
			errorMsg.put("name", "部門名稱不可空白!!!");
		}
		
		//轉換資料
		int num = 0;
		if(no!=null && no.trim().length()!=0){
			try {
				num = Integer.parseInt(no);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		if (errorMsg != null && !errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/management/department.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 呼叫model, 根據Model執行結果呼叫View
		DepInfoBean bean = new DepInfoBean();
		bean.setNo(num);
		bean.setName(name);
		bean.setSupervisor(supervisor);
		
		if("insert".equals(send)){
			Boolean insert = depInfoService.insert(bean);
			if(insert==true){
				List<DepInfoBean> list = depInfoService.depList();
				if(list!=null){
					HttpSession session = request.getSession();
					session.setAttribute("depList", list); 
					request.getRequestDispatcher("/management/department.jsp").forward(request, response);
					return;
				}
			}else{
				request.getRequestDispatcher("/management/department.jsp").forward(request, response);
        		return;
			}
		}
		
		if("update".equals(send)){
			Boolean update = depInfoService.update(bean);
			if(update!=false){
				List<DepInfoBean> list = depInfoService.depList();
				if(list!=null){
					HttpSession session = request.getSession();
					session.setAttribute("depList", list); 
					request.getRequestDispatcher("/management/department.jsp").forward(request, response);
					return;
				}
			}else{
				request.getRequestDispatcher("/management/department.jsp").forward(request, response);
        		return;
			}
		}
		
		if("delete".equals(send)){
			Boolean delete = depInfoService.delete(bean.getNo());
			if(delete==true){
				List<DepInfoBean> list = depInfoService.depList();
				if(list!=null){
					HttpSession session = request.getSession();
					session.setAttribute("depList", list);
					request.getRequestDispatcher("/management/department.jsp").forward(request, response);
					return;
				}
			}else{
				request.getRequestDispatcher("/management/department.jsp").forward(request, response);
				return;
			}
		}
		
	}

//	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//		
//		
//		
//		
//		
//		
//	}


}
