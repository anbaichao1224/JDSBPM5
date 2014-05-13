package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.DutytempDao;
import net.itjds.usm.persistence.model.Dutytemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class DutytempDaoHibernate extends
		GenericDaoHibernate<Dutytemp, String> implements
		DutytempDao {

	public DutytempDaoHibernate() {
		super(Dutytemp.class);
	}

	public List<Dutytemp> getAll() {
		return getHibernateTemplate().find("from Dutytemp e");
	}

	public List<Dutytemp> findAcct() {
		return getHibernateTemplate().find("from Dutytemp e where e.roleacct = 'N'");
	}

	public void update(Dutytemp r) {
		super.getSession().update(r);
	}

	public List<Dutytemp> finddbAcct() {
		return getHibernateTemplate().find("from Dutytemp e where e.roleacct != 'N'");
	}

}
