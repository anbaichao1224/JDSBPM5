package com.kzxd.Baosong.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;

import org.apache.struts2.ServletActionContext;

import com.kzxd.Baosong.dao.XxkPingfengGuizeDao;
import com.kzxd.Baosong.listBean.XxkPFGZListBean;
import com.kzxd.Baosong.service.GzAndBd;
import com.kzxd.Baosong.service.XxkbsMsg;
import com.opensymphony.xwork2.Action;

/**
 * 信息科评分统计action
 * @author admin
 *
 */
public class XxkPfTjAction implements Action {

	List gzList;
	private int totalCount;

	private List<XxkPFGZListBean> gzListBean;
	
	private String start;
	private String json = "";
	private String limit;

	
	public String execute() throws Exception {
		getAll();
		String json = "";
		return "success";
	}
	
	public void getAllTj() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		
		GzAndBd gzMsg = new GzAndBd();
		
		
		json = gzMsg.getJsonStr(gzMsg.tj(gzMsg.getBtAndTj()));
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}

	public void getAll() {

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

	}

 	private	List<XxkPingfengGuizeDao> xDaoT = new ArrayList<XxkPingfengGuizeDao>();
	
 	/**
 	 * 得到所有最新的规则
 	 */
	public void getAllNewGz() {
		
		for (int j = 1; j < gzList.size(); j++) {
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
			fb.setJiancheng(ba.getJiancheng());
			gzListBean.add(fb);
		}

	}
	
	public String getGzJsonStr(){
		String jsonStr = "";
		
		return jsonStr;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	
}