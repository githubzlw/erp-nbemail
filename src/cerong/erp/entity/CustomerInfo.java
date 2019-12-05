package cerong.erp.entity;

import java.util.Date;
import java.util.List;

/**
 * 客户邮箱信息
 */
public class CustomerInfo {

	/** 客户id */
	private int cid;
	/** 客户eid */
	private int eid;
	/** 客户通用Id */
	private String cidName;
	
	/** 客户最初联系人 */
	private String firstName;
	/** 客户最初联系人职位 */
	private String firstJob;
	/** 客户公司全名 */
	private String gsFullName;
	
	
	/** 录入者 */
	private String recoder;
	/** 录入者id */
	private int recoderId;
	/** 录入时间 */
	private Date recodDate;
	/** 客户对应的销售ID */
	private int saleId;
	
	/** 分页的开始*/
	private int start;
	/** 分页的结束*/
	private int end;
	
	
	/** 黑名单, 0 普通客户  1 黑名单  */
	private int blacklist;
	
	
	/** 联系距今时间*/
	private int time;
	/** 客户询问邮件清理版标题 */
	private String title;
	/** 客户询问邮件清理版内容 */
	private String content;
	/** 项目名*/
  private String projectDescc;
  /** 项目英文名*/
	private String projectDesce;
  /** 项目提交状态  0未提交，1，附件上传完成，2，表示已在ERP生成新项目 3，表示本地数据库已完成，4提交完成*/
  private String flowstate;
  //客户大小
  private String ddlDgree;
  //项目号
  private String projectId;
  //总金额
  private String amount;
  /** 客户对应的销售人员名字 */
  private String saleName1;
  /** 客户对应的跟单销售人员名字 */
  private String saleName2;
  /** 客户是否被投诉 */
  private int baobiao;
  /** 客户说明note */
  private String note;
  /** 客户职位 */
  private String work;


/** 客户潜力 */
  private String kehudaxiao;
  /** 客户金额 */
  private int mount;
  /** 客户状态 */
  private String cstatus ;
  /** 第一次真实付款时间 */
  private String createTime ;
  /** 最后一次付款时间 */
  private String createTime1 ;
  /** 运单时间 */
  private String deliveryTime ;
  /** 报价项目数*/
  private int quotationitem ;
  /** 跟单项目数 */
  private int documentaryproject ;
  
  
  
  public String getCreateTime1() {
	return createTime1;
}

public void setCreateTime1(String createTime1) {
	this.createTime1 = createTime1;
}

public int getQuotationitem() {
	return quotationitem;
}

public void setQuotationitem(int quotationitem) {
	this.quotationitem = quotationitem;
}

public int getDocumentaryproject() {
	return documentaryproject;
}

public void setDocumentaryproject(int documentaryproject) {
	this.documentaryproject = documentaryproject;
}

public String getSaleName1() {
	return saleName1;
}

public void setSaleName1(String saleName1) {
	this.saleName1 = saleName1;
}

public String getTime1() {
	return time1;
}

public void setTime1(String time1) {
	this.time1 = time1;
}
public String getCreateTime() {
	return createTime;
}

public void setCreateTime(String createTime) {
	this.createTime = createTime;
}

public String getDeliveryTime() {
	return deliveryTime;
}

public void setDeliveryTime(String deliveryTime) {
	this.deliveryTime = deliveryTime;
}

public String getTime2() {
	return time2;
}

public void setTime2(String time2) {
	this.time2 = time2;
}

public String getTime3() {
	return time3;
}

public void setTime3(String time3) {
	this.time3 = time3;
}

/** 距今4个月未联系，现在时间 */
  private String time1;
  /** 2016年销售排行，现在时间 */
  private String time2;
  /** 2015年销售排行，现在时间 */
  private String time3;
  
  
	
  
  
	public int getMount() {
	return mount;
}

public void setMount(int mount) {
	this.mount = mount;
}

