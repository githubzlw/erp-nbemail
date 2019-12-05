package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cerong.erp.entity.CustomerInfo;
import cerong.erp.entity.NudrawProject;
import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.jdbc.SQLDBhelper1;

public class NudrawProjectDaoImpl implements INudrawProjectDao{

	@Override
	public List<NudrawProject> getAll(Long millionSeconds1, Long millionSeconds2) {
		List<NudrawProject> list = new ArrayList<NudrawProject>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select iCaseNo,MAX(icreatedate)icreatedate from InvoiceInfo group by iCaseNo  order by icreatedate desc ";
		conn = SQLDBhelper.getConnection();
		try {
		stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				NudrawProject project=new NudrawProject();
				String CaseNo=rs.getString("iCaseNo");
				String time=rs.getString("icreatedate");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long millionSeconds=format.parse(time).getTime();
				if(millionSeconds>millionSeconds2&&millionSeconds<millionSeconds1){
					Connection conn6 = null;
					PreparedStatement stmt6 = null;
					ResultSet rs6 = null;
					String name="";
					String sql6 = "select * from tuzhi where caseno=?     ";
					conn6 = SQLDBhelper.getConnection();
					try {
						stmt6 = conn6.prepareStatement(sql6);
						stmt6.setString(1, CaseNo);
						rs6 = stmt6.executeQuery();
						if(rs6.next()) {
							name=rs6.getString("zhongwen");
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
					
					if(!"".equals(name)){}else{
						Connection conn5 = null;
						PreparedStatement stmt5 = null;
						ResultSet rs5 = null;
						int total1=0;
						
						String sql5 = "select * from itemCase  where CaseNo=? ";
						conn5 = SQLDBhelper.getConnection();
						
						try {
							stmt5 = conn5.prepareStatement(sql5);
							stmt5.setString(1, CaseNo);
							rs5 = stmt5.executeQuery();
							if(rs5.next()) {
							project.setCaseNo(CaseNo);	
							project.setCustomerManager(rs5.getString("CustomerManager"));
							project.setMerchandManager1(rs5.getString("MerchandManager1"));
							project.setMerchandManager2(rs5.getString("MerchandManager2"));
							project.setIidate(time);
							project.setZhijian(rs5.getString("zhijian1"));
							project.setZhijian1(rs5.getString("zhijian2"));
							list.add(project);
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
					}
					}else if(millionSeconds<millionSeconds2){
					Connection conn6 = null;
					PreparedStatement stmt6 = null;
					ResultSet rs6 = null;
					String name="";
					String sql6 = "select * from tuzhi where caseno=?     ";
					conn6 = SQLDBhelper.getConnection();
					try {
						stmt6 = conn6.prepareStatement(sql6);
						stmt6.setString(1, CaseNo);
						rs6 = stmt6.executeQuery();
						if(rs6.next()) {
							name=rs6.getString("zhongwen");
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
					Connection conn2 = null;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					String name2="";
					String sql2 = "select * from tuzhi where caseno=? order by id desc    ";
					conn2 = SQLDBhelper.getConnection();
					try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setString(1, CaseNo);
						rs2 = stmt2.executeQuery();
						if(rs2.next()) {
							name2=rs2.getString("zhongwen");
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
					if(!"".equals(name)&&!"".equals(name2)&&name!=name2){}else{
						Connection conn5 = null;
						PreparedStatement stmt5 = null;
						ResultSet rs5 = null;
						int total1=0;
						
						String sql5 = "select * from itemCase  where CaseNo=? ";
						conn5 = SQLDBhelper.getConnection();
						
						try {
							stmt5 = conn5.prepareStatement(sql5);
							stmt5.setString(1, CaseNo);
							rs5 = stmt5.executeQuery();
							if(rs5.next()) {
							project.setCaseNo(CaseNo);	
							project.setCustomerManager(rs5.getString("CustomerManager"));
							project.setMerchandManager1(rs5.getString("MerchandManager1"));
							project.setMerchandManager2(rs5.getString("MerchandManager2"));
							project.setIidate(time);
							project.setZhijian(rs5.getString("zhijian1"));
							project.setZhijian1(rs5.getString("zhijian2"));
							list.add(project);
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

}
