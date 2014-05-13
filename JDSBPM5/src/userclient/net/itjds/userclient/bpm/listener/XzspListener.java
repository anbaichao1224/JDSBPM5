package net.itjds.userclient.bpm.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.comment.dao.BpmCommentsDAO;

import com.kzxd.xzsp.action.XZSPAction;
import com.kzxd.xzsp.action.XzspjkTempAction;
import com.kzxd.xzsp.util.Node;
import com.kzxd.xzsp.util.XzspjkTempBean;
import com.opensymphony.xwork2.ActionContext;

public class XzspListener implements ActivityListener{

	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}
	
	public void activityCompleted(ActivityEvent event) throws BPMException {
		String activityinstid=event.getActivityInsts()[0].getActivityInstId();
		String processinstid=event.getActivityInsts()[0].getProcessInstId();
		String activitydefid=event.getActivityInsts()[0].getActivityDefId();
		
		String nodename=event.getActivityInsts()[0].getActivityDef().getName();
		ActionContext context=ActionContext.getContext();		
		Object result1=context.getValueStack().findValue("$currPerson.name");
		String nodeactor=(String) result1;
		Object result2=context.getValueStack().findValue("$Fdtnmwsxtsp.fdtOaWsxtspDAO.bsnum");
		String bsnum=(String) result2;
		Date date=new Date();
		String handlerdate=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+
		" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		
		Node node = new Node();
		node.setNodeid(activityinstid);
		node.setNodename(nodename);
		node.setNodeactor(nodeactor);
		node.setHandlerdate(handlerdate);
		node.setDepartment("7335954");
		
		DBBeanBase dbbase=new DBBeanBase("bpm");
		Connection conn=dbbase.getConn();
		BpmCommentsDAO bpmCommentsDAO=new BpmCommentsDAO();
		
		bpmCommentsDAO.setActivityinstid(activityinstid);
		bpmCommentsDAO.setProcessinstid(processinstid);
		bpmCommentsDAO.setActivitydefid(activitydefid);
		
		DAOFactory  factory = new DAOFactory(conn,bpmCommentsDAO);
		try {
			List<BpmCommentsDAO> daolist=factory.find();
		
			String comment=daolist.get(0).getComments();
			String handleridea=splitAndFilterString(comment);			
			node.setHandleridea(handleridea);
			
		} catch (DAOException e) {
			e.printStackTrace();
		}finally{

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		XZSPAction action = new XZSPAction();		
		List<String> xmlStrs = action.jtqScXml(bsnum, node,"0");
			XzspjkTempBean xtbean=new XzspjkTempBean();
			xtbean.setUuid((new UUID()).toString());
			xtbean.setXmlstr(xmlStrs.get(0));
			xtbean.setXmlstr2(xmlStrs.get(1));
			xtbean.setBsnum(bsnum);
			XzspjkTempAction xtaction = new XzspjkTempAction();
			xtaction.save(xtbean);
	}
	
	 public static String splitAndFilterString(String input) {   
		 if (input == null || input.trim().equals("")) {   
			 return "";   
		 }   
		 // ȥ������htmlԪ��,   
		 String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");   
		 str = str.replaceAll("[(/>)<]", "");   
   
		 return str;   
	}  

	public void activityCompleting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityDisplay(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityFormSaveed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityFormSaveing(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityInited(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityJoined(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityJoining(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowReturned(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowReturning(ActivityEvent arg0)
			throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowing(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityResumed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityResuming(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityRouted(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityRouting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySplited(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySpliting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySuspended(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySuspending(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityTakebacked(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityTakebacking(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

}
