package cerong.erp.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cerong.erp.util.DateUtil;




import org.springframework.web.bind.annotation.ResponseBody;






import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cerong.erp.dao.InvinceDaoImpl;
import cerong.erp.entity.AllDrawings;
import cerong.erp.entity.Bonusdata;
import cerong.erp.entity.Customer;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.Invoiceinfo1;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.ItemCase2;
import cerong.erp.entity.Mould;
import cerong.erp.entity.ProjectCustomer;
import cerong.erp.entity.QualityAnalysisTable;
import cerong.erp.entity.QuotePrice;
import cerong.erp.entity.invoice;
import cerong.erp.service.AllDrawingsService;
import cerong.erp.service.BonusdataServiceImpl;
import cerong.erp.service.CustomerService;
import cerong.erp.service.EmailUserServiceImpl;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IAllDrawingsServiceImpl;
import cerong.erp.service.IBonusdataService;
import cerong.erp.service.ICustomerServiceImpl;
import cerong.erp.service.IEmailUserService;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IInvinceServiceImpl;
import cerong.erp.service.IMouldServiceImpl;
import cerong.erp.service.IProjectCustomerServiceImpl;
import cerong.erp.service.IQualityAnalysisTableServiceImpl;
import cerong.erp.service.IQuotePriceServiceImpl;
import cerong.erp.service.InvinceService;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;
import cerong.erp.service.MouldService;
import cerong.erp.service.ProjectCustomerService;
import cerong.erp.service.QualityAnalysisTableService;
import cerong.erp.service.QuotePriceService;
import cerong.erp.util.SerializeUtil;


public class ExternalinterfaceServlet extends HttpServlet{
	IEmployeeServiceImpl eservice = new EmployeeService();
	private static final long serialVersionUID = 1L;
	static IInvinceServiceImpl iservice = new InvinceService();
	static ItCaseIdServiceImpl service = new ItCaseIdService();
    IEmailUserService uservice = new EmailUserServiceImpl();
	IBonusdataService bservice= new BonusdataServiceImpl();
	ICustomerServiceImpl cservice = new CustomerService();
	static IAllDrawingsServiceImpl aservice = new AllDrawingsService();
	IQuotePriceServiceImpl qservice = new QuotePriceService();
	IQualityAnalysisTableServiceImpl qaservice = new QualityAnalysisTableService();
	private static final Logger LOG = LoggerFactory.getLogger(ExternalinterfaceServlet.class);
	static IMouldServiceImpl mservice = new MouldService();
	static IProjectCustomerServiceImpl pservice = new ProjectCustomerService();
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
		
		String content=list1.getEmailcontent();
          content= URLEncoder.encode(content, "UTF-8");
          String productdesce=list1.getProductdesce();
          productdesce= URLEncoder.encode(productdesce, "UTF-8");
          String productdescc=list1.getProductdescc();
         productdescc= URLEncoder.encode(productdescc, "UTF-8");
        
