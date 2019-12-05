package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.ContractMaturity;

public interface IContractMaturityServiceImpl {

	/**
 * 名称：获取合同未完成全部项目
 * author:wy
 * date:2016-11-9
 * @return
 */
public List<ContractMaturity> getall(Long millionSeconds);

	

}
