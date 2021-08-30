package cerong.erp.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cerong.erp.lucene.bean.FileBean;
import cerong.erp.lucene.bean.FileBeanIndex;

public class CheckUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(CheckUtil.class);
	
	/**
	 * 方法描述:为文件创建索引  
	 * author:lzj
	 * date:2015年5月28日
	 * @param fileBeans
	 */
	public static void createIndex(List<FileBean> fileBeans) {
		try {
			int totalCount = fileBeans.size();
			int perThreadCount = 3000;
			LOG.warn("查询到的数据总数是" + fileBeans.size());
			int threadCount = totalCount / perThreadCount
					+ (totalCount % perThreadCount == 0 ? 0 : 1);
			ExecutorService pool = Executors.newFixedThreadPool(threadCount);
			CountDownLatch countDownLatch1 = new CountDownLatch(1);
			CountDownLatch countDownLatch2 = new CountDownLatch(threadCount);

			for (int i = 0; i < threadCount; i++) {
				int start = i * perThreadCount;
				int end = (i + 1) * perThreadCount < totalCount ? (i + 1)
						* perThreadCount : totalCount;
				List<FileBean> subList = fileBeans.subList(start, end);
				Runnable runnable = new FileBeanIndex("index", i,
						countDownLatch1, countDownLatch2, subList);
				// 子线程交给线程池管理
				pool.execute(runnable);
			}
			countDownLatch1.countDown();
			LOG.warn("开始创建索引");
			// 等待所有线程都完成
			countDownLatch2.await();
			// 线程全部完成工作
			LOG.warn("所有线程都创建索引完毕");
			// 释放线程池资源
			pool.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 方法描述:查询是否包含指定的关键字
	 * author:lzj
	 * date:2015年5月28日
	 */
	public static boolean seache() {
		boolean flag = false;
		try {
			IndexSearcher searcher = SearchUtil.getMultiSearcher("index",
					Executors.newCachedThreadPool(), false);
			//IndexSearcher searcher = SearchUtil.getIndexSearcher("index",
					//Executors.newCachedThreadPool(), false);
			
			
			Query phoneQuery = SearchUtil.getRegexExpQuery("content",CheckRegexUtil.TEL_REG);
			Query mailQuery = SearchUtil.getRegexExpQuery("content",CheckRegexUtil.EMAIL_REG);
			Query finaQuery = SearchUtil.getMultiQueryLikeSqlIn(new Query[] {
					phoneQuery, mailQuery });
			TopDocs topDocs = SearchUtil.getScoreDocsByPerPageAndSortField(
					searcher, finaQuery, 0, 20, null);
			LOG.warn("符合条件的数据总数：" + topDocs.totalHits);
			LOG.warn("本次查询到的数目为：" + topDocs.scoreDocs.length);
			if (topDocs.scoreDocs.length > 0) {
				flag = true;
			}
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = searcher.doc(scoreDoc.doc);
				LOG.warn(doc.get("path") + "  "+ doc.get("content"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	  /**
     * 方法描述:过滤掉电话号码以及邮箱
     * author:
     * date:2015年6月2日
     * @param regex
     * @param source
     * @return
     */
    public static String filter(String regex,String source) {
    	Pattern p = Pattern.compile(regex);
    	Matcher m = p.matcher(source);
    	boolean find = m.find();
    	StringBuffer sb = new StringBuffer();
    	while(find) {
    		m.appendReplacement(sb, "");
    		// 继续查找下一个匹配对象
    		find = m.find();
    	}
    	// 最后调用 appendTail() 方法将最后一次匹配后的剩余字符串加到 sb 里；
    	m.appendTail(sb);
    	//System.out.println(sb.toString());
    	return sb.toString();
    }
    
    /**
     * 方法描述:过滤掉客户的其他敏感信息(如公司名字,客户地址,邮编,网址)
     * author:lzj
     * date:2015年7月1日
     * @param regex 具体要过滤的字符串
     * @param source 来源字符串 
     * @return
     */
    public static String filterOther(String regex,String source) {
    	//source = source.toLowerCase();
    	String regex1= regex.toLowerCase();
    	String regex2 = regex.toUpperCase();
    	String regex3= regex.substring(0, 1).toUpperCase()+regex.substring(1, regex.length());
    	//Boolean index1 = source.toLowerCase().contains(user.getUserName().toLowerCase());
    	boolean c1 = source.contains(regex1);
    	boolean c2= source.contains(regex2);
    	boolean c3 = source.contains(regex3);
    	
    	//LOG.warn(c+"");
    	if(c1) {
    		source = source.replaceAll(regex1, "");
    	}
    	if(c2) {
    		source = source.replaceAll(regex2, "");
    	}
    	if(c3) {
    		source = source.replaceAll(regex3, "");
    	}
    	return source;
    }
    public static void main(String[] args) {
    	String source = "I have received the attached pricing from Barry Bloomfield of Earthtrack Australia. We are the Canadian division of Earthtrack and are also interested in purchasing these products from you. Please help with the following information:"
 
+"1.       What is the cost per unit if we began with 10 only sets to fit in a 20’ sea container?"
+"2.       What is the cost of having the galvanizing and powder coating done in China?"
+"3.       What is your factory address where the goods will be shipped from. I need this in order to get a quote on container shipping to the Port of Vancouver, Canada."
+"4.       Do you have container shipping quotes available?ask price this my phone:133 p is my key"
+"Steve Todd | Accounts Manager"
+"Earthtrack Supply Group Ltd."
+"steve@earthtrackgroup.com"
+ "521444"
+"Direct  021-84325688"
+"Phone 604 625 7400"
+"Toll Free 1 855  897  7400"
+"Fax 131-6256-8945,Fax 856-5869-568";
    	boolean c = source.contains("have");
    	System.out.println(c);
    	if(c) {
    		source = source.replaceAll("have", "");
    	}
    	System.out.println(source);
    	//"^(\\d{3,4}-)|(\\d{3,4}\\s)?\\d{3,8}|(\\d{3,4}-)?\\d{3,8}$";
    	//String reg = "^?1([\\d]{10})|((\\d{3,4})(-){1,}){1,}\\d{3,8}|((\\d{3,4})(\\s){1,}){1,}\\d{3,8}|((\\d{3,4})(-){1,}){1,}\\d{3,8}$";
    	//String reg2 = "^(\\(\\d{3,4}-)|(\\d{3,4}-)?\\d{7,8}$";
    	//Pattern p = Pattern.compile("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}");
    	/*Pattern p = Pattern.compile(CheckRegexUtil.TEL_REG);
    	Matcher m = p.matcher(source);
    	boolean find = m.find();
    	System.out.println(find);
    	StringBuffer sb = new StringBuffer();
    	while(find) {
    		Matcher matcher = m.appendReplacement(sb, "xxxxxx");
    		String group = matcher.group();
    		System.out.println(group);
    		System.out.println(sb);
    		// 继续查找下一个匹配对象
    		find = m.find();
    	}
    	// 最后调用 appendTail() 方法将最后一次匹配后的剩余字符串加到 sb 里；
    	m.appendTail(sb);
    	System.out.println(sb.toString());*/
	}
    
    /**
     * 方法描述:查找是否包含指定关键字
     * author:lzj
     * date:2015年6月3日
     * @param regex
     * @param source
     * @return
     */
    public static boolean isKey(String regex,String source) {
    	Pattern p = Pattern.compile(regex);
    	Matcher m = p.matcher(source);
    	boolean find = m.find();
    	return find;
    }


	public static String getCountyName(int coutryId) {

		String couryName="";
		switch(coutryId){
			case 1:
				couryName="USA";
				break;
			case 2:
				couryName="Canada";
				break;
			case 3:
				couryName="France";
				break;
			case 4:
				couryName="Germany";
				break;
			case 5:
				couryName="Netherlands";
				break;
			case 6:
				couryName="Israel";
				break;
			case 7:
				couryName="Mexico";
				break;
			case 8:
				couryName="Australia";
				break;
			case 9:
				couryName="Italy";
				break;
			case 10:
				couryName="Switzerland";
				break;
			case 11:
				couryName="Finland";
				break;
			case 12:
				couryName="Sweden";
				break;
			case 13:
				couryName="UK";
				break;
			case 14:
				couryName="Argentina";
				break;
			case 15:
				couryName="Other";
				break;
			case 16:
				couryName="Japan";
				break;
			case 17:
				couryName="China";
				break;
			case 18:
				couryName="Austria";
				break;
			case 19:
				couryName="Saudi Arabia";
				break;
			case 20:
				couryName="Belgium";
				break;
			case 21:
				couryName="Spain";
				break;
			case 22:
				couryName="New Zealand";
				break;
			case 23:
				couryName="Slovenia";
				break;
			case 24:
				couryName="Serbia";
				break;
			case 25:
				couryName="Ireland";
				break;

			default:
				couryName="";
		}
		return couryName;
	}

}
