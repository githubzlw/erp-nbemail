package cerong.erp.TimerListener;


import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cerong.erp.util.ReceiveTime1;




/**
 * 系统启动时的监听类 
 */
public class InitListener1 implements ServletContextListener {
	private static final Logger LOG = LoggerFactory.getLogger(InitListener1.class);
	String path = "";
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// context销毁时，销毁初始化数据
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			//获取
			getItemCaseInfo();

		
		} catch (Exception e) {
			LOG.warn("失败:" + e.getMessage());
		}
	}


	
	
	
	private void getItemCaseInfo() {
		Timer timmerTask = new Timer();
		
				timmerTask.schedule(new ReceiveTime1(), 3000, 365*24*60 * 1000 * 60 );
		
	}
}