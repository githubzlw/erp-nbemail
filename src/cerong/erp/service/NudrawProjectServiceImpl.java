package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.INudrawProjectDao;
import cerong.erp.dao.NudrawProjectDaoImpl;
import cerong.erp.entity.NudrawProject;


public class NudrawProjectServiceImpl implements INudrawProjectService{
	INudrawProjectDao dao = new NudrawProjectDaoImpl();

	@Override
	public List<NudrawProject> getAll(Long millionSeconds1, Long millionSeconds2) {
		
		return dao.getAll(millionSeconds1,millionSeconds2);
	}
}
