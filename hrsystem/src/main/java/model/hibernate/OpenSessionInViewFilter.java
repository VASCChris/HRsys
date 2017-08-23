package model.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class OpenSessionInViewFilter implements Filter {
	private SessionFactory sessionFactory = null;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			sessionFactory.getCurrentSession().beginTransaction();			//pre-processing
			String servletPath=((HttpServletRequest)req).getServletPath();
			
			if(!(servletPath.startsWith("/js")||servletPath.startsWith("/assets"))){
				System.out.println("前往"+servletPath+"的交易開始");
			}
			chain.doFilter(req, resp);
			sessionFactory.getCurrentSession().getTransaction().commit();//post-processing
			if(!(servletPath.startsWith("/js")||servletPath.startsWith("/assets"))){
				System.out.println("從"+servletPath+"的交易結束");
			}
			
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			System.out.println("交易rollback");
			e.printStackTrace();
			chain.doFilter(req, resp);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String sessionFactoryBeanName = config.getInitParameter("sessionFactoryBeanName");
		ServletContext application = config.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory = (SessionFactory)context.getBean(sessionFactoryBeanName);

	}

}
