package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.Factory;
import cerong.erp.entity.FactoryFund;

public interface IFactoryFundDaoImpl {

	/**
	 * 名称：获取项目全部信息
	 * author:wy
	 * date:2017-4-19
	 * @return
	 */ 
	public  List<FactoryFund> getall(String caseno);
	/**
	 * 名称：获取全部支付给工厂价格
	 * author:wy
	 * date:2017-4-20
	 * @return
	 */ 

	public FactoryFund getall1(String caseno);
	/**
	 * 
	 * @Title:IFactoryFundDaoImpl
	 * @Description:获取工厂信息
	   @author wangyang
	 * @param id
	 * @return Factory
	 * @throws
	 */
	public Factory getFactory(int id);
	/**
	 * 
	 * @Title:IFactoryFundServiceImpl
	 * @Description:工厂表修改扣完款金额
	   @author wangyang
	 * @param caseNo
	 * @param d
	 * @return int
	 * @throws
	 */
	public int updateMoney(String apNumber, double d);

}
