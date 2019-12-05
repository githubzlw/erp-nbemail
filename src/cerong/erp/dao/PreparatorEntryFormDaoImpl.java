package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.AmountClaimForm;
import cerong.erp.entity.PreparatorEntryForm;
import cerong.erp.entity.invoiceinfo;
import cerong.erp.jdbc.SQLDBhelper;

public class PreparatorEntryFormDaoImpl implements  IPreparatorEntryFormDao{

	@Override
	public int add(String invoice,int id,String time,int ibank, String caseno) {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");   
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");   
		 Date dateTime=null;
		try {
			dateTime = sdf.parse(time);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
        String  formDateStr = sdf1.format(dateTime);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = " insert into PreparatorEntryForm (invoice,iimoney,ifmoney,iid ,AmountClaimFormId,ifdate,ibank,caseno,imoneytype)select iInvNo,iimoney,ifmoney,iid,"+id+",'"+formDateStr+"',"+ibank+",'"+caseno+"',imoneytype from invoiceinfo where ifmoney is null and iimoney!=0 and iInvNo=?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, invoice);
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
	public int getId(String invoice) {
		int total=0;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select id from PreparatorEntryForm where invoice=? and dataProcessing=0	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, invoice);
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
	public int update(int id, double allInvoiceMoney) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update PreparatorEntryForm set ifmoney=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, allInvoiceMoney);
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
	public List<PreparatorEntryForm> getAll(int id) {
   List<PreparatorEntryForm> list = new ArrayList<PreparatorEntryForm>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String sql = " select * from PreparatorEntryForm where amountClaimFormId=?	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				PreparatorEntryForm con=new PreparatorEntryForm();
			    con.setAmountClaimFormId(rs.getInt("amountClaimFormId"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setIimoney(rs.getDouble("iimoney"));
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setIid(rs.getInt("iid"));
			    con.setSalesSubmission(rs.getInt("salesSubmission"));
			    con.setId(rs.getInt("id"));
			    con.setRemarks(rs.getString("remarks"));
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
		String sql = "delete from PreparatorEntryForm  where AmountClaimFormId = ? ";
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
	public int updateAll(PreparatorEntryForm preparator1) {
		  Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);   
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql="update PreparatorEntryForm set iimoney=?,saleName=?,salesSubmission=?";
		if(preparator1.getIfmoney()!=null&&!"".equals(preparator1.getIfmoney())&&preparator1.getIfmoney()!=0){
		 sql+= ",ifmoney=? "; 
		}else{
			sql+= ",ifmoney=null "; 
		}
		sql+=",createTime='"+time+"' ,ifdate='"+preparator1.getCaseno()+"' ,remarks='"+preparator1.getRemarks()+"' where AmountClaimFormId = ? and iid=? ";
		 conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, preparator1.getIimoney());
			stmt.setString(2, preparator1.getSaleName());
			stmt.setInt(3, 1);
			if(preparator1.getIfmoney()!=null&&!"".equals(preparator1.getIfmoney())&&preparator1.getIfmoney()!=0){
			stmt.setDouble(4, preparator1.getIfmoney());
			
			stmt.setInt(5, preparator1.getAmountClaimFormId());
			stmt.setInt(6, preparator1.getIid());
			}else{
				stmt.setInt(4, preparator1.getAmountClaimFormId());
				stmt.setInt(5, preparator1.getIid());	
					
			}
			
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
	public PreparatorEntryForm getOne(int id) {
		PreparatorEntryForm con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String sql = " select * from PreparatorEntryForm where amountClaimFormId=?	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				 con=new PreparatorEntryForm();
			    con.setAmountClaimFormId(rs.getInt("amountClaimFormId"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setIimoney(rs.getDouble("iimoney"));
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setIid(rs.getInt("iid"));
			    con.setSalesSubmission(rs.getInt("salesSubmission"));
			    con.setIfdate(rs.getDate("ifdate"));
				
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
	public List<PreparatorEntryForm> getAll() {
List<PreparatorEntryForm> list = new ArrayList<PreparatorEntryForm>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String sql = " select * from PreparatorEntryForm where financialConfirmation=0	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				PreparatorEntryForm con=new PreparatorEntryForm();
			    con.setAmountClaimFormId(rs.getInt("amountClaimFormId"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setIimoney(rs.getDouble("iimoney"));
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setIid(rs.getInt("iid"));
			    con.setSalesSubmission(rs.getInt("salesSubmission"));
			    con.setSaleName(rs.getString("saleName"));
			    con.setId(rs.getInt("id"));
			    con.setIfdate(rs.getDate("ifdate"));
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
	public int updateModificationResults(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "  UPDATE invoiceinfo  SET invoiceinfo.iimoney =PreparatorEntryForm.iimoney,invoiceinfo.ifmoney=PreparatorEntryForm.ifmoney"
				+ ",invoiceinfo.ibank=PreparatorEntryForm.ibank,invoiceinfo.ifdate=PreparatorEntryForm.ifdate, invoiceinfo.updateReason=PreparatorEntryForm.remarks  from invoiceinfo "
				+ ",PreparatorEntryForm  where invoiceinfo.iid=PreparatorEntryForm.iid and PreparatorEntryForm.AmountClaimFormId in ("+id+") and PreparatorEntryForm.iid!=0 ";
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
	public int updateModifyTemporaryTable(String eids) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "  UPDATE PreparatorEntryForm set DataProcessing=1,financialConfirmation=1,financialConfirmationTime='"+time+"'  where PreparatorEntryForm.AmountClaimFormId in("+eids+") ";
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
	public int add(String eids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into tab_InvoiceMessages (CaseNo,message,name,date )  select caseno,'发票['+convert(varchar(20),invoice)+']增加一笔实际到款 金额为['+convert(varchar(20),ifmoney)+']',saleName,createTime from PreparatorEntryForm where AmountClaimFormId in("+eids+") and ifmoney is not null  ";
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
	public int add1(String eids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into FactoryInvoice(CaseNo,Remark,CreateDate,empname) select caseno,'发票['+convert(varchar(20),invoice)+']  预计入账金额为['+convert(varchar(20),iimoney)+'] 现实际收款为['+convert(varchar(20),ifmoney)+']' ,createTime,saleName from PreparatorEntryForm where AmountClaimFormId in("+eids+") and ifmoney is not null  ";
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
	public int insert(PreparatorEntryForm preparator1) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		int id=0;
		String sql = " insert into PreparatorEntryForm (invoice,iimoney,ifmoney ,AmountClaimFormId,ifdate,ibank,caseno,salesSubmission,saleName,createTime,imoneytype)select invoice,?,?,AmountClaimFormId,ifdate,ibank,caseno,1,saleName,createTime,imoneytype from PreparatorEntryForm where id=?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, preparator1.getIimoney());
			stmt.setDouble(2, preparator1.getIfmoney());
			stmt.setInt(3, preparator1.getId());
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
	public int addInvoice(String eids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		int id=0;
		String sql = " insert into invoiceinfo (iCaseNo,iInvNo,imoneytype ,iimoney,iidate,ibank,icreatedate)select caseno,invoice,imoneytype,iimoney,createTime,ibank,createTime from PreparatorEntryForm where AmountClaimFormId in ("+eids+") and iid=0";
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
