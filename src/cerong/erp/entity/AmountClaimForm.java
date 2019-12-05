package cerong.erp.entity;
//金额认领详情表
public class AmountClaimForm {
	/** 编号 */
	private int id;
	/**进账录入表Id */
	private int accountEntryId;
	/** 默认进账年 */
	private String exportYear;
	/** 默认进账月 */
	private String exportMonth;
	/** invoice */
	private String invoice;
	/** 录入金额 */
	private double sumMoney;
	/** 财务确认，0无确认，1，已确认*/
	private int financialConfirmation;
	/**财务确认时间 */
	private String confirmationTime;
	/**0,改进账在处理，1改进账已完结*/
	private int dataProcessing;
	/**国家*/
	private int country;
	
	/**国家*/
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
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
	public int getAccountEntryId() {
		return accountEntryId;
	}
	public void setAccountEntryId(int accountEntryId) {
		this.accountEntryId = accountEntryId;
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
	public double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}
	public int getFinancialConfirmation() {
		return financialConfirmation;
	}
	public void setFinancialConfirmation(int financialConfirmation) {
		this.financialConfirmation = financialConfirmation;
	}
	public String getConfirmationTime()   {
		return confirmationTime;
	}
	public void setConfirmationTime(String confirmationTime) {
		this.confirmationTime = confirmationTime;
	}
	@Override
	public String toString() {
		return "AmountClaimForm [id=" + id + ", accountEntryId="
				+ accountEntryId + ", exportYear=" + exportYear
				+ ", exportMonth=" + exportMonth + ", invoice=" + invoice
				+ ", sumMoney=" + sumMoney + ", financialConfirmation="
				+ financialConfirmation + ", confirmationTime="
				+ confirmationTime + ", dataProcessing=" + dataProcessing
				+ ", country=" + country + ", state=" + state + "]";
	}
    
}
