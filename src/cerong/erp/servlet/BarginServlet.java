package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerong.erp.entity.Bargin;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.service.BarginService;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IBarginServiceImpl;
import cerong.erp.service.IEmployeeServiceImpl;

public class BarginServlet extends HttpServlet{

	IEmployeeServiceImpl eservice = new EmployeeService();
	IBarginServiceImpl service = new BarginService();
	private static final long serialVersionUID = 1L;
	/**
	 * 方法描述:查询全部合同
	 * author:wy
	 * date:2017年7月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void deleteBargin(HttpServletRequest request, HttpServletResponse response)
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
		int total1=eservice.getUser(EmpPWD, EmpEName);
		if(user1!=null&& total1>0){
			String s1 = "edwardfanemmaxieninazhao";
			 Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
		if(index1!=false){
			Bargin ba=new Bargin();
			String str = request.getParameter("vs");
			if(str != null && !"".equals(str)) {
				str = new String(str.getBytes("iso-8859-1"),"UTF-8");
			}
			ba.setName(str);
		List<Bargin>list=service.getall(ba);
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp/delete_bargin.jsp").forward(request, response);	
		}else{
			out.print("alert('对不起，你没有权限删除合同')");
		}
		}
	
	}
	
	/**
	 * 方法描述:删除合同
	 * author:wy
	 * date:2017年7月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteBargin1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");
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
		int totala=eservice.getUser(EmpPWD, EmpEName);
		if(user1!=null&& totala>0){
			String s1 = "edwardfanemmaxieninazhao";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){
				int id1=0;
				 if(id != null && !"".equals(id)) {
					  id1 = Integer.parseInt(id);
				  }
				 Bargin ba=service.getBaigin(id1);
				
				 int total1=service.deleteFactoryFund(ba.getName(),ba.getProjectId());
				 int total2=service.deleteMoneyCheckUp(ba.getName(),ba.getProjectId());	
				 int total=service.deleteBargin(id1);
				if(total>0&&total1>0&&total2>0){
					out.print("YES");
				}else{
					out.print("NO");	
				}	
			}else{
				out.print("alert('对不起，你没有权限删除合同')");
			}
		}
		
	}
	/**
	 * 方法描述:删除合同
	 * author:wy
	 * date:2017年7月24日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchBargin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");
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
		int totala=eservice.getUser(EmpPWD, EmpEName);
		if(user1!=null&& totala>0){
			String s1 = "edwardfanemmaxieninazhao";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){
				Bargin ba=new Bargin();
				String str = request.getParameter("projectId");
				if(str != null && !"".equals(str)) {
					str = new String(str.getBytes("iso-8859-1"),"UTF-8");
				}
				ba.setName(str);
			List<Bargin>list=service.getall1(ba);
			request.setAttribute("list", list);
			request.getRequestDispatcher("jsp/delete_bargin.jsp").forward(request, response);
			}else{
				out.print("alert('对不起，你没有权限删除合同')");
			}
		}
		
	}
	
	/**
	 * 
	 * @Title:ItCaseIdServlet
	 * @Description:超时未收齐发票合同
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void noInvoiceReceiptHasBeenReceived(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
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
		int total1=eservice.getUser(EmpPWD, EmpEName);
		if(user1!=null&& total1>0){
			String overtime=request.getParameter("overtime");
			String vs=request.getParameter("vs");
			
			String condition=request.getParameter("condition");
			Bargin it=new Bargin();
			int overtime1=0;
		    if(overtime!=null&&!"".equalsIgnoreCase(overtime)){
		    	overtime1=Integer.parseInt(overtime);
		    	it.setOvertime(overtime1);
		    }
		    if(vs!=null&&!"".equalsIgnoreCase(vs)){
		    	vs = new String(vs.getBytes("iso-8859-1"),"UTF-8");
		    	if("1".equalsIgnoreCase(condition)){
		    		it.setProjectId(vs);
		    		request.setAttribute("fyfy",1 );
		    	}else if("2".equalsIgnoreCase(condition)){
		    		it.setFactoryName(vs);
		    		request.setAttribute("fyfy",2 );
		    	}else if("3".equalsIgnoreCase(condition)){
		    		it.setName(vs);
		    		request.setAttribute("fyfy",3 );
		    	}
		    	request.setAttribute("vs",vs );
		    }
		    String time1=request.getParameter("time1");
			if(time1 != null && !"".equals(time1)){
			it.setStartTime(time1);
			request.setAttribute("starttime",time1 );
			}
			String time2=request.getParameter("time2");
			if(time2 != null && !"".equals(time2)){
				it.setEndTime(time2);
				request.setAttribute("endtime",time2 );
			}
			if(it.getStartTime()==null&&it.getEndTime()==null){
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String time=sdf.format(date);
				it.setStartTime(time+"-01");
				request.setAttribute("starttime",time+"-01" );	
			}
		    String invoiceCollected=request.getParameter("invoiceCollected");
		    if(invoiceCollected!=null&&!"".equalsIgnoreCase(invoiceCollected)){
		    it.setInvoiceCollected(Integer.parseInt(invoiceCollected));
		    request.setAttribute("fyfz",invoiceCollected );
		    }else{
		    	it.setInvoiceCollected(-1);
		    	request.setAttribute("fyfz",-1 );
		    }
		    double contractAmount=0.00;
		    double totalAmountPaid=0.00;
		    double amountRecoveredFromInvoice=0.00;
				List<Bargin> list=service.getAll(it);//查询超时未收回发票合同
				for(int i=0;i<list.size();i++){
					Bargin b=list.get(i);
					contractAmount+=b.getTotalSum();
					totalAmountPaid+=b.getAmountInvoiceReceived();
					amountRecoveredFromInvoice+=b.getPayMoeny();
				}
				request.setAttribute("contractAmount",contractAmount );
				request.setAttribute("totalAmountPaid",totalAmountPaid );
				request.setAttribute("amountRecoveredFromInvoice",amountRecoveredFromInvoice );
				request.setAttribute("cusList",list );
				request.getRequestDispatcher("jsp/no_invoice_receipt_has_been_received.jsp").forward(request, response);
			}
		}
	/**
	 * 
	 * @Title:BarginServlet
	 * @Description:超过4个月 没收齐发票的合同
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void more4MonthInvoiceNotReceived (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
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
		int total1=eservice.getUser(EmpPWD, EmpEName);
		if(user1!=null&& total1>0){
			Bargin b=new Bargin();
			String str = request.getParameter("vs");
			if(str != null && !"".equals(str)) {
				str = new String(str.getBytes("iso-8859-1"),"UTF-8");
			}
			String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
			if(str != null && !"".equals(str)) {//说明有输入条件内容做查询
				if(condition.equals("1")) {//说明输入的是项目号
					b.setProjectId(str);
				}else if(condition.equals("2")) {
					b.setFactoryName(str);
				}
				request.setAttribute("fyfy", condition);
				request.setAttribute("caseno", str);
			}
			String condition2 = request.getParameter("condition2");//获取下拉框输入的条件选项值
			if(condition2 != null && !"".equals(condition2)&&!"-1".equalsIgnoreCase(condition2)){
				b.setWhetherToDeclare(Integer.parseInt(condition2));
				request.setAttribute("fyfz", condition2);
			}
				List<Bargin> list=service.more4MonthInvoiceNotReceived(b);//查询超过4个月未收回发票合同
				request.setAttribute("cusList",list);
				request.getRequestDispatcher("jsp/more_four_month_invoice_not_received.jsp").forward(request, response);
			}
		}
	/**
	 * 
	 * @Title:BarginServlet
	 * @Description:查询报关名
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String bargainno=request.getParameter("bargain");
		String date=request.getParameter("date");
		if(bargainno != null && !"".equals(bargainno)) {
			bargainno = new String(bargainno.getBytes("iso-8859-1"),"UTF-8");
		}
			String bargain=service.search(bargainno,date);
				out.print(bargain);
			}
		}

