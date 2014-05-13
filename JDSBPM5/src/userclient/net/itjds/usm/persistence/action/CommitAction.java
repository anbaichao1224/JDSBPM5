
package net.itjds.usm.persistence.action;


import com.opensymphony.xwork2.Action;

import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.conf.Constants;



public class CommitAction implements Action{
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			CommitAction.class);
	public String subSystemId;
	public String execute() throws Exception {
			OrgManagerFactory.getOrgManager(subSystemId).getCacheManager().reloadAll();
		return this.SUCCESS;
	}
	public String getSubSystemId() {
		return subSystemId;
	}
	public void setSubSystemId(String subSystemId) {
		this.subSystemId = subSystemId;
	}
	
}
