package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Usergrouptemp;

import org.appfuse.service.GenericManager;

public interface UsergrouptempManager extends
		GenericManager<Usergrouptemp, String> {

	public List findAll();

	public List<Usergrouptemp> findAcct();

	public void update(Usergrouptemp r);

	public List<Usergrouptemp> finddbAcct();

}