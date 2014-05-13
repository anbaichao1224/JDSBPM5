
package net.itjds.usm2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.admin.AdminConfig;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;

import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.conf.Constants;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.usm2.USMException;
import net.itjds.usm2.db.UsmService;


public class LoginAction {
	protected static Log log =
		LogFactory.getLog(Constants.CONFIG_KEY,
				LoginAction.class);


	private String username;

	private String password;
	private DbPerson person; 
	private String message;
	private String personId;

	public DbPerson getPerson() {
		return person;
	}

	public void setPerson(DbPerson person) {
		this.person = person;
	}

	public String execute()  {
		try {
			
		  if(username.equals(AdminConfig.getValue("SuperAdmin.username"))) {            
	          if(password.equals(AdminConfig.getValue("SuperAdmin.password"))) {
	        	  return "webmaster_jsp";
	          }
	      }
		  
			HttpServletRequest request = ServletActionContext.getRequest();
			OrgManager orgManager = OrgManagerFactory.getOrgManager();
			try {
				person = (DbPerson) orgManager.getPersonByAccount(username);
			} catch (PersonNotFoundException e) {
			
				e.printStackTrace();
				return "login_jsp";
			}

		
			if (!password.equals(person.getPassword())) {
					return "login_jsp";
			
			}
			
		
			//person.setIP(BPMSessionFactory.getIpAddr(request));
			person.setStatus(person.available);
			person.setPassword(password);
			request.setAttribute("personId", person.getID());
			ActionContext context = ActionContext.getContext();
			context.getValueStack().setValue("personId", person.getID());
	    
			request.getSession().setAttribute("personId", person.getID());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
//		UsmService usmService = new RoPersonextaccountUsmServiceImpl();
//		try {
//			List personList = usmService.getUsmWhere("where PERSONID='" + personId + "'");
//			if(personList.size() != 0){
//				return "admin_jsp";
//			}
//		} catch (USMException e) {
//			e.printStackTrace();
//		}
		return "login_jsp";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	


}
