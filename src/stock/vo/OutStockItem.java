package stock.vo;

public class OutStockItem {
	private OutStock outstock;
	private Product product;
	private Double unitprice;
	private int numbers;
	
	public OutStockItem(){}
	public OutStockItem(OutStock outstock,Product product,double unitprice,int numbers){
		this.outstock=outstock;
		this.product=product;
		this.unitprice=unitprice;
		this.numbers=numbers;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public OutStock getOutstock() {
		return outstock;
	}
	public void setOutstock(OutStock outstock) {
		this.outstock = outstock;
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
	public double getPrice(){
		return this.unitprice*this.numbers;
	}
}