package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.AllDrawingsDaoImpl;
import cerong.erp.dao.IAllDrawingsDao;
import cerong.erp.entity.AllDrawings;



public class AllDrawingsService implements  IAllDrawingsServiceImpl{
	IAllDrawingsDao dao= new AllDrawingsDaoImpl();

	@Override
	public List<AllDrawings> getall() {
		
		return dao.getall();
	}

	@Override
	public List<AllDrawings> getall1() {
		
		return dao.getall1();
	}

	@Override
	public List<AllDrawings> getDrawingsAll() {
		
		return dao.getDrawingsAll();
	}

	@Override
	public List<AllDrawings> getDrawingsAll1() {
		
		return dao.getDrawingsAll1();
	}

	@Override
	public void insert(AllDrawings drawing) {
		dao.insert(drawing);
	}

    @Override
    public List<AllDrawings> getAllDrawings(String caseNo) {

		return dao.getAllDrawings(caseNo);
    }
}
