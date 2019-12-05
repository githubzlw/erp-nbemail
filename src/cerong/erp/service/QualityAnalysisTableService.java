package cerong.erp.service;

import cerong.erp.dao.IQualityAnalysisTableDaoImpl;
import cerong.erp.dao.QualityAnalysisTableDao;
import cerong.erp.entity.QualityAnalysisTable;



public class QualityAnalysisTableService implements  IQualityAnalysisTableServiceImpl{
	IQualityAnalysisTableDaoImpl dao = new  QualityAnalysisTableDao();

	@Override
	public int add(QualityAnalysisTable qualityAnalysisTable) {
		
		return dao.add(qualityAnalysisTable);
	}
}
