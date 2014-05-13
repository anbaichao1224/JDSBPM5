package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Leveltemp;
import net.itjds.usm.persistence.model.Moduletempdisable;

import org.appfuse.dao.GenericDao;

public interface ModuletempdisableDao extends GenericDao<Moduletempdisable, String> {

	public List<Moduletempdisable> findAcct();

	public void update(Moduletempdisable r);

	public List<Moduletempdisable> finddbAcct();

}

