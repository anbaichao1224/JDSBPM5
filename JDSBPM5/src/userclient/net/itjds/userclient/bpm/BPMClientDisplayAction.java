/**
 * $RCSfile: BPMClientDisplayAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/06/09 14:41:58 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.bpm;

import java.util.List;

import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.database.right.DbActivityDefRight;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.userclient.common.BPMActionBase;

import com.opensymphony.xwork2.Action;




/**
 * <p>
 * Title: BPM����������ϵͳ
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
public class BPMClientDisplayAction extends BPMActionBase {
	public BPMClientDisplayAction()  {
		super();
	}
	
	/**
	 * ���ݶ����ж��Ƿ���Ҫ����ѡ�˴���
	 * @param toActivityDefId
	 * @return
	 * @throws BPMException
	 */
	public boolean isNoNeedSelect(String toActivityDefId) throws BPMException{
		String performType=(String) this.getClient().getActivityDefRightAttribute(toActivityDefId, OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMTYPE, null);
		if (performType.equals(OARightConstants.PERFORMTYPE_NEEDNOTSELECT)||performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)){
			return true	;
		}	
		
	return false;
    }

	/**
	 * ���ݶ����ж��������һ��д��������е�ǰ��������ֱ�Ӵ򿪽��а���
	 * @param toActivityDefId
	 * @return
	 * @throws BPMException
	 * @throws PersonNotFoundException
	 */
	public boolean isContinuous(String toActivityDefId) throws BPMException, PersonNotFoundException{
		List<Person> personList=(List<Person>) this.getClient().getActivityDefRightAttribute(toActivityDefId, DbActivityDefRight.ATT_PERFORMERSELECTEDID, null);
		 Person person=OrgManagerFactory.getOrgManager().getPersonByID(this.getClient().getConnectInfo().getUserID());
		 if (personList.contains(person)){
			 return true;
		 }
		 return false;
	}
	
	
	public String execute() throws Exception {
	  
	  
	   if (this.getActivityInst().isCanSignReceive()){
		   this.getClient().signReceive(this.getActivityInst().getActivityInstId(), null);	
	   }	  
		int nResult = bpmUserClientUtil.performDisplay(this.getActivityInst().getActivityInstId());	
		if (nResult == -1) {
			return Action.ERROR;
		}
		if (nResult == -2) {
			return Action.INPUT;
		}	
		
		return Action.SUCCESS;
	}
	
	
	
}
