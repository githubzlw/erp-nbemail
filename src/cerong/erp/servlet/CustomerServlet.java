package cerong.erp.servlet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cerong.erp.entity.Customer;
import cerong.erp.entity.CustomerInfo;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.ItemCase1;
import cerong.erp.service.CustomerService;
import cerong.erp.service.EmailClientServiceImpl;
import cerong.erp.service.EmailUserServiceImpl;
import cerong.erp.service.ICustomerServiceImpl;
import cerong.erp.service.IEmailClientService;
import cerong.erp.service.IEmailUserService;
import cerong.erp.service.IProjectComplaintService;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;
import cerong.erp.service.ProjectComplaintServiceImpl;
import cerong.erp.util.MyFileUpLoad;
import cerong.erp.util.SplitPage;




public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int PAGE_SIZE = 5;//每页显示的最大数量
	ICustomerServiceImpl service = new CustomerService();
	ItCaseIdServiceImpl icservice = new ItCaseIdService();
	IEmailUserService uservice = new EmailUserServiceImpl();
	IProjectComplaintService pservice = new ProjectComplaintServiceImpl();
	IEmailClientService cservice = new EmailClientServiceImpl();
	/**
	 * 方法描述:方法描述:修改客户名或国家
	 * author:wy
	 * date:2016年1月28日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void cusUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String roleNo1 = request.getParameter("roleNo");
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		int roleNo=0;
		if(roleNo1 != null && !"".equals(roleNo1)) {
			roleNo= Integer.parseInt(roleNo1);
		}
		EmailUser user = new EmailUser();
		user.setRoleNo(roleNo);
		user.setUserName(userName);
		user.setPwd(pwd);
		String str = request.getParameter("uid");//获取客户id
		int erpcid = 0;
		if(str != null && !"".equals(str)) {
			erpcid = Integer.parseInt(str);
		}
		
		
		if(roleNo1!=null){
			
		
		if(erpcid>0){
		  String firstName =  request.getParameter("firstName");
		  if(firstName != null && !"".equals(firstName)) {
			  firstName = new String(firstName.getBytes("iso-8859-1"),"UTF-8");
			}
		  
		  
		  if(firstName != null && !"".equals(firstName)){
		  
		  
		 CustomerInfo cus = new CustomerInfo();
		 cus.setFirstName(firstName);
		 
		 cus.setRecoderId(user.getId());
		 cus.setRecoder(user.getUserName());
		
		
		
			int result = service.updateInfo(cus,erpcid);
			
			if(result > 0) {//说明修改成功
				out.write("<script>");
				out.write("ERP上客户信息修改成功");
				
				out.write("</script>");
			}else {
				out.write("<script>");
				out.write("ERP上客户信息修改失败");
				
				out.write("</script>");
			}
		  }else{
			     out.write("<script>");
			     out.write("ERP上客户信息无需修改");
				 out.write("</script>");	
			}	
			
		
		}else{
			 out.write("<script>");
			 out.write("客户未创建项目，ERP上不修改");
			 out.write("</script>");	
		}
	
				 
	}else{
		out.write("<script>");
		out.write("请登陆后在修改客户信息");
		out.write("</script>");		
		
	}	  
		
	}
	/**
	 * 方法描述:方法描述:修改客户名或国家
	 * author:wy
	 * date:2016年1月28日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void CreateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		
		//客户名
		String firstName = request.getParameter("firstName");
		int result=service.getfirstName(firstName);
		if(result<1){
		//客户国家
		 String country1 = request.getParameter("country");
		 int country = 0;
		  if(country1 != null && !"".equals(country1)) {
			  country = Integer.parseInt(country1);
		  }
		 //客户大小
		 String ddlDgree = request.getParameter("ddlDgree");

		 int total=service.getID(firstName,country,ddlDgree);
		 int id=service.getId(firstName);
		 request.setAttribute("id", id);
			if(total > 0) {//说明修改成功
				/*request.setAttribute("name", "操作成功,已在ERP系统生成新的客户，客户号为:"+id);
				request.getRequestDispatcher("jsp/cus_right.jsp").forward(request, response);*/
				out.write("id为:"+id);
				
			}else {
				out.write("No");
				
			} 
		}else{
			out.write("<script>");
			out.write("客户名已存在");
			out.write("window.location='/ERP-NBEmail/jsp/create_customer.jsp'");
			out.write("</script>");
			
		}
		
			
	  
		
	}
	/**
	 * 方法描述:查看所有客户列表
	 * author:wy
	 * date:2016年6月1日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void LookCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1=format.format(date);
		System.out.print(time1);
		int result=pservice.getid();
		
		if(result>0){
			
		}else{
		int total=pservice.getall();
		
		}
		
		
		Long millionSeconds = format.parse(time1).getTime();
		info.setStart(start);
		info.setEnd(PAGE_SIZE);
		//查看4个月未联系客户列表
        List<CustomerInfo> cusList=service.getCus(info);
		
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/Customer.jsp").forward(request, response);
	}
		
	/**
	 * 方法描述:对客户进行解释
	 * author:wy
	 * date:2016年6月1日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void explain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		//PrintWriter out = response.getWriter();
		String cid1 = request.getParameter("cid");
		int cid=0;
		if(cid1 != null && !"".equals(cid1)) {
			cid = Integer.parseInt(cid1);
		}
		CustomerInfo info=service.getCUS(cid);
        request.setAttribute("info", info);
        request.setAttribute("cid", cid);
        
		
		request.getRequestDispatcher("jsp/Customer1.jsp").forward(request, response);
		
	}
	/**
	 * 方法描述:对客户进行解释
	 * author:wy
	 * date:2016年6月1日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void explain1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String roleName = request.getParameter("role");
		String cid1 = request.getParameter("cid");
		int cid=0;
		if(cid1 != null && !"".equals(cid1)) {
			cid = Integer.parseInt(cid1);
		}
		int roleNo = 0;
		if(roleName != null && !"".equals(roleName)) {
			roleNo = Integer.parseInt(roleName);
		}
		String name=null;
		if(roleNo==1){
		  name="发明家";	
		}else if(roleNo==2){
			name="公司采购";
		}else if(roleNo==3){
			name="其他";
		}
		//潜力
		String potential = request.getParameter("potential");
		potential = new String(potential.getBytes("iso-8859-1"),"utf-8");
		//说明
		String explain = request.getParameter("explain");
		explain = new String(explain.getBytes("iso-8859-1"),"utf-8");
		
		int total=service.update(name, potential,explain,cid);
		
		if(total>0){
			/*out.write("yes");*/
			 request.setAttribute("name", "客户说明保存成功");
				request.getRequestDispatcher("jsp/right1.jsp").forward(request, response);
		}else{
			/*out.write("no");*/
		 request.setAttribute("name", "客户说明保存失败");
			request.getRequestDispatcher("jsp/right1.jsp").forward(request, response);
		}
	}
	
	/**
	 * 方法描述:查看4个月未联系所有客户列表
	 * author:wy
	 * date:2016年6月6日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void cusList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		String condition2 = request.getParameter("condition2");//获取翻译状态下拉列表选项值
		if(condition2 != null && !"".equals(condition2) && !"-1".equals(condition2))
		{
			num = Integer.parseInt(condition2);
			request.setAttribute("fyzx", condition2);
			if(num==1){
				info.setSaleName2("LynnYuan");
		}else if(num==2){
			info.setSaleName2("Fionayan");
		}else if(num==3){
			info.setSaleName2("Amyzhao");
		}else if(num==4){
			info.setSaleName2("PaulWang");
		}else if(num==5){
			info.setSaleName2("Joannahao");
		}else if(num==6){
			info.setSaleName2("ChloeMa");
		}else if(num==7){
			info.setSaleName2("ElaineSheng");
		}else if(num==8){
			info.setSaleName2("IvyWu");
		}else if(num==9){
			info.setSaleName2("JessieHan");
		}else if(num==10){
			info.setSaleName2("KathyPan");
		}else if(num==11){
			info.setSaleName2("SherryZhou");
		}else if(num==12){
			info.setSaleName2("JuliaZeng");
		}else if(num==13){
			info.setSaleName2("anna");
		}else if(num==14){
			info.setSaleName2("tina");
		}
		}
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String time1=format.format(date);
		
			
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		info.setTime1(time1);
			
		
		
		
		
		
		info.setStart(start);
		info.setEnd(PAGE_SIZE);
		//查看4个月未联系客户列表
        List<CustomerInfo> cusList=service.getCus1(info);
       /// int total=service.getCus1Total(info);
		//SplitPage.buildPager(request, total, PAGE_SIZE, page);
		
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/Customer2.jsp").forward(request, response);
	}	
		
	
		
	
	
	
	/**
	  * 方法描述:最近1个月下单的客户列表
	 * author:wy
	 * date:2016年7月9日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void Customer1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		
		String time1=format.format(date);
		Long millionSeconds = format.parse(time1).getTime();
		Long millionSeconds1= millionSeconds-2592000000l;
	    
		int id1=0;
		int id2=0;
		info.setStart(start);
		info.setEnd(PAGE_SIZE);		
		//查看1个月下单客户列表
		List<CustomerInfo> cusList=service.getCu(info,id1,id2);
		
		request.setAttribute("code", "最近一个月销售排行");
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/Customer5.jsp").forward(request, response);
	}
	/**
	  * 方法描述:最近2个月下单的客户列表
	 * author:wy
	 * date:2016年7月9日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void Customer2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		int id1=0;
		int id2=0;
		String time1=format.format(date);
		Long millionSeconds = format.parse(time1).getTime();
		Long millionSeconds1= millionSeconds-5184000000l;
	   
		info.setStart(start);
		info.setEnd(PAGE_SIZE);		
		//查看1个月下单客户列表
		List<CustomerInfo> cusList=service.getCu1(info,id1,id2);
		
		request.setAttribute("code", "最近两个月销售排行");
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/Customer6.jsp").forward(request, response);
	}
	/**
	 * 方法描述:对客户状态进行定义
	 * author:wy
	 * date:2016年7月9日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void cstatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String id1 = request.getParameter("id");
		String num = request.getParameter("num");
		String url = request.getParameter("url");
		int id=0;
		if(id1 != null && !"".equals(id1)) {
			id = Integer.parseInt(id1);
		}
		int num1 = 0;
		if(num != null && !"".equals(num)) {
			num1 = Integer.parseInt(num);
		}
		int url1 = 0;
		if(url != null && !"".equals(url)) {
			url1 = Integer.parseInt(url);
		}
		String cstatus=null;
		if(num1==1){
			cstatus="<font color=red>已死</font>";	
		}else if(num1==2){
			cstatus="<font color=blue>需跟进</font>";
		}else if(num1==3){
			cstatus="<font color=blur>没问题</font>";
		}
		String urll="";
		if(url1==1){
			urll="/helpServlet?action=LookCustomer&className=CustomerServlet";	
		}else if(url1==2){
			urll="/helpServlet?action=cusList&className=CustomerServlet&condition=1";
		}else if(url1==3){
			urll="/helpServlet?action=SalesRank&className=CustomerServlet";
		}else if(url1==4){
			urll="/helpServlet?action=SalesRank1&className=CustomerServlet";
		}else if(url1==5){
			urll="/helpServlet?action=Customer1&className=CustomerServlet";
		}else if(url1==6){
			urll="/helpServlet?action=Customer2&className=CustomerServlet";
		}
		
		
		
		int total=service.updateCstatus(cstatus,id);
		
		if(total>0){
			
			 
				request.getRequestDispatcher(urll).forward(request, response);
		}else{
			
		
			request.getRequestDispatcher(urll).forward(request, response);
		}
	}
	
	
	/**
	 * 方法描述:查询客户信息
	 * author:wy
	 * date:2016年10月17日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void lookCus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

		PrintWriter out = response.getWriter();List<Customer> list1=service.getAll();
		//List<ItemCase> list2=icservice.getall();
		//List<ItemCase1> list3=icservice.getall1();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("Customer", list1);
		//map.put("ClientDrawings", list2);
		//map.put("ClientOrder", list3);
		StringBuffer sb1 = new StringBuffer();//创建一个StringBuffer 对象  
		  
		 
		String result="";
		
			JSONObject json = JSONObject.fromObject(map);
			System.out.print(json);
			sb1.append(json); //拼接字符串  \"  转义 == "  
			 result = sb1.toString(); 
			response.getWriter().print(json);
			response.getWriter().close();
			result = new String(result.getBytes("UTF-8"));

			//result=URLEncoder.encode(result, "UTF-8");
	
		PrintWriter out1 = null;
        BufferedReader in1 = null;
        String result2 = "";
        try {
        	
            //URL realUrl1 = new URL("http://117.144.21.74:43900/ERP-NBEmail/helpServlet?action=Attachment&className=ItCaseIdServlet");
            URL realUrl1 = new URL("http://192.168.1.202:8099/crmconsole/port/receiveAllData.do");
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
            out1.print("result="+result);
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
            System.out.println("发送 POST 请求出现异常！"+e);
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
	
  if("YES".equals(result2)){
	  System.out.print("已将客户信息保存到本地");
  }
  
	
		
	}
	/**
	  * 方法描述:最近4个月下单的客户列表
	 * author:wy
	 * date:2016年10月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 

	 */
	public void Customera(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		
		String time1=format.format(date);
		Long millionSeconds = format.parse(time1).getTime();
		Long millionSeconds1= millionSeconds-10368000000l;
	    
		int id1=0;
		int id2=0;
		info.setStart(start);
		info.setEnd(PAGE_SIZE);	
		//查看1个月下单客户列表
		List<CustomerInfo> cusList=service.getCu2(info,id1,id2);
		
		request.setAttribute("code", "最近4个月销售排行");
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/customera.jsp").forward(request, response);
	}
	
	/**
	 * 方法描述:最近12个月有有下单 但 最近 4个月没下单的
	 * author:wy
	 * date:2016年10月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void Customerb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String time1=format.format(date);
		
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 =millionSeconds1-10368000000l ;
		Long millionSeconds3 =millionSeconds1-31104000000l ;
		
		int id1=0;
		int id2=0;
		int id3=0;
		int id=0;
		info.setStart(start);
		info.setEnd(PAGE_SIZE);	
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		info.setTime1(time1);
		//查看4个月未联系客户列表
        List<CustomerInfo> cusList=service.getCusb(info,id,id1,id2,id3);
       
		
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/customerb.jsp").forward(request, response);
	}
	
	/**
	 * 方法描述:最近24个月有下单 但 最近 18个月没下单的
	 * author:wy
	 * date:2016年10月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void Customerd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String time1=format.format(date);
		
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 =millionSeconds1-46656000000l ;
		Long millionSeconds3 =millionSeconds1-62208000000l ;
		int id1=0;
		int id2=0;
		int id3=0;
		int id=0;
	
		
		

			
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		info.setTime1(time1);
		//查看4个月未联系客户列表
        List<CustomerInfo> cusList=service.getCusd(info,id,id1,id2,id3);
      
		
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/customerd.jsp").forward(request, response);
	}
	/**
	 * 方法描述:最近36个月有下单 但 最近 24个月没下单的
	 * author:wy
	 * date:2016年10月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void Customere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String time1=format.format(date);
		
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 =millionSeconds1-62208000000l ;
		Long millionSeconds3 =millionSeconds1-93312000000l ;
		int id1=0;
		int id2=0;
		int id3=0;
		int id=0;
			
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		info.setTime1(time1);
		//查看4个月未联系客户列表
        List<CustomerInfo> cusList=service.getCuse(info,id,id1,id2,id3);
       
		
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/customere.jsp").forward(request, response);
	}
	/**
	 * 方法描述:最近48个月有下单 但 最近 36个月没下单的
	 * author:wy
	 * date:2016年10月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void Customerf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String time1=format.format(date);
		
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 =millionSeconds1-93312000000l ;
		Long millionSeconds3 =millionSeconds1-124416000000l ;
		int id1=0;
		int id2=0;
		int id3=0;
		int id=0;
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		info.setTime1(time1);
		//查看4个月未联系客户列表
        List<CustomerInfo> cusList=service.getCusf(info,id,id1,id2,id3);
         request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/customerf.jsp").forward(request, response);
	}
	/**
	 * 方法描述:最近18个月有下单 但 最近 12个月没下单的 
	 * author:wy
	 * date:2016年10月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void Customerc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		HttpSession session = request.getSession();
		EmailUser user = (EmailUser)session.getAttribute("user");
		int page = 1;//默认是第一页
		if(st != null && !"".equals(st)) {
			page = Integer.parseInt(st);
		}
		int start = (page-1) * PAGE_SIZE;
		CustomerInfo info = new CustomerInfo();
		String condition1 = request.getParameter("condition1");//获取翻译状态下拉列表选项值
		int num=0;
		if(condition1 != null && !"".equals(condition1) && !"-1".equals(condition1))
		{
			num = Integer.parseInt(condition1);
			request.setAttribute("fyzt", condition1);
			
		if(num==1){
					info.setSaleName1("LynnYuan");
			}else if(num==2){
				info.setSaleName1("Fionayan");
			}else if(num==3){
				info.setSaleName1("Amyzhao");
			}else if(num==4){
				info.setSaleName1("PaulWang");
			}else if(num==5){
				info.setSaleName1("Joannahao");
			}else if(num==6){
				info.setSaleName1("ChloeMa");
			}else if(num==7){
				info.setSaleName1("ElaineSheng");
			}else if(num==8){
				info.setSaleName1("IvyWu");
			}else if(num==9){
				info.setSaleName1("JessieHan");
			}else if(num==10){
				info.setSaleName1("KathyPan");
			}else if(num==11){
				info.setSaleName1("SherryZhou");
			}else if(num==12){
				info.setSaleName1("JuliaZeng");
			}else if(num==13){
				info.setSaleName1("anna");
			}else if(num==14){
				info.setSaleName1("tina");
			}
		}
		
		
		
		
		String condition4 = request.getParameter("condition4");//获取销售分配状态下拉列表选项值
		int saleId = -1;
		if(condition4 != null && !"".equals(condition4) && !"-1".equals(condition4))
		{
			saleId = Integer.parseInt(condition4);
			request.setAttribute("xsfp", condition4);
			if(saleId==0){
				info.setCstatus("已死");
		}else if(saleId==1){
			info.setCstatus("需跟进");
		}else if(saleId==2){
			info.setCstatus("没问题");
		}
			
			
		}
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String time1=format.format(date);
		
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 =millionSeconds1-31104000000l ;
		Long millionSeconds3 =millionSeconds1-46656000000l ;
		int id1=0;
		int id2=0;
		int id3=0;
		int id=0;
			
		String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		info.setTime1(time1);
		//查看4个月未联系客户列表
        List<CustomerInfo> cusList=service.getCusc(info,id,id1,id2,id3);
      
		request.setAttribute("cusList", cusList);
		
		request.getRequestDispatcher("jsp/customerc.jsp").forward(request, response);
	}
			
	  
			
	  
		
	}
	
	

