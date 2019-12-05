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

import cerong.erp.entity.ContractMaturity;
import cerong.erp.entity.Factory;
import cerong.erp.entity.FactoryFund;
import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.jdbc.SQLDBhelper1;

public class FactoryFundDao implements  IFactoryFundDaoImpl{

	@Override
	public List<FactoryFund> getall(String caseno) {
		List<FactoryFund> list = new ArrayList<FactoryFund>();
		FactoryFund con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " SELECT a.BargainNo,a.fid,b.FactoryName,"
	   		+ "a.friMoney,a.friFacDate,a.checkReason,a.moneytype,a.State,c.BargainUrl FROM FactoryFund as a"
	   		+ " left join FactoryInfo as b on a.fid = b.id left join Bargain as c on a.BargainNo=c.BargainNo  where a.CaseNo = ?	";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseno);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new FactoryFund();
			   
				con.setBargainNo(rs.getString("BargainNo"));
				con.setCheckReason(rs.getString("checkReason"));
				con.setFactoryName(rs.getString("factoryName"));
				con.setFriFacDate(rs.getString("friFacDate"));
				con.setFriMoney(rs.getDouble("friMoney"));
				con.setMoneytype(rs.getInt("moneytype"));
			    con.setState(rs.getString("state"));
			    con.setBargainUrl(rs.getString("bargainUrl"));
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
	public FactoryFund getall1(String caseno) {
		FactoryFund con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String time="";
	    double money1=0.00;
	    double money2=0.00;
	    String state="";
	    int moneytype=0;
	   String sql = " SELECT a.BargainNo,"
	   		+ "a.friMoney,a.checkReason,a.friFacDate,a.moneytype,a.State FROM FactoryFund as a"
	   		+ "   where a.CaseNo = ?	";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseno);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new FactoryFund();
				moneytype=rs.getInt("moneytype");
				state=rs.getString("checkReason");
				if(moneytype==2){
					money1=money1+rs.getDouble("friMoney");
				if("出纳完成付款".equals(state)){
					money2=money2+rs.getDouble("friMoney");
				}
				}else if(moneytype==1){
					time=rs.getString("friFacDate");
					if(time!=null&&!"".equals(time)){
						double total=0.00;
						Date date = new SimpleDateFormat("yyyy/MM/dd").parse(time);
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
						stmt1 = conn.prepareStatement(sql1);
						
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
						money1+=rs.getDouble("friMoney")*total;
					if("出纳完成付款".equals(state)){
						money2+=rs.getDouble("friMoney")*total;
					}	
				}
				
				
				}
				con.setAllMoney(money1);
				con.setAllMoney1(money2);
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
	public Factory getFactory(int id) {
		Factory con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   
	   String sql = " select iCaseNo from invoiceinfo where iid=?	";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new Factory();
				con.setCaseNo(rs.getString("icaseno"));
				
		
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
	public int updateMoney(String apNumber, double d) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "update FactoryFund set friMoney=? where ApNumber = ? ";
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, d);
			
			stmt.setString(2,apNumber );
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


