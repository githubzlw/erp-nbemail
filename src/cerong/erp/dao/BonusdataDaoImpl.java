package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.util.Calendar;










import cerong.erp.entity.Bonusdata;
import cerong.erp.entity.ItemCase;
import cerong.erp.entity.invoice;
import cerong.erp.jdbc.DBHelper;
import cerong.erp.jdbc.SQLDBhelper;

public class BonusdataDaoImpl implements IBonusdataDao {

	@Override
	public List<Bonusdata> getall(int year, int month) {

		List<Bonusdata> list = new ArrayList<Bonusdata>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String ifdate = "";
         String sql = "select distinct icaseno from invoiceinfo where year(ifdate)=? and month(ifdate)=?   ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, year + "");
			stmt.setString(2, month + "");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String ifdatea = "";
				Bonusdata bo = new Bonusdata();
				double ifmoney = 0.0;
				double ifmoneym = 0.0;
				double totalrmbm = 0.0;
				int imoneytype1 = 0;
				int degreeDifficulty = 0;
				double iimoney = 0.0;
				double totalrmb = 0.0;
				double totalrmb1 = 0.0;
				double totalrmb2 = 0.0;
				double profitmargin = 0.0;
				String profitmargin1 = "";
				double profitrmb = 0.0;
				double totalSalesUs = 0.0;
				double profitus = 0.0;
				double payfactory = 0.0;
				double payother = 0.0;
				double paywulliu = 0.0;
				double huilv = 0.0;
				double huilv1 = 0.0;
				String productdesce = "";
				String productdescc = "";
				String BackSingleCase = "";
				String time = "";
				// String  CaseNo="SHS17330";
				String CaseNo = rs.getString(1);
				String sql1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=? and year(ifdate)=? and month(ifdate)=?  and  ifmoney is not null ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, CaseNo);
					stmt1.setString(2, year + "");
					stmt1.setString(3, month + "");

