package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.EmployeeDao;
import cerong.erp.dao.IEmployeeDaoImpl;
import cerong.erp.entity.EmailUser;



public  class EmployeeService implements IEmployeeServiceImpl {
	IEmployeeDaoImpl dao = new EmployeeDao();

	@Override
	public int updateDel(String userName) {
		
		return dao.updateDel(userName);
	}

	@Override
	public int getBack(String userName) {
		
		return dao.getBack(userName);
	}

	@Override
	public int del(String userName) {
		
		return dao.del(userName);
	}

	@Override
	public int updateRole(EmailUser user, String userName) {
		
		return dao.updateRole(user,userName);
	}

	@Override
	public int addUser(EmailUser user) {
		
		return dao.addUser(user);
	}

	@Override
	public int getUser(String empPWD, String empEName) {
		
		return dao.getUser(empPWD,empEName);
	}

	@Override
	public int getName(String userName) {
		
		return dao.getName(userName);
	}

	@Override
	public int addUser1(EmailUser user) {
		
		return dao.addUser1(user);
	}

	@Override
	public boolean createUser(String empEName) {
		
		return dao.createUser(empEName);
	}

	@Override
	public EmailUser search(EmailUser user) {
		
		return dao.search(user);
	}

	@Override
	public void update(EmailUser user) {
		dao.update(user);
		
	}

	@Override
	public void insert1(EmailUser user) {
		dao.insert1(user);
		
	}

	@Override
	public List<EmailUser> getMember() {
		
		return dao.getMember();
	}

    @Override
    public List<EmailUser> getAllSales() {

		return dao.getAllSales();
    }

	@Override
	public List<EmailUser> getAll() {
		return dao.getAll();
	}

	@Override
	public EmailUser getAccountingCustomers(EmailUser user) {
		return dao.getAccountingCustomers(user);
	}
}
