package cerong.erp.entity;
/*,[amount_customs_declaration]
	      ,[bargainNo]
	      ,[factoryName]
	      ,[caseno]
	      ,[date]*/
public class ShippingBillData {
	/** 编号 */
	private int id;
	/** 价格 */
	private Double amountCustomsDeclaration;
	/** 合同号 */
	private String bargainNo;
	/** 工厂名 */
	private String factoryName;
	/** 项目号 */
	private String caseno;
	/** 日期 */
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getAmountCustomsDeclaration() {
		return amountCustomsDeclaration;
	}
	public void setAmountCustomsDeclaration(Double amountCustomsDeclaration) {
		this.amountCustomsDeclaration = amountCustomsDeclaration;
	}
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getCaseno() {
		return caseno;
	}
	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ShippingBillData [id=" + id + ", amountCustomsDeclaration="
				+ amountCustomsDeclaration + ", bargainNo=" + bargainNo
				+ ", factoryName=" + factoryName + ", caseno=" + caseno
				+ ", date=" + date + "]";
	}
	
	
	
	
}
