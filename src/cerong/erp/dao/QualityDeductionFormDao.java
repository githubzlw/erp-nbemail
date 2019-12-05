package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cerong.erp.entity.FactoryFund;
import cerong.erp.jdbc.SQLDBhelper;

public class QualityDeductionFormDao implements IQualityDeductionFormDaoImpl{

	@Override
	public int add(String caseNo, double money1, int id,String reason) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		Date date = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		String time=df.format(date);
		String sql = "insert into QualityDeductionForm (CaseNo,createTime,QualityId,QualityDeductions)values(?,?,?,?)";
		conn = SQLDBhelper.getConnection();
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseNo);
			stmt.setString(2, time);
			stmt.setInt(3, id);
			stmt.setDouble(4, money1);
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

	@Override
	public int add1(String projectId, String contractNumber,
			double actualDeductions1, int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		Date date = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		String time=df.format(date);
		String sql = "insert into ContractDeductionForm (CaseNo,ContractNumber,Amount,createTime,QualityId)values(?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			stmt.setString(2, contractNumber);
			stmt.setDouble(3, actualDeductions1);
			stmt.setString(4, time);
			stmt.setInt(5, id);
			
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
	public FactoryFund checkMoney(String projectId, String contractNumber,
			double actualDeductions1, int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		double result = 0.0;
		ResultSet rs = null;
		FactoryFund fund=null;
        String sql = "  select friMoney,ApNumber from FactoryFund where bargainNo=? and state !='<font color=green>已完成款项</font>' ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, contractNumber);
			rs = stmt.executeQuery();
			if(rs.next()) {
				fund=new FactoryFund();
				fund.setFriMoney(rs.getDouble("friMoney"));
				fund.setCaseNo(rs.getString("ApNumber"));
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
        return fund;
	}

	@Override
	public int addFactoryInvoice(String projectId, double actualDeductions1,
			String contractNumber, String empEName,double money,String reason) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
	
		String sql = "insert into FactoryInvoice(CaseNo,Remark,CreateDate,empname) values(?,?,getdate(),?)";
		conn = SQLDBhelper.getConnection();
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			if(reason!=null&&!"".equals(reason)){
			stmt.setString(2, contractNumber+"原需付款["+money+"元]  现需付款["+actualDeductions1+"元] 解释:"+reason);	
			}else{
			stmt.setString(2, contractNumber+"原需付款["+money+"元]  现需付款["+actualDeductions1+"元]");
			}
			stmt.setString(3, empEName);
			
			
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
	public int addFactoryInvoice1(String projectId, double actualDeductions1,
			String contractNumber, String empEName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
	     String sql = "insert into FactoryInvoice(CaseNo,Remark,CreateDate,empname) values(?,?,getdate(),?)";
		conn = SQLDBhelper.getConnection();
	      try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			stmt.setString(2, contractNumber+"需扣"+actualDeductions1+"元 但工厂款已经全部付完");
			stmt.setString(3, empEName);
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
	public int checkMoney(String projectId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		double result = 0.0;
		ResultSet rs = null;
		int id=0;
		FactoryFund fund=null;
        String sql = "  select id from FactoryInvoice where CaseNo=? and Remark like '%工厂款已经全部付完%' ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
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
        return id;
	}

	}

	


