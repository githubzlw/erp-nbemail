package cerong.erp.entity;

import java.util.Date;
import java.util.List;



//进账录入表
public class AccountEntryForm {
	 
	/** 编号 */
	private int id;
	/**进账日期 */
	private String transactionDate;
	/**银行交易号 */
	private String transactionReferenceNumber;
	/** 进账金额 */
	private Double tradeAmount;
	/** 到账银行 */
	private String beneficiaryAccountBank;
	/** 付款人名*/
	private String payersName;
	/** 客户Id*/
	private int nBEmailId;
	/** 客户国家*/
	private int country;
	/** 默认进账年*/
	private String exportYear;
	/** 默认进账月*/
	private String exportMonth;
	/**invoice*/
	private String invoice;
	/**分配金额*/
	private double amountOfDistribution;
	/**认领人*/
	private String claimant;
	/**财务确认*/
	private int financialConfirmation;
	/**财务确认人*/
	private int financialConfirmationMan;
	/**货币*/
	private String tradeCurrency;
	/**留言*/
	private String remark;
	/**认领时间*/
	private String claimTime;
	/**推测认领人*/
	private String conjecture;
	/**付款人账号*/
	private String payeeAccount;
	/**0,改进账在处理，1改进账已完结*/
	private int dataProcessing;
	/**0,销售没确认金额，1销售已确认金额*/
	private int salesConfirmationAmount;
	  /** 详情信息*/
	private List<PreparatorEntryForm> accessories;
	/** 详情信息*/
	private List<AmountClaimForm> amountClaimForm;
	
	private String createTime;
	/**0,老客户，1新客户*/
	private int newCustomer;
	/**理由*/
	private String reason;
	/**理由*/
	private String entryPerson;
	/**理由*/
	private Date entryTime;

	public AccountEntryForm() {
	}


	public int getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(int newCustomer) {
		this.newCustomer = newCustomer;
	}
	public int getSalesConfirmationAmount() {
		return salesConfirmationAmount;
	}
	public void setSalesConfirmationAmount(int salesConfirmationAmount) {
		this.salesConfirmationAmount = salesConfirmationAmount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPayeeAccount() {
		return payeeAccount;
	}
	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}
	public String getConjecture() {
		return conjecture;
	}
	public void setConjecture(String conjecture) {
		this.conjecture = conjecture;
	}
	public int getDataProcessing() {
		return dataProcessing;
	}
	public void setDataProcessing(int dataProcessing) {
		this.dataProcessing = dataProcessing;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionReferenceNumber() {
		return transactionReferenceNumber;
	}
	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}
	public Double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getBeneficiaryAccountBank() {
		return beneficiaryAccountBank;
	}
	public void setBeneficiaryAccountBank(String beneficiaryAccountBank) {
		this.beneficiaryAccountBank = beneficiaryAccountBank;
	}
	public String getPayersName() {
		return payersName;
	}
	public void setPayersName(String payersName) {
		this.payersName = payersName;
	}
	public int getnBEmailId() {
		return nBEmailId;
	}
	public void setnBEmailId(int nBEmailId) {
		this.nBEmailId = nBEmailId;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public String getExportYear() {
		return exportYear;
	}
	public void setExportYear(String exportYear) {
		this.exportYear = exportYear;
	}
	public String getExportMonth() {
		return exportMonth;
	}
	public void setExportMonth(String exportMonth) {
		this.exportMonth = exportMonth;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public double getAmountOfDistribution() {
		return amountOfDistribution;
	}
	public void setAmountOfDistribution(double amountOfDistribution) {
		this.amountOfDistribution = amountOfDistribution;
	}
	public String getClaimant() {
		return claimant;
	}
	public void setClaimant(String claimant) {
		this.claimant = claimant;
	}
	public int getFinancialConfirmation() {
		return financialConfirmation;
	}
	public void setFinancialConfirmation(int financialConfirmation) {
		this.financialConfirmation = financialConfirmation;
	}
	public int getFinancialConfirmationMan() {
		return financialConfirmationMan;
	}
	public void setFinancialConfirmationMan(int financialConfirmationMan) {
		this.financialConfirmationMan = financialConfirmationMan;
	}
	public String getTradeCurrency() {
		return tradeCurrency;
	}
	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getClaimTime() {
		return claimTime;
	}
	public void setClaimTime(String claimTime) {
		this.claimTime = claimTime;
	}
	
	public List<PreparatorEntryForm> getAccessories() {
		return accessories;
	}
	public void setAccessories(List<PreparatorEntryForm> accessories) {
		this.accessories = accessories;
	}
	
	public List<AmountClaimForm> getAmountClaimForm() {
		return amountClaimForm;
	}
	public void setAmountClaimForm(List<AmountClaimForm> amountClaimForm) {
		this.amountClaimForm = amountClaimForm;
	}

	@Override
	public String toString() {
		return "AccountEntryForm{" +
				"id=" + id +
				", transactionDate='" + transactionDate + '\'' +
				", transactionReferenceNumber='" + transactionReferenceNumber + '\'' +
				", tradeAmount=" + tradeAmount +
				", beneficiaryAccountBank='" + beneficiaryAccountBank + '\'' +
				", payersName='" + payersName + '\'' +
				", nBEmailId=" + nBEmailId +
				", country=" + country +
				", exportYear='" + exportYear + '\'' +
				", exportMonth='" + exportMonth + '\'' +
				", invoice='" + invoice + '\'' +
				", amountOfDistribution=" + amountOfDistribution +
				", claimant='" + claimant + '\'' +
				", financialConfirmation=" + financialConfirmation +
				", financialConfirmationMan=" + financialConfirmationMan +
				", tradeCurrency='" + tradeCurrency + '\'' +
				", remark='" + remark + '\'' +
				", claimTime='" + claimTime + '\'' +
				", conjecture='" + conjecture + '\'' +
				", payeeAccount='" + payeeAccount + '\'' +
				", dataProcessing=" + dataProcessing +
				", salesConfirmationAmount=" + salesConfirmationAmount +
				", accessories=" + accessories +
				", amountClaimForm=" + amountClaimForm +
				", createTime='" + createTime + '\'' +
				", newCustomer=" + newCustomer +
				", reason='" + reason + '\'' +
				", entryPerson='" + entryPerson + '\'' +
				", entryTime=" + entryTime +
				'}';
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEntryPerson() {
		return entryPerson;
	}

	public void setEntryPerson(String entryPerson) {
		this.entryPerson = entryPerson;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
}
