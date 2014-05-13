/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

import java.util.List;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.*;
import net.itjds.common.org.base.*;
import net.itjds.owen.metting.action.BpmMatterAction;
import net.itjds.owen.metting.action.BpmMatterInfoAction;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;

public class BPMClientInsertAction extends BPMActionBase
{

    public BPMClientInsertAction()
    {
    }

    public void setProcessDefId(String processDefId)
    {
        this.processDefId = processDefId;
    }

    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    public String getActivityInstId()
    {
        return activityInstId;
    }

    public boolean isNoNeedSelect(String toActivityDefId)
        throws BPMException
    {
        String performType = (String)getClient().getActivityDefRightAttribute(toActivityDefId, "PERFORMTYPE", null);
        return performType.equals("NEEDNOTSELECT") || performType.equals("NOSELECT");
    }

    public boolean isContinuous(String toActivityDefId)
        throws BPMException, PersonNotFoundException
    {
        List personList = (List)getClient().getActivityDefRightAttribute(toActivityDefId, "PERFORMERSELECTEDID", null);
        net.itjds.common.org.base.Person person = OrgManagerFactory.getOrgManager().getPersonByID(getClient().getConnectInfo().getUserID());
        return personList.contains(person);
    }

    public String execute()
    {
        try
        {
        	
            ProcessInst processInst = null;
            BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
            WorkflowClientService client = bpmUserClientUtil.getClient();
            client.beginTransaction();
            processInst = client.newProcess(processDefId, processName, null, null);
            activityInst = (ActivityInst)processInst.getActivityInstList().get(0);
            activityInstId = activityInst.getActivityInstId();
            client.commitTransaction();
            
            BpmMatterInfoAction bmaction = new BpmMatterInfoAction();
            bmaction.setUuid("1559094-132388547c4-2f87dce8d8643a73d4d0ede3f3cf44cb");
            //bmaction.proinst("1559094-132388547c4-2f87dce8d8643a73d4d0ede3f3cf44cb", processInst.getProcessInstId());
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    private String processDefId;
    private String processName;
    private String activityInstId;
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\Workspaces\MyEclipse8.5\JDSBPM4\defaultroot\WEB-INF\lib\itjds_userclient.jar
	Total time: 156 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/