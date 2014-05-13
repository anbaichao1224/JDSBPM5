package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.PersontempDao;
import net.itjds.usm.persistence.model.Persontemp;
import net.itjds.usm.persistence.service.PersontempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class PersontempManagerImpl extends
		GenericManagerImpl<Persontemp, String> implements PersontempManager {
	PersontempDao persontempDao;

	public PersontempManagerImpl(PersontempDao persontempDao) {
		super(persontempDao);
		this.persontempDao = persontempDao;
	}

	public List findAll() {
		return this.persontempDao.getAll();
	}

	public List<Persontemp> findAcct() {
		return this.persontempDao.findAcct();
	}

	public void update(Persontemp r) {
		this.persontempDao.update(r);
	}

	public List<Persontemp> finddbAcct() {
		return this.persontempDao.finddbAcct();
	}
}