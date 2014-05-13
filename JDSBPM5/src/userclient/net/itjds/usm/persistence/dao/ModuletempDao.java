package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Moduletemp;

import org.appfuse.dao.GenericDao;

public interface ModuletempDao extends GenericDao<Moduletemp, String> {

	public List<Moduletemp> findAcct();

	public void update(Moduletemp r);

	public List<Moduletemp> finddbAcct();

}

