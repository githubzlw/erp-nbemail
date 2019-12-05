package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cerong.erp.entity.QuoteClient;
import cerong.erp.jdbc.SQLDBhelper;

public class QuoteClientDao implements IQuoteClientDaoImpl{

	@Override
	public int add(QuoteClient qc) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "insert into BJduiwai(CaseNo,title,url,creater,gongyiliucheng,lirun,dingdan,creattime) values(?,?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, qc.getCaseNo());
			stmt.setString(2,qc.getTitle());
			stmt.setString(3,qc.getUrl() );
			stmt.setString(4,qc.getCreater());
			stmt.setString(5,qc.getGongyiliucheng() );
			stmt.setInt(6,qc.getLirun() );
			stmt.setInt(7,qc.getDingdan() );
			stmt.setString(8,qc.getCreatetime() );
			
			
          result = stmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SQLDBhelper.close(conn,stmt,rs);
			//DBHelper.returnConnection(conn);
		}
		return result;
	}

}
