package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Leveltemp;

import org.appfuse.dao.GenericDao;

public interface LeveltempDao extends GenericDao<Leveltemp, String> {

	public List<Leveltemp> findAcct();

	public void update(Leveltemp r);

	public List<Leveltemp> finddbAcct();

}

