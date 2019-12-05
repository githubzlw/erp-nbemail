package cerong.erp.TimerListener;

import cerong.erp.controller.QueryProfitController;
import cerong.erp.entity.EmailUser;
import cerong.erp.entity.MonthlyProfitStatement;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IEmployeeServiceImpl;
import cerong.erp.service.IInvinceServiceImpl;
import cerong.erp.service.InvinceService;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.application.Application;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class MoldListener implements ServletContextListener {

	public static String path = "";
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MOLD结束");
		sce.getServletContext().removeAttribute("path");
//		DBHelper.destory()
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MOLD开始");

		ServletContext context = sce.getServletContext();
		path = context.getRealPath("/");
		System.out.println(path);
		context.setAttribute("path", path);
//		DBHelper.init();
	}

}
