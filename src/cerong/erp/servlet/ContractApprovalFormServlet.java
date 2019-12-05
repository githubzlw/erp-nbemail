package cerong.erp.servlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerong.erp.entity.ContractApprovalForm;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.service.ContractApprovalFormServiceImpl;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IContractApprovalFormService;
import cerong.erp.service.IEmployeeServiceImpl;



public class ContractApprovalFormServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IContractApprovalFormService service=new ContractApprovalFormServiceImpl();
	IEmployeeServiceImpl eservice = new EmployeeService();
	
	/**
	 * 方法描述:对正在审批中的项目进行催款
	 * author:wy
	 * date:2016年5月30日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void examinationApprovalContracts(HttpServletRequest request, HttpServletResponse response)
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
			
		if(index1!=false){	
	    ContractApprovalForm cp =new ContractApprovalForm();
	    String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
		if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
			
			cp.setCaseNo(condition);
		}
		/*String conferenceMessage1 = request.getParameter("conferenceMessagea");//获取下拉框输入的条件选项值
		int  number1 = -1;
		if(conferenceMessage1 != null && !"".equals(conferenceMessage1)) {//说明有输入条件内容做查询
			number1 = Integer.parseInt(conferenceMessage1);
			if("-1".equals(conferenceMessage1)){
				request.setAttribute("fyzt", 0);
				}else if("0".equals(conferenceMessage1)){
				request.setAttribute("fyzt", 1);	
				}else if("1".equals(conferenceMessage1)){
					request.setAttribute("fyzt", 2);	
				}else if("2".equals(conferenceMessage1)){
					request.setAttribute("fyzt", 3);	
				}else if("3".equals(conferenceMessage1)){
					request.setAttribute("fyzt", 4);	
				}
			
			cp.setDealWith(number1);
		}else{
			cp.setDealWith(-1);
		}*/

	    cp.setSaleName(EmpEName);
		List <ContractApprovalForm> cusList =service.getAll(cp);
		request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
		//将客户信息保存（里面保存了客户的三个邮箱地址）
		request.getRequestDispatcher("jsp/examination_approval_contracts.jsp").forward(request, response);
		}else{
			ContractApprovalForm cp =new ContractApprovalForm();
			cp.setSaleName(EmpEName);
			String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
			if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
				
				cp.setCaseNo(condition);
			}
			String conferenceMessage1 = request.getParameter("conferenceMessagea");//获取下拉框输入的条件选项值
			int  number1 = -1;
			if(conferenceMessage1 != null && !"".equals(conferenceMessage1)) {//说明有输入条件内容做查询
				number1 = Integer.parseInt(conferenceMessage1);
				if("-1".equals(conferenceMessage1)){
					request.setAttribute("fyzt", 0);
					}else if("0".equals(conferenceMessage1)){
					request.setAttribute("fyzt", 1);	
					}else if("1".equals(conferenceMessage1)){
						request.setAttribute("fyzt", 2);	
					}else if("2".equals(conferenceMessage1)){
						request.setAttribute("fyzt", 3);	
					}else if("3".equals(conferenceMessage1)){
						request.setAttribute("fyzt", 4);	
					}
				
				cp.setDealWith(number1);
			}else{
				cp.setDealWith(-1);
			}
			List <ContractApprovalForm> cusList =service.getContracts(cp);
			request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
			//将客户信息保存（里面保存了客户的三个邮箱地址）
			request.getRequestDispatcher("jsp/examination_approval_contracts.jsp").forward(request, response);
			
		}
		}
	
	}
}
