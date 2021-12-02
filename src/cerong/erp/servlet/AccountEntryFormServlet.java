
package cerong.erp.servlet;

import cerong.erp.entity.*;
import cerong.erp.service.*;
import cerong.erp.util.AccountEntryUpLoad;
import cerong.erp.util.CheckUtil;
import cerong.erp.util.PathUtil;
import cerong.erp.util.ReadExcelUtils;
import com.ibm.icu.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;


public class AccountEntryFormServlet extends HttpServlet {
	IAmountClaimFormService acservice = new AmountClaimFormServiceImpl();
	IAccountEntryFormService service = new AccountEntryFormServiceImpl();
	IPreparatorEntryFormService pservice = new PreparatorEntryFormServiceImpl();
	ItCaseIdServiceImpl iservice = new ItCaseIdService();

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:进入到账录入页面
	 * @author wangyang
	 */

	public void accounEntry(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		if (user1 != null) {

			String s2 = "ands";
			Boolean index2 = false;
			index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());
			if (index2 != false) {
				request.setAttribute("roleNO", 99);
			} else {
				request.setAttribute("roleNO", 5);
			}
			String s1 = "mandymanlisaliShiGuoJuanroseli";
			Boolean index1 = false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if (index1 != false) {
				request.setAttribute("roleNO", 100);
			}
			List<AccountEntryForm> list = service.accounEntry();
			request.setAttribute("cusList", list);
			request.getRequestDispatcher("jsp/accoun_entry.jsp").forward(request, response);
		}

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:查看已审批列表页
	 * @author wangyang
	 */
	public void completionOfMoney(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		if (user1 != null) {
			String s1 = "mandymanlisaliShiGuoJuanroseli";
			Boolean index1 = false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if (index1 != false) {
				request.setAttribute("roleNO", 100);
			}
//			String bank = request.getParameter("bank");
			String time1 = request.getParameter("time1");
			String time2 = request.getParameter("time2");
			AccountEntryForm accountEntryForm = new AccountEntryForm();
//			if (bank != null && !"".equalsIgnoreCase(bank)) {
//				bank = new String(bank.getBytes("iso-8859-1"), "UTF-8");
//				accountEntryForm.setBeneficiaryAccountBank(bank);
//				request.setAttribute("bank", bank);
//			}
			if (time1 != null && !"".equalsIgnoreCase(time1)) {
				accountEntryForm.setTransactionDate(time1);
				request.setAttribute("time1", time1);
			}
			if (time2 != null && !"".equalsIgnoreCase(time2)) {
				accountEntryForm.setTransactionEndDate(time2);
				request.setAttribute("time2", time2);
			}

			List<AccountEntryForm> list = service.completionOfMoney(accountEntryForm,"1");

			// excel 下载
			File storefile = new File(PathUtil.FirstParagraph,"completionData.xls");

//			for(int i=0;storefile.exists();i++){
//				storefile.delete();
//			}
			if(storefile.exists()){
				storefile.delete();
			}
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("美元待核查");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			HSSFCell cell = row.createCell((short) 0);

			cell.setCellValue("付款人名称");
			cell.setCellStyle(style);
			cell = row.createCell((short) 1);
			cell.setCellValue("交易日期");
			cell.setCellStyle(style);
			cell = row.createCell((short) 2);
			cell.setCellValue("货币");
			cell.setCellStyle(style);
			cell = row.createCell((short) 3);
			cell.setCellValue("交易金额");
			cell.setCellStyle(style);
			cell = row.createCell((short) 4);
			cell.setCellValue("项目号");
			cell.setCellStyle(style);
			cell = row.createCell((short) 5);
			cell.setCellValue("出口日期");
			cell.setCellStyle(style);
			cell = row.createCell((short) 6);
			cell.setCellValue("出口国家");
			cell.setCellStyle(style);
			cell = row.createCell((short) 7);
			cell.setCellValue("到款银行");
			cell.setCellStyle(style);
			cell = row.createCell((short) 8);
			cell.setCellValue("银行流水号");
			cell.setCellStyle(style);
//			cell.setCellValue("原因");
//			cell.setCellStyle(style);

//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

			for (int i = 0; i < list.size(); i++)
			{
				row = sheet.createRow((int) i + 1);
				AccountEntryForm sc=list.get(i);
				// 第四步，创建单元格，并设置值
				row.createCell((short) 0).setCellValue(sc.getPayersName());
				row.createCell((short) 1).setCellValue(sc.getTransactionDate());
				row.createCell((short) 2).setCellValue(sc.getTradeCurrency());
				row.createCell((short) 3).setCellValue(sc.getTradeAmount());


				List<AmountClaimForm> amountClaimFormList=sc.getAmountClaimForm();
				String invoiceS="";
				HashMap map = new HashMap();
				for (int j = 0; j < amountClaimFormList.size(); j++){
					AmountClaimForm amountClaimForm=amountClaimFormList.get(j);

					invoiceS = amountClaimForm.getInvoice().replaceAll("INV","SHS").replaceAll("inv","SHS");
					invoiceS = invoiceS.substring(0,invoiceS.length()-1);

					if(!map.containsKey(invoiceS)){
						map.put(invoiceS,invoiceS);
					}
					row.createCell((short) 5).setCellValue(amountClaimForm.getExportYear()+"-"+amountClaimForm.getExportMonth());
					row.createCell((short) 6).setCellValue(CheckUtil.getCountyName(amountClaimForm.getCountry()));
				}

				List<String> result = new ArrayList(map.keySet());
				row.createCell((short) 4).setCellValue(StringUtils.strip(result.toString().trim(),"[]").replaceAll(", ","/"));

				row.createCell((short) 7).setCellValue(sc.getBeneficiaryAccountBank());
				row.createCell((short) 8).setCellValue(sc.getTransactionReferenceNumber());

			}
			// 第六步，将文件存到指定位置
			try
			{
				FileOutputStream fout = new FileOutputStream(PathUtil.FirstParagraph+File.separator+"completionData.xls");
				wb.write(fout);
				fout.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}


			//发票明细
			this.completionOfMoneyInv(accountEntryForm);


			request.setAttribute("cusList", list);
			request.getRequestDispatcher("jsp/completion_of_money.jsp").forward(request, response);
		}

	}

