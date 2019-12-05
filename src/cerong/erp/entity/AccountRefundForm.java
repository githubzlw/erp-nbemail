package cerong.erp.entity;

public class AccountRefundForm {
	/** 编号 */
	private int id;
	/**invoice */
	private String invoice;
	/**时间 */
	private String createTime;
	/**录入人 */
	private String operator;
	/** iid */
	private int iid;
	/** 退款金额 */
	private double refundAmount;
	/**项目号 */
	private String caseno;
	/**预计进账金额 */
	private double iimoney;
	/**实际进账金额 */
	private double ifmoney;
	/**退款后进账 */
	private double finalIncome;
	/**审批人 */
	private String approver;
	/**审批时间 */
	private String approvalTime;
	/**管理员审批审批结果 0未操作  1.审批通过  2.审批不通过*/
	private int approvalResults;
	/**退款理由 */
	private String Reason;
	/**财务确认退款  0 暂未退款  1.已完成 */
	private int financialConfirmation;
	/**退款确认人 */
	private String financialConfirmationPerson;
	
	
	public String getFinancialConfirmationPerson() {
		return financialConfirmationPerson;
	}
	public void setFinancialConfirmationPerson(String financialConfirmationPerson) {
		this.financialConfirmationPerson = financialConfirmationPerson;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public int getFinancialConfirmation() {
		return financialConfirmation;
	}
	public void setFinancialConfirmation(int financialConfirmation) {
		this.financialConfirmation = financialConfirmation;
	}
	public String getCaseno() {
		return caseno;
	}
	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}
	public double getIimoney() {
		return iimoney;
	}
	public void setIimoney(double iimoney) {
		this.iimoney = iimoney;
	}
	public double getIfmoney() {
		return ifmoney;
	}
	public void setIfmoney(double ifmoney) {
		this.ifmoney = ifmoney;
	}
	public double getFinalIncome() {
		return finalIncome;
	}
	public void setFinalIncome(double finalIncome) {
		this.finalIncome = finalIncome;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	public int getApprovalResults() {
		return approvalResults;
	}
	public void setApprovalResults(int approvalResults) {
		this.approvalResults = approvalResults;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	@Override
	public String toString() {
		return "AccountRefundForm [id=" + id + ", invoice=" + invoice
				+ ", createTime=" + createTime + ", operator=" + operator
				+ ", iid=" + iid + ", refundAmount=" + refundAmount
				+ ", caseno=" + caseno + ", iimoney=" + iimoney + ", ifmoney="
				+ ifmoney + ", finalIncome=" + finalIncome + ", approver="
				+ approver + ", approvalTime=" + approvalTime
				+ ", approvalResults=" + approvalResults + ", Reason=" + Reason
				+ ", financialConfirmation=" + financialConfirmation
				+ ", financialConfirmationPerson="
				+ financialConfirmationPerson + "]";
	}
	
}
