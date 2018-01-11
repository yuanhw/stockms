package stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import stock.dao.factory.InventoryFactory;
import stock.dao.factory.ProductPFactory;
import stock.dao.factory.WareHousePFactory;
import stock.vo.Inventory;
import stock.vo.Product;

@SuppressWarnings("serial")
public class InventoryOPData extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opType=request.getParameter("optype");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(opType!=null&&!opType.equals("")){
			
			switch(opType){
				case "search" : OpFindAll(out,request); break;
			}
		}else{
			out.write("");
		}
		out.flush();
		out.close();
	}
	private void OpFindAll(PrintWriter out, HttpServletRequest request) {
		try{
			List<Inventory> list=null;
			String wid=request.getParameter("wid");
			String pid=request.getParameter("pid");
			if("".equals(wid)){
				wid="000";
			}
			if("".equals(pid)){
				pid="000";
			}
			if("000".equals(wid)){
				if("000".equals(pid)){
					list=InventoryFactory.newInstance().findAll();
				}else{
					list=InventoryFactory.newInstance().findAllByPid(pid);
				}
			}else{
				if("000".equals(pid)){
					list=InventoryFactory.newInstance().findAllByWid(wid);
				}else{
					list=InventoryFactory.newInstance().findAllByKeyWord(wid, pid);
				}
			}
			JSONArray jarray=new JSONArray();
			for(Inventory item : list){
				JSONObject jonj=new JSONObject();
				String wname=WareHousePFactory.newInstence().findById(item.getWid()).getWname();
				Product pro=ProductPFactory.newInstence().findById(item.getPid());
				jonj.put("wname", wname);
				jonj.put("pname", pro.getPname());
				jonj.put("units", pro.getUnits());
				jonj.put("unitprice", item.getUnitprice());
				jonj.put("numbers", item.getNumbers());
				jarray.add(jonj);
			}
			out.write(jarray.toString());
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
