package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.jdbc.SQLDBhelper1;
import cerong.erp.entity.Bargin;
import cerong.erp.entity.Customer;
import cerong.erp.entity.CustomerInfo;

public    class CustomerDao implements ICustomerDaoImpl{

	@Override
	public int updateInf(CustomerInfo cus,int erpcid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "update customer set name=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cus.getFirstName());
			stmt.setInt(2,erpcid );
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
	public int getID(String firstName, int country, String ddlDgree) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		int result1 = 0;
		int  id=0;
		String sql = "insert into customer(name,kehudaxiao,countryid,customerid) values(?,?,?,NEWID())";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2,ddlDgree );
			stmt.setInt(3,country );
          result = stmt.executeUpdate();
          if (result == 1) {
        	  Connection conn1 = null;
      		PreparedStatement stmt1 = null;
      		
      		ResultSet rs1 = null;
      		
      		String sql1 = "select id from customer  order by id desc ";
      		conn1 = SQLDBhelper.getConnection();
      		//conn = DBHelper.getConnection();
      		try {
      			stmt1 = conn1.prepareStatement(sql1);
      			
      			rs1 = stmt1.executeQuery();
      			if(rs1.next()) {
      				id = rs1.getInt(1);
      			}
      		} catch (Exception e) {
      			e.printStackTrace();
      		} finally {
      			if (stmt1 != null) {
      				try {
      					stmt1.close();
      				} catch (SQLException e) {
      					e.printStackTrace();
      				}
      			}
      			if (rs1 != null) {
      				try {
      					rs1.close();
      				} catch (SQLException e) {
      					e.printStackTrace();
      				}
      			}
      			SQLDBhelper.close(conn1,stmt1,rs1);	
			
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
		return id;
	}

	@Override
	public int getfirstName(String firstName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "select count(*) from customer  where name = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}

	@Override
	public int getId(String firstName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "select id from customer  where name = ? order by id  desc";
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}

	@Override
	public List<CustomerInfo> getCus(CustomerInfo cus) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String time=null;
		int i=0;
		
		
		String project=null;
		String sql = "select   c.ifdate,a.total2,b.total1 ,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.*,itc1.ProductDescE,itc1.CaseStatus from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid  "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"
				+ " where"
				+ "  itc.money1>0 and itc.money1 is not null";
	
		conn = SQLDBhelper.getConnection();
		
		try {
			if(cus.getCstatus() != null && !"".equals(cus.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(cus.getSaleName1() != null && !"".equals(cus.getSaleName1())) {
				sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
			}
			
			sql +=" order by CreateTime desc";
			stmt = conn.prepareStatement(sql);
			if(cus.getCstatus() != null && !"".equals(cus.getCstatus())) {
				i++;
				stmt.setString(i, "%"+cus.getCstatus()+"%");
			}
			if(cus.getSaleName1() != null && !"".equals(cus.getSaleName1())) {
				i++;
				stmt.setString(i, cus.getSaleName1());
			    stmt.setString(i+1, cus.getSaleName1());
			    stmt.setString(i+2, cus.getSaleName1());
			    stmt.setString(i+3, cus.getSaleName1());
			    stmt.setString(i+4, cus.getSaleName1());
			    stmt.setString(i+5, cus.getSaleName1());
			    stmt.setString(i+6, cus.getSaleName1());
			    stmt.setString(i+7, cus.getSaleName1());
			  
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerInfo info = new CustomerInfo();
				int id = 0;
				
				ResultSet rs4 = null;
				id=rs.getInt("id");
				
				info.setCstatus(rs.getString("CaseStatus"));
				info.setQuotationitem(rs.getInt("total1"));
				info.setDocumentaryproject(rs.getInt("total2"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setCid(rs.getInt("id"));
				info.setFirstName(rs.getString("name"));
				info.setSaleName2(rs.getString("saleName2"));
				info.setSaleName1(rs.getString("saleName1"));
				info.setCreateTime1(rs.getString("ifdate"));
			    info.setCreateTime(rs.getString("CreateTime"));	
				info.setBaobiao(rs.getInt("baobiao"));
				info.setNote(rs.getString("kehuxingzhi"));
				info.setAmount(rs.getString("money1"));
				info.setWork(rs.getString("other"));
				info.setKehudaxiao(rs.getString("kehudaxiao"));
				info.setCstatus(rs.getString("class"));
				project=rs.getString("projectId");
				info.setProjectId(project);
				String project1="SHS-"+project.replaceAll("SHS", "");
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1 = "select saildate from products where nonum  like ? ";
				conn1 = SQLDBhelper1.getConnection();
				try {
					
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, "%"+project1+"%");
					rs1 = stmt1.executeQuery();
					if(rs1.next()){
						time=rs1.getString(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt1 != null) {
						try {
							stmt1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs1 != null) {
						try {
							rs1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper1.close(conn1,stmt1,rs1);
					
				}
				
				
				info.setDeliveryTime(time);
				
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
	public CustomerInfo getCUS(int cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		CustomerInfo info =null;
		String sql = "select cus.* from customer cus where cus.id=?     ";
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				 info = new CustomerInfo();
				info.setCid(rs.getInt("id"));
				info.setFirstName(rs.getString("name"));
				info.setNote(rs.getString("kehuxingzhi"));
				info.setWork(rs.getString("other"));
				info.setKehudaxiao(rs.getString("kehudaxiao"));
				info.setCstatus(rs.getString("class"));
					
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
	public int update(String name, String potential, String explain, int cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "update customer set other=?,kehudaxiao=?,kehuxingzhi=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2,potential );
			stmt.setString(3,explain );
			stmt.setInt(4,cid );
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
	public List<CustomerInfo> getCus1(CustomerInfo info1) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		int i=0;
		String sql = "select cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,isnull(itc1.CustomerManager,'')saleName1"
				+ ",inv2.*,itc1.*  from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo  group by itc.customercode  )"
				+ "inv2 on inv2.customercode=cus.id   where inv2.money1 !=0  ";
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.CustomerManager =? ";
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.MerchandManager1 =? ";
			}
			sql +=" order by inv2.money1  desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				i++;
				stmt.setString(i, info1.getSaleName2());
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerInfo info = new CustomerInfo();
				
				time1=rs.getString("CreateTime");
				if(info1.getTime1() != null && !"".equals(info1.getTime1())) {
					time2=info1.getTime1();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number2=(int) (Seconds/(60*60*1000*24));
					if(number2>120&&number2<545){
					info.setProjectId(rs.getString("projectId"));
					info.setProjectDesce(rs.getString("ProductDescE"));
					info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName1(rs.getString("saleName1"));
					info.setSaleName2(rs.getString("saleName2"));
				  String	CaseNO=rs.getString("projectId");
					String time3=null;
					Connection conn2 = null;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					String sql2 = "select inv.iCaseNo,ifdate=(select top 1 ifdate from InvoiceInfo "
							+ " where iCaseNo=inv.iCaseNo order by  ifdate desc) from InvoiceInfo inv where inv.iCaseNo=?  group by inv.iCaseNo ";
					conn2 = SQLDBhelper.getConnection();
					
					try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setString(1, CaseNO);
						rs2 = stmt2.executeQuery();
						if(rs2.next()){
							time3=rs2.getString("ifdate");
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
					if(!"".equals(time1)&&time1!=null){
						info.setCreateTime(time1);
						}else{
							info.setCreateTime("");
						}
					info.setCreateTime(rs.getString("CreateTime"));
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					project=rs.getString("projectId");
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
					list.add(info);
					}
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
	public List<CustomerInfo> getCus2(CustomerInfo info,int id,int id1,int id2,int id3) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		String project=null;
		String time=null;
		int i=0;
			String sql = "select cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,isnull(itc1.CustomerManager,'')saleName1"
					+ ",inv2.*,itc1.* from customer cus "
					+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
					+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv where inv.iid>="+id3+" and "+id2+">=inv.iid group by inv.iCaseNo) inv1  left join"
					+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo where  itc.id >= "+id1 +" and "+id+">=itc.id  group by itc.customercode )"
					+ "inv2 on inv2.customercode=cus.id    where inv2.money1 !=0     ";
			conn = SQLDBhelper.getConnection();
			
			try {
				if(info.getCstatus() != null && !"".equals(info.getCstatus())) {
					sql +=" and cus.class  like ? ";
				}
				if(info.getSaleName1() != null && !"".equals(info.getSaleName2())) {
					sql +=" and itc1.CustomerManager =? ";
				}
				if(info.getSaleName2() != null && !"".equals(info.getSaleName2())) {
					sql +=" and itc1.MerchandManager1 =? ";
				}
				
				stmt = conn.prepareStatement(sql);
				if(info.getCstatus() != null && !"".equals(info.getCstatus())) {
					i++;
					stmt.setString(i, "%"+info.getCstatus()+"%");
				}
				if(info.getSaleName1() != null && !"".equals(info.getSaleName1())) {
					i++;
					stmt.setString(i, info.getSaleName1());
				}
				if(info.getSaleName2() != null && !"".equals(info.getSaleName2())) {
					i++;
					stmt.setString(i, info.getSaleName2());
				}
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					CustomerInfo info1 = new CustomerInfo();
					
				
					info1.setProjectDesce(rs.getString("ProductDescE"));
						info1.setProjectId(rs.getString("projectId"));
						info1.setCid(rs.getInt("id"));
						info1.setFirstName(rs.getString("name"));
						info1.setSaleName1(rs.getString("saleName1"));
						info1.setSaleName2(rs.getString("saleName2"));
						String	CaseNO=rs.getString("projectId");
						String time3=null;
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						String sql2 = "select inv.iCaseNo,ifdate=(select top 1 ifdate from InvoiceInfo "
								+ " where iCaseNo=inv.iCaseNo order by  ifdate ) from InvoiceInfo inv where inv.iCaseNo=?  group by inv.iCaseNo ";
						conn2 = SQLDBhelper.getConnection();
						
						try {
							stmt2 = conn2.prepareStatement(sql2);
							stmt2.setString(1, CaseNO);
							rs2 = stmt2.executeQuery();
							if(rs2.next()){
								time3=rs2.getString("ifdate");
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
						if(!"".equals(time3)&&time3!=null){
							info1.setCreateTime(time3);
							}else{
								info1.setCreateTime(rs.getString("CreateTime"));	
							}
						//info.setComplain(rs.getInt("complain"));
						info1.setBaobiao(rs.getInt("baobiao"));
						info1.setNote(rs.getString("kehuxingzhi"));
						info1.setAmount(rs.getString("money1"));
						info1.setWork(rs.getString("other"));
						info1.setKehudaxiao(rs.getString("kehudaxiao"));
						info1.setCstatus(rs.getString("class"));
						project=rs.getString("projectId");
						String project1="SHS-"+project.replaceAll("SHS", "");
						Connection conn1 = null;
						PreparedStatement stmt1 = null;
						
						ResultSet rs1 = null;
						String sql1 = "select saildate from products where nonum  like ? ";
						conn1 = SQLDBhelper1.getConnection();
						
						try {
							
							stmt1 = conn1.prepareStatement(sql1);
							stmt1.setString(1, "%"+project1+"%");
							rs1 = stmt1.executeQuery();
							if(rs1.next()){
								time=rs1.getString(1);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (stmt1 != null) {
								try {
									stmt1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (rs1 != null) {
								try {
									rs1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper1.close(conn1,stmt1,rs1);
							
						}
						
						
						info.setDeliveryTime(time);
						
						list.add(info1);
					
					
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
	public List<CustomerInfo> getCus3(CustomerInfo info,int id,int id1,int id2,int id3) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		int i=0;
		ResultSet rs = null;
		String project =null;
		String time=null;
		
			String sql = "select cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,isnull(itc1.CustomerManager,'')saleName1"
					+ ",inv2.*,itc1.*  from customer cus "
					+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
					+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv where inv.iid>="+id3+" and "+id2+">=inv.iid group by inv.iCaseNo) inv1  left join"
					+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo where  itc.id >= "+id1 +" and "+id+">=itc.id   group by itc.customercode )"
					+ "inv2 on inv2.customercode=cus.id   where inv2.money1 !=0     ";
			conn = SQLDBhelper.getConnection();
			
			try {
				if(info.getCstatus() != null && !"".equals(info.getCstatus())) {
					sql +=" and cus.class  like ? ";
				}
				if(info.getSaleName1() != null && !"".equals(info.getSaleName2())) {
					sql +=" and itc1.CustomerManager =? ";
				}
				if(info.getSaleName2() != null && !"".equals(info.getSaleName2())) {
					sql +=" and itc1.MerchandManager1 =? ";
				}
				
				stmt = conn.prepareStatement(sql);
				if(info.getCstatus() != null && !"".equals(info.getCstatus())) {
					i++;
					stmt.setString(i, "%"+info.getCstatus()+"%");
				}
				if(info.getSaleName1() != null && !"".equals(info.getSaleName1())) {
					i++;
					stmt.setString(i, info.getSaleName1());
				}
				if(info.getSaleName2() != null && !"".equals(info.getSaleName2())) {
					i++;
					stmt.setString(i, info.getSaleName2());
				}
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					CustomerInfo info1 = new CustomerInfo();
					String	CaseNO=rs.getString("projectId");
					if(CaseNO!=null&&!"".equals(CaseNO)){
					info1.setProjectId(rs.getString("projectId"));
						info1.setCid(rs.getInt("id"));
						info1.setFirstName(rs.getString("name"));
						info1.setSaleName1(rs.getString("saleName1"));
						info1.setSaleName2(rs.getString("saleName2"));
						info1.setProjectDesce(rs.getString("ProductDescE"));	
						String time3=null;
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						String sql2 = "select inv.iCaseNo,ifdate=(select top 1 ifdate from InvoiceInfo "
								+ " where iCaseNo=inv.iCaseNo order by  ifdate ) from InvoiceInfo inv where inv.iCaseNo=?  group by inv.iCaseNo ";
						conn2 = SQLDBhelper.getConnection();
						
						try {
							stmt2 = conn2.prepareStatement(sql2);
							stmt2.setString(1, CaseNO);
							rs2 = stmt2.executeQuery();
							if(rs2.next()){
								time3=rs2.getString("ifdate");
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
						if(!"".equals(time3)&&time3!=null){
							info1.setCreateTime(time3);
							}else{
								info1.setCreateTime(rs.getString("CreateTime"));	
							}
						//info.setComplain(rs.getInt("complain"));
						info1.setBaobiao(rs.getInt("baobiao"));
						info1.setNote(rs.getString("kehuxingzhi"));
						info1.setAmount(rs.getString("money1"));
						info1.setWork(rs.getString("other"));
						info1.setKehudaxiao(rs.getString("kehudaxiao"));
						info1.setCstatus(rs.getString("class"));
						project=rs.getString("projectId");
						String project1="SHS-"+project.replaceAll("SHS", "");
						Connection conn1 = null;
						PreparedStatement stmt1 = null;
						
						ResultSet rs1 = null;
						String sql1 = "select saildate from products where nonum  like ? ";
						conn1 = SQLDBhelper1.getConnection();
						
						try {
							
							stmt1 = conn1.prepareStatement(sql1);
							stmt1.setString(1, "%"+project1+"%");
							rs1 = stmt1.executeQuery();
							if(rs1.next()){
								time=rs1.getString(1);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (stmt1 != null) {
								try {
									stmt1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (rs1 != null) {
								try {
									rs1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper1.close(conn1,stmt1,rs1);
							
						}
						
						
						info.setDeliveryTime(time);
						System.out.print(info);
						list.add(info1);
					
					
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
	public int getid(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from itemCase";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("CreateTime");
		String time3="2015-1-1 00:00:00";
		String time4="2016-1-1 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 = format.parse(time3).getTime();
		Long millionSeconds3 = format.parse(time4).getTime();
		if(millionSeconds1>millionSeconds2&&millionSeconds1<millionSeconds3){
			id=rs.getInt("id");
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
		return id;
		}

	@Override
	public int getid1(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from itemCase  where 1=1  order by id  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("CreateTime");
		String time3="2015-1-1 00:00:00";
		String time4="2016-1-1 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 = format.parse(time3).getTime();
		Long millionSeconds3 = format.parse(time4).getTime();
		if(millionSeconds1>millionSeconds2&&millionSeconds1<millionSeconds3){
			id=rs.getInt("id");
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
		return id;
	}

	@Override
	public int getid2(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from itemCase";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("CreateTime");
		String time3="2016-1-1 00:00:00";
		String time4="2017-1-1 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 = format.parse(time3).getTime();
		Long millionSeconds3 = format.parse(time4).getTime();
		if(millionSeconds1>millionSeconds2&&millionSeconds1<millionSeconds3){
			id=rs.getInt("id");
			
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
		return id;
	}

	@Override
	public int getid3(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from itemCase  where 1=1  order by id  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("CreateTime");
		String time3="2016-1-1 00:00:00";
		String time4="2017-1-1 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 = format.parse(time3).getTime();
		Long millionSeconds3 = format.parse(time4).getTime();
		if(millionSeconds1>millionSeconds2&&millionSeconds1<millionSeconds3){
			id=rs.getInt("id");
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
		return id;
	}

	@Override
	public int getid3(Long millionSeconds1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from itemCase  where 1=1  order by id  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("CreateTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds2 = format.parse(time1).getTime();
		
		if(millionSeconds2>millionSeconds1){
			id=rs.getInt("id");
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
		return id;
	}

	@Override
	public List<CustomerInfo> getCu(CustomerInfo info2, int id1,int id2) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		int i=0;
		ResultSet rs = null;
		String project=null;
		String time=null;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"
				+ " where"
				+ "  itc.money1>0 and itc.money1 is not null";
			
			conn = SQLDBhelper.getConnection();
			
			try {
				if(info2.getCstatus() != null && !"".equals(info2.getCstatus())) {
					sql +=" and cus.class  like ? ";
				}
				if(info2.getSaleName1() != null && !"".equals(info2.getSaleName1())) {
					sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
				}
				
				sql +=" order by CreateTime desc";
				stmt = conn.prepareStatement(sql);
				if(info2.getCstatus() != null && !"".equals(info2.getCstatus())) {
					i++;
					stmt.setString(i, "%"+info2.getCstatus()+"%");
				}
				if(info2.getSaleName1() != null && !"".equals(info2.getSaleName1())) {
					i++;
					stmt.setString(i, info2.getSaleName1());
				    stmt.setString(i+1, info2.getSaleName1());
				    stmt.setString(i+2, info2.getSaleName1());
				    stmt.setString(i+3, info2.getSaleName1());
				    stmt.setString(i+4, info2.getSaleName1());
				    stmt.setString(i+5, info2.getSaleName1());
				    stmt.setString(i+6, info2.getSaleName1());
				    stmt.setString(i+7, info2.getSaleName1());
				  
				}
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					CustomerInfo info = new CustomerInfo();
					Connection conn4 = null;
					PreparedStatement stmt4 = null;
					int id = 0;
					String ProductDescE="";
					String CaseNo=null;
					String time1=rs.getString("CreateTime");
					Date date = new Date(); 
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time2=format.format(date);
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number=(int) (Seconds/(60*60*1000*24));
					if(number<30&&number>0){
					 
					
					
					
				    info.setCreateTime(rs.getString("CreateTime"));	
				    info.setQuotationitem(rs.getInt("total1"));
					info.setDocumentaryproject(rs.getInt("total2"));
					info.setProjectDesce(rs.getString("ProductDescE"));
	                 info.setCreateTime1(rs.getString("ifdate"));
	

					//info.setProjectId(CaseNo);
					info.setProjectDesce(ProductDescE);
					info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName2(rs.getString("saleName2"));
					info.setSaleName1(rs.getString("saleName1"));
					
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					
					project=rs.getString("projectId");
					if(CaseNo!=null&&!"".equals(CaseNo)){
						info.setProjectId(CaseNo);
						}else{
						info.setProjectId(project);
						}
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					//String sql1 = "select ifdate from InvoiceInfo where iCaseNo like ? ";
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
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
	public int getId(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		String time3="2015-1-1 00:00:00";
		String time4="2016-1-1 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 = format.parse(time3).getTime();
		Long millionSeconds3 = format.parse(time4).getTime();
		if(millionSeconds1>millionSeconds2&&millionSeconds1<millionSeconds3){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int getId1(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo where 1=1  order by iid  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		String time3="2015-1-1 00:00:00";
		String time4="2016-1-1 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 = format.parse(time3).getTime();
		Long millionSeconds3 = format.parse(time4).getTime();
		if(millionSeconds1>millionSeconds2&&millionSeconds1<millionSeconds3){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int getId2(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo ";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("icreatedate");
		 String a[] = time1.split(" "); 
	        String projectId=a[0];
	        String erId=a[1];
	        String b[] = projectId.split("-"); 
	        String num=b[0];
		if("2016".equals(num)){
		
		
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int getId3(CustomerInfo info) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo where 1=1  order by iid  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		String time3="2016-1-1 00:00:00";
		String time4="2017-1-1 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		Long millionSeconds2 = format.parse(time3).getTime();
		Long millionSeconds3 = format.parse(time4).getTime();
		if(millionSeconds1>millionSeconds2&&millionSeconds1<millionSeconds3){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int getId3(Long millionSeconds1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo where 1=1  order by iid  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds2 = format.parse(time1).getTime();
		
		if(millionSeconds2>millionSeconds1){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int updateCstatus(String cstatus, int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "update customer set class=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cstatus);
			stmt.setInt(2,id );
			
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
	public List<Customer> getAll() {
		List<Customer> list = new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select *  from customer  ";
				
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Customer info = new Customer();
				
				info.setId(rs.getInt("id"));
				info.setName(rs.getString("name"));
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
	public int getCusTotal(CustomerInfo cus) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i=0;
		int total=0;
		
		String sql = "select count(*)   from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo  group by itc.customercode )"
				+ "inv2 on inv2.customercode=cus.id    where inv2.money1 !=0   ";
		conn = SQLDBhelper.getConnection();
		
		try {
			if(cus.getCstatus() != null && !"".equals(cus.getCstatus())) {
				sql +=" and cus.class like ? ";
			}
			if(cus.getSaleName1() != null && !"".equals(cus.getSaleName2())) {
				sql +=" and itc1.CustomerManager =? ";
			}
			if(cus.getSaleName2() != null && !"".equals(cus.getSaleName2())) {
				sql +=" and itc1.MerchandManager1 =? ";
			}
			
			stmt = conn.prepareStatement(sql);
			if(cus.getCstatus() != null && !"".equals(cus.getCstatus())) {
				i++;
				stmt.setString(i, "%"+cus.getCstatus()+"%");
			}
			if(cus.getSaleName1() != null && !"".equals(cus.getSaleName1())) {
				i++;
				stmt.setString(i, cus.getSaleName1());
			}
			if(cus.getSaleName2() != null && !"".equals(cus.getSaleName2())) {
				i++;
				stmt.setString(i, cus.getSaleName2());
			}
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
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
	public int getCus1Total(CustomerInfo info1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		int total=0;
		int i=0;
		String sql = "select cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,isnull(itc1.CustomerManager,'')saleName1"
				+ ",inv2.*,itc1.*  from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo  group by itc.customercode  )"
				+ "inv2 on inv2.customercode=cus.id   where inv2.money1 !=0   ";
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.CustomerManager =? ";
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.MerchandManager1 =? ";
			}
			sql +=" order by inv2.money1  desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				i++;
				stmt.setString(i, info1.getSaleName2());
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				time1=rs.getString("CreateTime");
				if(info1.getTime1() != null && !"".equals(info1.getTime1())) {
					time2=info1.getTime1();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number2=(int) (Seconds/(60*60*1000*24));
					if(number2>120&&number2<545){
				total++;
					}
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
		return total;
	}

	@Override
	public int getCuTotal(CustomerInfo info1,int id1,int id2) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i=0;
		int total=0;
		
		String sql = "select count(*)  from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv where inv.iid>="+id2+" group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo where  itc.id >= "+id1+ "  group by itc.customercode )"
				+ "inv2 on inv2.customercode=cus.id   where inv2.money1 !=0     ";
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.CustomerManager =? ";
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.MerchandManager1 =? ";
			}
		
		
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				i++;
				stmt.setString(i, info1.getSaleName2());
			}
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
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
	public int getCus2Total(CustomerInfo info1,int id,int id1,int id2,int id3) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i=0;
		int total=0;
		
		String sql = "select count(*) from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv where inv.iid>="+id3+" and "+id2+">=inv.iid group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo where  itc.id >= "+id1 +" and "+id+">=itc.id  group by itc.customercode )"
				+ "inv2 on inv2.customercode=cus.id    where inv2.money1 !=0    ";
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.CustomerManager =? ";
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.MerchandManager1 =? ";
			}
			
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				i++;
				stmt.setString(i, info1.getSaleName2());
			}
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
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
	public int getCus3Total(CustomerInfo info1,int id,int id1,int id2,int id3) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i=0;
		int total=0;
		
		String sql = "select count(*)  from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv where inv.iid>="+id3+" and "+id2+">=inv.iid group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo where  itc.id >= "+id1 +" and "+id+">=itc.id   group by itc.customercode )"
				+ "inv2 on inv2.customercode=cus.id   where inv2.money1 !=0     ";
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.CustomerManager =? ";
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.MerchandManager1 =? ";
			}
			
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				i++;
				stmt.setString(i, info1.getSaleName2());
			}
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
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
	public List<CustomerInfo> getCusb(CustomerInfo info1,int id,int id1,int id2,int id3) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		
		int i=0;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid"
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"

				+ "  where"
				+ "  itc.money1>0 and itc.money1 is not null";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
			}
				sql +=" order by CreateTime desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			    stmt.setString(i+1, info1.getSaleName1());
			    stmt.setString(i+2, info1.getSaleName1());
			    stmt.setString(i+3, info1.getSaleName1());
			    stmt.setString(i+4, info1.getSaleName1());
			    stmt.setString(i+5, info1.getSaleName1());
			    stmt.setString(i+6, info1.getSaleName1());
			    stmt.setString(i+7, info1.getSaleName1());
			  
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerInfo info = new CustomerInfo();
				String CaseNo=null;
				time1=rs.getString("CreateTime");
				if(info1.getTime1() != null && !"".equals(info1.getTime1())) {
					time2=info1.getTime1();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number2=(int) (Seconds/(60*60*1000*24));
					if(number2>120&&number2<365){
						
						
						
						
						info.setQuotationitem(rs.getInt("total1"));
						info.setDocumentaryproject(rs.getInt("total2"));
						info.setProjectDesce(rs.getString("ProductDescE"));
		               info.setCreateTime1(rs.getString("ifdate"));
						info.setCid(rs.getInt("id"));
						info.setFirstName(rs.getString("name"));
						info.setSaleName2(rs.getString("saleName2"));
						info.setSaleName1(rs.getString("saleName1"));
				  
					    info.setCreateTime(rs.getString("CreateTime"));
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					
					
					project=rs.getString("projectId");
					
						info.setProjectId(project);
						
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
					list.add(info);
					}
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
	public int getCusbTotal(CustomerInfo info1,int id,int id1,int id2,int id3) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		int total=0;
		int i=0;
		String sql = "select cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,isnull(itc1.CustomerManager,'')saleName1"
				+ ",inv2.*,itc1.* from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv where inv.iid>="+id2+" and "+id3+">=inv.iid group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo where  itc.id >= "+id +" and "+id1+">=itc.id  group by itc.customercode )"
				+ "inv2 on inv2.customercode=cus.id    where inv2.money1 !=0     ";
		
		/*String sql = "select cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,isnull(itc1.CustomerManager,'')saleName1"
				+ ",inv2.*,itc1.*  from customer cus "
				+ "left join (select * from itemCase  itc where not exists (select 1 from itemCase  where customercode=itc.customercode and id>itc.id)) itc1 on itc1.customercode=cus.id "
				+ "left join ( select sum(inv1.money) as money1  ,itc.customercode from  (select inv.iCaseNo,sum(inv.iimoney)as money from InvoiceInfo inv group by inv.iCaseNo) inv1  left join"
				+ " itemCase  itc  on itc.CaseNo=inv1.iCaseNo  group by itc.customercode  )"
				+ "inv2 on inv2.customercode=cus.id   where inv2.money1 !=0   ";*/
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.CustomerManager =? ";
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				sql +=" and itc1.MerchandManager1 =? ";
			}
			sql +=" order by inv2.money1  desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			}
			if(info1.getSaleName2() != null && !"".equals(info1.getSaleName2())) {
				i++;
				stmt.setString(i, info1.getSaleName2());
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				time1=rs.getString("CreateTime");
				if(info1.getTime1() != null && !"".equals(info1.getTime1())) {
					time2=info1.getTime1();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number2=(int) (Seconds/(60*60*1000*24));
					if(number2>120&&number2<365){
				total++;
					}
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
		return total;
	}

	@Override
	public int geid(CustomerInfo info, Long millionSeconds3) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from itemCase  ";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("CreateTime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		
		
		if(millionSeconds1<millionSeconds3){
			id=rs.getInt("id");
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
		return id;
	}

	@Override
	public int geid1(CustomerInfo info, Long millionSeconds2) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from itemCase  where 1=1  order by id  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("CreateTime");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		
		if(millionSeconds1>millionSeconds2){
			id=rs.getInt("id");
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
		return id;
	}

	@Override
	public int geId(CustomerInfo info, Long millionSeconds3) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo  ";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		
		if(millionSeconds1>millionSeconds3){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int geId1(CustomerInfo info, Long millionSeconds2) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo where 1=1  order by iid  desc";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		
		if(millionSeconds1<millionSeconds2){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int geida(CustomerInfo info, Long millionSeconds3) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int geidb(CustomerInfo info, Long millionSeconds2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int geIdc(CustomerInfo info, Long millionSeconds3) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo where 1=1 order by iid desc   ";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		
		if(millionSeconds1>millionSeconds3){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public int geIdd(CustomerInfo info, Long millionSeconds2) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		
		ResultSet rs = null;
		
		String time1=null;
		int id=0;
		String sql =  "select * from InvoiceInfo ";
     conn = SQLDBhelper.getConnection();

  try {
	stmt = conn.prepareStatement(sql);
	
	rs = stmt.executeQuery();
	while(rs.next()) {
		time1=rs.getString("ifdate");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long millionSeconds1 = format.parse(time1).getTime();
		
		if(millionSeconds1<millionSeconds2){
			id=rs.getInt("iid");
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
		return id;
	}

	@Override
	public List<CustomerInfo> getCusc(CustomerInfo info1, int id, int id1,
			int id2, int id3) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		
		int i=0;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"
				+ " where"
				+ "  itc.money1>0 and itc.money1 is not null";

		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
			}
			sql +=" order by CreateTime desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			    stmt.setString(i+1, info1.getSaleName1());
			    stmt.setString(i+2, info1.getSaleName1());
			    stmt.setString(i+3, info1.getSaleName1());
			    stmt.setString(i+4, info1.getSaleName1());
			    stmt.setString(i+5, info1.getSaleName1());
			    stmt.setString(i+6, info1.getSaleName1());
			    stmt.setString(i+7, info1.getSaleName1());
			  
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerInfo info = new CustomerInfo();
				String CaseNo=null;
				time1=rs.getString("CreateTime");
				if(info1.getTime1() != null && !"".equals(info1.getTime1())) {
					time2=info1.getTime1();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number2=(int) (Seconds/(60*60*1000*24));
					if(number2>365&&number2<540){
					info.setQuotationitem(rs.getInt("total1"));
					info.setDocumentaryproject(rs.getInt("total2"));
					info.setProjectDesce(rs.getString("ProductDescE"));
                    info.setCreateTime1(rs.getString("ifdate"));
					info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName2(rs.getString("saleName2"));
				    info.setSaleName1(rs.getString("saleName1"));
				    info.setCreateTime(rs.getString("CreateTime"));
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					project=rs.getString("projectId");
					info.setProjectId(project);
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
					list.add(info);
					}
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
	public List<CustomerInfo> getCusd(CustomerInfo info1, int id, int id1,
			int id2, int id3) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		
		int i=0;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid  "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"

				+ " where"
				+ "  itc.money1>0 and itc.money1 is not null";

		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
			}
			sql +=" order by CreateTime desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			    stmt.setString(i+1, info1.getSaleName1());
			    stmt.setString(i+2, info1.getSaleName1());
			    stmt.setString(i+3, info1.getSaleName1());
			    stmt.setString(i+4, info1.getSaleName1());
			    stmt.setString(i+5, info1.getSaleName1());
			    stmt.setString(i+6, info1.getSaleName1());
			    stmt.setString(i+7, info1.getSaleName1());
			  
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				    CustomerInfo info = new CustomerInfo();
				    info.setQuotationitem(rs.getInt("total1"));
				    info.setDocumentaryproject(rs.getInt("total2"));
				    info.setProjectDesce(rs.getString("ProductDescE"));
                    info.setCreateTime1(rs.getString("ifdate"));
					info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName2(rs.getString("saleName2"));
					info.setSaleName1(rs.getString("saleName1"));
				    info.setCreateTime(rs.getString("CreateTime"));
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					project=rs.getString("projectId");
					info.setProjectId(project);
						
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
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
	public List<CustomerInfo> getCuse(CustomerInfo info1, int id, int id1,
			int id2, int id3) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		
		int i=0;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"
				+ " where"
				+ "  itc.money1>0 and itc.money1 is not null";
	
		conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
			}
			sql +=" order by CreateTime desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			    stmt.setString(i+1, info1.getSaleName1());
			    stmt.setString(i+2, info1.getSaleName1());
			    stmt.setString(i+3, info1.getSaleName1());
			    stmt.setString(i+4, info1.getSaleName1());
			    stmt.setString(i+5, info1.getSaleName1());
			    stmt.setString(i+6, info1.getSaleName1());
			    stmt.setString(i+7, info1.getSaleName1());
			  
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerInfo info = new CustomerInfo();
				String CaseNo=null;
				time1=rs.getString("CreateTime");
				if(info1.getTime1() != null && !"".equals(info1.getTime1())) {
					time2=info1.getTime1();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number2=(int) (Seconds/(60*60*1000*24));
					if(number2>730&&number2<1095){
                    info.setQuotationitem(rs.getInt("total1"));
				    info.setDocumentaryproject(rs.getInt("total2"));
				    info.setProjectDesce(rs.getString("ProductDescE"));
                    info.setCreateTime1(rs.getString("ifdate"));
					info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName2(rs.getString("saleName2"));
					info.setSaleName1(rs.getString("saleName1"));
				    info.setCreateTime(rs.getString("CreateTime"));
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					project=rs.getString("projectId");
					info.setProjectId(project);
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
					list.add(info);
					}
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
	public List<CustomerInfo> getCusf(CustomerInfo info1, int id, int id1,
			int id2, int id3) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String time1=null;
		String time2=null;
		String project=null;
		String time=null;
		
		int i=0;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"
				+ " where"
				+ "  itc.money1>0 and itc.money1 is not null";
	conn = SQLDBhelper.getConnection();
		
		try {
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				sql +=" and cus.class  like ? ";
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
			}
			sql +=" order by CreateTime desc";
			stmt = conn.prepareStatement(sql);
			if(info1.getCstatus() != null && !"".equals(info1.getCstatus())) {
				i++;
				stmt.setString(i, "%"+info1.getCstatus()+"%");
			}
			if(info1.getSaleName1() != null && !"".equals(info1.getSaleName1())) {
				i++;
				stmt.setString(i, info1.getSaleName1());
			    stmt.setString(i+1, info1.getSaleName1());
			    stmt.setString(i+2, info1.getSaleName1());
			    stmt.setString(i+3, info1.getSaleName1());
			    stmt.setString(i+4, info1.getSaleName1());
			    stmt.setString(i+5, info1.getSaleName1());
			    stmt.setString(i+6, info1.getSaleName1());
			    stmt.setString(i+7, info1.getSaleName1());
			  
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerInfo info = new CustomerInfo();
				String CaseNo=null;
				time1=rs.getString("CreateTime");
				if(info1.getTime1() != null && !"".equals(info1.getTime1())) {
					time2=info1.getTime1();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number2=(int) (Seconds/(60*60*1000*24));
					if(number2>1095&&number2<1460){
					info.setQuotationitem(rs.getInt("total1"));
					info.setDocumentaryproject(rs.getInt("total2"));
					info.setProjectDesce(rs.getString("ProductDescE"));
		            info.setCreateTime1(rs.getString("ifdate"));
                    info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName2(rs.getString("saleName2"));
					info.setSaleName1(rs.getString("saleName1"));
				    info.setCreateTime(rs.getString("CreateTime"));
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					project=rs.getString("projectId");
					info.setProjectId(project);
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
					list.add(info);
					}
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
	public List<CustomerInfo> getCu2(CustomerInfo info2, int id1, int id2) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		int i=0;
		ResultSet rs = null;
		String project=null;
		String time=null;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode)   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid  "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"
				+ "where"
				+ "  itc.money1>0 and itc.money1 is not null";
		
			conn = SQLDBhelper.getConnection();
			
			try {
				if(info2.getCstatus() != null && !"".equals(info2.getCstatus())) {
					sql +=" and cus.class  like ? ";
				}
				if(info2.getSaleName1() != null && !"".equals(info2.getSaleName1())) {
					sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
				}
				/*if(info2.getSaleName2() != null && !"".equals(info2.getSaleName2())) {
					sql +=" and itc1.MerchandManager1 =? ";
				}*/
				sql +=" order by CreateTime desc";
				stmt = conn.prepareStatement(sql);
				if(info2.getCstatus() != null && !"".equals(info2.getCstatus())) {
					i++;
					stmt.setString(i, "%"+info2.getCstatus()+"%");
				}
				if(info2.getSaleName1() != null && !"".equals(info2.getSaleName1())) {
					i++;
					stmt.setString(i, info2.getSaleName1());
				    stmt.setString(i+1, info2.getSaleName1());
				    stmt.setString(i+2, info2.getSaleName1());
				    stmt.setString(i+3, info2.getSaleName1());
				    stmt.setString(i+4, info2.getSaleName1());
				    stmt.setString(i+5, info2.getSaleName1());
				    stmt.setString(i+6, info2.getSaleName1());
				    stmt.setString(i+7, info2.getSaleName1());
				  
				}
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					CustomerInfo info = new CustomerInfo();
					Connection conn4 = null;
					PreparedStatement stmt4 = null;
					String time1=rs.getString("CreateTime");
					Date date = new Date(); 
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time2=format.format(date);
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number=(int) (Seconds/(60*60*1000*24));
					if(number<120&& number>0){
					int id = 0;
					
					info.setQuotationitem(rs.getInt("total1"));
					info.setDocumentaryproject(rs.getInt("total2"));
					info.setProjectDesce(rs.getString("ProductDescE"));
	                info.setCreateTime1(rs.getString("ifdate"));
					info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName2(rs.getString("saleName2"));
					info.setSaleName1(rs.getString("saleName1"));
					
					
				    info.setCreateTime(rs.getString("CreateTime"));
				
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					
					project=rs.getString("projectId");
					info.setProjectId(project);
						
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					//String sql1 = "select ifdate from InvoiceInfo where iCaseNo like ? ";
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
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
	public List<CustomerInfo> getCu1(CustomerInfo info2, int id1, int id2) {
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		int i=0;
		ResultSet rs = null;
		String project=null;
		String time=null;
		String sql = "select    c.ifdate,a.total2,b.total1 ,itc1.ProductDescE,itc1.CaseStatus,cus.*,isnull(itc1.CaseNo,'')projectId,isnull(itc1.MerchandManager1,'')saleName2,"
				+ "isnull(itc1.CustomerManager,'')saleName1,itc.* from Customer cus 	"
				+ "left join (select MAX(id) mxid ,isnull(MAX(InvoiceInfo.ifdate),MAX(itemCase.CreateTime)) as CreateTime, "
				+ "sum(isnull(InvoiceInfo.iimoney,0)) as money1 , customercode,MAX(itemCase.baobiao)baobiao  from itemCase left join InvoiceInfo  "
				+ "on  itemCase.CaseNo=InvoiceInfo.iCaseNo  group by customercode  )   itc	"
				+ "on  cus.id=itc.customercode left join  itemCase itc1   on  itc1.id=itc.mxid  "
				+ " left join (select count(1)total2,customercode from itemCase cus  where  CaseStatus=0 or CaseStatus=14  group by customercode ) a on a.customercode=cus.id"
				+ " left join (select count(1)total1,customercode from itemCase cus  where  CaseStatus=2 or CaseStatus=13 or CaseStatus=4  group by customercode ) b on b.customercode=cus.id"
				
				+ " left join (select it.customercode,min(ifdate)ifdate from (select min(ifdate)ifdate,icaseno from InvoiceInfo where ifdate is not null  group by icaseno)a left join itemcase it on a.iCaseNo=it.CaseNo group by it.customercode     ) c on c.customercode=cus.id"

				+ " where"
				+ "  itc.money1>0 and itc.money1 is not null";
			
			conn = SQLDBhelper.getConnection();
			
			try {
				if(info2.getCstatus() != null && !"".equals(info2.getCstatus())) {
					sql +=" and cus.class  like ? ";
				}
				if(info2.getSaleName1() != null && !"".equals(info2.getSaleName1())) {
					sql +=" and itc1.CustomerManager =? or   itc1.MerchandManager1 =? or  itc1.MerchandManager2 =? or  itc1.Engineer1 =? or itc1.Engineer2 =? or itc1.Engineer3 =? or itc1.zhijian1 =? or itc1.zhijian2 =?  ";
				}
				
				sql +=" order by CreateTime desc";
				stmt = conn.prepareStatement(sql);
				if(info2.getCstatus() != null && !"".equals(info2.getCstatus())) {
					i++;
					stmt.setString(i, "%"+info2.getCstatus()+"%");
				}
				if(info2.getSaleName1() != null && !"".equals(info2.getSaleName1())) {
					i++;
					stmt.setString(i, info2.getSaleName1());
				    stmt.setString(i+1, info2.getSaleName1());
				    stmt.setString(i+2, info2.getSaleName1());
				    stmt.setString(i+3, info2.getSaleName1());
				    stmt.setString(i+4, info2.getSaleName1());
				    stmt.setString(i+5, info2.getSaleName1());
				    stmt.setString(i+6, info2.getSaleName1());
				    stmt.setString(i+7, info2.getSaleName1());
				  
				}
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					CustomerInfo info = new CustomerInfo();
					Connection conn4 = null;
					PreparedStatement stmt4 = null;
					String time1=rs.getString("CreateTime");
					Date date = new Date(); 
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time2=format.format(date);
					Long millionSeconds1 = format.parse(time1).getTime();
					Long millionSeconds2 = format.parse(time2).getTime();
					Long Seconds=millionSeconds2-millionSeconds1;
					int number=(int) (Seconds/(60*60*1000*24));
					if(number<60&& number>0){
					int id = 0;
					
					

					
				    info.setCreateTime(rs.getString("CreateTime"));
				    info.setQuotationitem(rs.getInt("total1"));
					info.setDocumentaryproject(rs.getInt("total2"));
					info.setProjectDesce(rs.getString("ProductDescE"));
	                info.setCreateTime1(rs.getString("ifdate"));
					info.setCid(rs.getInt("id"));
					info.setFirstName(rs.getString("name"));
					info.setSaleName2(rs.getString("saleName2"));
					info.setSaleName1(rs.getString("saleName1"));
					
					
					info.setBaobiao(rs.getInt("baobiao"));
					info.setNote(rs.getString("kehuxingzhi"));
					info.setAmount(rs.getString("money1"));
					info.setWork(rs.getString("other"));
					info.setKehudaxiao(rs.getString("kehudaxiao"));
					info.setCstatus(rs.getString("class"));
					
					project=rs.getString("projectId");
					
						info.setProjectId(project);
						
					String project1="SHS-"+project.replaceAll("SHS", "");
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					String sql1 = "select saildate from products where nonum  like ? ";
					//String sql1 = "select ifdate from InvoiceInfo where iCaseNo like ? ";
					conn1 = SQLDBhelper1.getConnection();
					
					try {
						
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, "%"+project1+"%");
						rs1 = stmt1.executeQuery();
						if(rs1.next()){
							time=rs1.getString(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmt1 != null) {
							try {
								stmt1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rs1 != null) {
							try {
								rs1.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper1.close(conn1,stmt1,rs1);
						
					}
					
					
					info.setDeliveryTime(time);
					
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
	public int updateremarks(int eid, String customerremarks) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "update customer set Customerremarks=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerremarks);
			stmt.setInt(2,eid );
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
	public List<Customer> getall() {
		List<Customer>list=new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	   String sql = "select * from Customer where id>120298 ";
	   conn = SQLDBhelper.getConnection();
		 try {
			 stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Customer cus=new Customer();
				cus.setId(rs.getInt("id"));
				cus.setName(rs.getString("name"));
				list.add(cus);
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
