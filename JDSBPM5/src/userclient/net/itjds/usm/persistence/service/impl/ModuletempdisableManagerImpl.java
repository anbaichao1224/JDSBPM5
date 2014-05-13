package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.ModuletempdisableDao;
import net.itjds.usm.persistence.model.Moduletempdisable;
import net.itjds.usm.persistence.service.ModuletempdisableManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class ModuletempdisableManagerImpl extends
		GenericManagerImpl<Moduletempdisable, String> implements ModuletempdisableManager {
	ModuletempdisableDao moduletempdisableDao;

	public ModuletempdisableManagerImpl(ModuletempdisableDao moduletempdisableDao) {
		super(moduletempdisableDao);
		this.moduletempdisableDao = moduletempdisableDao;
	}

	public List findAll() {
		return this.moduletempdisableDao.getAll();
	}

	public List<Moduletempdisable> findAcct() {
		return this.moduletempdisableDao.findAcct();
	}

	public void update(Moduletempdisable r) {
		this.moduletempdisableDao.update(r);
	}

	public List<Moduletempdisable> finddbAcct() {
		return this.moduletempdisableDao.finddbAcct();
	}
}