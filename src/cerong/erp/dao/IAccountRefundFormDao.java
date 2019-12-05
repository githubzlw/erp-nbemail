package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.AccountRefundForm;

public interface IAccountRefundFormDao {
/**
 * 
 * @Title:IAccountRefundFormDao
 * @Description:退款详情页
   @author wangyang
 * @return List<AccountRefundForm>
 * @throws
 */
	List<AccountRefundForm> allRefundInterface();
	/**
	 * 
	 * @Title:IAccountRefundFormService
	 * @Description:添加退款表
	   @author wangyang
	 * @param account
	 * @return int
	 * @throws
	 */
int add(AccountRefundForm account);
/**
 * @param account 
 * 
 * @Title:IAccountRefundFormService
 * @Description:审批结果页
   @author wangyang
 * @return List<AccountRefundForm>
 * @throws
 */
	List<AccountRefundForm> refundApprovalResult(AccountRefundForm account);
/**
 * 
 * @Title:IAccountRefundFormDao
 * @Description:修改审批结果
   @author wangyang
 * @param num
 * @param id
 * @param empEName
 * @return int
 * @throws
 */
int updateApprovalResults(int num, int id, String empEName);
/**
 * 
 * @Title:IAccountRefundFormDao
 * @Description:添加备注
   @author wangyang
 * @param id
 * @return int
 * @throws
 */
int updateModifyTemporaryTable(int id);
/**
 * 
 * @Title:IAccountRefundFormService
 * @Description:获取待审批数
   @author wangyang
 * @return int
 * @throws
 */
int getAccountRefundForm();
/**
 * 
 * @Title:IAccountRefundFormService
 * @Description:退款录入备注
   @author wangyang
 * @param id
 * @return int
 * @throws
 */
int add1(int id);
/**
 * 
 * @Title:IAccountRefundFormService
 * @Description:项目备注
   @author wangyang
 * @param id
 * @return int
 * @throws
 */
int add2(int id);
/**
 * @param invoice 
 * 
 * @Title:IAccountRefundFormService
 * @Description:查询该invoice是否已存在要求退款
   @author wangyang
 * @param invoice
 * @return AccountRefundForm
 * @throws
 */
AccountRefundForm getAll(String invoice);
/**
 * 
 * @Title:IAccountRefundFormService
 * @Description:查看审批完成列表页
   @author wangyang
 * @param empEName
 * @return List<AccountRefundForm>
 * @throws
 */
List<AccountRefundForm> refundApprovalResult1(AccountRefundForm account);
/**
 * 
 * @Title:IAccountRefundFormService
 * @Description:修改财务处理
   @author wangyang
 * @param id
 * @param empEName
 * @return int
 * @throws
 */
int updateRefundCompleted(int id, String empEName);
/**
 * 
 * @Title:IAccountRefundFormService
 * @Description:自己申请退款列表
   @author wangyang
 * @param empEName
 * @return List<AccountRefundForm>
 * @throws
 */
List<AccountRefundForm> allRefundInterface1(String empEName);
	

}
