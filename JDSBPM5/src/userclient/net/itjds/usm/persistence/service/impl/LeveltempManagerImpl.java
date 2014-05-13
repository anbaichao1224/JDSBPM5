package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.LeveltempDao;
import net.itjds.usm.persistence.model.Leveltemp;
import net.itjds.usm.persistence.service.LeveltempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class LeveltempManagerImpl extends
		GenericManagerImpl<Leveltemp, String> implements LeveltempManager {
	LeveltempDao leveltempDao;

	public LeveltempManagerImpl(LeveltempDao leveltempDao) {
		super(leveltempDao);
		this.leveltempDao = leveltempDao;
	}

	public List findAll() {
		return this.leveltempDao.getAll();
	}

	public List<Leveltemp> findAcct() {
		return this.leveltempDao.findAcct();
	}

	public void update(Leveltemp r) {
		this.leveltempDao.update(r);
	}

	public List<Leveltemp> finddbAcct() {
		return this.leveltempDao.finddbAcct();
	}
}