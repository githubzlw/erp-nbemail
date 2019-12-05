package cerong.erp.entity;

public class Invoiceinfo1 {
	/**客户id */
	private int invoiceId;
	/**客户id */
	private int eid;
	/**客户名 */
	private String name;
	/** 客户邮箱*/
	private String email;
	/** 项目号*/
	private String projectId;
	/** 项目中文名*/
	private  String productdescc;
	/** 项目英文名*/
	private String productdesce;
	/** 销售*/
	private String customermanager;
	/** 跟单销售*/
	private String merchandmanager1;
	/** 需求*/
	private String emailcontent;
	/** 发票号*/
	private String iInvNo;
	/** 发票金额*/
	private Double iSum;
	/** 预计金额*/
	private Double iimoney;
	/** 金额单元*/
	private int imoneytype;
	/** 实际金额*/
	private Double ifmoney;
	/** 到账时间*/
	private String ifdate;
	/** 到账时间*/
	private String iidate;
	/** 发票创建时间*/
	private String icreatedate;
	/**银行 */
	private int ibank;
	/**应付款 */
	private Double accountsPayable;
	/**总进账美元 */
	private Double totalRevenueDollars;
	/**总进账人名币 */
	private Double totalProceedsRMB;
	/**毛利润 */
	private Double grossProfit;
	/**毛利率 */
	private Double grossProfitMargin;
	/**第一笔进账*/
	private Double firstEntry;
	/**第二笔进账*/
	private Double secondEntry;
	/** 销售*/
    private String customerManager;
  
    /**毛利润 */
	private Double queryTimeProfit;
	 /** 起始时间*/
    private String startTime;
    /** 起始时间*/
    private String endTime;
	/** 第一笔进账货币单位*/
	private int currencyUnit1;
	/** 第二笔进账货币单位*/
	private int currencyUnit2;
	/**客户国家*/
	private int countryid;
	


	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getQueryTimeProfit() {
		return queryTimeProfit;
	}
	public void setQueryTimeProfit(Double queryTimeProfit) {
		this.queryTimeProfit = queryTimeProfit;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}
	
	public Double getFirstEntry() {
		return firstEntry;
	}
	public void setFirstEntry(Double firstEntry) {
		this.firstEntry = firstEntry;
	}
	public Double getSecondEntry() {
		return secondEntry;
	}
	public void setSecondEntry(Double secondEntry) {
		this.secondEntry = secondEntry;
	}
	public Double getTotalRevenueDollars() {
		return totalRevenueDollars;
	}
	public void setTotalRevenueDollars(Double totalRevenueDollars) {
		this.totalRevenueDollars = totalRevenueDollars;
	}
	public Double getTotalProceedsRMB() {
		return totalProceedsRMB;
	}
	public void setTotalProceedsRMB(Double totalProceedsRMB) {
		this.totalProceedsRMB = totalProceedsRMB;
	}
	public Double getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}
	public Double getGrossProfitMargin() {
		return grossProfitMargin;
	}
	public void setGrossProfitMargin(Double grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}
	public Double getAccountsPayable() {
		return accountsPayable;
	}
    public void setAccountsPayable(Double accountsPayable) {
		this.accountsPayable = accountsPayable;
	}
    public int getInvoiceId() {
		return invoiceId;
	}
    public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
    public int getEid() {
		return eid;
	}
   public void setEid(int eid) {
		this.eid = eid;
	}
   public String getName() {
		return name;
	}
   public void setName(String name) {
		this.name = name;
	}
   public String getEmail() {
		return email;
	}
   public void setEmail(String email) {
		this.email = email;
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
  public String getCustomermanager() {
		return customermanager;
	}
  public void setCustomermanager(String customermanager) {
		this.customermanager = customermanager;
	}
  public String getMerchandmanager1() {
		return merchandmanager1;
	}
  public void setMerchandmanager1(String merchandmanager1) {
		this.merchandmanager1 = merchandmanager1;
	}
  public String getEmailcontent() {
		return emailcontent;
	}
  public void setEmailcontent(String emailcontent) {
		this.emailcontent = emailcontent;
	}
  public String getiInvNo() {
		return iInvNo;
	}
  public void setiInvNo(String iInvNo) {
		this.iInvNo = iInvNo;
	}
  public Double getiSum() {
		return iSum;
	}
  public void setiSum(Double iSum) {
		this.iSum = iSum;
	}
  public Double getIimoney() {
		return iimoney;
	}
  public void setIimoney(Double iimoney) {
		this.iimoney = iimoney;
	}
  public int getImoneytype() {
		return imoneytype;
	}
  public void setImoneytype(int imoneytype) {
		this.imoneytype = imoneytype;
	}
  public Double getIfmoney() {
		return ifmoney;
	}
  public void setIfmoney(Double ifmoney) {
		this.ifmoney = ifmoney;
	}
  public String getIfdate() {
		return ifdate;
	}
  public void setIfdate(String ifdate) {
		this.ifdate = ifdate;
	}
  public String getIcreatedate() {
		return icreatedate;
	}
  public void setIcreatedate(String icreatedate) {
		this.icreatedate = icreatedate;
	}
  public int getIbank() {
		return ibank;
	}
  public void setIbank(int ibank) {
		this.ibank = ibank;
	}
  public String getIidate() {
		return iidate;
	}
  public void setIidate(String iidate) {
		this.iidate = iidate;
	}

	@Override
	public String toString() {
		return "Invoiceinfo1{" +
				"invoiceId=" + invoiceId +
				", eid=" + eid +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", projectId='" + projectId + '\'' +
				", productdescc='" + productdescc + '\'' +
				", productdesce='" + productdesce + '\'' +
				", customermanager='" + customermanager + '\'' +
				", merchandmanager1='" + merchandmanager1 + '\'' +
				", emailcontent='" + emailcontent + '\'' +
				", iInvNo='" + iInvNo + '\'' +
				", iSum=" + iSum +
				", iimoney=" + iimoney +
				", imoneytype=" + imoneytype +
				", ifmoney=" + ifmoney +
				", ifdate='" + ifdate + '\'' +
				", iidate='" + iidate + '\'' +
				", icreatedate='" + icreatedate + '\'' +
				", ibank=" + ibank +
				", accountsPayable=" + accountsPayable +
				", totalRevenueDollars=" + totalRevenueDollars +
				", totalProceedsRMB=" + totalProceedsRMB +
				", grossProfit=" + grossProfit +
				", grossProfitMargin=" + grossProfitMargin +
				", firstEntry=" + firstEntry +
				", secondEntry=" + secondEntry +
				", customerManager='" + customerManager + '\'' +
				", queryTimeProfit=" + queryTimeProfit +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", currencyUnit1=" + currencyUnit1 +
				", currencyUnit2=" + currencyUnit2 +
				", countryid=" + countryid +
				'}';
	}

	public int getCurrencyUnit1() {
		return currencyUnit1;
	}

	public void setCurrencyUnit1(int currencyUnit1) {
		this.currencyUnit1 = currencyUnit1;
	}

	public int getCurrencyUnit2() {
		return currencyUnit2;
	}

	public void setCurrencyUnit2(int currencyUnit2) {
		this.currencyUnit2 = currencyUnit2;
	}

	public int getCountryid() {
		return countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}
}
