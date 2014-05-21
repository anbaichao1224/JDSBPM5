package com.kzxd.zihao.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.kzxd.zihao.dao.WenZhongZiHaoDao;
import com.kzxd.zihao.entity.WenZhongZiHao;
import com.kzxd.zihao.entity.ZiHao;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;


public class WenZhongZiHaoDaoImpl extends GenericDaoHibernate<WenZhongZiHao, String> implements WenZhongZiHaoDao{

	public WenZhongZiHaoDaoImpl(){
		super(WenZhongZiHao.class);
	}

	public WenZhongZiHao getByWenZhongAndZiHao(String wenzhong,Integer zihao,String year) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from WenZhongZiHao bean1 where bean1.zihao=:zihao and bean1.year=:year and wenzhongid=:wenzhong");
		Query query = super.getSession().createQuery(sql.toString());
		query.setParameter("wenzhong", wenzhong);
		query.setParameter("year", year);
		query.setParameter("zihao", zihao);
		List list = query.list();
		if(list.size() > 0)
			return (WenZhongZiHao)list.get(0);
		else
			return null;
	}

	@Override
	public String getByZiHao(String wenzhong, Integer zihao, String year) {
	    org.hibernate.classic.Session session = null;
	    ZiHao zihao1 = null;
	    session = super.getHibernateTemplate().getSessionFactory()
	      .openSession();
	    StringBuffer sql = new StringBuffer();
	    sql.append("select * from kzxd_zihao bean1 where bean1.zihao='" + zihao + "' and bean1.year='" + year + "' and bean1.wenzhongid='" + wenzhong + "' and bean1.actid is not null ");
	    Query query = session.createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

	    List list = query.list();
	    if (list.size() > 0) {
	      return "yes";
	    }
	    return "no";
	  }
}
