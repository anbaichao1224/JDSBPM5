package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.OrgtempDao;
import net.itjds.usm.persistence.model.Orgtemp;
import net.itjds.usm.persistence.service.OrgtempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class OrgtempManagerImpl extends
		GenericManagerImpl<Orgtemp, String> implements OrgtempManager {
	OrgtempDao orgtempDao;

	public OrgtempManagerImpl(OrgtempDao orgtempDao) {
		super(orgtempDao);
		this.orgtempDao = orgtempDao;
	}

	public List findAll() {
		return this.orgtempDao.getAll();
	}

	public List<Orgtemp> findAcct() {
		return this.orgtempDao.findAcct();
	}

	public void update(Orgtemp r) {
		this.orgtempDao.update(r);
	}

	public List<Orgtemp> finddbAcct() {
		return this.orgtempDao.finddbAcct();
	}
}