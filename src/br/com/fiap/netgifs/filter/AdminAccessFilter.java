package br.com.fiap.netgifs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.fiap.netgifs.managedbean.PrincipalMBean;

@WebFilter(filterName = "AdminAccessFilter", urlPatterns = {"/admin/*"})
public class AdminAccessFilter implements Filter {

	private FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	@Override
	public void destroy() {
		this.filterConfig = null;
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (filterConfig == null) {
			return;
		}

		HttpSession session = ((HttpServletRequest) req).getSession(false);
		
		PrincipalMBean princialMBean = (session != null) ? (PrincipalMBean) session.getAttribute("principalMBean") : null;

		if (princialMBean != null && princialMBean.isLoggedin() && princialMBean.getUser().isAdmin()) {
			chain.doFilter(req, resp);
		} else {
			req.getRequestDispatcher("/").forward(req, resp);
		}
	} 

}
