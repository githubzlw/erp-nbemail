package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.NudrawProject;

public interface INudrawProjectDao {

	/**
	 * 名称：获取客户项目全部信息数
	 * author:wy
	 * date:2016-10-25
	 * @return
	 */ 
	public List<NudrawProject> getAll(Long millionSeconds1, Long millionSeconds2);


}
