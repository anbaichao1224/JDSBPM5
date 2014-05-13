package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Orgtemp;

import org.appfuse.dao.GenericDao;

public interface OrgtempDao extends GenericDao<Orgtemp, String> {

	public List<Orgtemp> findAcct();

	public void update(Orgtemp r);

	public List<Orgtemp> finddbAcct();

}

