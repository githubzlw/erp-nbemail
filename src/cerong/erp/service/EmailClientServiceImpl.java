package cerong.erp.service;

import cerong.erp.dao.EmailClientDaoImpl;
import cerong.erp.dao.IEmailClientIDDao;



public class EmailClientServiceImpl implements IEmailClientService{
	IEmailClientIDDao dao = new EmailClientDaoImpl();

	/*@Override
	public int getcid(int cid) {
		
		return dao.getcid(cid);
	}*/
}
