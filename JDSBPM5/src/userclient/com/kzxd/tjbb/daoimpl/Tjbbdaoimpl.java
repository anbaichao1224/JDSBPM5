package com.kzxd.tjbb.daoimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kzxd.tjbb.dao.Tjbbdao;
import com.kzxd.tjbb.entity.DanWeiBean;
import com.kzxd.tjbb.entity.MingXiBean;
import com.kzxd.tjbb.entity.NeiRongBean;
import com.kzxd.tjbb.entity.ShouLiYeWuBean;
import com.kzxd.tjbb.entity.TjbbBean;
import com.kzxd.tjbb.entity.YueDuBean;

public class Tjbbdaoimpl extends HibernateDaoSupport implements Tjbbdao {

	public boolean add(TjbbBean tjbbBean) {
		try {
			super.getHibernateTemplate().save(tjbbBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<TjbbBean> findAllByPersonid(String personid,String bjzt) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid2(String personid,String bjzt,String zxdw, Date sdate,
			Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=? and bean.sbdw like ? and bean.sbsj between ? and ?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			query.setParameter(2, "%"+zxdw+"%");
			query.setParameter(3, sdate);
			query.setParameter(4, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid3(String personid, String bjzt, String zxdw,
			Date sdate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=? and bean.sbdw like ? and bean.sbsj >= ?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			query.setParameter(2, "%"+zxdw+"%");
			query.setParameter(3, sdate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid4(String personid, String bjzt, String zxdw) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=? and bean.sbdw like ?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			query.setParameter(2, "%"+zxdw+"%");
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid5(String personid, String bjzt, String zxdw,
			Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=? and bean.sbdw like ? and bean.sbsj <= ?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			query.setParameter(2, "%"+zxdw+"%");
			query.setParameter(3, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid6(String personid,String bjzt, Date sdate,Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=? and bean.sbsj between ? and ?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			query.setParameter(2, sdate);
			query.setParameter(3, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid7(String personid, String bjzt, Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=? and bean.sbsj <= ?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			query.setParameter(2, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid8(String personid, String bjzt, Date sdate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt=? and bean.sbsj >= ?");
			query.setParameter(0, personid);
			query.setParameter(1, bjzt);
			query.setParameter(2, sdate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}
	
	public List<TjbbBean> findAllByPersonid12(String bjzt) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=?");
			query.setParameter(0, bjzt);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid22(String bjzt,String personid, Date sdate,
			Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=? and bean.personid = ? and bean.sbsj between ? and ?");
			query.setParameter(0, bjzt);
			query.setParameter(1, personid);
			query.setParameter(2, sdate);
			query.setParameter(3, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid32(String bjzt, String personid,
			Date sdate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=? and bean.personid = ? and bean.sbsj >= ?");
			query.setParameter(0, bjzt);
			query.setParameter(1, personid);
			query.setParameter(2, sdate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid42(String bjzt, String personid) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=? and bean.personid = ?");
			query.setParameter(0, bjzt);
			query.setParameter(1, personid);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid52(String bjzt, String personid,
			Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=? and bean.personid = ? and bean.sbsj <= ?");
			query.setParameter(0, bjzt);
			query.setParameter(1, personid);
			query.setParameter(2, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid62(String bjzt, Date sdate,Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=? and bean.sbsj between ? and ?");
			query.setParameter(0, bjzt);
			query.setParameter(1, sdate);
			query.setParameter(2, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid72(String bjzt, Date edate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=? and bean.sbsj <= ?");
			query.setParameter(0, bjzt);
			query.setParameter(1, edate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}

	public List<TjbbBean> findAllByPersonid82(String bjzt, Date sdate) {
		List<TjbbBean> tjbblist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt=? and bean.sbsj >= ?");
			query.setParameter(0, bjzt);
			query.setParameter(1, sdate);
			tjbblist = query.list();
			if (tjbblist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbblist;
	}	

	public boolean updateTjbbByUuid(TjbbBean tjbbBean) {
		Session session = null;
		try {
			super.getHibernateTemplate().update(tjbbBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public TjbbBean getByUuid(String uuid) {
		TjbbBean tjbbBean = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.uuid = ?");
			query.setParameter(0, uuid);
			List<TjbbBean> tjbblist = query.list();
			tjbbBean = (TjbbBean) (tjbblist.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
		}
		return tjbbBean;
	}

	public List findAllByYue(Date yst, Date yet,Date nst) {
		List yueduList = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,"
							+ "nvl((select sum(t1.zxjs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.sbsj between ? and ?),0) zxjs,"
							+ "nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='即办件' and t1.sbsj between ? and ?),0) jbj,"
							+ "nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='退办件' and t1.sbsj between ? and ?),0) thj,"
							+ "nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.sbsj between ? and ?),0) bbjsjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.bjsj between ? and ?),0) bbjbjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.sbsj between ? and ?),0) cnjsjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.bjsj between ? and ?),0) cnjbjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.sbsj between ? and ?),0) lbjsjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.bjsj between ? and ?),0) lbjbjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.sbsj between ? and ?),0) tbspjsjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.bjsj between ? and ?),0) tbspjbjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.sbsj between ? and ?),0) ksspjsjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.bjsj between ? and ?),0) ksspjbjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.sbsj between ? and ?),0) sbjsjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.bjsj between ? and ?),0) sbjbjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.sbsj between ? and ?),0) hjsjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.bjsj between ? and ?),0) hjbjs," +
									"nvl((select sum(t1.zxjs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.sbsj between ? and ?),0) ndjzbyzxj," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.sbsj between ? and ?),0) ndjzbysjs," +
									"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.bjsj between ? and ?),0) ndjzbybjs " +
									"from FDT_OA_CKPX p left join FDT_OA_BJRBB t  on p.personid=t.personid group by p.personid,p.bjpx order by p.bjpx").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));
			query.addScalar("personid");
			query.addScalar("zxjs", Hibernate.INTEGER);
			query.addScalar("jbj", Hibernate.INTEGER);
			query.addScalar("thj", Hibernate.INTEGER);
			query.addScalar("bbjsjs", Hibernate.INTEGER);
			query.addScalar("bbjbjs", Hibernate.INTEGER);
			query.addScalar("cnjsjs", Hibernate.INTEGER);
			query.addScalar("cnjbjs", Hibernate.INTEGER);
			query.addScalar("lbjsjs", Hibernate.INTEGER);
			query.addScalar("lbjbjs", Hibernate.INTEGER);
			query.addScalar("tbspjsjs", Hibernate.INTEGER);
			query.addScalar("tbspjbjs", Hibernate.INTEGER);
			query.addScalar("ksspjsjs", Hibernate.INTEGER);
			query.addScalar("ksspjbjs", Hibernate.INTEGER);
			query.addScalar("sbjsjs", Hibernate.INTEGER);
			query.addScalar("sbjbjs", Hibernate.INTEGER);
			query.addScalar("hjsjs", Hibernate.INTEGER);
			query.addScalar("hjbjs", Hibernate.INTEGER);
			query.addScalar("ndjzbyzxj", Hibernate.INTEGER);
			query.addScalar("ndjzbysjs", Hibernate.INTEGER);
			query.addScalar("ndjzbybjs", Hibernate.INTEGER);
			
			query.setDate(0, yst);
			query.setDate(1, yet);
			query.setDate(2, yst);
			query.setDate(3, yet);
			query.setDate(4, yst);
			query.setDate(5, yet);
			query.setDate(6, yst);
			query.setDate(7, yet);
			query.setDate(8, yst);
			query.setDate(9, yet);
			query.setDate(10, yst);
			query.setDate(11, yet);
			query.setDate(12, yst);
			query.setDate(13, yet);
			query.setDate(14, yst);
			query.setDate(15, yet);
			query.setDate(16, yst);
			query.setDate(17, yet);
			query.setDate(18, yst);
			query.setDate(19, yet);
			query.setDate(20, yst);
			query.setDate(21, yet);
			query.setDate(22, yst);
			query.setDate(23, yet);
			query.setDate(24, yst);
			query.setDate(25, yet);
			query.setDate(26, yst);
			query.setDate(27, yet);
			query.setDate(28, yst);
			query.setDate(29, yet);
			query.setDate(30, yst);
			query.setDate(31, yet);
			query.setDate(32, yst);
			query.setDate(33, yet);
			query.setDate(34, nst);
			query.setDate(35, yet);
			query.setDate(36, nst);
			query.setDate(37, yet);
			query.setDate(38, nst);
			query.setDate(39, yet);
		    
			yueduList = query.list();
			if (yueduList != null) {
			};
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return yueduList;
	}

	public YueDuBean findAllByNian(String personid, Date et, Date st) {
		List yueduList = null;
		YueDuBean yuedubean = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.cnname tjdw,"
					   +"nvl((select sum(t1.zxjs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.sbsj between ? and ?),0) zxjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='即办件' and t1.sbsj between ? and ?),0) jbj,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='退办件' and t1.sbsj between ? and ?),0) thj,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.sbsj between ? and ?),0) bbjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.bjsj between ? and ?),0) bbjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.sbsj between ? and ?),0) cnjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.bjsj between ? and ?),0) cnjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.sbsj between ? and ?),0) lbjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.bjsj between ? and ?),0) lbjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.sbsj between ? and ?),0) tbspjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.bjsj between ? and ?),0) tbspjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.sbsj between ? and ?),0) ksspjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.bjsj between ? and ?),0) ksspjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.sbsj between ? and ?),0) sbjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.bjsj between ? and ?),0) sbjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.sbsj between ? and ?),0) hjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.bjsj between ? and ?),0) hjbjs"
				       +" from RO_PERSON p left join FDT_OA_BJRBB t  on p.personid=t.personid where p.personid=? and p.personid in (select op.personid from RO_ORGPERSON op where op.orgid='fe3dfe-13479a3202a-0f1757bd788fba6201d1188eb641f407') group by p.cnname").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));
			query.addScalar("tjdw");
			query.addScalar("zxjs", Hibernate.INTEGER);
			query.addScalar("jbj", Hibernate.INTEGER);
			query.addScalar("thj", Hibernate.INTEGER);
			query.addScalar("bbjsjs", Hibernate.INTEGER);
			query.addScalar("bbjbjs", Hibernate.INTEGER);
			query.addScalar("cnjsjs", Hibernate.INTEGER);
			query.addScalar("cnjbjs", Hibernate.INTEGER);
			query.addScalar("lbjsjs", Hibernate.INTEGER);
			query.addScalar("lbjbjs", Hibernate.INTEGER);
			query.addScalar("tbspjsjs", Hibernate.INTEGER);
			query.addScalar("tbspjbjs", Hibernate.INTEGER);
			query.addScalar("ksspjsjs", Hibernate.INTEGER);
			query.addScalar("ksspjbjs", Hibernate.INTEGER);
			query.addScalar("sbjsjs", Hibernate.INTEGER);
			query.addScalar("sbjbjs", Hibernate.INTEGER);
			query.addScalar("hjsjs", Hibernate.INTEGER);
			query.addScalar("hjbjs", Hibernate.INTEGER);
			
			query.setString(0, personid);
			query.setDate(1, st);
			query.setDate(2, et);
			query.setString(3, personid);
			query.setDate(4, st);
			query.setDate(5, et);
			query.setString(6, personid);
			query.setDate(7, st);
			query.setDate(8, et);
			query.setString(9, personid);
			query.setDate(10, st);
			query.setDate(11, et);
			query.setString(12, personid);
			query.setDate(13, st);
			query.setDate(14, et);
			query.setString(15, personid);
			query.setDate(16, st);
			query.setDate(17, et);
			query.setString(18, personid);
			query.setDate(19, st);
			query.setDate(20, et);
			query.setString(21, personid);
			query.setDate(22, st);
			query.setDate(23, et);
			query.setString(24, personid);
			query.setDate(25, st);
			query.setDate(26, et);
			query.setString(27, personid);
			query.setDate(28, st);
			query.setDate(29, et);
			query.setString(30, personid);
			query.setDate(31, st);
			query.setDate(32, et);
			query.setString(33, personid);
			query.setDate(34, st);
			query.setDate(35, et);
			query.setString(36, personid);
			query.setDate(37, st);
			query.setDate(38, et);
			query.setString(39, personid);
			query.setDate(40, st);
			query.setDate(41, et);
			query.setString(42, personid);
			query.setDate(43, st);
			query.setDate(44, et);
			query.setString(45, personid);
			query.setDate(46, st);
			query.setDate(47, et);
			query.setString(48, personid);
			query.setDate(49, st);
			query.setDate(50, et);
			query.setString(51, personid);
		    
			yueduList = query.list();
			if (yueduList != null) {
			};
			yuedubean = (YueDuBean) yueduList.get(0);
			int a=yuedubean.getZxjs();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return yuedubean;
	}
	
	public YueDuBean findAllByNian2(String personid, Date et, Date st) {
		List yueduList = null;
		YueDuBean yuedubean = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.cnname tjdw,"
					   +"nvl((select sum(t1.zxjs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.sbsj>? and t1.sbsj<?),0) zxjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='即办件' and t1.sbsj>? and t1.sbsj<?),0) jbj,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='退办件' and t1.sbsj>? and t1.sbsj<?),0) thj,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.sbsj>? and t1.sbsj<?),0) bbjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.bjsj>? and t1.bjsj<?),0) bbjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.sbsj>? and t1.sbsj<?),0) cnjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.bjsj>? and t1.bjsj<?),0) cnjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.sbsj>? and t1.sbsj<?),0) lbjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.bjsj>? and t1.bjsj<?),0) lbjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.sbsj>? and t1.sbsj<?),0) tbspjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.bjsj>? and t1.bjsj<?),0) tbspjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.sbsj>? and t1.sbsj<?),0) ksspjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.bjsj>? and t1.bjsj<?),0) ksspjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.sbsj>? and t1.sbsj<?),0) sbjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.bjsj>? and t1.bjsj<?),0) sbjbjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.sbsj>? and t1.sbsj<?),0) hjsjs,"
				       +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.tjdw=? and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.bjsj>? and t1.bjsj<?),0) hjbjs"
				       +" from RO_PERSON p left join FDT_OA_BJRBB t  on p.personid=t.personid where p.personid=? and p.personid in (select op.personid from RO_ORGPERSON op where op.orgid='fe3dfe-13479a3202a-0f1757bd788fba6201d1188eb641f407') group by p.cnname").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));
			query.addScalar("tjdw");
			query.addScalar("zxjs", Hibernate.INTEGER);
			query.addScalar("jbj", Hibernate.INTEGER);
			query.addScalar("thj", Hibernate.INTEGER);
			query.addScalar("bbjsjs", Hibernate.INTEGER);
			query.addScalar("bbjbjs", Hibernate.INTEGER);
			query.addScalar("cnjsjs", Hibernate.INTEGER);
			query.addScalar("cnjbjs", Hibernate.INTEGER);
			query.addScalar("lbjsjs", Hibernate.INTEGER);
			query.addScalar("lbjbjs", Hibernate.INTEGER);
			query.addScalar("tbspjsjs", Hibernate.INTEGER);
			query.addScalar("tbspjbjs", Hibernate.INTEGER);
			query.addScalar("ksspjsjs", Hibernate.INTEGER);
			query.addScalar("ksspjbjs", Hibernate.INTEGER);
			query.addScalar("sbjsjs", Hibernate.INTEGER);
			query.addScalar("sbjbjs", Hibernate.INTEGER);
			query.addScalar("hjsjs", Hibernate.INTEGER);
			query.addScalar("hjbjs", Hibernate.INTEGER);
			
			query.setString(0, personid);
			query.setDate(1, st);
			query.setDate(2, et);
			query.setString(3, personid);
			query.setDate(4, st);
			query.setDate(5, et);
			query.setString(6, personid);
			query.setDate(7, st);
			query.setDate(8, et);
			query.setString(9, personid);
			query.setDate(10, st);
			query.setDate(11, et);
			query.setString(12, personid);
			query.setDate(13, st);
			query.setDate(14, et);
			query.setString(15, personid);
			query.setDate(16, st);
			query.setDate(17, et);
			query.setString(18, personid);
			query.setDate(19, st);
			query.setDate(20, et);
			query.setString(21, personid);
			query.setDate(22, st);
			query.setDate(23, et);
			query.setString(24, personid);
			query.setDate(25, st);
			query.setDate(26, et);
			query.setString(27, personid);
			query.setDate(28, st);
			query.setDate(29, et);
			query.setString(30, personid);
			query.setDate(31, st);
			query.setDate(32, et);
			query.setString(33, personid);
			query.setDate(34, st);
			query.setDate(35, et);
			query.setString(36, personid);
			query.setDate(37, st);
			query.setDate(38, et);
			query.setString(39, personid);
			query.setDate(40, st);
			query.setDate(41, et);
			query.setString(42, personid);
			query.setDate(43, st);
			query.setDate(44, et);
			query.setString(45, personid);
			query.setDate(46, st);
			query.setDate(47, et);
			query.setString(48, personid);
			query.setDate(49, st);
			query.setDate(50, et);
			query.setString(51, personid);
		    
			yueduList = query.list();
			if (yueduList != null) {
			};
			yuedubean = (YueDuBean) yueduList.get(0);
			int a=yuedubean.getZxjs();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
		}
		return yuedubean;
	}

	public List findMingXi(Date st, Date et) {
		List mingxiList = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,"
					+"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.sbsj between ? and ?),0) jds,"
                    +"nvl((select sum(t1.sljs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.bjzt='上报' and t1.bjsj between ? and ?),0) bjs,"
                    +"nvl((select sum(t1.zxjs) from FDT_OA_BJRBB t1 where t1.personid=p.personid and t1.sbsj between ? and ?),0) zxs"
                    +" from FDT_OA_CKPX p left join FDT_OA_BJRBB t  on p.personid=t.personid group by p.personid,p.bjpx order by p.bjpx").setResultTransformer(Transformers.aliasToBean(MingXiBean.class));
			query.addScalar("personid");
			query.addScalar("jds", Hibernate.INTEGER);
			query.addScalar("bjs", Hibernate.INTEGER);
			query.addScalar("zxs", Hibernate.INTEGER);
			
			query.setDate(0, st);
			query.setDate(1, et);
			query.setDate(2, st);
			query.setDate(3, et);
			query.setDate(4, st);
			query.setDate(5, et);
		    
			mingxiList = query.list();
			if (mingxiList != null) {
			};
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return mingxiList;
	}

	public List findMingXiJdnr(Date st, Date et) {
		List jdnrList = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,t.sbsx sx,sum(t.sljs) sl"
								+" from FDT_OA_CKPX p left join FDT_OA_BJRBB t  on (p.personid=t.personid and t.bjzt='上报' and t.sbsj between ? and ?) group by p.personid,p.bjpx,t.sbsx order by p.bjpx").setResultTransformer(Transformers.aliasToBean(NeiRongBean.class));
			query.addScalar("personid");
			query.addScalar("sx");
			query.addScalar("sl", Hibernate.INTEGER);
			
			query.setDate(0, st);
			query.setDate(1, et);
		    
			jdnrList = query.list();
			if (jdnrList != null) {
			};
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return jdnrList;
	}

	public List findMingXiBjnr(Date st, Date et) {
		List bjnrList = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,t.sbsx sx,sum(t.sljs) sl"
								+" from FDT_OA_CKPX p left join FDT_OA_BJRBB t  on (p.personid=t.personid and t.bjzt='上报' and t.bjsj between ? and ?) group by p.personid,p.bjpx,t.sbsx order by p.bjpx").setResultTransformer(Transformers.aliasToBean(NeiRongBean.class));
			query.addScalar("personid");
			query.addScalar("sx");
			query.addScalar("sl", Hibernate.INTEGER);
			
			query.setDate(0, st);
			query.setDate(1, et);
		    
			bjnrList = query.list();
			if (bjnrList != null) {
			};
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return bjnrList;
	}

	public List<DanWeiBean> danWeiXiaLa() {
		List<DanWeiBean> danweilist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid,p.cnname dw from RO_PERSON p where  p.personid in (select op.personid from RO_ORGPERSON op where op.orgid='fe3dfe-13479a3202a-0f1757bd788fba6201d1188eb641f407')").setResultTransformer(Transformers.aliasToBean(DanWeiBean.class));
			
			query.addScalar("dw");
			query.addScalar("personid");
			
			danweilist = query.list();
			if (danweilist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return danweilist;
	}

	public MingXiBean zongJiMingXi(Date st, Date et) {
		List<MingXiBean> heJiJieDanShuList = null;
		List<MingXiBean> heJiBanJieShuList = null;
		List<MingXiBean> heJiZiXunShuList = null;
		MingXiBean heJiBean = new MingXiBean();
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query1 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) jds from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(MingXiBean.class));			
			query1.addScalar("jds", Hibernate.INTEGER);
			query1.setDate(0, st);
			query1.setDate(1, et);
			heJiJieDanShuList = query1.list();
			if (heJiJieDanShuList != null) {
			}
			
			SQLQuery query2 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(MingXiBean.class));			
			query2.addScalar("bjs", Hibernate.INTEGER);
			query2.setDate(0, st);
			query2.setDate(1, et);
			heJiBanJieShuList = query2.list();
			if (heJiBanJieShuList != null) {
			}
			
			SQLQuery query3 = (SQLQuery) session.createSQLQuery("select sum(t1.zxjs) zxs from FDT_OA_BJRBB t1 where t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(MingXiBean.class));			
			query3.addScalar("zxs", Hibernate.INTEGER);
			query3.setDate(0, st);
			query3.setDate(1, et);
			heJiZiXunShuList = query3.list();
			if (heJiZiXunShuList != null) {
			}
			heJiBean.setJds(heJiJieDanShuList.get(0).getJds());
			heJiBean.setBjs(heJiBanJieShuList.get(0).getBjs());
			heJiBean.setZxs(heJiZiXunShuList.get(0).getZxs());
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return heJiBean;
	}

	public YueDuBean yueDuHeJi(Date yst, Date yet, Date nst) {
		List<YueDuBean> heJiZxjsList = null;
		List<YueDuBean> heJiJbjList = null;
		List<YueDuBean> heJiThjList = null;
		List<YueDuBean> heJiBbjsjsList = null;
		List<YueDuBean> heJiBbjbjsList = null;
		List<YueDuBean> heJiCnjsjsList = null;
		List<YueDuBean> heJiCnjbjsList = null;
		List<YueDuBean> heJiLbjsjsList = null;
		List<YueDuBean> heJiLbjbjsList = null;
		List<YueDuBean> heJiTbspjsjsList = null;
		List<YueDuBean> heJiTbspjbjsList = null;
		List<YueDuBean> heJiKsspjsjsList = null;
		List<YueDuBean> heJiKsspjbjsList = null;
		List<YueDuBean> heJiSbjsjsList = null;
		List<YueDuBean> heJiSbjbjsList = null;
		List<YueDuBean> heJiHjsjsList = null;
		List<YueDuBean> heJiHjbjsList = null;
		List<YueDuBean> heJiNdjzbyzxjList = null;
		List<YueDuBean> heJiNdjzbysjsList = null;
		List<YueDuBean> heJiNdjzbybjsList = null;

		YueDuBean yueDuHeJiBean = new YueDuBean();
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query1 = (SQLQuery) session.createSQLQuery("select sum(t1.zxjs) zxjs from FDT_OA_BJRBB t1 where t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query1.addScalar("zxjs", Hibernate.INTEGER);
			query1.setDate(0, yst);
			query1.setDate(1, yet);
			heJiZxjsList = query1.list();
			if (heJiZxjsList != null) {
			}
			
			SQLQuery query2 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) jbj from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='即办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query2.addScalar("jbj", Hibernate.INTEGER);
			query2.setDate(0, yst);
			query2.setDate(1, yet);
			heJiJbjList = query2.list();
			if (heJiJbjList != null) {
			}
			
			SQLQuery query3 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) thj from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='退办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query3.addScalar("thj", Hibernate.INTEGER);
			query3.setDate(0, yst);
			query3.setDate(1, yet);
			heJiThjList = query3.list();
			if (heJiThjList != null) {
			}
			
			SQLQuery query4 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bbjsjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='补办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query4.addScalar("bbjsjs", Hibernate.INTEGER);
			query4.setDate(0, yst);
			query4.setDate(1, yet);
			heJiBbjsjsList = query4.list();
			if (heJiBbjsjsList != null) {
			}
			
			SQLQuery query5 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bbjbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='补办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query5.addScalar("bbjbjs", Hibernate.INTEGER);
			query5.setDate(0, yst);
			query5.setDate(1, yet);
			heJiBbjbjsList = query5.list();
			if (heJiBbjbjsList != null) {
			}
			
			SQLQuery query6 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) cnjsjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query6.addScalar("cnjsjs", Hibernate.INTEGER);
			query6.setDate(0, yst);
			query6.setDate(1, yet);
			heJiCnjsjsList = query6.list();
			if (heJiCnjsjsList != null) {
			}
			
			SQLQuery query7 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) cnjbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query7.addScalar("cnjbjs", Hibernate.INTEGER);
			query7.setDate(0, yst);
			query7.setDate(1, yet);
			heJiCnjbjsList = query7.list();
			if (heJiCnjbjsList != null) {
			}
			
			SQLQuery query8 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) lbjsjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='联办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query8.addScalar("lbjsjs", Hibernate.INTEGER);
			query8.setDate(0, yst);
			query8.setDate(1, yet);
			heJiLbjsjsList = query8.list();
			if (heJiLbjsjsList != null) {
			}
			
			SQLQuery query9 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) lbjbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='联办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query9.addScalar("lbjbjs", Hibernate.INTEGER);
			query9.setDate(0, yst);
			query9.setDate(1, yet);
			heJiLbjbjsList = query9.list();
			if (heJiLbjbjsList != null) {
			}
			
			SQLQuery query10 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) tbspjsjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='同审件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query10.addScalar("tbspjsjs", Hibernate.INTEGER);
			query10.setDate(0, yst);
			query10.setDate(1, yet);
			heJiTbspjsjsList = query10.list();
			if (heJiTbspjsjsList != null) {
			}
			
			SQLQuery query11 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) tbspjbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='同审件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query11.addScalar("tbspjbjs", Hibernate.INTEGER);
			query11.setDate(0, yst);
			query11.setDate(1, yet);
			heJiTbspjbjsList = query11.list();
			if (heJiTbspjbjsList != null) {
			}
			
			SQLQuery query12 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) ksspjsjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='快办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query12.addScalar("ksspjsjs", Hibernate.INTEGER);
			query12.setDate(0, yst);
			query12.setDate(1, yet);
			heJiKsspjsjsList = query12.list();
			if (heJiKsspjsjsList != null) {
			}
			
			SQLQuery query13 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) ksspjbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='快办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query13.addScalar("ksspjbjs", Hibernate.INTEGER);
			query13.setDate(0, yst);
			query13.setDate(1, yet);
			heJiKsspjbjsList = query13.list();
			if (heJiKsspjbjsList != null) {
			}
			
			SQLQuery query14 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) sbjsjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='上报件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query14.addScalar("sbjsjs", Hibernate.INTEGER);
			query14.setDate(0, yst);
			query14.setDate(1, yet);
			heJiSbjsjsList = query14.list();
			if (heJiSbjsjsList != null) {
			}
			
