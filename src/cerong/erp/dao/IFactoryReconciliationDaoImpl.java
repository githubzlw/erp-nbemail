package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.FactoryReconciliation;

public interface IFactoryReconciliationDaoImpl {
	/**
	 * 名称：获取全部工厂统计数据
	 * author:wy
	 * date:2017-8-15
	 * @return
	 */ 
	public List<FactoryReconciliation> getall(int month,int year);
	/**
	 * 名称：年财务报表
	 * author:wy
	 * date:2017-8-16
	 * @return
	 */ 
	public List<FactoryReconciliation> getall1(int year);
	/**
	 * 
	 * @Title:IFactoryReconciliationServiceImpl
	 * @Description:获取当月交易详情
	   @author wangyang
	 * @param year
	 * @param month
	 * @param kingdee
	 * @return List<FactoryReconciliation>
	 * @throws
	 */
	public List<FactoryReconciliation> getAll(int year, int month, int kingdee);
	/**
	 * 修改创建时间
	 * @param id
	 * @param createTime
	 * @return
	 */
    int updateCreateTime(String id, String createTime);
}
