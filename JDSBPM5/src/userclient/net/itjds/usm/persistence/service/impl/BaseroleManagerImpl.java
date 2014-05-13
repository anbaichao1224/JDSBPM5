package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.BaseroleDao;
import net.itjds.usm.persistence.model.Baserole;
import net.itjds.usm.persistence.service.BaseroleManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class BaseroleManagerImpl extends
		GenericManagerImpl<Baserole, String> implements BaseroleManager {
	BaseroleDao baseroleDao;

	public BaseroleManagerImpl(BaseroleDao baseroleDao) {
		super(baseroleDao);
		this.baseroleDao = baseroleDao;
	}

	public List<Baserole> findAll() {
		return this.baseroleDao.getAll();
	}
}