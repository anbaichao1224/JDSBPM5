package net.itjds.usm.persistence.model;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

import java.io.Serializable;
import net.itjds.usm.annotation.PropertyChinaName;
import java.sql.Timestamp;
import java.sql.Timestamp;


@Entity
@Table(name = "RO_Personaccount")
public class Personaccount implements Serializable {


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

		
	

}