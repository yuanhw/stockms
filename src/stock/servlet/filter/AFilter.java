package stock.servlet.filter;

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

public class AFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null){
			arg0.setCharacterEncoding("utf-8");
			arg2.doFilter(arg0, arg1);
		}else{
			HttpServletResponse response=(HttpServletResponse)arg1;
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
