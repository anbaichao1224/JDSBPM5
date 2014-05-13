package com.kzxd.Baosong.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;

import com.kzxd.Baosong.dao.Xxkbsbdbh;
import com.kzxd.Baosong.dao.Xxkbsdwbd;

import com.kzxd.Baosong.dao.XxktjDao;
import com.kzxd.Baosong.listBean.XxkPFGZListBean;
import com.kzxd.Baosong.listBean.XxkbsdwbdListBean;
import com.kzxd.Baosong.service.XxkbsMsg;
import com.opensymphony.xwork2.Action;

public class XxkbsbdbhAction implements Action {


	public String execute() throws Exception {
		
		return "success";
	}

	public String toBiaoDan(){
		return "success";
	}
	// 1 保存
	// 2 退回
	// 3 查询报送列表 根据部门
	// 4查询单个信息

	private String uuid;
	List bdList;
	private XxkbsdwbdListBean bdBean;
	private List<XxkbsdwbdListBean> bdListBean;
	private int totalCount;
	private List<XxkPFGZListBean> gzListBean;
	
	private int yingyongt;
	private String dwuuid;
	/**
	 * 根据uuid 得到单个信息
	 * 
	 * @return
	 */
	public String getByUuid() {
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;

		Xxkbsbdbh bdDao = new Xxkbsbdbh();
		Xxkbsbdbh bdDaoS = new Xxkbsbdbh();
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bdDao);

			bdDao.setUuid(uuid);

			bdDaoS = (Xxkbsbdbh) factory.findByPrimaryKey();
			bdBean = new XxkbsdwbdListBean();
			bdBean.setDjr(bdDaoS.getDjr());
			bdBean.setUuid(bdDaoS.getUuid());
			bdBean.setZt(bdDaoS.getZt());
			bdBean.setSbsj(dfg.format(bdDaoS.getSbsj()));
			bdBean.setDw(bdDaoS.getDw());
			bdBean.setBt(bdDaoS.getBt());
			gzListBean = new XxkbsMsg().getGzBybduuid(bdDaoS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	private String dw;

	/**
	 * 根据部门得到列表
	 * @return
	 */
	public String getBydw() {

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;

		Xxkbsbdbh bhdao = new Xxkbsbdbh();
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");

		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bhdao);
			if (dwuuid != null && !"".equals(dwuuid)) {
				bhdao.setWhereClause(" dw_uuid = '" + dwuuid+"' ");
				bdList = factory.find();
			}
			else bdList = factory.findAll();
			
			if(bdList !=null){
				addFileToBeanList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "success";
	}
	
	/**
	 * 更新统计表，中的状态
	 * @return
	 */
	public String updateTj(){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxktjDao tjdao = new XxktjDao();
		XxktjDao tjdao1 = new XxktjDao();
		try {
			tjdao.setUuid(uuid);
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, tjdao);
			
			tjdao.setUuid(uuid);
			tjdao1 = (XxktjDao) factory.findByPrimaryKey();
			if(yingyongt == 1){
				yingyongt =0;
			}else{
				yingyongt = 1;
			}
			tjdao1.setYingyong(yingyongt);
			tjdao1.setConnection(conn);
			tjdao1.update();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 退回
	 * @return
	 */
	public String tuihui(){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		Xxkbsbdbh bhdao = new Xxkbsbdbh();
		Xxkbsbdbh bhdao1 = new Xxkbsbdbh();
		Connection conn1;
		conn1 = null;
		DAOFactory factory1 = null;
		try {
			bhdao.setUuid(uuid);
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bhdao);
			
			bhdao.setUuid(uuid);
			bhdao1 = (Xxkbsbdbh) factory.findByPrimaryKey();
			
			bhdao1.setConnection(conn);
			bhdao1.delete();
			new XxkbsMsg().deleteTjBybdUUid(uuid);
			
			Xxkbsdwbd bddao = new Xxkbsdwbd();
			Xxkbsdwbd bddao1 = new Xxkbsdwbd();
			try {
				conn1 = dbbase.getConn();
				factory1 = new DAOFactory(conn,bddao);
				bddao.setUuid(uuid);
				bddao1 = (Xxkbsdwbd) factory1.findByPrimaryKey();
				bddao1.setZt(-1);
				
				bddao1.setConnection(conn1);
				bddao.update();
				conn1.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		
		
		
			try {
				conn.close();
				conn1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public XxkbsdwbdListBean getBdBean() {
		return bdBean;
	}

	public void setBdBean(XxkbsdwbdListBean bdBean) {
		this.bdBean = bdBean;
	}

	public List<XxkbsdwbdListBean> getBdListBean() {
		return bdListBean;
	}

	public void setBdListBean(List<XxkbsdwbdListBean> bdListBean) {
		this.bdListBean = bdListBean;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	private int start;
	private int limit;
	public void addFileToBeanList() throws PersonNotFoundException {

		bdListBean = new ArrayList<XxkbsdwbdListBean>();
		totalCount = bdList.size();
		int count = start + limit;
		if(count > totalCount){
			bdList = bdList.subList(start, totalCount);
		}else{
			bdList = bdList.subList(start, limit);
		}
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < bdList.size(); i++) {
			XxkbsdwbdListBean fb = new XxkbsdwbdListBean();
			Xxkbsbdbh ba = (Xxkbsbdbh) bdList.get(i);
			fb.setDjr(ba.getDjr());
			fb.setUuid(ba.getUuid());
			fb.setZt(ba.getZt());
			fb.setSbsj(dfg.format(ba.getSbsj()));
			fb.setDw(ba.getDw());
			fb.setBt(ba.getBt());
			bdListBean.add(fb);
		}

	}

	public List<XxkPFGZListBean> getGzListBean() {
		return gzListBean;
	}

	public void setGzListBean(List<XxkPFGZListBean> gzListBean) {
		this.gzListBean = gzListBean;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public int getYingyongt() {
		return yingyongt;
	}

	public void setYingyongt(int yingyongt) {
		this.yingyongt = yingyongt;
	}

	public List getBdList() {
		return bdList;
	}

	public void setBdList(List bdList) {
		this.bdList = bdList;
	}

	public String getDwuuid() {
		return dwuuid;
	}

	public void setDwuuid(String dwuuid) {
		this.dwuuid = dwuuid;
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
	
}
