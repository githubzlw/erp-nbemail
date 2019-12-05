package cerong.erp.service;

import java.util.List;

import org.omg.DynamicAny.DynAnyOperations;

import cerong.erp.dao.IinvoiceinfoDao;
import cerong.erp.dao.invoiceinfoDaoImpl;
import cerong.erp.entity.Invoiceinfo1;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.invoiceinfo;



public class invoiceinfoService implements  IinvoiceinfoServiceImpl{
	IinvoiceinfoDao dao = new invoiceinfoDaoImpl();

	@Override
	public List<invoiceinfo> getall(String caseno) {
		
		return dao.getall(caseno);
	}

	@Override
	public invoiceinfo getall1(String caseno) {
		
		return dao.getall1(caseno);
	}

	@Override
	public invoiceinfo getInvoice(String invoice) {
		
		return dao.getInvoice(invoice);
	}

	@Override
	public invoiceinfo getRefundInvoice(String invoice, String empEName) {
		
		return dao.getRefundInvoice(invoice,empEName);
	}

	

	@Override
	public int updateApprovalResults(int id, int num) {
		
		return dao.updateApprovalResults(id,num);
	}

	@Override
	public List<Invoiceinfo1> queryReceiptList(Invoiceinfo1 it) {
		
		return dao.queryReceiptList(it);
	}

	@Override
	public invoiceinfo getRefundInvoice(String invoice) {
		
		return dao.getRefundInvoice(invoice);
	}

}
