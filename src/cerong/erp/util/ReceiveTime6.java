package cerong.erp.util;


import java.util.TimerTask;






import cerong.erp.servlet.ExternalinterfaceServlet;







public class ReceiveTime6 extends TimerTask {

	
	public ReceiveTime6() {
		super();
	}

	@Override
	public void run() {
		// 每二十分钟执行一次
		
		ExternalinterfaceServlet.projectDrawing1();
		
		}
		
	}

