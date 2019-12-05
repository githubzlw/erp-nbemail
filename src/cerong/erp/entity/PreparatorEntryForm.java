package cerong.erp.entity;

import java.util.Date;



public class PreparatorEntryForm {
	
	/** 编号 */
	private int id;
	/**认领数据Id*/
	private int amountClaimFormId;
	/**invoice */
	private String invoice;
	/** 预计到款 */
	private Double iimoney;
	/** 实际到款 */
	private Double ifmoney;
	/** 到款银行*/
	private String ibank;
	/** 货币单位*/
	private int imoneytype;
	/** invoiceId*/
	private int iid;
	/** 财务确认，0无确认，1，已确认*/
	private int financialConfirmation;
	/**0,改进账在处理，1改进账已完结*/
	private int dataProcessing;
	/**销售提交0，未提交，1已提交*/
	private int salesSubmission;
	//销售名
	private String saleName;
	//销售提交时间
	private String createTime;
	//销售提交时间
	private Date ifdate;
	//财务确认时间
	private String financialConfirmationTime;
	//财务确认时间
	private String caseno;
	//备注
	private String remarks;
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCaseno() {
		return caseno;
	}
	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}
	public Date getIfdate() {
		return ifdate;
	}
	public void setIfdate(Date ifdate) {
		this.ifdate = ifdate;
	}
	public int getSalesSubmission() {
		return salesSubmission;
	}
	public void setSalesSubmission(int salesSubmission) {
		this.salesSubmission = salesSubmission;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFinancialConfirmationTime() {
		return financialConfirmationTime;
	}
	public void setFinancialConfirmationTime(String financialConfirmationTime) {
		this.financialConfirmationTime = financialConfirmationTime;
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
	
	public int getAmountClaimFormId() {
		return amountClaimFormId;
	}
	public void setAmountClaimFormId(int amountClaimFormId) {
		this.amountClaimFormId = amountClaimFormId;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Double getIimoney() {
		return iimoney;
	}
	public void setIimoney(Double iimoney) {
		this.iimoney = iimoney;
	}
	public Double getIfmoney() {
		return ifmoney;
	}
	public void setIfmoney(Double ifmoney) {
		this.ifmoney = ifmoney;
	}
	public String getIbank() {
		return ibank;
	}
	public void setIbank(String ibank) {
		this.ibank = ibank;
	}
	public int getImoneytype() {
		return imoneytype;
	}
	public void setImoneytype(int imoneytype) {
		this.imoneytype = imoneytype;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getFinancialConfirmation() {
		return financialConfirmation;
	}
	public void setFinancialConfirmation(int financialConfirmation) {
		this.financialConfirmation = financialConfirmation;
	}
	@Override
	public String toString() {
		return "PreparatorEntryForm [id=" + id + ", amountClaimFormId="
				+ amountClaimFormId + ", invoice=" + invoice + ", iimoney="
				+ iimoney + ", ifmoney=" + ifmoney + ", ibank=" + ibank
				+ ", imoneytype=" + imoneytype + ", iid=" + iid
				+ ", financialConfirmation=" + financialConfirmation
				+ ", dataProcessing=" + dataProcessing + ", salesSubmission="
				+ salesSubmission + ", saleName=" + saleName + ", createTime="
				+ createTime + ", ifdate=" + ifdate
				+ ", financialConfirmationTime=" + financialConfirmationTime
				+ ", caseno=" + caseno + ", remarks=" + remarks + "]";
	}
	
	
	
}
