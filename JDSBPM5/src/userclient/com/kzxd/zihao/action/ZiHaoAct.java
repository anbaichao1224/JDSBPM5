package com.kzxd.zihao.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kzxd.ttinfo.action.WenZhongAction;

import net.itjds.common.database.DBBeanBase;

import org.appfuse.webapp.action.BaseAction;


import com.kzxd.utils.DateUtil;
import com.kzxd.zihao.entity.WenZhongZiHao;
import com.kzxd.zihao.entity.ZiHao;
import com.kzxd.zihao.service.WenZhongZiHaoService;
import com.kzxd.zihao.service.ZiHaoService;
public class ZiHaoAct extends BaseAction{

	/**
	 * �ֺ�action
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  ʵ��id
	 */
	private String actid;
	
	private Integer maxzihao;
	private int readonly;
	private ZiHao zihao;
	
	private String uuid;
	
	private Integer zi;
	
	private String wenzhong;
	
	private WenZhongZiHaoService wzzhSer;
	private ZiHaoService zhSer;
	private String year;
	
	java.util.List<String> yearList;
	private List wzlist;
	private String lastyear;
	
	public String getLastyear() {
		return lastyear;
	}
	public void setLastyear(String lastyear) {
		this.lastyear = lastyear;
	}
	/**
	 * �򿪱�ʱ��Ϊ�ֺŸ��ϳ�ʼֵ
	 * 
	 * @return
	 */
	public String toZiHao() {
//		if (readonly == 0) {
//			if (year == null || year == "") {
//				year = DateUtil.getYear();
//				//zihao = wzzhSer.getZiHao(actid);
//			}
//			// yearList.add(year);
//			lastyear = String.valueOf((Integer.parseInt(year) - 1));
//		} else {
//			year = "";
//			lastyear = "";
//			//zihao = null;
//		}
//
//		 zihao = wzzhSer.getZiHao(actid);
//		System.out.println("tozihaouuid"+zihao.getUuid());
//		WenZhongAction wzaction = new WenZhongAction();
//		wzaction.list();
//		wzlist = wzaction.getWzlist();
//		System.out.println(wzlist.toString());
//		return "success";
		if(year==null || year==""){
			year = DateUtil.getYear();
		}
		//yearList.add(year);
		lastyear = String.valueOf((Integer.parseInt(year)-1));
		zihao = wzzhSer.getZiHao(actid);
		System.out.println("tozihaouuid"+zihao.getUuid());
		WenZhongAction wzaction = new WenZhongAction();
		wzaction.list();
		wzlist = wzaction.getWzlist();
		return "success";
	}
	/**
	 * �ı��������ߵķ���
	 */
	public void getZiHaoByWenZhong(){
			
		if(year==null || year==""){
			year = DateUtil.getYear();
		}
		
		zihao = wzzhSer.getNewZiHao(actid, wenzhong, uuid,year);
		PrintWriter out = null;
		try{
			out = super.getResponse().getWriter();
			out.println("{success:true,data:{zihao:'"+zihao.getZihao()+"'}}");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (out != null) {
				out.close();
			}
		}
	}
	  public String getActdefIdByActid(String actid){
	    Statement st = null;
	    DBBeanBase dbbase = new DBBeanBase("bpm", true);
	    Connection conn = null;
	    ResultSet rs = null;
	    String sql = "select activitydef_id from bpm_activityinstance where activityinst_id='" + actid + "'";
	    String actdefid = null;
	    try {
	      conn = dbbase.getConn();
	      st = conn.createStatement();
	      rs = st.executeQuery(sql);
	      while (rs.next())
	        actdefid = rs.getString("activitydef_id");
	    }
	    catch (Exception localException)
	    {
	      try
	      {
	        rs.close();
	        st.close();
	        conn.close();
	      }
	      catch (Exception localException1)
	      {
	      }
	    }
	    finally
	    {
	      try
	      {
	        rs.close();
	        st.close();
	        conn.close();
	      }
	      catch (Exception localException2)
	      {
	      }
	    }

	    return actdefid;
	  }

