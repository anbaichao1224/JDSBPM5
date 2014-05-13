package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.ModuletempDao;
import net.itjds.usm.persistence.model.Moduletemp;
import net.itjds.usm.persistence.service.ModuletempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class ModuletempManagerImpl extends
		GenericManagerImpl<Moduletemp, String> implements ModuletempManager {
	ModuletempDao moduletempDao;

	public ModuletempManagerImpl(ModuletempDao moduletempDao) {
		super(moduletempDao);
		this.moduletempDao = moduletempDao;
	}

	public List findAll() {
		return this.moduletempDao.getAll();
	}

	public List<Moduletemp> findAcct() {
		return this.moduletempDao.findAcct();
	}

	public void update(Moduletemp r) {
		this.moduletempDao.update(r);
	}

	public List<Moduletemp> finddbAcct() {
		return this.moduletempDao.finddbAcct();
	}
}