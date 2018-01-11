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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import stock.dao.factory.OutStockPFactory;
import stock.dao.factory.ProductPFactory;
import stock.hibernate.StockSessionFactory;
import stock.vo.OutStock;
import stock.vo.OutStockItem;

@SuppressWarnings("serial")
public class OutStockOPData extends HttpServlet {

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
			List<OutStock> list = OutStockPFactory.newInstence().findAll(state);
			StringBuffer sbf=new StringBuffer();
			sbf.append("[");
			Iterator<OutStock> iterList = list.iterator();
			if(iterList.hasNext()){
				OutStock outstock=iterList.next();
				sbf.append("{");
				sbf.append("'outid':'"+outstock.getOutid()+"',");
				sbf.append("'whid':'"+outstock.getWhid()+"',");
				sbf.append("'requestdate':'"+outstock.getRequestdate()+"',");
				sbf.append("'operator':'"+outstock.getOperator()+"',");
				sbf.append("'state':'"+outstock.getState()+"',");
				sbf.append("items:[");
				Set<OutStockItem> items = outstock.getItems();
				Iterator<OutStockItem> iter = items.iterator();
				if(iter.hasNext()){
					OutStockItem item = iter.next();
					sbf.append("{");
					sbf.append("'pid':'"+item.getProduct().getPid()+"',");
					sbf.append("'unitprice':'"+item.getUnitprice()+"',");
					sbf.append("'numbers':'"+item.getNumbers()+"'");
					sbf.append("}");
				}
				while(iter.hasNext()){
					OutStockItem item=iter.next();
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
				OutStock outstock=iterList.next();
				sbf.append(",{");
				sbf.append("'outid':'"+outstock.getOutid()+"',");
				sbf.append("'whid':'"+outstock.getWhid()+"',");
				sbf.append("'requestdate':'"+outstock.getRequestdate()+"',");
				sbf.append("'operator':'"+outstock.getOperator()+"',");
				sbf.append("'state':'"+outstock.getState()+"',");
				sbf.append("items:[");
				Set<OutStockItem> items = outstock.getItems();
				Iterator<OutStockItem> iter = items.iterator();
				if(iter.hasNext()){
					OutStockItem item = iter.next();
					sbf.append("{");
					sbf.append("'pid':'"+item.getProduct().getPid()+"',");
					sbf.append("'unitprice':'"+item.getUnitprice()+"',");
					sbf.append("'numbers':'"+item.getNumbers()+"'");
					sbf.append("}");
				}
				while(iter.hasNext()){
					OutStockItem item=iter.next();
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
			JSONObject obj=(JSONObject) JSONObject.parse(data);
			String outid=(String) obj.get("outid");
			String whid=(String) obj.get("whid");
			String date=(String) obj.get("date");
			java.util.Date uDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date requestdate=new java.sql.Date(uDate.getTime());
			String operator=(String)obj.get("operator");
			JSONArray array=obj.getJSONArray("rows");
			OutStock outstock=new OutStock(outid,whid,requestdate,operator);
			ListIterator<?> iter = array.listIterator();
			while(iter.hasNext()){
				JSONObject jobj=(JSONObject) iter.next();
				String pid=(String) jobj.get("pid");
				String unitprice=(String) jobj.get("unitprice");
				String numbers=(String) jobj.get("numbers");
				OutStockItem item=new OutStockItem(outstock,ProductPFactory.newInstence().findById(pid),
						Double.parseDouble(unitprice),Integer.parseInt(numbers));
				outstock.getItems().add(item);
			}
			boolean tag = OutStockPFactory.newInstence().doCreate(outstock);
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
