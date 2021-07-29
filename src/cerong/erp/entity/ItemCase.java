package cerong.erp.entity;

import java.util.List;



public class ItemCase {
	/** 客户id */
	private int cid;
	/** 0,无 1,未上传检验计划  2.未上传检验报告*/
	private int inspectionRequirements;
	/** 项目中文名*/
	private String projectDescc;
	  /** 项目英文名*/
    private String projectDesce;
    /** 项目图纸名*/
    private String upload;
    /** 项目号*/
    private String caseNo;
    /** 销售*/
    private String saleName1;
    /** 跟单销售*/
    private String saleName2;
    /** 创建时间*/
    private String createTime;
    /** 质检*/
    private String zhijian1;
    /** 质检*/
    private String zhijian2;
    /** 采购*/
    private String engineer1;
    /** 采购*/
    private String engineer2;
    /** 采购*/
    private String engineer3;
    /** 销售*/
    private String customerManager;
    /** 跟单销售*/
    private String merchandManager1;
    /** 跟单销售*/
    private String merchandising;
    /** 采购*/
    private String merchandManager2;
    /** 需求*/
    private String projectContractNote;
    /** 多个质检报告和计划*/
	private List<POupload> accessories;
	/** 多个合同*/
	private List<Contract> contract;
	/** 多个图纸*/
	private List<TuZhi> tuzhi;
	/** 1.是   2.否*/
    private int commonMode;
	/** 共模理由*/
    private String commonModeResult;
    /** 1.是   2.否选厂是否通过*/
    private int plantAnalysis;
    /** 是否有图片*/
    private int drawingPicture;
    /** 起始时间*/
    private String startTime;
    /** 起始时间*/
    private String endTime;
    /** 起始时间*/
    private String startTime1;
    /** 起始时间*/
    private String endTime1;
    /** 1,新客户 2老客户*/
    private int productState;
     /** po表*/
    private String 	po;
    /** 内容*/
    private String 	content;
    /** 项目等级*/
    private int projectLevel;
    /** Po时间*/
    private String potime;
    /** 生产计划是否上传*/
    private int pdId;
    /** 需求汇总是否上传*/
    private String pdId1;
    /** 检验计划是否上传*/
    private String poId;
    /** 检验计划是否更新*/
    private String poId2;
    /** 质检报告是否上传*/
    private String intro;
    /** 受控版本图纸是否上传*/
    private String remark;

    /** 项目启动会*/
    private String qpId;
    /** 样品分析会*/
    private String qpId1;
    /** 大货分析会*/
    private String qpId2;
    /** 是否合同上传*/
    private String bargainNo;
    /** 样品交期*/
    private String dateSample;
    /** 大货交期*/
    private String completiontime;
    /** 技术报告*/
    private String technicalDocumentation;
    /** 技术报告*/
    private String remarks;

	public String getPartTuzhi() {
		return partTuzhi;
	}

	public void setPartTuzhi(String partTuzhi) {
		this.partTuzhi = partTuzhi;
	}

	/** 零件图纸*/
	private String partTuzhi;
    /** 客户名*/
    private String cusName;
    /** 客户名*/
    private int delay;
    /** 2007年付款*/
    private double payment2007;

	/** 7种图*/
	private List<AllDrawings> picture;
	/** 销售合同  0，不需要上传  1，已上传  2，未上传*/
	private int salesContract;

	/*项目总进账*/
	private double allmoney;

	/*项目总进账*/
	private int caseStatus;

	/*项目总进账*/
	private String feedbacktime;

	/*项目总进账*/
	private String quality_picture;

	/*项目状态*/
	private int project_status;

	/*项目状态*/
	private int complaint_id;

	/*项目状态*/
	private int verification;

