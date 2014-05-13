package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Dutytemp;
import net.itjds.usm.persistence.model.Roletemp;

import org.appfuse.dao.GenericDao;

public interface DutytempDao extends GenericDao<Dutytemp, String> {

	public List<Dutytemp> findAcct();

	public void update(Dutytemp r);

	public List<Dutytemp> finddbAcct();

}

