package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Moduletemp;
import net.itjds.usm.persistence.model.Usergrouptemp;

import org.appfuse.service.GenericManager;

public interface ModuletempManager extends
		GenericManager<Moduletemp, String> {

	public List findAll();

	public List<Moduletemp> findAcct();

	public void update(Moduletemp r);

	public List<Moduletemp> finddbAcct();

}