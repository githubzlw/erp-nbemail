package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MoneyCheckUp;

public interface ICheckUpDaoImpl {
	/**
	 * ��������:���������б�
	 * author:wy
	 * date:2016��4��20��

	 * @return
	 */

public	List<MoneyCheckUp> getAll(MoneyCheckUp cp);
/**
 * ��������:���������б���
 * author:wy
 * date:2016��4��20��

 * @return
 */

	public int getTotal(MoneyCheckUp cp);
	/**
	 * ��������:���������б������������ܽ��
	 * author:wy
	 * date:2016��5��27��

	 * @return
	 */
	public int getTotal1(MoneyCheckUp cp);
	/**
	 * ��������:���������б�����ͨ���ܽ��
	 * author:wy
	 * date:2016��5��27��

	 * @return
	 */
	public int getTotal2(MoneyCheckUp cp);
	/**
	 * ��������:���������б�����δͨ���ܽ��
	 * author:wy
	 * date:2016��5��27��

	 * @return
	 */
	public int getTotal3(MoneyCheckUp cp);
	/**
	 * ��������:���������б�����δͨ��
	 * author:wy
	 * date:2016��5��27��

	 * @return
	 */
	public List<MoneyCheckUp> getAll1(MoneyCheckUp cp);
	/**
	 * ��������:���������б�����δͨ����
	 * author:wy
	 * date:2016��5��27��

	 * @return
	 */
	public int getTotal4(MoneyCheckUp cp);
	/**
	 * ��������:�߿����������б�����
	 * author:wy
	 * date:2016��5��30��

	 * @return
	 */
	public List<MoneyCheckUp> getAll2(MoneyCheckUp cp);
	/**
	 * ��������:�߿����������б�������
	 * author:wy
	 * date:2016��5��30��

	 * @return
	 */
	
