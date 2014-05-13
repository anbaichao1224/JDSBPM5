package com.kzxd.changepssword.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.kzxd.changepssword.entity.personaccount;
import com.kzxd.changepssword.service.ChangePsswordService;
import com.kzxd.changepssword.service.impl.ChangePsswordServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePsswordAction extends ActionSupport {
     
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;     //用户名
	private String newpssword;  //新密码
	private String oldpssword;  //旧密码
	
	
	
	
	public String execute() throws Exception {
		
		
	HttpServletRequest request=ServletActionContext.getRequest();
	  HttpSession session = request.getSession(true);
	 String personId = session.getAttribute("personId").toString();
	

	 ChangePsswordService changepsswordsevice=new ChangePsswordServiceImpl(); 
		
			List<personaccount> person=changepsswordsevice.getPassword(username, personId,oldpssword);
	
		  if(person.size()>0){
				personaccount mt=person.get(0);
				String uuid= mt.getUuid().toString();
			    changepsswordsevice.uupdataPssword( uuid,newpssword, mt);
				return SUCCESS; 
			}
			  
			
	         else {
			   
			   return INPUT;
			   }
			   
		
}
 




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getNewpssword() {
		return newpssword;
	}




	public void setNewpssword(String newpssword) {
		this.newpssword = newpssword;
	}




	public String getOldpssword() {
		return oldpssword;
	}




	public void setOldpssword(String oldpssword) {
		this.oldpssword = oldpssword;
	}

}

/**	ChangePsswordServicea changepsswordsevicea=new ChangePsswordServiceImpla();
List<personaccounta>persona=changepsswordsevicea.getPssword(username, oldpssword);
if(persona.size()>0){
	 personaccounta st=persona.get(0);
	// personaccounta ma=new personaccounta();
	 String uuid=st.getUuid().toString();
	 changepsswordsevicea.updataPssword(newpssword, uuid);
	 return SUCCESS; 
}
else{
	 
	 return INPUT;

}
 
}*/
