package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.ProjectCustomer;

public interface IProjectCustomerServiceImpl {
	/**
	 * 名称：获取全部项目及客户信息
	 * author:wy
	 * date:2017-6-21
	 * @return
	 */ 
	public List<ProjectCustomer> getall(int start,int end);
	/**
	 * 名称：获取全部项目及客户信息
	 * author:wy
	 * date:2017-6-21
	 * @return
	 */ 
	
	public List<ProjectCustomer> getall1(int start, int end);
	/**
	 * 名称：获取全部客户信息
	 * author:wy
	 * date:2017-6-30
	 * @return
	 */
	public List<ProjectCustomer> getall2(int start, int end);
	/**
	 * 名称：拉取最近一年项目全部客户
	 * author:wy
	 * date:2017-7-1
	 * @return
	 */
	public List<ProjectCustomer> getall1(int eid);
	/**
	 * 名称：拉取最近半小时项目到NBEmail
	 * author:wy
	 * date:2017-7-7
	 * @return
	 */
	public List<ProjectCustomer> getall1();

}
