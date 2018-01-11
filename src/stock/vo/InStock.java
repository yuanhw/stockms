package stock.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InStock {
	private String inid;
	private String whid;
	private java.sql.Date requestdate;
	private String state="未审核";
	private String operator;
	private Set<InStockItem> items=new HashSet<InStockItem>();
	
	public InStock(){}
	public InStock(String inid,String whid,java.sql.Date requestdate,String operator){
		this.inid=inid;
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
	public Set<InStockItem> getItems() {
		return items;
	}
	public void setItems(Set<InStockItem> items) {
		this.items = items;
	}
	public String getInid() {
		return inid;
	}
	public void setInid(String inid) {
		this.inid = inid;
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
		Iterator<InStockItem> iter = this.items.iterator();
		while(iter.hasNext()){
			InStockItem item=iter.next();
			list.add(item.getProduct().getPid());
		}
		return list;
	}
}