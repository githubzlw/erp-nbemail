package cerong.erp.entity;
//a.BargainNo,a.fid,b.FactoryName,"
	//+ "a.friMoney,a.friFacDate,a.checkReason,a.moneytype,a.State
public class FactoryFund {
	/** id*/
	private int id;
	/** 项目号*/
	private String caseNo;
	/** 合同号*/
	private String bargainNo;
	/** 合同号*/
	private String bargainUrl;
	/** 工厂名*/
	private String factoryName;
	/** 付给工厂价格*/
	private Double friMoney;
	/** 付款时间时间*/
	private String friFacDate;
	/** 属性*/
	private String state;
	/** 原因*/
	private String checkReason;
	/** 输入时间*/
	private int moneytype;
	/** 工厂预计全部收款*/
	private Double allMoney;
	/** 工厂实际全部收款*/
	private Double allMoney1;
	
	public Double getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(Double allMoney) {
		this.allMoney = allMoney;
	}
	public Double getAllMoney1() {
		return allMoney1;
	}
	public void setAllMoney1(Double allMoney1) {
		this.allMoney1 = allMoney1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public Double getFriMoney() {
		return friMoney;
	}
	public void setFriMoney(Double friMoney) {
		this.friMoney = friMoney;
	}
	public String getFriFacDate() {
		return friFacDate;
	}
	public void setFriFacDate(String friFacDate) {
		this.friFacDate = friFacDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCheckReason() {
		return checkReason;
	}
	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}
	public int getMoneytype() {
		return moneytype;
	}
	public void setMoneytype(int moneytype) {
		this.moneytype = moneytype;
	}
	
	
	public String getBargainUrl() {
		return bargainUrl;
	}
	public void setBargainUrl(String bargainUrl) {
		this.bargainUrl = bargainUrl;
	}
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	@Override
	public String toString() {
		return "FactoryFund [id=" + id + ", caseNo=" + caseNo + ", bargainNo="
				+ bargainNo + ", bargainUrl=" + bargainUrl + ", factoryName="
				+ factoryName + ", friMoney=" + friMoney + ", friFacDate="
				+ friFacDate + ", state=" + state + ", checkReason="
				+ checkReason + ", moneytype=" + moneytype + ", allMoney="
				+ allMoney + ", allMoney1=" + allMoney1 + "]";
	}
	
	
}
