package stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import stock.dao.factory.ProductPFactory;
import stock.dao.factory.WareHousePFactory;
import stock.vo.Product;
import stock.vo.WareHouse;

@SuppressWarnings("serial")
public class LoadData extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opType=request.getParameter("optype");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(opType!=null&&!opType.equals("")){
			switch(opType){
				case "whdata" : loadWHData(out); break;
				case "prodata" : loadProData(out); break;
			}
		}else{
			out.write("");
		}
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	private void loadWHData(PrintWriter out){
		try{
			List<WareHouse> whdata= WareHousePFactory.newInstence().findAll();
			String jsonstr=JSONArray.toJSONString(whdata);
			out.write(jsonstr);
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
	private void loadProData(PrintWriter out){
		try{
			List<Product> pro= ProductPFactory.newInstence().findAll();
			String jsonstr=JSONArray.toJSONString(pro);
			out.write(jsonstr);
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
}
