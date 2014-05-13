package net.itjds.userclient.bpm.listener;

import java.text.SimpleDateFormat;
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

public class NbyjsjListener implements ActivityListener{

	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		ActionContext context=ActionContext.getContext();
		Object result1 = context.getValueStack().findValue("$Fdtgwg.fdtOaGwgDAO.nbyj");	
		String nbyj=(String)result1;
		if(nbyj.equals("")){
			
		}else{
			Date date = new Date();   
			String reg ="\n";
			String t = "\t";
			String str = nbyj+reg+t+t+t+t+t+t+t+"("+new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(date)+")";
			context.getValueStack().setValue("$Fdtgwg.fdtOaGwgDAO.nbyj", str);
		}
	
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
