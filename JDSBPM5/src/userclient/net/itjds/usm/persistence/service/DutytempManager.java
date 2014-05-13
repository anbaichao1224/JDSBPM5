package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Dutytemp;

import org.appfuse.service.GenericManager;

public interface DutytempManager extends
		GenericManager<Dutytemp, String> {

	public List findAll();

	public List<Dutytemp> findAcct();

	public void update(Dutytemp r);

	public List<Dutytemp> finddbAcct();

}