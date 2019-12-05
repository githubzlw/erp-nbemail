package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.AmountClaimForm;
import cerong.erp.jdbc.SQLDBhelper;

public class AmountClaimFormDaoImpl implements IAmountClaimFormDao{

	@Override
	public List<AmountClaimForm> enterFinancialEntry(int id) {
		List<AmountClaimForm> list = new ArrayList<AmountClaimForm>();
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select * from AmountClaimForm where AccountEntryId=?	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				AmountClaimForm con=new AmountClaimForm();
			    con.setAccountEntryId(id);
			    con.setId(rs.getInt("id"));
			    con.setExportMonth(rs.getString("exportMonth"));
			    con.setExportYear(rs.getString("exportYear"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setSumMoney(rs.getDouble("sumMoney"));
			    con.setConfirmationTime(rs.getString("confirmationTime"));
			    con.setDataProcessing(rs.getInt("dataProcessing"));
			    con.setFinancialConfirmation(rs.getInt("financialConfirmation"));
			    con.setCountry(rs.getInt("country"));
				list.add(con);
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
			
		}
		return list;
	}

	@Override
	public int recoveryInformation(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from AmountClaimForm  where AccountEntryId = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
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
	public int insert(AmountClaimForm claim) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into AmountClaimForm(AccountEntryId,ExportYear,ExportMonth,invoice,"
				+ "SumMoney,country,state) values(?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, claim.getAccountEntryId());
			stmt.setString(2, claim.getExportYear());
			stmt.setString(3, claim.getExportMonth());
			stmt.setString(4, claim.getInvoice());
			stmt.setDouble(5, claim.getSumMoney());
			stmt.setInt(6, claim.getCountry());
			stmt.setString(7, claim.getState());
			
			
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
	public int updateModificationResults(String eids) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update AmountClaimForm set FinancialConfirmation=1,DataProcessing=1,ConfirmationTime='"+time+"' where AccountEntryId in ("+eids+") ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
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

	

}
