package cerong.erp.service;

import cerong.erp.dao.IQuoteClientDaoImpl;
import cerong.erp.dao.QuoteClientDao;
import cerong.erp.entity.QuoteClient;



public class QuoteClientService implements IQuoteClientImpl{
	IQuoteClientDaoImpl dao = new QuoteClientDao();

	@Override
	public int add(QuoteClient qc) {
		
		return dao.add(qc);
	}
}
