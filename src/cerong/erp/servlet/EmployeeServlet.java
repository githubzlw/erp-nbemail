package cerong.erp.servlet;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.service.*;
import com.sun.org.apache.bcel.internal.generic.I2F;







import cerong.erp.entity.EmailUser;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeServiceImpl service = new EmployeeService();
	IEmailUserService uservice = new EmailUserServiceImpl();
	ICheckUpServiceImpl cservice = new CheckUpService();
	public void updateLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	   
		HttpSession session = request.getSession(); 
		session.invalidate();
		request.getRequestDispatcher("jsp/login-admin-2015.jsp").forward(request, response);	
	}
	/**
	 * 方法描述:修改员工状态
	 * author:wy
	 * date:2016年4月15日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			int userId = 1;
			if(id != null && !"".equals(id)) {
				userId = Integer.parseInt(id);
			}
			String userName1 = request.getParameter("userName");
			String userName = null;
			if(userName1 != null && !"".equals(userName1)) {
				userName = userName1;
			}
			String roleNo1 = request.getParameter("roleNo");
			String dimission1 = request.getParameter("dimission");
			int dimission=0;
			if(dimission1 != null && !"".equals(dimission1)) {
				dimission= Integer.parseInt(dimission1);
			}
			String userName2 = request.getParameter("userName1");
			String pwd = request.getParameter("pwd");
			int roleNo=0;
			if(roleNo1 != null && !"".equals(roleNo1)) {
				roleNo= Integer.parseInt(roleNo1);
			}
			EmailUser user = new EmailUser();
			user.setRoleNo(roleNo);
			user.setUserName(userName2);
			user.setPwd(pwd);
			if(roleNo1!=null){
			
			
			if(dimission==0){
			int result=service.updateDel(userName);
			if(result > 0 ) {//说明删除成功
				out.write("<script>");
				out.write("已修改为离职员工");
				out.write("</script>");
			}else {//删除失败
				out.write("<script>");
				out.write("修改失败或已是离职员工");
				out.write("</script>");
			}
			}else{
				
				
				int result=service.getBack(userName);
				if(result > 0 ) {//说明删除成功
					out.write("<script>");
					out.write("将离职员工改为在职员工");
					out.write("</script>");
				}else {//删除失败
					out.write("<script>");
					out.write("修改失败");
					out.write("</script>");
				}
			
			
			}
			
			}else{
				out.write("<script>");
				out.write("请登陆后在修改用户状态");
				out.write("</script>");
				
			}
		}
	/**
	 * 方法描述:删除用户
	 * author:wy
	 * date:2016年1月5日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName1 = request.getParameter("userName");
		String userName = null;
		if(userName1 != null && !"".equals(userName1)) {
			userName = userName1;
		}
		String roleNo1 = request.getParameter("roleNo");
		String userName2 = request.getParameter("userName1");
		String pwd = request.getParameter("pwd");
		int roleNo=0;
		if(roleNo1 != null && !"".equals(roleNo1)) {
			roleNo= Integer.parseInt(roleNo1);
		}
		EmailUser user = new EmailUser();
		user.setRoleNo(roleNo);
		user.setUserName(userName2);
		user.setPwd(pwd);
		if(roleNo1!=null){
		
		//删除ERP中的用户
		int del = service.del(userName);
		if(del >0 ) {//说明删除成功
			out.write("<script>");
			out.write("删除用户成功");
			out.write("</script>");
		}else {//删除失败
			out.write("<script>");
			out.write("删除用户失败");
			out.write("</script>");
		}
		
		}else{
			out.write("<script>");
			out.write("请登陆后在删除用户状态");
			out.write("</script>");
			
		}
	}

	/**
	 * 方法描述:更改角色
	 * author:wy
	 * date:2016年4月15日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		/*String str = request.getParameter("id");//获取人员id
		int id = 0;
		if(str != null && !"".equals(str)) {
			id = Integer.parseInt(str);
		}*/
		String email = request.getParameter("email");//获取邮箱地址
		String pwd = request.getParameter("pwd");//获取邮箱密码
		String str = request.getParameter("userName");//获取人员id
		String userName = null;
		if(str != null && !"".equals(str)) {
			userName = str;
		}
		String role = request.getParameter("roleNo");//获取角色
		int roleNo = 0;
		if(role != null && !"".equals(role)) {
			roleNo = Integer.parseInt(role);
		}
		
		String roleNo2 = request.getParameter("roleNo1");
		String userName2 = request.getParameter("userName1");
		String pwd2 = request.getParameter("pwd1");
		int roleNo1=0;
		if(roleNo2 != null && !"".equals(roleNo2)) {
			roleNo1= Integer.parseInt(roleNo2);
		}
		EmailUser user1 = new EmailUser();
		user1.setRoleNo(roleNo1);
		user1.setUserName(userName2);
		user1.setPwd(pwd2);
		
		if(roleNo2!=null){
		
		
		EmailUser user = new EmailUser();
		user.setEmailAddress(email);
		user.setEmailPWD(pwd);
		user.setRoleNo(roleNo);
		if(roleNo==8){
			 int qualification=4;	
			 user.setQualification(qualification);	
			}else if(roleNo==4){
				 int qualification=3;	
				 user.setQualification(qualification);		
				
			}else if(roleNo==3||roleNo==5){
				 int qualification=1;	
				 user.setQualification(qualification);		
				
			}else if(roleNo==6){
				 int qualification=4;	
				 user.setQualification(qualification);		
				
			}else{
				int qualification=111;	
				 user.setQualification(qualification);
			}
		if(roleNo==1){
			String job="翻译";
			user.setJob(job);
		}else if(roleNo==2){
			String job="数据录入";
			user.setJob(job);
			
		}else if(roleNo==3){
			String job="老外销售";
			user.setJob(job);
			
		}else if(roleNo==4){
			String job="跟单销售";
			user.setJob(job);
			
		}else if(roleNo==5){
			String job="其他销售";
			user.setJob(job);
			
		}else if(roleNo==6){
			String job="采购";
			user.setJob(job);
			
		}else if(roleNo==7){
			String job="物流";
			user.setJob(job);
			
		}else if(roleNo==8){
			String job="质检";
			user.setJob(job);	
		}
		
		
		int result=service.updateRole(user,userName);
		if(result > 0) {//说明修改成功
			out.write("YES");
		}else {//修改失败
			out.write("NO");
		}
	
		}else{
			out.write("<script>");
			out.write("请登陆后在修改用户角色");
			out.write("</script>");
			
		}
	}
	/**
	 * 方法描述:添加用户
	 * author:wy
	 * date:2016年4月18日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		/*if(userName != null && !"".equals(userName)) {
			userName = new String(userName.getBytes("iso-8859-1"),"UTF-8");
		}*/
		String pwd = request.getParameter("pwd");
		String trueName = request.getParameter("trueName");
		if(trueName != null && !"".equals(trueName)) {
			trueName = new String(trueName.getBytes("ISO-8859-1"),"gbk");
		}
		String emailAddress = request.getParameter("emailAddress");
		String emailPWD = request.getParameter("emailPWD");
		String roleName = request.getParameter("role");
		int roleNo = 0;
		if(roleName != null && !"".equals(roleName)) {
			roleNo = Integer.parseInt(roleName);
		}
		String qualification1 = request.getParameter("qualification");
		int qualification = 0;
		if(qualification1 != null && !"".equals(qualification1)) {
			qualification = Integer.parseInt(qualification1);
		}
		String job=null;
		if(roleNo==1){
			 job="翻译";
			
		}else if(roleNo==2){
		 job="数据录入";
			
			
		}else if(roleNo==3){
		 job="老外销售";
			
			
		}else if(roleNo==4){
			 job="跟单销售";
			
			
		}else if(roleNo==5){
			 job="其他销售";
			
			
		}else if(roleNo==6){
			 job="采购";
			
			
		}else if(roleNo==7){
			 job="物流";
			
			
		}else if(roleNo==8){
			 job="质检";
			
			
		}
		/*String job1 = request.getParameter("job");
		String job = null;
		if(job1 != null && !"".equals(job1)) {
			job = new String(job1.getBytes("iso-8859-1"),"UTF-8");
		}*/
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time=format.format(date);					
		EmailUser user = new EmailUser(userName, pwd, emailAddress);
		String roleNo2 = request.getParameter("roleNo1");
		String userName2 = request.getParameter("userName1");
		String pwd2 = request.getParameter("pwd1");
		int roleNo1=0;
		if(roleNo2 != null && !"".equals(roleNo2)) {
			roleNo1= Integer.parseInt(roleNo2);
		}
		EmailUser user1 = new EmailUser();
		user1.setRoleNo(roleNo1);
		user1.setUserName(userName2);
		user1.setPwd(pwd2);
		
		if(roleNo2!=null){
		
		
		
		if(roleNo==3||roleNo==4||roleNo==5){
			  int flag1 =1;
			  user.setFlag(flag1);
			}
		user.setEmailPWD(emailPWD);
		user.setPwd(pwd);
		user.setUserName(userName);
		user.setTrueName(trueName);
		user.setWorktime(time);
	    user.setQualification(qualification);
		user.setJob(job);
		user.setWorktime(time);
		int b=service.addUser(user);
		if(b > 0 ) {//注册成功
			out.write("<script>");
			out.write("录入成功");
			out.write("</script>");
		}else {//注册失败
			out.write("<script>");
			out.write("录入失败");
			out.write("</script>");
		}
		}else{
			out.write("<script>");
			out.write("请登陆后在添加用户");
			out.write("</script>");
			
		}
		
	}
	/**
	 * 方法描述:管理员登录
	 * author:wy
	 * date:2016年4月21日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void Adminlogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//获取登录的用户名和密码
		String EmpPWD = request.getParameter("EmpPWD");
		String EmpEName = request.getParameter("EmpEName");
		EmailUser user1=new EmailUser();
		user1.setEmailPWD(EmpPWD);
		user1.setUserName(EmpEName);
		int total=service.getUser(EmpPWD,EmpEName);
		EmailUser user2=service.search(user1);
		if(user2!=null&&total>0){
			String s = "edwardfanemmaxiejiangwenlong";
			 Boolean index1 = s.toLowerCase().contains(EmpEName.toLowerCase());
		if(index1!=false){	
			EmailUser user=new EmailUser();
			user.setUserName(EmpEName);
			user.setPwd(EmpPWD);
			String host = request.getServerName(); 
			Cookie cookie = new Cookie("SESSION_LOGIN_USERNAME", user2.getUserName()); // 保存用户名到Cookie 
			cookie.setPath("/"); 
			cookie.setDomain(host); 
			cookie.setMaxAge(60*60*24*7*1000); 
			response.addCookie(cookie); 
			cookie = new Cookie("SESSION_LOGIN_PASSWORD", user2.getEmailPWD()); 
			cookie.setPath("/"); 
			cookie.setDomain(host); 
			cookie.setMaxAge(60*60*24*7*1000); 
			response.addCookie(cookie); 
			session.setAttribute("user", user);
			
			request.setAttribute("EmpEName", user2.getUserName());
			
			request.getRequestDispatcher("jsp/administrator_permission_page.jsp").forward(request, response);
		//response.sendRedirect("/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet");
		//request.getRequestDispatcher("/ERP-NBEmail/helpServlet?action=CheckUp&className=CheckUpServlet").forward(request, response);
		}else{
			session.setAttribute("EmpEName", EmpEName);
			out.write("<script>");
			out.write("alert('对不起，你没有权限查看审批申请列表页面');");
			out.write("window.location.href='jsp/login-admin-2015.jsp'");
			out.write("</script>");
			
		}		
		}else{
			session.setAttribute("EmpEName", EmpEName);
			out.write("<script>");
			out.write("alert('登录失败,用户名或密码错误!!');");
			out.write("window.location.href='jsp/login-admin-2015.jsp'");
			out.write("</script>");	
		}
			
	}	
	/**
	 * 方法描述:创建项目登录
	 * author:wy
	 * date:2016年5月16日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//获取登录的用户名和密码
		String EmpPWD = request.getParameter("EmpPWD");
		String EmpEName = request.getParameter("EmpEName");
		
		EmailUser user1=new EmailUser();
		user1.setEmailPWD(EmpPWD);
		user1.setUserName(EmpEName);
		int total=service.getUser(EmpPWD,EmpEName);
		EmailUser user2=service.search(user1);
		if(user2!=null&&total>0){
			String host = request.getServerName(); 
			Cookie cookie = new Cookie("SESSION_LOGIN_USERNAME", user2.getUserName()); // 保存用户名到Cookie 
			cookie.setPath("/"); 
			cookie.setDomain(host); 
			cookie.setMaxAge(60*60*24*7*100); 
			response.addCookie(cookie); 
			cookie = new Cookie("SESSION_LOGIN_PASSWORD", EmpPWD); 
			cookie.setPath("/"); 
			cookie.setDomain(host); 
			cookie.setMaxAge(60*60*24*7*100); 
			response.addCookie(cookie);
			
			
			EmailUser user=new EmailUser();
			user.setUserName(EmpEName);
			user.setPwd(EmpPWD);
			session.setAttribute("user", user2);
			request.setAttribute("EmpEName", user2.getUserName());
			String s1="mandymanlisalishiguojuan";
			boolean index1 = s1.toLowerCase().contains(user.getUserName().toLowerCase());
			String notes = "";
			if(index1!=false) {

				String project = "";
				int num = 0;
				List<MoneyCheckUp> list = cservice.getProjectVeto();//获取否决项目数据
				for (int i = 0; i < list.size(); i++) {
					num++;
					project += ";" + list.get(i).getCaseNo();
				}
				if (num > 0) {
					project = project.replaceFirst(";", "");
					notes = "最近1个月存在" + num + "个快速通道付款项目被Ed否决<br/>项目列表如下:" + project + "<br/>请面谈项目组成员";

				}
			}
			request.setAttribute("notes", notes);
			request.getRequestDispatcher("jsp/middle_page.jsp").forward(request, response);

		}else{
			session.setAttribute("EmpEName", EmpEName);
			out.write("<script>");
			out.write("alert('登录失败,用户名或密码错误!!');");
			out.write("window.location.href='jsp/login.jsp'");
			out.write("</script>");	
		}
			
	}	
	/**
	 * 方法描述:注册用户
	 * author:wy
	 * date:2016年5月31日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void register1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = request.getParameter("EmpEName");
		
		String EmpPWD = request.getParameter("EmpPWD");
		 
		if(EmpEName != null && !"".equals(EmpEName)) {
			EmpEName = new String(EmpEName.getBytes("ISO-8859-1"),"gbk");
		}
		String trueName =EmpEName;
		String roleName = request.getParameter("role");
		int roleNo = 0;
		if(roleName != null && !"".equals(roleName)) {
			roleNo = Integer.parseInt(roleName);
		}
		int qualification =0;
		String job=null;
		if(roleNo==1){
			 job="销售";
			 qualification=1;
			
		}else if(roleNo==2){
		 job="跟单销售";
		 qualification=3;	
			
		}else if(roleNo==3){
		 job="质量";
		 qualification=4;	
			
		}else if(roleNo==4){
			 job="采购";
			 qualification=4;
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time=format.format(date);					
		EmailUser user = new EmailUser(EmpEName, EmpPWD);
		
		
		user.setPwd(EmpPWD);
		user.setUserName(EmpEName);
		user.setTrueName(trueName);
		user.setWorktime(time);
	    user.setQualification(qualification);
		user.setJob(job);
		
		
		
		
		int b=service.addUser1(user);
		boolean total=service.createUser(EmpEName);
		if(b > 0 ) {//注册成功
			out.write("<script>");
			out.write("alert('录入成功');");
			//out.write("window.location.href='jsp/register.jsp'");
			out.write("</script>");
		}else {//注册失败
			out.write("<script>");
			out.write("alert('录入失败');");
			//out.write("window.location.href='jsp/register.jsp'");
			out.write("</script>");
		}
		}
	
	/**
	 * 方法描述:注册用户
	 * author:wy
	 * date:2016年10月14日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void registererp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = request.getParameter("EmpEName");
		
		String EmpPWD = request.getParameter("EmpPWD");
		 
		if(EmpEName != null && !"".equals(EmpEName)) {
			EmpEName = new String(EmpEName.getBytes("ISO-8859-1"),"gbk");
		}
		EmpEName=EmpEName.trim();
		EmpPWD=EmpPWD.trim();
		String trueName =EmpEName;
		String roleName = request.getParameter("role");
		int roleNo = 0;
		if(roleName != null && !"".equals(roleName)) {
			roleNo = Integer.parseInt(roleName);
		}
		int qualification =0;
		String job=null;
		if(roleNo==1){
			 job="销售";
			 qualification=1;
			
		}else if(roleNo==2){
		 job="跟单销售";
		 qualification=3;	
			
		}else if(roleNo==3){
		 job="质检";
		 qualification=4;	
			
		}else if(roleNo==4){
			 job="采购";
			 qualification=4;
			
		
	}else if(roleNo==5){
		job="报价员";
		qualification=3;
		
	}else if(roleNo==6){
		job="技术人员";
		qualification=22;
		
	}
		String email=request.getParameter("email");
		String emailpwd=request.getParameter("emailpwd");
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time=format.format(date);					
		EmailUser user = new EmailUser(EmpEName, EmpPWD);
		emailpwd=URLEncoder.encode(emailpwd, "UTF-8");
		
		user.setPwd(EmpPWD);
		user.setUserName(EmpEName);
		user.setTrueName(trueName);
		user.setWorktime(time);
	    user.setQualification(qualification);
		user.setJob(job);
		int b=service.addUser1(user);
		if(qualification!=22){
		boolean total=service.createUser(EmpEName);
		 PrintWriter out1 = null;
	        BufferedReader in1 = null;
	        String result2 = "";
	        try {
	        	
	            URL realUrl1 = new URL("http://117.144.21.74:43900/NBEmail/helpServlet?action=adduser&className=EmailUserServlet");
	            // 打开和URL之间的连接
	            URLConnection conn1 = realUrl1.openConnection();
	            // 设置通用的请求属性
	           // conn1.setRequestProperty("accept", "*/*");
			 conn1.setRequestProperty("connection", "Keep-Alive");
	            conn1.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 发送POST请求必须设置如下两行
	            conn1.setDoOutput(true);
	            conn1.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out1 = new PrintWriter(conn1.getOutputStream());
	            // 发送请求参数
	            out1.print("&username="+EmpEName+"&pwd="+EmpPWD+"&emailpwd="+emailpwd+"&email="+email+"&role="+roleNo);
	            // flush输出流的缓冲
	            out1.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in1 = new BufferedReader(
	                    new InputStreamReader(conn1.getInputStream()));
	            String line;
	            while ((line = in1.readLine()) != null) {
	                result2 += line;
	            }
	        } catch (Exception e) {
	            //System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out1!=null){
	                    out1.close();
	                }
	                if(in1!=null){
	                    in1.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
      result2 = new String(result2.getBytes("gbk"),"utf-8");
		
 	 PrintWriter out2 = null;
     BufferedReader in2 = null;
     String result3 = "";
     try {
     	
         URL realUrl2 = new URL("http://140.82.48.255:8080/USA-NBEmail/helpServlet?action=adduser&className=EmailUserServlet");
         // 打开和URL之间的连接
         URLConnection conn2 = realUrl2.openConnection();
         // 设置通用的请求属性
        // conn1.setRequestProperty("accept", "*/*");
		 conn2.setRequestProperty("connection", "Keep-Alive");
         conn2.setRequestProperty("user-agent",
                 "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         // 发送POST请求必须设置如下两行
         conn2.setDoOutput(true);
         conn2.setDoInput(true);
         // 获取URLConnection对象对应的输出流
         out2 = new PrintWriter(conn2.getOutputStream());
         // 发送请求参数
         out2.print("&username="+EmpEName+"&pwd="+EmpPWD+"&emailpwd="+emailpwd+"&email="+email+"&role="+roleNo);
         // flush输出流的缓冲
         out2.flush();
         // 定义BufferedReader输入流来读取URL的响应
         in2 = new BufferedReader(
                 new InputStreamReader(conn2.getInputStream()));
         String line2;
         while ((line2 = in2.readLine()) != null) {
             result3 += line2;
         }
     } catch (Exception e) {
         //System.out.println("发送 POST 请求出现异常！"+e);
         e.printStackTrace();
     }
     //使用finally块来关闭输出流、输入流
     finally{
         try{
             if(out2!=null){
                 out2.close();
             }
             if(in2!=null){
                 in2.close();
             }
         }
         catch(IOException ex){
             ex.printStackTrace();
         }
     }
		}
		if(b > 0 ) {//注册成功
			request.setAttribute("name", "录入ERP和NBEmail用户成功");
			request.getRequestDispatcher("jsp/employee_right.jsp").forward(request, response);
		}else {//注册失败
			request.setAttribute("name", "录入ERP和NBEmail用户失败!");
			request.getRequestDispatcher("jsp/employee_right.jsp").forward(request, response);
		}
		}
	
	
			
		
			
	}	
		
		



