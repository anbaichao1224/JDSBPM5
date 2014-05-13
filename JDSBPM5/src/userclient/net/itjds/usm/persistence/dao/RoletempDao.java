package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Roletemp;

import org.appfuse.dao.GenericDao;

public interface RoletempDao extends GenericDao<Roletemp, String> {

	public List<Roletemp> findAcct();

	public void update(Roletemp r);

	public List<Roletemp> finddbAcct();

}

