package net.itjds.userclient.usm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.util.DateUtility;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.usm.dao.RoLogDAO;

import org.apache.struts2.ServletActionContext;
class OnlineUser extends BPMActionBase{

	private String personId;
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request=ServletActionContext.getRequest();
		OrgManager orgManager = OrgManagerFactory.getOrgManager();
		DbPerson person = (DbPerson)orgManager.getPersonByID(personId);
		person.setIP(null);
		Map<String, String> userList = null;
		ServletContext application = request.getSession().getServletContext();
		if(application.getAttribute("userList") != null){
			userList = (Map<String, String>)application.getAttribute("userList");
			userList.remove(personId);
			application.setAttribute("userList", userList);	
			
		}
		return null;
	}
	
	public void getOnlineUsers(){
		HttpServletRequest request=ServletActionContext.getRequest();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		OrgManager orgManager = OrgManagerFactory.getOrgManager();
		Map<String, String> userList = null;
		ServletContext application = request.getSession().getServletContext();
		if(application.getAttribute("userList") != null){
			List<String> onlineUsers = (List<String>)application.getAttribute("userList");
			for(int i=0; i<onlineUsers.size(); i++){
				String pid = onlineUsers.get(i);
				try {
					DbPerson person = (DbPerson)orgManager.getPersonByID(pid);
					Map<String, String> map = new HashMap<String, String>();
					map.put("personId", person.getID());
					map.put("name", person.getName());
					map.put("loginIP", person.getIP());
					
				//	map.put("loginTime", )
				} catch (PersonNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	

}
