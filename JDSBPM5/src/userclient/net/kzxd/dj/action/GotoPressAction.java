package net.kzxd.dj.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.UserClientException;
import net.kzxd.dj.bean.BpmProcessinstance;
import net.kzxd.dj.bean.FdtOaDj;
import net.kzxd.dj.yjbl.FdtYjbl;

import org.apache.struts2.ServletActionContext;

public class GotoPressAction extends BPMActionBase {

	private String uuid;

	private String process;

	public FormClassBean getCurrForm() throws BPMException, UserClientException {
		List formList = getActivityInst().getActivityDef().getAllDataFormDef();
		if (formList.size() == 1)
			return (FormClassBean) formList.get(0);
		for (int k = 0; formList.size() > k; k++) {
			FormClassBean formClassBean = (FormClassBean) formList.get(k);
			if (formClassBean.getId().equals(getFormID()))
				return formClassBean;
		}

		FormClassBean formClassBean = getActivityInst().getActivityDef()
				.getMainFormDef();
		return formClassBean;
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String pid = getProcessInst().getProcessInstId();
		request.setCharacterEncoding("utf-8");
		
		BpmProcessinstance bb = new BpmProcessinstance();
		bb.setProcessinstid(pid);
		bb.setProcessdefid(getProcessInst().getProcessDefId());
		bb.setPvid(getProcessInst().getProcessDefVersionId());
		bb.setPstate("notStarted");
		bb.setStime(new Date().getMinutes());
		bb.setRunstate("NORMAL");
		//setD(bb);
		// 1、得到数据库链接
		
		
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		try {
			FdtYjbl fod = new FdtYjbl();
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, fod);
			factory.setDAO(fod);
			fod.setConnection(conn);
			fod.setUuid(new UUID().toString());
			fod.setActivityinstid(activityInstId);
			fod.setProcessinstid(pid);
			fod.setYjbt("asdf");
			fod.create();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return "success";
	}
	
	public void setD(BpmProcessinstance bb){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bb);
			factory.setDAO(bb);
			bb.setConnection(conn);
			bb.create();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public FdtOaDj getFdtOaDj(String id){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		FdtOaDj fod = new FdtOaDj();
		try{
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return new FdtOaDj();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}
