package cerong.erp.service;

import cerong.erp.dao.EmailUserDaoImpl;
import cerong.erp.dao.IEmailUserDao;
import cerong.erp.entity.EmailUser;



public  class EmailUserServiceImpl implements IEmailUserService {
	IEmailUserDao dao = new EmailUserDaoImpl();

	/*@Override
	public int get(EmailUser user) {
		
		return dao.get(user);
	}

	@Override
	public int getDimission(int userId) {
		
		return dao.getDimission(userId);
	}

	@Override
	public int get1(EmailUser user) {
		
		return dao.get1(user);
	}

	@Override
	public String getsaleName(int cid) {
		
		return dao.getsaleName(cid);
	}
*/
}
