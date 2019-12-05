package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.AccountRefundForm;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.Invoiceinfo1;
import cerong.erp.entity.invoiceinfo;
import cerong.erp.service.AccountRefundFormServiceImpl;
import cerong.erp.service.EmailUserServiceImpl;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IAccountRefundFormService;
import cerong.erp.service.IEmailUserService;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IQualityDeductionFormService;
import cerong.erp.service.IinvoiceinfoServiceImpl;
import cerong.erp.service.QualityDeductionFormServiceImpl;
import cerong.erp.service.invoiceinfoService;
import cerong.erp.util.MyFileUpLoad;



public class AccountRefundFormServlet extends HttpServlet{
	/**
	 * @Fields serialVersionUID
	  @author wangyang
	 */
	
	private static final long serialVersionUID = 1L;
	IAccountRefundFormService service =new AccountRefundFormServiceImpl();
	IinvoiceinfoServiceImpl iservice=new invoiceinfoService();
	IQualityDeductionFormService qservice = new QualityDeductionFormServiceImpl();
	IEmployeeServiceImpl uservice = new EmployeeService();
	/**
	 * 
	 * @Title:AccountRefundFormServlet
	 * @Description:财务退款详情页
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void allRefundInterface(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    String EmpEName=null;
		String EmpPWD=null;
		Cookie c[]=request.getCookies();
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		if(user1!=null){
			String s1 = "EdwardfanEmmaxie";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
		   if(index1!=false){
			request.setAttribute("refundment", 100);
			 }
		   String s2 = "EdwardfanEmmaxiemandymanlisaliShiGuoJuanninazhaoroseli";
			Boolean index2=false;
			index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index2!=false){
			   List<AccountRefundForm> list=service.allRefundInterface();
				request.setAttribute("cusList",list );
				request.getRequestDispatcher("jsp/all_refund_interface.jsp").forward(request, response);
			}else{
				 List<AccountRefundForm> list=service.allRefundInterface1(EmpEName);
					request.setAttribute("cusList",list );
					request.getRequestDispatcher("jsp/all_refund_interface.jsp").forward(request, response);	
			}
			
		}
	}
	/**
	 * 
	 * @Title:AccountRefundFormServlet
	 * @Description:审批结果页
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void refundApprovalResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName=null;
		String EmpPWD=null;
		Cookie c[]=request.getCookies();
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		AccountRefundForm account=new AccountRefundForm();
		String str = request.getParameter("vs");
		if(str != null && !"".equals(str)) {
			str = new String(str.getBytes("iso-8859-1"),"UTF-8");
			request.setAttribute("caseno", str);
		}
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
			if(condition.equals("1")) {//说明输入的是项目号
				account.setOperator(str);
			}else if(condition.equals("2")) {
				account.setCaseno(str);
			}
			request.setAttribute("fyfy", condition);
		}
		String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
		int  number = -1;
		if(condition2 != null && !"".equals(condition2) )
		{
			number = Integer.parseInt(condition2);
			account.setFinancialConfirmation(number);
			request.setAttribute("fyfz", condition2);
			
		}else{
			request.setAttribute("fyfz", 0);
			account.setFinancialConfirmation(0);
		}
		
		
		
		if(user1!=null){
			String s1 = "EmmaXieEdwardFanninazhao";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			
			String s2 = "mandymanlisaliShiGuoJuanroseli";
			Boolean index2=false;
			index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index2!=false){
				request.setAttribute("refundment", 100);
			}
			if(index2!=false||index1!=false){
				List<AccountRefundForm> list=service.refundApprovalResult(account);
				request.setAttribute("cusList",list );
				request.getRequestDispatcher("jsp/refund_approval_result.jsp").forward(request, response);
			}else{
				account.setOperator(EmpEName);
				List<AccountRefundForm> list=service.refundApprovalResult1(account);
				request.setAttribute("cusList",list );
				request.getRequestDispatcher("jsp/refund_approval_result.jsp").forward(request, response);
			}
		}
	}
	/**
	 * 
	 * @Title:AccountRefundFormServlet
	 * @Description:退款录入
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void refundConfirmationButton(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		MyFileUpLoad.upload(request, response);
		String EmpEName=null;
		String EmpPWD=null;
		Cookie c[]=request.getCookies();
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
		EmailUser user1=new EmailUser();
		user1.setEmailPWD(EmpPWD);
		user1.setUserName(EmpEName);
		EmailUser user2=uservice.search(user1);
		boolean operation=true;
		for(int i=0;i<8;i++){
			int num=i+1;
			String invoice= (String) request.getAttribute("invoice"+num);
			if(!"其他无InvoiceID的退款".equalsIgnoreCase(invoice)){
			if(invoice!=null&&!"".equalsIgnoreCase(invoice)){
				invoiceinfo info=null;
				String s1 = "EmmaXieEdwardFanninazhaomandymanlisaliShiGuoJuanroseli";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
			    info=iservice.getRefundInvoice(invoice);
				}else{
			   info=iservice.getRefundInvoice(invoice,EmpEName);
				}
			String refundAmount1= (String) request.getAttribute("refundAmount"+num);
			String reason1= (String) request.getAttribute("reason"+num);
			if(reason1!=null&&!"".equalsIgnoreCase(reason1)){
				reason1 = new String(reason1.getBytes("iso-8859-1"),"UTF-8");
			}
			if(info!=null){
				
			 double refundAmount=0;
   			if(refundAmount1!=null&&!"".equals(refundAmount1)){
   				refundAmount=Double.parseDouble(refundAmount1);
   			}
   			AccountRefundForm account1=service.getAll(invoice);
   			if(account1!=null){
   				out.write("<script>");
				 out.write("alert('历史记录存在申请退款，暂不允许申请');");
				 out.write("window.history.back();");
				 out.write("</script>");
				
				 operation=false;
				 return;	
   				
   			}else{
			if(info.getIfmoney()<refundAmount){
				out.write("<script>");
				 out.write("alert('第一笔付款金额是:"+info.getIfmoney()+",现退款金额:"+refundAmount+"');");
				 out.write("window.history.back();");
				 out.write("</script>");
				
				 operation=false;
				 return;
			} 
   			}
			}else{
			   out.write("<script>");
				 out.write("alert('"+invoice+",该invoice数据库不存在！或你不在该项目中');");
				 out.write("window.history.back();");
				 out.write("</script>");
				 operation=false;
				 return;
		   }
			}
		  }
		}
		if(operation==true){
			
		 for(int i=0;i<8;i++){
			int num=i+1;
			String invoice= (String) request.getAttribute("invoice"+num);
			String refundAmount1= (String) request.getAttribute("refundAmount"+num);
			if(refundAmount1!=null&&!"".equalsIgnoreCase(refundAmount1)){
			 double refundAmount=0.00;
	   			if(refundAmount1!=null&&!"".equals(refundAmount1)){
	   				refundAmount=Double.parseDouble(refundAmount1);
	   			}
	   			String reason1= (String) request.getAttribute("reason"+num);
				
	   			
			if(!"其他无InvoiceID的退款".equalsIgnoreCase(invoice)){
				if(invoice!=null&&!"".equalsIgnoreCase(invoice)){
					invoiceinfo info=null;
					String s1 = "EmmaXieEdwardFanninazhaomandymanlisaliShiGuoJuanroseli";
					Boolean index1=false;
					index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
					if(index1!=false){
				    info=iservice.getRefundInvoice(invoice);
					}else{
				   info=iservice.getRefundInvoice(invoice,EmpEName);
					}
			
			if(info!=null){
			 
   			AccountRefundForm account=new AccountRefundForm();
   			account.setCaseno(info.getCaseno());
   			account.setIimoney(info.getIimoney());
   			account.setIfmoney(info.getIfmoney());
   			account.setIid(info.getIid());
   			account.setInvoice(invoice);
   			account.setOperator(EmpEName);
   			account.setRefundAmount(refundAmount);
   			account.setReason(reason1);
			int total=service.add(account);
		  }
		 }
			}else{
				
				AccountRefundForm account=new AccountRefundForm();
	   			account.setCaseno(null);
	   			account.setIimoney(0);
	   			account.setIfmoney(0);
	   			account.setIid(0);
	   			account.setInvoice(invoice);
	   			account.setOperator(EmpEName);
	   			account.setRefundAmount(refundAmount);
	   			account.setReason(reason1);
				int total=service.add(account);
				}
			}
		 }
		 response.sendRedirect("/ERP-NBEmail/helpServlet?action=allRefundInterface&className=AccountRefundFormServlet");
			
		}
	}
	/**
	 * 
	 * @Title:AccountRefundFormServlet
	 * @Description:管理员审批是否通过
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void approvalResults(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName=null;
		String EmpPWD=null;
		Cookie c[]=request.getCookies();
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
		String id1= request.getParameter("id");
		String num1= request.getParameter("num");
		int id=0;
		if(id1!=null&&!"".equals(id1)){
			id=Integer.parseInt(id1);
		}
		int num=0;
		if(num1!=null&&!"".equals(num1)){
			num=Integer.parseInt(num1);
		}
		int total=service.updateApprovalResults(num,id,EmpEName);
		/*int total2=0;
		if(num==1){
			 total2=service.updateModifyTemporaryTable(id);
		}*/
		 // int toatl1=iservice.updateApprovalResults(id,num);
		  //int total4=service.add1(id);
		 // int total5=service.add2(id);
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
	}
	/**
	 * 
	 * @Title:AccountRefundFormServlet
	 * @Description:是否完成退款
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void refundCompleted(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName=null;
		String EmpPWD=null;
		Cookie c[]=request.getCookies();
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
		String id1= request.getParameter("id");
		
		int id=0;
		if(id1!=null&&!"".equals(id1)){
			id=Integer.parseInt(id1);
		}
		
		int total=service.updateRefundCompleted(id,EmpEName);
		int total2=service.updateModifyTemporaryTable(id);
		int toatl1=iservice.updateApprovalResults(id,0);
		int total4=service.add1(id);
		int total5=service.add2(id);
		if(total>0&&toatl1>0&&total2>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
	}
}
