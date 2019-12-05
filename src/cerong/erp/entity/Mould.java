package cerong.erp.entity;
/** 模具*/
public class Mould {
	/** 项目号*/
	private int  id;
	/** 项目号*/
	private String caseno;
	  /** 价格*/
    private double price;
     /** 数量*/
    private int amount;
    /** 产品名*/
    private String product;
     /** 图纸名*/
    private String drawingname;
    /** 客户id*/
    private int customercode;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCaseno() {
		return caseno;
	}
	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDrawingname() {
		return drawingname;
	}
	public void setDrawingname(String drawingname) {
		this.drawingname = drawingname;
	}
	public int getCustomercode() {
		return customercode;
	}
	public void setCustomercode(int customercode) {
		this.customercode = customercode;
	}
	@Override
	public String toString() {
		return "Mould [id=" + id + ", caseno=" + caseno + ", price=" + price
				+ ", amount=" + amount + ", product=" + product
				+ ", drawingname=" + drawingname + ", customercode="
				+ customercode + "]";
	}
    
}
