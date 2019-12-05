package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cerong.erp.entity.CustomerReconciliationSystem;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.service.CustomerReconciliationSystemServiceImpl;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.ICustomerReconciliationSystemService;
import cerong.erp.service.IEmployeeServiceImpl;



public class CustomerReconciliationSystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeServiceImpl eservice = new EmployeeService();
       ICustomerReconciliationSystemService service=new CustomerReconciliationSystemServiceImpl();
       /**
   	 * 方法描述:对于每个客户 做一个对账系统
   	 * author:wy
   	 * date:2017年8月31日
   	 * @param request
   	 * @param response
   	 * @throws ServletException
   	 * @throws IOException
   	 */
   	
   	public void customerReconciliation(HttpServletRequest request, HttpServletResponse response)
   			throws ServletException, IOException {
   		PrintWriter out = response.getWriter();
   		HttpSession session1 = request.getSession();
   		String EmpEName=null;
   		String EmpPWD=null;
   		Cookie c[]=request.getCookies();
   		if(c!=null)
   		{
   		for(int x=0;x<c.length;x++){
   		if(c[x].getName().equals("SESSION_LOGIN_PASSWORD")){
   					//将cookie值取出来后，赋值给request，用以之后的显示
   			EmpPWD=c[x].getValue();
   					 //request.setAttribute("pwd",c[x].getValue());
   					}	
   			
   		if(c[x].getName().equals("SESSION_LOGIN_USERNAME"))
   		{
   		//在cookie值保存时如果编码了，取cookie时就需要进行解码。
   		//将cookie值取出来后，赋值给变量，用以之后的显示
   			EmpEName=c[x].getValue();
   			//request.setAttribute("userName",c[x].getValue()); 
   		}
   	   } 
   		}
   		EmailUser user=new EmailUser();
   		user.setUserName(EmpEName);
   		user.setPwd(EmpPWD);
   		int num =eservice.getUser(EmpPWD, EmpEName);
   		if(num>0){
   			 String s = "edwardfanemmaxiecashier";
   			 Boolean index1 = s.toLowerCase().contains(user.getUserName().toLowerCase());
   			 if(index1 !=false){
   	   CustomerReconciliationSystem cr=new CustomerReconciliationSystem();
   		String str = request.getParameter("vs");
   		if(str != null && !"".equals(str)) {
   			str = new String(str.getBytes("iso-8859-1"),"UTF-8");
   		}
   		
   		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
   		if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
   			if(condition.equals("1")) {//说明输入的是项目号
   				try{
   					int id=0;
   					if(str != null && !"".equals(str)) {
   						id= Integer.parseInt(str);
   					}
   					cr.setCid(id);
   				}catch(Exception e) {
   					request.getRequestDispatcher("jsp/CheckUp1.jsp").forward(request, response);
   					return;
   				}
   			}else if(condition.equals("2")) {
   				cr.setName(str);
   			}
   		}
   		
   		List <CustomerReconciliationSystem> cusList =service.getAll1(cr);
   		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
   		//将客户信息保存（里面保存了客户的三个邮箱地址）
   		request.getRequestDispatcher("jsp/customer_reconciliation_system.jsp").forward(request, response);
   			 }else{
   					out.write("<script>");
   					out.write("alert('对不起你没有权限查看审批申请列表页面');");
   					out.write("window.location.href='jsp/login-admin-2015.jsp'");
   					out.write("</script>"); 
   				 
   			 }
   	
   	}else{
   		out.write("<script>");
   		out.write("alert('登陆后再查询审批申请列表页面');");
   		out.write("window.location.href='jsp/login-admin-2015.jsp'");
   		out.write("</script>");	
   		
   	}
   	}
   	
    /**
   	 * 方法描述:查看未到账款项
   	 * author:wy
   	 * date:2017年8月31日
   	 * @param request
   	 * @param response
   	 * @throws ServletException
   	 * @throws IOException
   	 */
	public void lookOutstandingFunds(HttpServletRequest request, HttpServletResponse response)
   			throws ServletException, IOException {
   		PrintWriter out = response.getWriter();
   		HttpSession session1 = request.getSession();
   		String str=request.getParameter("cid");
   		int id=0;
			if(str != null && !"".equals(str)) {
				id= Integer.parseInt(str);
			}
   		String EmpEName=null;
   		String EmpPWD=null;
   		Cookie c[]=request.getCookies();
   		if(c!=null)
   		{
   		for(int x=0;x<c.length;x++){
   		if(c[x].getName().equals("SESSION_LOGIN_PASSWORD")){
   					//将cookie值取出来后，赋值给request，用以之后的显示
   			EmpPWD=c[x].getValue();
   					 //request.setAttribute("pwd",c[x].getValue());
   					}	
   			
   		if(c[x].getName().equals("SESSION_LOGIN_USERNAME"))
   		{
   		//在cookie值保存时如果编码了，取cookie时就需要进行解码。
   		//将cookie值取出来后，赋值给变量，用以之后的显示
   			EmpEName=c[x].getValue();
   			//request.setAttribute("userName",c[x].getValue()); 
   		}
   	   } 
   		}
   		EmailUser user=new EmailUser();
   		user.setUserName(EmpEName);
   		user.setPwd(EmpPWD);
   		int num =eservice.getUser(EmpPWD, EmpEName);
   		if(num>0){
   			 String s = "edwardfanemmaxiecashier";
   			 Boolean index1 = s.toLowerCase().contains(user.getUserName().toLowerCase());
   			 if(index1 !=false){
   	   CustomerReconciliationSystem cr=new CustomerReconciliationSystem();
   		cr.setCid(id);
   		
   		List <CustomerReconciliationSystem> cusList =service.getLookOutstandingFunds(cr);
   		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
   		//将客户信息保存（里面保存了客户的三个邮箱地址）
   		request.getRequestDispatcher("jsp/customer_reconciliation_system1.jsp").forward(request, response);
   			 }else{
   					out.write("<script>");
   					out.write("alert('对不起你没有权限查看审批申请列表页面');");
   					out.write("window.location.href='jsp/login-admin-2015.jsp'");
   					out.write("</script>"); 
   				 
   			 }
   	
   	}else{
   		out.write("<script>");
   		out.write("alert('登陆后再查询审批申请列表页面');");
   		out.write("window.location.href='jsp/login-admin-2015.jsp'");
   		out.write("</script>");	
   		
   	}
   	}
}
