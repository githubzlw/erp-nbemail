package cerong.erp.entity;

public class QuoteClient {
	/** 编号id */
	private int id;
	/** 项目号 */
	private String caseNo;
	private String title;
	private String url;
	private String creater;
	private String gongyiliucheng;
	private String baojiajieshi;
	private String qitajieshi;
	private int lirun;
	private int dingdan;
	private String exceed;
	private String createtime;
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getGongyiliucheng() {
		return gongyiliucheng;
	}
	public void setGongyiliucheng(String gongyiliucheng) {
		this.gongyiliucheng = gongyiliucheng;
	}
	public String getBaojiajieshi() {
		return baojiajieshi;
	}
	public void setBaojiajieshi(String baojiajieshi) {
		this.baojiajieshi = baojiajieshi;
	}
	public String getQitajieshi() {
		return qitajieshi;
	}
	public void setQitajieshi(String qitajieshi) {
		this.qitajieshi = qitajieshi;
	}
	public int getLirun() {
		return lirun;
	}
	public void setLirun(int lirun) {
		this.lirun = lirun;
	}
	public int getDingdan() {
		return dingdan;
	}
	public void setDingdan(int dingdan) {
		this.dingdan = dingdan;
	}
	public String getExceed() {
		return exceed;
	}
	public void setExceed(String exceed) {
		this.exceed = exceed;
	}
	@Override
	public String toString() {
		return "QuoteClient [id=" + id + ", caseNo=" + caseNo + ", title="
				+ title + ", url=" + url + ", creater=" + creater
				+ ", gongyiliucheng=" + gongyiliucheng + ", baojiajieshi="
				+ baojiajieshi + ", qitajieshi=" + qitajieshi + ", lirun="
				+ lirun + ", dingdan=" + dingdan + ", exceed=" + exceed
				+ ", createtime=" + createtime + "]";
	}
	

}
