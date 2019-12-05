package cerong.erp.service;

import cerong.erp.entity.ProjectComplaint;

public interface IProjectComplaintService {
	/**
	 * 方法描述:查询所有项目号对应的投诉案子号
	 * author:wy
	 * date:2016年6月17日
	 * @param user
	 * @return
	 */

	public int getall();
	/**
	 * 方法描述:查看最后一个投诉是否添加
	 * author:wy
	 * date:2016年6月17日
	 * @param user
	 * @return
	 */

	public int getid();
	
	

}
