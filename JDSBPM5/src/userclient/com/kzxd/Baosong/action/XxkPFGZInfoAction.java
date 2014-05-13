package com.kzxd.Baosong.action;

import java.sql.Connection;

import java.sql.SQLException;
import java.text.DateFormat;
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
import net.itjds.j2ee.util.UUID;

import com.kzxd.Baosong.dao.XxkPingfengGuizeDao;
import com.kzxd.Baosong.listBean.XxkPFGZListBean;
import com.opensymphony.xwork2.Action;

public class XxkPFGZInfoAction implements Action {

	private XxkPFGZListBean xxgzInfo;

	private XxkPingfengGuizeDao xxgzdao;
	private int totalCount;
	private String kaishishijian;
	private String jieshushijian;
	private List<XxkPFGZListBean> gzListBean;
	private String uuid;
	private String newUuid;
	private String name;
	private int fenshu;
	private int banbenhao;
	private String biaohao;

	
	public String execute() throws Exception {

		getByuuid();

		return "success";
	}

	List gzList;
	
	/**
	 * 判断是否存在标号（简称），若存在返回true，否则返回false
	 * @return
	 */
	public boolean isBiaohao(String sbiaohao){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao gzdao = new XxkPingfengGuizeDao();
		//gzdao.setUuid(uuid);
		xxgzInfo = new XxkPFGZListBean();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, gzdao);
			
			gzdao.setWhereClause(" biaohao = '"+sbiaohao+"' ");
			
			gzList = factory.find();
			if(gzList != null && gzList.size()>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}
		
		return false;
	}
	
	public String getByBiaohao(){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao gzdao = new XxkPingfengGuizeDao();
		gzdao.setUuid(uuid);
		xxgzInfo = new XxkPFGZListBean();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, gzdao);
			
			gzdao.setWhereClause(" biaohao = '"+biaohao+"' and uuid <> '"+uuid+"'");
			
			gzList = factory.find();
			if(gzList != null ){
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
		return "success";
	}
	
	public void getByuuid() {

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao gzdao = new XxkPingfengGuizeDao();
		gzdao.setUuid(uuid);
		xxgzInfo = new XxkPFGZListBean();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, gzdao);
			gzdao.setWhereClause("biaohao = '"+biaohao+"'");
			gzList = factory.find();
			if(gzList!=null && gzList.size()>0)
				addFileToBeanList();
			xxgzdao = (XxkPingfengGuizeDao) factory.findByPrimaryKey();

			SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
			
			xxgzInfo.setUuid(uuid);
			xxgzInfo.setName(xxgzdao.getName());
			xxgzInfo.setBanbenhao(xxgzdao.getBanbenhao());
			xxgzInfo.setBiaohao(xxgzdao.getBiaohao());
			xxgzInfo.setFenshu(xxgzdao.getFenshu());
			kaishishijian = dfg.format(xxgzdao.getKaishishijian());
			jieshushijian = dfg.format(xxgzdao.getJieshushijian());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void saveAndUpdate() {
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao saveDao = new XxkPingfengGuizeDao();
		newUuid = (new UUID()).toString();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, saveDao);

			saveDao.setName(name);
			saveDao.setKaishishijian(String2Date(kaishishijian));
			saveDao.setJieshushijian(String2Date(jieshushijian));
			if (uuid != null && !"".equals(uuid.trim())) {
				banbenhao= banbenhao+1;
				updateDao(saveDao.getKaishishijian());
			} else {
				if(isBiaohao(biaohao)){
					
					response.getWriter().write("{success:false,errors:{info:'简称已存在请重新填写!'}}");
					return;
				}
				banbenhao = 1;
			}

			saveDao.setUuid(newUuid);
			saveDao.setBiaohao(biaohao);
			saveDao.setBanbenhao(banbenhao);
			saveDao.setFenshu(fenshu);
			saveDao.setConnection(conn);
			saveDao.create();
			
			conn.commit();
			//request.setAttribute("status", "1");
			response.getWriter().write("{success:true,errors:{info:'添加成功！'}}");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addFileToBeanList() throws PersonNotFoundException {
		// String realPath =
		// ServletActionContext.getServletContext().getRealPath("");
		gzListBean = new ArrayList<XxkPFGZListBean>();
		totalCount = gzList.size();
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < gzList.size(); i++) {
			XxkPFGZListBean fb = new XxkPFGZListBean();
			XxkPingfengGuizeDao ba = (XxkPingfengGuizeDao) gzList.get(i);
			fb.setUuid(ba.getUuid());
			fb.setBanbenhao(ba.getBanbenhao());
			fb.setBiaohao(ba.getBiaohao());
			fb.setFenshu(ba.getFenshu());
			fb.setKaishishijian( dfg.format(ba.getKaishishijian()));
			fb.setJieshushijian( dfg.format(ba.getJieshushijian()));
			fb.setName(ba.getName());
			gzListBean.add(fb);
		}

	}
	
	public boolean updateDao(Date shijian){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao upDao = new XxkPingfengGuizeDao();
		
		upDao.setUuid(uuid);

		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, upDao);
			
			upDao = (XxkPingfengGuizeDao) factory.findByPrimaryKey();
			
//			upDao.setName("测试更新");
			
			upDao.setJieshushijian(shijian);
			
			upDao.setConnection(conn);
			upDao.update();
			
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public XxkPFGZListBean getXxgzInfo() {
		return xxgzInfo;
	}

	public void setXxgzInfo(XxkPFGZListBean xxgzInfo) {
		this.xxgzInfo = xxgzInfo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public static Date String2Date(String dateStr) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// String date = "2010-10-1 12:22:30";
		Date d = null;
		try {
			d = format.parse(dateStr);
		} catch (Exception e) {

		}

		return d;
	}

	public String getKaishishijian() {
		return kaishishijian;
	}

	public void setKaishishijian(String kaishishijian) {
		this.kaishishijian = kaishishijian;
	}

	public String getJieshushijian() {
		return jieshushijian;
	}

	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFenshu() {
		return fenshu;
	}

	public void setFenshu(int fenshu) {
		this.fenshu = fenshu;
	}

	public int getBanbenhao() {
		return banbenhao;
	}

	public void setBanbenhao(int banbenhao) {
		this.banbenhao = banbenhao;
	}

	public String getBiaohao() {
		return biaohao;
	}

	public void setBiaohao(String biaohao) {
		this.biaohao = biaohao;
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
	
	public String clUuid(String uuid){
		uuid = uuid.replaceAll("0", "a");
		uuid = uuid.replaceAll("1", "b");
		uuid = uuid.replaceAll("2", "c");
		uuid = uuid.replaceAll("3", "d");
		uuid = uuid.replaceAll("4", "e");
		uuid = uuid.replaceAll("5", "f");
		uuid = uuid.replaceAll("6", "g");
		uuid = uuid.replaceAll("7", "h");
		uuid = uuid.replaceAll("8", "i");
		uuid = uuid.replaceAll("9", "j");
		uuid = uuid.replaceAll("-", "k");
		return uuid;
	}
}
