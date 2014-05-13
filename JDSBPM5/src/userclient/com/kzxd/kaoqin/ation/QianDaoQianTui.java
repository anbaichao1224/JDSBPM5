package com.kzxd.kaoqin.ation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;

import org.apache.struts2.ServletActionContext;




import com.kzxd.kaoqin.pojo.kaoqin;
import com.kzxd.kaoqin.pojo.persona;

import com.kzxd.kaoqin.service.KaoQinService;
import com.kzxd.kaoqin.util.Constans;
import com.kzxd.kaoqin.util.KQUtil;
import com.opensymphony.xwork2.ActionSupport;


public class QianDaoQianTui extends ActionSupport {
	private int isZt;     //是否早退
	private int isCd;      //是否迟到
	private String isTc;   //签退
	private String isQd;  //签到
	private String username;
	private String uuid;
	private Date datekint;
	private Date datekout;
	private String personId;
	private persona person;
	KaoQinService  kaoqinservice;
	public String execute() throws Exception{

	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(true);
	personId=(String) session.getAttribute("personId".toString());
	isTc=(String)request.getParameter("isTc");
	isQd=(String)request.getParameter("isQd");
	Person personaa=(Person) OrgManagerFactory.getOrgManager().getPersonByID(personId);
	String username=personaa.getName();

		
	    boolean isCdOrZt;
		
	//	只要是结果值为1，则不早退，不迟到，否则。。。
   if("two".equals(isTc)&&(username!=null&&username.length()>0)){
	   Date date=new Date();
	   isTc=null;
		datekout=date;//早退
	     isCdOrZt=false;
		String qiandaoshijian=KQUtil.getNeedCurrent(datekout);
		int qiantui=KQUtil.getNeedTime(qiandaoshijian);
		if(qiantui>Constans.QIANTUISHIJIAN){
			isZt=1;}
		else{
		  isZt=0;
		}
		List<kaoqin> qn=kaoqinservice.getRecord( username, datekout,personId);
		if(qn.size()>0){
			for(int i=0;i<=qn.size();i++){
				kaoqin oop=qn.get(i);
			kaoqinservice.updateisZt(isZt,datekout,oop,isCdOrZt);
			kaoqinservice.toQin(response);	
	
		
		
		}
		
		}
	
   }
			
	//调用后台程序写入库
    
   
     if("one".equals(isQd)&&(username!=null&&username.length()>0)){
    	 isQd=null;
    	 Date date=new Date();
		datekint=date;
	    isCdOrZt=true;
		String qiantuishijian=KQUtil.getNeedCurrent(datekint);
		int qiantu=KQUtil.getNeedTime(qiantuishijian);
		if(qiantu<Constans.QIANDAOSHIJIAN){
			isCd=1;}
			else{isCd=0;}
		//
		  
		List<kaoqin> qn=kaoqinservice.getRecord(username, datekint,personId);
		
		if(qn!=null){
			for(int i=0;i<=qn.size();i++){
				kaoqin oop=qn.get(i);
			kaoqinservice.updateisCd(isCd,datekint,oop,isCdOrZt);
			kaoqinservice.toQin(response);	
			}
		}	
		else{
			 kaoqinservice.baoCun(isCd,datekint,username,personId,isCdOrZt);
				kaoqinservice.toQin(response);	
			
		}
		                             
	
     }
 
	                                                        
 
	return null;
		
	
}
	
	
	public KaoQinService getKaoqinservice() {
		return kaoqinservice;
	}

	public void setKaoqinservice(KaoQinService kaoqinservice) {
		this.kaoqinservice = kaoqinservice;
	}

	public String getIsTc() {
		return isTc;
	}

	public void setIsTc(String isTc) {
		this.isTc = isTc;
	}

	public String getIsQd() {
		return isQd;
	}

	public void setIsQd(String isQd) {
		this.isQd = isQd;
	}

	public int getIsZt() {
		return isZt;
	}
	public void setIsZt(int isZt) {
		this.isZt = isZt;
	}
	public int getIsCd() {
		return isCd;
	}
	public void setIsCd(int isCd) {
		this.isCd = isCd;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}




	

}
