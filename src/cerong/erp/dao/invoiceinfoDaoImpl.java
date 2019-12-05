package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cerong.erp.entity.FactoryFund;
import cerong.erp.entity.Invoiceinfo1;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.invoiceinfo;
import cerong.erp.jdbc.SQLDBhelper;

public class invoiceinfoDaoImpl implements  IinvoiceinfoDao{

	@Override
	public List<invoiceinfo> getall(String caseno) {
		List<invoiceinfo> list = new ArrayList<invoiceinfo>();
		invoiceinfo con=null;
		Connection conn = null;
		String time="";
		int moneytype=0;
		
		
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " SELECT a.iInvNo,a.iUrl,a.imoneytype"
	   		+ ",a.iimoney,a.iidate,a.ifmoney,a.ifdate,a.updateReason"
	   		+ ",a.icasetype,a.Split,b.bname FROM invoiceinfo AS a LEFT JOIN bankinfo"
	   		+ " AS b ON a.ibank=b.bid WHERE a.iCaseNo = ? ORDER BY iinvno ASC";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseno);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new invoiceinfo();
				time=rs.getString("iidate");
				moneytype=rs.getInt("imoneytype");
			   con.setIfmoney(rs.getDouble("ifmoney"));	
				con.setBname(rs.getString("bname"));
			   con.setIcasetype(rs.getString("icasetype"));
			   con.setIfdate(rs.getString("ifdate"));
			   con.setIidate(rs.getString("iidate"));
				con.setIimoney(rs.getDouble("iimoney"));
				con.setiInvNo(rs.getString("iInvNo"));
				con.setiUrl(rs.getString("iUrl"));
				con.setUpdateReason(rs.getString("updateReason"));
				con.setImoneytype(moneytype);
				if(moneytype==2){
					con.setExchangerate(1);	
				}else{
				if(time!=null&&!"".equals(time)){
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
					Calendar now = Calendar.getInstance();
					now.setTime(date);
					int year =  now.get(Calendar.YEAR);;
					int  month = now.get(Calendar.MONTH) + 1;
					String _month = "";
					if(month==1){
						_month = "Jan";	
					}else if(month==2){
						_month = "Feb";
					}else if(month==3){
						_month = "Mar";
					}else if(month==4){
						_month = "Apr";
					}else if(month==5){
						_month = "May";
					}else if(month==6){
						_month = "Jun";
					}else if(month==7){
						_month = "Jul";
					}else if(month==8){
						_month = "Aug";
					}else if(month==9){
						_month = "Sep";
					}else if(month==10){
						_month = "Oct";
					}else if(month==11){
						_month = "Nov";
					}else if(month==12){
						_month = "Dec";	
					}
				
				String sql1 = "select "+_month+" from OptionByProfit where pyear="+year; 
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				double total=0.00;
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					
					rs1 = stmt1.executeQuery();
					if(rs1.next()) {
						total = rs1.getDouble(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs1 != null) {
						try {
							rs1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt1 != null) {
						try {
							stmt1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn1,stmt1,rs1);
				}
				con.setExchangerate(total);
				}
				}
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
	public invoiceinfo getall1(String caseno) {
		invoiceinfo con=null;
		Connection conn = null;
		String time="";
		int moneytype=0;
		double money1=0.00;
		double money2=0.00;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " SELECT a.iInvNo,a.iUrl,a.imoneytype"
	   		+ ",a.iimoney,a.iidate,a.ifmoney,a.ifdate,a.updateReason"
	   		+ ",a.icasetype,a.Split,b.bname FROM invoiceinfo AS a LEFT JOIN bankinfo"
	   		+ " AS b ON a.ibank=b.bid WHERE a.iCaseNo = ? ORDER BY iinvno ASC";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseno);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new invoiceinfo();
				time=rs.getString("iidate");
				moneytype=rs.getInt("imoneytype");
				 if(moneytype==2){
					if(time!=null&&!"".equals(time)){
						double total=0.00;
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
						Calendar now = Calendar.getInstance();
						now.setTime(date);
						int year =  now.get(Calendar.YEAR);;
						int  month = now.get(Calendar.MONTH) + 1;
						String _month = "";
						if(month==1){
							_month = "Jan";	
						}else if(month==2){
							_month = "Feb";
						}else if(month==3){
							_month = "Mar";
						}else if(month==4){
							_month = "Apr";
						}else if(month==5){
							_month = "May";
						}else if(month==6){
							_month = "Jun";
						}else if(month==7){
							_month = "Jul";
						}else if(month==8){
							_month = "Aug";
						}else if(month==9){
							_month = "Sep";
						}else if(month==10){
							_month = "Oct";
						}else if(month==11){
							_month = "Nov";
						}else if(month==12){
							_month = "Dec";	
						}
					
					String sql1 = "select "+_month+" from OptionByProfit where pyear="+year; 
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					
					conn1 = SQLDBhelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						
						rs1 = stmt1.executeQuery();
						if(rs1.next()) {
							total = rs1.getDouble(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn1,stmt1,rs1);
					}
					money1=money1+rs.getDouble("iimoney")/total;
					money2=money2+rs.getDouble("ifmoney")/total;
					}
					
					
				}else{
					money1=money1+rs.getDouble("iimoney");
					money2=money2+rs.getDouble("ifmoney");
				}
				
				con.setAllmoney(money1);
				con.setAllmoney1(money2);
		
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
	public invoiceinfo getInvoice(String invoice) {
		invoiceinfo info=null;
		String sql = "select sum(iimoney)iimoney,sum(ifmoney)ifmoney,icaseno,min(imoneytype)imoneytype from invoiceinfo where iInvNo='"+invoice+"' and ifmoney is null  group by icaseno "; 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				info=new invoiceinfo();
				info.setCaseno(rs.getString("icaseno"));
				info.setIimoney(rs.getDouble("iimoney"));
				info.setIfmoney(rs.getDouble("ifmoney"));
				info.setImoneytype(rs.getInt("imoneytype"));
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
		return info;
	}

	@Override
	public invoiceinfo getRefundInvoice(String invoice, String empEName) {
		invoiceinfo info=null;
		String sql = " select * from InvoiceInfo info left join itemcase it on it.caseno=info.icaseno"
				+ "  where iInvNo=? and ifmoney !=0  and ifmoney is not null "
				+ " and (it.CustomerManager =? or it.MerchandManager1=? or it.MerchandManager2=? or it.MaturePurchase=? or"
				+ " it.OriginalPurchase=? or it.Merchandising=?)"; 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, invoice);
			for(int i=1;i<7;i++){
				
				stmt.setString(i+1, empEName);
			}
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				info=new invoiceinfo();
				info.setCaseno(rs.getString("icaseno"));
				info.setIimoney(rs.getDouble("iimoney"));
				info.setIfmoney(rs.getDouble("ifmoney"));
				info.setIid(rs.getInt("iid"));
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
		return info;
	}

	@Override
	public int updateApprovalResults(int id,int num) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = " UPDATE invoiceinfo  SET invoiceinfo.ifmoney=AccountRefundForm.finalIncome"
				+ " from invoiceinfo "
				+ ",AccountRefundForm  where invoiceinfo.iid=AccountRefundForm.iid and AccountRefundForm.id =? ";
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
	public List<Invoiceinfo1> queryReceiptList(Invoiceinfo1 it) {
		List<Invoiceinfo1> list = new ArrayList<Invoiceinfo1>();
		Invoiceinfo1 con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select * from (  select icaseno,ifmoney,CONVERT(varchar(100), ifdate, 23)ifdate,ibank,imoneytype from invoiceinfo where ifmoney is not null and ifmoney!=0 and ifdate is not null ";
	   		if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
	   		sql+= " and datediff(day,?,ifdate)>=0 ";
	   		}
	   		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
		   		sql+= " and datediff(day,?,ifdate)<=0 ";
		   		}
	   		if(it.getIbank()!=-1){
	   			if(it.getIbank()==0){
	   				sql+= " and ibank !=5  and ibank !=12 and ibank !=8 ";	
	   			}else{
		   		sql+= " and ibank=? ";
		   		}
	   		}
	   		sql+=" )a order by ifdate ";
		conn = SQLDBhelper.getConnection();
		try {
			int i=0;
			stmt = conn.prepareStatement(sql);
			if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
				i++;
				stmt.setString(i, it.getStartTime());
		   		}
		   		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
		   			i++;
		   			stmt.setString(i, it.getEndTime());
			   		}
		   		if(it.getIbank()!=-1){
		   			if(it.getIbank()==0){
		   		}else{
		   			i++;
		   			stmt.setInt(i, it.getIbank());
			   		}
		   		}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new Invoiceinfo1();
			    con.setIfmoney(rs.getDouble("ifmoney"));
			    con.setProjectId(rs.getString("icaseno"));
			    con.setIfdate(rs.getString("ifdate"));
			    con.setImoneytype(rs.getInt("imoneytype"));
			    con.setIbank(rs.getInt("ibank"));
				
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
	public invoiceinfo getRefundInvoice(String invoice) {
		invoiceinfo info=null;
		String sql = " select * from InvoiceInfo info left join itemcase it on it.caseno=info.icaseno"
				+ "  where iInvNo=? and ifmoney !=0  and ifmoney is not null "; 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, invoice);
			
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				info=new invoiceinfo();
				info.setCaseno(rs.getString("icaseno"));
				info.setIimoney(rs.getDouble("iimoney"));
				info.setIfmoney(rs.getDouble("ifmoney"));
				info.setIid(rs.getInt("iid"));
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
		return info;
	}

}