	public int getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}



	public double getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(double allmoney) {
		this.allmoney = allmoney;
	}



	public double getPayment2007() {
		return payment2007;
	}
	public void setPayment2007(double payment2007) {
		this.payment2007 = payment2007;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getStartTime1() {
		return startTime1;
	}
	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}
	public String getEndTime1() {
		return endTime1;
	}
	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
	public String getTechnicalDocumentation() {
		return technicalDocumentation;
	}
	public void setTechnicalDocumentation(String technicalDocumentation) {
		this.technicalDocumentation = technicalDocumentation;
	}
	public String getPdId1() {
		return pdId1;
	}
	public void setPdId1(String pdId1) {
		this.pdId1 = pdId1;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getProjectLevel() {
		return projectLevel;
	}
	public void setProjectLevel(int projectLevel) {
		this.projectLevel = projectLevel;
	}
	public String getPotime() {
		return potime;
	}
	public void setPotime(String potime) {
		this.potime = potime;
	}
	public int getPdId() {
		return pdId;
	}
	public void setPdId(int pdId) {
		this.pdId = pdId;
	}
	public String getPoId() {
		return poId;
	}
	public void setPoId(String poId) {
		this.poId = poId;
	}
	public String getPoId2() {
		return poId2;
	}
	public void setPoId2(String poId2) {
		this.poId2 = poId2;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQpId() {
		return qpId;
	}
	public void setQpId(String qpId) {
		this.qpId = qpId;
	}
	public String getQpId1() {
		return qpId1;
	}
	public void setQpId1(String qpId1) {
		this.qpId1 = qpId1;
	}
	public String getQpId2() {
		return qpId2;
	}
	public void setQpId2(String qpId2) {
		this.qpId2 = qpId2;
	}
	
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	public String getDateSample() {
		return dateSample;
	}
	public void setDateSample(String dateSample) {
		this.dateSample = dateSample;
	}
	public String getCompletiontime() {
		return completiontime;
	}
	public void setCompletiontime(String completiontime) {
		this.completiontime = completiontime;
	}
	public int getProductState() {
		return productState;
	}
	public void setProductState(int productState) {
		this.productState = productState;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}
	public String getMerchandising() {
		return merchandising;
	}
	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}
	public int getDrawingPicture() {
		return drawingPicture;
	}
	public void setDrawingPicture(int drawingPicture) {
		this.drawingPicture = drawingPicture;
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
	public int getPlantAnalysis() {
		return plantAnalysis;
	}
	public void setPlantAnalysis(int plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
	}
	public int getCommonMode() {
		return commonMode;
	}
	public void setCommonMode(int commonMode) {
		this.commonMode = commonMode;
	}
	public String getCommonModeResult() {
		return commonModeResult;
	}
	public void setCommonModeResult(String commonModeResult) {
		this.commonModeResult = commonModeResult;
	}
	public int getInspectionRequirements() {
		return inspectionRequirements;
	}
	public void setInspectionRequirements(int inspectionRequirements) {
		this.inspectionRequirements = inspectionRequirements;
	}
	public List<TuZhi> getTuzhi() {
		return tuzhi;
	}
	public void setTuzhi(List<TuZhi> tuzhi) {
		this.tuzhi = tuzhi;
	}
	public String getProjectContractNote() {
		return projectContractNote;
	}
	public void setProjectContractNote(String projectContractNote) {
		this.projectContractNote = projectContractNote;
	}
	public String getMerchandManager1() {
		return merchandManager1;
	}
	public void setMerchandManager1(String merchandManager1) {
		this.merchandManager1 = merchandManager1;
	}
	public List<Contract> getContract() {
		return contract;
	}
	public void setContract(List<Contract> contract) {
		this.contract = contract;
	}
	public List<POupload> getAccessories() {
		return accessories;
	}
	public void setAccessories(List<POupload> accessories) {
		this.accessories = accessories;
	}
	public String getZhijian2() {
		return zhijian2;
	}
	public void setZhijian2(String zhijian2) {
		this.zhijian2 = zhijian2;
	}
	public String getEngineer1() {
		return engineer1;
	}
	public void setEngineer1(String engineer1) {
		this.engineer1 = engineer1;
	}
	public String getMerchandManager2() {
		return merchandManager2;
	}
	public void setMerchandManager2(String merchandManager2) {
		this.merchandManager2 = merchandManager2;
	}
	public String getZhijian1() {
		return zhijian1;
	}
	public void setZhijian1(String zhijian1) {
		this.zhijian1 = zhijian1;
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
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "ItemCase{" +
				"cid=" + cid +
				", inspectionRequirements=" + inspectionRequirements +
				", projectDescc='" + projectDescc + '\'' +
				", projectDesce='" + projectDesce + '\'' +
				", upload='" + upload + '\'' +
				", caseNo='" + caseNo + '\'' +
				", saleName1='" + saleName1 + '\'' +
				", saleName2='" + saleName2 + '\'' +
				", createTime='" + createTime + '\'' +
				", zhijian1='" + zhijian1 + '\'' +
				", zhijian2='" + zhijian2 + '\'' +
				", engineer1='" + engineer1 + '\'' +
				", engineer2='" + engineer2 + '\'' +
				", engineer3='" + engineer3 + '\'' +
				", customerManager='" + customerManager + '\'' +
				", merchandManager1='" + merchandManager1 + '\'' +
				", merchandising='" + merchandising + '\'' +
				", merchandManager2='" + merchandManager2 + '\'' +
				", projectContractNote='" + projectContractNote + '\'' +
				", accessories=" + accessories +
				", contract=" + contract +
				", tuzhi=" + tuzhi +
				", commonMode=" + commonMode +
				", commonModeResult='" + commonModeResult + '\'' +
				", plantAnalysis=" + plantAnalysis +
				", drawingPicture=" + drawingPicture +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", startTime1='" + startTime1 + '\'' +
				", endTime1='" + endTime1 + '\'' +
				", productState=" + productState +
				", po='" + po + '\'' +
				", content='" + content + '\'' +
				", projectLevel=" + projectLevel +
				", potime='" + potime + '\'' +
				", pdId=" + pdId +
				", pdId1='" + pdId1 + '\'' +
				", poId='" + poId + '\'' +
				", poId2='" + poId2 + '\'' +
				", intro='" + intro + '\'' +
				", remark='" + remark + '\'' +
				", qpId='" + qpId + '\'' +
				", qpId1='" + qpId1 + '\'' +
				", qpId2='" + qpId2 + '\'' +
				", bargainNo='" + bargainNo + '\'' +
				", dateSample='" + dateSample + '\'' +
				", completiontime='" + completiontime + '\'' +
				", technicalDocumentation='" + technicalDocumentation + '\'' +
				", remarks='" + remarks + '\'' +
				", cusName='" + cusName + '\'' +
				", delay=" + delay +
				", payment2007=" + payment2007 +
				", picture=" + picture +
				", salesContract=" + salesContract +
				", allmoney=" + allmoney +
				", caseStatus=" + caseStatus +
				", feedbacktime='" + feedbacktime + '\'' +
				", quality_picture='" + quality_picture + '\'' +
				", project_status=" + project_status +
				", complaint_id=" + complaint_id +
				", verification=" + verification +
				'}';
	}

	public List<AllDrawings> getPicture() {
		return picture;
	}

	public void setPicture(List<AllDrawings> picture) {
		this.picture = picture;
	}

	public int getSalesContract() {
		return salesContract;
	}

	public void setSalesContract(int salesContract) {
		this.salesContract = salesContract;
	}

	public String getQuality_picture() {
		return quality_picture;
	}

	public void setQuality_picture(String quality_picture) {
		this.quality_picture = quality_picture;
	}

	public String getFeedbacktime() {
		return feedbacktime;
	}

	public void setFeedbacktime(String feedbacktime) {
		this.feedbacktime = feedbacktime;
	}

	public int getProject_status() {
		return project_status;
	}

	public void setProject_status(int project_status) {
		this.project_status = project_status;
	}

	public int getVerification() {
		return verification;
	}

	public void setVerification(int verification) {
		this.verification = verification;
	}

	public int getComplaint_id() {
		return complaint_id;
	}

	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}
}
