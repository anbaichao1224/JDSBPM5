package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.LeveltempDao;
import net.itjds.usm.persistence.model.Leveltemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class LeveltempDaoHibernate extends
		GenericDaoHibernate<Leveltemp, String> implements
		LeveltempDao {

	public LeveltempDaoHibernate() {
		super(Leveltemp.class);
	}

	public List<Leveltemp> getAll() {
		return getHibernateTemplate().find("from Leveltemp e");
	}

	public List<Leveltemp> findAcct() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Leveltemp e where e.roleacct = 'N'");
	}

	public void update(Leveltemp r) {
		super.getSession().update(r);
	}

	public List<Leveltemp> finddbAcct() {
		return getHibernateTemplate().find("from Leveltemp e where e.roleacct != 'N'");
	}

}
