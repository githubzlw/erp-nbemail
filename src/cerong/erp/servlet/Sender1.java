package cerong.erp.servlet;

/**
* @Header: MQ发送消息
* 类描述：
* @author: 王宏杰
* @date 2017-06-29 上午09:52:42
*/



import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender1 {
	private static final int SEND_NUMBER = 1;
	
	public static void main(String[] args) {
		//Sender.SendToMQ("select * from orderinfo");
//        Map<String,String> map=new HashMap<String, String>();
//        map.put("sql1", "value1");
//        map.put("sql2", "value2");
//        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(map);
//        System.out.println(jsonObject);
//        String msgg=jsonObject.toString();
//        System.out.println(msgg);
//        try {
//        	ObjectMapper objectMapper = new ObjectMapper();
//			Map<String, Map<String, Object>> maps = objectMapper.readValue(msgg, Map.class);
//			Set<String> key = maps.keySet();
//			Iterator<String> iter = key.iterator();
//            while (iter.hasNext()) {
//                String field = iter.next();
//                System.out.println(field + ":" + maps.get(field));
//            }
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	public static void SendToMQ(String sqls){
		  // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory; // Connection ：JMS 客户端到JMS
        // Provider 的连接
        Connection connection = null; // Session： 一个发送或接收消息的线程
        Session session; // Destination ：消息的目的地;消息发送给谁.
        Destination destination; // MessageProducer：消息发送者
        MessageProducer producer; // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(
                        ActiveMQConnection.DEFAULT_USER,
                        ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.1.62:61616");
        try { // 构造从工厂得到连接对象
                connection = connectionFactory.createConnection();
                // 启动
                connection.start();
                // 获取操作连接
                session = connection.createSession(Boolean.TRUE,
                                Session.AUTO_ACKNOWLEDGE);
                // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
                destination = session.createQueue("Invoiceinfo1");
                // 得到消息生成者【发送者】
                producer = session.createProducer(destination);
                // 设置持久化，此处学习，实际根据项目决定
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                // 构造消息，此处写死，项目就是参数，或者方法获取
                sendMessage(session, producer,sqls);
                session.commit();
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                try {
                        if (null != connection)
                                connection.close();
                } catch (Throwable ignore) {
                }
        }
	}


    public static void sendMessage(Session session, MessageProducer producer,String sqls)
                    throws Exception {
            for (int i = 1; i <= SEND_NUMBER; i++) {
                    TextMessage message = session.createTextMessage(sqls);//必须是String类型
                    // 发送消息到目的地方
                    System.out.println("发送消息：" + "ActiveMq 发送的消息" + sqls);
                    producer.send(message);
            }
    }
}
