package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.RoletempDao;
import net.itjds.usm.persistence.model.Roletemp;
import net.itjds.usm.persistence.service.RoletempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class RoletempManagerImpl extends
		GenericManagerImpl<Roletemp, String> implements RoletempManager {
	RoletempDao roletempDao;

	public RoletempManagerImpl(RoletempDao roletempDao) {
		super(roletempDao);
		this.roletempDao = roletempDao;
	}

	public List findAll() {
		return this.roletempDao.getAll();
	}

	public List<Roletemp> findAcct() {
		return this.roletempDao.findAcct();
	}

	public void update(Roletemp r) {
		this.roletempDao.update(r);
	}

	public List<Roletemp> finddbAcct() {
		return this.roletempDao.finddbAcct();
	}
}