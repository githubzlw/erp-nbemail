package cerong.erp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sound.midi.MidiDevice.Info;
import cerong.erp.entity.EmailUser;
import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.util.Calendar;
import com.sun.org.apache.bcel.internal.generic.I2F;
import cerong.erp.entity.MoneyCheckUp;
import cerong.erp.entity.invoice;
import cerong.erp.jdbc.SQLDBhelper;
import cerong.erp.service.ICheckUpServiceImpl;

public class CheckUpDao implements ICheckUpDaoImpl{
	private static final double PAGE_SIZE = 6.9;
	@Override
	public List<MoneyCheckUp> getAll(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select isnull(bar.ReceiptContract,3)ReceiptContract,a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.GrossProfit,b.LoginDate,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 2 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo"
				+ "   left join (select * from  Bargain where BargainNo!='' )bar on bar.BargainNo=a.BargainNo "

				+ " where a.state != '<font color=green>已完成款项</font>' and a.state != '<font color=red>未通过审批</font>' and a.FacMoney!=0  and datediff(day,a.StateDate,getdate())<30 "
				+" union" +
				" select isnull(bar.ReceiptContract,3)ReceiptContract,a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.GrossProfit,b.LoginDate," +
				" b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1," +
				" b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<font color=green>已完成款项</font>',a.State)>0" +
				"  then 5 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 6  else 7 end),a.FacMoney,a.State," +
				" a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate," +
				" a.StateDate,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo" +
				"    left join (select * from  Bargain where BargainNo!='' )bar on bar.BargainNo=a.BargainNo " +
				" where (a.state = '<font color=green>已完成款项</font>' or a.state = '<font color=red>未通过审批</font>') and a.FacMoney!=0 and (express_lane_approval=0 or express_lane_approval=2) and express_lane=1 and datediff(day,a.StateDate,getdate())<30 " +
				" ";

		int i=0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE = ? or b.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			sql += " order by  express_lane desc,State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setReceiptContract(rs.getInt("receiptContract"));
				info.setProfit(rs.getString("GrossProfit"));
				info.setLoginDate(rs.getString("LoginDate"));
				String  CustomerManager=rs.getString("CustomerManager");
				String  MerchandManager1=rs.getString("MerchandManager1");
				String  MerchandManager2=rs.getString("MerchandManager2");
				String  Engineer1=rs.getString("Engineer1");
				String  Engineer2=rs.getString("Engineer2");
				String  Engineer3=rs.getString("Engineer3");
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





				String s1 = "ChouchendongzhaoshuhaoHouQiangBensonzhangsunjinshuxuweixupingzhaoqiangRogerQiu";
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
				if(cp.getNum()==0){
					Timestamp  FacDate=rs.getTimestamp("StateDate");
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time1=format.format(date);
					String time2=format.format(FacDate);
					Long millionSeconds4 = format.parse(time1).getTime()-86400000*4L;
					Long millionSeconds5 = format.parse(time2).getTime();
					String State=rs.getString("State");
					if(millionSeconds5>millionSeconds4){
						if("<font color=blue>正在审批中</font>".equals(State)){
						info.setId(rs.getInt("id"));
						info.setCaseNo(rs.getString("CaseNo"));
						info.setProductDescE(rs.getString("ProductDescE"));
						info.setProductDescC(rs.getString("ProductDescC"));
						info.setItemManager(rs.getString("ItemManager"));
						info.setState(rs.getInt("State1"));
						info.setFacMoney(rs.getInt("FacMoney"));
						info.setMtype(rs.getString("mtype"));
						info.setState1(rs.getString("State"));
						info.setFacDate(rs.getTimestamp("FacDate"));
						info.setFacReason(rs.getString("FacReason"));
						info.setReason(rs.getString("Reason"));
						info.setGeldObject(rs.getString("GeldObject"));
						info.setChecktype(rs.getInt("checktype"));
						info.setPaystate(rs.getString("paystate"));
						info.setStateDate(rs.getTimestamp("StateDate"));
							info.setFastTrackReasons(rs.getString("fast_track_reasons"));
							info.setExpressLane(rs.getInt("express_lane"));
							info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
						list.add(info);
						}
					}
				}else if(cp.getNum()==1){
					  String State=rs.getString("State");
						Timestamp  FacDate=rs.getTimestamp("StateDate");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time1=format.format(date);
						String time2=format.format(FacDate);
						Long millionSeconds1 = format.parse(time1).getTime()-86400000*4L;
						Long millionSeconds2 = format.parse(time1).getTime()-86400000*10L;
						Long millionSeconds3 = format.parse(time2).getTime();
						if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
							if("<font color=blue>正在审批中</font>".equals(State)){
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
								info.setFastTrackReasons(rs.getString("fast_track_reasons"));
								info.setExpressLane(rs.getInt("express_lane"));
								info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
							list.add(info);
							}
						}

					}else if(cp.getNum()==2){
						String State=rs.getString("State");
							Timestamp  FacDate=rs.getTimestamp("StateDate");
							Date date = new Date();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time1=format.format(date);
							String time2=format.format(FacDate);
							Long millionSeconds1 = format.parse(time1).getTime()-86400000*10L;
							Long millionSeconds2 = format.parse(time1).getTime()-86400000*20L;
							Long millionSeconds3 = format.parse(time2).getTime();
							if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
								if("<font color=blue>正在审批中</font>".equals(State)){
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								info.setReason(rs.getString("Reason"));
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
									info.setFastTrackReasons(rs.getString("fast_track_reasons"));
									info.setExpressLane(rs.getInt("express_lane"));
									info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
								list.add(info);
								}
							}

						}else if(cp.getNum()==3){
							String State=  rs.getString("State");
								Timestamp  FacDate=rs.getTimestamp("StateDate");
								Date date = new Date();
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time1=format.format(date);
								String time2=format.format(FacDate);
								Long millionSeconds4 = format.parse(time1).getTime()-86400000*20L;
								Long millionSeconds5 = format.parse(time2).getTime();
								if(millionSeconds5<millionSeconds4){
									if("<font color=blue>正在审批中</font>".equals(State)){
									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									info.setReason(rs.getString("Reason"));
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));
										info.setFastTrackReasons(rs.getString("fast_track_reasons"));
										info.setExpressLane(rs.getInt("express_lane"));
										info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
									list.add(info);
									}
							}
						}else{
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
					info.setFastTrackReasons(rs.getString("fast_track_reasons"));
					info.setExpressLane(rs.getInt("express_lane"));
					info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
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
			SQLDBhelper.returnConnection(conn);
		}
		return list;

	}

	@Override
	public int getTotal(MoneyCheckUp cp) {
		String sql = "select count(*) from "
				+ " MoneyCheckUp as a inner join itemCase as c on c.CaseNo=a.CaseNo  "
		       + "where a.state != '<font color=green>已完成款项</font>' and a.state != '<font color=red>未通过审批</font>'";
		int total=0;
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and c.ProductDescE = ? or c.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, "%"+cp.getGeldObject()+"%");

			}

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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int getTotal1(MoneyCheckUp cp) {
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>' and a.state != '<font color=blur>已通过审批</font>'";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;

		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE like ? or b.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}

			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}


			rs = stmt.executeQuery();

			while(rs.next()) {
				total+=rs.getInt("FacMoney");
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int getTotal2(MoneyCheckUp cp) {
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>' and a.state != '<font color=blue>正在审批中</font>'"
				+ "and a.state !='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>'";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;

		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE like ? or b.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}

			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				total+=rs.getInt("FacMoney");
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int getTotal3(MoneyCheckUp cp) {
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=blur>已通过审批</font>' and a.state != '<font color=blue>正在审批中</font>'"
				+ "and a.state !='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>'";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;

		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE like ? or b.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}

			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}
			if(cp.getChecktype() != -1) {
				if(cp.getChecktype() == 0) {
					sql +=" and a.FacReason like '%首付款%'";
				}else if(cp.getChecktype() == 1) {
					sql +=" and a.FacReason like '%需垫付%'";
				}else if(cp.getChecktype() == 2) {
					sql +=" and a.FacReason like '%运费%'";
				}else if(cp.getChecktype() == 3) {
					sql +=" and a.FacReason like '%尾款%'";
				}

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				total+=rs.getInt("FacMoney");
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public List<MoneyCheckUp> getAll1(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=blue>正在审批中</font>' and a.state != '<font color=blur>已通过审批</font>'"
				+ "and a.state !='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>' ";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE like ? or b.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}

			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setId(rs.getInt("id"));
				info.setCaseNo(rs.getString("CaseNo"));
				info.setProductDescE(rs.getString("ProductDescE"));
				info.setProductDescC(rs.getString("ProductDescC"));
				info.setCustomerManager(rs.getString("CustomerManager"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setEngineer1(rs.getString("Engineer1"));
				info.setEngineer2(rs.getString("Engineer2"));
				info.setEngineer3(rs.getString("Engineer3"));
				info.setItemManager(rs.getString("ItemManager"));
				info.setState(rs.getInt("State1"));
				info.setFacMoney(rs.getInt("FacMoney"));
				info.setMtype(rs.getString("mtype"));
				info.setState1(rs.getString("State"));
				info.setFacDate(rs.getTimestamp("FacDate"));
				info.setFacReason(rs.getString("FacReason"));
				info.setReason(rs.getString("Reason"));
				info.setGeldObject(rs.getString("GeldObject"));
				info.setChecktype(rs.getInt("checktype"));
				info.setPaystate(rs.getString("paystate"));
				info.setStateDate(rs.getTimestamp("StateDate"));

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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public int getTotal4(MoneyCheckUp cp) {
		String sql = "select count(*) from "
				+ " MoneyCheckUp as a inner join itemCase as c on c.CaseNo=a.CaseNo  "
		       + "where a.state != '<font color=green>已完成款项</font>' "
		       + "and a.state != '<font color=blue>正在审批中</font>' and a.state != '<font color=blur>已通过审批</font>'"
		       + "and a.state !='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>' ";
		int total=0;
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and c.ProductDescE like ? or c.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, "%"+cp.getGeldObject()+"%");

			}

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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public List<MoneyCheckUp> getAll2(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 2 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 3 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>'  ";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE like ? or b.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}

			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setId(rs.getInt("id"));
				info.setCaseNo(rs.getString("CaseNo"));
				info.setProductDescE(rs.getString("ProductDescE"));
				info.setProductDescC(rs.getString("ProductDescC"));
				info.setCustomerManager(rs.getString("CustomerManager"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setEngineer1(rs.getString("Engineer1"));
				info.setEngineer2(rs.getString("Engineer2"));
				info.setEngineer3(rs.getString("Engineer3"));
				info.setItemManager(rs.getString("ItemManager"));
				info.setState(rs.getInt("State1"));
				info.setFacMoney(rs.getInt("FacMoney"));
				info.setMtype(rs.getString("mtype"));
				info.setState1(rs.getString("State"));
				info.setFacDate(rs.getTimestamp("FacDate"));
				info.setFacReason(rs.getString("FacReason"));
				info.setReason(rs.getString("Reason"));
				info.setGeldObject(rs.getString("GeldObject"));
				info.setChecktype(rs.getInt("checktype"));
				info.setPaystate(rs.getString("paystate"));
				info.setStateDate(rs.getTimestamp("StateDate"));

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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public int getTotal5(MoneyCheckUp cp) {
		String sql = "select count(*) from "
				+ " MoneyCheckUp as a inner join itemCase as c on c.CaseNo=a.CaseNo  "
		       + "where a.state != '<font color=green>已完成款项</font>' "
		       + "and a.state != '<font color=red>未通过审批</font>'  ";
		int total=0;
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and c.ProductDescE like ? or c.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}


			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, "%"+cp.getGeldObject()+"%");

			}

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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int updateStateDate(int id, String time) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "update MoneyCheckUp set StateDate=?,pressformoney=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, time);
			stmt.setInt(2, 1);
			stmt.setInt(3,id );
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
	public int update1(String time) {

		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where   a.state='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>'   ";

		int id=0;
		int total=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int money=0;
		conn = SQLDBhelper.getConnection();
		try {
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
			while(rs.next()) {
				money=rs.getInt("FacMoney");
				id=rs.getInt("id");
				if(money<10000){
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					int result1=0;
					String sql1 = "update MoneyCheckUp set StateDate=?,State=?,Reason=? where id = ?  ";
					conn1 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, time);
						stmt1.setString(2,"<font color=blur>已通过审批</font>");
						stmt1.setString(3, "Eddie审批通过");
						stmt1.setInt(4, id);
						total = stmt1.executeUpdate();
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn2 = null;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					int result2=0;
					String sql2 = "update FactoryFund set State =?, statetime = ? , checkReason = ?  where  ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn2 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setString(1, "<font color=blur>已通过审批</font>");
						stmt2.setString(2, time);
						stmt2.setString(3, "Eddie审批通过");
						stmt2.setInt(4, id);
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					ResultSet rs3 = null;
					int result3=0;
					String sql3 = "update Wuliu SET State = ?,WuliuReason = ? WHERE ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn3 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setString(1, "<font color=blur>已通过审批</font>");
						stmt3.setString(2, "Eddie审批通过");
						stmt3.setInt(3, id);
						result3 = stmt3.executeUpdate();
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
						if (rs3!= null) {
							try {
								rs3.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn3,stmt3,rs3);
						//DBHelper.returnConnection(conn);
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int update(String time) {
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where  a.State='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>'  and a.State!='<font color=green>已完成款项</font>' ";

		int id=0;
		int total=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int money=0;
		conn = SQLDBhelper.getConnection();
		try {
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
			while(rs.next()) {

				id=rs.getInt("id");

					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					int result1=0;
					String sql1 = "update MoneyCheckUp set StateDate=?,State=?,Reason=? where ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn1 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, time);
						stmt1.setString(2,"<font color=blur>已通过审批</font>");
						stmt1.setString(3, "Eddie审批通过");
						stmt1.setInt(4, id);
						total = stmt1.executeUpdate();
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn2 = null;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					int result2=0;
					String sql2 = "update FactoryFund set State =?, statetime = ? , checkReason = ?  where  ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?)  ";
					conn2 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setString(1, "<font color=blur>已通过审批</font>");
						stmt2.setString(2, time);
						stmt2.setString(3, "Eddie审批通过");
						stmt2.setInt(4, id);
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					ResultSet rs3 = null;
					int result3=0;
					String sql3 = "update Wuliu SET State = ?,WuliuReason = ? WHERE ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn3 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setString(1, "<font color=blur>已通过审批</font>");
						stmt3.setString(2, "Eddie审批通过");
						stmt3.setInt(3, id);
						result3 = stmt3.executeUpdate();
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
						if (rs3!= null) {
							try {
								rs3.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn3,stmt3,rs3);
						//DBHelper.returnConnection(conn);
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int getnum() {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "select count(*) from MoneyCheckUp  where State ='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>' and FacMoney<10000";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

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
	public int getnum1() {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "select count(*) from MoneyCheckUp  where State ='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>' ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);

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
	public List<MoneyCheckUp> getAlla(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select * from (select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,c.Customerremarks,a.pressformoney,b.GrossProfit,b.LoginDate,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 2 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 1 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate,a.approvalTime,a.ReasonsApproval,a.approval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "left join Customer as c  on c.id=b.customercode "
				+ "where a.state != '<font color=green>已完成款项</font>' and "
				+ "a.state != '<font color=red>未通过审批</font>' and a.FacMoney!=0 and datediff(day,a.StateDate,getdate())<30  "
				+" union " +
				"   select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,c.Customerremarks,a.pressformoney,b.GrossProfit,b.LoginDate," +
				" b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1," +
				" b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<font color=green>已完成款项</font>',a.State)>0" +
				"  then 5 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 6 else 7 end),a.FacMoney,a.State," +
				" a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate," +
				" a.StateDate,a.approvalTime,a.ReasonsApproval,a.approval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo " +
				" left join Customer as c  on c.id=b.customercode " +
				" where (a.state = '<font color=green>已完成款项</font>' or " +
				" a.state = '<font color=red>未通过审批</font>') and a.FacMoney!=0 " +
				"  and (express_lane_approval=0 or express_lane_approval=2) and express_lane=1 and datediff(day,a.StateDate,getdate())<30" +
				"  )a";

		int i=0;
		String facReason="";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE = ? or b.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			sql += " order by  express_lane desc,State1 , a.StateDate desc,a.approval  ";
			stmt = conn.prepareStatement(sql);

			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setPressformoney(rs.getInt("pressformoney"));
				info.setApprovalTime(rs.getString("approvalTime"));
				info.setProfit(rs.getString("GrossProfit"));
				info.setLoginDate(rs.getString("LoginDate"));
				info.setReasonsApproval(rs.getString("reasonsApproval"));
				String  CustomerManager=rs.getString("CustomerManager");
				String  MerchandManager1=rs.getString("MerchandManager1");
				String  MerchandManager2=rs.getString("MerchandManager2");
				String  Engineer1=rs.getString("Engineer1");
				String  Engineer2=rs.getString("Engineer2");
				String  Engineer3=rs.getString("Engineer3");
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
				if(cp.getNum()==0){
					Timestamp  FacDate=rs.getTimestamp("StateDate");
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time1=format.format(date);
					String time2=format.format(FacDate);
					Long millionSeconds4 = format.parse(time1).getTime()-86400000*4L;
					Long millionSeconds5 = format.parse(time2).getTime();
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");
					if(millionSeconds5>millionSeconds4){
						if("<font color=blue>正在审批中</font>".equals(State)){
							String s1 = "运费";
						Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
							if(index1 !=false){
								info.setStatea(2);
							}else{
								if(money<10000){
								info.setStatea(1);
								}else{
								info.setStatea(0);
								}
							}
						info.setId(rs.getInt("id"));
						info.setCaseNo(rs.getString("CaseNo"));
						info.setProductDescE(rs.getString("ProductDescE"));
						info.setProductDescC(rs.getString("ProductDescC"));

						info.setItemManager(rs.getString("ItemManager"));
						info.setState(rs.getInt("State1"));
						info.setFacMoney(rs.getInt("FacMoney"));
						info.setMtype(rs.getString("mtype"));
						info.setState1(rs.getString("State"));
						info.setFacDate(rs.getTimestamp("FacDate"));
						info.setFacReason(rs.getString("FacReason"));
						info.setReason(rs.getString("Reason"));
						info.setGeldObject(rs.getString("GeldObject"));
						info.setChecktype(rs.getInt("checktype"));
						info.setPaystate(rs.getString("paystate"));
						info.setStateDate(rs.getTimestamp("StateDate"));
						info.setCustomerremarks(rs.getString("Customerremarks"));
						info.setProjectnote(rs.getString("projectnote"));
							info.setFastTrackReasons(rs.getString("fast_track_reasons"));
							info.setExpressLane(rs.getInt("express_lane"));
						list.add(info);
						}
					}
				}else if(cp.getNum()==1){
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");


						Timestamp  FacDate=rs.getTimestamp("StateDate");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time1=format.format(date);
						String time2=format.format(FacDate);
						Long millionSeconds1 = format.parse(time1).getTime()-86400000*4L;
						Long millionSeconds2 = format.parse(time1).getTime()-86400000*10L;
						Long millionSeconds3 = format.parse(time2).getTime();
						if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
							if("<font color=blue>正在审批中</font>".equals(State)){
								String s1 = "运费";
								Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
									if(index1 !=false){
										info.setStatea(2);
									}else{
										if(money<10000){
										info.setStatea(1);
										}else{
										info.setStatea(0);
										}
									}
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							info.setCustomerremarks(rs.getString("Customerremarks"));
							info.setProjectnote(rs.getString("projectnote"));
								info.setFastTrackReasons(rs.getString("fast_track_reasons"));
								info.setExpressLane(rs.getInt("express_lane"));
							list.add(info);
							}
						}

					}else if(cp.getNum()==2){
						String State=rs.getString("State");
						facReason=rs.getString("FacReason");
						int money=rs.getInt("FacMoney");

							Timestamp  FacDate=rs.getTimestamp("StateDate");
							Date date = new Date();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time1=format.format(date);
							String time2=format.format(FacDate);
							Long millionSeconds1 = format.parse(time1).getTime()-86400000*10L;
							Long millionSeconds2 = format.parse(time1).getTime()-86400000*20L;
							Long millionSeconds3 = format.parse(time2).getTime();
							if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
								if("<font color=blue>正在审批中</font>".equals(State)){
									String s1 = "运费";
									Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
										if(index1 !=false){
											info.setStatea(2);
										}else{
											if(money<10000){
											info.setStatea(1);
											}else{
											info.setStatea(0);
											}
										}
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								info.setReason(rs.getString("Reason"));
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								info.setCustomerremarks(rs.getString("Customerremarks"));
								info.setProjectnote(rs.getString("projectnote"));
									info.setFastTrackReasons(rs.getString("fast_track_reasons"));
									info.setExpressLane(rs.getInt("express_lane"));
								list.add(info);
								}
							}

						}else if(cp.getNum()==3){
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
								Timestamp  FacDate=rs.getTimestamp("StateDate");
								Date date = new Date();
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time1=format.format(date);
								String time2=format.format(FacDate);
								Long millionSeconds4 = format.parse(time1).getTime()-86400000*20L;
								Long millionSeconds5 = format.parse(time2).getTime();
								if(millionSeconds5<millionSeconds4){
									if("<font color=blue>正在审批中</font>".equals(State)){
										String s1 = "运费";
										Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
											if(index1 !=false){
												info.setStatea(2);
											}else{
												if(money<10000){
												info.setStatea(1);
												}else{
												info.setStatea(0);
												}
											}
									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									info.setReason(rs.getString("Reason"));
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));
									info.setCustomerremarks(rs.getString("Customerremarks"));
									info.setProjectnote(rs.getString("projectnote"));
										info.setFastTrackReasons(rs.getString("fast_track_reasons"));
										info.setExpressLane(rs.getInt("express_lane"));
									list.add(info);
									}
							}
						}else{
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
							if("<font color=blur>已通过审批</font>".equals(State)||"<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){
								info.setStatea(3);
							}else{
							String s1 = "运费";
							Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
								if(index1 !=false){
									info.setStatea(2);
								}else{
									if(money<10000){
									info.setStatea(1);
									}else{
									info.setStatea(0);
									}
								}
							}
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							info.setCustomerremarks(rs.getString("Customerremarks"));
							info.setProjectnote(rs.getString("projectnote"));
					info.setFastTrackReasons(rs.getString("fast_track_reasons"));
					info.setExpressLane(rs.getInt("express_lane"));
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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public int getTotala(MoneyCheckUp cp) {
		String sql = "select count(*) from "
				+ " MoneyCheckUp as a inner join itemCase as c on c.CaseNo=a.CaseNo  "
		       + "where a.state != '<font color=green>已完成款项</font>' "
		       + "and a.state != '<font color=red>未通过审批</font>'  ";
		int total=0;
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and c.ProductDescE like ? or c.ProductDescC like ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}


			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, "%"+cp.getProductDescE()+"%");
				stmt.setString(i+1, "%"+cp.getProductDescE()+"%");
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, "%"+cp.getGeldObject()+"%");

			}

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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public List<MoneyCheckUp> getAllb(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select b.special_items,a.paystate,a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 2 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' and "
				+ "a.state != '<font color=red>未通过审批</font>'  "
				+ "and  a.FacMoney<5000  and a.FacMoney!=0   and datediff(day,a.StateDate,getdate())<30 and a.approval=0";

		int i=0;
		String facReason="";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE = ? or b.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);

			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				String  CustomerManager=rs.getString("CustomerManager");
				int  special_items=rs.getInt("special_items");
				String  MerchandManager1=rs.getString("MerchandManager1");
				String  MerchandManager2=rs.getString("MerchandManager2");
				String  Engineer1=rs.getString("Engineer1");
				String  Engineer2=rs.getString("Engineer2");
				String  Engineer3=rs.getString("Engineer3");
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
				if(special_items==1){

					info.setId(rs.getInt("id"));
					info.setCaseNo(rs.getString("CaseNo"));
					info.setProductDescE(rs.getString("ProductDescE"));
					info.setProductDescC(rs.getString("ProductDescC"));

					info.setItemManager(rs.getString("ItemManager"));
					info.setState(rs.getInt("State1"));
					info.setFacMoney(rs.getInt("FacMoney"));
					info.setMtype(rs.getString("mtype"));
					info.setState1(rs.getString("State"));
					info.setFacDate(rs.getTimestamp("FacDate"));
					info.setFacReason(rs.getString("FacReason"));
					info.setGeldObject(rs.getString("GeldObject"));
					info.setChecktype(rs.getInt("checktype"));
					info.setPaystate(rs.getString("paystate"));
					info.setStateDate(rs.getTimestamp("StateDate"));
					list.add(info);

				}else{
				if(cp.getNum()==0){
					String caseno=rs.getString("CaseNo");
					String paystate=rs.getString("paystate");
					Timestamp  FacDate=rs.getTimestamp("StateDate");
					String reason=rs.getString("Reason");
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time1=format.format(date);
					String time2=format.format(FacDate);
					Long millionSeconds4 = format.parse(time1).getTime()-86400000*4L;
					Long millionSeconds5 = format.parse(time2).getTime();
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");
					if(millionSeconds5>millionSeconds4){
						if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							String reason1=rs.getString("Reason");
							if(!"".equals(reason1) && reason1 !=null ){
								if("Emma审批通过".equals(reason1)){
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}else{
								info.setReason(rs.getString("Reason"));
								info.setStatea(2);
								}
							}else{
								info.setStatea(1);
								info.setReason(rs.getString("Reason"));
							}

							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							list.add(info);

						}else if("<font color=blue>正在审批中</font>".equals(State)){
							if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

							double getmoney = 0;
							double payfactory = 0;
							double payother = 0;
							double paywulliu = 0;
							Connection conn1 = null;
							PreparedStatement stmt1 = null;
							ResultSet rs1 = null;
							String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
							conn1 = SQLDBhelper.getConnection();
							try {
								stmt1 = conn1.prepareStatement(sql1);
								stmt1.setString(1, caseno);
								rs1 = stmt1.executeQuery();
								if(rs1.next()) {
									getmoney = rs1.getDouble(1);
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
							String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setString(1, caseno);
								rs2= stmt2.executeQuery();
								if(rs2.next()) {
									payfactory = rs2.getDouble(1);
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
							String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
							conn3 = SQLDBhelper.getConnection();
							try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setString(1, caseno);
								rs3= stmt3.executeQuery();
								while(rs3.next()) {
									int type=rs3.getInt("moneytype");
									if(type==2){
									payother += rs3.getDouble(1);
									}else if(type==1){
									payother += rs3.getDouble(1)*6.8;
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
								SQLDBhelper.close(conn3,stmt3,rs3);

							}
							Connection conn4 = null;
							PreparedStatement stmt4 = null;
							ResultSet rs4 = null;
							String sql4 = "select * from 	wuliu  where CaseNo=? ";
							conn4 = SQLDBhelper.getConnection();
							try {
								stmt4 = conn3.prepareStatement(sql4);
								stmt4.setString(1, caseno);
								rs4= stmt4.executeQuery();
								while(rs4.next()) {
									int type=rs4.getInt("moneytype");
									if(type==2){
										paywulliu += rs4.getDouble(1);
									}else if(type==1){
										paywulliu += rs4.getDouble(1)*6.8;
									}
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

							if(getmoney*6.8-payfactory-payother-paywulliu>0){

								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);
							}
							}
							}else if("<font color=blur>已通过审批</font>".equals(State)){
								if("采购副总审批通过".equals(reason)){
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);
								}

							}



					}
				}else if(cp.getNum()==1){
					String reason=rs.getString("Reason");
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");
					String caseno=rs.getString("CaseNo");
					String paystate=rs.getString("paystate");
						Timestamp  FacDate=rs.getTimestamp("StateDate");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time1=format.format(date);
						String time2=format.format(FacDate);
						Long millionSeconds1 = format.parse(time1).getTime()-86400000*4L;
						Long millionSeconds2 = format.parse(time1).getTime()-86400000*10L;
						Long millionSeconds3 = format.parse(time2).getTime();
						if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
							if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);

							}else if("<font color=blue>正在审批中</font>".equals(State)){
								if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

								double getmoney = 0;
								double payfactory = 0;
								double payother = 0;
								double paywulliu = 0;
								Connection conn1 = null;
								PreparedStatement stmt1 = null;
								ResultSet rs1 = null;
								String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
								conn1 = SQLDBhelper.getConnection();
								try {
									stmt1 = conn1.prepareStatement(sql1);
									stmt1.setString(1, caseno);
									rs1 = stmt1.executeQuery();
									if(rs1.next()) {
										getmoney = rs1.getDouble(1);
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
								String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
								conn2 = SQLDBhelper.getConnection();
								try {
									stmt2 = conn2.prepareStatement(sql2);
									stmt2.setString(1, caseno);
									rs2= stmt2.executeQuery();
									if(rs2.next()) {
										payfactory = rs2.getDouble(1);
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
								String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
								conn3 = SQLDBhelper.getConnection();
								try {
									stmt3 = conn3.prepareStatement(sql3);
									stmt3.setString(1, caseno);
									rs3= stmt3.executeQuery();
									while(rs3.next()) {
										int type=rs3.getInt("moneytype");
										if(type==2){
										payother += rs3.getDouble(1);
										}else if(type==1){
										payother += rs3.getDouble(1)*6.8;
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
									SQLDBhelper.close(conn3,stmt3,rs3);

								}
								Connection conn4 = null;
								PreparedStatement stmt4 = null;
								ResultSet rs4 = null;
								String sql4 = "select * from 	wuliu  where CaseNo=? ";
								conn4 = SQLDBhelper.getConnection();
								try {
									stmt4 = conn3.prepareStatement(sql4);
									stmt4.setString(1, caseno);
									rs4= stmt4.executeQuery();
									while(rs4.next()) {
										int type=rs4.getInt("moneytype");
										if(type==2){
											paywulliu += rs4.getDouble(1);
										}else if(type==1){
											paywulliu += rs4.getDouble(1)*6.8;
										}
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

								if(getmoney*6.8-payfactory-payother-paywulliu>0){

										info.setId(rs.getInt("id"));
										info.setCaseNo(rs.getString("CaseNo"));
										info.setProductDescE(rs.getString("ProductDescE"));
										info.setProductDescC(rs.getString("ProductDescC"));

										info.setItemManager(rs.getString("ItemManager"));
										info.setState(rs.getInt("State1"));
										info.setFacMoney(rs.getInt("FacMoney"));
										info.setMtype(rs.getString("mtype"));
										info.setState1(rs.getString("State"));
										info.setFacDate(rs.getTimestamp("FacDate"));
										info.setFacReason(rs.getString("FacReason"));
										String reason1=rs.getString("Reason");
										if(!"".equals(reason1) && reason1 !=null ){
											if("Emma审批通过".equals(reason1)){
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}else{
											info.setReason(rs.getString("Reason"));
											info.setStatea(2);
											}
										}else{
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}
										info.setGeldObject(rs.getString("GeldObject"));
										info.setChecktype(rs.getInt("checktype"));
										info.setPaystate(rs.getString("paystate"));
										info.setStateDate(rs.getTimestamp("StateDate"));
										list.add(info);
									}
								}
									}else if("<font color=blur>已通过审批</font>".equals(State)){
										if("采购副总审批通过".equals(reason)){
											info.setId(rs.getInt("id"));
											info.setCaseNo(rs.getString("CaseNo"));
											info.setProductDescE(rs.getString("ProductDescE"));
											info.setProductDescC(rs.getString("ProductDescC"));

											info.setItemManager(rs.getString("ItemManager"));
											info.setState(rs.getInt("State1"));
											info.setFacMoney(rs.getInt("FacMoney"));
											info.setMtype(rs.getString("mtype"));
											info.setState1(rs.getString("State"));
											info.setFacDate(rs.getTimestamp("FacDate"));
											info.setFacReason(rs.getString("FacReason"));
											String reason1=rs.getString("Reason");
											if(!"".equals(reason1) && reason1 !=null ){
												if("Emma审批通过".equals(reason1)){
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}else{
												info.setReason(rs.getString("Reason"));
												info.setStatea(2);
												}
											}else{
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}
											info.setGeldObject(rs.getString("GeldObject"));
											info.setChecktype(rs.getInt("checktype"));
											info.setPaystate(rs.getString("paystate"));
											info.setStateDate(rs.getTimestamp("StateDate"));
											list.add(info);
											}


								}
							}


					}else if(cp.getNum()==2){
						String paystate=rs.getString("paystate");
						String caseno=rs.getString("CaseNo");
						String State=rs.getString("State");
						facReason=rs.getString("FacReason");
						int money=rs.getInt("FacMoney");
						String reason=rs.getString("Reason");
							Timestamp  FacDate=rs.getTimestamp("StateDate");
							Date date = new Date();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time1=format.format(date);
							String time2=format.format(FacDate);
							Long millionSeconds1 = format.parse(time1).getTime()-86400000*10L;
							Long millionSeconds2 = format.parse(time1).getTime()-86400000*20L;
							Long millionSeconds3 = format.parse(time2).getTime();
							if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
								if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									String reason1=rs.getString("Reason");
									if(!"".equals(reason1) && reason1 !=null ){
										if("Emma审批通过".equals(reason1)){
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}else{
										info.setReason(rs.getString("Reason"));
										info.setStatea(2);
										}
									}else{
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));
									list.add(info);

								}else if("<font color=blue>正在审批中</font>".equals(State)){
									if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

									double getmoney = 0;
									double payfactory = 0;
									double payother = 0;
									double paywulliu = 0;
									Connection conn1 = null;
									PreparedStatement stmt1 = null;
									ResultSet rs1 = null;
									String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
									conn1 = SQLDBhelper.getConnection();
									try {
										stmt1 = conn1.prepareStatement(sql1);
										stmt1.setString(1, caseno);
										rs1 = stmt1.executeQuery();
										if(rs1.next()) {
											getmoney = rs1.getDouble(1);
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
									String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
									conn2 = SQLDBhelper.getConnection();
									try {
										stmt2 = conn2.prepareStatement(sql2);
										stmt2.setString(1, caseno);
										rs2= stmt2.executeQuery();
										if(rs2.next()) {
											payfactory = rs2.getDouble(1);
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
									String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
									conn3 = SQLDBhelper.getConnection();
									try {
										stmt3 = conn3.prepareStatement(sql3);
										stmt3.setString(1, caseno);
										rs3= stmt3.executeQuery();
										while(rs3.next()) {
											int type=rs3.getInt("moneytype");
											if(type==2){
											payother += rs3.getDouble(1);
											}else if(type==1){
											payother += rs3.getDouble(1)*6.8;
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
										SQLDBhelper.close(conn3,stmt3,rs3);

									}
									Connection conn4 = null;
									PreparedStatement stmt4 = null;
									ResultSet rs4 = null;
									String sql4 = "select * from 	wuliu  where CaseNo=? ";
									conn4 = SQLDBhelper.getConnection();
									try {
										stmt4 = conn3.prepareStatement(sql4);
										stmt4.setString(1, caseno);
										rs4= stmt4.executeQuery();
										while(rs4.next()) {
											int type=rs4.getInt("moneytype");
											if(type==2){
												paywulliu += rs4.getDouble(1);
											}else if(type==1){
												paywulliu += rs4.getDouble(1)*6.8;
											}
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

									if(getmoney*6.8-payfactory-payother-paywulliu>0){

											info.setId(rs.getInt("id"));
											info.setCaseNo(rs.getString("CaseNo"));
											info.setProductDescE(rs.getString("ProductDescE"));
											info.setProductDescC(rs.getString("ProductDescC"));

											info.setItemManager(rs.getString("ItemManager"));
											info.setState(rs.getInt("State1"));
											info.setFacMoney(rs.getInt("FacMoney"));
											info.setMtype(rs.getString("mtype"));
											info.setState1(rs.getString("State"));
											info.setFacDate(rs.getTimestamp("FacDate"));
											info.setFacReason(rs.getString("FacReason"));
											String reason1=rs.getString("Reason");
											if(!"".equals(reason1) && reason1 !=null ){
												if("Emma审批通过".equals(reason1)){
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}else{
												info.setReason(rs.getString("Reason"));
												info.setStatea(2);
												}
											}else{
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}
											info.setGeldObject(rs.getString("GeldObject"));
											info.setChecktype(rs.getInt("checktype"));
											info.setPaystate(rs.getString("paystate"));
											info.setStateDate(rs.getTimestamp("StateDate"));
											list.add(info);
										}
									}
									}else if("<font color=blur>已通过审批</font>".equals(State)){
										if("采购副总审批通过".equals(reason)){
										info.setId(rs.getInt("id"));
										info.setCaseNo(rs.getString("CaseNo"));
										info.setProductDescE(rs.getString("ProductDescE"));
										info.setProductDescC(rs.getString("ProductDescC"));

										info.setItemManager(rs.getString("ItemManager"));
										info.setState(rs.getInt("State1"));
										info.setFacMoney(rs.getInt("FacMoney"));
										info.setMtype(rs.getString("mtype"));
										info.setState1(rs.getString("State"));
										info.setFacDate(rs.getTimestamp("FacDate"));
										info.setFacReason(rs.getString("FacReason"));
										String reason1=rs.getString("Reason");
										if(!"".equals(reason1) && reason1 !=null ){
											if("Emma审批通过".equals(reason1)){
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}else{
											info.setReason(rs.getString("Reason"));
											info.setStatea(2);
											}
										}else{
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}
										info.setGeldObject(rs.getString("GeldObject"));
										info.setChecktype(rs.getInt("checktype"));
										info.setPaystate(rs.getString("paystate"));
										info.setStateDate(rs.getTimestamp("StateDate"));
										list.add(info);
										}

									}


							}

						}else if(cp.getNum()==3){
							String caseno=rs.getString("CaseNo");
							String paystate=rs.getString("paystate");
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
							String reason=rs.getString("Reason");
								Timestamp  FacDate=rs.getTimestamp("StateDate");
								Date date = new Date();
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time1=format.format(date);
								String time2=format.format(FacDate);
								Long millionSeconds4 = format.parse(time1).getTime()-86400000*20L;
								Long millionSeconds5 = format.parse(time2).getTime();
								if(millionSeconds5<millionSeconds4){
									if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

										info.setId(rs.getInt("id"));
										info.setCaseNo(rs.getString("CaseNo"));
										info.setProductDescE(rs.getString("ProductDescE"));
										info.setProductDescC(rs.getString("ProductDescC"));

										info.setItemManager(rs.getString("ItemManager"));
										info.setState(rs.getInt("State1"));
										info.setFacMoney(rs.getInt("FacMoney"));
										info.setMtype(rs.getString("mtype"));
										info.setState1(rs.getString("State"));
										info.setFacDate(rs.getTimestamp("FacDate"));
										info.setFacReason(rs.getString("FacReason"));
										String reason1=rs.getString("Reason");
										if(!"".equals(reason1) && reason1 !=null ){
											if("Emma审批通过".equals(reason1)){
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}else{
											info.setReason(rs.getString("Reason"));
											info.setStatea(2);
											}
										}else{
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}
										info.setGeldObject(rs.getString("GeldObject"));
										info.setChecktype(rs.getInt("checktype"));
										info.setPaystate(rs.getString("paystate"));
										info.setStateDate(rs.getTimestamp("StateDate"));
										list.add(info);
									}else if("<font color=blue>正在审批中</font>".equals(State)){
										if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

										double getmoney = 0;
										double payfactory = 0;
										double payother = 0;
										double paywulliu = 0;
										Connection conn1 = null;
										PreparedStatement stmt1 = null;
										ResultSet rs1 = null;
										String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
										conn1 = SQLDBhelper.getConnection();
										try {
											stmt1 = conn1.prepareStatement(sql1);
											stmt1.setString(1, caseno);
											rs1 = stmt1.executeQuery();
											if(rs1.next()) {
												getmoney = rs1.getDouble(1);
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
										String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
										conn2 = SQLDBhelper.getConnection();
										try {
											stmt2 = conn2.prepareStatement(sql2);
											stmt2.setString(1, caseno);
											rs2= stmt2.executeQuery();
											if(rs2.next()) {
												payfactory = rs2.getDouble(1);
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
										String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
										conn3 = SQLDBhelper.getConnection();
										try {
											stmt3 = conn3.prepareStatement(sql3);
											stmt3.setString(1, caseno);
											rs3= stmt3.executeQuery();
											while(rs3.next()) {
												int type=rs3.getInt("moneytype");
												if(type==2){
												payother += rs3.getDouble(1);
												}else if(type==1){
												payother += rs3.getDouble(1)*6.8;
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
											SQLDBhelper.close(conn3,stmt3,rs3);

										}
										Connection conn4 = null;
										PreparedStatement stmt4 = null;
										ResultSet rs4 = null;
										String sql4 = "select * from 	wuliu  where CaseNo=? ";
										conn4 = SQLDBhelper.getConnection();
										try {
											stmt4 = conn3.prepareStatement(sql4);
											stmt4.setString(1, caseno);
											rs4= stmt4.executeQuery();
											while(rs4.next()) {
												int type=rs4.getInt("moneytype");
												if(type==2){
													paywulliu += rs4.getDouble(1);
												}else if(type==1){
													paywulliu += rs4.getDouble(1)*6.8;
												}
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

										if(getmoney*6.8-payfactory-payother-paywulliu>0){

												info.setId(rs.getInt("id"));
												info.setCaseNo(rs.getString("CaseNo"));
												info.setProductDescE(rs.getString("ProductDescE"));
												info.setProductDescC(rs.getString("ProductDescC"));

												info.setItemManager(rs.getString("ItemManager"));
												info.setState(rs.getInt("State1"));
												info.setFacMoney(rs.getInt("FacMoney"));
												info.setMtype(rs.getString("mtype"));
												info.setState1(rs.getString("State"));
												info.setFacDate(rs.getTimestamp("FacDate"));
												info.setFacReason(rs.getString("FacReason"));
												String reason1=rs.getString("Reason");
												if(!"".equals(reason1) && reason1 !=null ){
													if("Emma审批通过".equals(reason1)){
														info.setStatea(1);
														info.setReason(rs.getString("Reason"));
													}else{
													info.setReason(rs.getString("Reason"));
													info.setStatea(2);
													}
												}else{
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}
												info.setGeldObject(rs.getString("GeldObject"));
												info.setChecktype(rs.getInt("checktype"));
												info.setPaystate(rs.getString("paystate"));
												info.setStateDate(rs.getTimestamp("StateDate"));
												list.add(info);
											}
										}
										}else if("<font color=blur>已通过审批</font>".equals(State)){
											if("采购副总审批通过".equals(reason)){
											info.setId(rs.getInt("id"));
											info.setCaseNo(rs.getString("CaseNo"));
											info.setProductDescE(rs.getString("ProductDescE"));
											info.setProductDescC(rs.getString("ProductDescC"));

											info.setItemManager(rs.getString("ItemManager"));
											info.setState(rs.getInt("State1"));
											info.setFacMoney(rs.getInt("FacMoney"));
											info.setMtype(rs.getString("mtype"));
											info.setState1(rs.getString("State"));
											info.setFacDate(rs.getTimestamp("FacDate"));
											info.setFacReason(rs.getString("FacReason"));
											String reason1=rs.getString("Reason");
											if(!"".equals(reason1) && reason1 !=null ){
												if("Emma审批通过".equals(reason1)){
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}else{
												info.setReason(rs.getString("Reason"));
												info.setStatea(2);
												}
											}else{
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}
											info.setGeldObject(rs.getString("GeldObject"));
											info.setChecktype(rs.getInt("checktype"));
											info.setPaystate(rs.getString("paystate"));
											info.setStateDate(rs.getTimestamp("StateDate"));
											list.add(info);


										}
									}

							}
						}else{
							String reason=rs.getString("Reason");
							String caseno=rs.getString("CaseNo");
							String paystate=rs.getString("paystate");
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
							if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							String reason1=rs.getString("Reason");
							if(!"".equals(reason1) && reason1 !=null ){
								if("Emma审批通过".equals(reason1)){
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}else{
								info.setReason(rs.getString("Reason"));
								info.setStatea(2);
								}
							}else{
								info.setStatea(1);
								info.setReason(rs.getString("Reason"));
							}
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							list.add(info);

						}else if("<font color=blue>正在审批中</font>".equals(State)){
							if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

							double getmoney = 0;
							double payfactory = 0;
							double payother = 0;
							double paywulliu = 0;
							Connection conn1 = null;
							PreparedStatement stmt1 = null;
							ResultSet rs1 = null;
							String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
							conn1 = SQLDBhelper.getConnection();
							try {
								stmt1 = conn1.prepareStatement(sql1);
								stmt1.setString(1, caseno);
								rs1 = stmt1.executeQuery();
								if(rs1.next()) {
									getmoney = rs1.getDouble(1);
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
							String sql2 = "select sum(friMoney) from factoryfund  where CaseNo=?";
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setString(1, caseno);
								rs2= stmt2.executeQuery();
								if(rs2.next()) {
									payfactory = rs2.getDouble(1);
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
							String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
							conn3 = SQLDBhelper.getConnection();
							try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setString(1, caseno);
								rs3= stmt3.executeQuery();
								while(rs3.next()) {
									int type=rs3.getInt("moneytype");
									if(type==2){
									payother += rs3.getDouble(1);
									}else if(type==1){
									payother += rs3.getDouble(1)*6.8;
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
								SQLDBhelper.close(conn3,stmt3,rs3);

							}
							Connection conn4 = null;
							PreparedStatement stmt4 = null;
							ResultSet rs4 = null;
							String sql4 = "select * from 	wuliu  where CaseNo=? ";
							conn4 = SQLDBhelper.getConnection();
							try {
								stmt4 = conn4.prepareStatement(sql4);
								stmt4.setString(1, caseno);
								rs4= stmt4.executeQuery();
								while(rs4.next()) {
									int type=rs4.getInt("moneytype");
									if(type==2){
										paywulliu += rs4.getDouble(1);
									}else if(type==1){
										paywulliu += rs4.getDouble(1)*6.8;
									}
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

							if(getmoney*6.8-payfactory-payother-paywulliu>0){

									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									//String reason1=rs.getString("Reason");
									if(!"".equals(reason) && reason !=null ){
										if("Emma审批通过".equals(reason)){
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}else{
										info.setReason(rs.getString("Reason"));
										info.setStatea(2);
										}
									}else{
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));
									list.add(info);
								}
							}
							}else if("<font color=blur>已通过审批</font>".equals(State)){
								if("采购副总审批通过".equals(reason)){
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);
								}


						}

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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public int getTotalb(MoneyCheckUp cp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int preliminaryhearing(int id, String time) {
		Connection conn1 = null;
		int total=0;

		PreparedStatement stmt1 = null;
		ResultSet rs1 = null;
		String sql1 = "update MoneyCheckUp set StateDate=?,State=?,Reason=? where id = ? and state !='<font color=green>已完成款项</font>' ";
		conn1 = SQLDBhelper.getConnection();
		try {
			stmt1 = conn1.prepareStatement(sql1);
			stmt1.setString(1, time);
			stmt1.setString(2,"<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>");
			stmt1.setString(3, "Emma审批通过");
			stmt1.setInt(4, id);
			total = stmt1.executeUpdate();
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
			//DBHelper.returnConnection(conn);
		}
		Connection conn2 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		int result2=0;
		String sql2 = "update FactoryFund set State =?, statetime = ? , checkReason = ?  where  ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
		conn2 = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt2 = conn2.prepareStatement(sql2);
			stmt2.setString(1,"<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>");
			stmt2.setString(2, time);

			stmt2.setString(3, "Emma审批通过");
			stmt2.setInt(4, id);
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
			//DBHelper.returnConnection(conn);
		}
		Connection conn3 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs3 = null;
		int result3=0;
		String sql3 = "update Wuliu SET State = ?,WuliuReason = ? WHERE ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
		conn3 = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt3 = conn3.prepareStatement(sql3);
			stmt3.setString(1,"<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>");
			stmt3.setString(2, "Emma审批通过");
			stmt3.setInt(3, id);
			result3 = stmt3.executeUpdate();
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
			if (rs3!= null) {
				try {
					rs3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SQLDBhelper.close(conn3,stmt3,rs3);
			//DBHelper.returnConnection(conn);
		}

		return total;
	}

	@Override
	public int directapproval(int id1, String time) {
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where  a.[id]=?  and a.state !='<font color=green>已完成款项</font>' ";

		int id=0;
		int total=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int money=0;
		conn = SQLDBhelper.getConnection();
		try {
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id1);
		rs = stmt.executeQuery();
			if(rs.next()) {

				id=rs.getInt("id");

					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					int result1=0;
					String sql1 = "update MoneyCheckUp set StateDate=?,State=?,Reason=? where id = ?   ";
					conn1 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, time);
						stmt1.setString(2,"<font color=blur>已通过审批</font>");
						stmt1.setString(3, "Emma审批通过");
						stmt1.setInt(4, id);
						total = stmt1.executeUpdate();
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn2 = null;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					int result2=0;
					String sql2 = "update FactoryFund set State =?, statetime = ? , checkReason = ?  where  ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn2 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setString(1, "<font color=blur>已通过审批</font>");
						stmt2.setString(2, time);
						stmt2.setString(3, "Emma审批通过");
						stmt2.setInt(4, id);
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					ResultSet rs3 = null;
					int result3=0;
					String sql3 = "update Wuliu SET State = ?,WuliuReason = ? WHERE ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn3 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setString(1, "<font color=blur>已通过审批</font>");
						stmt3.setString(2, "Emma审批通过");
						stmt3.setInt(3, id);
						result3 = stmt3.executeUpdate();
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
						if (rs3!= null) {
							try {
								rs3.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn3,stmt3,rs3);
						//DBHelper.returnConnection(conn);
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public List<MoneyCheckUp> getAllm(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 2 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.Reason ='采购副总审批通过'  ";

		int i=0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE = ? or b.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				if(cp.getNum()==0){
					Timestamp  FacDate=rs.getTimestamp("StateDate");
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time1=format.format(date);
					String time2=format.format(FacDate);
					Long millionSeconds4 = format.parse(time1).getTime()-86400000*4L;
					Long millionSeconds5 = format.parse(time2).getTime();
					String State=rs.getString("State");
					if(millionSeconds5>millionSeconds4){

						info.setId(rs.getInt("id"));
						info.setCaseNo(rs.getString("CaseNo"));
						info.setProductDescE(rs.getString("ProductDescE"));
						info.setProductDescC(rs.getString("ProductDescC"));
						info.setCustomerManager(rs.getString("CustomerManager"));
						info.setMerchandManager1(rs.getString("MerchandManager1"));
						info.setMerchandManager2(rs.getString("MerchandManager2"));
						info.setEngineer1(rs.getString("Engineer1"));
						info.setEngineer2(rs.getString("Engineer2"));
						info.setEngineer3(rs.getString("Engineer3"));
						info.setItemManager(rs.getString("ItemManager"));
						info.setState(rs.getInt("State1"));
						info.setFacMoney(rs.getInt("FacMoney"));
						info.setMtype(rs.getString("mtype"));
						info.setState1(rs.getString("State"));
						info.setFacDate(rs.getTimestamp("FacDate"));
						info.setFacReason(rs.getString("FacReason"));
						info.setReason(rs.getString("Reason"));
						info.setGeldObject(rs.getString("GeldObject"));
						info.setChecktype(rs.getInt("checktype"));
						info.setPaystate(rs.getString("paystate"));
						info.setStateDate(rs.getTimestamp("StateDate"));

						list.add(info);

					}
				}else if(cp.getNum()==1){
					  String State=rs.getString("State");
						Timestamp  FacDate=rs.getTimestamp("StateDate");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time1=format.format(date);
						String time2=format.format(FacDate);
						Long millionSeconds1 = format.parse(time1).getTime()-86400000*4L;
						Long millionSeconds2 = format.parse(time1).getTime()-86400000*10L;
						Long millionSeconds3 = format.parse(time2).getTime();
						if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){

							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));
							info.setCustomerManager(rs.getString("CustomerManager"));
							info.setMerchandManager1(rs.getString("MerchandManager1"));
							info.setMerchandManager2(rs.getString("MerchandManager2"));
							info.setEngineer1(rs.getString("Engineer1"));
							info.setEngineer2(rs.getString("Engineer2"));
							info.setEngineer3(rs.getString("Engineer3"));
							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));

							list.add(info);

						}

					}else if(cp.getNum()==2){
						String State=rs.getString("State");
							Timestamp  FacDate=rs.getTimestamp("StateDate");
							Date date = new Date();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time1=format.format(date);
							String time2=format.format(FacDate);
							Long millionSeconds1 = format.parse(time1).getTime()-86400000*10L;
							Long millionSeconds2 = format.parse(time1).getTime()-86400000*20L;
							Long millionSeconds3 = format.parse(time2).getTime();
							if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){

								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));
								info.setCustomerManager(rs.getString("CustomerManager"));
								info.setMerchandManager1(rs.getString("MerchandManager1"));
								info.setMerchandManager2(rs.getString("MerchandManager2"));
								info.setEngineer1(rs.getString("Engineer1"));
								info.setEngineer2(rs.getString("Engineer2"));
								info.setEngineer3(rs.getString("Engineer3"));
								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								info.setReason(rs.getString("Reason"));
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));

								list.add(info);

							}

						}else if(cp.getNum()==3){
							String State=  rs.getString("State");
								Timestamp  FacDate=rs.getTimestamp("StateDate");
								Date date = new Date();
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time1=format.format(date);
								String time2=format.format(FacDate);
								Long millionSeconds4 = format.parse(time1).getTime()-86400000*20L;
								Long millionSeconds5 = format.parse(time2).getTime();
								if(millionSeconds5<millionSeconds4){

									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));
									info.setCustomerManager(rs.getString("CustomerManager"));
									info.setMerchandManager1(rs.getString("MerchandManager1"));
									info.setMerchandManager2(rs.getString("MerchandManager2"));
									info.setEngineer1(rs.getString("Engineer1"));
									info.setEngineer2(rs.getString("Engineer2"));
									info.setEngineer3(rs.getString("Engineer3"));
									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									info.setReason(rs.getString("Reason"));
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));

									list.add(info);

							}
						}else{
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));
							info.setCustomerManager(rs.getString("CustomerManager"));
							info.setMerchandManager1(rs.getString("MerchandManager1"));
							info.setMerchandManager2(rs.getString("MerchandManager2"));
							info.setEngineer1(rs.getString("Engineer1"));
							info.setEngineer2(rs.getString("Engineer2"));
							info.setEngineer3(rs.getString("Engineer3"));
							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));

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
			SQLDBhelper.returnConnection(conn);
		}
		return list;

	}

	@Override
	public int getTotalm(MoneyCheckUp cp) {
		String sql = "select count(*) from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.Reason ='采购副总审批通过'  ";

		int total=0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {

		stmt = conn.prepareStatement(sql);


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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int directapproval1(int id1, String time) {
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('正在审批中',a.State)>0"
				+ " then 1 when CHARINDEX('已通过审批',a.State)>0 then 2 when CHARINDEX('未通过审批',a.State)>0 "
				+ "then 3 else 4 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where  a.[id]=? and a.state !='<font color=green>已完成款项</font>' ";

		int id=0;
		int total=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int money=0;
		conn = SQLDBhelper.getConnection();
		try {
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id1);
		rs = stmt.executeQuery();
			if(rs.next()) {

				id=rs.getInt("id");

					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					int result1=0;
					String sql1 = "update MoneyCheckUp set StateDate=?,State=?,Reason=? where id = ?  ";
					conn1 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, time);
						stmt1.setString(2,"<font color=blur>已通过审批</font>");
						stmt1.setString(3, "采购副总审批通过");
						stmt1.setInt(4, id);
						total = stmt1.executeUpdate();
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn2 = null;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					int result2=0;
					String sql2 = "update FactoryFund set State =?, statetime = ? , checkReason = ?  where  ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn2 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setString(1, "<font color=blur>已通过审批</font>");
						stmt2.setString(2, time);
						stmt2.setString(3, "采购副总审批通过");
						stmt2.setInt(4, id);
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
						//DBHelper.returnConnection(conn);
					}
					Connection conn3 = null;
					PreparedStatement stmt3 = null;
					ResultSet rs3 = null;
					int result3=0;
					String sql3 = "update Wuliu SET State = ?,WuliuReason = ? WHERE ApNumber = (SELECT ApNumber FROM MoneyCheckUp WHERE id = ?) ";
					conn3 = SQLDBhelper.getConnection();
					//conn = DBHelper.getConnection();
					try {
						stmt3 = conn3.prepareStatement(sql3);
						stmt3.setString(1, "<font color=blur>已通过审批</font>");
						stmt3.setString(2, "采购副总审批通过");
						stmt3.setInt(3, id);
						result3 = stmt3.executeUpdate();
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
						if (rs3!= null) {
							try {
								rs3.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conn3,stmt3,rs3);
						//DBHelper.returnConnection(conn);
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public List<MoneyCheckUp> getAll2a(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select * from(select  a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,d.Customerremarks,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,b.Merchandising, "
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 2 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate,a.approvalTime,a.ReasonsApproval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo"
				+ "  left join Customer d on d.id=b.customercode "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>' and a.FacMoney!=0 "
				+ "union "
				+"select  a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,d.Customerremarks,"
				+" b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,b.Merchandising, "
				+" b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<font color=green>已完成款项</font>',a.State)>0"
				+" then 5 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 6    else 7 end),a.FacMoney,a.State,"
				+" a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+" a.StateDate,a.approvalTime,a.ReasonsApproval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo"
				+"   left join Customer d on d.id=b.customercode "
				+" where (a.state = '<font color=green>已完成款项</font>' "
				+"  or a.state = '<font color=red>未通过审批</font>') and a.FacMoney!=0"
				+"  and (express_lane_approval=0 or express_lane_approval=2) and express_lane=1"
				+")a";
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		sql += " order by express_lane desc,State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				double iimoney1=0.0;
				String  CaseNo=rs.getString("CaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					String	date=rs1.getString("iidate");
					if(date!=null&&!"".equals(date)){
					imoneytype1=rs1.getInt("imoneytype");
					i=imoneytype1;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Calendar cal = Calendar.getInstance();
					    Date dt = null;
					    try {
					      dt = sdf.parse(date);
					      cal.setTime(dt);
					    } catch (Exception e) {
					      e.printStackTrace();
					    }
					    int year = cal.get(Calendar.YEAR);
					    int month = cal.get(Calendar.MONTH) + 1;
						double huilv=0.0;
						String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						conn2 = SQLDBhelper.getConnection();
						try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setInt(1, year);
						stmt2.setInt(2, month);
						stmt2.setInt(3, imoneytype1);
						rs2 = stmt2.executeQuery();
							if(rs2.next()) {
								huilv=rs2.getDouble(1);
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
							SQLDBhelper.returnConnection(conn2);
						}
						if(!"".equals(huilv)&&huilv!=0){
							if(imoneytype1==1){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}else if(imoneytype1==2){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney");
							}else{
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}
						}else{
							huilv=1;
							if(imoneytype1==1){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}else if(imoneytype1==2){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney");
							}else{
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}
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
					SQLDBhelper.returnConnection(conn1);
				}
				Double frimoney=0.0;
				String sql3 = "select sum(frimoney) from factoryfund where caseno=?";
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				conn3 = SQLDBhelper.getConnection();
				try {
				stmt3 = conn3.prepareStatement(sql3);
				stmt3.setString(1, CaseNo);
				rs3 = stmt3.executeQuery();
					if(rs3.next()) {
						frimoney=rs3.getDouble(1);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs3 != null) {
						try {
							rs3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt3 != null) {
						try {
							stmt3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn3);
				}
                int moneytype=0;
				int moneytype2=0;
				double OtherMoney=0.0;
				String sql4 = "select moneytype,OtherMoney,friFacDate from FactoryFund where CaseNo=? and OtherMoney is not null";
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				conn4 = SQLDBhelper.getConnection();
				try {
				stmt4 = conn4.prepareStatement(sql4);
				stmt4.setString(1, CaseNo);
				rs4 = stmt4.executeQuery();
					while(rs4.next()) {
						moneytype2=rs4.getInt("moneytype");

						String	date=rs4.getString("friFacDate");
							if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 Calendar cal = Calendar.getInstance();
						    Date dt = null;
						    try {
						      dt = sdf.parse(date);
						      cal.setTime(dt);
						    } catch (Exception e) {
						      // TODO Auto-generated catch block
						      e.printStackTrace();
						    }
						    int year = cal.get(Calendar.YEAR);
						    int month = cal.get(Calendar.MONTH) + 1;
						    double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
							stmt2 = conn2.prepareStatement(sql2);
							stmt2.setInt(1, year);
							stmt2.setInt(2, month);
							stmt2.setInt(3, moneytype2);
							rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							OtherMoney+=rs4.getDouble("OtherMoney")*huilv;
						}else if(moneytype2==2){
							OtherMoney+=rs4.getDouble("OtherMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs4 != null) {
						try {
							rs4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt4 != null) {
						try {
							stmt4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn4);
				}
				int moneyType3=0;
				double WuliuMoney=0.0;
				String sql5 = "select moneyType,WuliuMoney,wuliudate from Wuliu where CaseNo=?";
				Connection conn5 = null;
				PreparedStatement stmt5 = null;
				ResultSet rs5 = null;
				conn5 = SQLDBhelper.getConnection();
				try {
				stmt5 = conn5.prepareStatement(sql5);
				stmt5.setString(1, CaseNo);
				rs5 = stmt5.executeQuery();
					while(rs5.next()) {
						moneyType3=rs5.getInt("moneyType");

							String	date=rs5.getString("wuliudate");
							if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 Calendar cal = Calendar.getInstance();
						    Date dt = null;
						    try {
						      dt = sdf.parse(date);
						      cal.setTime(dt);
						    } catch (Exception e) {
						      // TODO Auto-generated catch block
						      e.printStackTrace();
						    }
						    int year = cal.get(Calendar.YEAR);
						    int month = cal.get(Calendar.MONTH) + 1;
						    double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
							stmt2 = conn2.prepareStatement(sql2);
							stmt2.setInt(1, year);
							stmt2.setInt(2, month);
							stmt2.setInt(3, moneyType3);
							rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							if(huilv!=0&&!"".equals(huilv)){
							WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}else{
								huilv=1;
								WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}
						}else if(moneyType3==2){
							WuliuMoney+=rs5.getDouble("WuliuMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs5 != null) {
						try {
							rs5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt5 != null) {
						try {
							stmt5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn5);
				}
				 double  profitmargin=0.0;
				 String profitmargin1="";
				 double   f2=0.0;
				 double   f =0.0;
				 double   f1 =0.0;
				if(ifmoney!=0&&!"".equals(ifmoney)){
				 BigDecimal   b   =   new   BigDecimal(ifmoney);
					   f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney!=0&&!"".equals(iimoney)){
					BigDecimal   b1   =   new   BigDecimal(iimoney);
					  f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney==0){
				}else{
			   profitmargin=((iimoney1-frimoney-OtherMoney-WuliuMoney)/iimoney1)*100;
			   BigDecimal   b2   =   new   BigDecimal(profitmargin);
				   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}

				MoneyCheckUp info =  new MoneyCheckUp();
				info.setMerchandising(rs.getString("Merchandising"));
			    info.setMerchandManager1(rs.getString("MerchandManager1"));
			    info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setId(rs.getInt("id"));
				info.setCaseNo(rs.getString("CaseNo"));
				info.setProductDescE(rs.getString("ProductDescE"));
				info.setProductDescC(rs.getString("ProductDescC"));
				info.setExpressLane(rs.getInt("express_lane"));
				info.setFastTrackReasons(rs.getString("fast_track_reasons"));
				info.setItemManager(rs.getString("ItemManager"));
				info.setState(rs.getInt("State1"));
				info.setFacMoney(rs.getInt("FacMoney"));
				info.setMtype(rs.getString("mtype"));
				info.setState1(rs.getString("State"));
				info.setFacDate(rs.getTimestamp("FacDate"));
				info.setFacReason(rs.getString("FacReason"));
				info.setReason(rs.getString("Reason"));
				info.setGeldObject(rs.getString("GeldObject"));
				info.setChecktype(rs.getInt("checktype"));
				info.setPaystate(rs.getString("paystate"));
				info.setStateDate(rs.getTimestamp("StateDate"));
				info.setAmount(f1);
				info.setSumacount(f);
				info.setCustomerremarks(rs.getString("Customerremarks"));
				info.setProjectnote(rs.getString("projectnote"));
				info.setProfit(f2+"%");
				info.setMoneytype(i);
				info.setApprovalTime(rs.getString("approvalTime"));
				info.setReasonsApproval(rs.getString("reasonsApproval"));
				info.setFastTrackReasons(rs.getString("fast_track_reasons"));
				info.setExpressLane(rs.getInt("express_lane"));
				info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
				String type=rs.getString("mtype");
				if("人民币".equals(type)){
					info.setMoneytype1(2);
				}else if("RMB".equals(type)){
					info.setMoneytype1(2);
				}
				if("美金".equals(type)){
					info.setMoneytype1(1);
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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public List<MoneyCheckUp> getproject(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select * from (select  a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,d.Customerremarks,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,b.Merchandising, "
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 2 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate,a.approvalTime,a.ReasonsApproval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo"
				+ "  left join Customer d on d.id=b.customercode "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>' "
				+ "and (MerchandManager1=? or   CustomerManager=? or   MerchandManager2=? or  Engineer1=? or   Engineer2=? or Engineer3=? or Merchandising=?) and a.FacMoney!=0"
				+ "union "
				+"select  a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,d.Customerremarks,"
				+" b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,b.Merchandising, "
				+" b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<font color=green>已完成款项</font>',a.State)>0"
				+" then 5 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 6    else 7 end),a.FacMoney,a.State,"
				+" a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+" a.StateDate,a.approvalTime,a.ReasonsApproval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo"
				+"   left join Customer d on d.id=b.customercode "
				+" where (a.state = '<font color=green>已完成款项</font>' "
				+"  or a.state = '<font color=red>未通过审批</font>') and a.FacMoney!=0"
				+"  and (express_lane_approval=0 or express_lane_approval=2) and express_lane=1"
				+ "and (MerchandManager1=? or   CustomerManager=? or   MerchandManager2=? or  Engineer1=? or   Engineer2=? or Engineer3=? or Merchandising=?) and a.FacMoney!=0"
				+")a";

				;
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
		sql += " order by express_lane desc, State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cp.getMerchandManager1());
			stmt.setString(2, cp.getMerchandManager1());
			stmt.setString(3, cp.getMerchandManager1());
			stmt.setString(4, cp.getMerchandManager1());
			stmt.setString(5, cp.getMerchandManager1());
			stmt.setString(6, cp.getMerchandManager1());
			stmt.setString(7, cp.getMerchandManager1());
			stmt.setString(8, cp.getMerchandManager1());
			stmt.setString(9, cp.getMerchandManager1());
			stmt.setString(10, cp.getMerchandManager1());
			stmt.setString(11, cp.getMerchandManager1());
			stmt.setString(12, cp.getMerchandManager1());
			stmt.setString(13, cp.getMerchandManager1());
			stmt.setString(14, cp.getMerchandManager1());
			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				double iimoney1=0.0;
				String  CaseNo=rs.getString("CaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
				stmt1 = conn1.prepareStatement(sql1);
				stmt1.setString(1, CaseNo);
				rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					String	date=rs1.getString("iidate");
					if(date!=null&&!"".equals(date)){
					imoneytype1=rs1.getInt("imoneytype");
					i=imoneytype1;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 Calendar cal = Calendar.getInstance();
					    Date dt = null;
					    try {
					      dt = sdf.parse(date);
					      cal.setTime(dt);
					    } catch (Exception e) {
					      e.printStackTrace();
					    }
					    int year = cal.get(Calendar.YEAR);
					    int month = cal.get(Calendar.MONTH) + 1;
						double huilv=0.0;
						String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						conn2 = SQLDBhelper.getConnection();
						try {
						stmt2 = conn2.prepareStatement(sql2);
						stmt2.setInt(1, year);
						stmt2.setInt(2, month);
						stmt2.setInt(3, imoneytype1);
						rs2 = stmt2.executeQuery();
							if(rs2.next()) {
								huilv=rs2.getDouble(1);
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
							SQLDBhelper.returnConnection(conn2);
						}
						if(!"".equals(huilv)&&huilv!=0){
							if(imoneytype1==1){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}else if(imoneytype1==2){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney");
							}else{
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}
						}else{
							huilv=1;
							if(imoneytype1==1){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}else if(imoneytype1==2){
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney");
							}else{
								ifmoney+=rs1.getDouble("ifmoney");
								iimoney+=rs1.getDouble("iimoney");
								iimoney1+=rs1.getDouble("iimoney")*huilv;
							}
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
					SQLDBhelper.returnConnection(conn1);
				}
				Double frimoney=0.0;
				String sql3 = "select sum(frimoney) from factoryfund where caseno=?";
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				conn3 = SQLDBhelper.getConnection();
				try {
				stmt3 = conn3.prepareStatement(sql3);
				stmt3.setString(1, CaseNo);
				rs3 = stmt3.executeQuery();
					if(rs3.next()) {
						frimoney=rs3.getDouble(1);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs3 != null) {
						try {
							rs3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt3 != null) {
						try {
							stmt3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn3);
				}
                int moneytype=0;
				int moneytype2=0;
				double OtherMoney=0.0;
				String sql4 = "select moneytype,OtherMoney,friFacDate from FactoryFund where CaseNo=? and OtherMoney is not null";
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				conn4 = SQLDBhelper.getConnection();
				try {
				stmt4 = conn4.prepareStatement(sql4);
				stmt4.setString(1, CaseNo);
				rs4 = stmt4.executeQuery();
					while(rs4.next()) {
						moneytype2=rs4.getInt("moneytype");
                        moneytype=moneytype2;
						String	date=rs4.getString("friFacDate");
							if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 Calendar cal = Calendar.getInstance();
						    Date dt = null;
						    try {
						      dt = sdf.parse(date);
						      cal.setTime(dt);
						    } catch (Exception e) {
						      // TODO Auto-generated catch block
						      e.printStackTrace();
						    }
						    int year = cal.get(Calendar.YEAR);
						    int month = cal.get(Calendar.MONTH) + 1;
						    double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
							stmt2 = conn2.prepareStatement(sql2);
							stmt2.setInt(1, year);
							stmt2.setInt(2, month);
							stmt2.setInt(3, moneytype2);
							rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							OtherMoney+=rs4.getDouble("OtherMoney")*huilv;
						}else if(moneytype2==2){
							OtherMoney+=rs4.getDouble("OtherMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs4 != null) {
						try {
							rs4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt4 != null) {
						try {
							stmt4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn4);
				}
				int moneyType3=0;
				double WuliuMoney=0.0;
				String sql5 = "select moneyType,WuliuMoney,wuliudate from Wuliu where CaseNo=?";
				Connection conn5 = null;
				PreparedStatement stmt5 = null;
				ResultSet rs5 = null;
				conn5 = SQLDBhelper.getConnection();
				try {
				stmt5 = conn5.prepareStatement(sql5);
				stmt5.setString(1, CaseNo);
				rs5 = stmt5.executeQuery();
					while(rs5.next()) {
						moneyType3=rs5.getInt("moneyType");
                        moneytype=moneyType3;
							String	date=rs5.getString("wuliudate");
							if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 Calendar cal = Calendar.getInstance();
						    Date dt = null;
						    try {
						      dt = sdf.parse(date);
						      cal.setTime(dt);
						    } catch (Exception e) {
						      // TODO Auto-generated catch block
						      e.printStackTrace();
						    }
						    int year = cal.get(Calendar.YEAR);
						    int month = cal.get(Calendar.MONTH) + 1;
						    double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
							stmt2 = conn2.prepareStatement(sql2);
							stmt2.setInt(1, year);
							stmt2.setInt(2, month);
							stmt2.setInt(3, moneyType3);
							rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							if(huilv!=0&&!"".equals(huilv)){
							WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}else{
								huilv=1;
								WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}
						}else if(moneyType3==2){
							WuliuMoney+=rs5.getDouble("WuliuMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs5 != null) {
						try {
							rs5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt5 != null) {
						try {
							stmt5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn5);
				}
				 double  profitmargin=0.0;
				 String profitmargin1="";
				 double   f2=0.0;
				 double   f =0.0;
				 double   f1 =0.0;
				if(ifmoney!=0&&!"".equals(ifmoney)){
				 BigDecimal   b   =   new   BigDecimal(ifmoney);
					   f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney!=0&&!"".equals(iimoney)){
					BigDecimal   b1   =   new   BigDecimal(iimoney);
					  f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney==0){
				}else{
			   profitmargin=((iimoney1-frimoney-OtherMoney-WuliuMoney)/iimoney1)*100;
			   BigDecimal   b2   =   new   BigDecimal(profitmargin);
				   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setMerchandising(rs.getString("Merchandising"));
			    info.setMerchandManager1(rs.getString("MerchandManager1"));
			    info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setId(rs.getInt("id"));
				info.setCaseNo(rs.getString("CaseNo"));
				info.setProductDescE(rs.getString("ProductDescE"));
				info.setProductDescC(rs.getString("ProductDescC"));

				info.setItemManager(rs.getString("ItemManager"));
				info.setState(rs.getInt("State1"));
				info.setFacMoney(rs.getInt("FacMoney"));
				info.setMtype(rs.getString("mtype"));
				info.setState1(rs.getString("State"));
				info.setFacDate(rs.getTimestamp("FacDate"));
				info.setFacReason(rs.getString("FacReason"));
				info.setReason(rs.getString("Reason"));
				info.setGeldObject(rs.getString("GeldObject"));
				info.setChecktype(rs.getInt("checktype"));
				info.setPaystate(rs.getString("paystate"));
				info.setStateDate(rs.getTimestamp("StateDate"));
				info.setAmount(f1);
				info.setSumacount(f);
				info.setCustomerremarks(rs.getString("Customerremarks"));
				info.setProjectnote(rs.getString("projectnote"));
				info.setProfit(f2+"%");
				info.setMoneytype(i);
				info.setApprovalTime(rs.getString("approvalTime"));
				info.setReasonsApproval(rs.getString("reasonsApproval"));
				info.setFastTrackReasons(rs.getString("fast_track_reasons"));
				info.setExpressLane(rs.getInt("express_lane"));
				info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
				String type=rs.getString("mtype");
				if("人民币".equals(type)){
					info.setMoneytype1(2);
				}else if("RMB".equals(type)){
					info.setMoneytype1(2);
				}
				if("美金".equals(type)){
					info.setMoneytype1(1);
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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public int getprojecttotal(MoneyCheckUp cp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEmTotal(MoneyCheckUp cp) {
		String sql = "select a.FacMoney from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>' and a.state != '<font color=blue>正在审批中</font>'"
				+ "and a.state !='<font color=blur>已通过审批</font>' ";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;

		conn = SQLDBhelper.getConnection();
		try {



			stmt = conn.prepareStatement(sql);


			rs = stmt.executeQuery();

			while(rs.next()) {
				total+=rs.getInt("FacMoney");
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int getEdTotal(MoneyCheckUp cp) {
		String sql = "select a.FacMoney from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>' and a.state != '<font color=blue>正在审批中</font>'"
				+ "and a.state !='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>' ";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;

		conn = SQLDBhelper.getConnection();
		try {



			stmt = conn.prepareStatement(sql);


			rs = stmt.executeQuery();

			while(rs.next()) {
				total+=rs.getInt("FacMoney");
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int getAllTotal(MoneyCheckUp cp) {
		String sql = "select a.FacMoney from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' "
				+ "and a.state != '<font color=red>未通过审批</font>' and a.state != '<font color=blur>已通过审批</font>'"
				+ "and a.state !='<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>' and datediff(day,a.StateDate,getdate())<4 ";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;

		conn = SQLDBhelper.getConnection();
		try {



			stmt = conn.prepareStatement(sql);


			rs = stmt.executeQuery();

			while(rs.next()) {
				total+=rs.getInt("FacMoney");
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public int getState(int id) {
		String sql = "select count(1) from MoneyCheckUp  "
				+ "where state = '<font color=green>已完成款项</font>' "
				+ "and id=? ";

		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;

		conn = SQLDBhelper.getConnection();
		try {



			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) {
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
			SQLDBhelper.returnConnection(conn);
		}
		return total;
	}

	@Override
	public List<MoneyCheckUp> getAllx(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 2 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate,a.approval,a.approvalTime,a.ReasonsApproval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' and a.state != '<font color=blur>已通过审批</font>' and a.state != '<font color=red>未通过审批</font>' and a.FacMoney!=0 and datediff(day,a.StateDate,getdate())>=30   ";

		int i=0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE = ? or b.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			sql += " order by State1 , a.StateDate desc,a.approval ";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				String  CustomerManager=rs.getString("CustomerManager");
				String  MerchandManager1=rs.getString("MerchandManager1");
				String  MerchandManager2=rs.getString("MerchandManager2");
				String  Engineer1=rs.getString("Engineer1");
				String  Engineer2=rs.getString("Engineer2");
				String  Engineer3=rs.getString("Engineer3");
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
				if(cp.getNum()==0){
					Timestamp  FacDate=rs.getTimestamp("StateDate");
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time1=format.format(date);
					String time2=format.format(FacDate);
					Long millionSeconds4 = format.parse(time1).getTime()-86400000*4L;
					Long millionSeconds5 = format.parse(time2).getTime();
					String State=rs.getString("State");
					if(millionSeconds5>millionSeconds4){
						if("<font color=blue>正在审批中</font>".equals(State)){
						info.setId(rs.getInt("id"));
						info.setCaseNo(rs.getString("CaseNo"));
						info.setProductDescE(rs.getString("ProductDescE"));
						info.setProductDescC(rs.getString("ProductDescC"));

						info.setItemManager(rs.getString("ItemManager"));
						info.setState(rs.getInt("State1"));
						info.setFacMoney(rs.getInt("FacMoney"));
						info.setMtype(rs.getString("mtype"));
						info.setState1(rs.getString("State"));
						info.setFacDate(rs.getTimestamp("FacDate"));
						info.setFacReason(rs.getString("FacReason"));
						info.setReason(rs.getString("Reason"));
						info.setGeldObject(rs.getString("GeldObject"));
						info.setChecktype(rs.getInt("checktype"));
						info.setPaystate(rs.getString("paystate"));
						info.setStateDate(rs.getTimestamp("StateDate"));

						list.add(info);
						}
					}
				}else if(cp.getNum()==1){
					  String State=rs.getString("State");
						Timestamp  FacDate=rs.getTimestamp("StateDate");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time1=format.format(date);
						String time2=format.format(FacDate);
						Long millionSeconds1 = format.parse(time1).getTime()-86400000*4L;
						Long millionSeconds2 = format.parse(time1).getTime()-86400000*10L;
						Long millionSeconds3 = format.parse(time2).getTime();
						if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
							if("<font color=blue>正在审批中</font>".equals(State)){
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));

							list.add(info);
							}
						}

					}else if(cp.getNum()==2){
						String State=rs.getString("State");
							Timestamp  FacDate=rs.getTimestamp("StateDate");
							Date date = new Date();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time1=format.format(date);
							String time2=format.format(FacDate);
							Long millionSeconds1 = format.parse(time1).getTime()-86400000*10L;
							Long millionSeconds2 = format.parse(time1).getTime()-86400000*20L;
							Long millionSeconds3 = format.parse(time2).getTime();
							if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
								if("<font color=blue>正在审批中</font>".equals(State)){
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								info.setReason(rs.getString("Reason"));
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));

								list.add(info);
								}
							}

						}else if(cp.getNum()==3){
							String State=  rs.getString("State");
								Timestamp  FacDate=rs.getTimestamp("StateDate");
								Date date = new Date();
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time1=format.format(date);
								String time2=format.format(FacDate);
								Long millionSeconds4 = format.parse(time1).getTime()-86400000*20L;
								Long millionSeconds5 = format.parse(time2).getTime();
								if(millionSeconds5<millionSeconds4){
									if("<font color=blue>正在审批中</font>".equals(State)){
									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									info.setReason(rs.getString("Reason"));
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));

									list.add(info);
									}
							}
						}else{
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));

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
			SQLDBhelper.returnConnection(conn);
		}
		return list;

	}

	@Override
	public List<MoneyCheckUp> getAlly(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,c.Customerremarks,a.pressformoney,b.GrossProfit,b.LoginDate,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 2 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 1 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate,a.approvalTime,a.ReasonsApproval,a.approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "left join Customer as c  on c.id=b.customercode "
				+ "where a.state != '<font color=green>已完成款项</font>'   and a.state != '<font color=blur>已通过审批</font>' and   "
				+ "a.state != '<font color=red>未通过审批</font>' and a.FacMoney!=0  and datediff(day,a.StateDate,getdate())>=30 ";

		int i=0;
		String facReason="";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE = ? or b.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			sql += " order by State1 , a.StateDate desc,a.approval ";
			stmt = conn.prepareStatement(sql);

			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setProfit(rs.getString("GrossProfit"));
				info.setLoginDate(rs.getString("LoginDate"));
				String  CustomerManager=rs.getString("CustomerManager");
				String  MerchandManager1=rs.getString("MerchandManager1");
				String  MerchandManager2=rs.getString("MerchandManager2");
				String  Engineer1=rs.getString("Engineer1");
				String  Engineer2=rs.getString("Engineer2");
				String  Engineer3=rs.getString("Engineer3");
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
				info.setPressformoney(rs.getInt("pressformoney"));
				info.setApprovalTime(rs.getString("approvalTime"));
				info.setReasonsApproval(rs.getString("reasonsApproval"));
				if(cp.getNum()==0){
					Timestamp  FacDate=rs.getTimestamp("StateDate");
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time1=format.format(date);
					String time2=format.format(FacDate);
					Long millionSeconds4 = format.parse(time1).getTime()-86400000*4L;
					Long millionSeconds5 = format.parse(time2).getTime();
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");
					if(millionSeconds5>millionSeconds4){
						if("<font color=blue>正在审批中</font>".equals(State)){
							String s1 = "运费";
						Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
							if(index1 !=false){
								info.setStatea(2);
							}else{
								if(money<10000){
								info.setStatea(1);
								}else{
								info.setStatea(0);
								}
							}
						info.setId(rs.getInt("id"));
						info.setCaseNo(rs.getString("CaseNo"));
						info.setProductDescE(rs.getString("ProductDescE"));
						info.setProductDescC(rs.getString("ProductDescC"));

						info.setItemManager(rs.getString("ItemManager"));
						info.setState(rs.getInt("State1"));
						info.setFacMoney(rs.getInt("FacMoney"));
						info.setMtype(rs.getString("mtype"));
						info.setState1(rs.getString("State"));
						info.setFacDate(rs.getTimestamp("FacDate"));
						info.setFacReason(rs.getString("FacReason"));
						info.setReason(rs.getString("Reason"));
						info.setGeldObject(rs.getString("GeldObject"));
						info.setChecktype(rs.getInt("checktype"));
						info.setPaystate(rs.getString("paystate"));
						info.setStateDate(rs.getTimestamp("StateDate"));
						info.setCustomerremarks(rs.getString("Customerremarks"));
						info.setProjectnote(rs.getString("projectnote"));
						list.add(info);
						}
					}
				}else if(cp.getNum()==1){
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");


						Timestamp  FacDate=rs.getTimestamp("StateDate");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time1=format.format(date);
						String time2=format.format(FacDate);
						Long millionSeconds1 = format.parse(time1).getTime()-86400000*4L;
						Long millionSeconds2 = format.parse(time1).getTime()-86400000*10L;
						Long millionSeconds3 = format.parse(time2).getTime();
						if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
							if("<font color=blue>正在审批中</font>".equals(State)){
								String s1 = "运费";
								Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
									if(index1 !=false){
										info.setStatea(2);
									}else{
										if(money<10000){
										info.setStatea(1);
										}else{
										info.setStatea(0);
										}
									}
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							info.setCustomerremarks(rs.getString("Customerremarks"));
							info.setProjectnote(rs.getString("projectnote"));
							list.add(info);
							}
						}

					}else if(cp.getNum()==2){
						String State=rs.getString("State");
						facReason=rs.getString("FacReason");
						int money=rs.getInt("FacMoney");

							Timestamp  FacDate=rs.getTimestamp("StateDate");
							Date date = new Date();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time1=format.format(date);
							String time2=format.format(FacDate);
							Long millionSeconds1 = format.parse(time1).getTime()-86400000*10L;
							Long millionSeconds2 = format.parse(time1).getTime()-86400000*20L;
							Long millionSeconds3 = format.parse(time2).getTime();
							if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
								if("<font color=blue>正在审批中</font>".equals(State)){
									String s1 = "运费";
									Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
										if(index1 !=false){
											info.setStatea(2);
										}else{
											if(money<10000){
											info.setStatea(1);
											}else{
											info.setStatea(0);
											}
										}
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								info.setReason(rs.getString("Reason"));
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								info.setCustomerremarks(rs.getString("Customerremarks"));
								info.setProjectnote(rs.getString("projectnote"));
								list.add(info);
								}
							}

						}else if(cp.getNum()==3){
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
								Timestamp  FacDate=rs.getTimestamp("StateDate");
								Date date = new Date();
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time1=format.format(date);
								String time2=format.format(FacDate);
								Long millionSeconds4 = format.parse(time1).getTime()-86400000*20L;
								Long millionSeconds5 = format.parse(time2).getTime();
								if(millionSeconds5<millionSeconds4){
									if("<font color=blue>正在审批中</font>".equals(State)){
										String s1 = "运费";
										Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
											if(index1 !=false){
												info.setStatea(2);
											}else{
												if(money<10000){
												info.setStatea(1);
												}else{
												info.setStatea(0);
												}
											}
									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									info.setReason(rs.getString("Reason"));
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));
									info.setCustomerremarks(rs.getString("Customerremarks"));
									info.setProjectnote(rs.getString("projectnote"));
									list.add(info);
									}
							}
						}else{
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
							if("<font color=blur>已通过审批</font>".equals(State)||"<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){
								info.setStatea(3);
							}else{
							String s1 = "运费";
							Boolean	index1 = facReason.toLowerCase().contains(s1.toLowerCase());
								if(index1 !=false){
									info.setStatea(2);
								}else{
									if(money<10000){
									info.setStatea(1);
									}else{
									info.setStatea(0);
									}
								}
							}
							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							info.setReason(rs.getString("Reason"));
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							info.setCustomerremarks(rs.getString("Customerremarks"));
							info.setProjectnote(rs.getString("projectnote"));
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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public List<MoneyCheckUp> getAllz(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select a.paystate,a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,"
				+ "b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,"
				+ "b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>',a.State)>0"
				+ " then 1 when CHARINDEX('<font color=blur>已通过审批</font>',a.State)>0 then 3 when CHARINDEX('<font color=blue>正在审批中</font>',a.State)>0 "
				+ "then 2 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 4 else 5 end),a.FacMoney,a.State,"
				+ "a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+ "a.StateDate from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo "
				+ "where a.state != '<font color=green>已完成款项</font>' and "
				+ "a.state != '<font color=red>未通过审批</font>' and a.state != '<font color=blur>已通过审批</font>' "
				+ "and  a.FacMoney<5000  and a.FacMoney!=0   and datediff(day,a.StateDate,getdate())>=30 and a.approval=0";

		int i=0;
		String facReason="";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		conn = SQLDBhelper.getConnection();
		try {
		if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
			sql +=" and a.CaseNo like ? ";
		}
		if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
			sql +=" and b.ProductDescE = ? or b.ProductDescC = ?";
		}
		if(cp.getFacMoney() != 0) {
			sql +=" and a.FacMoney= ?";
		}
		if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
			sql +=" and a.GeldObject=?";
		}
		if(cp.getChecktype() != -1) {
			if(cp.getChecktype() == 0) {
				sql +=" and a.FacReason like '%首付款%'";
			}else if(cp.getChecktype() == 1) {
				sql +=" and a.FacReason like '%需垫付%'";
			}else if(cp.getChecktype() == 2) {
				sql +=" and a.FacReason like '%运费%'";
			}else if(cp.getChecktype() == 3) {
				sql +=" and a.FacReason like '%尾款%'";
			}

		}


			sql += " order by State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);

			if(cp.getCaseNo() != null && !"".equals(cp.getCaseNo())) {
				i++;
				stmt.setString(i, "%"+cp.getCaseNo()+"%");
			}
			if(cp.getProductDescE() != null && !"".equals(cp.getProductDescE())) {
				i++;
				stmt.setString(i, cp.getProductDescE());
				stmt.setString(i+1, cp.getProductDescE());
			}
			if(cp.getFacMoney() != 0) {
				i++;
				stmt.setInt(i, cp.getFacMoney());
			}
			if(cp.getGeldObject() != null && !"".equals(cp.getGeldObject())) {
				i++;
				stmt.setString(i, cp.getGeldObject());

			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				MoneyCheckUp info =  new MoneyCheckUp();
				String  CustomerManager=rs.getString("CustomerManager");
				String  MerchandManager1=rs.getString("MerchandManager1");
				String  MerchandManager2=rs.getString("MerchandManager2");
				String  Engineer1=rs.getString("Engineer1");
				String  Engineer2=rs.getString("Engineer2");
				String  Engineer3=rs.getString("Engineer3");
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
				if(cp.getNum()==0){
					String caseno=rs.getString("CaseNo");
					String paystate=rs.getString("paystate");
					Timestamp  FacDate=rs.getTimestamp("StateDate");
					String reason=rs.getString("Reason");
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time1=format.format(date);
					String time2=format.format(FacDate);
					Long millionSeconds4 = format.parse(time1).getTime()-86400000*4L;
					Long millionSeconds5 = format.parse(time2).getTime();
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");
					if(millionSeconds5>millionSeconds4){
						if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							String reason1=rs.getString("Reason");
							if(!"".equals(reason1) && reason1 !=null ){
								if("Emma审批通过".equals(reason1)){
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}else{
								info.setReason(rs.getString("Reason"));
								info.setStatea(2);
								}
							}else{
								info.setStatea(1);
								info.setReason(rs.getString("Reason"));
							}

							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							list.add(info);

						}else if("<font color=blue>正在审批中</font>".equals(State)){
							if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

							double getmoney = 0;
							double payfactory = 0;
							double payother = 0;
							double paywulliu = 0;
							Connection conn1 = null;
							PreparedStatement stmt1 = null;
							ResultSet rs1 = null;
							String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
							conn1 = SQLDBhelper.getConnection();
							try {
								stmt1 = conn1.prepareStatement(sql1);
								stmt1.setString(1, caseno);
								rs1 = stmt1.executeQuery();
								if(rs1.next()) {
									getmoney = rs1.getDouble(1);
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
							String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setString(1, caseno);
								rs2= stmt2.executeQuery();
								if(rs2.next()) {
									payfactory = rs2.getDouble(1);
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
							String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
							conn3 = SQLDBhelper.getConnection();
							try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setString(1, caseno);
								rs3= stmt3.executeQuery();
								while(rs3.next()) {
									int type=rs3.getInt("moneytype");
									if(type==2){
									payother += rs3.getDouble(1);
									}else if(type==1){
									payother += rs3.getDouble(1)*6.8;
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
								SQLDBhelper.close(conn3,stmt3,rs3);

							}
							Connection conn4 = null;
							PreparedStatement stmt4 = null;
							ResultSet rs4 = null;
							String sql4 = "select * from 	wuliu  where CaseNo=? ";
							conn4 = SQLDBhelper.getConnection();
							try {
								stmt4 = conn3.prepareStatement(sql4);
								stmt4.setString(1, caseno);
								rs4= stmt4.executeQuery();
								while(rs4.next()) {
									int type=rs4.getInt("moneytype");
									if(type==2){
										paywulliu += rs4.getDouble(1);
									}else if(type==1){
										paywulliu += rs4.getDouble(1)*6.8;
									}
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

							if(getmoney*6.8-payfactory-payother-paywulliu>0){

								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);
							}
							}
							}else if("<font color=blur>已通过审批</font>".equals(State)){
								if("采购副总审批通过".equals(reason)){
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);
								}

							}



					}
				}else if(cp.getNum()==1){
					String reason=rs.getString("Reason");
					String State=rs.getString("State");
					facReason=rs.getString("FacReason");
					int money=rs.getInt("FacMoney");
					String caseno=rs.getString("CaseNo");
					String paystate=rs.getString("paystate");
						Timestamp  FacDate=rs.getTimestamp("StateDate");
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time1=format.format(date);
						String time2=format.format(FacDate);
						Long millionSeconds1 = format.parse(time1).getTime()-86400000*4L;
						Long millionSeconds2 = format.parse(time1).getTime()-86400000*10L;
						Long millionSeconds3 = format.parse(time2).getTime();
						if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
							if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);

							}else if("<font color=blue>正在审批中</font>".equals(State)){
								if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

								double getmoney = 0;
								double payfactory = 0;
								double payother = 0;
								double paywulliu = 0;
								Connection conn1 = null;
								PreparedStatement stmt1 = null;
								ResultSet rs1 = null;
								String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
								conn1 = SQLDBhelper.getConnection();
								try {
									stmt1 = conn1.prepareStatement(sql1);
									stmt1.setString(1, caseno);
									rs1 = stmt1.executeQuery();
									if(rs1.next()) {
										getmoney = rs1.getDouble(1);
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
								String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
								conn2 = SQLDBhelper.getConnection();
								try {
									stmt2 = conn2.prepareStatement(sql2);
									stmt2.setString(1, caseno);
									rs2= stmt2.executeQuery();
									if(rs2.next()) {
										payfactory = rs2.getDouble(1);
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
								String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
								conn3 = SQLDBhelper.getConnection();
								try {
									stmt3 = conn3.prepareStatement(sql3);
									stmt3.setString(1, caseno);
									rs3= stmt3.executeQuery();
									while(rs3.next()) {
										int type=rs3.getInt("moneytype");
										if(type==2){
										payother += rs3.getDouble(1);
										}else if(type==1){
										payother += rs3.getDouble(1)*6.8;
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
									SQLDBhelper.close(conn3,stmt3,rs3);

								}
								Connection conn4 = null;
								PreparedStatement stmt4 = null;
								ResultSet rs4 = null;
								String sql4 = "select * from 	wuliu  where CaseNo=? ";
								conn4 = SQLDBhelper.getConnection();
								try {
									stmt4 = conn3.prepareStatement(sql4);
									stmt4.setString(1, caseno);
									rs4= stmt4.executeQuery();
									while(rs4.next()) {
										int type=rs4.getInt("moneytype");
										if(type==2){
											paywulliu += rs4.getDouble(1);
										}else if(type==1){
											paywulliu += rs4.getDouble(1)*6.8;
										}
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

								if(getmoney*6.8-payfactory-payother-paywulliu>0){

										info.setId(rs.getInt("id"));
										info.setCaseNo(rs.getString("CaseNo"));
										info.setProductDescE(rs.getString("ProductDescE"));
										info.setProductDescC(rs.getString("ProductDescC"));

										info.setItemManager(rs.getString("ItemManager"));
										info.setState(rs.getInt("State1"));
										info.setFacMoney(rs.getInt("FacMoney"));
										info.setMtype(rs.getString("mtype"));
										info.setState1(rs.getString("State"));
										info.setFacDate(rs.getTimestamp("FacDate"));
										info.setFacReason(rs.getString("FacReason"));
										String reason1=rs.getString("Reason");
										if(!"".equals(reason1) && reason1 !=null ){
											if("Emma审批通过".equals(reason1)){
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}else{
											info.setReason(rs.getString("Reason"));
											info.setStatea(2);
											}
										}else{
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}
										info.setGeldObject(rs.getString("GeldObject"));
										info.setChecktype(rs.getInt("checktype"));
										info.setPaystate(rs.getString("paystate"));
										info.setStateDate(rs.getTimestamp("StateDate"));
										list.add(info);
									}
								}
									}else if("<font color=blur>已通过审批</font>".equals(State)){
										if("采购副总审批通过".equals(reason)){
											info.setId(rs.getInt("id"));
											info.setCaseNo(rs.getString("CaseNo"));
											info.setProductDescE(rs.getString("ProductDescE"));
											info.setProductDescC(rs.getString("ProductDescC"));

											info.setItemManager(rs.getString("ItemManager"));
											info.setState(rs.getInt("State1"));
											info.setFacMoney(rs.getInt("FacMoney"));
											info.setMtype(rs.getString("mtype"));
											info.setState1(rs.getString("State"));
											info.setFacDate(rs.getTimestamp("FacDate"));
											info.setFacReason(rs.getString("FacReason"));
											String reason1=rs.getString("Reason");
											if(!"".equals(reason1) && reason1 !=null ){
												if("Emma审批通过".equals(reason1)){
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}else{
												info.setReason(rs.getString("Reason"));
												info.setStatea(2);
												}
											}else{
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}
											info.setGeldObject(rs.getString("GeldObject"));
											info.setChecktype(rs.getInt("checktype"));
											info.setPaystate(rs.getString("paystate"));
											info.setStateDate(rs.getTimestamp("StateDate"));
											list.add(info);
											}


								}
							}


					}else if(cp.getNum()==2){
						String paystate=rs.getString("paystate");
						String caseno=rs.getString("CaseNo");
						String State=rs.getString("State");
						facReason=rs.getString("FacReason");
						int money=rs.getInt("FacMoney");
						String reason=rs.getString("Reason");
							Timestamp  FacDate=rs.getTimestamp("StateDate");
							Date date = new Date();
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time1=format.format(date);
							String time2=format.format(FacDate);
							Long millionSeconds1 = format.parse(time1).getTime()-86400000*10L;
							Long millionSeconds2 = format.parse(time1).getTime()-86400000*20L;
							Long millionSeconds3 = format.parse(time2).getTime();
							if(millionSeconds3>millionSeconds2&&millionSeconds3<millionSeconds1){
								if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									String reason1=rs.getString("Reason");
									if(!"".equals(reason1) && reason1 !=null ){
										if("Emma审批通过".equals(reason1)){
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}else{
										info.setReason(rs.getString("Reason"));
										info.setStatea(2);
										}
									}else{
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));
									list.add(info);

								}else if("<font color=blue>正在审批中</font>".equals(State)){
									if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

									double getmoney = 0;
									double payfactory = 0;
									double payother = 0;
									double paywulliu = 0;
									Connection conn1 = null;
									PreparedStatement stmt1 = null;
									ResultSet rs1 = null;
									String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
									conn1 = SQLDBhelper.getConnection();
									try {
										stmt1 = conn1.prepareStatement(sql1);
										stmt1.setString(1, caseno);
										rs1 = stmt1.executeQuery();
										if(rs1.next()) {
											getmoney = rs1.getDouble(1);
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
									String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
									conn2 = SQLDBhelper.getConnection();
									try {
										stmt2 = conn2.prepareStatement(sql2);
										stmt2.setString(1, caseno);
										rs2= stmt2.executeQuery();
										if(rs2.next()) {
											payfactory = rs2.getDouble(1);
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
									String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
									conn3 = SQLDBhelper.getConnection();
									try {
										stmt3 = conn3.prepareStatement(sql3);
										stmt3.setString(1, caseno);
										rs3= stmt3.executeQuery();
										while(rs3.next()) {
											int type=rs3.getInt("moneytype");
											if(type==2){
											payother += rs3.getDouble(1);
											}else if(type==1){
											payother += rs3.getDouble(1)*6.8;
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
										SQLDBhelper.close(conn3,stmt3,rs3);

									}
									Connection conn4 = null;
									PreparedStatement stmt4 = null;
									ResultSet rs4 = null;
									String sql4 = "select * from 	wuliu  where CaseNo=? ";
									conn4 = SQLDBhelper.getConnection();
									try {
										stmt4 = conn3.prepareStatement(sql4);
										stmt4.setString(1, caseno);
										rs4= stmt4.executeQuery();
										while(rs4.next()) {
											int type=rs4.getInt("moneytype");
											if(type==2){
												paywulliu += rs4.getDouble(1);
											}else if(type==1){
												paywulliu += rs4.getDouble(1)*6.8;
											}
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

									if(getmoney*6.8-payfactory-payother-paywulliu>0){

											info.setId(rs.getInt("id"));
											info.setCaseNo(rs.getString("CaseNo"));
											info.setProductDescE(rs.getString("ProductDescE"));
											info.setProductDescC(rs.getString("ProductDescC"));

											info.setItemManager(rs.getString("ItemManager"));
											info.setState(rs.getInt("State1"));
											info.setFacMoney(rs.getInt("FacMoney"));
											info.setMtype(rs.getString("mtype"));
											info.setState1(rs.getString("State"));
											info.setFacDate(rs.getTimestamp("FacDate"));
											info.setFacReason(rs.getString("FacReason"));
											String reason1=rs.getString("Reason");
											if(!"".equals(reason1) && reason1 !=null ){
												if("Emma审批通过".equals(reason1)){
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}else{
												info.setReason(rs.getString("Reason"));
												info.setStatea(2);
												}
											}else{
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}
											info.setGeldObject(rs.getString("GeldObject"));
											info.setChecktype(rs.getInt("checktype"));
											info.setPaystate(rs.getString("paystate"));
											info.setStateDate(rs.getTimestamp("StateDate"));
											list.add(info);
										}
									}
									}else if("<font color=blur>已通过审批</font>".equals(State)){
										if("采购副总审批通过".equals(reason)){
										info.setId(rs.getInt("id"));
										info.setCaseNo(rs.getString("CaseNo"));
										info.setProductDescE(rs.getString("ProductDescE"));
										info.setProductDescC(rs.getString("ProductDescC"));

										info.setItemManager(rs.getString("ItemManager"));
										info.setState(rs.getInt("State1"));
										info.setFacMoney(rs.getInt("FacMoney"));
										info.setMtype(rs.getString("mtype"));
										info.setState1(rs.getString("State"));
										info.setFacDate(rs.getTimestamp("FacDate"));
										info.setFacReason(rs.getString("FacReason"));
										String reason1=rs.getString("Reason");
										if(!"".equals(reason1) && reason1 !=null ){
											if("Emma审批通过".equals(reason1)){
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}else{
											info.setReason(rs.getString("Reason"));
											info.setStatea(2);
											}
										}else{
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}
										info.setGeldObject(rs.getString("GeldObject"));
										info.setChecktype(rs.getInt("checktype"));
										info.setPaystate(rs.getString("paystate"));
										info.setStateDate(rs.getTimestamp("StateDate"));
										list.add(info);
										}

									}


							}

						}else if(cp.getNum()==3){
							String caseno=rs.getString("CaseNo");
							String paystate=rs.getString("paystate");
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
							String reason=rs.getString("Reason");
								Timestamp  FacDate=rs.getTimestamp("StateDate");
								Date date = new Date();
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String time1=format.format(date);
								String time2=format.format(FacDate);
								Long millionSeconds4 = format.parse(time1).getTime()-86400000*20L;
								Long millionSeconds5 = format.parse(time2).getTime();
								if(millionSeconds5<millionSeconds4){
									if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

										info.setId(rs.getInt("id"));
										info.setCaseNo(rs.getString("CaseNo"));
										info.setProductDescE(rs.getString("ProductDescE"));
										info.setProductDescC(rs.getString("ProductDescC"));

										info.setItemManager(rs.getString("ItemManager"));
										info.setState(rs.getInt("State1"));
										info.setFacMoney(rs.getInt("FacMoney"));
										info.setMtype(rs.getString("mtype"));
										info.setState1(rs.getString("State"));
										info.setFacDate(rs.getTimestamp("FacDate"));
										info.setFacReason(rs.getString("FacReason"));
										String reason1=rs.getString("Reason");
										if(!"".equals(reason1) && reason1 !=null ){
											if("Emma审批通过".equals(reason1)){
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}else{
											info.setReason(rs.getString("Reason"));
											info.setStatea(2);
											}
										}else{
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}
										info.setGeldObject(rs.getString("GeldObject"));
										info.setChecktype(rs.getInt("checktype"));
										info.setPaystate(rs.getString("paystate"));
										info.setStateDate(rs.getTimestamp("StateDate"));
										list.add(info);
									}else if("<font color=blue>正在审批中</font>".equals(State)){
										if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

										double getmoney = 0;
										double payfactory = 0;
										double payother = 0;
										double paywulliu = 0;
										Connection conn1 = null;
										PreparedStatement stmt1 = null;
										ResultSet rs1 = null;
										String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
										conn1 = SQLDBhelper.getConnection();
										try {
											stmt1 = conn1.prepareStatement(sql1);
											stmt1.setString(1, caseno);
											rs1 = stmt1.executeQuery();
											if(rs1.next()) {
												getmoney = rs1.getDouble(1);
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
										String sql2 = "select sum(firMoney) from factoryfund  where CaseNo=?";
										conn2 = SQLDBhelper.getConnection();
										try {
											stmt2 = conn2.prepareStatement(sql2);
											stmt2.setString(1, caseno);
											rs2= stmt2.executeQuery();
											if(rs2.next()) {
												payfactory = rs2.getDouble(1);
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
										String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
										conn3 = SQLDBhelper.getConnection();
										try {
											stmt3 = conn3.prepareStatement(sql3);
											stmt3.setString(1, caseno);
											rs3= stmt3.executeQuery();
											while(rs3.next()) {
												int type=rs3.getInt("moneytype");
												if(type==2){
												payother += rs3.getDouble(1);
												}else if(type==1){
												payother += rs3.getDouble(1)*6.8;
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
											SQLDBhelper.close(conn3,stmt3,rs3);

										}
										Connection conn4 = null;
										PreparedStatement stmt4 = null;
										ResultSet rs4 = null;
										String sql4 = "select * from 	wuliu  where CaseNo=? ";
										conn4 = SQLDBhelper.getConnection();
										try {
											stmt4 = conn3.prepareStatement(sql4);
											stmt4.setString(1, caseno);
											rs4= stmt4.executeQuery();
											while(rs4.next()) {
												int type=rs4.getInt("moneytype");
												if(type==2){
													paywulliu += rs4.getDouble(1);
												}else if(type==1){
													paywulliu += rs4.getDouble(1)*6.8;
												}
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

										if(getmoney*6.8-payfactory-payother-paywulliu>0){

												info.setId(rs.getInt("id"));
												info.setCaseNo(rs.getString("CaseNo"));
												info.setProductDescE(rs.getString("ProductDescE"));
												info.setProductDescC(rs.getString("ProductDescC"));

												info.setItemManager(rs.getString("ItemManager"));
												info.setState(rs.getInt("State1"));
												info.setFacMoney(rs.getInt("FacMoney"));
												info.setMtype(rs.getString("mtype"));
												info.setState1(rs.getString("State"));
												info.setFacDate(rs.getTimestamp("FacDate"));
												info.setFacReason(rs.getString("FacReason"));
												String reason1=rs.getString("Reason");
												if(!"".equals(reason1) && reason1 !=null ){
													if("Emma审批通过".equals(reason1)){
														info.setStatea(1);
														info.setReason(rs.getString("Reason"));
													}else{
													info.setReason(rs.getString("Reason"));
													info.setStatea(2);
													}
												}else{
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}
												info.setGeldObject(rs.getString("GeldObject"));
												info.setChecktype(rs.getInt("checktype"));
												info.setPaystate(rs.getString("paystate"));
												info.setStateDate(rs.getTimestamp("StateDate"));
												list.add(info);
											}
										}
										}else if("<font color=blur>已通过审批</font>".equals(State)){
											if("采购副总审批通过".equals(reason)){
											info.setId(rs.getInt("id"));
											info.setCaseNo(rs.getString("CaseNo"));
											info.setProductDescE(rs.getString("ProductDescE"));
											info.setProductDescC(rs.getString("ProductDescC"));

											info.setItemManager(rs.getString("ItemManager"));
											info.setState(rs.getInt("State1"));
											info.setFacMoney(rs.getInt("FacMoney"));
											info.setMtype(rs.getString("mtype"));
											info.setState1(rs.getString("State"));
											info.setFacDate(rs.getTimestamp("FacDate"));
											info.setFacReason(rs.getString("FacReason"));
											String reason1=rs.getString("Reason");
											if(!"".equals(reason1) && reason1 !=null ){
												if("Emma审批通过".equals(reason1)){
													info.setStatea(1);
													info.setReason(rs.getString("Reason"));
												}else{
												info.setReason(rs.getString("Reason"));
												info.setStatea(2);
												}
											}else{
												info.setStatea(1);
												info.setReason(rs.getString("Reason"));
											}
											info.setGeldObject(rs.getString("GeldObject"));
											info.setChecktype(rs.getInt("checktype"));
											info.setPaystate(rs.getString("paystate"));
											info.setStateDate(rs.getTimestamp("StateDate"));
											list.add(info);


										}
									}

							}
						}else{
							String reason=rs.getString("Reason");
							String caseno=rs.getString("CaseNo");
							String paystate=rs.getString("paystate");
							String State=rs.getString("State");
							facReason=rs.getString("FacReason");
							int money=rs.getInt("FacMoney");
							if("<img src=images/Face/59.gif align=absmiddle>  <font color=blue>正在审批中</font>".equals(State)){

							info.setId(rs.getInt("id"));
							info.setCaseNo(rs.getString("CaseNo"));
							info.setProductDescE(rs.getString("ProductDescE"));
							info.setProductDescC(rs.getString("ProductDescC"));

							info.setItemManager(rs.getString("ItemManager"));
							info.setState(rs.getInt("State1"));
							info.setFacMoney(rs.getInt("FacMoney"));
							info.setMtype(rs.getString("mtype"));
							info.setState1(rs.getString("State"));
							info.setFacDate(rs.getTimestamp("FacDate"));
							info.setFacReason(rs.getString("FacReason"));
							String reason1=rs.getString("Reason");
							if(!"".equals(reason1) && reason1 !=null ){
								if("Emma审批通过".equals(reason1)){
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}else{
								info.setReason(rs.getString("Reason"));
								info.setStatea(2);
								}
							}else{
								info.setStatea(1);
								info.setReason(rs.getString("Reason"));
							}
							info.setGeldObject(rs.getString("GeldObject"));
							info.setChecktype(rs.getInt("checktype"));
							info.setPaystate(rs.getString("paystate"));
							info.setStateDate(rs.getTimestamp("StateDate"));
							list.add(info);

						}else if("<font color=blue>正在审批中</font>".equals(State)){
							if(!"".equals(paystate)&&!"垫付，客户钱马上到".equals(paystate)){

							double getmoney = 0;
							double payfactory = 0;
							double payother = 0;
							double paywulliu = 0;
							Connection conn1 = null;
							PreparedStatement stmt1 = null;
							ResultSet rs1 = null;
							String sql1 = "select sum(iimoney) from invoiceinfo  where iCaseNo=?";
							conn1 = SQLDBhelper.getConnection();
							try {
								stmt1 = conn1.prepareStatement(sql1);
								stmt1.setString(1, caseno);
								rs1 = stmt1.executeQuery();
								if(rs1.next()) {
									getmoney = rs1.getDouble(1);
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
							String sql2 = "select sum(friMoney) from factoryfund  where CaseNo=?";
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setString(1, caseno);
								rs2= stmt2.executeQuery();
								if(rs2.next()) {
									payfactory = rs2.getDouble(1);
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
							String sql3 = "select * from factoryfund  where CaseNo=?  and otherMoney is not null";
							conn3 = SQLDBhelper.getConnection();
							try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setString(1, caseno);
								rs3= stmt3.executeQuery();
								while(rs3.next()) {
									int type=rs3.getInt("moneytype");
									if(type==2){
									payother += rs3.getDouble(1);
									}else if(type==1){
									payother += rs3.getDouble(1)*6.8;
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
								SQLDBhelper.close(conn3,stmt3,rs3);

							}
							Connection conn4 = null;
							PreparedStatement stmt4 = null;
							ResultSet rs4 = null;
							String sql4 = "select * from 	wuliu  where CaseNo=? ";
							conn4 = SQLDBhelper.getConnection();
							try {
								stmt4 = conn4.prepareStatement(sql4);
								stmt4.setString(1, caseno);
								rs4= stmt4.executeQuery();
								while(rs4.next()) {
									int type=rs4.getInt("moneytype");
									if(type==2){
										paywulliu += rs4.getDouble(1);
									}else if(type==1){
										paywulliu += rs4.getDouble(1)*6.8;
									}
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

							if(getmoney*6.8-payfactory-payother-paywulliu>0){

									info.setId(rs.getInt("id"));
									info.setCaseNo(rs.getString("CaseNo"));
									info.setProductDescE(rs.getString("ProductDescE"));
									info.setProductDescC(rs.getString("ProductDescC"));

									info.setItemManager(rs.getString("ItemManager"));
									info.setState(rs.getInt("State1"));
									info.setFacMoney(rs.getInt("FacMoney"));
									info.setMtype(rs.getString("mtype"));
									info.setState1(rs.getString("State"));
									info.setFacDate(rs.getTimestamp("FacDate"));
									info.setFacReason(rs.getString("FacReason"));
									//String reason1=rs.getString("Reason");
									if(!"".equals(reason) && reason !=null ){
										if("Emma审批通过".equals(reason)){
											info.setStatea(1);
											info.setReason(rs.getString("Reason"));
										}else{
										info.setReason(rs.getString("Reason"));
										info.setStatea(2);
										}
									}else{
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}
									info.setGeldObject(rs.getString("GeldObject"));
									info.setChecktype(rs.getInt("checktype"));
									info.setPaystate(rs.getString("paystate"));
									info.setStateDate(rs.getTimestamp("StateDate"));
									list.add(info);
								}
							}
							}else if("<font color=blur>已通过审批</font>".equals(State)){
								if("采购副总审批通过".equals(reason)){
								info.setId(rs.getInt("id"));
								info.setCaseNo(rs.getString("CaseNo"));
								info.setProductDescE(rs.getString("ProductDescE"));
								info.setProductDescC(rs.getString("ProductDescC"));

								info.setItemManager(rs.getString("ItemManager"));
								info.setState(rs.getInt("State1"));
								info.setFacMoney(rs.getInt("FacMoney"));
								info.setMtype(rs.getString("mtype"));
								info.setState1(rs.getString("State"));
								info.setFacDate(rs.getTimestamp("FacDate"));
								info.setFacReason(rs.getString("FacReason"));
								String reason1=rs.getString("Reason");
								if(!"".equals(reason1) && reason1 !=null ){
									if("Emma审批通过".equals(reason1)){
										info.setStatea(1);
										info.setReason(rs.getString("Reason"));
									}else{
									info.setReason(rs.getString("Reason"));
									info.setStatea(2);
									}
								}else{
									info.setStatea(1);
									info.setReason(rs.getString("Reason"));
								}
								info.setGeldObject(rs.getString("GeldObject"));
								info.setChecktype(rs.getInt("checktype"));
								info.setPaystate(rs.getString("paystate"));
								info.setStateDate(rs.getTimestamp("StateDate"));
								list.add(info);
								}


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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public int updateApproval(int id, String projecttypes) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "update MoneyCheckUp set approval=?,approvalTime=getdate(),ReasonsApproval=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setString(2, projecttypes);
			stmt.setInt(3,id );
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
	public int updateApproval1(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "update MoneyCheckUp set approval=? where id = ? ";
		conn = SQLDBhelper.getConnection();
		//conn = DBHelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 0);

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
	public int updateMoney(String apNumber, double d) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;

		String sql = "update MoneyCheckUp set FacMoney=? where ApNumber = ? ";
		conn = SQLDBhelper.getConnection();

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, d);

			stmt.setString(2,apNumber );
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
	public int add(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into Affiche(CaseNo,ApNumber,Title,Content,"
				+ "CreateEMP,CreateDate)select caseno,ApNumber,'预审批通过',ApNumber+''+'Emma预审通过','Emma',getdate() from moneycheckup where id =?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id );
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
	public int add1(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into Affiche(CaseNo,ApNumber,Title,Content,"
				+ "CreateEMP,CreateDate)select caseno,ApNumber,'审批通过',ApNumber+''+'Emma审批通过','Emma',getdate() from moneycheckup where id =?";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id );
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
	public int updateWithDraw(int id, String time1, EmailUser user1) {

		String sql = "select * from moneycheckup where id=? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int total=0;
		int result = 0;
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			if(rs.next()) {
               String apnumber=rs.getString("ApNumber");
				String caseno=rs.getString("caseno");
				String FacMoney=rs.getString("FacMoney");
				String OtherMoney=rs.getString("OtherMoney");
				int checktype=rs.getInt("checktype");
				String friFacDate=rs.getString("FacDate");
				String OtherDate=rs.getString("OtherDate");
				String WuMoney=rs.getString("WuMoney");
				String WuDate=rs.getString("WuDate");
				if(checktype==1 || checktype==3){
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					String sql1 = "update factoryfund set state='计划已保存',checkReason=null where apnumber = ? ";
					conn1 = SQLDBhelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, apnumber);
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
						SQLDBhelper.close(conn1, stmt1, rs1);
					}
				}else{
					Connection conn1 = null;
					PreparedStatement stmt1 = null;
					ResultSet rs1 = null;
					String sql1 = "update wuliu set state='计划已保存',WuliuReason=null where apnumber = ? ";
					conn1 = SQLDBhelper.getConnection();
					try {
						stmt1 = conn1.prepareStatement(sql1);
						stmt1.setString(1, apnumber);
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
						SQLDBhelper.close(conn1, stmt1, rs1);
					}

				}
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				int result3 = 0;
				String sql3 = "insert into FactoryInvoice(CaseNo,Remark,CreateDate,empname) values(?,?,?,?)";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn.prepareStatement(sql3);
					stmt3.setString(1, caseno);
					if(checktype==1) {
						stmt3.setString(2, "取消项目"+friFacDate+"日工厂付款:"+FacMoney);
					}else if(checktype==2){
						stmt3.setString(2, "取消项目"+friFacDate+"日物流付款:"+FacMoney);
					}else if(checktype==3){
						stmt3.setString(2, "取消项目"+friFacDate+"日其他付款:"+FacMoney);
					}
					stmt3.setString(3, time1);
					stmt3.setString(4, user1.getUserName());
                    result3 = stmt3.executeUpdate();
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

				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				int result2 = 0;
				ResultSet rs2 = null;
				String sql2 = "delete from MoneyCheckUp where id=?  ";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setInt(1, id);
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
	public List<MoneyCheckUp> getFastTrackProject(MoneyCheckUp cp) {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select * from ( "
				+"select  a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,d.Customerremarks,"
				+" b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,b.Merchandising, "
				+" b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<font color=green>已完成款项</font>',a.State)>0"
				+" then 5 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 6    else 7 end),a.FacMoney,a.State,"
				+" a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+" a.StateDate,a.approvalTime,a.ReasonsApproval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo"
				+"   left join Customer d on d.id=b.customercode "
				+" where (a.state = '<font color=green>已完成款项</font>' "
				+"  or a.state = '<font color=red>未通过审批</font>') and a.FacMoney!=0"
				+"  and (express_lane_approval=0 or express_lane_approval=2) and express_lane=1"
				 +" and a.FacMoney!=0"
				+")a";

		;
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
			sql += " order by express_lane desc, State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				double iimoney1=0.0;
				String  CaseNo=rs.getString("CaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, CaseNo);
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String	date=rs1.getString("iidate");
						if(date!=null&&!"".equals(date)){
							imoneytype1=rs1.getInt("imoneytype");
							i=imoneytype1;
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH) + 1;
							double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year);
								stmt2.setInt(2, month);
								stmt2.setInt(3, imoneytype1);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							if(!"".equals(huilv)&&huilv!=0){
								if(imoneytype1==1){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}else if(imoneytype1==2){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney");
								}else{
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}
							}else{
								huilv=1;
								if(imoneytype1==1){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}else if(imoneytype1==2){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney");
								}else{
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}
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
					SQLDBhelper.returnConnection(conn1);
				}
				Double frimoney=0.0;
				String sql3 = "select sum(frimoney) from factoryfund where caseno=?";
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setString(1, CaseNo);
					rs3 = stmt3.executeQuery();
					if(rs3.next()) {
						frimoney=rs3.getDouble(1);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs3 != null) {
						try {
							rs3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt3 != null) {
						try {
							stmt3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn3);
				}
                int moneytype=0;
				int moneytype2=0;
				double OtherMoney=0.0;
				String sql4 = "select moneytype,OtherMoney,friFacDate from FactoryFund where CaseNo=? and OtherMoney is not null";
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				conn4 = SQLDBhelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					stmt4.setString(1, CaseNo);
					rs4 = stmt4.executeQuery();
					while(rs4.next()) {
						moneytype2=rs4.getInt("moneytype");
                        moneytype=moneytype2;
						String	date=rs4.getString("friFacDate");
						if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH) + 1;
							double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year);
								stmt2.setInt(2, month);
								stmt2.setInt(3, moneytype2);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							OtherMoney+=rs4.getDouble("OtherMoney")*huilv;
						}else if(moneytype2==2){
							OtherMoney+=rs4.getDouble("OtherMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs4 != null) {
						try {
							rs4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt4 != null) {
						try {
							stmt4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn4);
				}
				int moneyType3=0;
				double WuliuMoney=0.0;
				String sql5 = "select moneyType,WuliuMoney,wuliudate from Wuliu where CaseNo=?";
				Connection conn5 = null;
				PreparedStatement stmt5 = null;
				ResultSet rs5 = null;
				conn5 = SQLDBhelper.getConnection();
				try {
					stmt5 = conn5.prepareStatement(sql5);
					stmt5.setString(1, CaseNo);
					rs5 = stmt5.executeQuery();
					while(rs5.next()) {
						moneyType3=rs5.getInt("moneyType");
                        moneytype=moneyType3;
						String	date=rs5.getString("wuliudate");
						if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH) + 1;
							double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year);
								stmt2.setInt(2, month);
								stmt2.setInt(3, moneyType3);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							if(huilv!=0&&!"".equals(huilv)){
								WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}else{
								huilv=1;
								WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}
						}else if(moneyType3==2){
							WuliuMoney+=rs5.getDouble("WuliuMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs5 != null) {
						try {
							rs5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt5 != null) {
						try {
							stmt5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn5);
				}
				double  profitmargin=0.0;
				String profitmargin1="";
				double   f2=0.0;
				double   f =0.0;
				double   f1 =0.0;
				if(ifmoney!=0&&!"".equals(ifmoney)){
					BigDecimal   b   =   new   BigDecimal(ifmoney);
					f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney!=0&&!"".equals(iimoney)){
					BigDecimal   b1   =   new   BigDecimal(iimoney);
					f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney==0){
				}else{
					profitmargin=((iimoney1-frimoney-OtherMoney-WuliuMoney)/iimoney1)*100;
					BigDecimal   b2   =   new   BigDecimal(profitmargin);
					f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setMerchandising(rs.getString("Merchandising"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setId(rs.getInt("id"));
				info.setCaseNo(rs.getString("CaseNo"));
				info.setProductDescE(rs.getString("ProductDescE"));
				info.setProductDescC(rs.getString("ProductDescC"));

				info.setItemManager(rs.getString("ItemManager"));
				info.setState(rs.getInt("State1"));
				info.setFacMoney(rs.getInt("FacMoney"));
				info.setMtype(rs.getString("mtype"));
				info.setState1(rs.getString("State"));
				info.setFacDate(rs.getTimestamp("FacDate"));
				info.setFacReason(rs.getString("FacReason"));
				info.setReason(rs.getString("Reason"));
				info.setGeldObject(rs.getString("GeldObject"));
				info.setChecktype(rs.getInt("checktype"));
				info.setPaystate(rs.getString("paystate"));
				info.setStateDate(rs.getTimestamp("StateDate"));
				info.setAmount(f1);
				info.setSumacount(f);
				info.setCustomerremarks(rs.getString("Customerremarks"));
				info.setProjectnote(rs.getString("projectnote"));
				info.setProfit(f2+"%");
				info.setMoneytype(i);
				info.setApprovalTime(rs.getString("approvalTime"));
				info.setReasonsApproval(rs.getString("reasonsApproval"));
				info.setFastTrackReasons(rs.getString("fast_track_reasons"));
				info.setExpressLane(rs.getInt("express_lane"));
				info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
				String type=rs.getString("mtype");
				if("人民币".equals(type)){
					info.setMoneytype1(2);
				}else if("RMB".equals(type)){
					info.setMoneytype1(2);
				}
				if("美金".equals(type)){
					info.setMoneytype1(1);
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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

	@Override
	public List<MoneyCheckUp> getProjectVeto() {
		List<MoneyCheckUp> list = new ArrayList<MoneyCheckUp>();
		String sql = "select * from ( "
				+"select  a.[id],b.CaseNo,b.ProductDescE,b.ProductDescC,b.projectnote,d.Customerremarks,"
				+" b.CustomerManager,b.MerchandManager1,b.MerchandManager2,b.Engineer1,b.Merchandising, "
				+" b.Engineer2,b.Engineer3,a.ItemManager,State1=(Case when CHARINDEX('<font color=green>已完成款项</font>',a.State)>0"
				+" then 5 when CHARINDEX('<font color=red>未通过审批</font>',a.State)>0 then 6    else 7 end),a.FacMoney,a.State,"
				+" a.mtype,a.FacDate,a.FacReason,a.Reason,a.GeldObject,a.checktype,a.paystate,"
				+" a.StateDate,a.approvalTime,a.ReasonsApproval,a.express_lane,a.fast_track_reasons,a.express_lane_approval from MoneyCheckUp as a left join itemCase as b on a.CaseNo = b.CaseNo"
				+"   left join Customer d on d.id=b.customercode "
				+" where (a.state = '<font color=green>已完成款项</font>' "
				+"  or a.state = '<font color=red>未通过审批</font>') and a.FacMoney!=0"
				+"  and  express_lane_approval=2 and express_lane=1"
				+" and a.FacMoney!=0   and datediff(day,StateDate,getdate())<30"
				+")a";

		;
		int i=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = SQLDBhelper.getConnection();
		try {
			sql += " order by express_lane desc, State1 , a.StateDate desc ";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while(rs.next()) {
				double ifmoney=0.0;
				int imoneytype1=0;
				double iimoney=0.0;
				double iimoney1=0.0;
				String  CaseNo=rs.getString("CaseNo");
				String sql1 = "select iimoney,imoneytype,ifmoney,iidate,ifdate from invoiceinfo where iCaseNo=? ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, CaseNo);
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String	date=rs1.getString("iidate");
						if(date!=null&&!"".equals(date)){
							imoneytype1=rs1.getInt("imoneytype");
							i=imoneytype1;
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH) + 1;
							double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year);
								stmt2.setInt(2, month);
								stmt2.setInt(3, imoneytype1);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							if(!"".equals(huilv)&&huilv!=0){
								if(imoneytype1==1){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}else if(imoneytype1==2){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney");
								}else{
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}
							}else{
								huilv=1;
								if(imoneytype1==1){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}else if(imoneytype1==2){
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney");
								}else{
									ifmoney+=rs1.getDouble("ifmoney");
									iimoney+=rs1.getDouble("iimoney");
									iimoney1+=rs1.getDouble("iimoney")*huilv;
								}
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
					SQLDBhelper.returnConnection(conn1);
				}
				Double frimoney=0.0;
				String sql3 = "select sum(frimoney) from factoryfund where caseno=?";
				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setString(1, CaseNo);
					rs3 = stmt3.executeQuery();
					if(rs3.next()) {
						frimoney=rs3.getDouble(1);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs3 != null) {
						try {
							rs3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt3 != null) {
						try {
							stmt3.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn3);
				}
				int moneytype2=0;
				double OtherMoney=0.0;
				String sql4 = "select moneytype,OtherMoney,friFacDate from FactoryFund where CaseNo=? and OtherMoney is not null";
				Connection conn4 = null;
				PreparedStatement stmt4 = null;
				ResultSet rs4 = null;
				conn4 = SQLDBhelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					stmt4.setString(1, CaseNo);
					rs4 = stmt4.executeQuery();
					while(rs4.next()) {
						moneytype2=rs4.getInt("moneytype");
						String	date=rs4.getString("friFacDate");
						if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH) + 1;
							double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year);
								stmt2.setInt(2, month);
								stmt2.setInt(3, moneytype2);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							OtherMoney+=rs4.getDouble("OtherMoney")*huilv;
						}else if(moneytype2==2){
							OtherMoney+=rs4.getDouble("OtherMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs4 != null) {
						try {
							rs4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt4 != null) {
						try {
							stmt4.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn4);
				}
				int moneyType3=0;
				double WuliuMoney=0.0;
				String sql5 = "select moneyType,WuliuMoney,wuliudate from Wuliu where CaseNo=?";
				Connection conn5 = null;
				PreparedStatement stmt5 = null;
				ResultSet rs5 = null;
				conn5 = SQLDBhelper.getConnection();
				try {
					stmt5 = conn5.prepareStatement(sql5);
					stmt5.setString(1, CaseNo);
					rs5 = stmt5.executeQuery();
					while(rs5.next()) {
						moneyType3=rs5.getInt("moneyType");

						String	date=rs5.getString("wuliudate");
						if(date!=null&&!"".equals(date)){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH) + 1;
							double huilv=0.0;
							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year);
								stmt2.setInt(2, month);
								stmt2.setInt(3, moneyType3);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
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
								SQLDBhelper.returnConnection(conn2);
							}
							if(huilv!=0&&!"".equals(huilv)){
								WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}else{
								huilv=1;
								WuliuMoney+=rs5.getDouble("WuliuMoney")*huilv;
							}
						}else if(moneyType3==2){
							WuliuMoney+=rs5.getDouble("WuliuMoney");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs5 != null) {
						try {
							rs5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt5 != null) {
						try {
							stmt5.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn5);
				}
				double  profitmargin=0.0;
				String profitmargin1="";
				double   f2=0.0;
				double   f =0.0;
				double   f1 =0.0;
				if(ifmoney!=0&&!"".equals(ifmoney)){
					BigDecimal   b   =   new   BigDecimal(ifmoney);
					f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney!=0&&!"".equals(iimoney)){
					BigDecimal   b1   =   new   BigDecimal(iimoney);
					f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(iimoney==0){
				}else{
					profitmargin=((iimoney1-frimoney-OtherMoney-WuliuMoney)/iimoney1)*100;
					BigDecimal   b2   =   new   BigDecimal(profitmargin);
					f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				MoneyCheckUp info =  new MoneyCheckUp();
				info.setMerchandising(rs.getString("Merchandising"));
				info.setMerchandManager1(rs.getString("MerchandManager1"));
				info.setMerchandManager2(rs.getString("MerchandManager2"));
				info.setId(rs.getInt("id"));
				info.setCaseNo(rs.getString("CaseNo"));
				info.setProductDescE(rs.getString("ProductDescE"));
				info.setProductDescC(rs.getString("ProductDescC"));

				info.setItemManager(rs.getString("ItemManager"));
				info.setState(rs.getInt("State1"));
				info.setFacMoney(rs.getInt("FacMoney"));
				info.setMtype(rs.getString("mtype"));
				info.setState1(rs.getString("State"));
				info.setFacDate(rs.getTimestamp("FacDate"));
				info.setFacReason(rs.getString("FacReason"));
				info.setReason(rs.getString("Reason"));
				info.setGeldObject(rs.getString("GeldObject"));
				info.setChecktype(rs.getInt("checktype"));
				info.setPaystate(rs.getString("paystate"));
				info.setStateDate(rs.getTimestamp("StateDate"));
				info.setAmount(f1);
				info.setSumacount(f);
				info.setCustomerremarks(rs.getString("Customerremarks"));
				info.setProjectnote(rs.getString("projectnote"));
				info.setProfit(f2+"%");
				info.setMoneytype(i);
				info.setApprovalTime(rs.getString("approvalTime"));
				info.setReasonsApproval(rs.getString("reasonsApproval"));
				info.setFastTrackReasons(rs.getString("fast_track_reasons"));
				info.setExpressLane(rs.getInt("express_lane"));
				info.setExpressLaneApproval(rs.getInt("express_lane_approval"));
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
			SQLDBhelper.returnConnection(conn);
		}
		return list;
	}

}
