package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cerong.erp.entity.ContractMaturity;
import cerong.erp.entity.CustomerInfo;
import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.jdbc.SQLDBhelper1;

public class ContractMaturityDao implements IContractMaturityDaoImpl{

	@Override
	public List<ContractMaturity> getall(Long millionSeconds) {
		List<ContractMaturity> list = new ArrayList<ContractMaturity>();
		ContractMaturity con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
		
		/*String sql = "select bar.CaseNo,bar.BargainNo,ff.FacMoney,ff.FacName,ff.friFacDate,bar.completiontime,ff.inputDate from Bargain bar left join FactoryFund ff "
				+ "on ff.BargainNo=bar.BargainNo where bar.contract_status=0 and ff.State='<font color=green>已完成款项</font>'";*/
		String sql = " select 	bar.CaseNo,bar.BargainNo, ff.FacMoney,ff.FacName,ff.friFacDate,bar.completiontime,ff.inputDate from Bargain bar  "
				+ "left join (select MAX(FacMoney) FacMoney,MAX(State) State,MAX(FacName) FacName,MAX(friFacDate) friFacDate,MAX(inputDate) inputDate ,BargainNo from	FactoryFund group by BargainNo)ff  "
				+ "on  ff.BargainNo=bar.BargainNo where bar.contract_status=0 and ff.State='<font color=green>已完成款项</font>'";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new ContractMaturity();
			
				String time1=rs.getString("inputDate");	
			String time=rs.getString("completiontime");
			String time2="2005-01-01 01:01:01";
			String BargainNo=rs.getString("BargainNo");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long millionSeconds1 = format.parse(time).getTime();
			Long millionSeconds4 = format.parse(time2).getTime();
			if(millionSeconds1<millionSeconds){
				
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1 = "select pr.nonum,co.factory from contract co left join products pr on pr.id=co.proId  where co.purno=? ";
				
				conn1 = SQLDBhelper1.getConnection();
				
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1,BargainNo );
					rs1 = stmt1.executeQuery();
					if(rs1.next()){
						String nonum=rs1.getString("nonum");
						String factory=rs1.getString("factory");
						if(nonum!=null&&!"".equals(nonum)){
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
						      int result2=0;
							ResultSet rs2 = null;
							
							String sql2 = "update Bargain set contract_status=1 where BargainNo = ? ";
							conn2 = SQLDBhelper.getConnection();
							//conn = DBHelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setString(1, rs.getString("BargainNo"));
							
								result2 = stmt2.executeUpdate();
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
						
						}else{
							Long millionSeconds2 = format.parse(time1).getTime();
							Long millionSeconds3=millionSeconds-31104000000l;
							if(millionSeconds2>millionSeconds3){
							con.setBargainNo(rs.getString("BargainNo"));
							con.setCaseNo(rs.getString("CaseNo"));
							if(millionSeconds4<millionSeconds1){
							con.setCompletiontime(rs.getString("completiontime"));
							}else{
								con.setCompletiontime("");	
							}
							String factory1=rs.getString("FacName");
							if(factory1!=null&&!"".equals(factory1)){
							con.setFactory(rs.getString("FacName"));
							}else{
								con.setFactory(factory);	
							}
							con.setMoney(rs.getString("FacMoney"));
							con.setPaytime(rs.getString("friFacDate"));
							list.add(con);
							}
						}
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
				}
				
		/*con.setBargainNo(rs.getString("BargainNo"));
		con.setCaseNo(rs.getString("CaseNo"));
		con.setCompletiontime(rs.getString("completiontime"));
		con.setFactory(rs.getString("FacName"));
		con.setMoney(rs.getString("FacMoney"));
		con.setPaytime(rs.getString("friFacDate"));
		list.add(con);*/
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
