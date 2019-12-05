package cerong.erp.entity;
/** 项目会议列表*/
public class MeetingRecord {
	/** 项目号*/
	private String caseNo;
	  /** 新项目启动会*/
    private int newProject;
    /** 新项目启动会时间*/
    private String newProjectTime;
     /** 项目周进展会*/
    private int projectZhouJin;
    /** 项目周进展会时间*/
    private String projectZhouJinTime;
    /** 质量分析会（样品）*/
    private int qualityAnalysis;
    /** 质量分析会（样品）时间*/
    private String qualityAnalysisTime;
    /** 大货质量会议*/
    private int massQualityConference;
    /** 大货质量会议时间*/
    private String massQualityConferenceTime;
    /** 质量分析会（小批量）*/
    private int smallQuantity;
    /** 质量分析会（小批量）时间*/
    private String smallQuantityTime;
    /** 需开会议*/
    private String conferenceMessage;
    /** 需开会议*/
    private String merchandManager1;
    /** 跟单销售*/
    private String merchandising;
    
	public String getMerchandising() {
		return merchandising;
	}
	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}
	public String getMerchandManager1() {
		return merchandManager1;
	}
	public void setMerchandManager1(String merchandManager1) {
		this.merchandManager1 = merchandManager1;
	}
	public String getConferenceMessage() {
		return conferenceMessage;
	}
	public void setConferenceMessage(String conferenceMessage) {
		this.conferenceMessage = conferenceMessage;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public int getNewProject() {
		return newProject;
	}
	public void setNewProject(int newProject) {
		this.newProject = newProject;
	}
	public String getNewProjectTime() {
		return newProjectTime;
	}
	public void setNewProjectTime(String newProjectTime) {
		this.newProjectTime = newProjectTime;
	}
	public int getProjectZhouJin() {
		return projectZhouJin;
	}
	public void setProjectZhouJin(int projectZhouJin) {
		this.projectZhouJin = projectZhouJin;
	}
	public String getProjectZhouJinTime() {
		return projectZhouJinTime;
	}
	public void setProjectZhouJinTime(String projectZhouJinTime) {
		this.projectZhouJinTime = projectZhouJinTime;
	}
	public int getQualityAnalysis() {
		return qualityAnalysis;
	}
	public void setQualityAnalysis(int qualityAnalysis) {
		this.qualityAnalysis = qualityAnalysis;
	}
	public String getQualityAnalysisTime() {
		return qualityAnalysisTime;
	}
	public void setQualityAnalysisTime(String qualityAnalysisTime) {
		this.qualityAnalysisTime = qualityAnalysisTime;
	}
	public int getMassQualityConference() {
		return massQualityConference;
	}
	public void setMassQualityConference(int massQualityConference) {
		this.massQualityConference = massQualityConference;
	}
	public String getMassQualityConferenceTime() {
		return massQualityConferenceTime;
	}
	public void setMassQualityConferenceTime(String massQualityConferenceTime) {
		this.massQualityConferenceTime = massQualityConferenceTime;
	}
	public int getSmallQuantity() {
		return smallQuantity;
	}
	public void setSmallQuantity(int smallQuantity) {
		this.smallQuantity = smallQuantity;
	}
	public String getSmallQuantityTime() {
		return smallQuantityTime;
	}
	public void setSmallQuantityTime(String smallQuantityTime) {
		this.smallQuantityTime = smallQuantityTime;
	}
	@Override
	public String toString() {
		return "MeetingRecord [caseNo=" + caseNo + ", newProject=" + newProject
				+ ", newProjectTime=" + newProjectTime + ", projectZhouJin="
				+ projectZhouJin + ", projectZhouJinTime=" + projectZhouJinTime
				+ ", qualityAnalysis=" + qualityAnalysis
				+ ", qualityAnalysisTime=" + qualityAnalysisTime
				+ ", massQualityConference=" + massQualityConference
				+ ", massQualityConferenceTime=" + massQualityConferenceTime
				+ ", smallQuantity=" + smallQuantity + ", smallQuantityTime="
				+ smallQuantityTime + ", conferenceMessage="
				+ conferenceMessage + ", merchandManager1=" + merchandManager1
				+ ", merchandising=" + merchandising + "]";
	}
    
    
}
