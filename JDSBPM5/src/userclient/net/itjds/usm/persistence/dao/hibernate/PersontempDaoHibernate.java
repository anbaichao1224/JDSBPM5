package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.PersontempDao;
import net.itjds.usm.persistence.model.Persontemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class PersontempDaoHibernate extends
		GenericDaoHibernate<Persontemp, String> implements
		PersontempDao {

	public PersontempDaoHibernate() {
		super(Persontemp.class);
	}

	public List<Persontemp> getAll() {
		return getHibernateTemplate().find("from Persontemp e");
	}

	public List<Persontemp> findAcct() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Persontemp e where e.roleacct = 'N'");
	}

	public void update(Persontemp r) {
		super.getSession().update(r);
	}

	public List<Persontemp> finddbAcct() {
		return getHibernateTemplate().find("from Persontemp e where e.roleacct != 'N'");
	}

}
