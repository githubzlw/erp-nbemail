package cerong.erp.entity;

public class FactoryReconciliation {
	/** 金蝶号*/
	private int id;
	/** 金蝶号*/
	private int kingdee;
	/** 工厂名*/
	private String factoryName;
	/** 本期借方发生额*/
	private double currentDebitAmount;
	/** 本期贷方发生额*/
	private double amountCredit;
	/** 期末余额*/
	private Double endingBalance;
	/** 2007年价格*/
	private Double price;
	/** 日期*/
	private String createTime;
	/** 项目*/
	private String caseNo;
	/** 合同号*/
	private String bargainNo;
	/** 工厂Id*/
	private int factoryId;
	/** 合同金额*/
	private Double contractAmount;
	/** 已付款金额*/
	private Double amountPaid;
	/** 最终收发票时间*/
	private String finalTimeReceiptAndReceipt;
	/** 收票时间后出运单金额*/
	private Double shipmentBillAmount;
	/** 历史未收票金额*/
	private Double amountCustomsDeclaration;
	
	public Double getAmountCustomsDeclaration() {
		return amountCustomsDeclaration;
	}
	public void setAmountCustomsDeclaration(Double amountCustomsDeclaration) {
		this.amountCustomsDeclaration = amountCustomsDeclaration;
	}
	public String getFinalTimeReceiptAndReceipt() {
		return finalTimeReceiptAndReceipt;
	}
	public void setFinalTimeReceiptAndReceipt(String finalTimeReceiptAndReceipt) {
		this.finalTimeReceiptAndReceipt = finalTimeReceiptAndReceipt;
	}
	public Double getShipmentBillAmount() {
		return shipmentBillAmount;
	}
	public void setShipmentBillAmount(Double shipmentBillAmount) {
		this.shipmentBillAmount = shipmentBillAmount;
	}
	public Double getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	public int getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(int factoryId) {
		this.factoryId = factoryId;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getKingdee() {
		return kingdee;
	}
	public void setKingdee(int kingdee) {
		this.kingdee = kingdee;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public double getCurrentDebitAmount() {
		return currentDebitAmount;
	}
	public void setCurrentDebitAmount(double currentDebitAmount) {
		this.currentDebitAmount = currentDebitAmount;
	}
	public double getAmountCredit() {
		return amountCredit;
	}
	public void setAmountCredit(double amountCredit) {
		this.amountCredit = amountCredit;
	}
	public Double getEndingBalance() {
		return endingBalance;
	}
	public void setEndingBalance(Double endingBalance) {
		this.endingBalance = endingBalance;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "FactoryReconciliation [kingdee=" + kingdee + ", factoryName="
				+ factoryName+ ", id="
				+ id + ", currentDebitAmount=" + currentDebitAmount
				+ ", amountCredit=" + amountCredit + ", endingBalance="
				+ endingBalance + ", price=" + price + ", createTime="
				+ createTime + ", caseNo=" + caseNo + ", bargainNo="
				+ bargainNo + ", factoryId=" + factoryId + ", contractAmount="
				+ contractAmount + ", amountPaid=" + amountPaid
				+ ", finalTimeReceiptAndReceipt=" + finalTimeReceiptAndReceipt
				+ ", shipmentBillAmount=" + shipmentBillAmount
				+ ", amountCustomsDeclaration=" + amountCustomsDeclaration
				+ "]";
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
