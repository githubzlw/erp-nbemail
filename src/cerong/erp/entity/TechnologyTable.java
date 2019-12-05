package cerong.erp.entity;

public class TechnologyTable {
	/** 编号id */
	private int id;
	/** 工艺名 */
	private String technologyName;
	/** 工艺属性 */
	private int processAttributes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTechnologyName() {
		return technologyName;
	}
	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}
	public int getProcessAttributes() {
		return processAttributes;
	}
	public void setProcessAttributes(int processAttributes) {
		this.processAttributes = processAttributes;
	}
	@Override
	public String toString() {
		return "TechnologyTable [id=" + id + ", technologyName="
				+ technologyName + ", processAttributes=" + processAttributes
				+ "]";
	}
	
}
