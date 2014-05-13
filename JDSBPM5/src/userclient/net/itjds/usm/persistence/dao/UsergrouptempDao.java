package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Usergrouptemp;

import org.appfuse.dao.GenericDao;

public interface UsergrouptempDao extends GenericDao<Usergrouptemp, String> {

	public List<Usergrouptemp> findAcct();

	public void update(Usergrouptemp r);

	public List<Usergrouptemp> finddbAcct();

}

