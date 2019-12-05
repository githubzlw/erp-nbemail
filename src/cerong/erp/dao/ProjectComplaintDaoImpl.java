package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;








import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.jdbc.SQLDBhelper1;
import cerong.erp.entity.ProjectComplaint;
import cerong.erp.service.IProjectComplaintService;

public class ProjectComplaintDaoImpl implements IProjectComplaintDao{

	@Override
	public int getall() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProjectComplaint pc=null;
		int result = 0;
		int id = 0;
		Connection conn1 = null;
		PreparedStatement stmt1 = null;
		int result1 = 0;
		ResultSet rs1 = null;
		String project=null;
		String sql1 = "select * from project ";
		conn1 =  SQLDBhelper1.getConnection();
		 //DBHelper.getConnection();
		try {
			stmt1 = conn1.prepareStatement(sql1, 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			rs1 = stmt1.executeQuery();
			while(rs1.next()) {
				
				id=rs1.getInt("id");
				System.out.print(id);
				project=rs1.getString("proId");
				
				
				String sql = "update itemCase set baobiao=? where CaseNo = ? ";
				conn = SQLDBhelper.getConnection();
				
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					stmt.setString(2,project );
					result1 = stmt.executeUpdate();
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
					
				}
				
				
				
			}	
				
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SQLDBhelper1.close(conn1,stmt1,rs1);
			//SQLDBhelper1.returnConnection(conn);
			//DBHelper.returnConnection(conn);
		}
		return result1;
	}

	@Override
	public int getid() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ProjectComplaint pc=null;
		
		int id = 0;
		int total=0;
		Connection conn1 = null;
		PreparedStatement stmt1 = null;
		int result1 = 0;
		ResultSet rs1 = null;
		String sql1 = "select * from project order by id desc";
		conn1 =  SQLDBhelper1.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt1 = conn1.prepareStatement(sql1);
			
			
			rs1 = stmt1.executeQuery();
			if(rs1.next()) {
				id=rs1.getInt("id");
				
				
				String sql = "select count(*) from itemCase where  baobiao= ? ";
				conn = SQLDBhelper.getConnection();
				
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, id);
					rs = stmt.executeQuery();
					if(rs.next()) {
						result1 = rs.getInt(1);
					}
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
				
				
				
			}
				
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SQLDBhelper1.close(conn1,stmt1,rs1);
			// SQLDBhelper1.returnConnection(conn);
			//DBHelper.returnConnection(conn);
		}
		return result1;
	}

}
