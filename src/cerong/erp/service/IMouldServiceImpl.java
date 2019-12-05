package cerong.erp.service;

import java.util.List;

import cerong.erp.entity.Mould;

public interface IMouldServiceImpl {
	/**
	 * 名称：获取数据
	 * author:wy
	 * date:2017-6-6
	 * @return
	 */ 
	public List<Mould> getall();
	/**
	 * 名称：根据eid拉取郑佳拉去模具数据
	 * author:wy
	 * date:2017-7-1
	 * @return
	 */ 
	public List<Mould> getall1(int eid);
	/**
	 * 名称：拉取半小时内的全部模具
	 * author:wy
	 * date:2017-7-7
	 * @return
	 */ 
	public List<Mould> getall2();

}
