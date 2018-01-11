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

import stock.vo.User;

public class AuthorityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		String utype=user.getUtype();
		String url=request.getRequestURI();
		
		if(url.endsWith("warehouse_info.jsp")){
			if(!"系统管理员".equals(utype)&&!"仓库主管".equals(utype)){
				HttpServletResponse res=(HttpServletResponse)arg1;
				res.sendRedirect(request.getContextPath()+"/html/prompt.html");
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}else if(url.endsWith("staff_info.jsp")){
			if(!"系统管理员".equals(utype)){
				HttpServletResponse res=(HttpServletResponse)arg1;
				res.sendRedirect(request.getContextPath()+"/html/prompt.html");
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}else if(url.endsWith("in_stock.jsp")||url.endsWith("instock_review.jsp")){
			if(!"系统管理员".equals(utype)&&!"采购员".equals(utype)){
				HttpServletResponse res=(HttpServletResponse)arg1;
				res.sendRedirect(request.getContextPath()+"/html/prompt.html");
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}else if(url.endsWith("out_stock.jsp")||url.endsWith("outstock_review.jsp")){
			if(!"系统管理员".equals(utype)&&!"销售员".equals(utype)){
				HttpServletResponse res=(HttpServletResponse)arg1;
				res.sendRedirect(request.getContextPath()+"/html/prompt.html");
			}else{
				arg2.doFilter(arg0, arg1);
			}
		}else{
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
