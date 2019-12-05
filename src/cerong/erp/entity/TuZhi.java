package cerong.erp.entity;

public class TuZhi {
	/** id */
	private int id;
	/** 0,表示存在，1，表示已删除 */
	private int isDelete;
	/** 项目号 */
	private String CaseNo;
	/** 中文图纸*/
	private String cname;
	  /** 英文图纸*/
    private String ename;
    /** 上传中文图纸时间*/
    private String uploadtime;
    /** 上传英文图纸时间*/
    private String uploadTime1;
    /** 上传人*/
    private String name;
    /** 上传人*/
    private String taskSystemTechnicalDocumentation;
    
    
	public String getTaskSystemTechnicalDocumentation() {
		return taskSystemTechnicalDocumentation;
	}
	public void setTaskSystemTechnicalDocumentation(
			String taskSystemTechnicalDocumentation) {
		this.taskSystemTechnicalDocumentation = taskSystemTechnicalDocumentation;
	}
	public String getUploadTime1() {
		return uploadTime1;
	}
	public void setUploadTime1(String uploadTime1) {
		this.uploadTime1 = uploadTime1;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaseNo() {
		return CaseNo;
	}
	public void setCaseNo(String caseNo) {
		CaseNo = caseNo;
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TuZhi [id=" + id + ", isDelete=" + isDelete + ", CaseNo="
				+ CaseNo + ", cname=" + cname + ", ename=" + ename
				+ ", uploadtime=" + uploadtime + ", uploadTime1=" + uploadTime1
				+ ", name=" + name + ", taskSystemTechnicalDocumentation="
				+ taskSystemTechnicalDocumentation + "]";
	}
    

}
