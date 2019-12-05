package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cerong.erp.entity.*;
import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.util.Calendar;

import cerong.erp.jdbc.SQLDBhelper;

public class InvinceDaoImpl implements  IInvinceDao{

	private Object totalRevenueDollars;
	@Override
	public List<invoice> getAll(int start, int end) {
		List<invoice> list = new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select a.iCaseNo,it.customercode,it.CreateTime from "
				+ "(select ii.iCaseNo  from InvoiceInfo ii group by iCaseNo)a "
				+ "left join itemCase it on it.CaseNo=a.iCaseNo where datediff(day,CreateTime,getdate())<400 and CaseStatus=0 or CaseStatus=14 "
				+ "and datediff(day,CreateTime,getdate())<400   ";
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, end);
			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				String  CaseNo=rs.getString("iCaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				
				rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					String	date=rs1.getString("iidate");
					if(date!=null&&!"".equals(date)){
					imoneytype1=rs1.getInt("imoneytype");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Calendar cal = Calendar.getInstance(); 
					    Date dt = null; 
					    try { 
					      dt = sdf.parse(date); 
					      cal.setTime(dt); 
					    } catch (Exception e) { 
					      e.printStackTrace(); 
					    }
					    double huilv=0.0;
					    double huilv1=0.0;
					    int year = cal.get(Calendar.YEAR); 
					    int month = cal.get(Calendar.MONTH) + 1; 
						
						String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						conn2 = SQLDBhelper.getConnection();
						try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setInt(1, year);
						stmt2.setInt(2, month);
						stmt2.setInt(3, imoneytype1);
						rs2 = stmt2.executeQuery();
							if(rs2.next()) {
								huilv=rs2.getDouble(1);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (rs2 != null) {
								try {
									rs2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (stmt2 != null) {
								try {
									stmt2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.returnConnection(conn2);
						}
						if(imoneytype1==1){
							ifmoney+=rs1.getDouble("ifmoney");
							iimoney+=rs1.getDouble("iimoney");
						}else if(imoneytype1==2){
							ifmoney+=rs1.getDouble("ifmoney")/huilv;
							iimoney+=rs1.getDouble("iimoney")/huilv;
						}else{
							  String sql3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
								Connection conn3 = null;
								PreparedStatement stmt3 = null;
								ResultSet rs3 = null;
								conn3 = SQLDBhelper.getConnection();
								try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setInt(1, year);
								stmt3.setInt(2, month);
								stmt3.setInt(3, 1);
								rs3 = stmt3.executeQuery();
									if(rs3.next()) {
										huilv1=rs3.getDouble(1);
									}
									
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (rs3 != null) {
										try {
											rs3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									if (stmt3 != null) {
										try {
											stmt3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									SQLDBhelper.returnConnection(conn3);
								}
							ifmoney+=(rs1.getDouble("ifmoney")/huilv1)*huilv;
							iimoney+=(rs1.getDouble("iimoney")/huilv1)*huilv;
							
						}
						
					}
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
					SQLDBhelper.returnConnection(conn1);
				}
				
				BigDecimal   b   =   new   BigDecimal(ifmoney); 
				double   f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				BigDecimal   b1   =   new   BigDecimal(iimoney); 
				double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				invoice info = new invoice();
				info.setAmount(f1);
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(f);
				list.add(info);	
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
		return list;
	}

	public List<invoice> getAll1(int start, int end) {
		List<invoice> list = new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select a.iCaseNo,it.customercode,it.CreateTime from "
				+ "(select ii.iCaseNo  from InvoiceInfo ii group by iCaseNo)a "
				+ "left join itemCase it on it.CaseNo=a.iCaseNo where it.customercode<? and it.customercode>39999  ";
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, end);
			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				String  CaseNo=rs.getString("iCaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				
				rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					String	date=rs1.getString("iidate");
					if(date!=null&&!"".equals(date)){
					imoneytype1=rs1.getInt("imoneytype");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Calendar cal = Calendar.getInstance(); 
					    Date dt = null; 
					    try { 
					      dt = sdf.parse(date); 
					      cal.setTime(dt); 
					    } catch (Exception e) { 
					      e.printStackTrace(); 
					    }
					    double huilv=0.0;
					    double huilv1=0.0;
					    int year = cal.get(Calendar.YEAR); 
					    int month = cal.get(Calendar.MONTH) + 1; 
						
						String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						conn2 = SQLDBhelper.getConnection();
						try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setInt(1, year);
						stmt2.setInt(2, month);
						stmt2.setInt(3, imoneytype1);
						rs2 = stmt2.executeQuery();
							if(rs2.next()) {
								huilv=rs2.getDouble(1);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (rs2 != null) {
								try {
									rs2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (stmt2 != null) {
								try {
									stmt2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.returnConnection(conn2);
						}
						if(imoneytype1==1){
							ifmoney+=rs1.getDouble("ifmoney");
							iimoney+=rs1.getDouble("iimoney");
						}else if(imoneytype1==2){
							ifmoney+=rs1.getDouble("ifmoney")/huilv;
							iimoney+=rs1.getDouble("iimoney")/huilv;
						}else{
							  String sql3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
								Connection conn3 = null;
								PreparedStatement stmt3 = null;
								ResultSet rs3 = null;
								conn3 = SQLDBhelper.getConnection();
								try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setInt(1, year);
								stmt3.setInt(2, month);
								stmt3.setInt(3, 1);
								rs3 = stmt3.executeQuery();
									if(rs3.next()) {
										huilv1=rs3.getDouble(1);
									}
									
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (rs3 != null) {
										try {
											rs3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									if (stmt3 != null) {
										try {
											stmt3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									SQLDBhelper.returnConnection(conn3);
								}
							ifmoney+=(rs1.getDouble("ifmoney")/huilv1)*huilv;
							iimoney+=(rs1.getDouble("iimoney")/huilv1)*huilv;
							
						}
						
					}
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
					SQLDBhelper.returnConnection(conn1);
				}
				
				BigDecimal   b   =   new   BigDecimal(ifmoney); 
				double   f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				BigDecimal   b1   =   new   BigDecimal(iimoney); 
				double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				invoice info = new invoice();
				info.setAmount(f);
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(f1);
				list.add(info);	
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
		return list;
	}
	

	public List<invoice> getAll2(int start, int end) {
		List<invoice> list = new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select a.iCaseNo,it.customercode,it.CreateTime from "
				+ "(select ii.iCaseNo  from InvoiceInfo ii group by iCaseNo)a "
				+ "left join itemCase it on it.CaseNo=a.iCaseNo where it.customercode<? and it.customercode>79999  ";
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, end);
			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				String  CaseNo=rs.getString("iCaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				
				rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					String	date=rs1.getString("iidate");
					if(date!=null&&!"".equals(date)){
					imoneytype1=rs1.getInt("imoneytype");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Calendar cal = Calendar.getInstance(); 
					    Date dt = null; 
					    try { 
					      dt = sdf.parse(date); 
					      cal.setTime(dt); 
					    } catch (Exception e) { 
					      e.printStackTrace(); 
					    }
					    double huilv=0.0;
					    double huilv1=0.0;
					    int year = cal.get(Calendar.YEAR); 
					    int month = cal.get(Calendar.MONTH) + 1; 
						
						String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						conn2 = SQLDBhelper.getConnection();
						try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setInt(1, year);
						stmt2.setInt(2, month);
						stmt2.setInt(3, imoneytype1);
						rs2 = stmt2.executeQuery();
							if(rs2.next()) {
								huilv=rs2.getDouble(1);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (rs2 != null) {
								try {
									rs2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (stmt2 != null) {
								try {
									stmt2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.returnConnection(conn2);
						}
						if(imoneytype1==1){
							ifmoney+=rs1.getDouble("ifmoney");
							iimoney+=rs1.getDouble("iimoney");
						}else if(imoneytype1==2){
							ifmoney+=rs1.getDouble("ifmoney")/huilv;
							iimoney+=rs1.getDouble("iimoney")/huilv;
						}else{
							  String sql3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
								Connection conn3 = null;
								PreparedStatement stmt3 = null;
								ResultSet rs3 = null;
								conn3 = SQLDBhelper.getConnection();
								try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setInt(1, year);
								stmt3.setInt(2, month);
								stmt3.setInt(3, 1);
								rs3 = stmt3.executeQuery();
									if(rs3.next()) {
										huilv1=rs3.getDouble(1);
									}
									
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (rs3 != null) {
										try {
											rs3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									if (stmt3 != null) {
										try {
											stmt3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									SQLDBhelper.returnConnection(conn3);
								}
							ifmoney+=(rs1.getDouble("ifmoney")/huilv1)*huilv;
							iimoney+=(rs1.getDouble("iimoney")/huilv1)*huilv;
							
						}
						
					}
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
					SQLDBhelper.returnConnection(conn1);
				}
				
				BigDecimal   b   =   new   BigDecimal(ifmoney); 
				double   f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				BigDecimal   b1   =   new   BigDecimal(iimoney); 
				double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				invoice info = new invoice();
				info.setAmount(f);
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(f1);
				list.add(info);	
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
		return list;
	}

	public List<invoice> getAll3(int start, int end) {
		List<invoice> list = new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql =  " select a.*,b.iimoney from(select ii.iCaseNo,ii.iSum,it.CreateTime,it."
				+ "customercode,ii.iid from(select SUM(iSum)iSum,iCaseNo,min(iid)iid from "
				+ "(select iCaseNo,iSum,min(iid)as iid  from InvoiceInfo group by iCaseNo,iSum)a "
				+ "group by iCaseNo) ii left join (select CaseNo,customercode,MIN(CreateTime) as "
				+ "CreateTime from itemCase group by CaseNo,customercode) it on it.CaseNo=ii.iCaseNo )a "
				+ "inner  join (select iCaseNo,SUM(iimoney)iimoney  from InvoiceInfo group by iCaseNo) b "
				+ "on a.iCaseNo=b.iCaseNo   where iid>59999 and iid<80000 ";	
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				invoice info = new invoice();
		
				info.setAmount(rs.getDouble("iSum"));
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(rs.getDouble("iimoney"));
				list.add(info);	
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
		return list;
	}

	public List<invoice> getAll4(int start, int end) {
		List<invoice> list = new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql =  " select a.*,b.iimoney from(select ii.iCaseNo,ii.iSum,it.CreateTime,it."
				+ "customercode,ii.iid from(select SUM(iSum)iSum,iCaseNo,min(iid)iid from "
				+ "(select iCaseNo,iSum,min(iid)as iid  from InvoiceInfo group by iCaseNo,iSum)a "
				+ "group by iCaseNo) ii left join (select CaseNo,customercode,MIN(CreateTime) as "
				+ "CreateTime from itemCase group by CaseNo,customercode) it on it.CaseNo=ii.iCaseNo )a "
				+ "inner  join (select iCaseNo,SUM(iimoney)iimoney  from InvoiceInfo group by iCaseNo) b "
				+ "on a.iCaseNo=b.iCaseNo   where iid>79999 and iid<100000 ";
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				invoice info = new invoice();
	
				info.setAmount(rs.getDouble("iSum"));
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(rs.getDouble("iimoney"));
				list.add(info);	
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
		return list;
	}

	public List<invoice> getAll5(int start, int end) {
		List<invoice> list = new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "  select ii.iCaseNo,ii.iSum,it.CreateTime,it.customercode,ii.iid from(select iCaseNo,iSum,min(iid)as iid  from InvoiceInfo group by iCaseNo,iSum) ii left join (select CaseNo"
				+ ",customercode,MIN(CreateTime) as CreateTime from itemCase group by CaseNo,customercode) it on it.CaseNo=ii.iCaseNo where iid>10 and iid<"+end;
			
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				invoice info = new invoice();
			
				info.setAmount(rs.getDouble("iSum"));
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(rs.getDouble("iimoney"));
				list.add(info);	
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
		return list;
	}

	@Override
	public invoice getAll(String projectId) {
		invoice info = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "  select a.*,b.ifmoney from(select ii.iCaseNo,ii.iimoney,"
				+ "it.CreateTime,it.customercode,ii.iid,it.CustomerManager,it.MerchandManager1"
				+ " from(select SUM(iimoney)iimoney,iCaseNo,min(iid)iid from "
				+ " InvoiceInfo group by iCaseNo) ii left join (select CaseNo,"
				+ "customercode,MIN(CreateTime) as CreateTime,MIN(CustomerManager)CustomerManager"
				+ ",MIN(MerchandManager1)MerchandManager1 from itemCase group by CaseNo,customercode) "
				+ "it on it.CaseNo=ii.iCaseNo )a inner  join (select iCaseNo,SUM(ifmoney)ifmoney"
				+ "  from InvoiceInfo group by iCaseNo) b on a.iCaseNo=b.iCaseNo  where a.iCaseNo=?";
				
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,projectId );
			rs = stmt.executeQuery();
			while(rs.next()) {
				 info = new invoice();
				info.setAmount(rs.getDouble("iimoney"));
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(rs.getDouble("ifmoney"));
				info.setCustomerManager(rs.getString("CustomerManager"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
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
		return info;
	}

	@Override
	public List<Invoiceinfo1> getall() {
		List<Invoiceinfo1> list = new ArrayList<Invoiceinfo1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select info.iid,info.iInvNo,info.iCaseNo,info.iSum,info.ifmoney,info.iidate,info.ifdate,info.icreatedate,info.iimoney,info.imoneytype,it.customercode,it.CustomerManager from InvoiceInfo  "
				+ "info left join itemCase it on it.CaseNo=info.iCaseNo where datediff(day,info.updatetime,getdate())<60 and info.iid>34211 and iCaseNo is not null  and customercode is not null and iimoney!=0 ";
			
/*		String sql = "select info.iInvNo,info.iCaseNo,info.iSum,info.ifmoney,info.ifdate,info.icreatedate,info.iimoney,info.imoneytype,it.customercode,it.CustomerManager from InvoiceInfo  "
				+ "info left join itemCase it on it.CaseNo=info.iCaseNo where datediff(day,CreateTime,getdate())<400 and  CaseStatus=0 or datediff(day,CreateTime,getdate())<400 and  CaseStatus=14 ";
		
*/		
		
		try {
			conn = SQLDBhelper.getConnection();
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Invoiceinfo1 info = new Invoiceinfo1();
			    info.setCustomermanager(rs.getString("CustomerManager"));
			    info.setProjectId(rs.getString("iCaseNo").toUpperCase());
			   info.setiSum(rs.getDouble("iSum"));
			    int imoneytype=rs.getInt("imoneytype");
			    if(imoneytype==1){
			    info.setImoneytype(0);
			    }else if(imoneytype==2){
			    	 info.setImoneytype(1);
			    }else if(imoneytype==3){
			    	 info.setImoneytype(2);
			    }else if(imoneytype==5){
			    	 info.setImoneytype(3);	
			    }
			    info.setiInvNo(rs.getString("iInvNo"));
			    info.setIfmoney(rs.getDouble("ifmoney"));
			    info.setIfdate(rs.getString("ifdate"));
			    info.setIcreatedate(rs.getString("icreatedate"));
			    info.setIidate(rs.getString("iidate"));
			    info.setEid(rs.getInt("customercode"));
			    info.setIimoney(rs.getDouble("iimoney"));
			    info.setInvoiceId(rs.getInt("iid"));
				list.add(info);	
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
	public List<invoice> getall1() {
		List<invoice> list =new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		/*String sql = "select a.*,b.ifmoney from(select ii.iCaseNo,ii.iimoney,"
				+ "it.CreateTime,it.customercode,ii.iid,it.CustomerManager,"
				+ "it.MerchandManager1 from(select SUM(iimoney)iimoney,iCaseNo,min(iid)iid from "
				+ "InvoiceInfo group by iCaseNo) ii left join (select CaseNo,customercode,CaseStatus,"
				+ "CreateTime,MIN(CustomerManager)CustomerManager,MIN(MerchandManager1)MerchandManager1"
				+ " from itemCase  where CaseNo='SHS14669'  group by CaseNo,customercode,CreateTime,CaseStatus	) "
				+ "it on it.CaseNo=ii.iCaseNo )a 	left  join (select iCaseNo,SUM(ifmoney)ifmoney"
				+ "  from InvoiceInfo group by iCaseNo) b on a.iCaseNo=b.iCaseNo    ";*/
/*		String sql = "select CaseNo,customercode,CaseStatus,"
				+ "CreateTime,MIN(CustomerManager)CustomerManager,MIN(MerchandManager1)MerchandManager1"
				+ " from itemCase  where CaseNo='SHS14669'  group by CaseNo,customercode,CreateTime,CaseStatus	";*/
		String sql = "select a.*,b.ifmoney from(select it.CaseNo,ii.iimoney,"
				+ "it.CreateTime,it.customercode,ii.iid,it.CustomerManager,"
				+ "it.MerchandManager1 from(select SUM(iimoney)iimoney,iCaseNo,min(iid)iid from "
				+ "InvoiceInfo group by iCaseNo) ii inner join (select CaseNo,customercode,CaseStatus,"
				+ "CreateTime,MIN(CustomerManager)CustomerManager,MIN(MerchandManager1)MerchandManager1"
				+ " from itemCase    "
				+ "  group by CaseNo,customercode,CreateTime,CaseStatus	) "
				+ "it on it.CaseNo=ii.iCaseNo )a 	inner  join (select iCaseNo,SUM(ifmoney)ifmoney"
				+ "  from InvoiceInfo group by iCaseNo) b on a.CaseNo=b.iCaseNo    ";
				
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				invoice info = new invoice();
				info.setAmount(rs.getDouble("iimoney"));
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("CaseNo").toUpperCase());
				info.setSumacount(rs.getDouble("ifmoney"));
				info.setCustomerManager(rs.getString("CustomerManager"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				list.add(info);
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
		return list;
	}

	@Override
	public int add(Invoiceinfo1 info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into InvoiceInfo(iCaseNo,iInvNo,iSum,imoneytype,"
				+ "iimoney,iidate,icreatedate,icasetype,ibank,comfrom) values(?,?,?,?,?,?,getdate(),?,?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null; //初始化date
			try {
				date = formatter.parse(info.getIfdate()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, info.getProjectId());
			stmt.setString(2, info.getiInvNo());
			stmt.setDouble(3, info.getiSum());
			stmt.setInt(4, info.getImoneytype());
			stmt.setDouble(5, info.getIimoney());
			stmt.setDate(6, new java.sql.Date(date.getTime()));
			stmt.setString(7, "老客户 Old");
			stmt.setInt(8,info.getIbank() );
			stmt.setInt(9,1 );
			
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
	public List<invoice> getAll1(int eid) {
		List<invoice> list = new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select a.iCaseNo,it.customercode,it.CreateTime from "
				+ "(select ii.iCaseNo  from InvoiceInfo ii group by iCaseNo)a "
				+ "left join itemCase it on it.CaseNo=a.iCaseNo ";
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				String  CaseNo=rs.getString("iCaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				
				rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					String	date=rs1.getString("iidate");
					if(date!=null&&!"".equals(date)){
					imoneytype1=rs1.getInt("imoneytype");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Calendar cal = Calendar.getInstance(); 
					    Date dt = null; 
					    try { 
					      dt = sdf.parse(date); 
					      cal.setTime(dt); 
					    } catch (Exception e) { 
					      e.printStackTrace(); 
					    }
					    double huilv=0.0;
					    double huilv1=0.0;
					    int year = cal.get(Calendar.YEAR); 
					    int month = cal.get(Calendar.MONTH) + 1; 
						
						String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						conn2 = SQLDBhelper.getConnection();
						try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setInt(1, year);
						stmt2.setInt(2, month);
						stmt2.setInt(3, imoneytype1);
						rs2 = stmt2.executeQuery();
							if(rs2.next()) {
								huilv=rs2.getDouble(1);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (rs2 != null) {
								try {
									rs2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (stmt2 != null) {
								try {
									stmt2.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.returnConnection(conn2);
						}
						if(imoneytype1==1){
							ifmoney+=rs1.getDouble("ifmoney");
							iimoney+=rs1.getDouble("iimoney");
						}else if(imoneytype1==2){
							ifmoney+=rs1.getDouble("ifmoney");
							iimoney+=rs1.getDouble("iimoney");
						}else{
							  String sql3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
								Connection conn3 = null;
								PreparedStatement stmt3 = null;
								ResultSet rs3 = null;
								conn3 = SQLDBhelper.getConnection();
								try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setInt(1, year);
								stmt3.setInt(2, month);
								stmt3.setInt(3, 1);
								rs3 = stmt3.executeQuery();
									if(rs3.next()) {
										huilv1=rs3.getDouble(1);
									}
									
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (rs3 != null) {
										try {
											rs3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									if (stmt3 != null) {
										try {
											stmt3.close();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
									SQLDBhelper.returnConnection(conn3);
								}
							ifmoney+=(rs1.getDouble("ifmoney")/huilv1)*huilv;
							iimoney+=(rs1.getDouble("iimoney")/huilv1)*huilv;
							
						}
						
					}
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
					SQLDBhelper.returnConnection(conn1);
				}
				
				if(iimoney!=0){  
				invoice info = new invoice();
				info.setAmount(iimoney);
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(ifmoney);
				list.add(info);	
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
			//DBHelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public List<Invoiceinfo1> getall1(int eid) {
		List<Invoiceinfo1> list = new ArrayList<Invoiceinfo1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		/*String sql = "select info.iInvNo,info.iCaseNo,info.iSum,info.ifmoney,info.ifdate,info.icreatedate,info.iimoney,info.imoneytype,it.customercode,it.CustomerManager from InvoiceInfo  "
				+ "info left join itemCase it on it.CaseNo=info.iCaseNo where it.customercode in (87085,97814,97470) ";*/
		String sql = "select info.iInvNo,info.iCaseNo,info.iSum,info.ifmoney,info.ifdate,info.icreatedate,info.iimoney,info.imoneytype,it.customercode,it.CustomerManager from InvoiceInfo  "
				+ "info left join itemCase it on it.CaseNo=info.iCaseNo where it.customercode in (109288,97777,108398,97277) ";
			
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Invoiceinfo1 info = new Invoiceinfo1();
			    info.setCustomermanager(rs.getString("CustomerManager"));
			    info.setProjectId(rs.getString("iCaseNo"));
			   info.setiSum(rs.getDouble("iSum"));
			    int imoneytype=rs.getInt("imoneytype");
			    if(imoneytype==1){
			    info.setImoneytype(0);
			    }else if(imoneytype==2){
			    	 info.setImoneytype(1);
			    }else if(imoneytype==3){
			    	 info.setImoneytype(2);
			    }else if(imoneytype==5){
			    	 info.setImoneytype(3);	
			    }
			    info.setiInvNo(rs.getString("iInvNo"));
			    info.setIfmoney(rs.getDouble("ifmoney"));
			    info.setIfdate(rs.getString("ifdate"));
			    //info.setIcreatedate(rs.getString("icreatedate"));
			    info.setEid(rs.getInt("customercode"));
			    info.setIimoney(rs.getDouble("iimoney"));
				list.add(info);	
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
	public List<invoice> getalla(int eid) {
		List<invoice> list =new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select it.CaseNo,it.MerchandManager1,"
				+ "it.CustomerManager,II.iid,II.ifmoney,ii.iimoney,"
				+ "it.CreateTime,it.customercode from	itemCase it "
				+ "left join(select SUM(iimoney)iimoney,iCaseNo,min(iid)iid,SUM(ifmoney)ifmoney "
				+ "from InvoiceInfo group by iCaseNo) ii on it.CaseNo=ii.iCaseNo  where  customercode in (108926,16639)	and iimoney is not null	  ";
		/*String sql = "select a.*,b.ifmoney from(select ii.iCaseNo,ii.iimoney,"
				+ "it.CreateTime,it.customercode,ii.iid,it.CustomerManager,"
				+ "it.MerchandManager1 from(select SUM(iimoney)iimoney,iCaseNo,min(iid)iid from "
				+ "InvoiceInfo group by iCaseNo) ii inner join (select CaseNo,customercode,CaseStatus,"
				+ "CreateTime,MIN(CustomerManager)CustomerManager,MIN(MerchandManager1)MerchandManager1"
				+ " from itemCase  where customercode in (86933,1121,5825,5067,5100,4422,87085,97814,97470,"
				+ "3492,1085,127,2402,5760,119587,4165,3258,97644,109288,97777,108398,1085,4982,2382) "
				+ " group by CaseNo,customercode,CreateTime,CaseStatus	) "
				+ "it on it.CaseNo=ii.iCaseNo )a 	inner  join (select iCaseNo,SUM(ifmoney)ifmoney"
				+ "  from InvoiceInfo group by iCaseNo) b on a.iCaseNo=b.iCaseNo    ";*/
				
		conn = SQLDBhelper.getConnection();
		
		try {
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				invoice info = new invoice();
				info.setAmount(rs.getDouble("iimoney"));
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("CaseNo"));
				info.setSumacount(rs.getDouble("ifmoney"));
				info.setCustomerManager(rs.getString("CustomerManager"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				list.add(info);
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
		return list;
	}

	@Override
	public List<Invoiceinfo1> getall2() {
		List<Invoiceinfo1> list = new ArrayList<Invoiceinfo1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select info.updatetime,info.iInvNo,info.iCaseNo,info.iSum,info.ifmoney,info.ifdate,info.icreatedate,info.iimoney,info.imoneytype,it.customercode,it.CustomerManager from InvoiceInfo  "
				+ "info left join itemCase it on it.CaseNo=info.iCaseNo where datediff(day,info.updatetime,getdate())<1  ";
			
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				Date date = new Date(); 
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String time=format.format(date);
				Long millionSeconds1=format.parse(time).getTime();
				Long millionSeconds2=millionSeconds1-1800000;
				String updatetime=rs.getString("updatetime");
				Long millionSeconds3=format.parse(updatetime).getTime(); 
				if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
				Invoiceinfo1 info = new Invoiceinfo1();
			    info.setCustomermanager(rs.getString("CustomerManager"));
			    info.setProjectId(rs.getString("iCaseNo"));
			    info.setiSum(rs.getDouble("iSum"));
			    int imoneytype=rs.getInt("imoneytype");
			    if(imoneytype==1){
			    info.setImoneytype(0);
			    }else if(imoneytype==2){
			    	 info.setImoneytype(1);
			    }else if(imoneytype==3){
			    	 info.setImoneytype(2);
			    }else if(imoneytype==5){
			    	 info.setImoneytype(3);	
			    }
			    info.setiInvNo(rs.getString("iInvNo"));
			    info.setIfmoney(rs.getDouble("ifmoney"));
			    info.setIfdate(rs.getString("ifdate"));
			    //info.setIcreatedate(rs.getString("icreatedate"));
			    info.setEid(rs.getInt("customercode"));
			    info.setIimoney(rs.getDouble("iimoney"));
				list.add(info);	
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
	public List<invoice> getalla2() {
		List<invoice> list =new ArrayList<invoice>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from (select CaseNo,customercode,CaseStatus,CreateTime,MIN(CustomerManager)CustomerManager"
				+ ",MIN(MerchandManager1)MerchandManager1,UpdateTime from itemCase  where  datediff(day,UpdateTime,getdate())<1 "
				+ "or datediff(day,CreateTime,getdate())<1 group by CaseNo,customercode,CreateTime,CaseStatus,"
				+ "UpdateTime) a left join ( select SUM(ifmoney)ifmoney,SUM(iimoney)iimoney,iCaseNo "
				+ "from  InvoiceInfo  group by iCaseNo)b on a.CaseNo=b.iCaseNo    ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Date date = new Date(); 
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				Long millionSeconds1=format.parse(time).getTime();
				Long millionSeconds2=millionSeconds1-1800000;
				String updatetime=rs.getString("CreateTime");
				String updatetime1=rs.getString("UpdateTime");
				Long millionSeconds3=format.parse(updatetime).getTime(); 
				Long millionSeconds4=format.parse(updatetime1).getTime(); 
				if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1||millionSeconds4>millionSeconds2&&millionSeconds4<millionSeconds1){
				invoice info = new invoice();
				info.setAmount(rs.getDouble("iimoney"));
				info.setCreatetime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectid(rs.getString("iCaseNo"));
				info.setSumacount(rs.getDouble("ifmoney"));
				info.setCustomerManager(rs.getString("CustomerManager"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				list.add(info);
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
			//DBHelper.returnConnection(conn);
		}
		return list;
		
	}


	@Override
	public int updateOutstandingNotes(int id, String outstandingNotes,
			int reason1,String name) {
		String sql = "update invoiceinfo set outstandingNotes=?,reason=?,uploads=? where iid = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,outstandingNotes );
			stmt.setInt(2, reason1);
			stmt.setString(3, name);
			stmt.setInt(4, id);
			
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
	public int updateAll(int iid, String projectFinished, double money,
			String reason, String explain) {
		int sort=0;
		if("列入坏账".equalsIgnoreCase(projectFinished)){
			sort=2;
		}else if("预计无法收回全部".equalsIgnoreCase(projectFinished)){
			sort=3;
		}else if("去掉质量扣款后完结".equalsIgnoreCase(projectFinished)){
			sort=4;
		}else if("正常完结".equalsIgnoreCase(projectFinished)){
			sort=5;
		}else if("invoice上传错误改零".equalsIgnoreCase(projectFinished)){
			sort=6;
		}
		
		Date date = new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		String time=df.format(date);
		String sql = "update invoiceinfo set AmountMoney=?,QualityDeductions=?,ReasonsWithholding=?,explain=?,createTime='"+time+"'";
			if(sort!=0){
		    sql+= " ,sort=? ";
			}
				 sql+= " where iid = ?";
		Connection conn = null;
		ResultSet rs = null;
		int i=0;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,projectFinished );
			stmt.setDouble(2, money);
			stmt.setString(3, reason);
			stmt.setString(4, explain);
			if(sort!=0){
				i++;
			stmt.setInt(i+4, sort);   
				}
			stmt.setInt(i+5, iid);
			
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
	public List<Invoiceinfo1> detailsOfNewCustomerArrival(ItemCase it) {
    	
    	List<Invoiceinfo1> list =new ArrayList<Invoiceinfo1>();
    	Invoiceinfo1 info=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "   select cus.id,cus.name,it.CaseNo from customer cus left join itemcase it on it.customercode=cus.id where it.caseno in("
  +" select iCaseNo from invoiceinfo where datediff(day,ifdate,'"+it.getStartTime()+"')<0 "
  +" and  iCaseNo not in ( select caseno from itemcase where customercode in( "
  +" select it.customercode from invoiceinfo ii left join itemcase it on it.caseno=ii.icaseno where datediff(day,ifdate,'"+it.getStartTime()+"')>0)) and icasetype!='老客户 Old'"
 +" group by iCaseNo) ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoneym=0.0;
				double totalrmbm=0.0;
				double totalSalesUs=0.0;
				double actualArrival=0.0;
				double payother=0.0;
				double paywulliu=0.0;
				double payfactory=0.0;
				String CaseNo=rs.getString("CaseNo");
				info=new Invoiceinfo1();
				info.setProjectId(CaseNo);
				info.setName(rs.getString("name"));
				info.setEid(rs.getInt("id"));
				String sqlm1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?    ";
				Connection connm1 = null;
				PreparedStatement stmtm1 = null;
				ResultSet rsm1 = null;
				connm1 = SQLDBhelper.getConnection();
				try {
				stmtm1 = connm1.prepareStatement(sqlm1);
				stmtm1.setString(1, CaseNo);
				rsm1 = stmtm1.executeQuery();
					while(rsm1.next()) {
						String	date=rsm1.getString("iidate");
						String ifdate1=rsm1.getString("ifdate");
						String allmonth="";
						int imoneytype1=rsm1.getInt("imoneytype");
						double huilv=0.0;
						double huilv1=0.0;
						if(ifdate1!=null&&!"".equals(ifdate1)){
						huilv=getHuilva(ifdate1,imoneytype1);
						}else{
							huilv=getHuilva(date,imoneytype1);	
						}
						if(ifdate1!=null&&!"".equals(ifdate1)){
							huilv1=getHuilva(ifdate1,1);
							}else{
								huilv1=getHuilva(date,1);	
							}
							if(imoneytype1==1){
								Double money=rsm1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
								ifmoneym+=rsm1.getDouble("ifmoney");
								totalrmbm+=rsm1.getDouble("ifmoney")*huilv;
								totalSalesUs+=rsm1.getDouble("ifmoney");
								actualArrival+=rsm1.getDouble("ifmoney");
								}else{
								ifmoneym+=rsm1.getDouble("iimoney");
								totalrmbm+=rsm1.getDouble("iimoney")*huilv;
								totalSalesUs+=rsm1.getDouble("iimoney");
								}
								
							}else if(imoneytype1==2){
								Double money=rsm1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
								ifmoneym+=rsm1.getDouble("ifmoney")/huilv;
								totalrmbm+=rsm1.getDouble("ifmoney");
								totalSalesUs+=rsm1.getDouble("ifmoney")/huilv1;
								actualArrival+=rsm1.getDouble("ifmoney")/huilv1;
								}else{
									ifmoneym+=rsm1.getDouble("iimoney")/huilv;
									totalrmbm+=rsm1.getDouble("iimoney");
									totalSalesUs+=rsm1.getDouble("iimoney")/huilv1;
								}
							}else{
								  
									Double money=rsm1.getDouble("ifmoney");
									if(money!=null&&!"".equals(money)&&money!=0){
								ifmoneym+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
								totalrmbm+=rsm1.getDouble("ifmoney")*huilv;
								totalSalesUs+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
								actualArrival+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
									}else{
								ifmoneym+=(rsm1.getDouble("iimoney")/huilv1)*huilv;
								totalrmbm+=rsm1.getDouble("iimoney")*huilv;
								totalSalesUs+=(rsm1.getDouble("iimoney")/huilv1)*huilv;
									}
								
							}
							
						}
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rsm1 != null) {
							try {
								rsm1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmtm1 != null) {
							try {
								stmtm1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.returnConnection(connm1);
					}
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				String sql3 = "select otherMoney,moneytype,Convert(varchar(100),OtherDate,23)date from factoryfund  where CaseNo=?  and otherMoney is not null";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setString(1, CaseNo);
					rs3= stmt3.executeQuery();
					while(rs3.next()) {
						int type=rs3.getInt("moneytype");
						String date =rs3.getString("date");
						
						if(type==2){
						payother += rs3.getDouble("otherMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
						payother += rs3.getDouble("otherMoney")*huilva;	
						}
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
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				String sql4 = "select wuliuMoney,moneytype,Convert(varchar(100),WuliuDate,23)date from 	wuliu  where CaseNo=? ";
				conn4 = SQLDBhelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					stmt4.setString(1, CaseNo);
					rs4= stmt4.executeQuery();
					while(rs4.next()) {
						int type=rs4.getInt("moneytype");
						String date =rs4.getString("date");
						
						if(type==2){
							paywulliu += rs4.getDouble("wuliuMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
							paywulliu += rs4.getDouble("wuliuMoney")*huilva;	
						}
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
					
				}
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select friMoney,moneytype,Convert(varchar(100),friFacDate,23)date from factoryfund  where CaseNo=? and bargainno is not null";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, CaseNo);
					rs2= stmt2.executeQuery();
					while(rs2.next()) {
						int type=rs2.getInt("moneytype");
						String date =rs2.getString("date");
						if(type==2){
							payfactory += rs2.getDouble("friMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
							payfactory += rs2.getDouble("friMoney")*huilva;	
						}
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
					
				}
				
				Connection conn6 = null;
				PreparedStatement stmt6 = null;
				ResultSet rs6 = null;
				String sql6 = "select Convert(varchar,ifdate,120)ifdate,icaseno,MerchandManager1 from (select min(ifdate)ifdate,icaseno from invoiceinfo  where ifmoney is not null and icaseno=? group by icaseno)a left join itemcase it on a.icaseno=it.caseno";
				conn6 = SQLDBhelper.getConnection();
				try {
					stmt6 = conn6.prepareStatement(sql6);
					stmt6.setString(1, CaseNo);
					rs6= stmt6.executeQuery();
					if(rs6.next()) {
						String ifdate=rs6.getString("ifdate"); 
						info.setIfdate(ifdate);
						info.setMerchandmanager1(rs6.getString("MerchandManager1"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt6 != null) {
						try {
							stmt6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs6 != null) {
						try {
							rs6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn6,stmt6,rs6);
					
				}
				
				double payall=payother+paywulliu+payfactory;
				double grossProfit=totalrmbm-payall;
				double grossProfitMargin=0.0;
				if(totalrmbm!=0&&!"".equals(totalrmbm)){
				 grossProfitMargin=grossProfit/totalrmbm;
				}
				
				info.setTotalRevenueDollars(totalSalesUs);
				info.setTotalProceedsRMB(totalrmbm);
				info.setAccountsPayable(payall);
				info.setGrossProfit(grossProfit);
				info.setGrossProfitMargin(grossProfitMargin);
				info.setIfmoney(actualArrival);
				
				list.add(info);
				
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
		return list;
	}
    private double getHuilva(String date, int type) {
		double huilv=0.00;
		String[]dates=date.split("-");
		
		int year=Integer.parseInt(dates[0]);
		int month=Integer.parseInt(dates[1]);
		String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
		Connection conn2 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		conn2 = SQLDBhelper.getConnection();
		try {
		stmt2 = conn2.prepareStatement(sql2);
		stmt2.setInt(1, year);
		stmt2.setInt(2, month);
		stmt2.setInt(3, type);
		rs2 = stmt2.executeQuery();
			if(rs2.next()) {
				huilv=rs2.getDouble(1);
				if(huilv==0){
					huilv=1;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return huilv;
	}



	@Override
	public List<Invoiceinfo1> getall(ItemCase it) {
		List<Invoiceinfo1> list =new ArrayList<Invoiceinfo1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select icaseno,a.id,name,ifmoney,ifmoney1,it.CustomerManager,it.MerchandManager1,imoneytype,imoneytype1,countryid from ("
   +" select isnull(a.icaseno,b.icaseno)icaseno,cus.id,cus.name,isnull(a.ifmoney,0)ifmoney,isnull(b.ifmoney1,0)ifmoney1,a.imoneytype,b.imoneytype1,cus.countryid from customer cus"
   + "  left join itemcase it  on it.customercode=cus.id left join ("
   +" select it.customercode,min(iCaseNo)icaseno,sum(ifmoney)ifmoney,min(imoneytype)imoneytype from invoiceinfo info left join "
   + "itemcase it on it.caseno=info.iCaseNo   where datediff(day,'"
   + it.getStartTime()
   + "',ifdate)>0 and "
   + "datediff(day,'"
   + it.getEndTime()
   + "',ifdate)<0 and ifmoney is not null and ifmoney !=0 group by it.customercode"
   +" ) a on a.customercode=cus.id"
   +" left join ("
   +" select it.customercode,min(iCaseNo)icaseno,sum(ifmoney)ifmoney1,min(imoneytype)imoneytype1 from invoiceinfo info"
   + " left join itemcase it on it.caseno=info.iCaseNo   where datediff(day,'"
   + it.getStartTime1()
   + "',ifdate)>0 "
   + "and datediff(day,'"
   + it.getEndTime1()
   + "',ifdate)<0 and ifmoney is not null and ifmoney !=0 group by it.customercode"
   +" ) b on b.customercode=cus.id where (a.icaseno is not   null or b.iCaseNo is not  null) ";
		if(it.getCusName()!=null&&!"".equalsIgnoreCase(it.getCusName())){
		sql+=" and cus.name like ? ";	
		}
		if(it.getCid()!=0){
			sql+=" and cus.id =? ";	
		}
		conn = SQLDBhelper.getConnection();
		try {
			sql+=" )a left join itemcase it on it.caseno=a.icaseno ";
			if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
			sql+=" where (it.CustomerManager like ? or it.MerchandManager1 like ? or it.Merchandising like ?)";
			}
			sql+=" group by icaseno,a.id,name,ifmoney,ifmoney1,CustomerManager,MerchandManager1,imoneytype,imoneytype1,countryid ";
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(it.getCusName()!=null&&!"".equalsIgnoreCase(it.getCusName())){
				i++;
				stmt.setString(i, "%"+it.getCusName()+"%");
			}
			if(it.getCid()!=0){
				i++;
				stmt.setInt(i, it.getCid());
			}
			if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
				i++;
				stmt.setString(i, it.getCustomerManager());
				i++;
				stmt.setString(i, it.getCustomerManager());
				i++;
				stmt.setString(i, it.getCustomerManager());
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				Invoiceinfo1 info=new Invoiceinfo1();
				info.setEid(rs.getInt("id"));
				info.setName(rs.getString("name"));
				info.setProjectId(rs.getString("icaseno"));
				info.setFirstEntry(rs.getDouble("ifmoney"));
				info.setSecondEntry(rs.getDouble("ifmoney1"));
				info.setCustomermanager(rs.getString("customermanager"));
				info.setMerchandmanager1(rs.getString("merchandManager1"));
				info.setCurrencyUnit1(rs.getInt("imoneytype"));
				info.setCurrencyUnit2(rs.getInt("imoneytype1"));
				info.setCountryid(rs.getInt("countryid"));
				list.add(info);
				
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
	public List<Invoiceinfo1> getDocumentaryToAccountPage(ItemCase it) {
		List<Invoiceinfo1> list =new ArrayList<Invoiceinfo1>();
    	Invoiceinfo1 info=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "   select cus.id,cus.name,it.CaseNo from customer cus left join itemcase it on it.customercode=cus.id where it.caseno in("
  +" select iCaseNo from invoiceinfo where datediff(day,ifdate,'"+it.getStartTime()+"')<0 and datediff(day,ifdate,'"+it.getEndTime()+"')>0 )"
  +"  ";
		if(it.getMerchandManager1()!=null&&!"".equals(it.getMerchandManager1())){
			sql+=" and (it.MerchandManager1 like ? or it.MerchandManager2 like ? or it.CustomerManager like ? ) ";
		}
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			if(it.getMerchandManager1()!=null&&!"".equals(it.getMerchandManager1())){
				stmt.setString(1, "%"+it.getMerchandManager1()+"%");
				stmt.setString(2, "%"+it.getMerchandManager1()+"%");
				stmt.setString(3, "%"+it.getMerchandManager1()+"%");
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoneym=0.0;
				double totalrmbm=0.0;
				double totalrmbm1=0.0;
				double totalSalesUs=0.0;
				double actualArrival=0.0;
				double payother=0.0;
				double paywulliu=0.0;
				double payfactory=0.0;
				String CaseNo=rs.getString("CaseNo");
				info=new Invoiceinfo1();
				info.setProjectId(CaseNo);
				info.setName(rs.getString("name"));
				info.setEid(rs.getInt("id"));
				
				String sql1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=? and    datediff(day,ifdate,'"+it.getStartTime()+"')<0 and datediff(day,ifdate,'"+it.getEndTime()+"')>0 ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String	date=rs1.getString("iidate");
						String ifdate1=rs1.getString("ifdate");
						String allmonth="";
						int imoneytype1=rs1.getInt("imoneytype");
						double huilv=0.0;
						double huilv1=0.0;
						if(ifdate1!=null&&!"".equals(ifdate1)){
						huilv=getHuilva(ifdate1,imoneytype1);
						}else{
							huilv=getHuilva(date,imoneytype1);	
						}
						if(ifdate1!=null&&!"".equals(ifdate1)){
							huilv1=getHuilva(ifdate1,1);
							}else{
								huilv1=getHuilva(date,1);	
							}
							if(imoneytype1==1){
								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
								totalrmbm1+=rs1.getDouble("ifmoney")*huilv;
								}else{
								totalrmbm1+=rs1.getDouble("iimoney")*huilv;
								}
							}else if(imoneytype1==2){
								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
								totalrmbm1+=rs1.getDouble("ifmoney");
								}else{
								totalrmbm1+=rs1.getDouble("iimoney");
								}
							}else{
								 Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
							    totalrmbm1+=rs1.getDouble("ifmoney")*huilv;
								}else{
								totalrmbm1+=rs1.getDouble("iimoney")*huilv;
								}
								
							}
							
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
						SQLDBhelper.returnConnection(conn1);
					}
				
				String sqlm1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?    ";
				Connection connm1 = null;
				PreparedStatement stmtm1 = null;
				ResultSet rsm1 = null;
				connm1 = SQLDBhelper.getConnection();
				try {
				stmtm1 = connm1.prepareStatement(sqlm1);
				stmtm1.setString(1, CaseNo);
				rsm1 = stmtm1.executeQuery();
					while(rsm1.next()) {
						String	date=rsm1.getString("iidate");
						String ifdate1=rsm1.getString("ifdate");
						String allmonth="";
						int imoneytype1=rsm1.getInt("imoneytype");
						double huilv=0.0;
						double huilv1=0.0;
						if(ifdate1!=null&&!"".equals(ifdate1)){
						huilv=getHuilva(ifdate1,imoneytype1);
						}else{
							huilv=getHuilva(date,imoneytype1);	
						}
						if(ifdate1!=null&&!"".equals(ifdate1)){
							huilv1=getHuilva(ifdate1,1);
							}else{
								huilv1=getHuilva(date,1);	
							}
							if(imoneytype1==1){
								Double money=rsm1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
								ifmoneym+=rsm1.getDouble("ifmoney");
								totalrmbm+=rsm1.getDouble("ifmoney")*huilv;
								totalSalesUs+=rsm1.getDouble("ifmoney");
								actualArrival+=rsm1.getDouble("ifmoney");
								}else{
								ifmoneym+=rsm1.getDouble("iimoney");
								totalrmbm+=rsm1.getDouble("iimoney")*huilv;
								totalSalesUs+=rsm1.getDouble("iimoney");
								}
								
							}else if(imoneytype1==2){
								Double money=rsm1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
								ifmoneym+=rsm1.getDouble("ifmoney")/huilv;
								totalrmbm+=rsm1.getDouble("ifmoney");
								totalSalesUs+=rsm1.getDouble("ifmoney")/huilv1;
								actualArrival+=rsm1.getDouble("ifmoney")/huilv1;
								}else{
									ifmoneym+=rsm1.getDouble("iimoney")/huilv;
									totalrmbm+=rsm1.getDouble("iimoney");
									totalSalesUs+=rsm1.getDouble("iimoney")/huilv1;
								}
							}else{
								  
									Double money=rsm1.getDouble("ifmoney");
									if(money!=null&&!"".equals(money)&&money!=0){
								ifmoneym+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
								totalrmbm+=rsm1.getDouble("ifmoney")*huilv;
								totalSalesUs+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
								actualArrival+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
									}else{
								ifmoneym+=(rsm1.getDouble("iimoney")/huilv1)*huilv;
								totalrmbm+=rsm1.getDouble("iimoney")*huilv;
								totalSalesUs+=(rsm1.getDouble("iimoney")/huilv1)*huilv;
									}
								
							}
							
						}
						
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rsm1 != null) {
							try {
								rsm1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmtm1 != null) {
							try {
								stmtm1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.returnConnection(connm1);
					}
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				String sql3 = "select otherMoney,moneytype,Convert(varchar(100),OtherDate,23)date from factoryfund  where CaseNo=?  and otherMoney is not null";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setString(1, CaseNo);
					rs3= stmt3.executeQuery();
					while(rs3.next()) {
						int type=rs3.getInt("moneytype");
						String date =rs3.getString("date");
						
						if(type==2){
						payother += rs3.getDouble("otherMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
						payother += rs3.getDouble("otherMoney")*huilva;	
						}
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
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				String sql4 = "select wuliuMoney,moneytype,Convert(varchar(100),WuliuDate,23)date from 	wuliu  where CaseNo=? ";
				conn4 = SQLDBhelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					stmt4.setString(1, CaseNo);
					rs4= stmt4.executeQuery();
					while(rs4.next()) {
						int type=rs4.getInt("moneytype");
						String date =rs4.getString("date");
						
						if(type==2){
							paywulliu += rs4.getDouble("wuliuMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
							paywulliu += rs4.getDouble("wuliuMoney")*huilva;	
						}
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
					
				}
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select friMoney,moneytype,Convert(varchar(100),friFacDate,23)date from factoryfund  where CaseNo=? and bargainno is not null";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, CaseNo);
					rs2= stmt2.executeQuery();
					while(rs2.next()) {
						int type=rs2.getInt("moneytype");
						String date =rs2.getString("date");
						if(type==2){
							payfactory += rs2.getDouble("friMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
							payfactory += rs2.getDouble("friMoney")*huilva;	
						}
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
					
				}
				
				Connection conn6 = null;
				PreparedStatement stmt6 = null;
				ResultSet rs6 = null;
				String sql6 = "select Convert(varchar,ifdate,120)ifdate,icaseno,MerchandManager1 from (select min(ifdate)ifdate,icaseno from invoiceinfo  where ifmoney is not null and icaseno=? group by icaseno)a left join itemcase it on a.icaseno=it.caseno";
				conn6 = SQLDBhelper.getConnection();
				try {
					stmt6 = conn6.prepareStatement(sql6);
					stmt6.setString(1, CaseNo);
					rs6= stmt6.executeQuery();
					if(rs6.next()) {
						String ifdate=rs6.getString("ifdate"); 
						info.setIfdate(ifdate);
						info.setMerchandmanager1(rs6.getString("MerchandManager1"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt6 != null) {
						try {
							stmt6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs6 != null) {
						try {
							rs6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn6,stmt6,rs6);
					
				}
				
				double payall=payother+paywulliu+payfactory;
				double grossProfit=totalrmbm-payall;
				double queryTimeProfit=(totalrmbm1/totalrmbm)*grossProfit;
				
				info.setTotalRevenueDollars(totalSalesUs);
				info.setTotalProceedsRMB(totalrmbm);
				info.setAccountsPayable(payall);
				info.setGrossProfit(grossProfit);
				info.setQueryTimeProfit(queryTimeProfit);
				info.setIfmoney(actualArrival);
				list.add(info);
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
		return list;
	}

	@Override
	public int updateMonth(String time) {
		String time1=time+"-01 00:01:00";
		String sql = "update shipping_bill_month set waybill_month=?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,time1 );
			
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
	public int deleteHistoricalWaybillData() {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from shipment_bill_data   ";
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
	public int insertHistoricalWaybillData(ShippingBillData data) {
		String date=data.getDate()+" 00:00:01";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into shipment_bill_data(amount_customs_declaration,bargainNo,factoryName,caseno,date) "
				+ " select "+data.getAmountCustomsDeclaration()+",'"+data.getBargainNo()+"',factoryname,caseno,'"+date+"' from "
				+ "  ( select info.FactoryName,fu.CaseNo from factoryfund fu left join factoryinfo info on fu.fid=info.id where BargainNo='"+data.getBargainNo()+"' group by info.FactoryName,fu.CaseNo)a";
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
	public List<MonthlyProfitStatement> getMonthlyProfitStatement(String time, String time1) {
		List<MonthlyProfitStatement>list=new ArrayList<MonthlyProfitStatement>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select a.income,a.name,a.profit,a.time,isnull(b.income,0)income1 ,isnull(b.profit,0)profit1,isnull(b.time,'')time1 from (select * from monthly_profit_statement mon1 where time=?)a" +
				"   left join (select * from monthly_profit_statement mon1 where time=?)b" +
				"   on a.name=b.name";
		conn = SQLDBhelper.getConnection();
		try {
            stmt = conn.prepareStatement(sql);
			stmt.setString(1,time );
			stmt.setString(2,time1 );
			rs = stmt.executeQuery();
			while(rs.next()) {
				MonthlyProfitStatement monthy=new MonthlyProfitStatement();
				monthy.setIncome(rs.getDouble("income"));
				monthy.setTime(rs.getString("time"));
				monthy.setName(rs.getString("name"));
				monthy.setProfit(rs.getDouble("profit"));
				monthy.setProfit1(rs.getDouble("profit1"));
				monthy.setIncome1(rs.getDouble("income1"));
				monthy.setTime1(rs.getString("time1"));
				list.add(monthy);
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
    public MonthlyProfitStatement getOne(String userName, String ifdate) {
		MonthlyProfitStatement monthy=new MonthlyProfitStatement();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		double Income=0.00;
		double Profit=0.00;
		int result = 0;
		String sql = " select it.CaseNo,it.GrossProfit,info.ifmoney,info.imoneytype,it.ProfitAndLossAtPresent,info1.ifmoney1 from itemcase it left join (select icaseno,sum(ifmoney)ifmoney,min(imoneytype)imoneytype from invoiceinfo where datepart(yy,ifdate)=datepart(yy,'"+ifdate+"')and datepart(mm,ifdate)=datepart(mm,'"+ifdate+"')  group by icaseno)info"
				+"  on it.caseno=info.iCaseNo   left join (select icaseno,sum(ifmoney1)ifmoney1 from(select icaseno,isnull(ifmoney,iimoney)ifmoney1 from invoiceinfo )a group by icaseno)info1 " +
				"  on it.caseno=info1.iCaseNo" +
				" where info.icaseno is not null and (it.CustomerManager like '%"+userName+"%' or it.MerchandManager1 like '%"+userName+"%'  or it.Merchandising like '%"+userName+"%')";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
              String GrossProfit=rs.getString("GrossProfit");
              double Money=rs.getDouble("ifmoney");
              int imoneytype=rs.getInt("imoneytype");
              double Money1=rs.getDouble("ifmoney1");
              double ProfitAndLossAtPresent=rs.getDouble("ProfitAndLossAtPresent");
              double profit=0.00;
              if(GrossProfit!=null&&!"".equalsIgnoreCase(GrossProfit)){
              	String []gross=GrossProfit.split("%");
				  profit=Double.parseDouble(gross[0]);
              }
              if(imoneytype==1){
				  Income+=Money;
				  Profit+=(profit*Money)/100;
			  }else if(imoneytype==2){
				  Income+=((Money/Money1)*Money)/6.8;
				  Profit+=(profit*(Money/Money1))/(100*6.8);
			  }else if(imoneytype==3){
				  Income+=((Money/Money1)*Money)*1.1138;
				  Profit+=(profit*(Money/Money1)*1.1138)/100;
			  }else if(imoneytype==5){
				  Income+=((Money/Money1)*Money)*1.2612;
				  Profit+=(profit*(Money/Money1)*1.2612)/100;
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
		BigDecimal bg = new BigDecimal(Income);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal bg1 = new BigDecimal(Profit);
		double f2 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		monthy.setIncome(f1);
		monthy.setProfit(f2);
		monthy.setName(userName);
		return monthy;

    }

	@Override
	public void insertAll(List<MonthlyProfitStatement> statelist) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		for(int i=0;i<statelist.size();i++) {
			MonthlyProfitStatement state = statelist.get(i);
			String sql = "insert into monthly_profit_statement(name,time,income,profit) values(?,?,?,?)";
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, state.getName());
				stmt.setString(2, state.getTime());
				stmt.setDouble(3, state.getIncome());
				stmt.setDouble(4, state.getProfit());



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

    @Override
    public MonthlyProfitStatement getLastOne(String time1) {
		MonthlyProfitStatement monthy=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "select * from monthly_profit_statement where time=?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,time1 );
			rs = stmt.executeQuery();
			if(rs.next()) {
				monthy=new MonthlyProfitStatement();
				monthy.setIncome(rs.getDouble("income"));
				monthy.setTime(rs.getString("time"));
				monthy.setName(rs.getString("name"));
				monthy.setProfit(rs.getDouble("profit"));

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
		return monthy;
    }

    @Override
    public void updateAllList(List<MonthlyProfitStatement> statelist) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		for(int i=0;i<statelist.size();i++) {
			MonthlyProfitStatement state = statelist.get(i);
			String sql = "update monthly_profit_statement set income=?,profit=? where name = ? and time=? ";
            conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setDouble(1,state.getIncome() );
				stmt.setDouble(2, state.getProfit());
				stmt.setString(3, state.getName());
				stmt.setString(4, state.getTime());
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
}
