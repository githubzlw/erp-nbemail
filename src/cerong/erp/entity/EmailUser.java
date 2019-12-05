package cerong.erp.entity;

import java.util.Date;

public class EmailUser {
	
	/** 用户id*/
	private int id;
	/** 用户角色编号(1-翻译 2-数据录入 3-老外�?�� 4-跟单�?�� 5-其他�?�� 6-采购 7-物流 8-质检)*/
	private int roleNo;
	/** 用户名称*/
	private String userName;
	/** 用户密码*/
	private String pwd;
	/** 员工真实姓名*/
	private String trueName;
	/** 员工职位*/
	private String job;
	/** 用户邮件地址*/
	private String emailAddress;
	/** 出生日期*/
	private String birthday;
	/** 入职时间*/
	private String worktime;
	/** 英语水平*/
	private String englishLVL;
	/** 联系方式*/
	private String Phone;
	/** 住址*/
	private String liveAddress;
	/** 工作年限*/
	private int workmonth;
	/** skape邮件地址*/
	private String skapeemail;
	/** msn邮件地址*/
	private String Msn;
	/** 公司电话*/
	private String CompanyPhone;
	/** 移动电话*/
	private String Mobile;
	/** 邮箱密码*/
	private String emailPWD;
	/** 是否�?��翻译的标识（0不需要，1�?��)*/
	private int flag;
	/** 是否�?��在职的标识（0在职�?离职)*/
	private int dimission;
	/** 注册日期 */
	private Date registDate;
	/** 登录Ip */
	private String loginIp ;
	/** 登录错误次数 */
	private String wrongNumber ;
	/** 登录错误时间 */
	private Long wrongTime ;
	/** 错误Ip*/
	private Long wrongIp ;
	/** 权限*/
	private int qualification ;

	private String startTime ;//进账客户起始时间
	private String startTime1 ;//所有客户存量起始时间
	private String startTimea ;//上季度起始时间
	private String endTimea ;//上季度结束始时间a
	private String endTime ;//进账客户截止时间
	private String startTime2 ;//6个月前进账客户时间
	private String startTime3 ;//7个月前进账客户时间
	private int incomingCustomers;//客户下单数
	private int incomingCustomers1;//6个月进账客户数
	private int incomingCustomers2;//7个月进账客户数
	private int listedBelow;//下单客户数
	private int allCustomer;//全部客户数
	private int newlistedBelow;//2018年之后下单客户数
	private int allnewlistedBelow;//2018年之后全部客户数

	public String getStartTimea() {
		return startTimea;
	}

	public void setStartTimea(String startTimea) {
		this.startTimea = startTimea;
	}

	public String getEndTimea() {
		return endTimea;
	}

	public void setEndTimea(String endTimea) {
		this.endTimea = endTimea;
	}

	public int getAllCustomer() {
		return allCustomer;
	}

	public void setAllCustomer(int allCustomer) {
		this.allCustomer = allCustomer;
	}

	public int getAllnewlistedBelow() {
		return allnewlistedBelow;
	}

	public void setAllnewlistedBelow(int allnewlistedBelow) {
		this.allnewlistedBelow = allnewlistedBelow;
	}

	public int getNewlistedBelow() {
		return newlistedBelow;
	}

	public void setNewlistedBelow(int newlistedBelow) {
		this.newlistedBelow = newlistedBelow;
	}

	public int getIncomingCustomers() {
		return incomingCustomers;
	}

	public void setIncomingCustomers(int incomingCustomers) {
		this.incomingCustomers = incomingCustomers;
	}

	public int getIncomingCustomers1() {
		return incomingCustomers1;
	}

	public void setIncomingCustomers1(int incomingCustomers1) {
		this.incomingCustomers1 = incomingCustomers1;
	}

	public int getIncomingCustomers2() {
		return incomingCustomers2;
	}

	public void setIncomingCustomers2(int incomingCustomers2) {
		this.incomingCustomers2 = incomingCustomers2;
	}

	public int getListedBelow() {
		return listedBelow;
	}

	public void setListedBelow(int listedBelow) {
		this.listedBelow = listedBelow;
	}

