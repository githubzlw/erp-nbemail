package cerong.erp.servlet;

import java.util.Map;

public class SendProjectDrawing extends  Thread{
	Map<Object, String> map;
	public SendProjectDrawing(Map<Object, String> map) {
		this.map=map;
	}

	@Override
	public void run() {
		
		ItCaseIdServlet.sendPost("http://192.168.1.6:8080/projectDrawing/synchProjectDrawing",map);
		
		
		super.run();
	
	
	
	}
}
