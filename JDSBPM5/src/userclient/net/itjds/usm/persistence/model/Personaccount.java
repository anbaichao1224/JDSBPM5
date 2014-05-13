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


    @PropertyChinaName(cname="���һ�ε�¼IP")
    private String lastloginip;

	@Column(length=3000)
	public String getLastloginip(){
		return lastloginip;
	}
	
	public void setLastloginip( String lastloginip ){
		this.lastloginip = lastloginip;
	}
	

    @PropertyChinaName(cname="��ԱID")
    private String personid;

	@Column(length=3000)
	public String getPersonid(){
		return personid;
	}
	
	public void setPersonid( String personid ){
		this.personid = personid;
	}
	

    @PropertyChinaName(cname="����¼ʱ��")
    private Timestamp lastlogindate;

	public Timestamp getLastlogindate(){
		return lastlogindate;
	}
	
	public void setLastlogindate( Timestamp lastlogindate ){
		this.lastlogindate = lastlogindate;
	}
	

    @PropertyChinaName(cname="�����")
    private String passanswer;

	@Column(length=3000)
	public String getPassanswer(){
		return passanswer;
	}
	
	public void setPassanswer( String passanswer ){
		this.passanswer = passanswer;
	}
	

    @PropertyChinaName(cname="����ʱ��")
    private Timestamp createtime;

	public Timestamp getCreatetime(){
		return createtime;
	}
	
	public void setCreatetime( Timestamp createtime ){
		this.createtime = createtime;
	}
	

    @PropertyChinaName(cname="��¼ʧ�ܴ���")
    private Integer loginfailnum;

	public Integer getLoginfailnum(){
		return loginfailnum;
	}
	
	public void setLoginfailnum( Integer loginfailnum ){
		this.loginfailnum = loginfailnum;
	}
	

    @PropertyChinaName(cname="�˺���Ч��")
    private Integer accountttl;

	public Integer getAccountttl(){
		return accountttl;
	}
	
	public void setAccountttl( Integer accountttl ){
		this.accountttl = accountttl;
	}
	

    @PropertyChinaName(cname="��������")
    private String passquestion;

	@Column(length=3000)
	public String getPassquestion(){
		return passquestion;
	}
	
	public void setPassquestion( String passquestion ){
		this.passquestion = passquestion;
	}
	

    @PropertyChinaName(cname="��Ա��ʶ")
    private Integer flag;

	public Integer getFlag(){
		return flag;
	}
	
	public void setFlag( Integer flag ){
		this.flag = flag;
	}
	

    @PropertyChinaName(cname="����")
    private String password;

	@Column(length=3000)
	public String getPassword(){
		return password;
	}
	
	public void setPassword( String password ){
		this.password = password;
	}
	

    @PropertyChinaName(cname="�˺�״̬")
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
	
	//zhongqun 2011-11-22 ����rtx�˺�����
	
	 @PropertyChinaName(cname="rtx�˺�")
	    private String rtxaccount;

	public String getRtxaccount() {
		return rtxaccount;
	}

	public void setRtxaccount(String rtxaccount) {
		this.rtxaccount = rtxaccount;
	}

		
	

}