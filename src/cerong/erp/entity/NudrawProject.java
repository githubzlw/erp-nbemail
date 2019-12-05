package cerong.erp.entity;

public class NudrawProject {
	
	/** 项目号*/
	private String caseNo;
	/** 质检 */
	private String zhijian;
	/** 质检1 */
	private String zhijian1;
	
	/** 销售*/
	private String customerManager;
	/** 销售1*/
	private String merchandManager1;
	/** 销售2*/
	private String merchandManager2;
	
	/** 跟单时间*/
	private String iidate;
    
	
	
	
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

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getZhijian() {
		return zhijian;
	}

	public void setZhijian(String zhijian) {
		this.zhijian = zhijian;
	}

	public String getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	public String getIidate() {
		return iidate;
	}

	public void setIidate(String iidate) {
		this.iidate = iidate;
	}

	@Override
	public String toString() {
		return "NudrawProject [caseNo=" + caseNo + ", zhijian=" + zhijian
				+ ", zhijian1=" + zhijian1 + ", customerManager="
				+ customerManager + ", merchandManager1=" + merchandManager1
				+ ", merchandManager2=" + merchandManager2 + ", iidate="
				+ iidate + ", getMerchandManager1()=" + getMerchandManager1()
				+ ", getMerchandManager2()=" + getMerchandManager2()
				+ ", getZhijian1()=" + getZhijian1() + ", getCaseNo()="
				+ getCaseNo() + ", getZhijian()=" + getZhijian()
				+ ", getCustomerManager()=" + getCustomerManager()
				+ ", getIidate()=" + getIidate() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	


	
	

}
