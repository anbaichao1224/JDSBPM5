package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Persontemp;

import org.appfuse.service.GenericManager;

public interface PersontempManager extends
		GenericManager<Persontemp, String> {

	public List findAll();

	public List<Persontemp> findAcct();

	public void update(Persontemp r);

	public List<Persontemp> finddbAcct();

}