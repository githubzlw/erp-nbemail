package cerong.erp.entity;
//客户管理表
public class ArrivalAccountCorrespondenceTable {
	/** 编号 */
	private int id;
	/**customerId */
	private int customerId;
	/** 客户名 */
	private String name;
	/** 项目号 */
	private String projectId;
	/** 销售 */
	private String CustomerManager;
	/** 原始跟单 */
	private String MerchandManager1;
	/** 成熟跟单 */
	private String Merchandising;
	/** 金蝶号 */
	private int kingdee;
	/** 工厂名 */
	private String kingName;
	
	
	public String getKingName() {
		return kingName;
	}
	public void setKingName(String kingName) {
		this.kingName = kingName;
	}
	public int getKingdee() {
		return kingdee;
	}
	public void setKingdee(int kingdee) {
		this.kingdee = kingdee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCustomerManager() {
		return CustomerManager;
	}
	public void setCustomerManager(String customerManager) {
		CustomerManager = customerManager;
	}
	public String getMerchandManager1() {
		return MerchandManager1;
	}
	public void setMerchandManager1(String merchandManager1) {
		MerchandManager1 = merchandManager1;
	}
	public String getMerchandising() {
		return Merchandising;
	}
	public void setMerchandising(String merchandising) {
		Merchandising = merchandising;
	}
	@Override
	public String toString() {
		return "ArrivalAccountCorrespondenceTable [id=" + id + ", customerId="
				+ customerId + ", name=" + name + ", projectId=" + projectId
				+ ", CustomerManager=" + CustomerManager
				+ ", MerchandManager1=" + MerchandManager1 + ", Merchandising="
				+ Merchandising + ", kingdee=" + kingdee + ", kingName="
				+ kingName + "]";
	}
	
	
}
