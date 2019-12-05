package cerong.erp.dao;

import cerong.erp.entity.Admin;

public interface IAdminDaoImpl {

		/**
	 * 添加出运联系单人员
	 * author:wy
	 * date:2017-2-22
	 * @param user
	 * @return
	 */

	public int add(Admin admin);
	/**
	 * 查看用户名是否已存在
	 * author:wy
	 * date:2017-2-22
	 * @param user
	 * @return
	 */

		public int getid(String userName);

}
