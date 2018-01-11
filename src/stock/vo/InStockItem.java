package stock.vo;

public class InStockItem {
	private InStock instock;
	private Product product;
	private Double unitprice;
	private int numbers;
	public InStockItem(){}
	public InStockItem(InStock instock,Product product,double unitprice,int numbers){
		this.instock=instock;
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
	public InStock getInstock() {
		return instock;
	}
	public void setInstock(InStock instock) {
		this.instock = instock;
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