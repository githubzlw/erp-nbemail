package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cerong.erp.entity.EmailUser;
import cerong.erp.jdbc.SQLDBhelper;

public  class EmployeeDao implements IEmployeeDaoImpl{

	@Override
	public int updateDel(String userName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update employee set leave=1 where EmpEName = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
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
	public int getBack(String userName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "update employee set leave=0 where EmpEName = ? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
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
	public int del(String userName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "delete from employee where EmpEName=?  ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			
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
	public int updateRole(EmailUser user, String userName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
			if(user.getRoleNo()==3 || user.getRoleNo()==4 || user.getRoleNo()==5) {
				String sql = "update employee set EmpPostition=?,EmpEmail=?,EmpQualification=? where EmpEName = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getQualification());
				stmt.setString(2, user.getEmailAddress());
				stmt.setString(3, user.getEmailPWD());
				stmt.setString(4, user.getJob());
				stmt.setString(5, userName);
			}else {
				String sql = "update employee set EmpPostition=?,EmpQualification=? where EmpEName = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getQualification());
				stmt.setString(2, user.getJob());
				stmt.setString(3, userName);
			}
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
	public int addUser(EmailUser user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into employee(EmpEName,EmpCName,EmpGraduateTime,EmpCreateTime,"
				+ "EmpEmail,EmpPWD,EmpPostition,EmpQualification) values(?,?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getTrueName());
			stmt.setString(3, user.getWorktime());
			stmt.setString(4, user.getWorktime());
			stmt.setString(5, user.getEmailAddress());
			stmt.setString(6, user.getPwd());
			stmt.setInt(7, user.getQualification());
			stmt.setString(8, user.getJob());
			
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
	public int getUser(String empPWD, String empEName) {
		String sql = "select count(*) from employee where EmpEName=? and EmpPWD=? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empEName);
			stmt.setString(2, empPWD);
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
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
	public int getName(String userName) {
		String sql = "select count(*) from employee where EmpEName=?  ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
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
	public int addUser1(EmailUser user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into employee(EmpEName,EmpCName,EmpGraduateTime,EmpCreateTime,"
				+ "EmpPWD,EmpPostition,EmpQualification) values(?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getTrueName());
			stmt.setString(3, user.getWorktime());
			stmt.setString(4, user.getWorktime());
			
			stmt.setString(5, user.getPwd());
			stmt.setInt(6, user.getQualification());
			stmt.setString(7, user.getJob());
			
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
	public boolean createUser(String empEName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "alter table payment_maolirun add  "+"Employee_"+empEName  +"  float  ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			result= stmt.execute();
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
	public EmailUser search(EmailUser user1) {
		EmailUser user =null;
		String sql = "select * from employee where EmpEName=? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user1.getUserName());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				user=new EmailUser();
				user.setUserName(rs.getString("EmpEName"));
				user.setRoleNo(rs.getInt("EmpPostition"));
				user.setEmailPWD(rs.getString("EmpPWD"));

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
		return user;
	}

	@Override
	public void update(EmailUser user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
			if(user.getFlag()==1){
			String sql = "update employee set EmpPostition=?,EmpPWD=? where EmpEName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getRoleNo());
			stmt.setString(2, user.getPwd());
			stmt.setString(3, user.getUserName());
			
			result = stmt.executeUpdate();
			}else if(user.getFlag()==0){
				String sql = "update employee set EmpPostition=?,EmpPWD=? where EmpEName = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getRoleNo());
				stmt.setString(2, user.getPwd()+"abd");
				stmt.setString(3, user.getUserName());
				
				result = stmt.executeUpdate();	
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
		
		
	}

	@Override
	public void insert1(EmailUser user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into employee(EmpEName,EmpCName,EmpGraduateTime,EmpCreateTime,"
				+ "EmpPWD,EmpPostition) values(?,?,getdate(),getdate(),?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getPwd());
			stmt.setInt(4, user.getRoleNo());
			
			
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
	public List<EmailUser> getMember() {
		List<EmailUser>list=new ArrayList<EmailUser>();
        String sql = "select * from employee where member=1 ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				EmailUser user=new EmailUser();
				user.setUserName(rs.getString("EmpEName"));
				list.add(user);
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
    public List<EmailUser> getAllSales() {
		List<EmailUser>list=new ArrayList<EmailUser>();
		String sql = "select * from employee where EmpQualification ='销售' ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				EmailUser user=new EmailUser();
				user.setUserName(rs.getString("EmpEName"));
				list.add(user);
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
	public List<EmailUser> getAll() {
		List<EmailUser>list=new ArrayList<EmailUser>();
		String sql = "select * from employee where EmpQualification ='销售' and id not in (83,197,210,214,266) ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				EmailUser user=new EmailUser();
				user.setUserName(rs.getString("EmpEName"));
				list.add(user);
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
	public EmailUser getAccountingCustomers(EmailUser user1) {
		EmailUser user=null;
		String sql = "select * from (\n" +
				" select count(1)num1 from ( select customercode from (\n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0\n" +
				"and datediff(day,?,ifdate)>0 and datediff(day,?,ifdate)<0  and (CustomerManager=? or Merchandising=? or MerchandManager1=?)\n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"group by icaseno,customercode " +
				")a group by customercode)a\n" +
				")a ,(\n" +
				"  select count(1)num2 from ( select a.customercode from (select customercode from (\n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0\n" +
				"and datediff(day,?,ifdate)>0 and datediff(day,?,ifdate)<0  and (CustomerManager=? or Merchandising=? or MerchandManager1=?)\n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"group by icaseno,customercode\n" +
				")a group by customercode)a\n" +
				"left join \n" +
				"(\n" +
				"select customercode from (\n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0\n" +
				"and datediff(day,?,ifdate)>0  \n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"group by icaseno,customercode\n" +
				")a group by customercode)b\n" +
				"on a.customercode=b.customercode where b.customercode is null\n" +
				")b\n" +
				")b\n" +
				",\n" +
				"(select count(1)num3 from (\n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno left join customer cus on cus.id=it.customercode   where ifmoney is not null and ifmoney!=0\n" +
				"and datediff(day,?,ifdate)>0 and datediff(day,?,ifdate)<0  and (CustomerManager=? or Merchandising=? or MerchandManager1=?)\n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"and cus.customergrade  in(1,2)\n" +
				"group by icaseno,customercode\n" +
				")c\n" +
				")c\n" +
				",(\n" +
				"select count(1)num4 from (\n" +
				"select customercode from (\n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno left join customer cus on cus.id=it.customercode   where ifmoney is not null and ifmoney!=0\n" +
				"  and (CustomerManager=? or Merchandising=? or MerchandManager1=?)\n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"and cus.customergrade  in(1,2)\n" +
				"group by icaseno,customercode\n" +
				")a group by customercode\n" +
				")d\n" +
				")d\n" +
				",\n" +
				"(select count(1)num5 from (\n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno left join customer cus on cus.id=it.customercode   where ifmoney is not null and ifmoney!=0\n" +
				"and datediff(day,?,ifdate)>0 and datediff(day,?,ifdate)<0  and (CustomerManager=? or Merchandising=? or MerchandManager1=?)\n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"and cus.customergrade  in(1,2) and datediff(day,?,cus.creattime)>0\n" +
				"group by icaseno,customercode\n" +
				")c\n" +
				")e\n" +
				",(\n" +
				"select count(1)num6 from (\n" +
				"select customercode from (\n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno left join customer cus on cus.id=it.customercode   where ifmoney is not null and ifmoney!=0\n" +
				"  and (CustomerManager=? or Merchandising=? or MerchandManager1=?)\n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"and cus.customergrade  in(1,2) and datediff(day,?,cus.creattime)>0\n" +
				"group by icaseno,customercode\n" +
				")a group by customercode\n" +
				")d\n" +
				")f ,(" +
				"  select count(1)num7 from (select a.customercode from (select customercode from ( \n" +
				" select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0 \n" +
				" and datediff(day,?,ifdate)>0 and datediff(day,?,ifdate)<0  and (CustomerManager=? or Merchandising=? or MerchandManager1=?) \n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"group by icaseno,customercode \n" +
				")a group by customercode)a \n" +
				"left join  \n" +
				"( \n" +
				"select customercode from ( \n" +
				"select icaseno,customercode from invoiceinfo info left join itemcase it on info.icaseno=it.caseno   where ifmoney is not null and ifmoney!=0 \n" +
				"and datediff(day,?,ifdate)>0   \n" +
				" and customercode not in (select id from customer where customer_loss=1)"+
				"group by icaseno,customercode \n" +
				")a group by customercode)b \n" +
				"on a.customercode=b.customercode where b.customercode is null" +
				" )b " +
				" )h" +
				"";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			int i=0;
			stmt.setString(1, user1.getStartTime());
			stmt.setString(2, user1.getEndTime());
			stmt.setString(3, user1.getUserName());
			stmt.setString(4, user1.getUserName());
			stmt.setString(5, user1.getUserName());
			stmt.setString(6, user1.getStartTime());
			stmt.setString(7, user1.getEndTime());
			stmt.setString(8, user1.getUserName());
			stmt.setString(9, user1.getUserName());
			stmt.setString(10, user1.getUserName());
			stmt.setString(11, user1.getStartTime2());

			stmt.setString(12, user1.getStartTimea());
			stmt.setString(13, user1.getEndTimea());
			stmt.setString(14, user1.getUserName());
			stmt.setString(15, user1.getUserName());
			stmt.setString(16, user1.getUserName());
			stmt.setString(17, user1.getUserName());
			stmt.setString(18, user1.getUserName());
			stmt.setString(19, user1.getUserName());
			stmt.setString(20, user1.getStartTimea());
			stmt.setString(21, user1.getEndTimea());
			stmt.setString(22, user1.getUserName());
			stmt.setString(23, user1.getUserName());
			stmt.setString(24, user1.getUserName());
			stmt.setString(25, user1.getStartTime1());
			stmt.setString(26, user1.getUserName());
			stmt.setString(27, user1.getUserName());
			stmt.setString(28, user1.getUserName());
			stmt.setString(29, user1.getStartTime1());
			stmt.setString(30, user1.getStartTime());
			stmt.setString(31, user1.getStartTime3());
			stmt.setString(32, user1.getUserName());
			stmt.setString(33, user1.getUserName());
			stmt.setString(34, user1.getUserName());
			stmt.setString(35, user1.getStartTime3());
			rs = stmt.executeQuery();
			while(rs.next()) {
				user=new EmailUser();
				int num5=rs.getInt("num5");
				user.setUserName(user1.getUserName());
				user.setIncomingCustomers(rs.getInt("num1"));
				user.setIncomingCustomers1(rs.getInt("num2"));
			     user.setListedBelow(rs.getInt("num3"));
			     user.setAllCustomer(rs.getInt("num4"));
				user.setNewlistedBelow(rs.getInt("num5"));
				user.setAllnewlistedBelow(rs.getInt("num6"));
				user.setIncomingCustomers2(rs.getInt("num7"));
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
		return user;
	}

}
