package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.icu.util.Calendar;



import cerong.erp.entity.Bargin;
import cerong.erp.entity.Mould;
import cerong.erp.jdbc.SQLDBhelper;

public class BarginDaoImpl  implements IBarginDao{

	@Override
	public List<Bargin> getall(Bargin ba) {
		List<Bargin> list = new ArrayList<Bargin>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	   String sql = "select top 200 CaseNo,BargainNo,id,BargainUrl from Bargain where 1=1 ";
	   conn = SQLDBhelper.getConnection();
		 try {
			if(ba.getName()!=null&&!"".equals(ba.getName())) {
				sql+=" and  CaseNo like ?";
			}
			 sql+="  order by id desc";
			stmt = conn.prepareStatement(sql);
			if(ba.getName()!=null&&!"".equals(ba.getName())) {
				stmt.setString(1, "%"+ba.getName()+"%");	
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				String CaseNo=rs.getString("CaseNo");
				String BargainNo=rs.getString("BargainNo");
				int total=0;
				int total1=0;
				String sql1 = "select count(1) from FactoryFund where CaseNo=? and BargainNo=? and State='<font color=green>已完成款项</font>'";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				stmt1.setString(2, BargainNo);
				rs1 = stmt1.executeQuery();
					if(rs1.next()) {
					total=rs1.getInt(1);
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
							SQLDBhelper.close(conn1,stmt1,rs1);
						}		   
					}
				
				String sql2 = "select count(1) from MoneyCheckUp where CaseNo=? and BargainNo=? and State='<font color=green>已完成款项</font>'";
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				conn2 = SQLDBhelper.getConnection();
				try {
				stmt2 = conn2.prepareStatement(sql2);
				stmt2.setString(1, CaseNo);
				stmt2.setString(2, BargainNo);
				rs2 = stmt2.executeQuery();
					if(rs2.next()) {
					total1=rs2.getInt(1);
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
							SQLDBhelper.close(conn2,stmt2,rs2);
						}		   
					}
				if(total==0&&total1==0){
				Bargin con=new Bargin();
				con.setContractDocuments(rs.getString("BargainUrl"));
				con.setId(rs.getInt("id"));
				con.setProjectId(CaseNo);
				con.setName(BargainNo);
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
	public Bargin getBaigin(int id1) {
		Bargin con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	   String sql = "select  CaseNo,BargainNo,id,BargainUrl from Bargain where id=? ";
	   conn = SQLDBhelper.getConnection();
		 try {
			
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id1);
			rs = stmt.executeQuery();
			while(rs.next()) {
				String CaseNo=rs.getString("CaseNo");
				String BargainNo=rs.getString("BargainNo");
				
				con=new Bargin();
				con.setContractDocuments(rs.getString("BargainUrl"));
				con.setId(rs.getInt("id"));
				con.setProjectId(CaseNo);
				con.setName(BargainNo);
				
				
				
				
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
	public int deleteBargin(int id1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		 ResultSet rs = null;
		String sql = "delete from Bargain  where id =?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id1);
			result= stmt.executeUpdate();
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
			SQLDBhelper.close(conn,stmt,rs);
		}
		return result;
	}

	@Override
	public int deleteFactoryFund(String name, String projectId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from FactoryFund  where BargainNo=? and CaseNo=?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, projectId);
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
			SQLDBhelper.close(conn,stmt,rs);
		}
		return result;
	}

	@Override
	public int deleteMoneyCheckUp(String name, String projectId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from MoneyCheckUp   where BargainNo=? and CaseNo=?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, projectId);
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
			SQLDBhelper.close(conn,stmt,rs);
		}
		return result;
	}

