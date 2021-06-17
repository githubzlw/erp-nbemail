package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.ItCaseIdDao;
import cerong.erp.dao.ItCaseIdDaoImpl;
import cerong.erp.entity.*;


public  class ItCaseIdService implements ItCaseIdServiceImpl  {
	ItCaseIdDaoImpl dao = new ItCaseIdDao();

	@Override
	public int updateSaleName(String saleName, String strProjectId) {
		
		return dao.updateSaleName(saleName,strProjectId);
	}

	@Override
	public int updateSaleName1(String saleName1, String strProjectId) {
		
		return dao.updateSaleName1(saleName1,strProjectId);
	}

	@Override
	public int deleteSaleName(String strProjectId) {
		
		return dao.deleteSaleName(strProjectId);
	}

	@Override
	public int deleteSaleName1(String strProjectId) {
		
		return dao.deleteSaleName1(strProjectId);
	}

	@Override
	public int addItCase(String file1,String backcase,String caseNo, int cid, 
			String content, String saleName, String saleName1,
			String projectDesce, String projectDescc, int ddlType,
			String orderGroup, String customerGroup, int ddlSelectPriceDays,String time, String quotername) {
		
		return dao.addItCase(file1,backcase,caseNo,cid,content,saleName,saleName1,projectDesce,projectDescc,ddlType,orderGroup,customerGroup,ddlSelectPriceDays,time,quotername);
	}

	@Override
	public String getCaseNo() {
		
		return dao.getCaseNo();
	}

	@Override
	public int addCase(String title, String content, String caseNo,
			String empName, String time, String file1) {
		
		 return dao.addCase(title,content,  caseNo,
			 empName,  time, file1);
	}

	@Override
	public String getCaseNo1(String projectId) {
		
		return dao.getCaseNo1(projectId);
	}

	@Override
	public int addItCase(String backcase, String caseNo, int cid,
			String content, String saleName, String saleName1,
			String projectDesce, String projectDescc, int ddlSelectPriceDays,
			String time,String quotername) {
		
		return dao.addItCase(backcase,caseNo,cid,content,saleName,saleName1,projectDesce,projectDescc,ddlSelectPriceDays,time,quotername);
	}

	@Override
	public String getproject() {
		
		return dao.getproject();
	}

	@Override
	public List<ItemCase> getall() {
		
		return dao.getall();
	}

	@Override
	public List<ItemCase1> getall1() {
		
		return dao.getall1();
	}

	@Override
	public List<ItemCase> getall(int cid) {
		
		return dao.getall(cid);
	}

	@Override
	public int getalltotal(int cid) {
		
		return dao.getalltotal(cid);
	}

	@Override
	public ItemCase getall(String caseno) {
		
		return dao.getall(caseno);
	}

	@Override
	public int updatequoter(String quoter, String strProjectId) {
		
		return dao.updatequoter(quoter,strProjectId);
	}

	@Override
	public int deletequoter(String strProjectId) {
		
		return dao.deletequoter(strProjectId);
	}

	@Override
	public Invoiceinfo1 getall1(String projectId) {
		
		return dao.getall1(projectId);
	}

	@Override
	public List<ItemCase2> getalll() {
		
		return dao.getalll();
	}

	@Override
	public int geteid(String projectId) {
		
		return dao.geteid(projectId);
	}

	@Override
	public int updateprojectnote(String projectId, String projectnote) {
		
		return dao.updateprojectnote(projectId,projectnote);
	}

	@Override
	public List<ItemCase2> getPurchasingSystem() {
		
		return dao.getPurchasingSystem();
	}

	@Override
	public List<ItemCase2> getPurchasingSystem1(String projectId) {
		
		return dao.getPurchasingSystem1(projectId);
	}

	@Override
	public List<ItemCase> getAllItem(ItemCase it) {
		
		return dao.getAllItem(it);
	}

	@Override
	public List<ItemCase> getprojectContract(ItemCase it) {
		
		return dao.getprojectContract(it);
	}

	@Override
	public int updateCaseNo(String reason, String caseNo) {
		
		return dao.updateCaseNo(reason,caseNo);
	}

	@Override
	public List<TuZhi> viewAllDrawings(ItemCase it) {
		
		return dao.viewAllDrawings(it);
	}

	@Override
	public List<MeetingRecord> viewConferenceRecords(MeetingRecord it) {
		
		return dao.viewConferenceRecords(it);
	}

	@Override
	public int updateConference(String projectId, String conferenceMessage,
			String messsage) {
		
		return dao.updateConference(projectId,conferenceMessage,messsage);
	}

	@Override
	public List<MeetingRecord> viewConferenceRecords1(MeetingRecord it) {
		
		return dao.viewConferenceRecords1(it);
	}

