package net.itjds.usm.persistence.dao.hibernate;

import java.util.List;

import net.itjds.usm.persistence.dao.PersonsecureDao;
import net.itjds.usm.persistence.model.Personsecure;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

public class PersonsecureDaoHibernate extends
		GenericDaoHibernate<Personsecure, String> implements
		PersonsecureDao {

	public PersonsecureDaoHibernate() {
		super(Personsecure.class);
	}

	public List<Personsecure> getAll() {
		return getHibernateTemplate().find("from Personsecure e");
		
	}

}
