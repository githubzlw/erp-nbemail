package cerong.erp.service;

import java.util.Date;
import java.util.List;

import cerong.erp.entity.Bonusdata;

public interface IBonusdataService {
	/**
	 * 拉取本月全部分红列表
	 * author:wy
	 * date:2017-7-6
	 * @param user
	 * @return
	 */
	public List<Bonusdata> getall(int year,int month);
/**
 * 
 * @Title:IBonusdataService
 * @Description:TODO
   @author wangyang
 * @param projectId
 * @return Bonusdata
 * @throws
 */
	public Bonusdata getall(String projectId);

/**
 * 
 * @Title:IBonusdataService
 * @Description:查询数据返回数据
   @author wangyang
 * @param projectIdList
 * @return List<Bonusdata>
 * @throws
 */
public List<Bonusdata> getalla(String projectIdList, int year, int month);
/**
 * 
 * @Title:IBonusdataService
 * @Description:跟新分红比例
   @author wangyang
 * @param year
 * @param month
 * @return List<Bonusdata>
 * @throws
 */
public List<Bonusdata> updateGrossProfit(int year, int month);

	/**
	 * 查看质量扣款退税项目
	 * @param projectNo
	 * @return
	 */
    List<Bonusdata> getQualityDeduction(String projectNo);

	/**
	 * 查询质量扣款分红
	 * @param projectNo

	 * @return
	 */

	List<Bonusdata> getAllDeductionProject(String projectNo, Date date1);
}
