package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.ContractApprovalForm;

public interface IContractApprovalFormDaoImpl {

	/**
	 * 查看全部项目
	 * author:wy
	 * date:2017-11-21
	 * @param user
	 * @return
	 */
	public List<ContractApprovalForm> getAll(ContractApprovalForm cp);
	/**
	 * 根据用户名查看项目
	 * author:wy
	 * date:2017-11-21
	 * @param user
	 * @return
	 */
	public List<ContractApprovalForm> getContracts(ContractApprovalForm cp);

}
