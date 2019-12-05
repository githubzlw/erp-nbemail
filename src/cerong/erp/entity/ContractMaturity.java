package cerong.erp.entity;

import java.util.Date;

public class ContractMaturity {
	/**id */
	private int id;
	/** 合同号 */
	private String bargainNo;
	/** 工厂名 */
	private String factory;
	
	/** 合同总金额 */
	private String money;
	/** 项目号 */
	private String caseNo;
	/** 合同交期 */
	private String completiontime;
	
	
	/** 出货日期 */
	private String timeDate;
	/** 合同工厂付款日期 */
	private String paytime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getCompletiontime() {
		return completiontime;
	}
	public void setCompletiontime(String completiontime) {
		this.completiontime = completiontime;
	}
	public String getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(String timeDate) {
		this.timeDate = timeDate;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	@Override
	public String toString() {
		return "ContractMaturity [id=" + id + ", bargainNo=" + bargainNo
				+ ", factory=" + factory + ", money=" + money + ", caseNo="
				+ caseNo + ", completiontime=" + completiontime + ", timeDate="
				+ timeDate + ", paytime=" + paytime + "]";
	}
	
	
}
