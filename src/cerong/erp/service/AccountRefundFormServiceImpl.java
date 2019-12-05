package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.AccountRefundFormDaoImpl;
import cerong.erp.dao.IAccountRefundFormDao;
import cerong.erp.entity.AccountRefundForm;



public class AccountRefundFormServiceImpl implements IAccountRefundFormService{
	IAccountRefundFormDao dao=new AccountRefundFormDaoImpl();

	@Override
	public List<AccountRefundForm> allRefundInterface() {
		
		return dao.allRefundInterface();
	}

	@Override
	public int add(AccountRefundForm account) {
		
		return dao.add(account);
	}

	@Override
	public List<AccountRefundForm> refundApprovalResult(AccountRefundForm account) {
		
		return dao.refundApprovalResult(account);
	}

	@Override
	public int updateApprovalResults(int num, int id, String empEName) {
		
		return dao.updateApprovalResults(num,id,empEName);
	}

	@Override
	public int updateModifyTemporaryTable(int id) {
		
		return dao.updateModifyTemporaryTable(id);
	}

	@Override
	public int getAccountRefundForm() {
		
		return dao.getAccountRefundForm();
	}

	@Override
	public int add1(int id) {
		
		return dao.add1(id);
	}

	@Override
	public int add2(int id) {
		
		return dao.add2(id);
	}

	@Override
	public AccountRefundForm getAll(String invoice) {
		
		return dao.getAll(invoice);
	}

	@Override
	public List<AccountRefundForm> refundApprovalResult1(AccountRefundForm account) {
		
		return dao.refundApprovalResult1(account);
	}

	@Override
	public int updateRefundCompleted(int id, String empEName) {
		
		return dao.updateRefundCompleted(id,empEName);
	}

	@Override
	public List<AccountRefundForm> allRefundInterface1(String empEName) {
		
		return dao.allRefundInterface1(empEName);
	}
}
