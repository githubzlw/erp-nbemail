package cerong.erp.servlet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cerong.erp.entity.CSjishu;
import cerong.erp.entity.QuotePrice;
import cerong.erp.entity.TechnologyTable;
import cerong.erp.entity.TuZhi;
import cerong.erp.service.IQuotePriceServiceImpl;
import cerong.erp.service.ITuZhiServiceImpl;
import cerong.erp.service.ItCaseIdService;
import cerong.erp.service.ItCaseIdServiceImpl;
import cerong.erp.service.QuotePriceService;
import cerong.erp.service.TuZhiService;
import cerong.erp.util.SerializeUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class TechnicalDocumentation {
	static ITuZhiServiceImpl tservice = new TuZhiService();
	private static final Logger LOG = LoggerFactory.getLogger(TechnicalDocumentation.class);
	static IQuotePriceServiceImpl qservice = new QuotePriceService();
	static ItCaseIdServiceImpl itservice = new ItCaseIdService();
	public static Connection getConnection() throws Exception { //定义连接工厂 
		 ConnectionFactory factory = new ConnectionFactory(); 
		 factory.setHost("117.144.21.74");
		 factory.setPort(5672); 
		 //设置vhost 
		 factory.setVirtualHost("/"); 
		 factory.setUsername("admin"); 
		 factory.setPassword("admin"); 
		 //通过工厂获取连接
		 Connection connection = factory.newConnection(); 
		 return connection; 
	 }
	 /*public static void main(String[] args) throws Exception { //获取连接和通道 
*/		 
	 public static Void addData() throws Exception {
	 
	 Connection connection = getConnection(); 
		 Channel channel = connection.createChannel(); 
		 //声明通道 
		 channel.queueDeclare("quality",true,false,false,null); 
		 //定义消费者 
		 QueueingConsumer consumer = new QueueingConsumer(channel); 
		 //监听队列 
		 channel.basicConsume("quality",true,consumer);
		 while(true){ //这个方法会阻塞住，直到获取到消息 
			
			 QueueingConsumer.Delivery delivery = consumer.nextDelivery(); 
			 try{
			 String messages=new String(delivery.getBody(),"utf-8");
				messages=messages.replaceAll("\\\\", "");
				messages = messages.replaceFirst("\"","");
				messages = messages.substring(0,messages.length()-1);
				Map<Object, Object> result=SerializeUtil.JsonToMap(messages);
				String projectNo = (String)result.get("projectNo");
			    String qualityAnalysisNewName=(String)result.get("qualityAnalysisNewName");
				String technologyAnalysisNewName=(String)result.get("technologyAnalysisNewName");
				String process=(String)result.get("process");
				if(process!=null&&!"".equalsIgnoreCase(process)){
					String []processes=process.split(",");
					String process1=processes[0];
					if(process1!=null&&!"".equalsIgnoreCase(process1)){
						TechnologyTable table=itservice.getAll(process1);
						if(table!=null){
							itservice.updateProjectMaterialProperties(table.getProcessAttributes(),projectNo);
						}
					}
				}
				if(qualityAnalysisNewName!=null&&!"".equalsIgnoreCase(qualityAnalysisNewName)){
					TuZhi tuzhi=new TuZhi();
					tuzhi.setTaskSystemTechnicalDocumentation(qualityAnalysisNewName);
					tuzhi.setCaseNo(projectNo);
					tuzhi.setName("technician");
					TuZhi tuzhi1=tservice.getTuzhi(tuzhi);
					if(tuzhi1!=null){}else{
					tservice.add(tuzhi);
					}
				}
				if(technologyAnalysisNewName!=null&&!"".equalsIgnoreCase(technologyAnalysisNewName)){
					CSjishu csjishu=new CSjishu();
					csjishu.setCaseNo(projectNo);
					csjishu.setTaskTechnicalAgreement(technologyAnalysisNewName);
					csjishu.setUploader("technician");
					CSjishu csjishu1=tservice.getCSjishu(csjishu);
					if(csjishu1!=null){}else{
						tservice.addCSjishu(csjishu);
					}
				}
			 }catch(Exception e){
				 String messages=new String(delivery.getBody(),"utf-8");
					messages=messages.replaceAll("\\\\", "");
					messages = messages.replaceFirst("\"","");
					messages = messages.substring(0,messages.length()-1);
					Map<Object, Object> result=SerializeUtil.JsonToMap(messages);
					String projectNo = (String)result.get("projectNo");
				    String qualityAnalysisNewName=(String)result.get("qualityAnalysisNewName");
					String technologyAnalysisNewName=(String)result.get("technologyAnalysisNewName");
					String process=(String)result.get("process");
					if(process!=null&&!"".equalsIgnoreCase(process)){
						String []processes=process.split(",");
						String process1=processes[0];
						if(process1!=null&&!"".equalsIgnoreCase(process1)){
							TechnologyTable table=itservice.getAll(process1);
							if(table!=null){
								itservice.updateProjectMaterialProperties(table.getProcessAttributes(),projectNo);
							}
						}
					}
					if(qualityAnalysisNewName!=null&&!"".equalsIgnoreCase(qualityAnalysisNewName)){
						TuZhi tuzhi=new TuZhi();
						tuzhi.setTaskSystemTechnicalDocumentation(qualityAnalysisNewName);
						tuzhi.setCaseNo(projectNo);
						tuzhi.setName("technician");
						TuZhi tuzhi1=tservice.getTuzhi(tuzhi);
						if(tuzhi1!=null){}else{
						tservice.add(tuzhi);
						}
					}
					if(technologyAnalysisNewName!=null&&!"".equalsIgnoreCase(technologyAnalysisNewName)){
						CSjishu csjishu=new CSjishu();
						csjishu.setCaseNo(projectNo);
						csjishu.setTaskTechnicalAgreement(technologyAnalysisNewName);
						csjishu.setUploader("technician");
						CSjishu csjishu1=tservice.getCSjishu(csjishu);
						if(csjishu1!=null){}else{
							tservice.addCSjishu(csjishu);
						}
					}
				 LOG.error(e.toString()); 
			 }
				
			}
	 }
	 public static Void deleteTechnicalDocuments() throws Exception {
		 
		 Connection connection = getConnection(); 
		 Channel channel = connection.createChannel(); 
		 //声明通道 
		 channel.queueDeclare("qualityDelete",true,false,false,null); 
		 //定义消费者 
		 QueueingConsumer consumer = new QueueingConsumer(channel); 
		 //监听队列 
		 channel.basicConsume("qualityDelete",true,consumer);
		 while(true){ //这个方法会阻塞住，直到获取到消息 
			  QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			  try{
			 String messages=new String(delivery.getBody(),"utf-8");
			 messages=messages.replaceAll("\\\\", "");
			 messages = messages.replaceFirst("\"","");
			 messages = messages.substring(0,messages.length()-1);
			 Map<Object, Object> result=SerializeUtil.JsonToMap(messages);
			 String projectNo = (String)result.get("projectNo");
			 String delete=(String)result.get("delete");
			
			 if("qualityAnalysisNewName".equals(delete)){
				 TuZhi tuzhi=new TuZhi();
				 tuzhi.setCaseNo(projectNo);
				 tservice.delete(tuzhi);
			 }
			 if("technologyAnalysisNewName".equalsIgnoreCase(delete)){
				 CSjishu csjishu=new CSjishu();
				 csjishu.setCaseNo(projectNo);
				 tservice.deleteCSjishu(csjishu);
				
			 }
			  }catch(Exception e){
				  String messages=new String(delivery.getBody(),"utf-8");
					 messages=messages.replaceAll("\\\\", "");
					 messages = messages.replaceFirst("\"","");
					 messages = messages.substring(0,messages.length()-1);
					 Map<Object, Object> result=SerializeUtil.JsonToMap(messages);
					 String projectNo = (String)result.get("projectNo");
					 String delete=(String)result.get("delete");
					
					 if("qualityAnalysisNewName".equals(delete)){
						 TuZhi tuzhi=new TuZhi();
						 tuzhi.setCaseNo(projectNo);
						 tservice.delete(tuzhi);
					 }
					 if("technologyAnalysisNewName".equalsIgnoreCase(delete)){
						 CSjishu csjishu=new CSjishu();
						 csjishu.setCaseNo(projectNo);
						 tservice.deleteCSjishu(csjishu);
						
					 }
				  LOG.error(e.toString()); 
			  }
			 
		 }
	 }
}
