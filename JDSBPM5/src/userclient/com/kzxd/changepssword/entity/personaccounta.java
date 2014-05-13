package com.kzxd.changepssword.entity;

import java.sql.Timestamp;

//@Entity
//@Table(name = "RO_Personaccount")
public class personaccounta {
	
	


/**
	    @PropertyChinaName(cname="最后一次登录IP")
	    private String lastloginip;

		@Column(length=3000)
		public String getLastloginip(){
			return lastloginip;
		}
		
		public void setLastloginip( String lastloginip ){
			this.lastloginip = lastloginip;
		}
		

	    @PropertyChinaName(cname="人员ID")
	    private String personid;

		@Column(length=3000)
		public String getPersonid(){
			return personid;
		}
		
		public void setPersonid( String personid ){
			this.personid = personid;
		}
		

	    @PropertyChinaName(cname="最后登录时间")
	    private Timestamp lastlogindate;

		public Timestamp getLastlogindate(){
			return lastlogindate;
		}
		
		public void setLastlogindate( Timestamp lastlogindate ){
			this.lastlogindate = lastlogindate;
		}
		

	    @PropertyChinaName(cname="问题答案")
	    private String passanswer;

		@Column(length=3000)
		public String getPassanswer(){
			return passanswer;
		}
		
		public void setPassanswer( String passanswer ){
			this.passanswer = passanswer;
		}
		

	    @PropertyChinaName(cname="创建时间")
	    private Timestamp createtime;

		public Timestamp getCreatetime(){
			return createtime;
		}
		
		public void setCreatetime( Timestamp createtime ){
			this.createtime = createtime;
		}
		

	    @PropertyChinaName(cname="登录失败次数")
	    private Integer loginfailnum;

		public Integer getLoginfailnum(){
			return loginfailnum;
		}
		
		public void setLoginfailnum( Integer loginfailnum ){
			this.loginfailnum = loginfailnum;
		}
		

	    @PropertyChinaName(cname="账号有效期")
	    private Integer accountttl;

		public Integer getAccountttl(){
			return accountttl;
		}
		
		public void setAccountttl( Integer accountttl ){
			this.accountttl = accountttl;
		}
		

	    @PropertyChinaName(cname="密码问题")
	    private String passquestion;

		@Column(length=3000)
		public String getPassquestion(){
			return passquestion;
		}
		
		public void setPassquestion( String passquestion ){
			this.passquestion = passquestion;
		}
		

	    @PropertyChinaName(cname="人员标识")
	    private Integer flag;

		public Integer getFlag(){
			return flag;
		}
		
		public void setFlag( Integer flag ){
			this.flag = flag;
		}
		

	    @PropertyChinaName(cname="密码")
	    private String password;

		@Column(length=3000)
		public String getPassword(){
			return password;
		}
		
		public void setPassword( String password ){
			this.password = password;
		}
		

	    @PropertyChinaName(cname="账号状态")
	    private String accountstat;

		public String getAccountstat(){
			return accountstat;
		}
		
		public void setAccountstat( String accountstat ){
			this.accountstat = accountstat;
		}
		

	    @PropertyChinaName(cname="UUID")
	    private String uuid;

		@Id @GeneratedValue(generator="system-uuid")
		@GenericGenerator(name="system-uuid", strategy="uuid")
		@Column(length=32)
		public String getUuid(){
			return uuid;
		}
		
		public void setUuid( String uuid ){
			this.uuid = uuid;
		}
		
		


		

	    @PropertyChinaName(cname="USERID")
	    private String userid;

		@Column(length=3000)
		public String getUserid(){
			return userid;
		}
		
		public void setUserid( String userid ){
			this.userid = userid;
		}
		
		//zhongqun 2011-11-22 新增rtx账号属性
		
		 @PropertyChinaName(cname="rtx账号")
		    private String rtxaccount;

		public String getRtxaccount() {
			return rtxaccount;
		}

		public void setRtxaccount(String rtxaccount) {
			this.rtxaccount = rtxaccount;
		}

			
	
	
}*/

	
	private String lastloginip;
	 private String personid;
	 private Timestamp lastlogindate;
	 private String passanswer;
	 private Timestamp createtime;
	 private Integer loginfailnum;
	 private Integer accountttl;
	 private String passquestion;
	 private Integer flag;
	 private String password;
	 private String accountstat;
	 private String uuid;
	 private String userid;
	 private String rtxaccount;
	 
	 public  personaccounta(){
		 
		 
	         }
	 
	 
	public personaccounta(String lastloginip, String personid,
			Timestamp lastlogindate, String passanswer, Timestamp createtime,
			Integer loginfailnum, Integer accountttl, String passquestion,
			Integer flag, String password, String accountstat, String uuid,
			String userid, String rtxaccount) {
		super();
		this.lastloginip = lastloginip;
		this.personid = personid;
		this.lastlogindate = lastlogindate;
		this.passanswer = passanswer;
		this.createtime = createtime;
		this.loginfailnum = loginfailnum;
		this.accountttl = accountttl;
		this.passquestion = passquestion;
		this.flag = flag;
		this.password = password;
		this.accountstat = accountstat;
		this.uuid = uuid;
		this.userid = userid;
		this.rtxaccount = rtxaccount;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public Timestamp getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(Timestamp lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	public String getPassanswer() {
		return passanswer;
	}
	public void setPassanswer(String passanswer) {
		this.passanswer = passanswer;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getLoginfailnum() {
		return loginfailnum;
	}
	public void setLoginfailnum(Integer loginfailnum) {
		this.loginfailnum = loginfailnum;
	}
	public Integer getAccountttl() {
		return accountttl;
	}
	public void setAccountttl(Integer accountttl) {
		this.accountttl = accountttl;
	}
	public String getPassquestion() {
		return passquestion;
	}
	public void setPassquestion(String passquestion) {
		this.passquestion = passquestion;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountstat() {
		return accountstat;
	}
	public void setAccountstat(String accountstat) {
		this.accountstat = accountstat;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRtxaccount() {
		return rtxaccount;
	}
	public void setRtxaccount(String rtxaccount) {
		this.rtxaccount = rtxaccount;
	}
	
}
