package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.BaseroleDao;
import net.itjds.usm.persistence.model.Baserole;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class BaseroleDaoHibernate extends
		GenericDaoHibernate<Baserole, String> implements
		BaseroleDao {

	public BaseroleDaoHibernate() {
		super(Baserole.class);
	}

	public List<Baserole> getAll() {
		return super.getSession().createSQLQuery("select distinct e.roleclass from Baserole e").list();
	}

}