	  public void selzihao() throws IOException {
	    ZiHao zihaoU = new ZiHao();
	    WenZhongZiHao wzzhU = new WenZhongZiHao();
	    PrintWriter out = null;
	    zihaoU = (ZiHao)this.zhSer.get(this.uuid);
	    Integer yuanMax = zihaoU.getZihao();
	    zihaoU.setZihao(this.zi);
	    zihaoU.setYear(this.year);
	    HttpServletResponse response = super.getResponse();

	    response.setContentType("text/html;charset=UTF-8");

	    super.getResponse().setContentType("text/html;charset=UTF-8");
	    out = response.getWriter();
	    if ((this.year == null) || (this.year == ""))
	      this.year = DateUtil.getYear();
	  }
	/**
	 * �ֺű���
	 * @throws IOException 
	 */
	  public void saveZiHao()
	    throws IOException
	  {
	    String zih = getActdefIdByActid(this.actid);

	    ZiHao zihaoU = new ZiHao();
	    String zihaoO = null;
	    WenZhongZiHao wzzhU = new WenZhongZiHao();
	    PrintWriter out = null;
	    zihaoU = (ZiHao)this.zhSer.get(this.uuid);
	    Integer yuanMax = zihaoU.getZihao();
	    zihaoU.setZihao(this.zi);
	    zihaoU.setYear(this.year);
	    HttpServletResponse response = super.getResponse();

	    response.setContentType("text/html;charset=UTF-8");

	    super.getResponse().setContentType("text/html;charset=UTF-8");
	    out = response.getWriter();
	    if ((this.year == null) || (this.year == "")) {
	      this.year = DateUtil.getYear();
	    }
	    if (zih.equals("4AED1BE0-F319-11E0-AADD-D1CF6FBB2348_Act12")) {
	      zihaoO = this.wzzhSer.selZihao(this.zi, this.wenzhong, this.year);
	      if (zihaoO == "yes") {
	        out.println("{success:false,data:{message:'�ֺ��Ѿ�����'}}");
	        return;
	      }
	      if (this.maxzihao.intValue() <= this.zi.intValue()) {
	        try {
	          this.wzzhSer.saveWzzh(zihaoU, this.maxzihao, yuanMax, this.year);
	          out.println("{success:true,data:{message:'����ɹ���'}}");
	        } catch (Exception e) {
	          e.printStackTrace();
	          out.println("{success:false,data:{message:'����ʧ�ܣ�'}}");
	        }
	      }
	      else if (this.maxzihao.intValue() > this.zi.intValue())
	      {
	        if (zihaoO == "yes") {
	          out.println("{success:false,data:{message:'�ֺ��Ѿ�����'}}");
	          return;
	        }
	        this.wzzhSer.saveWzzh(wzzhU, zihaoU, yuanMax, this.year);
	        out.println("{success:true,data:{message:'����ɹ�'}}");
	      }
	    }
	  }
	
	public List getByWzId(String wzid){
		List list = zhSer.getByWZId(wzid);
		return list;
	}
	
	public String getActid() {
		return actid;
	}

	public void setActid(String actid) {
		this.actid = actid;
	}



	public ZiHao getZihao() {
		return zihao;
	}

	public void setZihao(ZiHao zihao) {
		this.zihao = zihao;
	}

	public void setWzzhSer(WenZhongZiHaoService wzzhSer) {
		this.wzzhSer = wzzhSer;
	}

	public WenZhongZiHaoService getWzzhSer() {
		return wzzhSer;
	}

	public void setWzshSer(WenZhongZiHaoService wzshSer) {
		this.wzzhSer = wzshSer;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getZi() {
		return zi;
	}
	public void setZi(Integer zi) {
		this.zi = zi;
	}
	public String getWenzhong() {
		return wenzhong;
	}
	public void setWenzhong(String wenzhong) {
		this.wenzhong = wenzhong;
	}
	public ZiHaoService getZhSer() {
		return zhSer;
	}
	public void setZhSer(ZiHaoService zhSer) {
		this.zhSer = zhSer;
	}
	public Integer getMaxzihao() {
		return maxzihao;
	}
	public void setMaxzihao(Integer maxzihao) {
		this.maxzihao = maxzihao;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public java.util.List<String> getYearList() {
		return yearList;
	}
	public void setYearList(java.util.List<String> yearList) {
		this.yearList = yearList;
	}
	public List getWzlist() {
		return wzlist;
	}
	public void setWzlist(List wzlist) {
		this.wzlist = wzlist;
	}
	public int getReadonly() {
		return readonly;
	}
	public void setReadonly(int readonly) {
		this.readonly = readonly;
	}
	
	
	
	
	
}
