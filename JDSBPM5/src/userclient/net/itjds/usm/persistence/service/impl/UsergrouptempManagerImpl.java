package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.UsergrouptempDao;
import net.itjds.usm.persistence.model.Usergrouptemp;
import net.itjds.usm.persistence.service.UsergrouptempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class UsergrouptempManagerImpl extends
		GenericManagerImpl<Usergrouptemp, String> implements UsergrouptempManager {
	UsergrouptempDao usergrouptempDao;

	public UsergrouptempManagerImpl(UsergrouptempDao usergrouptempDao) {
		super(usergrouptempDao);
		this.usergrouptempDao = usergrouptempDao;
	}

	public List findAll() {
		return this.usergrouptempDao.getAll();
	}

	public List<Usergrouptemp> findAcct() {
		return this.usergrouptempDao.findAcct();
	}

	public void update(Usergrouptemp r) {
		this.usergrouptempDao.update(r);
	}

	public List<Usergrouptemp> finddbAcct() {
		return this.usergrouptempDao.finddbAcct();
	}
}