	public String getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}

	public String getStartTime3() {
		return startTime3;
	}

	public void setStartTime3(String startTime3) {
		this.startTime3 = startTime3;
	}



	public String getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}

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

	public EmailUser() {
		
	}
	public EmailUser(String userName, String pwd, String emailAddress) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.emailAddress = emailAddress;
	}
	public EmailUser(String userName2, String newPWD) {
		super();
		this.userName = userName;
		this.pwd = pwd;
	}
	/*public EmailUser(String userName2) {
		super();
		this.userName = userName;
		
	}*/
	
	
	
	public String getEmailPWD() {
		return emailPWD;
	}
	public int getQualification() {
		return qualification;
	}
	public void setQualification(int qualification) {
		this.qualification = qualification;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public Long getWrongIp() {
		return wrongIp;
	}
	public void setWrongIp(Long wrongIp) {
		this.wrongIp = wrongIp;
	}
	public Long getWrongTime() {
		return wrongTime;
	}
	public void setWrongTime(Long wrongTime) {
		this.wrongTime = wrongTime;
	}
	public String getWrongNumber() {
		return wrongNumber;
	}
	public void setWrongNumber(String wrongNumber) {
		this.wrongNumber = wrongNumber;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public void setEmailPWD(String emailPWD) {
		this.emailPWD = emailPWD;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	public int getDimission() {
		return dimission;
	}
	public void setDimission(int dimission) {
		this.dimission = dimission;
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getEnglishLVL() {
		return englishLVL;
	}
	public void setEnglishLVL(String englishLVL) {
		this.englishLVL = englishLVL;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	public int getWorkmonth() {
		return workmonth;
	}
	public void setWorkmonth(int workmonth) {
		this.workmonth = workmonth;
	}
	public String getSkapeemail() {
		return skapeemail;
	}
	public void setSkapeemail(String skapeemail) {
		this.skapeemail = skapeemail;
	}
	public String getMsn() {
		return Msn;
	}
	public void setMsn(String msn) {
		Msn = msn;
	}
	public String getCompanyPhone() {
		return CompanyPhone;
	}
	public void setCompanyPhone(String companyPhone1) {
		CompanyPhone = companyPhone1;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	@Override
	public String toString() {
		return "EmailUser{" +
				"id=" + id +
				", roleNo=" + roleNo +
				", userName='" + userName + '\'' +
				", pwd='" + pwd + '\'' +
				", trueName='" + trueName + '\'' +
				", job='" + job + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", birthday='" + birthday + '\'' +
				", worktime='" + worktime + '\'' +
				", englishLVL='" + englishLVL + '\'' +
				", Phone='" + Phone + '\'' +
				", liveAddress='" + liveAddress + '\'' +
				", workmonth=" + workmonth +
				", skapeemail='" + skapeemail + '\'' +
				", Msn='" + Msn + '\'' +
				", CompanyPhone='" + CompanyPhone + '\'' +
				", Mobile='" + Mobile + '\'' +
				", emailPWD='" + emailPWD + '\'' +
				", flag=" + flag +
				", dimission=" + dimission +
				", registDate=" + registDate +
				", loginIp='" + loginIp + '\'' +
				", wrongNumber='" + wrongNumber + '\'' +
				", wrongTime=" + wrongTime +
				", wrongIp=" + wrongIp +
				", qualification=" + qualification +
				", startTime='" + startTime + '\'' +
				", startTime1='" + startTime1 + '\'' +
				", startTimea='" + startTimea + '\'' +
				", endTimea='" + endTimea + '\'' +
				", endTime='" + endTime + '\'' +
				", startTime2='" + startTime2 + '\'' +
				", startTime3='" + startTime3 + '\'' +
				", incomingCustomers=" + incomingCustomers +
				", incomingCustomers1=" + incomingCustomers1 +
				", incomingCustomers2=" + incomingCustomers2 +
				", listedBelow=" + listedBelow +
				", allCustomer=" + allCustomer +
				", newlistedBelow=" + newlistedBelow +
				", allnewlistedBelow=" + allnewlistedBelow +
				'}';
	}
}
