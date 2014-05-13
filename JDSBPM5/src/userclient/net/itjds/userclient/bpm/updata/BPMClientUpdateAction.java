
package net.itjds.userclient.bpm.updata;



import net.itjds.userclient.common.BPMActionBase;

import com.opensymphony.xwork2.Action;


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
public class BPMClientUpdateAction extends BPMActionBase {
  
	public BPMClientUpdateAction() {
		super();	
	}
	public String nextActivityInstId;
	public String execute() throws Exception {
		
		try {	
			this.getClient().beginTransaction();
			int nResult = this.getBpmUserClientUtil().performUpdate(this.getActivityInst().getActivityInstId());
			if (nResult == -1) {
				this.getClient().rollbackTransaction();
				return Action.ERROR;
			}
			
			this.getClient().commitTransaction();
			
			return Action.NONE;
		} catch (Exception e) {
			this.getClient().rollbackTransaction();
			e.printStackTrace();
			return this.ERROR;
		}
	}
	
	

	

}
