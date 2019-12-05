package cerong.erp.entity;

public class QualityDeductionForm {
	/**id */
	private int id;
	
	/** 扣款金额 */
	private double qualityDeductions;
	/** 时间 */
	private String createTime;
	/** 项目号 */
	private String caseNO;
	/**invoiceId */
	private int qualityId;
	
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
	
	public double getQualityDeductions() {
		return qualityDeductions;
	}
	public void setQualityDeductions(double qualityDeductions) {
		this.qualityDeductions = qualityDeductions;
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
		return "QualityDeductionForm [id=" + id + ", qualityDeductions="
				+ qualityDeductions + ", createTime=" + createTime
				+ ", caseNO=" + caseNO + ", qualityId=" + qualityId + "]";
	}
	
	
}
