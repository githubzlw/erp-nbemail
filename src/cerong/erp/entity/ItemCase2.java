package cerong.erp.entity;

public class ItemCase2 {
	
	/** 项目中文名*/
	private String projectDescc;
	  /** 项目英文名*/
    private String projectDesce;
     /** 项目号*/
    private String caseNo;
    /** 合同号*/
    private String bargainNo;
   
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
    /** 采购*/
    private String customerManager;
    /** 采购*/
    private String merchandManager1;
    /** 采购*/
    private String merchandManager2;
    /** 采购*/
    private String merchandising;
    /** 采购*/
    private String originalPurchase;
    /** 采购*/
    private String maturePurchase;
    /** 工厂名*/
    private String factoryName;
    /** 项目等级*/
    private int plantAnalysis;
    /** 项目等级*/
    private int ProjectLevel;
    /** 项目材料属性*/
    private int projectMaterialProperties;
    /** 样品交期*/
    private String dateSample;
    /** 技术支持*/
    private String technicalSupport1;
    /** 合同交期*/
    private String completionTime;
    /** 上传日期*/
    private String dateSampleUploading;
    /** po上传日期*/
    private int poDate;
    /** 上传日期*/
    private String firstPaymentDate;
    /** 合同所有未收发票金额*/
    private Double allUnacceptableInvoiceAmounts;
    /** 已付款所有未收发票金额*/
    private Double allUnacceptableInvoiceAmounts1;
    /** 已经付款4个月以上未收发票金额*/
    private Double amountOfUnpaidInvoices;
    /** 尾款未收发票金额*/
    private Double amountOfNonReceiptAndReceiptOfInvoices;
    /** 金蝶号*/
    private int kingdee;
    /** 2007年付款*/
    private double payment2007;
    /** 金蝶号*/
    private int underInvoice;
    /** 起始时间*/
    private String startTime;
    /** 发票金额*/
    private double invoiceAmount;
    
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Double getAllUnacceptableInvoiceAmounts1() {
		return allUnacceptableInvoiceAmounts1;
	}
	public void setAllUnacceptableInvoiceAmounts1(
			Double allUnacceptableInvoiceAmounts1) {
		this.allUnacceptableInvoiceAmounts1 = allUnacceptableInvoiceAmounts1;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getUnderInvoice() {
		return underInvoice;
	}
	public void setUnderInvoice(int underInvoice) {
		this.underInvoice = underInvoice;
	}
	public String getBargainNo() {
		return bargainNo;
	}
	public void setBargainNo(String bargainNo) {
		this.bargainNo = bargainNo;
	}
	public double getPayment2007() {
		return payment2007;
	}
	public void setPayment2007(double payment2007) {
		this.payment2007 = payment2007;
	}
	public int getKingdee() {
		return kingdee;
	}
	public void setKingdee(int kingdee) {
		this.kingdee = kingdee;
	}
	public Double getAllUnacceptableInvoiceAmounts() {
		return allUnacceptableInvoiceAmounts;
	}
	public void setAllUnacceptableInvoiceAmounts(
			Double allUnacceptableInvoiceAmounts) {
		this.allUnacceptableInvoiceAmounts = allUnacceptableInvoiceAmounts;
	}
	public Double getAmountOfUnpaidInvoices() {
		return amountOfUnpaidInvoices;
	}
	public void setAmountOfUnpaidInvoices(Double amountOfUnpaidInvoices) {
		this.amountOfUnpaidInvoices = amountOfUnpaidInvoices;
	}
	public Double getAmountOfNonReceiptAndReceiptOfInvoices() {
		return amountOfNonReceiptAndReceiptOfInvoices;
	}
	public void setAmountOfNonReceiptAndReceiptOfInvoices(
			Double amountOfNonReceiptAndReceiptOfInvoices) {
		this.amountOfNonReceiptAndReceiptOfInvoices = amountOfNonReceiptAndReceiptOfInvoices;
	}
	public int getProjectLevel() {
		return ProjectLevel;
	}
	public void setProjectLevel(int projectLevel) {
		ProjectLevel = projectLevel;
	}
	public String getTechnicalSupport1() {
		return technicalSupport1;
	}
	public void setTechnicalSupport1(String technicalSupport1) {
		this.technicalSupport1 = technicalSupport1;
	}
	public String getOriginalPurchase() {
		return originalPurchase;
	}
	public void setOriginalPurchase(String originalPurchase) {
		this.originalPurchase = originalPurchase;
	}
	public String getMerchandising() {
		return merchandising;
	}
	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}
	public String getMaturePurchase() {
		return maturePurchase;
	}
	public void setMaturePurchase(String maturePurchase) {
		this.maturePurchase = maturePurchase;
	}
	public int getPoDate() {
		return poDate;
	}
	public void setPoDate(int poDate) {
		this.poDate = poDate;
	}
	public String getFirstPaymentDate() {
		return firstPaymentDate;
	}
	public void setFirstPaymentDate(String firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}
	public String getDateSample() {
		return dateSample;
	}
	public void setDateSample(String dateSample) {
		this.dateSample = dateSample;
	}
	
