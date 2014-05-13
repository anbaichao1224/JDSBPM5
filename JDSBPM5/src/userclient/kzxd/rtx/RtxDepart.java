package kzxd.rtx;

import java.util.List;

import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import rtx.RTXSvrApi;

public class RtxDepart {
	public void addorUpdateDepart(String deptId,String deptname,String parentId){
		RTXSvrApi RtxsvrapiObj = new RTXSvrApi();
		int res = RtxsvrapiObj.deptIsExist(deptId);
		if(res==0){
			int ress = RtxsvrapiObj.setDept(deptId, "", deptname, parentId);
			if(ress==0){
			}
		}else{
			res = RtxsvrapiObj.addDept(deptId, "", deptname, parentId);
		}
		
	}
	
	public void delDepart(String deptId){
		RTXSvrApi RtxsvrapiObj = new RTXSvrApi();
		if(RtxsvrapiObj.deptIsExist(deptId)==0){
			RtxsvrapiObj.deleteDept(deptId, "1");
		}
		
		
	}
}
