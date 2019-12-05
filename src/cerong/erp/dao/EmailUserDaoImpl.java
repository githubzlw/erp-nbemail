package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*import cerong.erp.jdbc.DBHelper;*/
import cerong.erp.entity.EmailUser;

public class EmailUserDaoImpl implements IEmailUserDao {

	/*@Override
	public int get(EmailUser user) {
		String sql = "select count(*) from email_user where role_no=?&&user_name=?&&pwd=?&&role_no>98 ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,user.getRoleNo() );
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getPwd());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}

	@Override
	public int getDimission(int userId) {
		String sql = "select dimission from email_user where id=? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int dimission=0;
		conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				dimission = rs.getInt(1);
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
		return dimission;
	}

	@Override
	public int get1(EmailUser user) {
		String sql = "select count(*) from email_user where role_no=?&&user_name=?&&pwd=?&&role_no in(2,99,100) ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,user.getRoleNo() );
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getPwd());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}

	@Override
	public String getsaleName(int userId) {
		String sql = "select user_name from email_user where id=? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String userName=null;
		conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				userName = rs.getString(1);
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
		return userName;
	}
*/
	

}
