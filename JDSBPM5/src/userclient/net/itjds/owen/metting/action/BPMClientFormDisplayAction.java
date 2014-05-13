/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.util.List;
import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.owen.metting.action.BpmMatterInfoAction;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.UserClientException;

public class BPMClientFormDisplayAction extends BPMActionBase
{

    public BPMClientFormDisplayAction()
    {
    }

    public FormClassBean getCurrForm()
        throws BPMException, UserClientException
    {
        List formList = getActivityInst().getActivityDef().getAllDataFormDef();
        if(formList.size() == 1)
            return (FormClassBean)formList.get(0);
        for(int k = 0; formList.size() > k; k++)
        {
            FormClassBean formClassBean = (FormClassBean)formList.get(k);
            if(formClassBean.getId().equals(getFormID()))
                return formClassBean;
        }

        FormClassBean formClassBean = getActivityInst().getActivityDef().getMainFormDef();
        return formClassBean;
    }

    public String execute()
        throws Exception
    {
    	BpmMatterInfoAction bmaction = new BpmMatterInfoAction();
        bmaction.setUuid(sxxxid);
        bmaction.proinst(sxxxid, super.getProcessInst().getProcessInstId(),super.getActivityInstId());
        return "success";
    }
    
    
    private String sxxxid;
    public String getSxxxid() {
    	return sxxxid;
    }

    public void setSxxxid(String sxxxid) {
    	this.sxxxid = sxxxid;
    }
}





/*
	DECOMPILATION REPORT

	Decompiled from: E:\Workspaces\MyEclipse8.5\JDSBPM4\defaultroot\WEB-INF\lib\itjds_userclient.jar
	Total time: 150 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/