	public String getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}
	public String getDateSampleUploading() {
		return dateSampleUploading;
	}
	public void setDateSampleUploading(String dateSampleUploading) {
		this.dateSampleUploading = dateSampleUploading;
	}
	
	public int getPlantAnalysis() {
		return plantAnalysis;
	}
	public void setPlantAnalysis(int plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
	}
	public int getProjectMaterialProperties() {
		return projectMaterialProperties;
	}
	public void setProjectMaterialProperties(int projectMaterialProperties) {
		this.projectMaterialProperties = projectMaterialProperties;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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
	public String getZhijian1() {
		return zhijian1;
	}
	public void setZhijian1(String zhijian1) {
		this.zhijian1 = zhijian1;
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
	@Override
	public String toString() {
		return "ItemCase2 [projectDescc=" + projectDescc + ", projectDesce="
				+ projectDesce + ", caseNo=" + caseNo + ", bargainNo="
				+ bargainNo + ", zhijian1=" + zhijian1 + ", zhijian2="
				+ zhijian2 + ", engineer1=" + engineer1 + ", engineer2="
				+ engineer2 + ", engineer3=" + engineer3 + ", customerManager="
				+ customerManager + ", merchandManager1=" + merchandManager1
				+ ", merchandManager2=" + merchandManager2 + ", merchandising="
				+ merchandising + ", originalPurchase=" + originalPurchase
				+ ", maturePurchase=" + maturePurchase + ", factoryName="
				+ factoryName + ", plantAnalysis=" + plantAnalysis
				+ ", ProjectLevel=" + ProjectLevel
				+ ", projectMaterialProperties=" + projectMaterialProperties
				+ ", dateSample=" + dateSample + ", technicalSupport1="
				+ technicalSupport1 + ", completionTime=" + completionTime
				+ ", dateSampleUploading=" + dateSampleUploading + ", poDate="
				+ poDate + ", firstPaymentDate=" + firstPaymentDate
				+ ", allUnacceptableInvoiceAmounts="
				+ allUnacceptableInvoiceAmounts
				+ ", allUnacceptableInvoiceAmounts1="
				+ allUnacceptableInvoiceAmounts1 + ", amountOfUnpaidInvoices="
				+ amountOfUnpaidInvoices
				+ ", amountOfNonReceiptAndReceiptOfInvoices="
				+ amountOfNonReceiptAndReceiptOfInvoices + ", kingdee="
				+ kingdee + ", payment2007=" + payment2007 + ", underInvoice="
				+ underInvoice + ", startTime=" + startTime
				+ ", invoiceAmount=" + invoiceAmount + "]";
	}
    

}
