package com.kzxd.zihao.dao.impl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import net.itjds.common.database.DBBeanBase;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.kzxd.zihao.dao.ZiHaoDao;
import com.kzxd.zihao.entity.WenZhongZiHao;
import com.kzxd.zihao.entity.ZiHao;
public class ZiHaoDaoImpl extends GenericDaoHibernate<ZiHao, String> implements ZiHaoDao{
	public ZiHaoDaoImpl(){
		super(ZiHao.class);
	}

	public void update(ZiHao zihao) {
		this.getSession().update(zihao);
		
	}

	public Integer getMaxZiHao(String wenzhong,String year) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("  select max(bean.zihao) from ZiHao bean where bean.wenzhongid=:wenzhong and bean.year=:year");
		Query query = super.getSession().createQuery(sql.toString());
		query.setParameter("wenzhong", wenzhong);
		query.setParameter("year", year);
		Integer s = (Integer) query.uniqueResult();
		if(s!=null){
			return s;
		}
		return 0;
	}
	
	public ZiHao getByActid(String actid){
		StringBuffer sql = new StringBuffer();
		sql.append("  from ZiHao bean where bean.actid=:actid ");
		Query query = super.getSession().createQuery(sql.toString());
		query.setParameter("actid", actid);
		ZiHao s = (ZiHao) query.uniqueResult();
		return s;
	}
	
	
	public List getByWZId(String wzid) {
		
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ZiHao bean where bean.wenzhongid=:wenzhong");
			Query query = super.getSession().createQuery(sql.toString());
			query.setParameter("wenzhong", wzid);
			return query.list();
	}
	  public String getActdefIdByActid(String actid) {
		    System.out.println("hhh=" + actid);
		    Statement st = null;
		    DBBeanBase dbbase = new DBBeanBase("bpm", true);
		    Connection conn = null;
		    ResultSet rs = null;
		    String sql = "select activitydef_id from bpm_activityinstance where activityinst_id='" + actid + "'";
		    System.out.println("sql=" + sql);
		    String actdefid = null;
		    try {
		      conn = dbbase.getConn();
		      st = conn.createStatement();
		      rs = st.executeQuery(sql);
		      while (rs.next()) {
		        actdefid = rs.getString("activitydef_id");
		        System.out.println("aaa=" + actdefid);
		      }
		    }
		    catch (Exception localException)
		    {
		      try {
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
}
