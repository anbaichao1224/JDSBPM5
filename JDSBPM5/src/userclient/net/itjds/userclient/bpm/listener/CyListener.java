package net.itjds.userclient.bpm.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.data.DataMap;
import net.itjds.bpm.data.mapdao.MapDAODataMap;
import net.itjds.bpm.engine.BPMException;
import net.itjds.service.bpm.GetRouteToBean;
import net.itjds.service.bpm.RouteToBean;
import net.itjds.bpm.engine.OARightConstants;

import com.opensymphony.xwork2.ActionContext;
import com.sun.star.sdbc.SQLException;
import net.itjds.common.org.base.Person;

public class CyListener implements ActivityListener{

	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
		
		
		{
			
			
			List<String> activityDefList=new ArrayList<String> ();
			GetRouteToBean routeBeanList=(GetRouteToBean) ActionContext.getContext().getValueStack().findValue("$GetRouteToBean");
			Map<String, RouteToBean> routeBeanMap=routeBeanList.getBeanMap();
			Iterator<String> it=routeBeanMap.keySet().iterator();
			
			
			//局领导130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act12
			//130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act13" Name="主办"
			//130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act14" Name="协办"
			//130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act15" Name="传阅"
			String toActivityDefId2 = "130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act13";
			RouteToBean routeBean2=routeBeanMap.get(toActivityDefId2);
			
			if(routeBean2==null){
			
			}else{
				
				List zhuban=routeBean2.getPerformPersonIdList();
				String zb = zhuban.get(0).toString();
			
				String orgid;
				String cnname="";
				
				ActionContext context=ActionContext.getContext();
				context.getValueStack().setValue("$Fdtnmswbl.fdtOaNmswblDAO.ffzh", cnname);
			
				
				
			}
			String toActivityDefId3 = "130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act14";
			RouteToBean routeBean3=routeBeanMap.get(toActivityDefId3);
			if(routeBean3==null){
			
			}else{
				
				List xieban=routeBean3.getPerformPersonIdList();
				String xb = xieban.get(0).toString();
				
				String orgid;
				String cnname="";
				
				ActionContext context=ActionContext.getContext();
				context.getValueStack().setValue("$Fdtnmswbl.fdtOaNmswblDAO.jbr", cnname);
				
			}
			String toActivityDefId4 = "130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act15";
			RouteToBean routeBean4=routeBeanMap.get(toActivityDefId4);
			if(routeBean4==null){
			
			}else{
				
				List chuanyue=routeBean4.getPerformPersonIdList();
				String cy = chuanyue.get(0).toString();
				
				String orgid;
				String cnname="";
				
				ActionContext context=ActionContext.getContext();
				context.getValueStack().setValue("$Fdtnmswbl.fdtOaNmswblDAO.yf", cnname);
				
			}
			
		}
		
	}

	public void activityCompleting(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
		
		
		{
			
			
			List<String> activityDefList=new ArrayList<String> ();
			GetRouteToBean routeBeanList=(GetRouteToBean) ActionContext.getContext().getValueStack().findValue("$GetRouteToBean");
			Map<String, RouteToBean> routeBeanMap=routeBeanList.getBeanMap();
			Iterator<String> it=routeBeanMap.keySet().iterator();
			
			
			//局领导130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act12
			//130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act13" Name="主办"
			//130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act14" Name="协办"
			//130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act15" Name="传阅"
			String toActivityDefId2 = "130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act13";
			RouteToBean routeBean2=routeBeanMap.get(toActivityDefId2);
			if(routeBean2==null){
			
			}else{
				
				List zhuban=routeBean2.getPerformPersonIdList();
				String zb = zhuban.get(0).toString();
				
				String orgid;
				String cnname="";
				
				ActionContext context=ActionContext.getContext();
				context.getValueStack().setValue("$Fdtnmswbl.fdtOaNmswblDAO.ffzh", cnname);
			
				
				
			}
			String toActivityDefId3 = "130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act14";
			RouteToBean routeBean3=routeBeanMap.get(toActivityDefId3);
			if(routeBean3==null){
			
			}else{
				
				List xieban=routeBean3.getPerformPersonIdList();
				String xb = xieban.get(0).toString();
			
				String orgid;
				String cnname="";
				
				ActionContext context=ActionContext.getContext();
				context.getValueStack().setValue("$Fdtnmswbl.fdtOaNmswblDAO.jbr", cnname);
				
			}
			String toActivityDefId4 = "130230C0-7E11-11E1-B0C0-EDB5E6A1E526_Act15";
			RouteToBean routeBean4=routeBeanMap.get(toActivityDefId4);
			if(routeBean4==null){
			
			}else{
				
				List chuanyue=routeBean4.getPerformPersonIdList();
				String cy = chuanyue.get(0).toString();
			
				String orgid;
				String cnname="";
				
				ActionContext context=ActionContext.getContext();
				context.getValueStack().setValue("$Fdtnmswbl.fdtOaNmswblDAO.yf", cnname);
				
			}
			
		}
		
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
