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
import cerong.erp.entity.AccountRefundForm;
import cerong.erp.jdbc.SQLDBhelper;

public class AccountRefundFormDaoImpl implements IAccountRefundFormDao{

	@Override
	public List<AccountRefundForm> allRefundInterface() {
     List<AccountRefundForm> list = new ArrayList<AccountRefundForm>();
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select * from AccountRefundForm where approvalResults=0 order by createTime desc	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				AccountRefundForm con=new AccountRefundForm();
			    con.setId(rs.getInt("id"));
			    con.setCaseno(rs.getString("caseno"));
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setIimoney(rs.getDouble("iimoney"));
			    con.setFinalIncome(rs.getDouble("finalIncome"));
			    con.setRefundAmount(rs.getDouble("refundAmount"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setCreateTime(rs.getString("createTime"));
			    con.setOperator(rs.getString("operator"));
			    con.setIid(rs.getInt("iid"));
			    con.setApprover(rs.getString("approver"));
			    con.setApprovalTime(rs.getString("approvalTime"));
			    con.setApprovalResults(rs.getInt("approvalResults"));
			    con.setReason(rs.getString("reason"));
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
	public int add(AccountRefundForm account) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         String createTime= df.format(new Date());
		String sql = "insert into AccountRefundForm(invoice,refundAmount,iid,createTime,"
				+ "Operator,caseno,iimoney,ifmoney,reason) values(?,?,?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getInvoice());
			stmt.setDouble(2, account.getRefundAmount());
			stmt.setInt(3, account.getIid());
			stmt.setString(4,createTime);
			stmt.setString(5, account.getOperator());
			stmt.setString(6, account.getCaseno());
			stmt.setDouble(7, account.getIimoney());
			stmt.setDouble(8, account.getIfmoney());
			stmt.setString(9, account.getReason());
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
	public List<AccountRefundForm> refundApprovalResult(AccountRefundForm account) {
		 List<AccountRefundForm> list = new ArrayList<AccountRefundForm>();
			Connection conn = null;
			PreparedStatement stmt = null;
		    ResultSet rs = null;
		   String sql = " select * from AccountRefundForm where approvalResults!=0 ";
		   		if(account.getCaseno()!=null&&!"".equalsIgnoreCase(account.getCaseno())){
		   		sql+=" and caseno like ? ";
		   		}
		   		if(account.getOperator()!=null&&!"".equalsIgnoreCase(account.getOperator())){
		   			sql+=" and Operator like ? ";	
		   		}
		   		if(account.getFinancialConfirmation()==0){
		   			sql+=" and financial_confirmation =0 ";
		   		}else if(account.getFinancialConfirmation()==1){
		   			sql+=" and financial_confirmation =1 ";
		   		}else if(account.getFinancialConfirmation()==-1){
		   			
		   		}
		   		sql+= " order by approvalTime desc	";
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				int i=0;
				if(account.getCaseno()!=null&&!"".equalsIgnoreCase(account.getCaseno())){
			   		i++; 
			   		stmt.setString(i, "%"+account.getCaseno()+"%");
			   		}
			   		if(account.getOperator()!=null&&!"".equalsIgnoreCase(account.getOperator())){
			   			i++;
				   		stmt.setString(i, "%"+account.getOperator()+"%");	
			   		}
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					AccountRefundForm con=new AccountRefundForm();
				    con.setId(rs.getInt("id"));
				    con.setCaseno(rs.getString("caseno"));
				    con.setIfmoney(rs.getDouble("ifmoney"));
				    con.setIimoney(rs.getDouble("iimoney"));
				    con.setFinalIncome(rs.getDouble("finalIncome"));
				    con.setRefundAmount(rs.getDouble("refundAmount"));
				    con.setInvoice(rs.getString("invoice"));
				    con.setCreateTime(rs.getString("createTime"));
				    con.setOperator(rs.getString("operator"));
				    con.setIid(rs.getInt("iid"));
				    con.setApprover(rs.getString("approver"));
				    con.setApprovalTime(rs.getString("approvalTime"));
				    con.setApprovalResults(rs.getInt("approvalResults"));
				    con.setReason(rs.getString("reason"));
				    con.setFinancialConfirmation(rs.getInt("financial_confirmation"));
				    con.setFinancialConfirmationPerson(rs.getString("financial_confirmation_person"));
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
	public int updateApprovalResults(int num, int id, String empEName) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "update AccountRefundForm set approver=?,approvalTime='"+time+"',approvalResults=?";
				if(num==1){
					sql+=",finalIncome=ifmoney-refundAmount";	
				}else if(num==2){
					sql+=",finalIncome=ifmoney";
				}
				sql+= " where id=? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empEName);
			stmt.setInt(2, num);
			stmt.setInt(3, id);
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
	public int updateModifyTemporaryTable(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into tab_InvoiceMessages (CaseNo,message,name,date )  select caseno,'发票['+convert(varchar(20),invoice)+']退一笔款项 金额为['+convert(varchar(20),refundAmount)+']，原已付款为'+convert(varchar(20),ifmoney)+'对应的到款日期为['+Convert(varchar(10),b.ifdate,120)+']',Operator,createTime from AccountRefundForm a left join (select iid,ifdate from invoiceinfo  )b on a.iid=b.iid  where id =? ";
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
	public int getAccountRefundForm() {
		int total=0;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select count(1) from AccountRefundForm where approvalResults=0 	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				total=rs.getInt(1);
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
		return total;
	}

	@Override
	public int add1(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into tab_InvoiceMessages (CaseNo,message,name,date )  select caseno,'发票['+convert(varchar(20),invoice)+'] 原收入['+convert(varchar(20),ifmoney)+'] 现退款金额为['+convert(varchar(20),finalIncome)+'] 现实际收款为['+convert(varchar(20),refundAmount)+']' ,Operator,createTime from AccountRefundForm where id in("+id+")   ";
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

	@Override
	public int add2(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into FactoryInvoice(CaseNo,Remark,CreateDate,empname) select caseno,'发票['+convert(varchar(20),invoice)+'] 原收入['+convert(varchar(20),ifmoney)+'] 现退款金额为['+convert(varchar(20),refundAmount)+'] 现实际收款为['+convert(varchar(20),finalIncome)+']' ,getdate(),Operator from AccountRefundForm where id in("+id+")   ";
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

	@Override
	public AccountRefundForm getAll(String invoice) {
		AccountRefundForm con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select * from AccountRefundForm where financial_confirmation=0  and invoice=?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, invoice);
			rs = stmt.executeQuery();
			if(rs.next()) {
				con=new AccountRefundForm();
				con.setId(rs.getInt("id"));
			    con.setCaseno(rs.getString("caseno"));
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setIimoney(rs.getDouble("iimoney"));
			    con.setFinalIncome(rs.getDouble("finalIncome"));
			    con.setRefundAmount(rs.getDouble("refundAmount"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setCreateTime(rs.getString("createTime"));
			    con.setOperator(rs.getString("operator"));
			    con.setIid(rs.getInt("iid"));
			    con.setApprover(rs.getString("approver"));
			    con.setApprovalTime(rs.getString("approvalTime"));
			    con.setApprovalResults(rs.getInt("approvalResults"));
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
		return con;
	}

	@Override
	public List<AccountRefundForm> refundApprovalResult1(AccountRefundForm account) {
		List<AccountRefundForm> list = new ArrayList<AccountRefundForm>();
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select * from AccountRefundForm where approvalResults!=0 and Operator=?";
	   if(account.getCaseno()!=null&&!"".equalsIgnoreCase(account.getCaseno())){
	   		sql+=" and caseno like ? ";
	   		}
	   		
	   		if(account.getFinancialConfirmation()==0){
	   			sql+=" and financial_confirmation =0 ";
	   		}else if(account.getFinancialConfirmation()==1){
	   			sql+=" and financial_confirmation =1 ";
	   		}else if(account.getFinancialConfirmation()==-1){
	   			
	   		}
	   		sql+= " order by approvalTime desc	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			int i=0;
			stmt.setString(1, account.getOperator());
			if(account.getCaseno()!=null&&!"".equalsIgnoreCase(account.getCaseno())){
		   		i++;
		   		stmt.setString(i+1, "%"+account.getCaseno()+"%");
		   		}
		   		if(account.getOperator()!=null&&!"".equalsIgnoreCase(account.getOperator())){
		   			i++;
			   		stmt.setString(i+1, "%"+account.getOperator()+"%");	
		   		}
			rs = stmt.executeQuery();
			while(rs.next()) {
				AccountRefundForm con=new AccountRefundForm();
			    con.setId(rs.getInt("id"));
			    con.setCaseno(rs.getString("caseno"));
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setIimoney(rs.getDouble("iimoney"));
			    con.setFinalIncome(rs.getDouble("finalIncome"));
			    con.setRefundAmount(rs.getDouble("refundAmount"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setCreateTime(rs.getString("createTime"));
			    con.setOperator(rs.getString("operator"));
			    con.setIid(rs.getInt("iid"));
			    con.setApprover(rs.getString("approver"));
			    con.setApprovalTime(rs.getString("approvalTime"));
			    con.setApprovalResults(rs.getInt("approvalResults"));
			    con.setReason(rs.getString("reason"));
			    con.setFinancialConfirmation(rs.getInt("financial_confirmation"));
			    con.setFinancialConfirmationPerson(rs.getString("financial_confirmation_person"));
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
	public int updateRefundCompleted(int id, String empEName) {
	
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "update AccountRefundForm set financial_confirmation=1,financial_confirmation_person=?";
				
				sql+= " where id=? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empEName);
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
	public List<AccountRefundForm> allRefundInterface1(String empEName) {
		List<AccountRefundForm> list = new ArrayList<AccountRefundForm>();
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select * from AccountRefundForm where approvalResults=0 and Operator=? order by approvalTime desc	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empEName);
			rs = stmt.executeQuery();
			while(rs.next()) {
				AccountRefundForm con=new AccountRefundForm();
			    con.setId(rs.getInt("id"));
			    con.setCaseno(rs.getString("caseno"));
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setIimoney(rs.getDouble("iimoney"));
			    con.setFinalIncome(rs.getDouble("finalIncome"));
			    con.setRefundAmount(rs.getDouble("refundAmount"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setCreateTime(rs.getString("createTime"));
			    con.setOperator(rs.getString("operator"));
			    con.setIid(rs.getInt("iid"));
			    con.setApprover(rs.getString("approver"));
			    con.setApprovalTime(rs.getString("approvalTime"));
			    con.setApprovalResults(rs.getInt("approvalResults"));
			    con.setReason(rs.getString("reason"));
			    con.setFinancialConfirmation(rs.getInt("financial_confirmation"));
			    con.setFinancialConfirmationPerson(rs.getString("financial_confirmation_person"));
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

}
