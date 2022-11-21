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

import cerong.erp.entity.*;
import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.jdbc.SQLDBhelper1;
import cerong.erp.jdbc.DBHelper;
import org.apache.commons.lang.StringUtils;


public  class ItCaseIdDao implements ItCaseIdDaoImpl  {

	@Override
	public int updateSaleName(String saleName, String strProjectId) {
		String sql = "update itemCase set CustomerManager=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,saleName );
			stmt.setString(2, strProjectId);
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
	public int updateSaleName1(String saleName1, String strProjectId) {
		String sql = "update itemCase set MerchandManager1=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,saleName1 );
			stmt.setString(2, strProjectId);
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
	public int deleteSaleName(String strProjectId) {
		String sql = "update itemCase set CustomerManager='' where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, strProjectId);
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
	public int deleteSaleName1(String strProjectId) {
		String sql = "update itemCase set MerchandManager1='' where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, strProjectId);
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
	public int addItCase(String file1,String backcase,String caseNo, int cid,
			String content, String saleName, String saleName1,
			String projectDesce, String projectDescc, int ddlType,
			String orderGroup, String customerGroup, int ddlSelectPriceDays,String time, String quotername) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "insert into itemCase(CaseNo,ProductDescE,ProductDescC,ProductSort"
				+ ",CustomerManager,MerchandManager1,EmailContent,CreateTime,UpdateTime,customercode,BackSingleCase,DeliveryTime,CaseStatus,Upload,Engineer1) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, caseNo);
			stmt.setString(2,projectDesce );
			stmt.setString(3,projectDescc );
			stmt.setInt(4,ddlType );
			stmt.setString(5,saleName );
			stmt.setString(6,saleName1 );
			stmt.setString(7,content );
			stmt.setString(8,time );
			stmt.setString(9,time );
			stmt.setInt(10,cid );
			stmt.setString(11,backcase );
			stmt.setString(12,time );
			stmt.setInt(13,4 );
			stmt.setString(14,file1 );
			stmt.setString(15,quotername );
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
	public String getCaseNo() {
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select substring(CaseNo,4,8)+1 from itemCase where id=(select MAX(id) from itemCase  where BackSingleCase='' and case_type=0)";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
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


        String strNum =result;

        //strNum = "00000" + strNum;




       str = "SHS" + strNum;
        return str;
	}

	@Override
	public int addCase(String title, String content, String caseNo,
			String empName, String time, String file1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "insert into postback(caseno,title,content,empname"
				+ ",posttime,uploadfile) values(?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseNo);
			stmt.setString(2,title );
			stmt.setString(3,content );
			stmt.setString(4,empName );
			stmt.setString(5,time );
			stmt.setString(6,file1 );

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
	public String getCaseNo1(String projectId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select CaseNo from itemCase    where BackSingleCase=? order by id desc";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
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



        return result;
	}

	@Override
	public int addItCase(String backcase, String caseNo, int cid,
			String content, String saleName, String saleName1,
			String projectDesce, String projectDescc, int ddlSelectPriceDays,
			String time,String quotername) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "insert into itemCase(CaseNo,ProductDescE,ProductDescC"
				+ ",CustomerManager,MerchandManager1,EmailContent,CreateTime,UpdateTime,customercode,BackSingleCase,DeliveryTime,CaseStatus,Engineer1) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, caseNo);
			stmt.setString(2,projectDesce );
			stmt.setString(3,projectDescc );
			stmt.setString(4,saleName );
			stmt.setString(5,saleName1 );
			stmt.setString(6,content );
			stmt.setString(7,time );
			stmt.setString(8,time );
			stmt.setInt(9,cid );
			stmt.setString(10,backcase );
			stmt.setString(11,time );
			stmt.setInt(12,0 );
			stmt.setString(13,quotername );

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
	public String getproject() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String projectId="";
		int CaseStatus=0;
        String sql = "select * from itemCase";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				CaseStatus=rs.getInt("CaseStatus");
				if(CaseStatus==0||CaseStatus==14){
					projectId+=","+ rs.getString("CaseNo");
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



        return projectId;
	}

	@Override
	public List<ItemCase> getall() {
		List<ItemCase>list =new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
        String sql = "select * from itemCase";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				itcase=new ItemCase();
				itcase.setCid(rs.getInt("customercode"));
				itcase.setCreateTime(rs.getString("CreateTime"));
				itcase.setProjectDescc(rs.getString("ProductDescC"));
				itcase.setProjectDesce(rs.getString("ProductDescE"));
				itcase.setUpload(rs.getString("Upload"));
				itcase.setCaseNo(rs.getString("CaseNo"));
				list.add(itcase);
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
	public List<ItemCase1> getall1() {
		List<ItemCase1>list =new ArrayList<ItemCase1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase1 itcase=null;
		String time="";
		String orderid="";
        String sql = "select * from itemCase   ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				itcase=new ItemCase1();
				itcase.setCreateTime(rs.getString("CreateTime"));
				itcase.setCustomerId(rs.getInt("customercode"));
				itcase.setCaseStatus(rs.getString("CaseStatus"));
				itcase.setOrderId(rs.getString("CaseNo"));
				orderid=rs.getString("CaseNo");
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				String money="";
				ResultSet rs2 = null;
				String sql2 = "select iSum from InvoiceInfo  where iCaseNo=?   ";
				conn2 = SQLDBhelper.getConnection();

				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, orderid);
					rs2 = stmt2.executeQuery();
					if(rs2.next()){
						money=rs2.getString("iSum");
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
					SQLDBhelper1.close(conn2,stmt2,rs2);

				}
				String project=rs.getString("CaseNo");
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

				itcase.setAmount(money);
				itcase.setSaildate(time);
				list.add(itcase);
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
	public List<ItemCase> getall(int cid) {
		List<ItemCase>list =new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
        String sql = "select * from itemCase where customercode=? order by id  desc";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cid);
			rs = stmt.executeQuery();
			while(rs.next()) {
				itcase=new ItemCase();
				itcase.setCid(rs.getInt("customercode"));
				itcase.setCreateTime(rs.getString("CreateTime"));
				itcase.setProjectDescc(rs.getString("ProductDescC"));
				itcase.setProjectDesce(rs.getString("ProductDescE"));
				itcase.setUpload(rs.getString("Upload"));
				itcase.setCaseNo(rs.getString("CaseNo"));
				itcase.setSaleName1(rs.getString("CustomerManager"));
				itcase.setSaleName2(rs.getString("MerchandManager1"));
				list.add(itcase);
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
	public int getalltotal(int cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
		int total=0;
        String sql = "select count(*) from itemCase where customercode=?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				total=rs.getInt(1);
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



        return total;
	}

	@Override
	public ItemCase getall(String caseno) {
		ItemCase itcase=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select CaseNo,zhijian1,zhijian2,CustomerManager,MerchandManager1,MerchandManager2,Engineer1,Engineer2,Engineer3,Merchandising,CreateTime,ProductDescC,ProductDescE,customercode,all_money,CaseStatus from itemCase  where CaseNo=? ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseno);
			rs = stmt.executeQuery();
			if(rs.next()) {
				itcase=new ItemCase();
				itcase.setCreateTime(rs.getString("CreateTime"));
				itcase.setProjectDescc(rs.getString("ProductDescC"));
				itcase.setProjectDesce(rs.getString("ProductDescE"));
				itcase.setCaseNo(rs.getString("CaseNo"));
				itcase.setSaleName1(rs.getString("CustomerManager"));
				itcase.setSaleName2(rs.getString("MerchandManager1"));
				itcase.setZhijian1(rs.getString("zhijian1"));
				itcase.setZhijian2(rs.getString("zhijian2"));
				itcase.setMerchandManager2(rs.getString("MerchandManager2"));
				itcase.setEngineer1(rs.getString("Engineer1"));
				itcase.setEngineer2(rs.getString("Engineer2"));
				itcase.setEngineer3(rs.getString("Engineer3"));
				itcase.setMerchandising(rs.getString("merchandising"));
				itcase.setCid(rs.getInt("customercode"));
				itcase.setAllmoney(rs.getDouble("all_money"));
				itcase.setCaseStatus(rs.getInt("CaseStatus"));
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



        return itcase;
	}

	@Override
	public int updatequoter(String quoter, String strProjectId) {
		String sql = "update itemCase set Engineer1=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,quoter );
			stmt.setString(2, strProjectId);
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
	public int deletequoter(String strProjectId) {
		String sql = "update itemCase set Engineer1=null where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, strProjectId);
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
	public Invoiceinfo1 getall1(String projectId) {
		Invoiceinfo1 info =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
        String sql = "select it.EmailContent,it.ProductDescE,it.ProductDescC, it.CustomerManager,it.MerchandManager1,it.customercode,cus.name"
        		+ " from itemCase it left join Customer cus on it.customercode=cus.id where it.CaseNo=?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				info=new Invoiceinfo1();
				info.setEid(rs.getInt("customercode"));
				info.setEmailcontent(rs.getString("EmailContent"));
				info.setCustomermanager(rs.getString("CustomerManager"));

				info.setMerchandmanager1(rs.getString("MerchandManager1"));
				info.setName(rs.getString("name"));
				info.setProductdescc(rs.getString("ProductDescC"));
				info.setProductdesce(rs.getString("ProductDescE"));
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
	public List<ItemCase2> getalll() {
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

        String sql = "select * from itemCase where YEAR(CreateTime)=2017 and month(followtime)=9 and DAY(followtime)>13 ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase2 info=new ItemCase2();
				info.setCaseNo(rs.getString("CaseNo"));
				info.setCustomerManager(rs.getString("CustomerManager"));
				info.setEngineer1(rs.getString("Engineer1"));
				info.setEngineer2(rs.getString("Engineer2"));
				info.setEngineer3(rs.getString("Engineer3"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setZhijian1(rs.getString("zhijian1"));
				info.setZhijian2(rs.getString("zhijian2"));
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
	public int geteid(String projectId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id=0;
        String sql = "select customercode from itemCase where CaseNo=? ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			rs = stmt.executeQuery();
			if(rs.next()) {
			id=rs.getInt(1)	;
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



        return id;
	}

	@Override
	public int updateprojectnote(String projectId, String projectnote) {
		String sql = "update itemCase set projectnote=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,projectnote );
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
	public List<ItemCase2> getPurchasingSystem() {
		 Calendar cal = Calendar.getInstance();
	    int year = cal.get(Calendar.YEAR);//获取年份
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 String sql = "select * from itemCase it left join(select MIN(b.FactoryName) FactoryName,a.CaseNo from FactoryFund AS a   LEFT JOIN FactoryInfo AS b ON a.fid = b.id group by a.CaseNo) m on m.CaseNo=it.CaseNo "
        		 +" left join (select a.CaseNo,completiontime,DateSample,statetime from (select max(completiontime)completiontime,CaseNo  from Bargain  where completiontime>'2000-01-01' group by CaseNo )b "
        		 +" left join (select CaseNo ,max(DateSample)DateSample from Bargain  where DateSample>'2000-01-01' group by CaseNo)d on b.caseno=d.caseno left join (select CaseNo,min(inputDate)statetime from  FactoryFund group by CaseNo)a on a.CaseNo=b.CaseNo"
        		 +" left join (select icaseno,min(ifdate)ifdate from invoiceinfo where ifmoney is not null and ifmoney!=0 group by icaseno )ii on ii.icaseno=b.caseno)c "
        		 +" on c.caseno=it.caseno  where ( it.CaseStatus=14 or it.CaseStatus=0)   and it.CreateTime>?";

		 Connection conn  = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,year+"" );
			rs = stmt.executeQuery();
			while(rs.next()) {
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				Long millionSeconds1=format.parse(time).getTime();
				Long millionSeconds2=millionSeconds1-7200000;
				String updatetime=rs.getString("followtime");
				Long millionSeconds3=format.parse(updatetime).getTime();
				if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
				ItemCase2 info=new ItemCase2();
				info.setCaseNo(rs.getString("CaseNo"));
				String MerchandManager1=rs.getString("MerchandManager1");

				String Merchandising=rs.getString("Merchandising");
				if(Merchandising!=null&&!"".equals(Merchandising)){
					info.setMerchandManager1(Merchandising);
				}else{
					info.setMerchandManager1(MerchandManager1);
				}

				info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setFactoryName(rs.getString("FactoryName"));
				info.setProjectMaterialProperties(rs.getInt("projectMaterialProperties"));
				info.setPlantAnalysis(rs.getInt("ProjectLevel"));
				info.setZhijian1(rs.getString("zhijian1"));
				info.setZhijian2(rs.getString("zhijian2"));
				//info.setFirstPaymentDate(rs.getString("ifdate"));
				info.setCompletionTime(rs.getString("completiontime"));
				info.setDateSample(rs.getString("DateSample"));
				info.setDateSampleUploading(rs.getString("statetime"));
				list.add(info);

				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
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
	public List<ItemCase2> getPurchasingSystem1(String projectId) {
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

        String sql = "select * from itemCase it left join(select MIN(b.FactoryName) FactoryName,a.CaseNo from FactoryFund AS a   LEFT JOIN FactoryInfo AS b ON a.fid = b.id group by a.CaseNo) m on m.CaseNo=it.CaseNo where it.CaseNo=?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,projectId );
			rs = stmt.executeQuery();
			while(rs.next()) {
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String time=format.format(date);
				Long millionSeconds1=format.parse(time).getTime();
				Long millionSeconds2=millionSeconds1-3600000;
				String updatetime=rs.getString("followtime");
				Long millionSeconds3=format.parse(updatetime).getTime();
				if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
				ItemCase2 info=new ItemCase2();
				info.setCaseNo(rs.getString("CaseNo"));
				String CustomerManager=rs.getString("CustomerManager");
				String MerchandManager1=rs.getString("MerchandManager1");
				String MerchandManager2=rs.getString("MerchandManager2");
				String Engineer1=rs.getString("Engineer1");
				String Engineer2=rs.getString("Engineer2");
				String Engineer3=rs.getString("Engineer3");


				String s2 = "LynnYuanannazhuElaineShengIvyWuKathyPanSherryZhouminniewuellazhuShirleyYurosegaoThomasChenjennyguoKristinemeiJanezhouRitajiangNataliaLijennyguoRitajiang";
				Boolean index5=false;
				Boolean index6=false;
				if(CustomerManager!=null&&!"".equals(CustomerManager)){
					index5 = s2.toLowerCase().contains(CustomerManager.toLowerCase());
					if(index5!=false){
					info.setMerchandManager1(rs.getString("CustomerManager"));
					}else{
						if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
							index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
							if(index6!=false){
							info.setMerchandManager1(rs.getString("MerchandManager1"));
							}
						}
					}
				}else{
					if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
						index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
						if(index6!=false){
						info.setMerchandManager1(rs.getString("MerchandManager1"));
						}
					}
				}





				String s1 = "HouQiangMichealYinBensonzhangThomasChensunjinshuxuweixupingzhaoqiangRogerQiu";
				Boolean index1=false;
				Boolean index2=false;
				Boolean index3=false;
				Boolean index4=false;
				if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
				index1 = s1.toLowerCase().contains(MerchandManager2.toLowerCase());
				if(index1!=false){
					info.setMerchandManager2(rs.getString("MerchandManager2"));
				}else{
					if(Engineer1!=null&&!"".equals(Engineer1)){
					index2 = s1.toLowerCase().contains(Engineer1.toLowerCase());
					if(index2!=false){
						info.setMerchandManager2(rs.getString("Engineer1"));
					}else{
						if(Engineer2!=null&&!"".equals(Engineer2)){
							index2 = s1.toLowerCase().contains(Engineer2.toLowerCase());
							if(index2!=false){
								info.setMerchandManager2(rs.getString("Engineer2"));
							}else{
								if(Engineer3!=null&&!"".equals(Engineer3)){
									index2 = s1.toLowerCase().contains(Engineer3.toLowerCase());
									if(index2!=false){
										info.setMerchandManager2(rs.getString("Engineer3"));
									}
									}
							}
							}
					}
					}else{
						if(Engineer2!=null&&!"".equals(Engineer2)){
							index2 = s1.toLowerCase().contains(Engineer2.toLowerCase());
							if(index2!=false){
								info.setMerchandManager2(rs.getString("Engineer2"));
							}else{
								if(Engineer3!=null&&!"".equals(Engineer3)){
									index2 = s1.toLowerCase().contains(Engineer3.toLowerCase());
									if(index2!=false){
										info.setMerchandManager2(rs.getString("Engineer3"));
									}
									}
							}
						}else{
							if(Engineer3!=null&&!"".equals(Engineer3)){
								index2 = s1.toLowerCase().contains(Engineer3.toLowerCase());
								if(index2!=false){
									info.setMerchandManager2(rs.getString("Engineer3"));
								}
								}
						}
					}

				}
				}else{
					if(Engineer1!=null&&!"".equals(Engineer1)){
						index2 = s1.toLowerCase().contains(Engineer1.toLowerCase());
						if(index2!=false){
							info.setMerchandManager2(rs.getString("Engineer1"));
						}else{
							if(Engineer2!=null&&!"".equals(Engineer2)){
								index2 = s1.toLowerCase().contains(Engineer2.toLowerCase());
								if(index2!=false){
									info.setMerchandManager2(rs.getString("Engineer2"));
								}else{
									if(Engineer3!=null&&!"".equals(Engineer3)){
										index2 = s1.toLowerCase().contains(Engineer3.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs.getString("Engineer3"));
										}
										}
								}
								}else{
									if(Engineer3!=null&&!"".equals(Engineer3)){
										index2 = s1.toLowerCase().contains(Engineer3.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs.getString("Engineer3"));
										}
										}
								}
						}
					}else{
						if(Engineer2!=null&&!"".equals(Engineer2)){
							index2 = s1.toLowerCase().contains(Engineer2.toLowerCase());
							if(index2!=false){
								info.setMerchandManager2(rs.getString("Engineer2"));
							}else{
								if(Engineer3!=null&&!"".equals(Engineer3)){
									index2 = s1.toLowerCase().contains(Engineer3.toLowerCase());
									if(index2!=false){
										info.setMerchandManager2(rs.getString("Engineer3"));
									}
									}
							}
						}else{
							if(Engineer3!=null&&!"".equals(Engineer3)){
								index2 = s1.toLowerCase().contains(Engineer3.toLowerCase());
								if(index2!=false){
									info.setMerchandManager2(rs.getString("Engineer3"));
								}
								}
						}
					}
				}
				if((info.getMerchandManager1()!=null&&!"".equals(info.getMerchandManager1()))
						||(info.getMerchandManager2()!=null&&!"".equals(info.getMerchandManager2()))){

				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setFactoryName(rs.getString("FactoryName"));
				list.add(info);
				}
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

	@Override
	public List<ItemCase> getAllItem(ItemCase it) {

		List<ItemCase>list =new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
		if(it.getInspectionRequirements()!=0||(it.getCreateTime()!=null&&!"".equals(it.getCreateTime()))){
			int i=0;
			String sql="";
			if(it.getCreateTime()!=null&&!"".equals(it.getCreateTime())){
			List<POupload>accessories =new ArrayList<POupload>();
			Connection conn1 = null;
			PreparedStatement stmt1 = null;
			ResultSet rs1 = null;
			POupload po=null;
			String sql1 = "select * from (select it.CreateTime,it.zhijian1,it.zhijian2, po.CaseNo,po.intro,po.url,po.type,CONVERT(varchar(100), uploadtime, 23)uploadtime from POupload po left join itemcase it on it.caseno=po.caseno where   po.type='JIANYANBAOGAO')a  where  a.uploadtime = ? order by a.uploadtime DESC ";
			conn1 = SQLDBhelper.getConnection();
			try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, it.getCreateTime());
				rs1 = stmt1.executeQuery();
				while(rs1.next()) {
				itcase=new ItemCase();
				List<POupload>accessories1 =new ArrayList<POupload>();
				POupload po1=new	POupload();
				String CaseNo= rs1.getString("CaseNo");
				String type=rs1.getString("type");
				if("JIANYANBAOGAO".equals(type)){
				po1.setCaseNo(rs1.getString("CaseNo"));
				po1.setIntro(rs1.getString("intro"));
				po1.setUrl(rs1.getString("url"));
				po1.setUploadtime(rs1.getString("uploadtime"));
				po1.setType(rs1.getString("type"));
				po1.setStatus(1);
				accessories1.add(po1);
				}else if("JIANYANJIHUAZJ".equals(type)){
					po1.setCaseNo(rs1.getString("CaseNo"));
					po1.setIntro(rs1.getString("intro"));
					po1.setUrl(rs1.getString("url"));
					po1.setUploadtime(rs1.getString("uploadtime"));
					po1.setType(rs1.getString("type"));
					po1.setStatus(0);
					accessories1.add(po1);
				}
				List<TuZhi>lisy1=new ArrayList<TuZhi>();
				Connection conna = null;
				PreparedStatement stmta = null;
				ResultSet rsa = null;
				String	sqla = "select * from tuzhi  where caseno like '%"+CaseNo+"%'  order by id desc";
				conna = SQLDBhelper.getConnection();
		        try {
					stmta = conna.prepareStatement(sqla);
					rsa = stmta.executeQuery();
					while(rsa.next()) {
						TuZhi tuZhi=new TuZhi();
						String caseNo=rsa.getString("caseNo");
						String zhongwen=  rsa.getString("zhongwen");
						if(zhongwen!=null&&!"".equals(zhongwen)){
							tuZhi.setUploadtime(rsa.getString("UploadDate"));
						}
						String yingwen=  rsa.getString("yingwen");
						if(yingwen!=null&&!"".equals(yingwen)){
							tuZhi.setUploadTime1(rsa.getString("UploadDate"));
						}
						    tuZhi.setCname(rsa.getString("zhongwen"));
							tuZhi.setEname(rsa.getString("yingwen"));
							tuZhi.setName(rsa.getString("name"));
							lisy1.add(tuZhi);



					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsa != null) {
						try {
							rsa.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmta != null) {
						try {
							stmta.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conna,stmta,rsa);
				}
		        if(it.getDrawingPicture()==2){
		        if(lisy1.size()>0){
		        itcase.setTuzhi(lisy1);
				itcase.setAccessories(accessories1);
				itcase.setCaseNo(rs1.getString("CaseNo"));
				itcase.setCreateTime(rs1.getString("CreateTime"));
				itcase.setZhijian1(rs1.getString("zhijian1"));
				itcase.setZhijian2(rs1.getString("zhijian2"));
				list.add(itcase);
		        }
		        }else{
		        	 itcase.setTuzhi(lisy1);
						itcase.setAccessories(accessories1);
						itcase.setCaseNo(rs1.getString("CaseNo"));
						itcase.setCreateTime(rs1.getString("CreateTime"));
						itcase.setZhijian1(rs1.getString("zhijian1"));
						itcase.setZhijian2(rs1.getString("zhijian2"));
						list.add(itcase);
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
							SQLDBhelper.close(conn1,stmt1,rs1);
						}

				}else if(it.getInspectionRequirements()!=0){
					 sql = "select it.CaseNo,it.CreateTime,it.zhijian1,it.zhijian2 from itemCase it left join(select sum(inv.iimoney)iimoney,iCaseNo from "
			        		+ " InvoiceInfo inv group by iCaseNo )inv on inv.iCaseNo=it.CaseNo where  "
			        		+ " inv.iimoney is not null "
			        		+ " and datediff(day,it.CreateTime,getdate())<240"
			        		  +" order by it.id desc";
			        conn = SQLDBhelper.getConnection();
			        try {
						stmt = conn.prepareStatement(sql);

						rs = stmt.executeQuery();
						while(rs.next()) {
							itcase=new ItemCase();
							String CaseNo= rs.getString("CaseNo");
							List<POupload>accessories =new ArrayList<POupload>();
							Connection conn1 = null;
							PreparedStatement stmt1 = null;
							ResultSet rs1 = null;
							POupload po=null;
							String sql1="";
							if(it.getInspectionRequirements()==1){
								sql1 = "select CaseNo,intro,url,type,CONVERT(varchar(100), uploadtime, 23)uploadtime from POupload where CaseNo=? and type !='JIANYANJIHUAZJ'  ";
							}else if(it.getInspectionRequirements()==2){
								sql1 = "select CaseNo,intro,url,type,CONVERT(varchar(100), uploadtime, 23)uploadtime from POupload where CaseNo=? and type !='JIANYANBAOGAO' ";
							}

					        conn1 = SQLDBhelper.getConnection();
					        try {
								stmt1 = conn1.prepareStatement(sql1);
								stmt1.setString(1,CaseNo );
								rs1 = stmt1.executeQuery();
								while(rs1.next()) {
								po=new	POupload();
								String type=rs1.getString("type");
								if("JIANYANBAOGAO".equals(type)){
								po.setCaseNo(rs1.getString("CaseNo"));
								po.setIntro(rs1.getString("intro"));
								po.setUrl(rs1.getString("url"));
								po.setUploadtime(rs1.getString("uploadtime"));
								po.setType(rs1.getString("type"));
								po.setStatus(1);
								accessories.add(po);
								}else if("JIANYANJIHUAZJ".equals(type)){
									po.setCaseNo(rs1.getString("CaseNo"));
									po.setIntro(rs1.getString("intro"));
									po.setUrl(rs1.getString("url"));
									po.setUploadtime(rs1.getString("uploadtime"));
									po.setType(rs1.getString("type"));
									po.setStatus(0);
									accessories.add(po);
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
								SQLDBhelper.close(conn1,stmt1,rs1);
							}
					        List<TuZhi>lisy1=new ArrayList<TuZhi>();
							Connection conna = null;
							PreparedStatement stmta = null;
							ResultSet rsa = null;
							String	sqla = "select * from tuzhi  where caseno like '%"+CaseNo+"%'  order by id desc";
							conna = SQLDBhelper.getConnection();
					        try {
								stmta = conna.prepareStatement(sqla);
								rsa = stmta.executeQuery();
								while(rsa.next()) {
									TuZhi tuZhi=new TuZhi();
									String caseNo=rsa.getString("caseNo");
									String zhongwen=  rsa.getString("zhongwen");
									if(zhongwen!=null&&!"".equals(zhongwen)){
										tuZhi.setUploadtime(rsa.getString("UploadDate"));
									}
									String yingwen=  rsa.getString("yingwen");
									if(yingwen!=null&&!"".equals(yingwen)){
										tuZhi.setUploadTime1(rsa.getString("UploadDate"));
									}
									    tuZhi.setCname(rsa.getString("zhongwen"));
										tuZhi.setEname(rsa.getString("yingwen"));

										tuZhi.setName(rsa.getString("name"));

										lisy1.add(tuZhi);



								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsa != null) {
									try {
										rsa.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmta != null) {
									try {
										stmta.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.close(conna,stmta,rsa);
							}
					        if(it.getDrawingPicture()==2){
						        if(lisy1.size()>0){
						        itcase.setTuzhi(lisy1);
								itcase.setAccessories(accessories);
								itcase.setCaseNo(rs.getString("CaseNo"));
								itcase.setCreateTime(rs.getString("CreateTime"));
								itcase.setZhijian1(rs.getString("zhijian1"));
								itcase.setZhijian2(rs.getString("zhijian2"));
								list.add(itcase);
						        }
						        }else{
						        	 itcase.setTuzhi(lisy1);
										itcase.setAccessories(accessories);
										itcase.setCaseNo(rs.getString("CaseNo"));
										itcase.setCreateTime(rs.getString("CreateTime"));
										itcase.setZhijian1(rs.getString("zhijian1"));
										itcase.setZhijian2(rs.getString("zhijian2"));
										list.add(itcase);
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

					}



		}else{
        String sql = "select it.CaseNo,it.CreateTime,it.zhijian1,it.zhijian2 from itemCase it left join(select sum(inv.iimoney)iimoney,iCaseNo from "
        		+ " InvoiceInfo inv group by iCaseNo )inv on inv.iCaseNo=it.CaseNo where  "
        		+ " inv.iimoney is not null "
        		+ " and datediff(day,it.CreateTime,getdate())<240"
        		  +" order by it.id desc";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				itcase=new ItemCase();
				String CaseNo= rs.getString("CaseNo");
				List<POupload>accessories =new ArrayList<POupload>();
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				POupload po=null;
		        String sql1 = "select CaseNo,intro,url,type,CONVERT(varchar(100), uploadtime, 23)uploadtime from POupload where CaseNo=?  ";
		        conn1 = SQLDBhelper.getConnection();
		        try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1,CaseNo );
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					po=new	POupload();
					String type=rs1.getString("type");
					if("JIANYANBAOGAO".equals(type)){
					po.setCaseNo(rs1.getString("CaseNo"));
					po.setIntro(rs1.getString("intro"));
					po.setUrl(rs1.getString("url"));
					po.setUploadtime(rs1.getString("uploadtime"));
					po.setType(rs1.getString("type"));
					po.setStatus(1);
					accessories.add(po);
					}else if("JIANYANJIHUAZJ".equals(type)){
						po.setCaseNo(rs1.getString("CaseNo"));
						po.setIntro(rs1.getString("intro"));
						po.setUrl(rs1.getString("url"));
						po.setUploadtime(rs1.getString("uploadtime"));
						po.setType(rs1.getString("type"));
						po.setStatus(0);
						accessories.add(po);
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
					SQLDBhelper.close(conn1,stmt1,rs1);
				}

		        List<TuZhi>lisy1=new ArrayList<TuZhi>();
				Connection conna = null;
				PreparedStatement stmta = null;
				ResultSet rsa = null;
				String	sqla = "select * from tuzhi  where caseno like '%"+CaseNo+"%'  order by id desc";
				conna = SQLDBhelper.getConnection();
		        try {
					stmta = conna.prepareStatement(sqla);
					rsa = stmta.executeQuery();
					while(rsa.next()) {
						TuZhi tuZhi=new TuZhi();
						String caseNo=rsa.getString("caseNo");
						String zhongwen=  rsa.getString("zhongwen");
						if(zhongwen!=null&&!"".equals(zhongwen)){
							tuZhi.setUploadtime(rsa.getString("UploadDate"));
						}
						String yingwen=  rsa.getString("yingwen");
						if(yingwen!=null&&!"".equals(yingwen)){
							tuZhi.setUploadTime1(rsa.getString("UploadDate"));
						}
						    tuZhi.setCname(rsa.getString("zhongwen"));
							tuZhi.setEname(rsa.getString("yingwen"));

							tuZhi.setName(rsa.getString("name"));

							lisy1.add(tuZhi);



					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsa != null) {
						try {
							rsa.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmta != null) {
						try {
							stmta.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conna,stmta,rsa);
				}
		        if(it.getDrawingPicture()==2){
			        if(lisy1.size()>0){
			        itcase.setTuzhi(lisy1);
					itcase.setAccessories(accessories);
					itcase.setCaseNo(rs.getString("CaseNo"));
					itcase.setCreateTime(rs.getString("CreateTime"));
					itcase.setZhijian1(rs.getString("zhijian1"));
					itcase.setZhijian2(rs.getString("zhijian2"));
					list.add(itcase);
			        }
			        }else{
			        	 itcase.setTuzhi(lisy1);
							itcase.setAccessories(accessories);
							itcase.setCaseNo(rs.getString("CaseNo"));
							itcase.setCreateTime(rs.getString("CreateTime"));
							itcase.setZhijian1(rs.getString("zhijian1"));
							itcase.setZhijian2(rs.getString("zhijian2"));
							list.add(itcase);
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

		}
		return list;
	}

	@Override
	public List<ItemCase> getprojectContract(ItemCase it) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);

		 int month = now.get(Calendar.MONTH)+1;
       List<ItemCase>list =new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
        String sql = "  select ii.iCaseNo ,min(CONVERT(varchar, ii.ifdate, 120 ))ifdate from InvoiceInfo ii   "
        		+ " where ifmoney is not null and datediff(day,ifdate,getdate())<120  group by iCaseNo   ";
        conn = SQLDBhelper.getConnection();
        try {
        	if(it.getInspectionRequirements()==0){
        		sql +=" order by ifdate desc";
        	}else if(it.getInspectionRequirements()==1){
        		sql +=" order by ifdate desc";
        	}else if(it.getInspectionRequirements()==2){
        		sql +=" order by ii.iCaseNo desc";
        	}

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				itcase=new ItemCase();
				String CaseNo= rs.getString("iCaseNo");
				List<Contract>accessories =new ArrayList<Contract>();
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				Contract po=null;
				String BargainUrl="";
		        String sql1 = "select ba.BargainUrl,mcp.GeldObject from Bargain ba left join MoneyCheckUp mcp on ba.BargainNo=mcp.BargainNo where ba.CaseNo=?  group by ba.BargainUrl,mcp.GeldObject  ";
		        conn1 = SQLDBhelper.getConnection();
		        try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1,CaseNo );
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					po=new Contract();
					BargainUrl=rs1.getString("BargainUrl");
					po.setContract(rs1.getString("BargainUrl"));
					po.setFactory(rs1.getString("GeldObject"));
					accessories.add(po);
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
		        Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select projectContractNote,CustomerManager,MerchandManager1,MerchandManager2 ,Engineer1 ,Engineer2,Engineer3,Merchandising from itemCase where CaseNo=?";
		        conn2 = SQLDBhelper.getConnection();
		        try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1,CaseNo );
					rs2 = stmt2.executeQuery();
					while(rs2.next()) {


						itcase.setProjectContractNote(rs2.getString("projectContractNote"));
						itcase.setMerchandManager1(rs2.getString("MerchandManager1"));
						itcase.setMerchandManager2(rs2.getString("MerchandManager2"));
						itcase.setMerchandising(rs2.getString("Merchandising"));


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


		        if(it.getCid()!=0&&!"".equals(it.getCid())){
		        if(it.getCid()==1){
		        itcase.setContract(accessories);
				itcase.setCaseNo(rs.getString("iCaseNo"));
				itcase.setCreateTime(rs.getString("ifdate"));
				list.add(itcase);
		        }else if(it.getCid()==2){
		        	if(BargainUrl!=null&&!"".equals(BargainUrl)){}else{
		        		itcase.setContract(accessories);
						itcase.setCaseNo(rs.getString("iCaseNo"));
						itcase.setCreateTime(rs.getString("ifdate"));
						list.add(itcase);
		        	}
		        }else if(it.getCid()==3){
		        	if(BargainUrl!=null&&!"".equals(BargainUrl)){}else{
		        		itcase.setContract(accessories);
						itcase.setCaseNo(rs.getString("iCaseNo"));
						itcase.setCreateTime(rs.getString("ifdate"));
						list.add(itcase);
		        	}
		        }
		        }else{

		        	itcase.setContract(accessories);
					itcase.setCaseNo(rs.getString("iCaseNo"));
					itcase.setCreateTime(rs.getString("ifdate"));
					list.add(itcase);
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

	@Override
	public int updateCaseNo(String reason, String caseNo) {
		String sql = "update itemCase set projectContractNote=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,reason );
			stmt.setString(2, caseNo);
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
	public List<TuZhi> viewAllDrawings(ItemCase it) {

		   Calendar now = Calendar.getInstance();
	       List<TuZhi>list1 =new ArrayList<TuZhi>();
			Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1="";
				if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
					 sql1 = "select * from tuzhi  where caseno like '%"+it.getCaseNo()+"%'  order by id desc";
				}else{
		         sql1 = "select * from tuzhi  where datediff(day,UploadDate,getdate())<90  order by id desc";
				}
		        conn1 = SQLDBhelper.getConnection();
		        try {
					stmt1 = conn1.prepareStatement(sql1);

					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						TuZhi tuZhi=new TuZhi();
						String caseNo=rs1.getString("caseNo");
						Boolean index=false;
						 index = caseNo.toLowerCase().contains("A".toLowerCase());
						 if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
						 if(index!=false){
							 tuZhi.setIsDelete(1);
						}else{
							tuZhi.setIsDelete(0);
						}
						    tuZhi.setId(rs1.getInt("id"));
							tuZhi.setCaseNo(rs1.getString("caseNo"));
							tuZhi.setCname(rs1.getString("zhongwen"));
							tuZhi.setEname(rs1.getString("yingwen"));
							tuZhi.setUploadtime(rs1.getString("UploadDate"));
							tuZhi.setName(rs1.getString("name"));
							list1.add(tuZhi);
						 }else{
							 if(index!=false){

							}else{
								tuZhi.setIsDelete(0);
								tuZhi.setId(rs1.getInt("id"));
								tuZhi.setCaseNo(rs1.getString("caseNo"));
								tuZhi.setCname(rs1.getString("zhongwen"));
								tuZhi.setEname(rs1.getString("yingwen"));
								tuZhi.setUploadtime(rs1.getString("UploadDate"));
								tuZhi.setName(rs1.getString("name"));
								list1.add(tuZhi);
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
					SQLDBhelper.close(conn1,stmt1,rs1);
				}



		        return list1;

	}

	@Override
	public List<MeetingRecord> viewConferenceRecords(MeetingRecord it) {
		Calendar now = Calendar.getInstance();
	       List<MeetingRecord>list1 =new ArrayList<MeetingRecord>();
	       MeetingRecord mr=null;
	       String conferenceMessage="";
	        Connection conn1 = null;
			PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1=null;
				int i=0;
				if(it.getConferenceMessage()!=null&&!"".equals(it.getConferenceMessage())){
					if(it.getNewProjectTime()!=null&&!"".equals(it.getConferenceMessage())){
					sql1="select it.* from  itemcase it where  it.conferenceMessage like ? and it.conferenceMessage like ?";
					}else{
						sql1="select it.* from  itemcase it where  it.conferenceMessage like ?";
					}
				}else{
					if(it.getNewProjectTime()!=null&&!"".equals(it.getConferenceMessage())){
						sql1="select it.* from  itemcase it where  it.conferenceMessage like ?";
					}else{
				if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
					sql1 = "select it.* from  itemcase it where it.Caseno like ? ";
				}else{
					sql1 = "select a.*,it.conferenceMessage,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.Merchandising from (select iCaseNo,min(ifdate)ifdate from InvoiceInfo   group by iCaseNo)a left join itemcase it on it.caseno=a.iCaseNo where DATEDIFF(day,ifdate,getdate())<150   ";
				}
				}
				}
				conn1 = SQLDBhelper.getConnection();
		        try {

					stmt1 = conn1.prepareStatement(sql1);
					if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
						i++;
						stmt1.setString(i, "%"+it.getCaseNo()+"%");
					}
					if(it.getConferenceMessage()!=null&&!"".equals(it.getConferenceMessage())){
						i++;
						stmt1.setString(i, "%"+it.getConferenceMessage()+"%");
					}
					if(it.getNewProjectTime()!=null&&!"".equals(it.getConferenceMessage())){
						i++;
						stmt1.setString(i, "%"+it.getNewProjectTime()+"%");
					}
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						 mr=new MeetingRecord();
						 conferenceMessage=rs1.getString("conferenceMessage");
						mr.setConferenceMessage(conferenceMessage);
						 String caseNo="";

						mr.setMerchandManager1(rs1.getString("MerchandManager1"));
						mr.setMerchandising(rs1.getString("Merchandising"));


						 if(it.getConferenceMessage()!=null&&!"".equals(it.getConferenceMessage())){

							 caseNo=rs1.getString("CaseNo");
						 }else{
							 if(it.getNewProjectTime()!=null&&!"".equals(it.getConferenceMessage())){
								 caseNo=rs1.getString("CaseNo");
							 }else{

						if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
							 caseNo=rs1.getString("CaseNo");
						 }else{
					       caseNo=rs1.getString("iCaseNo");
						 }
							 }
						 }
						mr.setCaseNo(caseNo);
						String sql = "select mr.* from MeetingRecord mr  where  CaseNo = ?";
						Connection conn = null;
						ResultSet rs = null;

						PreparedStatement stmt = null;
						conn = SQLDBhelper.getConnection();
						try {
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, caseNo);
							rs = stmt.executeQuery();
							while(rs.next()) {

						     int num=rs.getInt("mState");
						     if(num==3){
						    	mr.setNewProject(1);
						    	mr.setNewProjectTime(rs.getString("CreateDate"));
						     }else if(num==6){
						    	 mr.setProjectZhouJin(1);
						    	 mr.setProjectZhouJinTime(rs.getString("CreateDate"));
						     }else if(num==7){
						    	mr.setQualityAnalysis(1);
						    	mr.setQualityAnalysisTime(rs.getString("CreateDate"));
						     }else if(num==8){
						    	mr.setMassQualityConference(1);
						    	mr.setMassQualityConferenceTime(rs.getString("CreateDate"));
						     }else if(num==9){
						    	mr.setSmallQuantity(1);
						    	mr.setSmallQuantityTime(rs.getString("CreateDate"));
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
						if(mr.getNewProject()!=1){
							mr.setNewProject(0);
						}
						if(mr.getProjectZhouJin()!=1){
							mr.setProjectZhouJin(0);
						}
						if(mr.getQualityAnalysis()!=1){
							mr.setQualityAnalysis(0);
						}
						if(mr.getMassQualityConference()!=1){
							mr.setMassQualityConference(0);
						}
						if(mr.getSmallQuantity()!=1){
							mr.setSmallQuantity(0);
						}


						list1.add(mr);

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



		        return list1;

	}

	@Override
	public int updateConference(String projectId, String conferenceMessage,
			String messsage) {
		String sql = "update itemCase set conferenceMessage=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,conferenceMessage+"开"+messsage );
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
	public List<MeetingRecord> viewConferenceRecords1(MeetingRecord it) {
		Calendar now = Calendar.getInstance();
	       List<MeetingRecord>list1 =new ArrayList<MeetingRecord>();

	       String conferenceMessage="";
	        Connection conn1 = null;
			PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1=null;

				sql1="select mr.*,it.CustomerManager,it.MerchandManager1,it.conferenceMessage from MeetingRecord mr left join itemCase it on it.CaseNo=mr.CaseNo  where mState=?  and CreateDate=? ";

				conn1 = SQLDBhelper.getConnection();
		        try {
		        	stmt1 = conn1.prepareStatement(sql1);
					stmt1.setInt(1, it.getNewProject());
					stmt1.setString(2, it.getNewProjectTime());
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						 MeetingRecord mr=new MeetingRecord();
						 conferenceMessage=rs1.getString("conferenceMessage");
						mr.setConferenceMessage(conferenceMessage);
						 String caseNo="";
						 String s2 = "LynnYuanannazhuElaineShengIvyWuKathyPanSherryZhouminniewuellazhuShirleyYurosegaoThomasChenjennyguoKristinemeiJanezhouRitajiangNataliaLijennyguoRitajiang";
							String CustomerManager=rs1.getString("CustomerManager");
							String MerchandManager1=rs1.getString("MerchandManager1");
						 Boolean index5=false;
							Boolean index6=false;
							if(CustomerManager!=null&&!"".equals(CustomerManager)){
								index5 = s2.toLowerCase().contains(CustomerManager.toLowerCase());
								if(index5!=false){
									mr.setMerchandManager1(rs1.getString("CustomerManager"));
								}else{
									if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
										index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
										if(index6!=false){
											mr.setMerchandManager1(rs1.getString("MerchandManager1"));
										}
									}
								}
							}else{
								if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
									index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
									if(index6!=false){
										mr.setMerchandManager1(rs1.getString("MerchandManager1"));
									}
								}
							}




					    caseNo=rs1.getString("CaseNo");

						mr.setCaseNo(caseNo);


						     int num=rs1.getInt("mState");
						     if(num==3){
						    	mr.setNewProject(1);
						    	mr.setNewProjectTime(rs1.getString("CreateDate"));
						     }else if(num==6){
						    	 mr.setProjectZhouJin(1);
						    	 mr.setProjectZhouJinTime(rs1.getString("CreateDate"));
						     }else if(num==7){
						    	mr.setQualityAnalysis(1);
						    	mr.setQualityAnalysisTime(rs1.getString("CreateDate"));
						     }else if(num==8){
						    	mr.setMassQualityConference(1);
						    	mr.setMassQualityConferenceTime(rs1.getString("CreateDate"));
						     }else if(num==9){
						    	mr.setSmallQuantity(1);
						    	mr.setSmallQuantityTime(rs1.getString("CreateDate"));
						     }






						list1.add(mr);

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



		        return list1;
	}

	@Override
	public List<ItemCase> getAllProject(ItemCase it) {
		List<ItemCase>list =new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
		String sql=null;
		if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
			sql = "select * from itemCase where 1=1   ";
		}else{
		 sql = "select * from itemCase where datediff(day,CreateTime,getdate())<90   ";
		}

        conn = SQLDBhelper.getConnection();
        try {
        	if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
        	sql+="  and caseno like ?";
        	}
        	sql+=" ORDER BY CreateTime DESC ";
			stmt = conn.prepareStatement(sql);
           if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
        	   stmt.setString(1,"%"+it.getCaseNo()+"%" );
        	}
			rs = stmt.executeQuery();
			while(rs.next()) {
				itcase=new ItemCase();
				itcase.setCommonMode(rs.getInt("commonMode"));
				itcase.setCreateTime(rs.getString("CreateTime"));
				itcase.setCommonModeResult(rs.getString("commonModeResult"));
			    itcase.setCaseNo(rs.getString("CaseNo"));
			    itcase.setPlantAnalysis(rs.getInt("plantAnalysis"));
				list.add(itcase);
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
	public int updateCommonMode(String projectId, String reason, int sign) {
		String sql = "update itemCase set commonMode=?,commonModeResult=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,sign );
			stmt.setString(2, reason);
			stmt.setString(3, projectId);
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
	public int updateFactory(String projectId, int factory) {
		String sql = "update itemCase set PlantAnalysis=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,factory );

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
	public String getContent(String projectNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select EmailContent from itemCase    where caseno=? ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
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



        return result;
	}

	@Override
	public int addTuZhi(String caseNo, String backsingleCase) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "INSERT INTO tuzhi (caseno,zhongwen,yingwen,remark,UploadDate,name)  select distinct '" + caseNo + "', zhongwen,yingwen,remark,UploadDate,name from tuzhi where caseno like  '" + backsingleCase + "%'";
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
	public int addPOupload(String caseNo, String backsingleCase) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "INSERT INTO POupload (caseno,url,type,intro,uploadtime,uploader)  select distinct '" + caseNo + "',url,type,intro,uploadtime,uploader from POupload where caseno like  '" + backsingleCase + "%' and type='JIANYANJIHUAZJ'";
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
	public int addQuotePrice(String caseNo, String backsingleCase) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "INSERT INTO QuotePrice (caseno,EmployeeName,CurrentStatus,UpdateTime,Upload,msgtype,color)  select distinct '" + caseNo + "',EmployeeName,CurrentStatus,UpdateTime,Upload,msgtype,color from QuotePrice where caseno like  '" + backsingleCase + "%' ";
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
	public int addQuotationAnalysis(String caseNo, String backsingleCase) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String content = null;
		ResultSet rs = null;
		int state=0;
		int team=0;
		String CreateName="";
		String url="";

		int result=0;
		String str;
        String sql = "select state,team,cast(content as varchar(4000)) as content,CreateName,url from QuotationAnalysis   where state=5 and   CaseNo like '" + backsingleCase + "%' group by state,team,cast(content as varchar(4000)),CreateName,url ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				content =content+","+ rs.getString("content");
				state=rs.getInt("state");
				team=rs.getInt("team");
				CreateName=rs.getString("CreateName");
				url=rs.getString("url");
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
        if(content!=null&&!"".equals(content)){
        content=content.replaceFirst(",", "");
        }
        Connection conn1 = null;
		PreparedStatement stmt1 = null;
	    ResultSet rs1 = null;
		String sql1 = "INSERT INTO QuotationAnalysis (caseno,state,team,content,CreateName,CreateDate,url)  values ('" + caseNo + "'," + state+ ","  + team + ",'" + content + "','" + CreateName + "',getdate(),'" + url + "')";
		conn1 = SQLDBhelper.getConnection();

		try {
			stmt1 = conn1.prepareStatement(sql1);


          result = stmt1.executeUpdate();



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
		return result;
	}

	@Override
	public int updateAll(String caseNo, String caseNo1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String content = null;
		ResultSet rs = null;
		int result=0;
		 String sql = "select * from itemcase where caseno =?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseNo1);
			rs = stmt.executeQuery();
			if(rs.next()) {

				String sql1 = "update itemCase set zhijian1=?,zhijian2=?,MerchandManager2=?,Engineer1=?,Engineer2=?,Engineer3=? where CaseNo = ?";
				Connection conn1 = null;
				ResultSet rs1 = null;
				PreparedStatement stmt1 = null;
				int result1=0;
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1,rs.getString("zhijian1") );
					stmt1.setString(2,rs.getString("zhijian2"));
					stmt1.setString(3,rs.getString("MerchandManager2") );
					stmt1.setString(4,rs.getString("Engineer1") );
					stmt1.setString(5,rs.getString("Engineer2") );
					stmt1.setString(6,rs.getString("Engineer3") );
					stmt1.setString(7, caseNo1);
					result = stmt1.executeUpdate();
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
        return result;
	}

	@Override
	public List<Factory> factoryUnpaidStatistics(Factory it) {
		Calendar now = Calendar.getInstance();
	       List<Factory>list1 =new ArrayList<Factory>();
	       Factory info=null;

	        Connection conn1 = null;
	        int i=0;
			PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1=null;

				sql1="  select fac.friMoney,fac.CaseNo,fac.friFacDate,fac.State,fain.FactoryName,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.Engineer1,it.Engineer2,it.Engineer3,it.Merchandising from FactoryFund fac left join itemCase it on it.CaseNo=fac.CaseNo left join FactoryInfo fain on fac.fid=fain.id where (State ='计划已保存' or State='<font color=blue>正在审批中</font>' ) and friMoney is not null and friMoney!=0 ";
				if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
		        	sql1+="  and caseno like ?";
		        	}
				if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
		        	sql1+="  and friFacDate > ? ";
		        }
				if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
		        	sql1+="  and friFacDate <? ";
		        }
				sql1+=" order by fac.friFacDate desc";
				conn1 = SQLDBhelper.getConnection();
		        try {


					stmt1 = conn1.prepareStatement(sql1);
					if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
						i++;
			        	   stmt1.setString(i,"%"+it.getCaseNo()+"%" );
			        	}
					if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
						i++;
						stmt1.setString(i,it.getStartTime() );
			        }
					if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
						i++;
						stmt1.setString(i,it.getOutTime() );
			        }
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						 info=new Factory();

						 info.setMerchandising(rs1.getString("Merchandising"));
						info.setMerchandManager1(rs1.getString("MerchandManager1"));
						info.setMerchandManager2(rs1.getString("MerchandManager2"));
						info.setCaseNo(rs1.getString("caseno"));
						info.setFactoryName(rs1.getString("FactoryName"));
						info.setState(rs1.getString("state"));
						info.setMoney(rs1.getDouble("friMoney"));
						info.setTime(rs1.getString("friFacDate"));
						list1.add(info);

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



		        return list1;
	}

	@Override
	public Double getAmountApproved(Factory it) {
		PreparedStatement stmt1 = null;
		ResultSet rs1 = null;
		String sql1=null;
		double amountApproved=0;
		Connection conn1 = null;
		int i=0;
		sql1="  select sum(friMoney)friMoney from FactoryFund fac left join itemCase it on it.CaseNo=fac.CaseNo left join FactoryInfo fain on fac.fid=fain.id where  State='<font color=blue>正在审批中</font>' and friMoney is not null and friMoney!=0 ";
		if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
        	sql1+="  and caseno like ?";
        	}
		if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
        	sql1+="  and friFacDate > ? ";
        }
		if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
        	sql1+="  and friFacDate <? ";
        }

		conn1 = SQLDBhelper.getConnection();
        try {


			stmt1 = conn1.prepareStatement(sql1);
			if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
				i++;
	        	   stmt1.setString(i,"%"+it.getCaseNo()+"%" );
	        	}
			if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
				i++;
				stmt1.setString(i,it.getStartTime() );
	        }
			if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
				i++;
				stmt1.setString(i,it.getOutTime() );
	        }
			rs1 = stmt1.executeQuery();
			if(rs1.next()) {
				amountApproved=rs1.getDouble("friMoney")/10000;

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



        return amountApproved;
	}

	@Override
	public Double getPlanSave(Factory it) {
		PreparedStatement stmt1 = null;
		ResultSet rs1 = null;
		String sql1=null;
		double planSave=0;
		Connection conn1 = null;
		int i=0;
		sql1="  select sum(friMoney)friMoney from FactoryFund fac left join itemCase it on it.CaseNo=fac.CaseNo left join FactoryInfo fain on fac.fid=fain.id where  State='计划已保存' and friMoney is not null and friMoney!=0 ";
		if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
        	sql1+="  and caseno like ?";
        	}
		if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
        	sql1+="  and friFacDate > ? ";
        }
		if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
        	sql1+="  and friFacDate <? ";
        }

		conn1 = SQLDBhelper.getConnection();
        try {


			stmt1 = conn1.prepareStatement(sql1);
			if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
				i++;
	        	   stmt1.setString(i,"%"+it.getCaseNo()+"%" );
	        	}
			if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
				i++;
				stmt1.setString(i,it.getStartTime() );
	        }
			if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
				i++;
				stmt1.setString(i,it.getOutTime() );
	        }
			rs1 = stmt1.executeQuery();
			if(rs1.next()) {
				planSave=rs1.getDouble("friMoney")/10000;

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



        return planSave;
	}

	@Override
	public int updatename(ItemCase it, int total) {
		String sql = "update itemCase set MerchandManager2=?,Engineer1=?,Engineer2=?,Engineer3=?,zhijian1=?,zhijian2=?,Merchandising=?,CustomerManager=?,MerchandManager1=? where id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,it.getMerchandManager2());
			stmt.setString(2,it.getEngineer1() );
			stmt.setString(3,it.getEngineer2() );
			stmt.setString(4,it.getEngineer3() );
			stmt.setString(5,it.getZhijian1() );
			stmt.setString(6,it.getZhijian2() );
			stmt.setString(7,it.getMerchandising() );
			stmt.setString(8,it.getSaleName1());
			stmt.setString(9,it.getSaleName2() );
			stmt.setInt(10, total);
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
	public String getCaseNo2(int erpcid1) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select CaseNo from itemCase    where customercode=? order by id desc";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, erpcid1);
			rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
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



        return result;
	}

	@Override
	public int getId(String caseNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		int id=0;
        String sql = "select id from itemCase    order by id desc ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
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



        return id;
	}

	@Override
	public List<invoiceinfo> CustomerPaymentStatistics(invoiceinfo it) {
		Calendar now = Calendar.getInstance();
	       List<invoiceinfo>list1 =new ArrayList<invoiceinfo>();
	       invoiceinfo info=null;

	        Connection conn1 = null;
	        int i=0;
			PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1=null;

				sql1="    select it.GrossProfit,b.ifmoney,b.ifdate,b.imoneytype,b.iCaseNo,b.iimoney,b.iidate,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.Engineer1,it.Engineer2,it.Engineer3,cus.name from InvoiceInfo b "
               +" left join itemcase it on it.CaseNo=b.iCaseNo left join customer cus on cus.id=it.customercode where b.ifmoney is not null  and b.iimoney!=0   ";
				if(it.getCaseno()!=null&&!"".equals(it.getCaseno())){
		        	sql1+="  and (b.icaseno like ? or cus.name like ? or it.CustomerManager like ? or it.MerchandManager1 like ? or it.MerchandManager2 like ? or it.Engineer1 like ? or it.Engineer2 like ? or it.Engineer3 like ? or cus.id like ? )  ";
		        	}
				if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
		        	sql1+="  and  b.ifdate>?  ";
		        	}
				if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
		        	sql1+="  and  b.ifdate<?  ";
		        	}
				conn1 = SQLDBhelper.getConnection();
		        try {
		        	sql1+="   order by b.ifdate desc ";

					stmt1 = conn1.prepareStatement(sql1);
					if(it.getCaseno()!=null&&!"".equals(it.getCaseno())){
						i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"'"+it.getCaseno()+"'" );
			        	}

					if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
						i++;
						stmt1.setString(i,it.getStartTime() );
			        	}
					if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
						i++;
						stmt1.setString(i,it.getOutTime());
			        	}

					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						 info=new invoiceinfo();
						 String  CustomerManager=rs1.getString("CustomerManager");
							String  MerchandManager1=rs1.getString("MerchandManager1");
							String  MerchandManager2=rs1.getString("MerchandManager2");
							String  Engineer1=rs1.getString("Engineer1");
							String  Engineer2=rs1.getString("Engineer2");
							String  Engineer3=rs1.getString("Engineer3");
						    String s2 = "LynnYuanannazhuElaineShengIvyWuKathyPanSherryZhouminniewuellazhuShirleyYurosegaoThomasChenjennyguoKristinemeiJanezhouRitajiangNataliaLijennyguoRitajiang";
							Boolean index5=false;
							Boolean index6=false;
							if(CustomerManager!=null&&!"".equals(CustomerManager)){
								index5 = s2.toLowerCase().contains(CustomerManager.toLowerCase());
								if(index5!=false){
								info.setMerchandManager1(rs1.getString("CustomerManager"));
								}else{
									if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
										index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
										if(index6!=false){
										info.setMerchandManager1(rs1.getString("MerchandManager1"));
										}
									}
								}
							}else{
								if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
									index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
									if(index6!=false){
									info.setMerchandManager1(rs1.getString("MerchandManager1"));
									}
								}
							}





							String sa1 = "ChouchendongzhaoshuhaoHouQiangBensonzhangsunjinshuxuweixupingzhaoqiangRogerQiu";
							Boolean indexa1=false;
							Boolean index2=false;
							Boolean index3=false;
							Boolean index4=false;
							if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
							indexa1 = sa1.toLowerCase().contains(MerchandManager2.toLowerCase());
							if(indexa1!=false){
								info.setMerchandManager2(rs1.getString("MerchandManager2"));
							}else{
								if(Engineer1!=null&&!"".equals(Engineer1)){
								index2 = sa1.toLowerCase().contains(Engineer1.toLowerCase());
								if(index2!=false){
									info.setMerchandManager2(rs1.getString("Engineer1"));
								}else{
									if(Engineer2!=null&&!"".equals(Engineer2)){
										index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs1.getString("Engineer2"));
										}else{
											if(Engineer3!=null&&!"".equals(Engineer3)){
												index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
												if(index2!=false){
													info.setMerchandManager2(rs1.getString("Engineer3"));
												}
												}
										}
										}
								}
								}else{
									if(Engineer2!=null&&!"".equals(Engineer2)){
										index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs1.getString("Engineer2"));
										}else{
											if(Engineer3!=null&&!"".equals(Engineer3)){
												index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
												if(index2!=false){
													info.setMerchandManager2(rs1.getString("Engineer3"));
												}
												}
										}
									}else{
										if(Engineer3!=null&&!"".equals(Engineer3)){
											index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
											if(index2!=false){
												info.setMerchandManager2(rs1.getString("Engineer3"));
											}
											}
									}
								}

							}
							}else{
								if(Engineer1!=null&&!"".equals(Engineer1)){
									index2 = sa1.toLowerCase().contains(Engineer1.toLowerCase());
									if(index2!=false){
										info.setMerchandManager2(rs1.getString("Engineer1"));
									}else{
										if(Engineer2!=null&&!"".equals(Engineer2)){
											index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
											if(index2!=false){
												info.setMerchandManager2(rs1.getString("Engineer2"));
											}else{
												if(Engineer3!=null&&!"".equals(Engineer3)){
													index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
													if(index2!=false){
														info.setMerchandManager2(rs1.getString("Engineer3"));
													}
													}
											}
											}else{
												if(Engineer3!=null&&!"".equals(Engineer3)){
													index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
													if(index2!=false){
														info.setMerchandManager2(rs1.getString("Engineer3"));
													}
													}
											}
									}
								}else{
									if(Engineer2!=null&&!"".equals(Engineer2)){
										index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs1.getString("Engineer2"));
										}else{
											if(Engineer3!=null&&!"".equals(Engineer3)){
												index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
												if(index2!=false){
													info.setMerchandManager2(rs1.getString("Engineer3"));
												}
												}
										}
									}else{
										if(Engineer3!=null&&!"".equals(Engineer3)){
											index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
											if(index2!=false){
												info.setMerchandManager2(rs1.getString("Engineer3"));
											}
											}
									}
								}
							}



						info.setGrossProfit(rs1.getString("GrossProfit"));
						info.setCaseno(rs1.getString("icaseno"));
						info.setImoneytype(rs1.getInt("imoneytype"));
						info.setIimoney(rs1.getDouble("iimoney"));
						info.setIfmoney(rs1.getDouble("ifmoney"));
						info.setIidate(rs1.getString("iidate"));
						info.setIfdate(rs1.getString("ifdate"));
						info.setCustomerName(rs1.getString("name"));
						list1.add(info);

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



		        return list1;
	}

	@Override
	public Double getCompletedMoney(Factory it) {
		PreparedStatement stmt1 = null;
		ResultSet rs1 = null;
		String sql1=null;
		double amountApproved=0;
		Connection conn1 = null;
		int i=0;
		sql1="  select sum(friMoney)friMoney from FactoryFund fac left join itemCase it on it.CaseNo=fac.CaseNo left join FactoryInfo fain on fac.fid=fain.id where  State='<font color=green>已完成款项</font>' and friMoney is not null and friMoney!=0 ";
		if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
        	sql1+="  and caseno like ?";
        	}
		if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
        	sql1+="  and friFacDate > ? ";
        }
		if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
        	sql1+="  and friFacDate <? ";
        }

