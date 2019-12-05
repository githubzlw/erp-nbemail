package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.CustomerReconciliationSystemDao;
import cerong.erp.dao.ICustomerReconciliationSystemDaoImpl;
import cerong.erp.entity.CustomerReconciliationSystem;



public class CustomerReconciliationSystemServiceImpl implements   ICustomerReconciliationSystemService{
	ICustomerReconciliationSystemDaoImpl dao = new  CustomerReconciliationSystemDao();

	@Override
	public List<CustomerReconciliationSystem> getAll1(
			CustomerReconciliationSystem cr) {
		
		return dao.getAll1(cr);
	}

	@Override
	public List<CustomerReconciliationSystem> getLookOutstandingFunds(
			CustomerReconciliationSystem cr) {
		
		return dao.getLookOutstandingFunds(cr);
	}
}
