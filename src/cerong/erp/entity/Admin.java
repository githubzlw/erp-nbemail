package cerong.erp.entity;

public class Admin {
	/** 客户id */
	private int id;
	/** 客户最初联系人 */
	private String name;
	/** 客户密码 */
	private String pass;
	/** 客户 */
	private int auth;
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth2) {
		this.auth = auth2;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", pass=" + pass
				+ ", auth=" + auth + "]";
	}
	
}
