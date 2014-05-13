package com.kzxd.cpbl.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.itjds.worklist.list.support.GridUtils;

import org.appfuse.webapp.action.BaseAction;

import com.kzxd.cpbl.module.CbBean;
import com.kzxd.cpbl.module.PbBean;
import com.kzxd.cpbl.service.BaseBeanManager;
import com.kzxd.cpbl.service.CbManager;
import com.kzxd.cpbl.service.PbManager;

public class CpAction extends BaseAction {

	private CbManager cbManager;

	private PbManager pbManager;

	/**
	 * 分页参数
	 */
	private int start;
	private int limit;

	/**
	 * 查询参数
	 */
	private String ttitle;
	private String startdate;
	private String enddate;

	/**
	 * 总数
	 */
	int count = 0;

	/**
	 * 修改ID
	 */
	private String aid;

	
	public String cpWork() {

		return SUCCESS;
	}

	public void zbchDatas() {
		List<CbBean> cbs = cbManager.findByZb(start, limit, ttitle, startdate,
				enddate);
		count = cbManager.getCount(ttitle, startdate, enddate);
		String str = sbCpJson(cbs);
		print(str);
	}

	public void bjchDatas() {
		List<CbBean> cbs = cbManager.findByBjcb(start, limit, ttitle,
				startdate, enddate);
		count = cbManager.getBjCount(ttitle, startdate, enddate);
		String str = sbCpJson(cbs);
		print(str);
	}

	public void updateCb() {
		CbBean cb = cbManager.get(aid);
		cb.setStatus("running");
		cbManager.update(cb);
		print("{success:'true'}");
	}

	public void zbphDatas() {
		List<PbBean> cbs = pbManager.findByZb(start, limit, ttitle, startdate,
				enddate);
		count = pbManager.getCount(ttitle, startdate, enddate);
		String str = sbPbJson(cbs);
		print(str);
	}

	public void bjphDatas() {
		List<PbBean> cbs = pbManager.findByBjcb(start, limit, ttitle,
				startdate, enddate);
		count = pbManager.getBjCount(ttitle, startdate, enddate);
		String str = sbPbJson(cbs);
		print(str);
	}

	public void updatePb() {
		PbBean cb = pbManager.get(aid);
		cb.setStatus("running");
		pbManager.update(cb);
		print("{success:'true'}");
	}

	private void print(String str) {
		super.getResponse().setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = super.getResponse().getWriter();
			out.print(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}

	}

	private String sbCpJson(List<CbBean> cbs) {
		StringBuffer json = new StringBuffer();
		json.append("{totalProperty :" + count + ", root : [");
		for (int i = 0; i < cbs.size(); i++) {
			CbBean cb = cbs.get(i);
			json.append("{'uuid':'" + cb.getUuid() + "',");
			json.append("'jjcd':'" + cb.getImpending() + "',");
			String status = "";
			if ("suspended".equals(cb.getStatus())) {
				status = "";
			} else if ("notStarted".equals(cb.getStatus())) {
				status = "<font color=\"red\">未阅</font>";
			} else if ("running".equals(cb.getStatus())) {
				status = "正在办理";
			}
			json.append("'state':'" + status + "',");
			json.append("'timex':'" + cb.getSx() + "',");
			json.append("'title':'" + cb.getTitle() + "',");
			json.append("'stime':'" + cb.getArriveTime() + "',");
			json.append("'pname':'收文办理',");
			json.append("'sendperson':'" + cb.getLastNodePerson() + "',");
			String op = GridUtils.HTML_OpenWinDisplay("查看", cb
					.getActivityinstid());
			json.append("'option':\"" + op + "\"}");
			if (i != cbs.size() - 1) {
				json.append(",");
			}
		}
		json.append("]}");
		return json.toString();
	}

	private String sbPbJson(List<PbBean> cbs) {
		StringBuffer json = new StringBuffer();
		json.append("{totalProperty :" + count + ", root : [");
		for (int i = 0; i < cbs.size(); i++) {
			PbBean cb = cbs.get(i);
			json.append("{'uuid':'" + cb.getUuid() + "',");
			json.append("'jjcd':'" + cb.getImpending() + "',");
			String status = "";
			if ("suspended".equals(cb.getStatus())) {
				status = "";
			} else if ("notStarted".equals(cb.getStatus())) {
				status = "<font color=\"red\">未阅</font>";
			} else if ("running".equals(cb.getStatus())) {
				status = "正在办理";
			}
			json.append("'state':'" + status + "',");
			json.append("'timex':'" + cb.getSx() + "',");
			json.append("'title':'" + cb.getTitle() + "',");
			json.append("'stime':'" + cb.getArriveTime() + "',");
			json.append("'pname':'收文办理',");
			json.append("'sendperson':'" + cb.getLastNodePerson() + "',");
			String op = GridUtils.HTML_OpenWinDisplay("查看", cb
					.getActivityinstid());
			json.append("'option':\"" + op + "\"}");
			if (i != cbs.size() - 1) {
				json.append(",");
			}
		}
		json.append("]}");
		return json.toString();
	}

	public CbManager getCbManager() {
		return cbManager;
	}

	public void setCbManager(CbManager cbManager) {
		this.cbManager = cbManager;
	}

	public PbManager getPbManager() {
		return pbManager;
	}

	public void setPbManager(PbManager pbManager) {
		this.pbManager = pbManager;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getTtitle() {
		return ttitle;
	}

	public void setTtitle(String ttitle) {
		this.ttitle = ttitle;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}
}
