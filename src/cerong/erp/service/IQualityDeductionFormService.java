package cerong.erp.service;

import cerong.erp.entity.FactoryFund;

public interface IQualityDeductionFormService {
/**
 * 
 * @Title:IQualityDeductionFormService
 * @Description:质量扣款表添加数据
   @author wangyang
 * @param caseNo
 * @param money1
 * @return int
 * @throws
 */


int add(String caseNo, double money1, int id,String reason);
/**
 * 
 * @Title:IQualityDeductionFormService
 * @Description:添加到合同表
   @author wangyang
 * @param projectId
 * @param contractNumber
 * @param actualDeductions1
 * @param id
 * @return int
 * @throws
 */
int add1(String projectId, String contractNumber, double actualDeductions1,
		int id);
/**
 * 
 * @Title:IQualityDeductionFormService
 * @Description:校验未付款合同金额
   @author wangyang
 * @param projectId
 * @param contractNumber
 * @param actualDeductions1
 * @param id
 * @return int
 * @throws
 */
FactoryFund checkMoney(String projectId, String contractNumber,
		double actualDeductions1, int id);
/**
 * 
 * @Title:IQualityDeductionFormService
 * @Description:添加质量扣款到记录
   @author wangyang
 * @param projectId
 * @param actualDeductions1
 * @param contractNumber
 * @param empEName
 * @return int
 * @throws
 */
int addFactoryInvoice(String projectId, double actualDeductions1,
		String contractNumber, String empEName,double money,String reason);
/**
 * 
 * @Title:IQualityDeductionFormService
 * @Description:工厂款已付完
   @author wangyang
 * @param projectId
 * @param actualDeductions1
 * @param contractNumber
 * @param empEName
 * @return int
 * @throws
 */
int addFactoryInvoice1(String projectId, double actualDeductions1,
		String contractNumber, String empEName);
/**
 * 
 * @Title:IQualityDeductionFormService
 * @Description:验证是否已录入
   @author wangyang
 * @param projectId
 * @return FactoryFund
 * @throws
 */
int checkMoney(String projectId);


}
