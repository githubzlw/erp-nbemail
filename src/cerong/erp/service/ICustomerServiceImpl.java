package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.Customer;
import cerong.erp.entity.CustomerInfo;

public interface ICustomerServiceImpl {
	/**
	 * ��������:�޸Ŀͻ���Ϣ
	 * author:wy
	 * date:2016��4��15��
	 * @return
	 */

public	int updateInfo(CustomerInfo cus, int erpcid);
/**
 * ��������:�����ͻ�
 * author:wy
 * date:2016��5��16��
 * @return
 */

	public int getID(String firstName, int country, String ddlDgree);
	/**
	 * ��������:��ѯ�ͻ����Ƿ��Ѵ���
	 * author:wy
	 * date:2016��5��16��
	 * @return
	 */
public int getfirstName(String firstName);
/**
 * ��������:�鿴�ͻ�Id
 * author:wy
 * date:2016��5��16��
 * @return
 */
	public int getId(String firstName);
	/**
	 * ��������:�鿴4����δ��ϵ�ͻ��б�
	 * author:wy
	 * date:2016��6��1��
	 * @return
	 */
	public List<CustomerInfo> getCus(CustomerInfo info);
/**
 * ��������:�Կͻ����н���
 * author:wy
 * date:2016��6��3��
 * @return
 */

