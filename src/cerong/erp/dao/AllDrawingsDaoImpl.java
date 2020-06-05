package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cerong.erp.entity.AllDrawings;
import cerong.erp.jdbc.SQLDBhelper;

public class AllDrawingsDaoImpl implements  IAllDrawingsDao{

	@Override
	public List<AllDrawings> getDrawingsAll() {
		
		Calendar now = Calendar.getInstance();  
		int year =now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH)+1;
		int day=now.get(Calendar.DAY_OF_MONTH);
		int HOUR=now.get(Calendar.HOUR_OF_DAY);
		List<AllDrawings> list=new ArrayList<AllDrawings>();
		
				
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				if(HOUR==23){
				String sql1 = "select url,caseno from upload_blueprint where  datename(year,uploadtime)=? and datename(MONTH,uploadtime)=? and  datename(DD,uploadtime)=?   ";
				Connection conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					
					stmt1.setInt(1, year);
					stmt1.setInt(2, month);
					stmt1.setInt(3, day);
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String drawingName=rs1.getString("url");
						AllDrawings ad=new AllDrawings();
						ad.setDrawingName(drawingName);
						ad.setProjectNo(rs1.getString("caseno"));
						ad.setCategory(1);
						list.add(ad);
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
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select url,caseno from BJneibufile where  datename(year,uploadtime)=? and datename(MONTH,uploadtime)=? and  datename(DD,uploadtime)=? ";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					
					stmt2.setInt(1, year);
					stmt2.setInt(2, month);
					stmt2.setInt(3, day);
					rs2 = stmt2.executeQuery();
					while(rs2.next()) {
						String drawingName=rs2.getString("url");
						AllDrawings ad=new AllDrawings();
						ad.setDrawingName(drawingName);
						ad.setProjectNo(rs2.getString("caseno"));
						ad.setCategory(2);
						list.add(ad);
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
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				String sql3 = "select url,caseno from BJduiwai where  datename(year,creattime)=? and datename(MONTH,creattime)=? and  datename(DD,creattime)=? ";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					
					stmt3.setInt(1, year);
					stmt3.setInt(2, month);
					stmt3.setInt(3, day);
					rs3 = stmt3.executeQuery();
					while(rs3.next()) {
						String drawingName=rs3.getString("url");
						AllDrawings ad=new AllDrawings();
						ad.setDrawingName(drawingName);
						ad.setProjectNo(rs3.getString("caseno"));
						ad.setCategory(3);
						list.add(ad);
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
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				String sql4 = "select yingwen,zhongwen,caseno,remark,uploadDate,name from tuzhi where  datename(year,UploadDate)=? and datename(MONTH,UploadDate)=? and  datename(DD,UploadDate)=?";
				conn4 = SQLDBhelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					
					stmt4.setInt(1, year);
					stmt4.setInt(2, month);
					stmt4.setInt(3, day);
					rs4 = stmt4.executeQuery();
					while(rs4.next()) {
						String drawingName1=rs4.getString("zhongwen");
						String drawingName2=rs4.getString("yingwen");
						AllDrawings ad=new AllDrawings();
						if(drawingName1!=null&&!"".equals(drawingName1)){
						ad.setDrawingName(drawingName1);
						ad.setCategory(4);
						}else{
							ad.setDrawingName(drawingName2);
							ad.setCategory(5);
						}
						ad.setProjectNo(rs4.getString("caseno"));
						ad.setRemark(rs4.getString("remark"));
						ad.setUploadTime(rs4.getString("uploadDate"));
						ad.setReportName(rs4.getString("name"));
						list.add(ad);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt4 != null) {
						try {
							stmt4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs4 != null) {
						try {
							rs4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn4,stmt4,rs4);
				}
				}
				return list;
	}
	@Override
	public List<AllDrawings> getDrawingsAll1() {
		Calendar now = Calendar.getInstance();  
		int year =now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH)+1;
		int day=now.get(Calendar.DAY_OF_MONTH);
		int HOUR=now.get(Calendar.HOUR_OF_DAY);
		List<AllDrawings> list=new ArrayList<AllDrawings>();
		
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				if(HOUR==23){
				String sql1 = "select url,CaseNo,type from POupload where  datename(year,uploadtime)=? and datename(MONTH,uploadtime)=? and  datename(DD,uploadtime)=?";
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setInt(1, year);
					stmt1.setInt(2, month);
					stmt1.setInt(3, day);
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String type=rs1.getString("type");
						String drawingName=rs1.getString("url");
						AllDrawings ad=new AllDrawings();
						if(drawingName!=null&&!"".equalsIgnoreCase(drawingName)){
						if("JIANYANBAOGAO".equals(type)){
							ad.setCategory(1);
						}else if("JIANYANJIHUAZJ".equals(type)){
							ad.setCategory(2);
						}
						ad.setReportName(drawingName);
						ad.setProjectNo(rs1.getString("CaseNo"));
						list.add(ad);
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
					SQLDBhelper.close(conn1,stmt1,rs1);
				}
				}
			return list;
	}

	@Override
	public void insert(AllDrawings drawing) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into ProjectDrawings(caseno,DrawingName,UploadDate,name,"
				+ "definition_file) values(?,?,getdate(),?,?)";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, drawing.getProjectNo());
			stmt.setString(2, drawing.getDrawingName());
			stmt.setString(3, drawing.getReportName());
			stmt.setInt(4, drawing.getCategory());


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
    public List<AllDrawings> getAllDrawings(String caseNo) {
		List<AllDrawings> list=new ArrayList<AllDrawings>();
        Connection conn1 = null;
		PreparedStatement stmt1 = null;
		ResultSet rs1 = null;
         String sql1 = "select caseno,definition_file,max(DrawingName)DrawingName,max(UploadDate)UploadDate from ProjectDrawings where  definition_file!=0 and caseno like ? group by CaseNo,definition_file ";
			conn1 = SQLDBhelper.getConnection();
			try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, "%"+caseNo+"%");

				rs1 = stmt1.executeQuery();
				while(rs1.next()) {
					AllDrawings ad=new AllDrawings();
					ad.setReportName(rs1.getString("DrawingName"));
					ad.setProjectNo(rs1.getString("CaseNo"));
					ad.setCategory(rs1.getInt("definition_file"));
					ad.setUploadTime(rs1.getString("UploadDate"));
					list.add(ad);

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

		return list;
    }

    @Override
	public List<AllDrawings> getall() {
		Calendar now = Calendar.getInstance();  
		int year =now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH)+1;
		int day=now.get(Calendar.DAY_OF_MONTH);
		int HOUR=now.get(Calendar.HOUR_OF_DAY);
		List<AllDrawings> list=new ArrayList<AllDrawings>();
		
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				/*if(HOUR==23){*/
				String sql1 = "select url,caseno from upload_blueprint where  datename(year,uploadtime)=? ";
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setInt(1, 2017);
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String drawingName=rs1.getString("url");
						AllDrawings ad=new AllDrawings();
						ad.setDrawingName(drawingName);
						ad.setProjectNo(rs1.getString("caseno"));
						ad.setCategory(1);
						list.add(ad);
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
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select url,caseno from BJneibufile where  datename(year,uploadtime)=? ";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					
					stmt2.setInt(1, 2017);
					
					rs2 = stmt2.executeQuery();
					while(rs2.next()) {
						String drawingName=rs2.getString("url");
						AllDrawings ad=new AllDrawings();
						ad.setDrawingName(drawingName);
						ad.setProjectNo(rs2.getString("caseno"));
						ad.setCategory(2);
						list.add(ad);
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
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				String sql3 = "select url,caseno from BJduiwai where  datename(year,creattime)=?  ";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					
					stmt3.setInt(1, 2017);
					
					rs3 = stmt3.executeQuery();
					while(rs3.next()) {
						String drawingName=rs3.getString("url");
						AllDrawings ad=new AllDrawings();
						ad.setDrawingName(drawingName);
						ad.setProjectNo(rs3.getString("caseno"));
						ad.setCategory(3);
						list.add(ad);
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
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				String sql4 = "select yingwen,zhongwen,caseno from tuzhi where  datename(year,UploadDate)=? ";
				conn4 = SQLDBhelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					
					stmt4.setInt(1, 2017);
					
					rs4 = stmt4.executeQuery();
					while(rs4.next()) {
						String drawingName1=rs4.getString("zhongwen");
						String drawingName2=rs4.getString("yingwen");
						AllDrawings ad=new AllDrawings();
						if(drawingName1!=null&&!"".equals(drawingName1)){
						ad.setDrawingName(drawingName1);
						ad.setCategory(4);
						}else{
							ad.setDrawingName(drawingName2);
							ad.setCategory(5);
						}
						ad.setProjectNo(rs4.getString("caseno"));
						list.add(ad);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (stmt4 != null) {
						try {
							stmt4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (rs4 != null) {
						try {
							rs4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.close(conn4,stmt4,rs4);
				}
				return list;
	}
	@Override
	public List<AllDrawings> getall1() {
		Calendar now = Calendar.getInstance();  
		int year =now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH)+1;
		int day=now.get(Calendar.DAY_OF_MONTH);
		int HOUR=now.get(Calendar.HOUR_OF_DAY);
		List<AllDrawings> list=new ArrayList<AllDrawings>();
		
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				/*if(HOUR==23){*/
				String sql1 = "select url,CaseNo from POupload where  datename(year,uploadtime)=? ";
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					
					stmt1.setInt(1, 2017);
					
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String drawingName=rs1.getString("url");
						AllDrawings ad=new AllDrawings();
						ad.setReportName(drawingName);
						ad.setProjectNo(rs1.getString("CaseNo"));
						list.add(ad);
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
			return list;
	}

	

}
