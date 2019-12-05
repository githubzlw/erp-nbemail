package cerong.erp.entity;

public class CSjishu {
	/** id */
	private int id;
	/** 地址 */
	private int url;
	/** 项目号 */
	private String CaseNo;
	/** 上传人*/
	private String uploader;
	  /** 上传时间*/
    private String uploadtime;
    /** 上传时间*/
    private String taskTechnicalAgreement;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUrl() {
		return url;
	}
	public void setUrl(int url) {
		this.url = url;
	}
	public String getCaseNo() {
		return CaseNo;
	}
	public void setCaseNo(String caseNo) {
		CaseNo = caseNo;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getTaskTechnicalAgreement() {
		return taskTechnicalAgreement;
	}
	public void setTaskTechnicalAgreement(String taskTechnicalAgreement) {
		this.taskTechnicalAgreement = taskTechnicalAgreement;
	}
	@Override
	public String toString() {
		return "CSjishu [id=" + id + ", url=" + url + ", CaseNo=" + CaseNo
				+ ", uploader=" + uploader + ", uploadtime=" + uploadtime
				+ ", taskTechnicalAgreement=" + taskTechnicalAgreement + "]";
	}
    
    
}
