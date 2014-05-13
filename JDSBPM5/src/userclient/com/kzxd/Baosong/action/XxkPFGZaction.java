package com.kzxd.Baosong.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.PersonNotFoundException;

import net.itjds.j2ee.dao.DAOFactory;

import com.kzxd.Baosong.dao.XxkPingfengGuizeDao;
import com.kzxd.Baosong.listBean.XxkPFGZListBean;

import com.opensymphony.xwork2.Action;

public class XxkPFGZaction implements Action {

	List gzList;
	private int totalCount;

	private List<XxkPFGZListBean> gzListBean;

	private int start;
	private String json = "";
	private int limit;

	public String execute() throws Exception {
		getAll();
		String json = "";
		return "success";
	}

	public List getAll() {

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao gzdao = new XxkPingfengGuizeDao();

		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, gzdao);
			gzList = factory.findAll();

			if (gzList != null && gzList.size() > 0) {
				totalCount = gzList.size();
				getAllNewGz();
				addFileToBeanList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private List<XxkPingfengGuizeDao> xDaoT = new ArrayList<XxkPingfengGuizeDao>();

	/**
	 * 得到所有最新的规则
	 */
	public void getAllNewGz() {
		for (int j = 0; j < gzList.size(); j++) {
			if (xDaoT.size() == 0 && gzList.size() > 0) {
				xDaoT.add((XxkPingfengGuizeDao) gzList.get(0));
			}
			int i = 0;
			XxkPingfengGuizeDao daoT = (XxkPingfengGuizeDao) gzList.get(j);
			for ( i = 0; i < xDaoT.size(); i++) {

				
				XxkPingfengGuizeDao mubiao = xDaoT.get(i);
				if (daoT.getBiaohao().trim().equals(mubiao.getBiaohao().trim())) {
					if (daoT.getBanbenhao() > mubiao.getBanbenhao()) {
						xDaoT.set(i, daoT);
						
					}
					break;
				} 
			}
			if( i == xDaoT.size() ){
				xDaoT.add(daoT);
			}
		}

	}

	public void addFileToBeanList() throws PersonNotFoundException {
		gzListBean = new ArrayList<XxkPFGZListBean>();
		totalCount = xDaoT.size();
		int count = start + limit;
		
		if(count > totalCount){
			xDaoT = xDaoT.subList(start,totalCount);
		}else{
			xDaoT = xDaoT.subList(start, limit);
		}
		
		
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < xDaoT.size(); i++) {
			XxkPFGZListBean fb = new XxkPFGZListBean();
			XxkPingfengGuizeDao ba = (XxkPingfengGuizeDao) xDaoT.get(i);
			fb.setUuid(ba.getUuid());
			fb.setBanbenhao(ba.getBanbenhao());
			fb.setBiaohao(ba.getBiaohao());
			fb.setFenshu(ba.getFenshu());
			fb.setKaishishijian(dfg.format(ba.getKaishishijian()));
			fb.setJieshushijian(dfg.format(ba.getJieshushijian()));
			fb.setName(ba.getName());
			gzListBean.add(fb);
		}

	}


	public List<XxkPFGZListBean> getGzListBean() {
		return gzListBean;
	}

	public void setGzListBean(List<XxkPFGZListBean> gzListBean) {
		this.gzListBean = gzListBean;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
