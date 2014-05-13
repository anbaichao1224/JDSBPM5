package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Orgtemp;

import org.appfuse.service.GenericManager;

public interface OrgtempManager extends
		GenericManager<Orgtemp, String> {

	public List findAll();

	public List<Orgtemp> findAcct();

	public void update(Orgtemp r);

	public List<Orgtemp> finddbAcct();

}