package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.ContractApprovalFormDao;
import cerong.erp.dao.IContractApprovalFormDaoImpl;
import cerong.erp.entity.ContractApprovalForm;



public class ContractApprovalFormServiceImpl implements IContractApprovalFormService{
	IContractApprovalFormDaoImpl dao = new ContractApprovalFormDao();

	@Override
	public List<ContractApprovalForm> getAll(ContractApprovalForm cp) {
		
		return dao.getAll(cp);
	}

	@Override
	public List<ContractApprovalForm> getContracts(ContractApprovalForm cp) {
		
		return dao.getContracts(cp);
	}
}