			SQLQuery query15 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) sbjbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj='上报件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query15.addScalar("sbjbjs", Hibernate.INTEGER);
			query15.setDate(0, yst);
			query15.setDate(1, yet);
			heJiSbjbjsList = query15.list();
			if (heJiSbjbjsList != null) {
			}
			
			SQLQuery query16 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) hjsjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query16.addScalar("hjsjs", Hibernate.INTEGER);
			query16.setDate(0, yst);
			query16.setDate(1, yet);
			heJiHjsjsList = query16.list();
			if (heJiHjsjsList != null) {
			}
			
			SQLQuery query17 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) hjbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query17.addScalar("hjbjs", Hibernate.INTEGER);
			query17.setDate(0, yst);
			query17.setDate(1, yet);
			heJiHjbjsList = query17.list();
			if (heJiHjbjsList != null) {
			}
			
			SQLQuery query18 = (SQLQuery) session.createSQLQuery("select sum(t1.zxjs) ndjzbyzxj from FDT_OA_BJRBB t1 where t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query18.addScalar("ndjzbyzxj", Hibernate.INTEGER);
			query18.setDate(0, nst);
			query18.setDate(1, yet);
			heJiNdjzbyzxjList = query18.list();
			if (heJiNdjzbyzxjList != null) {
			}
			
			SQLQuery query19 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) ndjzbysjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query19.addScalar("ndjzbysjs", Hibernate.INTEGER);
			query19.setDate(0, nst);
			query19.setDate(1, yet);
			heJiNdjzbysjsList = query19.list();
			if (heJiNdjzbysjsList != null) {
			}
			
			SQLQuery query20 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) ndjzbybjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query20.addScalar("ndjzbybjs", Hibernate.INTEGER);
			query20.setDate(0, nst);
			query20.setDate(1, yet);
			heJiNdjzbybjsList = query20.list();
			if (heJiNdjzbybjsList != null) {
			}


			yueDuHeJiBean.setZxjs(heJiZxjsList.get(0).getZxjs());
			yueDuHeJiBean.setJbj(heJiJbjList.get(0).getJbj());
			yueDuHeJiBean.setThj(heJiThjList.get(0).getThj());
			yueDuHeJiBean.setBbjsjs(heJiBbjsjsList.get(0).getBbjsjs());
			yueDuHeJiBean.setBbjbjs(heJiBbjbjsList.get(0).getBbjbjs());
			yueDuHeJiBean.setCnjsjs(heJiCnjsjsList.get(0).getCnjsjs());
			yueDuHeJiBean.setCnjbjs(heJiCnjbjsList.get(0).getCnjbjs());
			yueDuHeJiBean.setLbjsjs(heJiLbjsjsList.get(0).getLbjsjs());
			yueDuHeJiBean.setLbjbjs(heJiLbjbjsList.get(0).getLbjbjs());
			yueDuHeJiBean.setTbspjsjs(heJiTbspjsjsList.get(0).getTbspjsjs());
			yueDuHeJiBean.setTbspjbjs(heJiTbspjbjsList.get(0).getTbspjbjs());
			yueDuHeJiBean.setKsspjsjs(heJiKsspjsjsList.get(0).getKsspjsjs());
			yueDuHeJiBean.setKsspjbjs(heJiKsspjbjsList.get(0).getKsspjbjs());
			yueDuHeJiBean.setSbjsjs(heJiSbjsjsList.get(0).getSbjsjs());
			yueDuHeJiBean.setSbjbjs(heJiSbjbjsList.get(0).getSbjbjs());
			yueDuHeJiBean.setHjsjs(heJiHjsjsList.get(0).getHjsjs());
			yueDuHeJiBean.setHjbjs(heJiHjbjsList.get(0).getHjbjs());
			yueDuHeJiBean.setNdjzbyzxj(heJiNdjzbyzxjList.get(0).getNdjzbyzxj());
			yueDuHeJiBean.setNdjzbysjs(heJiNdjzbysjsList.get(0).getNdjzbysjs());
			yueDuHeJiBean.setNdjzbybjs(heJiNdjzbybjsList.get(0).getNdjzbybjs());
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return yueDuHeJiBean;
	}

	public YueDuBean nianDuHeJi(String personid,Date nst, Date net) {
		List<YueDuBean> heJiZxjsList = null;
		List<YueDuBean> heJiJbjList = null;
		List<YueDuBean> heJiThjList = null;
		List<YueDuBean> heJiBbjsjsList = null;
		List<YueDuBean> heJiBbjbjsList = null;
		List<YueDuBean> heJiCnjsjsList = null;
		List<YueDuBean> heJiCnjbjsList = null;
		List<YueDuBean> heJiLbjsjsList = null;
		List<YueDuBean> heJiLbjbjsList = null;
		List<YueDuBean> heJiTbspjsjsList = null;
		List<YueDuBean> heJiTbspjbjsList = null;
		List<YueDuBean> heJiKsspjsjsList = null;
		List<YueDuBean> heJiKsspjbjsList = null;
		List<YueDuBean> heJiSbjsjsList = null;
		List<YueDuBean> heJiSbjbjsList = null;
		List<YueDuBean> heJiHjsjsList = null;
		List<YueDuBean> heJiHjbjsList = null;
		YueDuBean nianDuHeJiBean = new YueDuBean();
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query1 = (SQLQuery) session.createSQLQuery("select sum(t1.zxjs) zxjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query1.addScalar("zxjs", Hibernate.INTEGER);
			query1.setString(0, personid);
			query1.setDate(1, nst);
			query1.setDate(2, net);
			heJiZxjsList = query1.list();
			if (heJiZxjsList != null) {
			}
			
			SQLQuery query2 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) jbj from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='即办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query2.addScalar("jbj", Hibernate.INTEGER);
			query2.setString(0, personid);
			query2.setDate(1, nst);
			query2.setDate(2, net);
			heJiJbjList = query2.list();
			if (heJiJbjList != null) {
			}
			
			SQLQuery query3 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) thj from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='退办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query3.addScalar("thj", Hibernate.INTEGER);
			query3.setString(0, personid);
			query3.setDate(1, nst);
			query3.setDate(2, net);
			heJiThjList = query3.list();
			if (heJiThjList != null) {
			}
			
			SQLQuery query4 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bbjsjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query4.addScalar("bbjsjs", Hibernate.INTEGER);
			query4.setString(0, personid);
			query4.setDate(1, nst);
			query4.setDate(2, net);
			heJiBbjsjsList = query4.list();
			if (heJiBbjsjsList != null) {
			}
			
			SQLQuery query5 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bbjbjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='补办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query5.addScalar("bbjbjs", Hibernate.INTEGER);
			query5.setString(0, personid);
			query5.setDate(1, nst);
			query5.setDate(2, net);
			heJiBbjbjsList = query5.list();
			if (heJiBbjbjsList != null) {
			}
			
			SQLQuery query6 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) cnjsjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query6.addScalar("cnjsjs", Hibernate.INTEGER);
			query6.setString(0, personid);
			query6.setDate(1, nst);
			query6.setDate(2, net);
			heJiCnjsjsList = query6.list();
			if (heJiCnjsjsList != null) {
			}
			
			SQLQuery query7 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) cnjbjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='承诺件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query7.addScalar("cnjbjs", Hibernate.INTEGER);
			query7.setString(0, personid);
			query7.setDate(1, nst);
			query7.setDate(2, net);
			heJiCnjbjsList = query7.list();
			if (heJiCnjbjsList != null) {
			}
			
			SQLQuery query8 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) lbjsjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query8.addScalar("lbjsjs", Hibernate.INTEGER);
			query8.setString(0, personid);
			query8.setDate(1, nst);
			query8.setDate(2, net);
			heJiLbjsjsList = query8.list();
			if (heJiLbjsjsList != null) {
			}
			
			SQLQuery query9 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) lbjbjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='联办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query9.addScalar("lbjbjs", Hibernate.INTEGER);
			query9.setString(0, personid);
			query9.setDate(1, nst);
			query9.setDate(2, net);
			heJiLbjbjsList = query9.list();
			if (heJiLbjbjsList != null) {
			}
			
			SQLQuery query10 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) tbspjsjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query10.addScalar("tbspjsjs", Hibernate.INTEGER);
			query10.setString(0, personid);
			query10.setDate(1, nst);
			query10.setDate(2, net);
			heJiTbspjsjsList = query10.list();
			if (heJiTbspjsjsList != null) {
			}
			
			SQLQuery query11 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) tbspjbjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='同审件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query11.addScalar("tbspjbjs", Hibernate.INTEGER);
			query11.setString(0, personid);
			query11.setDate(1, nst);
			query11.setDate(2, net);
			heJiTbspjbjsList = query11.list();
			if (heJiTbspjbjsList != null) {
			}
			
			SQLQuery query12 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) ksspjsjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query12.addScalar("ksspjsjs", Hibernate.INTEGER);
			query12.setString(0, personid);
			query12.setDate(1, nst);
			query12.setDate(2, net);
			heJiKsspjsjsList = query12.list();
			if (heJiKsspjsjsList != null) {
			}
			
			SQLQuery query13 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) ksspjbjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='快办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query13.addScalar("ksspjbjs", Hibernate.INTEGER);
			query13.setString(0, personid);
			query13.setDate(1, nst);
			query13.setDate(2, net);
			heJiKsspjbjsList = query13.list();
			if (heJiKsspjbjsList != null) {
			}
			
			SQLQuery query14 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) sbjsjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query14.addScalar("sbjsjs", Hibernate.INTEGER);
			query14.setString(0, personid);
			query14.setDate(1, nst);
			query14.setDate(2, net);
			heJiSbjsjsList = query14.list();
			if (heJiSbjsjsList != null) {
			}
			
			SQLQuery query15 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) sbjbjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj='上报件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query15.addScalar("sbjbjs", Hibernate.INTEGER);
			query15.setString(0, personid);
			query15.setDate(1, nst);
			query15.setDate(2, net);
			heJiSbjbjsList = query15.list();
			if (heJiSbjbjsList != null) {
			}
			
			SQLQuery query16 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) hjsjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query16.addScalar("hjsjs", Hibernate.INTEGER);
			query16.setString(0, personid);
			query16.setDate(1, nst);
			query16.setDate(2, net);
			heJiHjsjsList = query16.list();
			if (heJiHjsjsList != null) {
			}
			
			SQLQuery query17 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) hjbjs from FDT_OA_BJRBB t1 where t1.personid=? and t1.bjzt='上报' and t1.xmbllj<>'退办件' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(YueDuBean.class));			
			query17.addScalar("hjbjs", Hibernate.INTEGER);
			query17.setString(0, personid);
			query17.setDate(1, nst);
			query17.setDate(2, net);
			heJiHjbjsList = query17.list();
			if (heJiHjbjsList != null) {
			}

			nianDuHeJiBean.setZxjs(heJiZxjsList.get(0).getZxjs());
			nianDuHeJiBean.setJbj(heJiJbjList.get(0).getJbj());
			nianDuHeJiBean.setThj(heJiThjList.get(0).getThj());
			nianDuHeJiBean.setBbjsjs(heJiBbjsjsList.get(0).getBbjsjs());
			nianDuHeJiBean.setBbjbjs(heJiBbjbjsList.get(0).getBbjbjs());
			nianDuHeJiBean.setCnjsjs(heJiCnjsjsList.get(0).getCnjsjs());
			nianDuHeJiBean.setCnjbjs(heJiCnjbjsList.get(0).getCnjbjs());
			nianDuHeJiBean.setLbjsjs(heJiLbjsjsList.get(0).getLbjsjs());
			nianDuHeJiBean.setLbjbjs(heJiLbjbjsList.get(0).getLbjbjs());
			nianDuHeJiBean.setTbspjsjs(heJiTbspjsjsList.get(0).getTbspjsjs());
			nianDuHeJiBean.setTbspjbjs(heJiTbspjbjsList.get(0).getTbspjbjs());
			nianDuHeJiBean.setKsspjsjs(heJiKsspjsjsList.get(0).getKsspjsjs());
			nianDuHeJiBean.setKsspjbjs(heJiKsspjbjsList.get(0).getKsspjbjs());
			nianDuHeJiBean.setSbjsjs(heJiSbjsjsList.get(0).getSbjsjs());
			nianDuHeJiBean.setSbjbjs(heJiSbjbjsList.get(0).getSbjbjs());
			nianDuHeJiBean.setHjsjs(heJiHjsjsList.get(0).getHjsjs());
			nianDuHeJiBean.setHjbjs(heJiHjbjsList.get(0).getHjbjs());
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return nianDuHeJiBean;
	}

	public ShouLiYeWuBean yueDuYeWu(Date yst, Date yet) {
		List<ShouLiYeWuBean> yueduyewulist1=null;
		List<ShouLiYeWuBean> yueduyewulist2=null;
		List<ShouLiYeWuBean> yueduyewulist3=null;
		List<ShouLiYeWuBean> yueduyewulist4=null;
		List<ShouLiYeWuBean> yueduyewulist5=null;
		
		ShouLiYeWuBean yueDuYeWuBean=new ShouLiYeWuBean();
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query1 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) sjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query1.addScalar("sjs", Hibernate.INTEGER);
			query1.setDate(0, yst);
			query1.setDate(1, yet);
			yueduyewulist1=query1.list();
			if (yueduyewulist1 != null) {
			}					
			
			SQLQuery query2 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) nss from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ? and t1.sbsx in (select t2.cksx from FDT_OA_CKSXML t2 where t2.sfns='是')").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query2.addScalar("nss", Hibernate.INTEGER);
			query2.setDate(0, yst);
			query2.setDate(1, yet);
			yueduyewulist2=query2.list();
			if (yueduyewulist2 != null) {
			}
			
			SQLQuery query3 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query3.addScalar("bjs", Hibernate.INTEGER);
			query3.setDate(0, yst);
			query3.setDate(1, yet);
			yueduyewulist3=query3.list();
			if (yueduyewulist3 != null) {
			}
			
			SQLQuery query4 = (SQLQuery) session.createSQLQuery("select sum(t1.zxjs) zxj from FDT_OA_BJRBB t1 where t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query4.addScalar("zxj", Hibernate.INTEGER);
			query4.setDate(0, yst);
			query4.setDate(1, yet);
			yueduyewulist4=query4.list();
			if (yueduyewulist4 != null) {
			}
			
			SQLQuery query5 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bysbbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ? and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query5.addScalar("bysbbjs", Hibernate.INTEGER);
			query5.setDate(0, yst);
			query5.setDate(1, yet);
			query5.setDate(2, yst);
			query5.setDate(3, yet);
			yueduyewulist5=query5.list();
			if (yueduyewulist5 != null) {
			}
			
			Integer sjs=yueduyewulist1.get(0).getSjs();
			if(sjs==null){
				sjs=0;
			}
			Integer nss=yueduyewulist2.get(0).getNss();
			if(nss==null){
				nss=0;
			}
			float a=(float)nss/sjs;
			float nssbl=(float)(Math.round(a*10000))/100;
			Integer bjs=yueduyewulist3.get(0).getBjs();
			if(bjs==null){
				bjs=0;
			}
			Integer bysbbjs=yueduyewulist5.get(0).getBysbbjs();
			if(bysbbjs==null){
				bysbbjs=0;
			}
			int fbysbbjs=bjs-bysbbjs;
			
			float b=(float)bysbbjs/sjs;
			float bjl=(float)(Math.round(b*10000))/100;
			
			Integer zxj=yueduyewulist4.get(0).getZxj();
			if(zxj==null){
				zxj=0;
			}
			
			yueDuYeWuBean.setSjs(sjs);
			yueDuYeWuBean.setNss(nss);
			yueDuYeWuBean.setNssbl(nssbl);
			yueDuYeWuBean.setBjs(bjs);
			yueDuYeWuBean.setFbysbbjs(fbysbbjs);
			yueDuYeWuBean.setBjl(bjl);
			yueDuYeWuBean.setZxj(zxj);
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return yueDuYeWuBean;
	}

	public ShouLiYeWuBean nianDuYeWu(Date yst, Date yet) {
		List<ShouLiYeWuBean> nianduyewulist1=null;
		List<ShouLiYeWuBean> nianduyewulist2=null;
		List<ShouLiYeWuBean> nianduyewulist3=null;
		List<ShouLiYeWuBean> nianduyewulist4=null;
		List<ShouLiYeWuBean> nianduyewulist5=null;
		
		ShouLiYeWuBean nianDuYeWuBean=new ShouLiYeWuBean();
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query1 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) sjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query1.addScalar("sjs", Hibernate.INTEGER);
			query1.setDate(0, yst);
			query1.setDate(1, yet);
			nianduyewulist1=query1.list();
			if (nianduyewulist1 != null) {
			}					
			
			SQLQuery query2 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) nss from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ? and t1.sbsx in (select t2.cksx from FDT_OA_CKSXML t2 where t2.sfns='是')").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query2.addScalar("nss", Hibernate.INTEGER);
			query2.setDate(0, yst);
			query2.setDate(1, yet);
			nianduyewulist2=query2.list();
			if (nianduyewulist2 != null) {
			}
			
			SQLQuery query3 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query3.addScalar("bjs", Hibernate.INTEGER);
			query3.setDate(0, yst);
			query3.setDate(1, yet);
			nianduyewulist3=query3.list();
			if (nianduyewulist3 != null) {
			}
			
			SQLQuery query4 = (SQLQuery) session.createSQLQuery("select sum(t1.zxjs) zxj from FDT_OA_BJRBB t1 where t1.sbsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query4.addScalar("zxj", Hibernate.INTEGER);
			query4.setDate(0, yst);
			query4.setDate(1, yet);
			nianduyewulist4=query4.list();
			if (nianduyewulist4 != null) {
			}
			
			SQLQuery query5 = (SQLQuery) session.createSQLQuery("select sum(t1.sljs) bysbbjs from FDT_OA_BJRBB t1 where t1.bjzt='上报' and t1.sbsj between ? and ? and t1.bjsj between ? and ?").setResultTransformer(Transformers.aliasToBean(ShouLiYeWuBean.class));
			query5.addScalar("bysbbjs", Hibernate.INTEGER);
			query5.setDate(0, yst);
			query5.setDate(1, yet);
			query5.setDate(2, yst);
			query5.setDate(3, yet);
			nianduyewulist5=query5.list();
			if (nianduyewulist5 != null) {
			}
			
			Integer bjs=nianduyewulist3.get(0).getBjs();
			if(bjs==null){
				bjs=0;
			}
			Integer bysbbjs=nianduyewulist5.get(0).getBysbbjs();
			if(bysbbjs==null){
				bysbbjs=0;
			}
			
			Integer sjs=nianduyewulist1.get(0).getSjs();
			if(sjs==null){
				sjs=0;
			}
			
			Integer nss=nianduyewulist2.get(0).getNss();
			if(nss==null){
				nss=0;
			}
			
			Integer zxj=nianduyewulist4.get(0).getZxj();
			if(zxj==null){
				zxj=0;
			}
			
			float b=(float)bysbbjs/sjs;
			float bjl=(float)(Math.round(b*10000))/100;
			nianDuYeWuBean.setSjs(sjs);
			nianDuYeWuBean.setNss(nss);
			nianDuYeWuBean.setBjs(bjs);
			nianDuYeWuBean.setBjl(bjl);
			nianDuYeWuBean.setZxj(zxj);
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return nianDuYeWuBean;
	}

	public List<TjbbBean> ziXunFindAllByPersonid(String personid) {
		List<TjbbBean> zixunlist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.personid=? and bean.bjzt is null");
			query.setParameter(0, personid);
			zixunlist = query.list();
			if (zixunlist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
		}
		return zixunlist;
	}

	public List<TjbbBean> ziXunHuiZongFindAllByTjdw() {
		List<TjbbBean> zixunlist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from TjbbBean bean where bean.bjzt is null");
			zixunlist = query.list();
			if (zixunlist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return zixunlist;
	}

	public MingXiBean findByPersonid(String personid) {
		MingXiBean mxBean = null;
   	    Session session = null;
   	    try{
   	    	session = super.getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,p.cnname dw"
					+" from RO_PERSON p where p.personid = ?").setResultTransformer(Transformers.aliasToBean(MingXiBean.class));
			query.addScalar("personid");
			query.addScalar("dw");
	
			query.setString(0, personid);

   	    	List<MingXiBean> mxList = query.list();
   	    	if(mxList.size()>0){
   	    		mxBean=(MingXiBean)(mxList.get(0));
   	    	}else{
   	    		return mxBean;
   	    	}
   	    } catch(Exception e){
   	    	e.printStackTrace();  		 
   	    }finally{
   	   	 	session.close();
   	    }

   	    return mxBean;
	}
	
	

}
