package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.BarginDaoImpl;
import cerong.erp.dao.IBarginDao;
import cerong.erp.entity.Bargin;



public class BarginService  implements IBarginServiceImpl{
	IBarginDao dao= new BarginDaoImpl();

	@Override
	public List<Bargin> getall(Bargin ba) {
		
		return dao.getall(ba);
	}

	@Override
	public Bargin getBaigin(int id1) {
		
		return dao.getBaigin(id1);
	}

	@Override
	public int deleteBargin(int id1) {
		
		return dao.deleteBargin(id1);
	}

	@Override
	public int deleteFactoryFund(String name, String projectId) {
		
		return dao.deleteFactoryFund(name,projectId);
	}

	@Override
	public int deleteMoneyCheckUp(String name, String projectId) {
		
		return dao.deleteMoneyCheckUp(name,projectId);
	}

	@Override
	public List<Bargin> getall1(Bargin ba) {
		
		return dao.getall1(ba);
	}

	@Override
	public List<Bargin> getAll(Bargin it) {
		
		return dao.getAll(it);
	}

	@Override
	public List<Bargin> more4MonthInvoiceNotReceived(Bargin b) {
		
		return dao.more4MonthInvoiceNotReceived(b);
	}

	@Override
	public String search(String bargainno, String date) {
		
		return dao.search(bargainno,date);
	}
}
