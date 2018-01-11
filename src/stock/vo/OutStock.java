package stock.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OutStock {
	private String outid;
	private String whid;
	private java.sql.Date requestdate;
	private String state="未审核";
	private String operator;
	private Set<OutStockItem> items=new HashSet<OutStockItem>();
	
	public OutStock(){}
	public OutStock(String outid,String whid,java.sql.Date requestdate,String operator){
		this.outid=outid;
		this.whid=whid;
		this.requestdate=requestdate;
		this.operator=operator;
	}
	public String getWhid() {
		return whid;
	}
	public void setWhid(String whid) {
		this.whid = whid;
	}
	public Set<OutStockItem> getItems() {
		return items;
	}
	public void setItems(Set<OutStockItem> items) {
		this.items = items;
	}
	public String getOutid() {
		return outid;
	}
	public void setOutid(String outid) {
		this.outid = outid;
	}
	public java.sql.Date getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(java.sql.Date requestdate) {
		this.requestdate = requestdate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public List<String> getProductIds(){
		List<String> list=new ArrayList<String>();
		Iterator<OutStockItem> iter = this.items.iterator();
		while(iter.hasNext()){
			OutStockItem item=iter.next();
			list.add(item.getProduct().getPid());
		}
		return list;
	}
}