package cerong.erp.entity;

public class ItemCase1 {
	/** 客户id */
	private int customerId;
	/** 项目id */
	private String orderId;
	/** 发票金额*/
	private String amount;
	  /** 订单状态*/
    private String caseStatus;
    /** 下单时间*/
    private String createTime;
    /** 出库日期*/
    private String saildate;
   /* *//** 历史销售*//*
    private String saleName1;
    *//** 历史跟单销售*//*
    private String saleName2;*/
    
	public String getOrderId() {
		return orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSaildate() {
		return saildate;
	}
	public void setSaildate(String saildate) {
		this.saildate = saildate;
	}
	@Override
	public String toString() {
		return "ItemCase1 [customerId=" + customerId + ", orderId=" + orderId
				+ ", amount=" + amount + ", caseStatus=" + caseStatus
				+ ", createTime=" + createTime + ", saildate=" + saildate + "]";
	}
	
    
}
