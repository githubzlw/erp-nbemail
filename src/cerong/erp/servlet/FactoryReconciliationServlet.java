package cerong.erp.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cerong.erp.entity.EmailUser;
import cerong.erp.entity.FactoryReconciliation;
import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.FactoryReconciliationService;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IFactoryReconciliationServiceImpl;
import cerong.erp.util.PathUtil;



public class FactoryReconciliationServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IEmployeeServiceImpl service = new EmployeeService();
	IFactoryReconciliationServiceImpl fservice = new FactoryReconciliationService();
	/**
	 * 方法描述:查看月财务报表
	 * author:wy
	 * date:2017年8月15日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void ViewFactoryData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=null;
		String pwd=null;
		Cookie c[]=request.getCookies();
		if(c!=null)
		{
			for(int x=0;x<c.length;x++){
				if(c[x].getName().equals("SESSION_LOGIN_PASSWORD")){
					pwd=c[x].getValue();
				}
                if(c[x].getName().equals("SESSION_LOGIN_USERNAME"))
				{name=c[x].getValue();
				}
          }
		}
		String time=request.getParameter("time");
		if(time!=null&&!"".equalsIgnoreCase(time)){
			request.setAttribute("time",time);
		}else{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.MONTH, -1);    //得到前一个月
			time= format.format(ca.getTime());
			request.setAttribute("time",time);
		}
		String[]yeara=time.split("-");
		String year1=yeara[0];
		String month1=yeara[1];
		int year=0;
		int month=0;
		if(year1!=null&&!"".equals(year1)){
			year= Integer.parseInt(year1);
		}
		if(month1!=null&&!"".equals(month1)){
			month= Integer.parseInt(month1);
		}
		int total=service.getUser(pwd,name);
		if(total>0){
			 String s1 = "mandymanlisaliShiGuoJuanroseli";
		    Boolean index1=false;
			index1 = s1.toLowerCase().contains(name.toLowerCase());
			 if(index1 !=false){
				 File storefile = new File(PathUtil.FinancialStatement,"inancialStatement.xls");
					
					for(int i=0;storefile.exists();i++){
						storefile.delete();
					}
				List<FactoryReconciliation> list=fservice.getall(month,year);
					if(list.size()>0) {
						// 第一步，创建一个webbook，对应一个Excel文件
						HSSFWorkbook wb = new HSSFWorkbook();
						// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
						HSSFSheet sheet = wb.createSheet("月财务对照表");
						// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
						HSSFRow row = sheet.createRow((int) 0);
						// 第四步，创建单元格，并设置值表头 设置表头居中
						HSSFCellStyle style = wb.createCellStyle();
						style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
						HSSFCell cell = row.createCell((short) 0);
						cell.setCellValue("金蝶号");
						cell.setCellStyle(style);
						cell = row.createCell((short) 1);
						cell.setCellValue("工厂名");
						cell.setCellStyle(style);
						cell = row.createCell((short) 2);
						cell.setCellValue("本期支付工厂");
						cell.setCellStyle(style);
						cell = row.createCell((short) 3);
						cell.setCellValue("本地收回发票");
						cell.setCellStyle(style);
						cell = row.createCell((short) 4);
						cell.setCellValue("期末余额");
						cell.setCellStyle(style);
						cell = row.createCell((short) 5);
						cell.setCellValue("2007期末余额");
						cell.setCellStyle(style);
						for (int i = 0; i < list.size(); i++) {
							row = sheet.createRow((int) i + 1);
							FactoryReconciliation sc = list.get(i);
							// 第四步，创建单元格，并设置值

							row.createCell((short) 0).setCellValue((Integer) sc.getKingdee());
							row.createCell((short) 1).setCellValue(sc.getFactoryName());
							row.createCell((short) 2).setCellValue((double) sc.getAmountCredit());
							row.createCell((short) 3).setCellValue((double) sc.getCurrentDebitAmount());
							row.createCell((short) 4).setCellValue((double) sc.getEndingBalance());
							row.createCell((short) 5).setCellValue((double) sc.getPrice());


						}
						// 第六步，将文件存到指定位置
						try {
							FileOutputStream fout = new FileOutputStream(PathUtil.FinancialStatement + File.separator + "inancialStatement.xls");
							wb.write(fout);
							fout.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				request.setAttribute("list", list);
				request.setAttribute("month", month);
				request.setAttribute("year", year);
				request.getRequestDispatcher("jsp/financial_statement.jsp").forward(request, response);
			 }
		}
	}
	/**
	 * 方法描述:查看年财务报表
	 * author:wy
	 * date:2017年8月16日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void AnnualFinancialStatement(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String year1=request.getParameter("year");
		int year=0;
		if(year1 != null && !"".equals(year1)) {
			year= Integer.parseInt(year1);
		}
		int total=service.getUser(pwd,name);
		if(total>0){
			 String s1 = "cashier";
		    Boolean index1=false;
			index1 = s1.toLowerCase().contains(name.toLowerCase());
			 if(index1 !=false){
				 
				 File storefile = new File(PathUtil.FinancialStatement,"AnnualFinancialStatement.xls");
					
					for(int i=0;storefile.exists();i++){
						storefile.delete();
					}
				List<FactoryReconciliation> list=fservice.getall1(year);
				
				 // 第一步，创建一个webbook，对应一个Excel文件  
		        HSSFWorkbook wb = new HSSFWorkbook();  
		        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
		        HSSFSheet sheet = wb.createSheet("年财务报表");  
		        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		        HSSFRow row = sheet.createRow((int) 0);  
		        // 第四步，创建单元格，并设置值表头 设置表头居中  
		        HSSFCellStyle style = wb.createCellStyle();  
		        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		  
		        HSSFCell cell = row.createCell((short) 0);  
		         
		        cell.setCellValue("金蝶号");  
		        cell.setCellStyle(style);  
		        cell = row.createCell((short) 1);  
		        cell.setCellValue("工厂名");  
		        cell.setCellStyle(style);  
		        cell = row.createCell((short) 2);  
		        cell.setCellValue("本期支付工厂");  
		        cell.setCellStyle(style); 
		        cell = row.createCell((short) 3);  
		        cell.setCellValue("本地收回发票");  
		        cell.setCellStyle(style); 
		        cell = row.createCell((short) 4);  
		        cell.setCellValue("期末余额");  
		        cell.setCellStyle(style); 
		        cell = row.createCell((short) 5);  
		        cell.setCellValue("2007期末余额");  
		        cell.setCellStyle(style); 
		       for (int i = 0; i < list.size(); i++)  
		        {  
		            row = sheet.createRow((int) i + 1);  
		            FactoryReconciliation sc=list.get(i);
		            // 第四步，创建单元格，并设置值  
		           
		            row.createCell((short) 0).setCellValue((Integer)sc.getKingdee());  
		            row.createCell((short) 1).setCellValue(sc.getFactoryName());  
		            row.createCell((short) 2).setCellValue((double)sc.getAmountCredit());  
		            row.createCell((short) 3).setCellValue((double)sc.getCurrentDebitAmount());  
		            row.createCell((short) 4).setCellValue((double)sc.getEndingBalance());  
		            row.createCell((short) 5).setCellValue((double)sc.getPrice());  
		          
		             
		        } 
		       // 第六步，将文件存到指定位置  
		        try  
		        {  
		            FileOutputStream fout = new FileOutputStream(PathUtil.FinancialStatement+File.separator+"AnnualFinancialStatement.xls");  
		            wb.write(fout);  
		            fout.close();  
		        }  
		        catch (Exception e)  
		        {  
		            e.printStackTrace();  
		        } 
				request.setAttribute("list", list);
				request.getRequestDispatcher("jsp/annual_financial_statement.jsp").forward(request, response);
			 }
		}
	}
	/**
	 * 
	 * @Title:FactoryReconciliationServlet
	 * @Description:查询到账详情
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void enquiryDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String month1 = request.getParameter("month");
		String year1 = request.getParameter("year");
		String kingdee1 = request.getParameter("kingdee");
		String name1 = null;
		String pwd = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					pwd = c[x].getValue();
				}
				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					name = c[x].getValue();
				}
			}
		}
		int total = service.getUser(pwd, name);
		if (total > 0) {
			int year = 0;
			if (year1 != null && !"".equals(year1)) {
				year = Integer.parseInt(year1);
			}
			int month = 0;
			if (month1 != null && !"".equals(month1)) {
				month = Integer.parseInt(month1);
			}
			int kingdee = 0;
			if (kingdee1 != null && !"".equals(kingdee1)) {
				kingdee = Integer.parseInt(kingdee1);
			}
			List<FactoryReconciliation> list = fservice.getAll(year, month, kingdee);
			request.setAttribute("list", list);
			request.setAttribute("kingdee", kingdee);
           request.getRequestDispatcher("jsp/enquiry_details.jsp").forward(request, response);
        }
	   else{
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * 修改创建时间
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateCreateTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");
		String createTime=request.getParameter("createTime");

        int total=fservice.updateCreateTime(id,createTime);
        if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
	}
	}

