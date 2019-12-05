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
import cerong.erp.entity.FactoryReconciliation;
import cerong.erp.jdbc.SQLDBhelper;

public class FactoryReconciliationDao implements  IFactoryReconciliationDaoImpl{

	@Override
	public List<FactoryReconciliation> getall(int month,int year) {
		
		List<FactoryReconciliation> list = new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select min(id)id,kingdee,min(FactoryName)FactoryName from FactoryInfo where kingdee!=0  group by kingdee	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String factoryName=rs.getString("FactoryName");
				//int kingdee=1064;
				int kingdee=rs.getInt("kingdee");
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				int result2=0;
				Double money1=0.00;
				Double money2=0.00;
				String time="";
				if(month==12){ 
				int year1=year+1;	
					
				time=year1+"-01-01";
				}else if(month==11){
					int month1=month+1;
					time=year+"-"+month1+"-01";
				}else if(month==10){
					int month1=month+1;
					time=year+"-"+month1+"-01";
				}else if(month==9){
					int month1=month+1;
					time=year+"-"+month1+"-01";
				}else{
			    int month1=month+1;
				time=year+"-0"+month1+"-01";	
				}
				
				String sql2 = "select SUM(Pay_Moeny)Pay_Moeny from Tab_Factory_Money tfm left join  FactoryInfo  fi on fi.id=tfm.Factory_id where fi.kingdee=? and datediff(day,Date_time,'"+time+"')>0 ";
				conn2 = SQLDBhelper.getConnection();
				//conn = DBHelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setInt(1, kingdee);
					
					rs2 = stmt2.executeQuery();
					if(rs2.next()) {
						money1=rs2.getDouble("Pay_Moeny");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt2 != null) {
						try {
							stmt2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs2 != null) {
						try {
							rs2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn2,stmt2,rs2);
					//DBHelper.returnConnection(conn);
				}
				Connection conna2 = null;
				PreparedStatement stmta2 = null;
				ResultSet rsa2 = null;
				String sqla2 = "select SUM(Get_Moeny)Get_Moeny from Tab_Factory_Money tfm left join  FactoryInfo  fi on fi.id=tfm.Factory_id where fi.kingdee=? and datediff(day,createtime,'"+time+"')>0 ";
				conna2 = SQLDBhelper.getConnection();
				//conn = DBHelper.getConnection();
				try {
					stmta2 = conna2.prepareStatement(sqla2);
					stmta2.setInt(1, kingdee);
					
					rsa2 = stmta2.executeQuery();
					if(rsa2.next()) {
						money2=rsa2.getDouble("Get_Moeny");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmta2 != null) {
						try {
							stmta2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rsa2 != null) {
						try {
							rsa2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conna2,stmta2,rsa2);
					//DBHelper.returnConnection(conn);
				}
				
				
				Double money5=0.00;
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				
				String sql4 = "select SUM(Factory_yue_Money)Factory_yue_Money from Tab_Factory_yue where  Factory_yue_kingdeId=?";
				conn4 = SQLDBhelper.getConnection();
				//conn = DBHelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					stmt4.setInt(1, kingdee);
					
					rs4 = stmt4.executeQuery();
					if(rs4.next()) {
						money5=rs4.getDouble("Factory_yue_Money");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt4 != null) {
						try {
							stmt4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs4 != null) {
						try {
							rs4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn4,stmt4,rs4);
					//DBHelper.returnConnection(conn);
				}
				
				double money3=0.00;
				//double money4=0.00;
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				String sql3 = "select sum(Pay_Moeny)Pay_Moeny from Tab_Factory_Money ta left join FactoryInfo fi on fi.id=ta.Factory_id where"
		   		+ " fi.kingdee=? and year(ta.Date_time)=? and month(ta.Date_time)=? ";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setInt(1, kingdee);
					stmt3.setInt(2, year);
					stmt3.setInt(3, month);
					rs3= stmt3.executeQuery();
					if(rs3.next()) {
						money3=rs3.getDouble("Pay_Moeny");
						//money4=rs3.getDouble("Get_Moeny");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt3 != null) {
						try {
							stmt3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs3 != null) {
						try {
							rs3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn3,stmt3,rs3);
					
				}
				
				
				double money4=0.00;
				Connection conn5 = null;
				PreparedStatement stmt5 = null;
				ResultSet rs5 = null;
				String sql5 = "select sum(Get_Moeny)Get_Moeny from Tab_Factory_Money ta left join FactoryInfo fi on fi.id=ta.Factory_id where"
		   		+ " fi.kingdee=? and year(ta.createtime)=? and month(ta.createtime)=? ";
				conn5 = SQLDBhelper.getConnection();
				try {
					stmt5 = conn5.prepareStatement(sql5);
					stmt5.setInt(1, kingdee);
					stmt5.setInt(2, year);
					stmt5.setInt(3, month);
					rs5= stmt5.executeQuery();
					if(rs5.next()) {
						//money3=rs3.getDouble("Pay_Moeny");
						money4=rs5.getDouble("Get_Moeny");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt5 != null) {
						try {
							stmt5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs5 != null) {
						try {
							rs5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn5,stmt5,rs5);
					
				}
				
				if(money3!=0 ||money4!=0){
				FactoryReconciliation con=new FactoryReconciliation();
				if(!"".equals(kingdee)){
				con.setKingdee(kingdee);
				}else{
					con.setKingdee(0);	
				}
				con.setFactoryName(factoryName);
				con.setAmountCredit(money3);
				con.setCurrentDebitAmount(money4);
				double endingBalance=money1-money2;
				con.setEndingBalance(endingBalance);
				con.setPrice(money5);
				list.add(con);
				}
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
	public List<FactoryReconciliation> getall1(int year) {
		 List<FactoryReconciliation> list = new ArrayList<FactoryReconciliation>();
			Connection conn = null;
			PreparedStatement stmt = null;
		    ResultSet rs = null;
		   String sql = " select min(id)id,kingdee,min(FactoryName)FactoryName from FactoryInfo where kingdee!=0  group by kingdee	";
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					int id=rs.getInt("id");
					String factoryName=rs.getString("FactoryName");
					int kingdee=rs.getInt("kingdee");
					Connection conn2 = null;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					int result2=0;
					Double money1=0.00;
					Double money2=0.00;
					String sql2 = "select SUM(Pay_Moeny)Pay_Moeny,SUM(Get_Moeny)Get_Moeny from Tab_Factory_Money tfm left join  FactoryInfo  fi on fi.id=tfm.Factory_id where fi.kingdee=?";
					conn2 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setInt(1, kingdee);
						
						rs2 = stmt2.executeQuery();
						if(rs2.next()) {
							money1=rs2.getDouble("Pay_Moeny");
							money2=rs2.getDouble("Get_Moeny");
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt2 != null) {
							try {
								stmt2.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs2 != null) {
							try {
								rs2.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn2,stmt2,rs2);
						//DBHelper.returnConnection(conn);
					}
					
					Connection conn4 = null;
					PreparedStatement stmt4 = null;
					ResultSet rs4 = null;
					Double money5=0.0;
					String sql4 = "select SUM(Factory_yue_Money)Factory_yue_Money from Tab_Factory_yue where  Factory_yue_kingdeId=?";
					conn4 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt4 = conn4.prepareStatement(sql4);
						stmt4.setInt(1, kingdee);
						
						rs4 = stmt4.executeQuery();
						if(rs4.next()) {
							money5=rs4.getDouble("Factory_yue_Money");
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt4 != null) {
							try {
								stmt4.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs4 != null) {
							try {
								rs4.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn4,stmt4,rs4);
						//DBHelper.returnConnection(conn);
					}
					double money3=0.00;
					double money4=0.00;
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					ResultSet rs3 = null;
					String sql3 = "select SUM(Pay_Moeny)Pay_Moeny,SUM(Get_Moeny)Get_Moeny from Tab_Factory_Money tfm left join  FactoryInfo  fi on fi.id=tfm.Factory_id where fi.kingdee=? and  datename(year,createTime)=?    ";
					conn3 = SQLDBhelper.getConnection();
					try {
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setInt(1, kingdee);
						stmt3.setInt(2, year);
						
						rs3= stmt3.executeQuery();
						if(rs3.next()) {
							money3=rs3.getDouble("Pay_Moeny");
							money4=rs3.getDouble("Get_Moeny");
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt3 != null) {
							try {
								stmt3.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs3 != null) {
							try {
								rs3.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn3,stmt3,rs3);
						
					}
					if(money3!=0){
					FactoryReconciliation con=new FactoryReconciliation();
					if(!"".equals(kingdee)){
					con.setKingdee(kingdee);
					}else{
						con.setKingdee(0);	
					}
					con.setFactoryName(factoryName);
					con.setAmountCredit(money3);
					con.setCurrentDebitAmount(money4);
					double endingBalance=money1-money2;
					con.setEndingBalance(endingBalance);
					con.setPrice(money5);
					list.add(con);
					}
					
			
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
	public List<FactoryReconciliation> getAll(int year, int month, int kingdee) {
		 List<FactoryReconciliation> list = new ArrayList<FactoryReconciliation>();
		  Connection conn = null;
			PreparedStatement stmt = null;
		    ResultSet rs = null;
		   String sql = " select ta.id,fi.FactoryName,ta.Pay_Moeny,ta.Get_Moeny,fi.kingdee,ta.Date_time,ta.case_No,ta.Factory_id from Tab_Factory_Money ta left join FactoryInfo fi on fi.id=ta.Factory_id where"
		   		+ " fi.kingdee=? and year(ta.Date_time)=? and month(ta.Date_time)=? and Get_Moeny=0	";
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, kingdee);
				stmt.setInt(2, year);
				stmt.setInt(3, month);
				rs = stmt.executeQuery();
				while(rs.next()) {
					FactoryReconciliation con=new FactoryReconciliation();
					con.setFactoryName(rs.getString("FactoryName"));
					con.setAmountCredit(rs.getDouble("Pay_Moeny"));
					con.setCurrentDebitAmount(rs.getDouble("Get_Moeny"));
					con.setKingdee(rs.getInt("kingdee"));
					con.setCreateTime(rs.getString("Date_time"));
					con.setCaseNo(rs.getString("case_No"));
					con.setFactoryId(rs.getInt("Factory_id"));
					con.setId(rs.getInt("id"));
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
			Connection conn1 = null;
			PreparedStatement stmt1 = null;
		    ResultSet rs1 = null;
		   String sql1 = " select ta.id,fi.FactoryName,ta.Pay_Moeny,ta.Get_Moeny,fi.kingdee,ta.createtime,ta.case_No,ta.Factory_id  from Tab_Factory_Money ta left join FactoryInfo fi on fi.id=ta.Factory_id where"
		   		+ " fi.kingdee=? and year(ta.createtime)=? and month(ta.createtime)=? and pay_moeny=0	";
			conn1 = SQLDBhelper.getConnection();
			try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setInt(1, kingdee);
				stmt1.setInt(2, year);
				stmt1.setInt(3, month);
				rs1 = stmt1.executeQuery();
				while(rs1.next()) {
					FactoryReconciliation con1=new FactoryReconciliation();
					con1.setFactoryName(rs1.getString("FactoryName"));
					con1.setAmountCredit(rs1.getDouble("Pay_Moeny"));
					con1.setCurrentDebitAmount(rs1.getDouble("Get_Moeny"));
					con1.setKingdee(rs1.getInt("kingdee"));
					con1.setCreateTime(rs1.getString("createtime"));
					con1.setCaseNo(rs1.getString("case_No"));
					con1.setFactoryId(rs1.getInt("Factory_id"));
					con1.setId(rs1.getInt("id"));
					list.add(con1);
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
    public int updateCreateTime(String id, String createTime) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "update Tab_Factory_Money set createtime=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, createTime);
			stmt.setInt(2,Integer.parseInt(id) );
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

}
