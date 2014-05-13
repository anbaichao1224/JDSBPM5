package net.itjds.userclient.bpm.listener;

import java.util.Calendar;
import java.util.Date;

import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;

import com.kzxd.newkaoqin.dao.QianJiaTiaoDao;
import com.kzxd.newkaoqin.dao.QingJiaMingXiDao;
import com.kzxd.newkaoqin.dao.impl.QianJiaTiaoDaoImpl;
import com.kzxd.newkaoqin.dao.impl.QingJiaMingXiDaoImpl;
import com.kzxd.newkaoqin.entity.QingJiaMingXiBean;
import com.kzxd.newkaoqin.entity.QingJiaTiaobean;
import com.opensymphony.xwork2.ActionContext;

public class QingJiaListener implements ActivityListener{

	private QianJiaTiaoDao qjtDao;
	private QingJiaTiaobean qjtBean;
	private QingJiaMingXiDao qjmxDao;
	private QingJiaMingXiBean qjmxBean;
	
	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		
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

	public void activityRouted(ActivityEvent event) throws BPMException {
		
		ActionContext context=ActionContext.getContext();
		qjtDao= new QianJiaTiaoDaoImpl();
		try{
			Object result1 = context.getValueStack().findValue("$Fdtqjt.fdtOaQjtDAO.xtdbkfzryj");
			Object result2 = context.getValueStack().findValue("$Fdtqjt.fdtOaQjtDAO.qjsjks");
			Object result3 = context.getValueStack().findValue("$Fdtqjt.fdtOaQjtDAO.qjsjjs");
			Object result4 = context.getValueStack().findValue("$Fdtqjt.fdtOaQjtDAO.ckmc");
			Object result5 = context.getValueStack().findValue("$Fdtqjt.fdtOaQjtDAO.qjlx");
			Object result6 = context.getValueStack().findValue("$Fdtqjt.fdtOaQjtDAO.xm");
			Object result7 = context.getValueStack().findValue("$Fdtqjt.fdtOaQjtDAO.zxldyj");
			Object result8 = context.getValueStack().findValue("$startPerson.iD");		
			
			String xtdbkfzryj=(String)result1;
			Date qjsjks=(Date)result2;
			Date qjsjjs=(Date)result3;
			String ckmc=(String)result4;
			String qjlx=(String)result5;
			String xm=(String)result6;
			String zxldyj=(String)result7;
			String personid=(String)result8;
			
			if(xtdbkfzryj.equals("同意")){
				Date sdate=qjsjks;
				Date edate=qjsjjs;
				Calendar startCalendar = Calendar.getInstance();
				startCalendar.setTime(edate);
				startCalendar.add(Calendar.DATE, 1);
				Date endtime=startCalendar.getTime();
				while (sdate.before(endtime)) {
					qjmxBean=new QingJiaMingXiBean();
					qjmxBean.setCk(ckmc);
					qjmxBean.setXm(xm);
					qjmxBean.setQingjiariqi(sdate);
					qjmxBean.setQjlx(qjlx);
					qjmxBean.setPersonid(personid);
					qjmxDao = new QingJiaMingXiDaoImpl();
					qjmxDao.add(qjmxBean);
					startCalendar.setTime(sdate);
					startCalendar.add(Calendar.DATE, 1);
					sdate = startCalendar.getTime();
				}
			}else{
				if(zxldyj.equals("同意")){
					Date sdate=qjsjks;
					Date edate=qjsjjs;
					Calendar startCalendar = Calendar.getInstance();
					startCalendar.setTime(edate);
					startCalendar.add(Calendar.DATE, 1);
					Date endtime=startCalendar.getTime();
					while (sdate.before(endtime)) {
						qjmxBean=new QingJiaMingXiBean();
						qjmxBean.setCk(ckmc);
						qjmxBean.setXm(xm);
						qjmxBean.setQingjiariqi(sdate);
						qjmxBean.setQjlx(qjlx);
						qjmxBean.setPersonid(personid);
						qjmxDao = new QingJiaMingXiDaoImpl();
						qjmxDao.add(qjmxBean);
						startCalendar.setTime(sdate);
						startCalendar.add(Calendar.DATE, 1);
						sdate = startCalendar.getTime();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}


		
/**		
 * 
		String ActivityDefId=event.getActivityInsts()[0].getActivityDefId();
		
		if(ActivityDefId.equals("C866EAA0-AFA8-11E1-AAA0-E69066AF893F_Act4")){
			String ActivityInstId=event.getActivityInsts()[0].getActivityInstId();
			qjtDao= new QianJiaTiaoDaoImpl();
			qjtBean=qjtDao.getById(ActivityInstId);
				try{
					if(qjtBean.getXtdbkfzryj().equals("同意")){
						Date sdate=qjtBean.getQjsjks();
						Date edate=qjtBean.getQjsjjs();
						Calendar startCalendar = Calendar.getInstance();
						startCalendar.setTime(edate);
						startCalendar.add(Calendar.DATE, 1);
						Date endtime=startCalendar.getTime();
						while (sdate.before(endtime)) {
						qjmxBean=new QingJiaMingXiBean();
						qjmxBean.setCk(qjtBean.getCkmc());
						qjmxBean.setXm(qjtBean.getXm());
						qjmxBean.setQingjiariqi(sdate);
						qjmxBean.setQjlx(qjtBean.getQjlx());
						qjmxDao = new QingJiaMingXiDaoImpl();
						qjmxDao.add(qjmxBean);
						startCalendar.setTime(sdate);
						startCalendar.add(Calendar.DATE, 1);
						sdate = startCalendar.getTime();
						}
					}else{
						if(qjtBean.getZxldyj().equals("同意")){
							Date sdate=qjtBean.getQjsjks();
							Date edate=qjtBean.getQjsjjs();
							Calendar startCalendar = Calendar.getInstance();
							startCalendar.setTime(edate);
							startCalendar.add(Calendar.DATE, 1);
							Date endtime=startCalendar.getTime();
							while (sdate.before(endtime)) {
							qjmxBean=new QingJiaMingXiBean();
							qjmxBean.setCk(qjtBean.getCkmc());
							qjmxBean.setXm(qjtBean.getXm());
							qjmxBean.setQingjiariqi(sdate);
							qjmxBean.setQjlx(qjtBean.getQjlx());
							qjmxDao = new QingJiaMingXiDaoImpl();
							qjmxDao.add(qjmxBean);
							startCalendar.setTime(sdate);
							startCalendar.add(Calendar.DATE, 1);
							sdate = startCalendar.getTime();
							}
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}*/
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

	public QingJiaTiaobean getQjtBean() {
		return qjtBean;
	}

	public void setQjtBean(QingJiaTiaobean qjtBean) {
		this.qjtBean = qjtBean;
	}

	public QianJiaTiaoDao getQjtDao() {
		return qjtDao;
	}

	public void setQjtDao(QianJiaTiaoDao qjtDao) {
		this.qjtDao = qjtDao;
	}

	public QingJiaMingXiDao getQjmxDao() {
		return qjmxDao;
	}

	public void setQjmxDao(QingJiaMingXiDao qjmxDao) {
		this.qjmxDao = qjmxDao;
	}

	public QingJiaMingXiBean getQjmxBean() {
		return qjmxBean;
	}

	public void setQjmxBean(QingJiaMingXiBean qjmxBean) {
		this.qjmxBean = qjmxBean;
	}

}
