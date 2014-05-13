package kzxd.rtx;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jds.usm.persistence.model.Application;
import com.opensymphony.xwork2.Action;

import net.itjds.bpm.engine.AdminService;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowServer;
import net.itjds.common.org.base.App;
import net.itjds.common.org.base.Module;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.fdt.metting.ModuleBean;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.usm.persistence.model.Personaccount;
import net.itjds.usm.persistence.service.PersonaccountManager;
import rtx.RTXSvrApi;

public class SendNotify extends BPMActionBase{
	
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendmsg(){
	
		RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();   
		performPersonIdList = getPerson(RtxsvrapiObj);
		
		RtxsvrapiObj.UnInit();
	}
	
	public void sendMsn(RTXSvrApi  RtxsvrapiObj,String receivers,String modulename,String url){
		String title = receivers+":待办公文提醒";
		String msg = modulename+"模块里有公文需要办理，请及时办理[aa|http://127.0.0.1:82/"+url+"]";//http://127.0.0.1:82/"+url+"]";
		String type = "0";
		String delayTime = "0";
		int iRet= -1;
		
		//iRet = RtxsvrapiObj.sendNotify(receivers,title,msg, type,delayTime);
		msg = modulename+"模块里有公文需要办理，请及时办理";
		iRet = RtxsvrapiObj.sendNotify(receivers,title,msg, type,delayTime);
		if (iRet == 0)
		{
		}
		else 
		{
		}
	}
	
	public String getPerson(RTXSvrApi  RtxsvrapiObj){
		String[] personIds = performPersonIdList.split(",");
		String receivers = "";
		for(int i=0;i<personIds.length;i++){
				//Personaccount personaccount = (Personaccount)personaccountManager.get(personIds[i]);
				try {
					Person person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personIds[i]);
					if(person.getAccount()!=null){
						if(!person.getAccount().equals("")){
							getModule(personIds[i],RtxsvrapiObj,person.getAccount());
							
						}
						
					}
				} catch (PersonNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
				
		}
		return receivers;
	}
	
	public List<ModuleBean> getModule(String personId,RTXSvrApi  RtxsvrapiObj,String receivers){
		String processversionId = "";//this.getActivityInst().getProcessDef().getProcessDefVersion(0).getProcessDefVersionId();
		List modulealllist = new ArrayList();
		List<ModuleBean> modulelist = new ArrayList<ModuleBean>();
		try {
			BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
			//WorkflowClientService client = bpmUserClientUtil.getClient();
			AdminService client = null;
			try {
				client = WorkflowServer.getInstance().getAdminService(bpmUserClientUtil.getClient());
			} catch (BPMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				processversionId = client.getActivityInst(activityInstId).getProcessDefVersion().getProcessDefVersionId();
			} catch (BPMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			modulealllist = OrgManagerFactory.getOrgManager().getPersonByID(personId).getAllRightModule();
			
			if(modulealllist!=null&&modulealllist.size()>0){
				for(int i=0;i<modulealllist.size();i++){
					Module module = (Module)modulealllist.get(i);
					
					if(module.getUrl().indexOf("type=AllWaitList&param['state']=5&param['processDefVersionIds']="+processversionId)>=0){
						List<App> applist = module.getAppList();
						if(applist.size()>0){
							for(int j =0;j<applist.size();j++){
								sendMsn(RtxsvrapiObj,receivers,applist.get(j).getName(),module.getUrl());
							}
						
						}
					}
				}
				
				
			}
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modulelist;
	}
	
	
	
	private String activityInstId;
	private String performPersonIdList;
	
	PersonaccountManager personaccountManager;
	
	public String getActivityInstId() {
		return activityInstId;
	}
	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}
	public String getPerformPersonIdList() {
		return performPersonIdList;
	}
	public void setPerformPersonIdList(String performPersonIdList) {
		this.performPersonIdList = performPersonIdList;
	}

	public PersonaccountManager getPersonaccountManager() {
		return personaccountManager;
	}

	public void setPersonaccountManager(PersonaccountManager personaccountManager) {
		this.personaccountManager = personaccountManager;
	}
	
	
	
}
