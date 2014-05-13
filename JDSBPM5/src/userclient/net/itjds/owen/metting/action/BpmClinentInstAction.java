/*jadclipse*/// Decompiled b"y Jad" v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.*;
import net.itjds.common.util.DateUtility;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class BpmClinentInstAction extends BPMActionBase
{

    public BpmClinentInstAction()
    {
        start = Integer.valueOf(0);
        limit = Integer.valueOf(20);
        bpmUserClientUtil = new BPMUserClientUtil();
    }

	public String execute() throws Exception {
		return null;
	}


    
    private BPMUserClientUtil bpmUserClientUtil;
    private List historyList;
    private WorkflowClientService client;
    private ArrayList webHistoryList;
    private ArrayList webCurrentWrpList;
    
    private class HistoryWrp
    {

        public ActivityInstHistory getActivityInstHistory()
        {
            return activityInstHistory;
        }
        
        public ActivityDef getActivityDef()
        {
            return activityDef;
        }

        public String getActivityHistoryId()
        {
            return activityHistoryId;
        }

        public List getPerformers()
        {
            return performers;
        }

        public String getArrivedTime()
        {
            return DateUtility.formatDate(activityInstHistory.getArrivedTime(), "yyyy-MM-dd HH:mm:ss");
        }

        public String getEndTime()
        {
            return DateUtility.formatDate(activityInstHistory.getEndTime(), "yyyy-MM-dd HH:mm:ss");
        }

        private ActivityInstHistory activityInstHistory;
        private String activityHistoryId;
        private List performers;
        private ActivityDef activityDef;
        final BpmClinentInstAction this$0;

        public HistoryWrp(ActivityInstHistory history, WorkflowClientService vlient)
            throws BPMException
        {
            this$0 = BpmClinentInstAction.this;
            activityInstHistory = history;
            activityHistoryId = history.getActivityHistoryId();
            performers = (List)client.getActivityInstHistoryRightAttribute(activityHistoryId, "PERFORMER", null);
            activityDef = client.getActivityDef(history.getActivityDefId());
        }
    }
	public String getProcessInstById() throws Exception {
		bpmUserClientUtil = new BPMUserClientUtil();
        client = bpmUserClientUtil.getClient();
		historyList = client.getActivityInstHistoryListByProcessInst(
				processInstId, null);
		return "success";
	}

	public List getHistoryWrpList() throws BPMException {
		if (webHistoryList == null) {
			webHistoryList = new ArrayList();
			for (int i = 0; historyList.size() > i; i++) {
				ActivityInstHistory activityHistory = (ActivityInstHistory) historyList
						.get(i);
				HistoryWrp historyWrp = new HistoryWrp(activityHistory, client);
				webHistoryList.add(historyWrp);
			}

		}
		return webHistoryList;
	}
    
    
	
	 private class CurrentWrp
	    {

	        public ActivityInst getActivityInst()
	        {
	            return activityInst;
	        }

	        public String getActivityHistoryId()
	        {
	            return activityInstId;
	        }

	        public ActivityDef getActivityDef()
	        {
	            return activityDef;
	        }

	        public List getPerformers()
	        {
	            return performers;
	        }

	        public String getStatus()
	        {
	            String status = "";
	            if(activityInst.getState().equals("READ"))
	                status = "\u4F20\u9605";
	            else
	            if(activityInst.getState().equals("ENDREAD"))
	                status = "\u9605\u6BD5";
	            else
	            if(activityInst.getState().equals("notStarted"))
	                status = "<span style='color:red'>\u672A\u9605</span>";
	            else
	            if(activityInst.getState().equals("running"))
	                status = "\u6B63\u5728\u529E\u7406";
	            return status;
	        }

	        public String getArrivedTime()
	        {
	            return DateUtility.formatDate(activityInst.getArrivedTime(), "yyyy-MM-dd HH:mm:ss");
	        }

	        public int getHistoryWrpSize()
	        {
	            return historyList.size();
	        }

	        private ActivityInst activityInst;
	        private String activityInstId;
	        private List performers;
	        private ActivityDef activityDef;
	        final BpmClinentInstAction this$0;

	        public CurrentWrp(ActivityInst activityInst, WorkflowClientService vlient)
	            throws BPMException
	        {
	            this$0 = BpmClinentInstAction.this;
	            //super();
	            this.activityInst = activityInst;
	            activityInstId = activityInst.getActivityInstId();
	            performers = (List)client.getActivityInstRightAttribute(activityInstId, "PERFORMER", null);
	            activityDef = client.getActivityDef(activityInst.getActivityDefId());
	        }
	    }
	
	public List getCurrentWrpList() throws BPMException {
		if (webCurrentWrpList == null) {
			webCurrentWrpList = new ArrayList();
			getActivityInst();
			//this.activityInst = (ActivityInst)parExprocession("$currActivityInst");
			//this.activityInst = (ActivityInst)ActionContext.getContext().getValueStack().findValue("$currActivityInst");
			List activityList = this.activityInst.getProcessInst()
					.getActivityInstList();
			for (int i = 0; activityList.size() > i; i++) {
				ActivityInst activityInst = (ActivityInst) activityList.get(i);
				CurrentWrp currentWrp = new CurrentWrp(activityInst, client);
				webCurrentWrpList.add(currentWrp);
			}

		}
		return webCurrentWrpList;
	}

    public String getActivityDefId()
    {
        return activityDefId;
    }

    public void setActivityDefId(String activityDefId)
    {
        this.activityDefId = activityDefId;
    }

    public String getProcessDefName()
    {
        return processDefName;
    }

    public String getProcessDeId()
    {
        return processDeId;
    }

    public void setProcessDeId(String processDeId)
    {
        this.processDeId = processDeId;
    }

    private String activityDefId;
    public String processDefName;
    public String processDeId;
    public String processDefVersionIds;
    private String processInstId;
    private Integer start;
    private Integer limit;
    
    
    
    
    public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProcessDefVersionIds() {
		return processDefVersionIds;
	}

	public void setProcessDefVersionIds(String processDefVersionIds) {
		this.processDefVersionIds = processDefVersionIds;
	}
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\Workspaces\MyEclipse8.5\JDSBPM4\defaultroot\WEB-INF\lib\itjds_worklist.jar
	Total time: 78 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/