package kzxd.rtx;

import java.util.List;

import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.OrgNotFoundException;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import rtx.RTXSvrApi;

public class RtxUser {
	public void addUser(String username,String updatename,String password,String personid,String DeptId,String ChsName,String gender){
		RTXSvrApi RtxsvrapiObj = new RTXSvrApi();
		int res = RtxsvrapiObj.userIsExist(username);
		if(res==0){
			if(gender.equals("0")){
				gender = "";
			}else if(gender.equals("1")){
				gender = "0";
			}else if(gender.equals("2")){
				gender = "1";
			}
			RtxsvrapiObj.SetUserSimpleInfo(username, ChsName, "", gender, "", "", password);
			
		}else{
			RtxsvrapiObj.addUser(updatename, DeptId, ChsName, password);
			/*try{
			if(DeptId==null||DeptId.equals("")){
				Person person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personid);
				List<Org> orglist = person.getOrgList();
				for(Org org:orglist){
					RtxsvrapiObj.addUser(updatename, org.getIndex()+"", ChsName, password);
				}
			}else{
				Org org = null;
				org = (Org)OrgManagerFactory.getOrgManager().getOrgByID(DeptId);
				RtxsvrapiObj.addUser(updatename, org.getIndex()+"", ChsName, password);
			}
			}catch(Exception e){
				e.printStackTrace();
			}*/
		}
		RtxsvrapiObj.UnInit();
	}
	
	public void delUser(String personid){
		RTXSvrApi RtxsvrapiObj = new RTXSvrApi();
		try {
			Person person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personid);
			int res = RtxsvrapiObj.userIsExist(person.getAccount());
			if(res==0){
				RtxsvrapiObj.deleteUser(person.getAccount());
			}
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
