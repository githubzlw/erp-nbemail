package cerong.erp.service;

import cerong.erp.entity.CSjishu;
import cerong.erp.entity.TuZhi;

public interface ITuZhiServiceImpl {
	/**
	 * 名称：修改项目链接
	 * author:wy
	 * date:2017-10-18
	 * @return
	 */
	public int updateCaseNo(int id, String caseNo);
	/**
	 * 名称：修改项目链接
	 * author:wy
	 * date:2017-10-18
	 * @return
	 */
	public int updateCaseNo1(int id, String caseNo);
	/**
	 * 
	 * @Title:ITuZhiServiceImpl
	 * @Description:上传技术文档
	   @author wangyang
	 * @param tuzhi void
	 * @throws
	 */
	public void add(TuZhi tuzhi);
	/**
	 * 
	 * @Title:ITuZhiServiceImpl
	 * @Description:查询是否已存在
	   @author wangyang
	 * @param tuzhi
	 * @return TuZhi
	 * @throws
	 */
	public TuZhi getTuzhi(TuZhi tuzhi);
	/**
	 * 
	 * @Title:ITuZhiServiceImpl
	 * @Description:查询技术协议是否存在
	   @author wangyang
	 * @param csjishu
	 * @return CSjishu
	 * @throws
	 */
	public CSjishu getCSjishu(CSjishu csjishu);
	/**
	 * 
	 * @Title:ITuZhiServiceImpl
	 * @Description:添加数据
	   @author wangyang
	 * @param csjishu1 void
	 * @throws
	 */
	public void addCSjishu(CSjishu csjishu1);
	/**
	 * 
	 * @Title:ITuZhiServiceImpl
	 * @Description:删除技术文档
	   @author wangyang
	 * @param tuzhi void
	 * @throws
	 */
	public void delete(TuZhi tuzhi);
	/**
	 * 
	 * @Title:ITuZhiServiceImpl
	 * @Description:删除技术协议
	   @author wangyang
	 * @param tuzhi void
	 * @throws
	 */
	public void deleteCSjishu(CSjishu csjishu);

}
