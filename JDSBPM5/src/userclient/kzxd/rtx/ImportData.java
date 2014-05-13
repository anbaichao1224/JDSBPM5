package kzxd.rtx;

import java.util.List;

import rtx.RTXSvrApi;

import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.usm.persistence.action.DepartAction;
import net.itjds.usm.persistence.service.OrgManager;
import net.itjds.usm.persistence.service.impl.OrgManagerImpl;

public class ImportData {
	
	public void getAllOrgs(){
		RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();
		
		Org[] orgs = OrgManagerFactory.getOrgManager().getTopOrgs();
		for(Org org : orgs){
			net.itjds.usm.persistence.model.Org orgbean = null;
			int iRet = RtxsvrapiObj.addDept(orgbean.getRtxorgid()+"", "", org.getName(), "0");
			if (iRet == 0)
			{
				
			}
			else 
			{
			}
			List<Person> personlist = org.getPersonList();
			if(personlist.size()>0){
				for(Person person:personlist){
					RtxsvrapiObj.addUser(person.getAccount(), orgbean.getRtxorgid()+"", person.getName(), person.getPassword());
				}
			}
			List<Org> childorglist = org.getChildrenList();
			for(Org corg : childorglist){
			getChildOrgs(corg,org.getID()+"",RtxsvrapiObj);
			}
		}
		RtxsvrapiObj.UnInit();
	}
	
	public void getChildOrgs(Org org,String pid,RTXSvrApi  RtxsvrapiObj){
		net.itjds.usm.persistence.model.Org orgbean = null;
		int iRet = RtxsvrapiObj.addDept(orgbean.getRtxorgid()+"", "", org.getName(), pid);
		if (iRet == 0)
		{
			
		}
		else 
		{
		}

		List<Org> childorglist = org.getChildrenList();
		for(Org childorg :childorglist){
					
					getChildOrgs(childorg,orgbean.getRtxorgid()+"",RtxsvrapiObj);
		}
		List<Person> personlist = org.getPersonList();
		if(personlist.size()>0){
			for(Person person:personlist){
				RtxsvrapiObj.addUser(person.getAccount(), orgbean.getRtxorgid()+"", person.getName(), person.getPassword());
			}
		}
		
	}
	
}
