package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerong.erp.entity.EmailUser;
import cerong.erp.entity.NudrawProject;
import cerong.erp.service.EmailUserServiceImpl;
import cerong.erp.service.IEmailUserService;
import cerong.erp.service.INudrawProjectService;
import cerong.erp.service.NudrawProjectServiceImpl;


public class NudrawProjectServlet extends HttpServlet {
	INudrawProjectService uservice = new NudrawProjectServiceImpl();
	/**
	 * 方法描述:获取15天未上传图纸30天未上传质检报告的项目
	 * author:wy
	 * date:2016年11月25日
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void notuploadpicture(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		NudrawProject project=new NudrawProject();
		Date date = new Date(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String time=format.format(date);
	    Long millionSeconds;
	    Long millionSeconds1=0l;
	    Long millionSeconds2=0l;
		try {
			millionSeconds = format.parse(time).getTime();
			millionSeconds1=millionSeconds-1796000000l;
			millionSeconds2=millionSeconds-2592000000l;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  List<NudrawProject> list=uservice.getAll(millionSeconds1,millionSeconds2);
		request.setAttribute("list",list);
		request.getRequestDispatcher("jsp/not_upload_picture.jsp").forward(request, response);
	
	}
}
