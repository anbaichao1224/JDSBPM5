package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Persontemp;

import org.appfuse.dao.GenericDao;

public interface PersontempDao extends GenericDao<Persontemp, String> {

	public List<Persontemp> findAcct();

	public void update(Persontemp r);

	public List<Persontemp> finddbAcct();

}