		PrintWriter out1 = null;
        BufferedReader in1 = null;
        String result2 = "";
        try {
        	 URL realUrl1 = new URL("http://117.144.21.74:43900/NBEmail/helpServlet?action=getcustomerinformation&className=ExternalInterfaceServlet");
            // 打开和URL之间的连接
            URLConnection conn1 = realUrl1.openConnection();
            // 设置通用的请求属性
            conn1.setRequestProperty("connection", "Keep-Alive");
            conn1.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn1.setDoOutput(true);
            conn1.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out1 = new PrintWriter(conn1.getOutputStream());
            // 发送请求参数
           out1.print("eid="+list1.getEid()+"&&name="+list1.getName()+"&&email="+email
            		+"&&projectId="+projectId+"&&productdescc="+productdescc
            		+"&&productdesce="+productdesce+"&&customermanager="+list1.getCustomermanager()
            		+"&&merchandmanager1="+list1.getMerchandmanager1()+"&&emailcontent="+content);
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
	}
  out.print(result);
}
	/**
	 * 方法描述:调数据
	 * author:wy
	 * date:2017年5月17日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void lookCus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
     PrintWriter out = response.getWriter();
		String str = request.getParameter("start");
		int start = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			start = Integer.parseInt(str);
		}
		InvinceDaoImpl dao = new InvinceDaoImpl();
		List<invoice> list1=null;
		
		String str1 = request.getParameter("end");
		int end = 10;//默认是第一页
		if(str1 != null && !"".equals(str1)) {
			end = Integer.parseInt(str1);
		}
		if(start==1){
		 list1=iservice.getAll(start,end);
		}else if(start==2){
		list1=dao.getAll1(start,end);	
		}else if(start==3){
		list1=dao.getAll2(start,end);	
		}else if(start==4){
			list1=dao.getAll3(start,end);	
			}else if(start==5){
				list1=dao.getAll4(start,end);	
				}
			else if(start==6){
				list1=dao.getAll5(start,end);	
				}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("invoice", list1);
		StringBuffer sb1 = new StringBuffer();//创建一个StringBuffer 对象  
		String result="";
		JSONObject json = JSONObject.fromObject(map);
			System.out.print(json);
			sb1.append(json); //拼接字符串  \"  转义 == "  
			 result = sb1.toString(); 
			response.getWriter().print(json);
			response.getWriter().close();
			result = new String(result.getBytes("UTF-8"));
       PrintWriter out1 = null;
        BufferedReader in1 = null;
        String result2 = "";
        try {
        	
            //URL realUrl1 = new URL("http://117.144.21.74:43900/ERP-NBEmail/helpServlet?action=Attachment&className=ItCaseIdServlet");
            //URL realUrl1 = new URL("http://192.168.1.54:8099/crmconsole/port/receiveAllData.do");
            //URL realUrl1 = new URL("http://192.168.1.54:8080/crmconsole/port/updateClientOrderBatch.do");
            URL realUrl1 = new URL("https://www.importx.net/supplier/port/updateClientOrderBatch.do");
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
     * 发送POST请求 
     *  
     * @param url 
     *            目的地址 
     * @param map 
     *            请求参数，Map类型。 
     * @return 远程响应结果 
     */  
    public static String sendPost(String url, Map<Object, String> map) {  
        String result = "";// 返回的结果  
        BufferedReader in = null;// 读取响应输入流  
        PrintWriter out = null;  
        StringBuffer sb = new StringBuffer();// 处理请求参数  
        String params = "";// 编码之后的参数  
        try {  
            // 编码请求参数  
            if (map.size() == 1) {  
                for (Object name : map.keySet()) {  
                    sb.append(name).append("=").append(  
                            java.net.URLEncoder.encode((String) map.get(name),  
                                    "UTF-8"));  
                }  
                params = sb.toString();  
               
            } else {  
                for (Object name : map.keySet()) {  
                    sb.append(name).append("=").append(  
                            java.net.URLEncoder.encode((String) map.get(name),  
                                    "UTF-8")).append("&");  
                }  
                String temp_params = sb.toString();  
                params = temp_params.substring(0, temp_params.length() - 1);  
            }  
            System.out.print(params);
            // 创建URL对象  
            java.net.URL connURL = new java.net.URL(url);  
            // 打开URL连接  
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
                    .openConnection();  
            // 设置通用属性  
            httpConn.setRequestProperty("Accept", "*/*");  
            httpConn.setRequestProperty("Connection", "Keep-Alive");  
            httpConn.setRequestProperty("User-Agent",  
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
            // 设置POST方式  
            httpConn.setDoInput(true);  
            httpConn.setDoOutput(true);  
            // 获取HttpURLConnection对象对应的输出流  
            out = new PrintWriter(httpConn.getOutputStream());  
            // 发送请求参数  
            out.write(params);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式  
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));  
            String line;  
            // 读取返回的内容  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }
    
    
    /**
	 * 方法描述:郑佳拉去模具数据
	 * author:wy
	 * date:2017年6月6日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void pulldie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		List<Mould>list=mservice.getall();
         String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("Mould", json);
			response.getWriter().print(map);
			response.getWriter().close();
	         String obj = SerializeUtil.ObjToJson(map);
	          System.out.println(obj);
	              //Client.sendPost("", map);
	         // ItCaseIdServlet.sendPost("http://192.168.1.151:8099/supplier/port/importDrawing.do",map);
	          ItCaseIdServlet.sendPost("http://67.198.209.91:8099/supplier/port/importDrawing.do",map);
	}
	
	/**
	 * 方法描述:郑佳拉取发票数据
	 * author:wy
	 * date:2017年6月21日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void pullinvoicedata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		List<Invoiceinfo1>list=iservice.getall();
         String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("Invoiceinfo1", json);
			
	         String obj = SerializeUtil.ObjToJson(map);
	          System.out.println(obj);
	              //Client.sendPost("", map);
	         // String sum=ItCaseIdServlet.sendPost("https://www.importx.net/supplier/port/importInvoiceInfo.do",map);
	       // String sum=ItCaseIdServlet.sendPost("http://192.168.1.151:8099/supplier/port/importInvoiceInfo.do",map);
	        String sum=ItCaseIdServlet.sendPost("http://67.198.209.91:8099/supplier/port/importInvoiceInfo.do",map);
	        response.getWriter().print(sum);
			response.getWriter().close();
	}
	
	
	
	
	/**
	 * 方法描述:郑佳拉取全部项目
	 * author:wy
	 * date:2017年6月21日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void pullallorders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
	List<invoice>list=iservice.getall1();
         String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("Invoiceinfo1", json);
			String obj = SerializeUtil.ObjToJson(map);
	         
	          
	              //Client.sendPost("", map);
	         // String num=ItCaseIdServlet.sendPost("http://192.168.1.54:8080/crmconsole/port/importSaleOrder.do",map);
	         String num=ItCaseIdServlet.sendPost("http://67.198.209.91:8099/supplier/port/importERPOrderByYear.do",map);
	        //String num=ItCaseIdServlet.sendPost("http://192.168.1.151:8099/supplier/port/importERPOrderByYear.do",map);
	          response.getWriter().print(num);
				response.getWriter().close();
	          System.out.print(num);
	           
	}
	
	
	/**
	 * 方法描述:拉取最近一年项目全部客户
	 * author:wy
	 * date:2017年6月30日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void acquiringcustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String str = request.getParameter("start");
		int start = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			start = Integer.parseInt(str);
		}
		String str1 = request.getParameter("end");
		int end = 0;//默认是第一页
		if(str1 != null && !"".equals(str1)) {
			end = Integer.parseInt(str1);
		}
		List<ProjectCustomer>list=pservice.getall1(start,end);
		String json=SerializeUtil.ListToJson(list);
		json= URLEncoder.encode(json, "UTF-8");
	   // System.out.print(json);
		String result = "";// 返回的结果  
        BufferedReader in = null;// 读取响应输入流  
        PrintWriter out = null;  
        StringBuffer sb = new StringBuffer();// 处理请求参数  
        String params = "";// 编码之后的参数   
		// 创建URL对象  
        try{
        java.net.URL connURL = new java.net.URL("http://117.144.21.74:43900/NBEmail/helpServlet?action=addproject&className=CustomerServlet");
        // 打开URL连接  
        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
                .openConnection();  
        // 设置通用属性  
        httpConn.setRequestProperty("Accept", "*/*");  
        httpConn.setRequestProperty("Connection", "Keep-Alive");  
        httpConn.setRequestProperty("User-Agent",  
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
        // 设置POST方式  
        httpConn.setDoInput(true);  
        httpConn.setDoOutput(true);  
        // 获取HttpURLConnection对象对应的输出流  
        out = new PrintWriter(httpConn.getOutputStream());  
        // 发送请求参数  
        out.write("&result="+json);  
        // flush输出流的缓冲  
        out.flush();  
        // 定义BufferedReader输入流来读取URL的响应，设置编码方式  
        in = new BufferedReader(new InputStreamReader(httpConn  
                .getInputStream(), "UTF-8"));  
        String line;  
        // 读取返回的内容  
        while ((line = in.readLine()) != null) {  
            result += line;  
        }  
    } catch (Exception e) {  
        e.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
            if (in != null) {  
                in.close();  
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
		//ItCaseIdServlet.sendPost("http://192.168.1.62:8080/NBEmail/helpServlet?action=addproject&className=CustomerServlet",json);
	}
	           
	}
	
	
	/**
	 * 方法描述:调数据
	 * author:wy
	 * date:2017年5月17日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void lookCus1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

		PrintWriter out = response.getWriter();
		String str = request.getParameter("eid");
		int eid = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			eid = Integer.parseInt(str);
		}
		InvinceDaoImpl dao = new InvinceDaoImpl();
		
		List<invoice>list1=iservice.getall1();
		
		
		 
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("invoice", list1);
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

	
		PrintWriter out1 = null;
        BufferedReader in1 = null;
        String result2 = "";
        try {
        	
            //URL realUrl1 = new URL("http://117.144.21.74:43900/ERP-NBEmail/helpServlet?action=Attachment&className=ItCaseIdServlet");
            //URL realUrl1 = new URL("http://192.168.1.54:8099/crmconsole/port/receiveAllData.do");
            //URL realUrl1 = new URL("http://192.168.1.54:8080/crmconsole/port/updateClientOrderBatch.do");
            URL realUrl1 = new URL("http://67.198.209.91:8099/supplier/port/updateClientOrderBatch.do");
            //URL realUrl1 = new URL("http://192.168.1.151:8099/supplier/port/updateClientOrderBatch.do");
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
  if("success".equals(result2)){
	  System.out.print("已将客户信息保存到本地");
  }
  }
	 /**
	 * 方法描述:郑佳拉去模具数据
	 * author:wy
	 * date:2017年6月6日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void pulldie1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String str = request.getParameter("eid");
		int eid = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			eid = Integer.parseInt(str);
		}
		List<Mould>list=mservice.getall1(eid);
         String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("Mould", json);
			response.getWriter().print(map);
			response.getWriter().close();
	         String obj = SerializeUtil.ObjToJson(map);
	          System.out.println(obj);
	              //Client.sendPost("", map);
	         //ItCaseIdServlet.sendPost("http://192.168.1.54:8080/crmconsole/port/importDrawing.do",map);
	          ItCaseIdServlet.sendPost("https://www.importx.net/supplier/port/importDrawing.do",map);
	}
	
	/**
	 * 方法描述:郑佳拉取发票数据
	 * author:wy
	 * date:2017年6月21日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void pullinvoicedata1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String str = request.getParameter("eid");
		int eid = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			eid = Integer.parseInt(str);
		}
		List<Invoiceinfo1>list=iservice.getall1(eid);
         String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("Invoiceinfo1", json);
			response.getWriter().print(map);
			response.getWriter().close();
	         String obj = SerializeUtil.ObjToJson(map);
	         // System.out.println(obj);
	              //Client.sendPost("", map);
	       // String sum=ItCaseIdServlet.sendPost("http://192.168.1.54:8080/crmconsole/port/importInvoiceInfo.do",map);
	        String sum=ItCaseIdServlet.sendPost("https://www.importx.net/supplier/port/importInvoiceInfo.do",map);
	           System.out.print(sum);
	}
	
	/**
	 * 方法描述:将ERP上项目全部拉取到NBEmail上
	 * author:wy
	 * date:2017年6月21日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void ERPpulldata1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String str = request.getParameter("start");
		int start = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			start = Integer.parseInt(str);
		}
		String str1 = request.getParameter("end");
		int end = 0;//默认是第一页
		if(str1 != null && !"".equals(str1)) {
			end = Integer.parseInt(str1);
		}
		List<ProjectCustomer>list=pservice.getall(start,end);
		String json=SerializeUtil.ListToJson(list);
		json= URLEncoder.encode(json, "UTF-8");
	   // System.out.print(json);
		String result = "";// 返回的结果  
        BufferedReader in = null;// 读取响应输入流  
        PrintWriter out = null;  
        StringBuffer sb = new StringBuffer();// 处理请求参数  
        String params = "";// 编码之后的参数   
		// 创建URL对象  
        try{
        java.net.URL connURL = new java.net.URL("http://117.144.21.74:43900/NBEmail/helpServlet?action=addproject1&className=CustomerServlet");
        // 打开URL连接  
        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
                .openConnection();  
        // 设置通用属性  
        httpConn.setRequestProperty("Accept", "*/*");  
        httpConn.setRequestProperty("Connection", "Keep-Alive");  
        httpConn.setRequestProperty("User-Agent",  
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
        // 设置POST方式  
        httpConn.setDoInput(true);  
        httpConn.setDoOutput(true);  
        // 获取HttpURLConnection对象对应的输出流  
        out = new PrintWriter(httpConn.getOutputStream());  
        // 发送请求参数  
        out.write("&result="+json);  
        // flush输出流的缓冲  
        out.flush();  
        // 定义BufferedReader输入流来读取URL的响应，设置编码方式  
        in = new BufferedReader(new InputStreamReader(httpConn  
                .getInputStream(), "UTF-8"));  
        String line;  
        // 读取返回的内容  
        while ((line = in.readLine()) != null) {  
            result += line;  
        }  
    } catch (Exception e) {  
        e.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
            if (in != null) {  
                in.close();  
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
		//ItCaseIdServlet.sendPost("http://192.168.1.62:8080/NBEmail/helpServlet?action=addproject&className=CustomerServlet",json);
	}

	}
	
	
	/**
	 * 方法描述:郑佳拉取全部项目
	 * author:wy
	 * date:2017年6月21日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void pullallorders1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String str = request.getParameter("eid");
		int eid = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			eid = Integer.parseInt(str);
		}
		List<invoice>list=iservice.getalla(eid);
         String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("Invoiceinfo1", json);
			
	         String obj = SerializeUtil.ObjToJson(map);
	          System.out.println(obj);
	              //Client.sendPost("", map);
	          String num=ItCaseIdServlet.sendPost("https://www.importx.net/supplier/port/importERPOrderByYear.do",map);
	        //String num=ItCaseIdServlet.sendPost("http://192.168.1.54:8080/crmconsole/port/importSaleOrder.do",map);
	        //String num=ItCaseIdServlet.sendPost("http://192.168.1.54:8080/crmconsole/port/importERPOrderByYear.do",map);
	          response.getWriter().print(num);
				response.getWriter().close();
	          System.out.print(num);
	           
	}
	
	
	/**
	 * 方法描述:拉取最近一年项目全部客户
	 * author:wy
	 * date:2017年6月30日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void acquiringcustomersa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String str = request.getParameter("eid");
		int eid = 0;//默认是第一页
		if(str != null && !"".equals(str)) {
			eid = Integer.parseInt(str);
		}
		
		List<ProjectCustomer>list=pservice.getall1(eid);
		String json=SerializeUtil.ListToJson(list);
		json= URLEncoder.encode(json, "UTF-8");
	   // System.out.print(json);
		String result = "";// 返回的结果  
        BufferedReader in = null;// 读取响应输入流  
        PrintWriter out = null;  
        StringBuffer sb = new StringBuffer();// 处理请求参数  
        String params = "";// 编码之后的参数   
		// 创建URL对象  
        try{
        java.net.URL connURL = new java.net.URL("http://117.144.21.74:43900/WEBTEST/helpServlet?action=addproject&className=CustomerServlet");
        // 打开URL连接  
        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
                .openConnection();  
        // 设置通用属性  
        httpConn.setRequestProperty("Accept", "*/*");  
        httpConn.setRequestProperty("Connection", "Keep-Alive");  
        httpConn.setRequestProperty("User-Agent",  
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
        // 设置POST方式  
        httpConn.setDoInput(true);  
        httpConn.setDoOutput(true);  
        // 获取HttpURLConnection对象对应的输出流  
        out = new PrintWriter(httpConn.getOutputStream());  
        // 发送请求参数  
        out.write("&result="+json);  
        // flush输出流的缓冲  
        out.flush();  
        // 定义BufferedReader输入流来读取URL的响应，设置编码方式  
        in = new BufferedReader(new InputStreamReader(httpConn  
                .getInputStream(), "UTF-8"));  
        String line;  
        // 读取返回的内容  
        while ((line = in.readLine()) != null) {  
            result += line;  
        }  
    } catch (Exception e) {  
        e.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
            if (in != null) {  
                in.close();  
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
		//ItCaseIdServlet.sendPost("http://192.168.1.62:8080/NBEmail/helpServlet?action=addproject&className=CustomerServlet",json);
	}
	           
	}





	


	/**
	 * 方法描述:调取分红系统数据
	 * author:wy
	 * date:2017年7月6日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * 
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void bonusdata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String date=request.getParameter("synchroDate");
		String[]yeara=date.split("-");
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
		List<Bonusdata>list=bservice.getall(year,month);
         String json=SerializeUtil.ListToJson(list);
			/*Map<Object, String> map = new HashMap<Object,String>();
			map.put("Bonusdata", json);*/
			//String json1=SerializeUtil.ListToJson(list1);
			
			if(list.size()>0){
				out.print(json);
			}
	         //String obj = SerializeUtil.ObjToJson(map);
	         // System.out.println(obj);
	              //Client.sendPost("http://192.168.1.54:8099/crmconsole/port/insertSaleCustomer.do", map);
			
	         // ItCaseIdServlet.sendPost("http://192.168.1.70:8080/bonus-system/project/synchERPProject",map);
	}
	
	
	
	/**
	 * 
	 * @Title:ExternalinterfaceServlet
	 * @Description:尾款及退税分红
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void getTailBonus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String projectIdList = request.getParameter("projectIdList");
		String bonusDate = request.getParameter("bonusDate");
		String number = request.getParameter("number");
		String[]yeara=bonusDate.split("-");
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
		
		 projectIdList=new String(projectIdList.getBytes("iso-8859-1"),"utf-8");
		List<Bonusdata>list=bservice.getalla(projectIdList,year,month);
		if("1".equalsIgnoreCase(number)) {
			service.updateDividendBalance(list);//修改
		}
         String json=SerializeUtil.ListToJson(list);
			if(list.size()>0){
				out.print(json);
			}
	         
	}
	
	
	
	
	
	/**
	 * 方法描述:同步全部用户到分红系统
	 * author:wy
	 * date:2017年8月8日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void allCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		List<Customer>list=cservice.getall();
		String json=SerializeUtil.ListToJson(list);
		Map<Object, String> map = new HashMap<Object,String>();
		map.put("Customer", json);
		response.getWriter().print(map);
		response.getWriter().close();
		//String obj = SerializeUtil.ObjToJson(map);
		// System.out.println(obj);
		//Client.sendPost("http://192.168.1.54:8099/crmconsole/port/insertSaleCustomer.do", map);
		ItCaseIdServlet.sendPost("http://192.168.1.71:8099/bonus-system/project/synchProjectCustomer",map);
/*		ItCaseIdServlet.sendPost("http://192.168.1.6:8080/bonus-system/project/synchProjectCustomer",map);
*/	}
	
	


	
	
	/**
	 * 方法描述:获取全部图纸
	 * author:wy
	 * date:2017年7月25日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void projectDrawing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		List<AllDrawings>list=aservice.getall();
		String json=SerializeUtil.ListToJson(list);
		Map<Object, String> map = new HashMap<Object,String>();
		map.put("projectDrawing", json);
		response.getWriter().print(map);
		response.getWriter().close();
		ItCaseIdServlet.sendPost("http://192.168.1.6:8080/test/projectDrawing/synchProjectDrawing",map);
	}
	/**
	 * 方法描述:同步项目组成员及重要时间，根据极简任务给项目
	 * author:wy
	 * date:2017年7月25日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void inspectionReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String projectIdList = request.getParameter("projectIdList");
         String [] json=projectIdList.split(",");
         List<ItemCase2>list1=new ArrayList<ItemCase2>();
		if(json.length>0){
		
			for(int i=0;i<json.length;i++){
			 String projectId=json[i];
			 if(projectId!=null&&!"".equals(projectId)){
				 ItemCase2 bouns=service.getInspectionReport(projectId);
				 if(bouns!=null &&!"".equals(bouns)){
		      list1.add(bouns);
				 }
			 }
		  }
		}
        String json1=SerializeUtil.ListToJson(list1);
			 if(list1.size()>0){
				 out.print(json1);
			 }
	}
	//同步最近8天全部项目到极简任务
	public void inspectionReporta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		List<ItemCase2>list=service.getPurchasingSystem1();
        String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("EmailClient", json);
			System.out.print(json);
			 String obj = SerializeUtil.ObjToJson(map);
			 if(list.size()>0){
				
			ExternalinterfaceServlet.sendPost("http://192.168.1.6:8080/TaskManager/project/synchERPProject",map);
			 }
	}
	


	public static void PurchasingSystem1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String projectId = request.getParameter("projectId");
		
		List<ItemCase2>list=service.getPurchasingSystem1(projectId);
        String json=SerializeUtil.ListToJson(list);
			Map<Object, String> map = new HashMap<Object,String>();
			map.put("EmailClient", json);
			System.out.print(json);
			 String obj = SerializeUtil.ObjToJson(map);
			 if(list.size()>0){
				
			 ExternalinterfaceServlet.sendPost("http://192.168.1.186:8080/test/project/synchERPProject",map);
			 // ExternalinterfaceServlet.sendPost("http://117.144.21.74:80/project/synchERPProject",map);
			 }
	}
	
	
/**
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:TODO
   @author wangyang
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 * @throws ParseException void
 * @throws
 */
	public void publicComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String statusEnter= request.getParameter("statusEnter");
		JSONObject OBJ=JSONObject.fromObject(statusEnter);
		String projectNo = (String)OBJ.get("projectNo");
		String fileUrl=(String)OBJ.get("fileUrl");
		String details=(String)OBJ.get("details");;
		String operator=(String)OBJ.get("operator");;
		details = new String(details.getBytes("iso-8859-1"),"utf-8");
		fileUrl = new String(fileUrl.getBytes("iso-8859-1"),"utf-8");
		QuotePrice qu=new QuotePrice();
		qu.setDetails(details);
		qu.setFileUrl(fileUrl);
		qu.setProjectNo(projectNo);
		qu.setOperator(operator);
		if(fileUrl!=null&&!"".equals(fileUrl)){
			String fileUrl1 = URLEncoder.encode(fileUrl,"UTF-8").replace("+", "%20") ;
			
			String file1="http://192.168.1.6:8080/uploadimg/"+fileUrl1;
			try {
	            URLConnection connection = new URL(file1).openConnection();
	            InputStream input = connection.getInputStream();
	            OutputStream output = new FileOutputStream(new File("F:/ERP/upload/cmt/"+fileUrl));
	            try {
	                byte[] buffer = new byte[1024];
	                int i = 0;
	                while ((i = input.read(buffer)) != -1) {
	                    output.write(buffer, 0, i);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                output.flush();
	                output.close();
	                input.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
			out.write("将附件保存到ERP上");
		}
		int total=qservice.add(qu);
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
	
	}
	/**
	 * 
	 * @Title:ExternalinterfaceServlet
	 * @Description:极简会议同步
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void publicComment2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String statusEnter= request.getParameter("statusEnter");
		JSONObject OBJ=JSONObject.fromObject(statusEnter);
		String projectNo = (String)OBJ.get("projectNo");
	    String meetingName=(String)OBJ.get("meetingName");;
		String operator=(String)OBJ.get("meetingInputer");;
		String meetingDescribe=(String)OBJ.get("meetingDescribe");
		if(meetingName!=null&&!"".equalsIgnoreCase(meetingName)){
		meetingName = new String(meetingName.getBytes("iso-8859-1"),"utf-8");
		}
		if(meetingDescribe!=null&&!"".equalsIgnoreCase(meetingDescribe)){
		meetingDescribe = new String(meetingDescribe.getBytes("iso-8859-1"),"utf-8");
		}
		String details = meetingName+","+meetingDescribe;
		QuotePrice qu=new QuotePrice();
		qu.setDetails(details);
		qu.setProjectNo(projectNo);
		qu.setOperator(operator);
	    int total=qservice.add(qu);
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
	}
	/**
	 * 
	 * @Title:ExternalinterfaceServlet
	 * @Description:极简任务同步
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void publicComment3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String statusEnter= request.getParameter("statusEnter");
		JSONObject OBJ=JSONObject.fromObject(statusEnter);
		String projectNo = (String)OBJ.get("projectNo");
		String meetingDescribe=(String)OBJ.get("description");;
		String operator=(String)OBJ.get("initiator");;
		meetingDescribe = new String(meetingDescribe.getBytes("iso-8859-1"),"utf-8");
		String details = meetingDescribe;
		QuotePrice qu=new QuotePrice();
		qu.setDetails(details);
		qu.setProjectNo(projectNo);
		qu.setOperator(operator);
		int total=qservice.add(qu);
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
		
	}
	/**
	 * 方法描述:将信息保存到数据库
	 * author:wy
	 * date:2017年9月5日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	public void publicComment1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		//&content1"+content1+"&&filename="+filename+"&&projectId="+projectId+"&&username="+EmpEName
		String projectNo = request.getParameter("projectId");
		String fileUrl=request.getParameter("filename");
		String details=request.getParameter("content1");
		String operator=request.getParameter("username");
		details = new String(details.getBytes("iso-8859-1"),"utf-8");
		fileUrl = new String(fileUrl.getBytes("iso-8859-1"),"utf-8");
		QuotePrice qu=new QuotePrice();
		qu.setDetails(details);
		qu.setFileUrl(fileUrl);
		qu.setProjectNo(projectNo);
		qu.setOperator(operator);
		if(fileUrl!=null&&!"".equals(fileUrl)){
			String fileUrl1 = URLEncoder.encode(fileUrl,"UTF-8").replace("+", "%20") ;
			
			String file1="http://117.144.21.74:43900/NBEmail/image/"+fileUrl1;
			try {
				URLConnection connection = new URL(file1).openConnection();
				InputStream input = connection.getInputStream();
				OutputStream output = new FileOutputStream(new File("F:/ERP/upload/cmt/"+fileUrl));
				try {
					byte[] buffer = new byte[1024];
					int i = 0;
					while ((i = input.read(buffer)) != -1) {
						output.write(buffer, 0, i);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					output.flush();
					output.close();
					input.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			out.write("将附件保存到ERP上");
		}
		int total=qservice.add(qu);
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
		
	}
	public static void projectDrawing1() {
		try{
		List<AllDrawings>list=aservice.getDrawingsAll();
		if(list.size()>0){
		String json=SerializeUtil.ListToJson(list);
		Map<Object, String> map = new HashMap<Object,String>();
		map.put("projectDrawing", json);
	    new SendProjectDrawing(map).start();
    	
		}
		}catch(Exception e){
		LOG.warn("同步图纸信息报错"+e);	
		}
	}
	public static void inspectionReport1() {
		try{
		List<AllDrawings>list=aservice.getDrawingsAll1();
		if(list.size()>0){
		String json=SerializeUtil.ListToJson(list);
		Map<Object, String> map = new HashMap<Object,String>();
		map.put("inspectionReport", json);
		new SendProjectDrawing1(map).start();
		//ItCaseIdServlet.sendPost("http://192.168.1.6:8080/inspectionReport/synchInspectionReport",map);
		}
		}catch(Exception e){
			LOG.warn("同步质检图纸报错"+e);	
			}
	}

	/**
	 * 
	 * @Title:ExternalinterfaceServlet
	 * @Description:TODO
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void projectNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String projectIdList = request.getParameter("projectIdList");
		
		String [] json=projectIdList.split(",");
		
		if(json.length>0){
			List<Bonusdata>list1=new ArrayList<Bonusdata>();
		for(int i=0;i<json.length;i++){
			 String projectId=json[i];
			 if(projectId!=null&&!"".equals(projectId)){
		    Bonusdata bouns=bservice.getall(projectId);
		      list1.add(bouns);
			 }
		  }
		String json1=SerializeUtil.ListToJson(list1);
		
		if(list1.size()>0){
			out.print(json1);
		}
		}
	}
	/**
	 * 
	 * @Title:ExternalinterfaceServlet
	 * @Description:获取客户需求
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void getContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String projectNo = request.getParameter("projectNo");
		String content=service.getContent(projectNo);
		if(content!=null&&!"".equals(content)){
			out.print(content);
		}else{
			out.print("");
		}
	}
	
	/**
	 * 
	 * @Title:ExternalinterfaceServlet
	 * @Description:质量分析报告同步到ERP
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
	public void SendReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String statusEnter= request.getParameter("QualityAnalysisTable");
		JSONObject OBJ=JSONObject.fromObject(statusEnter);
		String projectId = (String)OBJ.get("projectId");
		int quantityDrawings1=0;
		if(OBJ.get("quantityDrawings")!=null&&!"".equals(OBJ.get("quantityDrawings"))){
		 quantityDrawings1=(int)OBJ.get("quantityDrawings");
		}
		int bomTable1=0;
		if(OBJ.get("bomTable")!=null&&!"".equals(OBJ.get("bomTable"))){
		 bomTable1=(int)OBJ.get("bomTable");
		}
		int annotation1=(int)OBJ.get("annotation");
		int assemblyRelationship1=(int)OBJ.get("assemblyRelationship");
		String maxPrecisionRequiremen=(String)OBJ.get("maxPrecisionRequiremen");
		String precisionGrade=(String)OBJ.get("precisionGrade");
		String maxRoughnessRequirement=(String)OBJ.get("maxRoughnessRequirement");
		String surfaceTreatment=(String)OBJ.get("surfaceTreatment");
		String materialsAbroad=(String)OBJ.get("materialsAbroad");
		String correspondingNationalStandard=(String)OBJ.get("correspondingNationalStandard");
		String listStandards=(String)OBJ.get("listStandards");
		String standardNotFound=(String)OBJ.get("standardNotFound");
		String heatTreatmentRequirements=(String)OBJ.get("heatTreatmentRequirements");
		String customerRequirements=(String)OBJ.get("customerRequirements");
		String designDefects=(String)OBJ.get("designDefects");
		String recommendedProcess=(String)OBJ.get("recommendedProcess");
		String suggestCommunicationProblems=(String)OBJ.get("suggestCommunicationProblems");
		String userName=(String)OBJ.get("userName");
		maxPrecisionRequiremen = new String(maxPrecisionRequiremen.getBytes("iso-8859-1"),"utf-8");
		precisionGrade = new String(precisionGrade.getBytes("iso-8859-1"),"utf-8");
		maxRoughnessRequirement = new String(maxRoughnessRequirement.getBytes("iso-8859-1"),"utf-8");
		
		surfaceTreatment = new String(surfaceTreatment.getBytes("iso-8859-1"),"utf-8");
		materialsAbroad = new String(materialsAbroad.getBytes("iso-8859-1"),"utf-8");
		correspondingNationalStandard = new String(correspondingNationalStandard.getBytes("iso-8859-1"),"utf-8");
		listStandards = new String(listStandards.getBytes("iso-8859-1"),"utf-8");
		standardNotFound = new String(standardNotFound.getBytes("iso-8859-1"),"utf-8");
		heatTreatmentRequirements = new String(heatTreatmentRequirements.getBytes("iso-8859-1"),"utf-8");
		customerRequirements = new String(customerRequirements.getBytes("iso-8859-1"),"utf-8");
		designDefects = new String(designDefects.getBytes("iso-8859-1"),"utf-8");
		recommendedProcess = new String(recommendedProcess.getBytes("iso-8859-1"),"utf-8");
		suggestCommunicationProblems = new String(suggestCommunicationProblems.getBytes("iso-8859-1"),"utf-8");
		userName = new String(userName.getBytes("iso-8859-1"),"utf-8");
		
		QualityAnalysisTable qualityAnalysisTable=new QualityAnalysisTable();
		qualityAnalysisTable.setAnnotation(annotation1);
	    qualityAnalysisTable.setAssemblyRelationship(assemblyRelationship1);
	    qualityAnalysisTable.setBomTable(bomTable1);
	    qualityAnalysisTable.setCorrespondingNationalStandard(correspondingNationalStandard);
	    qualityAnalysisTable.setCustomerRequirements(customerRequirements);
	    qualityAnalysisTable.setDesignDefects(designDefects);
	    qualityAnalysisTable.setHeatTreatmentRequirements(heatTreatmentRequirements);
	    qualityAnalysisTable.setListStandards(listStandards);
	    qualityAnalysisTable.setMaterialsAbroad(materialsAbroad);
	    qualityAnalysisTable.setMaxPrecisionRequiremen(maxPrecisionRequiremen);
	    qualityAnalysisTable.setPrecisionGrade(precisionGrade);
	    qualityAnalysisTable.setProjectId(projectId);
	    qualityAnalysisTable.setQuantityDrawings(quantityDrawings1);
	    qualityAnalysisTable.setRecommendedProcess(recommendedProcess);
	    qualityAnalysisTable.setStandardNotFound(standardNotFound);
	    qualityAnalysisTable.setSuggestCommunicationProblems(suggestCommunicationProblems);
	    qualityAnalysisTable.setSurfaceTreatment(surfaceTreatment);
	    qualityAnalysisTable.setUserName(userName);
	    qualityAnalysisTable.setMaxRoughnessRequirement(maxRoughnessRequirement);
		
		int total=qaservice.add(qualityAnalysisTable);
		if(total>0){
			out.print("YES");
		}else{
			out.print("NO");
		}
		
	}

	
	
	 /**
	  *         
	  * @Title:ExternalinterfaceServlet
	  * @Description:质检报告同步ERP
	    @author wangyang
	  * @param request
	  * @param response
	  * @throws ServletException
	  * @throws IOException
	  * @throws ParseException void
	  * @throws
	  */
	
	public void qualityReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		PrintWriter out = response.getWriter();
		String qualityReport = request.getParameter("qualityReport");
		JSONObject OBJ=JSONObject.fromObject(qualityReport);
		String type = OBJ.get("type").toString();
		String uploader=(String)OBJ.get("user");
		String projectNo=(String)OBJ.get("projectNo");
		String state="0";
		state=OBJ.get("status").toString();
		String reason="";
		if("2".equals(state)){
		if("0".equals(type)){
			reason="["+projectNo+"] 样品检验,有问题需要采购解决";
		}else if("1".equals(type)){
			reason="["+projectNo+"] 大货样品检验,有问题需要采购解决";
		}else if("2".equals(type)){
			reason="["+projectNo+"] 中期检验,有问题需要采购解决";
		}else if("3".equals(type)){
			reason="["+projectNo+"] 终期检验,有问题需要采购解决";
		}
		}else{
			if("0".equals(type)){
				reason="["+projectNo+"] 样品检验,没问题";
			}else if("1".equals(type)){
				reason="["+projectNo+"] 大货样品检验,没问题";
			}else if("2".equals(type)){
				reason="["+projectNo+"] 中期检验,没问题";
			}else if("3".equals(type)){
				reason="["+projectNo+"] 终期检验,没问题";
			}
		}
		
		//String createtime=(String)OBJ.get("createtime");
	
		String qualityReportUrl=(String)OBJ.get("qualityReportUrl");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		int total=service.add(uploader,projectNo,reason,formatter.format(new Date()),qualityReportUrl);
		if(total>0){
			out.print("YES");
		}else {
			out.print("NO");
		}
	}
		
	
	
	
	
	
/**
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:TODO
   @author wangyang
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 * @throws ParseException void
 * @throws
 */
public void bonusdat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
	PrintWriter out = response.getWriter();
	
	String year1=request.getParameter("year");
	String month1=request.getParameter("month");
	int year=0;
	int month=0;
	if(year1!=null&&!"".equals(year1)){
		year= Integer.parseInt(year1);
	}
	if(month1!=null&&!"".equals(month1)){
		month= Integer.parseInt(month1);
	}
	List<Bonusdata>list=bservice.updateGrossProfit(year,month);
    
         
}
/**
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:上传内部报价单
   @author wangyang
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 * @throws ParseException void
 * @throws
 */
