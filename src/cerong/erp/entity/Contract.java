package cerong.erp.entity;

public class Contract {
	/** 合同号 */
	private String contract;
	/** 工厂名*/
	private String factory;
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	@Override
	public String toString() {
		return "Contract [contract=" + contract + ", factory=" + factory + "]";
	}


}
