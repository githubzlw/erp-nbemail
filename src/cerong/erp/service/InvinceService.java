package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.IInvinceDao;
import cerong.erp.dao.InvinceDaoImpl;
import cerong.erp.entity.*;

public class InvinceService  implements IInvinceServiceImpl{
	IInvinceDao dao = new InvinceDaoImpl();

	@Override
	public List<invoice> getAll(int start, int end) {
		
		return dao.getAll(start,end);
	}

	@Override
	public invoice getAll(String projectId) {
		
		return dao.getAll(projectId);
	}

	@Override
	public List<Invoiceinfo1> getall() {
		
		return dao.getall();
	}

	@Override
	public List<invoice> getall1() {
		
		return dao.getall1();
	}

	@Override
	public int add(Invoiceinfo1 info) {
		
		return dao.add(info);
	}

	@Override
	public List<invoice> getAll1(int eid) {
		
		return dao.getAll1(eid);
	}

	@Override
	public List<Invoiceinfo1> getall1(int eid) {
		
		return dao.getall1(eid);
	}

	@Override
	public List<invoice> getalla(int eid) {
		
		return dao.getalla(eid);
	}

	@Override
	public List<Invoiceinfo1> getall2() {
		
		return dao.getall2();
	}

	@Override
	public List<invoice> getalla2() {
		
		return dao.getalla2();
	}

	/*@Override
	public int updateOutstandingNotes(String projectId, String outstandingNotes) {
		
		return dao.updateOutstandingNotes(projectId,outstandingNotes);
	}*/

	@Override
	public int updateOutstandingNotes(int id, String outstandingNotes,
			int reason1,String name) {
		
		return dao.updateOutstandingNotes(id,outstandingNotes,reason1,name);
	}

	@Override
	public int updateAll(int iid, String projectFinished, double money,
			String reason, String explain) {
		
		return dao.updateAll(iid,projectFinished,money,reason,explain);
	}

	@Override
	public List<Invoiceinfo1> detailsOfNewCustomerArrival(ItemCase it) {
		
		return dao.detailsOfNewCustomerArrival(it);
	}

	

	@Override
	public List<Invoiceinfo1> getall(ItemCase it) {
		
		return dao.getall(it);
	}

	@Override
	public List<Invoiceinfo1> getDocumentaryToAccountPage(ItemCase it) {
		
		return dao.getDocumentaryToAccountPage(it);
	}

	@Override
	public int updateMonth(String time) {
		
		return dao.updateMonth(time);
	}

	@Override
	public int deleteHistoricalWaybillData() {
		
		return dao.deleteHistoricalWaybillData();
	}

	@Override
	public int insertHistoricalWaybillData(ShippingBillData data) {
		
		return dao.insertHistoricalWaybillData(data);
	}

	@Override
	public List<MonthlyProfitStatement> getMonthlyProfitStatement(String time, String time1) {
		return dao.getMonthlyProfitStatement(time,time1);
	}

    @Override
    public MonthlyProfitStatement getOne(String userName, String s) {
        return dao.getOne(userName,s);
    }

    @Override
    public void insertAll(List<MonthlyProfitStatement> statelist) {
		dao.insertAll(statelist);
    }

    @Override
    public MonthlyProfitStatement getLastOne(String time1) {

		return dao.getLastOne(time1);
    }

    @Override
    public void updateAllList(List<MonthlyProfitStatement> statelist) {
		dao.updateAllList(statelist);
    }
}
