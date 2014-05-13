package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.ModuletempdisableDao;
import net.itjds.usm.persistence.model.Moduletemp;
import net.itjds.usm.persistence.model.Moduletempdisable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class ModuletempdisableDaoHibernate extends
		GenericDaoHibernate<Moduletempdisable, String> implements
		ModuletempdisableDao {

	public ModuletempdisableDaoHibernate() {
		super(Moduletempdisable.class);
	}

	public List<Moduletempdisable> getAll() {
		return getHibernateTemplate().find("from Moduletempdisable e");
	}

	public List<Moduletempdisable> findAcct() {
		return getHibernateTemplate().find("from Moduletempdisable e where e.roleacct = 'N'");
	}

	public void update(Moduletempdisable r) {
		super.getSession().update(r);
	}

	public List<Moduletempdisable> finddbAcct() {
		return getHibernateTemplate().find("from Moduletempdisable e where e.roleacct != 'N'");
	}

}
