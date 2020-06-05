package cerong.erp.entity;

import java.sql.Timestamp;

public class MoneyCheckUp {
	/** 编号id */
	private int id;
	/** 项目号 */
	private String caseNo;
	private String productDescE;
	private String productDescC;
	private String customerManager;
	private String merchandManager1;
	private String merchandManager2;
	private String merchandising;
	private String engineer1;
	private String engineer2;
	private String engineer3;
	private String itemManager;
	public int state;
	public String state1;
	private int facMoney;
	private String mtype;
	private Timestamp facDate;
	private String facReason;
	private String reason;
	private String geldObject;
	private int checktype;
	private String paystate;
	private Timestamp stateDate;
	private String total;
	private int num;
	private int receiptContract;
	/** 状态0.一万以上，emma只能预审 1.其他费用Emma可直接审批通过，2.不是其他费用Emma 10000元以下可直接审批通过,3.审批通过  */
	private int  statea;
	/** 客户备注  */
	private String  customerremarks;
	/** 项目备注  */
	private String  projectnote;
	/** 全部资金*/
	private Double amount;
	
	/** 资金*/
	private Double sumacount;
	/** 利润率*/
	private String profit;
	/** 利润率*/
	private int moneytype;
	/** 是否催款*/
	private int pressformoney;
	/** Emma审批理由*/
	private int approval;
	/** 审批时间*/
	private String approvalTime;
	/** 审批理由*/
	private String reasonsApproval;
	/** 登录日期*/
	private String loginDate;

	/** invoice总额*/
	private String iimoney;

	/** 已到款总额*/
	private String ifmoney;
	/** 1,进入快速通道*/
	private int expressLane;
	/** 1,快速通道理由*/
	private String fastTrackReasons;
	/** Eddie是否审批*/
	private int expressLaneApproval;
	/** 利润率*/
	private int moneytype1;

	public int getMoneytype1() {
		return moneytype1;
	}

	public void setMoneytype1(int moneytype1) {
		this.moneytype1 = moneytype1;
	}

