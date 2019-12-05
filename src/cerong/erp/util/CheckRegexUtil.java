package cerong.erp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定义正则表达式的常量
 */
public class CheckRegexUtil {

	/** 电话号码正则表达式（支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号）*/
	//public static final String TEL_REG = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
	//public static final String TEL_REG = "1([\\d]{10})\\s?|((\\+[0-9]{2,4})?\\s?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
	//public static final String TEL_REG = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
	public static final String TEL_REG = "^?1([\\d]{10})|(\\d{7,8})|((\\d{3,4})(-){1,}){1,}\\d{3,8}|((\\d{3,4})(\\s){1,}){1,}\\d{3,8}|((\\d{3,4})(-){1,}){1,}\\d{3,8}$";
	/** 邮箱的正则表达式*/
	public static final String EMAIL_REG = "([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]*@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}";
}
