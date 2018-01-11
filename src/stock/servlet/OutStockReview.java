package stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.dao.factory.InventoryFactory;
import stock.dao.factory.OutStockPFactory;
import stock.hibernate.StockSessionFactory;
import stock.vo.Inventory;
import stock.vo.OutStock;
import stock.vo.OutStockItem;

@SuppressWarnings("serial")
public class OutStockReview extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opType=request.getParameter("optype");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(opType!=null&&!opType.equals("")){
			switch(opType){
				case "delete" : remove(request,out); break;
				case "sh" : sh(request,out); break;
			}
		}else{
			out.write("");
		}
		out.flush();
		out.close();
	}
	private void sh(HttpServletRequest request, PrintWriter out) {
		try{
			String outid=request.getParameter("outid");
			OutStock outstock = OutStockPFactory.newInstence().findById(outid);
			List<Inventory> list=new ArrayList<>();
			Set<OutStockItem> set = outstock.getItems();
			Iterator<OutStockItem> items = set.iterator();
			while(items.hasNext()){
				Inventory ven=new Inventory();
				ven.setWid(outstock.getWhid());
				OutStockItem item=items.next();
				ven.setPid(item.getProduct().getPid());
				ven.setUnitprice(item.getUnitprice());
				ven.setNumbers(item.getNumbers());
				list.add(ven);
			}
			StockSessionFactory.closeSession();
			boolean tag=InventoryFactory.newInstance().doSub(list);
			if(tag){
				OutStock outstock2 = OutStockPFactory.newInstence().findById(outid);
				outstock2.setState("已审核");
				boolean tag2=OutStockPFactory.newInstence().doUpdateState(outstock2);
				if(tag2){
					out.write("success");
				}
			}else{
				out.write("fail");
			}
			
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
	private void remove(HttpServletRequest request, PrintWriter out) {
		try{
			String outid=request.getParameter("outid");
			boolean tag=OutStockPFactory.newInstence().doDelete(outid);
			if(tag){
				out.write("success");
			}else{
				out.write("fail");
			}
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
