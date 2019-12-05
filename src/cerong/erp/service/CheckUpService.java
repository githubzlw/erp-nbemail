package cerong.erp.service;

import java.util.List;

import cerong.erp.dao.CheckUpDao;
import cerong.erp.dao.ICheckUpDaoImpl;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MoneyCheckUp;



public class CheckUpService implements ICheckUpServiceImpl{
	ICheckUpDaoImpl dao = new  CheckUpDao();

	@Override
	public List<MoneyCheckUp> getAll(MoneyCheckUp cp) {
		
		return dao.getAll(cp);
	}

	@Override
	public int getTotal(MoneyCheckUp cp) {
		
		return dao.getTotal(cp);
	}

	@Override
	public int getTotal1(MoneyCheckUp cp) {
		
		return dao.getTotal1(cp);
	}

	@Override
	public int getTotal2(MoneyCheckUp cp) {
		
		return dao.getTotal2(cp);
	}

	@Override
	public int getTotal3(MoneyCheckUp cp) {
		
		return dao.getTotal3(cp);
	}

	@Override
	public List<MoneyCheckUp> getAll1(MoneyCheckUp cp) {
		
		return dao.getAll1(cp);
	}

	@Override
	public int getTotal4(MoneyCheckUp cp) {
		
		return dao.getTotal4(cp);
	}

	@Override
	public List<MoneyCheckUp> getAll2(MoneyCheckUp cp) {
		
		return dao.getAll2(cp);
	}

	@Override
	public int getTotal5(MoneyCheckUp cp) {
		
		return dao.getTotal5(cp);
	}

	@Override
	public int updateStateDate(int id, String time) {
		
		return dao.updateStateDate(id,time);
	}

	@Override
	public int update1(String time) {
		
		return dao.update1(time);
	}

	@Override
	public int update(String time) {
		
		return dao.update(time);
	}

	@Override
	public int getnum() {
		
		return dao.getnum();
	}

	@Override
	public int getnum1() {

		return dao.getnum1();
	}

	@Override
	public List<MoneyCheckUp> getAlla(MoneyCheckUp cp) {
		
		return dao.getAlla(cp);
	}

	@Override
	public int getTotala(MoneyCheckUp cp) {
		
		return dao.getTotala(cp);
	}

	@Override
	public List<MoneyCheckUp> getAllb(MoneyCheckUp cp) {
		
		return dao.getAllb(cp);
	}

	@Override
	public int getTotalb(MoneyCheckUp cp) {
		
		return dao.getTotalb(cp);
	}

	@Override
	public int preliminaryhearing(int id, String time) {
		
		return dao.preliminaryhearing(id,time);
	}

	@Override
	public int directapproval(int id, String time) {
		
		return dao.directapproval(id,time);
	}

	@Override
	public List<MoneyCheckUp> getAllm(MoneyCheckUp cp) {
		
		return dao.getAllm(cp);
	}

	@Override
	public int getTotalm(MoneyCheckUp cp) {
		
		return dao.getTotalm(cp);
	}

	@Override
	public int directapproval1(int id, String time) {
		
		return dao.directapproval1(id,time);
	}

	@Override
	public List<MoneyCheckUp> getAll2a(MoneyCheckUp cp) {
		
		return dao.getAll2a(cp);
	}

	@Override
	public List<MoneyCheckUp> getproject(MoneyCheckUp cp) {
		
		return dao.getproject(cp);
	}

	@Override
	public int getprojecttotal(MoneyCheckUp cp) {
		
		return dao.getprojecttotal(cp);
	}

	@Override
	public int getEmTotal(MoneyCheckUp cp) {
		
		return dao.getEmTotal(cp);
	}

	@Override
	public int getEdTotal(MoneyCheckUp cp) {
		
		return dao.getEdTotal(cp);
	}

	@Override
	public int getAllTotal(MoneyCheckUp cp) {
		
		return dao.getAllTotal(cp);
	}

	@Override
	public int getState(int id) {
		
		return dao.getState(id);
	}

	@Override
	public List<MoneyCheckUp> getAllx(MoneyCheckUp cp) {
		
		return dao.getAllx(cp);
	}

	@Override
	public List<MoneyCheckUp> getAlly(MoneyCheckUp cp) {
		
		return dao.getAlly(cp);
	}

	@Override
	public List<MoneyCheckUp> getAllz(MoneyCheckUp cp) {
		
		return dao.getAllz(cp);
	}

	@Override
	public int updateApproval(int id, String projecttypes) {
		
		return dao.updateApproval(id,projecttypes);
	}

	@Override
	public int updateApproval1(int id) {
		
		return dao.updateApproval1(id);
	}

	@Override
	public int updateMoney(String ApNumber, double d) {
		
		return dao.updateMoney(ApNumber,d);
	}

	@Override
	public int add(int id) {
		
		return dao.add(id);
	}

	@Override
	public int add1(int id) {
		
		return dao.add1(id);
	}

	@Override
	public int updateWithDraw(int id, String time1, EmailUser user1) {
		return dao.updateWithDraw(id,time1,user1);
	}

	@Override
	public List<MoneyCheckUp> getFastTrackProject(MoneyCheckUp cp) {
		return dao.getFastTrackProject(cp);
	}

	@Override
	public List<MoneyCheckUp> getProjectVeto() {
		return dao.getProjectVeto();
	}
}
