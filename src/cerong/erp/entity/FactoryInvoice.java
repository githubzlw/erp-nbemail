package cerong.erp.entity;

public class FactoryInvoice {
	/** 工厂Id*/
	private String factoryId;
	/** 项目*/
	private String caseNo;

	/** 日期*/
	private String dateTime;

	/** 收取金额*/
	private double payMoeny;

	/** 合同号*/
	private String bargainNo;

	/** 凭证号*/
	private String labNo;

	/** 备注*/
	private String remarks;

	/** 发票名称*/
	private String invoiceName;

	/** 发票总金额*/
	private double totalAmount;

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getPayMoeny() {
		return payMoeny;
	}

	public void setPayMoeny(double payMoeny) {
		this.payMoeny = payMoeny;
	}

	public String getBargainNo() {
		return bargainNo;
	}

	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}

	public String getLabNo() {
		return labNo;
	}

	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
