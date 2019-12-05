package cerong.erp.util;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/** 
 *  
 * @author Administrator 
 * 文件上传 
 * 具体步骤： 
 * 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 
 * 2） 利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 
 * 3）对 DiskFileItemFactory 对象设置一些 属性 
 * 4）高水平的API文件上传处理  ServletFileUpload upload = new ServletFileUpload(factory); 
 * 目的是调用 parseRequest（request）方法  获得 FileItem 集合list ， 
 *      
 * 5）在 FileItem 对象中 获取信息，   遍历， 判断 表单提交过来的信息 是否是 普通文本信息  另做处理 
 * 6） 
 *    第一种. 用第三方 提供的  item.write( new File(path,filename) );  直接写到磁盘上 
 *    第二种. 手动处理   
 * 
 */ 
public class MyFileUpLoad1 {
	private static final Logger LOG = LoggerFactory.getLogger(MyFileUpLoad1.class);
	public static void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8"); // 设置编码
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取文件需要上传到的路径
		//String path = request.getRealPath("/accessories");
		String path = PathUtil.SBFile;
		LOG.warn("文件上传的服务器的路径:"+path);
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(path));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		MyProgressListener getBarListener = new MyProgressListener(request);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setProgressListener(getBarListener);
		
		//用来保存上传文件服务器的路径以及文件名称
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
			//for (int i = 0; i < list.size(); i++) {
				//FileItem item = list.get(i);
				// 获取表单的属性名字
				String name = item.getFieldName();
				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，因为表单提交过来的是 字符串类型的
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"),"utf-8");
					request.setAttribute(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else {
					// 获取路径名
					String value = item.getName();
					//boolean flag = value.contains("\\");
					boolean flag = value.contains(File.separator);
					if(flag) {
						// 索引到最后一个反斜杠
					   //int index = value.lastIndexOf("\\");
						int index = value.lastIndexOf(File.separator);
					   value = value.substring(index+1);// 提取文件名
					}
					// 真正写到磁盘上
					// 它抛出的异常 用exception 捕捉

					// item.write( new File(path,filename) );//第三方提供的

					// 手动写的
					OutputStream out = item.getOutputStream();
					InputStream in = item.getInputStream();
					request.getSession().setAttribute("outPutStream", out);
					request.getSession().setAttribute("inPutStream", in);
					//item.write(new File(path,value));
					//获取不包括后缀名的文件名
					String  fmz = value.substring(0,value.lastIndexOf(".")); 
					//获取文件后缀
					String fhz = value.substring(value.lastIndexOf("."));
					Date date = new Date();
					//value = fmz+"$"+date.getTime()+"".trim()+fhz;
					File storefile = new File(path,value);
					LOG.warn("storefile's path: " + storefile.toString());
					for(int i=0;storefile.exists();i++){//如果存在同名的附件，则在后面添加数字区分
						 //获取文件名称后面的文件组后一个.的下标（后缀名）
			            int index = value.lastIndexOf(".");
			            //String sb = value.substring(0,index)+i;
			            String sb = value.replace(value.substring(index-1), i+"");
			            LOG.warn("sb:"+sb);
			            value = sb+value.substring(index);
						storefile = new File(path,value);
					}
					//value = date.getTime()+"".trim()+fhz;
					item.write(storefile);
					/*int length = 0;
					byte[] buf = new byte[1024];
					LOG.warn("获取上传文件的总共的容量：" + item.getSize());
					// in.read(buf) 每次读到的数据存放在 buf 数组中
					while ((length = in.read(buf)) != -1) {
						// 在 buf 数组中 取出数据 写到 （输出流）磁盘上
						out.write(buf, 0, length);
					}
					in.close();
					out.close();*/
					//文件保存到服务器后，将文件路径保存到map中
				map.put(name,value);
					LOG.warn("name:"+name+" value:"+value);
				}
			}
			request.setAttribute("map", map);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block

			// e.printStackTrace();
		}
	}
}