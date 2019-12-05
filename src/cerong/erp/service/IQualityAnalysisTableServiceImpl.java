package cerong.erp.service;

import cerong.erp.entity.QualityAnalysisTable;

public interface IQualityAnalysisTableServiceImpl {
	/**
	 * 
	 * @Title:IQualityAnalysisTableServiceImpl
	 * @Description:将质量分析表保存入数据库
	   @author wangyang
	 * @param qualityAnalysisTable
	 * @return int
	 * @throws
	 */
	public int add(QualityAnalysisTable qualityAnalysisTable);

}
