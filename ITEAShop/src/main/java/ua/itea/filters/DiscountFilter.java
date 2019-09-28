package ua.itea.filters;

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

/**
 * Servlet Filter implementation class DiscountFilter
 */
public class DiscountFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DiscountFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("principal")!=null) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			//PrintWriter out = response.getWriter();
			//out.println("403 Forbidden");
			HttpServletResponse responseH = (HttpServletResponse) response;
			responseH.sendRedirect("./403Forbiden.jsp");
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
