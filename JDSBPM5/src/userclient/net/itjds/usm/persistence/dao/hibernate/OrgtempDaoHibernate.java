package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.OrgtempDao;
import net.itjds.usm.persistence.model.Orgtemp;
import net.itjds.usm.persistence.model.Roletemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class OrgtempDaoHibernate extends
		GenericDaoHibernate<Orgtemp, String> implements
		OrgtempDao {

	public OrgtempDaoHibernate() {
		super(Orgtemp.class);
	}

	public List<Orgtemp> getAll() {
		return getHibernateTemplate().find("from Orgtemp e");
	}

	public List<Orgtemp> findAcct() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Orgtemp e where e.roleacct = 'N'");
	}

	public void update(Orgtemp r) {
		super.getSession().update(r);
	}

	public List<Orgtemp> finddbAcct() {
		return getHibernateTemplate().find("from Orgtemp e where e.roleacct != 'N'");
	}

}
