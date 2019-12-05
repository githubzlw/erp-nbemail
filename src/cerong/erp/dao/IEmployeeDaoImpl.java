package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.EmailUser;

public interface IEmployeeDaoImpl {
	/**
	 * ��������:�޸�ERP�ϵ��û�״̬����Ϊ��ְ��
	 * author:wy
	 * date:2016��4��15��
	 * @param user
	 * @return
	 */


	public int updateDel(String userName);
	
	/**
	 * ��������:�޸�ERP�ϵ��û�״̬����Ϊ��ְ��
	 * author:wy
	 * date:2016��4��15��
	 * @param user
	 * @return
	 */

	public int getBack(String userName);
	/**
	 * ��������:ɾ���û�
	 * author:wy
	 * date:2016��4��15��
	 * @param user
	 * @return
	 */

	public int del(String userName);
	/**
	 * ��������:�޸��û���ɫ
	 * author:wy
	 * date:2016��4��15��
	 * @param user
	 * @return
	 */

	public int updateRole(EmailUser user, String userName);
	/**
	 * ��������:����û�
	 * author:wy
	 * date:2016��4��18��
	 * @param user
	 * @return
	 */

	public int addUser(EmailUser user);
	/**
	 * ��������:��֤�û���������Ƿ���ȷ
	 * author:wy
	 * date:2016��4��21��
	 * @param user
	 * @return
	 */

	public int getUser(String empPWD, String empEName);
	/**
	 * ��������:�鿴�û����Ƿ����
	 * author:wy
	 * date:2016��4��26��
	 * @param user
	 * @return
	 */

	public int getName(String userName);
	/**
	 * ��������:����û�
	 * author:wy
	 * date:2016��6��1��
	 * @param user
	 * @return
	 */

	public int addUser1(EmailUser user);
	/**
	 * ��������:��Payment_Maolirun�����ֶ�
	 * author:wy
	 * date:2016��6��1��
	 * @param user
	 * @return
	 */

	public boolean createUser(String empEName);
	/**
	 * 
	 * @Title:IEmployeeServiceImpl
	 * @Description:查询客户是否存在
	   @author wangyang
	 * @param user
	 * @return EmailUser
	 * @throws
	 */
	public EmailUser search(EmailUser user);
	/**
	 * 
	 * @Title:IEmployeeServiceImpl
	 * @Description:修改成员
	   @author wangyang
	 * @param user void
	 * @throws
	 */
	public void update(EmailUser user);
	/**
	 * 
	 * @Title:IEmployeeServiceImpl
	 * @Description:添加成员
	   @author wangyang
	 * @param user void
	 * @throws
	 */
	public void insert1(EmailUser user);
	/**
	 * 
	 * @Title:IEmployeeServiceImpl
	 * @Description:获取跟单采购列表
	   @author wangyang
	 * @return List<EmailUser>
	 * @throws
	 */
	public List<EmailUser> getMember();
	/**
	 * 查询在职销售跟单
	 * @return
	 */

    List<EmailUser> getAllSales();


	/**
	 * 查询全部跟单
	 * @return
	 */
	List<EmailUser> getAll();
	/**
	 * 查询跟单数据
	 * @return
	 */
	EmailUser getAccountingCustomers(EmailUser user);
}
