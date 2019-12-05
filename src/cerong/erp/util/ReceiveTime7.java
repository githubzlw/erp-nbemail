package cerong.erp.util;

import java.text.SimpleDateFormat;
import java.util.*;


import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MonthlyProfitStatement;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IInvinceServiceImpl;
import cerong.erp.service.InvinceService;
import cerong.erp.servlet.ExternalinterfaceServlet;







public class ReceiveTime7 extends TimerTask {
	static IInvinceServiceImpl iservice = new InvinceService();
	static IEmployeeServiceImpl eservice = new EmployeeService();
	
	public ReceiveTime7() {
		super();
	}

	@Override
	public void run() {
		// 每二十分钟执行一次

		ExternalinterfaceServlet.inspectionReport1();
		Calendar now = Calendar.getInstance();

		int HOUR=now.get(Calendar.HOUR_OF_DAY);
		if(HOUR==23) {
			queryMailInformation();
		}
		}



	public static void queryMailInformation() {
		show();
	}
	public static void show(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String time=sdf.format(new Date());
		for(int i=0;i<36;i++){
			try {
				Date date1=sdf.parse(time);
				int num=i+1;
				Calendar theCa = Calendar.getInstance();
				theCa.setTime(date1);
				theCa.add(theCa.MONTH, -num);
				String time1 = sdf.format(theCa.getTime());
				updateQueryProfit(time1);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private static void updateQueryProfit(String time1) {
		List<EmailUser> alllist=eservice.getAllSales();
		List<MonthlyProfitStatement> statelist=new ArrayList<MonthlyProfitStatement>();
		for(int i=0;i<alllist.size();i++){
			EmailUser user=alllist.get(i);
			MonthlyProfitStatement statement=iservice.getOne(user.getUserName(),time1+"-01");
			statement.setTime(time1);
			statelist.add(statement);
		}
		MonthlyProfitStatement state=iservice.getLastOne(time1);
		if(state!=null){
			iservice.updateAllList(statelist);
		}else {
			iservice.insertAll(statelist);
		}
	}
}