	public String getWork() {
	return work;
}

public void setWork(String work) {
	this.work = work;
}

public String getKehudaxiao() {
	return kehudaxiao;
}

public void setKehudaxiao(String kehudaxiao) {
	this.kehudaxiao = kehudaxiao;
}

	public String getDdlDgree() {
	return ddlDgree;
}

public void setDdlDgree(String ddlDgree) {
	this.ddlDgree = ddlDgree;
}

public String getProjectId() {
	return projectId;
}

public void setProjectId(String projectId) {
	this.projectId = projectId;
}

public String getAmount() {
	return amount;
}

public void setAmount(String amount) {
	this.amount = amount;
}

public String getSaleName2() {
	return saleName2;
}

public void setSaleName2(String saleName2) {
	this.saleName2 = saleName2;
}



public int getBaobiao() {
	return baobiao;
}

public void setBaobiao(int baobiao) {
	this.baobiao = baobiao;
}

public String getNote() {
	return note;
}

public void setNote(String note) {
	this.note = note;
}

	public String getCidName() {
	return cidName;
}

public void setCidName(String cidName) {
	this.cidName = cidName;
}

	public String getFlowstate() {
	return flowstate;
}

public void setFlowstate(String flowstate) {
	this.flowstate = flowstate;
}

	public String getProjectDescc() {
		return projectDescc;
	}

	public void setProjectDescc(String projectDescc) {
		this.projectDescc = projectDescc;
	}

	public CustomerInfo() {
		
	}
	
	
   
     
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTime() {
		return time;
	}

	public String getProjectDesce() {
		return projectDesce;
	}

	public void setProjectDesce(String projectDesce) {
		this.projectDesce = projectDesce;
	}

	public void setTime(int time) {
		this.time = time;
	}

	

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}
	

	public int getBlacklist() {
		return blacklist;
	}
	

	

	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}

	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstJob() {
		return firstJob;
	}
	public void setFirstJob(String firstJob) {
		this.firstJob = firstJob;
	}
	public String getGsFullName() {
		return gsFullName;
	}
	public void setGsFullName(String gsFullName) {
		this.gsFullName = gsFullName;
	}
	
	
	public String getRecoder() {
		return recoder;
	}
	public void setRecoder(String recoder) {
		this.recoder = recoder;
	}
	public Date getRecodDate() {
		return recodDate;
	}
	public void setRecodDate(Date recodDate) {
		this.recodDate = recodDate;
	}
	
	
	public int getRecoderId() {
		return recoderId;
	}
	public void setRecoderId(int recoderId) {
		this.recoderId = recoderId;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
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

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	@Override
	public String toString() {
		return "CustomerInfo [cid=" + cid + ", eid=" + eid + ", cidName="
				+ cidName + ", firstName=" + firstName + ", firstJob="
				+ firstJob + ", gsFullName=" + gsFullName + ", recoder="
				+ recoder + ", recoderId=" + recoderId + ", recodDate="
				+ recodDate + ", saleId=" + saleId + ", start=" + start
				+ ", end=" + end + ", blacklist=" + blacklist + ", time="
				+ time + ", title=" + title + ", content=" + content
				+ ", projectDescc=" + projectDescc + ", projectDesce="
				+ projectDesce + ", flowstate=" + flowstate + ", ddlDgree="
				+ ddlDgree + ", projectId=" + projectId + ", amount=" + amount
				+ ", saleName1=" + saleName1 + ", saleName2=" + saleName2
				+ ", baobiao=" + baobiao + ", note=" + note + ", work=" + work
				+ ", kehudaxiao=" + kehudaxiao + ", mount=" + mount
				+ ", cstatus=" + cstatus + ", createTime=" + createTime
				+ ", createTime1=" + createTime1 + ", deliveryTime="
				+ deliveryTime + ", quotationitem=" + quotationitem
				+ ", documentaryproject=" + documentaryproject + ", time1="
				+ time1 + ", time2=" + time2 + ", time3=" + time3 + "]";
	}

	

	

	

	

	
	
	

	
	
	
	

	

	


	
	

	

	
	

	

	

	
}
