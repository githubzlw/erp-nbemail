package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.IPreparatorEntryFormDao;
import cerong.erp.dao.PreparatorEntryFormDaoImpl;
import cerong.erp.entity.PreparatorEntryForm;
import cerong.erp.entity.invoiceinfo;



public class PreparatorEntryFormServiceImpl implements  IPreparatorEntryFormService{
	IPreparatorEntryFormDao dao = new PreparatorEntryFormDaoImpl();

	@Override
	public int add(String invoice,int id,String time,int ibank, String caseno) {
		
		return dao.add(invoice,id,time,ibank,caseno);
	}

	@Override
	public int getId(String invoice) {
		
		return dao.getId(invoice);
	}

	@Override
	public int update(int invoiceId, double allInvoiceMoney) {
		
		return dao.update(invoiceId,allInvoiceMoney);
	}

	@Override
	public List<PreparatorEntryForm> getAll(int id) {
		
		return dao.getAll(id);
	}

	@Override
	public int recoveryInformation(int id) {
		
		return dao.recoveryInformation(id);
	}

	@Override
	public int updateAll(PreparatorEntryForm preparator1) {
		
		return dao.updateAll(preparator1);
	}

	@Override
	public PreparatorEntryForm getOne(int id) {
		
		return dao.getOne(id);
	}

	@Override
	public List<PreparatorEntryForm> getAll() {
		
		return dao.getAll();
	}

	@Override
	public int updateModificationResults(String id) {
		
		return dao.updateModificationResults(id);
	}

	@Override
	public int updateModifyTemporaryTable(String eids) {
		
		return dao.updateModifyTemporaryTable(eids);
	}

	@Override
	public int add(String eids) {
		
		return dao.add(eids);
	}

	@Override
	public int add1(String eids) {
		
		return dao.add1(eids);
	}

	@Override
	public int insert(PreparatorEntryForm preparator1) {
		
		return dao.insert(preparator1);
	}

	@Override
	public int addInvoice(String eids) {
		
		return dao.addInvoice(eids);
	}

	
}
