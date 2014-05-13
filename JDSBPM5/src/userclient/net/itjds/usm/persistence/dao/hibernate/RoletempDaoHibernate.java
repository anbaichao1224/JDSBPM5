package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.RoletempDao;
import net.itjds.usm.persistence.model.Personsecure;
import net.itjds.usm.persistence.model.Roletemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class RoletempDaoHibernate extends
		GenericDaoHibernate<Roletemp, String> implements
		RoletempDao {

	public RoletempDaoHibernate() {
		super(Roletemp.class);
	}

	public List<Roletemp> getAll() {
		return getHibernateTemplate().find("from Roletemp e");
	}

	public List<Roletemp> findAcct() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Roletemp e where e.roleacct = 'N'");
	}

	public void update(Roletemp r) {
		super.getSession().update(r);
	}

	public List<Roletemp> finddbAcct() {
		return getHibernateTemplate().find("from Roletemp e where e.roleacct != 'N'");
	}

}
