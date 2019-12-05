package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cerong.erp.entity.QuotePrice;
import cerong.erp.jdbc.SQLDBhelper;

public class QuotePriceDao implements IQuotePriceDaoImpl{

	@Override
	public int add(QuotePrice qu) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "insert into QuotePrice(CaseNo,EmployeeName,CurrentStatus,UpdateTime,UploadUrl) values(?,?,?,getdate(),?)";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, qu.getProjectNo());
			stmt.setString(2,qu.getOperator());
			stmt.setString(3,qu.getDetails());
	        stmt.setString(4,qu.getFileUrl());
			
			
			
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
