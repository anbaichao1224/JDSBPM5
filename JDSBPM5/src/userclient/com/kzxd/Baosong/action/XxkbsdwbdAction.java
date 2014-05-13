package com.kzxd.Baosong.action;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import com.kzxd.Baosong.dao.Xxkbsbdbh;
import com.kzxd.Baosong.dao.Xxkbsdwbd;
import com.kzxd.Baosong.listBean.XxkbsdwbdListBean;
import com.kzxd.Baosong.service.GzAndBd;
import com.kzxd.Baosong.service.XxkbsMsg;
import com.opensymphony.xwork2.Action;

public class XxkbsdwbdAction implements Action{


	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String uuid;
	
	private XxkbsdwbdListBean bdBean;
	
	private List<XxkbsdwbdListBean> bdListBean;
	
	private int zt;
	
	private int totalCount;
	
	List bdList;
	
	private int start;
	private int limit;
	//1 保存  --实现
	//2 报送 -- 部分实现
	//3 查询草稿列表 --实现
	//4 查询报送列表 --实现
	//5查询单个信息 --实现
	
	/**
	 * 查询单个信息，根据uuid
	 * @return
	 */
	public String getByUuid(){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		
		Xxkbsdwbd bdDao = new Xxkbsdwbd();
		Xxkbsdwbd bdDaoS = new Xxkbsdwbd();
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bdDao);
			
			bdDao.setUuid(uuid);
			
			bdDaoS = (Xxkbsdwbd)factory.findByPrimaryKey();
			bdBean = new XxkbsdwbdListBean();
			bdBean.setDjr(bdDaoS.getDjr());
			bdBean.setUuid(bdDaoS.getUuid());
			bdBean.setZt(bdDaoS.getZt());
			bdBean.setSbsj(dfg.format(bdDaoS.getSbsj()));
			bdBean.setDw(bdDaoS.getDw());
			bdBean.setBt(bdDaoS.getBt());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(bdBean.getZt() == 1){
			return "baosong";
		}else 
		
		return "success";
	}
	
	public String toBiaodan(){
		return "success";
	}
	
	/**
	 * 根据状态得到，草稿列表，已报送列表。
	 * @return
	 */
	public String getByZT(){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		
		Xxkbsdwbd bdDao = new Xxkbsdwbd();
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bdDao);
			
			bdDao.setWhereClause(" zt = " + zt);
			
			
			bdList = factory.find();
			if(bdList != null){
				addFileToBeanList() ;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return "success";
	}
	
	public String toCaogao(){
		
		if(uuid == null || "".equals(uuid))
			uuid = (new UUID()).toString();
		return "success";
	}
	/**
	 * 保存信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String saveInfo() throws UnsupportedEncodingException{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		@SuppressWarnings("unused")
		DAOFactory factory = null;
		
		Xxkbsdwbd bdDao = new Xxkbsdwbd();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bdDao);
			
			bdDao.setDjr(djr);
			
			bdDao.setDw(dw);
			bdDao.setZt(zt);
			bdDao.setBt(bt);
			bdDao.setSbsj(sbsj);
			bdDao.setDw_uuid(dwuuid);
			bdDao.setConnection(conn);
			
			if("send".equals(sendorsave)){
				bdDao.setZt(1);
			}
			
			if(neworold == null || "".equals(neworold)){
				bdDao.setUuid(uuid);
				bdDao.update();
			}else{
				uuid = (new UUID()).toString();
				bdDao.setUuid(uuid);
				bdDao.create();
			}
			if("send".equals(sendorsave)){
				new GzAndBd().createGzAndBd(uuid);
				new XxkbsMsg().save(bdDao);
			}
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "success";
	}
	
	/**
	 * 报送信息
	 * @return
	 */
	public String baosongInfo(){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		
		Xxkbsdwbd bdDao = new Xxkbsdwbd();
		Xxkbsdwbd bdDaoS = new Xxkbsdwbd();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bdDao);
			
			bdDao.setUuid(uuid);
			
			bdDaoS = (Xxkbsdwbd)factory.findByPrimaryKey();
			bdDaoS.setZt(1);
			bdDaoS.setConnection(conn);
			bdDaoS.update();
			new GzAndBd().createGzAndBd(uuid);
			new XxkbsMsg().save(bdDaoS);
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "success";
	}

	public String liebiao(){
		
		return "success";
	}
	
	
	
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
			Xxkbsdwbd ba = (Xxkbsdwbd) bdList.get(i);
			fb.setDjr(ba.getDjr());
			fb.setUuid(ba.getUuid());
			fb.setZt(ba.getZt());
			fb.setSbsj(dfg.format(ba.getSbsj()));
			fb.setDw(ba.getDw());
			fb.setBt(ba.getBt());
			bdListBean.add(fb);
		}

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

	public int getZt() {
		return zt;
	}

	public void setZt(int zt) {
		this.zt = zt;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	/**
	 * 以下为保存时得到数据
	 */

	private String dw;
	private Date sbsj;
	private String djr;
	private String bt;
	private String sendorsave;
	
	private String neworold;
	private String dwuuid;
	
	
	public String getNeworold() {
		return neworold;
	}

	public void setNeworold(String neworold) {
		this.neworold = neworold;
	}

	public String getSendorsave() {
		return sendorsave;
	}

	public void setSendorsave(String sendorsave) {
		this.sendorsave = sendorsave;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public Date getSbsj() {
		return sbsj;
	}

	public void setSbsj(Date sbsj) {
		this.sbsj = sbsj;
	}

	public String getDjr() {
		return djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
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

	public String getDwuuid() {
		return dwuuid;
	}

	public void setDwuuid(String dwuuid) {
		this.dwuuid = dwuuid;
	}
	
	
}
