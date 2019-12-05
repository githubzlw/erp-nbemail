package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cerong.erp.entity.ContractMaturity;
import cerong.erp.entity.CustomerInfo;
import cerong.erp.entity.EmailUser;
import cerong.erp.service.CheckUpService;
import cerong.erp.service.ContractMaturityService;
import cerong.erp.service.ICheckUpServiceImpl;
import cerong.erp.service.IContractMaturityServiceImpl;


public class ContractMaturityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IContractMaturityServiceImpl service = new ContractMaturityService(); 
	/**
	  * 方法描述:查询全部到期合同数
	 * author:wy
	 * date:2016年11月9日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void contractMaturity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String st = request.getParameter("page");
		CustomerInfo info = new CustomerInfo();
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1=format.format(date);
		Long millionSeconds = format.parse(time1).getTime();
		List<ContractMaturity>list=service.getall(millionSeconds);
		request.setAttribute("list",list);
	   request.getRequestDispatcher("jsp/contract-maturity.jsp").forward(request, response);
	}
	
	

}
