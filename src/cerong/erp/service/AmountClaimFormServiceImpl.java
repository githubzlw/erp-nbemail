package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.AmountClaimFormDaoImpl;
import cerong.erp.dao.IAmountClaimFormDao;
import cerong.erp.entity.AmountClaimForm;



public class AmountClaimFormServiceImpl implements IAmountClaimFormService{
	IAmountClaimFormDao dao=new AmountClaimFormDaoImpl();

	@Override
	public List<AmountClaimForm> enterFinancialEntry(int id) {
		
		return dao.enterFinancialEntry(id);
	}

	@Override
	public int recoveryInformation(int id) {
		
		return dao.recoveryInformation(id);
	}

	@Override
	public int insert(AmountClaimForm claim) {
		
		return dao.insert(claim);
	}

	@Override
	public int updateModificationResults(String eids) {
		
		return dao.updateModificationResults(eids);
	}

	
}
