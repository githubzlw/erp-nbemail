
package cerong.erp.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SQLDBhelper {
    public static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    public static String url="jdbc:sqlserver://192.168.1.2; DatabaseName=ShangHaiSourcing";
//    public static String user="sa";
//    public static String pwd="Safevault73_1";
//    public static String url="jdbc:sqlserver://192.168.1.91; DatabaseName=ShangHaiSourcing";
//    public static String user="sa";
//    public static String pwd="Admin123";
    public static String url="jdbc:sqlserver://192.168.1.55; DatabaseName=ShangHaiSourcing";
    public static String user="sa";
    public static String pwd="Admin123";
   public static Connection getConnection(){
        try {
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url, user, pwd);
            return con;
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection con,Statement stm,ResultSet rs){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
               
                e.printStackTrace();
            }
        }
        if(stm!=null){
            try {
                stm.close();
            } catch (SQLException e) {
              
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        }
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
     
    public static void main(String[] args) {
        SQLDBhelper.getConnection();
    }
}