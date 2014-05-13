package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Postiontemp;

import org.appfuse.service.GenericManager;

public interface PostiontempManager extends
		GenericManager<Postiontemp, String> {

	public List findAll();

	public List<Postiontemp> findAcct();

	public void update(Postiontemp p);

	public List<Postiontemp> findybAcct();

}