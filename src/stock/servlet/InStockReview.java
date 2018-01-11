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

import stock.dao.factory.InStockPFactory;
import stock.dao.factory.InventoryFactory;
import stock.hibernate.StockSessionFactory;
import stock.vo.InStock;
import stock.vo.InStockItem;
import stock.vo.Inventory;

@SuppressWarnings("serial")
public class InStockReview extends HttpServlet {
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
			String inid=request.getParameter("inid");
			InStock instock = InStockPFactory.newInstence().findById(inid);
			List<Inventory> list=new ArrayList<>();
			Set<InStockItem> set = instock.getItems();
			Iterator<InStockItem> items = set.iterator();
			while(items.hasNext()){
				Inventory ven=new Inventory();
				ven.setWid(instock.getWhid());
				InStockItem item=items.next();
				ven.setPid(item.getProduct().getPid());
				ven.setUnitprice(item.getUnitprice());
				ven.setNumbers(item.getNumbers());
				list.add(ven);
			}
			StockSessionFactory.closeSession();
			boolean tag=InventoryFactory.newInstance().doAdd(list);
			if(tag){
				InStock instock2 = InStockPFactory.newInstence().findById(inid);
				instock2.setState("已审核");
				boolean tag2=InStockPFactory.newInstence().doUpdateState(instock2);
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
			String inid=request.getParameter("inid");
			boolean tag=InStockPFactory.newInstence().doDelete(inid);
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
