package cerong.erp.service;

import cerong.erp.dao.IQualityDeductionFormDaoImpl;
import cerong.erp.dao.QualityDeductionFormDao;
import cerong.erp.entity.FactoryFund;



public class QualityDeductionFormServiceImpl implements IQualityDeductionFormService{
	IQualityDeductionFormDaoImpl dao = new  QualityDeductionFormDao();

	@Override
	public int add(String caseNo, double money1, int id,String reason) {
		
		return dao.add(caseNo,money1,id,reason);
	}

	@Override
	public int add1(String projectId, String contractNumber,
			double actualDeductions1, int id) {
		
		return dao.add1(projectId,contractNumber,actualDeductions1,id);
	}

	@Override
	public FactoryFund checkMoney(String projectId, String contractNumber,
			double actualDeductions1, int id) {
		
		return dao.checkMoney(projectId,contractNumber,actualDeductions1,id);
	}

	@Override
	public int addFactoryInvoice(String projectId, double actualDeductions1,
			String contractNumber, String empEName,double money,String reason) {
		
		return dao.addFactoryInvoice(projectId,actualDeductions1,contractNumber,empEName,money,reason);
	}

	@Override
	public int addFactoryInvoice1(String projectId, double actualDeductions1,
			String contractNumber, String empEName) {
		
		return dao.addFactoryInvoice1(projectId,actualDeductions1,contractNumber,empEName);
	}

	@Override
	public int checkMoney(String projectId) {
		
		return dao.checkMoney(projectId);
	}

	
}
