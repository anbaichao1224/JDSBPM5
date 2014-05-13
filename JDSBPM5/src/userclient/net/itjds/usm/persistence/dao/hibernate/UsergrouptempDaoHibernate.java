package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.UsergrouptempDao;
import net.itjds.usm.persistence.model.Usergrouptemp;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class UsergrouptempDaoHibernate extends
		GenericDaoHibernate<Usergrouptemp, String> implements
		UsergrouptempDao {

	public UsergrouptempDaoHibernate() {
		super(Usergrouptemp.class);
	}

	public List<Usergrouptemp> getAll() {
		return getHibernateTemplate().find("from Usergrouptemp e");
	}

	public List<Usergrouptemp> findAcct() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from Usergrouptemp e where e.roleacct = 'N'");
	}

	public void update(Usergrouptemp r) {
		super.getSession().update(r);
	}

	public List<Usergrouptemp> finddbAcct() {
		return getHibernateTemplate().find("from Usergrouptemp e where e.roleacct != 'N'");
	}

}
