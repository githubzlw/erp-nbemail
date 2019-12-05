package cerong.erp.util;


import java.util.TimerTask;








import cerong.erp.servlet.ExternalinterfaceServlet;
import cerong.erp.servlet.PushEmailController;







public class ReceiveTime1 extends TimerTask {

	
	public ReceiveTime1() {
		super();
	}

	@Override
	public void run() {
		// 每二十分钟执行一次
		try {
			PushEmailController.addOnMeeting();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ExternalinterfaceServlet.projectDrawing1();
		
		}
		
	}

