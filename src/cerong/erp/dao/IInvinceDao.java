package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.*;

public interface IInvinceDao {

	/**
	 * 名称：获取价格信息
	 * author:wy
	 * date:2017-5-16
	 * @return
	 */ 
	

	public List<invoice> getAll(int start, int end);
	/**
	 * 名称：拉数据
	 * author:wy
	 * date:2017-5-25
	 * @return
	 */ 

	public invoice getAll(String projectId);
	/**
	 * 名称：郑佳拉取发票数据
	 * author:wy
	 * date:2017-6-21
	 * @return
	 */
	public List<Invoiceinfo1> getall();
	/**
	 * 名称：获取近一年的项目
	 * author:wy
	 * date:2017-6-21
	 * @return
	 */
	public List<invoice> getall1();
	/**
	 * 名称：新增invoice
	 * author:wy
	 * date:2017-6-29
	 * @return
	 */
	public int add(Invoiceinfo1 info);
	/**
	 * 名称：根据eid调数据
	 * author:wy
	 * date:2017-6-29
	 * @return
	 */
	public List<invoice> getAll1(int eid);
	/**
	 * 名称：根据eid拉取郑佳拉取发票数据
	 * author:wy
	 * date:2017-7-1
	 * @return
	 */
	
	public List<Invoiceinfo1> getall1(int eid);
	/**
	 * 名称：根据eid拉取全部项目
	 * author:wy
	 * date:2017-7-1
	 * @return
	 */
	
	public List<invoice> getalla(int eid);
	/**
	 * 名称：定时器拉取发票信息
	 * author:wy
	 * date:2017-7-6
	 * @return
	 */
	public List<Invoiceinfo1> getall2();
	/**
	 * 名称：定时拉取项目
	 * author:wy
	 * date:2017-7-7
	 * @return
	 */
	public List<invoice> getalla2();
	/**
	 * 
	 * @Title:IInvinceDao
	 * @Description:修改客户未付款备注
	   @author wangyang
	 * @param projectId
	 * @param outstandingNotes
	 * @return int
	 * @throws
	 */
	//public int updateOutstandingNotes(String projectId, String outstandingNotes);
	public int updateOutstandingNotes(int id, String outstandingNotes,
			int reason1,String name);
	/**
	 * 
	 * @Title:IInvinceServiceImpl
	 * @Description:将提交理由放到数据库
	   @author wangyang
	 * @param iid
	 * @param projectFinished
	 * @param money
	 * @param reason
	 * @param explain
	 * @return int
	 * @throws
	 */
	public int updateAll(int iid, String projectFinished, double money,
			String reason, String explain);
	/**
	 * 
	 * @Title:IInvinceDao
	 * @Description:查询新客户新项目列表页
	   @author wangyang
	 * @param it
	 * @return List<Invoiceinfo1>
	 * @throws
	 */
	public List<Invoiceinfo1> detailsOfNewCustomerArrival(ItemCase it);
	
	/**
	 * 
	 * @Title:IInvinceServiceImpl
	 * @Description:客户列表页,获取总金额
	   @author wangyang
	 * @param it
	 * @return List<Invoiceinfo1>
	 * @throws
	 */
	public List<Invoiceinfo1> getall(ItemCase it);
	/**
	 * 
	 * @Title:IInvinceServiceImpl
	 * @Description:获取跟单进账页面
	   @author wangyang
	 * @param it
	 * @return List<Invoiceinfo1>
	 * @throws
	 */
	public List<Invoiceinfo1> getDocumentaryToAccountPage(ItemCase it);
	/**
	 * 
	 * @Title:IInvinceServiceImpl
	 * @Description:修改查询出运单时间
	   @author wangyang
	 * @param time
	 * @return int
	 * @throws
	 */
	public int updateMonth(String time);
	/**
	 * 
	 * @Title:IInvinceServiceImpl
	 * @Description:清除历史出运单数据
	   @author wangyang
	 * @return int
	 * @throws
	 */
	public int deleteHistoricalWaybillData();
	/**
	 * 
	 * @Title:IInvinceDao
	 * @Description:上传数据
	   @author wangyang
	 * @param data
	 * @return int
	 * @throws
	 */
	public int insertHistoricalWaybillData(ShippingBillData data);
	/**
	 * 根据月份查询数据
	 * @param time
	 * @param time1
	 * @return
	 */
    List<MonthlyProfitStatement> getMonthlyProfitStatement(String time, String time1);
	/**
	 * 查询该月该销售营业额及利润
	 * @param userName
	 * @param s
	 * @return
	 */
    MonthlyProfitStatement getOne(String userName, String s);

	/**
	 * 批量插入数据到数据库
	 * @param statelist
	 */
	void insertAll(List<MonthlyProfitStatement> statelist);
	/**
	 * 查询最后一个
	 * @param time1
	 * @return
	 */
	MonthlyProfitStatement getLastOne(String time1);
	/**
	 * 修改全部数据
	 * @param statelist
	 */
	void updateAllList(List<MonthlyProfitStatement> statelist);
}
