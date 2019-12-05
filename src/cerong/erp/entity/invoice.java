package cerong.erp.entity;

public class invoice {
	/** id*//*
	private int iid;*/
	/**eid */
	private int eid;
	/** projectid*/
	private String projectid;
	/** 全部资金*/
	private Double amount;
	/** 时间*/
	private String createtime;
	/** 时间*/
	private String updatetime;
	/** 资金*/
	private Double sumacount;
	/** 销售*/
	private String customerManager;
	/** 跟单销售*/
	private String merchandManager1;
	
	
	
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
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
	public Double getSumacount() {
		return sumacount;
	}
	public void setSumacount(Double sumacount) {
		this.sumacount = sumacount;
	}
	/*public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}*/
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "invoice [eid=" + eid + ", projectid=" + projectid + ", amount="
				+ amount + ", createtime=" + createtime + ", updatetime="
				+ updatetime + ", sumacount=" + sumacount
				+ ", customerManager=" + customerManager
				+ ", merchandManager1=" + merchandManager1 + "]";
	}
	

}
