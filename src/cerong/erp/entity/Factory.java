package cerong.erp.entity;

public class Factory {
	/** id*/
	private int id;
	/** 项目号*/
	private String caseNo;
	/** 工厂名*/
	private String factoryName;
	/** 销售*/
    private String saleName1;
    /** 跟单销售*/
    private String saleName2;
    /** 采购*/
    private String engineer1;
    /** 跟单销售*/
    private String merchandManager1;
    /** 跟单销售*/
    private String merchandising;
    /** 采购*/
    private String merchandManager2;
    /** 状态*/
    private String state;
    /** 金额*/
    private Double money;
    /** 起始时间*/
    private String startTime;
    /** 结束时间*/
    private String outTime;
    /** 时间*/
    private String time;
    
	public String getMerchandising() {
		return merchandising;
	}
	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setMoney(Double money) {
		this.money = money;
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
	public String getSaleName1() {
		return saleName1;
	}
	public void setSaleName1(String saleName1) {
		this.saleName1 = saleName1;
	}
	public String getSaleName2() {
		return saleName2;
	}
	public void setSaleName2(String saleName2) {
		this.saleName2 = saleName2;
	}
	public String getEngineer1() {
		return engineer1;
	}
	public void setEngineer1(String engineer1) {
		this.engineer1 = engineer1;
	}
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	@Override
	public String toString() {
		return "Factory [id=" + id + ", caseNo=" + caseNo + ", factoryName="
				+ factoryName + ", saleName1=" + saleName1 + ", saleName2="
				+ saleName2 + ", engineer1=" + engineer1
				+ ", merchandManager1=" + merchandManager1 + ", merchandising="
				+ merchandising + ", merchandManager2=" + merchandManager2
				+ ", state=" + state + ", money=" + money + ", startTime="
				+ startTime + ", outTime=" + outTime + ", time=" + time + "]";
	}
    
    
}
