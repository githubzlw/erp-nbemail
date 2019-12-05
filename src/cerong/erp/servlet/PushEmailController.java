package cerong.erp.servlet;



import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import cerong.erp.entity.QuotePrice;
import cerong.erp.service.IQuotePriceServiceImpl;
import cerong.erp.service.QuotePriceService;
import cerong.erp.util.SerializeUtil;


public class PushEmailController {
	private static final Logger LOG = LoggerFactory.getLogger(PushEmailController.class);
	 static IQuotePriceServiceImpl qservice = new QuotePriceService();
	public boolean saveMq(Message message) {
		Boolean save=false;
		try {
			String messages=new String(message.getBody(),"utf-8");
			messages=messages.replaceAll("\\\\", "");
			messages = messages.replaceFirst("\"","");
			messages = messages.substring(0,messages.length()-1);
			Map<Object, Object> result=SerializeUtil.JsonToMap(messages);
			String projectNo = (String)result.get("projectNo");
		    String meetingName=(String)result.get("meetingName");
		    if(meetingName!=null&&!"".equalsIgnoreCase(meetingName)){
			String operator=(String)result.get("meetingInputer");
			String meetingDescribe=(String)result.get("meetingDescribe");
			if(meetingName!=null&&!"".equalsIgnoreCase(meetingName)){
			meetingName = new String(meetingName.getBytes("iso-8859-1"),"utf-8");
			}
			if(meetingDescribe!=null&&!"".equalsIgnoreCase(meetingDescribe)){
			meetingDescribe = new String(meetingDescribe.getBytes("iso-8859-1"),"utf-8");
			}
			String details = meetingName+","+meetingDescribe;
			QuotePrice qu=new QuotePrice();
			qu.setDetails(details);
			qu.setProjectNo(projectNo);
			qu.setOperator(operator);
		    int total=qservice.add(qu);
			if(total>0){
				save=true;
			}
		    }
		}catch(Exception e){
			
		}	
		return save;
	}
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
	 public static void addOnMeeting() throws Exception {
	     Connection connection = getConnection(); 
		 Channel channel = connection.createChannel(); 
		 //声明通道 
		 channel.queueDeclare("meeting",true,false,false,null); 
		 //定义消费者 
		 QueueingConsumer consumer = new QueueingConsumer(channel); 
		 //监听队列 
		 channel.basicConsume("meeting",true,consumer);
		 while(true){ //这个方法会阻塞住，直到获取到消息 
			 QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			 try{
			    String messages=new String(delivery.getBody(),"utf-8");
				messages=messages.replaceAll("\\\\", "");
				messages = messages.replaceFirst("\"","");
			    messages = messages.replaceAll("<br/>","");
				messages = messages.substring(0,messages.length()-1);
				Map<Object, Object> result=SerializeUtil.JsonToMap(messages);
				String projectNo = (String)result.get("projectNo");
			    String meetingName=(String)result.get("meetingName");
			    if(meetingName!=null&&!"".equalsIgnoreCase(meetingName)){
				String operator=(String)result.get("meetingInputer");
				String meetingDescribe=(String)result.get("meetingDescribe");
				String details = meetingName+","+meetingDescribe;
				QuotePrice qu=new QuotePrice();
				qu.setDetails(details);
				qu.setProjectNo(projectNo);
				qu.setOperator(operator);
			    int total=qservice.add(qu);
			    LOG.warn(projectNo);
			    }
			 }catch(Exception e){
				  LOG.error(delivery.getBody()+"错误");
				    LOG.error(e.toString());
				    String messages=new String(delivery.getBody(),"utf-8");
				    messages=messages.replaceAll("\\\\", "");
					messages = messages.replaceFirst("\"","");
					messages = messages.substring(0,messages.length()-1);
					Map<Object, Object> result=SerializeUtil.JsonToMap(messages);
					String projectNo = (String)result.get("projectNo");
				    String meetingName=(String)result.get("meetingName");
				    if(meetingName!=null&&!"".equalsIgnoreCase(meetingName)){
					String operator=(String)result.get("meetingInputer");
					String meetingDescribe=(String)result.get("meetingDescribe");
					String details = meetingName+","+meetingDescribe;
					QuotePrice qu=new QuotePrice();
					qu.setDetails(details);
					qu.setProjectNo(projectNo);
					qu.setOperator(operator);
				    int total=qservice.add(qu);
				   
				    }
				 }
			}
	    }
}
