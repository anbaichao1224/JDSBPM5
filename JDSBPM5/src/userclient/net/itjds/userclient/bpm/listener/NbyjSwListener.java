package net.itjds.userclient.bpm.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.data.DataMap;
import net.itjds.bpm.data.mapdao.MapDAODataMap;
import net.itjds.bpm.engine.BPMException;
import net.itjds.service.bpm.GetRouteToBean;
import net.itjds.service.bpm.RouteToBean;
import net.itjds.bpm.engine.OARightConstants;

import com.kzxd.nbyj.entity.NbyjBean;
import com.kzxd.nbyj.service.NbyjService;
import com.opensymphony.xwork2.ActionContext;
import com.sun.star.sdbc.SQLException;

import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.util.UUID;

public class NbyjSwListener implements ActivityListener{

	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
		String activityInstId = event.getActivityInsts()[0].getActivityInstId();
		String processInstId = event.getActivityInsts()[0].getProcessInstId();
		String hj = event.getActivityInsts()[0].getActivityDef().getName();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		Person person;
		String personname=null;
		String orgname=null;
		try {
			person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			 personname=person.getName();
			 orgname = person.getOrgList().get(0).getName();
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				ActionContext context=ActionContext.getContext();
				Object result1 = context.getValueStack().findValue("$Fdtnmswbl.FdtOaNmswblDAO.nbyj");	
				String nbyj=(String)result1;
				NbyjService service = new NbyjService();
				NbyjBean bean = new NbyjBean();
				bean.setPersonname(personname);
				bean.setOrg(orgname);
				bean.setNbyj(nbyj);
				bean.setHj(hj);
				bean.setActivityinst_id(activityInstId);
				bean.setProcessinst_id(processInstId);
				bean.setCreatedate(new Date());
				bean.setUuid((new UUID()).toString());
				service.save(bean);
		
		
	}

	public void activityCompleting(ActivityEvent event) throws BPMException {
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

	public void activityRouted(ActivityEvent event) throws BPMException {
		
	}

	public void activityRouting(ActivityEvent event) throws BPMException {

	}

	public void activitySplited(ActivityEvent event) throws BPMException {

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
