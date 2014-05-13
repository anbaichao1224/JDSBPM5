package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Moduletempdisable;

import org.appfuse.service.GenericManager;

public interface ModuletempdisableManager extends
		GenericManager<Moduletempdisable, String> {

	public List findAll();

	public List<Moduletempdisable> findAcct();

	public void update(Moduletempdisable r);

	public List<Moduletempdisable> finddbAcct();

}