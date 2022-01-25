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

import cerong.erp.entity.AmountClaimForm;
import cerong.erp.entity.ArrivalAccountCorrespondenceTable;
import cerong.erp.entity.AccountEntryForm;
import cerong.erp.entity.FactoryFund;
import cerong.erp.entity.PreparatorEntryForm;
import cerong.erp.jdbc.SQLDBhelper;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

public class AccountEntryFormDaoImpl  implements  IAccountEntryFormDao{

	@Override
	public List<AccountEntryForm> accounEntry() {
		List<AccountEntryForm> list = new ArrayList<AccountEntryForm>();


		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = "  select account.*,isnull(pre.salesSubmission,0)salesSubmission,ji.kingdee_id from AccountEntryForm account left join (select AmountClaimFormId,min(salesSubmission)salesSubmission from  PreparatorEntryForm group by AmountClaimFormId) pre on account.id=pre.AmountClaimFormId " +
               " left join kingdee_info ji on account.PayersName=ji.kingdee_name where account.DataProcessing=0	order by account.TransactionDate desc ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				AccountEntryForm con=new AccountEntryForm();
				int id=rs.getInt("id");
			    con.setTransactionDate(rs.getString("transactionDate"));
			    con.setTransactionReferenceNumber(rs.getString("transactionReferenceNumber"));
			    con.setTradeAmount(rs.getDouble("tradeAmount"));
			    con.setPayersName(rs.getString("payersName"));
			    con.setBeneficiaryAccountBank(rs.getString("beneficiaryAccountBank"));
			    con.setConjecture(rs.getString("conjecture"));
			    con.setClaimant(rs.getString("claimant"));
			    con.setClaimTime(rs.getString("claimTime"));
			    con.setRemark(rs.getString("remark"));
			    con.setTradeCurrency(rs.getString("tradeCurrency"));
			    con.setPayeeAccount(rs.getString("payeeAccount"));
			    con.setnBEmailId(rs.getInt("nBEmailId"));
			    con.setSalesConfirmationAmount(rs.getInt("salesSubmission"));
			    String payersName= rs.getString("payersName");
			    con.setReason(rs.getString("reason"));
			    con.setEntryPerson(rs.getString("entry_person"));
			    con.setEntryTime(rs.getDate("entry_time"));
				con.setKingdeeId(rs.getString("kingdee_id"));
			    int num=0;
			    Connection conn2 = null;
				PreparedStatement stmt2 = null;
			    ResultSet rs2 = null;
			    String sql2 = " select count(1) from AccountEntryForm account where account.DataProcessing=1 and  account.payersName=?	";
				conn2= SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, payersName);
					rs2 = stmt2.executeQuery();
					if(rs2.next()) {
					num=rs2.getInt(1);	
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
				if(num>0){
			    con.setNewCustomer(0);
				}else{
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					int result = 0;
					ResultSet rs3 = null;
					String sql3 = "update AccountEntryForm set newCustomer=1 where id = ? ";
					conn3 = SQLDBhelper.getConnection();
					try {
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setInt(1, id);
						
						result = stmt3.executeUpdate();
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
					
				con.setNewCustomer(1);	
				}
			     con.setId(rs.getInt("id"));
			     
			   int  salesSubmission= rs.getInt("salesSubmission");   
			   if(salesSubmission==1){
				   List<PreparatorEntryForm> list1 = new ArrayList<PreparatorEntryForm>();
					
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
				    ResultSet rs1 = null;
				    String sql1 = " select * from PreparatorEntryForm where amountClaimFormId=?	";
					conn1= SQLDBhelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setInt(1, id);
						rs1 = stmt1.executeQuery();
						while(rs1.next()) {
							PreparatorEntryForm con1=new PreparatorEntryForm();
						    con1.setAmountClaimFormId(rs1.getInt("amountClaimFormId"));
						    con1.setInvoice(rs1.getString("invoice"));
						    con1.setIimoney(rs1.getDouble("iimoney"));
						    con1.setIfmoney(rs1.getDouble("ifmoney"));
						    con1.setIid(rs1.getInt("iid"));
						    con1.setSalesSubmission(rs1.getInt("salesSubmission"));
							list1.add(con1);
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
					con.setAccessories(list1);
			   } else{
				   con.setAccessories(null);
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
	public void add(AccountEntryForm account) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into AccountEntryForm(TransactionDate,TransactionReferenceNumber,TradeAmount,BeneficiaryAccountBank,"
				+ "PayersName,PayeeAccount,Remark,TradeCurrency,createTime,conjecture,NBEmailId) values(?,?,?,?,?,?,?,?,'"+time+"',?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getTransactionDate());
			stmt.setString(2, account.getTransactionReferenceNumber());
			stmt.setDouble(3, account.getTradeAmount());
			stmt.setString(4, account.getBeneficiaryAccountBank());
			stmt.setString(5, account.getPayersName());
			stmt.setString(6, account.getPayeeAccount());
			stmt.setString(7, account.getRemark());
			stmt.setString(8, account.getTradeCurrency());
			stmt.setString(9, account.getConjecture());
			stmt.setInt(10, account.getnBEmailId());
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

	@Override
	public ArrivalAccountCorrespondenceTable ArrivalAccountCorrespondenceTable(
			String payersName) {
		
		ArrivalAccountCorrespondenceTable con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = " select  a.*,it.customerManager,it.merchandising,it.merchandManager1 from ArrivalAccountCorrespondenceTable a left join   itemcase it on it.customercode=a.customerId"
	   		+ " where a.name=? order by it.id desc	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, payersName);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new ArrivalAccountCorrespondenceTable();
			    con.setCustomerId(rs.getInt("customerId"));
			   con.setCustomerManager(rs.getString("customerManager"));
				con.setMerchandising(rs.getString("merchandising"));
				con.setMerchandManager1(rs.getString("merchandManager1"));
				con.setKingdee(rs.getInt("kingdee"));
				con.setKingName(rs.getString("kingName"));
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
	public int replacementOfClaim(String empEName, int id) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update AccountEntryForm set Claimant=?,ClaimTime='"+time+"' where id = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empEName);
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
	public int recoveryInformation(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update AccountEntryForm set Claimant=?,ClaimTime=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, null);
			stmt.setString(2, null);
			stmt.setInt(3, id);
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
	public int updateAll(String eids, String empEName) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update AccountEntryForm set Claimant=?,ClaimTime='"+time+"' where id in ("+eids+") ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empEName);
			
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
	public int getIbank(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    int bid=0;
	   String sql = " select  bid from BankInfo b left join   AccountEntryForm a on a.BeneficiaryAccountBank=b.bname  "
	   		+ " where a.id=? 	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				bid=rs.getInt("bid");
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
		return bid;
	}

	@Override
	public int updateModificationResults(String eids) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update AccountEntryForm set financialConfirmationMan=1,DataProcessing=1 where id in ("+eids+") ";
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
	public List<AccountEntryForm> completionOfMoney(AccountEntryForm account,String flag) {

		List<AccountEntryForm> list = null;

		if("1".equals(flag)){
			list = new ArrayList<AccountEntryForm>();
			if(StringUtils.isEmpty(account.getTransactionDate()) && StringUtils.isEmpty(account.getTransactionEndDate())){
				return  list;
			}

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = " select * from AccountEntryForm where DataProcessing=1	";
			if(account.getTransactionDate()!=null&&!"".equalsIgnoreCase(account.getTransactionDate())){
				sql+=" and transactionDate >= ?";
			}
			if(account.getTransactionEndDate()!=null&&!"".equalsIgnoreCase(account.getTransactionEndDate())){
				sql+=" and transactionDate <= ?";
			}
//			if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
//				sql+=" and (BeneficiaryAccountBank like ? or PayersName like ? or TradeCurrency like ?)";
//			}
			if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
				sql+=" and (BeneficiaryAccountBank like ? )";
			}

			sql+=" order by transactionDate ";
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				int i=0;
				if(account.getTransactionDate()!=null&&!"".equalsIgnoreCase(account.getTransactionDate())){
					i++;
					stmt.setString(i, account.getTransactionDate());
				}
				if(account.getTransactionEndDate()!=null&&!"".equalsIgnoreCase(account.getTransactionEndDate())){
					i++;
					stmt.setString(i, account.getTransactionEndDate());
				}
				if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
					i++;
					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
//					i++;
//					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
//					i++;
//					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
				}
				rs = stmt.executeQuery();
				while(rs.next()) {
					AccountEntryForm con=new AccountEntryForm();
					int id=rs.getInt("id");
					con.setId(rs.getInt("id"));
					con.setTransactionDate(rs.getString("transactionDate"));
					con.setTransactionReferenceNumber(rs.getString("transactionReferenceNumber"));
					con.setTradeAmount(rs.getDouble("tradeAmount"));
					con.setPayersName(rs.getString("payersName"));
					con.setBeneficiaryAccountBank(rs.getString("beneficiaryAccountBank"));
					con.setConjecture(rs.getString("conjecture"));
					con.setClaimant(rs.getString("claimant"));
					con.setClaimTime(rs.getString("claimTime"));
					con.setRemark(rs.getString("remark"));
					con.setTradeCurrency(rs.getString("tradeCurrency"));
					con.setPayeeAccount(rs.getString("payeeAccount"));
					con.setnBEmailId(rs.getInt("nBEmailId"));
					con.setNewCustomer(rs.getInt("newCustomer"));
//					con.setKingdeeName(rs.getString("kingdee_name"));
					List<AmountClaimForm> list1 = new ArrayList<AmountClaimForm>();
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					String sql1 = " select * from AmountClaimForm where AccountEntryId=?	";
					conn1= SQLDBhelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setInt(1, id);
						rs1 = stmt1.executeQuery();
						while(rs1.next()) {
							AmountClaimForm con1=new AmountClaimForm();
							con1.setInvoice(rs1.getString("invoice"));
							con1.setExportYear(rs1.getString("exportYear"));
							con1.setExportMonth(rs1.getString("exportMonth"));
							con1.setCountry(rs1.getInt("country"));
							con1.setState(rs1.getString("state"));
							list1.add(con1);
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
					if(list1.size()>0){
						con.setAmountClaimForm(list1);
					} else{
						con.setAmountClaimForm(null);
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
		}else if("2".equals(flag)){
			list = new ArrayList<AccountEntryForm>();
			if(StringUtils.isEmpty(account.getTransactionDate()) && StringUtils.isEmpty(account.getTransactionEndDate())){
				return  list;
			}
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = " select * from AmountClaimForm a  inner join AccountEntryForm b on a.AccountEntryId=b.id where b.DataProcessing=1	";
			if(account.getTransactionDate()!=null&&!"".equalsIgnoreCase(account.getTransactionDate())){
				sql+=" and b.transactionDate >= ?";
			}
			if(account.getTransactionEndDate()!=null&&!"".equalsIgnoreCase(account.getTransactionEndDate())){
				sql+=" and b.transactionDate <= ?";
			}
//			if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
//				sql+=" and (BeneficiaryAccountBank like ? or PayersName like ? or TradeCurrency like ?)";
//			}

			if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
				sql+=" and (BeneficiaryAccountBank like ? )";
			}

			sql+=" order by b.transactionDate ";
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				int i=0;
				if(account.getTransactionDate()!=null&&!"".equalsIgnoreCase(account.getTransactionDate())){
					i++;
					stmt.setString(i, account.getTransactionDate());
				}
				if(account.getTransactionEndDate()!=null&&!"".equalsIgnoreCase(account.getTransactionEndDate())){
					i++;
					stmt.setString(i, account.getTransactionEndDate());
				}
				if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
					i++;
					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
//					i++;
//					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
//					i++;
//					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
				}
				rs = stmt.executeQuery();
				while(rs.next()) {
					AccountEntryForm con=new AccountEntryForm();
//					int id=rs.getInt("id");
					con.setId(rs.getInt("id"));
					con.setTransactionDate(rs.getString("transactionDate"));
					con.setTransactionReferenceNumber(rs.getString("transactionReferenceNumber"));
					con.setTradeAmount(rs.getDouble("tradeAmount"));
					con.setPayersName(rs.getString("payersName"));
					con.setBeneficiaryAccountBank(rs.getString("beneficiaryAccountBank"));
					con.setConjecture(rs.getString("conjecture"));
					con.setClaimant(rs.getString("claimant"));
					con.setClaimTime(rs.getString("claimTime"));
					con.setRemark(rs.getString("remark"));
					con.setTradeCurrency(rs.getString("tradeCurrency"));
					con.setPayeeAccount(rs.getString("payeeAccount"));
					con.setnBEmailId(rs.getInt("nBEmailId"));
					con.setNewCustomer(rs.getInt("newCustomer"));

					//AmountClaimForm表数据
					con.setInvoice(rs.getString("invoice"));
					con.setExportYear(rs.getString("exportYear"));
					con.setExportMonth(rs.getString("exportMonth"));
					con.setCountry(rs.getInt("country"));
					con.setSumMoney(rs.getDouble("SumMoney"));
					con.setKingdeeName(rs.getString("kingdee_name"));
//					con.setState(rs.getString("state"));

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
		}else if("3".equals(flag)){

			list = new ArrayList<AccountEntryForm>();
			if(StringUtils.isEmpty(account.getTransactionDate()) && StringUtils.isEmpty(account.getTransactionEndDate())){
				return  list;
			}
			List<AccountEntryForm> list3 = new ArrayList<AccountEntryForm>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = " select af.id,af.transactionDate,af.tradeAmount,kif.kingdee_id,af.PayersName from AccountEntryForm af left join kingdee_info kif on af.PayersName = kif.kingdee_name where af.DataProcessing=1	";
			if(account.getTransactionDate()!=null&&!"".equalsIgnoreCase(account.getTransactionDate())){
				sql+=" and af.transactionDate >= ?";
			}
			if(account.getTransactionEndDate()!=null&&!"".equalsIgnoreCase(account.getTransactionEndDate())){
				sql+=" and af.transactionDate <= ?";
			}
//			if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
//				sql+=" and (BeneficiaryAccountBank like ? or PayersName like ? or TradeCurrency like ?)";
//			}
			if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
				sql+=" and (af.BeneficiaryAccountBank like ? )";
			}

			sql+=" order by af.transactionDate ";
			conn = SQLDBhelper.getConnection();
			try {
				stmt = conn.prepareStatement(sql);
				int i=0;

				if(account.getTransactionDate()!=null&&!"".equalsIgnoreCase(account.getTransactionDate())){
					i++;
					stmt.setString(i, account.getTransactionDate());
				}
				if(account.getTransactionEndDate()!=null&&!"".equalsIgnoreCase(account.getTransactionEndDate())){
					i++;
					stmt.setString(i, account.getTransactionEndDate());
				}
				if(account.getBeneficiaryAccountBank()!=null&&!"".equalsIgnoreCase(account.getBeneficiaryAccountBank())){
					i++;
					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
//					i++;
//					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
//					i++;
//					stmt.setString(i, "%"+account.getBeneficiaryAccountBank()+"%");
				}
				rs = stmt.executeQuery();

				int num = account.getNumZ()+1;
				while(rs.next()) {
					List<AccountEntryForm> list1 = new ArrayList<AccountEntryForm>();
					AccountEntryForm con=new AccountEntryForm();
					int id=rs.getInt("id");
					con.setId(rs.getInt("id"));
					con.setTransactionDate(rs.getString("transactionDate"));
					con.setTradeAmount(rs.getDouble("tradeAmount"));
					con.setKingdeeId(rs.getString("kingdee_id"));
					con.setPayersName(rs.getString("PayersName"));
					//FAccountNum
					con.setfAccountNum("1002.08");
					//FAccountName
					con.setfAccountName("中国银行外币帐户");
					//fEntryId
					con.setfEntryId(0);
					int fNumber = num++;
					con.setNumZ(fNumber);

					list1.add(con);
					list3.addAll(list1);

					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					String sql1 = " select invoice,SumMoney from AmountClaimForm where AccountEntryId=?	";
					conn1= SQLDBhelper.getConnection();
					try {

						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setInt(1, id);
						rs1 = stmt1.executeQuery();
						int z=1;
						while(rs1.next()) {

							List<AccountEntryForm> list2 = new ArrayList<AccountEntryForm>();
							AccountEntryForm con1=new AccountEntryForm();
							con1.setInvoice(rs1.getString("invoice"));
							con1.setSumMoney(rs1.getDouble("SumMoney"));
							con1.setTransactionDate(rs.getString("transactionDate"));
							con1.setPayersName(rs.getString("PayersName"));

							//FAccountNum
							con1.setfAccountNum("2203");
							//FAccountName
							con1.setfAccountName("预收账款");
							//fEntryId
							con1.setfEntryId(z++);
							//fNumber
							con1.setNumZ(fNumber);

							list2.add(con1);
							list3.addAll(list2);
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

			}
			return list3;
		}
		return list;
	}

	@Override
	public cerong.erp.entity.AccountEntryForm AccountEntryForm(int id) {
		AccountEntryForm con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = "  select account.*,isnull(pre.salesSubmission,0)salesSubmission from AccountEntryForm account left join (select AmountClaimFormId,min(salesSubmission)salesSubmission from  PreparatorEntryForm group by AmountClaimFormId) pre on account.id=pre.AmountClaimFormId where id=?	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new AccountEntryForm();
			    con.setTransactionDate(rs.getString("transactionDate"));
			    con.setTransactionReferenceNumber(rs.getString("transactionReferenceNumber"));
			    con.setTradeAmount(rs.getDouble("tradeAmount"));
			    con.setPayersName(rs.getString("payersName"));
			    con.setBeneficiaryAccountBank(rs.getString("beneficiaryAccountBank"));
			    con.setConjecture(rs.getString("conjecture"));
			    con.setClaimant(rs.getString("claimant"));
			    con.setClaimTime(rs.getString("claimTime"));
			    con.setRemark(rs.getString("remark"));
			    con.setTradeCurrency(rs.getString("tradeCurrency"));
			    con.setPayeeAccount(rs.getString("payeeAccount"));
			    con.setnBEmailId(rs.getInt("nBEmailId"));
			    con.setSalesConfirmationAmount(rs.getInt("salesSubmission"));
			    con.setId(rs.getInt("id"));
			    con.setNewCustomer(rs.getInt("newCustomer"));
				
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
	public int getId(int iid) {
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    int total=0;
	   String sql = " select AmountClaimFormId from PreparatorEntryForm where iid=? 	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, iid);
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
	public cerong.erp.entity.AccountEntryForm getAll(int id) {
		AccountEntryForm con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = "  select account.*,isnull(pre.salesSubmission,0)salesSubmission from AccountEntryForm account left join (select AmountClaimFormId,min(salesSubmission)salesSubmission from  PreparatorEntryForm group by AmountClaimFormId) pre on account.id=pre.AmountClaimFormId where account.DataProcessing=0	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				 con=new AccountEntryForm();
			    con.setTransactionDate(rs.getString("transactionDate"));
			    con.setTransactionReferenceNumber(rs.getString("transactionReferenceNumber"));
			    con.setTradeAmount(rs.getDouble("tradeAmount"));
			    con.setPayersName(rs.getString("payersName"));
			    con.setBeneficiaryAccountBank(rs.getString("beneficiaryAccountBank"));
			    con.setConjecture(rs.getString("conjecture"));
			    con.setClaimant(rs.getString("claimant"));
			    con.setClaimTime(rs.getString("claimTime"));
			    con.setRemark(rs.getString("remark"));
			    con.setTradeCurrency(rs.getString("tradeCurrency"));
			    con.setPayeeAccount(rs.getString("payeeAccount"));
			    con.setnBEmailId(rs.getInt("nBEmailId"));
			    con.setSalesConfirmationAmount(rs.getInt("salesSubmission"));
			     con.setId(rs.getInt("id"));
			     con.setNewCustomer(rs.getInt("newCustomer"));
			     
			   
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
	public int addAccount(ArrivalAccountCorrespondenceTable account) {
		int total=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into ArrivalAccountCorrespondenceTable(name,customerId,projectId,kingdee,kingName) values(?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getName());
			stmt.setInt(2, account.getCustomerId());
			stmt.setString(3, account.getProjectId());
			stmt.setInt(4, account.getKingdee());
			stmt.setString(5, account.getKingName());
			
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
	public int deleteAccount(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from AccountEntryForm where id=?  ";
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
	public int updateAccountEntry(AccountEntryForm entry) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update AccountEntryForm set" ;
				if(entry.getNewCustomer()!=-1) {
					sql += " newCustomer=?  ";
				}
				if(entry.getReason()!=null&&!"".equals(entry.getReason())){

					sql+=" reason=?,entry_person=?,entry_time=getdate() ";
				}
//		if(StringUtils.isNotEmpty(entry.getKingdeeName())){
//			sql+=" kingdee_name=?,entry_time=getdate() ";
//		}
				sql+="where id = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(entry.getNewCustomer()!=-1) {
				i++;
				stmt.setInt(i, entry.getNewCustomer());
			}
			if(entry.getReason()!=null&&!"".equals(entry.getReason())){
				i++;
				stmt.setString(i,entry.getReason() );
				i++;
				stmt.setString(i,entry.getEntryPerson() );

			}
//			if(StringUtils.isNotEmpty(entry.getKingdeeName())){
//				i++;
//				stmt.setString(i,entry.getKingdeeName() );
//			}

			stmt.setInt(i+1,entry.getId());
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
    public int updateKingdeeInfo(AccountEntryForm entry) {

        int result = 0;

        if(selectKingdeeInFo(entry.getKingdeeName())>0){
            result = updateKingdeeId(entry.getKingdeeName(), entry.getKingdeeId());
        }else{
            result = insertKingdeeInFo(entry.getKingdeeName(), entry.getKingdeeId());
        }
        return result;
    }

    public int selectKingdeeInFo(String kingdeeName)
    {
        int result = 0;
        ResultSet rs = null;
        Connection conn = SQLDBhelper.getConnection();
        String insertErrorSql = "select count(1) cn from kingdee_info where kingdee_name=?";
        PreparedStatement errorstmt = null;
        try {
            errorstmt = conn.prepareStatement(insertErrorSql);
            errorstmt.setString(1, kingdeeName);

            rs = errorstmt.executeQuery();
            while(rs.next()) {
                result = rs.getInt("cn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                errorstmt.close();
                SQLDBhelper.returnConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int insertKingdeeInFo(String kingdeeName, String kingdeeId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        String sql = "insert into kingdee_info(kingdee_id,kingdee_name) values(?,?)";
        conn = SQLDBhelper.getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, kingdeeId);
            stmt.setString(2,kingdeeName);

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



    public int updateKingdeeId(String kingdeeName, String kingdeeId)
    {
        int result = 0;
        Connection conn = SQLDBhelper.getConnection();
        String insertErrorSql = "update kingdee_info set kingdee_id=? ,update_time=getdate() where kingdee_name=? ";
        PreparedStatement errorstmt = null;
        try {
            errorstmt = conn.prepareStatement(insertErrorSql);

            errorstmt.setString(1, kingdeeId);
            errorstmt.setString(2, kingdeeName);
            result = errorstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                errorstmt.close();
                SQLDBhelper.returnConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


	@Override
	public List<cerong.erp.entity.AccountEntryForm> getAll(String time1) {
   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMdd");
   String date="";
    try {
		Date dateTime = sdf.parse(time1);
		 date = sdf1.format(dateTime);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   List<AccountEntryForm> list = new ArrayList<AccountEntryForm>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = "  select account.*,isnull(pre.salesSubmission,0)salesSubmission from AccountEntryForm account left join (select AmountClaimFormId,min(salesSubmission)salesSubmission from  PreparatorEntryForm group by AmountClaimFormId) pre on account.id=pre.AmountClaimFormId where account.DataProcessing=1 and account.TransactionDate=? and BeneficiaryAccountBank='中国银行上海市福州路支行'	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, date);
			rs = stmt.executeQuery();
			while(rs.next()) {
				AccountEntryForm con=new AccountEntryForm();
			    con.setTransactionDate(time1);
			    con.setTransactionReferenceNumber(rs.getString("transactionReferenceNumber"));
			    con.setTradeAmount(rs.getDouble("tradeAmount"));
			    con.setPayersName(rs.getString("payersName"));
			    con.setBeneficiaryAccountBank(rs.getString("beneficiaryAccountBank"));
			    con.setConjecture(rs.getString("conjecture"));
			    con.setClaimant(rs.getString("claimant"));
			    con.setClaimTime(rs.getString("claimTime"));
			    con.setRemark(rs.getString("remark"));
			    con.setTradeCurrency(rs.getString("tradeCurrency"));
			    con.setPayeeAccount(rs.getString("payeeAccount"));
			    con.setnBEmailId(rs.getInt("nBEmailId"));
			    con.setSalesConfirmationAmount(rs.getInt("salesSubmission"));
			    con.setNewCustomer(rs.getInt("newCustomer"));
			    con.setInvoice(rs.getString("invoice"));
			    con.setId(rs.getInt("id"));
			    list.add(con);
			}
			}catch(Exception e){
			
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
	public int updateFirstSumMoney(int id, double firstSumMoney1) {
		Date dt = new Date();  
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String  time=sdf.format(dt);
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update AccountEntryForm set TradeAmount=?  where id = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, firstSumMoney1);
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
	public int insertSecondSumMoney(int id, double secondSumMoney) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = " insert into AccountEntryForm(TransactionDate,TransactionReferenceNumber,TradeAmount,BeneficiaryAccountBank,"
				+ "PayersName,PayeeAccount,Remark,TradeCurrency,createTime,conjecture,NBEmailId)"
				+ "select TransactionDate,TransactionReferenceNumber,"+secondSumMoney+",BeneficiaryAccountBank,PayersName,PayeeAccount,Remark,TradeCurrency,createTime,conjecture,NBEmailId from AccountEntryForm where  id=?";
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
	public List<AccountEntryForm> inquireIntoAccounts(AccountEntryForm accountEntryForm) {
List<AccountEntryForm> list = new ArrayList<AccountEntryForm>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = "  select account.*,isnull(pre.salesSubmission,0)salesSubmission from AccountEntryForm account left join (select AmountClaimFormId,min(salesSubmission)salesSubmission from  PreparatorEntryForm group by AmountClaimFormId) pre on account.id=pre.AmountClaimFormId where 1=1 ";
	    if(accountEntryForm.getClaimant()!=null&&!"".equalsIgnoreCase(accountEntryForm.getClaimant())){
	    	sql+= "and Claimant  like ? ";
	   		
	    }
	    sql+=  " order by TransactionDate desc, DataProcessing ASC	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			if(accountEntryForm.getClaimant()!=null&&!"".equalsIgnoreCase(accountEntryForm.getClaimant())){
				stmt.setString(1, "%"+accountEntryForm.getClaimant().trim()+"%");
		   	}
			rs = stmt.executeQuery();
			while(rs.next()) {
				AccountEntryForm con=new AccountEntryForm();
			    con.setTransactionDate(rs.getString("transactionDate"));
			    con.setTransactionReferenceNumber(rs.getString("transactionReferenceNumber"));
			    con.setTradeAmount(rs.getDouble("tradeAmount"));
			    con.setPayersName(rs.getString("payersName"));
			    con.setBeneficiaryAccountBank(rs.getString("beneficiaryAccountBank"));
			    con.setConjecture(rs.getString("conjecture"));
			    con.setClaimant(rs.getString("claimant"));
			    con.setClaimTime(rs.getString("claimTime"));
			    con.setRemark(rs.getString("remark"));
			    con.setTradeCurrency(rs.getString("tradeCurrency"));
			    con.setPayeeAccount(rs.getString("payeeAccount"));
			    con.setnBEmailId(rs.getInt("nBEmailId"));
			    con.setSalesConfirmationAmount(rs.getInt("salesSubmission"));
			    con.setNewCustomer(rs.getInt("newCustomer"));
			    con.setDataProcessing(rs.getInt("dataProcessing"));
			     con.setId(rs.getInt("id"));
			     int id=rs.getInt("id");
			   int  salesSubmission= rs.getInt("salesSubmission");   
			   if(salesSubmission==1){
				   List<PreparatorEntryForm> list1 = new ArrayList<PreparatorEntryForm>();
					
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
				    ResultSet rs1 = null;
				    String sql1 = " select * from PreparatorEntryForm where amountClaimFormId=?	";
					conn1= SQLDBhelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setInt(1, id);
						rs1 = stmt1.executeQuery();
						while(rs1.next()) {
							PreparatorEntryForm con1=new PreparatorEntryForm();
						    con1.setAmountClaimFormId(rs1.getInt("amountClaimFormId"));
						    con1.setInvoice(rs1.getString("invoice"));
						    con1.setIimoney(rs1.getDouble("iimoney"));
						    con1.setIfmoney(rs1.getDouble("ifmoney"));
						    con1.setIid(rs1.getInt("iid"));
						    con1.setSalesSubmission(rs1.getInt("salesSubmission"));
							list1.add(con1);
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
					con.setAccessories(list1);
			   } else{
				   con.setAccessories(null);
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
	public double getAllMoney(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    double total=0.0;
	   String sql = " select TradeAmount from AccountEntryForm where id=? 	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				total=rs.getDouble(1);	
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
	public AmountClaimForm getAmount(String invoice) {
		AmountClaimForm con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	   String sql = "  select * from AmountClaimForm  where DataProcessing=0 and invoice=?	";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, invoice);
			rs = stmt.executeQuery();
			if(rs.next()) {
				 con=new AmountClaimForm();
			    con.setId(rs.getInt("id"));
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
}
