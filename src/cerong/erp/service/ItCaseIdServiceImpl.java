package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.*;
;

public interface ItCaseIdServiceImpl {
	/**
	 * ��������:�޸���������
	 * author:wy
	 * date:2016��4��15��
	 * @return
	 */
public	int updateSaleName(String saleName, String strProjectId);
/**
 * ��������:�޸����и�����
 * author:wy
 * date:2016��4��15��
 * @return
 */

	public int updateSaleName1(String saleName1, String strProjectId);
	/**
	 * ��������:ɾ����������
	 * author:wy
	 * date:2016��4��15��
	 * @return
	 */
public int deleteSaleName( String strProjectId);
/**
 * ��������:ɾ�����и�����
 * author:wy
 * date:2016��4��15��
 * @return
 */
public int deleteSaleName1( String strProjectId);
/**
 * ��������:ERP�ϴ�����Ŀ
 * author:wy
 * date:2016��5��16��
 * @return
 */
public int addItCase(String file1, String backsingleCase, String caseNo,
		int cid, String content, String saleName, String saleName1,
		String projectDesce, String projectDescc, int ddlType,
		String orderGroup, String customerGroup, int ddlSelectPriceDays,
		String time, String quotername);
/**
 * ��������:��ѯ��Ŀ��
 * author:wy
 * date:2016��5��16��
 * @return
 */
public String getCaseNo();
/**
 * ��������:��Ӹ���Ŀ¼
 * author:wy
 * date:2016��5��25��
 * @return
 */

public int addCase(String title, String content, String caseNo, String empName,
		String time, String file1);
/**
 * ��������:���������Ŀ�Ų�ѯ��Ӧ�ĸ���Ŀ��
 * author:wy
 * date:2016��7��25��
 * @return
 */
public String getCaseNo1(String projectId);
/**
 * ��������:ERP�ϴ�����Ŀ
 * author:wy
 * date:2016��7��25��
 * @return
 */

public int addItCase(String backsingleCase, String caseNo, int erpcid,
		String content, String saleName, String saleName1, String projectDesce,
		String projectDescc, int ddlSelectPriceDays, String time,String quotername);
/**
 * ��������:ERP�ϻ�ȡ״̬Ϊ�����Ŀ��
 * author:wy
 * date:2016��8��26��
 * @return
 */

public String getproject();
/**
 * 名称：获取项目全部信息
 * author:wy
 * date:2016-10-17
 * @return
 */
public List<ItemCase> getall();
/**
 * 名称：获取项目全部信息
 * author:wy
 * date:2016-10-17
 * @return
 */

public List<ItemCase1> getall1();
/**
 * 名称：获取客户项目全部信息
 * author:wy
 * date:2016-10-25
 * @return
 */
public List<ItemCase> getall(int cid);
/**
 * 名称：获取客户项目全部信息数
 * author:wy
 * date:2016-10-25
 * @return
 */
public int getalltotal(int cid);
/**
 * 名称：获取项目全部信息
 * author:wy
 * date:2017-4-19
 * @return
 */
public ItemCase getall(String caseno);
/**
 * 名称：修改项目报价员
 * author:wy
 * date:2017-5-5
 * @return
 */
public int updatequoter(String quoter, String strProjectId);
/**
 * 名称：删除项目报价员
 * author:wy
 * date:2017-5-5
 * @return
 */
public int deletequoter(String strProjectId);
/**
 * 名称：获取项目及客户信息
 * author:wy
 * date:2017-5-25
 * @return
 */
public Invoiceinfo1 getall1(String projectId);
/**
 * 名称：获取全部项目对应销售
 * author:wy
 * date:2017-6-5
 * @return
 */
public List<ItemCase2> getalll();
/**
 * 名称：根据项目号获取客户id
 * author:wy
 * date:2017-6-8
 * @return
 */
public int geteid(String projectId);
/**
 * 名称：修改项目备注
 * author:wy
 * date:2017-6-8
 * @return
 */
public int updateprojectnote(String projectId, String projectnote);
/**
 * 名称：采购系统数据同步
 * author:wy
 * date:2017-8-21
 * @return
 */
public List<ItemCase2> getPurchasingSystem();
/**
 * 名称：根据项目号同步采购系统数据
 * author:wy
 * date:2017-9-14
 * @return
 */
public List<ItemCase2> getPurchasingSystem1(String projectId);
/**
 * 名称：统计全部质检报告表
 * author:wy
 * date:2017-10-10
 * @return
 */
public List<ItemCase> getAllItem(ItemCase it);
/**
 * 名称：查看项目第一次付款时间
 * author:wy
 * date:2017-10-13
 * @return
 */
public List<ItemCase> getprojectContract(ItemCase it);
/**
 * 名称：修改不上传合同理由
 * author:wy
 * date:2017-10-13
 * @return
 */
public int updateCaseNo(String reason, String caseNo);
/**
 * 名称：查看图纸统计页面
 * author:wy
 * date:2017-10-18
 * @return
 */
public List<TuZhi> viewAllDrawings(ItemCase it);
/**
 * 名称：查看会议记录
 * author:wy
 * date:2017-10-20
 * @return
 */
public List<MeetingRecord> viewConferenceRecords(MeetingRecord it);
/**
 * 名称：添加备注
 * author:wy
 * date:2017-11-08
 * @return
 */
public int updateConference(String projectId, String conferenceMessage,
		String messsage);
/**
 * 名称：查询已开会议
 * author:wy
 * date:2017-11-24
 * @return
 */
public List<MeetingRecord> viewConferenceRecords1(MeetingRecord it);
/**
 * 名称：查询已开会议
 * author:wy
 * date:2017-11-24
 * @return
 */
public List<ItemCase> getAllProject(ItemCase it);
/**
 * 名称：修改备注及是否需要共模
 * author:wy
 * date:2017-12-09
 * @return
 */
public int updateCommonMode(String projectId, String reason, int sign);
/**
 * 名称：新项目选厂
 * author:wy
 * date:2017-12-11
 * @return
 */
public int updateFactory(String projectId, int factory);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:根据项目号查询项目需求
   @author wangyang
 * @param projectNo
 * @return String
 * @throws
 */
public String getContent(String projectNo);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:将图纸放到新跟单项目
   @author wangyang
 * @param caseNo
 * @param backsingleCase
 * @return int
 * @throws
 */
public int addTuZhi(String caseNo, String backsingleCase);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:检验计划
   @author wangyang
 * @param caseNo
 * @param backsingleCase
 * @return int
 * @throws
 */
public int addPOupload(String caseNo, String backsingleCase);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:添加评论
   @author wangyang
 * @param caseNo
 * @param backsingleCase
 * @return int
 * @throws
 */
public int addQuotePrice(String caseNo, String backsingleCase);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:添加项目需求
   @author wangyang
 * @param caseNo
 * @param backsingleCase
 * @return int
 * @throws
 */
public int addQuotationAnalysis(String caseNo, String backsingleCase);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:修改项目组成员
   @author wangyang
 * @param caseNo
 * @param caseNo1
 * @return int
 * @throws
 */
public int updateAll(String caseNo, String caseNo1);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:查询未付给工厂款信息
   @author wangyang
 * @param it
 * @return List<Factory>
 * @throws
 */
public List<Factory> factoryUnpaidStatistics(Factory it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:正在审批金额
   @author wangyang
 * @param it
 * @return Double
 * @throws
 */
public Double getAmountApproved(Factory it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:计划保存金额
   @author wangyang
 * @param it
 * @return Double
 * @throws
 */
public Double getPlanSave(Factory it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:替换人员
   @author wangyang
 * @param it
 * @param total
 * @return int
 * @throws
 */
public int updatename(ItemCase it, int total);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:根据客户查询最后一个项目
   @author wangyang
 * @param erpcid1
 * @return String
 * @throws
 */
public String getCaseNo2(int erpcid1);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:获取客户id
   @author wangyang
 * @param caseNo
 * @return int
 * @throws
 */
public int getId(String caseNo);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:查询invoiceInfo数据
   @author wangyang
 * @param it
 * @return List<invoiceinfo>
 * @throws
 */
public List<invoiceinfo> CustomerPaymentStatistics(invoiceinfo it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:已完成款项
   @author wangyang
 * @param it
 * @return Double
 * @throws
 */
public Double getCompletedMoney(Factory it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:获取项目类型
   @author wangyang
 * @param projectId
 * @return ItemCase2
 * @throws
 */
public ItemCase2 getInspectionReport(String projectId);
public List<ItemCase2> getPurchasingSystem1();
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:上传检验报告
   @author wangyang
 * @param uploader
 * @param projectNo
 * @param reason
 * @param createtime
 * @param name
 * @return int
 * @throws
 */
public int add(String uploader, String projectNo, String reason,
		String createtime, String name);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:获取客户未付款情况
   @author wangyang
 * @param it
 * @return List<invoiceinfo>
 * @throws
 */
public List<invoiceinfo> nonPaymentCustomers(invoiceinfo it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:修改该客户全部跟单销售及销售信息
   @author wangyang
 * @param customerId
 * @param saleName1
 * @param i
 * @return int
 * @throws
 */
public int updateAllSaleName(int customerId, String saleName1, int i);
/**
 * *****************************************************************************************
 * 类描述：删除成熟跟单
 *
 * @author: wangyang
 * @date： 2018-05-05 
 * @version 1.0
 *
 *
 * Version    Date                ModifiedBy                 Content
 * --------   ---------           ----------                -----------------------
 * 1.0.0        2018-05-05         wangyang                   初版
 *******************************************************************************************
 */
public int deleteMerchandiser(String strProjectId);
/**
 * *****************************************************************************************
 * 类描述：修改成熟跟单
 *
 * @author: wangyang
 * @date： 2018-05-05 
 * @version 1.0
 *
 *
 * Version    Date                ModifiedBy                 Content
 * --------   ---------           ----------                -----------------------
 * 1.0.0        2018-05-05         wangyang                   初版
 *******************************************************************************************
 */
public int updateMerchandiser(String saleName1, String strProjectId);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:获取销售关联客户
   @author wangyang
 * @param it
 * @return List<invoiceinfo>
 * @throws
 */
public List<invoiceinfo> nonPaymentCustomers1(invoiceinfo it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:查看成员是否存在
   @author wangyang
 * @param empEName
 * @param caseno
 * @return int
 * @throws
 */
public int find(String empEName, String caseno);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:根据项目号获取数据
   @author wangyang
 * @param it
 * @return List<ItemCase>
 * @throws
 */
public List<ItemCase> startProjectStatistics(ItemCase it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:添加内部报价
   @author wangyang
 * @param fileName
 * @param projectId
 * @param userName
 * @param fid
 * @return int
 * @throws
 */
public int insert(String fileName, String projectId, String userName, int fid);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:判断合同号是否在项目下
   @author wangyang
 * @param projectId
 * @param contractNumber
 * @return ItemCase
 * @throws
 */
public ItemCase getCaseno(String projectId, String contractNumber);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:修改项目成员
   @author wangyang
 * @param item
 * @return int
 * @throws
 */
public int updateAll(ItemCase2 item);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:添加到关联表
   @author wangyang
 * @param factoryName
 * @param factoryId
 * @return int
 * @throws
 */
public int add(String factoryName, String factoryId);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description 查看属于自己项目
   @author wangyang
 * @param it
 * @return List<ItemCase>
 * @throws
 */
public List<ItemCase> startProjectStatistics1(ItemCase it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:清理项目
   @author wangyang
 * @param caseno
 * @return int
 * @throws
 */
public int updateCaseStatus(String caseno);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:修改备注
   @author wangyang
 * @param caseno
 * @param remarks
 * @return int
 * @throws
 */
public int updateRemarks(String caseno, String remarks);

	/**
	 *
	 * @Title:ItCaseIdServiceImpl
	 * @Description:修改发票备注
	 @author wangyang
	  * @param caseno
	 * @param remarks
	 * @return int
	 * @throws
	 */
	public int updateFpRemarks(String caseno, String remarks);


/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:添加对外报价
   @author wangyang
 * @param value
 * @param projectId
 * @param userName
 * @param fid
 * @return int
 * @throws
 */
public int insert1(String value, String projectId, String userName, int fid);
/**
 * @param it 
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:欠我司发票工厂
   @author wangyang
 * @return List<ItemCase2>
 * @throws
 */
public List<ItemCase2> invoiceFactoryOwnedToUs(ItemCase2 it);

public List<ItemCase2> invoiceFactoryOwnedToUsNew(ItemCase2 it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:查看所有未收发票金额详情列表
   @author wangyang
 * @param itemcase
 * @return List<ItemCase2>
 * @throws
 */
public List<FactoryReconciliation> getAllDetailedAccounts(ItemCase2 itemcase);
/**
 * @param it 
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:跟单销售列表
   @author wangyang
 * @return List<ItemCase2>
 * @throws
 */
public List<ItemCase2> invoiceDocumentarySaleOwnedToUs(ItemCase2 it);
/**
 * @param it 
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:采购名下欠发票数量
   @author wangyang
 * @return List<ItemCase2>
 * @throws
 */
public List<ItemCase2> invoiceDocumentaryPurchaseOwnedToUs(ItemCase2 it);
/**
 * @param
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:尾款已清，尚未收回发票金额
   @author wangyang
 * @param itemcase
 * @return List<FactoryReconciliation>
 * @throws
 */
public List<FactoryReconciliation> getFinalInvoice(ItemCase2 itemcase);

public List<FactoryReconciliation> factoryNameByInvoiceName(String invoiceName);


public List<FactoryReconciliation> factoryPayInfo(String factoryName);
public List<FactoryReconciliation> factoryPayInfoNew(String factoryName);

public List<FactoryReconciliation> getPayInfo(String factoryName);


public List<FactoryReconciliation> factoryPayInfoDetail(String factoryName);


/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:付款超4月未收回发票金额
   @author wangyang
 * @param itemcase
 * @return List<FactoryReconciliation>
 * @throws
 */
public List<FactoryReconciliation> getPaymentExceededApril(ItemCase2 itemcase);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:到账录入客户关联表
   @author wangyang
 * @param it
 * @return List<ArrivalAccountCorrespondenceTable>
 * @throws
 */
public List<ArrivalAccountCorrespondenceTable> enterTheCustomerRelevanceTableIntoTheAccount(
		ArrivalAccountCorrespondenceTable it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:跟单采购欠我司发票
   @author wangyang
 * @param it
 * @return List<ItemCase2>
 * @throws
 */
public List<ItemCase2> invoiceDocumentarySaleOwnedToUs1(ItemCase2 it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:查询跟单、采购与自己关联项目 
   @author wangyang
 * @param it
 * @return List<ItemCase2>
 * @throws
 */
public List<ItemCase2> invoiceFactoryOwnedToUs1(ItemCase2 it);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:查看工艺对应工艺属性
   @author wangyang
 * @param process1
 * @return TechnologyTable
 * @throws
 */
public TechnologyTable getAll(String process1);
/**
 * @param projectNo 
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:修改材料属性
   @author wangyang
 * @param processAttributes void
 * @throws
 */
public void updateProjectMaterialProperties(int processAttributes, String projectNo);
/**
 * 
 * @Title:ItCaseIdServiceImpl
 * @Description:获取出运单起始时间
   @author wangyang
 * @return ItemCase
 * @throws
 */
public ItemCase getStartTime();

	/**
	 * 修改快速通道及理由
	 * @param id
	 * @param fastTrackReasons
	 * @return
	 */

    int updateFastTrackReasons(int id, String fastTrackReasons);

	/**
	 * 查询是否存在销售合同
	 * @param caseNo
	 * @return
	 */
	int getSalesContract(String caseNo);

	/**
	 * 根据工厂名查询是金蝶号
	 * @param factoryName
	 * @return
	 */
	int getFactoryName(String factoryName);


	/**
	 * 查询数据并修改数据
	 * @param list
	 */
    void updateDividendBalance(List<Bonusdata> list);

	/**
	 * 下单客户
	 * @param user
	 * @return
	 */
    List<ItemCase1> getOrderCustomerList(EmailUser user);

	/**
	 * 未下单客户
	 * @param user
	 * @return
	 */
	List<ItemCase1> getNoOrderCustomerList(EmailUser user);

	/**
	 * 2018全部A、B级客户
	 * @param user
	 * @return
	 */
	List<ItemCase1> getallCustomerList(EmailUser user);
	/**
	 * 规定时间A/B级，新客户A、B级项目
	 * @param user
	 * @return
	 */
	List<ItemCase1> getnewCustomerList(EmailUser user);
	/**
	 * 规定时间A/B级项目
	 * @param user
	 * @return
	 */
	List<ItemCase1> getfixedTimeCustomersList(EmailUser user);

	/**
	 * 根据时间获取全部指定全部项目
	 * @param it
	 * @return
	 */
    List<ItemCase> getAllProjectNo(ItemCase it);

	/**
	 * 查询扣款列表
	 * @param itcase
	 * @return
	 */
	ItemCase getProjectStatistics(ItemCase itcase);


}
