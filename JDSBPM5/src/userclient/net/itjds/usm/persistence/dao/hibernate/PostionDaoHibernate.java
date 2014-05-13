package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.PostiontempDao;
import net.itjds.usm.persistence.model.Postiontemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class PostionDaoHibernate extends
		GenericDaoHibernate<Postiontemp, String> implements
		PostiontempDao {

	public PostionDaoHibernate() {
		super(Postiontemp.class);
	}

	public List<Postiontemp> getAll() {
		return getHibernateTemplate().find("from Postiontemp e");
	}

	public List<Postiontemp> findAcct() {
		return getHibernateTemplate().find("from Postiontemp e where e.roleacct = 'N'");
	}

	public void update(Postiontemp p) {
		super.getSession().update(p);
	}

	public List<Postiontemp> findybAcct() {
		return getHibernateTemplate().find("from Postiontemp e where e.roleacct != 'N'");
	}

}
