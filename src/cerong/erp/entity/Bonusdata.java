package cerong.erp.entity;
/** 分红表 */
public class Bonusdata {

	/** 客户id */
	private int customerId;
	/** 客户名 */
	private String customer_name;
	/** 项目号*/
	private String projectId;
	/** 项目中文名*/
	private  String productdescc;
	/** 项目英文名*/
	private String productdesce;
	/** 销售*/
	private String customerManager;
	/** 原始跟单销售*/
	private String merchandManager1;
	/** 采购*/
	private String merchandManager2;
	/** 跟单销售*/
	private String zhijian1;
	/** 跟单销售*/
	private String zhijian2;
	/** 跟单销售*/
	private String engineer1;
	/** 跟单销售*/
	private String engineer2;
	/** 跟单销售*/
	private String engineer3;
	/** 成熟跟单销售*/
	private String merchandising;
	/** 成熟跟单采购*/
	private String maturePurchase;
	/** 成熟跟单采购*/
	private String originalPurchase;
	private Double totalSalesUs; //总销售额(美元)

	private Double totalSalesRmb; //总销售额(人民币)

	private String totalGrossProfitMargin; //总毛利率

	private Double totalGrossProfitRmb; // 总毛利润(人民币)

	private Double totalGrossProfitUs;//总毛利润(美元)

	private Double salesThisPeriodRmb; // 本期销售额(人民币) 已到款

	private Double accountPayableRmb; //应付款(人民币)

	private Double salesThisPeriodUs; // 本期销售额(美元) 已到款

	private Double profitThisPeriodRmb;// 本期利润(人民币)
	private int isInvoice;// 0,发票未收全  1，发票已收全
	/**付款时间*/
	private String payment_time;
	/** 最近付款时间*/
	private String latest_payment_time;
	/** 难度系数*/
	private int degreeDifficulty;
	/** 传承项目*/
	private String heritageProject;
	/** 到账总金额 */
	private Double totalAmountOfArrival;
	/** 应收总金额 */
	private Double totalAmountReceivable;
	/** 物流费用0，有 1.无 */
	private int logisticsCost;
	/** 项目状态 */
	private int caseStatus;
	/** 质量扣款项目 */
	private int qualityDeductionProject;
	/** 利润率 */
	private String grossProfit;



