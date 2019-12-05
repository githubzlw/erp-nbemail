package cerong.erp.entity;

public class AllDrawings {
	
	/**项目号 */
	private String projectNo;
	/**图纸号  */
	private String drawingName;
	/**质检图纸号  */
	private String reportName;
	/**类别,不同类别链接不一样  */
	private  int category;
	/**备注  */
	private  String remark;
	/**时间  */
	private  String uploadTime;
	
	
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	
	public String getDrawingName() {
		return drawingName;
	}
	public void setDrawingName(String drawingName) {
		this.drawingName = drawingName;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	@Override
	public String toString() {
		return "AllDrawings [projectNo=" + projectNo + ", drawingName="
				+ drawingName + ", reportName=" + reportName + ", category="
				+ category + ", remark=" + remark + ", uploadTime="
				+ uploadTime + "]";
	}
	
}
