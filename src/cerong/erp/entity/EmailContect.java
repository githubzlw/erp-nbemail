package cerong.erp.entity;

public class EmailContect {
	/** 邮件内容*/
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EmailContect(String content) {
		super();
		this.content = content;
	}

	public EmailContect() {
		super();
	}

	@Override
	public String toString() {
		return "EmailContect [content=" + content + "]";
	}
	
}
