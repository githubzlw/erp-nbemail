package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.INTERNAL;

import cerong.erp.entity.EmailUser;
import cerong.erp.entity.FactoryFund;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.entity.invoiceinfo;
import cerong.erp.service.AccountRefundFormServiceImpl;
import cerong.erp.service.CheckUpService;
import cerong.erp.service.CustomerService;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.FactoryFundService;
import cerong.erp.service.IAccountRefundFormService;
import cerong.erp.service.ICheckUpServiceImpl;
import cerong.erp.service.ICustomerServiceImpl;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IFactoryFundServiceImpl;
import cerong.erp.service.IinvoiceinfoServiceImpl;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;
import cerong.erp.service.invoiceinfoService;




public class CheckUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICheckUpServiceImpl service = new CheckUpService();
	ItCaseIdServiceImpl iservice = new ItCaseIdService();
	IFactoryFundServiceImpl fservice=new FactoryFundService();
	IinvoiceinfoServiceImpl inservice=new invoiceinfoService();
	ICustomerServiceImpl cservice = new CustomerService();
	IEmployeeServiceImpl eservice = new EmployeeService();
	IAccountRefundFormService aservice =new AccountRefundFormServiceImpl();
	private static final int PAGE_SIZE = 20;//每页显示的最大数量
	/**
	 * 方法描述:审批申请列表
	 * author:wy
	 * date:2016年4月20日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void CheckUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		HttpSession session1 = request.getSession();
		
		EmailUser user = (EmailUser)session1.getAttribute("user");
		if(user!=null||user1!=null){
			 String s1 = "edwardfan";
			 String s2 = "emmaxie";
			 String s3 = "jiangwenlong";
			 Boolean index1=false;
			 Boolean index2=false;
			 Boolean index3=false;
			 if(user!=null){
			  index1 = s1.toLowerCase().contains(user.getUserName().toLowerCase());
			 }
			 if(user!=null){
			  index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());
			 }
			 if(user!=null){
				 index3 = s3.toLowerCase().contains(user1.getUserName().toLowerCase());
			 }
			 int accountRefundForm =aservice.getAccountRefundForm();//获取需要审批数据
			 request.setAttribute("accountRefundForm", accountRefundForm);
			 if(index1 !=false){
		MoneyCheckUp cp =new MoneyCheckUp();
		String str = request.getParameter("vs");
		if(str != null && !"".equals(str)) {
			str = new String(str.getBytes("iso-8859-1"),"UTF-8");
		}
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
			if(condition.equals("1")) {//说明输入的是项目号
				try{
				cp.setCaseNo(str);
				}catch(Exception e) {
					request.getRequestDispatcher("jsp/CheckUp.jsp").forward(request, response);
					return;
				}
			}else if(condition.equals("2")) {
				cp.setProductDescE(str);
			}else if(condition.equals("3")) {
				
			cp.setFacMoney(Integer.parseInt(str));
			}else if(condition.equals("4")) {
			cp.setGeldObject(str);	
				
			}
			request.setAttribute("fyfy", condition);
		}
		String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
		int  number = -1;
		if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
		{
			number = Integer.parseInt(condition2);
			request.setAttribute("fyfz", condition2);
			
		}
		String condition3 = request.getParameter("condition3");//获取翻译状态下拉列表选项值
		int  number1 = -1;
		if(condition3 != null && !"".equals(condition3) && !"-1".equals(condition3))
		{
			number1 = Integer.parseInt(condition3);
			request.setAttribute("fyfx", condition3);
			
		}
		cp.setNum(number1);
		cp.setChecktype(number);
		//审批申请列表正在审批中总金额
		int total1=service.getTotal1(cp);
		//审批申请列表审批通过总金额
		int total2=service.getTotal2(cp);
		List <MoneyCheckUp> cusList =service.getAll(cp);
		int total=service.getTotal(cp);
		HttpSession session = request.getSession();
		session.setAttribute("total", total);
		//System.out.print(total);
		//System.out.print(cusList);
		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.setAttribute("total2", total2);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.getRequestDispatcher("jsp/CheckUp.jsp").forward(request, response);
	   }else if(index2 !=false){
				 MoneyCheckUp cp =new MoneyCheckUp();
					String str = request.getParameter("vs");
					if(str != null && !"".equals(str)) {
						str = new String(str.getBytes("iso-8859-1"),"UTF-8");
					}
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
						if(condition.equals("1")) {//说明输入的是项目号
							try{
							cp.setCaseNo(str);
							}catch(Exception e) {
								request.getRequestDispatcher("jsp/CheckUp2.jsp").forward(request, response);
								return;
							}
						}else if(condition.equals("2")) {
							cp.setProductDescE(str);
						}else if(condition.equals("3")) {
							
						cp.setFacMoney(Integer.parseInt(str));
						}else if(condition.equals("4")) {
						cp.setGeldObject(str);	
							
						}
						request.setAttribute("fyfy", condition);
					}
					String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
					int  number = -1;
					if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
					{
						number = Integer.parseInt(condition2);
						request.setAttribute("fyfz", condition2);
						
					}
					String condition3 = request.getParameter("condition3");//获取翻译状态下拉列表选项值
					int  number1 = -1;
					if(condition3 != null && !"".equals(condition3) && !"-1".equals(condition3))
					{
						number1 = Integer.parseInt(condition3);
						request.setAttribute("fyfx", condition3);
						
					}
					cp.setNum(number1);
					cp.setChecktype(number);
					//审批申请列表正在审批中总金额
					int total1=service.getTotal1(cp);
					//审批申请列表审批通过总金额
					int total2=service.getTotal2(cp);
					List <MoneyCheckUp> cusList =service.getAlla(cp);
					int total=service.getTotala(cp);
					request.setAttribute("total", total);
					//System.out.print(total);
					//System.out.print(cusList);
					request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total2", total2);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.getRequestDispatcher("jsp/CheckUp2.jsp").forward(request, response);	 
			 }else if(index3 !=false){
				 MoneyCheckUp cp =new MoneyCheckUp();
					String str = request.getParameter("vs");
					if(str != null && !"".equals(str)) {
						str = new String(str.getBytes("iso-8859-1"),"UTF-8");
					}
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
						if(condition.equals("1")) {//说明输入的是项目号
							try{
							cp.setCaseNo(str);
							}catch(Exception e) {
								request.getRequestDispatcher("jsp/CheckUp3.jsp").forward(request, response);
								return;
							}
						}else if(condition.equals("2")) {
							cp.setProductDescE(str);
						}else if(condition.equals("3")) {
							
						cp.setFacMoney(Integer.parseInt(str));
						}else if(condition.equals("4")) {
						cp.setGeldObject(str);	
							
						}
						request.setAttribute("fyfy", condition);
					}
					String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
					int  number = -1;
					if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
					{
						number = Integer.parseInt(condition2);
						request.setAttribute("fyfz", condition2);
						
					}
					String condition3 = request.getParameter("condition3");//获取翻译状态下拉列表选项值
					int  number1 = -1;
					if(condition3 != null && !"".equals(condition3) && !"-1".equals(condition3))
					{
						number1 = Integer.parseInt(condition3);
						request.setAttribute("fyfx", condition3);
						
					}
					cp.setNum(number1);
					cp.setChecktype(number);
					//审批申请列表正在审批中总金额
					int total1=service.getTotal1(cp);
					//审批申请列表审批通过总金额
					int total2=service.getTotal2(cp);
					List <MoneyCheckUp> cusList =service.getAllb(cp);
					int total=service.getTotalb(cp);
					
					request.setAttribute("total", total);
					//System.out.print(total);
					//System.out.print(cusList);
					request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total2", total2);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.getRequestDispatcher("jsp/CheckUp3.jsp").forward(request, response);	 
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
	 * 方法描述:审批未通过申请列表
	 * author:wy
	 * date:2016年4月20日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void CheckUp1(HttpServletRequest request, HttpServletResponse response)
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		
		EmailUser user = (EmailUser)session1.getAttribute("user");
		if(user!=null){
			 String s = "edwardfanemmaxie";
			 Boolean index1 = s.toLowerCase().contains(user.getUserName().toLowerCase());
			 if(index1 !=false){
		MoneyCheckUp cp =new MoneyCheckUp();
		String str = request.getParameter("vs");
		if(str != null && !"".equals(str)) {
			str = new String(str.getBytes("iso-8859-1"),"UTF-8");
		}
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
			if(condition.equals("1")) {//说明输入的是项目号
				try{
				cp.setCaseNo(str);
				}catch(Exception e) {
					request.getRequestDispatcher("jsp/CheckUp1.jsp").forward(request, response);
					return;
				}
			}else if(condition.equals("2")) {
				cp.setProductDescE(str);
			}else if(condition.equals("3")) {
				
			cp.setFacMoney(Integer.parseInt(str));
			}else if(condition.equals("4")) {
			cp.setGeldObject(str);	
				
			}
		}
		String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
		int  number = -1;
		if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
		{
			number = Integer.parseInt(condition2);
			request.setAttribute("fyfz", condition2);
			
		}
		
		cp.setChecktype(number);
		//审批申请列表未通过审批中总金额
		int total1=service.getTotal3(cp);
		
		List <MoneyCheckUp> cusList =service.getAll1(cp);
		int total=service.getTotal4(cp);
		HttpSession session = request.getSession();
		session.setAttribute("total", total);
		System.out.print(total);
		System.out.print(cusList);
		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
		//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.getRequestDispatcher("jsp/CheckUp1.jsp").forward(request, response);
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
	 * 方法描述:对正在审批中的项目进行催款
	 * author:wy
	 * date:2016年5月30日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void pressMoney(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		int total1=eservice.getUser(EmpPWD, EmpEName);
		if(user1!=null&& total1>0){
			String s1 = "edwardfanemmaxieninazhao";
			 Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			String s3 = "shiguojuanmandymanlisali";
			Boolean index3 = s3.toLowerCase().contains(user1.getUserName().toLowerCase());
		if(index1!=false){	
		MoneyCheckUp cp =new MoneyCheckUp();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		List <MoneyCheckUp> cusList =service.getAll2a(cp);
		String s2 = "edwardfanemmaxieninazhao";
		Boolean index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());

		if(index2!=false) {
			request.setAttribute("id", 1);
		}
		//Emma正在审批中总金额
		int total3=service.getEmTotal(cp);
		//Eddie审批通过总金额
	    int total2=service.getEdTotal(cp);
	     //最近3天在审批款项
	    int total4=service.getAllTotal(cp);
	    request.setAttribute("total1", total3);
	    request.setAttribute("total2", total2);
	    request.setAttribute("total3", total4);
	    
		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
		//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.getRequestDispatcher("jsp/press_for_money.jsp").forward(request, response);
		}else if(index3!=false){
			MoneyCheckUp cp =new MoneyCheckUp();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			//审批申请列表未通过审批中总金额
			cp.setMerchandManager1(EmpEName);
			List <MoneyCheckUp> cusList =service.getFastTrackProject(cp);
			request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
			//将客户信息保存（里面保存了客户的三个邮箱地址）
			request.setAttribute("id", 0);
			request.getRequestDispatcher("jsp/press_for_money.jsp").forward(request, response);

		}else{
			MoneyCheckUp cp =new MoneyCheckUp();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			//审批申请列表未通过审批中总金额
			cp.setMerchandManager1(EmpEName);
			List <MoneyCheckUp> cusList =service.getproject(cp);
			request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
			//将客户信息保存（里面保存了客户的三个邮箱地址）
			request.setAttribute("id", 0);
			request.getRequestDispatcher("jsp/press_for_money.jsp").forward(request, response);	
		}
		}
	
	}
	/**
	 * 方法描述:对正在审批中的项目进行催款
	 * author:wy
	 * date:2016年5月30日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void updateMoney(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    String caseNo=request.getParameter("caseNo");
	    int id=0;
	    if(caseNo!=null&&!"".equals(caseNo)){
	    	id=Integer.parseInt(caseNo);
	    }
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1 = sf.format(date1);
		int total=service.updateStateDate(id,time1);
		
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
		}

	
	
	/**
	 * 方法描述:审批全部项目
	 * author:wy
	 * date:2017年1月10日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void Marlboro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		int num=service.getnum1();
		if(num>0){
		int total=service.update(time);
		if(total>0){
			request.setAttribute("name", "审批全部项目通过!");
			request.getRequestDispatcher("jsp/Marlboro_right.jsp").forward(request, response);
		//response.sendRedirect("/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet");
		}else{
			request.setAttribute("name", "审批全部项目没通过!");
			request.getRequestDispatcher("jsp/Marlboro_right.jsp").forward(request, response);
			/*out.write("<script>");
			out.write("alert('审批全部项目没通过')");
			out.write("window.location='/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet'");
			out.write("</script>");	*/
		}
		}else{
			request.setAttribute("name", "暂无Emma审批项目!");
			request.getRequestDispatcher("jsp/Marlboro_right.jsp").forward(request, response);
			/*out.write("<script>");
			out.write("alert('暂无审批项目')");
			out.write("window.location='/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet'");
			out.write("</script>");*/
		}
		
	}
	/**
	 * 方法描述:审批1万以下全部项目
	 * author:wy
	 * date:2017年1月10日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void Marlboro1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int num=service.getnum();
		if(num>0){
		String time = sdf.format(date);
		int total=service.update1(time);
		if(total>0){
			request.setAttribute("name", "审批一万元以下项目通过!");
			request.getRequestDispatcher("jsp/Marlboro_right.jsp").forward(request, response);
		//response.sendRedirect("/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet");
		}else{
			request.setAttribute("name", "审批一万元以下项目没通过!");
			request.getRequestDispatcher("jsp/Marlboro_right.jsp").forward(request, response);
			/*out.write("<script>");
			out.write("alert('审批一万元以下项目没通过')");
			out.write("window.location='/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet'");
			out.write("</script>");*/	
		}
		}else{
			request.setAttribute("name", "暂无1万元以下Emma审批项目!");
			request.getRequestDispatcher("jsp/Marlboro_right.jsp").forward(request, response);
			/*out.write("<script>");
			out.write("alert('暂无1万元以下审批项目')");
			out.write("window.location='/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet'");
			out.write("</script>");*/
		}
	}
	/**
	 * 方法描述:Emma预审通过
	 * author:wy
	 * date:2017年3月16日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void preliminaryhearing(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id1 = request.getParameter("id");
		int id=0;
		if(id1 != null && !"".equals(id1)) {
			id= Integer.parseInt(id1);
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		int num=service.getState(id);
		int total=0;
		if(num>0){
			total=1;
					}else{
		int total2=service.add(id);
		//Emma预审项目
		   total=service.preliminaryhearing(id,time);
		 
		}
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
	   
			
		
	}
	/**
	 * 方法描述:Emma直接审批通过
	 * author:wy
	 * date:2017年3月16日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void directapproval(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id1 = request.getParameter("id");
		int id=0;
		if(id1 != null && !"".equals(id1)) {
			id= Integer.parseInt(id1);
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		int num=service.getState(id);
		int total=0;
		if(num>0){
			total=1;
					}else{
		int total2=service.add1(id);
		//Emma预审项目
			total=service.directapproval(id,time);
		}
		
		
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
		
		
		
	}
	/**
	 * 方法描述:采购副总直接审批通过
	 * author:wy
	 * date:2017年3月16日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void directapproval1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id1 = request.getParameter("id");
		int id=0;
		if(id1 != null && !"".equals(id1)) {
			id= Integer.parseInt(id1);
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		//Emma预审项目
		int total=service.directapproval1(id,time);
		if(total>0){
			request.setAttribute("name", "购副总直接审批通过!");
			request.getRequestDispatcher("jsp/preliminaryhearing_right1.jsp").forward(request, response);
		}else{
			
			request.setAttribute("name", "购副总直接审批失败!");
			request.getRequestDispatcher("jsp/preliminaryhearing_right1.jsp").forward(request, response);
		}
		
		
		
	}
	/**
	 * 方法描述:销售经理审批通过项目
	 * author:wy
	 * date:2017年3月22日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void salesmanagerapproval(HttpServletRequest request, HttpServletResponse response)
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		
		EmailUser user = (EmailUser)session1.getAttribute("user");
		if(user!=null){
			 String s = "edwardfan";
			 Boolean index1 = s.toLowerCase().contains(user.getUserName().toLowerCase());
			 if(index1 !=false){
		MoneyCheckUp cp =new MoneyCheckUp();
		String str = request.getParameter("vs");
		if(str != null && !"".equals(str)) {
			str = new String(str.getBytes("iso-8859-1"),"UTF-8");
		}
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
			if(condition.equals("1")) {//说明输入的是项目号
				try{
				cp.setCaseNo(str);
				}catch(Exception e) {
					request.getRequestDispatcher("jsp/CheckUp4.jsp").forward(request, response);
					return;
				}
			}else if(condition.equals("2")) {
				cp.setProductDescE(str);
			}else if(condition.equals("3")) {
				
			cp.setFacMoney(Integer.parseInt(str));
			}else if(condition.equals("4")) {
			cp.setGeldObject(str);	
				
			}
		}
		String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
		int  number = -1;
		if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
		{
			number = Integer.parseInt(condition2);
			request.setAttribute("fyfz", condition2);
			
		}
		
		cp.setChecktype(number);
		//审批申请列表未通过审批中总金额
		int total1=service.getTotal3(cp);
		
		List <MoneyCheckUp> cusList =service.getAllm(cp);
		int total=service.getTotalm(cp);
		//HttpSession session = request.getSession();
		request.setAttribute("total", total);
		//System.out.print(total);
		//System.out.print(cusList);
		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
		//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.getRequestDispatcher("jsp/CheckUp4.jsp").forward(request, response);
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
	 * 方法描述:销售经理查看合同详情
	 * author:wy
	 * date:2017年4月13日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void viewdetails(HttpServletRequest request, HttpServletResponse response)
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		
		EmailUser user = (EmailUser)session1.getAttribute("user");
		if(user!=null){
			String caseno=request.getParameter("caseno");
			ItemCase itemcase=iservice.getall(caseno);
			List<FactoryFund> factoryfund=fservice.getall(caseno);
			List<invoiceinfo> invoiceinfo=inservice.getall(caseno);
			invoiceinfo info=inservice.getall1(caseno);
			FactoryFund fund=fservice.getall1(caseno);
			Double remainder=0.00;
			remainder=info.getAllmoney()-info.getAllmoney1();
			DecimalFormat df = new DecimalFormat("#.##");
		     request.setAttribute("itemcase", itemcase);
			request.setAttribute("factoryfund", factoryfund);
			request.setAttribute("invoiceinfo", invoiceinfo);
			request.setAttribute("fund", fund);
			request.setAttribute("info", info);
			request.setAttribute("remainder", df.format(remainder));
				//将客户信息保存（里面保存了客户的三个邮箱地址）
			request.getRequestDispatcher("jsp/CheckUp5.jsp").forward(request, response);
			 
				
			
			
		}else{
			out.write("<script>");
			out.write("alert('登陆后再查询审批申请详情页面');");
			out.write("window.location.href='jsp/login-admin-2015.jsp'");
			out.write("</script>");	
			
		}
	}
	
	/**
	 * 方法描述:修改客户备注和项目备注
	 * author:wy
	 * date:2017年6月8日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void updatecustomerremarks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String customerremarks = request.getParameter("customerremarks");
		String projectId = request.getParameter("projectId");
		String caseNo=request.getParameter("id");
	    int id=0;
	    if(caseNo!=null&&!"".equals(caseNo)){
	    	id=Integer.parseInt(caseNo);
	    }
		String projectnote = request.getParameter("projectNote");
        int eid=iservice.geteid(projectId);
        if(customerremarks!=null&&!"".equals(customerremarks)){
        	customerremarks = new String(customerremarks.getBytes("iso-8859-1"),"utf-8");
        	int total=cservice.updateremarks(eid,customerremarks);
        }
        if(projectnote!=null&&!"".equals(projectnote)){
        	projectnote = new String(projectnote.getBytes("iso-8859-1"),"utf-8");
        	int total=iservice.updateprojectnote(projectId,projectnote);
        }
		String fastTrackReasons = request.getParameter("fastTrackReasons");
		if(fastTrackReasons!=null&&!"".equals(fastTrackReasons)){
			fastTrackReasons = new String(fastTrackReasons.getBytes("iso-8859-1"),"utf-8");
			int total=iservice.updateFastTrackReasons(id,fastTrackReasons);
		}
        int total2=service.updateApproval1(id);
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		int total1=eservice.getUser(EmpPWD, EmpEName);
		if(user1!=null&& total1>0){
			String s1 = "edwardfanemmaxieninazhao";
			 Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			
		if(index1!=false){	
		MoneyCheckUp cp =new MoneyCheckUp();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		//审批申请列表未通过审批中总金额
		//int total1=service.getTotal1(cp);
		List <MoneyCheckUp> cusList =service.getAll2a(cp);
		int total=service.getTotal5(cp);
		HttpSession session = request.getSession();
		session.setAttribute("total", total);
		System.out.print(total);
		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
		//request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
		//System.out.print(cusList);
		//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.getRequestDispatcher("jsp/press_for_money.jsp").forward(request, response);
		}else{
			MoneyCheckUp cp =new MoneyCheckUp();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			//审批申请列表未通过审批中总金额
			cp.setMerchandManager1(EmpEName);
			List <MoneyCheckUp> cusList =service.getproject(cp);
			int total=service.getprojecttotal(cp);
			
			request.setAttribute("total", total);
			
			request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
			//request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
			//System.out.print(cusList);
			//将客户信息保存（里面保存了客户的三个邮箱地址）
			request.getRequestDispatcher("jsp/press_for_money.jsp").forward(request, response);	
		}
		}
		
	}
	/**
	 * 
	 * @Title:CheckUpServlet
	 * @Description:老的请求付款信息
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	
	public void RequestPaymentInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		HttpSession session1 = request.getSession();
		
		EmailUser user = (EmailUser)session1.getAttribute("user");
		if(user!=null||user1!=null){
			 String s1 = "edwardfan";
			 String s2 = "emmaxie";
			 String s3 = "jiangwenlong";
			 Boolean index1=false;
			 Boolean index2=false;
			 Boolean index3=false;
			 if(user!=null){
			  index1 = s1.toLowerCase().contains(user.getUserName().toLowerCase());
			 }
			 if(user!=null){
			  index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());
			 }
			 if(user!=null){
				 index3 = s3.toLowerCase().contains(user1.getUserName().toLowerCase());
			 }
			 if(index1 !=false){
		MoneyCheckUp cp =new MoneyCheckUp();
		String str = request.getParameter("vs");
		if(str != null && !"".equals(str)) {
			str = new String(str.getBytes("iso-8859-1"),"UTF-8");
		}
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
			if(condition.equals("1")) {//说明输入的是项目号
				try{
				cp.setCaseNo(str);
				}catch(Exception e) {
					request.getRequestDispatcher("jsp/CheckUp.jsp").forward(request, response);
					return;
				}
			}else if(condition.equals("2")) {
				cp.setProductDescE(str);
			}else if(condition.equals("3")) {
				
			cp.setFacMoney(Integer.parseInt(str));
			}else if(condition.equals("4")) {
			cp.setGeldObject(str);	
				
			}
			request.setAttribute("fyfy", condition);
		}
		String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
		int  number = -1;
		if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
		{
			number = Integer.parseInt(condition2);
			request.setAttribute("fyfz", condition2);
			
		}
		String condition3 = request.getParameter("condition3");//获取翻译状态下拉列表选项值
		int  number1 = -1;
		if(condition3 != null && !"".equals(condition3) && !"-1".equals(condition3))
		{
			number1 = Integer.parseInt(condition3);
			request.setAttribute("fyfx", condition3);
			
		}
		cp.setNum(number1);
		cp.setChecktype(number);
		//审批申请列表正在审批中总金额
		int total1=service.getTotal1(cp);
		//审批申请列表审批通过总金额
		int total2=service.getTotal2(cp);
		List <MoneyCheckUp> cusList =service.getAllx(cp);
		int total=service.getTotal(cp);
		HttpSession session = request.getSession();
		session.setAttribute("total", total);
		//System.out.print(total);
		//System.out.print(cusList);
		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.setAttribute("total2", total2);//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.getRequestDispatcher("jsp/CheckUp.jsp").forward(request, response);
	   }else if(index2 !=false){
				 MoneyCheckUp cp =new MoneyCheckUp();
					String str = request.getParameter("vs");
					if(str != null && !"".equals(str)) {
						str = new String(str.getBytes("iso-8859-1"),"UTF-8");
					}
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
						if(condition.equals("1")) {//说明输入的是项目号
							try{
							cp.setCaseNo(str);
							}catch(Exception e) {
								request.getRequestDispatcher("jsp/CheckUp2.jsp").forward(request, response);
								return;
							}
						}else if(condition.equals("2")) {
							cp.setProductDescE(str);
						}else if(condition.equals("3")) {
							
						cp.setFacMoney(Integer.parseInt(str));
						}else if(condition.equals("4")) {
						cp.setGeldObject(str);	
							
						}
						request.setAttribute("fyfy", condition);
					}
					String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
					int  number = -1;
					if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
					{
						number = Integer.parseInt(condition2);
						request.setAttribute("fyfz", condition2);
						
					}
					String condition3 = request.getParameter("condition3");//获取翻译状态下拉列表选项值
					int  number1 = -1;
					if(condition3 != null && !"".equals(condition3) && !"-1".equals(condition3))
					{
						number1 = Integer.parseInt(condition3);
						request.setAttribute("fyfx", condition3);
						
					}
					cp.setNum(number1);
					cp.setChecktype(number);
					//审批申请列表正在审批中总金额
					int total1=service.getTotal1(cp);
					//审批申请列表审批通过总金额
					int total2=service.getTotal2(cp);
					List <MoneyCheckUp> cusList =service.getAlly(cp);
					int total=service.getTotala(cp);
					request.setAttribute("total", total);
					//System.out.print(total);
					//System.out.print(cusList);
					request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total2", total2);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.getRequestDispatcher("jsp/CheckUp2.jsp").forward(request, response);	 
			 }else if(index3 !=false){
				 MoneyCheckUp cp =new MoneyCheckUp();
					String str = request.getParameter("vs");
					if(str != null && !"".equals(str)) {
						str = new String(str.getBytes("iso-8859-1"),"UTF-8");
					}
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
						if(condition.equals("1")) {//说明输入的是项目号
							try{
							cp.setCaseNo(str);
							}catch(Exception e) {
								request.getRequestDispatcher("jsp/CheckUp3.jsp").forward(request, response);
								return;
							}
						}else if(condition.equals("2")) {
							cp.setProductDescE(str);
						}else if(condition.equals("3")) {
							
						cp.setFacMoney(Integer.parseInt(str));
						}else if(condition.equals("4")) {
						cp.setGeldObject(str);	
							
						}
						request.setAttribute("fyfy", condition);
					}
					String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
					int  number = -1;
					if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
					{
						number = Integer.parseInt(condition2);
						request.setAttribute("fyfz", condition2);
						
					}
					String condition3 = request.getParameter("condition3");//获取翻译状态下拉列表选项值
					int  number1 = -1;
					if(condition3 != null && !"".equals(condition3) && !"-1".equals(condition3))
					{
						number1 = Integer.parseInt(condition3);
						request.setAttribute("fyfx", condition3);
						
					}
					cp.setNum(number1);
					cp.setChecktype(number);
					//审批申请列表正在审批中总金额
					int total1=service.getTotal1(cp);
					//审批申请列表审批通过总金额
					int total2=service.getTotal2(cp);
					List <MoneyCheckUp> cusList =service.getAllz(cp);
					int total=service.getTotalb(cp);
					
					request.setAttribute("total", total);
					//System.out.print(total);
					//System.out.print(cusList);
					request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total1", total1);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.setAttribute("total2", total2);//将客户信息保存（里面保存了客户的三个邮箱地址）
					request.getRequestDispatcher("jsp/CheckUp3.jsp").forward(request, response);	 
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
	 * 
	 * @Title:CheckUpServlet
	 * @Description:emma添加审批不通过理由
	 * 
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void PassTheReason(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id1 = request.getParameter("id");
		String projecttypes = request.getParameter("projecttypes");
		int id=0;
		if(id1 != null && !"".equals(id1)) {
			id= Integer.parseInt(id1);
		}
		if(projecttypes != null && !"".equals(projecttypes)) {
			projecttypes = new String(projecttypes.getBytes("iso-8859-1"),"UTF-8");
		}
		if(projecttypes==null){
			projecttypes="需要更多信息";
		}
		int total=service.updateApproval(id,projecttypes);
		if(total>0)
		{
			out.print("YES");
		}else{
			out.print("NO");
		}
	}

	/**
	 * 方法描述:对正在审批中的项目进行催款
	 * author:wy
	 * date:2019年5月20日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void updateWithDraw(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id1=request.getParameter("id");
		int id=0;
		if(id1!=null&&!"".equals(id1)){
			id=Integer.parseInt(id1);
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
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		Date date1 = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1 = sf.format(date1);
		int total=service.updateWithDraw(id,time1,user1);
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
	}

	public void updatecustomerremarks1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String customerremarks = request.getParameter("customerremarks");
		String projectId = request.getParameter("projectId");
		String caseNo=request.getParameter("id");
		int id=0;
		if(caseNo!=null&&!"".equals(caseNo)){
			id=Integer.parseInt(caseNo);
		}
		String projectnote = request.getParameter("projectNote");
		int eid=iservice.geteid(projectId);
		if(customerremarks!=null&&!"".equals(customerremarks)){
			customerremarks = new String(customerremarks.getBytes("iso-8859-1"),"utf-8");
			int total=cservice.updateremarks(eid,customerremarks);
		}
		if(projectnote!=null&&!"".equals(projectnote)){
			projectnote = new String(projectnote.getBytes("iso-8859-1"),"utf-8");
			int total=iservice.updateprojectnote(projectId,projectnote);
		}
		String fastTrackReasons = request.getParameter("fastTrackReasons");
		if(fastTrackReasons!=null&&!"".equals(fastTrackReasons)){
			fastTrackReasons = new String(fastTrackReasons.getBytes("iso-8859-1"),"utf-8");
			int total=iservice.updateFastTrackReasons(id,fastTrackReasons);
		}
		int total2=service.updateApproval1(id);
		if(total2>0){
			out.print("YES");
		}else{
			out.print("NO");
		}



	}
}
