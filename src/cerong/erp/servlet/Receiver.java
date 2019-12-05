package cerong.erp.servlet;

/**
 * @Header: MQ接收消息
 * 类描述：
 * @author: 王宏杰
 * @date 2017-06-29 上午09:52:42
 */


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class Receiver {
	private static final Log MQLOG = LogFactory.getLog("mq");

	public static void main(String[] args) {
		Receiver.ReceiverForMQ();
	}

	public static void ReceiverForMQ() {
		ConnectionFactory connectionFactory;
		Connection connection = null;
		Session session;
		Destination destination;
		MessageConsumer consumer;
		com.mysql.jdbc.Connection conn2=null;
		com.mysql.jdbc.Connection conn1=null;
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,
				"tcp://192.168.1.62:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			while (true) {
				// 设置接收者接收消息的时间，定为1s
				TextMessage message = (TextMessage) consumer.receive(3000);
				if (null != message) {
					//conn2 = (com.mysql.jdbc.Connection) DBHelper2
							//.getConnection();
					//conn1 = (com.mysql.jdbc.Connection) DBHelper2
							//.getConnection();
					String sqls = message.getText();
					System.out.println("收到消息" + sqls);
					/*try {
						ObjectMapper objectMapper = new ObjectMapper();
						Map<String, Map<String, Object>> maps = objectMapper.readValue(sqls, Map.class);
						Set<String> key = maps.keySet();
						Iterator<String> iter = key.iterator();
						while (iter.hasNext()) {
							String field = iter.next();
							MQLOG.info("类别备注:" + field);
							MQLOG.info("执行语句:" + maps.get(field));
							String type=field;
							String sql=maps.get(field).toString();
							if("0".equals(sql.split("|")[1])){
								try{
									conn1.setAutoCommit(false);
									conn1.createStatement().execute(sql);//prepareStatement(maps.get(field).toString()).executeUpdate();
									conn1.commit();
								}catch(Exception e){
									conn1.rollback();
									MQLOG.info("开始回滚;消息队列本地执行失败:【"+type+"】,执行的语句为:"+sql);
								}
							}else if("1".equals(sql.split("|")[1])){
								try{
									conn2.setAutoCommit(false);
									conn2.createStatement().execute(sql);
									conn2.commit();
								}catch(Exception e){
									conn2.rollback();
									MQLOG.info("开始回滚;消息队列美服执行失败:【"+type+"】,执行的语句为:"+sql);
								}
							}
						}
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}*/
				} else {
					System.out.println("没有消息");
					//DBHelper.returnConnection(conn1);
					//DBHelper.returnConnection(conn2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (Throwable ignore) {
			}
		}
	}

}
