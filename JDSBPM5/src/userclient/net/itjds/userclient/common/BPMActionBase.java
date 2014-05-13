
package net.itjds.userclient.common;


import java.util.Map;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;






import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.data.DataMap;


import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.base.Person;


/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhangli
 * @version 2.0
 */
public abstract class BPMActionBase implements Action {

	protected BPMUserClientUtil bpmUserClientUtil ;

	protected String activityInstId;

	protected ProcessInst processInst;
	
	protected WorkflowClientService client;
	
	protected String actiontype="result";


	protected Log logger = LogFactory.getLog(BPMActionBase.class
			.getName());

	protected ActivityInst activityInst;

	public String formID;
	
	private DataMap mapdaomap;
	private String state;

	private ActivityInstHistory activityInstHistory;
	

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public BPMActionBase() {
		
		this.bpmUserClientUtil = new BPMUserClientUtil();
	}
	 public WorkflowClientService getClient(){
		 WorkflowClientService client = this.getBpmUserClientUtil().getClient();
		
	    	return client;
	     }

	public BPMUserClientUtil getBpmUserClientUtil() {
		return bpmUserClientUtil;
	}


	public void setBpmUserClientUtil(BPMUserClientUtil bpmUserClientUtil) {
		this.bpmUserClientUtil = bpmUserClientUtil;
	}


	public String getActivityInstId() {
		
		return activityInstId;
	}
	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}

	
	public ProcessInst getProcessInst() {
		if (this.processInst==null){
			try {
				
				if (this.getActivityInst()!=null){
					this.processInst=this.getActivityInst().getProcessInst();
				}else if(this.getActivityInstHistory()!=null){
					this.processInst=this.getActivityInstHistory().getProcessInst();
				}    	
				
				
			} catch (BPMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.processInst;
	}
	

	
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
	}


	/**
	 * 
	 * @throws OAException
	 * @throws BPMException
	 * @throws UserClientException 
	 */
	public void init() throws  BPMException, UserClientException  {
			if (this.activityInstId == null) {
			this.activityInstId = (String) ActionContext.getContext()
					.getValueStack().findValue("activityInstId");
			if (this.activityInstId == null || activityInstId.equals("")) {
				// 如果是第二次发送时数据可能在Parameters()中
				this.activityInstId = (String) ServletActionContext
						.getRequest().getParameter("activityInstId");
			}			
		}	
		if (this.formID == null) {
			this.formID = (String) ActionContext.getContext()
					.getValueStack().findValue("formID");
			if (this.formID == null
					|| formID.equals("")) {
				// 如果是第二次发送时数据可能在Parameters()中
				this.formID = (String) ServletActionContext
						.getRequest().getParameter("formID");
			}			
		}	
		this.client = bpmUserClientUtil.getClient();
	
		this.activityInst=(ActivityInst) ActionContext.getContext()
		.getValueStack().findValue("$currActivityInst");
		try{
		this.processInst=activityInst.getProcessInst();
		
		this.state = activityInst.getState();
		}catch(Exception e){
			
		}
	}


	/**
	 * 
	 * @return
	 * @throws OAException
	 * @throws BPMException
	 * @throws UserClientException 
	 */
	public DataMap getMapDAO() throws  BPMException, UserClientException  {
		init();
		//if (this.mapdaomap==null){
//			mapdaomap=(Map) ActionContext.getContext().getSession().get(this.activityInstId);
//			if (mapdaomap==null){
		this.mapdaomap=this.activityInst.getAllMapDAO(this.client.getConnectInfo().getUserID());
//			ActionContext.getContext().getSession().put(this.activityInstId, mapdaomap);
//			}
//			
//		}
	
		return this.mapdaomap;
	}
	


	public ActivityInst getActivityInst() {
		if (activityInst==null){
			this.activityInst=(ActivityInst) this.parExprocession("$currActivityInst");
		}
		return activityInst;
	}
	
	public ActivityInstHistory getActivityInstHistory() {
		if (activityInstHistory==null){
			this.activityInstHistory=(ActivityInstHistory) this.parExprocession("$currActivityInstHistory");
		}

		
		return activityInstHistory;
	}


		public Map getMapdaomap() {
			return mapdaomap;
		}

		public void setMapdaomap(DataMap mapdaomap) {
			this.mapdaomap = mapdaomap;
		}


		public String getActiontype() {
			return actiontype;
		}


		public void setActiontype(String actiontype) {
			this.actiontype = actiontype;
		}


		public Object parExprocession(String exprocession){
			return ActionContext.getContext().getValueStack().findValue(exprocession);
		}

		public Person getCurrPerson (){
			return (Person) this.parExprocession("$currPerson");
		}
		
}
