package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.IProjectCustomerDaoImpl;
import cerong.erp.dao.ProjectCustomerDao;
import cerong.erp.entity.ProjectCustomer;



public class ProjectCustomerService implements  IProjectCustomerServiceImpl{
	IProjectCustomerDaoImpl dao = new  ProjectCustomerDao();

	@Override
	public List<ProjectCustomer> getall(int start,int end) {
		
		return dao.getall(start,end);
	}

	@Override
	public List<ProjectCustomer> getall1(int start, int end) {
		
		return dao.getall1(start,end);
	}

	@Override
	public List<ProjectCustomer> getall2(int start, int end) {
		
		return dao.getall2(start,end);
	}

	@Override
	public List<ProjectCustomer> getall1(int eid) {
	
		return dao.getall1(eid);
	}

	@Override
	public List<ProjectCustomer> getall1() {
		
		return dao.getall1();
	}
}
