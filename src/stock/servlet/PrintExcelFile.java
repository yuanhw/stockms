package stock.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@SuppressWarnings("serial")
public class PrintExcelFile extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String stamp= sdf.format(date);
		response.setHeader("Content-Disposition","attachment;filename="+stamp+".xlsx");
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		 ServletOutputStream out = response.getOutputStream();
		printExcelFile(out,request);
	}
	@SuppressWarnings("resource")
	private void printExcelFile(ServletOutputStream out,HttpServletRequest request) {
		String data=request.getParameter("data");
		JSONArray jarray=(JSONArray) JSONArray.parse(data);
		Iterator<?> iter = jarray.iterator();
		String filepath=request.getServletContext().getRealPath("excel_model/库存信息.xlsx");
		FileInputStream fin=null;
		try {
			fin=new FileInputStream(filepath);
			XSSFWorkbook xwb= new XSSFWorkbook(fin);
			XSSFSheet st1 = xwb.getSheet("Sheet1");
			int index=3;
			while(iter.hasNext()){
				JSONObject obj=(JSONObject) iter.next();
				XSSFRow row = st1.createRow(index);
				XSSFCell cell = row.createCell(0);
				cell.setCellValue(obj.getString("wname"));
				XSSFCell cell2 = row.createCell(1);
				cell2.setCellValue(obj.getString("pname"));
				XSSFCell cell3 = row.createCell(2);
				cell3.setCellValue(obj.getString("units"));
				XSSFCell cell4 = row.createCell(3);
				cell4.setCellValue(Double.parseDouble(obj.getString("unitprice")));
				XSSFCell cell5 = row.createCell(4);
				cell5.setCellValue(Integer.parseInt(obj.getString("numbers")));
				index++;
			}
			xwb.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