	public void completionOfMoneyInv(AccountEntryForm accountEntryForm)
			throws ServletException, IOException {

			List<AccountEntryForm> listInv = service.completionOfMoney(accountEntryForm,"2");
			// excel 下载
			File storefile = new File(PathUtil.FirstParagraph,"completionDataInv.xls");


//			for(int i=0;storefile.exists();i++){
//				storefile.delete();
//			}
			if(storefile.exists()){
				storefile.delete();
			}
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("美元待核查");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			HSSFCell cell = row.createCell((short) 0);

			cell.setCellValue("付款人名称");
			cell.setCellStyle(style);
			cell = row.createCell((short) 1);
			cell.setCellValue("交易日期");
			cell.setCellStyle(style);
			cell = row.createCell((short) 2);
			cell.setCellValue("货币");
			cell.setCellStyle(style);
			cell = row.createCell((short) 3);
			cell.setCellValue("总到款");
			cell.setCellStyle(style);
			cell = row.createCell((short) 4);
			cell.setCellValue("分到的Invoice号");
			cell.setCellStyle(style);
			cell = row.createCell((short) 5);
			cell.setCellValue("出口日期");
			cell.setCellStyle(style);
			cell = row.createCell((short) 6);
			cell.setCellValue("出口国家");
			cell.setCellStyle(style);
			cell = row.createCell((short) 7);
			cell.setCellValue("到款银行");
			cell.setCellStyle(style);
			cell = row.createCell((short) 8);
			cell.setCellValue("银行流水号");
			cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("分到此Invoice的金额");
		cell.setCellStyle(style);

			for (int i = 0; i < listInv.size(); i++)
			{
				row = sheet.createRow((int) i + 1);
				AccountEntryForm sc=listInv.get(i);
				// 第四步，创建单元格，并设置值
				row.createCell((short) 0).setCellValue(sc.getPayersName());
				row.createCell((short) 1).setCellValue(sc.getTransactionDate());
				row.createCell((short) 2).setCellValue(sc.getTradeCurrency());
				row.createCell((short) 3).setCellValue(sc.getTradeAmount());
				row.createCell((short) 4).setCellValue(sc.getInvoice());
				row.createCell((short) 5).setCellValue(sc.getExportYear()+"-"+sc.getExportMonth());
				row.createCell((short) 6).setCellValue(CheckUtil.getCountyName(sc.getCountry()));
				row.createCell((short) 7).setCellValue(sc.getBeneficiaryAccountBank());
				row.createCell((short) 8).setCellValue(sc.getTransactionReferenceNumber());
				row.createCell((short) 9).setCellValue(sc.getSumMoney());

			}
			// 第六步，将文件存到指定位置
			try
			{
				FileOutputStream fout = new FileOutputStream(PathUtil.FirstParagraph+File.separator+"completionDataInv.xls");
				wb.write(fout);
				fout.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}


	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:上传excel表到数据库
	 * @author wangyang
	 */
	public void UploadForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountEntryUpLoad.upload(request, response);
		String payBank = (String) request.getAttribute("payBank");
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		AccountEntryForm account = new AccountEntryForm();
		String fileName = "";
		Map<String, String> map = (Map<String, String>) request.getAttribute("map");
		//准备参数map
		if (map != null && map.size() > 0) {//说明之前的附件没有检测到敏感字符  将所有的附件信息保存到数据库
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				String value = map.get(key);
				fileName = value;
			}
		}
		String path = PathUtil.FirstParagraph;
		File storefile = new File(path, fileName);
		String filepath = path + File.separator + fileName;
		ReadExcelUtils excelReader = new ReadExcelUtils(filepath);

		try {

			// 对读取Excel表格内容测试
			Map<Integer, Map<Integer, Object>> map1 = excelReader.readExcelContent();
			for (int name : map1.keySet()) {
				int key = name;
				Map<Integer, Object> map2 = map1.get(key);
				for (int name1 : map2.keySet()) {
					int keya = name1;
					String value = (String) map2.get(keya);
					if (keya == 4) {
						account.setPayeeAccount(value);
					} else if (keya == 5) {
						value = value.replaceAll(",", "");
						account.setPayersName(value);
					} else if (keya == 7) {
						account.setBeneficiaryAccountBank(value);
					} else if (keya == 10) {
						account.setTransactionDate(value);
					} else if (keya == 12) {
						account.setTradeCurrency(value);
					} else if (keya == 13) {
						double id = 0.000;//默认是第一页
						if (value != null && !"".equals(value)) {
							id = Double.parseDouble(value);
						}
						account.setTradeAmount(id);
					} else if (keya == 17) {
						account.setTransactionReferenceNumber(value);
					} else if (keya == 25) {
						account.setRemark(value);
					}

				}
				String PayersName = account.getPayersName();

				ArrivalAccountCorrespondenceTable arrival = service.getall(PayersName);
				if (arrival != null) {
					account.setnBEmailId(arrival.getCustomerId());
					String Conjecture = "";
					if (arrival.getCustomerManager() != null && !"".equalsIgnoreCase(arrival.getCustomerManager()) && !"null".equalsIgnoreCase(arrival.getCustomerManager())) {
						Conjecture += arrival.getCustomerManager();
					}
					if (arrival.getMerchandManager1() != null && !"".equalsIgnoreCase(arrival.getMerchandManager1()) && !"null".equalsIgnoreCase(arrival.getMerchandManager1())) {
						Conjecture += ";" + arrival.getMerchandManager1();
					}
					if (arrival.getMerchandising() != null && !"".equalsIgnoreCase(arrival.getMerchandising()) && !"null".equalsIgnoreCase(arrival.getMerchandising())) {
						Conjecture += ";" + arrival.getMerchandising();
					}
					account.setConjecture(Conjecture);
				} else {
					account.setnBEmailId(0);
					account.setConjecture(null);
				}
				service.add(account);
			}

			response.sendRedirect("/ERP-NBEmail/helpServlet?action=accounEntry&className=AccountEntryFormServlet");
		} catch (Exception e) {

		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:信息还原
	 * @author wangyang
	 */
	public void recoveryInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);

