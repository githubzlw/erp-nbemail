package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cerong.erp.entity.CSjishu;
import cerong.erp.entity.TuZhi;
import cerong.erp.jdbc.SQLDBhelper;

public class TuZhiDao implements ITuZhiDaoImpl{

	@Override
	public int updateCaseNo(int id, String caseNo) {
		String sql = "update tuzhi set caseno=? where id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,caseNo+"A" );
			stmt.setInt(2, id);
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
		}
		return result;
	}

	@Override
	public int updateCaseNo1(int id, String caseNo) {
		String sql = "update tuzhi set caseno=? where id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,caseNo);
			stmt.setInt(2, id);
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
		}
		return result;
	}

	@Override
	public void updateCaseNo1(TuZhi tuzhi) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "insert into tuzhi(CaseNo,remark,UploadDate,name,attribute,task_system_technical_documentation) values(?,?,getdate(),?,?,?)";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, tuzhi.getCaseNo());
			stmt.setString(2,"技术文档");
			stmt.setString(3,tuzhi.getName());
	        stmt.setInt(4,1);
	        stmt.setString(5,tuzhi.getTaskSystemTechnicalDocumentation());
	       
			
			
			
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
			
		}
		
	}

	@Override
	public TuZhi getTuzhi(TuZhi tuzhi) {
		TuZhi tuzhi1=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select * from tuzhi    where caseno=? and task_system_technical_documentation=?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tuzhi.getCaseNo());
			stmt.setString(2, tuzhi.getTaskSystemTechnicalDocumentation());
			rs = stmt.executeQuery();
			if(rs.next()) {
				tuzhi1=new TuZhi();
				tuzhi1.setCaseNo(rs.getString("caseNo"));
				tuzhi1.setTaskSystemTechnicalDocumentation(rs.getString("task_system_technical_documentation"));
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
			SQLDBhelper.close(conn,stmt,rs);
		}
		return tuzhi1;
	}

	@Override
	public CSjishu getCSjishu(CSjishu csjishu) {
		CSjishu csjishu1=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select * from csjishu    where caseno=? and task_technical_agreement=?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, csjishu.getCaseNo());
			stmt.setString(2, csjishu.getTaskTechnicalAgreement());
			rs = stmt.executeQuery();
			if(rs.next()) {
				csjishu1=new CSjishu();
				csjishu1.setCaseNo(rs.getString("caseNo"));
				csjishu1.setTaskTechnicalAgreement(rs.getString("task_technical_agreement"));
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
			SQLDBhelper.close(conn,stmt,rs);
		}
		return csjishu1;
	}

	@Override
	public void addCSjishu(CSjishu csjishu1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "insert into CSjishu(CaseNo,uploader,uploadtime,task_technical_agreement) values(?,?,getdate(),?)";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, csjishu1.getCaseNo());
			stmt.setString(2,csjishu1.getUploader());
			stmt.setString(3,csjishu1.getTaskTechnicalAgreement());
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
		
	}
	}

	@Override
	public void delete(TuZhi tuzhi) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from tuzhi  where id in(select top 1 id from tuzhi where  caseno = ? and attribute=1  order by id desc)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tuzhi.getCaseNo());
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
		}
		
		
	}

	@Override
	public void deleteCSjishu(CSjishu csjishu) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from csjishu  where id in(select top 1 id from csjishu where  caseno = ?   order by id desc)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, csjishu.getCaseNo());
			
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
		}
		
		
	}

}
