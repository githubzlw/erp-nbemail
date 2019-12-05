package cerong.erp.entity;

public class ProjectCustomer {
	/** 客户id */
	private int eid;
	/** 客户名*/
	private String name;
	/** 项目中文名*/
	private String projectDescc;
	  /** 项目英文名*/
    private String projectDesce;
    /** 项目号*/
    private String caseNo;
    /** 销售*/
    private String saleName1;
    /** 跟单销售*/
    private String saleName2;
    /** 创建时间*/
    private String createTime;
    /** 内容*/
    private String EmailContent;
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectDescc() {
		return projectDescc;
	}
	public void setProjectDescc(String projectDescc) {
		this.projectDescc = projectDescc;
	}
	public String getProjectDesce() {
		return projectDesce;
	}
	public void setProjectDesce(String projectDesce) {
		this.projectDesce = projectDesce;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEmailContent() {
		return EmailContent;
	}
	public void setEmailContent(String emailContent) {
		EmailContent = emailContent;
	}
	@Override
	public String toString() {
		return "ProjectCustomer [eid=" + eid + ", name=" + name
				+ ", projectDescc=" + projectDescc + ", projectDesce="
				+ projectDesce + ", caseNo=" + caseNo + ", saleName1="
				+ saleName1 + ", saleName2=" + saleName2 + ", createTime="
				+ createTime + ", EmailContent=" + EmailContent + "]";
	}
    

}
