package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.FactoryFundDao;
import cerong.erp.dao.IFactoryFundDaoImpl;
import cerong.erp.entity.Factory;
import cerong.erp.entity.FactoryFund;


public class FactoryFundService implements  IFactoryFundServiceImpl{
	IFactoryFundDaoImpl dao = new  FactoryFundDao();

	@Override
	public List<FactoryFund> getall(String caseno) {
		
		return dao.getall(caseno);
	}

	@Override
	public FactoryFund getall1(String caseno) {
		
		return dao.getall1(caseno);
	}

	@Override
	public Factory getFactory(int id) {
		
		return dao.getFactory(id);
	}

	@Override
	public int updateMoney(String ApNumber, double d) {
		
		return dao.updateMoney(ApNumber,d);
	}
}
