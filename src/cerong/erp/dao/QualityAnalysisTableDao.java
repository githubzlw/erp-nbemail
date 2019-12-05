package cerong.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cerong.erp.entity.QualityAnalysisTable;
import cerong.erp.jdbc.SQLDBhelper;

public class QualityAnalysisTableDao implements IQualityAnalysisTableDaoImpl{

	@Override
	public int add(QualityAnalysisTable qu) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		ResultSet rs = null;
		
		String sql = "insert into QualityAnalysisTable (CaseNo,create_time,quantity_drawings,bom_table,"
				+ "max_precision_requiremen,precision_grade,max_roughness_requirement,surface_treatment,materials_abroad,"
				+ "corresponding_national_standard,list_standards,standard_not_found,annotation,assembly_relationship,"
				+ "heat_treatment_requirements,customer_requirements,design_defects,recommended_process,"
				+ "suggest_communication_problems,user_name)values(?,getdate(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		conn = SQLDBhelper.getConnection();
	
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, qu.getProjectId());
			stmt.setInt(2, qu.getQuantityDrawings());
			stmt.setInt(3, qu.getBomTable());
			stmt.setString(4, qu.getMaxPrecisionRequiremen());
			stmt.setString(5, qu.getPrecisionGrade());
			stmt.setString(6, qu.getMaxRoughnessRequirement());
			stmt.setString(7, qu.getSurfaceTreatment());
			stmt.setString(8, qu.getMaterialsAbroad());
			stmt.setString(9, qu.getCorrespondingNationalStandard());
			stmt.setString(10, qu.getListStandards());
			stmt.setString(11, qu.getStandardNotFound());
			stmt.setInt(12, qu.getAnnotation());
			stmt.setInt(13, qu.getAssemblyRelationship());
			stmt.setString(14, qu.getHeatTreatmentRequirements());
			stmt.setString(15, qu.getCustomerRequirements());
			stmt.setString(16, qu.getDesignDefects());
			stmt.setString(17, qu.getRecommendedProcess());
			stmt.setString(18, qu.getSuggestCommunicationProblems());
			stmt.setString(19, qu.getUserName());
			
			
			
			
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

}
