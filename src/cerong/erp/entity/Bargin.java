package cerong.erp.entity;

public class Bargin {
	/** 编号 */
	private int id;
	/**合同号 */
	private String name;
	/** 项目号 */
	private String projectId;
	/** 合同文件 */
	private String contractDocuments;
	/** 合同总金额 */
	private double totalSum;
	/** 收到的发票金额 */
	private double amountInvoiceReceived;
	/** 工厂名 */
	private String factoryName;
	/** 最后一次付款日期 */
	private String datePayment;
	/** 报关品名 */
	private String declarationCustoms;
	/** 已付款金额 */
	private double payMoeny;
	/** 是否报关*/
	private int whetherToDeclare;
	/** 是否报关*/
	private int overtime;
	/** 是不是尾款*/
	private int lastParagraph;//0不是 1 是
	/** 发票是否收齐*/
	private int invoiceCollected;//0 是 1 不是
	/** start */
	private int start;
	/** end */
	private int end;
	/** 起始时间 */
	private String startTime;
	/** 截止时间 */
	private String endTime;
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getInvoiceCollected() {
		return invoiceCollected;
	}
	public void setInvoiceCollected(int invoiceCollected) {
		this.invoiceCollected = invoiceCollected;
	}
	public double getPayMoeny() {
		return payMoeny;
	}
	public void setPayMoeny(double payMoeny) {
		this.payMoeny = payMoeny;
	}
	public int getLastParagraph() {
		return lastParagraph;
	}
	public void setLastParagraph(int lastParagraph) {
		this.lastParagraph = lastParagraph;
	}
	public int getOvertime() {
		return overtime;
	}
	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}
	public double getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}
	public double getAmountInvoiceReceived() {
		return amountInvoiceReceived;
	}
	public void setAmountInvoiceReceived(double amountInvoiceReceived) {
		this.amountInvoiceReceived = amountInvoiceReceived;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getDatePayment() {
		return datePayment;
	}
	public void setDatePayment(String datePayment) {
		this.datePayment = datePayment;
	}
	public String getDeclarationCustoms() {
		return declarationCustoms;
	}
	public void setDeclarationCustoms(String declarationCustoms) {
		this.declarationCustoms = declarationCustoms;
	}
	public int getWhetherToDeclare() {
		return whetherToDeclare;
	}
	public void setWhetherToDeclare(int whetherToDeclare) {
		this.whetherToDeclare = whetherToDeclare;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getContractDocuments() {
		return contractDocuments;
	}
	public void setContractDocuments(String contractDocuments) {
		this.contractDocuments = contractDocuments;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Bargin [id=" + id + ", name=" + name + ", projectId="
				+ projectId + ", contractDocuments=" + contractDocuments
				+ ", totalSum=" + totalSum + ", amountInvoiceReceived="
				+ amountInvoiceReceived + ", factoryName=" + factoryName
				+ ", datePayment=" + datePayment + ", declarationCustoms="
				+ declarationCustoms + ", payMoeny=" + payMoeny
				+ ", whetherToDeclare=" + whetherToDeclare + ", overtime="
				+ overtime + ", lastParagraph=" + lastParagraph
				+ ", invoiceCollected=" + invoiceCollected + ", start=" + start
				+ ", end=" + end + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
	
}