		conn1 = SQLDBhelper.getConnection();
        try {


			stmt1 = conn1.prepareStatement(sql1);
			if(it.getCaseNo()!=null&&!"".equals(it.getCaseNo())){
				i++;
	        	   stmt1.setString(i,"%"+it.getCaseNo()+"%" );
	        	}
			if(it.getStartTime()!=null&&!"".equals(it.getStartTime())){
				i++;
				stmt1.setString(i,it.getStartTime() );
	        }
			if(it.getOutTime()!=null&&!"".equals(it.getOutTime())){
				i++;
				stmt1.setString(i,it.getOutTime() );
	        }
			rs1 = stmt1.executeQuery();
			if(rs1.next()) {
				amountApproved=rs1.getDouble("friMoney")/10000;

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



        return amountApproved;
	}

	@Override
	public ItemCase2 getInspectionReport(String projectId) {
		ItemCase2 info=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

        String sql = "select it.*,c.completiontime,c.statetime,c.DateSample,ii.ifdate,m.FactoryName from itemCase it left join(select MIN(b.FactoryName) FactoryName,a.CaseNo from FactoryFund AS a   LEFT JOIN FactoryInfo AS b ON a.fid = b.id group by a.CaseNo) m on m.CaseNo=it.CaseNo"
        		+ " left join (select a.CaseNo,completiontime,DateSample,statetime from (select max(completiontime)completiontime,CaseNo  from Bargain where datediff(day,completiontime,'2000-01-01')<0  group by CaseNo )b  left join (select CaseNo,min(inputDate)statetime from  FactoryFund group by CaseNo)a on a.CaseNo=b.CaseNo"
        		+ "  left join(select CaseNo ,max(DateSample)DateSample from Bargain where datediff(day,DateSample,'2000-01-01')<0  group by CaseNo)d on d.CaseNo=b.CaseNo"
        		+ ""
        		+ ")c"
        		+ " on c.caseno=it.caseno left join (select icaseno,min(ifdate)ifdate from invoiceinfo where ifmoney is not null and ifmoney!=0 group by icaseno )ii on ii.icaseno=it.caseno  where it.caseno=?";

        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,projectId );
			rs = stmt.executeQuery();
			while(rs.next()) {

				info=new ItemCase2();

				info.setFirstPaymentDate(rs.getString("ifdate"));
				info.setCaseNo(rs.getString("CaseNo"));
				String CustomerManager=rs.getString("CustomerManager");
				String MerchandManager1=rs.getString("MerchandManager1");
				String MerchandManager2=rs.getString("MerchandManager2");
				String Engineer1=rs.getString("Engineer1");
				String Engineer2=rs.getString("Engineer2");
				String Engineer3=rs.getString("Engineer3");
				info.setMerchandising(rs.getString("merchandising"));
				info.setMaturePurchase(rs.getString("maturePurchase"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setFactoryName(rs.getString("FactoryName"));
				info.setProjectMaterialProperties(rs.getInt("projectMaterialProperties"));
				info.setPlantAnalysis(rs.getInt("ProjectLevel"));
				info.setZhijian1(rs.getString("zhijian1"));
				info.setZhijian2(rs.getString("zhijian2"));
				info.setCompletionTime(rs.getString("completiontime"));
				info.setDateSample(rs.getString("DateSample"));
				info.setDateSampleUploading(rs.getString("statetime"));


				}

		} catch (Exception e) {
			//e.printStackTrace();
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
	public List<ItemCase2> getPurchasingSystem1() {
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

        String sql = "select * from itemCase it left join(select MIN(b.FactoryName) FactoryName,a.CaseNo from FactoryFund AS a   LEFT JOIN FactoryInfo AS b ON a.fid = b.id group by a.CaseNo) m on m.CaseNo=it.CaseNo"
        		+ " left join (select a.CaseNo,completiontime,DateSample,statetime from (select max(completiontime)completiontime,CaseNo ,max(DateSample)DateSample from Bargain group by CaseNo)b  left join (select CaseNo,min(inputDate)statetime from  FactoryFund group by CaseNo)a on a.CaseNo=b.CaseNo)c"
        		+ " on c.caseno=it.caseno where datediff(day,it.followtime,getdate())<8";

        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {

				ItemCase2 info=new ItemCase2();
				info.setCaseNo(rs.getString("CaseNo"));
				String CustomerManager=rs.getString("CustomerManager");
				String MerchandManager1=rs.getString("MerchandManager1");
				String MerchandManager2=rs.getString("MerchandManager2");
				String Engineer1=rs.getString("Engineer1");
				String Engineer2=rs.getString("Engineer2");
				String Engineer3=rs.getString("Engineer3");


				  String s2 = "LynnYuanannazhuElaineShengIvyWuKathyPanSherryZhouminniewuellazhuShirleyYurosegaoThomasChenjennyguoKristinemeiJanezhouRitajiangNataliaLijennyguoRitajiang";
					Boolean index5=false;
					Boolean index6=false;
					if(CustomerManager!=null&&!"".equals(CustomerManager)){
						index5 = s2.toLowerCase().contains(CustomerManager.toLowerCase());
						if(index5!=false){
						info.setMerchandManager1(rs.getString("CustomerManager"));
						}else{
							if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
								index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
								if(index6!=false){
								info.setMerchandManager1(rs.getString("MerchandManager1"));
								}
							}
						}
					}else{
						if(MerchandManager1!=null&&!"".equals(MerchandManager1)){
							index6 = s2.toLowerCase().contains(MerchandManager1.toLowerCase());
							if(index6!=false){
							info.setMerchandManager1(rs.getString("MerchandManager1"));
							}
						}
					}





					String sa1 = "ChouchendongzhaoshuhaoHouQiangBensonzhangsunjinshuxuweixupingzhaoqiangRogerQiu";
					Boolean indexa1=false;
					Boolean index2=false;
					Boolean index3=false;
					Boolean index4=false;
					if(MerchandManager2!=null&&!"".equals(MerchandManager2)){
					indexa1 = sa1.toLowerCase().contains(MerchandManager2.toLowerCase());
					if(indexa1!=false){
						info.setMerchandManager2(rs.getString("MerchandManager2"));
					}else{
						if(Engineer1!=null&&!"".equals(Engineer1)){
						index2 = sa1.toLowerCase().contains(Engineer1.toLowerCase());
						if(index2!=false){
							info.setMerchandManager2(rs.getString("Engineer1"));
						}else{
							if(Engineer2!=null&&!"".equals(Engineer2)){
								index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
								if(index2!=false){
									info.setMerchandManager2(rs.getString("Engineer2"));
								}else{
									if(Engineer3!=null&&!"".equals(Engineer3)){
										index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs.getString("Engineer3"));
										}
										}
								}
								}
						}
						}else{
							if(Engineer2!=null&&!"".equals(Engineer2)){
								index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
								if(index2!=false){
									info.setMerchandManager2(rs.getString("Engineer2"));
								}else{
									if(Engineer3!=null&&!"".equals(Engineer3)){
										index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs.getString("Engineer3"));
										}
										}
								}
							}else{
								if(Engineer3!=null&&!"".equals(Engineer3)){
									index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
									if(index2!=false){
										info.setMerchandManager2(rs.getString("Engineer3"));
									}
									}
							}
						}

					}
					}else{
						if(Engineer1!=null&&!"".equals(Engineer1)){
							index2 = sa1.toLowerCase().contains(Engineer1.toLowerCase());
							if(index2!=false){
								info.setMerchandManager2(rs.getString("Engineer1"));
							}else{
								if(Engineer2!=null&&!"".equals(Engineer2)){
									index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
									if(index2!=false){
										info.setMerchandManager2(rs.getString("Engineer2"));
									}else{
										if(Engineer3!=null&&!"".equals(Engineer3)){
											index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
											if(index2!=false){
												info.setMerchandManager2(rs.getString("Engineer3"));
											}
											}
									}
									}else{
										if(Engineer3!=null&&!"".equals(Engineer3)){
											index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
											if(index2!=false){
												info.setMerchandManager2(rs.getString("Engineer3"));
											}
											}
									}
							}
						}else{
							if(Engineer2!=null&&!"".equals(Engineer2)){
								index2 = sa1.toLowerCase().contains(Engineer2.toLowerCase());
								if(index2!=false){
									info.setMerchandManager2(rs.getString("Engineer2"));
								}else{
									if(Engineer3!=null&&!"".equals(Engineer3)){
										index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
										if(index2!=false){
											info.setMerchandManager2(rs.getString("Engineer3"));
										}
										}
								}
							}else{
								if(Engineer3!=null&&!"".equals(Engineer3)){
									index2 = sa1.toLowerCase().contains(Engineer3.toLowerCase());
									if(index2!=false){
										info.setMerchandManager2(rs.getString("Engineer3"));
									}
									}
							}
						}
					}
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setFactoryName(rs.getString("FactoryName"));
				info.setProjectMaterialProperties(rs.getInt("projectMaterialProperties"));
				info.setPlantAnalysis(rs.getInt("ProjectLevel"));
				info.setZhijian1(rs.getString("zhijian1"));
				info.setZhijian2(rs.getString("zhijian2"));
				info.setCompletionTime(rs.getString("completiontime"));
				info.setDateSample(rs.getString("DateSample"));
				info.setDateSampleUploading(rs.getString("statetime"));
				list.add(info);

				}

		} catch (Exception e) {
			//e.printStackTrace();
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
	public int add(String uploader, String projectNo, String reason,
			String createtime, String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "insert into poupload(CaseNo,minimalistTask,type,intro"
				+ ",uploadtime,uploader) values(?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectNo);
			stmt.setString(2,name );
			stmt.setString(3,"JIANYANBAOGAO" );
			stmt.setString(4,reason );
			stmt.setString(5,createtime );
			stmt.setString(6,uploader );

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
	public List<invoiceinfo> nonPaymentCustomers(invoiceinfo it) {
		Calendar now = Calendar.getInstance();
	       List<invoiceinfo>list1 =new ArrayList<invoiceinfo>();
	       invoiceinfo info=null;

	        Connection conn1 = null;
	        int i=0;
			PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1=null;

				sql1=" select allArrears,discrepancy,uploads,iid,ifmoney,ifdate,imoneytype,iCaseNo,iimoney,iidate,CustomerManager,MerchandManager1,MerchandManager2,Engineer1,Engineer2,Engineer3,Merchandising,outstandingNotes,name"
						+ ",AmountMoney,QualityDeductions,ReasonsWithholding,explain,createTime,ContractNumber,reason,sort "
						+ "from  (select isnull(c.iimoney1-c.ifmoney1,0)allArrears,isnull( b.iimoney,0) -isnull( b.ifmoney, 0 ) discrepancy,b.sort,b.uploads,b.iid,b.reason,b.ifmoney,b.ifdate,b.imoneytype,b.iCaseNo,b.iimoney,b.iidate,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.Engineer1,it.Engineer2,it.Engineer3,it.Merchandising,cus.name,b.outstandingNotes,"
						+ "b.AmountMoney,b.QualityDeductions,b.ReasonsWithholding,b.explain,b.createTime,con.ContractNumber from InvoiceInfo b "
               +" left join itemcase it on it.CaseNo=b.iCaseNo left join customer cus on cus.id=it.customercode left join ContractDeductionForm con on con.QualityId=b.iid "
               + " left join (select isnull(a.iimoney1,0)iimoney1,isnull(a.ifmoney1,0)ifmoney1,a.icaseno from(select sum(iimoney)iimoney1,sum(ifmoney)ifmoney1,icaseno from InvoiceInfo group by iCaseNo)a)c on b.iCaseNo=c.iCaseNo where  iid not in (  select iid from InvoiceInfo where ifmoney is not null and iimoney-ifmoney<50) and  b.iimoney!=0   ";
				if(it.getCaseno()!=null&&!"".equals(it.getCaseno())){
		        	sql1+="  and (b.icaseno like ? or cus.name like ? or b.uploads like ? or cus.id like ? )  ";
		        	}
				if(it.getIidate()!=null&&!"".equals(it.getIidate())){
		        	sql1+="  and  b.iidate <=?  ";
		        	}
				if(it.getReason()!=11 && it.getReason()!=-1){
		        	sql1+="  and  b.reason  =?  ";
		        	}
				if(it.getDiscrepancy()!=0){
					sql1+="  and  b.iimoney-b.ifmoney >?  ";
				}

				conn1 = SQLDBhelper.getConnection();
		        try {
		        	sql1+=" )a where a.allArrears>200  order by a.sort  ,a.iidate desc ";

					stmt1 = conn1.prepareStatement(sql1);
					if(it.getCaseno()!=null&&!"".equals(it.getCaseno())){
						i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );

			        	}
					if(it.getIidate()!=null&&!"".equals(it.getIidate())){
						i++;
						stmt1.setString(i,it.getIidate() );
			        	}
					if(it.getReason()!=11 && it.getReason()!=-1){
						i++;
						stmt1.setInt(i,it.getReason() );
			        	}
					if(it.getDiscrepancy()!=0){
						i++;
						stmt1.setInt(i,it.getDiscrepancyFlag() );
					}
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						 info=new invoiceinfo();
						 String caseno=rs1.getString("icaseno");
						 info.setCaseno(rs1.getString("icaseno"));
						 Connection conn = null;
							PreparedStatement stmt = null;
							ResultSet rs = null;
							String sql = "select a.icaseno,a.iimoney,b.ifmoney from ( select sum(iimoney)iimoney"
					        		+ " ,icaseno from invoiceinfo  group by icaseno)a left join "
					        		+ " (select sum(ifmoney)ifmoney ,icaseno from invoiceinfo "
					        		+ " where ifmoney is not null   group by icaseno)b on a.icaseno=b.icaseno"
					        		+ " where a.icaseno=?";
					        conn = SQLDBhelper.getConnection();
					        try {
								stmt = conn.prepareStatement(sql);
								stmt.setString(1, caseno);
								rs = stmt.executeQuery();
								if(rs.next()) {
								double ifmoney=rs.getDouble("ifmoney");
								double iimoney=rs.getDouble("iimoney");
								info.setEstimatedFullPayment(iimoney);
								info.setActualPayment(ifmoney);
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
							}
						 info.setIid(rs1.getInt("iid"));
						 info.setMerchandManager1(rs1.getString("MerchandManager1"));
						 info.setMerchandManager2(rs1.getString("MerchandManager2"));
						 info.setMerchandising(rs1.getString("Merchandising"));
						info.setOutstandingNotes(rs1.getString("outstandingNotes"));

						info.setImoneytype(rs1.getInt("imoneytype"));
						info.setIimoney(rs1.getDouble("iimoney"));
						info.setIfmoney(rs1.getDouble("ifmoney"));
						info.setIidate(rs1.getString("iidate"));
						info.setReason(rs1.getInt("reason"));
						info.setDiscrepancy(rs1.getDouble("discrepancy"));
						info.setCustomerName(rs1.getString("name"));
						info.setIfdate(rs1.getString("ifdate"));
						info.setUploads(rs1.getString("uploads"));
						info.setAmountMoney(rs1.getString("amountMoney"));
						info.setQualityDeductions(rs1.getDouble("qualityDeductions"));
						info.setReasonsWithholding(rs1.getString("reasonsWithholding"));
						info.setExplain(rs1.getString("explain"));
						info.setCreateTime(rs1.getString("createTime"));
						info.setContractNumber(rs1.getString("contractNumber"));
						info.setAllArrears(rs1.getDouble("allArrears"));
						list1.add(info);

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



		        return list1;
	}

	@Override
	public int updateAllSaleName(int customerId, String saleName1, int i) {
		String sql="";
		if(i==1){
		  sql= "update itemCase set CustomerManager=? where customercode = ?";
		}else if(i==2){
			sql= "update itemCase set MerchandManager1=? where customercode = ?";
		}
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,saleName1 );
			stmt.setInt(2, customerId);
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
	public int deleteMerchandiser(String strProjectId) {
		String sql = "update itemCase set Merchandising='' where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, strProjectId);
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
	public int updateMerchandiser(String saleName1, String strProjectId) {
		String sql = "update itemCase set Merchandising=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, saleName1);
			stmt.setString(2, strProjectId);
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
	public List<invoiceinfo> nonPaymentCustomers1(invoiceinfo it) {
		Calendar now = Calendar.getInstance();
	       List<invoiceinfo>list1 =new ArrayList<invoiceinfo>();
	       invoiceinfo info=null;

	        Connection conn1 = null;
	        int i=0;
			PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				String sql1=null;

				sql1=" select allArrears,discrepancy,uploads,iid,ifmoney,ifdate,imoneytype,iCaseNo,iimoney,iidate,CustomerManager,MerchandManager1,MerchandManager2,Engineer1,Engineer2,Engineer3,Merchandising,outstandingNotes,name"
						+ ",AmountMoney,QualityDeductions,ReasonsWithholding,explain,createTime,ContractNumber,reason,sort "
						+ "from  (select isnull(c.iimoney1-c.ifmoney1,0)allArrears,isnull( b.iimoney,0) -isnull( b.ifmoney, 0 ) discrepancy,b.uploads,b.sort,b.iid,b.reason,b.ifmoney,b.ifdate,b.imoneytype,b.iCaseNo,b.iimoney,b.iidate,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.Engineer1,it.Engineer2,it.Engineer3,it.Merchandising,cus.name,b.outstandingNotes,"
						+ "b.AmountMoney,b.QualityDeductions,b.ReasonsWithholding,b.explain,b.createTime,con.ContractNumber from InvoiceInfo b "
               +" left join itemcase it on it.CaseNo=b.iCaseNo left join customer cus on cus.id=it.customercode left join ContractDeductionForm con on con.QualityId=b.iid"
               + " left join (select isnull(a.iimoney1,0)iimoney1,isnull(a.ifmoney1,0)ifmoney1,a.icaseno from(select sum(iimoney)iimoney1,sum(ifmoney)ifmoney1,icaseno from InvoiceInfo group by iCaseNo)a)c on b.iCaseNo=c.iCaseNo where  iid not in (  select iid from InvoiceInfo where ifmoney is not null and iimoney-ifmoney<50)  and  b.iimoney!=0 ";
				if(it.getCaseno()!=null&&!"".equals(it.getCaseno())){
		        	sql1+="  and (b.icaseno like ? or cus.name like ? or b.uploads like ? or cus.id like ? )  ";
		        	}
				if(it.getIidate()!=null&&!"".equals(it.getIidate())){
		        	sql1+="  and  b.iidate  <=?  ";
		        	}
				if(it.getReason()!=11 && it.getReason()!=-1){
		        	sql1+="  and  b.reason  =?  ";
		        	}
				if(it.getDiscrepancy()!=0){
					sql1+="  and  b.iimoney-b.ifmoney  >?  ";
				}
				sql1+=" and (it.CustomerManager like ? or it.MerchandManager1 like ? or it.MerchandManager2 like ? or it.Engineer1 like ? or it.Engineer2 like ? or it.Engineer3 like ? or it.Merchandising like ?)";
				conn1 = SQLDBhelper.getConnection();
		        try {
		        	sql1+=")a where a.allArrears>200   order by a.sort ,a.iidate desc ";

					stmt1 = conn1.prepareStatement(sql1);
					if(it.getCaseno()!=null&&!"".equals(it.getCaseno())){
						i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );
			        	   i++;
			        	   stmt1.setString(i,"%"+it.getCaseno()+"%" );

			        	}
					if(it.getIidate()!=null&&!"".equals(it.getIidate())){
						i++;
						stmt1.setString(i,it.getIidate() );
			        	}
					if(it.getReason()!=11 && it.getReason()!=-1){
						i++;
						stmt1.setInt(i,it.getReason() );
			        	}
					if(it.getDiscrepancy()!=0){
						i++;
						stmt1.setInt(i,it.getDiscrepancyFlag() );
					}
					stmt1.setString(i+1,it.getMerchandManager1());
					stmt1.setString(i+2,it.getMerchandManager1());
					stmt1.setString(i+3,it.getMerchandManager1());
					stmt1.setString(i+4,it.getMerchandManager1());
					stmt1.setString(i+5,it.getMerchandManager1());
					stmt1.setString(i+6,it.getMerchandManager1());
					stmt1.setString(i+7,it.getMerchandManager1());
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						 info=new invoiceinfo();
						 String caseno=rs1.getString("icaseno");
						 info.setCaseno(rs1.getString("icaseno"));
						 Connection conn = null;
							PreparedStatement stmt = null;
							ResultSet rs = null;
							String sql = "select a.icaseno,a.iimoney,b.ifmoney from ( select sum(iimoney)iimoney"
					        		+ " ,icaseno from invoiceinfo  group by icaseno)a left join "
					        		+ " (select sum(ifmoney)ifmoney ,icaseno from invoiceinfo "
					        		+ " where ifmoney is not null   group by icaseno)b on a.icaseno=b.icaseno"
					        		+ " where a.icaseno=?";
					        conn = SQLDBhelper.getConnection();
					        try {
								stmt = conn.prepareStatement(sql);
								stmt.setString(1, caseno);
								rs = stmt.executeQuery();
								if(rs.next()) {
								double ifmoney=rs.getDouble("ifmoney");
								double iimoney=rs.getDouble("iimoney");
								info.setEstimatedFullPayment(iimoney);
								info.setActualPayment(ifmoney);
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
							}
						 info.setIid(rs1.getInt("iid"));
						 info.setMerchandManager1(rs1.getString("MerchandManager1"));
						 info.setMerchandManager2(rs1.getString("MerchandManager2"));
						 info.setMerchandising(rs1.getString("Merchandising"));
						info.setOutstandingNotes(rs1.getString("outstandingNotes"));
						info.setCaseno(rs1.getString("icaseno"));
						info.setImoneytype(rs1.getInt("imoneytype"));
						info.setIimoney(rs1.getDouble("iimoney"));
						info.setIfmoney(rs1.getDouble("ifmoney"));
						info.setIidate(rs1.getString("iidate"));
						info.setReason(rs1.getInt("reason"));
						info.setUploads(rs1.getString("uploads"));
						info.setDiscrepancy(rs1.getDouble("discrepancy"));
						info.setIfdate(rs1.getString("ifdate"));
						info.setCustomerName(rs1.getString("name"));
						info.setAmountMoney(rs1.getString("amountMoney"));
						info.setQualityDeductions(rs1.getDouble("qualityDeductions"));
						info.setReasonsWithholding(rs1.getString("reasonsWithholding"));
						info.setExplain(rs1.getString("explain"));
						info.setCreateTime(rs1.getString("createTime"));
						info.setContractNumber(rs1.getString("contractNumber"));
						info.setAllArrears(rs1.getDouble("allArrears"));
						list1.add(info);

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



		        return list1;
	}

	@Override
	public int find(String empEName, String caseno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id=0;
        String sql = "select count(*) from itemCase where CaseNo=? and (CustomerManager=? or "
        		+ "MerchandManager1=? or MerchandManager2=? or Engineer1=? or Engineer2=? or Engineer3=? or Merchandising=?) ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, caseno);
			stmt.setString(2, empEName);
			stmt.setString(3, empEName);
			stmt.setString(4, empEName);
			stmt.setString(5, empEName);
			stmt.setString(6, empEName);
			stmt.setString(7, empEName);
			stmt.setString(8, empEName);
			rs = stmt.executeQuery();
			if(rs.next()) {
			id=rs.getInt(1)	;
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
		return id;
	}

	@Override
	public List<ItemCase> startProjectStatistics(ItemCase it) {
		List<ItemCase>list =new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
        String sql = "select * from(select isNull(PObiao,null)PObiao,isnull(DateSample,null)DateSample,isnull(completiontime,null)completiontime,isnull(pdId,null)pdId,isnull(DrawingName1,null)DrawingName1 "
        		+ " ,isnull(intro,null)intro,isnull(uploader,null)uploader,  "
             + "  isnull(uploadtime,null)uploadtime,isnull(intro1,null)intro1,isnull(intro2,null)intro2,isnull(uploader2,null)uploader2,isnull(uploadtime2,null)uploadtime2, "
              + "  isnull(zhongwen,null)zhongwen,isnull(zhongwen1,null)zhongwen1,isnull(yingwen,null)yingwen,isnull(BargainNo,null)BargainNo,isnull(EmployeeName1,null)EmployeeName1,isnull(EmployeeName2,null)EmployeeName2,isnull(EmployeeName3,null)EmployeeName3,isnull(UpdateTime1,null)UpdateTime1,"
              + " isnull(UpdateTime2,null)UpdateTime2,isnull(UpdateTime3,null)UpdateTime3,isnull(ProjectLevel,null)ProjectLevel,isnull(potime,null)potime,caseno,isnull(ProductDescC,null)ProductDescC,isnull(ProductDescE,null)ProductDescE, "
               + " isnull(CustomerManager,null)CustomerManager,isnull(MerchandManager1,null)MerchandManager1,isnull(MerchandManager2,null)MerchandManager2,isnull(ProductState,null)ProductState,isnull(QIC,null)QIC,remarks "
              + " from( "
        		+ "select po.PObiao,m.DateSample,m.completiontime,isnull(x.id,0)pdId,y.DrawingName1,c.intro,c.uploader,convert(varchar(10),c.uploadtime,120)uploadtime,c.intro1,"
        		+ " c.intro2,c.uploader2,convert(varchar(10),c.uploadtime2,120)uploadtime2,n.zhongwen,n.yingwen,yy.zhongwen1,m.BargainNo,qp.EmployeeName1,qp.EmployeeName2,qp.EmployeeName3"
        		+ ",convert(varchar(10),qp.UpdateTime1,120)UpdateTime1,convert(varchar(10),qp.UpdateTime2,120)UpdateTime2,convert(varchar(10),qp.UpdateTime3,120)UpdateTime3, "
        		+ " it.ProjectLevel,convert(nvarchar(10),it.potime,120)potime,a.caseno,it.ProductDescC,it.ProductDescE,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.ProductState,it.QIC,it.remarks from ("
        		+ " select caseno from ( "
        		+ " select start.CaseNo from itemcase it left join tab_StateDate start on it.caseno=start.caseno  where ( CaseStatus=0 or  CaseStatus=14)  ";
        		if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
        			sql+= " and  start.xuanchang> '"+it.getStartTime() +"'" ;
        		}
        		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
        			sql+= " and  start.xuanchang< '" +it.getEndTime() +"'";
        		}
        		sql+= "and it.caseno not like '%-%' "
        		+ " union select start.CaseNo from itemcase it left join tab_StateDate start on it.caseno=start.caseno  where ( CaseStatus=0 or  CaseStatus=14)  ";
        		if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
        			sql+= " and  start.shengchanzhunbei> '"+it.getStartTime()+"'" ;
        		}
        		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
        			sql+= " and  start.shengchanzhunbei< '" +it.getEndTime()+"'";
        		}
        		sql+= " and it.caseno not like '%-%' "
                + " union select start.CaseNo from itemcase it left join tab_StateDate start on it.caseno=start.caseno  where ( CaseStatus=0 or  CaseStatus=14)  ";
        		if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
        			sql+= " and  start.gendan> '"+it.getStartTime() +"'";
        		}
        		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
        			sql+= " and  start.gendan< '" +it.getEndTime()+"'";
        		}
                sql+= " and it.caseno not like '%-%' "
                + ")m";
                if(it.getCaseNo()!=null&&!"".equalsIgnoreCase(it.getCaseNo())){
        			sql+= " where caseno like '%"+it.getCaseNo() +"%'";
        		}
                sql+= " )a "
                + "left join itemcase it on a.CaseNo=it.CaseNo left join QuotationAnalysis qa on qa.caseno=a.caseno "
                + " left join "
                + " ( select qp.CaseNo,a.EmployeeName1,a.UpdateTime1,qp1.EmployeeName2,qp1.UpdateTime2,qp2.EmployeeName3,qp2.UpdateTime3 from QuotePrice qp left join  ( select CaseNo,min(EmployeeName)EmployeeName1,min(UpdateTime)UpdateTime1 from QuotePrice where CurrentStatus like '%项目启动会%' group by CaseNo "
                + "  )a on a.CaseNo=qp.caseno left join (select CaseNo,min(EmployeeName)EmployeeName2,min(UpdateTime)UpdateTime2 from QuotePrice where CurrentStatus like '%样品分析会%' or CurrentStatus like '%样品验货会%' group by CaseNo )  qp1 on qp1.caseno=qp.CaseNo "
                + " left join (select CaseNo,min(EmployeeName)EmployeeName3,min(UpdateTime)UpdateTime3 from QuotePrice where CurrentStatus like '%大货分析会%' or CurrentStatus like '%大货验货会%' group by CaseNo )  qp2 on qp2.caseno=qp.CaseNo "
                + " )  qp on qp.caseno=a.CaseNo left join ( select a.caseno,a.zhongwen,a.yingwen from ( select caseno,min(tz.zhongwen)zhongwen,min(yingwen)yingwen from tuzhi tz where remark like '%受控版本%'  group by caseno "
                + " )a  "
                + " )n on n.caseno=a.CaseNo "
                + "  left join ( select xx.caseno,isnull(xx.zhongwen,xx.task_system_technical_documentation)zhongwen1 from ( select caseno,min(tz.zhongwen)zhongwen,min(tz.task_system_technical_documentation)task_system_technical_documentation from tuzhi tz where attribute =1  group by caseno "
                + " )xx  "
                + " )yy on yy.caseno=a.CaseNo"
                + " left join (select po.CaseNo,isnull(a.intro,null)intro1,a.uploader,a.uploadtime,e.intro2,e.uploader2,e.uploadtime2,f.intro from poupload po left join ( "
                + " select caseno,min(intro)intro,min(uploader)uploader,min(uploadtime)uploadtime from poupload where intro like  '%采购A版%' group by caseno "
                + ")a  on a.caseno=po.CaseNo   left join (select caseno,min(intro)intro2,min(uploader)uploader2,min(uploadtime)uploadtime2 from poupload where intro like  '%采购B版%' group by caseno)e on e.CaseNo=po.CaseNo "
                + " left join (select caseno,min(intro)intro from poupload where type like  '%JIANYANBAOGAO%' group by caseno)f on f.CaseNo=po.CaseNo)c on c.CaseNo=a.CaseNo "
                + " left join (select a.CaseNo,a.BargainNo,b.DateSample,c.completiontime from( "
                + " select min(BargainNo)BargainNo,CaseNo from  bargain group by CaseNo "
                + " )a left join (select min(DateSample)DateSample,caseno from bargain where DateSample>'2017-01-01' group by caseno )b on a.CaseNo=b.CaseNo "
                + " left join (select min(completiontime)completiontime,caseno from bargain where completiontime>'2017-01-01' group by caseno )c on a.CaseNo=c.CaseNo "
                + " )m on m.caseno=a.CaseNo "
                + "  left join  (select min(id)id,caseno from (select id,caseno from ProjectDrawings where remark like '%生产计划%')a group by caseno)x on x.caseno= a.CaseNo "
                + " left join (select max(DrawingName1)DrawingName1 ,caseno from( select isnull(DrawingName,'')DrawingName1,caseno from ProjectDrawings where  remark not like '%生产计划%' and remark not like '%第三方质检报告%' and DrawingName like '%项目需求汇总%')a group by caseno )"
                + " y on a.caseno=y.caseno "
                + " left join  po on po.caseno=a.caseno )cc  group by PObiao,DateSample,completiontime,pdId,"
                + "DrawingName1,intro,uploader,uploadtime,intro1,intro2,uploader2,uploadtime2,zhongwen,yingwen,BargainNo"
                + ",EmployeeName1,EmployeeName2,EmployeeName3,UpdateTime1,UpdateTime2,UpdateTime3,ProjectLevel,potime,caseno,"
                + "ProductDescC,ProductDescE,CustomerManager,MerchandManager1,MerchandManager2,ProductState,QIC,zhongwen1,remarks)x ";

                	if(it.getDrawingPicture()==1){
                	sql+=" where zhongwen is null ";
                	}else if(it.getDrawingPicture()==2){
                	sql+=" where zhongwen  is not null ";
                	}

                sql+= "order by caseno";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
			    itcase=new ItemCase();
			    String caseno=rs.getString("caseNo");
			    double money=0.00;
			    int type=0;
			    PreparedStatement stmt1 = null;
			    ResultSet rs1 = null;
			    Connection conn1 = null;
			   String sql1 = " SELECT icaseno,sum(iimoney)iimoney,min(imoneytype)imoneytype  FROM invoiceinfo  WHERE iCaseNo = ? group by icaseno";

				conn1 = SQLDBhelper.getConnection();

				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, caseno);
					rs1 = stmt1.executeQuery();
					if(rs1.next()) {
						money=rs1.getDouble("iimoney");
					    type=rs1.getInt("imoneytype");
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
                String UpdateTime1="";
				String meeting_inputer="";
				PreparedStatement stmt2 = null;
				ResultSet rs2= null;
				Connection conn2 = null;
				String sql2 = " SELECT meeting_name,min(meeting_inputer)meeting_inputer  FROM meeting_record  WHERE project_no = ? and meeting_name='项目启动会' group by meeting_name ";
                conn2= DBHelper.getConnection();
                try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, caseno);
					rs2 = stmt2.executeQuery();
					if(rs2.next()) {
						UpdateTime1=rs2.getString("meeting_name");
						meeting_inputer=rs2.getString("meeting_inputer");
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
					DBHelper.returnConnection(conn2);

				}
				itcase.setIntro(rs.getString("intro"));
				String introA=rs.getString("intro");
				String UpdateTime3= rs.getString("UpdateTime3");
				if(UpdateTime3!=null&&!"".equalsIgnoreCase(UpdateTime3)){
					itcase.setQpId2(rs.getString("EmployeeName3") +UpdateTime3);
				}else{
					if(introA!=null&&!"".equalsIgnoreCase(introA)){
						Boolean save=introA.toLowerCase().contains("没问题");
						Boolean judgePrice=judgePrice(type,money);
						if(save!=true&&judgePrice!=false){
							itcase.setQpId2("0");//有问题，大于5000美元，未开
						}else if(save!=false&&judgePrice!=false){
							itcase.setQpId2("1");//大于5000美元，未开
						}else if(save!=true&&judgePrice!=true){
							itcase.setQpId2("2");//有问题，未开
						}else{
							itcase.setQpId2("3");
						}

					}else {
						Boolean judgePrice=judgePrice(type,money);
						if(judgePrice!=false){
							itcase.setQpId2("1");
						}else {
							itcase.setQpId2("3");
						}

					}
					}



			    itcase.setCaseNo(rs.getString("caseNo"));
				itcase.setProjectDescc(rs.getString("ProductDescC"));
				itcase.setProjectDesce(rs.getString("ProductDescE"));
				itcase.setCustomerManager(rs.getString("customerManager"));
				itcase.setMerchandManager1(rs.getString("merchandManager1"));
				itcase.setMerchandManager2(rs.getString("merchandManager2"));
				itcase.setPo(rs.getString("PObiao"));
				itcase.setRemarks(rs.getString("remarks"));
				itcase.setProjectLevel(rs.getInt("projectLevel"));
				itcase.setPotime(rs.getString("potime"));
				itcase.setPdId1(rs.getString("DrawingName1"));


				String yingwen=rs.getString("yingwen");
				String zhongwen=rs.getString("zhongwen");
				if(zhongwen!=null&&!"".equalsIgnoreCase(zhongwen)){
					itcase.setRemark(zhongwen);
				}else if(yingwen!=null&&!"".equalsIgnoreCase(yingwen)){
					itcase.setRemark(yingwen);
				}else{
					itcase.setRemark(null);
				}

				String intro=rs.getString("intro1");
				if(intro!=null&&!"".equalsIgnoreCase(intro)){
					itcase.setPoId(intro+"["+rs.getString("uploader")+"/"+rs.getString("uploadtime")+"]");
				}else{
					itcase.setPoId(null);
				}
				String intro2=rs.getString("intro2");
				if(intro2!=null&&!"".equalsIgnoreCase(intro2)){
					itcase.setPoId2(intro2+"["+rs.getString("uploader2")+"/"+rs.getString("uploadtime2")+"]");
				}else{
					itcase.setPoId2(null);
				}


				//String UpdateTime1= rs.getString("UpdateTime1");
				if(UpdateTime1!=null&&!"".equalsIgnoreCase(UpdateTime1)){
					itcase.setQpId(meeting_inputer +UpdateTime1);
				}else{
					itcase.setQpId(null);
				}
				String UpdateTime2= rs.getString("UpdateTime2");
				if(UpdateTime2!=null&&!"".equalsIgnoreCase(UpdateTime2)){
					itcase.setQpId1(rs.getString("EmployeeName2") +UpdateTime2);
				}else{
					itcase.setQpId1(null);
				}


				itcase.setPdId(rs.getInt("pdId"));
				itcase.setCompletiontime(rs.getString("completiontime"));
				itcase.setDateSample(rs.getString("dateSample"));
				itcase.setBargainNo(rs.getString("bargainNo"));
				itcase.setProductState(rs.getInt("productState"));
				String zhongwen1=rs.getString("zhongwen1");
				itcase.setTechnicalDocumentation(zhongwen1);
				list.add(itcase);
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

	private Boolean judgePrice(int type, double money) {
	   boolean judgePrice=false;
	   if(type==1&&money>5000){
		   judgePrice=true;
	   }else if(type==2&&money>35000){
		   judgePrice=true;
	   }else if(type==3&&money>4450){
		   judgePrice=true;
	   }else if(type==5&&money>3848){
		   judgePrice=true;
	   }
		return judgePrice;
	}

	@Override
	public int insert(String fileName, String projectId, String userName,
			int fid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		String sql = "insert into BJneibufile(CaseNo,url,uploader,uploadtime,team,f_id,p_num,is_PF,price,p_txt) "
				+ "values(?,?,?,getdate(),?,?,?,?,?,'')";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			stmt.setString(2, fileName);
			stmt.setString(3, userName);
			stmt.setInt(4, 1);
			stmt.setInt(5, fid);
			stmt.setInt(6, 0);
			stmt.setInt(7, 1);
			stmt.setInt(8, 1);



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
	public ItemCase getCaseno(String projectId, String contractNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
        String sql = " select * from itemcase it left join Bargain b on it.CaseNo=b.CaseNo where it.caseno=? and b.BargainNo=?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			stmt.setString(2, contractNumber);
			rs = stmt.executeQuery();
			while(rs.next()) {
				itcase=new ItemCase();
				itcase.setCid(rs.getInt("customercode"));
				itcase.setCreateTime(rs.getString("CreateTime"));
				itcase.setProjectDescc(rs.getString("ProductDescC"));
				itcase.setProjectDesce(rs.getString("ProductDescE"));
				itcase.setUpload(rs.getString("Upload"));
				itcase.setCaseNo(rs.getString("CaseNo"));
				itcase.setSaleName1(rs.getString("CustomerManager"));
				itcase.setSaleName2(rs.getString("MerchandManager1"));

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



        return itcase;
	}

	@Override
	public int updateAll(ItemCase2 item) {
		String sql = "update itemCase set updateTime=getDATE()";
			if(item.getCustomerManager()!=null){
				sql+=" ,CustomerManager=?  ";
			}
			if(item.getMerchandManager1()!=null){
				sql+=" ,MerchandManager1=?  ";
			}
			if(item.getMerchandManager2()!=null){
				sql+=" ,MerchandManager2=?  ";
			}
			if(item.getMerchandising()!=null){
				sql+=" ,Merchandising=? ";
			}
			if(item.getMaturePurchase()!=null){
				sql+=" ,MaturePurchase=? ";
			}
			if(item.getOriginalPurchase()!=null){
				sql+=" ,OriginalPurchase=? ";
			}
			if(item.getZhijian1()!=null){
				sql+=" ,Zhijian1=? ";
			}
			if(item.getZhijian2()!=null){
				sql+=" ,Zhijian2=? ";
			}

			if(item.getProjectLevel()!=0){
				sql+=" ,ProjectLevel=? ";
			}
			if(item.getTechnicalSupport1()!=null){
				sql+=" ,technical_support1=? ";
			}


				sql+= "where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		int i=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			if(item.getCustomerManager()!=null){
				i++;
				stmt.setString(i, item.getCustomerManager());
			}
			if(item.getMerchandManager1()!=null){
				i++;
				stmt.setString(i, item.getMerchandManager1());
			}
			if(item.getMerchandManager2()!=null){
				i++;
				stmt.setString(i, item.getMerchandManager2());
			}
			if(item.getMerchandising()!=null){
				i++;
				stmt.setString(i, item.getMerchandising());
			}
			if(item.getMaturePurchase()!=null){
				i++;
				stmt.setString(i, item.getMaturePurchase());
			}
			if(item.getOriginalPurchase()!=null){
				i++;
				stmt.setString(i, item.getOriginalPurchase());
			}
			if(item.getZhijian1()!=null){
				i++;
				stmt.setString(i, item.getZhijian1());
			}
			if(item.getZhijian2()!=null){
				i++;
				stmt.setString(i, item.getZhijian2());
			}
			if(item.getProjectLevel()!=0){
				i++;
				stmt.setInt(i, item.getProjectLevel());
			}
			if(item.getTechnicalSupport1()!=null){
				i++;
				stmt.setString(i, item.getTechnicalSupport1());
			}
			stmt.setString(i+1, item.getCaseNo());
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
	public int add(String factoryName, String factoryId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "insert into relational_correspondence_table(FastManufacturingPlantId,FastManufacturingFactoryName) values(?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, factoryId);
			stmt.setString(2,factoryName );


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
	public List<ItemCase> startProjectStatistics1(ItemCase it) {
		List<ItemCase>list =new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ItemCase itcase=null;
        String sql = "select * from(select isNull(PObiao,null)PObiao,isnull(DateSample,null)DateSample,isnull(completiontime,null)completiontime,isnull(pdId,null)pdId,isnull(DrawingName1,null)DrawingName1 "
        		+ " ,isnull(intro,null)intro,isnull(uploader,null)uploader,  "
             + "  isnull(uploadtime,null)uploadtime,isnull(intro1,null)intro1,isnull(intro2,null)intro2,isnull(uploader2,null)uploader2,isnull(uploadtime2,null)uploadtime2, "
              + "  isnull(zhongwen,null)zhongwen,isnull(zhongwen1,null)zhongwen1,isnull(yingwen,null)yingwen,isnull(BargainNo,null)BargainNo,isnull(EmployeeName1,null)EmployeeName1,isnull(EmployeeName2,null)EmployeeName2,isnull(EmployeeName3,null)EmployeeName3,isnull(UpdateTime1,null)UpdateTime1,"
              + " isnull(UpdateTime2,null)UpdateTime2,isnull(UpdateTime3,null)UpdateTime3,isnull(ProjectLevel,null)ProjectLevel,isnull(potime,null)potime,caseno,isnull(ProductDescC,null)ProductDescC,isnull(ProductDescE,null)ProductDescE, "
               + " isnull(CustomerManager,null)CustomerManager,isnull(MerchandManager1,null)MerchandManager1,isnull(MerchandManager2,null)MerchandManager2,isnull(ProductState,null)ProductState,isnull(QIC,null)QIC,remarks "
              + " from( "
        		+ "select po.PObiao,m.DateSample,m.completiontime,isnull(x.id,0)pdId,y.DrawingName1,c.intro,c.uploader,convert(varchar(10),c.uploadtime,120)uploadtime,c.intro1,"
        		+ " c.intro2,c.uploader2,convert(varchar(10),c.uploadtime2,120)uploadtime2,n.zhongwen,n.yingwen,yy.zhongwen1,m.BargainNo,qp.EmployeeName1,qp.EmployeeName2,qp.EmployeeName3"
        		+ ",convert(varchar(10),qp.UpdateTime1,120)UpdateTime1,convert(varchar(10),qp.UpdateTime2,120)UpdateTime2,convert(varchar(10),qp.UpdateTime3,120)UpdateTime3, "
        		+ " it.ProjectLevel,convert(nvarchar(10),it.potime,120)potime,a.caseno,it.ProductDescC,it.ProductDescE,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.ProductState,it.QIC,it.remarks from ("
        		+ " select caseno from ( "
        		+ " select start.CaseNo from itemcase it left join tab_StateDate start on it.caseno=start.caseno  where ( CaseStatus=0 or  CaseStatus=14)  ";
        		if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
        			sql+= " and  start.xuanchang> '"+it.getStartTime() +"'" ;
        		}
        		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
        			sql+= " and  start.xuanchang< '" +it.getEndTime() +"'";
        		}
        		sql+= "and it.caseno not like '%-%' "
        		+ " and (it.CustomerManager=? or it.MerchandManager1=? or it.MerchandManager2=? or it.zhijian1=? or it.zhijian2=? or it.Merchandising=? or it.MaturePurchase=? or it.OriginalPurchase=?)"

        		+ " union select start.CaseNo from itemcase it left join tab_StateDate start on it.caseno=start.caseno  where ( CaseStatus=0 or  CaseStatus=14)  ";
        		if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
        			sql+= " and  start.shengchanzhunbei> '"+it.getStartTime()+"'" ;
        		}
        		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
        			sql+= " and  start.shengchanzhunbei< '" +it.getEndTime()+"'";
        		}
        		sql+= " and it.caseno not like '%-%' "
        		+ " and (it.CustomerManager=? or it.MerchandManager1=? or it.MerchandManager2=? or it.zhijian1=? or it.zhijian2=? or it.Merchandising=? or it.MaturePurchase=? or it.OriginalPurchase=?)"

                + " union select start.CaseNo from itemcase it left join tab_StateDate start on it.caseno=start.caseno  where ( CaseStatus=0 or  CaseStatus=14)  ";
        		if(it.getStartTime()!=null&&!"".equalsIgnoreCase(it.getStartTime())){
        			sql+= " and  start.gendan> '"+it.getStartTime() +"'";
        		}
        		if(it.getEndTime()!=null&&!"".equalsIgnoreCase(it.getEndTime())){
        			sql+= " and  start.gendan< '" +it.getEndTime()+"'";
        		}
                sql+= " and it.caseno not like '%-%' "
                + " and (it.CustomerManager=? or it.MerchandManager1=? or it.MerchandManager2=? or it.zhijian1=? or it.zhijian2=? or it.Merchandising=? or it.MaturePurchase=? or it.OriginalPurchase=?)"

                + ")m";
                if(it.getCaseNo()!=null&&!"".equalsIgnoreCase(it.getCaseNo())){
        			sql+= " where caseno like '%"+it.getCaseNo() +"%'";
        		}
                sql+= " )a "
                + "left join itemcase it on a.CaseNo=it.CaseNo left join QuotationAnalysis qa on qa.caseno=a.caseno "
                + " left join "
                + " ( select qp.CaseNo,a.EmployeeName1,a.UpdateTime1,qp1.EmployeeName2,qp1.UpdateTime2,qp2.EmployeeName3,qp2.UpdateTime3 from QuotePrice qp left join  ( select CaseNo,min(EmployeeName)EmployeeName1,min(UpdateTime)UpdateTime1 from QuotePrice where CurrentStatus like '%项目启动会%' group by CaseNo "
                + "  )a on a.CaseNo=qp.caseno left join (select CaseNo,min(EmployeeName)EmployeeName2,min(UpdateTime)UpdateTime2 from QuotePrice where CurrentStatus like '%样品分析会%' or CurrentStatus like '%样品验货会%' group by CaseNo )  qp1 on qp1.caseno=qp.CaseNo "
                + " left join (select CaseNo,min(EmployeeName)EmployeeName3,min(UpdateTime)UpdateTime3 from QuotePrice where CurrentStatus like '%大货分析会%' or CurrentStatus like '%大货验货会%' group by CaseNo )  qp2 on qp2.caseno=qp.CaseNo "
                + " )  qp on qp.caseno=a.CaseNo left join ( select a.caseno,a.zhongwen,a.yingwen from ( select caseno,min(tz.zhongwen)zhongwen,min(yingwen)yingwen from tuzhi tz where remark like '%受控版本%'  group by caseno "
                + " )a  "
                + " )n on n.caseno=a.CaseNo "
                + "  left join ( select xx.caseno,isnull(xx.zhongwen,'')zhongwen1 from ( select caseno,min(tz.zhongwen)zhongwen from tuzhi tz where attribute =1  group by caseno "
                + " )xx  "
                + " )yy on yy.caseno=a.CaseNo"
                + " left join (select po.CaseNo,isnull(a.intro,null)intro1,a.uploader,a.uploadtime,e.intro2,e.uploader2,e.uploadtime2,f.intro from poupload po left join ( "
                + " select caseno,min(intro)intro,min(uploader)uploader,min(uploadtime)uploadtime from poupload where intro like  '%采购A版%' group by caseno "
                + ")a  on a.caseno=po.CaseNo   left join (select caseno,min(intro)intro2,min(uploader)uploader2,min(uploadtime)uploadtime2 from poupload where intro like  '%采购B版%' group by caseno)e on e.CaseNo=po.CaseNo "
                + " left join (select caseno,min(intro)intro from poupload where type like  '%JIANYANBAOGAO%' group by caseno)f on f.CaseNo=po.CaseNo)c on c.CaseNo=a.CaseNo "
                + " left join (select a.CaseNo,a.BargainNo,b.DateSample,c.completiontime from( "
                + " select min(BargainNo)BargainNo,CaseNo from  bargain group by CaseNo "
                + " )a left join (select min(DateSample)DateSample,caseno from bargain where DateSample>'2017-01-01' group by caseno )b on a.CaseNo=b.CaseNo "
                + " left join (select min(completiontime)completiontime,caseno from bargain where completiontime>'2017-01-01' group by caseno )c on a.CaseNo=c.CaseNo "
                + " )m on m.caseno=a.CaseNo "
                + "  left join  (select min(id)id,caseno from (select id,caseno from ProjectDrawings where remark like '%生产计划%')a group by caseno)x on x.caseno= a.CaseNo "
                + " left join (select max(DrawingName1)DrawingName1 ,caseno from( select isnull(DrawingName,'')DrawingName1,caseno from ProjectDrawings where  remark not like '%生产计划%' and remark not like '%第三方质检报告%' and DrawingName like '%项目需求汇总%')a group by caseno )"
                + " y on a.caseno=y.caseno "
                + " left join  po on po.caseno=a.caseno )cc "

                + " group by PObiao,DateSample,completiontime,pdId,"
                + "DrawingName1,intro,uploader,uploadtime,intro1,intro2,uploader2,uploadtime2,zhongwen,yingwen,BargainNo"
                + ",EmployeeName1,EmployeeName2,EmployeeName3,UpdateTime1,UpdateTime2,UpdateTime3,ProjectLevel,potime,caseno,"
                + "ProductDescC,ProductDescE,CustomerManager,MerchandManager1,MerchandManager2,ProductState,QIC,zhongwen1,remarks)x order by caseno";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, it.getCustomerManager());
			stmt.setString(2, it.getCustomerManager());
			stmt.setString(3, it.getCustomerManager());
			stmt.setString(4, it.getCustomerManager());
			stmt.setString(5, it.getCustomerManager());
			stmt.setString(6, it.getCustomerManager());
			stmt.setString(7, it.getCustomerManager());
			stmt.setString(8, it.getCustomerManager());
			stmt.setString(9, it.getCustomerManager());
			stmt.setString(10, it.getCustomerManager());
			stmt.setString(11, it.getCustomerManager());
			stmt.setString(12, it.getCustomerManager());
			stmt.setString(13, it.getCustomerManager());
			stmt.setString(14, it.getCustomerManager());
			stmt.setString(15, it.getCustomerManager());
			stmt.setString(16, it.getCustomerManager());
			stmt.setString(17, it.getCustomerManager());
			stmt.setString(18, it.getCustomerManager());
			stmt.setString(19, it.getCustomerManager());
			stmt.setString(20, it.getCustomerManager());
			stmt.setString(21, it.getCustomerManager());
			stmt.setString(22, it.getCustomerManager());
			stmt.setString(23, it.getCustomerManager());
			stmt.setString(24, it.getCustomerManager());

			rs = stmt.executeQuery();
			while(rs.next()) {
			    itcase=new ItemCase();
			    String caseNo=rs.getString("caseNo");
			    itcase.setCaseNo(rs.getString("caseNo"));
				itcase.setProjectDescc(rs.getString("ProductDescC"));
				itcase.setProjectDesce(rs.getString("ProductDescE"));
				itcase.setCustomerManager(rs.getString("customerManager"));
				itcase.setMerchandManager1(rs.getString("merchandManager1"));
				itcase.setMerchandManager2(rs.getString("merchandManager2"));
				itcase.setPo(rs.getString("PObiao"));
				itcase.setRemarks(rs.getString("remarks"));
				itcase.setProjectLevel(rs.getInt("projectLevel"));
				itcase.setPotime(rs.getString("potime"));
				itcase.setPdId1(rs.getString("DrawingName1"));
				itcase.setIntro(rs.getString("intro"));
				String yingwen=rs.getString("yingwen");
				String zhongwen=rs.getString("zhongwen");
				if(zhongwen!=null&&!"".equalsIgnoreCase(zhongwen)){
					itcase.setRemark(zhongwen);
				}else if(yingwen!=null&&!"".equalsIgnoreCase(yingwen)){
					itcase.setRemark(yingwen);
				}else{
					itcase.setRemark(null);
				}

				String intro=rs.getString("intro1");
				if(intro!=null&&!"".equalsIgnoreCase(intro)){
					itcase.setPoId(intro+"["+rs.getString("uploader")+"/"+rs.getString("uploadtime")+"]");
				}else{
					itcase.setPoId(null);
				}
				String intro2=rs.getString("intro2");
				if(intro2!=null&&!"".equalsIgnoreCase(intro2)){
					itcase.setPoId2(intro2+"["+rs.getString("uploader2")+"/"+rs.getString("uploadtime2")+"]");
				}else{
					itcase.setPoId2(null);
				}
				String UpdateTime1="";
				String meeting_inputer="";
				PreparedStatement stmt2 = null;
				ResultSet rs2= null;
				Connection conn2 = null;
				String sql2 = " SELECT meeting_name,min(meeting_inputer)meeting_inputer  FROM meeting_record  WHERE project_no = ? and meeting_name='项目启动会' group by meeting_name ";
				conn2= DBHelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, caseNo);
					rs2 = stmt2.executeQuery();
					if(rs2.next()) {
						UpdateTime1=rs2.getString("meeting_name");
						meeting_inputer=rs2.getString("meeting_inputer");
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
					DBHelper.returnConnection(conn2);

				}


				if(UpdateTime1!=null&&!"".equalsIgnoreCase(UpdateTime1)){
					itcase.setQpId(meeting_inputer +UpdateTime1);
				}else{
					itcase.setQpId(null);
				}
				String UpdateTime2= rs.getString("UpdateTime2");
				if(UpdateTime2!=null&&!"".equalsIgnoreCase(UpdateTime2)){
					itcase.setQpId1(rs.getString("EmployeeName2") +UpdateTime2);
				}else{
					itcase.setQpId1(null);
				}
				String UpdateTime3= rs.getString("UpdateTime3");
				if(UpdateTime3!=null&&!"".equalsIgnoreCase(UpdateTime3)){
					itcase.setQpId2(rs.getString("EmployeeName3") +UpdateTime3);
				}else{
					itcase.setQpId2(null);
				}

				itcase.setPdId(rs.getInt("pdId"));
				itcase.setCompletiontime(rs.getString("completiontime"));
				itcase.setDateSample(rs.getString("dateSample"));
				itcase.setBargainNo(rs.getString("bargainNo"));
				itcase.setProductState(rs.getInt("productState"));
				String zhongwen1=rs.getString("zhongwen1");
				itcase.setTechnicalDocumentation(zhongwen1);
				list.add(itcase);
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
	public int updateCaseStatus(String caseno) {
		String sql = "update itemCase set casestatus=4 where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,caseno );
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
		String sql1 = "update tab_StateDate set xuanchang=null,shengchanzhunbei=null,gendan=null where CaseNo = ?";
		Connection conn1 = null;
		ResultSet rs1 = null;
		PreparedStatement stmt1 = null;
	    int result1=0;
		conn1 = SQLDBhelper.getConnection();
		try {
			stmt1 = conn1.prepareStatement(sql1);
			stmt1.setString(1,caseno );
			result1 = stmt1.executeUpdate();
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


		return result;
	}

	@Override
	public int updateRemarks(String caseno, String remarks) {
		String sql1 = "update itemcase set remarks=? where CaseNo = ?";
		Connection conn1 = null;
		ResultSet rs1 = null;
		PreparedStatement stmt1 = null;
	    int result=0;
		conn1 = SQLDBhelper.getConnection();
		try {
			stmt1 = conn1.prepareStatement(sql1);
			stmt1.setString(1,remarks );
			stmt1.setString(2,caseno );
			result = stmt1.executeUpdate();
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


		return result;
	}


	@Override
	public int updateFpRemarks(String bargainNo, String remarks) {
		String sql1 = "update Tab_Factory_Money set remark=? where Case_No = ? and Factory_id=?";
		Connection conn1 = null;
		ResultSet rs1 = null;
		PreparedStatement stmt1 = null;
		int result=0;
		conn1 = SQLDBhelper.getConnection();
		try {
			stmt1 = conn1.prepareStatement(sql1);
			stmt1.setString(1,remarks );
			stmt1.setString(2,bargainNo.split(",")[0] );
            stmt1.setString(3,bargainNo.split(",")[1] );
			result = stmt1.executeUpdate();
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


		return result;
	}





	@Override
	public int insert1(String fileName, String projectId, String userName,
			int fid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		String sql = "insert into BJduiwai(CaseNo,title,url,creater,creattime,lirun) "
				+ "values(?,?,?,?,getdate(),?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			stmt.setString(2, projectId+"对外报价");
			stmt.setString(3, fileName);
			stmt.setString(4, userName);
			stmt.setInt(5, 10);



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
	public List<ItemCase2> invoiceFactoryOwnedToUs(ItemCase2 it) {
		String time=it.getStartTime();
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select * from ("
				+ "select FactoryName,kingdee,Convert(decimal(18,2),sum(money1))money1,sum(money2)money2,sum(money3)money3,sum(money4)money4"
				+ ",isnull(min(Factory_yue_Money),0)Factory_yue_Money,sum(Get_Moeny1)Get_Moeny1,isnull(Factory_invice.Invoice_name,'') invoice_name  from (  "
		        +"  select a.kingdee,a.FactoryName,(a.friMoney-a.Get_Moeny-rmb-amount_customs_declaration)money3,b.money2,c.money1,d.money4,e.Get_Moeny1,Factory_id from ( "
		        +" select a.*,isnull(b.friMoney,0)friMoney,isnull(c.rmb,0)rmb,isnull(d.amount_customs_declaration,0)"
		        + " amount_customs_declaration from ( select sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName,min(Factory_id) Factory_id from ( "
		   +" select a.*,info.FactoryName,info.kingdee  from Tab_Factory_Money a left join factoryinfo info "
		   + " on info.id=a.Factory_id  left join itemcase it on it.caseno=a.case_no   where  Case_No  in ( select a.CaseNo from( "
		  +" select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
          +" )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
          +"  and state='<font color=green>已完成款项</font>' group by caseno )b  on a.caseno=b.CaseNo where "
          + " a.friMoney1=b.friMoney)  )y group by kingdee)a left join  (select sum(friMoney)friMoney,info.kingdee "
          + " from factoryfund fu left join factoryinfo info on fu.fid=info.id where friMoney is not null "
        +"  and   caseno  in ( select a.CaseNo from(   select sum(friMoney)friMoney1,caseno from factoryfund "
        + " a WHERE friMoney is not null group by caseno   )a left join (select sum(friMoney)friMoney,caseno"
        + " from factoryfund where friMoney is not null   and state='<font color=green>已完成款项</font>' group by caseno )b "
        +"  on a.caseno=b.CaseNo where a.friMoney1=b.friMoney) group by info.kingdee)b  on a.kingdee=b.kingdee "
		+"  left join ( select kingdee,sum(rmb)rmb from (  select a.*,info.kingdee from ( select purno,factory,"
		+ " sum( CAST(rmb AS decimal(11,0)))rmb,max(CONVERT(datetime,saildate,20))timeDate 	 from contract con left join products "
		+ " p on p.id=con.proId	 where year(CONVERT(datetime,saildate,20))=year('"+time+"') and "
		+ " month(CONVERT(datetime,saildate,20))>=month('"+time+"')   group by purno, factory)a "
		+"		  left join (select bargainNo,fid from  factoryfund  where   caseno  in ( select a.CaseNo from( "
		+"  select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
        +"  )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
        +"  and state='<font color=green>已完成款项</font>' group by caseno )b on a.caseno=b.CaseNo where "
        + " a.friMoney1=b.friMoney ) group by bargainNo,fid) fu on a.purno=fu.BargainNo left join    factoryinfo "
		+"	 info on fu.fid=info.id)a where 1=1   group by kingdee )c on a.kingdee=c.kingdee  left join ( select "
		+ " sum(amount_customs_declaration)amount_customs_declaration,kingdee from (  select sd.*,info.kingdee "
		+ " from shipment_bill_data sd left join (select bargainNo,fid from  factoryfund   where   caseno "
		+ " in ( select a.CaseNo from(   select sum(friMoney)friMoney1,caseno from factoryfund a "
		+ " WHERE friMoney is not null group by caseno   )a left join (select sum(friMoney)friMoney,caseno "
		+ " from factoryfund where friMoney is not null  and state='<font color=green>已完成款项</font>' group by caseno )b "
        +"  on a.caseno=b.CaseNo where a.friMoney1=b.friMoney ) group by bargainNo,fid) fu on sd.bargainno=fu.BargainNo"
        + " left join factoryinfo info on fu.fid=info.id)a group by kingdee)d on d.kingdee=a.kingdee "
        +"        )a "
		 + "left join (  "
		 +" select a.kingdee,a.FactoryName,(a.friMoney-a.Get_Moeny-rmb-amount_customs_declaration)money2 from ( "
		 +" select a.*,isnull(b.friMoney,0)friMoney,isnull(c.rmb,0)rmb,isnull(d.amount_customs_declaration,0)"
		 +" amount_customs_declaration from ( select sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName from ( "
		 +"  select a.*,info.FactoryName,info.kingdee  from Tab_Factory_Money a left join factoryinfo info on "
		 + " info.id=a.Factory_id left join itemcase it on it.caseno=a.case_no  where  Case_No  in ( select "
		 + " a.CaseNo from( select sum(friMoney)friMoney1,caseno,max(friFacDate)friFacDate from factoryfund "
		 + " a WHERE friMoney is not null group by caseno )a left join (select sum(friMoney)friMoney,"
		 + " caseno from factoryfund where friMoney is not null   and state='<font color=green>已完成款项</font>' "
		 + " group by caseno )b on a.caseno=b.CaseNo where a.friMoney1=b.friMoney and "
		 + " Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095))y group by kingdee)a "
		 + " left join  (select sum(friMoney)friMoney,info.kingdee from factoryfund fu left join factoryinfo "
		 + " info on fu.fid=info.id where friMoney is not null and caseno  in ( select a.CaseNo from( select "
		 + " sum(friMoney)friMoney1,caseno,max(friFacDate)friFacDate from factoryfund a WHERE friMoney is not null "
		 + " group by caseno   )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney "
		 + " is not null and state='<font color=green>已完成款项</font>' group by caseno )b on a.caseno=b.CaseNo where "
		 + " a.friMoney1=b.friMoney and Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095 "
		 +" ) group by info.kingdee)b on a.kingdee=b.kingdee left join (select kingdee,sum(rmb)rmb from(select a.*,"
		 + " info.kingdee from (select purno,factory,sum( CAST(rmb AS decimal(11,0)))rmb,max(CONVERT(datetime,saildate,20))timeDate "
		+" from contract con left join products p on p.id=con.proId where year(CONVERT(datetime,saildate,20))=year('"+time+"') "
		+ " and month(CONVERT(datetime,saildate,20))>=month('"+time+"')   group by purno, factory)a left join "
		+" (select bargainNo,fid from  factoryfund  where   caseno  in ( select a.CaseNo from( select "
		+ " sum(friMoney)friMoney1,caseno,max(friFacDate)friFacDate from factoryfund a WHERE friMoney is not null "
		+ " group by caseno )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
        +"  and state='<font color=green>已完成款项</font>' group by caseno )b on a.caseno=b.CaseNo where "
        + " a.friMoney1=b.friMoney and Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095 "
		+" ) group by bargainNo,fid) fu on a.purno=fu.BargainNo left join factoryinfo info on fu.fid=info.id)a "
		+ " where 1=1 group by kingdee )c on a.kingdee=c.kingdee left join(select sum(amount_customs_declaration)"
		+ "amount_customs_declaration,kingdee from ( select sd.*,info.kingdee from shipment_bill_data sd "
		+ " left join (select bargainNo,fid from  factoryfund   where   caseno  in ( select a.CaseNo from(select "
		+ " sum(friMoney)friMoney1,caseno,max(friFacDate)friFacDate from factoryfund a WHERE friMoney is not null "
		+ " group by caseno)a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
        +" and state='<font color=green>已完成款项</font>' group by caseno )b on a.caseno=b.CaseNo where "
        + " a.friMoney1=b.friMoney and Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095 "
		+"  ) group by bargainNo,fid) fu on sd.bargainno=fu.BargainNo left join factoryinfo info on fu.fid=info.id)a "
		+ " group by kingdee)d on d.kingdee=a.kingdee)a   "
		 + ")b on a.kingdee=b.kingdee  "
		 + " left join (select a.kingdee,a.FactoryName,(a.friMoney-a.Get_Moeny-rmb-amount_customs_declaration)money1 "
		 + " from (select a.*,isnull(b.friMoney,0)friMoney,isnull(c.rmb,0)rmb,"
		 + " isnull(d.amount_customs_declaration,0)amount_customs_declaration from ( select sum(Get_Moeny)Get_Moeny"
		 + ",kingdee,min(FactoryName)FactoryName from (select a.*,info.FactoryName,info.kingdee from Tab_Factory_Money "
		 + " a left join factoryinfo info on info.id=a.Factory_id left join itemcase it on it.caseno=a.case_no)y group "
		 + " by kingdee)a left join(select sum(friMoney)friMoney,info.kingdee from factoryfund fu left join "
		 + " factoryinfo info on fu.fid=info.id where friMoney is not null group by info.kingdee)b on a.kingdee=b.kingdee "
		 + " left join (select kingdee,sum(rmb)rmb from (select a.*,info.kingdee from (select purno,factory,"
		 + " sum( CAST(rmb AS decimal(11,0)))rmb,max(CONVERT(datetime,saildate,20))timeDate from contract con left join products p "
		 + " on p.id=con.proId where year(CONVERT(datetime,saildate,20))=year('"+time+"') and "
		 + " month(CONVERT(datetime,saildate,20))>=month('"+time+"')   group by purno, factory)a  left join (select "
		 + " bargainNo,fid from  factoryfund   group by bargainNo,fid) fu on a.purno=fu.BargainNo left join factoryinfo "
		 + " info on fu.fid=info.id)a where 1=1   group by kingdee )c on a.kingdee=c.kingdee left join ( "
		 + " select sum(amount_customs_declaration)amount_customs_declaration,kingdee from ( select sd.*,"
		 + " info.kingdee from shipment_bill_data sd left join (select bargainNo,fid from  factoryfund group by"
		 + " bargainNo,fid) fu on sd.bargainno=fu.BargainNo left join factoryinfo info on fu.fid=info.id)a"
		 + " group by kingdee)d on d.kingdee=a.kingdee)a     "
		 + "  )c "
         + "on a.kingdee=c.kingdee"
         + " left join "
         + "(select a.kingdee,a.FactoryName,(a.friMoney-a.Get_Moeny-rmb-amount_customs_declaration)money4 "
		 + " from (select a.*,isnull(b.friMoney,0)friMoney,isnull(c.rmb,0)rmb,"
		 + " isnull(d.amount_customs_declaration,0)amount_customs_declaration from ( select sum(Get_Moeny)Get_Moeny"
		 + ",kingdee,min(FactoryName)FactoryName from (select a.*,info.FactoryName,info.kingdee from Tab_Factory_Money "
		 + " a left join factoryinfo info on info.id=a.Factory_id left join itemcase it on it.caseno=a.case_no)y group "
		 + " by kingdee)a left join(select sum(friMoney)friMoney,info.kingdee from factoryfund fu left join "
		 + " factoryinfo info on fu.fid=info.id where friMoney is not null and  state='<font color=green>已完成款项</font>' group by info.kingdee)b on a.kingdee=b.kingdee "
		 + " left join (select kingdee,sum(rmb)rmb from (select a.*,info.kingdee from (select purno,factory,"
		 + " sum( CAST(rmb AS decimal(11,0)))rmb,max(CONVERT(datetime,saildate,20))timeDate from contract con left join products p "
		 + " on p.id=con.proId where year(CONVERT(datetime,saildate,20))=year('"+time+"') and "
		 + " month(CONVERT(datetime,saildate,20))>=month('"+time+"')   group by purno, factory)a  left join (select "
		 + " bargainNo,fid from  factoryfund   group by bargainNo,fid) fu on a.purno=fu.BargainNo left join factoryinfo "
		 + " info on fu.fid=info.id)a where 1=1   group by kingdee )c on a.kingdee=c.kingdee left join ( "
		 + " select sum(amount_customs_declaration)amount_customs_declaration,kingdee from ( select sd.*,"
		 + " info.kingdee from shipment_bill_data sd left join (select bargainNo,fid from  factoryfund group by"
		 + " bargainNo,fid) fu on sd.bargainno=fu.BargainNo left join factoryinfo info on fu.fid=info.id)a"
		 + " group by kingdee)d on d.kingdee=a.kingdee)a "
         + ")d on d.kingdee=a.kingdee "
         + " left join ( select kingdee,sum(Get_Moeny)Get_Moeny1 from (select tab.Get_Moeny,info.kingdee"
         + ",tab.createtime,tab.Date_time from Tab_Factory_Money tab left join factoryinfo info on tab.Factory_id=info.id"
         + " where tab.Get_Moeny!=0 and datediff(day,createtime,getdate())<30)a group by kingdee)e"
         + " on a.kingdee=e.kingdee"
         + ")a left join Tab_Factory_yue yue on a.kingdee=yue.Factory_yue_kingdeId " +
			" left join  (SELECT Factory_id, Invoice_name = stuff((SELECT ',' + Invoice_name  " +
			" FROM (SELECT Factory_id,Invoice_name FROM Tab_Factory_Money  where Invoice_name is not null group by Factory_id ,Invoice_name) t  " +
			" WHERE t.Factory_id = p.Factory_id  FOR xml path('')) , 1 , 1 , '') " +
			" FROM (SELECT Factory_id,Invoice_name FROM Tab_Factory_Money  where Invoice_name is not null group by Factory_id ,Invoice_name) p " +
			" GROUP BY Factory_id) Factory_invice on a.Factory_id=Factory_invice.Factory_id " +
			" group by FactoryName,kingdee,Factory_invice.Invoice_name"
         + ")a where a.money1!=-a.Factory_yue_Money and kingdee!=0  "
         ;
		 if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
			  sql+="and FactoryName like ? ";
		  }
		  if(it.getKingdee()!=0){
			  sql+="and kingdee = ? ";
		  }
        conn = SQLDBhelper.getConnection();
        try {
        	sql+=" order by money2 desc";
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
				for(int j=0;j<18;j++){
				  i++;
				  stmt.setString(i, it.getCustomerManager());
				}
			  }
			if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
				  i++;
				  stmt.setString(i, "%"+it.getFactoryName()+"%");
			  }
			  if(it.getKingdee()!=0){
				  i++;
				  stmt.setInt(i,it.getKingdee() );
			  }
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase2 info=new ItemCase2();
				int kingdee=rs.getInt("kingdee");
				if(kingdee!=1208){
				double money1=rs.getDouble("money1");
				double Factory_yue_Money=rs.getDouble("Factory_yue_Money");
				if(money1+Factory_yue_Money!=0){
				info.setFactoryName(rs.getString("FactoryName"));
				info.setAllUnacceptableInvoiceAmounts(rs.getDouble("money1"));
				info.setAmountOfNonReceiptAndReceiptOfInvoices(rs.getDouble("money3"));
				info.setAmountOfUnpaidInvoices(rs.getDouble("money2"));
				info.setKingdee(rs.getInt("kingdee"));
				info.setPayment2007(rs.getDouble("Factory_yue_Money"));
				info.setAllUnacceptableInvoiceAmounts1(rs.getDouble("money4"));
				info.setInvoiceAmount(rs.getDouble("Get_Moeny1"));
				info.setInvoicName(rs.getString("invoice_name"));
				list.add(info);
				}
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


	@Override
	public List<ItemCase2> invoiceFactoryOwnedToUsNew(ItemCase2 it) {
		String time=it.getStartTime();
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " Select b.FactoryName,isnull(sum(a.Pay_Moeny),0) as PayMoney,isnull(sum(a.Get_Moeny),0) as GetMoney ,sum(Pay_Moeny)-sum(Get_Moeny) as amount "
				+ " ,min(a.Date_time) as DateTime,max(it.merchandManager2) as merchandManager2 ,max(it1.CustomerManager) as CustomerManager ,max(isnull(it.merchandManager1,'')) merchandManager1 ,max(isnull(it.Merchandising,'')) Merchandising  from Tab_Factory_Money a inner join factoryinfo b on a.Factory_id=b.id "
				+" left join itemcase it on a.Case_No=it.caseno  and it.merchandManager2!=''  and it.merchandManager2 is not null   "
				+" left join itemcase it1 on a.Case_No=it1.caseno  and   it1.CustomerManager!=''  and it1.CustomerManager is not null ";
		if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
			sql +=" where b.FactoryName like ? ";
		}
		sql += " group by b.FactoryName order by amount desc";

		conn = SQLDBhelper.getConnection();

		try {
			stmt = conn.prepareStatement(sql);
			if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())) {
				stmt.setString(1, "%" + it.getFactoryName() + "%");
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase2 info=new ItemCase2();
				info.setFactoryName(rs.getString("FactoryName"));
				info.setInvoiceAmount(rs.getDouble("PayMoney"));
				info.setAllUnacceptableInvoiceAmounts(rs.getDouble("GetMoney"));
				info.setAllUnacceptableInvoiceAmounts1(rs.getDouble("amount"));
				info.setStartTime(rs.getString("DateTime"));
				info.setMerchandManager1(rs.getString("CustomerManager"));
				info.setMerchandManager2(rs.getString("merchandManager2"));
				if(StringUtils.isNotEmpty(rs.getString("Merchandising"))){
					info.setMerchandising(rs.getString("merchandManager1")+","+rs.getString("Merchandising"));
				}else{
					info.setMerchandising(rs.getString("merchandManager1"));
				}
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
	public List<FactoryReconciliation> getAllDetailedAccounts(ItemCase2 itemcase) {
		String time=itemcase.getStartTime();
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from (select b.kingdee,b.FactoryName,b.Factory_id,b.CaseNo,b.bargain_no"
				+ ",a.createtime,isnull(a.Get_Moeny,0)Get_Moeny,isnull(a.Pay_Moeny,0)Pay_Moeny,isnull(z.rmb,0)rmb,isnull(b.friMoney,0)friMoney,isnull(c.friMoney1,0)friMoney1,"
				+ "isnull(d.amount_customs_declaration,0)amount_customs_declaration,it.CustomerManager ,"
				+ " it.MerchandManager1,it.MerchandManager2 , it.Merchandising ,it.MaturePurchase , "
				+ "it.OriginalPurchase " +
//				",b.remarks " +
				"from (select sum(fu.friMoney)friMoney,CaseNo,max(info.FactoryName)FactoryName"
				+ "  ,max(fu.fid)Factory_id,kingdee ," +
//				"max(fu.remarks) as remarks, " +
				" STUFF( ( SELECT    ',' + BargainNo   FROM factoryfund x left join factoryinfo y "
		        + "on y.id=x.fid  WHERE x.CaseNo = fu.CaseNo and y.kingdee=info.kingdee group by BargainNo  FOR XML PATH('')) ,1,1,'') AS  bargain_no "
		        + " from factoryfund fu left join "
				+ "factoryinfo info on fu.fid=info.id where info.kingdee=?  ";
				if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
					 sql+= " and  datediff(m,friFacDate,?)=0  and  datediff(yyyy,friFacDate,?)=0 ";
					}
				sql+= "   group by CaseNo,kingdee)b left join (select Case_No,sum(a.Pay_Moeny)Pay_Moeny,"
				+ "sum(a.Get_Moeny)Get_Moeny,max(createtime)createtime"

		 +"  from  (select bargain_no,Case_No,max(Factory_id)Factory_id,sum(Pay_Moeny)Pay_Moeny,sum(a.Get_Moeny)Get_Moeny,"
		 + "max(createtime)createtime from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id where info.kingdee=? "
		 +" group by bargain_no,Case_No ) a left join factoryinfo info on info.id=a.Factory_id where info.kingdee=?";
		if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
		 sql+= " and  datediff(m,createtime,?)=0  and  datediff(yyyy,createtime,?)=0 ";
		}
		 sql+=" group by kingdee,Case_No)a on a.Case_No=b.CaseNo "
		 		+ "	left join ("
		 		+ "select * from (select sum(rmb)rmb,max(timeDate)timeDate,CaseNo from "
		 		+ "(select a.*,info.kingdee,fund.CaseNo from (select purno,factory,"
		 		+ " sum( CAST(rmb AS decimal(11,0)))rmb,max(CONVERT(datetime,saildate,20))timeDate "
		 		+ " from contract con left join products p on p.id=con.proId "
		 		+ " where (year(CONVERT(datetime,saildate,20))=year('"+time+"') and"
		 		+ " month(CONVERT(datetime,saildate,20))>=month('"+time+"') )group by purno, factory)a "
		 		+ " left join ( select min(fid)fid,BargainNo,CaseNo from factoryfund group by BargainNo,CaseNo) fund "
		 		+ " on fund.BargainNo=a.purno left join FactoryInfo info on info.id=fund.fid )x "
		 		+ " where x.kingdee=? group by kingdee,CaseNo)z)z on   z.caseno =b.caseno  "
		 		+ " left join  itemcase it on it.caseno=a.case_no "
				+ "   "
				+ "   left join (select sum(fu.friMoney)friMoney1,CaseNo from factoryfund fu left join "
				+ "factoryinfo info on fu.fid=info.id where info.kingdee=? and friMoney is not null ";
				if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
					 sql+= " and  datediff(m,friFacDate,?)=0  and  datediff(yyyy,friFacDate,?)=0 ";
					}
				sql+= " and state='<font color=green>已完成款项</font>' group by CaseNo )c on c.CaseNo=a.Case_No"
						+ " left join (select sum(amount_customs_declaration)amount_customs_declaration,sb.caseno "
						+ "  from  shipment_bill_data sb left join  (select bargainNo,fid from  factoryfund group by bargainNo,fid) "
						+ " fu on sb.bargainNo=fu.BargainNo left join factoryinfo "
						+ "  info on fu.fid=info.id where info.kingdee= ? ";
						if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
							 sql+= " and  datediff(m,date,?)=0  and  datediff(yyyy,date,?)=0 ";
							}
						sql+= " group by sb.caseno) d on d.caseno=a.Case_No"
						+ " )x "
				+ " where 1=1";

		if(itemcase.getCustomerManager()!=null&&!"".equalsIgnoreCase(itemcase.getCustomerManager())){
			sql+=" and (CustomerManager like ?  or MerchandManager1 like ? or MerchandManager2 like ?  "
					+ "or Merchandising like ?  or MaturePurchase like ?  or OriginalPurchase like ?)  ";
		}
		if(itemcase.getMerchandManager1()!=null&&!"".equalsIgnoreCase(itemcase.getMerchandManager1())){
			sql+=" and MerchandManager1 like ? ";
		}
		if(itemcase.getMerchandManager2()!=null&&!"".equalsIgnoreCase(itemcase.getMerchandManager2())){
			sql+=" and MerchandManager2 like ? ";
		}
		if(itemcase.getUnderInvoice()==0){

		}else if(itemcase.getUnderInvoice()==-1){
	        sql+=" and friMoney!=Get_Moeny+amount_customs_declaration+rmb" ;
		}else if(itemcase.getUnderInvoice()==1){
			 sql+=" and friMoney=Get_Moeny+amount_customs_declaration+rmb" ;
		}else if(itemcase.getUnderInvoice()==2){
			 sql+=" and friMoney=friMoney1 and friMoney!=Get_Moeny+amount_customs_declaration+rmb " ;
		}

        conn = SQLDBhelper.getConnection();
        sql+=" order by friMoney-Get_Moeny-amount_customs_declaration-rmb desc ";
        try {
			stmt = conn.prepareStatement(sql);
			int i=0;
			stmt.setInt(1, itemcase.getKingdee());
			stmt.setInt(2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}
			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}

			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}
			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}

			if(itemcase.getCustomerManager()!=null&&!"".equalsIgnoreCase(itemcase.getCustomerManager())){
				for(int j=0;j<6;j++){
					i++;
					stmt.setString(i+2,"%"+itemcase.getCustomerManager()+"%");
				}
			}
			if(itemcase.getMerchandManager1()!=null&&!"".equalsIgnoreCase(itemcase.getMerchandManager1())){
				i++;
				stmt.setString(i+2, "%"+itemcase.getMerchandManager1()+"%");
			}
			if(itemcase.getMerchandManager2()!=null&&!"".equalsIgnoreCase(itemcase.getMerchandManager2())){
				i++;
				stmt.setString(i+2, "%"+itemcase.getMerchandManager2()+"%");
			}

			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setFactoryName(rs.getString("FactoryName"));
				con.setAmountCredit(rs.getDouble("Pay_Moeny"));
				con.setCurrentDebitAmount(rs.getDouble("Get_Moeny"));
				con.setKingdee(rs.getInt("kingdee"));
				con.setCreateTime(rs.getString("createtime"));
				con.setCaseNo(rs.getString("caseNo"));
				con.setFactoryId(rs.getInt("Factory_id"));
				con.setBargainNo(rs.getString("bargain_No"));
				con.setContractAmount(rs.getDouble("friMoney"));
				con.setAmountPaid(rs.getDouble("friMoney1"));
				con.setFinalTimeReceiptAndReceipt(time);
				con.setShipmentBillAmount(rs.getDouble("rmb"));
				con.setAmountCustomsDeclaration(rs.getDouble("amount_customs_declaration"));
				con.setMerchandManager2(rs.getString("MerchandManager2"));
				con.setMerchandManager1(rs.getString("MerchandManager1"));
				//202012 rose 增加合同备注暂时删除
//				con.setRemarks(rs.getString("remarks"));
				list.add(con);
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
	public List<ItemCase2> invoiceDocumentarySaleOwnedToUs(ItemCase2 it) {
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 String sql = " select * from ( select x.*,tab.Factory_yue_Money from ( select min(FactoryName)FactoryName,kingdee,MerchandManager1 ,Convert(decimal(18,2),sum(money1))money1,sum(money2)money2,sum(money3)money3"
		 		+ "  from(  select a.FactoryName,a.money3,a.kingdee,b.money2,c.money1,a.MerchandManager1 from ( "
		 +"  select kingdee,min(FactoryName)FactoryName,sum(money3)money3,MerchandManager1 from ( "
		 +" select FactoryName,(Pay_Moeny-Get_Moeny)money3,kingdee,MerchandManager1 from ( "
		 +"  select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName,MerchandManager1 from ( "
		 +"  select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager1 IS NULL OR it.MerchandManager1='')"
		 + " then it.Merchandising else MerchandManager1 "
		 +"         end) as MerchandManager1 "
		 +"   from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id left join itemcase it "
		 + "on it.CaseNo=a.Case_No "
		 +" where  Case_No  in ( select a.CaseNo from( "
		 +" select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
         +" )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null and"
         + " state='<font color=green>已完成款项</font>' group by caseno )b "
         +"  on a.caseno=b.CaseNo where a.friMoney1=b.friMoney) ";

         if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
        	sql+=" and(it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =? "
        			+ "or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?)";
         }

         sql+=" )y group by kingdee,MerchandManager1)x "
		 +"  )s group by kingdee,MerchandManager1)a "
		 + "left join ("
		 + "   select kingdee,min(FactoryName)FactoryName,sum(money3)money2,MerchandManager1 from ( "
		 + "  select FactoryName,(Pay_Moeny-Get_Moeny)money3,kingdee,MerchandManager1 from ( "
		 + "  select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName,MerchandManager1 from ( "
		 + "  select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager1 IS NULL OR it.MerchandManager1='') "
		 + " then it.Merchandising else MerchandManager1 "
		 + "         end) as MerchandManager1 "
		 + "   from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id left join itemcase it "
		 + "on it.CaseNo=a.Case_No "
		 + " where  Case_No   in ( select CaseNo from ( "
		 + "  select max(friFacDate)friFacDate,CaseNo from factoryfund where friMoney is not null "
		 + "  and state='<font color=green>已完成款项</font>' group by caseno)a "
         + "  where Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095) "
         + " and kingdee is not null";
         if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
         	sql+=" and(it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =? "
         			+ "or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?)";
          }
         sql+= " )y  group by kingdee,MerchandManager1)x "
		 + " )s group by kingdee,MerchandManager1"
		 +"		   )b on a.kingdee=b.kingdee  and a.MerchandManager1=b.MerchandManager1 "
		 + "left join ( "
		 + " select min(FactoryName)FactoryName,kingdee,sum(money1)money1,MerchandManager1 from ( "
		 + " 		   select FactoryName, "
		 + " (Pay_Moeny-Get_Moeny)money1,kingdee,MerchandManager1 from(  "
         + " select min(FactoryName)FactoryName,sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,MerchandManager1 from (  "
         + " select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager1 IS NULL OR it.MerchandManager1='') then "
		 + "  it.Merchandising else MerchandManager1 end) as MerchandManager1 "
		 + "  from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id "
		 + "left join itemcase it on it.caseno=a.case_no where 1=1 ";
		 if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
	         	sql+=" and(it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =? "
	         			+ "or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?)";
	          }
		 sql+= " )f group by kingdee,MerchandManager1)v)z group by kingdee,MerchandManager1  )c "
         + " on a.kingdee=c.kingdee and a.MerchandManager1=c.MerchandManager1  "
         + " )x group by MerchandManager1,kingdee) x left join Tab_Factory_yue tab on x.kingdee=tab.Factory_yue_kingdeId where kingdee not in ("
         + " select kingdee from ( "
         + " select (Pay_Moeny-Get_Moeny)money1,isnull(Factory_yue_Money,0)Factory_yue_Money,kingdee from ( "
         + " select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(Factory_yue_Money)"
         + " Factory_yue_Money from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id "
         + " left join Tab_Factory_yue tab on info.kingdee=tab.Factory_yue_kingdeId "
         + " group by kingdee)a)x where money1=-Factory_yue_Money) and x.money1!=0  and kingdee!=0)a where 1=1 ";
		 if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
			  sql+="and FactoryName like ? ";
		  }
		  if(it.getKingdee()!=0){
			  sql+="and kingdee = ? ";
		  }
		  if(it.getMerchandManager1()!=null&&!"".equalsIgnoreCase(it.getMerchandManager1())){
			  sql+="and MerchandManager1 like ? ";
		  }
        conn = SQLDBhelper.getConnection();
        try {
        	sql+=" order by a.MerchandManager1 desc,a.money3 desc";
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
				for(int j=0;j<18;j++){
				  i++;
				  stmt.setString(i, it.getCustomerManager());
				}
			  }
			if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
				  i++;
				  stmt.setString(i, "%"+it.getFactoryName()+"%");
			  }
			  if(it.getKingdee()!=0){
				  i++;
				  stmt.setInt(i,it.getKingdee() );
			  }
			  if(it.getMerchandManager1()!=null&&!"".equalsIgnoreCase(it.getMerchandManager1())){
				  i++;
				  stmt.setString(i, "%"+it.getMerchandManager1()+"%");
			  }
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase2 info=new ItemCase2();
				int kingdee=rs.getInt("kingdee");
				if(kingdee!=1208){
				info.setMerchandManager1(rs.getString("merchandManager1"));
				info.setAllUnacceptableInvoiceAmounts(rs.getDouble("money1"));
				info.setAmountOfNonReceiptAndReceiptOfInvoices(rs.getDouble("money3"));
				info.setAmountOfUnpaidInvoices(rs.getDouble("money2"));
				info.setKingdee(rs.getInt("kingdee"));
				info.setFactoryName(rs.getString("FactoryName"));
				info.setPayment2007(rs.getDouble("Factory_yue_Money"));
				info.setCustomerManager(it.getCustomerManager());
				list.add(info);
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

	@Override
	public List<ItemCase2> invoiceDocumentaryPurchaseOwnedToUs(ItemCase2 it) {
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select * from( select x.*,tab.Factory_yue_Money from ( select min(FactoryName)FactoryName,kingdee,MerchandManager2 ,Convert(decimal(18,2),sum(money1))money1,sum(money2)money2,sum(money3)money3"
		 		+ "  from(  select a.FactoryName,a.money3,a.kingdee,b.money2,c.money1,a.MerchandManager2 from ( "
		 +"  select kingdee,min(FactoryName)FactoryName,sum(money3)money3,MerchandManager2 from ( "
		 +" select FactoryName,(Pay_Moeny-Get_Moeny)money3,kingdee,MerchandManager2 from ( "
		 +"  select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName,MerchandManager2 from ( "
		 +"  select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager2 IS NULL OR it.MerchandManager2='')"
		 + " then it.OriginalPurchase else MerchandManager2 "
		 +"         end) as MerchandManager2 "
		 +"   from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id left join itemcase it "
		 + "on it.CaseNo=a.Case_No "
		 +" where  Case_No  in ( select a.CaseNo from( "
		 +" select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
         +" )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null and"
         + " state='<font color=green>已完成款项</font>' group by caseno )b "
         +"  on a.caseno=b.CaseNo where a.friMoney1=b.friMoney) )y group by kingdee,MerchandManager2)x "
		 +"  )s group by kingdee,MerchandManager2)a "
		 + "left join ("
		 + "  select kingdee,min(FactoryName)FactoryName,sum(money3)money2,MerchandManager2 from ( "
		 + "  select FactoryName,(Pay_Moeny-Get_Moeny)money3,kingdee,MerchandManager2 from ( "
		 + "  select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName,MerchandManager2 from ( "
		 + "  select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager2 IS NULL OR it.MerchandManager2='') "
		 + " then it.OriginalPurchase else MerchandManager2 "
		 + "         end) as MerchandManager2 "
		 + "   from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id left join itemcase it "
		 + "on it.CaseNo=a.Case_No "
		 + " where  Case_No   in ( select CaseNo from ( "
		 + "  select max(friFacDate)friFacDate,CaseNo from factoryfund where friMoney is not null "
		 + "  and state='<font color=green>已完成款项</font>' group by caseno)a "
         + "  where Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095) "
         + " and kingdee is not null "
         + " )y  group by kingdee,MerchandManager2)x "
		 + " )s group by kingdee,MerchandManager2"
		 +"		   )b on a.kingdee=b.kingdee  and a.MerchandManager2=b.MerchandManager2 "
		 + "left join ( "
		 + " select min(FactoryName)FactoryName,kingdee,sum(money1)money1,MerchandManager2 from ( "
		 + " 		   select FactoryName, "
		 + " (Pay_Moeny-Get_Moeny)money1,kingdee,MerchandManager2 from(  "
         + " select min(FactoryName)FactoryName,sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,MerchandManager2 from (  "
         + " select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager2 IS NULL OR it.MerchandManager2='') then "
		 + "  it.OriginalPurchase else MerchandManager2 end) as MerchandManager2 "
		 + "  from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id left join itemcase it on it.caseno=a.case_no "
		 + " )f group by kingdee,MerchandManager2)v)z group by kingdee,MerchandManager2  )c "
         + " on a.kingdee=c.kingdee and a.MerchandManager2=c.MerchandManager2  "
         + " )x group by MerchandManager2,kingdee) x left join Tab_Factory_yue tab on x.kingdee=tab.Factory_yue_kingdeId where kingdee not in ("
         + " select kingdee from ( "
         + " select (Pay_Moeny-Get_Moeny)money1,isnull(Factory_yue_Money,0)Factory_yue_Money,kingdee from ( "
         + " select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(Factory_yue_Money)"
         + " Factory_yue_Money from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id "
         + " left join Tab_Factory_yue tab on info.kingdee=tab.Factory_yue_kingdeId "
         + " group by kingdee)a)x where money1=-Factory_yue_Money) and x.money1!=0 and kingdee!=0)a where 1=1   ";
		if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
			  sql+="and FactoryName like ? ";
		  }
		  if(it.getKingdee()!=0){
			  sql+="and kingdee = ? ";
		  }
		  if(it.getMerchandManager1()!=null&&!"".equalsIgnoreCase(it.getMerchandManager1())){
			  sql+="and MerchandManager2 like ? ";
		  }
      conn = SQLDBhelper.getConnection();
      try {
      	sql+=" order by a.MerchandManager2 desc,a.money3 desc";
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
				  i++;
				  stmt.setString(i, "%"+it.getFactoryName()+"%");
			  }
			  if(it.getKingdee()!=0){
				  i++;
				  stmt.setInt(i,it.getKingdee() );
			  }
			  if(it.getMerchandManager1()!=null&&!"".equalsIgnoreCase(it.getMerchandManager1())){
				  i++;
				  stmt.setString(i, "%"+it.getMerchandManager1()+"%");
			  }
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase2 info=new ItemCase2();
				int kingdee=rs.getInt("kingdee");
				if(kingdee!=1208){
			     info.setMerchandManager2(rs.getString("merchandManager2"));
				info.setAllUnacceptableInvoiceAmounts(rs.getDouble("money1"));
				info.setAmountOfNonReceiptAndReceiptOfInvoices(rs.getDouble("money3"));
				info.setAmountOfUnpaidInvoices(rs.getDouble("money2"));
				info.setKingdee(rs.getInt("kingdee"));
				info.setFactoryName(rs.getString("FactoryName"));
				info.setPayment2007(rs.getDouble("Factory_yue_Money"));

				list.add(info);
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

	@Override
	public List<FactoryReconciliation> getFinalInvoice(ItemCase2 itemcase) {
		String time=itemcase.getStartTime();
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from (select b.caseno, b.Factory_id, b.kingdee,b.FactoryName,b.bargain_no, isnull(a.Pay_Moeny,0)Pay_Moeny"
				+ ", isnull(a.Get_Moeny,0)Get_Moeny,a.createtime,isnull(z.rmb,0)rmb,isnull(b.friMoney,0)friMoney,isnull(c.friMoney1,0)friMoney1,"
				+ "isnull(d.amount_customs_declaration,0)amount_customs_declaration,it.CustomerManager ,"
				+ " it.MerchandManager1,it.MerchandManager2 , it.Merchandising ,it.MaturePurchase , "
				+ "it.OriginalPurchase from "
				+ "(select max(fu.fid)Factory_id,sum(fu.friMoney)friMoney,CaseNo,kingdee,"
				+ "max(info.FactoryName)FactoryName, STUFF( ( SELECT    ',' + BargainNo   FROM factoryfund  x left join factoryinfo y on y.id=x.fid "
				+ " WHERE x.CaseNo = fu.CaseNo  and y.kingdee=info.kingdee "
				+ " group by BargainNo  FOR XML PATH('')) ,1,1,'') AS  bargain_no from factoryfund fu left join "
				+ "factoryinfo info on fu.fid=info.id where info.kingdee=?  "
				+ " and CaseNo  in ( select a.CaseNo from(  select sum(friMoney)friMoney1,caseno "
				+ " from factoryfund a WHERE friMoney is not null group by caseno )a left join "
				+ " (select sum(friMoney)friMoney,caseno from factoryfund  where friMoney is not null "
				+ " and state='<font color=green>已完成款项</font>' group by caseno )b  on a.CaseNo=b.CaseNo where a.friMoney1=b.friMoney) ";

				if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
					 sql+= " and  datediff(m,friFacDate,?)=0  and  datediff(yyyy,friFacDate,?)=0 ";
					}
				sql+= "   group by CaseNo,kingdee)b  "
						+ " left join "
				+ "(select Case_No,sum(a.Pay_Moeny)Pay_Moeny,max(Factory_id)Factory_id"
				+ ",sum(a.Get_Moeny)Get_Moeny,max(createtime)createtime"
				+ "  from "
				+ "( select bargain_no,Case_No,max(Factory_id)Factory_id,sum(a.Pay_Moeny)Pay_Moeny,"
				+ "sum(a.Get_Moeny)Get_Moeny,max(createtime)createtime from Tab_Factory_Money a left join factoryinfo info on "
				+ "info.id=a.Factory_id where info.kingdee=? group by bargain_no,Case_No ) a left join factoryinfo info on info.id=a.Factory_id "
                + "  where info.kingdee=? and Case_No  in ( select a.CaseNo from( "
                + " select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
                + " )a left join (select sum(friMoney)friMoney,caseno from factoryfund "
                + "where friMoney is not null and state='<font color=green>已完成款项</font>' group by caseno )b "
                + " on a.CaseNo=b.CaseNo where a.friMoney1=b.friMoney)";

       if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
		 sql+= " and  datediff(m,createtime,?)=0  and  datediff(yyyy,createtime,?)=0 ";
		}
       sql+=  " group by Case_No)a  on a.Case_No=b.CaseNo"
    		   + "	left join "
    		   + "(select * from (select sum(rmb)rmb,max(timeDate)timeDate,CaseNo from "
		 		+ "(select a.*,info.kingdee,fund.CaseNo from (select purno,factory,"
		 		+ " sum( CAST(rmb AS decimal(11,0)))rmb,max(CONVERT(datetime,saildate,20))timeDate "
		 		+ " from contract con left join products p on p.id=con.proId "
		 		+ " where (year(CONVERT(datetime,saildate,20))=year('"+time+"') and"
		 		+ " month(CONVERT(datetime,saildate,20))>=month('"+time+"') )group by purno, factory)a "
		 		+ " left join ( select min(fid)fid,BargainNo,CaseNo from factoryfund group by BargainNo,CaseNo) fund "
		 		+ " on fund.BargainNo=a.purno left join FactoryInfo info on info.id=fund.fid )x "
		 		+ " where x.kingdee=? group by kingdee,CaseNo)z)z on   z.caseno =b.caseno  "
       		+ " left join itemcase it on it.caseno=a.case_no "

				+ "   left join (select sum(fu.friMoney)friMoney1,CaseNo from factoryfund fu left join "
				+ "factoryinfo info on fu.fid=info.id where info.kingdee=?  "
				+ " and CaseNo  in ( select a.CaseNo from(  select sum(friMoney)friMoney1,caseno "
				+ " from factoryfund a  group by caseno )a left join "
				+ " (select sum(friMoney)friMoney,caseno from factoryfund  where friMoney is not null "
				+ " and state='<font color=green>已完成款项</font>' group by caseno )b  on a.CaseNo=b.CaseNo where a.friMoney1=b.friMoney) ";

				if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
					 sql+= " and  datediff(m,friFacDate,?)=0  and  datediff(yyyy,friFacDate,?)=0 ";
					}
				sql+= " group by CaseNo )c on c.CaseNo=a.Case_No"
						+ " left join (select sum(amount_customs_declaration)amount_customs_declaration,sb.caseno "
						+ "  from  shipment_bill_data sb left join  (select bargainNo,fid from  factoryfund group by bargainNo,fid) "
						+ " fu on sb.bargainNo=fu.BargainNo left join factoryinfo "
						+ "  info on fu.fid=info.id where info.kingdee= ? ";
						if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
							 sql+= " and  datediff(m,date,?)=0  and  datediff(yyyy,date,?)=0 ";
							}
						sql+= " group by sb.caseno) d on d.caseno=a.Case_No"
						+ " )x "

       		+ " where 1=1 ";
        conn = SQLDBhelper.getConnection();
        if(itemcase.getCustomerManager()!=null&&!"".equalsIgnoreCase(itemcase.getCustomerManager())){
			sql+=" and (CustomerManager like ?  or MerchandManager1 like ? or MerchandManager2 like ?  "
					+ "or Merchandising like ?  or MaturePurchase like ?  or OriginalPurchase like ? ) ";
		}
        	if(itemcase.getMerchandManager1()!=null){
    			sql+=" and MerchandManager1 like ? ";
    		}
    		if(itemcase.getMerchandManager2()!=null){
    			sql+=" and MerchandManager2 like ? ";
    		}
    		if(itemcase.getUnderInvoice()==0){

    		}else if(itemcase.getUnderInvoice()==-1){
    	        sql+=" and friMoney!=Get_Moeny+amount_customs_declaration+rmb" ;
    		}else if(itemcase.getUnderInvoice()==1){
    			 sql+=" and friMoney=Get_Moeny+amount_customs_declaration+rmb" ;
    		}else if(itemcase.getUnderInvoice()==2){
    			 sql+=" and friMoney=friMoney1 and friMoney!=Get_Moeny+amount_customs_declaration+rmb " ;
    		}
    		sql+=" order by friMoney-Get_Moeny-amount_customs_declaration-rmb desc ";
            conn = SQLDBhelper.getConnection();
            try {
    			stmt = conn.prepareStatement(sql);
    			int i=0;
    			stmt.setInt(1, itemcase.getKingdee());
    			stmt.setInt(2, itemcase.getKingdee());
    			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				}
    			i++;
    			stmt.setInt(i+2, itemcase.getKingdee());
    			i++;
    			stmt.setInt(i+2, itemcase.getKingdee());
    			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				}
    			i++;
    			stmt.setInt(i+2, itemcase.getKingdee());
    			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				}
    			i++;
    			stmt.setInt(i+2, itemcase.getKingdee());
    			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				i++;
    				stmt.setString(i+2, itemcase.getDateSampleUploading());
    				}
    			if(itemcase.getCustomerManager()!=null&&!"".equalsIgnoreCase(itemcase.getCustomerManager())){
    				for(int j=0;j<6;j++){
    					i++;
    					stmt.setString(i+2, itemcase.getCustomerManager());
    				}
    			}
    			if(itemcase.getMerchandManager1()!=null){
    				i++;
    				stmt.setString(i+2, "%"+itemcase.getMerchandManager1()+"%");
    			}
    			if(itemcase.getMerchandManager2()!=null){
    				i++;
    				stmt.setString(i+2, "%"+itemcase.getMerchandManager2()+"%");
    			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setFactoryName(rs.getString("FactoryName"));
				con.setAmountCredit(rs.getDouble("Pay_Moeny"));
				con.setCurrentDebitAmount(rs.getDouble("Get_Moeny"));
				con.setKingdee(rs.getInt("kingdee"));
				con.setCreateTime(rs.getString("createtime"));
				con.setCaseNo(rs.getString("caseNo"));
				con.setFactoryId(rs.getInt("Factory_id"));
				con.setBargainNo(rs.getString("bargain_No"));
				con.setContractAmount(rs.getDouble("friMoney"));
				con.setAmountPaid(rs.getDouble("friMoney1"));
				con.setFinalTimeReceiptAndReceipt(time);
				con.setShipmentBillAmount(rs.getDouble("rmb"));
				con.setAmountCustomsDeclaration(rs.getDouble("amount_customs_declaration"));
				con.setMerchandManager2(rs.getString("MerchandManager2"));
				con.setMerchandManager1(rs.getString("MerchandManager1"));
				list.add(con);
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
	public List<FactoryReconciliation> factoryNameByInvoiceName(String  invoiceName) {
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select b.FactoryName from VIEW_Factory_Money a ,factoryinfo b " +
				" where a.Factory_id=b.id " +
				" and a.Invoice_name=? " +
				" group by b.FactoryName ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, invoiceName);

			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setFactoryName(rs.getString("FactoryName"));
				list.add(con);
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
	public List<FactoryReconciliation> factoryPayInfo(String  factoryName) {
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select fu.CaseNo,fu.friMoney,fu.friFacDate FROM factoryfund fu " +
				" LEFT JOIN factoryinfo info ON fu.fid = info.id  " +
				" where fu.friMoney!=0   and info.FactoryName=? " +
				" order by fu.CaseNo,fu.friFacDate ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, factoryName);

			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setCaseNo(rs.getString("CaseNo"));
				con.setPrice(rs.getDouble("friMoney"));
				con.setCreateTime(rs.getString("friFacDate"));
				list.add(con);
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
	public List<FactoryReconciliation> factoryPayInfoNew(String  factoryName) {
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
//		String sql = " Select b.FactoryName,a.Case_No,a.Pay_Moeny,a.Get_Moeny,a.Bargain_No,a.createtime " +
//				" from Tab_Factory_Money a inner join factoryinfo b on a.Factory_id=b.id " +
//				" where b.FactoryName=? " +
//				" order by a.Case_No ,a.createtime desc ";

//		String sql = " Select a.Factory_id,b.FactoryName,a.Case_No,isnull(sum(a.Pay_Moeny),0) as PayMoney,isnull(sum(a.Get_Moeny),0) as GetMoney,sum(Pay_Moeny)-sum(Get_Moeny) as amount,a.remark " +
//				" from Tab_Factory_Money a inner join factoryinfo b on a.Factory_id=b.id " +
//				" where b.FactoryName=? " +
//				" group by a.Factory_id,b.FactoryName,a.Case_No,a.remark order by amount desc ";
		String sql = " Select max(a.Factory_id) Factory_id ,max(b.FactoryName) FactoryName ,a.Case_No,sum(isnull(a.Pay_Moeny,0)) as PayMoney,sum(isnull(a.Get_Moeny,0)) as GetMoney, " +
				"  sum(isnull(Pay_Moeny,0))-sum(isnull(Get_Moeny,0)) as amount, " +
				"  a.remark,min(a.Date_time) minDate_time ,max(it.merchandManager2) merchandManager2 ,max(it.CustomerManager) CustomerManager " +
				" ,max(isnull(it.merchandManager1,'')) merchandManager1 ,max(isnull(it.Merchandising,'')) Merchandising "+
				" from Tab_Factory_Money a inner join factoryinfo b on a.Factory_id=b.id  left join itemcase it on a.Case_No=it.caseno  where b.FactoryName=? " +
				" group by  a.Case_No,a.remark order by amount desc ";

		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, factoryName);

			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setBargainNo(rs.getString("Factory_id"));
				con.setFactoryName(rs.getString("FactoryName"));
				con.setCaseNo(rs.getString("Case_No"));
				con.setPrice(rs.getDouble("PayMoney"));
				con.setEndingBalance(rs.getDouble("GetMoney"));
				con.setAmountCredit(rs.getDouble("amount"));
				con.setRemarks(rs.getString("remark"));
				con.setCreateTime(rs.getString("minDate_time"));
				con.setMerchandManager1(rs.getString("CustomerManager"));
				con.setMerchandManager2(rs.getString("merchandManager2"));
				if(StringUtils.isNotEmpty(rs.getString("Merchandising"))){
					con.setMerchandising(rs.getString("merchandManager1")+","+rs.getString("Merchandising"));
				}else{
					con.setMerchandising(rs.getString("merchandManager1"));
				}
//				con.setCreateTime(rs.getString("createtime"));
				list.add(con);
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
	public List<FactoryReconciliation> casePayInfoNew(String  caseNo) {
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " Select a.Case_No,sum(isnull(a.Pay_Moeny,0)) as PayMoney,sum(isnull(a.Get_Moeny,0)) as GetMoney, " +
				"  sum(isnull(Pay_Moeny,0))-sum(isnull(Get_Moeny,0)) as amount, " +
				"  min(a.Date_time) minDate_time ,max(it.merchandManager2) merchandManager2 ,max(it.CustomerManager) CustomerManager " +
				" ,max(isnull(it.merchandManager1,'')) merchandManager1 ,max(isnull(it.Merchandising,'')) Merchandising "+
				" from Tab_Factory_Money a   left join itemcase it on a.Case_No=it.caseno  " ;
		if(StringUtils.isNotEmpty(caseNo)){
			sql+="where a.Case_No=? ";
		}

		sql+=" group by  a.Case_No order by amount desc ";

		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			if(StringUtils.isNotEmpty(caseNo)){
				stmt.setString(1, caseNo);
			}

			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
//				con.setBargainNo(rs.getString("Factory_id"));
//				con.setFactoryName(rs.getString("FactoryName"));
				con.setCaseNo(rs.getString("Case_No"));
				con.setPrice(rs.getDouble("PayMoney"));
				con.setEndingBalance(rs.getDouble("GetMoney"));
				con.setAmountCredit(rs.getDouble("amount"));
//				con.setRemarks(rs.getString("remark"));
				con.setCreateTime(rs.getString("minDate_time"));
				con.setMerchandManager1(rs.getString("CustomerManager"));
				con.setMerchandManager2(rs.getString("merchandManager2"));
				if(StringUtils.isNotEmpty(rs.getString("Merchandising"))){
					con.setMerchandising(rs.getString("merchandManager1")+","+rs.getString("Merchandising"));
				}else{
					con.setMerchandising(rs.getString("merchandManager1"));
				}
//				con.setCreateTime(rs.getString("createtime"));
				list.add(con);
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
	public List<FactoryReconciliation> getPayInfo(String  factoryName) {
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = " Select a.Case_No,b.FactoryName,isnull(sum(a.Pay_Moeny),0) as PayMoney,isnull(sum(a.Get_Moeny),0) as GetMoney,sum(Pay_Moeny)-sum(Get_Moeny) as amount,it.CustomerManager  " +
				" from Tab_Factory_Money a inner join factoryinfo b on a.Factory_id=b.id inner join itemcase it on a.Case_No=it.caseno   " +
				" where it.CustomerManager !='' " ;
		if(factoryName!=null&&!"".equalsIgnoreCase(factoryName)){
			sql +=" and it.CustomerManager=? ";
		}

		sql +=" group by b.FactoryName,a.Case_No,it.CustomerManager  order by amount desc,b.FactoryName asc ";


		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			if(factoryName!=null&&!"".equalsIgnoreCase(factoryName)){
				stmt.setString(1, factoryName.trim());
			}


			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setFactoryName(rs.getString("FactoryName"));
				con.setCaseNo(rs.getString("Case_No"));
				con.setPrice(rs.getDouble("PayMoney"));
				con.setEndingBalance(rs.getDouble("GetMoney"));
				con.setAmountCredit(rs.getDouble("amount"));
				con.setMerchandManager1(rs.getString("CustomerManager"));
				list.add(con);
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
	public List<FactoryReconciliation> factoryPayInfoDetail(String  factoryName,int flag) {
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

//		String sql = " Select b.FactoryName,a.Case_No,a.Bargain_No,a.Pay_Moeny,a.Get_Moeny,a.Date_time ,it.merchandManager2,it.CustomerManager " +
//				" from Tab_Factory_Money a inner join itemcase it on a.Case_No=it.caseno  left join factoryinfo b on a.Factory_id=b.id  " +
//				" where a.Factory_id=? " +
//				" and  a.Case_No=? " +
//				" order by a.Case_No ,a.Date_time desc  ";

		String sql = "  Select max(b.FactoryName) FactoryName,max(a.Case_No) Case_No,max(a.Bargain_No) Bargain_No,sum(a.Pay_Moeny) Pay_Moeny,sum(a.Get_Moeny) Get_Moeny,min(a.Date_time) Date_time,max(it.merchandManager2) merchandManager2,max(it.CustomerManager) CustomerManager  " +
				",max(isnull(it.merchandManager1,'')) merchandManager1 ,max(isnull(it.Merchandising,'')) Merchandising "+
				" from Tab_Factory_Money a inner join itemcase it on a.Case_No=it.caseno  left join factoryinfo b on a.Factory_id=b.id    " ;
		if(flag==1){
			sql+=" where a.Factory_id=? and  a.Case_No=? ";
		}else{
			sql+=" where  a.Case_No=? ";
		}
			sql+=" group by a.Bargain_No  ";

		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			if(flag==1){
				stmt.setString(1, factoryName.split(",")[0]);
				stmt.setString(2, factoryName.split(",")[1]);
			}else{
				stmt.setString(1, factoryName);

			}


			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setFactoryName(rs.getString("FactoryName"));
				con.setCaseNo(rs.getString("Case_No"));
				con.setBargainNo(rs.getString("Bargain_No"));
				con.setPrice(rs.getDouble("Pay_Moeny"));
				con.setEndingBalance(rs.getDouble("Get_Moeny"));
				con.setCreateTime(rs.getString("Date_time"));
				con.setMerchandManager1(rs.getString("CustomerManager"));
				con.setMerchandManager2(rs.getString("merchandManager2"));
				if(StringUtils.isNotEmpty(rs.getString("Merchandising"))){
					con.setMerchandising(rs.getString("merchandManager1")+","+rs.getString("Merchandising"));
				}else{
					con.setMerchandising(rs.getString("merchandManager1"));
				}

				//发票系统在显示工厂欠票的同时，还可以显示 该厂涉及的所有出运联系单
				con.setProId("N/A");
				con.setCkRmb("");
				String sql2 = "select proId,rmb from  reportform.[dbo].contract where purno=?";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setString(1, rs.getString("Bargain_No"));
				rs2 = stmt2.executeQuery();
				while(rs2.next()) {
					con.setProId(rs2.getString("proId"));
					con.setCkRmb(rs2.getString("rmb"));
				}

				list.add(con);
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
	public List<FactoryInvoice> factoryGetInfoDetail(FactoryInvoiceParam param) {
		List<FactoryInvoice> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "Select a.id,a.Factory_id,a.Case_No,a.Date_time,a.Pay_Moeny,a.Get_Moeny,a.Direction,a.Bargain_No," +
				"a.Lab_No,a.remarks,a.ApNumer,a.createtime,a.Invoice_name,a.remark,a.nonum,a.times," +
				"(select sum(b.Get_Moeny) from Tab_Factory_Money b where b.Factory_id= a.Factory_id) as total_mo " +
				"FROM  Tab_Factory_Money a WHERE a.Get_Moeny > 0 ";
		if (null != param && StringUtils.isNotEmpty(param.getCaseNo())) {
			sql += " and a.Case_No = ?";
		}
		if (null != param && StringUtils.isNotEmpty(param.getFactoryId())) {
			sql += " and a.Factory_id = ?";
		}
		if (null != param && StringUtils.isNotEmpty(param.getLabNo())) {
			sql += " and a.Lab_No = ?";
		}
		if (null != param && StringUtils.isNotEmpty(param.getBeginTime())) {
			sql += " and a.Date_time >= ?";
		}
		if (null != param && StringUtils.isNotEmpty(param.getEndTime())) {
			sql += " and a.Date_time < ?";
		}
		System.err.println(sql);
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			int i = 1;

			if (null != param && StringUtils.isNotEmpty(param.getCaseNo())) {
				stmt.setString(i++, param.getCaseNo());
			}
			if (null != param && StringUtils.isNotEmpty(param.getFactoryId())) {
				stmt.setString(i++, param.getFactoryId());
			}
			if (null != param && StringUtils.isNotEmpty(param.getLabNo())) {
				stmt.setString(i++, param.getLabNo());
			}
			if (null != param && StringUtils.isNotEmpty(param.getBeginTime())) {
				stmt.setString(i++, param.getBeginTime());
			}
			if (null != param && StringUtils.isNotEmpty(param.getEndTime())) {
				stmt.setString(i++, param.getEndTime());
			}

			rs = stmt.executeQuery();
			while (rs.next()) {
				FactoryInvoice con = new FactoryInvoice();
				con.setFactoryId(rs.getString("Factory_id"));
				con.setCaseNo(rs.getString("Case_No"));
				con.setBargainNo(rs.getString("Bargain_No"));
				con.setPayMoeny(rs.getDouble("Get_Moeny"));
				con.setTotalAmount(rs.getDouble("total_mo"));
				con.setDateTime(rs.getString("Date_time"));
				con.setLabNo(rs.getString("Lab_No"));
				con.setRemarks(rs.getString("remarks"));
				con.setInvoiceName(rs.getString("Invoice_name"));
				list.add(con);
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
			SQLDBhelper.close(conn, stmt, rs);
		}
		return list;
	}




	@Override
	public List<FactoryReconciliation> getPaymentExceededApril(
			ItemCase2 itemcase) {
		String time=itemcase.getStartTime();
		List<FactoryReconciliation> list =new ArrayList<FactoryReconciliation>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select * from (select b.FactoryName,b.kingdee,a.*,isnull(z.rmb,0)rmb,isnull(b.friMoney,0)friMoney,isnull(c.friMoney1,0)friMoney1,"
				+ "isnull(d.amount_customs_declaration,0)amount_customs_declaration,it.CustomerManager ,"
				+ " it.MerchandManager1,it.MerchandManager2 , it.Merchandising ,it.MaturePurchase , "
				+ "it.OriginalPurchase from "
				+ "(select sum(fu.friMoney)friMoney,CaseNo,max(info.FactoryName)FactoryName,info.kingdee from factoryfund fu left join "
               		+ " factoryinfo info on fu.fid=info.id where info.kingdee=?  "
               		+ "and CaseNo   in ( select CaseNo from ( "
				+ "  select a.CaseNo,friFacDate from( "
				+ " select sum(friMoney)friMoney1,caseno,max(friFacDate)friFacDate from factoryfund a "
				+ " WHERE friMoney is not null group by caseno "
                + " )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
                + " and state='<font color=green>已完成款项</font>' group by caseno )b "
                + " on a.caseno=b.CaseNo where a.friMoney1=b.friMoney )a "
                + "   where Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095)";

               		if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
   					 sql+= " and  datediff(m,friFacDate,?)=0  and  datediff(yyyy,friFacDate,?)=0 ";
   					}
               		sql+= " group by CaseNo,kingdee )b "
				+ " left join "
				+ "(select max(a.Factory_id)Factory_id,Case_No,sum(a.Pay_Moeny)Pay_Moeny"
				+ ",sum(a.Get_Moeny)Get_Moeny,max(a.createtime)createtime,"
				+ " STUFF( ( SELECT    ',' + Bargain_No   FROM Tab_Factory_Money  x left join factoryinfo y "
				+ "on y.id=x.Factory_id  WHERE x.Case_No = a.Case_No and y.kingdee=info.kingdee  group by Bargain_No  FOR XML PATH('')) ,1,1,'') AS  bargain_no "
			    + " from ( select bargain_no,Case_No,max(Factory_id)Factory_id,sum(a.Pay_Moeny)Pay_Moeny"
				+ ",sum(a.Get_Moeny)Get_Moeny,max(a.createtime)createtime from Tab_Factory_Money a left join factoryinfo info on "
			    + "info.id=a.Factory_id where info.kingdee=? group by bargain_no,Case_No ) a left join factoryinfo info on info.id=a.Factory_id "
			    + " left join itemcase it on it.caseno=a.case_no"
				+ " where info.kingdee=?  and Case_No   in ( select CaseNo from ( "
				+ "  select a.CaseNo,friFacDate from( "
				+ " select sum(friMoney)friMoney1,caseno,max(friFacDate)friFacDate from factoryfund a "
				+ " WHERE friMoney is not null group by caseno "
                + " )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
                + " and state='<font color=green>已完成款项</font>' group by caseno )b "
                + " on a.caseno=b.CaseNo where a.friMoney1=b.friMoney )a "
                + "   where Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095)";
		if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				 sql+= " and  datediff(m,friFacDate,?)=0  and  datediff(yyyy,friFacDate,?)=0 ";
				}

               sql+= " group by kingdee,Case_No)a "
            		   + "	   on a.Case_No=b.CaseNo "
            		   + "	left join "
            		   + "(select * from (select sum(rmb)rmb,max(timeDate)timeDate,CaseNo from "
       		 		+ "(select a.*,info.kingdee,fund.CaseNo from (select purno,factory,"
       		 		+ " sum( CAST(rmb AS decimal(11,0)))rmb,max(CONVERT(datetime,saildate,20))timeDate "
       		 		+ " from contract con left join products p on p.id=con.proId "
       		 		+ " where (year(CONVERT(datetime,saildate,20))=year('"+time+"') and"
       		 		+ " month(CONVERT(datetime,saildate,20))>=month('"+time+"') )group by purno, factory)a "
       		 		+ " left join ( select min(fid)fid,BargainNo,CaseNo from factoryfund group by BargainNo,CaseNo) fund "
       		 		+ " on fund.BargainNo=a.purno left join FactoryInfo info on info.id=fund.fid )x "
       		 		+ " where x.kingdee=? group by kingdee,CaseNo)z)z on   z.caseno =b.caseno  "
               		+ " left join itemcase it on it.caseno=a.case_no"


               		+ " left join (select sum(fu.friMoney)friMoney1,CaseNo from factoryfund fu left join "
               		+ " factoryinfo info on fu.fid=info.id where info.kingdee=? and friMoney is not null "
               		+ "and CaseNo  in ( select CaseNo from ( "
				+ "  select a.CaseNo,friFacDate from( "
				+ " select sum(friMoney)friMoney1,caseno,max(friFacDate)friFacDate from factoryfund a "
				+ " WHERE friMoney is not null group by caseno "
                + " )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
                + " and state='<font color=green>已完成款项</font>' group by caseno )b "
                + " on a.caseno=b.CaseNo where a.friMoney1=b.friMoney )a "
                + "   where Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095)";
               		if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
      					 sql+= " and  datediff(m,friFacDate,?)=0  and  datediff(yyyy,friFacDate,?)=0 ";
      					}
               		sql+= " and state='<font color=green>已完成款项</font>' group by CaseNo)c on c.CaseNo=a.Case_No"
               				+ " left join (select sum(amount_customs_declaration)amount_customs_declaration,sb.caseno "
               				+ " from  shipment_bill_data sb left join  (select bargainNo,fid from  factoryfund group by bargainNo,fid) "
               				+ " fu on sb.bargainNo=fu.BargainNo "
               				+ " left join factoryinfo "
               				+ "  info on fu.fid=info.id where info.kingdee=? ";
						if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
							 sql+= " and  datediff(m,date,?)=0  and  datediff(yyyy,date,?)=0 ";
							}
						sql+= " group by caseno) d on d.caseno=a.Case_No"
						+ " )x "
               		+ " where 1=1 ";
               		if(itemcase.getCustomerManager()!=null&&!"".equalsIgnoreCase(itemcase.getCustomerManager())){
            			sql+=" and (x.CustomerManager like ?  or x.MerchandManager1 like ? or x.MerchandManager2 like ?  "
            			+ "or x.Merchandising like ?  or x.MaturePurchase like ?  or x.OriginalPurchase like ? ) ";
            		}


                   if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
    		      sql+= " and  datediff(m,Date_time,?)=0  and  datediff(yyyy,Date_time,?)=0 ";
    		        }
                   if(itemcase.getUnderInvoice()==0){

           		}else if(itemcase.getUnderInvoice()==-1){
           	        sql+=" and friMoney!=Get_Moeny+amount_customs_declaration+rmb" ;
           		}else if(itemcase.getUnderInvoice()==1){
           			 sql+=" and friMoney=Get_Moeny+amount_customs_declaration+rmb" ;
           		}else if(itemcase.getUnderInvoice()==2){
           			 sql+=" and friMoney=friMoney1 and friMoney!=Get_Moeny+amount_customs_declaration+rmb " ;
           		}



                   sql+=" order by friMoney-Get_Moeny-amount_customs_declaration-rmb desc ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			int i=0;
			stmt.setInt(1, itemcase.getKingdee());
			stmt.setInt(2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}
			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}
			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}
			i++;
			stmt.setInt(i+2, itemcase.getKingdee());
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2, itemcase.getDateSampleUploading());
				}
			if(itemcase.getCustomerManager()!=null&&!"".equalsIgnoreCase(itemcase.getCustomerManager())){
				for(int j=0;j<6;j++){
					i++;
					stmt.setString(i+2,"%"+itemcase.getCustomerManager()+"%");
				}
			}
			if(itemcase.getDateSampleUploading()!=null&&!"".equalsIgnoreCase(itemcase.getDateSampleUploading())){
				i++;
				stmt.setString(i+2,itemcase.getDateSampleUploading());
				i++;
				stmt.setString(i+2,itemcase.getDateSampleUploading());
  		        }
			rs = stmt.executeQuery();
			while(rs.next()) {
				FactoryReconciliation con=new FactoryReconciliation();
				con.setFactoryName(rs.getString("FactoryName"));
				con.setAmountCredit(rs.getDouble("Pay_Moeny"));
				con.setCurrentDebitAmount(rs.getDouble("Get_Moeny"));
				con.setKingdee(rs.getInt("kingdee"));
				con.setCreateTime(rs.getString("createtime"));
				con.setCaseNo(rs.getString("case_No"));
				con.setFactoryId(rs.getInt("Factory_id"));
				con.setBargainNo(rs.getString("bargain_No"));
				con.setContractAmount(rs.getDouble("friMoney"));
				con.setAmountPaid(rs.getDouble("friMoney1"));
				con.setFinalTimeReceiptAndReceipt(time);
				con.setShipmentBillAmount(rs.getDouble("rmb"));
				con.setAmountCustomsDeclaration(rs.getDouble("amount_customs_declaration"));
				con.setMerchandManager2(rs.getString("MerchandManager2"));
				con.setMerchandManager1(rs.getString("MerchandManager1"));
				list.add(con);
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
	public List<ArrivalAccountCorrespondenceTable> enterTheCustomerRelevanceTableIntoTheAccount(
			ArrivalAccountCorrespondenceTable it) {
		List<ArrivalAccountCorrespondenceTable> list =new ArrayList<ArrivalAccountCorrespondenceTable>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select name,projectId,customerId,kingdee,kingName,country,abbreviation from ArrivalAccountCorrespondenceTable where 1=1";
        if(it.getName()!=null&&!"".equalsIgnoreCase(it.getName())){
        	sql+=" and (name like ? or abbreviation like ? or kingName like ?)";
        }
		if(it.getCustomerId()!=0){
			sql+=" and customerId=?";
		}
		if(it.getProjectId()!=null&&!"".equalsIgnoreCase(it.getProjectId())){
			sql+=" and projectId like ?";
		}
        conn = SQLDBhelper.getConnection();
        try {
        	int i=0;
			stmt = conn.prepareStatement(sql);
			if(it.getName()!=null&&!"".equalsIgnoreCase(it.getName())){
				i++;
				stmt.setString(i, "%"+it.getName()+"%");
				i++;
				stmt.setString(i, "%"+it.getName()+"%");
				i++;
				stmt.setString(i, "%"+it.getName()+"%");

	        }
			if(it.getCustomerId()!=0){
				i++;
				stmt.setInt(i, it.getCustomerId());
			}
			if(it.getProjectId()!=null&&!"".equalsIgnoreCase(it.getProjectId())){
				i++;
				stmt.setString(i, it.getProjectId());
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				ArrivalAccountCorrespondenceTable con=new ArrivalAccountCorrespondenceTable();
				con.setProjectId(rs.getString("projectId"));
				con.setCustomerId(rs.getInt("customerId"));
				con.setName(rs.getString("name"));
				con.setKingdee(rs.getInt("kingdee"));
				con.setKingName(rs.getString("kingName"));
				con.setCountry(rs.getInt("country"));
				con.setAbbreviation(rs.getString("abbreviation"));
				list.add(con);
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
	public List<ItemCase2> invoiceDocumentarySaleOwnedToUs1(ItemCase2 it) {
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 String sql = " select * from ( select x.*,tab.Factory_yue_Money from ( select min(FactoryName)FactoryName,kingdee,MerchandManager1 ,Convert(decimal(18,2),sum(money1))money1,sum(money2)money2,sum(money3)money3"
		 		+ "  from(  select a.FactoryName,a.money3,a.kingdee,b.money2,c.money1,a.MerchandManager1 from ( "
		 +"  select kingdee,min(FactoryName)FactoryName,sum(money3)money3,MerchandManager1 from ( "
		 +" select FactoryName,(Pay_Moeny-Get_Moeny)money3,kingdee,MerchandManager1 from ( "
		 +"  select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName,MerchandManager1 from ( "
		 +"  select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager1 IS NULL OR it.MerchandManager1='')"
		 + " then it.Merchandising else MerchandManager1 "
		 +"         end) as MerchandManager1 "
		 +"   from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id left join itemcase it "
		 + "on it.CaseNo=a.Case_No "
		 +" where  Case_No  in ( select a.CaseNo from( "
		 +" select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
         +" )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null and"
         + " state='<font color=green>已完成款项</font>' group by caseno )b "
         +"  on a.caseno=b.CaseNo where a.friMoney1=b.friMoney) ";

         if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
        	sql+=" and(it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =? "
        			+ "or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?)";
         }

         sql+=" )y group by kingdee,MerchandManager1)x "
		 +"  )s group by kingdee,MerchandManager1)a "
		 + "left join ("
		 + "   select kingdee,min(FactoryName)FactoryName,sum(money3)money2,MerchandManager1 from ( "
		 + "  select FactoryName,(Pay_Moeny-Get_Moeny)money3,kingdee,MerchandManager1 from ( "
		 + "  select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName,MerchandManager1 from ( "
		 + "  select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager1 IS NULL OR it.MerchandManager1='') "
		 + " then it.Merchandising else MerchandManager1 "
		 + "         end) as MerchandManager1 "
		 + "   from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id left join itemcase it "
		 + "on it.CaseNo=a.Case_No "
		 + " where  Case_No  in ( select a.CaseNo from( "
		 + " select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
         + " )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null and "
         + " state='<font color=green>已完成款项</font>' group by caseno )b "
         + " on a.caseno=b.CaseNo where a.friMoney1=b.friMoney)";
         if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
         	sql+=" and(it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =? "
         			+ "or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?)";
          }
         sql+= "  and  Case_No not in (select case_no from Tab_Factory_Money where "
         + "datediff(day,createtime,getdate())<119 and Pay_Moeny!=0) "
         + " )y  group by kingdee,MerchandManager1)x "
		 + " )s group by kingdee,MerchandManager1"
		 +"		   )b on a.kingdee=b.kingdee  and a.MerchandManager1=b.MerchandManager1 "
		 + "left join ( "
		 + " select min(FactoryName)FactoryName,kingdee,sum(money1)money1,MerchandManager1 from ( "
		 + " 		   select FactoryName, "
		 + " (Pay_Moeny-Get_Moeny)money1,kingdee,MerchandManager1 from(  "
         + " select min(FactoryName)FactoryName,sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,MerchandManager1 from (  "
         + " select a.*,info.FactoryName,info.kingdee,(CASE when (it.MerchandManager1 IS NULL OR it.MerchandManager1='') then "
		 + "  it.Merchandising else MerchandManager1 end) as MerchandManager1 "
		 + "  from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id "
		 + "left join itemcase it on it.caseno=a.case_no where 1=1 ";
		 if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
	         	sql+=" and(it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =? "
	         			+ "or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?)";
	          }
		 sql+= " )f group by kingdee,MerchandManager1)v)z group by kingdee,MerchandManager1  )c "
         + " on a.kingdee=c.kingdee and a.MerchandManager1=c.MerchandManager1  "
         + " )x group by MerchandManager1,kingdee) x left join Tab_Factory_yue tab on x.kingdee=tab.Factory_yue_kingdeId where kingdee not in ("
         + " select kingdee from ( "
         + " select (Pay_Moeny-Get_Moeny)money1,isnull(Factory_yue_Money,0)Factory_yue_Money,kingdee from ( "
         + " select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(Factory_yue_Money)"
         + " Factory_yue_Money from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id "
         + " left join Tab_Factory_yue tab on info.kingdee=tab.Factory_yue_kingdeId "
         + " group by kingdee)a)x where money1=-Factory_yue_Money) and x.money1!=0  and kingdee!=0)a where 1=1 ";
		 if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
			  sql+="and FactoryName like ? ";
		  }
		  if(it.getKingdee()!=0){
			  sql+="and kingdee = ? ";
		  }
		  if(it.getMerchandManager1()!=null&&!"".equalsIgnoreCase(it.getMerchandManager1())){
			  sql+="and MerchandManager1 like ? ";
		  }
        conn = SQLDBhelper.getConnection();
        try {
        	sql+=" order by a.MerchandManager1 desc,a.money3 desc";
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
				for(int j=0;j<18;j++){
				  i++;
				  stmt.setString(i, it.getCustomerManager());
				}
			  }
			if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
				  i++;
				  stmt.setString(i, "%"+it.getFactoryName()+"%");
			  }
			  if(it.getKingdee()!=0){
				  i++;
				  stmt.setInt(i,it.getKingdee() );
			  }
			  if(it.getMerchandManager1()!=null&&!"".equalsIgnoreCase(it.getMerchandManager1())){
				  i++;
				  stmt.setString(i, "%"+it.getMerchandManager1()+"%");
			  }
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase2 info=new ItemCase2();
				int kingdee=rs.getInt("kingdee");
				if(kingdee!=1208){
				info.setMerchandManager1(rs.getString("merchandManager1"));
				info.setAllUnacceptableInvoiceAmounts(rs.getDouble("money1"));
				info.setAmountOfNonReceiptAndReceiptOfInvoices(rs.getDouble("money3"));
				info.setAmountOfUnpaidInvoices(rs.getDouble("money2"));
				info.setKingdee(rs.getInt("kingdee"));
				info.setFactoryName(rs.getString("FactoryName"));
				info.setPayment2007(rs.getDouble("Factory_yue_Money"));
				info.setCustomerManager(it.getCustomerManager());
				list.add(info);
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

	@Override
	public List<ItemCase2> invoiceFactoryOwnedToUs1(ItemCase2 it) {
		List<ItemCase2> list =new ArrayList<ItemCase2>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = " select * from ( "
				 +" select FactoryName,kingdee,Convert(decimal(18,2),sum(money1))money1,sum(money2)money2,sum(money3)money3 "
				 +",isnull(min(Factory_yue_Money),0)Factory_yue_Money from ( select a.FactoryName,a.money3,a.kingdee,b.money2,c.money1 from ( "
		   +" select kingdee,min(FactoryName)FactoryName,sum(money3)money3 from ( "
		  +" select FactoryName,(Pay_Moeny-Get_Moeny)money3,kingdee from ( "
		  +"  select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee,min(FactoryName)FactoryName from ( "
		   +" select a.*,info.FactoryName,info.kingdee "
		   +"  from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id "
			+" left join itemcase it on a.Case_No=it.CaseNo "
		 +" where  Case_No  in ( select a.CaseNo from( "
		 +" select sum(friMoney)friMoney1,caseno from factoryfund a WHERE friMoney is not null group by caseno "
         +" )a left join (select sum(friMoney)friMoney,caseno from factoryfund where friMoney is not null "
        +" and state='<font color=green>已完成款项</font>' group by caseno )b "
        +"  on a.caseno=b.CaseNo where a.friMoney1=b.friMoney)  "
		+" and  (it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =?  "
         +"			or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?) "
		+"  )y group by kingdee)x "
		+"  )s group by kingdee)a "
		+" left join (  "
		+"  select kingdee,min(FactoryName)FactoryName,sum(Pay_Moeny-Get_Moeny)money2 "
		+" from(select max(a.Factory_id)Factory_id,Case_No,sum(a.Pay_Moeny)Pay_Moeny "
		+"  ,sum(a.Get_Moeny)Get_Moeny,max(Date_time)Date_time,max(info.FactoryName)FactoryName,info.kingdee, "
		+"  STUFF( ( SELECT    ','  Bargain_No   FROM Tab_Factory_Money  WHERE Case_No = a.Case_No "
		+"  and  kingdee=info.kingdee  group by Bargain_No  FOR XML PATH('')) ,1,1,'') AS  bargain_no "
		+"  from Tab_Factory_Money a left join factoryinfo info on info.id=a.Factory_id "
		+"  left join itemcase it on a.Case_No=it.CaseNo "
		+"  where  Case_No   in ( select CaseNo from ( "
		+"  select max(friFacDate)friFacDate,CaseNo from factoryfund where friMoney is not null "
		+"  and state='<font color=green>已完成款项</font>' group by caseno)a "
		+"  where Datediff(day,friFacDate,getdate())>119 and Datediff(day,friFacDate,getdate())<1095) "
        +" and  (it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =?  "
        +" 			or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?) "
        +" and kingdee is not null   group by kingdee,Case_No)a  group by kingdee "
		+" )b on a.kingdee=b.kingdee  "
		+" left join ( "
		+"  select min(FactoryName)FactoryName,kingdee,sum(money1)money1 from ( "
		+" 		   select FactoryName, "
		+"  (Pay_Moeny-Get_Moeny)money1,kingdee from(  "
        +"  select min(FactoryName)FactoryName,sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny,kingdee from (  "
        +"  select a.*,info.FactoryName,info.kingdee from Tab_Factory_Money a left join factoryinfo info  "
        +"  on info.id=a.Factory_id "
		+"  left join itemcase it on a.Case_No=it.CaseNo "
		+"  where  (it.CustomerManager =? or it.MerchandManager1 =? or it.MerchandManager2 =?  "
        +" 			or it.Merchandising =? or it.MaturePurchase =? or it.OriginalPurchase =?) "
		+"  )f group by kingdee)v)z group by kingdee  )c "
        +" on a.kingdee=c.kingdee "
        +" )a left join Tab_Factory_yue yue on a.kingdee=yue.Factory_yue_kingdeId group by FactoryName,kingdee "
        +" )a where a.money1!=-a.Factory_yue_Money and kingdee!=0  ";
		 if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
			  sql+="and FactoryName like ? ";
		  }
		  if(it.getKingdee()!=0){
			  sql+="and kingdee = ? ";
		  }
        conn = SQLDBhelper.getConnection();
        try {
        	sql+=" order by money3 desc";
			stmt = conn.prepareStatement(sql);
			int i=0;
			if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
				for(int j=0;j<18;j++){
				  i++;
				  stmt.setString(i, it.getCustomerManager());
				}
			  }
			if(it.getFactoryName()!=null&&!"".equalsIgnoreCase(it.getFactoryName())){
				  i++;
				  stmt.setString(i, "%"+it.getFactoryName()+"%");
			  }
			  if(it.getKingdee()!=0){
				  i++;
				  stmt.setInt(i,it.getKingdee() );
			  }
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase2 info=new ItemCase2();
				int kingdee=rs.getInt("kingdee");
				if(kingdee!=1208){
				double money1=rs.getDouble("money1");
				double Factory_yue_Money=rs.getDouble("Factory_yue_Money");
				if(money1+Factory_yue_Money!=0){
				info.setFactoryName(rs.getString("FactoryName"));
				info.setAllUnacceptableInvoiceAmounts(rs.getDouble("money1"));
				info.setAmountOfNonReceiptAndReceiptOfInvoices(rs.getDouble("money3"));
				info.setAmountOfUnpaidInvoices(rs.getDouble("money2"));
				info.setKingdee(rs.getInt("kingdee"));
				info.setPayment2007(rs.getDouble("Factory_yue_Money"));
				info.setCustomerManager(it.getCustomerManager());
				list.add(info);
				}
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

	@Override
	public TechnologyTable getAll(String process1) {
		TechnologyTable table=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		String result = null;
		ResultSet rs = null;
		String str;
        String sql = "select * from technology_table    where technology_name=? ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, process1);
			rs = stmt.executeQuery();
			if(rs.next()) {
				table=new TechnologyTable();
				table.setProcessAttributes(rs.getInt("process_attributes"));
				table.setTechnologyName(rs.getString("technology_name"));
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



        return table;
	}

	@Override
	public void updateProjectMaterialProperties(int processAttributes, String projectNo) {
		String sql = "update itemCase set ProjectMaterialProperties=? where CaseNo = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,processAttributes );
			stmt.setString(2, projectNo);
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
	public ItemCase getStartTime() {
		ItemCase it=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from shipping_bill_month    ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				it=new ItemCase();
				it.setStartTime(rs.getString("waybill_month"));

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
		return it;
	}


	@Override
	public int updateFastTrackReasons(int id, String fastTrackReasons) {
		String sql = "update MoneyCheckUp set express_lane=1,fast_track_reasons=? where id = ?";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,fastTrackReasons );
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
	public int getSalesContract(String caseNo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(1) from revenue_contract where caseno=?    ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,caseNo );
			rs = stmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1)	;
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
		return result;
	}

	@Override
	public int getFactoryName(String factoryName) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select kingdee from factoryinfo where FactoryName=?    ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,factoryName );
			rs = stmt.executeQuery();
			if(rs.next()) {
				result= rs.getInt("kingdee");
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
		return result;
	}



	@Override
	public void updateDividendBalance(List<Bonusdata> list) {
		for(int i=0;i<list.size();i++){
			if(!(list.get(i).getTotalAmountOfArrival()/list.get(i).getTotalAmountReceivable()>=0.95 && list.get(i).getIsInvoice()==1)){
				continue;
			}else{

				String sql = "update itemcase set dividend_balance=1 where caseno = ?";
				Connection conn = null;
				ResultSet rs = null;
				PreparedStatement stmt = null;
				int result = 0;
				conn = SQLDBhelper.getConnection();
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,list.get(i).getProjectId() );

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

	@Override
	public List<ItemCase1> getOrderCustomerList(EmailUser user) {
		List<ItemCase1> list=new ArrayList<ItemCase1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0 " +
				" and datediff(day,'"+user.getStartTime()+"',ifdate)>0 and datediff(day,'"+user.getEndTime()+"',ifdate)<0  and (CustomerManager=? or Merchandising=? or MerchandManager1=?) " +
				" and customercode not in (select id from customer where customer_loss=1)"+
				" group by icaseno,customercode ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getUserName() );
			stmt.setString(2,user.getUserName() );
			stmt.setString(3,user.getUserName() );
			rs = stmt.executeQuery();
            while(rs.next()) {
				ItemCase1 it=new ItemCase1();
                it.setOrderId(rs.getString("icaseno"));
                it.setCustomerId(rs.getInt("customercode"));
                list.add(it);
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
	public List<ItemCase1> getNoOrderCustomerList(EmailUser user) {
		List<ItemCase1> list=new ArrayList<ItemCase1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from( " +
				" select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0 " +
				" and datediff(day,'"+user.getStartTime()+"',ifdate)>0 and datediff(day,'"+user.getEndTime()+"',ifdate)<0    and (CustomerManager=? or Merchandising=? or MerchandManager1=?) " +
				" and customercode not in (select id from customer where customer_loss=1)"+
				" group by icaseno,customercode " +
				" )a where customercode not in ( " +
				" select customercode from ( " +
				" select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0 " +
				" and datediff(day,'"+user.getEndTime()+"',ifdate)>0   and (CustomerManager=? or Merchandising=? or MerchandManager1=?) " +
				" and customercode not in (select id from customer where customer_loss=1)"+
				" group by icaseno,customercode)a " +
				" ) ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getUserName() );
			stmt.setString(2,user.getUserName() );
			stmt.setString(3,user.getUserName() );
			stmt.setString(4,user.getUserName() );
			stmt.setString(5,user.getUserName() );
			stmt.setString(6,user.getUserName() );
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase1 it=new ItemCase1();
				it.setOrderId(rs.getString("icaseno"));
				it.setCustomerId(rs.getInt("customercode"));
				list.add(it);
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
	public List<ItemCase1> getallCustomerList(EmailUser user) {
		List<ItemCase1> list=new ArrayList<ItemCase1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno left join customer cus on cus.id=it.customercode   where ifmoney is not null and ifmoney!=0 " +
				" and (CustomerManager=? or Merchandising=? or MerchandManager1=?) " +
				"    and cus.customergrade  in(1,2) and datediff(day,'"+user.getStartTime1()+"',ifdate)>0 " +
				"group by icaseno,customercode; ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getUserName() );
			stmt.setString(2,user.getUserName() );
			stmt.setString(3,user.getUserName() );

			rs = stmt.executeQuery();
            while(rs.next()) {
				ItemCase1 it=new ItemCase1();
				it.setOrderId(rs.getString("icaseno"));
				it.setCustomerId(rs.getInt("customercode"));
				list.add(it);
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
	public List<ItemCase1> getnewCustomerList(EmailUser user) {
		List<ItemCase1> list=new ArrayList<ItemCase1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from(select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno left join customer cus on cus.id=it.customercode   where ifmoney is not null and ifmoney!=0\n" +
				" and datediff(day,'"+user.getStartTimea()+"',ifdate)>0 and datediff(day,'"+user.getEndTimea()+"',ifdate)<0  and  (CustomerManager=? or Merchandising=? or MerchandManager1=?)\n" +
				" and cus.customergrade  in(1,2) )a " +
				" where  customercode not in ( " +
				" " +
				"select it.customercode from invoiceinfo inv left join itemcase it on inv.icaseno=it.caseno where datediff(day,'"+user.getStartTimea()+"',ifdate)<0 and customercode is not null\n" +
				" " +
				" ) " +
				"group by icaseno,customercode";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getUserName() );
			stmt.setString(2,user.getUserName() );
			stmt.setString(3,user.getUserName() );

			rs = stmt.executeQuery();
            while(rs.next()) {
				ItemCase1 it=new ItemCase1();
				it.setOrderId(rs.getString("icaseno"));
				it.setCustomerId(rs.getInt("customercode"));
				list.add(it);
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
	public List<ItemCase1> getfixedTimeCustomersList(EmailUser user) {
		List<ItemCase1> list=new ArrayList<ItemCase1>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno left join customer cus on cus.id=it.customercode   where ifmoney is not null and ifmoney!=0 " +
				"    and datediff(day,'"+user.getStartTimea()+"',ifdate)>0 and datediff(day,'"+user.getEndTimea()+"',ifdate)<0  and (CustomerManager=? or Merchandising=? or MerchandManager1=?) " +
				"     and cus.customergrade  in(1,2)" +
				"group by icaseno,customercode; ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,user.getUserName() );
			stmt.setString(2,user.getUserName() );
			stmt.setString(3,user.getUserName() );

			rs = stmt.executeQuery();
            while(rs.next()) {
				ItemCase1 it=new ItemCase1();
				it.setOrderId(rs.getString("icaseno"));
				it.setCustomerId(rs.getInt("customercode"));

				list.add(it);
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
    public List<ItemCase> getAllProjectNo(ItemCase it) {
		List<ItemCase> list=new ArrayList<ItemCase>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select pro.project_no,pro.project_status,pir.create_date,ifnull(pt.verification,0)verification,ifnull(pt.complaint_id,0)complaint_id from project pro " +
				" left join (select min(complaint_id)complaint_id,min(verification)verification,project_no from project_task where complaint_id !=0 group by project_no)pt " +
				" on pro.project_no=pt.project_no" +
				" left join( select project_no,max(create_date)create_date from project_inspection_report where email_content is not null group by project_no) pir  " +
				" on pro.project_no=pir.project_no  where 1=1    " +
				" and ((  pro.project_status = 2 " +
				"  and datediff(pro.finish_time,?) >0  and  pro.finish_time <?    " +
				"  ) or (pro.project_status = 6 and " +
				"     datediff(pro.sample_finish_time,?) >0  and pro.sample_finish_time  <?        " +
				"  )or(pro.project_status = 1  and pro.create_date<?)"
				+" or (pro.project_status = 0  and pro.create_date<?))"
				+"   ";
		conn = DBHelper.getConnection();
		try {
			if(it.getCaseNo()!=null&&!"".equalsIgnoreCase(it.getCaseNo())){
				sql+=" and pro.project_no like ?";
			}
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,it.getStartTime() );
			stmt.setString(2,it.getEndTime() );
			stmt.setString(3,it.getStartTime() );
			stmt.setString(4,it.getEndTime() );
			stmt.setString(5,it.getEndTime() );
			stmt.setString(6,it.getEndTime() );
			int i=0;
			if(it.getCaseNo()!=null&&!"".equalsIgnoreCase(it.getCaseNo())){
				i++;
				stmt.setString(i+6,"%"+it.getCaseNo()+"%" );
			}

			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemCase it1=new ItemCase();
				it1.setCaseNo(rs.getString("project_no"));
				it1.setProject_status(rs.getInt("project_status"));
				it1.setCreateTime(rs.getString("create_date"));
				it1.setComplaint_id(rs.getInt("complaint_id"));
				it1.setVerification(rs.getInt("verification"));
				list.add(it1);
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
			DBHelper.returnConnection(conn);
		}
		return list;
    }

	@Override
	public ItemCase getProjectStatistics(ItemCase it) {
		ItemCase itcase=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select * from(select isNull(PObiao,null)PObiao,isnull(DateSample,null)DateSample,isnull(completiontime,null)completiontime,isnull(pdId,null)pdId,isnull(DrawingName1,null)DrawingName1 "
				+ " ,isnull(intro,null)intro,isnull(uploader,null)uploader,  "
				+ "  isnull(uploadtime,null)uploadtime,isnull(intro1,null)intro1,isnull(intro2,null)intro2,isnull(uploader2,null)uploader2,isnull(uploadtime2,null)uploadtime2, "
				+ "  isnull(zhongwen,null)zhongwen,isnull(up_file_name, NULL) upFileName,isnull(zhongwen1,null)zhongwen1,isnull(yingwen,null)yingwen,isnull(BargainNo,null)BargainNo,isnull(EmployeeName1,null)EmployeeName1,isnull(EmployeeName2,null)EmployeeName2,isnull(EmployeeName3,null)EmployeeName3,isnull(UpdateTime1,null)UpdateTime1,"
				+ " isnull(UpdateTime2,null)UpdateTime2,isnull(UpdateTime3,null)UpdateTime3,isnull(ProjectLevel,null)ProjectLevel,isnull(potime,null)potime,caseno,isnull(ProductDescC,null)ProductDescC,isnull(ProductDescE,null)ProductDescE, "
				+ " isnull(CustomerManager,null)CustomerManager,isnull(MerchandManager1,null)MerchandManager1,isnull(MerchandManager2,null)MerchandManager2,Merchandising,OriginalPurchase,MaturePurchase,isnull(ProductState,null)ProductState,isnull(QIC,null)QIC,remarks,feedbacktime,quality_picture "
				+ " from( "
				+ "select po.PObiao,m.DateSample,m.completiontime,isnull(x.id,0)pdId,y.DrawingName1,c.intro,c.uploader,convert(varchar(10),c.uploadtime,120)uploadtime,c.intro1,"
				+ " c.intro2,c.uploader2,convert(varchar(10),c.uploadtime2,120)uploadtime2,n.zhongwen,p.up_file_name,n.yingwen,yy.zhongwen1,m.BargainNo,qp.EmployeeName1,qp.EmployeeName2,qp.EmployeeName3"
				+ ",convert(varchar(10),qp.UpdateTime1,120)UpdateTime1,convert(varchar(10),qp.UpdateTime2,120)UpdateTime2,convert(varchar(10),qp.UpdateTime3,120)UpdateTime3, "
				+ " it.ProjectLevel,convert(nvarchar(10),it.potime,120)potime,a.caseno,it.ProductDescC,it.ProductDescE,it.CustomerManager,it.MerchandManager1,it.MerchandManager2,it.Merchandising,it.OriginalPurchase,it.MaturePurchase,it.ProductState,it.QIC,it.remarks,isnull(z.create_time,'')feedbacktime ,isnull(quality_picture,'')quality_picture from ("
				+ " select caseno from ( select caseno from itemcase)m where caseno=?   )a "
				+ "left join itemcase it on a.CaseNo=it.CaseNo left join QuotationAnalysis qa on qa.caseno=a.caseno "
				+ " left join "
				+ " ( select qp.CaseNo,a.EmployeeName1,a.UpdateTime1,qp1.EmployeeName2,qp1.UpdateTime2,qp2.EmployeeName3,qp2.UpdateTime3 from QuotePrice qp left join  ( select CaseNo,min(EmployeeName)EmployeeName1,min(UpdateTime)UpdateTime1 from QuotePrice where CurrentStatus like '%项目启动会%' group by CaseNo "
				+ "  )a on a.CaseNo=qp.caseno left join (select CaseNo,min(EmployeeName)EmployeeName2,min(UpdateTime)UpdateTime2 from QuotePrice where CurrentStatus like '%样品分析会%' or CurrentStatus like '%样品验货会%' group by CaseNo )  qp1 on qp1.caseno=qp.CaseNo "
				+ " left join (select CaseNo,min(EmployeeName)EmployeeName3,min(UpdateTime)UpdateTime3 from QuotePrice where CurrentStatus like '%大货分析会%' or CurrentStatus like '%大货验货会%' group by CaseNo )  qp2 on qp2.caseno=qp.CaseNo "
				+ " )  qp on qp.caseno=a.CaseNo left join ( select a.caseno,a.zhongwen,a.yingwen from ( select caseno,min(tz.zhongwen)zhongwen,min(yingwen)yingwen from tuzhi tz where remark like '%受控版本%'  group by caseno "
				+ " )a  "
				+ " )n on n.caseno=a.CaseNo LEFT JOIN (SELECT b.caseno,b.up_file_name FROM (SELECT top 1 ptz.caseno,ptz.up_file_name FROM part_tuzhi ptz where ptz.caseno=? order by upload_date desc) b ) p ON p.caseno = a.CaseNo"
				+ "  left join ( select xx.caseno,isnull(xx.zhongwen,xx.task_system_technical_documentation)zhongwen1 from ( select caseno,min(tz.zhongwen)zhongwen,min(tz.task_system_technical_documentation)task_system_technical_documentation from tuzhi tz where attribute =1  group by caseno "
				+ " )xx  "
				+ " )yy on yy.caseno=a.CaseNo"
				+ " left join (select po.CaseNo,isnull(a.intro,null)intro1,a.uploader,a.uploadtime,e.intro2,e.uploader2,e.uploadtime2,f.intro from poupload po left join ( "
				+ " select caseno,min(intro)intro,min(uploader)uploader,min(uploadtime)uploadtime from poupload where intro like  '%JIANYANJIHUAZJ%' group by caseno "
				+ ")a  on a.caseno=po.CaseNo   left join (select caseno,min(intro)intro2,min(uploader)uploader2,min(uploadtime)uploadtime2 from poupload where intro like  '%采购B版%' group by caseno)e on e.CaseNo=po.CaseNo "
				+ " left join (select caseno,min(intro)intro from poupload where type like  '%JIANYANBAOGAO%' group by caseno)f on f.CaseNo=po.CaseNo)c on c.CaseNo=a.CaseNo "
				+ " left join (select a.CaseNo,a.BargainNo,b.DateSample,c.completiontime from( "
				+ " select min(BargainNo)BargainNo,CaseNo from  bargain group by CaseNo "
				+ " )a left join (select min(DateSample)DateSample,caseno from bargain where DateSample>'2017-01-01' group by caseno )b on a.CaseNo=b.CaseNo "
				+ " left join (select min(completiontime)completiontime,caseno from bargain where completiontime>'2017-01-01' group by caseno )c on a.CaseNo=c.CaseNo "
				+ " )m on m.caseno=a.CaseNo "
				+ "  left join  (select min(id)id,caseno from (select id,caseno from ProjectDrawings where remark like '%生产计划%')a group by caseno)x on x.caseno= a.CaseNo "
				+ " left join (select max(DrawingName1)DrawingName1 ,caseno from( select isnull(DrawingName,'')DrawingName1,caseno from ProjectDrawings where  remark not like '%生产计划%' and remark not like '%第三方质检报告%' and DrawingName like '%项目需求汇总%')a group by caseno )"
				+ " y on a.caseno=y.caseno "
				+ " left join  po on po.caseno=a.caseno left join (select max(create_time)create_time,max(quality_picture)quality_picture,caseno from quality_feedback_table group by caseno)z on a.caseno=z.caseno where 1=1" ;
		if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
			sql+=" and (CustomerManager=?  or MerchandManager1=?  or MerchandManager2=? or Merchandising =? or MaturePurchase=? or OriginalPurchase=?";
			sql+=	   " or zhijian1=? or zhijian2=? )";
		}
		        sql+=")cc  " ;


				sql+=" group by PObiao,DateSample,completiontime,pdId,"
				+ "DrawingName1,intro,uploader,uploadtime,intro1,intro2,uploader2,uploadtime2,zhongwen,up_file_name,yingwen,BargainNo"
				+ ",EmployeeName1,EmployeeName2,EmployeeName3,UpdateTime1,UpdateTime2,UpdateTime3,ProjectLevel,potime,caseno,"
				+ "ProductDescC,ProductDescE,CustomerManager,MerchandManager1,MerchandManager2,Merchandising,OriginalPurchase,MaturePurchase,ProductState,QIC,zhongwen1,remarks,feedbacktime,quality_picture)x where 1=1  ";

		if(it.getDrawingPicture()==1){
			sql+=" and  zhongwen is null ";
		}else if(it.getDrawingPicture()==2){
			sql+=" and zhongwen  is not null ";
		}

		sql+= "  order by caseno";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
//			int i=0;
			stmt.setString(1,it.getCaseNo() );
            stmt.setString(2,it.getCaseNo() );
			if(it.getCustomerManager()!=null&&!"".equalsIgnoreCase(it.getCustomerManager())){
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );
//				i++;
//				stmt.setString(1+i,it.getCustomerManager() );

                stmt.setString(3,it.getCustomerManager() );
                stmt.setString(4,it.getCustomerManager() );
                stmt.setString(5,it.getCustomerManager() );
                stmt.setString(6,it.getCustomerManager() );
                stmt.setString(7,it.getCustomerManager() );
                stmt.setString(8,it.getCustomerManager() );
                stmt.setString(9,it.getCustomerManager() );
                stmt.setString(10,it.getCustomerManager() );

			}
			rs = stmt.executeQuery();
			if(rs.next()) {
				itcase=new ItemCase();
				String caseno=rs.getString("caseNo");
				double money=0.00;
				int type=0;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				Connection conn1 = null;
				String sql1 = " SELECT icaseno,sum(iimoney)iimoney,min(imoneytype)imoneytype  FROM invoiceinfo  WHERE iCaseNo = ? group by icaseno";

				conn1 = SQLDBhelper.getConnection();

				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, caseno);
					rs1 = stmt1.executeQuery();
					if(rs1.next()) {
						money=rs1.getDouble("iimoney");
						type=rs1.getInt("imoneytype");
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
				String UpdateTime1="";
				String meeting_inputer="";
				PreparedStatement stmt2 = null;
				ResultSet rs2= null;
				Connection conn2 = null;
				String sql2 = " SELECT meeting_name,min(meeting_inputer)meeting_inputer  FROM meeting_record  WHERE project_no = ? and meeting_name='项目启动会' group by meeting_name ";
				conn2= DBHelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, caseno);
					rs2 = stmt2.executeQuery();
					if(rs2.next()) {
						UpdateTime1=rs2.getString("meeting_name");
						meeting_inputer=rs2.getString("meeting_inputer");
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
					DBHelper.returnConnection(conn2);

				}
				itcase.setIntro(rs.getString("intro"));
				String introA=rs.getString("intro");
				String UpdateTime3= rs.getString("UpdateTime3");
				if(UpdateTime3!=null&&!"".equalsIgnoreCase(UpdateTime3)){
					itcase.setQpId2(rs.getString("EmployeeName3") +UpdateTime3);
				}else{
					if(introA!=null&&!"".equalsIgnoreCase(introA)){
						Boolean save=introA.toLowerCase().contains("没问题");
						Boolean judgePrice=judgePrice(type,money);
						if(save!=true&&judgePrice!=false){
							itcase.setQpId2("0");//有问题，大于5000美元，未开
						}else if(save!=false&&judgePrice!=false){
							itcase.setQpId2("1");//大于5000美元，未开
						}else if(save!=true&&judgePrice!=true){
							itcase.setQpId2("2");//有问题，未开
						}else{
							itcase.setQpId2("3");
						}

					}else {
						Boolean judgePrice=judgePrice(type,money);
						if(judgePrice!=false){
							itcase.setQpId2("1");
						}else {
							itcase.setQpId2("3");
						}

					}
				}


				itcase.setMerchandising(rs.getString("Merchandising"));
				itcase.setCaseNo(rs.getString("caseNo"));
				itcase.setProjectDescc(rs.getString("ProductDescC"));
				itcase.setProjectDesce(rs.getString("ProductDescE"));
				itcase.setCustomerManager(rs.getString("customerManager"));
				itcase.setMerchandManager1(rs.getString("merchandManager1"));
				itcase.setMerchandManager2(rs.getString("merchandManager2"));
				itcase.setEngineer1(rs.getString("OriginalPurchase"));
				itcase.setEngineer2(rs.getString("MaturePurchase"));
				itcase.setPo(rs.getString("PObiao"));
				itcase.setRemarks(rs.getString("remarks"));
                itcase.setPartTuzhi(rs.getString("upFileName"));
				itcase.setProjectLevel(rs.getInt("projectLevel"));
				itcase.setPotime(rs.getString("potime"));
				itcase.setPdId1(rs.getString("DrawingName1"));
				itcase.setFeedbacktime(rs.getString("feedbacktime"));
				itcase.setQuality_picture(rs.getString("quality_picture"));
				String yingwen=rs.getString("yingwen");
				String zhongwen=rs.getString("zhongwen");
				if(zhongwen!=null&&!"".equalsIgnoreCase(zhongwen)){
					itcase.setRemark(zhongwen);
				}else if(yingwen!=null&&!"".equalsIgnoreCase(yingwen)){
					itcase.setRemark(yingwen);
				}else{
					itcase.setRemark(null);
				}

				//20201016 update start
//				if(it.getProjectLevel()==1||it.getProjectLevel()==2||it.getProjectLevel()==0) {
//					if(it.getCreateTime()!=null&&!"".equalsIgnoreCase(it.getCreateTime())) {
//						String []time=it.getCreateTime().split(" ");
//						itcase.setPoId("阳工已上传，上传时间:"+time[0]);
//					}else{
//						itcase.setPoId("未上传");
//					}
//				}else{
//					itcase.setPoId("C级项目无需上传");
//				}

				PreparedStatement stmt3= null;
				ResultSet rs3= null;
				Connection conn3 = null;
				String sql3 = " select count(1) cn from POupload where caseno=? and (intro like '%采购A版%' or intro like '%采购B版%')";
				conn3= SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setString(1, caseno);
					rs3 = stmt3.executeQuery();
					if(rs3.next()) {
						int count3 =rs3.getInt("cn");
						if(count3>0){
							itcase.setPoId("已上传");
						}else{
							itcase.setPoId("未上传");
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
					DBHelper.returnConnection(conn3);

				}

				//20201016 update end

				String intro2=rs.getString("intro2");
				if(intro2!=null&&!"".equalsIgnoreCase(intro2)){
					itcase.setPoId2(intro2+"["+rs.getString("uploader2")+"/"+rs.getString("uploadtime2")+"]");
				}else{
					itcase.setPoId2(null);
				}


				//String UpdateTime1= rs.getString("UpdateTime1");
				if(UpdateTime1!=null&&!"".equalsIgnoreCase(UpdateTime1)){
					itcase.setQpId(meeting_inputer +UpdateTime1);
				}else{
					itcase.setQpId(null);
				}
				String UpdateTime2= rs.getString("UpdateTime2");
				if(UpdateTime2!=null&&!"".equalsIgnoreCase(UpdateTime2)){
					itcase.setQpId1(rs.getString("EmployeeName2") +UpdateTime2);
				}else{
					itcase.setQpId1(null);
				}


				itcase.setPdId(rs.getInt("pdId"));
				itcase.setCompletiontime(rs.getString("completiontime"));
				itcase.setDateSample(rs.getString("dateSample"));
				itcase.setBargainNo(rs.getString("bargainNo"));
				itcase.setProductState(rs.getInt("productState"));
				String zhongwen1=rs.getString("zhongwen1");
				itcase.setTechnicalDocumentation(zhongwen1);

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
       return itcase;
	}


}