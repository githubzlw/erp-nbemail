package cerong.erp.entity;

public class ProjectComplaint {
	/** 案子号id */
	private int id;
	/** 客户项目Id */
	private int Project;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProject() {
		return Project;
	}
	public void setProject(int project) {
		Project = project;
	}
	@Override
	public String toString() {
		return "ProjectComplaint [id=" + id + ", Project=" + Project + "]";
	}
	

}
