package stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import stock.dao.factory.InStockPFactory;
import stock.dao.factory.ProductPFactory;
import stock.hibernate.StockSessionFactory;
import stock.vo.InStock;
import stock.vo.InStockItem;

@SuppressWarnings("serial")
public class InStockOPData extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opType=request.getParameter("optype");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(opType!=null&&!opType.equals("")){
			switch(opType){
				case "insert" : insertData(request,out); break;
				case "findlist" :findList(request,out); break;
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
	private void findList(HttpServletRequest request, PrintWriter out){
		try{
			String state=request.getParameter("state");
			List<InStock> list = InStockPFactory.newInstence().findAll(state);
			StringBuffer sbf=new StringBuffer();
			sbf.append("[");
			Iterator<InStock> iterList = list.iterator();
			if(iterList.hasNext()){
				InStock instock=iterList.next();
				sbf.append("{");
				sbf.append("'inid':'"+instock.getInid()+"',");
				sbf.append("'whid':'"+instock.getWhid()+"',");
				sbf.append("'requestdate':'"+instock.getRequestdate()+"',");
				sbf.append("'operator':'"+instock.getOperator()+"',");
				sbf.append("'state':'"+instock.getState()+"',");
				sbf.append("items:[");
				Set<InStockItem> items = instock.getItems();
				Iterator<InStockItem> iter = items.iterator();
				if(iter.hasNext()){
					InStockItem item = iter.next();
					sbf.append("{");
					sbf.append("'pid':'"+item.getProduct().getPid()+"',");
					sbf.append("'unitprice':'"+item.getUnitprice()+"',");
					sbf.append("'numbers':'"+item.getNumbers()+"'");
					sbf.append("}");
				}
				while(iter.hasNext()){
					InStockItem item=iter.next();
					sbf.append(",{");
					sbf.append("'pid':'"+item.getProduct().getPid()+"',");
					sbf.append("'unitprice':'"+item.getUnitprice()+"',");
					sbf.append("'numbers':'"+item.getNumbers()+"'");
					sbf.append("}");
				}
				sbf.append("]");
				sbf.append("}");
			}
			while(iterList.hasNext()){
				InStock instock=iterList.next();
				sbf.append(",{");
				sbf.append("'inid':'"+instock.getInid()+"',");
				sbf.append("'whid':'"+instock.getWhid()+"',");
				sbf.append("'requestdate':'"+instock.getRequestdate()+"',");
				sbf.append("'operator':'"+instock.getOperator()+"',");
				sbf.append("'state':'"+instock.getState()+"',");
				sbf.append("items:[");
				Set<InStockItem> items = instock.getItems();
				Iterator<InStockItem> iter = items.iterator();
				if(iter.hasNext()){
					InStockItem item = iter.next();
					sbf.append("{");
					sbf.append("'pid':'"+item.getProduct().getPid()+"',");
					sbf.append("'unitprice':'"+item.getUnitprice()+"',");
					sbf.append("'numbers':'"+item.getNumbers()+"'");
					sbf.append("}");
				}
				while(iter.hasNext()){
					InStockItem item=iter.next();
					sbf.append(",{");
					sbf.append("'pid':'"+item.getProduct().getPid()+"',");
					sbf.append("'unitprice':'"+item.getUnitprice()+"',");
					sbf.append("'numbers':'"+item.getNumbers()+"'");
					sbf.append("}");
				}
				sbf.append("]");
				sbf.append("}");
			}
			sbf.append("]");
			out.write(sbf.toString());
			StockSessionFactory.closeSession();
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
	private void insertData(HttpServletRequest request,PrintWriter out){
		try{
			String data=request.getParameter("data");
			JSONObject obj = (JSONObject) JSON.parse(data);
			String inid=(String) obj.get("inid");
			String whid=(String) obj.get("whid");
			String date=(String) obj.get("date");
			java.util.Date uDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date requestdate=new java.sql.Date(uDate.getTime());
			String operator=(String)obj.get("operator");
			JSONArray array=obj.getJSONArray("rows");
			InStock instock=new InStock(inid,whid,requestdate,operator);
			ListIterator<?> iter = array.listIterator();
			while(iter.hasNext()){
				JSONObject jobj=(JSONObject) iter.next();
				String pid=(String) jobj.get("pid");
				String unitprice=(String) jobj.get("unitprice");
				String numbers=(String) jobj.get("numbers");
				InStockItem item=new InStockItem(instock,ProductPFactory.newInstence().findById(pid),
						Double.parseDouble(unitprice),Integer.parseInt(numbers));
				instock.getItems().add(item);
			}
			boolean tag = InStockPFactory.newInstence().doCreate(instock);
			if(tag){
				out.write("success");
			}else{
				out.write("error");
			}
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
}
