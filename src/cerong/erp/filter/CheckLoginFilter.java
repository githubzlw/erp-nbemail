package cerong.erp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cerong.erp.entity.EmailUser;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IEmployeeServiceImpl;

public class CheckLoginFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		// 获得用户请求的URI
		String path = req.getRequestURI();
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		String path1[]= path.split("/");
		if(path.indexOf("/login-admin-2015.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/ERP-NBEmail/js/jquery-1.3.2.min.js") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path1[2].indexOf("My97DatePicker") > -1 ) {
			chain.doFilter(req, res);
			return;
		}

		if(path.indexOf("/register.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/shipment_contact_list_register.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/clearing_details_page.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/login.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/CheckUp.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/right.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		
		
		if(path.indexOf("/press_for_money.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/refund_entry_page.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		
		if(path.indexOf("/quote_to_client.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/create_customer.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/css/loginbase.css") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/emana/elion.png") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/ERP-NBEmail/css/add.css") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/ERP-NBEmail/css/index.css") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/logo.png") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/Customer.jsp") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/login/loginm_04.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/login/loginm_05.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/login/loginmb.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/login/basebtn.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/login/logtop.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/examine/img/3.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/login/loginm_04.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/img/user/userhead.jpg") > -1 ) {
			chain.doFilter(req, res);
			return;
		}
		if(path.indexOf("/images/Face/59.gif") > -1 ) {
			chain.doFilter(req, res);
			return;
		}

		if(action != null && action.equals("detailPage")) {//查看返单率
			chain.doFilter(req, res);
			return;
		}
		
		if(action != null && action.equals("refundCompleted")) {//财务修改操作
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("transAudit")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("cusList")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("pressMoney")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("contractMaturity")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("cstatus")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("notuploadpicture")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("LookCustomer")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("transAudit2")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("transAudit1")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("salesmanagerapproval")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("directapproval1")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("detailsOfNewCustomerArrival")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("SalesRank")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("lookCus")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("directapproval")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("preliminaryhearing")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("SalesRank1")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customer1")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customer2")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		// 审批页
     if(path.indexOf("/ERP-NBEmail/download") > -1 ) {
					chain.doFilter(req, res);
					return;
	}
		if(action != null && action.equals("updateMoney")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		
		if(action != null && action.equals("registerAdmin")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		
		if(action != null && action.equals("updateSaleName")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Attachment")) {//登录的action也无�?���?
			chain.doFilter(req, res);
			return;
		}
		
	
		if(action != null && action.equals("updateSaleName1")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("CreateCustomer")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customerb")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customerc")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("createProject")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		
		if(action != null && action.equals("Adminlogin")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("login")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("register1")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("CheckUp")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("CheckUp1")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("deleteSaleName")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		
		if(action != null && action.equals("deleteSaleName1")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("update")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("explain")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("explain1")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("del")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("updateRole")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("register")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("registererp")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customera")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customerd")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customere")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Customerf")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("cusUpdate")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("updateMoney")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("quoteClient")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("getItemCase")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Marlboro1")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("Marlboro")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("viewdetails")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("updatequoter")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("deleteQuoter")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("getinformation")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("findproject")) {//�?��也无�?���?
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("pulldie")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("updatecustomerremarks")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("pullinvoicedata")) {//郑佳拉去invoice
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("ERPpulldata")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("pullallorders")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("modifyinvoice")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("acquiringcustomers")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("acquiringcustomers1")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("lookCus1")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("pulldie1")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("pullinvoicedata1")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("pullallorders1")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("bonusdata")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("deleteBargin")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("deleteBargin1")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("searchBargin")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("projectDrawing")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("inspectionReport")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("allCustomer")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("ViewFactoryData")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("AnnualFinancialStatement")) {//郑佳拉去数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("customerReconciliation")) {//客户对账
			chain.doFilter(req, res);
			return;
		}
	     if(action != null && action.equals("lookOutstandingFunds")) {//客户对账
			chain.doFilter(req, res);
			return;
		}
	     if(action != null && action.equals("publicComment")) {//客户对账
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("publicComment1")) {//客户对账
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("PurchasingSystem1")) {//客户对账
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("checkQualityReport")) {//查看全部质检报告
	    	 chain.doFilter(req, res);
	    	 return;
	     }
		
	     if(action != null && action.equals("projectContractTime")) {//查看全部质检报告
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateReason1")) {//查看全部质检报告
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("acquiringcustomersa")) {//查看全部质检报告
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("viewAllDrawings")) {//查看全部图纸信息
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateCaseNo")) {//修改项目号
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateCaseNo1")) {//还原
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("viewConferenceRecords")) {//会议记录
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("reviseMeetingRecord")) {//提交数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("examinationApprovalContracts")) {//提交数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("viewConferenceRecords1")) {//提交数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("LookNewProject")) {//提交数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateCommonMode")) {//提交数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateFactory")) {//提交数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("projectNumber")) {//分红数据同步
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("publicComment2")) {//分红数据同步
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("publicComment3")) {//分红数据同步
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("SendReport")) {//同步质量分析表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("getContent")) {//获取正文内容
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("bonusdata1")) {//获取正文内容
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("RequestPaymentInformation")) {//老的请求付款信息
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("PassTheReason")) {//Emma添加审批不通过理由
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("factoryUnpaidStatistics")) {//工厂未付款
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("CustomerPaymentStatistics")) {//客户未付款
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("getTailBonus")) {//客户未付款
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("inspectionReporta")) {//客户未付款
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("qualityReport")) {//上传质检报告
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("nonPaymentCustomers")) {//上传质检报告
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateAllSaleName")) {//修改客户全部销售
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateAllSaleName1")) {//修改客户全部跟单销售
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("bonusdat")) {//修改客户全部跟单销售
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("outstandingNotes")) {//修改客户全部跟单销售
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateExplain")) {//修改项目质量扣款
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("addContractWithdrawing")) {//修改项目质量扣款
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     
	     if(action != null && action.equals("accounEntry")) {//进账录入页面
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("UploadForm")) {//进账录入excel导入
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("enterFinancialEntry")) {//进账录入详情页
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("lookEnterFinancialEntry")) {//进账录入详情页查看
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("recoveryInformation")) {//信息复原
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("checkData")) {//校验信息并转具体页面
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("batchClaim")) {//批量认领
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("salesModificationInformation")) {//销售确认成本
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("financialContributionApproval")) {//财务审核页面
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("batchApproval")) {//审批通过
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("completionOfMoney")) {//审批通过列表页
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("allRefundInterface")) {//退款列表页
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("refundConfirmationButton")) {//退款录入
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("approvalResults")) {//审批列表页
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("refundApprovalResult")) {//审批结果页
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("lookERPEnterFinancialEntry")) {//审批结果页
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     
	     if(action != null && action.equals("startProjectStatistics")) {//审批结果页
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("quotationsUploaded")) {//内部报价转ERP内部报价单
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("insertCustomer")) {//客户关系表录入页面
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("insertEnteryCustomer")) {//确认录入操作
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("deleteAccountEntry")) {//删除信息
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateAccountEntry")) {//修改客户是否老客户
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("exportReceiptForm")) {//导出excel表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("enquiryDetails")) {//查询到账详情
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("checkCustomer")) {//查询到账详情
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("modifyingProjectMembers")) {//查询到账详情
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("splitAmounty")) {//金额拆分
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("modifyingAllCustomerMembers")) {//修改成员
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("inquireIntoAccounts")) {//修改成员
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("modifyingMembers")) {//修改成员
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("queryReceiptList")) {//显示进账列表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("addFactory")) {//显示进账列表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("modifyingMembers")) {//显示进账列表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     
	     if(action != null && action.equals("deleteItem")) {//清理项目
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     
	     if(action != null && action.equals("updateRemarks")) {//清理项目
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("checkData1")) {//清理项目
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("clientToAccountListPage")) {//清理项目
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("documentaryToAccountPage")) {//跟单到账
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("externalQuotation")) {//对外报价单上传
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("noInvoiceReceiptHasBeenReceived")) {//发票未收齐合同列表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("more4MonthInvoiceNotReceived")) {//超过4个月发票未收齐合同列表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("invoiceFactoryOwnedToUs")) {//列出欠我司发票的所有工厂以及欠的未收发票金额
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("allDetailedAccounts")) {//所有未收发票金额数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
		if(action != null && action.equals("factoryNameByInvoiceName")) {//可以开该品名的工厂列表
			chain.doFilter(req, res);
			return;
		}

	     if(action != null && action.equals("search")) {//查询报关名
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("invoiceDocumentarySaleOwnedToUs")) {//跟单名下欠我司发票工厂
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("invoiceDocumentaryPurchaseOwnedToUs")) {//采购名下欠我司发票工厂
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("finalInvoice")) {//尾款发票，根据金蝶号查询
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("enterTheCustomerRelevanceTableIntoTheAccount")) {//到账录入客户关联表
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("updateMonth")) {//修改查询月份
	    	 chain.doFilter(req, res);
	    	 return;
	     }
	     if(action != null && action.equals("uploadHistoricalWaybillData")) {//上传历史出运单数据
	    	 chain.doFilter(req, res);
	    	 return;
	     }
		if(action != null && action.equals("inquiryDocumentaryAccountin")) {//上传历史出运单数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("updateWithDraw")) {//取消申请付款
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("queryProfit")) {//查询利
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("updatecustomerremarks1")) {//查询利
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("efficiencyReport")) {//7个表格数据
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("getOne")) {//查询利
			chain.doFilter(req, res);
			return;
		}
		if(action != null && action.equals("updateCreateTime")) {//查询利
			chain.doFilter(req, res);
			return;
		}
		if(user == null) {
			String EmpEName=null;
			String EmpPWD=null;
			Cookie c[]=req.getCookies();
			if(c!=null)
			{
			for(int x=0;x<c.length;x++){
			if(c[x].getName().equals("SESSION_LOGIN_PASSWORD")){
						
				EmpPWD=c[x].getValue();
						
						}	
				
			if(c[x].getName().equals("SESSION_LOGIN_USERNAME"))
			{
			
				EmpEName=c[x].getValue();
				 
			}
		
			
			} 
			}
			if(EmpEName!=null&&EmpPWD!=null&&!"".equals(EmpPWD)&&!"".equals(EmpEName)){
				
				 String s = "edwardfanemmaxieZHANGLAN3";
				 Boolean index1 = s.toLowerCase().contains(EmpEName.toLowerCase());
			if(index1!=false){	
				EmailUser user1=new EmailUser();
				user1.setUserName(EmpEName);
				user1.setPwd(EmpPWD);
				session.setAttribute("user", user1);
				res.sendRedirect("/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet");	
					
				
				
			}else{
			
		
			if(path.indexOf(".jsp") > -1 /*|| path.indexOf("/jsp") >-1*/ ) {//说明直接在浏览器输入jsp地址
				res.sendRedirect("/ERP-NBEmail/jsp/login-admin-2015.jsp");
				
				
			}else{
				res.sendRedirect("/ERP-NBEmail/jsp/login.jsp");
				
				
			}
			}
		}else {
			chain.doFilter(req, res);
		
		}
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