public void quotationsUploaded(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
	PrintWriter out = response.getWriter();
	String userName=request.getParameter("userName");
	String projectId=request.getParameter("projectId");
	String path = "F://ERP//upload//neibubaojia" ;// TODO
	String value=projectId+".xlsx";
	String fileName=path + File.separator+ value;
	boolean falg = true;
	boolean falg1 = true;
	// String
	// file1="http://140.82.48.255:8080/email/"+username+"/"+value;//TODO
	String file1 = "http://117.144.21.74:43888/static_img/" + value;// TODO
	// String
	// file1="http://192.168.1.91:8080/NBEmail/download7?filename=20180526003826.eml&&username=Ed";//TODO

	String path1 = "F:\\ERP\\upload\\neibubaojia\\" ;// TODO
	int num = 100000;
	File storefile = new File(path1, value);
	String sb3 = null;
	for (int i = 0; storefile.exists(); i++) {// 如果存在同名的附件，则在后面添加数字区分
		// 获取文件名称后面的文件组后一个.的下标（后缀名）
		num++;
		int index = value.lastIndexOf(".");
        Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyyMMddHHmmss");
			String time1 = format.format(date);
            sb3 = value.replace(value.substring(index-1), time1 + "");
			value = sb3 + value.substring(index);
			storefile = new File(path, value);
		}
		
	
	
	
	try {
			URL url = new URL(file1);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(120 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(path);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(path + File.separator
					+ value);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (Exception e) {
			falg = false;
			e.printStackTrace();
		} finally {

		}
	int fid=0;
	if("pandage".equalsIgnoreCase(userName)){
		fid=6176;
	}else if("chengmingkun".equalsIgnoreCase(userName)){
		fid=6176;
	}else if("VivianXiong".equalsIgnoreCase(userName)){
		fid=6176;
	}else if("MinNXu".equalsIgnoreCase(userName)){
		fid=6176;
	}else if("OwenCui".equalsIgnoreCase(userName)){
		fid=6176;
	}
	if(projectId!=null&&!"".equals(projectId)){
	 int total=	service.insert(value,projectId,userName,fid);
	}
	}
/**
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:上传对外报价单
   @author wangyang
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 * @throws ParseException void
 * @throws
 */
public void externalQuotation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
	PrintWriter out = response.getWriter();
	String userName=request.getParameter("userName");
	String projectId=request.getParameter("projectId");
	String urlName=request.getParameter("urlName");
	String path = "F://ERP//upload//duiwaibaojia" ;// TODO
	String value=urlName;
	String fileName=path + File.separator+ value;
	boolean falg = true;
	boolean falg1 = true;
	// String
	// file1="http://140.82.48.255:8080/email/"+username+"/"+value;//TODO
	String file1 = "http://117.144.21.74:43888/static_img/" + value;// TODO
	//String file1 = "http://117.144.21.74:43888/static_img/" + value;// TODO
	String path1 = "F:\\ERP\\upload\\duiwaibaojia\\" ;// TODO
	int num = 100000;
	File storefile = new File(path1, value);
	String sb3 = null;
	for (int i = 0; storefile.exists(); i++) {// 如果存在同名的附件，则在后面添加数字区分
		// 获取文件名称后面的文件组后一个.的下标（后缀名）
		num++;
		int index = value.lastIndexOf(".");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String time1 = format.format(date);
		sb3 = value.replace(value.substring(index-1), time1 + "");
		value = sb3 + value.substring(index);
		storefile = new File(path, value);
	}
	
	
	
	
	try {
		URL url = new URL(file1);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(120 * 1000);
		// 输入流
		InputStream is = con.getInputStream();
		
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(path);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(path + File.separator
				+ value);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	} catch (Exception e) {
		falg = false;
		e.printStackTrace();
	} finally {
		
	}
	int fid=6176;
	
	if(projectId!=null&&!"".equals(projectId)){
		int total=	service.insert1(value,projectId,userName,fid);
	}
}

/**
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:TODO
   @author wangyang
 * @param request
 * @param response

 * @return String
 * @throws
 */

public String modifyingProjectMembers(HttpServletRequest request,HttpServletResponse response){
	    String rolename=request.getParameter("roleName");
	     String userName=request.getParameter("userName");
	     String password=request.getParameter("password");
	     String emailAddress=request.getParameter("emailAddress");
	     String emailPwd=request.getParameter("emailPwd");
	     String phone=request.getParameter("phone");
	     String flag=request.getParameter("flag");
	     String quotationRole=request.getParameter("erpRole");//内部报价角色
	     int quotationRole1=0;
	     if(quotationRole!=null&&!"".equals(quotationRole)){
	    	 quotationRole1=Integer.parseInt(quotationRole);
	     }
	     int flag1=0;
	     if(flag!=null&&!"".equals(flag)){
	    	 flag1=Integer.parseInt(flag);
	     }
	    
	     EmailUser user=new EmailUser();
	     user.setUserName(userName);
	     user.setPwd(password);
	     user.setEmailAddress(emailAddress);
	     user.setRoleNo(quotationRole1);
	     user.setFlag(flag1);
	     EmailUser user1=eservice.search(user);
	     if(userName!=null&&!"".equals(userName)){
	     if(user1!=null){
	    	 eservice.update(user);
	     }else{
	    	 eservice.insert1(user); 
	    	 eservice.createUser(user.getUserName());
	     }
	     }
	      return "success";
	}
/**
 * @throws IOException 
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:修改全部成员
   @author wangyang
 * @param request
 * @param response
 * @return String
 * @throws
 */
public void modifyingAllCustomerMembers(HttpServletRequest request,HttpServletResponse response) throws IOException{
	
	PrintWriter out = response.getWriter();
	String result=request.getParameter("itemcase");
	JSONArray json = JSONArray.fromObject(result); // 首先把字符串转成 JSONArray  对象
		ItemCase2 item=new ItemCase2();
	 try {
	
		if(json.size()>0){
		  for(int i=0;i<json.size();i++){
		    JSONObject obj = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
           if(obj== null && obj.isNullObject()){
           	continue;
           }
           String caseNo = (String) obj.get("projectNo");
           item.setCaseNo(caseNo);
		    String customerManager=""; 
		    if(obj.get("customerManager")!="" && obj.get("customerManager")!=null){
		    	customerManager = obj.get("customerManager").toString();
		    	item.setCustomerManager(customerManager);
		    }
		    String merchandManager1=""; 
		    if(obj.get("customerManager")!="" && obj.get("merchandManager1")!=null){
		    	merchandManager1 = obj.get("merchandManager1").toString();
		    	item.setMerchandManager1(merchandManager1);
		    }
		    String merchandManager2=""; 
		    if(obj.get("merchandManager2")!="" && obj.get("merchandManager2")!=null){
		    	merchandManager2 = obj.get("merchandManager2").toString();
		    	item.setMerchandManager2(merchandManager2);
		    }
		    String zhijian1=""; 
		    if(obj.get("zhijian1")!="" && obj.get("zhijian1")!=null){
		    	zhijian1 = obj.get("zhijian1").toString();
		    	item.setZhijian1(zhijian1);
		    }
		    String zhijian2=""; 
		    if(obj.get("zhijian2")!="" && obj.get("zhijian2")!=null){
		    	zhijian2 = obj.get("zhijian2").toString();
		    	item.setZhijian2(zhijian2);
		    }
		    String originalPurchase=""; 
		    if(obj.get("originalPurchase")!="" && obj.get("originalPurchase")!=null){
		    	originalPurchase = obj.get("originalPurchase").toString();
		    	item.setOriginalPurchase(originalPurchase);
		    }
		    String maturePurchase=""; 
		    if(obj.get("maturePurchase")!="" && obj.get("maturePurchase")!=null){
		    	maturePurchase = obj.get("maturePurchase").toString();
		    	item.setMaturePurchase(maturePurchase);
		    }
		    String merchandising=""; 
		    if(obj.get("matureDocumentary")!="" && obj.get("matureDocumentary")!=null){
		    	merchandising = obj.get("matureDocumentary").toString();
		    	item.setMerchandising(merchandising);
		    }
		   int total= service.updateAll(item);
		    
		    
		  }
		}
}catch(Exception e){
	
}
	 out.print("YES");
}
/**
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:添加工厂
   @author wangyang
 * @param request
 * @param response
 * @throws IOException void
 * @throws
 */
public void addFactory(HttpServletRequest request,HttpServletResponse response) throws IOException{
	
	PrintWriter out = response.getWriter();
	String factoryName=request.getParameter("factoryName");
	factoryName = new String(factoryName.getBytes("iso-8859-1"),"utf-8");
	String factoryId=request.getParameter("factoryId");
	int total=service.add(factoryName, factoryId);
	out.print("YES");
}
/**
 * 
 * @Title:ExternalinterfaceServlet
 * @Description:添加工厂
   @author wangyang
 * @param request
 * @param response
 * @throws IOException void
 * @throws
 */
public void modifyingMembers(HttpServletRequest request,HttpServletResponse response) throws IOException{
	PrintWriter out = response.getWriter();
	String projectNo=request.getParameter("projectNo");
	String technicalSupport1=request.getParameter("technicalSupport1");
	String projectLevel1=request.getParameter("ProjectLevel");
	int projectLevel=0;
    if(projectLevel1!=null&&!"".equals(projectLevel1)){
    	projectLevel=Integer.parseInt(projectLevel1);
    }
	ItemCase2 item=new ItemCase2();
	item.setCaseNo(projectNo);
	item.setProjectLevel(projectLevel);
	item.setTechnicalSupport1(technicalSupport1);
	int total=service.updateAll(item);
	out.print("YES");
}

	/**
	 * 获取质量扣款数据
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	public void allDeductionProject(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String projectNo=request.getParameter("allDeductionProject");
		String bonusDate = request.getParameter("bonusData");
		Date date=DateUtil.StrToDate(bonusDate+"-01");
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(date);
		theCa.add(theCa.MONTH, +1);
		Date date1=theCa.getTime();
        projectNo=new String(projectNo.getBytes("iso-8859-1"),"utf-8");
		List<Bonusdata>list=bservice.getAllDeductionProject(projectNo,date1);
		String json=SerializeUtil.ListToJson(list);
		if(list.size()>0){
			out.print(json);
		}

	}

	/**
	 * 根据项目号查询退税项目
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void qualityDeduction(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String projectNo = request.getParameter("qualityDeduction");

		projectNo = new String(projectNo.getBytes("iso-8859-1"), "utf-8");
		List<Bonusdata> list = bservice.getQualityDeduction(projectNo);
		String json = SerializeUtil.ListToJson(list);
		if (list.size() > 0) {
			out.print(json);
		}

	}

	/**
	 * 修改A/B级项目上传文件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
		public void efficiencyReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
			PrintWriter out = response.getWriter();
			String efficiencyReport = request.getParameter("efficiencyReport");
			JSONObject OBJ=JSONObject.fromObject(efficiencyReport);
			String fileName = OBJ.get("fileName").toString();
			String projectNo=(String)OBJ.get("projectNo");
			String uploader=(String)OBJ.get("uploader");
			String category="0";
			category=OBJ.get("category").toString();
			AllDrawings drawing=new AllDrawings();
			drawing.setProjectNo(projectNo);
			drawing.setDrawingName(fileName);
			drawing.setReportName(uploader);
			drawing.setCategory(Integer.parseInt(category));
            aservice.insert(drawing);
			if(fileName!=null&&!"".equalsIgnoreCase(fileName)){
				String file1="http://192.168.1.91:8080/product_img/"+fileName;
				try {
					URLConnection connection = new URL(file1).openConnection();
					InputStream input = connection.getInputStream();
					OutputStream output = new FileOutputStream(new File("F:/ERP/upload/zhongwentuzhi/"+fileName));
					try {
						byte[] buffer = new byte[1024];
						int i = 0;
						while ((i = input.read(buffer)) != -1) {
							output.write(buffer, 0, i);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						output.flush();
						output.close();
						input.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			int total=0;
			if(total>0){
				out.print("YES");
			}else {
				out.print("NO");
			}
		}

	}