	@Override
	public List<ItemCase> getAllProject(ItemCase it) {
		
		return dao.getAllProject(it);
	}

	@Override
	public int updateCommonMode(String projectId, String reason, int sign) {
		
		return dao.updateCommonMode(projectId,reason,sign);
	}

	@Override
	public int updateFactory(String projectId, int factory) {
		
		return dao.updateFactory(projectId,factory);
	}

	@Override
	public String getContent(String projectNo) {
		
		return dao.getContent(projectNo);
	}

	@Override
	public int addTuZhi(String caseNo, String backsingleCase) {
		
		return dao.addTuZhi(caseNo,backsingleCase);
	}

	@Override
	public int addPOupload(String caseNo, String backsingleCase) {
		
		return dao.addPOupload(caseNo,backsingleCase);
	}

	@Override
	public int addQuotePrice(String caseNo, String backsingleCase) {
		
		return dao.addQuotePrice(caseNo,backsingleCase);
	}

	@Override
	public int addQuotationAnalysis(String caseNo, String backsingleCase) {
		
		return dao.addQuotationAnalysis(caseNo,backsingleCase);
	}

	@Override
	public int updateAll(String caseNo, String caseNo1) {
		
		return dao.updateAll(caseNo,caseNo1);
	}

	@Override
	public List<Factory> factoryUnpaidStatistics(Factory it) {
		
		return dao.factoryUnpaidStatistics(it);
	}

	@Override
	public Double getAmountApproved(Factory it) {
		
		return dao.getAmountApproved(it);
	}

	@Override
	public Double getPlanSave(Factory it) {
		
		return dao.getPlanSave(it);
	}

	@Override
	public int updatename(ItemCase it, int total) {
		
		return dao.updatename(it,total);
	}

	@Override
	public String getCaseNo2(int erpcid1) {
		
		return dao.getCaseNo2(erpcid1);
	}

	@Override
	public int getId(String caseNo) {
		
		return dao.getId(caseNo);
	}

	@Override
	public List<invoiceinfo> CustomerPaymentStatistics(invoiceinfo it) {
		
		return dao.CustomerPaymentStatistics(it);
	}

	@Override
	public Double getCompletedMoney(Factory it) {
		
		return dao.getCompletedMoney(it);
	}

	@Override
	public ItemCase2 getInspectionReport(String projectId) {
		
		return dao.getInspectionReport(projectId);
	}

	@Override
	public List<ItemCase2> getPurchasingSystem1() {
		
		return dao.getPurchasingSystem1();
	}

	@Override
	public int add(String uploader, String projectNo, String reason,
			String createtime, String name) {
		
		return dao.add(uploader,projectNo,reason,createtime,name);
	}

	@Override
	public List<invoiceinfo> nonPaymentCustomers(invoiceinfo it) {
		
		return dao.nonPaymentCustomers(it);
	}

	@Override
	public int updateAllSaleName(int customerId, String saleName1, int i) {
		
		return dao.updateAllSaleName(customerId,saleName1,i);
	}

	@Override
	public int deleteMerchandiser(String strProjectId) {
		
		return dao.deleteMerchandiser(strProjectId);
	}

	@Override
	public int updateMerchandiser(String saleName1, String strProjectId) {
		
		return dao.updateMerchandiser(saleName1,strProjectId);
	}

	@Override
	public List<invoiceinfo> nonPaymentCustomers1(invoiceinfo it) {
		
		return dao.nonPaymentCustomers1(it);
	}

	@Override
	public int find(String empEName, String caseno) {
		
		return dao.find(empEName,caseno);
	}

	@Override
	public List<ItemCase> startProjectStatistics(ItemCase it) {
		
		return dao.startProjectStatistics(it);
	}

	@Override
	public int insert(String fileName, String projectId, String userName,
			int fid) {
		
		return dao.insert(fileName,projectId,userName,fid);
	}

	@Override
	public ItemCase getCaseno(String projectId, String contractNumber) {
		
		return dao.getCaseno(projectId,contractNumber);
	}

	@Override
	public int updateAll(ItemCase2 item) {
		
		return dao.updateAll(item);
	}

	@Override
	public int add(String factoryName, String factoryId) {
		
		return dao.add(factoryName,factoryId);
	}

	@Override
	public List<ItemCase> startProjectStatistics1(ItemCase it) {
		
		return dao.startProjectStatistics1(it);
	}

	@Override
	public int updateCaseStatus(String caseno) {
		
		return dao.updateCaseStatus(caseno);
	}

	@Override
	public int updateRemarks(String caseno, String remarks) {
		
		return dao.updateRemarks(caseno,remarks);
	}

	@Override
	public int updateFpRemarks(String caseno, String remarks) {

		return dao.updateFpRemarks(caseno,remarks);
	}




