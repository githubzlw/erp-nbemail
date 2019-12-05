package cerong.erp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return date;
    }
	/**
	 * 根据当前时间得到前两周的时间
	 * @return
	 */
	public static String getTwoWeeksDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去两周
        c.setTime(new Date());
        c.add(Calendar.DATE, - 14);
        Date d = c.getTime();
        String date = format.format(d);
		return date;
	}
	
	public static String dateToStr(Date myDate) {  
	      SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");  
	      String strDate = formatter.format(myDate);  
	      return strDate;  
	}  
	  
	public static void main(String xp[]) throws ParseException{
		//System.out.println(dateToStr(new Date()));
		System.err.println("www.google.com".replaceAll("\\.", "////"));
	}
}
