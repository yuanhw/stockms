package stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import stock.dao.factory.UserPFactory;
import stock.vo.User;

@SuppressWarnings("serial")
public class LogIn extends HttpServlet {

	public LogIn() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String uid=request.getParameter("uid");
		String password=request.getParameter("password");
		String utype=request.getParameter("utype");
		PrintWriter out = response.getWriter();
		User user=UserPFactory.newInstence().findById(uid);
		if(user==null){
			out.write("账号不存在！");
		}else{
			if(!user.getPassword().equals(password)){
				out.write("密码有误！");
			}else if(!user.getUtype().equals(utype)){
				out.write("角色不匹配！");
			}else{
				HttpSession session=request.getSession(true);
				session.setAttribute("user", user);
				out.write("ok");
			}
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
