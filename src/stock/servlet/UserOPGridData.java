package stock.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import stock.dao.factory.UserPFactory;
import stock.vo.User;

@SuppressWarnings("serial")
public class UserOPGridData extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opType=request.getParameter("optype");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(opType!=null&&!opType.equals("")){
			
			switch(opType){
				case "insert" : OpInsert(out,request); break;
				case "update" : OpUpdate(out,request); break;
				case "select" : OpFindAll(out,request); break;
				case "delete" : OpDelete(out,request); break;
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
	private void OpInsert(PrintWriter out, HttpServletRequest request){
		try{
			String jsonArrayStr=request.getParameter("data");
			List<User> whlist=JSON.parseArray(jsonArrayStr, User.class);
			if(UserPFactory.newInstence().doCreate(whlist)){
				out.write("插入成功！");
			}else{
				out.write("插入失败！");
			}
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
		
	}
	private void OpUpdate(PrintWriter out, HttpServletRequest request){
		try{

			String jsonArrayStr=request.getParameter("data");
			List<User> whlist=JSON.parseArray(jsonArrayStr, User.class);
			if(UserPFactory.newInstence().doUpdate(whlist)){
				out.write("更新成功！");
			}else{
				out.write("更新失败！");
			}
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
	private void OpDelete(PrintWriter out, HttpServletRequest request){
		try{

			String jsonArrayStr=request.getParameter("data");
			JSONArray jsonArrayObj = JSON.parseArray(jsonArrayStr);
			List<String> list=new ArrayList<>();
			ListIterator<?> iter = jsonArrayObj.listIterator();
			while(iter.hasNext()){
				JSONObject jobj=(JSONObject)iter.next();
				list.add(jobj.getString("uid"));
			}
			if(UserPFactory.newInstence().doDelete(list)){
				out.write("删除成功！");
			}else{
				out.write("删除失败！");
			}
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
	private void OpFindAll(PrintWriter out, HttpServletRequest request){
		try{
			String param1=request.getParameter("start");
			String param2=request.getParameter("pagesize");
			int start=Integer.parseInt(param1);
			int pagesize=Integer.parseInt(param2);
			List<User> list=UserPFactory.newInstence().findAll();
			List<User> sublist=null;
			if((start+pagesize)>list.size()-1){
				sublist = list.subList(start, list.size());
			}else{
				sublist = list.subList(start, start+pagesize);
			}
			if(list!=null&&list.size()!=0){
				String jsonstr=JSON.toJSONString(sublist);
				JSONArray jarray=JSON.parseArray(jsonstr);
				JSONObject jobj=new JSONObject();
				jobj.put("total", list.size());
				jobj.put("source", jarray);
				out.write(jobj.toJSONString());
			}
		}catch(Exception e){
			out.write("error");
			e.printStackTrace();
		}
	}
}
