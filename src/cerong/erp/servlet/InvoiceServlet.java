package cerong.erp.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerong.erp.entity.*;
import net.sf.json.JSONObject;
import cerong.erp.dao.InvinceDaoImpl;
import cerong.erp.service.EmailUserServiceImpl;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.FactoryFundService;
import cerong.erp.service.IEmailUserService;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IFactoryFundServiceImpl;
import cerong.erp.service.IInvinceServiceImpl;
import cerong.erp.service.IinvoiceinfoServiceImpl;
import cerong.erp.service.InvinceService;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;
import cerong.erp.service.invoiceinfoService;
import cerong.erp.util.AccountEntryUpLoad;
import cerong.erp.util.Client;
import cerong.erp.util.PathUtil;
import cerong.erp.util.ReadExcelUtils;
import org.apache.commons.lang.StringUtils;

public class InvoiceServlet extends HttpServlet{
	
	IInvinceServiceImpl iservice = new InvinceService();
	ItCaseIdServiceImpl service = new ItCaseIdService();
	IEmployeeServiceImpl eservice = new EmployeeService();
	IFactoryFundServiceImpl fservice=new FactoryFundService();
	static IEmployeeServiceImpl uservice = new EmployeeService();
	static IInvinceServiceImpl iiservice = new InvinceService();
	/**
	 * 方法描述:根据项目号调数据
	 * author:wy
	 * date:2017年5月25日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void getinformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

		PrintWriter out = response.getWriter();
		//Map<String,String> map = new HashMap<String, String>();
		String projectId = request.getParameter("projectId");
		String email = request.getParameter("email");
		invoice list=iservice.getAll(projectId);//给郑佳拉数据
		Invoiceinfo1 list1= service.getall1(projectId);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("invoice", list);
		StringBuffer sb = new StringBuffer();//创建一个StringBuffer 对象  
		String result="";
		JSONObject json = JSONObject.fromObject(map);
		System.out.print(json);
		sb.append(json); //拼接字符串  \"  转义 == "  
		result = sb.toString(); 
		response.getWriter().print(json);
		response.getWriter().close();
		result = new String(result.getBytes("UTF-8"));
		out.print(result);
     }
	/**
	 * 
	 * @Title:InvoiceServlet
	 * @Description:新客户到账详情
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
		public void detailsOfNewCustomerArrival(HttpServletRequest request, HttpServletResponse response)
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
				ItemCase it=new ItemCase();
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
					it.setStartTime(time1);
					request.setAttribute("starttime",time1 );
					}else{
						it.setStartTime("2018-03-01");
						request.setAttribute("starttime","2018-03-01" );
	  				}
					List<Invoiceinfo1> list=iservice.detailsOfNewCustomerArrival(it);//查询进账列表
					request.setAttribute("cusList",list );
					request.getRequestDispatcher("jsp/details_of_new_customer_arrival.jsp").forward(request, response);
				}
			
			
		}
	
		/**
		 * 
		 * @Title:InvoiceServlet
		 * @Description:客户到账列表页
	     @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException void
		 * @throws
		 */
		public void clientToAccountListPage(HttpServletRequest request, HttpServletResponse response)
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
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
				String date=format.format(new Date());
				ItemCase it=new ItemCase();
				String str = request.getParameter("vs");
				if(str != null && !"".equals(str)) {
					str = new String(str.getBytes("iso-8859-1"),"UTF-8");
					boolean save =isNumeric(str);
					if(save!=false){
					it.setCid(Integer.parseInt(str));
					}else{
						it.setCusName(str);	
					}
					request.setAttribute("name",str );
					
				}
				
