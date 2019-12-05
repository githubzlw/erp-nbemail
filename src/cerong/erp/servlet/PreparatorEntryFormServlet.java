package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.AmountClaimForm;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.PreparatorEntryForm;
import cerong.erp.service.AccountEntryFormServiceImpl;
import cerong.erp.service.AmountClaimFormServiceImpl;
import cerong.erp.service.IAccountEntryFormService;
import cerong.erp.service.IAmountClaimFormService;
import cerong.erp.service.IPreparatorEntryFormService;
import cerong.erp.service.PreparatorEntryFormServiceImpl;
import cerong.erp.util.MyFileUpLoad;

public class PreparatorEntryFormServlet extends HttpServlet{
	IPreparatorEntryFormService service=new PreparatorEntryFormServiceImpl();
	IAmountClaimFormService acservice =new AmountClaimFormServiceImpl();
	IAccountEntryFormService aeservice =new AccountEntryFormServiceImpl();
	/**
	 * 
	 * @Title:PreparatorEntryFormServlet
	 * @Description:销售确认成本
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * 
	 * @throws IOException void
	 * @throws
	 */
	public void salesModificationInformation(HttpServletRequest request, HttpServletResponse response)
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
		String customerId1=(String) request.getAttribute("customerId");
		String allMoney1=(String) request.getAttribute("allMoney");
		String id1=(String) request.getAttribute("id");
		int id=0;
		if(id1!=null&&!"".equals(id1)){
			id=Integer.parseInt(id1);
		}
		double allMoney=0.00;
		if(allMoney1!=null&&!"".equals(allMoney1)){
			allMoney=Double.parseDouble(allMoney1);
		}
		int customerId=0;
		if(customerId1!=null&&!"".equals(customerId1)){
			customerId=Integer.parseInt(customerId1);
		}
		AccountEntryForm accountEntryForm=aeservice.getAccount(id);
		String time=accountEntryForm.getTransactionDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String time1=sdf1.format(date);
		List<PreparatorEntryForm>preparator=service.getAll(id);
		double allMoney2=0.00;
		for(int i=0;i<preparator.size();i++){
			int num=i+1;
			String iid2= (String) request.getAttribute("id"+num);
			int iid3=0;
			if(iid2!=null&&!"".equals(iid2)){
				iid3=Integer.parseInt(iid2);
			}
			String ifmoney1= (String) request.getAttribute("ifmoney"+num);
			double ifmoney=0;
			if(ifmoney1!=null&&!"".equals(ifmoney1)){
				ifmoney=Double.parseDouble(ifmoney1);
			}
		   allMoney2+=ifmoney;
		}
		allMoney2 = (double) Math.round(allMoney2 * 100) / 100;
		String wrong1 = "";
		if(allMoney==allMoney2){
		
		for(int i=0;i<preparator.size();i++){
			int num=i+1;
			String iid2= (String) request.getAttribute("id"+num);
			int iid3=0;
			if(iid2!=null&&!"".equals(iid2)){
				iid3=Integer.parseInt(iid2);
			}
		double allAcountMoney=aeservice.getAllMoney(id);
		String iid1= (String) request.getAttribute("iid"+num);
		int iid=0;
		if(iid1!=null&&!"".equals(iid1)){
			iid=Integer.parseInt(iid1);
		}
		String ifmoney1= (String) request.getAttribute("ifmoney"+num);
		double ifmoney=0;
		if(ifmoney1!=null&&!"".equals(ifmoney1)){
			ifmoney=Double.parseDouble(ifmoney1);
		}
		String iimoney1= (String) request.getAttribute("iimoney"+num);
		String remarks1= (String) request.getAttribute("remarks"+num);
		if(remarks1!=null&&!"".equals(remarks1)){
			remarks1="总进账金额:"+allAcountMoney+","+remarks1;
		}
		double iimoney=0;
		if(iimoney1!=null&&!"".equals(iimoney1)){
			iimoney=Double.parseDouble(iimoney1);
		}
		
		if(iimoney>ifmoney+50&&ifmoney!=0){
			PreparatorEntryForm preparator2=new PreparatorEntryForm();
			preparator2.setIid(iid);
			preparator2.setAmountClaimFormId(id);
			preparator2.setIimoney(ifmoney);
			preparator2.setIfmoney(ifmoney);
			preparator2.setSaleName(EmpEName);
			preparator2.setCaseno(time1);
			preparator2.setRemarks(remarks1);
			int total1=service.updateAll(preparator2);
	    	PreparatorEntryForm preparator1=new PreparatorEntryForm();
	    	preparator1.setId(iid3);
	   		preparator1.setIid(iid);
	   		preparator1.setAmountClaimFormId(id);
	   		preparator1.setIimoney(iimoney-ifmoney);
	   		preparator1.setIfmoney(0.0);
	   		preparator1.setSaleName(EmpEName);
	   		preparator1.setCaseno(time1);
	   		int total=service.insert(preparator1);
	   		
			}else{
			PreparatorEntryForm preparator1=new PreparatorEntryForm();
			preparator1.setIid(iid);
			preparator1.setAmountClaimFormId(id);
			preparator1.setIimoney(iimoney);
			preparator1.setIfmoney(ifmoney);
			preparator1.setSaleName(EmpEName);
			preparator1.setCaseno(time1);
			preparator1.setRemarks(remarks1);
			int total=service.updateAll(preparator1);
			}
		}

		}else{
			wrong1="实际进账填写错误";
		}
		response.sendRedirect("/ERP-NBEmail/helpServlet?action=lookEnterFinancialEntry&className=AmountClaimFormServlet&allMoney="+allMoney+"&id="+id+"&customerId="+customerId+"&&wrong="+wrong1);
		
		}
	/**
	 * 
	 * @Title:PreparatorEntryFormServlet
	 * @Description:财务进账审批页
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void financialContributionApproval(HttpServletRequest request, HttpServletResponse response)
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
		List<PreparatorEntryForm>cuslist=service.getAll();
		request.setAttribute("cuslist", cuslist);
		request.getRequestDispatcher("jsp/financial_contribution_approval.jsp").forward(request, response);
	}
	/**
	 * 
	 * @Title:PreparatorEntryFormServlet
	 * @Description:批量审批
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void batchApproval(HttpServletRequest request, HttpServletResponse response)
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
		String eids= request.getParameter("eids");
		eids=eids.replaceFirst(",", "");
		int total=service.updateModificationResults(eids);
	    int total1=acservice.updateModificationResults(eids);
	    int total2=aeservice.updateModificationResults(eids);
	    int total6=service.addInvoice(eids);
	    int total3=service.updateModifyTemporaryTable(eids);
	    int total5=service.add1(eids);
	    int total4=service.add(eids);
	    if(total>0&&total1>0&&total2>0&&total3>0){
		out.print("YES");
	    }else{
	    	out.print("NO");	
	    }
	}
	
	
	
}
