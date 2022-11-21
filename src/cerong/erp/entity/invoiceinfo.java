package cerong.erp.entity;

public class invoiceinfo {
	/** id*/
	private int iid;
	/**发票号 */
	private String iInvNo;
	/**发票号 */
	private String caseno;
	/** 发票链接*/
	private String iUrl;
	/** 资金类别*/
	private int imoneytype;
	/** 资金*/
	private  Double iimoney;
	/** 时间*/
	private String iidate;
	/** 实际到款*/
	private Double ifmoney;
	/** 到款时间*/
	private String ifdate;
	/** 理由*/
	private String updateReason;
	/** 发票类型*/
	private String icasetype;
	/** 发票类型*/
	private int Split;
	/** 银行名*/
	private String bname;
	/**汇率*/
	private double exchangerate;
	/**项目全部预计付款*/
	private double allmoney;
	/**项目全部付款*/
	private double allmoney1;
	/** 起始时间*/
    private String startTime;
    /** 结束时间*/
    private String outTime;
    /** 跟单销售*/
    private String merchandManager1;
    /** 采购*/
    private String merchandManager2;
    /** 客户名*/
    private String customerName;
    /** 跟单销售*/
    private String merchandising;
    /** 利润率*/
    private String grossProfit;
    /** 利润率*/
    private String outstandingNotes;
    /** 选择原因*/
    private int reason;
    /** 是谁上传*/
    private String uploads;
    /** 预计收入和实际收入差距*/
    private double discrepancy;

	public double getDiscrepancy() {
		return discrepancy;
	}

	public void setDiscrepancy(double discrepancy) {
		this.discrepancy = discrepancy;
	}

	private int discrepancyFlag;

	public int getDiscrepancyFlag() {
		return discrepancyFlag;
	}

	public void setDiscrepancyFlag(int discrepancyFlag) {
		this.discrepancyFlag = discrepancyFlag;
	}

	/** 款项理由*/
    private String amountMoney;
    /** 质量扣款金额*/
    private double qualityDeductions;
    /** 质量扣款原因*/
    private String reasonsWithholding;
    /** 解释*/
    private String explain;
    /** 时间*/
    private String createTime;
    /**合同号*/
    private String contractNumber;
    /** 全部欠款*/
    private double allArrears;
    /** 全部欠款*/
    private double estimatedFullPayment;
    /** 全部欠款*/
    private double actualPayment;
    /** 2007年付款*/
    private double payment2007;
    
