package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.ModuletempDao;
import net.itjds.usm.persistence.model.Moduletemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class ModuletempDaoHibernate extends
		GenericDaoHibernate<Moduletemp, String> implements
		ModuletempDao {

	public ModuletempDaoHibernate() {
		super(Moduletemp.class);
	}

	public List<Moduletemp> getAll() {
		return getHibernateTemplate().find("from Moduletemp e");
	}

	public List<Moduletemp> findAcct() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Moduletemp e where e.roleacct = 'N'");
	}

	public void update(Moduletemp r) {
		super.getSession().update(r);
	}

	public List<Moduletemp> finddbAcct() {
		return getHibernateTemplate().find("from Moduletemp e where e.roleacct != 'N'");
	}

}
