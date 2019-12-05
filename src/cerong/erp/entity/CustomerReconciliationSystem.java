package cerong.erp.entity;
//客户对账
public class CustomerReconciliationSystem {
	/** 客户id */
	private int cid;
	/** 客户名 */
	private String cusName;
	/** 跟单销售名 */
	private String name;
	/** 所有银行到账总额 */
	private Double allBankBills;
	
	
	/**系统录入的所有 Invoice总额 */
	private Double totalSystemEntry;
	/** 系统录入的所有到账的总额 */
	private Double totalAmountDue;
	/** 未到账金额 */
	private Double outstandingAmount;
	/** 预估时间 */
	private String estimatedTime;
	/** 项目号 */
	private String projectId;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAllBankBills() {
		return allBankBills;
	}
	public void setAllBankBills(Double allBankBills) {
		this.allBankBills = allBankBills;
	}
	public Double getTotalSystemEntry() {
		return totalSystemEntry;
	}
	public void setTotalSystemEntry(Double totalSystemEntry) {
		this.totalSystemEntry = totalSystemEntry;
	}
	public Double getTotalAmountDue() {
		return totalAmountDue;
	}
	public void setTotalAmountDue(Double totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}
	public Double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	@Override
	public String toString() {
		return "CustomerReconciliationSystem [cid=" + cid + ", cusName="
				+ cusName + ", name=" + name + ", allBankBills=" + allBankBills
				+ ", totalSystemEntry=" + totalSystemEntry
				+ ", totalAmountDue=" + totalAmountDue + ", outstandingAmount="
				+ outstandingAmount + ", estimatedTime=" + estimatedTime
				+ ", projectId=" + projectId + "]";
	}
	
	
	
}