	public double getPayment2007() {
		return payment2007;
	}
	public void setPayment2007(double payment2007) {
		this.payment2007 = payment2007;
	}
	public double getEstimatedFullPayment() {
		return estimatedFullPayment;
	}
	public void setEstimatedFullPayment(double estimatedFullPayment) {
		this.estimatedFullPayment = estimatedFullPayment;
	}
	public double getActualPayment() {
		return actualPayment;
	}
	public void setActualPayment(double actualPayment) {
		this.actualPayment = actualPayment;
	}
	public double getAllArrears() {
		return allArrears;
	}
	public void setAllArrears(double allArrears) {
		this.allArrears = allArrears;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(String amountMoney) {
		this.amountMoney = amountMoney;
	}
	public double getQualityDeductions() {
		return qualityDeductions;
	}
	public void setQualityDeductions(double qualityDeductions) {
		this.qualityDeductions = qualityDeductions;
	}
	public String getReasonsWithholding() {
		return reasonsWithholding;
	}
	public void setReasonsWithholding(String reasonsWithholding) {
		this.reasonsWithholding = reasonsWithholding;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
//	public int getDiscrepancy() {
//		return discrepancy;
//	}
//	public void setDiscrepancy(double discrepancy) {
//		this.discrepancy = discrepancy;
//	}
	public String getUploads() {
		return uploads;
	}
	public void setUploads(String uploads) {
		this.uploads = uploads;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	public String getMerchandising() {
		return merchandising;
	}
	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}
	public String getOutstandingNotes() {
		return outstandingNotes;
	}
	public void setOutstandingNotes(String outstandingNotes) {
		this.outstandingNotes = outstandingNotes;
	}
	public String getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCaseno() {
		return caseno;
	}
	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public double getAllmoney() {
		return allmoney;
	}
	public void setAllmoney(double allmoney) {
		this.allmoney = allmoney;
	}
	public double getAllmoney1() {
		return allmoney1;
	}
	public void setAllmoney1(double allmoney1) {
		this.allmoney1 = allmoney1;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public String getiInvNo() {
		return iInvNo;
	}
	public void setiInvNo(String iInvNo) {
		this.iInvNo = iInvNo;
	}
	public String getiUrl() {
		return iUrl;
	}
	public void setiUrl(String iUrl) {
		this.iUrl = iUrl;
	}
	public int getImoneytype() {
		return imoneytype;
	}
	public void setImoneytype(int imoneytype) {
		this.imoneytype = imoneytype;
	}
	public Double getIimoney() {
		return iimoney;
	}
	public void setIimoney(Double iimoney) {
		this.iimoney = iimoney;
	}
	public String getIidate() {
		return iidate;
	}
	public void setIidate(String iidate) {
		this.iidate = iidate;
	}
	
	public String getIfdate() {
		return ifdate;
	}
	public void setIfdate(String ifdate) {
		this.ifdate = ifdate;
	}
	
	public Double getIfmoney() {
		return ifmoney;
	}
	public void setIfmoney(Double ifmoney) {
		this.ifmoney = ifmoney;
	}
	public String getUpdateReason() {
		return updateReason;
	}
	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}
	public String getIcasetype() {
		return icasetype;
	}
	public void setIcasetype(String icasetype) {
		this.icasetype = icasetype;
	}
	
	public int getSplit() {
		return Split;
	}
	public void setSplit(int split) {
		Split = split;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	public double getExchangerate() {
		return exchangerate;
	}
	public void setExchangerate(double exchangerate) {
		this.exchangerate = exchangerate;
	}
	
	public String getMerchandManager1() {
		return merchandManager1;
	}
	public void setMerchandManager1(String merchandManager1) {
		this.merchandManager1 = merchandManager1;
	}
	public String getMerchandManager2() {
		return merchandManager2;
	}
	public void setMerchandManager2(String merchandManager2) {
		this.merchandManager2 = merchandManager2;
	}
	@Override
	public String toString() {
		return "invoiceinfo [iid=" + iid + ", iInvNo=" + iInvNo + ", caseno="
				+ caseno + ", iUrl=" + iUrl + ", imoneytype=" + imoneytype
				+ ", iimoney=" + iimoney + ", iidate=" + iidate + ", ifmoney="
				+ ifmoney + ", ifdate=" + ifdate + ", updateReason="
				+ updateReason + ", icasetype=" + icasetype + ", Split="
				+ Split + ", bname=" + bname + ", exchangerate=" + exchangerate
				+ ", allmoney=" + allmoney + ", allmoney1=" + allmoney1
				+ ", startTime=" + startTime + ", outTime=" + outTime
				+ ", merchandManager1=" + merchandManager1
				+ ", merchandManager2=" + merchandManager2 + ", customerName="
				+ customerName + ", merchandising=" + merchandising
				+ ", grossProfit=" + grossProfit + ", outstandingNotes="
				+ outstandingNotes + ", reason=" + reason + ", uploads="
				+ uploads + ", discrepancy=" + discrepancy + ", amountMoney="
				+ amountMoney + ", qualityDeductions=" + qualityDeductions
				+ ", reasonsWithholding=" + reasonsWithholding + ", explain="
				+ explain + ", createTime=" + createTime + ", contractNumber="
				+ contractNumber + ", allArrears=" + allArrears
				+ ", estimatedFullPayment=" + estimatedFullPayment
				+ ", actualPayment=" + actualPayment + ", payment2007="
				+ payment2007 + "]";
	}

	
	
	

}
