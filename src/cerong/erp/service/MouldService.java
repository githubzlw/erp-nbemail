package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.IMouldDao;
import cerong.erp.dao.MouldDaoImpl;
import cerong.erp.entity.Mould;



public class MouldService  implements IMouldServiceImpl{
	IMouldDao dao = new MouldDaoImpl();

	@Override
	public List<Mould> getall() {
		
		return dao.getall();
	}

	@Override
	public List<Mould> getall1(int eid) {
		
		return dao.getall1(eid);
	}

	@Override
	public List<Mould> getall2() {
		
		return dao.getall2();
	}
}
