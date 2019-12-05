package cerong.erp.service;

import cerong.erp.dao.ITuZhiDaoImpl;
import cerong.erp.dao.TuZhiDao;
import cerong.erp.entity.CSjishu;
import cerong.erp.entity.TuZhi;



public class TuZhiService implements ITuZhiServiceImpl{
	ITuZhiDaoImpl dao = new TuZhiDao();

	@Override
	public int updateCaseNo(int id, String caseNo) {
	
		return dao.updateCaseNo(id,caseNo);
	}

	@Override
	public int updateCaseNo1(int id, String caseNo) {
		
		return dao.updateCaseNo1(id,caseNo);
	}

	@Override
	public void add(TuZhi tuzhi) {
		
		dao.updateCaseNo1(tuzhi);
	}

	@Override
	public TuZhi getTuzhi(TuZhi tuzhi) {
		
		return dao.getTuzhi(tuzhi);
	}

	@Override
	public CSjishu getCSjishu(CSjishu csjishu) {
		
		return dao.getCSjishu(csjishu);
	}

	@Override
	public void addCSjishu(CSjishu csjishu1) {
		dao.addCSjishu(csjishu1);
		
	}

	@Override
	public void delete(TuZhi tuzhi) {
		dao.delete(tuzhi);
		
	}

	@Override
	public void deleteCSjishu(CSjishu csjishu) {
		dao.deleteCSjishu(csjishu);
		
	}
}
