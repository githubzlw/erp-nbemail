package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cerong.erp.entity.Admin;
import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.jdbc.SQLDBhelper1;

public class AdminDao implements IAdminDaoImpl{

	@Override
	public int add(Admin admin) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into admin(name,pass,auth) values(?,?,?)";
		conn = SQLDBhelper1.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, admin.getName());
			stmt.setString(2, admin.getPass());
			stmt.setInt(3, admin.getAuth());
		
			
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
			SQLDBhelper1.close(conn,stmt,rs);
		}
		return result;
	}

	@Override
	public int getid(String userName) {
		String sql = "select id from admin where name=?  ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper1.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
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
			SQLDBhelper1.close(conn,stmt,rs);
		}
		return total;
	}

}
