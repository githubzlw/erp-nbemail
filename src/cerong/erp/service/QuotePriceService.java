package cerong.erp.service;

import cerong.erp.dao.IQuotePriceDaoImpl;
import cerong.erp.dao.QuotePriceDao;
import cerong.erp.entity.QuotePrice;



public class QuotePriceService  implements   IQuotePriceServiceImpl{
	IQuotePriceDaoImpl dao = new QuotePriceDao();

	@Override
	public int add(QuotePrice qu) {
		
		return dao.add(qu);
	}
}
