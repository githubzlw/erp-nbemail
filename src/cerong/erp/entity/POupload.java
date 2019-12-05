package cerong.erp.entity;

public class POupload {
	
	/** 项目号 */
	private String CaseNo;
	/** 下载链接*/
	private String url;
	  /** 版本*/
    private String intro;
    /** 时间*/
    private String uploadtime;
    /** 样式*/
    private String type;
    /** 样式*/
    private int status;
    
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCaseNo() {
		return CaseNo;
	}
	public void setCaseNo(String caseNo) {
		CaseNo = caseNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	@Override
	public String toString() {
		return "POupload [CaseNo=" + CaseNo + ", url=" + url + ", intro="
				+ intro + ", uploadtime=" + uploadtime + ", type=" + type
				+ ", status=" + status + "]";
	}
    
}
