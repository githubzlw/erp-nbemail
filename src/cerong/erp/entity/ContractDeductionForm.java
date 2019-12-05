package cerong.erp.entity;
//合同详情扣款表
public class ContractDeductionForm {
	/**id */
	private int id;
	/**QualityId */
	private int qualityId;
	/** 合同号 */
	private String contractNumber;
	/** 扣款金额 */
	private double amount;
	
	/** 时间 */
	private String createTime;
	/** 项目号 */
	private String caseNO;
	
	
	
	public int getQualityId() {
		return qualityId;
	}
	public void setQualityId(int qualityId) {
		this.qualityId = qualityId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCaseNO() {
		return caseNO;
	}
	public void setCaseNO(String caseNO) {
		this.caseNO = caseNO;
	}
	@Override
	public String toString() {
		return "ContractDeductionForm [id=" + id + ", qualityId=" + qualityId
				+ ", contractNumber=" + contractNumber + ", amount=" + amount
				+ ", createTime=" + createTime + ", caseNO=" + caseNO + "]";
	}
	
	
}
