package cerong.erp.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * 将配置信息读入系统变量
 * @author Administrator
 *
 */
public class SystemServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(SystemServlet.class);
	
	private static final long serialVersionUID = -833322220864312415L;

	@Override
	public void init() throws ServletException {
        String rootPath = this.getServletContext().getRealPath("/");  
        
        logger.info(rootPath);
        
        String log4jPath = this.getServletConfig().getInitParameter("email.log4j.path");  
        //若没有指定oss.log4j.path初始参数，则使用WEB的工程目录  
        log4jPath = (log4jPath==null||"".equals(log4jPath))?rootPath:log4jPath;  
        System.setProperty("email.log4j.path", log4jPath); 
        System.out.print(log4jPath);
        super.init();  
	}

}
