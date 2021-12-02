package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.Invoiceinfo1;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.invoiceinfo;

public interface IinvoiceinfoServiceImpl {
	/**
	 * 名称：查看发票页面
	 * author:wy
	 * date:2017-4-19
	 * @return
	 */ 
	public List<invoiceinfo> getall(String caseno);
	/**
	 * 名称：查看该项目预计全部收入及收款
	 * author:wy
	 * date:2017-4-20
	 * @return
	 */ 
	public invoiceinfo getall1(String caseno);
	/**
	 * 
	 * @Title:IinvoiceinfoServiceImpl
	 * @Description:查询invoice是否存在
	   @author wangyang
	 * @param invoice
	 * @return int
	 * @throws
	 */
	public invoiceinfo getInvoice(String invoice,String flag);
	/**
	 * @param empEName 
	 * 
	 * @Title:IinvoiceinfoServiceImpl
	 * @Description:获取invoice表数据
	   @author wangyang
	 * @param invoice
	 * @return invoiceinfo
	 * @throws
	 */
	public invoiceinfo getRefundInvoice(String invoice, String empEName);
	/**
	 * 
	 * @Title:IinvoiceinfoServiceImpl
	 * @Description:修改invoice数据
	   @author wangyang
	 * @param id
	 * @return int
	 * @throws
	 */

	public int updateApprovalResults(int id, int num);
	/**
	 * 
	 * @Title:IinvoiceinfoServiceImpl
	 * @Description:查询进账列表
	   @author wangyang
	 * @param it
	 * @return List<Invoiceinfo1>
	 * @throws
	 */
	public List<Invoiceinfo1> queryReceiptList(Invoiceinfo1 it);
	/**
	 * 
	 * @Title:IinvoiceinfoServiceImpl
	 * @Description:查看合同号是否存在
	   @author wangyang
	 * @param invoice
	 * @return invoiceinfo
	 * @throws
	 */
	public invoiceinfo getRefundInvoice(String invoice);
	

}