	@Override
	public int insert1(String fileName, String projectId, String userName, int fid) {
		
		return dao.insert1(fileName,projectId,userName,fid);
	}

	@Override
	public List<ItemCase2> invoiceFactoryOwnedToUs(ItemCase2 it) {
		
		return dao.invoiceFactoryOwnedToUs(it);
	}

	@Override
	public List<ItemCase2> invoiceFactoryOwnedToUsNew(ItemCase2 it) {

		return dao.invoiceFactoryOwnedToUsNew(it);
	}

	@Override
	public List<FactoryReconciliation> getAllDetailedAccounts(ItemCase2 itemcase) {
		
		return dao.getAllDetailedAccounts(itemcase);
	}

	@Override
	public List<ItemCase2> invoiceDocumentarySaleOwnedToUs(ItemCase2 it) {
		
		return dao.invoiceDocumentarySaleOwnedToUs(it);
	}

	@Override
	public List<ItemCase2> invoiceDocumentaryPurchaseOwnedToUs(ItemCase2 it) {
		
		return dao.invoiceDocumentaryPurchaseOwnedToUs(it);
	}

	@Override
	public List<FactoryReconciliation> getFinalInvoice(ItemCase2 itemcase) {
		
		return dao.getFinalInvoice(itemcase);
	}
	@Override
	public List<FactoryReconciliation> factoryNameByInvoiceName(String invoiceName) {

		return dao.factoryNameByInvoiceName(invoiceName);
	}

	@Override
	public List<FactoryReconciliation> factoryPayInfo(String factoryName) {

		return dao.factoryPayInfo(factoryName);
	}

	@Override
	public List<FactoryReconciliation> factoryPayInfoNew(String factoryName) {

		return dao.factoryPayInfoNew(factoryName);
	}

	@Override
	public List<FactoryReconciliation> factoryPayInfoDetail(String factoryName) {

		return dao.factoryPayInfoDetail(factoryName);
	}



	@Override
	public List<FactoryReconciliation> getPaymentExceededApril(
			ItemCase2 itemcase) {
		
		return dao.getPaymentExceededApril(itemcase);
	}

	@Override
	public List<ArrivalAccountCorrespondenceTable> enterTheCustomerRelevanceTableIntoTheAccount(
			ArrivalAccountCorrespondenceTable it) {
		
		return dao.enterTheCustomerRelevanceTableIntoTheAccount(it);
	}

	@Override
	public List<ItemCase2> invoiceDocumentarySaleOwnedToUs1(ItemCase2 it) {
		
		return dao.invoiceDocumentarySaleOwnedToUs1(it);
	}

	@Override
	public List<ItemCase2> invoiceFactoryOwnedToUs1(ItemCase2 it) {
		
		return dao.invoiceFactoryOwnedToUs1(it);
	}

	@Override
	public TechnologyTable getAll(String process1) {
		
		return dao.getAll(process1);
	}

	@Override
	public void updateProjectMaterialProperties(int processAttributes, String projectNo) {
		dao.updateProjectMaterialProperties(processAttributes,projectNo);
		
	}

	@Override
	public ItemCase getStartTime() {
		
		return dao.getStartTime();
	}

    @Override
    public int updateFastTrackReasons(int id, String fastTrackReasons) {
        return dao.updateFastTrackReasons(id,fastTrackReasons);
    }

	@Override
	public int getSalesContract(String caseNo) {
		return dao.getSalesContract(caseNo);
	}

	@Override
	public int getFactoryName(String caseNo) {
		return dao.getFactoryName(caseNo);
	}

	@Override
	public void updateDividendBalance(List<Bonusdata> list) {
		dao.updateDividendBalance(list);
	}

	@Override
	public List<ItemCase1> getOrderCustomerList(EmailUser user) {
		return dao.getOrderCustomerList(user);
	}

	@Override
	public List<ItemCase1> getNoOrderCustomerList(EmailUser user) {
		return dao.getNoOrderCustomerList(user);
	}

	@Override
	public List<ItemCase1> getallCustomerList(EmailUser user) {
		return dao.getallCustomerList(user);
	}

	@Override
	public List<ItemCase1> getnewCustomerList(EmailUser user) {
		return dao.getnewCustomerList(user);
	}

	@Override
	public List<ItemCase1> getfixedTimeCustomersList(EmailUser user) {
		return dao.getfixedTimeCustomersList(user);
	}

    @Override
    public List<ItemCase> getAllProjectNo(ItemCase it) {

		return dao.getAllProjectNo(it);
    }

	@Override
	public ItemCase getProjectStatistics(ItemCase itcase) {
		return dao.getProjectStatistics(itcase);
	}


}
