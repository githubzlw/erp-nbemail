package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.PreparatorEntryForm;
import cerong.erp.entity.invoiceinfo;

public interface IPreparatorEntryFormService {
/**
 * @param caseno 
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:正式表添加到零时表
   @author wangyang
 * @param invoice
 * @return int
 * @throws
 */
int add(String invoice, int id,String time,int ibank, String caseno);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:获取第一个Id
   @author wangyang
 * @return int
 * @throws
 */

int getId(String invoice);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:修改预计表统计金额
   @author wangyang
 * @param invoiceId
 * @param allInvoiceMoney
 * @return int
 * @throws
 */
int update(int invoiceId, double allInvoiceMoney);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:获取invoice临时全部列表
   @author wangyang
 * @param id
 * @return List<PreparatorEntryForm>
 * @throws
 */
List<PreparatorEntryForm> getAll(int id);
/**
 * @Title:IPreparatorEntryFormService
 * @Description:清理invoice临时表
   @author wangyang
 * @param id
 * @return int
 * @throws
 */
int recoveryInformation(int id);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:销售修改财务临时表
   @author wangyang
 * @param preparator1
 * @return int
 * @throws
 */
int updateAll(PreparatorEntryForm preparator1);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:获取一条数据
   @author wangyang
 * @param id
 * @return PreparatorEntryForm
 * @throws
 */
PreparatorEntryForm getOne(int id);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:查看销售全部录入进账
   @author wangyang
 * @return List<PreparatorEntryForm>
 * @throws
 */
List<PreparatorEntryForm> getAll();
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:修改正式表数据
   @author wangyang
 * @param id
 * @return int
 * @throws
 */
int updateModificationResults(String eids);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:TODO
   @author wangyang
 * @param eids
 * @return int
 * @throws
 */
int updateModifyTemporaryTable(String eids);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:将记录保存数据库
   @author wangyang
 * @param eids
 * @return int
 * @throws
 */
int add(String eids);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:将记录保存数据库
   @author wangyang
 * @param eids
 * @return int
 * @throws
 */
int add1(String eids);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:添加新数据到数据库
   @author wangyang
 * @param preparator1
 * @return int
 * @throws
 */
int insert(PreparatorEntryForm preparator1);
/**
 * 
 * @Title:IPreparatorEntryFormService
 * @Description:将新增加数据加入进账表
   @author wangyang
 * @param eids
 * @return int
 * @throws
 */
int addInvoice(String eids);



}
