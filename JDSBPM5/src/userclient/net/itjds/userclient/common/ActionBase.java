
package net.itjds.userclient.common;


import java.util.Map;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;






import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.data.DataMap;


import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;


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
public abstract class ActionBase implements Action {

	protected BPMUserClientUtil bpmUserClientUtil ;

	public ActionBase() {
		this.bpmUserClientUtil = new BPMUserClientUtil();
	}


	public BPMUserClientUtil getBpmUserClientUtil() {
		return bpmUserClientUtil;
	}


	public void setBpmUserClientUtil(BPMUserClientUtil bpmUserClientUtil) {
		this.bpmUserClientUtil = bpmUserClientUtil;
	}


		
}
