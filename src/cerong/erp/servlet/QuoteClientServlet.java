package cerong.erp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
















import cerong.erp.entity.EmailUser;
import cerong.erp.entity.QuoteClient;
import cerong.erp.service.IQuoteClientImpl;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;
import cerong.erp.service.QuoteClientService;
import cerong.erp.util.MyFileUpLoad1;

public class QuoteClientServlet extends HttpServlet{
	
	IQuoteClientImpl service = new QuoteClientService();
	private static final long serialVersionUID = 1L;
	/**
	 * 方法描述:对外报价上传
	 * author:wy
	 * date:2016年8月17日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void quoteClient(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
	      response.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
	      PrintWriter out = response.getWriter();
	      //将附件文件保存到服务器上
		  MyFileUpLoad1.upload(request, response);
		  String client = (String) request.getAttribute("client");
		  String remark = (String) request.getAttribute("remark");
		  String margin = (String) request.getAttribute("margin");
		  String money = (String) request.getAttribute("money");
		  String money1 = (String) request.getAttribute("money1");
		  String grade = (String) request.getAttribute("grade");
		  int cid1 = 0;
		  if(money != null && !"".equals(money)) {
			  cid1 = Integer.parseInt(money);
		  }
		  int cid2 = 0;
		  if(margin != null && !"".equals(margin)) {
			  cid2 = Integer.parseInt(margin);
		  }
		    Date date = new Date(); 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String time1=format.format(date);
		  
		  QuoteClient qc=new QuoteClient();
		  qc.setCaseNo(client);
		  qc.setCreater("jerrylong");
		  qc.setLirun(cid2);
		  qc.setTitle(client+"对外报价");
		  qc.setGongyiliucheng(remark);
		  qc.setDingdan(cid1);
		  qc.setCreatetime(time1);
		  
		  Map<String, String> map = (Map<String, String>) request.getAttribute("map");
		  if(map != null && map.size() > 0) {
			  Set<String> keySet = map.keySet();
		    	for (String key : keySet) {
		    		String value = map.get(key);
		    		qc.setUrl(value);
		    	} 
		  }
		 int total=service.add(qc);
		 PrintWriter out1 = null;
	        BufferedReader in1 = null;
	        String result2 = "";
	        try {
	        	
	            //URL realUrl1 = new URL("http://117.144.21.74:43900/ERP-NBEmail/helpServlet?action=Attachment&className=ItCaseIdServlet");
	            URL realUrl1 = new URL("http://117.144.21.74:43900/NBEmail/helpServlet?action=updatetime&className=EmailClientServlet");
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
	            out1.print("time="+time1+"&money="+money+"&money1="+money1+"&grade="+grade+"&client="+client);
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
	if("替换成功".equals(result2)){    
		 
		 request.setAttribute("name", "对外报价成功");
		 request.getRequestDispatcher("jsp/righta.jsp").forward(request, response);
	}else{
		 request.setAttribute("name", "对外报价失败");
		 request.getRequestDispatcher("jsp/righta.jsp").forward(request, response);
	}
	}
}
