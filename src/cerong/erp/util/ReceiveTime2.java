package cerong.erp.util;


import java.util.TimerTask;









import cerong.erp.servlet.ExternalinterfaceServlet;
import cerong.erp.servlet.PushEmailController;
import cerong.erp.servlet.TechnicalDocumentation;







public class ReceiveTime2 extends TimerTask {

	
	public ReceiveTime2() {
		super();
	}

	@Override
	public void run() {
		// 每二十分钟执行一次
		try {
			TechnicalDocumentation.addData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

