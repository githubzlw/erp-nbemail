package cerong.erp.jdbc;

/**
 * 业务服务器使用
 * @author chenhaishen
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;



import cerong.erp.util.TransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class DBHelper {
	public static ComboPooledDataSource pool = null;
//	public static int serverId=0;
	public static void init() {
		try {
			destory();
			//System.out.println("初始化数据库");
			synchronized (DBHelper.class) {
				InputStream ins = DBHelper.class.getResourceAsStream("../../../jdbc.properties");
				Properties p = new Properties();
				try {
					p.load(ins);
				} catch (Exception e) {
					e.printStackTrace();
				}
				pool = new ComboPooledDataSource();
				pool.setDriverClass(p.getProperty("driver")); // loads the jdbc
																// driver
				pool.setJdbcUrl(p.getProperty("url"));
				pool.setUser(p.getProperty("userName"));
				pool.setPassword(p.getProperty("userPass"));
				
				
//				serverId=Integer.parseInt(p.getProperty("serverId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 销毁
	 */
	public static void destory() {
		try {
			if (pool != null) {
				DataSources.destroy(pool);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			if(pool==null){
				init();
			}
			return pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void returnConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 /** 
     * 获取事务管理器 
     * @return 事务管理实例 
     */  
    public static synchronized TransactionManager getTranManager(){  
        return new TransactionManager(getConnection());  
    } 
}
