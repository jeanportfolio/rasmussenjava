package ci.inventory.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebFilter("/*")
public class SessionFilters implements Filter {
	
	private HttpSession session;
	
    public SessionFilters() {
    	
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("Initialization of the SessionFilter");
   
	}
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Passe par le filter");
		
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		
		session = req.getSession(false);
		
		if(session == null) {
			System.out.println("request.getRequestDispatcher ----  "+ req.getRequestURL());
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else if(!req.isRequestedSessionIdValid()){
			System.out.println("resp.sendRedirect ----  "+ req.getRequestURL());
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}else if(session.getAttribute("user") == null){
			System.out.println("resp.sendRedirect not connected ----  "+ session);
			if(req.getParameter("login") != null && req.getParameter("pwd") != null &&
					req.getRequestURL().indexOf(req.getContextPath().concat("/login")) != -1) {
				chain.doFilter(request, response);
			}else {
				System.out.println("------ "+req.getRequestURL());
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}else {
			System.out.println("chain.doFilter ----  "+ session);
			chain.doFilter(request, response);
		}
	} 
	
	public void destroy() {
		System.out.println("Destruction du filter");
	}
}
