package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cerong.erp.entity.FactoryFund;
import cerong.erp.entity.Mould;
import cerong.erp.jdbc.SQLDBhelper;

public class MouldDaoImpl  implements IMouldDao{

	@Override
	public List<Mould> getall() {
		List<Mould> list = new ArrayList<Mould>();
		Mould con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	   String sql = "select mo.mid,it.CaseNo,it.customercode,it.Upload,mo.price,mo.amount,mo.product from itemCase it  left join moju mo on mo.caseno=it.CaseNo where  price is not null and mid>38667 ";
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				con=new Mould();
				id=rs.getInt("amount");
				if(id==0){
					 con.setAmount(1);
				}else{
			   con.setAmount(rs.getInt("amount"));
				}
			   con.setCaseno(rs.getString("CaseNo").toLowerCase());
			   con.setCustomercode(rs.getInt("customercode"));
			   con.setDrawingname(rs.getString("Upload"));
			   con.setPrice(rs.getDouble("price"));
			   con.setProduct(rs.getString("product"));
			   con.setId(rs.getInt("mid"));
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
	public List<Mould> getall1(int eid) {
		List<Mould> list = new ArrayList<Mould>();
		Mould con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	    //
	   String sql = "select mo.mid,it.CaseNo,it.customercode,it.Upload,mo.price,mo.amount,"
	   		+ "mo.product from  (select * from moju a where a.caseno !='')  mo inner join "
	   		+ "itemCase it   on mo.caseno=it.CaseNo where   it.CaseNo  in "
	   		+ "('SHS14697') ";
	/*   String sql = "select mo.mid,it.CaseNo,it.customercode,it.Upload,mo.price,mo.amount,"
			   + "mo.product from  (select * from moju a where a.caseno !='')  mo inner join "
			   + "itemCase it   on mo.caseno=it.CaseNo where price is not null  and it.customercode "
			   + "in(86933,1121,5825,5067,5100,4422,87085,97814,97470,3492,1085,127,2402,5760,119587,4165,"
			   + "3258,97644,109288,97777,108398,1085,4982,2382)  and mo.mid>29581";*/
		
		conn = SQLDBhelper.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				con=new Mould();
				id=rs.getInt("amount");
				if(id==0){
					 con.setAmount(1);
				}else{
			   con.setAmount(rs.getInt("amount"));
				}
			   con.setCaseno(rs.getString("CaseNo"));
			   con.setCustomercode(rs.getInt("customercode"));
			   con.setDrawingname(rs.getString("Upload"));
			   con.setPrice(rs.getDouble("price"));
			   con.setProduct(rs.getString("product"));
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
	public List<Mould> getall2() {
		List<Mould> list = new ArrayList<Mould>();
		Mould con=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		int id=0;
	    ResultSet rs = null;
	   String sql = "select mo.updatetime,mo.mid,it.CaseNo,it.customercode,it.Upload,mo.price,mo.amount,"
	   		+ "mo.product from  (select * from moju a where a.caseno !='')  mo inner join "
	   		+ "itemCase it   on mo.caseno=it.CaseNo where price is not null   and datediff(day,mo.updatetime,getdate())<1";
	
		
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
				con=new Mould();
				id=rs.getInt("amount");
				if(id==0){
					 con.setAmount(1);
				}else{
			   con.setAmount(rs.getInt("amount"));
				}
			   con.setCaseno(rs.getString("CaseNo"));
			   con.setCustomercode(rs.getInt("customercode"));
			   con.setDrawingname(rs.getString("Upload"));
			   con.setPrice(rs.getDouble("price"));
			   con.setProduct(rs.getString("product"));
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

}
