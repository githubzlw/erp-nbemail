package cerong.erp.entity;

public class QuotePrice {
	/** 编号id */
	private int id;
	/** 项目号 */
	private String projectNo;
	/** 附件名 */
	private String fileUrl;
	/** 内容 */
	private String details;
	/** 日期 */
	private String createDate;
	/** 人员 */
	private String operator;
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "QuotePrice [id=" + id + ", projectNo=" + projectNo
				+ ", fileUrl=" + fileUrl + ", details=" + details
				+ ", createDate=" + createDate + ", operator=" + operator + "]";
	}

}
