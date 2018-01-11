package stock.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Inventory implements Serializable{
	private String wid;
	private String pid;
	private Double unitprice;
	private int numbers;
	
	public Inventory() {
	}
	public Inventory(String wid, String pid, Double unitprice, int numbers) {
		this.wid = wid;
		this.pid = pid;
		this.unitprice = unitprice;
		this.numbers = numbers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numbers;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result
				+ ((unitprice == null) ? 0 : unitprice.hashCode());
		result = prime * result + ((wid == null) ? 0 : wid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (numbers != other.numbers)
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (unitprice == null) {
			if (other.unitprice != null)
				return false;
		} else if (!unitprice.equals(other.unitprice))
			return false;
		if (wid == null) {
			if (other.wid != null)
				return false;
		} else if (!wid.equals(other.wid))
			return false;
		return true;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
}