	public int getQualityDeductionProject() {
		return qualityDeductionProject;
	}
	public void setQualityDeductionProject(int qualityDeductionProject) {
		this.qualityDeductionProject = qualityDeductionProject;
	}
	public int getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getOriginalPurchase() {
		return originalPurchase;
	}
	public void setOriginalPurchase(String originalPurchase) {
		this.originalPurchase = originalPurchase;
	}
	public int getLogisticsCost() {
		return logisticsCost;
	}
	public void setLogisticsCost(int logisticsCost) {
		this.logisticsCost = logisticsCost;
	}
	public String getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}
	public Double getTotalSalesUs() {
		return totalSalesUs;
	}
	public void setTotalSalesUs(Double totalSalesUs) {
		this.totalSalesUs = totalSalesUs;
	}
	public Double getTotalSalesRmb() {
		return totalSalesRmb;
	}
	public void setTotalSalesRmb(Double totalSalesRmb) {
		this.totalSalesRmb = totalSalesRmb;
	}
	public String getTotalGrossProfitMargin() {
		return totalGrossProfitMargin;
	}
	public void setTotalGrossProfitMargin(String totalGrossProfitMargin) {
		this.totalGrossProfitMargin = totalGrossProfitMargin;
	}
	public Double getTotalGrossProfitRmb() {
		return totalGrossProfitRmb;
	}
	public void setTotalGrossProfitRmb(Double totalGrossProfitRmb) {
		this.totalGrossProfitRmb = totalGrossProfitRmb;
	}
	public Double getTotalGrossProfitUs() {
		return totalGrossProfitUs;
	}
	public void setTotalGrossProfitUs(Double totalGrossProfitUs) {
		this.totalGrossProfitUs = totalGrossProfitUs;
	}
	public Double getSalesThisPeriodUs() {
		return salesThisPeriodUs;
	}
	public void setSalesThisPeriodUs(Double salesThisPeriodUs) {
		this.salesThisPeriodUs = salesThisPeriodUs;
	}
	public Double getProfitThisPeriodRmb() {
		return profitThisPeriodRmb;
	}
	public void setProfitThisPeriodRmb(Double profitThisPeriodRmb) {
		this.profitThisPeriodRmb = profitThisPeriodRmb;
	}
	public Double getSalesThisPeriodRmb() {
		return salesThisPeriodRmb;
	}
	public void setSalesThisPeriodRmb(Double salesThisPeriodRmb) {
		this.salesThisPeriodRmb = salesThisPeriodRmb;
	}
	public Double getAccountPayableRmb() {
		return accountPayableRmb;
	}
	public void setAccountPayableRmb(Double accountPayableRmb) {
		this.accountPayableRmb = accountPayableRmb;
	}
	public Double getTotalAmountOfArrival() {
		return totalAmountOfArrival;
	}
	public void setTotalAmountOfArrival(Double totalAmountOfArrival) {
		this.totalAmountOfArrival = totalAmountOfArrival;
	}
	public Double getTotalAmountReceivable() {
		return totalAmountReceivable;
	}
	public void setTotalAmountReceivable(Double totalAmountReceivable) {
		this.totalAmountReceivable = totalAmountReceivable;
	}
	public String getHeritageProject() {
		return heritageProject;
	}
	public void setHeritageProject(String heritageProject) {
		this.heritageProject = heritageProject;
	}
	public int getDegreeDifficulty() {
		return degreeDifficulty;
	}
	public void setDegreeDifficulty(int degreeDifficulty) {
		this.degreeDifficulty = degreeDifficulty;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
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
	public String getZhijian1() {
		return zhijian1;
	}
	public void setZhijian1(String zhijian1) {
		this.zhijian1 = zhijian1;
	}
	public String getZhijian2() {
		return zhijian2;
	}
	public void setZhijian2(String zhijian2) {
		this.zhijian2 = zhijian2;
	}

	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProductdescc() {
		return productdescc;
	}
	public void setProductdescc(String productdescc) {
		this.productdescc = productdescc;
	}
	public String getProductdesce() {
		return productdesce;
	}
	public void setProductdesce(String productdesce) {
		this.productdesce = productdesce;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getEngineer1() {
		return engineer1;
	}
	public void setEngineer1(String engineer1) {
		this.engineer1 = engineer1;
	}
	public String getEngineer2() {
		return engineer2;
	}
	public void setEngineer2(String engineer2) {
		this.engineer2 = engineer2;
	}
	public String getEngineer3() {
		return engineer3;
	}
	public void setEngineer3(String engineer3) {
		this.engineer3 = engineer3;
	}
	public String getMerchandising() {
		return merchandising;
	}
	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}
	public String getLatest_payment_time() {
		return latest_payment_time;
	}
	public void setLatest_payment_time(String latest_payment_time) {
		this.latest_payment_time = latest_payment_time;
	}

	public int getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(int isInvoice) {
		this.isInvoice = isInvoice;
	}


	public String getMaturePurchase() {
		return maturePurchase;
	}
	public void setMaturePurchase(String maturePurchase) {
		this.maturePurchase = maturePurchase;
	}
	@Override
	public String toString() {
		return "Bonusdata [customerId=" + customerId + ", customer_name="
				+ customer_name + ", projectId=" + projectId
				+ ", productdescc=" + productdescc + ", productdesce="
				+ productdesce + ", customerManager=" + customerManager
				+ ", merchandManager1=" + merchandManager1
				+ ", merchandManager2=" + merchandManager2 + ", zhijian1="
				+ zhijian1 + ", zhijian2=" + zhijian2 + ", engineer1="
				+ engineer1 + ", engineer2=" + engineer2 + ", engineer3="
				+ engineer3 + ", merchandising=" + merchandising
				+ ", maturePurchase=" + maturePurchase + ", originalPurchase="
				+ originalPurchase + ", totalSalesUs=" + totalSalesUs
				+ ", totalSalesRmb=" + totalSalesRmb
				+ ", totalGrossProfitMargin=" + totalGrossProfitMargin
				+ ", totalGrossProfitRmb=" + totalGrossProfitRmb
				+ ", totalGrossProfitUs=" + totalGrossProfitUs
				+ ", salesThisPeriodRmb=" + salesThisPeriodRmb
				+ ", accountPayableRmb=" + accountPayableRmb
				+ ", salesThisPeriodUs=" + salesThisPeriodUs
				+ ", profitThisPeriodRmb=" + profitThisPeriodRmb
				+ ", isInvoice=" + isInvoice + ", payment_time=" + payment_time
				+ ", latest_payment_time=" + latest_payment_time
				+ ", degreeDifficulty=" + degreeDifficulty
				+ ", heritageProject=" + heritageProject
				+ ", totalAmountOfArrival=" + totalAmountOfArrival
				+ ", totalAmountReceivable=" + totalAmountReceivable
				+ ", logisticsCost=" + logisticsCost + ", caseStatus="
				+ caseStatus + ", qualityDeductionProject="
				+ qualityDeductionProject + ", grossProfit="
				+ grossProfit + "]";
	}

	public String getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}
}
