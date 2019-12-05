package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import cerong.erp.entity.ContractApprovalForm;
import cerong.erp.jdbc.SQLDBhelper;

public class ContractApprovalFormDao implements  IContractApprovalFormDaoImpl{

	@Override
	public List<ContractApprovalForm> getAll(ContractApprovalForm cp) {
		List<ContractApprovalForm> list = new ArrayList<ContractApprovalForm>();
		String sql = "select * from ContractApprovalForm cf where cf.dealWith=3 ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i=0;
		conn = SQLDBhelper.getConnection();
		try {
			if(cp.getCaseNo()!=null&&!"".equals(cp.getCaseNo())){
				sql+= " and cf.CaseNo like ?";
			}
			/*if(cp.getDealWith()!=-1){
				sql+= " and cf.dealWith =?";
			}*/
			sql+=" order by cf.dealWith";
			stmt = conn.prepareStatement(sql);
			if(cp.getCaseNo()!=null&&!"".equals(cp.getCaseNo())){
				i++;
				stmt.setString(i,"%"+cp.getCaseNo()+"%" );
			}
			/*if(cp.getDealWith()!=-1){
				i++;
				stmt.setInt(i,cp.getDealWith() );
			}*/
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				 ContractApprovalForm info  = new ContractApprovalForm();
					info.setAttribute(rs.getInt("Attribute"));
					info.setBargainNo(rs.getString("BargainNo"));
					info.setBigFreightRate(rs.getString("bigFreightRate"));
					info.setCaseNo(rs.getString("CaseNo"));
					info.setDealWith(rs.getInt("dealWith"));
					info.setDeliveryRequirement(rs.getInt("DeliveryRequirement"));
					info.setDownPaymentRatio(rs.getString("DownPaymentRatio"));
					info.setGrossProfitMargin(rs.getString("GrossProfitMargin"));
					info.setIfdate(rs.getString("ifdate"));
					info.setLargecargo(rs.getInt("largecargo"));
					info.setMediumTermPayment(rs.getString("mediumTermPayment"));
					info.setMold(rs.getInt("mold"));
					info.setNotes(rs.getString("notes"));
					info.setpODeliveryDate(rs.getString("pODeliveryDate"));
					info.setPriceChange(rs.getInt("priceChange"));
					info.setProjectStartupTime(rs.getString("projectStartupTime"));
					info.setRetainage(rs.getString("retainage"));
					info.setSample(rs.getInt("sample"));
					
					info.setTotalCustomerPurchases(rs.getString("totalCustomerPurchases"));
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
	public List<ContractApprovalForm> getContracts(ContractApprovalForm cp) {
		List<ContractApprovalForm> list = new ArrayList<ContractApprovalForm>();
		String sql = "select cf.* from ContractApprovalForm  cf left join itemcase it on it.CaseNo=cf.CaseNo  where (it.CustomerManager=? or it.MerchandManager1=? or it.MerchandManager2=? )  ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i=0;
		conn = SQLDBhelper.getConnection();
		try {
			if(cp.getCaseNo()!=null&&!"".equals(cp.getCaseNo())){
				sql+= " and cf.CaseNo like ?";
			}
			if(cp.getDealWith()!=-1){
				sql+= " and cf.dealWith =?";
			}
			sql+=" order by cf.dealWith";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,cp.getSaleName() );
			stmt.setString(2,cp.getSaleName() );
			stmt.setString(3,cp.getSaleName() );
			if(cp.getCaseNo()!=null&&!"".equals(cp.getCaseNo())){
				i++;
				stmt.setString(i+3,"%"+cp.getCaseNo()+"%" );
			}
			if(cp.getDealWith()!=-1){
				i++;
				stmt.setInt(i+3,cp.getDealWith() );
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
		    ContractApprovalForm info  = new ContractApprovalForm();
			info.setAttribute(rs.getInt("Attribute"));
			info.setBargainNo(rs.getString("BargainNo"));
			info.setBigFreightRate(rs.getString("bigFreightRate"));
			info.setCaseNo(rs.getString("CaseNo"));
			info.setDealWith(rs.getInt("dealWith"));
			info.setDeliveryRequirement(rs.getInt("DeliveryRequirement"));
			info.setDownPaymentRatio(rs.getString("DownPaymentRatio"));
			info.setGrossProfitMargin(rs.getString("GrossProfitMargin"));
			info.setIfdate(rs.getString("ifdate"));
			info.setLargecargo(rs.getInt("largecargo"));
			info.setMediumTermPayment(rs.getString("mediumTermPayment"));
			info.setMold(rs.getInt("mold"));
			info.setNotes(rs.getString("notes"));
			info.setpODeliveryDate(rs.getString("pODeliveryDate"));
			info.setPriceChange(rs.getInt("priceChange"));
			info.setProjectStartupTime(rs.getString("projectStartupTime"));
			info.setRetainage(rs.getString("retainage"));
			info.setSample(rs.getInt("sample"));
			
			info.setTotalCustomerPurchases(rs.getString("totalCustomerPurchases"));
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
