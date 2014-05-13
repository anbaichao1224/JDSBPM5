package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.DutytempDao;
import net.itjds.usm.persistence.model.Dutytemp;
import net.itjds.usm.persistence.service.DutytempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class DutytempManagerImpl extends
		GenericManagerImpl<Dutytemp, String> implements DutytempManager {
	DutytempDao dutytempDao;
	public DutytempManagerImpl(DutytempDao dutytempDao) {
		super(dutytempDao);
		this.dutytempDao = dutytempDao;
	}

	public List findAll() {
		return this.dutytempDao.getAll();
	}

	public List<Dutytemp> findAcct() {
		return this.dutytempDao.findAcct();
	}

	public void update(Dutytemp r) {
		this.dutytempDao.update(r);
	}

	public List<Dutytemp> finddbAcct() {
		return this.dutytempDao.finddbAcct();
	}

}