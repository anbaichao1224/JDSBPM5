package com.kzxd.newkaoqin.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kzxd.newkaoqin.dao.QingJiaMingXiDao;
import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;
import com.kzxd.newkaoqin.entity.QingJiaMingXiBean;
import com.kzxd.newkaoqin.entity.QingJiaMingXiDAO;

public class QingJiaMingXiDaoImpl extends HibernateDaoSupport implements QingJiaMingXiDao{

	public void add(QingJiaMingXiBean qjmxBean) {
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		QingJiaMingXiDAO qjtdao = new QingJiaMingXiDAO();
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, qjtdao);
			UUID uuid = new UUID();
			qjtdao.setUuid(uuid.toString());
			qjtdao.setQingjiariqi(qjmxBean.getQingjiariqi());
			qjtdao.setCk(qjmxBean.getCk());
			qjtdao.setQjlx(qjmxBean.getQjlx());
			qjtdao.setPersonid(qjmxBean.getPersonid());
			qjtdao.setXm(qjmxBean.getXm());
			qjtdao.setConnection(conn);
			qjtdao.create();
			conn.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<ChuangKouTianShuBean> findAllByDate(Date sdate, Date edate) {
		List<ChuangKouTianShuBean> ckqjlist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,"
					+ "nvl((select count(*) from FDT_OA_QINGJIAMINGXI t1 where t1.personid=p.personid and t1.qingjiariqi between ? and ?),0) ts"
					+" from FDT_OA_CKPX p left join FDT_OA_QINGJIAMINGXI t  on p.personid=t.personid group by p.personid,p.kqpx order by p.kqpx").setResultTransformer(Transformers.aliasToBean(ChuangKouTianShuBean.class));
			query.addScalar("personid");
			query.addScalar("ts", Hibernate.INTEGER);
					
			query.setDate(0, sdate);
			query.setDate(1, edate);
			ckqjlist = query.list();
			if (ckqjlist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return ckqjlist;
	}

}