	public int getTotal5(MoneyCheckUp cp);
	/**
	 * ��������:�滻����ʱ��
	 * author:wy
	 * date:2016��5��30��

	 * @return
	 */
	public int updateStateDate(int id, String time);
	/**
	 * 审批通过1万一下的全部审批
	 * author:wy
	 * date:2017-1-10

	 * @return
	 */
	public int update1(String time);
	/**
	 * 审批全部审批
	 * author:wy
	 * date:2017-1-10

	 * @return
	 */
	public int update(String time);
	/**
	 * 查看有没有Emma审批过订单
	 * author:wy
	 * date:2017-2-22

	 * @return
	 */
	public int getnum();
	/**
	 * 查看有没有Emma审批过订单
	 * author:wy
	 * date:2017-2-22

	 * @return
	 */
	public int getnum1();
	/**
	 * 查看Emma审批页面
	 * author:wy
	 * date:2017-3-14

	 * @return
	 */
	public List<MoneyCheckUp> getAlla(MoneyCheckUp cp);
	/**
	 * 查看Emma审批页面数
	 * author:wy
	 * date:2017-3-14

	 * @return
	 */
	public int getTotala(MoneyCheckUp cp);
	/**
	 * 查看销售经理审批页面
	 * author:wy
	 * date:2017-3-14

	 * @return
	 */
	public List<MoneyCheckUp> getAllb(MoneyCheckUp cp);
	/**
	 * 查看销售经理审批页面
	 * author:wy
	 * date:2017-3-14

	 * @return
	 */
	public int getTotalb(MoneyCheckUp cp);
	/**
	 * Emma预审
	 * author:wy
	 * date:2017-3-16

	 * @return
	 */
	public int preliminaryhearing(int id, String time);
	/**
	 * Emma直接审批通过
	 * author:wy
	 * date:2017-3-16

	 * @return
	 */
	public int directapproval(int id, String time);
	/**
	 * 查看销售经理审批通过项目
	 * author:wy
	 * date:2017-3-22

	 * @return
	 */
	public List<MoneyCheckUp> getAllm(MoneyCheckUp cp);
	/**
	 * 查看销售经理审批通过项目数
	 * author:wy
	 * date:2017-3-22

	 * @return
	 */
	public int getTotalm(MoneyCheckUp cp);
	/**
	 * 采购副总直接审批通过
	 * author:wy
	 * date:2017-3-22

	 * @return
	 */
	public int directapproval1(int id, String time);
	/**
	 * 查看审批列表
	 * author:wy
	 * date:2017-6-8

	 * @return
	 */
	public List<MoneyCheckUp> getAll2a(MoneyCheckUp cp);
	/**
	 * 获取对应项目
	 * author:wy
	 * date:2017-7-4

	 * @return
	 */
	public List<MoneyCheckUp> getproject(MoneyCheckUp cp);
	/**
	 * 获取对应项目数
	 * author:wy
	 * date:2017-7-4

	 * @return
	 */
	public int getprojecttotal(MoneyCheckUp cp);
	/**
	 * Emma审批通过款项
	 * author:wy
	 * date:2017-11-2

	 * @return
	 */
	public int getEmTotal(MoneyCheckUp cp);
	/**
	 * Eddie审批通过款项
	 * author:wy
	 * date:2017-11-2

	 * @return
	 */
	public int getEdTotal(MoneyCheckUp cp);
	/**
	 * 最近3天待审批款项
	 * author:wy
	 * date:2017-11-2

	 * @return
	 */
	public int getAllTotal(MoneyCheckUp cp);
	/**
	 * 
	 * @Title:ICheckUpServiceImpl
	 * @Description:查询是否已完成款项已完成，显示为1
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */
	public int getState(int id);
	/**
	 * 
	 * @Title:ICheckUpServiceImpl
	 * @Description:老的请求付款信息
	   @author wangyang
	 * @param cp
	 * @return List<MoneyCheckUp>
	 * @throws
	 */
	public List<MoneyCheckUp> getAllx(MoneyCheckUp cp);
	/**
	 * 
	 * @Title:ICheckUpServiceImpl
	 * @Description:T老的请求付款信息
	   @author wangyang
	 * @param cp
	 * @return List<MoneyCheckUp>
	 * @throws
	 */
	public List<MoneyCheckUp> getAlly(MoneyCheckUp cp);
	/**
	 * 
	 * @Title:ICheckUpServiceImpl
	 * @Description:老的请求付款信息
	   @author wangyang
	 * @param cp
	 * @return List<MoneyCheckUp>
	 * @throws
	 */
	public List<MoneyCheckUp> getAllz(MoneyCheckUp cp);
	/**
	 * 
	 * @Title:ICheckUpDaoImpl
	 * @Description:审批需添加理由
	   @author wangyang
	 * @param id
	 * @param projecttypes
	 * @return int
	 * @throws
	 */
	public int updateApproval(int id, String projecttypes);
	/**
	 * 
	 * @Title:ICheckUpServiceImpl
	 * @Description:销售提交审批理由
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */
	public int updateApproval1(int id);
	/**
	 * 
	 * @Title:ICheckUpServiceImpl
	 * @Description:审批表显示扣完款金额
	   @author wangyang

	 * @param d
	 * @return int
	 * @throws
	 */
	public int updateMoney(String apNumber, double d);
	/**
	 * 
	 * @Title:ICheckUpDaoImpl
	 * @Description:添加时间标记到付款信息
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */
	public int add(int id);
	/**
	 * 
	 * @Title:ICheckUpDaoImpl
	 * @Description:添加时间标记到付款信息
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */
	public int add1(int id);

	/**
	 * 取消申请付款
	 * @param id
	 * @param time1
	 * @return
	 */

	int updateWithDraw(int id, String time1, EmailUser user1);
	/**
	 * 查看全部快速通道项目
	 * @param cp
	 * @return
	 */
	List<MoneyCheckUp> getFastTrackProject(MoneyCheckUp cp);
	/**
	 * 获取否决项目数据
	 * @return
	 */
    List<MoneyCheckUp> getProjectVeto();
}
