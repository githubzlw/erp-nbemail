package cerong.erp.servlet;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cerong.erp.entity.*;
import cerong.erp.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cerong.erp.util.CheckUtil;
import cerong.erp.dao.InvinceDaoImpl;
import cerong.erp.lucene.bean.FileBean;
import cerong.erp.lucene.bean.FileUtil;
import cerong.erp.util.Client;
import cerong.erp.util.MyFileUpLoad;
import cerong.erp.util.PathUtil;
import cerong.erp.util.SerializeUtil;

public class ItCaseIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ItCaseIdServiceImpl service = new ItCaseIdService();
	private static final int PAGE_SIZE = 20;//每页显示的最大数量
	private static final int PAGESIZE = 2;//每页显示的最大数量
	IEmailUserService uservice = new EmailUserServiceImpl();
	IEmployeeServiceImpl eservice = new EmployeeService();
	ICustomerServiceImpl cservice = new CustomerService();
	IInvinceServiceImpl iservice = new InvinceService();
	IMouldServiceImpl mservice = new MouldService();
	ITuZhiServiceImpl tservice = new TuZhiService();
	IFactoryFundServiceImpl fservice=new FactoryFundService();
	IQualityDeductionFormService qservice = new QualityDeductionFormServiceImpl();
	IProjectCustomerServiceImpl pservice = new ProjectCustomerService();
	ICheckUpServiceImpl cuservice = new CheckUpService();
	IinvoiceinfoServiceImpl inservice=new invoiceinfoService();
	IAllDrawingsServiceImpl aservice = new AllDrawingsService();
	private static final Logger LOG = LoggerFactory.getLogger(ItCaseIdServlet.class);
	/**
	 * 方法描述:修改项目现有销售
	 * author:wy
	 * date:2016年4月15日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateSaleName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String saleName = request.getParameter("saleName");
		String strProjectId = request.getParameter("strProjectId");
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
		
		
		if(roleNo1!=null){
		
		int result=service.updateSaleName(saleName,strProjectId);
		   if(result>0){
			 out.write("<script>");
			 out.write("ERP上修改现有销售成功");
			 out.write("</script>");
		   }else{
			 out.write("<script>");
			 out.write("ERP上修改现有销售失败");
			 out.write("</script>");	
		   }	
	   
		}else{
			out.write("<script>");
			out.write("请登陆后在修改项目销售");
			out.write("</script>");	
		}
	}
	
	/**
	 * 方法描述:修改项目现有跟单销售
	 * author:wy
	 * date:2016年4月15日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void updateSaleName1(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String saleName1 = request.getParameter("saleName1");
		String strProjectId = request.getParameter("strProjectId");
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
		
		if(roleNo1!=null){
		
		int result=service.updateSaleName1(saleName1,strProjectId);
		   if(result>0){
			 out.write("<script>");
			 out.write("ERP上修改现有跟单销售成功");
			 out.write("</script>");
		   }else{
			 out.write("<script>");
			 out.write("ERP上修改现有跟单销售失败");
			 out.write("</script>");	
		   }	
	 
		}else{
			
			out.write("<script>");
			out.write("请登陆后在修改项目销售");
			out.write("</script>");
		}
	}
	/**
	 * *****************************************************************************************
	 * 类描述：修改成熟跟单
	 *
	 * @author: wangyang
	 * @date： 2018-05-05 
	 * @version 1.0
	 *
	 *
	 * Version    Date                ModifiedBy                 Content
	 * --------   ---------           ----------                -----------------------
	 * 1.0.0        2018-05-03          wangyang                    初版
      *******************************************************************************************
	 */
	public void updateMerchandiser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String saleName1 = request.getParameter("saleName1");
		String strProjectId = request.getParameter("strProjectId");
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
		
		if(roleNo1!=null){
			
			int result=service.updateMerchandiser(saleName1,strProjectId);
			if(result>0){
				out.write("<script>");
				out.write("ERP上修改现有跟单销售成功");
				out.write("</script>");
			}else{
				out.write("<script>");
				out.write("ERP上修改现有跟单销售失败");
				out.write("</script>");	
			}	
			
		}else{
			
			out.write("<script>");
			out.write("请登陆后在修改项目销售");
			out.write("</script>");
		}
	}
	/**
	 * 方法描述:删除项目现有销售
	 * author:wy
	 * date:2016年4月15日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void deleteMerchandiser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String strProjectId = request.getParameter("strProjectId");
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
		if(roleNo1!=null){
		
		int result=service.deleteMerchandiser(strProjectId);
		   if(result>0){
			 out.write("<script>");
			 out.write("ERP上删除现有销售成功");
			 out.write("</script>");
		   }else{
			 out.write("<script>");
			 out.write("ERP上删除现有销售失败");
			 out.write("</script>");	
		   }	
	   	
		}else{
			out.write("<script>");
			out.write("请登陆后在修改项目销售");
			out.write("</script>");
			
		}
	}
	/**
	 * 
	 * @Title:ItCaseIdServlet
	 * @Description:替换该客户全部销售
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	public void updateAllSaleName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String saleName = request.getParameter("saleName");
		String customerId1 = request.getParameter("customerId");
		
		int customerId=0;
		if(customerId1 != null && !"".equals(customerId1)) {
			customerId= Integer.parseInt(customerId1);
		}
		int result=0;
		if(customerId!=0){
			result=service.updateAllSaleName(customerId,saleName,1);
		}
		
		
		   if(result>0){
			 out.write("<script>");
			 out.write("ERP上修改现有销售成功");
			 out.write("</script>");
		   }else{
			 out.write("<script>");
			 out.write("ERP上修改现有销售失败");
			 out.write("</script>");	
		   }	
	   
		
	}
	
	/**
	 * 
	 * @Title:ItCaseIdServlet
	 * @Description:替换该客户全部跟单销售
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException void
	 * @throws
	 */
	
	
	public void updateAllSaleName1(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String saleName1 = request.getParameter("saleName1");
		
		String customerId1 = request.getParameter("customerId");
		
		int customerId=0;
		if(customerId1 != null && !"".equals(customerId1)) {
			customerId= Integer.parseInt(customerId1);
		}
		int result=0;
		if(customerId!=0){
			result=service.updateAllSaleName(customerId,saleName1,2);
		}
		   if(result>0){
			 out.write("<script>");
			 out.write("ERP上修改现有跟单销售成功");
			 out.write("</script>");
		   }else{
			 out.write("<script>");
			 out.write("ERP上修改现有跟单销售失败");
			 out.write("</script>");	
		   }	
	 
		
	}
	
	
	/**
	 * 方法描述:删除项目现有销售
	 * author:wy
	 * date:2016年4月15日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void deleteSaleName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String strProjectId = request.getParameter("strProjectId");
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
		if(roleNo1!=null){
		
		int result=service.deleteSaleName(strProjectId);
		   if(result>0){
			 out.write("<script>");
			 out.write("ERP上删除现有销售成功");
			 out.write("</script>");
		   }else{
			 out.write("<script>");
			 out.write("ERP上删除现有销售失败");
			 out.write("</script>");	
		   }	
	   	
		}else{
			out.write("<script>");
			out.write("请登陆后在修改项目销售");
			out.write("</script>");
			
		}
	}
	/**
	 * 方法描述:删除项目现有跟单销售
	 * author:wy
	 * date:2016年4月15日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void deleteSaleName1(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String strProjectId = request.getParameter("strProjectId");
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
		if(roleNo1!=null){
		
		int result=service.deleteSaleName1(strProjectId);
		   if(result>0){
			 out.write("<script>");
			 out.write("ERP上删除现有跟单销售成功");
			 out.write("</script>");
		   }else{
			 out.write("<script>");
			 out.write("ERP上删除现有跟单销售失败");
			 out.write("</script>");	
		   }	
	   
		
}else{
	
	out.write("<script>");
	out.write("请登陆后在修改项目销售");
	out.write("</script>");
}
	
	}
	
	/**
	 * 方法描述:创建项目
	 * author:wy
	 * date:2016年5月16日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void createProject (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String CaseNo=service.getCaseNo();
		
		MyFileUpLoad.upload(request, response);
		
		String cid1=(String) request.getAttribute("cid");
		String backsingleCase="";
		int cid=0;
		if(cid1 != null && !"".equals(cid1)) {
			cid= Integer.parseInt(cid1);
		}
		String value=null;
		String path=PathUtil.SENDFile;
		Map<String, String> map = (Map<String, String>) request.getAttribute("map");
		if(map != null && map.size() > 0) {//说明有附件上传，将附件保存到数据库
			try {
				Set<String> keySet = map.keySet();
		    	for (String key : keySet) {
		    		 value = map.get(key);//获取附件文件名
		    		//获取文件名称后面的文件组后一个.的下标（后缀名）
					int index = map.get(key).lastIndexOf(".");
					//获取附件文件类型
					String sb = map.get(key).substring(index+1);
					LOG.warn("附件文件类型:"+sb);
					if(!"png".equalsIgnoreCase(sb) && !"jpg".equalsIgnoreCase(sb) && !"jpeg".equalsIgnoreCase(sb)
							&& !"gif".equalsIgnoreCase(sb) && !"tiff".equalsIgnoreCase(sb)
							&& !"bmp".equalsIgnoreCase(sb)&& !"zip".equalsIgnoreCase(sb)) {//图片的话不需要建立索引检测
						LOG.warn(path+File.separator+value);
						List<FileBean> beans = FileUtil.getFolderFiles(path+File.separator+value);

						Date d1 = new Date();
						long t1 = d1.getTime();
						CheckUtil.createIndex(beans);
						
						Date d2 = new Date();
						long t2 = d2.getTime();
						LOG.warn("lucene创建索引、查询所花费的时间:"+(t2-t1));
			    	
				}
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		String content=(String) request.getAttribute("content");
		String saleName1=null;
		String saleName=(String) request.getAttribute("saleName");
		 saleName1=(String) request.getAttribute("saleName1");
		String quotername=(String) request.getAttribute("quotername");
		String projectDesce=(String) request.getAttribute("projectDesce");
		String projectDescc=(String) request.getAttribute("projectDescc");
		String ddlType1=(String) request.getAttribute("ddlType");
		int ddlType=0;
		if(ddlType1 != null && !"".equals(ddlType1)) {
			ddlType= Integer.parseInt(ddlType1);
		}
		String OrderGroup=(String) request.getAttribute("OrderGroup");
		OrderGroup = new String(OrderGroup.getBytes("iso-8859-1"),"utf-8");
		String customerGroup=(String) request.getAttribute("customerGroup");
		customerGroup = new String(customerGroup.getBytes("iso-8859-1"),"utf-8");
		String ddlSelectPriceDays1=(String) request.getAttribute("ddlSelectPriceDays");
		int ddlSelectPriceDays=0;
		if(ddlSelectPriceDays1 != null && !"".equals(ddlSelectPriceDays1)) {
			ddlSelectPriceDays= Integer.parseInt(ddlSelectPriceDays1);
		}
		String file=path+File.separator+value;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		String time=str;
		int total=service.addItCase(file,backsingleCase,CaseNo,cid,content,saleName,saleName1,projectDesce,projectDescc,ddlType,OrderGroup,customerGroup,ddlSelectPriceDays,time,quotername);
		if(total > 0) {//说明修改成功
			
			out.write("项目号为:"+CaseNo);
		}else {
			
			out.write("<script>");
			out.write("操作失败,项目未创建成功");
			out.write("window.location='/ERP-NBEmail/helpServlet?action=create&className=ItCaseIdServlet'");
			out.write("</script>");
		}
		
	}
	
		/**
		 * 方法描述:远程创建项目
		 * author:wy
		 * date:2016年5月17日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */

		public void transAudit (HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    String firstName = request.getParameter("firstName");
		int result=cservice.getfirstName(firstName);
		String file1=request.getParameter("file");
		String roleNo1=request.getParameter("roleNo");
		int roleNo=0;
		if(roleNo1 != null && !"".equals(roleNo1)) {
			roleNo = Integer.parseInt(roleNo1);
		  }
		//客户国家
		 String country1 = request.getParameter("country");
		 int country = 0;
		  if(country1 != null && !"".equals(country1)) {
			  country = Integer.parseInt(country1);
		  }
		 //客户大小
		 String ddlDgree = "小客户";

		 int cid=cservice.getID(firstName,country,ddlDgree);
		
		
		 String CaseNo=service.getCaseNo();
		
		
		
		
		String backsingleCase="";
		String content=request.getParameter("content");
		
		content = new String(content.getBytes("iso-8859-1"),"utf-8");
		
		String saleName1=null;
		String saleName=request.getParameter("saleName");
		String quotername=request.getParameter("quotername");
		 saleName1=request.getParameter("saleName1");
		String projectDesce=request.getParameter("projectDesce");
		projectDesce = new String(projectDesce.getBytes("iso-8859-1"),"gbk");
		String projectDescc=request.getParameter("projectDescc");
		projectDescc = new String(projectDescc.getBytes("iso-8859-1"),"gbk");
		int ddlType=11;
		
		String OrderGroup="Radiobutton4";
		
		String customerGroup="ddlCustomerType_new";
		
		int ddlSelectPriceDays=3;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		String time=str;
		
		String title="首次沟通";
		String empName="EdwardFan";
		
		int total2=service.addCase(title,content,CaseNo,empName,time,file1);
		int total=service.addItCase(file1,backsingleCase,CaseNo,cid,content,saleName,saleName1,projectDesce,projectDescc,ddlType,OrderGroup,customerGroup,ddlSelectPriceDays,time,quotername);
		
		if(total > 0) {//说明修改成功
			
			out.write("ERP上创建项目成功，项目号CaseNo:"+CaseNo+"客户id:"+cid);
			
			
			
		}else {
			
			out.write("ERP上创建项目失败");
			
	
		}
		
		}
		
		
		/**
		 * 方法描述:远程保存附件
		 * author:wy
		 * date:2016年6月2日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */

		
		public void Attachment (HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String roleNo1=request.getParameter("roleNo");
			int roleNo=0;
			if(roleNo1 != null && !"".equals(roleNo1)) {
				roleNo = Integer.parseInt(roleNo1);
			}
			String fileName=request.getParameter("fileName");
				fileName = new String(fileName.getBytes("iso-8859-1"),"gbk");
				System.out.print(fileName);
				String file1="http://117.144.21.74:43900/NBEmail/image/"+fileName;
				try {
		            URLConnection connection = new URL(file1).openConnection();
		            InputStream input = connection.getInputStream();
		            OutputStream output = new FileOutputStream(new File("F:/ERP/upload/FJ/"+fileName));
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
		/**
		 * 方法描述:远程创建老客户新项目
		 * author:wy
		 * date:2016年7月25日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */

		public void transAudit1 (HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	   
		String file1=request.getParameter("file");
		String erpcid1=request.getParameter("CustomerCode");
		int erpcid=0;
		if(erpcid1 != null && !"".equals(erpcid1)) {
			erpcid = Integer.parseInt(erpcid1);
		  }
		 //客户大小
		 String ddlDgree = "小客户";
        String CaseNo=service.getCaseNo();
		
		String backsingleCase="";
		String content=request.getParameter("content");
		content = new String(content.getBytes("iso-8859-1"),"utf-8");
		
		String saleName=request.getParameter("saleName");
		String saleName1=request.getParameter("saleName1");
		String transAudit1=request.getParameter("saleName");
		String quotername=request.getParameter("quotername");
		 
		String projectDesce=request.getParameter("projectDesce");
		projectDesce = new String(projectDesce.getBytes("iso-8859-1"),"gbk");
		String projectDescc=request.getParameter("projectDescc");
		projectDescc = new String(projectDescc.getBytes("iso-8859-1"),"gbk");
		int ddlType=11;
		String CaseNo1=service.getCaseNo2(erpcid);
		String OrderGroup="Radiobutton4";
		
		String customerGroup="ddlCustomerType_new";
		
		int ddlSelectPriceDays=3;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		String time=str;
		ItemCase it=service.getall(CaseNo1);
		String title="再次沟通";
		String empName="EdwardFan";
		
		int total2=service.addCase(title,content,CaseNo,empName,time,file1);
		int total=service.addItCase(file1,backsingleCase,CaseNo,erpcid,content,saleName,saleName1,projectDesce,projectDescc,ddlType,OrderGroup,customerGroup,ddlSelectPriceDays,time,quotername);
		int id=service.getId(CaseNo1);
		int total3=service.updatename(it,id);
		
		if(total > 0) {//说明修改成功
			
			out.write("ERP上创建项目成功，项目号CaseNo:"+CaseNo);
			
			
		}else {
			
			out.write("ERP上创建项目失败");
		
		}
		
		}
		/**
		 * 方法描述:远程保存附件
		 * author:wy
		 * date:2016年6月2日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */

		
		public void Attachment1 (HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			//String roleNo1=request.getParameter("roleNo");
			//String picStr=request.getParameter("picStr");
				String fileName=request.getParameter("fileName");
				fileName = new String(fileName.getBytes("iso-8859-1"),"gbk");
				//fileName = new String(fileName.getBytes("iso-8859-1"),"utf-8");
				System.out.print(fileName);
				//String file1=request.getParameter("file");
				
				String file1="http://117.144.21.74:43900/NBEmail/image/"+fileName;
				
				try {
		            URLConnection connection = new URL(file1).openConnection();
		            InputStream input = connection.getInputStream();
		            OutputStream output = new FileOutputStream(new File("F:/ERP/upload/FJ/"+fileName));
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
		/**
		 * 方法描述:远程创建老客户跟单项目
		 * author:wy
		 * date:2016年7月25日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */

		public void transAudit2 (HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	   String projectId1=request.getParameter("projectId");
		String projectId=null;
		if(projectId1 != null && !"".equals(projectId1)) {
			projectId = projectId1;
		  }
		String backsingleCase=projectId;
		System.out.print(backsingleCase);
		String CaseNo1=service.getCaseNo1(projectId);
		String CaseNo="";
		
		if(CaseNo1==null||"".equals(CaseNo1)){
			CaseNo=projectId+"-1";
			System.out.print(CaseNo);
		}else{
			 String a[] = CaseNo1.split("-");
			 String b=a[1];
			 int num=0;
			 int num1=0;
			 if(b!=null&&!"".equals(b)){
				  num= Integer.parseInt(b);
				  num1=num+1;
				 CaseNo= a[0]+"-"+num1; 
			 }
		}
		ItemCase it;
		if(CaseNo1!=null&&!"".equals(CaseNo1)){
		it=service.getall(CaseNo1);
		}else{
		it=service.getall(backsingleCase);
		}
		String content=request.getParameter("content");
		content = new String(content.getBytes("iso-8859-1"),"utf-8");
		String saleName1=null;
		String saleName=request.getParameter("saleName");
		String quotername=request.getParameter("quotername");
		String erpcid1=request.getParameter("erpcid");
		int erpcid=0;
		if(erpcid1 != null && !"".equals(erpcid1)) {
			erpcid = Integer.parseInt(erpcid1);
		  }
		 saleName1=request.getParameter("saleName1");
		String projectDesce=request.getParameter("projectDesce");
		projectDesce = new String(projectDesce.getBytes("iso-8859-1"),"gbk");
		String projectDescc=request.getParameter("projectDescc");
		projectDescc = new String(projectDescc.getBytes("iso-8859-1"),"gbk");
        Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		String time=str;
		int ddlSelectPriceDays=3;
		String title="再次沟通";
		String empName=saleName;
		int total=service.addItCase(backsingleCase,CaseNo,erpcid,content,saleName,saleName1,projectDesce,projectDescc,ddlSelectPriceDays,time,quotername);
		int id=service.getId(CaseNo);
		int total2=service.updatename(it,id);
		int num=service.addTuZhi(CaseNo,backsingleCase);//新跟单项目添加图纸
		int num1=service.addPOupload(CaseNo,backsingleCase);//添加新的检验计划
		int num2=service.addQuotePrice(CaseNo,backsingleCase);//添加评论到新项目
		int num3=service.addQuotationAnalysis(CaseNo,backsingleCase);//添加客户需求到新项目
		int num4=service.updateAll(CaseNo,CaseNo1);
		if(total > 0) {//说明修改成功
			out.write("ERP上创建项目成功，项目号CaseNo:"+CaseNo);
		}else {
			out.write("ERP上创建项目失败");
		}
		}
		/**
		 * 方法描述:获取客户全部项目
		 * author:wy
		 * date:2016年10月25日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void getItemCase (HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			String st = request.getParameter("page");
			HttpSession session = request.getSession();
			EmailUser user = (EmailUser)session.getAttribute("user");
			int page = 1;//默认是第一页
			if(st != null && !"".equals(st)) {
				page = Integer.parseInt(st);
			}
			int start = (page-1) * PAGE_SIZE;
			PrintWriter out = response.getWriter();
			String cid1=request.getParameter("cid");
			int cid=0;
			if(cid1 != null && !"".equals(cid1)) {
			   cid = Integer.parseInt(cid1);
			}
			List<ItemCase> list=service.getall(cid);
			//int total=service.getalltotal(cid);
			//SplitPage.buildPager(request, total, PAGE_SIZE, page);
			request.setAttribute("cusList",list );
			
			request.getRequestDispatcher("jsp/item_case.jsp").forward(request, response);
		}
		
		/**
		 * 方法描述:修改项目现有报价员
		 * author:wy
		 * date:2017年5月4日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		
		public void updatequoter(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String quoter = request.getParameter("quoter");
			String strProjectId = request.getParameter("strProjectId");
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
			
			
			if(roleNo1!=null){
			
			int result=service.updatequoter(quoter,strProjectId);
			   if(result>0){
				 out.write("<script>");
				 out.write("ERP上修改现有报价员成功");
				 out.write("</script>");
			   }else{
				 out.write("<script>");
				 out.write("ERP上修改现有销售失败");
				 out.write("</script>");	
			   }	
		   
			}else{
				out.write("<script>");
				out.write("请登陆后在修改项目销售");
				out.write("</script>");	
			}
		}
		/**
		 * 方法描述:删除项目现有报价员
		 * author:wy
		 * date:2017年5月5日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void deleteQuoter(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String strProjectId = request.getParameter("strProjectId");
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
			if(roleNo1!=null){
			
			int result=service.deletequoter(strProjectId);
			   if(result>0){
				 out.write("<script>");
				 out.write("ERP上删除现有报价员成功");
				 out.write("</script>");
			   }else{
				 out.write("<script>");
				 out.write("ERP上删除现有报价员失败");
				 out.write("</script>");	
			   }	
		   	
			}else{
				out.write("<script>");
				out.write("请登陆后在修改项目销售");
				out.write("</script>");
				
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
		
		
		/**startProjectStatistics
		 * 方法描述:调采购数据
		 * author:wy
		 * date:2017年6月5日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException 
		 */
		public void findproject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
			
			List<ItemCase2>list=service.getalll();
             String json=SerializeUtil.ListToJson(list);
				Map<Object, String> map = new HashMap<Object,String>();
				map.put("EmailClient", json);
				response.getWriter().print(map);
				response.getWriter().close();
		         String obj = SerializeUtil.ObjToJson(map);
		          System.out.println(obj);
		              //Client.sendPost("http://192.168.1.54:8099/crmconsole/port/insertSaleCustomer.do", map);
		          ItCaseIdServlet.sendPost("http://192.168.1.189:8084/Test/project/synchERPProject",map);
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
		public void pullinvoicedata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
			
			List<Invoiceinfo1>list=iservice.getall();
             String json=SerializeUtil.ListToJson(list);
				Map<Object, String> map = new HashMap<Object,String>();
				map.put("Invoiceinfo1", json);
				response.getWriter().print(map);
				response.getWriter().close();
		         String obj = SerializeUtil.ObjToJson(map);
		          System.out.println(obj);
		              //Client.sendPost("", map);
		          ItCaseIdServlet.sendPost("https://www.importx.net/supplier/port/importInvoiceInfo.do",map);
		       // String num= ItCaseIdServlet.sendPost("https://www.importx.net/supplier/port/importInvoiceInfo.do",map);
		          // System.out.print(num);
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
		public void ERPpulldata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
			List<ProjectCustomer>list=pservice.getall(1,1000);
			String json=SerializeUtil.ListToJson(list);
			json= URLEncoder.encode(json, "UTF-8");
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
		 * 方法描述:修改invoice
		 * author:wy
		 * date:2017年5月5日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void modifyinvoice(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			Invoiceinfo1 info=new Invoiceinfo1();
			String str=request.getParameter("payment");
			String projectId = request.getParameter("projectId");
			String invoiceId = request.getParameter("invoiceId");
			String amountUnit = request.getParameter("amountUnit");
			String amount = request.getParameter("amount");
			int amountUnit1=0;
			if(amountUnit != null && !"".equals(amountUnit)) {
				amountUnit1= Integer.parseInt(amountUnit);
			}
			double amount1=0.00;
			if(amount != null && !"".equals(amount)) {
				amount1= Double.parseDouble(amount);
			}
			info.setProjectId(projectId);
			info.setImoneytype(amountUnit1);
			info.setiInvNo(invoiceId);
			info.setiSum(amount1);
			response.getWriter().print(str);
			response.getWriter().close();
			JSONArray json = JSONArray.fromObject(str); // 首先把字符串转成 JSONArray  对象
			if(json.size()>0){
			  for(int i=0;i<json.size();i++){
			    JSONObject job = json.getJSONObject(i);
			    int paymentBank=Integer.parseInt(String.valueOf(job.get("paymentBank")));  // 客户eid
			    info.setIbank(paymentBank);
			    String paymentTime=String.valueOf(job.get("paymentTime"));
			    info.setIfdate(paymentTime);
			    String amounta=String.valueOf(job.get("amount"));
			    double amount2=0.00;
				if(amounta != null && !"".equals(amounta)) {
					amount2= Double.parseDouble(amounta);
				}
			    info.setIimoney(amount2);
			    int total=iservice.add(info);
			   if(total>0){
				   out.print("YES");	
			   }else{
				 out.print("NO");	
			   }	
			  }
			  }
		}
		
		/**
		 * 方法描述:查看全部项目质检报告是否上传
		 * author:wy
		 * date:2017年10月10日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void checkQualityReport(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhaowangweiping";
				 Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){	
				ItemCase it=new ItemCase();
				String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
				if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
					if("0".equals(condition)){
						it.setInspectionRequirements(0);
						request.setAttribute("fyzt", 0);
						}else if("1".equals(condition)){
						it.setInspectionRequirements(1);	
						request.setAttribute("fyzt", 1);	
						}else if("2".equals(condition)){
							it.setInspectionRequirements(2);	
							request.setAttribute("fyzt", 2);	
						}
					
					
				}
				String condition1 = request.getParameter("condition1");//获取下拉框输入的条件选项值
				if(condition1 != null && !"".equals(condition1)) {//说明有输入条件内容做查询
					if("0".equals(condition1)){
						it.setDrawingPicture(0);
						request.setAttribute("fyzt1", 0);
					}else if("1".equals(condition1)){
						it.setDrawingPicture(1);	
						request.setAttribute("fyzt1", 1);	
					}
					
					
				}
				String time1=request.getParameter("time1");
				if(time1 != null && !"".equals(time1)){
				it.setCreateTime(time1);
			   
				}
				
				List<ItemCase> list=service.getAllItem(it);
				
				request.setAttribute("cusList",list );
				
				request.getRequestDispatcher("jsp/po_upload.jsp").forward(request, response);
			}else{
				out.write("<script>");
				out.write("alert('对不起你没有权限查看质检报告统计页面');");
				out.write("window.location.href='jsp/login.jsp'");
				out.write("</script>");
			}
			}
		
		}
		
		/**
		 * 方法描述:查看全部项目第一次付款时间是否上传
		 * author:wy
		 * date:2017年10月13日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void projectContractTime(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				 Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){
				ItemCase it=new ItemCase();
				
				String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
				if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
					if(condition.equals("1")) {//说明输入的是项目号
						it.setCid(1);
						
					}else if(condition.equals("2")) {
						it.setCid(2);
					}else if(condition.equals("3")) {
						it.setCid(3);
					}
					
					
				}
				String condition1 = request.getParameter("condition1");//获取下拉框输入的条件选项值
				if(condition1 != null && !"".equals(condition1)) {//说明有输入条件内容做查询
					if(condition1.equals("0")) {//说明输入的是项目号
						it.setInspectionRequirements(0);
					}else if(condition1.equals("1")) {
						it.setInspectionRequirements(1);
					}else if(condition1.equals("2")) {
						it.setInspectionRequirements(2);
					}
					
					
				}
				List<ItemCase> list=service.getprojectContract(it);
				
				  File storefile = new File(PathUtil.FirstParagraph,"firstparagraph.xls");
					
					for(int i=0;storefile.exists();i++){
						storefile.delete();
					}
				  	// 第一步，创建一个webbook，对应一个Excel文件  
			        HSSFWorkbook wb = new HSSFWorkbook();  
			        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
			        HSSFSheet sheet = wb.createSheet("客户首笔到款日期");  
			        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
			        HSSFRow row = sheet.createRow((int) 0);  
			        // 第四步，创建单元格，并设置值表头 设置表头居中  
			        HSSFCellStyle style = wb.createCellStyle();  
			        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
			  
			        HSSFCell cell = row.createCell((short) 0);  
			         
			        cell.setCellValue("项目号");  
			        cell.setCellStyle(style);  
			        cell = row.createCell((short) 1);  
			        cell.setCellValue("首笔到款日期");  
			        cell.setCellStyle(style);  
			        cell = row.createCell((short) 2);  
			        cell.setCellValue("跟单销售");  
			        cell.setCellStyle(style); 
			        cell = row.createCell((short) 3);  
			        cell.setCellValue("采购");  
			        cell.setCellStyle(style); 
			        cell = row.createCell((short) 4);  
			        cell.setCellValue("合同及工厂");  
			        cell.setCellStyle(style); 
			        cell = row.createCell((short) 5);  
			        cell.setCellValue("原因");  
			        cell.setCellStyle(style); 
			       
			        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			        
			        for (int i = 0; i < list.size(); i++)  
			        {  
			            row = sheet.createRow((int) i + 1);  
			            ItemCase sc=list.get(i);
			            // 第四步，创建单元格，并设置值  
			            row.createCell((short) 0).setCellValue(sc.getCaseNo());  
			            row.createCell((short) 1).setCellValue(sc.getCreateTime()); 
			            row.createCell((short) 2).setCellValue(sc.getMerchandManager1());
			            row.createCell((short) 3).setCellValue(sc.getMerchandManager2());  
			            List<Contract> contract=sc.getContract();
			            String name="";
			            for (int j = 0; j < contract.size(); j++)  
				        {
			             Contract mc=contract.get(j);
			             name=name+" 工厂名: "+mc.getFactory();
			             name=name+" 合同号: "+mc.getContract();
				        }
			            row.createCell((short) 4).setCellValue(name);  
			            row.createCell((short) 5).setCellValue(sc.getProjectContractNote());  
			             
			        }  
			        // 第六步，将文件存到指定位置  
			        try  
			        {  
			            FileOutputStream fout = new FileOutputStream(PathUtil.FirstParagraph+File.separator+"firstparagraph.xls");  
			            wb.write(fout);  
			            fout.close();  
			        }  
			        catch (Exception e)  
			        {  
			            e.printStackTrace();  
			        }
				
				
				
				
				request.setAttribute("cusList",list );
				if(it.getCid()!=3){
				request.getRequestDispatcher("jsp/project_contract.jsp").forward(request, response);
				}else if(it.getCid()==3){
					request.getRequestDispatcher("jsp/project_contract1.jsp").forward(request, response);	
				}
				}else{
				out.write("<script>");
				out.write("alert('对不起你没有权限查看质检报告统计页面');");
				out.write("window.location.href='jsp/login.jsp'");
				out.write("</script>");
			}
			}
		
		}
		
		
		/**
		 * 方法描述:修改合同未上传理由
		 * author:wy
		 * date:2017年10月17日
		 * @param request
		 * @param response
		 */
		
		public void updateReason1(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				 Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){
				String reason=request.getParameter("reason");
				reason = new String(reason.getBytes("iso-8859-1"),"utf-8");
				String caseNo=request.getParameter("caseNo");
				int total=service.updateCaseNo(reason,caseNo);
				if(total>0){
					out.print("YES");
				}else{
					out.print("NO");
				}
			}else{
				out.write("<script>");
				out.write("alert('对不起你没有权限查看质检报告统计页面');");
				out.write("window.location.href='jsp/login.jsp'");
				out.write("</script>");
			}
			}
		
		}
		/**
		 * 方法描述:去除图纸关系
		 * author:wy
		 * date:2017年10月18日
		 * @param request
		 * @param response
		 */
		
		public void updateCaseNo(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
					String cid1=request.getParameter("id");
					int id=0;
					if(cid1 != null && !"".equals(cid1)) {
					   id = Integer.parseInt(cid1);
					}
					String caseNo=request.getParameter("caseNo");
					int total=tservice.updateCaseNo(id,caseNo);
					if(total>0){
						out.print("YES");
					}else{
						out.print("NO");
					}
				}else{
					out.write("<script>");
					out.write("alert('对不起你没有权限查看质检报告统计页面');");
					out.write("window.location.href='jsp/login.jsp'");
					out.write("</script>");
				}
			}
			
		}
		/**
		 * 方法描述:去除图纸关系
		 * author:wy
		 * date:2017年10月18日
		 * @param request
		 * @param response
		 */
		
		public void updateCaseNo1(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
					String cid1=request.getParameter("id");
					int id=0;
					if(cid1 != null && !"".equals(cid1)) {
						id = Integer.parseInt(cid1);
					}
					String caseNo=request.getParameter("caseNo");
					Boolean index=false;
					 index = caseNo.toLowerCase().contains("A".toLowerCase());
					if(index!=false){
						String []caseno=caseNo.split("A");
						caseNo=caseno[0];
					}
					int total=tservice.updateCaseNo1(id,caseNo);
					if(total>0){
						out.print("YES");
					}else{
						out.print("NO");
					}
				}else{
					out.write("<script>");
					out.write("alert('对不起你没有权限查看质检报告统计页面');");
					out.write("window.location.href='jsp/login.jsp'");
					out.write("</script>");
				}
			}
			
		}
		
		/**
		 * 方法描述:查看全部合同表
		 * author:wy
		 * date:2017年10月18日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void viewAllDrawings(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				 Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){
				ItemCase it=new ItemCase();
				
				String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
				if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
					it.setCaseNo(condition);
				}
				
				List<TuZhi> list=service.viewAllDrawings(it);
				request.setAttribute("cusList",list );
				request.getRequestDispatcher("jsp/view_all_drawings.jsp").forward(request, response);
			}else{
				out.write("<script>");
				out.write("alert('对不起你没有权限查看图纸统计页面');");
				out.write("window.location.href='jsp/login.jsp'");
				out.write("</script>");
			}
			}
		
		}
		/**
		 * 方法描述:查看全部会议记录表
		 * author:wy
		 * date:2017年10月20日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException 
		 */
		
		public void viewConferenceRecords(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
					MeetingRecord it=new MeetingRecord();
					
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
						
						it.setCaseNo(condition);
					}
					String conferenceMessage1 = request.getParameter("conferenceMessagea");//获取下拉框输入的条件选项值
					
					if(conferenceMessage1 != null && !"".equals(conferenceMessage1)) {//说明有输入条件内容做查询
						conferenceMessage1 = new String(conferenceMessage1.getBytes("iso-8859-1"),"utf-8");
						if("".equals(conferenceMessage1)){
							request.setAttribute("fyzt", 0);
							}else if("新项目启动会".equals(conferenceMessage1)){
							request.setAttribute("fyzt", 1);	
							}else if("项目周进展会".equals(conferenceMessage1)){
								request.setAttribute("fyzt", 2);	
							}else if("质量分析会(样品)".equals(conferenceMessage1)){
								request.setAttribute("fyzt", 3);	
							}else if("大货质量会议".equals(conferenceMessage1)){
								request.setAttribute("fyzt", 4);	
							}else if("质量分析会(小批量)".equals(conferenceMessage1)){
								request.setAttribute("fyzt", 5);	
							}
						
						it.setConferenceMessage(conferenceMessage1);
					}
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
						time1=time1.replaceAll("/0", "/");
				    it.setNewProjectTime(time1);
					}
					List<MeetingRecord> list=service.viewConferenceRecords(it);
					request.setAttribute("cusList",list );
					request.getRequestDispatcher("jsp/view_conference_records.jsp").forward(request, response);
				}else{
					out.write("<script>");
					out.write("alert('对不起你没有权限查看图纸统计页面');");
					out.write("window.location.href='jsp/login.jsp'");
					out.write("</script>");
				}
			}
			
		}
		/**
		 * 方法描述:查看会议记录表
		 * author:wy
		 * date:2017年11月24日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException 
		 */
		
		public void viewConferenceRecords1(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
					MeetingRecord it=new MeetingRecord();
					
					
					String conferenceMessage1 = request.getParameter("conferenceMessagea");//获取下拉框输入的条件选项值
					
					if(conferenceMessage1 != null && !"".equals(conferenceMessage1)) {//说明有输入条件内容做查询
						conferenceMessage1 = new String(conferenceMessage1.getBytes("iso-8859-1"),"utf-8");
						if("".equals(conferenceMessage1)){
							request.setAttribute("fyzt1", 0);
							it.setNewProject(3);
						}else if("新项目启动会".equals(conferenceMessage1)){
							it.setNewProject(3);
							request.setAttribute("fyzt1", 1);	
						}else if("项目周进展会".equals(conferenceMessage1)){
							it.setNewProject(6);
							request.setAttribute("fyzt1", 2);	
						}else if("质量分析会(样品)".equals(conferenceMessage1)){
							it.setNewProject(7);
							request.setAttribute("fyzt1", 3);	
						}else if("大货质量会议".equals(conferenceMessage1)){
							it.setNewProject(8);
							request.setAttribute("fyzt1", 4);	
						}else if("质量分析会(小批量)".equals(conferenceMessage1)){
							it.setNewProject(9);
							request.setAttribute("fyzt", 5);	
						}
						
						
					}
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
						time1=time1.replaceAll("/0", "/");
						it.setNewProjectTime(time1);
					}
					List<MeetingRecord> list=service.viewConferenceRecords1(it);
					request.setAttribute("cusList",list );
					request.getRequestDispatcher("jsp/view_conference_records1.jsp").forward(request, response);
				}else{
					out.write("<script>");
					out.write("alert('对不起你没有权限查看图纸统计页面');");
					out.write("window.location.href='jsp/login.jsp'");
					out.write("</script>");
				}
			}
			
		}
		
		/**
		 * 方法描述:提交数据到后台
		 * author:wy
		 * date:2017年11月08日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void reviseMeetingRecord(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String projectId=request.getParameter("caseno");
			String conferenceMessage=request.getParameter("conferenceMessage");
			String messsage=request.getParameter("message");
			
			
			messsage = new String(messsage.getBytes("iso-8859-1"),"utf-8");
			int total=service.updateConference(projectId,conferenceMessage,messsage);
			if(total>0){
				out.print("YES");
			}else{
				out.print("NO");	
			}
		}
		
		/**
		 * 方法描述:共模录入查看界面
		 * author:wy
		 * date:2017年12月09日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void LookNewProject(HttpServletRequest request, HttpServletResponse response)
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
				String s1 = "edwardfanemmaxieninazhaoOwenCui";
				 Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				
			if(index1!=false){	
				ItemCase it=new ItemCase();
				String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
				if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
					
					it.setCaseNo(condition);
				}
			List<ItemCase> cusList=service.getAllProject(it);
			request.setAttribute("cusList", cusList);//将客户信息保存（里面保存了客户的三个邮箱地址）
			//将客户信息保存（里面保存了客户的三个邮箱地址）
			request.getRequestDispatcher("jsp/common_mode.jsp").forward(request, response);
			}
			}
		
		}

		/**
		 * 方法描述:提交数据到后台
		 * author:wy
		 * date:2017年11月08日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void updateCommonMode(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String projectId=request.getParameter("caseNo");
			String sign=request.getParameter("sign");
			int commonMode=0;
			if(sign!=null&&!"".equals(sign)){
				commonMode=Integer.parseInt(sign);
			}
			String reason=request.getParameter("reason");
			reason = new String(reason.getBytes("iso-8859-1"),"utf-8");
			int total=service.updateCommonMode(projectId,reason,commonMode);
			if(total>0){
				out.print("YES");
			}else{
				out.print("NO");	
			}
		}
		/**
		 * 方法描述:提交数据到后台
		 * author:wy
		 * date:2017年11月08日
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		
		public void updateFactory(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String projectId=request.getParameter("caseNo");
			String factory1=request.getParameter("factory");
			int factory=0;
			if(factory1!=null&&!"".equals(factory1)){
				factory=Integer.parseInt(factory1);
			}
			
			int total=service.updateFactory(projectId,factory);
			if(total>0){
				out.print("YES");
			}else{
				out.print("NO");	
			}
		}
		
		/**
		 * 
		 * @Title:ItCaseIdServlet
		 * @Description:修改未付款备注
		   @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException void
		 * @throws
		 */
		
		public void outstandingNotes(HttpServletRequest request, HttpServletResponse response)
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
			PrintWriter out = response.getWriter();
			String iid=request.getParameter("iid");
			String outstandingNotes=request.getParameter("outstandingNotes");
			String reason=request.getParameter("reason");
			outstandingNotes = new String(outstandingNotes.getBytes("iso-8859-1"),"utf-8");
			int id=0;
			if(iid!=null&&!"".equals(iid)){
				id=Integer.parseInt(iid);
			}
			int reason1=0;
			if(reason!=null&&!"".equals(reason)){
				reason1=Integer.parseInt(reason);
			}
			int total=0;
			if(outstandingNotes!=null&&!"".equals(outstandingNotes)){
			total=iservice.updateOutstandingNotes(id,outstandingNotes,reason1,EmpEName);
			}else{
				total=iservice.updateOutstandingNotes(id,null,reason1,EmpEName);	
			}
			if(total>0){
				out.print("YES");
			}else{
				out.print("NO");	
			}
		}
		
		
		
		/**
		 * 
		 * @Title:ItCaseIdServlet
		 * @Description:工厂 未付款统计
		   @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException void
		 * @throws
		 */
		
		public void factoryUnpaidStatistics(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhao";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
					Factory it=new Factory();
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
						it.setStartTime(time1);
						request.setAttribute("starttime",time1 );
					}
					String time2=request.getParameter("time2");
					if(time2 != null && !"".equals(time2)){
						it.setOutTime(time2);
						request.setAttribute("OutTime",time2 );
					}
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
                      if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
						
						it.setCaseNo(condition);
					}
				    Double amountApproved=service.getAmountApproved(it);//正在审批总金额
				    Double planSave=service.getPlanSave(it);//计划保存金额
				    Double completedMoney=service.getCompletedMoney(it);//已完成款项
					List<Factory> list=service.factoryUnpaidStatistics(it);//查看未付工厂
					request.setAttribute("cusList",list );
					request.setAttribute("amountApproved",amountApproved );
					request.setAttribute("planSave",planSave );
					request.setAttribute("completedMoney",completedMoney );
					request.getRequestDispatcher("jsp/factory_unpaid_statistics.jsp").forward(request, response);
				}else{
					out.write("<script>");
					out.write("alert('对不起你没有权限查看图纸统计页面');");
					out.write("window.location.href='jsp/login.jsp'");
					out.write("</script>");
				}
			}
			
		}
		
		/**
		 * 
		 * @Title:ItCaseIdServlet
		 * @Description:客户到款情况的数据统计
		   @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException void
		 * @throws
		 */
		
		public void CustomerPaymentStatistics(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhaojerrylong";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
					invoiceinfo it=new invoiceinfo();
					
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
                      if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
						
						it.setCaseno(condition);
					}
                      String time1=request.getParameter("time1");
  					if(time1 != null && !"".equals(time1)){
  					it.setStartTime(time1);
  					request.setAttribute("starttime",time1 );
  					}else{
  						it.setStartTime("2018-01-01");	
  					}
  					String time2=request.getParameter("time2");
					if(time2 != null && !"".equals(time2)){
					it.setOutTime(time2);
					request.setAttribute("endtime",time2 );
					}else{
						it.setOutTime("2019-12-31");		
					}
					List<invoiceinfo> list=service.CustomerPaymentStatistics(it);
					request.setAttribute("cusList",list );
				
					request.getRequestDispatcher("jsp/customer_payment_statistics.jsp").forward(request, response);
				}else{
					out.write("<script>");
					out.write("alert('对不起你没有权限查看图纸统计页面');");
					out.write("window.location.href='jsp/login.jsp'");
					out.write("</script>");
				}
			}
			
		}
		/**
		 * 
		 * @Title:ItCaseIdServlet
		 * @Description:客户未付款页面
		   @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException void
		 * @throws
		 */
		
		public void nonPaymentCustomers(HttpServletRequest request, HttpServletResponse response)
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
			//List<ItemCase> list1=service.getAllItem();
			if(user1!=null&& total1>0){
				String s1 = "edwardfanemmaxieninazhaoLisodZhengjerrylongroseli";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false){
					invoiceinfo it=new invoiceinfo();
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
						
						it.setCaseno(condition.trim());
					}
					String condition1 = request.getParameter("condition1");
					int reason=0;
					if(condition1!=null&&!"".equals(condition1)){
						reason=Integer.parseInt(condition1);
					}
					String condition2 = request.getParameter("condition2");
					int discrepancy=0;
					if(condition2!=null&&!"".equals(condition2)){
						discrepancy=Integer.parseInt(condition2);
					}
					if(discrepancy!=-1){
						it.setDiscrepancy(discrepancy);
						request.setAttribute("fyfx",discrepancy );
					}
					if(reason!=-1){
					it.setReason(reason);
					request.setAttribute("fyfz",reason );
					}
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
					it.setIidate(time1);
					request.setAttribute("starttime",time1 );
					}
					List<invoiceinfo> list=service.nonPaymentCustomers(it);//查看未付工厂
					request.setAttribute("cusList",list );
					
					request.getRequestDispatcher("jsp/non_payment_customers.jsp").forward(request, response);
				}else{
                     invoiceinfo it=new invoiceinfo();
					it.setMerchandManager1(EmpEName);
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
						it.setCaseno(condition.trim());
					}
					String condition1 = request.getParameter("condition1");
					int reason=0;
					if(condition1!=null&&!"".equals(condition1)){
						reason=Integer.parseInt(condition1);
					}
					String condition2 = request.getParameter("condition2");
					int discrepancy=0;
					if(condition2!=null&&!"".equals(condition2)){
						discrepancy=Integer.parseInt(condition2);
					}
					if(discrepancy!=-1){
						it.setDiscrepancy(discrepancy);
						request.setAttribute("fyfx",discrepancy );
					}
					if(reason!=-1){
					it.setReason(reason);
					request.setAttribute("fyfz",reason );
					}
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
					it.setIidate(time1);
					request.setAttribute("starttime",time1 );
					}
					List<invoiceinfo> list=service.nonPaymentCustomers1(it);//查看未付工厂
					request.setAttribute("cusList",list );
					request.getRequestDispatcher("jsp/non_payment_customers.jsp").forward(request, response);
				}
			}else{
				out.write("<script>");
				out.write("alert('对不起你没有登录无法查看');");
				out.write("window.location.href='jsp/login.jsp'");
				out.write("</script>");
			}
			
		}
		/**
		 * 
		 * @Title:ItCaseIdServlet
		 * @Description:清账页面备注
		   @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException void
		 * @throws
		 */
		
		public void updateExplain(HttpServletRequest request, HttpServletResponse response)
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
			PrintWriter out = response.getWriter();
			String iid=request.getParameter("iid");
			String projectId=request.getParameter("projectId");
			String contractNumber=request.getParameter("contractNumber");
			String projectFinished=request.getParameter("projectFinished");
			String money=request.getParameter("money");
			
			String explain=request.getParameter("explain");
			
			if(explain!=null&&!"".equals(explain)){
			explain = new String(explain.getBytes("iso-8859-1"),"utf-8");
			}
			if(contractNumber!=null&&!"".equals(contractNumber)){
				contractNumber = new String(contractNumber.getBytes("iso-8859-1"),"utf-8");
			}
			if(projectFinished!=null&&!"".equals(projectFinished)){
			projectFinished = new String(projectFinished.getBytes("iso-8859-1"),"utf-8");
			}
			int id=0;
			if(iid!=null&&!"".equals(iid)){
				id=Integer.parseInt(iid);
			}
			String reason=explain;
			double money1=0.0;
			if(money!=null&&!"".equals(money)){
				money1=Double.parseDouble(money);
			}
			int reason1=0;
			if("未完结还在讨要".equals(projectFinished)){
				reason1=7;
			}else if("正常完结".equals(projectFinished)){
				reason1=10;
			}else if("预计滞后3个月内收回".equals(projectFinished)){
				reason1=2;
			}else if("预计滞后6个月内收回".equals(projectFinished)){
				reason1=3;
			}else if("列入坏账".equals(projectFinished)){
				reason1=8;
			}else if("收不回款".equals(projectFinished)){
				reason1=5;
			}else if("预计无法收回全部".equals(projectFinished)){
				reason1=4;
			}else if("invoice上传错误改零".equals(projectFinished)){
				reason1=6;
			}else if("去掉质量扣款后完结".equals(projectFinished)){
				reason1=9;
			}
			int total=0;
			int totala=0;
			if("去掉质量扣款后完结".equalsIgnoreCase(projectFinished)){
			Factory factory=fservice.getFactory(id);
			 ItemCase it=service.getCaseno(projectId,contractNumber);
				FactoryFund factory1=qservice.checkMoney(projectId,contractNumber, money1, id);
				if(it!=null){
				if(factory1!=null){
				 double moneya=factory1.getFriMoney();
				if(moneya>money1||moneya==money1){
			if("去掉质量扣款后完结".equalsIgnoreCase(projectFinished)){
				totala=iservice.updateAll(id,projectFinished,money1,reason,explain);
				totala=qservice.add(factory.getCaseNo(),money1,id,explain);
			}else{
				totala=iservice.updateAll(id,projectFinished,money1,reason,explain);	
			}
			if(projectFinished!=null&&!"".equals(projectFinished)){
				total=iservice.updateOutstandingNotes(id,reason,reason1,EmpEName);
				}else{
					total=iservice.updateOutstandingNotes(id,null,reason1,EmpEName);	
				}
			if("去掉质量扣款后完结".equalsIgnoreCase(projectFinished)){
			 totala=qservice.add1(projectId,contractNumber, money1, id);
			int total1=qservice.addFactoryInvoice(projectId,moneya-money1,contractNumber,EmpEName,moneya,explain);
			int total2=fservice.updateMoney(factory1.getCaseNo(),moneya-money1);
			int total3=cuservice.updateMoney(factory1.getCaseNo(),moneya-money1);
			}
				}
				}else{
					if("去掉质量扣款后完结".equalsIgnoreCase(projectFinished)){
					int id1=qservice.checkMoney(projectId);
					if(id1>0){}else{
				totala=qservice.addFactoryInvoice1(projectId,money1,contractNumber,EmpEName);
					}
					}
				}
				if(totala>0){
					out.print("YES");
				}else{
					out.print("NO");	
				}
				}else{
					out.print("wrong");
				}
			}else if("正常完结".equals(projectFinished)){
				totala=iservice.updateAll(id,projectFinished,money1,reason,explain);
				totala=iservice.updateOutstandingNotes(id,explain,reason1,EmpEName);	
				if(totala>0){
					out.print("YES");
				}else{
					out.print("NO");	
				}
			}else{
				if(reason1!=7){
				totala=iservice.updateAll(id,projectFinished,money1,reason,explain);	
				}
				totala=iservice.updateOutstandingNotes(id,explain,reason1,EmpEName);
				if(totala>0){
					out.print("YES");
				}else{
					out.print("NO");	
				}
			}
		}
		/**
		 * 
		 * @Title:ItCaseIdServlet
		 * @Description:修改质量扣款添加到合同
		   @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException void
		 * @throws
		 */
		public void addContractWithdrawing(HttpServletRequest request, HttpServletResponse response)
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
			PrintWriter out = response.getWriter();
			String iid=request.getParameter("iid");
			String projectId=request.getParameter("projectId");
			String actualDeductions=request.getParameter("actualDeductions");
			String contractNumber=request.getParameter("contractNumber");
			contractNumber = new String(contractNumber.getBytes("iso-8859-1"),"utf-8");
			int id=0;
			if(iid!=null&&!"".equals(iid)){
				id=Integer.parseInt(iid);
			}
			double actualDeductions1=0.0;
			if(actualDeductions!=null&&!"".equals(actualDeductions)){
				actualDeductions1=Double.parseDouble(actualDeductions);
			}
		    ItemCase it=service.getCaseno(projectId,contractNumber);
			FactoryFund factory=qservice.checkMoney(projectId,contractNumber, actualDeductions1, id);
			if(it!=null){
			int total=0;
			if(factory!=null){
			 double money=factory.getFriMoney();
			if(money>actualDeductions1||money==actualDeductions1){
			total=qservice.add1(projectId,contractNumber, actualDeductions1, id);
			int total1=qservice.addFactoryInvoice(projectId,money-actualDeductions1,contractNumber,EmpEName,money,"");
			
			
			int total2=fservice.updateMoney(factory.getCaseNo(),money-actualDeductions1);
			int total3=cuservice.updateMoney(factory.getCaseNo(),money-actualDeductions1);
			}
			}else{
				int id1=qservice.checkMoney(projectId);
				if(id1>0){}else{
			int total1=qservice.addFactoryInvoice1(projectId,actualDeductions1,contractNumber,EmpEName);
				}
			}
			if(total>0){
				out.print("YES");
			}else{
				out.print("NO");	
			}
			}else{
				out.print("wrong");
			}
		}
	/**
	 * @Title:ItCaseIdServlet
	 * @Description:启动项目统计数据
	   @author wangyang
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException void
	 * @throws
	 */
		public void startProjectStatistics(HttpServletRequest request, HttpServletResponse response)
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
				String s1 = "edwardfanemmaxieninazhaoandsjerrylong";
				String s2 = "wangweipingzhangqunowencuiyaoyuDeanZhangfanli";
				String s3 = "ninazhao";
				Boolean index1=false;
				index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
				Boolean index2=false;
				index2 = s2.toLowerCase().contains(user1.getUserName().toLowerCase());
				if(index1!=false||index2!=false){
					ItemCase it=new ItemCase();
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
						it.setCaseNo(condition.trim());
					}
					String condition1 = request.getParameter("condition1");//获取下拉框输入的条件选项值
					if(condition1 != null && !"".equals(condition1)) {//说明有输入条件内容做查询
						int id=Integer.parseInt(condition1);
					    it.setDrawingPicture(id);
						request.setAttribute("fyfz",id );
					}
					String time1=request.getParameter("time1");
					if(time1 != null && !"".equals(time1)){
					it.setStartTime(time1);
					request.setAttribute("starttime",time1);
					}
					String time2=request.getParameter("time2");
					if(time2 != null && !"".equals(time2)){
						it.setEndTime(time2);
						request.setAttribute("endtime",time2);
					}
					if(condition != null && !"".equals(condition)||time2 != null && !"".equals(time2)||time1 != null && !"".equals(time1)){
					}else{
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					String time=sdf.format(date);
					it.setStartTime(time+"-01");
					request.setAttribute("starttime",time+"-01" );	
				    }
					if(s3.toLowerCase().contains(user1.getUserName().toLowerCase())!=false){
						request.setAttribute("loginName","ninazhao" );
					}
					List<ItemCase> list=service.startProjectStatistics(it);//查看逻辑
						for(int i=0;i<list.size();i++){
						ItemCase itemCase=list.get(i);
						int num=itemCase.getProjectLevel();
						String projectNo="&&projectNo="+itemCase.getCaseNo();
						String CaseNo=itemCase.getCaseNo();
						String CaseNo1=CaseNo.substring(0,8);
						ItemCase item=service.getall(itemCase.getCaseNo());
						if(item.getCaseStatus()!=5&&item.getCaseStatus()!=10 &&item.getAllmoney()>10000){
                          int number=service.getSalesContract(itemCase.getCaseNo());
                          if(number>0){
							  itemCase.setSalesContract(1);
						  }else{
							  itemCase.setSalesContract(2);

						  }
						}else{
							itemCase.setSalesContract(0);
						}
                       if(num!=3){
							try{
						String returnNum=Client.sendPost1("http://117.144.21.74:10010/port/judgeDelay",projectNo);
						returnNum=returnNum.replaceAll("\"", "");
						if("null".equalsIgnoreCase(returnNum)){
							itemCase.setDelay(0);
						}else if("true".equalsIgnoreCase(returnNum)){
							itemCase.setDelay(0);	
						}else if("false".equalsIgnoreCase(returnNum)){
							itemCase.setDelay(1);	
						}	else{
							itemCase.setDelay(0);	
						}	
								}catch(Exception e){
									
								}
						}else{
							itemCase.setDelay(0);	
						}
                      List<AllDrawings>allList=aservice.getAllDrawings(CaseNo1);//根据项目号获取图纸
						itemCase.setPicture(allList);
					}
					
					new ExportPenaltyProcessDataThread(list).start();
					request.setAttribute("cusList",list );
					request.getRequestDispatcher("jsp/start_project_statistics.jsp").forward(request, response);
					
				}else{
					ItemCase it=new ItemCase();
					it.setCustomerManager(EmpEName);
					String condition = request.getParameter("condition");//获取下拉框输入的条件选项值
					if(condition != null && !"".equals(condition)) {//说明有输入条件内容做查询
						it.setCaseNo(condition.trim());
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
					if(condition != null && !"".equals(condition)||time2 != null && !"".equals(time2)||time1 != null && !"".equals(time1)){
					}else{
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					String time=sdf.format(date);
					it.setStartTime(time+"-01");
					request.setAttribute("starttime",time+"-01" );	
				}
					List<ItemCase> list=service.startProjectStatistics1(it);//查看逻辑
					
					for(int i=0;i<list.size();i++){
						ItemCase itemCase=list.get(i);
						int num=itemCase.getProjectLevel();
						String projectNo="&& "+itemCase.getCaseNo();
						String CaseNo=itemCase.getCaseNo();
						String CaseNo1=CaseNo.substring(0,8);
						ItemCase item=service.getall(itemCase.getCaseNo());
						if(item.getCaseStatus()!=5&&item.getCaseStatus()!=10 &&item.getAllmoney()>10000){
							int number=service.getSalesContract(itemCase.getCaseNo());
							if(number>0){
								itemCase.setSalesContract(1);
							}else{
								itemCase.setSalesContract(2);

							}
						}else{
							itemCase.setSalesContract(0);
						}

						if(num!=3){
							try{
								String returnNum=Client.sendPost1("http://117.144.21.74:10010/port/judgeDelay",projectNo);
								returnNum=returnNum.replaceAll("\"", "");
								if("null".equalsIgnoreCase(returnNum)){
									itemCase.setDelay(0);
								}else if("true".equalsIgnoreCase(returnNum)){
									itemCase.setDelay(0);	
								}else if("false".equalsIgnoreCase(returnNum)){
									itemCase.setDelay(1);	
								}
								else{
									itemCase.setDelay(0);	
								}
							}catch(Exception e){
								
							}
						}else{
							itemCase.setDelay(0);	
						}
						List<AllDrawings>allList=aservice.getAllDrawings(CaseNo1);//根据项目号获取图纸
						itemCase.setPicture(allList);
					}
					request.setAttribute("cusList",list );
					request.getRequestDispatcher("jsp/start_project_statistics.jsp").forward(request, response);
				}
			}else{
				out.write("<script>");
				out.write("alert('对不起你没有登录无法查看');");
				out.write("window.location.href='jsp/login.jsp'");
				out.write("</script>");
			}
			
		}
		/**
		 * 方法描述:修改项目现有销售
		 * author:wy
		 * date:2016年4月15日
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void modifyingMembers(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			String projectId = request.getParameter("projectId");
			String CustomerManager = request.getParameter("CustomerManager");
			String MerchandManager1 = request.getParameter("MerchandManager1");
			String Merchandising = request.getParameter("Merchandising");
			String OriginalPurchase = request.getParameter("OriginalPurchase");
			String MaturePurchase = request.getParameter("MaturePurchase");
			String zhijian1 = request.getParameter("zhijian1");
			String zhijian2 = request.getParameter("zhijian2");
			String MerchandManager2 = request.getParameter("MerchandManager2");
			if(CustomerManager!=null&&!"".equals(CustomerManager)){
				
			}
		}
		
		/**
		 * @Title:ItCaseIdServlet
		 * @Description:查询进账列表
		   @author wangyang
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws ParseException void
		 * @throws
		 */
			public void queryReceiptList(HttpServletRequest request, HttpServletResponse response)
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
					Invoiceinfo1 it=new Invoiceinfo1();
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
						String bank=request.getParameter("ibank");
						if(bank!=null&&!"".equalsIgnoreCase(bank)){
							it.setIbank(Integer.parseInt(bank));
							request.setAttribute("fyfz",bank );
						}
						List<Invoiceinfo1> list=inservice.queryReceiptList(it);//查询进账列表
						int bankOfChinaId=0;
						double bankOfChinaMoney=0.00;
						int standardCharteredId=0;
						double standardCharteredMoney=0.00;
						int creditCardId=0;
						double creditCardMoney=0.00;
						int otherId=0;
						double otherMoney=0.00;
						for(int i=0;i<list.size();i++){
							Invoiceinfo1 info1=list.get(i);
							if(info1.getIbank()==5){
								bankOfChinaId++;
								bankOfChinaMoney+=info1.getIfmoney();
							}else if(info1.getIbank()==12){
								standardCharteredId++;
								standardCharteredMoney+=info1.getIfmoney();
							}else if(info1.getIbank()==8){
								creditCardId++;
								creditCardMoney+=info1.getIfmoney();
							}else{
								otherId++;
								otherMoney+=info1.getIfmoney();
							}
						}
						
						request.setAttribute("bankOfChinaId", bankOfChinaId);
						request.setAttribute("bankOfChinaMoney", bankOfChinaMoney);
						request.setAttribute("standardCharteredId", standardCharteredId);
						request.setAttribute("standardCharteredMoney", standardCharteredMoney);
						request.setAttribute("creditCardId", creditCardId);
						request.setAttribute("creditCardMoney", creditCardMoney);
						request.setAttribute("otherId", otherId);
						request.setAttribute("otherMoney", otherMoney);
						
						request.setAttribute("cusList",list );
						request.getRequestDispatcher("jsp/query_receipt_list.jsp").forward(request, response);
					}
				
				
			}
			/**
			 * 
			 * @Title:ItCaseIdServlet
			 * @Description:清理该项目
			   @author wangyang
			 * @param request
			 * @param response
			 * @throws ServletException
			 * @throws IOException
			 * @throws ParseException void
			 * @throws
			 */
			public void deleteItem(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException, ParseException {
				     PrintWriter out = response.getWriter();
				
					String caseno=request.getParameter("caseno");
					
					int total=service.updateCaseStatus(caseno);
					if(total>0){
						out.print("YES");
					}else{
						out.print("NO");
					}
				}
			/**
			 * 
			 * @Title:ItCaseIdServlet
			 * @Description:添加备注
			   @author wangyang
			 * @param request
			 * @param response
			 * @throws ServletException
			 * @throws IOException
			 * @throws ParseException void
			 * @throws
			 */
			public void updateRemarks(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException, ParseException {
				PrintWriter out = response.getWriter();
				
				String caseno=request.getParameter("caseno");
				String remarks=request.getParameter("remarks");
				if(remarks!=null&&!"".equalsIgnoreCase(remarks)){
				remarks = new String(remarks.getBytes("iso-8859-1"),"utf-8");
				}
				int total=service.updateRemarks(caseno,remarks);
				if(total>0){
					out.print("YES");
				}else{
					out.print("NO");
				}
			}

	/**
	 * 查询跟单进账
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void inquiryDocumentaryAccountin(HttpServletRequest request, HttpServletResponse response)
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cl = Calendar.getInstance();
		EmailUser user =new EmailUser();
		String time3=request.getParameter("time3");
		if(time3 != null && !"".equals(time3)){
			user.setStartTime1(time3);
			request.setAttribute("starttime1",time3 );
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR)-1;
			user.setStartTime1(year+"-01-01");
			request.setAttribute("starttime1",year+"-01-01" );

		}

		String time1=request.getParameter("time1");
		if(time1 != null && !"".equals(time1)){
			user.setStartTime(time1);
			request.setAttribute("starttime",time1 );
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR)-1;
			user.setStartTime(year+"-01-01");
			request.setAttribute("starttime",year+"-01-01" );
		}
		String time2=request.getParameter("time2");
		if(time2 != null && !"".equals(time2)){
			user.setEndTime(time2);
			request.setAttribute("endtime",time2 );
			user.setStartTime2(time2);
			request.setAttribute("starttime2",time2);
		}else{
			cl.setTime(new Date());
			cl.add(Calendar.MONTH, -6);
			Date m3 = cl.getTime();
			String mon3 = format.format(m3);
			user.setEndTime(mon3+"-01");
			request.setAttribute("endtime",mon3+"-01" );
			user.setStartTime2(mon3+"-01");
			request.setAttribute("starttime2",mon3+"-01");
		}


		cl.setTime(new Date());
		cl.add(Calendar.MONTH, -5);
		Date m4 = cl.getTime();
		String mon4 = format.format(m4);
		user.setStartTime3(mon4+"-01");
		request.setAttribute("starttime3",mon4+"-01");

		String time4=request.getParameter("time4");
		if(time4!= null && !"".equals(time4)){
			user.setStartTimea(time4);
			request.setAttribute("starttimea",time4 );
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			user.setStartTimea(year+"-04-01");
			request.setAttribute("starttimea",year+"-04-01" );

		}
		String time5=request.getParameter("time5");
		if(time5!= null && !"".equals(time5)){
			user.setEndTimea(time5);
			request.setAttribute("endtimea",time5 );
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			user.setEndTimea(year+"-07-01");
			request.setAttribute("endtimea",year+"-07-01" );

		}
		if(user1!=null&& total1>0){
			String s1 = "edwardfanemmaxieninazhaoandsjerrylongninazhao";
			Boolean index1=false;
			index1 = s1.toLowerCase().contains(user1.getUserName().toLowerCase());
			if(index1!=false){

				List<EmailUser>userList1=new ArrayList<EmailUser>();
				List<EmailUser> userlist2=eservice.getAll();
                 for(int i=0;i<userlist2.size();i++){
					 user.setUserName(userlist2.get(i).getUserName());
					 EmailUser user2=eservice.getAccountingCustomers(user);
                     userList1.add(user2);
				 }



				request.setAttribute("userList",userList1);
				request.getRequestDispatcher("jsp/inquiry_documentary_accountin.jsp").forward(request, response);

			}else{
                 user.setUserName(EmpEName);
				List<EmailUser>userList1=new ArrayList<EmailUser>();
				EmailUser user2=eservice.getAccountingCustomers(user);
				userList1.add(user2);
				request.setAttribute("userList",userList1);

				request.getRequestDispatcher("jsp/inquiry_documentary_accountin.jsp").forward(request, response);
			}
		}else{
			out.write("<script>");
			out.write("alert('对不起你没有登录无法查看');");
			out.write("window.location.href='jsp/login.jsp'");
			out.write("</script>");
		}

	}
}

