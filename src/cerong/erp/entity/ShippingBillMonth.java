package cerong.erp.entity;

public class ShippingBillMonth {
	/** 编号 */
	private int id;
	/** 月份 */
	private String waybillMonth;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWaybillMonth() {
		return waybillMonth;
	}
	public void setWaybillMonth(String waybillMonth) {
		this.waybillMonth = waybillMonth;
	}
	@Override
	public String toString() {
		return "ShippingBillMonth [id=" + id + ", waybillMonth=" + waybillMonth
				+ "]";
	}
	
	
}