		String id1 = request.getParameter("id");
		int id = 0;
		if (id1 != null && !"".equals(id1)) {
			id = Integer.parseInt(id1);
		}
		int total1 = service.recoveryInformation(id);
		int total2 = acservice.recoveryInformation(id);
		int total3 = pservice.recoveryInformation(id);
		if (user1 != null) {
			String s1 = "mandymanlisaliShiGuoJuanroseli";
			Boolean index1 = false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if (index1 != false) {
				request.setAttribute("roleNO", 100);
			}
			List<AccountEntryForm> list = service.accounEntry();
			request.setAttribute("cusList", list);
			request.getRequestDispatcher("jsp/accoun_entry.jsp").forward(request, response);
		}

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:客户关联表录入
	 * @author wangyang
	 */
	public void insertCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		if (user1 != null) {
			String s1 = "mandymanlisaliShiGuoJuanroseli";
			Boolean index1 = false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if (index1 != false) {
				request.getRequestDispatcher("jsp/insert_customer.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:批量认领数据
	 * @author wangyang
	 */
	public void insertEnteryCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		String customerName = request.getParameter("customerName");
		String kingdee1 = request.getParameter("kingdee");
		String kingName = request.getParameter("kingName");
		String CasNo = request.getParameter("CasNo");
		String country1 = request.getParameter("country1");
		String abbreviation = request.getParameter("abbreviation");
		int kingdee = 0;
		if (kingdee1 != null && !"".equals(kingdee1)) {
			kingdee = Integer.parseInt(kingdee1);
		}
		int country = 0;
		if (country1 != null && !"".equals(country1)) {
			country = Integer.parseInt(country1);
		}

		ItemCase it = iservice.getall(CasNo);
		ArrivalAccountCorrespondenceTable account = new ArrivalAccountCorrespondenceTable();
		account.setCustomerId(it.getCid());
		account.setName(customerName);
		account.setProjectId(CasNo);
		account.setKingdee(kingdee);
		account.setKingName(kingName);
		account.setCountry(country);
		account.setAbbreviation(abbreviation);
		int total = service.addAccount(account);
		if (total > 0) {
			out.write("<script>");
			out.write("alert('成功录入客户关系');");
			out.write("window.location.href='/ERP-NBEmail/helpServlet?action=enterTheCustomerRelevanceTableIntoTheAccount&className=InvoiceServlet'");
			out.write("</script>");
		} else {
			out.write("<script>");
			out.write("alert('录入客户关系失败');");
			out.write("window.location.href='/ERP-NBEmail/helpServlet?action=enterTheCustomerRelevanceTableIntoTheAccount&className=InvoiceServlet'");
			out.write("</script>");
		}

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:删除数据
	 * @author wangyang
	 */
	public void deleteAccountEntry(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		String id1 = request.getParameter("id");
		int id = 0;
		if (id1 != null && !"".equals(id1)) {
			id = Integer.parseInt(id1);
		}
		int total = service.deleteAccount(id);
		if (total > 0) {
			out.write("YES");
		} else {
			out.write("NO");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException      void
	 * @throws
	 * @Title:AccountEntryFormServlet
	 * @Description:修改客户属性
	 * @author wangyang
	 */
	public void updateAccountEntry(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String EmpEName = null;
		String EmpPWD = null;
		Cookie c[] = request.getCookies();
		if (c != null) {
			for (int x = 0; x < c.length; x++) {
				if (c[x].getName().equals("SESSION_LOGIN_PASSWORD")) {
					EmpPWD = c[x].getValue();
				}

				if (c[x].getName().equals("SESSION_LOGIN_USERNAME")) {
					EmpEName = c[x].getValue();
				}
			}
		}
		EmailUser user1 = new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		String id1 = request.getParameter("id");
		String num1 = request.getParameter("num");
		String allreason = request.getParameter("allreason");

		AccountEntryForm entry = new AccountEntryForm();
		int id = 0;
		if (id1 != null && !"".equals(id1)) {
			id = Integer.parseInt(id1);
			entry.setId(id);
		}
		int num = 0;
		if (num1 != null && !"".equals(num1)) {
			num = Integer.parseInt(num1);
			entry.setNewCustomer(num);
		} else {
			entry.setNewCustomer(-1);
		}
		if (allreason != null && !"".equalsIgnoreCase(allreason)) {
			allreason = new String(allreason.getBytes("iso-8859-1"), "utf-8");
			entry.setReason(allreason);
		}
		entry.setEntryPerson(EmpEName);
		int total = service.updateAccountEntry(entry);
		if (total > 0) {
			out.write("YES");
		} else {
			out.write("NO");
		}
	}

			/**
             *
             * @Title:AccountEntryFormServlet
             * @Description:导出excel表
               @author wangyang
             * @param request
             * @param response
             * @throws ServletException
             * @throws IOException void
             * @throws
             */
	public void exportReceiptForm(HttpServletRequest request, HttpServletResponse response)
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
		request.setAttribute("user", user1);
		String fNumber=request.getParameter("fNumber");
		String time1=request.getParameter("time1");
		String exchangeRate=request.getParameter("exchangeRate");
		if(fNumber!=null&&!"".equals(fNumber)&&exchangeRate!=null&&!"".equals(exchangeRate)&&!"".equals(time1)&&time1!=null){
		int fNumber1=0;
		if(fNumber!=null&&!"".equals(fNumber)){
			fNumber1=Integer.parseInt(fNumber);
		}
		double exchangeRate1=0.000;
		if(exchangeRate!=null&&!"".equals(exchangeRate)){
			exchangeRate1=Double.parseDouble(exchangeRate);
		}
		File storefile = new File(PathUtil.FinancialStatement,"ExportReceiptForm.xlsx");
		for(int i=0;storefile.exists();i++){
			storefile.delete();
		}
	   //
	    XSSFWorkbook wb1 = new XSSFWorkbook();  
        XSSFSheet sheet1 = wb1.createSheet("t_Schema");  
        XSSFRow row1 = sheet1.createRow((int) 0);  
        XSSFCellStyle style1 = wb1.createCellStyle();  
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        XSSFCell cell1 = row1.createCell((short) 0);  
        cell1.setCellValue("FType");  
        cell1.setCellStyle(style1);  
        cell1 = row1.createCell((short) 1);  
        cell1.setCellValue("FKey");  
        cell1.setCellStyle(style1);  
        cell1 = row1.createCell((short) 2);
        cell1.setCellValue("FFieldName");  
        cell1.setCellStyle(style1);  
        cell1 = row1.createCell((short) 3);
        cell1.setCellValue("FCaption");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 4);  
        cell1.setCellValue("FValueType");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 5);  
        cell1.setCellValue("FNeedSave");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 6);  
        cell1.setCellValue("FColIndex");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 7);
        cell1.setCellValue("FSrcTableName");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 8);
        cell1.setCellValue("FSrcFieldName");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 9);
        cell1.setCellValue("FExpFieldName");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 10);
        cell1.setCellValue("FImpFieldName");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 11);
        cell1.setCellValue("FDefaultVal");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 12);
        cell1.setCellValue("FSearch");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 13);
        cell1.setCellValue("FItemPageName");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 14);
        cell1.setCellValue("FTrueType");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 15);
        cell1.setCellValue("FPrecision");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 16);
        cell1.setCellValue("FSearchName");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 17);
        cell1.setCellValue("FIsShownList");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 18);
        cell1.setCellValue("FViewMask");  
        cell1.setCellStyle(style1); 
        cell1 = row1.createCell((short) 19);
        cell1.setCellValue("FPage");  
        cell1.setCellStyle(style1); 
        for(int i=0;i<39;i++){
        	row1 = sheet1.createRow((int) i+1);
        if(i==0){
        row1.createCell((short) 0).setCellValue("ClassInfo");  
        row1.createCell((short) 1).setCellValue("ClassType");
        row1.createCell((short) 2).setCellValue("");
        row1.createCell((short) 3).setCellValue("VoucherData");
        row1.createCell((short) 4).setCellValue("");
        row1.createCell((short) 5).setCellValue("");
        row1.createCell((short) 6).setCellValue("");
        row1.createCell((short) 7).setCellValue("");
        row1.createCell((short) 8).setCellValue("");
        row1.createCell((short) 9).setCellValue("");
        row1.createCell((short) 10).setCellValue("");
        row1.createCell((short) 11).setCellValue("");
        row1.createCell((short) 12).setCellValue("");
        row1.createCell((short) 13).setCellValue("");
        row1.createCell((short) 14).setCellValue("");
        row1.createCell((short) 15).setCellValue("");
        row1.createCell((short) 16).setCellValue("");
        row1.createCell((short) 17).setCellValue("");
        row1.createCell((short) 18).setCellValue("");
        row1.createCell((short) 19).setCellValue("");
        }else if(i==1){
        	row1.createCell((short) 0).setCellValue("ClassInfo");  
            row1.createCell((short) 1).setCellValue("ClassTypeID");
            row1.createCell((short) 2).setCellValue("");
            row1.createCell((short) 3).setCellValue("ExportReceiptForm");
            row1.createCell((short) 4).setCellValue("");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("");
            row1.createCell((short) 10).setCellValue("");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("");
            row1.createCell((short) 16).setCellValue("");
            row1.createCell((short) 17).setCellValue("");
            row1.createCell((short) 18).setCellValue("");
            row1.createCell((short) 19).setCellValue("");	
        }else if(i==2){
        	row1.createCell((short) 0).setCellValue("PageInfo");  
            row1.createCell((short) 1).setCellValue("Page1");
            row1.createCell((short) 2).setCellValue("");
            row1.createCell((short) 3).setCellValue("Page1");
            row1.createCell((short) 4).setCellValue("");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("");
            row1.createCell((short) 10).setCellValue("");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("");
            row1.createCell((short) 16).setCellValue("");
            row1.createCell((short) 17).setCellValue("");
            row1.createCell((short) 18).setCellValue("");
            row1.createCell((short) 19).setCellValue("");	
        }else if(i==3){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FDate");
            row1.createCell((short) 2).setCellValue("FDate");
            row1.createCell((short) 3).setCellValue("凭证日期");
            row1.createCell((short) 4).setCellValue("DateTime");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("1");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FDate");
            row1.createCell((short) 10).setCellValue("FDate");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==4){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FYear");
            row1.createCell((short) 2).setCellValue("FYear");
            row1.createCell((short) 3).setCellValue("会计年度");
            row1.createCell((short) 4).setCellValue("Decimal(23,10)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("2");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FYear");
            row1.createCell((short) 10).setCellValue("FYear");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==5){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FPeriod");
            row1.createCell((short) 2).setCellValue("FPeriod");
            row1.createCell((short) 3).setCellValue("会计期间");
            row1.createCell((short) 4).setCellValue("Decimal(23,10)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("3");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FPeriod");
            row1.createCell((short) 10).setCellValue("FPeriod");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==6){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FGroupID");
            row1.createCell((short) 2).setCellValue("FGroupID");
            row1.createCell((short) 3).setCellValue("凭证字");
            row1.createCell((short) 4).setCellValue("Varchar(80)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("4");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FGroupID");
            row1.createCell((short) 10).setCellValue("FGroupID");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==7){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FNumber");
            row1.createCell((short) 2).setCellValue("FNumber");
            row1.createCell((short) 3).setCellValue("凭证号");
            row1.createCell((short) 4).setCellValue("Decimal(23,10)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("5");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FNumber");
            row1.createCell((short) 10).setCellValue("FNumber");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==8){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FAccountNum");
            row1.createCell((short) 2).setCellValue("FAccountNum");
            row1.createCell((short) 3).setCellValue("科目代码");
            row1.createCell((short) 4).setCellValue("Varchar(40)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("7");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FAccountNum");
            row1.createCell((short) 10).setCellValue("FAccountNum");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==9){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FAccountName");
            row1.createCell((short) 2).setCellValue("FAccountName");
            row1.createCell((short) 3).setCellValue("科目名称");
            row1.createCell((short) 4).setCellValue("Varchar(80)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("8");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FAccountName");
            row1.createCell((short) 10).setCellValue("FAccountName");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==10){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FCurrencyNum");
            row1.createCell((short) 2).setCellValue("FCurrencyNum");
            row1.createCell((short) 3).setCellValue("币别代码");
            row1.createCell((short) 4).setCellValue("Varchar(10)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("9");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FCurrencyNum");
            row1.createCell((short) 10).setCellValue("FCurrencyNum");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==11){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FCurrencyName");
            row1.createCell((short) 2).setCellValue("FCurrencyName");
            row1.createCell((short) 3).setCellValue("币别名称");
            row1.createCell((short) 4).setCellValue("Varchar(40)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("10");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FCurrencyName");
            row1.createCell((short) 10).setCellValue("FCurrencyName");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==12){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FAmountFor");
            row1.createCell((short) 2).setCellValue("FAmountFor");
            row1.createCell((short) 3).setCellValue("原币金额");
            row1.createCell((short) 4).setCellValue("Decimal(23,10)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("11");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FAmountFor");
            row1.createCell((short) 10).setCellValue("FAmountFor");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==13){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FDebit");
            row1.createCell((short) 2).setCellValue("FDebit");
            row1.createCell((short) 3).setCellValue("借方");
            row1.createCell((short) 4).setCellValue("Decimal(23,10)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("12");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FDebit");
            row1.createCell((short) 10).setCellValue("FDebit");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==14){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FCredit");
            row1.createCell((short) 2).setCellValue("FCredit");
            row1.createCell((short) 3).setCellValue("贷方");
            row1.createCell((short) 4).setCellValue("Decimal(23,10)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("13");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FCredit");
            row1.createCell((short) 10).setCellValue("FCredit");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==15){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FPreparerID");
            row1.createCell((short) 2).setCellValue("FPreparerID");
            row1.createCell((short) 3).setCellValue("制单");
            row1.createCell((short) 4).setCellValue("Varchar(255)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("14");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FPreparerID");
            row1.createCell((short) 10).setCellValue("FPreparerID");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==16){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FCheckerID");
            row1.createCell((short) 2).setCellValue("FCheckerID");
            row1.createCell((short) 3).setCellValue("审核");
            row1.createCell((short) 4).setCellValue("Varchar(255)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("15");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FCheckerID");
            row1.createCell((short) 10).setCellValue("FCheckerID");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==17){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FApproveID");
            row1.createCell((short) 2).setCellValue("FApproveID");
            row1.createCell((short) 3).setCellValue("核准");
            row1.createCell((short) 4).setCellValue("Varchar(255)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("17");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FApproveID");
            row1.createCell((short) 10).setCellValue("FApproveID");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==18){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FCashierID");
            row1.createCell((short) 2).setCellValue("FCashierID");
            row1.createCell((short) 3).setCellValue("出纳");
            row1.createCell((short) 4).setCellValue("Varchar(255)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("18");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FCashierID");
            row1.createCell((short) 10).setCellValue("FCashierID");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==19){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
            row1.createCell((short) 1).setCellValue("FHandler");
            row1.createCell((short) 2).setCellValue("FHandler");
            row1.createCell((short) 3).setCellValue("经办");
            row1.createCell((short) 4).setCellValue("Varchar(50)");
            row1.createCell((short) 5).setCellValue("");
            row1.createCell((short) 6).setCellValue("19");
            row1.createCell((short) 7).setCellValue("");
            row1.createCell((short) 8).setCellValue("");
            row1.createCell((short) 9).setCellValue("FHandler");
            row1.createCell((short) 10).setCellValue("FHandler");
            row1.createCell((short) 11).setCellValue("");
            row1.createCell((short) 12).setCellValue("0");
            row1.createCell((short) 13).setCellValue("");
            row1.createCell((short) 14).setCellValue("");
            row1.createCell((short) 15).setCellValue("0");
            row1.createCell((short) 16).setCellValue("0");
            row1.createCell((short) 17).setCellValue("0");
            row1.createCell((short) 18).setCellValue("0");
            row1.createCell((short) 19).setCellValue(1);	
        }else if(i==20){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
        	row1.createCell((short) 1).setCellValue("FSettleTypeID");
        	row1.createCell((short) 2).setCellValue("FSettleTypeID");
        	row1.createCell((short) 3).setCellValue("结算方式");
        	row1.createCell((short) 4).setCellValue("Varchar(80)");
        	row1.createCell((short) 5).setCellValue("");
        	row1.createCell((short) 6).setCellValue("20");
        	row1.createCell((short) 7).setCellValue("");
        	row1.createCell((short) 8).setCellValue("");
        	row1.createCell((short) 9).setCellValue("FSettleTypeID");
        	row1.createCell((short) 10).setCellValue("FSettleTypeID");
        	row1.createCell((short) 11).setCellValue("");
        	row1.createCell((short) 12).setCellValue("0");
        	row1.createCell((short) 13).setCellValue("");
        	row1.createCell((short) 14).setCellValue("");
        	row1.createCell((short) 15).setCellValue("0");
        	row1.createCell((short) 16).setCellValue("0");
        	row1.createCell((short) 17).setCellValue("0");
        	row1.createCell((short) 18).setCellValue("0");
        	row1.createCell((short) 19).setCellValue(1);	
        }else if(i==21){
			row1.createCell((short) 0).setCellValue("FieldInfo");  
			row1.createCell((short) 1).setCellValue("FSettleNo");
			row1.createCell((short) 2).setCellValue("FSettleNo");
			row1.createCell((short) 3).setCellValue("结算号");
			row1.createCell((short) 4).setCellValue("Varchar(255)");
			row1.createCell((short) 5).setCellValue("");
			row1.createCell((short) 6).setCellValue("21");
			row1.createCell((short) 7).setCellValue("");
			row1.createCell((short) 8).setCellValue("");
			row1.createCell((short) 9).setCellValue("FSettleNo");
			row1.createCell((short) 10).setCellValue("FSettleNo");
			row1.createCell((short) 11).setCellValue("");
			row1.createCell((short) 12).setCellValue("0");
			row1.createCell((short) 13).setCellValue("");
			row1.createCell((short) 14).setCellValue("");
			row1.createCell((short) 15).setCellValue("0");
			row1.createCell((short) 16).setCellValue("0");
			row1.createCell((short) 17).setCellValue("0");
			row1.createCell((short) 18).setCellValue("0");
			row1.createCell((short) 19).setCellValue(1);	
		}else if(i==22){
		row1.createCell((short) 0).setCellValue("FieldInfo");  
		row1.createCell((short) 1).setCellValue("FExplanation");
		row1.createCell((short) 2).setCellValue("FExplanation");
		row1.createCell((short) 3).setCellValue("凭证摘要");
		row1.createCell((short) 4).setCellValue("Varchar(50)");
		row1.createCell((short) 5).setCellValue("");
		row1.createCell((short) 6).setCellValue("22");
		row1.createCell((short) 7).setCellValue("");
		row1.createCell((short) 8).setCellValue("");
		row1.createCell((short) 9).setCellValue("FExplanation");
		row1.createCell((short) 10).setCellValue("FExplanation");
		row1.createCell((short) 11).setCellValue("");
		row1.createCell((short) 12).setCellValue("0");
		row1.createCell((short) 13).setCellValue("");
		row1.createCell((short) 14).setCellValue("");
		row1.createCell((short) 15).setCellValue("0");
		row1.createCell((short) 16).setCellValue("0");
		row1.createCell((short) 17).setCellValue("0");
		row1.createCell((short) 18).setCellValue("0");
		row1.createCell((short) 19).setCellValue(1);	
	}else if(i==23){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FQuantity");
	row1.createCell((short) 2).setCellValue("FQuantity");
	row1.createCell((short) 3).setCellValue("数量");
	row1.createCell((short) 4).setCellValue("Decimal(23,10)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("23");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FQuantity");
	row1.createCell((short) 10).setCellValue("FQuantity");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==24){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FMeasureUnitID");
	row1.createCell((short) 2).setCellValue("FMeasureUnitID");
	row1.createCell((short) 3).setCellValue("数量单位");
	row1.createCell((short) 4).setCellValue("Varchar(255)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("24");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FMeasureUnitID");
	row1.createCell((short) 10).setCellValue("FMeasureUnitID");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==25){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FUnitPrice");
	row1.createCell((short) 2).setCellValue("FUnitPrice");
	row1.createCell((short) 3).setCellValue("单价");
	row1.createCell((short) 4).setCellValue("Decimal(23,10)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("25");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FUnitPrice");
	row1.createCell((short) 10).setCellValue("FUnitPrice");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==26){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FReference");
	row1.createCell((short) 2).setCellValue("FReference");
	row1.createCell((short) 3).setCellValue("参考信息");
	row1.createCell((short) 4).setCellValue("Varchar(255)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("26");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FReference");
	row1.createCell((short) 10).setCellValue("FReference");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==27){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FTransDate");
	row1.createCell((short) 2).setCellValue("FTransDate");
	row1.createCell((short) 3).setCellValue("业务日期");
	row1.createCell((short) 4).setCellValue("DateTime");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("27");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FTransDate");
	row1.createCell((short) 10).setCellValue("FTransDate");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==28){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FTransNo");
	row1.createCell((short) 2).setCellValue("FTransNo");
	row1.createCell((short) 3).setCellValue("往来业务编号");
	row1.createCell((short) 4).setCellValue("Varchar(40)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("28");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FTransNo");
	row1.createCell((short) 10).setCellValue("FTransNo");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==29){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FAttachments");
	row1.createCell((short) 2).setCellValue("FAttachments");
	row1.createCell((short) 3).setCellValue("附件数");
	row1.createCell((short) 4).setCellValue("Decimal(23,10)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("29");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FAttachments");
	row1.createCell((short) 10).setCellValue("FAttachments");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==30){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FSerialNum");
	row1.createCell((short) 2).setCellValue("FSerialNum");
	row1.createCell((short) 3).setCellValue("序号");
	row1.createCell((short) 4).setCellValue("Decimal(23,10)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("30");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FSerialNum");
	row1.createCell((short) 10).setCellValue("FSerialNum");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==31){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FObjectName");
	row1.createCell((short) 2).setCellValue("FObjectName");
	row1.createCell((short) 3).setCellValue("系统模块");
	row1.createCell((short) 4).setCellValue("Varchar(100)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("31");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FObjectName");
	row1.createCell((short) 10).setCellValue("FObjectName");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==32){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FParameter");
	row1.createCell((short) 2).setCellValue("FParameter");
	row1.createCell((short) 3).setCellValue("业务描述");
	row1.createCell((short) 4).setCellValue("Varchar(100)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("32");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FParameter");
	row1.createCell((short) 10).setCellValue("FParameter");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==33){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FExchangeRate");
	row1.createCell((short) 2).setCellValue("FExchangeRate");
	row1.createCell((short) 3).setCellValue("汇率");
	row1.createCell((short) 4).setCellValue("Decimal(23,10)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("33");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FExchangeRate");
	row1.createCell((short) 10).setCellValue("FExchangeRate");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}else if(i==34){
        	row1.createCell((short) 0).setCellValue("FieldInfo");  
        	row1.createCell((short) 1).setCellValue("FEntryID");
        	row1.createCell((short) 2).setCellValue("FEntryID");
        	row1.createCell((short) 3).setCellValue("分录序号");
        	row1.createCell((short) 4).setCellValue("Decimal(23,10)");
        	row1.createCell((short) 5).setCellValue("");
        	row1.createCell((short) 6).setCellValue("34");
        	row1.createCell((short) 7).setCellValue("");
        	row1.createCell((short) 8).setCellValue("");
        	row1.createCell((short) 9).setCellValue("FEntryID");
        	row1.createCell((short) 10).setCellValue("FEntryID");
        	row1.createCell((short) 11).setCellValue("");
        	row1.createCell((short) 12).setCellValue("0");
        	row1.createCell((short) 13).setCellValue("");
        	row1.createCell((short) 14).setCellValue("");
        	row1.createCell((short) 15).setCellValue("0");
        	row1.createCell((short) 16).setCellValue("0");
        	row1.createCell((short) 17).setCellValue("0");
        	row1.createCell((short) 18).setCellValue("0");
        	row1.createCell((short) 19).setCellValue(1);	
        }else if(i==35){
			row1.createCell((short) 0).setCellValue("FieldInfo");  
			row1.createCell((short) 1).setCellValue("FItem");
			row1.createCell((short) 2).setCellValue("FItem");
			row1.createCell((short) 3).setCellValue("核算项目");
			row1.createCell((short) 4).setCellValue("Varchar(255)");
			row1.createCell((short) 5).setCellValue("");
			row1.createCell((short) 6).setCellValue("35");
			row1.createCell((short) 7).setCellValue("");
			row1.createCell((short) 8).setCellValue("");
			row1.createCell((short) 9).setCellValue("FItem");
			row1.createCell((short) 10).setCellValue("FItem");
			row1.createCell((short) 11).setCellValue("");
			row1.createCell((short) 12).setCellValue("0");
			row1.createCell((short) 13).setCellValue("");
			row1.createCell((short) 14).setCellValue("");
			row1.createCell((short) 15).setCellValue("0");
			row1.createCell((short) 16).setCellValue("0");
			row1.createCell((short) 17).setCellValue("0");
			row1.createCell((short) 18).setCellValue("0");
			row1.createCell((short) 19).setCellValue(1);	
		}else if(i==36){
		row1.createCell((short) 0).setCellValue("FieldInfo");  
		row1.createCell((short) 1).setCellValue("FPosted");
		row1.createCell((short) 2).setCellValue("FPosted");
		row1.createCell((short) 3).setCellValue("过账");
		row1.createCell((short) 4).setCellValue("Decimal(23,10)");
		row1.createCell((short) 5).setCellValue("");
		row1.createCell((short) 6).setCellValue("36");
		row1.createCell((short) 7).setCellValue("");
		row1.createCell((short) 8).setCellValue("");
		row1.createCell((short) 9).setCellValue("FPosted");
		row1.createCell((short) 10).setCellValue("FPosted");
		row1.createCell((short) 11).setCellValue("");
		row1.createCell((short) 12).setCellValue("0");
		row1.createCell((short) 13).setCellValue("");
		row1.createCell((short) 14).setCellValue("");
		row1.createCell((short) 15).setCellValue("0");
		row1.createCell((short) 16).setCellValue("0");
		row1.createCell((short) 17).setCellValue("0");
		row1.createCell((short) 18).setCellValue("0");
		row1.createCell((short) 19).setCellValue(1);	
	}else if(i==37){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FInternalInd");
	row1.createCell((short) 2).setCellValue("FInternalInd");
	row1.createCell((short) 3).setCellValue("机制凭证");
	row1.createCell((short) 4).setCellValue("Varchar(10)");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("37");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FInternalInd");
	row1.createCell((short) 10).setCellValue("FInternalInd");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	

}else if(i==38){
	row1.createCell((short) 0).setCellValue("FieldInfo");  
	row1.createCell((short) 1).setCellValue("FCashFlow");
	row1.createCell((short) 2).setCellValue("FCashFlow");
	row1.createCell((short) 3).setCellValue("现金流量");
	row1.createCell((short) 4).setCellValue("Memo");
	row1.createCell((short) 5).setCellValue("");
	row1.createCell((short) 6).setCellValue("38");
	row1.createCell((short) 7).setCellValue("");
	row1.createCell((short) 8).setCellValue("");
	row1.createCell((short) 9).setCellValue("FCashFlow");
	row1.createCell((short) 10).setCellValue("FCashFlow");
	row1.createCell((short) 11).setCellValue("");
	row1.createCell((short) 12).setCellValue("0");
	row1.createCell((short) 13).setCellValue("");
	row1.createCell((short) 14).setCellValue("");
	row1.createCell((short) 15).setCellValue("0");
	row1.createCell((short) 16).setCellValue("0");
	row1.createCell((short) 17).setCellValue("0");
	row1.createCell((short) 18).setCellValue("0");
	row1.createCell((short) 19).setCellValue(1);	
}
	 }
      
         
        XSSFSheet sheet = wb1.createSheet("Page1");  
        XSSFRow row = sheet.createRow((int) 0);  
        XSSFCellStyle style = wb1.createCellStyle();  
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        XSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("FDate");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("FYear");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);
        cell.setCellValue("FPeriod");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);
        cell.setCellValue("FGroupID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 4);  
        cell.setCellValue("FNumber");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 5);  
        cell.setCellValue("FAccountNum");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 6);  
        cell.setCellValue("FAccountName");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 7);
        cell.setCellValue("FCurrencyNum");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 8);
        cell.setCellValue("FCurrencyName");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 9);
        cell.setCellValue("FAmountFor");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 10);
        cell.setCellValue("FDebit");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 11);
        cell.setCellValue("FCredit");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 12);
        cell.setCellValue("FPreparerID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 13);
        cell.setCellValue("FCheckerID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 14);
        cell.setCellValue("FApproveID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 15);
        cell.setCellValue("FCashierID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 16);
        cell.setCellValue("FHandler");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 17);
        cell.setCellValue("FSettleTypeID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 18);
        cell.setCellValue("FSettleNo");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 19);
        cell.setCellValue("FExplanation");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 20);
        cell.setCellValue("FQuantity");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 21);
        cell.setCellValue("FMeasureUnitID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 22);
        cell.setCellValue("FUnitPrice");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 23);
        cell.setCellValue("FReference");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 24);
        cell.setCellValue("FTransDate");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 25);
        cell.setCellValue("FTransNo");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 26);
        cell.setCellValue("FAttachments");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 27);
        cell.setCellValue("FSerialNum");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 28);
        cell.setCellValue("FObjectName");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 29);
        cell.setCellValue("FParameter");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 30);
        cell.setCellValue("FExchangeRate");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 31);
        cell.setCellValue("FEntryID");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 32);
        cell.setCellValue("FItem");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 33);
        cell.setCellValue("FPosted");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 34);
        cell.setCellValue("FInternalInd");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 35);
        cell.setCellValue("FCashFlow");  
        cell.setCellStyle(style); 
         
        int total=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<AccountEntryForm>list1=service.getAll(time1);
        String times[]=time1.split("-");
        for (int i = 0; i < list1.size(); i++)  
        { 
        	total++;
        	fNumber1++;
            row = sheet.createRow((int) total);  
            AccountEntryForm sc=list1.get(i);
            int id= sc.getId();
           String PayersName=sc.getPayersName();
           ArrivalAccountCorrespondenceTable arrival=service.getall(PayersName); 
           row.createCell((short) 0).setCellValue(sc.getTransactionDate());
           row.createCell((short) 1).setCellValue(times[0]);
           row.createCell((short) 2).setCellValue(times[1]);
           row.createCell((short) 3).setCellValue("银");
           row.createCell((short) 4).setCellValue(fNumber1);
           row.createCell((short) 5).setCellValue("1002.09");
           row.createCell((short) 6).setCellValue("待核查美金账户8600-16005008114014");
           row.createCell((short) 7).setCellValue(sc.getTradeCurrency());
           if("USD".equalsIgnoreCase(sc.getTradeCurrency())){
           row.createCell((short) 8).setCellValue("美元");
          }else if("EUR".equalsIgnoreCase(sc.getTradeCurrency())){
        	  row.createCell((short) 8).setCellValue("欧元");
          }else if("GBP".equalsIgnoreCase(sc.getTradeCurrency())){
        	  row.createCell((short) 8).setCellValue("英镑");
          }
           row.createCell((short) 9).setCellValue(sc.getTradeAmount());
           
           double money=sc.getTradeAmount()*exchangeRate1;
           BigDecimal   b   =   new   BigDecimal(money); 
           double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
           row.createCell((short) 10).setCellValue(f1);
           row.createCell((short) 11).setCellValue(0);
           row.createCell((short) 12).setCellValue("李思");
           row.createCell((short) 13).setCellValue("NONE");
           row.createCell((short) 14).setCellValue("NONE");
           row.createCell((short) 15).setCellValue("NONE");
           row.createCell((short) 16).setCellValue("");
           row.createCell((short) 17).setCellValue("*");
           row.createCell((short) 18).setCellValue("");
           String notes="收到"+sc.getPayersName();
           if("USD".equalsIgnoreCase(sc.getTradeCurrency())){
        	   notes+="美元"+sc.getTradeAmount();
              }else if("EUR".equalsIgnoreCase(sc.getTradeCurrency())){
            	  notes+= "欧元"+sc.getTradeAmount();
              }else if("GBP".equalsIgnoreCase(sc.getTradeCurrency())){
            	  notes+="英镑"+sc.getTradeAmount();
              }
           String projectno="";
           List<AmountClaimForm> list=acservice.enterFinancialEntry(id);
           for(int j = 0; j < list.size(); j++){
        	   AmountClaimForm amount=list.get(j);
        	   projectno+="/"+amount.getInvoice();
           }
           projectno=projectno.replaceFirst("/", "");
           notes=notes+" "+projectno+"("+arrival.getKingdee()+")";
           row.createCell((short) 19).setCellValue(notes);
           row.createCell((short) 20).setCellValue(0);
           row.createCell((short) 21).setCellValue("*");
           row.createCell((short) 22).setCellValue(0);
           row.createCell((short) 23).setCellValue("");
           row.createCell((short) 24).setCellValue(sc.getTransactionDate());
           row.createCell((short) 25).setCellValue("");
           row.createCell((short) 26).setCellValue(0);
           row.createCell((short) 27).setCellValue(fNumber1);
           row.createCell((short) 28).setCellValue("");
           row.createCell((short) 29).setCellValue("");
           row.createCell((short) 30).setCellValue(exchangeRate1);
           row.createCell((short) 31).setCellValue(0);
           row.createCell((short) 32).setCellValue("");
           row.createCell((short) 33).setCellValue(0);
           row.createCell((short) 34).setCellValue("");
           row.createCell((short) 35).setCellValue("");
           total++;
           row = sheet.createRow((int) total);
           row.createCell((short) 0).setCellValue(sc.getTransactionDate());
           row.createCell((short) 1).setCellValue(times[0]);
           row.createCell((short) 2).setCellValue(times[1]);
           row.createCell((short) 3).setCellValue("银");
           row.createCell((short) 4).setCellValue(fNumber1);
           row.createCell((short) 5).setCellValue("2203");
           row.createCell((short) 6).setCellValue("预收账款");
           row.createCell((short) 7).setCellValue(sc.getTradeCurrency());
           if("USD".equalsIgnoreCase(sc.getTradeCurrency())){
           row.createCell((short) 8).setCellValue("美元");
          }else if("EUR".equalsIgnoreCase(sc.getTradeCurrency())){
        	  row.createCell((short) 8).setCellValue("欧元");
          }else if("GBP".equalsIgnoreCase(sc.getTradeCurrency())){
        	  row.createCell((short) 8).setCellValue("英镑");
          }
           row.createCell((short) 9).setCellValue(sc.getTradeAmount());
             
           row.createCell((short) 10).setCellValue(0);
           row.createCell((short) 11).setCellValue(f1);
           row.createCell((short) 12).setCellValue("李思");
           row.createCell((short) 13).setCellValue("NONE");
           row.createCell((short) 14).setCellValue("NONE");
           row.createCell((short) 15).setCellValue("NONE");
           row.createCell((short) 16).setCellValue("");
           row.createCell((short) 17).setCellValue("*");
           row.createCell((short) 18).setCellValue("");
          
           row.createCell((short) 19).setCellValue(notes);
           row.createCell((short) 20).setCellValue(0);
           row.createCell((short) 21).setCellValue("*");
           row.createCell((short) 22).setCellValue(0);
           row.createCell((short) 23).setCellValue("");
           row.createCell((short) 24).setCellValue(sc.getTransactionDate());
           row.createCell((short) 25).setCellValue("");
           row.createCell((short) 26).setCellValue(0);
           row.createCell((short) 27).setCellValue(fNumber1);
           row.createCell((short) 28).setCellValue("");
           row.createCell((short) 29).setCellValue("");
           row.createCell((short) 30).setCellValue(exchangeRate1);
           row.createCell((short) 31).setCellValue(1);
           String note="预收账款---"+arrival.getKingdee()+"---"+arrival.getKingName();
           row.createCell((short) 32).setCellValue(note);
           row.createCell((short) 33).setCellValue(0);
           row.createCell((short) 34).setCellValue("");
           row.createCell((short) 35).setCellValue("");
           
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(PathUtil.FinancialStatement+File.separator+"ExportReceiptForm.xlsx");  
            wb1.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }	  
      
        response.sendRedirect("/ERP-NBEmail/download?filename=ExportReceiptForm.xlsx");
		
		}else{
			out.write("<script>");
			 out.write("alert('数据编号和汇率必填');");
			 out.write("window.history.back();");
			 out.write("</script>");
		}
	}
	
	/**
	 * 
	 * @Title:AccountEntryFormServlet
	 * @Description:查询客户是否存在
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void checkCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String customerName=request.getParameter("customerName");
		 ArrivalAccountCorrespondenceTable arrival=service.getall(customerName); 
		if(arrival!=null){
			out.print("YES");
		}else {
			out.print("NO");
		}
	}
	/**
	 * 
	 * @Title:AccountEntryFormServlet
	 * @Description:到账金额拆分
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void splitAmounty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String firstSumMoney=request.getParameter("firstSumMoney");
		String secondSumMoney=request.getParameter("secondSumMoney");
		String id1=request.getParameter("id");
		double firstSumMoney1=0.00;
		double secondSumMoney1=0.00;
		int id=0;
		if(id1!=null&&!"".equals(id1)){
			id=Integer.parseInt(id1);
		}
		if(firstSumMoney!=null&&!"".equals(firstSumMoney)){
			firstSumMoney1=Double.parseDouble(firstSumMoney);
		}
		if(secondSumMoney!=null&&!"".equals(secondSumMoney)){
			secondSumMoney1=Double.parseDouble(secondSumMoney);
		}
		int total=service.updateFirstSumMoney(id, firstSumMoney1);
		int total1=service.insertSecondSumMoney(id, secondSumMoney1);
		if(total>0&&total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
		
	}
/**
 * 
 * @Title:AccountEntryFormServlet
 * @Description:查看最近一个月进账
   @author wangyang
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException void
 * @throws
 */
	
	public void inquireIntoAccounts(HttpServletRequest request, HttpServletResponse response)
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
		AccountEntryForm accountEntryForm=new AccountEntryForm();
		String saleName1 = request.getParameter("saleName");//获取翻译状态下拉列表选项值
		String  saleName = "";
		if(saleName1 != null && !"".equals(saleName1) && !"-1".equals(saleName1))
		{
			accountEntryForm.setClaimant(saleName1);
			request.setAttribute("saleName", saleName1);
			
		}
		EmailUser user1=new EmailUser();
		user1.setUserName(EmpEName);
		user1.setPwd(EmpPWD);
		request.setAttribute("user", user1);
		
		   List<AccountEntryForm> list=service.inquireIntoAccounts(accountEntryForm);
			request.setAttribute("cusList",list );
			request.getRequestDispatcher("jsp/accoun_entry_month.jsp").forward(request, response);
		}
	
	
}