					rs1 = stmt1.executeQuery();
					while (rs1.next()) {
						String date = rs1.getString("iidate");
						String ifdate1 = rs1.getString("ifdate");

						if (ifdate1 != null && !"".equals(ifdate1)) {
							String ymonth1 = "";
							if (month == 12) {
								int yeara = year + 1;
								ymonth1 = yeara + "-" + "01-01";
							} else {
								int montha = month + 1;
								String allmonth = "";
								if (month < 10) {
									allmonth = "0" + montha;
								} else {
									allmonth = String.valueOf(montha);
								}
								ymonth1 = year + "-" + allmonth + "-" + "01";
							}
							boolean a = ifdate1.contains(ymonth1);
							if (a != false) {
								ifdatea = ifdate1;
							}
							ifdate = ifdate1;
						} else {

							ifdate = date;
						}
						if (ifdate != null && !"".equals(ifdate)) {

							imoneytype1 = rs1.getInt("imoneytype");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(ifdate);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH) + 1;
							huilv = getHuilv(year1, month1, imoneytype1);
							huilv1 = getHuilv(year1, month1, 1);

							if (huilv == 0) {
								huilv = 1;
							}

							if (imoneytype1 == 1) {
								Double money = rs1.getDouble("ifmoney");
								if (money != null && !"".equals(money)) {
									ifmoney += rs1.getDouble("ifmoney");
									totalrmb += rs1.getDouble("ifmoney") * huilv;

								} else {

									double huilva = getHuilva(date, imoneytype1);
									ifmoney += rs1.getDouble("iimoney");
									totalrmb += rs1.getDouble("iimoney") * huilva;

								}

							} else if (imoneytype1 == 2) {
								Double money = rs1.getDouble("ifmoney");
								if (money != null && !"".equals(money)) {
									ifmoney += rs1.getDouble("ifmoney") / huilv1;
									totalrmb += rs1.getDouble("ifmoney");

								} else {
									ifmoney += rs1.getDouble("iimoney") / huilv1;
									totalrmb += rs1.getDouble("iimoney");

								}
							} else {

								Double money = rs1.getDouble("ifmoney");
								if (money != null && !"".equals(money)) {
									ifmoney += (rs1.getDouble("ifmoney") / huilv1) * huilv;
									totalrmb += rs1.getDouble("ifmoney") * huilv;

								} else {
									double huilva = getHuilva(date, imoneytype1);
									ifmoney += (rs1.getDouble("iimoney") / huilv1) * huilv;
									totalrmb += rs1.getDouble("iimoney") * huilva;

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

				String sqla = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?  ";
				Connection conna = null;
				PreparedStatement stmta = null;
				ResultSet rsa = null;
				conna = SQLDBhelper.getConnection();
				try {
					stmta = conna.prepareStatement(sqla);
					stmta.setString(1, CaseNo);
					rsa = stmta.executeQuery();
					while (rsa.next()) {
						String date = rsa.getString("iidate");
						String ifdate1 = rsa.getString("ifdate");
						if (ifdate1 != null && !"".equals(ifdate1)) {
							ifdate = ifdate1;
						}
						if (date != null && !"".equals(date)) {
							imoneytype1 = rsa.getInt("imoneytype");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH);
							huilv = getHuilv(year1, month1, imoneytype1);
							huilv1 = getHuilv(year1, month1, 1);

							if (huilv == 0) {
								huilv = 1;
							}
							double ifmoneya = 0.0;
							if (imoneytype1 == 1) {
								ifmoneya = rsa.getDouble("ifmoney") * huilv;
								if (ifmoneya != 0) {
									totalrmb2 += rsa.getDouble("ifmoney") * huilv;
								} else {
									totalrmb2 += rsa.getDouble("iimoney") * huilv;
								}
							} else if (imoneytype1 == 2) {
								ifmoneya = rsa.getDouble("ifmoney");
								if (ifmoneya != 0) {
									totalrmb2 += rsa.getDouble("ifmoney");
								} else {
									totalrmb2 += rsa.getDouble("iimoney");
								}
							} else {
								ifmoneya = rsa.getDouble("ifmoney") * huilv;
								if (ifmoneya != 0) {
									totalrmb2 += rsa.getDouble("ifmoney") * huilv;
								} else {

									totalrmb2 += rsa.getDouble("iimoney") * huilv;
								}

							}
						}
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
					SQLDBhelper.returnConnection(conna);
				}
				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select friMoney,moneytype,Convert(varchar(100),friFacDate,23)date from factoryfund  where CaseNo=? and bargainno is not null";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, CaseNo);
					rs2 = stmt2.executeQuery();
					while (rs2.next()) {
						int type = rs2.getInt("moneytype");
						String date = rs2.getString("date");

						if (type == 2) {
							payfactory += rs2.getDouble("friMoney");
						} else if (type != 2) {
							double huilva = getHuilva(date, type);
							payfactory += rs2.getDouble("friMoney") * huilva;
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
					}
					SQLDBhelper.close(conn2, stmt2, rs2);

				}
				String sql6 = "select CaseNo,BackSingleCase from itemCase where customercode=( select customercode from itemCase where CaseNo=? )";
				Connection conn6 = null;
				PreparedStatement stmt6 = null;
				ResultSet rs6 = null;
				conn6 = SQLDBhelper.getConnection();
				try {
					stmt6 = conn6.prepareStatement(sql6);

					stmt6.setString(1, CaseNo);
					rs6 = stmt6.executeQuery();
					if (rs6.next()) {
						String CaseNo1 = rs6.getString("CaseNo");
						if (CaseNo1 == CaseNo) {
							BackSingleCase = rs6.getString("BackSingleCase");
						} else {
							Connection conn10 = null;
							PreparedStatement stmt10 = null;
							ResultSet rs10 = null;
							String sql10 = "select BackSingleCase from itemCase where CaseNo=?";
							conn10 = SQLDBhelper.getConnection();
							try {
								stmt10 = conn10.prepareStatement(sql10);
								stmt10.setString(1, CaseNo);
								rs10 = stmt10.executeQuery();
								if (rs10.next()) {
									BackSingleCase = rs10.getString("BackSingleCase");

								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (stmt10 != null) {
									try {
										stmt10.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (rs10 != null) {
									try {
										rs10.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.close(conn10, stmt10, rs10);

							}
						}
						degreeDifficulty = getDegreeDifficulty(BackSingleCase,CaseNo);

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs6 != null) {
						try {
							rs6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt6 != null) {
						try {
							stmt6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn6);
				}

				payother=getPay(CaseNo);
				paywulliu=getPayWuLiu(CaseNo);

				Connection conn5 = null;
				PreparedStatement stmt5 = null;
				ResultSet rs5 = null;
				String sql5 = "select ic.quality_deduction_project,ic.CustomerManager,ic.customercode,ic.MerchandManager1,ic.MerchandManager2,"
						+ "ic.Engineer1,ic.Engineer2,ic.Engineer3,ic.zhijian1,ic.zhijian2,ic.ProductDescE"
						+ ",ic.ProductDescC,ic.Merchandising,ic.MaturePurchase,ic.OriginalPurchase,ic.CaseStatus,ic.master_quality_inspection" +
						" ,ic.QualityInspector,ic.QualityInspector1,ic.QualityInspector2,ic.QualityInspector3,ic.QualityInspector4" +
						",ic.QualityInspector5,ic.QualityInspector6,ic.QualityInspector7  " +
						" from itemCase ic left join invoiceInfo ii on ic.CaseNo=ii.iCaseNo  "
						+ " where CaseNo=? ";
				conn5 = SQLDBhelper.getConnection();
				try {
					stmt5 = conn5.prepareStatement(sql5);
					stmt5.setString(1, CaseNo);
					rs5 = stmt5.executeQuery();
					while (rs5.next()) {
						bo.setCustomerManager(rs5.getString("CustomerManager"));
						bo.setMerchandManager1(rs5.getString("MerchandManager1"));
						bo.setCustomerId(rs5.getInt("customercode"));
						bo.setOriginalPurchase(rs5.getString("OriginalPurchase"));
						bo.setMerchandManager2(rs5.getString("MerchandManager2"));
						bo.setMerchandising(rs5.getString("Merchandising"));
						bo.setMaturePurchase(rs5.getString("MaturePurchase"));
						bo.setZhijian1(rs5.getString("zhijian1"));
						bo.setZhijian2(rs5.getString("zhijian2"));
						bo.setProductdescc(rs5.getString("ProductDescC"));
						bo.setProductdesce(rs5.getString("ProductDescE"));
						bo.setQualityDeductionProject(rs5.getInt("quality_deduction_project"));
						bo.setMasterQualityInspection(rs5.getString("master_quality_inspection"));
						bo.setQualityInspector(rs5.getString("QualityInspector"));
						bo.setQualityInspector1(rs5.getString("QualityInspector1"));
						bo.setQualityInspector2(rs5.getString("QualityInspector2"));
						bo.setQualityInspector3(rs5.getString("QualityInspector3"));
						bo.setQualityInspector4(rs5.getString("QualityInspector4"));
						bo.setQualityInspector5(rs5.getString("QualityInspector5"));
						bo.setQualityInspector6(rs5.getString("QualityInspector6"));
						bo.setQualityInspector7(rs5.getString("QualityInspector7"));
						int caseStatus = rs5.getInt("CaseStatus");
						if (caseStatus == 0 || caseStatus == 14 || caseStatus == 10 || caseStatus == 5) {
							bo.setCaseStatus(1);
						} else {
							bo.setCaseStatus(0);
						}
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
					SQLDBhelper.close(conn5, stmt5, rs5);

				}
				String sqlm1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?    ";
				Connection connm1 = null;
				PreparedStatement stmtm1 = null;
				ResultSet rsm1 = null;
				connm1 = SQLDBhelper.getConnection();
				try {
					stmtm1 = connm1.prepareStatement(sqlm1);
					stmtm1.setString(1, CaseNo);
                    rsm1 = stmtm1.executeQuery();
					while (rsm1.next()) {
						String date = rsm1.getString("iidate");
						String ifdate1 = rsm1.getString("ifdate");
						String allmonth = "";
						if (month < 10) {
							allmonth = "0" + month;
						} else {
							allmonth = String.valueOf(month);
						}
						String ymonth = year + "-" + allmonth;
						if (ifdate1 != null && !"".equals(ifdate1)) {
							boolean a = ifdate1.contains(ymonth);
							if (a != false) {
								ifdatea = ifdate1;
							}
							ifdate = ifdate1;
						} else {
							ifdate = date;
						}
						if (ifdate != null && !"".equals(ifdate)) {

							imoneytype1 = rsm1.getInt("imoneytype");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(ifdate);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH) + 1;

							huilv = getHuilv(year1, month1, imoneytype1);
							huilv1 = getHuilv(year1, month1, 1);
							if (huilv == 0) {
								huilv = 1;
							}

							if (imoneytype1 == 1) {
								Double money = rsm1.getDouble("ifmoney");
								if (money != null && !"".equals(money) && money != 0) {
									ifmoneym += rsm1.getDouble("ifmoney");
									totalrmbm += rsm1.getDouble("ifmoney") * huilv;
									totalSalesUs += rsm1.getDouble("ifmoney");
								} else {
									ifmoneym += rsm1.getDouble("iimoney");
									totalrmbm += rsm1.getDouble("iimoney") * huilv;
									totalSalesUs += rsm1.getDouble("iimoney");
								}

							} else if (imoneytype1 == 2) {
								Double money = rsm1.getDouble("ifmoney");
								if (money != null && !"".equals(money) && money != 0) {
									ifmoneym += rsm1.getDouble("ifmoney") / huilv;
									totalrmbm += rsm1.getDouble("ifmoney");
									totalSalesUs += rsm1.getDouble("ifmoney") / huilv1;
								} else {
									ifmoneym += rsm1.getDouble("iimoney") / huilv;
									totalrmbm += rsm1.getDouble("iimoney");
									totalSalesUs += rsm1.getDouble("iimoney") / huilv1;
								}
							} else {

								Double money = rsm1.getDouble("ifmoney");
								if (money != null && !"".equals(money) && money != 0) {
									ifmoneym += (rsm1.getDouble("ifmoney") / huilv1) * huilv;
									totalrmbm += rsm1.getDouble("ifmoney") * huilv;
									totalSalesUs += (rsm1.getDouble("ifmoney") / huilv1) * huilv;
								} else {
									ifmoneym += (rsm1.getDouble("iimoney") / huilv1) * huilv;
									totalrmbm += rsm1.getDouble("iimoney") * huilv;
									totalSalesUs += (rsm1.getDouble("iimoney") / huilv1) * huilv;
								}

							}

						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsm1 != null) {
						try {
							rsm1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmtm1 != null) {
						try {
							stmtm1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(connm1);
				}


				String ifdatex = "";
				double profitrmb1 = 0.0;
				double profitmargin2 = 0.0;
				String profitmargin3 = "";
				if (totalrmb != 0) {
					double debitDeduction=getDebitDeduction(CaseNo);//进账扣款
					double factoryDeduction=getFactoryDeduction(CaseNo);//工厂扣款
					totalrmbm=totalrmbm-debitDeduction;
					totalrmb1=paywulliu+payother+payfactory-factoryDeduction;
					profitrmb = totalrmbm - totalrmb1;
					profitrmb1=totalrmbm-totalrmb1;
					totalSalesUs=getTotalSalseUs(totalrmbm,year,month);
					profitus = profitrmb / huilv1;
					profitmargin = profitrmb / totalrmb;
					profitmargin2 = profitrmb1 / totalrmb2;
					double profitmargina = profitmargin * 100;
					double profitmarginb = profitmargin2 * 100;
					double f = 0.0;
					double f1 = 0.0;
					double f2 = 0.0;
					double f3 = 0.0;
					double f4 = 0.0;
					double f5 = 0.0;


					if (profitmargina != 0 && !"".equals(profitmargina)) {
						BigDecimal b = new BigDecimal(profitmargina);
						f = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
						profitmargin1 = f + "%";
					}
					if (profitmarginb != 0 && !"".equals(profitmarginb)) {
						BigDecimal b = new BigDecimal(profitmarginb);
						f = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
						profitmargin3 = f + "%";
					}
					if (totalrmb1 != 0 && !"".equals(totalrmb1)) {
						BigDecimal b = new BigDecimal(totalrmb1);
						f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

					}
					if (totalrmb != 0 && !"".equals(totalrmb)) {
						BigDecimal b = new BigDecimal(totalrmb);
						f5 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

					}
					if (profitrmb != 0 && !"".equals(profitrmb)) {
						BigDecimal b = new BigDecimal(profitrmb);
						f2 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

					}
					if (profitus != 0 && !"".equals(profitus)) {
						BigDecimal b = new BigDecimal(profitus);
						f3 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

					}
					if (ifmoney != 0 && !"".equals(ifmoney) && !"NaN".equals(ifmoney) && !"Infinity".equals(ifmoney)) {
						System.out.print(ifmoney);
						BigDecimal b = new BigDecimal(ifmoney);

						f4 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

					}
					double saleallMoney = 0.0;
					String sqlx = "  select imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?  ";
					Connection connx = null;
					PreparedStatement stmtx = null;
					ResultSet rsx = null;
					connx = SQLDBhelper.getConnection();
					try {
						stmtx = connx.prepareStatement(sqlx);
						stmtx.setString(1, CaseNo);
						rsx = stmtx.executeQuery();
						while (rsx.next()) {
							String date = rsx.getString("iidate");
							String ifdate1 = rsx.getString("ifdate");

							if (ifdate1 != null && !"".equals(ifdate1)) {
								ifdate = ifdate1;
								if (ifdatex == null || "".equals(ifdatex)) {
									ifdatex = ifdate1;
								} else {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
									Date date1 = sdf.parse(ifdate1);
									Date date2 = sdf.parse(ifdatex);
									if (sdf.format(date1).compareTo(sdf.format(date2)) > 0) {
										ifdatex = ifdate1;
									}
								}

							} else {

								ifdate = date;

							}
							if (ifdate != null && !"".equals(ifdate)) {
								imoneytype1 = rsx.getInt("imoneytype");

								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Calendar cal = Calendar.getInstance();
								Date dt = null;
								try {
									dt = sdf.parse(ifdate);
									cal.setTime(dt);
								} catch (Exception e) {
									e.printStackTrace();
								}

								int year1 = cal.get(Calendar.YEAR);
								int month1 = cal.get(Calendar.MONTH) + 1;
								huilv = getHuilv(year1, month1, imoneytype1);
								huilv1 = getHuilv(year1, month1, 1);

								if (huilv == 0) {
									huilv = 1;
								}

								if (imoneytype1 == 1) {
									Double money = rsx.getDouble("ifmoney");
									if (money != null && !"".equals(money) && money != 0) {

										saleallMoney += rsx.getDouble("ifmoney") * huilv;
									} else {

										saleallMoney += rsx.getDouble("iimoney") * huilv;
									}

								} else if (imoneytype1 == 2) {
									Double money = rsx.getDouble("ifmoney");
									if (money != null && !"".equals(money) && money != 0) {

										saleallMoney += rsx.getDouble("ifmoney");
									} else {

										saleallMoney += rsx.getDouble("iimoney");
									}
								} else {

									Double money = rsx.getDouble("ifmoney");
									if (money != null && !"".equals(money) && money != 0) {

										saleallMoney += rsx.getDouble("ifmoney") * huilv;
									} else {

										saleallMoney += rsx.getDouble("iimoney") * huilv;
									}

								}

							}


						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rsx != null) {
							try {
								rsx.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmtx != null) {
							try {
								stmtx.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.returnConnection(connx);
					}
					double fm = 0.0;
					if (saleallMoney != 0 && !"".equals(saleallMoney) && !"NaN".equals(saleallMoney) && !"Infinity".equals(saleallMoney)) {
						//System.out.print(saleallMoney);
						BigDecimal b = new BigDecimal(saleallMoney);

						fm = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

					}
					double totalAmountOfArrival = 0.0;
					double totalAmountReceivable = 0.0;
					String sqlb = "  select sum(iimoney)iimoney,sum(ifmoney)ifmoney,iCaseNo from invoiceinfo where icaseno=?  group by iCaseNo";
					Connection connb = null;
					PreparedStatement stmtb = null;
					ResultSet rsb = null;
					connb = SQLDBhelper.getConnection();
					try {
						stmtb = connb.prepareStatement(sqlb);
						stmtb.setString(1, CaseNo);
						rsb = stmtb.executeQuery();
						if (rsb.next()) {
							totalAmountOfArrival = rsb.getDouble("ifmoney");
							totalAmountReceivable = rsb.getDouble("iimoney");

						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rsb != null) {
							try {
								rsb.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmtb != null) {
							try {
								stmtb.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.returnConnection(connb);
					}
					double Pay_Moeny = 0.0;
					double Get_Moeny = 0.0;
					double iimoney1 = 0.0;
					double ifmoney1 = 0.0;
					double factory_yue_money = 0.0;
					String sqlf = "select sum(iimoney)iimoney from InvoiceInfo  where iCaseNo=?     ";
					Connection connf = null;
					PreparedStatement stmtf = null;
					ResultSet rsf = null;
					connf = SQLDBhelper.getConnection();
					try {
						stmtf = connf.prepareStatement(sqlf);
						stmtf.setString(1, CaseNo);


						rsf = stmtf.executeQuery();
						while (rsf.next()) {
							iimoney1 = rsf.getDouble("iimoney");
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rsf != null) {
							try {
								rsf.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmtf != null) {
							try {
								stmtf.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.returnConnection(connf);
					}
					String ymonth1 = "";
					if (month == 12) {
						int yeara = year + 1;
						ymonth1 = yeara + "-" + "01-01";
					} else {
						int montha = month + 1;
						String allmonth = "";
						if (month < 10) {
							allmonth = "0" + montha;
						} else {
							allmonth = String.valueOf(montha);
						}
						ymonth1 = year + "-" + allmonth + "-" + "01";
					}

					String sqly = "select sum(ifmoney)ifmoney from InvoiceInfo  where iCaseNo=? and DATEDIFF(day,'" + ymonth1 + "',ifdate)<0    ";
					Connection conny = null;
					PreparedStatement stmty = null;
					ResultSet rsy = null;
					conny = SQLDBhelper.getConnection();
					try {
						stmty = conny.prepareStatement(sqly);
						stmty.setString(1, CaseNo);


						rsy = stmty.executeQuery();
						while (rsy.next()) {
							ifmoney1 = rsy.getDouble("ifmoney");
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rsy != null) {
							try {
								rsy.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmty != null) {
							try {
								stmty.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.returnConnection(conny);
					}


					String sqlg = "select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny from Tab_Factory_Money left join itemCase  on itemCase.CaseNo=Tab_Factory_Money.Case_No where CaseNo=? and DATEDIFF(day,'" + ymonth1 + "',Date_time)<0   ";
					Connection conng = null;
					PreparedStatement stmtg = null;
					ResultSet rsg = null;
					conng = SQLDBhelper.getConnection();
					try {
						stmtg = conng.prepareStatement(sqlg);
						stmtg.setString(1, CaseNo);


						rsg = stmtg.executeQuery();
						while (rsg.next()) {
							Pay_Moeny = rsg.getDouble("Pay_Moeny");
							Get_Moeny = rsg.getDouble("Get_Moeny");

						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (rsg != null) {
							try {
								rsg.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (stmtg != null) {
							try {
								stmtg.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.returnConnection(conng);
					}

					if (Pay_Moeny == Get_Moeny && Pay_Moeny != 0) {
						bo.setIsInvoice(1);
					} else {
						bo.setIsInvoice(0);
					}
					bo.setProjectId(CaseNo);
					bo.setAccountPayableRmb(f1);
					bo.setSalesThisPeriodRmb(f5);
					bo.setTotalGrossProfitRmb(profitrmb1);
					bo.setSalesThisPeriodUs(f4);
					bo.setTotalGrossProfitUs(f4);
					bo.setTotalSalesRmb(totalrmbm);
					bo.setTotalGrossProfitMargin(profitmargin3);
					bo.setLatest_payment_time(ifdatex);
					bo.setTotalSalesUs(totalSalesUs);
					bo.setPayment_time(ifdatea);
					bo.setDegreeDifficulty(degreeDifficulty);
					bo.setHeritageProject(BackSingleCase);
					bo.setTotalAmountOfArrival(ifmoney1);
					bo.setTotalAmountReceivable(iimoney1);
					if (paywulliu > 0) {
						bo.setLogisticsCost(0);
					} else {
						bo.setLogisticsCost(1);
					}
					//System.out.print(totalrmb2+";"+f1+";"+profitmargin3);
					list.add(bo);
					System.out.print(ifdate + ";" + ifdatea);
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
			SQLDBhelper.close(conn, stmt, rs);

		}
		return list;
	}

	private double getPayWuLiu(String CaseNo) {
		double paywulliu=0.000;
		Connection conn4 = null;
		PreparedStatement stmt4 = null;
		ResultSet rs4 = null;
		String sql4 = "select wuliuMoney,moneytype,Convert(varchar(100),WuliuDate,23)date from 	wuliu  where CaseNo=? ";
		conn4 = SQLDBhelper.getConnection();
		try {
			stmt4 = conn4.prepareStatement(sql4);
			stmt4.setString(1, CaseNo);
			rs4 = stmt4.executeQuery();
			while (rs4.next()) {
				int type = rs4.getInt("moneytype");
				String date = rs4.getString("date");

				if (type == 2) {
					paywulliu += rs4.getDouble("wuliuMoney");
				} else if (type != 2) {
					double huilva = getHuilva(date, type);
					paywulliu += rs4.getDouble("wuliuMoney") * huilva;
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
			SQLDBhelper.close(conn4, stmt4, rs4);

		}
		return paywulliu;
	}

	private double getPay(String CaseNo) {
		double payother=0.000;
		Connection conn3 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs3 = null;
		String sql3 = "select otherMoney,moneytype,Convert(varchar(100),OtherDate,23)date from factoryfund  where CaseNo=?  and otherMoney is not null";
		conn3 = SQLDBhelper.getConnection();
		try {
			stmt3 = conn3.prepareStatement(sql3);
			stmt3.setString(1, CaseNo);
			rs3 = stmt3.executeQuery();
			while (rs3.next()) {
				int type = rs3.getInt("moneytype");
				String date = rs3.getString("date");

				if (type == 2) {
					payother += rs3.getDouble("otherMoney");
				} else if (type != 2) {
					double huilva = getHuilva(date, type);
					payother += rs3.getDouble("otherMoney") * huilva;
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
			SQLDBhelper.close(conn3, stmt3, rs3);

		}
		return payother;
	}

	private int getDegreeDifficulty(String BackSingleCase,String CaseNo) {
		int degreeDifficulty=0;
		if (BackSingleCase != null && !"".equals(BackSingleCase)) {
			Connection conn7 = null;
			PreparedStatement stmt7 = null;
			ResultSet rs7 = null;
			String sql7 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? )) and  datediff(day,iidate,getdate())>1095";
			conn7 = SQLDBhelper.getConnection();
			try {
				stmt7 = conn7.prepareStatement(sql7);
				stmt7.setString(1, BackSingleCase);
				rs7 = stmt7.executeQuery();
				if (rs7.next()) {

					int id = rs7.getInt(1);
					if (id > 0) {
						degreeDifficulty = 6;
					} else {
						Connection conn8 = null;
						PreparedStatement stmt8 = null;
						ResultSet rs8 = null;
						String sql8 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? )) and  datediff(day,iidate,getdate())>730 and  datediff(day,iidate,getdate())<1095";
						conn8 = SQLDBhelper.getConnection();
						try {
							stmt8 = conn8.prepareStatement(sql8);
							stmt8.setString(1, BackSingleCase);
							rs8 = stmt8.executeQuery();
							if (rs8.next()) {

								int id1 = rs8.getInt(1);
								if (id1 > 0) {
									degreeDifficulty = 5;
								} else {
									Connection conn9 = null;
									PreparedStatement stmt9 = null;
									ResultSet rs9 = null;
									String sql9 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? )) and  datediff(day,iidate,getdate())>365 and  datediff(day,iidate,getdate())<730";
									conn9 = SQLDBhelper.getConnection();
									try {
										stmt9 = conn9.prepareStatement(sql9);
										stmt9.setString(1, BackSingleCase);
										rs9 = stmt9.executeQuery();
										if (rs9.next()) {

											int id2 = rs9.getInt(1);
											if (id2 > 0) {
												degreeDifficulty = 4;
											} else {
												Connection conn10 = null;
												PreparedStatement stmt10 = null;
												ResultSet rs10 = null;
												String sql10 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? ))  and  datediff(day,iidate,getdate())<365";
												conn10 = SQLDBhelper.getConnection();
												try {
													stmt10 = conn10.prepareStatement(sql10);
													stmt10.setString(1, BackSingleCase);
													rs10 = stmt10.executeQuery();
													if (rs10.next()) {

														int id3 = rs10.getInt(1);
														if (id3 > 0) {
															degreeDifficulty = 3;
														}
													}
												} catch (Exception e) {
													e.printStackTrace();
												} finally {
													if (stmt10 != null) {
														try {
															stmt10.close();
														} catch (SQLException e) {
															e.printStackTrace();
														}
													}
													if (rs10 != null) {
														try {
															rs10.close();
														} catch (SQLException e) {
															e.printStackTrace();
														}
													}
													SQLDBhelper.close(conn10, stmt10, rs10);

												}


											}
										}
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										if (stmt9 != null) {
											try {
												stmt9.close();
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
										if (rs9 != null) {
											try {
												rs9.close();
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
										SQLDBhelper.close(conn9, stmt9, rs9);

									}


								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (stmt8 != null) {
								try {
									stmt8.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (rs8 != null) {
								try {
									rs8.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.close(conn8, stmt8, rs8);

						}


					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stmt7 != null) {
					try {
						stmt7.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (rs7 != null) {
					try {
						rs7.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				SQLDBhelper.close(conn7, stmt7, rs7);

			}
		}
		else {

			Connection conn10 = null;
			PreparedStatement stmt10 = null;
			ResultSet rs10 = null;
			String sql10 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? ))  and  datediff(day,iidate,getdate())>365";
			conn10 = SQLDBhelper.getConnection();
			try {
				stmt10 = conn10.prepareStatement(sql10);
				stmt10.setString(1, CaseNo);
				rs10 = stmt10.executeQuery();
				if (rs10.next()) {

					int id3 = rs10.getInt(1);
					if (id3 > 0) {
						degreeDifficulty = 2;
					} else {

						Connection conna1 = null;
						PreparedStatement stmta1 = null;
						ResultSet rsa1 = null;
						String sqla1 = " select datediff(day,b.CreateTime,a.ifdate) from (select min(ifdate)ifdate from invoiceinfo where icaseno in"
								+ "( select caseno from itemcase where customercode in( select customercode from itemcase where caseno=?)))a,"
								+ "(select min(CreateTime)CreateTime from itemcase where customercode in( select customercode from itemcase where caseno=?))b ";
						conna1 = SQLDBhelper.getConnection();
						try {
							stmta1 = conna1.prepareStatement(sqla1);
							stmta1.setString(1, CaseNo);
							stmta1.setString(2, CaseNo);
							rsa1 = stmta1.executeQuery();
							if (rsa1.next()) {

								int id1 = rsa1.getInt(1);
								if (id1 > 365) {
									degreeDifficulty = 2;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (stmta1 != null) {
								try {
									stmta1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (rsa1 != null) {
								try {
									rsa1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.close(conna1, stmta1, rsa1);

						}


						if (degreeDifficulty == 0) {

							degreeDifficulty = 1;

						}


					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stmt10 != null) {
					try {
						stmt10.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (rs10 != null) {
					try {
						rs10.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				SQLDBhelper.close(conn10, stmt10, rs10);

			}


		}
		return degreeDifficulty;
	}

	private double getHuilv(int year1, int month1, int imoneytype1) {
		double huilv = 0.00;
		String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
		Connection conn2 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		conn2 = SQLDBhelper.getConnection();
		try {
			stmt2 = conn2.prepareStatement(sql2);
			stmt2.setInt(1, year1);
			stmt2.setInt(2, month1);
			stmt2.setInt(3, imoneytype1);
			rs2 = stmt2.executeQuery();
			if (rs2.next()) {
				huilv = rs2.getDouble(1);
				if (huilv == 0) {
					huilv = 1;
				}
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
		return huilv;
	}


	@Override
	public Bonusdata getall(String projectId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs5 = null;
		Bonusdata bo=null;
		String sql = "select * from itemCase where CaseNo=? ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			rs5 = stmt.executeQuery();
			while(rs5.next()) {
				bo=new Bonusdata();
				bo.setProjectId(projectId);
				bo.setCustomerManager(rs5.getString("CustomerManager"));
				bo.setMerchandManager1(rs5.getString("MerchandManager1"));

				bo.setMaturePurchase(rs5.getString("MaturePurchase"));
				bo.setMerchandising(rs5.getString("Merchandising"));
				bo.setMerchandManager2(rs5.getString("MerchandManager2"));
				bo.setZhijian1(rs5.getString("zhijian1"));
				bo.setZhijian2(rs5.getString("zhijian2"));
				bo.setMasterQualityInspection(rs5.getString("master_quality_inspection"));
				bo.setQualityInspector(rs5.getString("QualityInspector"));
				bo.setQualityInspector1(rs5.getString("QualityInspector1"));
				bo.setQualityInspector2(rs5.getString("QualityInspector2"));
				bo.setQualityInspector3(rs5.getString("QualityInspector3"));
				bo.setQualityInspector4(rs5.getString("QualityInspector4"));
				bo.setQualityInspector5(rs5.getString("QualityInspector5"));
				bo.setQualityInspector6(rs5.getString("QualityInspector6"));
				bo.setQualityInspector7(rs5.getString("QualityInspector7"));
				bo.setProductdescc(rs5.getString("ProductDescC"));
				bo.setProductdesce(rs5.getString("ProductDescE"));

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
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SQLDBhelper.close(conn,stmt,rs5);
		}
		return bo;

	}





	@Override
	public List<Bonusdata> getalla(String projectIdList, int year, int month) {
		List<Bonusdata> list = new ArrayList<Bonusdata>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String ifdate="";
		String [] json=projectIdList.split(",");

		if(json.length>0){

			for(int i=0;i<json.length;i++){
				String ifdatea="";
				Bonusdata bo=new Bonusdata();
				double ifmoney=0.0;
				double ifmoneym=0.0;
				double totalrmbm=0.0;
				int imoneytype1=0;
				int degreeDifficulty=0;
				double iimoney=0.0;
				double totalrmb=0.0;
				double totalrmb1=0.0;
				double totalrmb2=0.0;
				double profitmargin=0.0;
				String profitmargin1="";
				double profitrmb=0.0;
				double totalSalesUs=0.0;
				double profitus=0.0;
				double payfactory=0.0;
				double payother=0.0;
				double paywulliu=0.0;
				double huilv=0.00;
				double huilv1=0.00;
				String productdesce="";
				String productdescc="";
				String BackSingleCase="";
				String time="";
				// String  CaseNo="SHS17330";
				String  CaseNo=json[i];
				String sql1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=? and year(ifdate)=? and month(ifdate)=?  and  ifmoney is not null ";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, CaseNo);
					stmt1.setString(2, year+"");
					stmt1.setString(3, month+"");

					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
						String	date=rs1.getString("iidate");
						String ifdate1=rs1.getString("ifdate");

						if(ifdate1!=null&&!"".equals(ifdate1)){
							String ymonth1="";
							if(month==12){
								int yeara=year+1;
								ymonth1=yeara+"-"+"01-01";
							}else{
								int montha=month+1;
								String allmonth="";
								if(month<10){
									allmonth="0"+montha;
								}else{
									allmonth=String.valueOf(montha);
								}
								ymonth1=year+"-"+allmonth+"-"+"01";
							}
							boolean a = ifdate1.contains(ymonth1);
							if(a!=false){
								ifdatea=ifdate1;
							}
							ifdate=ifdate1;
						}else{

							ifdate=date;
						}
						if(ifdate!=null&&!"".equals(ifdate)){

							imoneytype1=rs1.getInt("imoneytype");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(ifdate);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH)+1 ;

							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year1);
								stmt2.setInt(2, month1);
								stmt2.setInt(3, imoneytype1);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
									if(huilv==0){
										huilv=1;
									}
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
							String sql3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn3 = null;
							PreparedStatement stmt3 = null;
							ResultSet rs3 = null;
							conn3 = SQLDBhelper.getConnection();
							try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setInt(1, year1);
								stmt3.setInt(2, month1);
								stmt3.setInt(3, 1);
								rs3 = stmt3.executeQuery();
								if(rs3.next()) {
									huilv1=rs3.getDouble(1);
									if(huilv1==0){
										huilv1=1;
									}
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
							if(huilv==0){
								huilv=1;
							}

							if(imoneytype1==1){
								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)){
									ifmoney+=rs1.getDouble("ifmoney");
									totalrmb+=rs1.getDouble("ifmoney")*huilv;

								}else{
									ifmoney+=rs1.getDouble("iimoney");
									totalrmb+=rs1.getDouble("iimoney")*huilv;

								}

							}else if(imoneytype1==2){
								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)){
									ifmoney+=rs1.getDouble("ifmoney")/huilv1;
									totalrmb+=rs1.getDouble("ifmoney");

								}else{
									ifmoney+=rs1.getDouble("iimoney")/huilv1;
									totalrmb+=rs1.getDouble("iimoney");

								}
							}else{

								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)){
									ifmoney+=(rs1.getDouble("ifmoney")/huilv1)*huilv;
									totalrmb+=rs1.getDouble("ifmoney")*huilv;

								}else{
									ifmoney+=(rs1.getDouble("iimoney")/huilv1)*huilv;
									totalrmb+=rs1.getDouble("iimoney")*huilv;

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

				String sqla = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?  ";
				Connection conna = null;
				PreparedStatement stmta = null;
				ResultSet rsa = null;
				conna = SQLDBhelper.getConnection();
				try {
					stmta = conna.prepareStatement(sqla);
					stmta.setString(1, CaseNo);
					rsa = stmta.executeQuery();
					while(rsa.next()) {
						String	date=rsa.getString("iidate");
						String ifdate1=rsa.getString("ifdate");
						if(ifdate1!=null&&!"".equals(ifdate1)){
							ifdate=ifdate1;
						}
						if(date!=null&&!"".equals(date)){
							imoneytype1=rsa.getInt("imoneytype");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(date);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH) ;

							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year1);
								stmt2.setInt(2, month1);
								stmt2.setInt(3, imoneytype1);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
									if(huilv==0){
										huilv=1;
									}
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
							String sql3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn3 = null;
							PreparedStatement stmt3 = null;
							ResultSet rs3 = null;
							conn3 = SQLDBhelper.getConnection();
							try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setInt(1, year1);
								stmt3.setInt(2, month1);
								stmt3.setInt(3, 1);
								rs3 = stmt3.executeQuery();
								if(rs3.next()) {
									huilv1=rs3.getDouble(1);
									if(huilv1==0){
										huilv1=1;
									}
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
							if(huilv==0){
								huilv=1;
							}
							double ifmoneya=0.0;
							if(imoneytype1==1){
								ifmoneya= rsa.getDouble("ifmoney")*huilv;
								if(ifmoneya!=0){
									totalrmb2+=rsa.getDouble("ifmoney")*huilv;
								}else{
									totalrmb2+=rsa.getDouble("iimoney")*huilv;
								}
							}else if(imoneytype1==2){
								ifmoneya= rsa.getDouble("ifmoney");
								if(ifmoneya!=0){
									totalrmb2+=rsa.getDouble("ifmoney");
								}else{
									totalrmb2+=rsa.getDouble("iimoney");
								}
							}else{
								ifmoneya= rsa.getDouble("ifmoney")*huilv;
								if(ifmoneya!=0){
									totalrmb2+=rsa.getDouble("ifmoney")*huilv;
								}else{

									totalrmb2+=rsa.getDouble("iimoney")*huilv;
								}

							}
						}
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
					SQLDBhelper.returnConnection(conna);
				}



				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select friMoney,moneytype,Convert(varchar(100),friFacDate,23)date from factoryfund  where CaseNo=? and bargainno is not null";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, CaseNo);
					rs2= stmt2.executeQuery();
					while(rs2.next()) {
						int type=rs2.getInt("moneytype");
						String date =rs2.getString("date");

						if(type==2){
							payfactory += rs2.getDouble("friMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
							payfactory += rs2.getDouble("friMoney")*huilva;
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
					}
					SQLDBhelper.close(conn2,stmt2,rs2);

				}

				String sql6 = "select CaseNo,BackSingleCase from itemCase where customercode=( select customercode from itemCase where CaseNo=? )";
				Connection conn6 = null;
				PreparedStatement stmt6 = null;
				ResultSet rs6 = null;
				conn6 = SQLDBhelper.getConnection();
				try {
					stmt6 = conn6.prepareStatement(sql6);

					stmt6.setString(1, CaseNo);
					rs6 = stmt6.executeQuery();
					if(rs6.next()) {
						String CaseNo1=rs6.getString("CaseNo");
						if(CaseNo1==CaseNo){
							BackSingleCase=rs6.getString("BackSingleCase");
						}else{
							Connection conn10 = null;
							PreparedStatement stmt10= null;
							ResultSet rs10 = null;
							String sql10 = "select BackSingleCase from itemCase where CaseNo=?";
							conn10 = SQLDBhelper.getConnection();
							try {
								stmt10 = conn10.prepareStatement(sql10);
								stmt10.setString(1, CaseNo);
								rs10= stmt10.executeQuery();
								if(rs10.next()) {
									BackSingleCase=rs10.getString("BackSingleCase");

								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (stmt10 != null) {
									try {
										stmt10.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (rs10 != null) {
									try {
										rs10.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.close(conn10,stmt10,rs10);

							}
						}
						if(BackSingleCase!=null&&!"".equals(BackSingleCase)){
							Connection conn7 = null;
							PreparedStatement stmt7 = null;
							ResultSet rs7 = null;
							String sql7 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? )) and  datediff(day,iidate,getdate())>1095";
							conn7 = SQLDBhelper.getConnection();
							try {
								stmt7 = conn7.prepareStatement(sql7);
								stmt7.setString(1, BackSingleCase);
								rs7= stmt7.executeQuery();
								if(rs7.next()) {

									int id = rs7.getInt(1);
									if(id>0){
										degreeDifficulty=6;
									}else{
										Connection conn8 = null;
										PreparedStatement stmt8 = null;
										ResultSet rs8 = null;
										String sql8 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? )) and  datediff(day,iidate,getdate())>730 and  datediff(day,iidate,getdate())<1095";
										conn8 = SQLDBhelper.getConnection();
										try {
											stmt8 = conn8.prepareStatement(sql8);
											stmt8.setString(1, BackSingleCase);
											rs8= stmt8.executeQuery();
											if(rs8.next()) {

												int id1 = rs8.getInt(1);
												if(id1>0){
													degreeDifficulty=5;
												}else{
													Connection conn9 = null;
													PreparedStatement stmt9 = null;
													ResultSet rs9 = null;
													String sql9 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? )) and  datediff(day,iidate,getdate())>365 and  datediff(day,iidate,getdate())<730";
													conn9 = SQLDBhelper.getConnection();
													try {
														stmt9 = conn9.prepareStatement(sql9);
														stmt9.setString(1, BackSingleCase);
														rs9= stmt9.executeQuery();
														if(rs9.next()) {

															int id2 = rs9.getInt(1);
															if(id2>0){
																degreeDifficulty=4;
															}else{
																Connection conn10 = null;
																PreparedStatement stmt10= null;
																ResultSet rs10 = null;
																String sql10 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? ))  and  datediff(day,iidate,getdate())<365";
																conn10 = SQLDBhelper.getConnection();
																try {
																	stmt10 = conn10.prepareStatement(sql10);
																	stmt10.setString(1, BackSingleCase);
																	rs10= stmt10.executeQuery();
																	if(rs10.next()) {

																		int id3 = rs10.getInt(1);
																		if(id3>0){
																			degreeDifficulty=3;
																		}
																	}
																} catch (Exception e) {
																	e.printStackTrace();
																} finally {
																	if (stmt10 != null) {
																		try {
																			stmt10.close();
																		} catch (SQLException e) {
																			e.printStackTrace();
																		}
																	}
																	if (rs10 != null) {
																		try {
																			rs10.close();
																		} catch (SQLException e) {
																			e.printStackTrace();
																		}
																	}
																	SQLDBhelper.close(conn10,stmt10,rs10);

																}



															}
														}
													} catch (Exception e) {
														e.printStackTrace();
													} finally {
														if (stmt9 != null) {
															try {
																stmt9.close();
															} catch (SQLException e) {
																e.printStackTrace();
															}
														}
														if (rs9 != null) {
															try {
																rs9.close();
															} catch (SQLException e) {
																e.printStackTrace();
															}
														}
														SQLDBhelper.close(conn9,stmt9,rs9);

													}


												}
											}
										} catch (Exception e) {
											e.printStackTrace();
										} finally {
											if (stmt8 != null) {
												try {
													stmt8.close();
												} catch (SQLException e) {
													e.printStackTrace();
												}
											}
											if (rs8 != null) {
												try {
													rs8.close();
												} catch (SQLException e) {
													e.printStackTrace();
												}
											}
											SQLDBhelper.close(conn8,stmt8,rs8);

										}


									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (stmt7 != null) {
									try {
										stmt7.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (rs7 != null) {
									try {
										rs7.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.close(conn7,stmt7,rs7);

							}
						}else{


							Connection conn10 = null;
							PreparedStatement stmt10= null;
							ResultSet rs10 = null;
							String sql10 = "select COUNT(*) from  InvoiceInfo where iCaseNo in (select CaseNo from itemCase where customercode in ( select customercode from itemCase where CaseNo=? ))  and  datediff(day,iidate,getdate())>365";
							conn10 = SQLDBhelper.getConnection();
							try {
								stmt10 = conn10.prepareStatement(sql10);
								stmt10.setString(1, CaseNo);
								rs10= stmt10.executeQuery();
								if(rs10.next()) {

									int id3 = rs10.getInt(1);
									if(id3>0){
										degreeDifficulty=2;
									}else{

										Connection conna1 = null;
										PreparedStatement stmta1= null;
										ResultSet rsa1 = null;
										String sqla1 = " select datediff(day,b.CreateTime,a.ifdate) from (select min(ifdate)ifdate from invoiceinfo where icaseno in"
												+ "( select caseno from itemcase where customercode in( select customercode from itemcase where caseno=?)))a,"
												+ "(select min(CreateTime)CreateTime from itemcase where customercode in( select customercode from itemcase where caseno=?))b ";
										conna1 = SQLDBhelper.getConnection();
										try {
											stmta1 = conna1.prepareStatement(sqla1);
											stmta1.setString(1, CaseNo);
											stmta1.setString(2, CaseNo);
											rsa1= stmta1.executeQuery();
											if(rsa1.next()) {

												int id1 = rsa1.getInt(1);
												if(id1>365){
													degreeDifficulty=2;
												}
											}
										} catch (Exception e) {
											e.printStackTrace();
										} finally {
											if (stmta1 != null) {
												try {
													stmta1.close();
												} catch (SQLException e) {
													e.printStackTrace();
												}
											}
											if (rsa1 != null) {
												try {
													rsa1.close();
												} catch (SQLException e) {
													e.printStackTrace();
												}
											}
											SQLDBhelper.close(conna1,stmta1,rsa1);

										}
										if(degreeDifficulty==0){

											degreeDifficulty=1;
										}
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (stmt10 != null) {
									try {
										stmt10.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (rs10 != null) {
									try {
										rs10.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.close(conn10,stmt10,rs10);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rs6 != null) {
						try {
							rs6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt6 != null) {
						try {
							stmt6.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conn6);
				}


				Connection conn3 = null;
				PreparedStatement stmt3 = null;
				ResultSet rs3 = null;
				String sql3 = "select otherMoney,moneytype,Convert(varchar(100),OtherDate,23)date from factoryfund  where CaseNo=?  and otherMoney is not null";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setString(1, CaseNo);
					rs3= stmt3.executeQuery();
					while(rs3.next()) {
						int type=rs3.getInt("moneytype");
						String date =rs3.getString("date");

						if(type==2){
							payother += rs3.getDouble("otherMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
							payother += rs3.getDouble("otherMoney")*huilva;
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
				String sql4 = "select wuliuMoney,moneytype,Convert(varchar(100),WuliuDate,23)date from 	wuliu  where CaseNo=? ";
				conn4 = SQLDBhelper.getConnection();
				try {
					stmt4 = conn4.prepareStatement(sql4);
					stmt4.setString(1, CaseNo);
					rs4= stmt4.executeQuery();
					while(rs4.next()) {
						int type=rs4.getInt("moneytype");
						String date =rs4.getString("date");

						if(type==2){
							paywulliu += rs4.getDouble("wuliuMoney");
						}else if(type!=2){
							double huilva=getHuilva(date,type);
							paywulliu += rs4.getDouble("wuliuMoney")*huilva;
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
				int  project_situation=0;
				Connection conn5 = null;
				PreparedStatement stmt5 = null;
				ResultSet rs5 = null;
				String sql5 = "select ic.project_situation,ic.quality_deduction_project,ic.CustomerManager,ic.customercode,ic.MerchandManager1,ic.MerchandManager2,"
						+ "ic.Engineer1,ic.Engineer2,ic.Engineer3,ic.Merchandising,ic.zhijian1,ic.zhijian2,ic.ProductDescE"
						+ ",ic.ProductDescC,ic.OriginalPurchase,ic.MaturePurchase,ic.CaseStatus,ic.master_quality_inspection" +
						" ,ic.QualityInspector,ic.QualityInspector1,ic.QualityInspector2,ic.QualityInspector3,ic.QualityInspector4" +
						",ic.QualityInspector5,ic.QualityInspector6,ic.QualityInspector7  " +
						"  from itemCase ic left join invoiceInfo ii on ic.CaseNo=ii.iCaseNo  "
						+ " where CaseNo=? ";
				conn5 = SQLDBhelper.getConnection();
				try {
					stmt5 = conn5.prepareStatement(sql5);
					stmt5.setString(1, CaseNo);
					rs5= stmt5.executeQuery();
					while(rs5.next()) {
						bo.setCustomerManager(rs5.getString("CustomerManager"));
						bo.setMerchandManager1(rs5.getString("MerchandManager1"));
						bo.setCustomerId(rs5.getInt("customercode"));
						bo.setOriginalPurchase(rs5.getString("OriginalPurchase"));
						bo.setMaturePurchase(rs5.getString("MaturePurchase"));
						bo.setMerchandising(rs5.getString("Merchandising"));
						bo.setMerchandManager2(rs5.getString("MerchandManager2"));
						bo.setZhijian1(rs5.getString("zhijian1"));
						bo.setZhijian2(rs5.getString("zhijian2"));
						bo.setMasterQualityInspection(rs5.getString("master_quality_inspection"));
						bo.setQualityInspector(rs5.getString("QualityInspector"));
						bo.setQualityInspector1(rs5.getString("QualityInspector1"));
						bo.setQualityInspector2(rs5.getString("QualityInspector2"));
						bo.setQualityInspector3(rs5.getString("QualityInspector3"));
						bo.setQualityInspector4(rs5.getString("QualityInspector4"));
						bo.setQualityInspector5(rs5.getString("QualityInspector5"));
						bo.setQualityInspector6(rs5.getString("QualityInspector6"));
						bo.setQualityInspector7(rs5.getString("QualityInspector7"));
						bo.setProductdescc(rs5.getString("ProductDescC"));
						bo.setProductdesce(rs5.getString("ProductDescE"));
						bo.setQualityDeductionProject(rs5.getInt("quality_deduction_project"));
						project_situation=rs5.getInt("project_situation");
						int caseStatus=rs5.getInt("CaseStatus");
						if(caseStatus==0||caseStatus==14||caseStatus==10||caseStatus==5){
							bo.setCaseStatus(1);
						}else{
							bo.setCaseStatus(0);
						}
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
				String sqlm1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?    ";
				Connection connm1 = null;
				PreparedStatement stmtm1 = null;
				ResultSet rsm1 = null;
				connm1 = SQLDBhelper.getConnection();
				try {
					stmtm1 = connm1.prepareStatement(sqlm1);
					stmtm1.setString(1, CaseNo);


					rsm1 = stmtm1.executeQuery();
					while(rsm1.next()) {
						String	date=rsm1.getString("iidate");
						String ifdate1=rsm1.getString("ifdate");
						String allmonth="";
						if(month<10){
							allmonth="0"+month;
						}else{
							allmonth=String.valueOf(month);
						}
						String ymonth=year+"-"+allmonth;
						if(ifdate1!=null&&!"".equals(ifdate1)){
							boolean a = ifdate1.contains(ymonth);
							if(a!=false){
								ifdatea=ifdate1;
							}
							ifdate=ifdate1;
						}else{
							ifdate=date;
						}
						if(ifdate!=null&&!"".equals(ifdate)){

							imoneytype1=rsm1.getInt("imoneytype");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(ifdate);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH)+1 ;

							String sqlm2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection connm2 = null;
							PreparedStatement stmtm2 = null;
							ResultSet rsm2 = null;
							connm2 = SQLDBhelper.getConnection();
							try {
								stmtm2 = connm2.prepareStatement(sqlm2);
								stmtm2.setInt(1, year1);
								stmtm2.setInt(2, month1);
								stmtm2.setInt(3, imoneytype1);
								rsm2 = stmtm2.executeQuery();
								if(rsm2.next()) {
									huilv=rsm2.getDouble(1);
									if(huilv==0){
										huilv=1;
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsm2 != null) {
									try {
										rsm2.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtm2 != null) {
									try {
										stmtm2.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(connm2);
							}
							String sqlm3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection connm3 = null;
							PreparedStatement stmtm3 = null;
							ResultSet rsm3 = null;
							connm3 = SQLDBhelper.getConnection();
							try {
								stmtm3 = connm3.prepareStatement(sqlm3);
								stmtm3.setInt(1, year1);
								stmtm3.setInt(2, month1);
								stmtm3.setInt(3, 1);
								rsm3 = stmtm3.executeQuery();
								if(rsm3.next()) {
									huilv1=rsm3.getDouble(1);
									if(huilv1==0){
										huilv1=1;
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsm3 != null) {
									try {
										rsm3.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtm3 != null) {
									try {
										stmtm3.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(connm3);
							}
							if(huilv==0){
								huilv=1;
							}

							if(imoneytype1==1){
								Double money=rsm1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
									ifmoneym+=rsm1.getDouble("ifmoney");
									totalrmbm+=rsm1.getDouble("ifmoney")*huilv;
									totalSalesUs+=rsm1.getDouble("ifmoney");
								}else{
									ifmoneym+=rsm1.getDouble("iimoney");
									totalrmbm+=rsm1.getDouble("iimoney")*huilv;
									totalSalesUs+=rsm1.getDouble("iimoney");
								}

							}else if(imoneytype1==2){
								Double money=rsm1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
									ifmoneym+=rsm1.getDouble("ifmoney")/huilv;
									totalrmbm+=rsm1.getDouble("ifmoney");
									totalSalesUs+=rsm1.getDouble("ifmoney")/huilv1;
								}else{
									ifmoneym+=rsm1.getDouble("iimoney")/huilv;
									totalrmbm+=rsm1.getDouble("iimoney");
									totalSalesUs+=rsm1.getDouble("iimoney")/huilv1;
								}
							}else{

								Double money=rsm1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){
									ifmoneym+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
									totalrmbm+=rsm1.getDouble("ifmoney")*huilv;
									totalSalesUs+=(rsm1.getDouble("ifmoney")/huilv1)*huilv;
								}else{
									ifmoneym+=(rsm1.getDouble("iimoney")/huilv1)*huilv;
									totalrmbm+=rsm1.getDouble("iimoney")*huilv;
									totalSalesUs+=(rsm1.getDouble("iimoney")/huilv1)*huilv;
								}

							}

						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsm1 != null) {
						try {
							rsm1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmtm1 != null) {
						try {
							stmtm1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(connm1);
				}


				double debitDeduction=getDebitDeduction(CaseNo);//进账扣款
				double factoryDeduction=getFactoryDeduction(CaseNo);//工厂扣款
				String ifdatex="";
				double profitrmb1=0.0;
				double profitmargin2=0.0;
				String profitmargin3="";
				totalrmbm=totalrmbm-debitDeduction;
				totalrmb1=paywulliu+payother+payfactory-factoryDeduction;
				profitrmb=totalrmbm-totalrmb1;
				profitrmb1=totalrmbm-totalrmb1;
				totalSalesUs=getTotalSalseUs(totalrmbm,year,month);
				double   f =0.0;
				double   f1 =0.0;
				double   f2 =0.0;
				double   f3 =0.0;
				double   f4 =0.0;
				double   f5 =0.0;
				if(totalrmb1!=0&&!"".equals(totalrmb1)){
					BigDecimal   b   =   new   BigDecimal(totalrmb1);
					f1   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();

				}

				if(profitrmb!=0&&!"".equals(profitrmb)){
					BigDecimal   b   =   new   BigDecimal(profitrmb);
					f2   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();

				}

				if(ifmoney!=0&&!"".equals(ifmoney)&&!"NaN".equals(ifmoney)&&!"Infinity".equals(ifmoney)){
					System.out.print(ifmoney);
					BigDecimal   b   =   new   BigDecimal(ifmoney);

					f4   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();

				}
				double saleallMoney=0.0;
				String sqlx = "  select imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?  ";
				Connection connx = null;
				PreparedStatement stmtx = null;
				ResultSet rsx = null;
				connx= SQLDBhelper.getConnection();
				try {
					stmtx= connx.prepareStatement(sqlx);
					stmtx.setString(1, CaseNo);
					rsx = stmtx.executeQuery();
					while(rsx.next()) {
						String	date=rsx.getString("iidate");
						String ifdate1=rsx.getString("ifdate");

						if(ifdate1!=null&&!"".equals(ifdate1)){
							ifdate=ifdate1;
							if(ifdatex==null||"".equals(ifdatex)){
								ifdatex=ifdate1;
							}else{
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
								Date date1 = sdf.parse(ifdate1);
								Date date2 = sdf.parse(ifdatex);
								if(sdf.format(date1).compareTo(sdf.format(date2))>0){
									ifdatex=ifdate1;
								}
							}

						}else{

							ifdate=date;

						}
						if(ifdate!=null&&!"".equals(ifdate)){
							imoneytype1=rsx.getInt("imoneytype");

							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(ifdate);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH)+1 ;

							String sqlm = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection connm = null;
							PreparedStatement stmtm = null;
							ResultSet rsm = null;
							connm = SQLDBhelper.getConnection();
							try {
								stmtm = connm.prepareStatement(sqlm);
								stmtm.setInt(1, year1);
								stmtm.setInt(2, month1);
								stmtm.setInt(3, imoneytype1);
								rsm = stmtm.executeQuery();
								if(rsm.next()) {
									huilv=rsm.getDouble(1);
									if(huilv==0){
										huilv=1;
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsm != null) {
									try {
										rsm.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtm != null) {
									try {
										stmtm.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(connm);
							}
							String sqln = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection connn = null;
							PreparedStatement stmtn = null;
							ResultSet rsn = null;
							connn = SQLDBhelper.getConnection();
							try {
								stmtn = connn.prepareStatement(sqln);
								stmtn.setInt(1, year1);
								stmtn.setInt(2, month1);
								stmtn.setInt(3, 1);
								rsn = stmtn.executeQuery();
								if(rsn.next()) {
									huilv1=rsn.getDouble(1);
									if(huilv1==0){
										huilv1=1;
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsn != null) {
									try {
										rsn.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtn != null) {
									try {
										stmtn.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(connn);
							}
							if(huilv==0){
								huilv=1;
							}

							if(imoneytype1==1){
								Double money=rsx.getDouble("ifmoney");
								if(money!=null&&!"".equals(money) &&money!=0){

									saleallMoney+=rsx.getDouble("ifmoney")*huilv;
								}else{

									saleallMoney+=rsx.getDouble("iimoney")*huilv;
								}

							}else if(imoneytype1==2){
								Double money=rsx.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){

									saleallMoney+=rsx.getDouble("ifmoney");
								}else{

									saleallMoney+=rsx.getDouble("iimoney");
								}
							}else{

								Double money=rsx.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)&&money!=0){

									saleallMoney+=rsx.getDouble("ifmoney")*huilv;
								}else{

									saleallMoney+=rsx.getDouble("iimoney")*huilv;
								}

							}

						}


					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsx != null) {
						try {
							rsx.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmtx != null) {
						try {
							stmtx.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(connx);
				}
				double fm=0.0;
				if(saleallMoney!=0&&!"".equals(saleallMoney)&&!"NaN".equals(saleallMoney)&&!"Infinity".equals(saleallMoney)){
					BigDecimal   b   =   new   BigDecimal(saleallMoney);
					fm   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				double totalAmountOfArrival=0.0;
				double totalAmountReceivable=0.0;
				String sqlb = "  select sum(iimoney)iimoney,sum(ifmoney)ifmoney,iCaseNo from invoiceinfo where icaseno=?  group by iCaseNo";
				Connection connb = null;
				PreparedStatement stmtb = null;
				ResultSet rsb = null;
				connb = SQLDBhelper.getConnection();
				try {
					stmtb= connb.prepareStatement(sqlb);
					stmtb.setString(1, CaseNo);
					rsb = stmtb.executeQuery();
					if(rsb.next()) {
						totalAmountOfArrival=rsb.getDouble("ifmoney");
						totalAmountReceivable=rsb.getDouble("iimoney");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsb != null) {
						try {
							rsb.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmtb != null) {
						try {
							stmtb.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(connb);
				}
				double Pay_Moeny=0.0;
				double Get_Moeny=0.0;
				double iimoney1=0.0;
				double ifmoney1=0.0;
				double factory_yue_money=0.0;
				String sqlf = "select sum(iimoney)iimoney from InvoiceInfo  where iCaseNo=?     ";
				Connection connf = null;
				PreparedStatement stmtf = null;
				ResultSet rsf = null;
				connf = SQLDBhelper.getConnection();
				try {
					stmtf = connf.prepareStatement(sqlf);
					stmtf.setString(1, CaseNo);
					rsf = stmtf.executeQuery();
					while(rsf.next()) {
						iimoney1=rsf.getDouble("iimoney");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsf != null) {
						try {
							rsf.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmtf != null) {
						try {
							stmtf.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(connf);
				}
				String ymonth1="";
				if(month==12){
					int yeara=year+1;
					ymonth1=yeara+"-"+"01-01";
				}else{
					int montha=month+1;
					String allmonth="";
					if(month<10){
						allmonth="0"+montha;
					}else{
						allmonth=String.valueOf(montha);
					}
					ymonth1=year+"-"+allmonth+"-"+"01";
				}

				String sqly = "select sum(ifmoney)ifmoney from InvoiceInfo  where iCaseNo=? and DATEDIFF(day,'"+ymonth1+"',ifdate)<0    ";
				Connection conny = null;
				PreparedStatement stmty = null;
				ResultSet rsy = null;
				conny = SQLDBhelper.getConnection();
				try {
					stmty = conny.prepareStatement(sqly);
					stmty.setString(1, CaseNo);
					rsy = stmty.executeQuery();
					while(rsy.next()) {
						ifmoney1=rsy.getDouble("ifmoney");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsy != null) {
						try {
							rsy.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmty != null) {
						try {
							stmty.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conny);
				}
				String sqlg = "select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny from Tab_Factory_Money left join itemCase  on itemCase.CaseNo=Tab_Factory_Money.Case_No where CaseNo=? and DATEDIFF(day,'"+ymonth1+"',Date_time)<0   ";
				Connection conng = null;
				PreparedStatement stmtg = null;
				ResultSet rsg = null;
				conng = SQLDBhelper.getConnection();
				try {
					stmtg = conng.prepareStatement(sqlg);
					stmtg.setString(1, CaseNo);
					rsg = stmtg.executeQuery();
					while(rsg.next()) {
						Pay_Moeny=rsg.getDouble("Pay_Moeny");
						Get_Moeny=rsg.getDouble("Get_Moeny");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (rsg != null) {
						try {
							rsg.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmtg != null) {
						try {
							stmtg.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					SQLDBhelper.returnConnection(conng);
				}

				if(Pay_Moeny-Get_Moeny<=50 &&Pay_Moeny!=0 && project_situation==1){

					bo.setIsInvoice(1);
				}else{

					bo.setIsInvoice(0);
				}
				bo.setProjectId(CaseNo);
				bo.setAccountPayableRmb(f1);
				bo.setSalesThisPeriodRmb(f5);
				bo.setTotalGrossProfitRmb(profitrmb1);
				bo.setSalesThisPeriodUs(f4);
				bo.setTotalGrossProfitUs(f4);
				bo.setTotalSalesRmb(totalrmbm);
				bo.setTotalGrossProfitMargin(profitmargin3);
				bo.setLatest_payment_time(ifdatex);
				bo.setTotalSalesUs(totalSalesUs);
				bo.setPayment_time(ifdatea);
				bo.setDegreeDifficulty(degreeDifficulty);
				bo.setHeritageProject(BackSingleCase);
				bo.setTotalAmountOfArrival(ifmoney1);
				bo.setTotalAmountReceivable(iimoney1);
				//System.out.print(totalrmb2+";"+f1+";"+profitmargin3);
				list.add(bo);
				System.out.print(ifdate+";"+ifdatea);
			}
		}
		return list;
	}

	private double getTotalSalseUs(double totalrmbm, int year, int month) {
		Double totalSalseUs1=0.00;
		double tmpD1=getHuilva(year+"-"+month+"-"+01,1);
		double totalSalseUs   =  totalrmbm/tmpD1;
		if(totalSalseUs!=0&&!"".equals(totalSalseUs)&&!"NaN".equals(totalSalseUs)&&!"Infinity".equals(totalSalseUs)){
			BigDecimal   b   =   new   BigDecimal(totalSalseUs);
			totalSalseUs1   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();
		}

				return totalSalseUs1;

	}

	private double getFactoryDeduction(String caseNo) {
		double money=0.00;
		String sql2 = "select sum(amount_deduction)amount_deduction,caseno from contract_deduction where caseno=? group by caseno";
		Connection conn2 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		conn2 = SQLDBhelper.getConnection();
		try {
			stmt2 = conn2.prepareStatement(sql2);
			stmt2.setString(1, caseNo);
			rs2 = stmt2.executeQuery();
			if (rs2.next()) {
				double ifmoney = rs2.getDouble("amount_deduction");

				money=ifmoney;
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
		return money;

	}

	private double getDebitDeduction(String caseNo) {
		double money=0.00;
		boolean save=getTotalArrival(caseNo);
        if(save==true) {
			String sql2 = "select ifmoney,ifdate from deduction_actual_income where caseno=?";
			Connection conn2 = null;
			PreparedStatement stmt2 = null;
			ResultSet rs2 = null;
			conn2 = SQLDBhelper.getConnection();
			try {
				stmt2 = conn2.prepareStatement(sql2);
				stmt2.setString(1, caseNo);
				rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					double ifmoney = rs2.getDouble("ifmoney");
					double tmpD1=getHuilva(rs2.getString("ifdate"),1);
					money=ifmoney*tmpD1;
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
		}else if(save==false){
			String sql2 = "select iimoney,iidate from debit_deduction where caseno=?";
			Connection conn2 = null;
			PreparedStatement stmt2 = null;
			ResultSet rs2 = null;
			conn2 = SQLDBhelper.getConnection();
			try {
				stmt2 = conn2.prepareStatement(sql2);
				stmt2.setString(1, caseNo);
				rs2 = stmt2.executeQuery();
				if (rs2.next()) {
					double ifmoney = rs2.getDouble("iimoney");
					double tmpD1=getHuilva(rs2.getString("iidate"),1);
					money=ifmoney*tmpD1;
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
		}
		return money;
	}

	private boolean getTotalArrival(String caseNo) {
		boolean save=false;
		String sql2 = "select count(*) from invoiceinfo where icaseno='"+caseNo+"' and  ifmoney is null";
		Connection conn2 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		conn2 = SQLDBhelper.getConnection();
		try {
			stmt2 = conn2.prepareStatement(sql2);

			rs2 = stmt2.executeQuery();
			if(rs2.next()) {
				int num=rs2.getInt(1);
				if(num!=0){
					save=false;
				}else{
					save=true;
				}
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
		return save;
	}

	private double getHuilva(String date, int type) {
		double huilv=0.00;
		String[]dates=date.split("-");
		int year=Integer.parseInt(dates[0]);
		int month=Integer.parseInt(dates[1]);
		String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
		Connection conn2 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		conn2 = SQLDBhelper.getConnection();
		try {
			stmt2 = conn2.prepareStatement(sql2);
			stmt2.setInt(1, year);
			stmt2.setInt(2, month);
			stmt2.setInt(3, type);
			rs2 = stmt2.executeQuery();
			if(rs2.next()) {
				huilv=rs2.getDouble(1);
				if(huilv==0){
					huilv=1;
				}
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
		}
		return huilv;
	}
	@Override
	public List<Bonusdata> updateGrossProfit(int year, int month) {
		List<Bonusdata> list = new ArrayList<Bonusdata>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String ifdate="";

		String sql = "select distinct icaseno from invoiceinfo where year(ifdate)=? and month(ifdate)=?  ";
		conn = SQLDBhelper.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, year+"");
			stmt.setString(2, month+"");
			rs = stmt.executeQuery();
			while(rs.next()) {
				double totalrmbm=0.0;
				int imoneytype1=0;
				double totalrmb=0.0;
				double totalrmb1=0.0;
				double profitrmb=0.0;
				double payfactory=0.0;
				double payother=0.0;
				double paywulliu=0.0;
				double huilv=0.0;
				double huilv1=0.0;
				double ifmoney=0.0;

				String  CaseNo=rs.getString(1);
				String sql1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?   and  ifmoney is not null ";
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
						String ifdate1=rs1.getString("ifdate");

						if(ifdate1!=null&&!"".equals(ifdate1)){
							String ymonth1="";
							if(month==12){
								int yeara=year+1;
								ymonth1=yeara+"-"+"01-01";
							}else{
								int montha=month+1;
								String allmonth="";
								if(month<10){
									allmonth="0"+montha;
								}else{
									allmonth=String.valueOf(montha);
								}
								ymonth1=year+"-"+allmonth+"-"+"01";
							}
							boolean a = ifdate1.contains(ymonth1);
							if(a!=false){

								ifdate=ifdate1;
							}else{

								ifdate=date;
							}
						}
						if(ifdate!=null&&!"".equals(ifdate)){

							imoneytype1=rs1.getInt("imoneytype");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							Date dt = null;
							try {
								dt = sdf.parse(ifdate);
								cal.setTime(dt);
							} catch (Exception e) {
								e.printStackTrace();
							}

							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH)+1 ;

							String sql2 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn2 = null;
							PreparedStatement stmt2 = null;
							ResultSet rs2 = null;
							conn2 = SQLDBhelper.getConnection();
							try {
								stmt2 = conn2.prepareStatement(sql2);
								stmt2.setInt(1, year1);
								stmt2.setInt(2, month1);
								stmt2.setInt(3, imoneytype1);
								rs2 = stmt2.executeQuery();
								if(rs2.next()) {
									huilv=rs2.getDouble(1);
									if(huilv==0){
										huilv=1;
									}
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
							String sql3 = "select ExchageRage from Tab_ExchageRate_Date where eYear=? and eMonth=? and eType=?";
							Connection conn3 = null;
							PreparedStatement stmt3 = null;
							ResultSet rs3 = null;
							conn3 = SQLDBhelper.getConnection();
							try {
								stmt3 = conn3.prepareStatement(sql3);
								stmt3.setInt(1, year1);
								stmt3.setInt(2, month1);
								stmt3.setInt(3, 1);
								rs3 = stmt3.executeQuery();
								if(rs3.next()) {
									huilv1=rs3.getDouble(1);
									if(huilv1==0){
										huilv1=1;
									}
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
							if(huilv==0){
								huilv=1;
							}

							if(imoneytype1==1){
								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)){
									ifmoney+=rs1.getDouble("ifmoney");
									totalrmb+=rs1.getDouble("ifmoney")*huilv;

								}else{
									ifmoney+=rs1.getDouble("iimoney");
									totalrmb+=rs1.getDouble("iimoney")*huilv;

								}

							}else if(imoneytype1==2){
								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)){
									ifmoney+=rs1.getDouble("ifmoney")/huilv1;
									totalrmb+=rs1.getDouble("ifmoney");

								}else{
									ifmoney+=rs1.getDouble("iimoney")/huilv1;
									totalrmb+=rs1.getDouble("iimoney");

								}
							}else{

								Double money=rs1.getDouble("ifmoney");
								if(money!=null&&!"".equals(money)){
									ifmoney+=(rs1.getDouble("ifmoney")/huilv1)*huilv;
									totalrmb+=rs1.getDouble("ifmoney")*huilv;

								}else{
									ifmoney+=(rs1.getDouble("iimoney")/huilv1)*huilv;
									totalrmb+=rs1.getDouble("iimoney")*huilv;

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



				Connection conn2 = null;
				PreparedStatement stmt2 = null;
				ResultSet rs2 = null;
				String sql2 = "select sum(friMoney) from factoryfund  where CaseNo=? and bargainno is not null";
				conn2 = SQLDBhelper.getConnection();
				try {
					stmt2 = conn2.prepareStatement(sql2);
					stmt2.setString(1, CaseNo);
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
				String sql3 = "select otherMoney,moneytype from factoryfund  where CaseNo=?  and otherMoney is not null";
				conn3 = SQLDBhelper.getConnection();
				try {
					stmt3 = conn3.prepareStatement(sql3);
					stmt3.setString(1, CaseNo);
					rs3= stmt3.executeQuery();
					while(rs3.next()) {
						int type=rs3.getInt("moneytype");
						if(type==2){
							payother += rs3.getDouble("otherMoney");
						}else if(type==1){
							payother += rs3.getDouble("otherMoney")*huilv1;
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
					stmt4.setString(1, CaseNo);
					rs4= stmt4.executeQuery();
					while(rs4.next()) {
						int type=rs4.getInt("moneytype");
						if(type==2){
							paywulliu += rs4.getDouble("wuliuMoney");
						}else if(type==1){
							paywulliu += rs4.getDouble("wuliuMoney")*huilv1;
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
               double profitrmb1=0.0;
				if(totalrmb!=0){
					totalrmb1=paywulliu+payother+payfactory;
					profitrmb=totalrmb-totalrmb1;
					profitrmb1=(profitrmb/totalrmb)*100;
					Connection conna = null;
					PreparedStatement stmta = null;
					int resulta = 0;
					ResultSet rsa = null;
					String sqla = "update itemcase set GrossProfit=? where caseno = ? ";
					conna = SQLDBhelper.getConnection();
					try {
						stmta = conna.prepareStatement(sqla);
						stmta.setString(1, profitrmb1+"%");
						stmta.setString(2, CaseNo);
						resulta = stmta.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (stmta != null) {
							try {
								stmta.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (rsa != null) {
							try {
								rsa.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						SQLDBhelper.close(conna,stmta,rsa);

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

		}
		return list;
	}

	@Override
	public List<Bonusdata> getQualityDeduction(String projectNo) {
		List<Bonusdata> list = new ArrayList<Bonusdata>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String [] json=projectNo.split(",");
		if(json.length>0) {
			String GrossProfit="";
			for (int i = 0; i < json.length; i++) {
				String  CaseNo=json[i];
				String sql1 = "select CaseNo,GrossProfit from itemcase where  caseno=? and quality_deduction_project=0";
				Connection conn1 = null;
				PreparedStatement stmt1 = null;
				ResultSet rs1 = null;
				conn1 = SQLDBhelper.getConnection();
				try {
					stmt1 = conn1.prepareStatement(sql1);
					stmt1.setString(1, CaseNo);
					rs1 = stmt1.executeQuery();
					if(rs1.next()) {
						Bonusdata bonus=new Bonusdata();
						GrossProfit=rs1.getString("GrossProfit");
						bonus.setProjectId(CaseNo);
						bonus.setGrossProfit(GrossProfit);
						list.add(bonus);
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

			}
		}
		return list;
	}

	@Override
	public List<Bonusdata> getAllDeductionProject(String projectNo, Date date1) {
		List<Bonusdata> list = new ArrayList<Bonusdata>();
		String [] json=projectNo.split(",");
		if(json.length>0) {
			String GrossProfit="";
			for (int i = 0; i < json.length; i++) {
				String  CaseNo=json[i];
				String sql = "select CaseNo from itemcase where  caseno=? and quality_deduction_project=0";
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				conn = SQLDBhelper.getConnection();
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, CaseNo);
					rs = stmt.executeQuery();
					if(rs.next()) {
						Bonusdata bo = new Bonusdata();
						double ifmoney = 0.0;
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						String ymonth1=sdf1.format(date1);
						double ifmoneym = 0.0;
						double totalrmbm = 0.0;
						int imoneytype1 = 0;
						int degreeDifficulty = 0;
						double iimoney = 0.0;
						double totalrmb = 0.0;
						double totalrmb1 = 0.0;
						double totalrmb2 = 0.0;
						double profitmargin = 0.0;
						String profitmargin1 = "";
						double profitrmb = 0.0;
						double totalSalesUs = 0.0;
						double profitus = 0.0;
						double payfactory = 0.0;
						double payother = 0.0;
						double paywulliu = 0.0;
						double huilv = 0.0;
						double huilv1 = 0.0;
						String productdesce = "";
						String productdescc = "";
						String BackSingleCase = "";
						String time = "";

						String sql1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=? and datediff(day,'"+ymonth1+"',ifdate)<0    and  ifmoney is not null ";
						Connection conn1 = null;
						PreparedStatement stmt1 = null;
						ResultSet rs1 = null;
						conn1 = SQLDBhelper.getConnection();
						try {
							stmt1 = conn1.prepareStatement(sql1);
							stmt1.setString(1, CaseNo);



							rs1 = stmt1.executeQuery();
							while (rs1.next()) {
								String date = rs1.getString("iidate");
								String ifdate = rs1.getString("ifdate");


								if (ifdate != null && !"".equals(ifdate)) {

									imoneytype1 = rs1.getInt("imoneytype");
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Calendar cal = Calendar.getInstance();
									Date dt = null;
									try {
										dt = sdf.parse(ifdate);
										cal.setTime(dt);
									} catch (Exception e) {
										e.printStackTrace();
									}

									int year1 = cal.get(Calendar.YEAR);
									int month1 = cal.get(Calendar.MONTH) + 1;
									huilv = getHuilv(year1, month1, imoneytype1);
									huilv1 = getHuilv(year1, month1, 1);

									if (huilv == 0) {
										huilv = 1;
									}

									if (imoneytype1 == 1) {
										Double money = rs1.getDouble("ifmoney");
										if (money != null && !"".equals(money)) {
											ifmoney += rs1.getDouble("ifmoney");
											totalrmb += rs1.getDouble("ifmoney") * huilv;

										} else {

											double huilva = getHuilva(date, imoneytype1);
											ifmoney += rs1.getDouble("iimoney");
											totalrmb += rs1.getDouble("iimoney") * huilva;

										}

									} else if (imoneytype1 == 2) {
										Double money = rs1.getDouble("ifmoney");
										if (money != null && !"".equals(money)) {
											ifmoney += rs1.getDouble("ifmoney") / huilv1;
											totalrmb += rs1.getDouble("ifmoney");

										} else {
											ifmoney += rs1.getDouble("iimoney") / huilv1;
											totalrmb += rs1.getDouble("iimoney");

										}
									} else {

										Double money = rs1.getDouble("ifmoney");
										if (money != null && !"".equals(money)) {
											ifmoney += (rs1.getDouble("ifmoney") / huilv1) * huilv;
											totalrmb += rs1.getDouble("ifmoney") * huilv;

										} else {
											double huilva = getHuilva(date, imoneytype1);
											ifmoney += (rs1.getDouble("iimoney") / huilv1) * huilv;
											totalrmb += rs1.getDouble("iimoney") * huilva;

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

						String sqla = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?  ";
						Connection conna = null;
						PreparedStatement stmta = null;
						ResultSet rsa = null;
						conna = SQLDBhelper.getConnection();
						try {
							stmta = conna.prepareStatement(sqla);
							stmta.setString(1, CaseNo);
							rsa = stmta.executeQuery();
							while (rsa.next()) {
								String date = rsa.getString("iidate");
								String ifdate1 = rsa.getString("ifdate");

								if (date != null && !"".equals(date)) {
									imoneytype1 = rsa.getInt("imoneytype");
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Calendar cal = Calendar.getInstance();
									Date dt = null;
									try {
										dt = sdf.parse(date);
										cal.setTime(dt);
									} catch (Exception e) {
										e.printStackTrace();
									}

									int year1 = cal.get(Calendar.YEAR);
									int month1 = cal.get(Calendar.MONTH);
									huilv = getHuilv(year1, month1, imoneytype1);
									huilv1 = getHuilv(year1, month1, 1);

									if (huilv == 0) {
										huilv = 1;
									}
									double ifmoneya = 0.0;
									if (imoneytype1 == 1) {
										ifmoneya = rsa.getDouble("ifmoney") * huilv;
										if (ifmoneya != 0) {
											totalrmb2 += rsa.getDouble("ifmoney") * huilv;
										} else {
											totalrmb2 += rsa.getDouble("iimoney") * huilv;
										}
									} else if (imoneytype1 == 2) {
										ifmoneya = rsa.getDouble("ifmoney");
										if (ifmoneya != 0) {
											totalrmb2 += rsa.getDouble("ifmoney");
										} else {
											totalrmb2 += rsa.getDouble("iimoney");
										}
									} else {
										ifmoneya = rsa.getDouble("ifmoney") * huilv;
										if (ifmoneya != 0) {
											totalrmb2 += rsa.getDouble("ifmoney") * huilv;
										} else {

											totalrmb2 += rsa.getDouble("iimoney") * huilv;
										}

									}
								}
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
							SQLDBhelper.returnConnection(conna);
						}
						Connection conn2 = null;
						PreparedStatement stmt2 = null;
						ResultSet rs2 = null;
						String sql2 = "select friMoney,moneytype,Convert(varchar(100),friFacDate,23)date from factoryfund  where CaseNo=? and bargainno is not null";
						conn2 = SQLDBhelper.getConnection();
						try {
							stmt2 = conn2.prepareStatement(sql2);
							stmt2.setString(1, CaseNo);
							rs2 = stmt2.executeQuery();
							while (rs2.next()) {
								int type = rs2.getInt("moneytype");
								String date = rs2.getString("date");

								if (type == 2) {
									payfactory += rs2.getDouble("friMoney");
								} else if (type != 2) {
									double huilva = getHuilva(date, type);
									payfactory += rs2.getDouble("friMoney") * huilva;
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
							}
							SQLDBhelper.close(conn2, stmt2, rs2);

						}
						String sql6 = "select CaseNo,BackSingleCase from itemCase where customercode=( select customercode from itemCase where CaseNo=? )";
						Connection conn6 = null;
						PreparedStatement stmt6 = null;
						ResultSet rs6 = null;
						conn6 = SQLDBhelper.getConnection();
						try {
							stmt6 = conn6.prepareStatement(sql6);

							stmt6.setString(1, CaseNo);
							rs6 = stmt6.executeQuery();
							if (rs6.next()) {
								String CaseNo1 = rs6.getString("CaseNo");
								if (CaseNo1 == CaseNo) {
									BackSingleCase = rs6.getString("BackSingleCase");
								} else {
									Connection conn10 = null;
									PreparedStatement stmt10 = null;
									ResultSet rs10 = null;
									String sql10 = "select BackSingleCase from itemCase where CaseNo=?";
									conn10 = SQLDBhelper.getConnection();
									try {
										stmt10 = conn10.prepareStatement(sql10);
										stmt10.setString(1, CaseNo);
										rs10 = stmt10.executeQuery();
										if (rs10.next()) {
											BackSingleCase = rs10.getString("BackSingleCase");

										}
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										if (stmt10 != null) {
											try {
												stmt10.close();
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
										if (rs10 != null) {
											try {
												rs10.close();
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
										SQLDBhelper.close(conn10, stmt10, rs10);

									}
								}
								degreeDifficulty = getDegreeDifficulty(BackSingleCase, CaseNo);

							}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (rs6 != null) {
								try {
									rs6.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (stmt6 != null) {
								try {
									stmt6.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.returnConnection(conn6);
						}

						payother = getPay(CaseNo);
						paywulliu = getPayWuLiu(CaseNo);

						Connection conn5 = null;
						PreparedStatement stmt5 = null;
						ResultSet rs5 = null;
						String sql5 = "select ic.quality_deduction_project,ic.CustomerManager,ic.customercode,ic.MerchandManager1,ic.MerchandManager2,"
								+ "ic.Engineer1,ic.Engineer2,ic.Engineer3,ic.zhijian1,ic.zhijian2,ic.ProductDescE"
								+ ",ic.ProductDescC,ic.Merchandising,ic.MaturePurchase,ic.OriginalPurchase,ic.CaseStatus,ic.master_quality_inspection " +
								" ,ic.QualityInspector,ic.QualityInspector1,ic.QualityInspector2,ic.QualityInspector3,ic.QualityInspector4 " +
								" ,ic.QualityInspector5,ic.QualityInspector6,ic.QualityInspector7     from itemCase ic left join invoiceInfo ii " +
								" on ic.CaseNo=ii.iCaseNo  "
								+ " where CaseNo=? ";
						conn5 = SQLDBhelper.getConnection();
						try {
							stmt5 = conn5.prepareStatement(sql5);
							stmt5.setString(1, CaseNo);
							rs5 = stmt5.executeQuery();
							while (rs5.next()) {
								bo.setCustomerManager(rs5.getString("CustomerManager"));
								bo.setMerchandManager1(rs5.getString("MerchandManager1"));
								bo.setCustomerId(rs5.getInt("customercode"));
								bo.setOriginalPurchase(rs5.getString("OriginalPurchase"));
								bo.setMerchandManager2(rs5.getString("MerchandManager2"));
								bo.setMerchandising(rs5.getString("Merchandising"));
								bo.setMaturePurchase(rs5.getString("MaturePurchase"));
								bo.setZhijian1(rs5.getString("zhijian1"));
								bo.setZhijian2(rs5.getString("zhijian2"));
								bo.setMasterQualityInspection(rs5.getString("master_quality_inspection"));
								bo.setQualityInspector(rs5.getString("QualityInspector"));
								bo.setQualityInspector1(rs5.getString("QualityInspector1"));
								bo.setQualityInspector2(rs5.getString("QualityInspector2"));
								bo.setQualityInspector3(rs5.getString("QualityInspector3"));
								bo.setQualityInspector4(rs5.getString("QualityInspector4"));
								bo.setQualityInspector5(rs5.getString("QualityInspector5"));
								bo.setQualityInspector6(rs5.getString("QualityInspector6"));
								bo.setQualityInspector7(rs5.getString("QualityInspector7"));
								bo.setProductdescc(rs5.getString("ProductDescC"));
								bo.setProductdesce(rs5.getString("ProductDescE"));
								bo.setQualityDeductionProject(rs5.getInt("quality_deduction_project"));
								int caseStatus = rs5.getInt("CaseStatus");
								if (caseStatus == 0 || caseStatus == 14 || caseStatus == 10 || caseStatus == 5) {
									bo.setCaseStatus(1);
								} else {
									bo.setCaseStatus(0);
								}
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
							SQLDBhelper.close(conn5, stmt5, rs5);

						}
						String sqlm1 = "select iCaseNo,imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?    ";
						Connection connm1 = null;
						PreparedStatement stmtm1 = null;
						ResultSet rsm1 = null;
						connm1 = SQLDBhelper.getConnection();
						try {
							stmtm1 = connm1.prepareStatement(sqlm1);
							stmtm1.setString(1, CaseNo);
							rsm1 = stmtm1.executeQuery();
							while (rsm1.next()) {
								String date = rsm1.getString("iidate");
								String ifdate = rsm1.getString("ifdate");


								if (ifdate != null && !"".equals(ifdate)) {

									imoneytype1 = rsm1.getInt("imoneytype");
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Calendar cal = Calendar.getInstance();
									Date dt = null;
									try {
										dt = sdf.parse(ifdate);
										cal.setTime(dt);
									} catch (Exception e) {
										e.printStackTrace();
									}

									int year1 = cal.get(Calendar.YEAR);
									int month1 = cal.get(Calendar.MONTH) + 1;

									huilv = getHuilv(year1, month1, imoneytype1);
									huilv1 = getHuilv(year1, month1, 1);
									if (huilv == 0) {
										huilv = 1;
									}

									if (imoneytype1 == 1) {
										Double money = rsm1.getDouble("ifmoney");
										if (money != null && !"".equals(money) && money != 0) {
											ifmoneym += rsm1.getDouble("ifmoney");
											totalrmbm += rsm1.getDouble("ifmoney") * huilv;
											totalSalesUs += rsm1.getDouble("ifmoney");
										} else {
											ifmoneym += rsm1.getDouble("iimoney");
											totalrmbm += rsm1.getDouble("iimoney") * huilv;
											totalSalesUs += rsm1.getDouble("iimoney");
										}

									} else if (imoneytype1 == 2) {
										Double money = rsm1.getDouble("ifmoney");
										if (money != null && !"".equals(money) && money != 0) {
											ifmoneym += rsm1.getDouble("ifmoney") / huilv;
											totalrmbm += rsm1.getDouble("ifmoney");
											totalSalesUs += rsm1.getDouble("ifmoney") / huilv1;
										} else {
											ifmoneym += rsm1.getDouble("iimoney") / huilv;
											totalrmbm += rsm1.getDouble("iimoney");
											totalSalesUs += rsm1.getDouble("iimoney") / huilv1;
										}
									} else {

										Double money = rsm1.getDouble("ifmoney");
										if (money != null && !"".equals(money) && money != 0) {
											ifmoneym += (rsm1.getDouble("ifmoney") / huilv1) * huilv;
											totalrmbm += rsm1.getDouble("ifmoney") * huilv;
											totalSalesUs += (rsm1.getDouble("ifmoney") / huilv1) * huilv;
										} else {
											ifmoneym += (rsm1.getDouble("iimoney") / huilv1) * huilv;
											totalrmbm += rsm1.getDouble("iimoney") * huilv;
											totalSalesUs += (rsm1.getDouble("iimoney") / huilv1) * huilv;
										}

									}

								}
							}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (rsm1 != null) {
								try {
									rsm1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (stmtm1 != null) {
								try {
									stmtm1.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							SQLDBhelper.returnConnection(connm1);
						}


						String ifdatex = "";
						double profitrmb1 = 0.0;
						double profitmargin2 = 0.0;
						String profitmargin3 = "";
						if (totalrmb != 0) {
							totalrmb1 = paywulliu + payother + payfactory;
							profitrmb = totalrmbm - totalrmb1;
							profitrmb1 = totalrmb2 - totalrmb1;
							profitus = profitrmb / huilv1;
							profitmargin = profitrmb / totalrmb;
							profitmargin2 = profitrmb1 / totalrmb2;
							double profitmargina = profitmargin * 100;
							double profitmarginb = profitmargin2 * 100;
							double f = 0.0;
							double f1 = 0.0;
							double f2 = 0.0;
							double f3 = 0.0;
							double f4 = 0.0;
							double f5 = 0.0;


							if (profitmargina != 0 && !"".equals(profitmargina)) {
								BigDecimal b = new BigDecimal(profitmargina);
								f = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
								profitmargin1 = f + "%";
							}
							if (profitmarginb != 0 && !"".equals(profitmarginb)) {
								BigDecimal b = new BigDecimal(profitmarginb);
								f = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
								profitmargin3 = f + "%";
							}
							if (totalrmb1 != 0 && !"".equals(totalrmb1)) {
								BigDecimal b = new BigDecimal(totalrmb1);
								f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

							}
							if (totalrmb != 0 && !"".equals(totalrmb)) {
								BigDecimal b = new BigDecimal(totalrmb);
								f5 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

							}
							if (profitrmb != 0 && !"".equals(profitrmb)) {
								BigDecimal b = new BigDecimal(profitrmb);
								f2 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

							}
							if (profitus != 0 && !"".equals(profitus)) {
								BigDecimal b = new BigDecimal(profitus);
								f3 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

							}
							if (ifmoney != 0 && !"".equals(ifmoney) && !"NaN".equals(ifmoney) && !"Infinity".equals(ifmoney)) {
								System.out.print(ifmoney);
								BigDecimal b = new BigDecimal(ifmoney);

								f4 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

							}
							double saleallMoney = 0.0;
							String sqlx = "  select imoneytype,iimoney,iidate,ifmoney,ifdate from invoiceinfo where icaseno=?  ";
							Connection connx = null;
							PreparedStatement stmtx = null;
							ResultSet rsx = null;
							connx = SQLDBhelper.getConnection();
							try {
								stmtx = connx.prepareStatement(sqlx);
								stmtx.setString(1, CaseNo);
								rsx = stmtx.executeQuery();
								while (rsx.next()) {
									String date = rsx.getString("iidate");
									String ifdate = rsx.getString("ifdate");


									if (ifdate != null && !"".equals(ifdate)) {
										imoneytype1 = rsx.getInt("imoneytype");

										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Calendar cal = Calendar.getInstance();
										Date dt = null;
										try {
											dt = sdf.parse(ifdate);
											cal.setTime(dt);
										} catch (Exception e) {
											e.printStackTrace();
										}

										int year1 = cal.get(Calendar.YEAR);
										int month1 = cal.get(Calendar.MONTH) + 1;
										huilv = getHuilv(year1, month1, imoneytype1);
										huilv1 = getHuilv(year1, month1, 1);

										if (huilv == 0) {
											huilv = 1;
										}

										if (imoneytype1 == 1) {
											Double money = rsx.getDouble("ifmoney");
											if (money != null && !"".equals(money) && money != 0) {

												saleallMoney += rsx.getDouble("ifmoney") * huilv;
											} else {

												saleallMoney += rsx.getDouble("iimoney") * huilv;
											}

										} else if (imoneytype1 == 2) {
											Double money = rsx.getDouble("ifmoney");
											if (money != null && !"".equals(money) && money != 0) {

												saleallMoney += rsx.getDouble("ifmoney");
											} else {

												saleallMoney += rsx.getDouble("iimoney");
											}
										} else {

											Double money = rsx.getDouble("ifmoney");
											if (money != null && !"".equals(money) && money != 0) {

												saleallMoney += rsx.getDouble("ifmoney") * huilv;
											} else {

												saleallMoney += rsx.getDouble("iimoney") * huilv;
											}

										}

									}


								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsx != null) {
									try {
										rsx.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtx != null) {
									try {
										stmtx.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(connx);
							}
							double fm = 0.0;
							if (saleallMoney != 0 && !"".equals(saleallMoney) && !"NaN".equals(saleallMoney) && !"Infinity".equals(saleallMoney)) {
								//System.out.print(saleallMoney);
								BigDecimal b = new BigDecimal(saleallMoney);

								fm = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

							}
							double totalAmountOfArrival = 0.0;
							double totalAmountReceivable = 0.0;
							String sqlb = "  select sum(iimoney)iimoney,sum(ifmoney)ifmoney,iCaseNo from invoiceinfo where icaseno=?  group by iCaseNo";
							Connection connb = null;
							PreparedStatement stmtb = null;
							ResultSet rsb = null;
							connb = SQLDBhelper.getConnection();
							try {
								stmtb = connb.prepareStatement(sqlb);
								stmtb.setString(1, CaseNo);
								rsb = stmtb.executeQuery();
								if (rsb.next()) {
									totalAmountOfArrival = rsb.getDouble("ifmoney");
									totalAmountReceivable = rsb.getDouble("iimoney");

								}

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsb != null) {
									try {
										rsb.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtb != null) {
									try {
										stmtb.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(connb);
							}
							double Pay_Moeny = 0.0;
							double Get_Moeny = 0.0;
							double iimoney1 = 0.0;
							double ifmoney1 = 0.0;
							double factory_yue_money = 0.0;
							String sqlf = "select sum(iimoney)iimoney from InvoiceInfo  where iCaseNo=?     ";
							Connection connf = null;
							PreparedStatement stmtf = null;
							ResultSet rsf = null;
							connf = SQLDBhelper.getConnection();
							try {
								stmtf = connf.prepareStatement(sqlf);
								stmtf.setString(1, CaseNo);


								rsf = stmtf.executeQuery();
								while (rsf.next()) {
									iimoney1 = rsf.getDouble("iimoney");
								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsf != null) {
									try {
										rsf.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtf != null) {
									try {
										stmtf.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(connf);
							}


							String sqly = "select sum(ifmoney)ifmoney from InvoiceInfo  where iCaseNo=? and DATEDIFF(day,'" + ymonth1 + "',ifdate)<0    ";
							Connection conny = null;
							PreparedStatement stmty = null;
							ResultSet rsy = null;
							conny = SQLDBhelper.getConnection();
							try {
								stmty = conny.prepareStatement(sqly);
								stmty.setString(1, CaseNo);


								rsy = stmty.executeQuery();
								while (rsy.next()) {
									ifmoney1 = rsy.getDouble("ifmoney");
								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsy != null) {
									try {
										rsy.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmty != null) {
									try {
										stmty.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(conny);
							}


							String sqlg = "select sum(Pay_Moeny)Pay_Moeny,sum(Get_Moeny)Get_Moeny from Tab_Factory_Money left join itemCase  on itemCase.CaseNo=Tab_Factory_Money.Case_No where CaseNo=? and DATEDIFF(day,'" + ymonth1 + "',Date_time)<0   ";
							Connection conng = null;
							PreparedStatement stmtg = null;
							ResultSet rsg = null;
							conng = SQLDBhelper.getConnection();
							try {
								stmtg = conng.prepareStatement(sqlg);
								stmtg.setString(1, CaseNo);


								rsg = stmtg.executeQuery();
								while (rsg.next()) {
									Pay_Moeny = rsg.getDouble("Pay_Moeny");
									Get_Moeny = rsg.getDouble("Get_Moeny");

								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (rsg != null) {
									try {
										rsg.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								if (stmtg != null) {
									try {
										stmtg.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								SQLDBhelper.returnConnection(conng);
							}

							if (Pay_Moeny == Get_Moeny && Pay_Moeny != 0) {
								bo.setIsInvoice(1);
							} else {
								bo.setIsInvoice(0);
							}
							bo.setProjectId(CaseNo);
							bo.setAccountPayableRmb(f1);
							bo.setSalesThisPeriodRmb(f5);
							bo.setTotalGrossProfitRmb(profitrmb1);
							bo.setSalesThisPeriodUs(f4);
							bo.setTotalGrossProfitUs(f4);
							bo.setTotalSalesRmb(totalrmbm);
							bo.setTotalGrossProfitMargin(profitmargin3);
							bo.setLatest_payment_time(ifdatex);
							bo.setTotalSalesUs(totalSalesUs);
							//bo.setPayment_time(ifdatea);
							bo.setDegreeDifficulty(degreeDifficulty);
							bo.setHeritageProject(BackSingleCase);
							bo.setTotalAmountOfArrival(ifmoney1);
							bo.setTotalAmountReceivable(iimoney1);
							if (paywulliu > 0) {
								bo.setLogisticsCost(0);
							} else {
								bo.setLogisticsCost(1);
							}
							//System.out.print(totalrmb2+";"+f1+";"+profitmargin3);
							list.add(bo);


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

			}
		}
		return list;
	}
}
