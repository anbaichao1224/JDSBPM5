package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Roletemp;

import org.appfuse.service.GenericManager;

public interface RoletempManager extends
		GenericManager<Roletemp, String> {

	public List findAll();

	public List<Roletemp> findAcct();

	public void update(Roletemp r);

	public List<Roletemp> finddbAcct();

}