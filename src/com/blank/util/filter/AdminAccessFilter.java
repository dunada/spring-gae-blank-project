package com.blank.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blank.entity.UserAdmin;
import com.blank.util.Constant;



public class AdminAccessFilter implements Filter{
	

	@SuppressWarnings("unused")
	private FilterConfig filterConfig;
    
    public void init(final FilterConfig filterConfig) throws ServletException {
    	this.filterConfig = filterConfig;
    }

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		HttpSession session = request.getSession();
		
		String path = request.getRequestURI();
		UserAdmin userAdminLoggedIn = (UserAdmin) session.getAttribute(Constant.ADMIN_AUTH_USER);
		if(!path.toLowerCase().startsWith("/admin/login") && !path.toLowerCase().startsWith("/admin/startdb") && (userAdminLoggedIn==null)){
			response.sendRedirect("/admin/login");
		}
		
		chain.doFilter(request, response);
		
		
	}
	
	public void destroy() {
	}
}
