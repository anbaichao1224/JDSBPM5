package net.itjds.usm.persistence.service.impl;

import java.util.List;

import net.itjds.usm.persistence.dao.PostiontempDao;
import net.itjds.usm.persistence.model.Postiontemp;
import net.itjds.usm.persistence.service.PostiontempManager;

import org.appfuse.service.impl.GenericManagerImpl;

public class PostiontempManagerImpl extends
		GenericManagerImpl<Postiontemp, String> implements PostiontempManager {
	PostiontempDao postiontempDao;

	public PostiontempManagerImpl(PostiontempDao postiontempDao) {
		super(postiontempDao);
		this.postiontempDao = postiontempDao;
	}

	public List findAll() {
		return this.postiontempDao.getAll();
	}

	public List<Postiontemp> findAcct() {
		return this.postiontempDao.findAcct();
	}

	public void update(Postiontemp p) {
		this.postiontempDao.update(p);
	}

	public List<Postiontemp> findybAcct() {
		return this.postiontempDao.findybAcct();
	}
}