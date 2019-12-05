package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.CustomerReconciliationSystem;

public interface ICustomerReconciliationSystemDaoImpl {
	/**
	 * 名称：对于每个客户 做一个对账系统
	 * author:wy
	 * date:2017-8-31
	 * @return
	 */
	public List<CustomerReconciliationSystem> getAll1(CustomerReconciliationSystem cr);
	/**
	 * 名称：客户未到款项
	 * author:wy
	 * date:2017-8-31
	 * @return
	 */
	public List<CustomerReconciliationSystem> getLookOutstandingFunds(
			CustomerReconciliationSystem cr);

}
