package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Leveltemp;

import org.appfuse.service.GenericManager;

public interface LeveltempManager extends
		GenericManager<Leveltemp, String> {

	public List findAll();

	public List<Leveltemp> findAcct();

	public void update(Leveltemp r);

	public List<Leveltemp> finddbAcct();

}