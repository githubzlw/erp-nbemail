package cerong.erp.service;

import java.util.Date;
import java.util.List;

import cerong.erp.dao.BonusdataDaoImpl;
import cerong.erp.dao.IBonusdataDao;
import cerong.erp.entity.Bonusdata;

public class BonusdataServiceImpl implements IBonusdataService{
	IBonusdataDao dao= new BonusdataDaoImpl();

	@Override
	public List<Bonusdata> getall(int year,int month) {
		
		return dao.getall(year,month);
	}

	@Override
	public Bonusdata getall(String projectId) {
		
		return dao.getall(projectId);
	}


	@Override
	public List<Bonusdata> getalla(String projectIdList, int year, int month) {
		
		return dao.getalla(projectIdList, year,  month);
	}

	@Override
	public List<Bonusdata> updateGrossProfit(int year, int month) {
		
		return dao.updateGrossProfit(year,month);
	}

	@Override
	public List<Bonusdata> getQualityDeduction(String projectNo) {
		return dao.getQualityDeduction(projectNo);
	}

	@Override
	public List<Bonusdata> getAllDeductionProject(String projectNo,  Date date1) {
		return dao.getAllDeductionProject(projectNo,date1);
	}

}