	public String getIimoney() {
		return iimoney;
	}
	public void setIimoney(String iimoney) {
		this.iimoney = iimoney;
	}
	public String getIfmoney() {
		return ifmoney;
	}
	public void setIfmoney(String ifmoney) {
		this.ifmoney = ifmoney;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public int getReceiptContract() {
		return receiptContract;
	}
	public void setReceiptContract(int receiptContract) {
		this.receiptContract = receiptContract;
	}
	public String getMerchandising() {
		return merchandising;
	}
	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	
	public String getReasonsApproval() {
		return reasonsApproval;
	}
	public void setReasonsApproval(String reasonsApproval) {
		this.reasonsApproval = reasonsApproval;
	}
	public int getPressformoney() {
		return pressformoney;
	}
	public void setPressformoney(int pressformoney) {
		this.pressformoney = pressformoney;
	}
	public int getMoneytype() {
		return moneytype;
	}
	public void setMoneytype(int moneytype) {
		this.moneytype = moneytype;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getSumacount() {
		return sumacount;
	}
	public void setSumacount(Double sumacount) {
		this.sumacount = sumacount;
	}
	public String getCustomerremarks() {
		return customerremarks;
	}
	public void setCustomerremarks(String customerremarks) {
		this.customerremarks = customerremarks;
	}
	public String getProjectnote() {
		return projectnote;
	}
	public void setProjectnote(String projectnote) {
		this.projectnote = projectnote;
	}
	public int getStatea() {
		return statea;
	}
	public void setStatea(int statea) {
		this.statea = statea;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
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
	public String getProductDescE() {
		return productDescE;
	}
	public void setProductDescE(String productDescE) {
		this.productDescE = productDescE;
	}
	public String getProductDescC() {
		return productDescC;
	}
	public void setProductDescC(String productDescC) {
		this.productDescC = productDescC;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
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
	public String getEngineer1() {
		return engineer1;
	}
	public void setEngineer1(String engineer1) {
		this.engineer1 = engineer1;
	}
	public String getEngineer2() {
		return engineer2;
	}
	public void setEngineer2(String engineer2) {
		this.engineer2 = engineer2;
	}
	public String getEngineer3() {
		return engineer3;
	}
	public void setEngineer3(String engineer3) {
		this.engineer3 = engineer3;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getFacMoney() {
		return facMoney;
	}
	public void setFacMoney(int facMoney) {
		this.facMoney = facMoney;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public Timestamp getFacDate() {
		return facDate;
	}
	public void setFacDate(Timestamp facDate) {
		this.facDate = facDate;
	}
	public String getFacReason() {
		return facReason;
	}
	public void setFacReason(String facReason) {
		this.facReason = facReason;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getGeldObject() {
		return geldObject;
	}
	public void setGeldObject(String geldObject) {
		this.geldObject = geldObject;
	}
	public int getChecktype() {
		return checktype;
	}
	public void setChecktype(int checktype) {
		this.checktype = checktype;
	}
	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	public Timestamp getStateDate() {
		return stateDate;
	}
	public void setStateDate(Timestamp stateDate) {
		this.stateDate = stateDate;
	}

	@Override
	public String toString() {
		return "MoneyCheckUp{" +
				"id=" + id +
				", caseNo='" + caseNo + '\'' +
				", productDescE='" + productDescE + '\'' +
				", productDescC='" + productDescC + '\'' +
				", customerManager='" + customerManager + '\'' +
				", merchandManager1='" + merchandManager1 + '\'' +
				", merchandManager2='" + merchandManager2 + '\'' +
				", merchandising='" + merchandising + '\'' +
				", engineer1='" + engineer1 + '\'' +
				", engineer2='" + engineer2 + '\'' +
				", engineer3='" + engineer3 + '\'' +
				", itemManager='" + itemManager + '\'' +
				", state=" + state +
				", state1='" + state1 + '\'' +
				", facMoney=" + facMoney +
				", mtype='" + mtype + '\'' +
				", facDate=" + facDate +
				", facReason='" + facReason + '\'' +
				", reason='" + reason + '\'' +
				", geldObject='" + geldObject + '\'' +
				", checktype=" + checktype +
				", paystate='" + paystate + '\'' +
				", stateDate=" + stateDate +
				", total='" + total + '\'' +
				", num=" + num +
				", receiptContract=" + receiptContract +
				", statea=" + statea +
				", customerremarks='" + customerremarks + '\'' +
				", projectnote='" + projectnote + '\'' +
				", amount=" + amount +
				", sumacount=" + sumacount +
				", profit='" + profit + '\'' +
				", moneytype=" + moneytype +
				", pressformoney=" + pressformoney +
				", approval=" + approval +
				", approvalTime='" + approvalTime + '\'' +
				", reasonsApproval='" + reasonsApproval + '\'' +
				", loginDate='" + loginDate + '\'' +
				", iimoney='" + iimoney + '\'' +
				", ifmoney='" + ifmoney + '\'' +
				", expressLane=" + expressLane +
				", fastTrackReasons='" + fastTrackReasons + '\'' +
				", expressLaneApproval=" + expressLaneApproval +
				", moneytype1=" + moneytype1 +
				'}';
	}

	public int getExpressLane() {
		return expressLane;
	}

	public void setExpressLane(int expressLane) {
		this.expressLane = expressLane;
	}

	public String getFastTrackReasons() {
		return fastTrackReasons;
	}

	public void setFastTrackReasons(String fastTrackReasons) {
		this.fastTrackReasons = fastTrackReasons;
	}

	public int getExpressLaneApproval() {
		return expressLaneApproval;
	}

	public void setExpressLaneApproval(int expressLaneApproval) {
		this.expressLaneApproval = expressLaneApproval;
	}
}
