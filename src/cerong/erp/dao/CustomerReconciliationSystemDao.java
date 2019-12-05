package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cerong.erp.entity.CustomerReconciliationSystem;
import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.entity.invoice;
import cerong.erp.jdbc.SQLDBhelper;

public class CustomerReconciliationSystemDao implements  ICustomerReconciliationSystemDaoImpl{

	@Override
	public List<CustomerReconciliationSystem> getAll1(
			CustomerReconciliationSystem cr) {
		List<CustomerReconciliationSystem> list = new ArrayList<CustomerReconciliationSystem>();
		String sql = " select cus.name,cus.id,a.CustomerManager,a.MerchandManager1,"
				+ "a.MerchandManager2 from Customer cus left join(select customercode,MIN(MerchandManager1)MerchandManager1,"
				+ "MIN(CustomerManager)CustomerManager,MIN(MerchandManager2)MerchandManager2 from itemCase group by customercode)a"
				+ " on a.customercode=cus.id where id in (SELECT customercode  FROM itemCase  it  inner join InvoiceInfo "
				+ "info on info.iCaseNo=it.CaseNo  GROUP BY it.customercode)  ";
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		
		if(cr.getCid()!=0) {
			sql+="and cus.id=?";
		}
		if(cr.getName()!= null && !"".equals(cr.getName())) {
			sql+=" and (a.CustomerManager=? or a.MerchandManager1=? or a.MerchandManager2=?)  ";
		}
	           sql+=" order by cus.id desc";
			stmt = conn.prepareStatement(sql);
			if(cr.getCid()!=0) {
				i++;
				stmt.setInt(i, cr.getCid());
			}
			if(cr.getName()!= null && !"".equals(cr.getName())) {
				i++;
				stmt.setString(i, cr.getName());
				stmt.setString(i+1, cr.getName());
				stmt.setString(i+2, cr.getName());
			}
			
		   rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerReconciliationSystem info=new CustomerReconciliationSystem();
				Double allBankBills=0.00;
				Double totalSystemEntry=0.00;
				int cid=rs.getInt("id");
				String name=rs.getString("name");
				String CustomerManager=rs.getString("CustomerManager"); 
				String MerchandManager1=rs.getString("MerchandManager1"); 
				String MerchandManager2=rs.getString("MerchandManager2");
				String s2 = "LynnYuanannazhuElaineShengIvyWuKathyPanSherryZhouminniewuellazhuShirleyYurosegaoThomasChenjennyguoKristinemeiJanezhouRitajiangNataliaLijennyguoRitajiang";
				Boolean index5=false;
				Boolean index6=false;
				Boolean index7=false;
				if(CustomerManager!=null&&!"".equals(CustomerManager)){
					index5 = s2.toLowerCase().contains(CustomerManager.toLowerCase());
					if(index5!=false){
					info.setName(rs.getString("CustomerManager"));
					}else{
						if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
							index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
							if(index6!=false){
								info.setName(rs.getString("MerchandManager1"));
							}else{
								if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
									index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
									if(index7!=false){
										info.setName(rs.getString("MerchandManager2"));
									}	
							}
							}
						}else{
							if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
								index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
								if(index7!=false){
									info.setName(rs.getString("MerchandManager2"));
								}
						}	
					}
					}
				}else{
					if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
						index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
						if(index6!=false){
							info.setName(rs.getString("MerchandManager1"));
						}else{
							if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
								index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
								if(index7!=false){
									info.setName(rs.getString("MerchandManager2"));
								}
							}
						}
					}else{
						if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
							index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
							if(index7!=false){
								info.setName(rs.getString("MerchandManager2"));
							}
					}		
				}
				}
				
				info.setCid(cid);
				info.setCusName(name);
				
				String sql2 = " select CaseNo from itemCase where customercode = ?";
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				conn2 = SQLDBhelper.getConnection();
				try {
			    stmt2 = conn2.prepareStatement(sql2);
				stmt2.setInt(1, cid);
				rs2 = stmt2.executeQuery();
				while(rs2.next()) {
					String CaseNo=rs2.getString("CaseNo");
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					ResultSet rs3 = null;
					String sql3 = "select * from (select SUM(ifmoney)ifmoney,SUM(iimoney)iimoney,iCaseNo from  InvoiceInfo info   group by iCaseNo )a where a.iCaseNo=?    ";
					conn3 = SQLDBhelper.getConnection();
					
					try {
						
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setString(1, CaseNo);
						rs3 = stmt3.executeQuery();
						while(rs3.next()) {
							totalSystemEntry+=rs3.getDouble("iimoney");
							allBankBills+=rs3.getDouble("ifmoney");
							
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
					SQLDBhelper.close(conn2,stmt2,rs2);
				}			
				info.setAllBankBills(allBankBills);
				info.setTotalSystemEntry(totalSystemEntry);
				info.setTotalAmountDue(allBankBills);
				list.add(info);
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
		return list;
	}

	@Override
	public List<CustomerReconciliationSystem> getLookOutstandingFunds(
			CustomerReconciliationSystem cr) {
		List<CustomerReconciliationSystem> list = new ArrayList<CustomerReconciliationSystem>();
		String sql = " select cus.name,cus.id,a.CustomerManager,a.MerchandManager1,"
				+ "a.MerchandManager2,a.CaseNo from Customer cus left join(select CaseNo,customercode,MerchandManager1,"
				+ "CustomerManager,MerchandManager2 from itemCase)a"
				+ " on a.customercode=cus.id where id in (SELECT customercode  FROM itemCase  it  inner join InvoiceInfo "
				+ "info on info.iCaseNo=it.CaseNo  ) and cus.id=? ";
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		
	          
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cr.getCid());
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				
				
					String CaseNo=rs.getString("CaseNo");
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					ResultSet rs3 = null;
					String sql3 = " select iimoney,iCaseNo,ifmoney,iidate from  InvoiceInfo info   where     iimoney!=0 and  iCaseNo=?    ";
					conn3 = SQLDBhelper.getConnection();
					try {
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setString(1, CaseNo);
						rs3 = stmt3.executeQuery();
						while(rs3.next()) {
							CustomerReconciliationSystem info=new CustomerReconciliationSystem();
							int cid=rs.getInt("id");
							String name=rs.getString("name");
							String CustomerManager=rs.getString("CustomerManager"); 
							String MerchandManager1=rs.getString("MerchandManager1"); 
							String MerchandManager2=rs.getString("MerchandManager2");
							String s2 = "LynnYuanannazhuElaineShengIvyWuKathyPanSherryZhouminniewuellazhuShirleyYurosegaoThomasChenjennyguoKristinemeiJanezhouRitajiangNataliaLijennyguoRitajiang";
							Boolean index5=false;
							Boolean index6=false;
							Boolean index7=false;
							if(CustomerManager!=null&&!"".equals(CustomerManager)){
								index5 = s2.toLowerCase().contains(CustomerManager.toLowerCase());
								if(index5!=false){
								info.setName(rs.getString("CustomerManager"));
								}else{
									if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
										index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
										if(index6!=false){
											info.setName(rs.getString("MerchandManager1"));
										}else{
											if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
												index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
												if(index7!=false){
													info.setName(rs.getString("MerchandManager2"));
												}	
										}
										}
									}else{
										if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
											index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
											if(index7!=false){
												info.setName(rs.getString("MerchandManager2"));
											}
									}	
								}
								}
							}else{
								if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
									index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
									if(index6!=false){
										info.setName(rs.getString("MerchandManager1"));
									}else{
										if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
											index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
											if(index7!=false){
												info.setName(rs.getString("MerchandManager2"));
											}
										}
									}
								}else{
									if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
										index7 = s2.toLowerCase().contains(MerchandManager2.toLowerCase());
										if(index7!=false){
											info.setName(rs.getString("MerchandManager2"));
										}
								}		
							}
							}
							info.setCid(cid);
							info.setCusName(name);
							
							
							Double ifmoney=rs3.getDouble("ifmoney");
							if(!"".equals(ifmoney)&&ifmoney!=0){}else{
							Double iimoney=rs3.getDouble("iimoney");
							String iidate=rs3.getString("iidate");
							info.setProjectId(CaseNo);
							info.setOutstandingAmount(iimoney);
							info.setEstimatedTime(iidate);
							list.add(info);
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
		return list;
	}

}
