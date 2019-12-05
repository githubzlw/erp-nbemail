package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.FactoryReconciliationDao;
import cerong.erp.dao.IFactoryReconciliationDaoImpl;
import cerong.erp.entity.FactoryReconciliation;



public class FactoryReconciliationService implements IFactoryReconciliationServiceImpl{
	IFactoryReconciliationDaoImpl dao = new  FactoryReconciliationDao();

	@Override
	public List<FactoryReconciliation> getall(int month,int year) {
		
		return dao.getall( month,year);
	}

	@Override
	public List<FactoryReconciliation> getall1(int year) {
		
		return dao.getall1(year);
	}

	@Override
	public List<FactoryReconciliation> getAll(int year, int month, int kingdee) {
		
		return dao.getAll(year,month,kingdee);
	}

    @Override
    public int updateCreateTime(String id, String createTime) {

		return dao.updateCreateTime(id,createTime);
    }
}
