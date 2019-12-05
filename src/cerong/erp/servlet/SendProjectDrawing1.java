package cerong.erp.servlet;

import java.util.Map;

public class SendProjectDrawing1 extends  Thread{
	Map<Object, String> map;
	public SendProjectDrawing1(Map<Object, String> map) {
		this.map=map;
	}

	@Override
	public void run() {
		
		ItCaseIdServlet.sendPost("http://192.168.1.6:8080/inspectionReport/synchInspectionReport",map);
		
		
		super.run();
	
	
	
	}
}
