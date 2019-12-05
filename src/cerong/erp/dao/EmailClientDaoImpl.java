package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*import cerong.erp.jdbc.DBHelper;*/

public class EmailClientDaoImpl implements IEmailClientIDDao{

	/*@Override
	public int getcid(int cid) {
		int id = 0;
		String sql = "select erp_client_id from email_clientid where email_client_id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBHelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBHelper.returnConnection(conn);
		}
		return id;
	}*/

}
