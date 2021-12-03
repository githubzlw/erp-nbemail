package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.icu.text.DecimalFormat;

import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.AmountClaimForm;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.PreparatorEntryForm;
import cerong.erp.entity.invoiceinfo;
import cerong.erp.service.AccountEntryFormServiceImpl;
import cerong.erp.service.AmountClaimFormServiceImpl;
import cerong.erp.service.IAccountEntryFormService;
import cerong.erp.service.IAmountClaimFormService;
import cerong.erp.service.IPreparatorEntryFormService;
import cerong.erp.service.IinvoiceinfoServiceImpl;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;
import cerong.erp.service.PreparatorEntryFormServiceImpl;
import cerong.erp.service.invoiceinfoService;
import cerong.erp.util.Base64Encode;
import cerong.erp.util.MyFileUpLoad;



//金额认领详情页
public class AmountClaimFormServlet extends HttpServlet{
	IAmountClaimFormService service =new AmountClaimFormServiceImpl();
	IAccountEntryFormService acervice =new AccountEntryFormServiceImpl();
	IinvoiceinfoServiceImpl inservice=new invoiceinfoService();
	ItCaseIdServiceImpl cservice = new ItCaseIdService();
	IPreparatorEntryFormService pservice=new PreparatorEntryFormServiceImpl();
	/**
	 * 
	 * @Title:AmountClaimFormServletcheckData1
	 * @Description:销售选中进入详情录入页
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void enterFinancialEntry(HttpServletRequest request, HttpServletResponse response)
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
		String customerId1=request.getParameter("customerId");
		String allMoney1=request.getParameter("allMoney");
		String id1=request.getParameter("id");
		String tradeCurrency=request.getParameter("tradeCurrency");
		int id=0;
		if(id1!=null&&!"".equals(id1)){
			id=Integer.parseInt(id1);
		}
		AccountEntryForm account1=acervice.getAccount(id);
		int customerId=0;
		if(customerId1!=null&&!"".equals(customerId1)){
			customerId=Integer.parseInt(customerId1);
		}
		double allMoney=0;
		if(allMoney1!=null&&!"".equals(allMoney1)){
			allMoney=Double.parseDouble(allMoney1);
		}
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		int total=acervice.replacementOfClaim(EmpEName,id);
		if(user1!=null){
			
		    List<AmountClaimForm> list=service.enterFinancialEntry(id);
		    if(list.size()>0){
			request.setAttribute("cusList",list );
		    }else{
		    	request.setAttribute("cusList",null );
		    }
		    Calendar cal=Calendar.getInstance();   
		    int year=cal.get(Calendar.YEAR);   
		    int  month=cal.get(Calendar.MONTH)+1; 
		    String s1 = "mandymanlisaliShiGuoJuanroseli";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
		   if(index1!=false){
			request.setAttribute("roleNO", 100);
		     }
			request.setAttribute("allMoney",allMoney );
			request.setAttribute("id",id );
			request.setAttribute("year",year );
			request.setAttribute("month",month );
			request.setAttribute("customerId",customerId );
			request.setAttribute("account",account1 );
			request.setAttribute("tradeCurrency",tradeCurrency );
			if(!"USD".equals(tradeCurrency)){
				request.setAttribute("payAttentionTo","ERP进账一般为美元，该进账不是美元，注意校验进账货币与ERP上是否一致" );	
			}
			request.getRequestDispatcher("jsp/enter_financial_entry.jsp").forward(request, response);
		}
	
	}
	/**
	 * 
	 * @Title:AmountClaimFormServlet
	 * @Description:直接进入详情页查看
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void lookEnterFinancialEntry(HttpServletRequest request, HttpServletResponse response)
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
		String tradeCurrency=request.getParameter("tradeCurrency");
		String customerId1=request.getParameter("customerId");
		String allMoney1=request.getParameter("allMoney");
		String wrong1=request.getParameter("wrong");
		if(wrong1!=null&&!"".equalsIgnoreCase(wrong1)){
		String wrong=URLDecoder.decode(wrong1, "utf-8");
		request.setAttribute("wrong",wrong);
		}
		String id1=request.getParameter("id");
		int id=0;
		if(id1!=null&&!"".equals(id1)){
			id=Integer.parseInt(id1);
		}
		AccountEntryForm account1=acervice.getAccount(id);
		double allMoney=0;
		if(allMoney1!=null&&!"".equals(allMoney1)){
			allMoney=Double.parseDouble(allMoney1);
		}
		int customerId=0;
		if(customerId1!=null&&!"".equals(customerId1)){
			customerId=Integer.parseInt(customerId1);
		}
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		
		if(user1!=null){
			
			List<AmountClaimForm> list=service.enterFinancialEntry(id);
			if(list.size()>0){
				request.setAttribute("cusList",list );
			}else{
				request.setAttribute("cusList",null );
			}
			List<PreparatorEntryForm>cusList1=pservice.getAll(id);
			if(cusList1.size()>0){
				request.setAttribute("cusList1",cusList1 );
			}else{
				request.setAttribute("cusList1",null );
			}
			PreparatorEntryForm preparator =pservice.getOne(id);
			request.setAttribute("preparator",preparator );
			Calendar cal=Calendar.getInstance();   
			int year=cal.get(Calendar.YEAR);   
			int  month=cal.get(Calendar.MONTH)+1; 
			request.setAttribute("allMoney",allMoney );
			request.setAttribute("id",id );
			request.setAttribute("year",year );
			request.setAttribute("month",month );
			request.setAttribute("customerId",customerId);
			request.setAttribute("account",account1 );
			request.setAttribute("tradeCurrency",tradeCurrency );
			 String s2 = "ands";
				Boolean index2=false;
				index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());
			   if(index2!=false){
				request.setAttribute("financialAuthority", 99);
			     }else{
			    	request.setAttribute("financialAuthority", 5);	 
			     }
			String s1 = "mandymanlisali";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){
				request.setAttribute("financialAuthority", 100);
			}
			if(tradeCurrency!=null&&!"".equals(tradeCurrency)){
			if(!"USD".equals(tradeCurrency)){
				request.setAttribute("payAttentionTo","ERP进账一般为美元，该进账不是美元，注意校验进账货币与ERP上是否一致" );	
			}
			}else{
				if(!"USD".equals(account1.getTradeCurrency())){
					request.setAttribute("payAttentionTo","ERP进账一般为美元，该进账不是美元，注意校验进账货币与ERP上是否一致" );	
				}	
			}
			request.getRequestDispatcher("jsp/enter_financial_entry.jsp").forward(request, response);
		}
		
	}
	/**
	 * 
	 * @Title:AmountClaimFormServlet
	 * @Description:ERP直接进入详情页查看
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void lookERPEnterFinancialEntry(HttpServletRequest request, HttpServletResponse response)
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
		
		
		String iid1=request.getParameter("iid");
		int iid=0;
		if(iid1!=null&&!"".equals(iid1)){
			iid=Integer.parseInt(iid1);
		}
		int id=acervice.getId(iid);
		AccountEntryForm account1=acervice.getAccount(id);
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		
		if(user1!=null){
			
			List<AmountClaimForm> list=service.enterFinancialEntry(id);
			if(list.size()>0){
				request.setAttribute("cusList",list );
			}else{
				request.setAttribute("cusList",null );
			}
			List<PreparatorEntryForm>cusList1=pservice.getAll(id);
			if(cusList1.size()>0){
				request.setAttribute("cusList1",cusList1 );
			}else{
				request.setAttribute("cusList1",null );
			}
			PreparatorEntryForm preparator =pservice.getOne(id);
			request.setAttribute("preparator",preparator );
			Calendar cal=Calendar.getInstance();   
			int year=cal.get(Calendar.YEAR);   
			int  month=cal.get(Calendar.MONTH)+1; 
			request.setAttribute("id",id );
			request.setAttribute("year",year );
			request.setAttribute("month",month );
			request.setAttribute("account",account1 );
			
			String s1 = "mandymanlisali";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){
				request.setAttribute("financialAuthority", 100);
			}
			request.getRequestDispatcher("jsp/enter_financial_entry.jsp").forward(request, response);
		}
		
	}
	
	/**
	 * 
	 * @Title:AmountClaimFormServlet
	 * @Description:到账检查
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void checkData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyFileUpLoad.upload(request, response);
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
			String state=(String) request.getAttribute("state");
			String id1=(String) request.getAttribute("id");
			String allMoney=(String) request.getAttribute("allMoney");
			String customerId=(String) request.getAttribute("customerId");
			int id=0;
			if(id1!=null&&!"".equals(id1)){
				id=Integer.parseInt(id1);
			}
			String wrong="";
			List<AmountClaimForm> list=service.enterFinancialEntry(id);
			if(list.size()>0){
				wrong="合同金额已录入,请勿重复录入合同金额";
				wrong = new String(wrong.getBytes("iso-8859-1"),"utf-8");
				response.sendRedirect("/ERP-NBEmail/helpServlet?action=lookEnterFinancialEntry&className=AmountClaimFormServlet&allMoney="+allMoney+"&id="+id+"&customerId="+customerId+"&&wrong="+wrong+"");


			}else{


		boolean operation=true;
		String allinvoice="";
		for(int i=0;i<12;i++){
			if(operation==true){
			int num=i+1;
		String invoice= (String) request.getAttribute("invoice"+num);
		if(invoice!=null&&!"".equals(invoice)){
		invoiceinfo info=inservice.getInvoice(invoice,"1");//查询invoice是否存在
		AmountClaimForm amount=acervice.getAmount(invoice);//查询invoice是否存在
	    if(info!=null){
	    	 if(amount!=null){
	    		 wrong=invoice+",该合同号已录入,再次录入会出现认领错误现象,请等财务确认完上一笔认领后再认领该笔数据";
	 	    	operation=false;

	    	 }else{
	       if(!"mandyman".equalsIgnoreCase(EmpEName)&&!"lisali".equalsIgnoreCase(EmpEName)&&!"planner".equalsIgnoreCase(EmpEName)
	    		   &&!"ands".equalsIgnoreCase(EmpEName)){
	    	int username=cservice.find(EmpEName,info.getCaseno());
	    	 if(username>0){
	    		 String invoicemoney=(String) request.getAttribute("invoicemoney"+num);
	    		 double allInvoiceMoney=0;
	    			if(invoicemoney!=null&&!"".equals(invoicemoney)){
	    				allInvoiceMoney=Double.parseDouble(invoicemoney);
	    			}
	    		if(50+info.getIimoney()<info.getIfmoney()+allInvoiceMoney){
	    			wrong=invoice+",该invoice总金额是:"+info.getIimoney()+"已录入金额"+info.getIfmoney();

	    			operation=false;

	    		}
	    	}else{
	    		wrong=invoice+",你无权限操作该项目";
	    		operation=false;

	    	 }
	       }

	       if(allinvoice!=null&&!"".equals(allinvoice)){
	   		if(allinvoice.toLowerCase().contains(invoice.toLowerCase())){
	   			wrong=invoice+",相同invoice只允许出现一次";
	    		operation=false;
	   		}
	   		}else{
	   		allinvoice+=invoice;

	       }
	    	 }
	    }else{
	    	wrong=invoice+",该invoice数据库不存在";
	    	operation=false;

	    }
		}
			}
			}



		if(operation==true){
		for(int i=0;i<12;i++){
			int num=i+1;
		String invoice= (String) request.getAttribute("invoice"+num);
		if(invoice!=null&&!"".equals(invoice)){
		invoiceinfo info=inservice.getInvoice(invoice,"1");//查询invoice是否存在
	    if(info!=null){
	    String invoicemoney=(String) request.getAttribute("invoicemoney"+num);
		double allInvoiceMoney=0;
		if(invoicemoney!=null&&!"".equals(invoicemoney)){
			allInvoiceMoney=Double.parseDouble(invoicemoney);
		}

		String year=(String) request.getAttribute("year"+num);
	    String month=(String) request.getAttribute("month"+num);
		String country1=(String) request.getAttribute("country"+num);
		int country=0;
		if(country1!=null&&!"".equals(country1)){
			country=Integer.parseInt(country1);
		}
		int ibank=acervice.getIbank(id);
		AccountEntryForm accountEntryForm=acervice.getAccount(id);
		if("中国银行上海市福州路支行".equals(accountEntryForm.getBeneficiaryAccountBank())&&ibank==0){
			ibank=5;
		}
		AmountClaimForm claim=new AmountClaimForm();
		claim.setAccountEntryId(id);
		claim.setExportYear(year);
		claim.setExportMonth(month);
		claim.setInvoice(invoice);
		claim.setSumMoney(allInvoiceMoney);
		claim.setCountry(country);
		claim.setState(state);
		AccountEntryForm account=acervice.getAll(id);
		String time=account.getTransactionDate();
		int total=service.insert(claim);//将金额分配给invoice
		int total1=pservice.add(invoice,id,time,ibank,info.getCaseno());
		int invoiceId=pservice.getId(invoice);
		int total2=pservice.update(invoiceId,allInvoiceMoney);
	    }else{
	    	out.write("<script>");
			 out.write("alert('"+invoice+",该invoice数据库不存在！！！');");
			 out.write("window.history.back();");
			 out.write("</script>");

	    }
		}
		}
		response.sendRedirect("/ERP-NBEmail/helpServlet?action=lookEnterFinancialEntry&className=AmountClaimFormServlet&allMoney="+allMoney+"&id="+id+"&customerId="+customerId);

		}else{
			String wrong1 = URLEncoder.encode(wrong, "utf-8");

			response.sendRedirect("/ERP-NBEmail/helpServlet?action=lookEnterFinancialEntry&className=AmountClaimFormServlet&allMoney="+allMoney+"&id="+id+"&customerId="+customerId+"&&wrong="+wrong1);

		}
			}
	}
	
	/**
	 * 
	 * @Title:AmountClaimFormServlet
	 * @Description:验证数据是否正确
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void checkData1(HttpServletRequest request, HttpServletResponse response)
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
		List<String>allList1=new ArrayList<String>(); 
		List<AmountClaimForm>allList=new ArrayList<AmountClaimForm>(); 
			EmailUser user1=new EmailUser();
			user1.setUserName(EmpEName);
			user1.setPwd(EmpPWD);
			String id1= request.getParameter("id");
			String allMoney= request.getParameter("allMoney");
			String customerId= request.getParameter("customerId");
			String tradeCurrency=request.getParameter("tradeCurrency");
			int tradeCurrency1=0;
			if("USD".equalsIgnoreCase(tradeCurrency)){
				tradeCurrency1=1;
			}else if("EUR".equalsIgnoreCase(tradeCurrency)){
				tradeCurrency1=3;
			}else if("CNY".equalsIgnoreCase(tradeCurrency)||"RMB".equalsIgnoreCase(tradeCurrency)){
				tradeCurrency1=2;
			}else if("GBP".equalsIgnoreCase(tradeCurrency)){
				tradeCurrency1=5;
			}
			double money=0.00;
			double money1=0.00;
			 money1=Double.parseDouble(allMoney);
			int id=0;
			if(id1!=null&&!"".equals(id1)){
				id=Integer.parseInt(id1);
			}
			String wrong="";
			List<AmountClaimForm> list=service.enterFinancialEntry(id);
			if(list.size()>0){
				wrong="合同金额已录入,请勿重复录入合同金额";
				wrong = new String(wrong.getBytes("iso-8859-1"),"utf-8");
				response.sendRedirect("/ERP-NBEmail/helpServlet?action=lookEnterFinancialEntry&className=AmountClaimFormServlet&allMoney="+allMoney+"&id="+id+"&customerId="+customerId+"&&wrong="+wrong+"");
					
				
			}else{
			boolean operation=true;
		String allinvoice="";
		for(int i=0;i<12;i++){
			if(operation==true){
				AmountClaimForm amountClaimForm=new AmountClaimForm();
			int num=i+1;
		String invoice=  request.getParameter("invoice"+num);
		amountClaimForm.setInvoice(invoice);
		list.add(amountClaimForm);
		allList1.add(invoice);
		if(invoice!=null&&!"".equals(invoice)){
		invoiceinfo info=inservice.getInvoice(invoice,"1");//查询invoice是否存在
		AmountClaimForm amount=acervice.getAmount(invoice);//查询invoice是否存在
//			invoiceinfo info1=inservice.getInvoice(invoice,"2");//查询invoice实际到款,预计收款
	    if(info!=null){
	    	/*if(info.getImoneytype()==tradeCurrency1){*/
	    	 if(amount!=null){
	    		 wrong=invoice+",该合同号已录入,再次录入会出现认领错误现象,请等财务确认完上一笔认领后再认领该笔数据";
	 	    	operation=false;
	    		 
	    	 }else{
	       if(!"mandyman".equalsIgnoreCase(EmpEName)&&!"lisali".equalsIgnoreCase(EmpEName)&&!"planner".equalsIgnoreCase(EmpEName)
	    		   &&!"ands".equalsIgnoreCase(EmpEName)){
	       	if(info.getImoneytype()==tradeCurrency1) {
				int username = cservice.find(EmpEName, info.getCaseno());
				if (username > 0) {
					String invoicemoney = request.getParameter("invoicemoney" + num);
					double allInvoiceMoney = 0.000;
					if (invoicemoney != null && !"".equals(invoicemoney)) {
						allInvoiceMoney = Double.parseDouble(invoicemoney);
						money += allInvoiceMoney;
					}
					if (50 + info.getIimoney() < info.getIfmoney() + allInvoiceMoney) {
						wrong = invoice + ",该invoice总金额是:" + info.getIimoney() + "已录入金额" + info.getIfmoney();

						operation = false;

					}
//					if (info.getIimoney() < info1.getIfmoney() + allInvoiceMoney) {
//						wrong = invoice + "该invoice的已录入金额+实际到款大于Invoice 总金额";
//
//						operation = false;
//
//					}

				} else {
					wrong = invoice + ",你无权限操作该项目";
					operation = false;

				}
			}else{
				wrong = invoice + ",该进账预计录入货币单位与实际进账单位不一致";
				operation = false;
			}

	       }
	       
	       if(allinvoice!=null&&!"".equals(allinvoice)){
	   		if(allinvoice.toLowerCase().contains(invoice.toLowerCase())){
	   			wrong=invoice+",相同invoice只允许出现一次";
	    		operation=false;	
	   		}
	   		}else{
	   		allinvoice+=invoice;  
	   		
	       }
	    	 }
	    /*}else{
	    	wrong="货币单位不统一，不允许录入，请与财务沟通处理";
	    	operation=false;	
	    	
	    }*/
	    }else{
	    	wrong=invoice+",该invoice数据库不存在";
	    	operation=false;
			
	    }
		}
			}
			}
		DecimalFormat df = new DecimalFormat("#.00");
		String str = df.format(money);
		double money2=Double.parseDouble(str); 
		
		
		
		if(operation==true){
			if(money2==money1){
		out.print("YES");
			}else{
				wrong="页面分配金额不等于总金额";	
				out.print(wrong);
			}
		}else{
			
			out.print(wrong);
			
		}
			}
	}
}
