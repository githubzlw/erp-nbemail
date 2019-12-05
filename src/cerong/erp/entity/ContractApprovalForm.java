package cerong.erp.entity;

public class ContractApprovalForm {
	
	/**id */
	private int id;
	/** 合同号 */
	private String bargainNo;
	/** 价格变化 */
	private int priceChange;
	
	/** 首付款 */
	private String downPaymentRatio;
	/** 项目号 */
	private String caseNo;
	/** 中期付款 */
	private String mediumTermPayment;
	/** 尾款 */
	private String retainage;
	
	
	/** 项目启动时间 */
	private String projectStartupTime;
	/** 出货日期 */
	private String pODeliveryDate;
	/** 客户首付款时间 */
	private String ifdate;
	/** 客户对交期有要求吗 0，无 1.有 */
	private int deliveryRequirement;
	/** 客户是否需要垫付  0不需要  1.需要 */
	private int attribute;
	/** 模具  0不存在  1.存在 */
	private int mold;
	/** 样品  0不存在  1.存在 */
	private int sample;
	/** 大货  0不存在  1.存在 */
	private int Largecargo;
	/** 0未打印  1.暂停   2.已打印  3 信息不全 */
	private int dealWith;
	/** 客户总定量 */
	private String totalCustomerPurchases;
	/** 项目利润率 */
	private String grossProfitMargin;
	/** 注释*/
	private String notes;
	/** 大货利润率 */
	private String bigFreightRate;
	/** 销售 */
	private String saleName;
	
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	public int getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(int priceChange) {
		this.priceChange = priceChange;
	}
	public String getDownPaymentRatio() {
		return downPaymentRatio;
	}
	public void setDownPaymentRatio(String downPaymentRatio) {
		this.downPaymentRatio = downPaymentRatio;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getMediumTermPayment() {
		return mediumTermPayment;
	}
	public void setMediumTermPayment(String mediumTermPayment) {
		this.mediumTermPayment = mediumTermPayment;
	}
	public String getRetainage() {
		return retainage;
	}
	public void setRetainage(String retainage) {
		this.retainage = retainage;
	}
	public String getProjectStartupTime() {
		return projectStartupTime;
	}
	public void setProjectStartupTime(String projectStartupTime) {
		this.projectStartupTime = projectStartupTime;
	}
	public String getpODeliveryDate() {
		return pODeliveryDate;
	}
	public void setpODeliveryDate(String pODeliveryDate) {
		this.pODeliveryDate = pODeliveryDate;
	}
	public String getIfdate() {
		return ifdate;
	}
	public void setIfdate(String ifdate) {
		this.ifdate = ifdate;
	}
	public int getDeliveryRequirement() {
		return deliveryRequirement;
	}
	public void setDeliveryRequirement(int deliveryRequirement) {
		this.deliveryRequirement = deliveryRequirement;
	}
	public int getAttribute() {
		return attribute;
	}
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	public int getMold() {
		return mold;
	}
	public void setMold(int mold) {
		this.mold = mold;
	}
	public int getSample() {
		return sample;
	}
	public void setSample(int sample) {
		this.sample = sample;
	}
	public int getLargecargo() {
		return Largecargo;
	}
	public void setLargecargo(int largecargo) {
		Largecargo = largecargo;
	}
	public int getDealWith() {
		return dealWith;
	}
	public void setDealWith(int dealWith) {
		this.dealWith = dealWith;
	}
	public String getTotalCustomerPurchases() {
		return totalCustomerPurchases;
	}
	public void setTotalCustomerPurchases(String totalCustomerPurchases) {
		this.totalCustomerPurchases = totalCustomerPurchases;
	}
	public String getGrossProfitMargin() {
		return grossProfitMargin;
	}
	public void setGrossProfitMargin(String grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getBigFreightRate() {
		return bigFreightRate;
	}
	public void setBigFreightRate(String bigFreightRate) {
		this.bigFreightRate = bigFreightRate;
	}
	@Override
	public String toString() {
		return "ContractApprovalForm [id=" + id + ", bargainNo=" + bargainNo
				+ ", priceChange=" + priceChange + ", downPaymentRatio="
				+ downPaymentRatio + ", caseNo=" + caseNo
				+ ", mediumTermPayment=" + mediumTermPayment + ", retainage="
				+ retainage + ", projectStartupTime=" + projectStartupTime
				+ ", pODeliveryDate=" + pODeliveryDate + ", ifdate=" + ifdate
				+ ", deliveryRequirement=" + deliveryRequirement
				+ ", attribute=" + attribute + ", mold=" + mold + ", sample="
				+ sample + ", Largecargo=" + Largecargo + ", dealWith="
				+ dealWith + ", totalCustomerPurchases="
				+ totalCustomerPurchases + ", grossProfitMargin="
				+ grossProfitMargin + ", notes=" + notes + ", bigFreightRate="
				+ bigFreightRate + ", saleName=" + saleName + "]";
	}
	
	
	
}
