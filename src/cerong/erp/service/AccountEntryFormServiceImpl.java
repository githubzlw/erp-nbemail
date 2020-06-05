package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.AccountEntryFormDaoImpl;
import cerong.erp.dao.IAccountEntryFormDao;
import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.AmountClaimForm;
import cerong.erp.entity.ArrivalAccountCorrespondenceTable;

public class AccountEntryFormServiceImpl implements IAccountEntryFormService{
IAccountEntryFormDao dao=new AccountEntryFormDaoImpl();
	@Override
	public List<AccountEntryForm> accounEntry() {
		
		return dao.accounEntry();
	}
	@Override
	public void add(AccountEntryForm account) {
		dao.add(account);
		
	}
	@Override
	public ArrivalAccountCorrespondenceTable getall(String payersName) {
		
		return dao.ArrivalAccountCorrespondenceTable(payersName);
	}
	@Override
	public int replacementOfClaim(String empEName, int id) {
		
		return dao.replacementOfClaim(empEName,id);
	}
	@Override
	public int recoveryInformation(int id) {
		
		return dao.recoveryInformation(id);
	}
	@Override
	public int updateAll(String eids, String empEName) {
		
		return dao.updateAll(eids,empEName);
	}
	@Override
	public int getIbank(int id) {
		
		return dao.getIbank(id);
	}
	@Override
	public int updateModificationResults(String eids) {
		
		return dao.updateModificationResults(eids);
	}
	@Override
	public List<AccountEntryForm> completionOfMoney(AccountEntryForm accountEntryForm) {
		
		return dao.completionOfMoney(accountEntryForm);
	}
	@Override
	public AccountEntryForm getAccount(int id) {
		
		return dao.AccountEntryForm(id);
	}
	@Override
	public int getId(int iid) {
		
		return dao.getId(iid);
	}
	@Override
	public AccountEntryForm getAll(int id) {
		
		return dao.getAll(id);
	}
	@Override
	public int addAccount(ArrivalAccountCorrespondenceTable account) {
		
		return dao.addAccount(account);
	}
	@Override
	public int deleteAccount(int id) {
		
		return dao.deleteAccount(id);
	}
	@Override
	public int updateAccountEntry(AccountEntryForm entry) {
		
		return dao.updateAccountEntry(entry);
	}
	@Override
	public List<AccountEntryForm> getAll(String time1) {
		
		return dao.getAll(time1);
	}
	@Override
	public int updateFirstSumMoney(int id, double firstSumMoney) {
		
		return dao.updateFirstSumMoney(id,firstSumMoney);
	}
	@Override
	public int insertSecondSumMoney(int id, double secondSumMoney) {
		
		return dao.insertSecondSumMoney(id,secondSumMoney);
	}
	@Override
	public List<AccountEntryForm> inquireIntoAccounts(AccountEntryForm accountEntryForm) {
		
		return dao.inquireIntoAccounts(accountEntryForm);
	}
	@Override
	public double getAllMoney(int id) {
		
		return dao.getAllMoney(id);
	}
	@Override
	public AmountClaimForm getAmount(String invoice) {
		
		return dao.getAmount(invoice);
	}
	

}
