package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.ContractMaturityDao;
import cerong.erp.dao.IContractMaturityDaoImpl;
import cerong.erp.entity.ContractMaturity;



public class ContractMaturityService implements IContractMaturityServiceImpl{
	IContractMaturityDaoImpl dao = new  ContractMaturityDao();

	@Override
	public List<ContractMaturity> getall(Long millionSeconds) {
		
		return dao.getall(millionSeconds);
	}

}
