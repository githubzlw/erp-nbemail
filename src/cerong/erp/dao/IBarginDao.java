package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.Bargin;

public interface IBarginDao {
	/**
	 * 查询全部合同号
	 * author:wy
	 * date:2017-7-24
	 * @param user
	 * @return
	 */

	public List<Bargin> getall(Bargin ba);
	/**
	 * 根据id查询全部合同号
	 * author:wy
	 * date:2017-7-24
	 * @param user
	 * @return
	 */

	public Bargin getBaigin(int id1);
	/**
 	 * 根据id，删除
 	 * author:wy
 	 * date:2017-7-24
 	 * @param user
 	 * @return
 	 */
	public int deleteBargin(int id1);
	/**
 	 * 项目及合同号，删除
 	 * author:wy
 	 * date:2017-7-24
 	 * @param user
 	 * @return
 	 */
	public int deleteFactoryFund(String name, String projectId);
	/**
 	 * 项目及合同号，删除
 	 * author:wy
 	 * date:2017-7-24
 	 * @param user
 	 * @return
 	 */
	public int deleteMoneyCheckUp(String name, String projectId);
	/**
	 * 查询全部合同号
	 * author:wy
	 * date:2017-7-24
	 * @param user
	 * @return
	 */
	public List<Bargin> getall1(Bargin ba);
	/**
	 * 
	 * @Title:IBarginServiceImpl
	 * @Description:超时未收回发票合同列表
	   @author wangyang
	 * @param it
	 * @return List<Bargin>
	 * @throws
	 */
	public List<Bargin> getAll(Bargin it);
	/**
	 * @param b 
	 * 
	 * @Title:IBarginServiceImpl
	 * @Description:超过4个月未收到尾款项目
	   @author wangyang
	 * @return List<Bargin>
	 * @throws
	 */
	public List<Bargin> more4MonthInvoiceNotReceived(Bargin b);
	/**
	 * @param date 
	 * 
	 * @Title:IBarginDao
	 * @Description:查询报关品名
	   @author wangyang
	 * @param bargainno
	 * @return String
	 * @throws
	 */
	public String search(String bargainno, String date);
	

}
