package cerong.erp.service;

import cerong.erp.dao.IProjectComplaintDao;
import cerong.erp.dao.ProjectComplaintDaoImpl;
import cerong.erp.entity.ProjectComplaint;



public class ProjectComplaintServiceImpl implements IProjectComplaintService{
	IProjectComplaintDao dao = new ProjectComplaintDaoImpl();

	@Override
	public int getall() {
		
		return dao.getall();
	}

	@Override
	public int getid() {
		
		return dao.getid();
	}
}