	@Override
	public List<Bargin> getall1(Bargin ba) {
		List<Bargin> list = new ArrayList<Bargin>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	   String sql = "select  CaseNo,BargainNo,id,BargainUrl from Bargain where 1=1 ";
	   conn = SQLDBhelper.getConnection();
		 try {
			if(ba.getName()!=null&&!"".equals(ba.getName())) {
				sql+=" and  CaseNo like ?";
			}
			 sql+="  order by id desc";
			stmt = conn.prepareStatement(sql);
			if(ba.getName()!=null&&!"".equals(ba.getName())) {
				stmt.setString(1, "%"+ba.getName()+"%");	
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				String CaseNo=rs.getString("CaseNo");
				String BargainNo=rs.getString("BargainNo");
				int total=0;
				int total1=0;
				String sql1 = "select count(1) from FactoryFund where CaseNo=? and BargainNo=? and State='<font color=green>已完成款项</font>'";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				stmt1.setString(2, BargainNo);
				rs1 = stmt1.executeQuery();
					if(rs1.next()) {
					total=rs1.getInt(1);
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
							SQLDBhelper.close(conn1,stmt1,rs1);
						}		   
					}
				
				String sql2 = "select count(1) from MoneyCheckUp where CaseNo=? and BargainNo=? and State='<font color=green>已完成款项</font>'";
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				conn2 = SQLDBhelper.getConnection();
				try {
				stmt2 = conn2.prepareStatement(sql2);
				stmt2.setString(1, CaseNo);
				stmt2.setString(2, BargainNo);
				rs2 = stmt2.executeQuery();
					if(rs2.next()) {
					total1=rs2.getInt(1);
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
							SQLDBhelper.close(conn2,stmt2,rs2);
						}		   
					}
				if(total==0&&total1==0){
				Bargin con=new Bargin();
				con.setContractDocuments(rs.getString("BargainUrl"));
				con.setId(rs.getInt("id"));
				con.setProjectId(CaseNo);
				con.setName(BargainNo);
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
	public List<Bargin> getAll(Bargin ba) {
		List<Bargin> list = new ArrayList<Bargin>();	
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	    String sql = "select * from ( select a.CaseNo,a.FactoryName,a.bargain_no,b.FacDate,b.FacMoney,isnull(c.Get_Moeny,0)Get_Moeny,isnull(b.Pay_Moeny,0)Pay_Moeny from ( "
		+"   select CaseNo,max(info.FactoryName)FactoryName,kingdee , STUFF( ( SELECT    ',' + BargainNo   FROM factoryfund left join Factoryinfo "
		+"  info1 on fid=info1.id WHERE CaseNo = fund.CaseNo and  info1.kingdee=info.kingdee "
		+"  group by BargainNo  FOR XML PATH('')) ,1,1,'') AS  bargain_no from factoryfund fund "
		+"  left join Factoryinfo info on fund.fid=info.id where BargainNo not in (select BargainNo from factoryfund "
		+"  where State !='<font color=green>已完成款项</font>'  and BargainNo is not null ) and kingdee is not null and kingdee !=0 ";
	    if(ba.getStartTime()!=null&&!"".equalsIgnoreCase(ba.getStartTime())){
			sql+=" and BargainNo in (select bargainno from FactoryFund  where datediff(day,'"+ba.getStartTime()+"',inputDate)>0) ";
		     }
	    if(ba.getEndTime()!=null&&!"".equalsIgnoreCase(ba.getEndTime())){
			sql+=" and BargainNo in (select bargainno from FactoryFund  where datediff(day,'"+ba.getEndTime()+"',inputDate)<0) ";
		     }
	    sql+="  group by CaseNo,kingdee)a ";
		sql+= "left join";
		sql+=" (  select m.caseno  ,max(m.FacDate)FacDate,info.kingdee, ";
		sql+=" min(info.FactoryName)FactoryName,sum(m.FacMoney)FacMoney,sum(tfm.Pay_Moeny)Pay_Moeny from moneycheckup m left join factoryfund fu on m.ApNumber=fu.ApNumber ";
		sql+=" left join factoryinfo info on info.id=fu.fid ";
		sql+= " left join Tab_Factory_Money tfm on tfm.case_no=m.caseno and tfm.Factory_id=fu.fid ";
		sql+="  where m.FacMoney is not null and m.bargainno is not null and m.GeldObject!='' ";
		sql+=" and m.BargainNo !='' and fu.friFacDate is not null and m.state='<font color=green>已完成款项</font>' ";
		sql+=" and kingdee !=0 and kingdee is not null ";
		sql+="  group by m.caseno ,kingdee)b on a.CaseNo=b.CaseNo and a.kingdee=b.kingdee";
		sql+= " left join";
		sql+= " (select Case_No,sum(Get_Moeny)Get_Moeny,max(info.FactoryName)FactoryName,kingdee ";
		sql+= " from Tab_Factory_Money tab left join ";
		sql+= " factoryinfo info on tab.Factory_id=info.id where Get_Moeny!=0 and Bargain_No is not null ";
		sql+= " and FactoryName is not null and kingdee!=0  group by Case_No,kingdee)c on a.CaseNo=c.Case_No";
		sql+= " and a.kingdee=c.kingdee)a where 1=1  ";
	    if(ba.getInvoiceCollected()==-1){
	    	sql+=" and Pay_Moeny!=Get_Moeny";
	    }else if(ba.getInvoiceCollected()==0){
	    	
	    }else if(ba.getInvoiceCollected()==1){
	    	sql+=" and Pay_Moeny=Get_Moeny";
	    }
	  if(ba.getFactoryName()!=null&&!"".equalsIgnoreCase(ba.getFactoryName())){
		  sql+="and a.FactoryName like ? ";
	  }
	  if(ba.getProjectId()!=null&&!"".equalsIgnoreCase(ba.getProjectId())){
		  sql+="and a.CaseNo like ? ";
	  }
	  if(ba.getName()!=null&&!"".equalsIgnoreCase(ba.getName())){
		  sql+="and a.bargain_no like ? ";
	  }
	   if(ba.getOvertime()==1){
	   sql+="and datediff(day,a.FacDate,getdate())>30 ";
	   }else if(ba.getOvertime()==2){
		   sql+="and datediff(day,a.FacDate,getdate())>60 "; 
	   }else if(ba.getOvertime()==3){
		   sql+="and datediff(day,a.FacDate,getdate())>90 "; 
	   }
	   conn = SQLDBhelper.getConnection();
		 try {
			sql+="  order by a.FactoryName desc";
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(ba.getFactoryName()!=null&&!"".equalsIgnoreCase(ba.getFactoryName())){
				   i++;
				   stmt.setString(i, "%"+ba.getFactoryName()+"%");
			  }
			  if(ba.getProjectId()!=null&&!"".equalsIgnoreCase(ba.getProjectId())){
				  i++;
				  stmt.setString(i, "%"+ba.getProjectId()+"%");
			  }
			  if(ba.getName()!=null&&!"".equalsIgnoreCase(ba.getName())){
				  i++;
				  stmt.setString(i, "%"+ba.getName()+"%");
			  }
			rs = stmt.executeQuery();
			while(rs.next()) {
				Bargin b=new Bargin();
				double FacMoney=rs.getDouble("FacMoney");
				if(FacMoney!=0){
				b.setAmountInvoiceReceived(rs.getDouble("Get_Moeny"));
				b.setDatePayment(rs.getString("FacDate"));
				b.setDeclarationCustoms("");
				b.setFactoryName(rs.getString("FactoryName"));
				b.setName(rs.getString("Bargain_No"));
				b.setProjectId(rs.getString("CaseNo"));
			    b.setTotalSum(rs.getDouble("FacMoney"));
			    b.setPayMoeny(rs.getDouble("Pay_Moeny"));
				//
			    list.add(b);
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
	public List<Bargin> more4MonthInvoiceNotReceived(Bargin ba) {
		List<Bargin> list = new ArrayList<Bargin>();
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	    String sql = "select * from ( select a.CaseNo,a.FactoryName,a.bargain_no,b.FacDate,b.FacMoney,c.Get_Moeny,d.friMoney from ( "
	    		+"   select CaseNo,max(info.FactoryName)FactoryName,kingdee , STUFF( ( SELECT    ',' + BargainNo   FROM factoryfund left join Factoryinfo "
	    		+"  info1 on fid=info1.id WHERE CaseNo = fund.CaseNo and  info1.kingdee=info.kingdee "
	    		+"  group by BargainNo  FOR XML PATH('')) ,1,1,'') AS  bargain_no from factoryfund fund "
	    		+"  left join Factoryinfo info on fund.fid=info.id where  kingdee is not null and kingdee !=0 "
	    		+"  group by CaseNo,kingdee)a "
	    		+ "left join"
	    		+ " (  select m.caseno  ,max(fu.inputDate)FacDate,info.kingdee, "
	    		+" min(info.FactoryName)FactoryName,sum(m.FacMoney)FacMoney from moneycheckup m left join factoryfund fu on m.ApNumber=fu.ApNumber "
	    		+" left join factoryinfo info on info.id=fu.fid "
	    		+"  where m.FacMoney is not null and m.bargainno is not null and m.GeldObject!='' "
	    		+" and m.BargainNo !='' and fu.friFacDate is not null  "
	    		+" and kingdee !=0 and kingdee is not null "
	    		+"  group by m.caseno ,kingdee)b on a.CaseNo=b.CaseNo and a.kingdee=b.kingdee"
	    		+ " left join"
	    		+ " (select Case_No,sum(Get_Moeny)Get_Moeny,max(info.FactoryName)FactoryName,kingdee "
	    		+ " from Tab_Factory_Money tab left join "
	    		+ " factoryinfo info on tab.Factory_id=info.id where Get_Moeny!=0 and Bargain_No is not null "
	    		+ " and FactoryName is not null and kingdee!=0  group by Case_No,kingdee)c on a.CaseNo=c.Case_No"
	    		+ " and a.kingdee=c.kingdee"
	    		+ " left join (select CaseNo,sum(friMoney)friMoney,kingdee from factoryfund fu left join factoryinfo info "
	    		+ "on fu.fid=info.id where kingdee is not null  group by kingdee,CaseNo)d on d.caseno=a.caseno and d.kingdee=a.kingdee"
	    		+ ")a where FacMoney!=Get_Moeny and a.FacDate is not null and datediff(day,a.FacDate,getdate())>120 ";
	    		if(ba.getWhetherToDeclare()==1){
	    			sql+="and a.FacMoney=a.friMoney";
	    		}else if(ba.getWhetherToDeclare()==0){
	    			sql+="and a.FacMoney!=a.friMoney";
	    		}
	    		if(ba.getProjectId()!=null&&!"".equalsIgnoreCase(ba.getProjectId())){
	    			sql+=" and CaseNo like ? ";
	    		}
	    		if(ba.getFactoryName()!=null&&!"".equalsIgnoreCase(ba.getFactoryName())){
	    			sql+=" and FactoryName like ? ";
	    		}
	    		sql+= " order by a.caseno ";
	  conn = SQLDBhelper.getConnection();
		 try {
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(ba.getProjectId()!=null&&!"".equalsIgnoreCase(ba.getProjectId())){
    			i++;
    			stmt.setString(1, ba.getProjectId());
    		}
    		if(ba.getFactoryName()!=null&&!"".equalsIgnoreCase(ba.getFactoryName())){
    			i++;
    			stmt.setString(1, ba.getFactoryName());
    		}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Bargin b=new Bargin();
				b.setAmountInvoiceReceived(rs.getDouble("Get_Moeny"));
				b.setDatePayment(rs.getString("FacDate"));
				//b.setDeclarationCustoms(rs.getString("Invoice_name"));
				b.setFactoryName(rs.getString("FactoryName"));
				b.setName(rs.getString("bargain_no"));
				b.setProjectId(rs.getString("CaseNo"));
			    b.setTotalSum(rs.getDouble("FacMoney"));
			    if(rs.getDouble("FacMoney")==rs.getDouble("friMoney")){
			    	b.setLastParagraph(1);
			    }else{
			    	b.setLastParagraph(0);
			    }
				
			    list.add(b);
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
	public String search(String bargainNo,String date) {
		String purno1=null;
		double trueprice=0.0;
		String sql2 = "select itemchn,con.purno from [reportform].[dbo].[items] it left join [reportform].[dbo].[products] p on it.proId=p.id left join [reportform].[dbo].[contract] con on con.proId=it.proId where "
					+ " ? like '%'+con.purno+'%' or  ? like '%'+con.purno+'%'  ";
			Connection conn2 = null;
			PreparedStatement stmt2 = null;
			ResultSet rs2 = null;
			conn2 = SQLDBhelper.getConnection();
			try {
			stmt2 = conn2.prepareStatement(sql2);
			stmt2.setString(1, bargainNo);
			stmt2.setString(2, bargainNo);
			rs2 = stmt2.executeQuery();
				while(rs2.next()) {
					String itemchn= rs2.getString("itemchn");
					boolean Save=false;
					if(purno1!=null&&!"".equalsIgnoreCase(purno1)){
						Save=purno1.toLowerCase().contains(itemchn.toLowerCase());
					}
					if(!Save){
					if(!"0".equalsIgnoreCase(rs2.getString("itemchn"))){
					purno1+="-"+rs2.getString("itemchn");
					
					}
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
						SQLDBhelper.close(conn2,stmt2,rs2);
					}		   
				}
			
			String sql = " select sum(trueprice)trueprice from ("
					+ "select cast(trueprice as float)trueprice,p.* from [reportform].[dbo].[items] it left join [reportform].[dbo].[products] p on it.proId=p.id"
					+ " left join [reportform].[dbo].[contract] con on con.proId=it.proId where "
					+ "? like '%'+con.purno+'%' or   ? like"
					+ " '%'+con.purno+'%')a where  DATEDIFF(day,'"+date+"',timeDate)>0  ";
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, bargainNo);
				stmt.setString(2, bargainNo);
				rs = stmt.executeQuery();
				while(rs.next()) {
					trueprice+=rs.getDouble("trueprice");
						
					
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
					SQLDBhelper.close(conn,stmt,rs);
				}		   
			}
			
		if(purno1!=null&&!"".equalsIgnoreCase(purno1)){
			purno1=purno1.replaceFirst("-", "");
			purno1=purno1.replaceFirst("null", "");
		}
		//purno1=purno1+":"+trueprice;
		return purno1;
	}

}