				String time1=request.getParameter("time1");
				if(time1 != null && !"".equals(time1)){
					it.setStartTime(time1);
					request.setAttribute("starttime",time1 );
				}else{
					it.setStartTime(date+"-01");
					request.setAttribute("starttime",date+"-01" );
				}
				String time2=request.getParameter("time2");
				if(time2 != null && !"".equals(time2)){
					it.setEndTime(time2);
					request.setAttribute("endtime",time2 );
				}else{
					it.setEndTime(date+"-03");
					request.setAttribute("endtime",date+"-03" );
				}
				String time3=request.getParameter("time3");
				if(time3 != null && !"".equals(time3)){
					it.setStartTime1(time3);
					request.setAttribute("starttime1",time3 );
				}else{
					it.setStartTime1(date+"-01");
					request.setAttribute("starttime1",date+"-01" );
				}
				String time4=request.getParameter("time4");
				if(time4 != null && !"".equals(time4)){
					it.setEndTime1(time4);
					request.setAttribute("endtime1",time4 );
				}else{
					it.setEndTime1(date+"-03");
					request.setAttribute("endtime1",date+"-03" );
				}
				if(!"jerrylong".equalsIgnoreCase(EmpEName)){
					it.setCustomerManager(EmpEName);
				}
				//List<Invoiceinfo1> list=iservice.getClientToAccountListPage(it);//查询客户列表页
				List<Invoiceinfo1> list=iservice.getall(it);
				//List<Invoiceinfo1> list=new ArrayList<Invoiceinfo1>();
				request.setAttribute("cusList",list);
				//request.setAttribute("info",info);
				request.getRequestDispatcher("jsp/client_to_account_list_page.jsp").forward(request, response);
			}
		}
			/**
			 * 
			 * @Title:InvoiceServlet
			 * @Description:跟单到账页
	          @author wangyang
			 * @param request
			 * @param response
			 * @throws ServletException
			 * @throws IOException
			 * @throws ParseException void
			 * @throws
			 */
			public void documentaryToAccountPage(HttpServletRequest request, HttpServletResponse response)
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
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
					String date=format.format(new Date());
					ItemCase it=new ItemCase();
					String str = request.getParameter("vs");
					if(str != null && !"".equals(str)) {
						str = new String(str.getBytes("iso-8859-1"),"UTF-8");
						it.setMerchandManager1(str);
						request.setAttribute("saleName",str );
					}
					
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
						it.setStartTime(time1);
						request.setAttribute("starttime",time1 );
					}else{
						it.setStartTime(date+"-01");
						request.setAttribute("starttime",date+"-01" );
					}
					String time2=request.getParameter("time2");
					if(time2 != null && !"".equals(time2)){
						it.setEndTime(time2);
						request.setAttribute("endtime",time2 );
					}else{
						it.setEndTime(date+"-03");
						request.setAttribute("endtime",date+"-03" );
					}
					List<Invoiceinfo1> list=iservice.getDocumentaryToAccountPage(it);
					request.setAttribute("cusList",list);
					request.getRequestDispatcher("jsp/documentary_to_account_page.jsp").forward(request, response);
				}
			
			
			}
		
			 public boolean isNumeric(String str){
		           Pattern pattern = Pattern.compile("[0-9]*");
		           Matcher isNum = pattern.matcher(str);
		           if( !isNum.matches() ){
		               return false;
		           }
		           return true;
		    }
			 /**
			  * 
			  * @Title:InvoiceServlet
			  * @Description:列出欠我司发票的 所有工厂 以及欠的  未收发票金额
			    @author wangyang
			  * @param request
			  * @param response
			  * @throws ServletException
			  * @throws IOException
			  * @throws ParseException void
			  * @throws
			  */
				public void invoiceFactoryOwnedToUs (HttpServletRequest request, HttpServletResponse response)
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
						ItemCase it3=service.getStartTime();
						String vs=request.getParameter("vs");
						String condition=request.getParameter("condition");
						ItemCase2 it=new ItemCase2();
						if(vs!=null&&!"".equalsIgnoreCase(vs)){
					    	vs = new String(vs.getBytes("iso-8859-1"),"UTF-8");
					    	if("1".equalsIgnoreCase(condition)){
					    		it.setKingdee(Integer.parseInt(vs));
					    		request.setAttribute("fyfy",1 );
					    	}else if("2".equalsIgnoreCase(condition)){
					    		it.setFactoryName(vs);
					    		request.setAttribute("fyfy",2 );
					    	}
					    	request.setAttribute("vs",vs );
					    }
						it.setStartTime(it3.getStartTime());
						String s1="mandymanlisaliShiGuoJuanedwardfanemmaxiejerrylongninazhaoroseli";
						boolean index1 = s1.toLowerCase().contains(EmpEName.toLowerCase());
						if(index1!=false){
							request.setAttribute("roleNo",100 );
							String userName=request.getParameter("userName");
							List<EmailUser>projectMembers=eservice.getMember();//获取跟单、采购列表
							if(userName!=null&&!"".equalsIgnoreCase(userName)&&!"-1".equalsIgnoreCase(userName)){
								it.setCustomerManager(userName);
								request.setAttribute("userName",userName );
							}
							
						    List<ItemCase2> list=service.invoiceFactoryOwnedToUs(it);//欠我司发票工厂
						    request.setAttribute("projectMembers",projectMembers );
							request.setAttribute("cusList",list );
							request.getRequestDispatcher("jsp/invoice_factory_owned_to_us.jsp").forward(request, response);
						}else{
							String userName=request.getParameter("userName");
							List<EmailUser>projectMembers=eservice.getMember();//获取跟单、采购列表
							if(userName!=null&&!"".equalsIgnoreCase(userName)&&!"-1".equalsIgnoreCase(userName)){
								it.setCustomerManager(userName);
								request.setAttribute("userName",userName );
							}
						    List<ItemCase2> list=service.invoiceFactoryOwnedToUs(it);//跟单、采购
							request.setAttribute("cusList",list );
							request.getRequestDispatcher("jsp/invoice_factory_owned_to_us.jsp").forward(request, response);
						}
						}
					}


	/**
	 *
	 * @Title:InvoiceServlet 20200826
	 * @Description:可以开该品名的工厂列表
	  * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void factoryNameByInvoiceName (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String invoiceName = request.getParameter("invoiceName");
//		if(StringUtils.isNotEmpty(invoiceName)){
//			invoiceName = java.net.URLEncoder.encode(invoiceName,"utf-8");
////			invoiceName = new String(invoiceName.getBytes("ISO-8859-1"),"UTF-8");
//		}

		// 根据品名查询工厂名
		List<FactoryReconciliation> list=service.factoryNameByInvoiceName(invoiceName);
		request.setAttribute("factoryList",list );

		request.getRequestDispatcher("jsp/factory_invoice.jsp").forward(request, response);

	}


	/**
				 * 
				 * @Title:InvoiceServlet
				 * @Description:根据客户金蝶号查询明细账
				   @author wangyang
				 * @param request
				 * @param response
				 * @throws ServletException
				 * @throws IOException
				 * @throws ParseException void
				 * @throws
				 */
				public void allDetailedAccounts (HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException, ParseException {
					PrintWriter out = response.getWriter();
					String kingdee = request.getParameter("kingdee");
					// add 20200806 start
					String factoryName = request.getParameter("factoryName");
//					if(StringUtils.isNotEmpty(factoryName)){
//						factoryName = java.net.URLEncoder.encode(factoryName,"utf-8");
////						factoryName=new String(factoryName.getBytes("ISO-8859-1"),"UTF-8");
//					}

					// add 20200806 end
					String num = request.getParameter("num");
					String saleName = request.getParameter("saleName");
					String month = request.getParameter("month");
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
					ItemCase it=service.getStartTime();
					// 取得金蝶号  add 20200806 start
					if(StringUtils.isNotEmpty(factoryName)){
						kingdee = String.valueOf(service.getFactoryName(factoryName));
					}
					// 取得金蝶号  add 20200806 end

					if(user1!=null&& total1>0){
						ItemCase2 itemcase=new ItemCase2();
						String condition2 = request.getParameter("condition2");
						if(condition2!=null&&!"".equalsIgnoreCase(condition2)){
							itemcase.setUnderInvoice(Integer.parseInt(condition2));
							request.setAttribute("fyfz",condition2 );
						}else{
							itemcase.setUnderInvoice(2);
							request.setAttribute("fyfz",2 );
						}
						itemcase.setKingdee(Integer.parseInt(kingdee));
						if(month!=null&&!"".equalsIgnoreCase(month)){
							String minth1=month+"-01";
							itemcase.setDateSampleUploading(minth1);//添加年月查询
						}
						itemcase.setStartTime(it.getStartTime());
						String s1="mandymanlisaliShiGuoJuanedwardfanemmaxiejerrylongninazhaoroseli";
						boolean index1 = s1.toLowerCase().contains(EmpEName.toLowerCase());
						if(index1!=false){
							if(saleName!=null&&!"".equalsIgnoreCase(saleName)){
								itemcase.setCustomerManager(saleName);	
							}
						if("0".equalsIgnoreCase(num)){
						List<FactoryReconciliation> list=service.getAllDetailedAccounts(itemcase);//根据金蝶号查询全部
						request.setAttribute("cusList",list );
						}else if("1".equalsIgnoreCase(num)){
							
							List<FactoryReconciliation> list=service.getFinalInvoice(itemcase);//根据金蝶号查询尾款已付
							request.setAttribute("cusList",list );
							
						}else if("2".equalsIgnoreCase(num)){
							List<FactoryReconciliation> list=service.getPaymentExceededApril(itemcase);//付款超过4个月未收回发票金额
							request.setAttribute("cusList",list );	
						}
						request.setAttribute("roleNo", 100);
						}else{
							itemcase.setCustomerManager(EmpEName);
							if("0".equalsIgnoreCase(num)){
								List<FactoryReconciliation> list=service.getAllDetailedAccounts(itemcase);//根据金蝶号查询全部
								request.setAttribute("cusList",list );
								}else if("1".equalsIgnoreCase(num)){
									List<FactoryReconciliation> list=service.getFinalInvoice(itemcase);//根据金蝶号查询尾款已付
									request.setAttribute("cusList",list );
								}else if("2".equalsIgnoreCase(num)){
									List<FactoryReconciliation> list=service.getPaymentExceededApril(itemcase);//付款超过4个月未收回发票金额
									request.setAttribute("cusList",list );	
								}	
						}
                        request.setAttribute("kingdee",kingdee);
						request.setAttribute("num",num );
						request.setAttribute("day",it.getStartTime() );
						request.getRequestDispatcher("jsp/all_detailed_accounts.jsp").forward(request, response);
						}
					}
				
				
				
						
						/**
						 * 
						 * @Title:InvoiceServlet
						 * @Description:到账录入客户关联表
						   @author wangyang
						 * @param request
						 * @param response
						 * @throws ServletException
						 * @throws IOException
						 * @throws ParseException void
						 * @throws
						 */
						public void enterTheCustomerRelevanceTableIntoTheAccount(HttpServletRequest request, HttpServletResponse response)
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
								if("lisalimandymanroseli".toLowerCase().contains(EmpEName.toLowerCase())){
									request.setAttribute("EmpEName","mandyman" );
								}

								String name=request.getParameter("name");
								String customerId=request.getParameter("customerId");
								String projectId=request.getParameter("projectId");
								ArrivalAccountCorrespondenceTable it=new ArrivalAccountCorrespondenceTable();
								if(name!=null&&!"".equalsIgnoreCase(name)){
									name = new String(name.getBytes("iso-8859-1"),"UTF-8");
									it.setName(name);
									request.setAttribute("name",name );
								}

								if(customerId!=null&&!"".equalsIgnoreCase(customerId)){
									it.setCustomerId(Integer.parseInt(customerId));
									request.setAttribute("customerId",customerId );
								}
								if(projectId!=null&&!"".equalsIgnoreCase(projectId)){
									it.setProjectId(projectId);
									request.setAttribute("projectId",projectId );
								}
								List<ArrivalAccountCorrespondenceTable> list=service.enterTheCustomerRelevanceTableIntoTheAccount(it);//查询到账录入客户关联表
								request.setAttribute("cusList",list );
								request.getRequestDispatcher("jsp/enter_the_customer_relevance_table_into_the_account.jsp").forward(request, response);
							}
						}
						/**
						 * 
						 * @Title:InvoiceServlet
						 * @Description:修改查询出运单时间
						   @author wangyang
						 * @param request
						 * @param response
						 * @throws ServletException
						 * @throws IOException void
						 * @throws
						 */
						public void updateMonth(HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException {
							PrintWriter out = response.getWriter();
							String time=request.getParameter("time");
							 int total=iservice.updateMonth(time);
								if(total>0){
										out.print("YES");
									}else{
										out.print("NO");	
									}	
								
							}
						
						/**
						 * 
						 * @Title:InvoiceServlet
						 * @Description:上传历史出运单数据
						   @author wangyang
						 * @param request
						 * @param response
						 * @throws ServletException
						 * @throws IOException void
						 * @throws
						 */
						public void uploadHistoricalWaybillData (HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException {
							AccountEntryUpLoad.upload(request, response);
							int total=iservice.deleteHistoricalWaybillData(); 
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
							ShippingBillData data=new ShippingBillData();
							String fileName="";
							 Map<String, String> map = (Map<String, String>) request.getAttribute("map");
							  //准备参数map
							if(map != null && map.size() > 0) {//说明之前的附件没有检测到敏感字符  将所有的附件信息保存到数据库
								Set<String> keySet = map.keySet();
							    	for (String key : keySet) {
							    		String value = map.get(key);
							    		fileName=value;
							    	}
							}
							String path = PathUtil.FirstParagraph;
							File storefile = new File(path,fileName);
							String filepath=path+File.separator+fileName;
					        ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
					       try{
					    	// 对读取Excel表格内容测试
					        Map<Integer, Map<Integer,Object>> map1 = excelReader.readExcelContent2();
					        for (int name : map1.keySet()) {
					        	 int key =  name;
					        	 if(key>0){
					        	 Map<Integer, Object> map2 = map1.get(key);
					             for (int name1 : map2.keySet()) {
					            	 int keya =  name1;
					            	 String value="";
					            	 if(keya!=1){
					            	 value = (String) map2.get(keya);
					            	 }else{
					                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					            	 value = sdf.format(map2.get(keya));	 
					            	 }
					                if(keya==0){
					            	 data.setBargainNo(value);
					                }else if(keya==1){
					                data.setDate(value);	
					                }else if(keya==2){
					                 double id = 0.000;//默认是第一页
					              	if(value != null && !"".equals(value)) {
					              		id = Double.parseDouble(value);
					              	}
					                data.setAmountCustomsDeclaration(id);	
					               }
					             }
					             int num=iservice.insertHistoricalWaybillData(data); 
					                
					        	 }
					        }
					        response.sendRedirect("/ERP-NBEmail/helpServlet?action=invoiceFactoryOwnedToUs&className=InvoiceServlet");
					       }catch(Exception e){
					    	   e.printStackTrace();
					       }
						}

	/**
	 * 查询利润
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void queryProfit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String EmpEName=null;
		String EmpPWD=null;
		Cookie c[]=request.getCookies();
		String time = request.getParameter("time");
		String time1 = request.getParameter("time1");
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
			EmailUser user=new EmailUser();
			if(time!=null&&!"".equalsIgnoreCase(time)){
             request.setAttribute("time",time);
            }else{
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				time=sdf.format(date);
				request.setAttribute("time",time);
			}
			if(time1!=null&&!"".equalsIgnoreCase(time1)){
				request.setAttribute("time1",time1);
			}else{
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				time1=sdf.format(date);
				request.setAttribute("time1",time1);
			}
          List<MonthlyProfitStatement> list=iservice.getMonthlyProfitStatement(time,time1);
            request.setAttribute("cusList",list );

			request.getRequestDispatcher("jsp/query_profit.jsp").forward(request, response);
		}
	}
	/**
	 * 查询利润
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void getOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		queryMailInformation();
		}

	public static void queryMailInformation() {
		show();
	}
	public static void show(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String time=sdf.format(new Date());
		for(int i=0;i<36;i++){
			try {
				Date date1=sdf.parse(time);
				int num=i+1;
				Calendar theCa = Calendar.getInstance();
				theCa.setTime(date1);
				theCa.add(theCa.MONTH, -num);
				String time1 = sdf.format(theCa.getTime());
				updateQueryProfit(time1);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private static void updateQueryProfit(String time1) {
		List<EmailUser> alllist=uservice.getAllSales();
		List<MonthlyProfitStatement> statelist=new ArrayList<MonthlyProfitStatement>();
		for(int i=0;i<alllist.size();i++){
			EmailUser user=alllist.get(i);
			MonthlyProfitStatement statement=iiservice.getOne(user.getUserName(),time1+"-01");
			statement.setTime(time1);
			statelist.add(statement);
		}
		MonthlyProfitStatement state=iiservice.getLastOne(time1);
		if(state!=null){
			iiservice.updateAllList(statelist);
		}else {
			iiservice.insertAll(statelist);
		}
	}
 }
						
			 

