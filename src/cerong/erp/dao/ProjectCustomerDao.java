package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cerong.erp.entity.ItemCase2;
import cerong.erp.entity.ProjectCustomer;
import cerong.erp.jdbc.SQLDBhelper;

public class ProjectCustomerDao implements IProjectCustomerDaoImpl{

	@Override
	public List<ProjectCustomer> getall(int start,int end) {
		List<ProjectCustomer> list =new ArrayList<ProjectCustomer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
        String sql = "select it.CaseNo,it.ProductDescC,it.ProductDescE,it.CustomerManager,it.customercode,it.MerchandManager1"
        		+ ",it.CreateTime,cus.name from itemCase it left join Customer cus on it.customercode=cus.id where datediff(day,CreateTime,getdate())<100";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				ProjectCustomer info=new ProjectCustomer();
				int eid=rs.getInt("customercode");
				if(eid!=0){
				info.setCaseNo(rs.getString("CaseNo"));
				info.setCreateTime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
			    info.setName(rs.getString("name"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setSaleName1(rs.getString("CustomerManager"));
				info.setSaleName2(rs.getString("MerchandManager1"));
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
	public List<ProjectCustomer> getall1(int start, int end) {
		List<ProjectCustomer> list =new ArrayList<ProjectCustomer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
        String sql = "select it.CaseNo,it.ProductDescC,it.ProductDescE,it.CustomerManager,it.customercode,it.MerchandManager1"
        		+ ",it.CreateTime,cus.name from itemCase it left join Customer cus on it.customercode=cus.id where it.id=21571";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			//stmt.setInt(1, start);
			//stmt.setInt(2, end);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ProjectCustomer info=new ProjectCustomer();
				int eid=rs.getInt("customercode");
				if(eid!=0){
				info.setCaseNo(rs.getString("CaseNo"));
				info.setCreateTime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setSaleName1(rs.getString("CustomerManager"));
				info.setSaleName2(rs.getString("MerchandManager1"));
				info.setName(rs.getString("name"));
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
	public List<ProjectCustomer> getall2(int start, int end) {
		List<ProjectCustomer> list =new ArrayList<ProjectCustomer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
        String sql = "select it.CaseNo,it.ProductDescC,it.ProductDescE,it.CustomerManager,it.customercode,it.MerchandManager1"
        		+ ",it.CreateTime from itemCase it left join Customer cus on it.customercode=cus.id where it.id=?";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 34185);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				ProjectCustomer info=new ProjectCustomer();
				int eid=rs.getInt("customercode");
				if(eid!=0){
				info.setCaseNo(rs.getString("CaseNo"));
				info.setCreateTime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setSaleName1(rs.getString("CustomerManager"));
				info.setSaleName2(rs.getString("MerchandManager1"));
				
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
	public List<ProjectCustomer> getall1(int eid1) {
		List<ProjectCustomer> list =new ArrayList<ProjectCustomer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
        String sql = "select it.CaseNo,it.ProductDescC,it.EmailContent,it.ProductDescE,it.CustomerManager,"
        		+ "it.customercode,it.MerchandManager1,it.CreateTime,cus.name from itemCase"
        		+ " it left join Customer cus on it.customercode=cus.id where datediff(day,it.CreateTime,getdate() )<80 "
        		+ "  ORDER BY it.CreateTime ";
        conn = SQLDBhelper.getConnection();
        try {
			stmt = conn.prepareStatement(sql);

			
			rs = stmt.executeQuery();
			while(rs.next()) {
				ProjectCustomer info=new ProjectCustomer();
				String ProductDescC=rs.getString("ProductDescC");
				 Boolean index1=false;
				index1 = ProductDescC.toLowerCase().contains("##".toLowerCase());
			    if(index1!=false){}else{
				int eid=rs.getInt("customercode");
				if(eid!=0){
				info.setCaseNo(rs.getString("CaseNo"));
				info.setCreateTime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setEmailContent(rs.getString("EmailContent"));
				info.setName(rs.getString("name"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setSaleName1(rs.getString("CustomerManager"));
				info.setSaleName2(rs.getString("MerchandManager1"));
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
	public List<ProjectCustomer> getall1() {
		List<ProjectCustomer> list =new ArrayList<ProjectCustomer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
        String sql = "select it.CaseNo,it.ProductDescC,it.ProductDescE,it.CustomerManager,it.customercode,it.MerchandManager1,it.EmailContent"
        		+ ",it.CreateTime,cus.name from itemCase it left join Customer cus on it.customercode=cus.id where datediff(day,CreateTime,getdate())<1";
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
				Long millionSeconds3=format.parse(updatetime).getTime(); 
				if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
				ProjectCustomer info=new ProjectCustomer();
				int eid=rs.getInt("customercode");
				if(eid!=0){
				info.setCaseNo(rs.getString("CaseNo"));
				info.setCreateTime(rs.getString("CreateTime"));
				info.setEid(rs.getInt("customercode"));
				info.setEmailContent(rs.getString("EmailContent"));
				info.setName(rs.getString("name"));
				info.setProjectDescc(rs.getString("ProductDescC"));
				info.setProjectDesce(rs.getString("ProductDescE"));
				info.setSaleName1(rs.getString("CustomerManager"));
				info.setSaleName2(rs.getString("MerchandManager1"));
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

}