	public CustomerInfo getCUS(int cid);
	/**
	 * ��������:�Կͻ����н���
	 * author:wy
	 * date:2016��6��3��
	 * @return
	 */

public int update(String name, String potential, String explain, int cid);
/**
 * ��������:�鿴�ͻ��б�
 * author:wy
 * date:2016��6��6��
 * @return
 */
	
public List<CustomerInfo> getCus1(CustomerInfo info);
/**
 * ��������:�鿴2015���������б�
 * author:wy
 * date:2016��6��22��
 * @return
 */
//public List<CustomerInfo> getCus2(CustomerInfo info,int id,int id1);
public List<CustomerInfo> getCus2(CustomerInfo info, int id, int id1, int id2,
		int id3);
/**
 * ��������:�鿴2016���������б�
 * author:wy
 * date:2016��6��22��
 * @return
 */
public List<CustomerInfo> getCus3(CustomerInfo info,int id,int id1,int id2,
		int id3);
/**
 * ��������:��ȡ2015�����һ����Ŀid
 * author:wy
 * date:2016��6��22��
 * @return
 */
public int getid(CustomerInfo info);
/**
 * ��������:��ȡ2015���һ����Ŀid
 * author:wy
 * date:2016��6��22��
 * @return
 */
public int getid1(CustomerInfo info);
/**
 * ��������:��ȡ2016�����һ����Ŀid
 * author:wy
 * date:2016��6��22��
 * @return
 */
public int getid2(CustomerInfo info);
/**
 * ��������:��ȡ2016���һ����Ŀid
 * author:wy
 * date:2016��6��22��
 * @return
 */
public int getid3(CustomerInfo info);
/**
 * ��������:�鿴�������30��ǰ���һ���ͻ�����id
 * author:wy
 * date:2016��7��7��
 * @return
 */
public int getid3(Long millionSeconds1);
/**
 * ��������:��ȡ���һ���¶������
 * author:wy
 * date:2016��7��7��
 * @return
 */
public List<CustomerInfo> getCu(CustomerInfo info, int id1, int id2);
//public List<CustomerInfo> getCu(CustomerInfo info, int id1);
/**
 * ��������:��ȡ2015�����һ��������Ŀid
 * author:wy
 * date:2016��7��9��
 * @return
 */

public int getId(CustomerInfo info);
/**
 * ��������:��ȡ2015���һ��������Ŀid
 * author:wy
 * date:2016��7��9��
 * @return
 */
public int getId1(CustomerInfo info);
/**
 * ��������:��ȡ2016�����һ��������Ŀid
 * author:wy
 * date:2016��7��9��
 * @return
 */
public int getId2(CustomerInfo info);
/**
 * ��������:��ȡ2016���һ��������Ŀid
 * author:wy
 * date:2016��7��9��
 * @return
 */
public int getId3(CustomerInfo info);
/**
 * ��������:�鿴�������30��ǰ���һ���ͻ�����id
 * author:wy
 * date:2016��7��9��
 * @return
 */
public int getId3(Long millionSeconds1);

/**
 * ��������:�Կͻ�״̬���ж���
 * author:wy
 * date:2016��7��9��
 * @return
 */
public int updateCstatus(String cstatus, int id);
/**
 * 方法：获取全部的客户信息
 * author:wy
 * date:2016-10-17
 * @return
 */
public List<Customer> getAll();
/**
 * 方法：获取全部客户数
 * author:wy
 * date:2016-10-21
 * @return
 */

public int getCusTotal(CustomerInfo info);
/**
 * 方法：获取4-18个月的客户数
 * author:wy
 * date:2016-10-21
 * @return
 */
public int getCus1Total(CustomerInfo info);
/**
 * 方法：获取1-2个月的客户数
 * author:wy
 * date:2016-10-21
 * @return
 */
public int getCuTotal(CustomerInfo info, int id1, int id2);
/**
 * 方法：获取2015年的客户数
 * author:wy
 * date:2016-10-21
 * @return
 */
public int getCus2Total(CustomerInfo info, int id, int id1, int id2, int id3);
/**
 * 方法：获取2016年的客户数
 * author:wy
 * date:2016-10-21
 * @return
 */


public int getCus3Total(CustomerInfo info, int id, int id1, int id2, int id3);
/**
 * 方法：获取最近12个月有有下单 但 最近 4个月没下单的客户数
 * author:wy
 * date:2016-10-24
 * @return
 */
public List<CustomerInfo> getCusb(CustomerInfo info, int id, int id1, int id2,
		int id3);
/**
 * 方法：最近12个月有有下单 但 最近 4个月没下单的
 * author:wy
 * date:2016-10-24
 * @return
 */
public int getCusbTotal(CustomerInfo info, int id, int id1, int id2, int id3);
/**
 * 方法：获取对应项目最后一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geid(CustomerInfo info, Long millionSeconds3);
/**
 * 方法：获取对应项目第一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geid1(CustomerInfo info, Long millionSeconds2);
/**
 * 方法：获取收款最后一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geId(CustomerInfo info, Long millionSeconds3);
/**
 * 方法：获取收款第一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geId1(CustomerInfo info, Long millionSeconds2);
/**
 * 方法：获取对应项目最后一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geida(CustomerInfo info, Long millionSeconds3);
/**
 * 方法：获取对应项目第一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geidb(CustomerInfo info, Long millionSeconds2);
/**
 * 方法：获取收款第一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geIdc(CustomerInfo info, Long millionSeconds3);
/**
 * 方法：获取收款第一个id
 * author:wy
 * date:2016-10-24
 * @return
 */
public int geIdd(CustomerInfo info, Long millionSeconds2);

/**
 * 方法：获取最近18个月有有下单 但 最近 12个月没下单的客户数
 * author:wy
 * date:2016-10-31
 * @return
 */
public List<CustomerInfo> getCusc(CustomerInfo info, int id, int id1, int id2,
		int id3);

/**
 * 方法：获取最近24个月有有下单 但 最近 18个月没下单的客户数
 * author:wy
 * date:2016-10-31
 * @return
 */
public List<CustomerInfo> getCusd(CustomerInfo info, int id, int id1, int id2,
		int id3);

/**
 * 方法：获取最近36个月有有下单 但 最近 24个月没下单的客户数
 * author:wy
 * date:2016-10-31
 * @return
 */
public List<CustomerInfo> getCuse(CustomerInfo info, int id, int id1, int id2,
		int id3);

/**
 * 方法：获取最近48个月有有下单 但 最近 36个月没下单的客户数
 * author:wy
 * date:2016-10-31
 * @return
 */
public List<CustomerInfo> getCusf(CustomerInfo info, int id, int id1, int id2,
		int id3);
/**
 * 方法：最近4个月下单客户情况
 * author:wy
 * date:2016-10-31
 * @return
 */
public List<CustomerInfo> getCu2(CustomerInfo info, int id1, int id2);
/**
 * 方法：最近2个月下单情况
 * author:wy
 * date:2016-10-31
 * @return
 */
public List<CustomerInfo> getCu1(CustomerInfo info, int id1, int id2);
/**
 * 方法：修改客户备注
 * author:wy
 * date:2017-6-8
 * @return
 */
public int updateremarks(int eid, String customerremarks);
/**
 * 方法：获取全部客户信息
 * author:wy
 * date:2017-8-8
 * @return
 */
public List<Customer> getall();





	

}
