package stock.test;

import static java.lang.System.out;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Client {

	public static void main(String[] args) {
		out.println("test begin:");
		try{
			test();
		}catch(Exception e){
			e.printStackTrace();
		}
		out.println("test end:");
	}
	private static void test() throws Exception{
		String filepath = "F:\\我的文档\\课程\\BI\\adata"+"\\高技术产业专利申请数.xls";
		String filepath2 = "F:\\我的文档\\课程\\BI\\adata"+"\\高技术产业发明专利申请数.xls";
		String filepath3 = "F:\\我的文档\\课程\\BI\\adata"+"\\高技术产业有效发明专利数.xls";
		FileInputStream fin = new FileInputStream(filepath);
		FileInputStream fin2 = new FileInputStream(filepath2);
		FileInputStream fin3 = new FileInputStream(filepath3);
		HSSFWorkbook wb = new HSSFWorkbook(fin); 
		HSSFWorkbook wb2 = new HSSFWorkbook(fin2); 
		HSSFWorkbook wb3 = new HSSFWorkbook(fin3); 
		HSSFSheet sheet = wb.getSheet("年度数据");
		HSSFSheet sheet2 = wb2.getSheet("年度数据");
		HSSFSheet sheet3 = wb3.getSheet("年度数据");
		int id = 1;
		for (int i = 4; i <= 22; i++) {
			HSSFRow row = sheet.getRow(i);
			HSSFRow row2 = sheet2.getRow(i);
			HSSFRow row3 = sheet3.getRow(i);
			
			String name = row.getCell(0).toString();
			int year = 2015;
			for (int j = 1; j <= 5; j++) {
				String nums = row.getCell(j).toString();
				float numf = Float.parseFloat(nums);
				int numm = (int) numf;
				
				String nums2 = row2.getCell(j).toString();
				float numf2 = Float.parseFloat(nums2);
				int numm2 = (int) numf2;
				
				String nums3 = row3.getCell(j).toString();
				float numf3 = Float.parseFloat(nums3);
				int numm3 = (int) numf3;
				
				String sql = "insert into zhuanli_shumu values ( "
						+ (id++) +", '"
						+ name +"', '"
						+ (year + 1 - j) +"', "
						+ numm + ", "
						+ numm2 + ", "
						+ numm3 +
						")";
				out.println(sql);
			}
		}
		fin.close();
		fin2.close();
		fin3.close();
	}
}