package cerong.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cerong.erp.entity.Admin;
import cerong.erp.entity.EmailUser;
import cerong.erp.service.AdminService;
import cerong.erp.service.EmployeeService;
import cerong.erp.service.IAdminServiceImpl;
import cerong.erp.service.IEmployeeServiceImpl;


public class AdminServlet extends HttpServlet {
	IAdminServiceImpl service = new AdminService();
	private static final long serialVersionUID = 1L;
	
	/**
	 * 方法描述:添加用户
	 * author:wy
	 * date:2016年4月18日
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	
	public void registerAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("EmpEName");
		int id=service.getid(userName);
		if(id>0){
			/*out.write("<script>");
			out.write("alert('用户名已存在')");
			out.write("</script>");*/
			request.setAttribute("name", "用户名已存在");
			request.getRequestDispatcher("jsp/admin_right.jsp").forward(request, response);
		}else{
			String pwd = request.getParameter("EmpPWD");
			int auth=0;
			Admin admin=new Admin();
			admin.setAuth(auth);
			admin.setName(userName);
			admin.setPass(pwd);
			int b=service.add(admin); 
			if(b > 0 ) {//注册成功
				/*out.write("<script>");
				out.write("alert('录入成功')");
				out.write("</script>");*/
				request.setAttribute("name", "录入出运联系单用户成功");
				request.getRequestDispatcher("jsp/admin_right.jsp").forward(request, response);
			}else {//注册失败
				/*out.write("<script>");
				out.write("alert('录入失败')");
				out.write("</script>");*/
				request.setAttribute("name", "录入出运联系单用户失败");
				request.getRequestDispatcher("jsp/admin_right.jsp").forward(request, response);
			}	
		}
		
		
	}   
   

}
