package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.AmountClaimForm;
import cerong.erp.entity.ArrivalAccountCorrespondenceTable;

public interface IAccountEntryFormService {
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:获取进账录入列表
   @author wangyang
 * @return List<AccountEntryForm>
 * @throws
 */
	List<AccountEntryForm> accounEntry();
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:上传excel保存
   @author wangyang
 * @param account void
 * @throws
 */
void add(AccountEntryForm account);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:根据客户名获取客户关联表数据
   @author wangyang
 * @param payersName
 * @return ArrivalAccountCorrespondenceTable
 * @throws
 */
ArrivalAccountCorrespondenceTable getall(String payersName);
/**
 * 
 * @Title:IAmountClaimFormService
 * @Description:修改认领人
   @author wangyang
 * @param empEName
 * @param id
 * @return int
 * @throws
 */
	int replacementOfClaim(String empEName, int id);
	/**
	 * 
	 * @Title:IAccountEntryFormService
	 * @Description:取消认领人
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */
int recoveryInformation(int id);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:批量认领成员
   @author wangyang
 * @param eids
 * @param empEName
 * @return int
 * @throws
 */
	int updateAll(String eids, String empEName);
	/**
	 * 
	 * @Title:IAccountEntryFormService
	 * @Description:查询进账银行
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */
int getIbank(int id);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:修改录入表
   @author wangyang
 * @param eids
 * @return int
 * @throws
 */
	int updateModificationResults(String eids);
	/**
	 * @param accountEntryForm 
	 * 
	 * @Title:IAccountEntryFormService
	 * @Description:查看已审批列表页
	   @author wangyang
	 * @return List<AccountEntryForm>
	 * @throws
	 */
List<AccountEntryForm> completionOfMoney(AccountEntryForm accountEntryForm,String flag);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:获取基本信息
   @author wangyang
 * @param id
 * @return AccountEntryForm
 * @throws
 */
	AccountEntryForm getAccount(int id);
	/**
	 * 
	 * @Title:IAccountEntryFormService
	 * @Description:查看id
	   @author wangyang
	 * @param iid
	 * @return int
	 * @throws
	 */
int getId(int iid);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:获取详情
   @author wangyang
 * @param id
 * @return AccountEntryForm
 * @throws
 */
	AccountEntryForm getAll(int id);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:添加客户关系
   @author wangyang
 * @param account
 * @return int
 * @throws
 */
int addAccount(ArrivalAccountCorrespondenceTable account);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:删除
   @author wangyang
 * @param id
 * @return int
 * @throws
 */
int deleteAccount(int id);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:修改客户性质
   @author wangyang

 * @return int
 * @throws
 */
int updateAccountEntry(AccountEntryForm entry);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:获取进账信息
   @author wangyang
 * @param time1
 * @return List<AccountEntryForm>
 * @throws
 */
List<AccountEntryForm> getAll(String time1);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:修改原始进账金额
   @author wangyang
 * @param id
 * @param firstSumMoney1
 * @return int
 * @throws
 */
int updateFirstSumMoney(int id, double firstSumMoney1);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:新加进账金额
   @author wangyang
 * @param id
 * @param firstSumMoney1
 * @return int
 * @throws
 */
int insertSecondSumMoney(int id, double secondSumMoney1);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:查询最近一个月进账
   @author wangyang
 * @return List<AccountEntryForm>
 * @throws
 */
List<AccountEntryForm> inquireIntoAccounts(AccountEntryForm accountEntryForm);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:查询到账总金额
   @author wangyang
 * @param id
 * @return double
 * @throws
 */
double getAllMoney(int id);
/**
 * 
 * @Title:IAccountEntryFormService
 * @Description:查询该合同号是否已分配过
   @author wangyang
 * @param invoice
 * @return AmountClaimForm
 * @throws
 */
AmountClaimForm getAmount(String invoice);



}
