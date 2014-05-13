package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.PersonsecureDao;
import net.itjds.usm.persistence.model.Personsecure;
import net.itjds.usm.persistence.service.PersonsecureManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class PersonsecureManagerImpl extends
		GenericManagerImpl<Personsecure, String> implements PersonsecureManager {
	PersonsecureDao PersonsecureDao;

	public PersonsecureManagerImpl(PersonsecureDao PersonsecureDao) {
		super(PersonsecureDao);
		this.PersonsecureDao = PersonsecureDao;
	}

	public List findAll() {
		return this.PersonsecureDao.getAll();
	}
}