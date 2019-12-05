package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.AmountClaimForm;

public interface IAmountClaimFormDao {
	/**
	 * 
	 * @Title:IAmountClaimFormService
	 * @Description:财务进账详情页
	   @author wangyang
	 * @param id
	 * @return List<AmountClaimForm>
	 * @throws
	 */
	List<AmountClaimForm> enterFinancialEntry(int id);
	/**
	 * 
	 * @Title:IAmountClaimFormService
	 * @Description:取消该id对应全部认领记录
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */
	int recoveryInformation(int id);
	/**
	 * 
	 * @Title:IAmountClaimFormService
	 * @Description:将金额分配到具体invoice
	   @author wangyang
	 * @param claim
	 * @return int
	 * @throws
	 */
	int insert(AmountClaimForm claim);
	/**
	 * 
	 * @Title:IAmountClaimFormService
	 * @Description:修改详情表数据
	   @author wangyang
	 * @param eids
	 * @return int
	 * @throws
	 */
	int updateModificationResults(String eids);
	
}
