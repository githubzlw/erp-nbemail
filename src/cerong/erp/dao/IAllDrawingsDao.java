package cerong.erp.dao;

import java.util.List;

import cerong.erp.entity.AllDrawings;

public interface IAllDrawingsDao {

	/**
	 * 查看图纸全部合同
	 * author:wy
	 * date:2017-7-26
	 * @param user
	 * @return
	 */

	public List<AllDrawings> getall();
	/**
	 * 查看质检图纸
	 * author:wy
	 * date:2017-7-26
	 * @param user
	 * @return
	 */
	public List<AllDrawings> getall1();
	/**
	 * 定时跟新图纸
	 * author:wy
	 * date:2017-9-14
	 * @param user
	 * @return
	 */
	public List<AllDrawings> getDrawingsAll();
	/**
	 * 定时跟新质检图纸
	 * author:wy
	 * date:2017-9-14
	 * @param user
	 * @return
	 */
	public List<AllDrawings> getDrawingsAll1();
	/**
	 * 图片集中添加数据
	 * @param drawing
	 */
    void insert(AllDrawings drawing);
	/**
	 * 查看全部文件
	 * @param caseNo1
	 * @return
	 */
    List<AllDrawings> getAllDrawings(String caseNo);
}
