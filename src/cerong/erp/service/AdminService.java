package cerong.erp.service;

import cerong.erp.dao.AdminDao;
import cerong.erp.dao.IAdminDaoImpl;
import cerong.erp.entity.Admin;


public class AdminService implements  IAdminServiceImpl{
	IAdminDaoImpl dao = new  AdminDao();

	@Override
	public int add(Admin admin) {
		
		return dao.add(admin);
	}

	@Override
	public int getid(String userName) {
		
		return dao.getid(userName);
	}
}
