package net.itjds.usm.persistence.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Postiontemp;
import net.itjds.usm.persistence.model.Roletemp;

import org.appfuse.dao.GenericDao;

public interface PostiontempDao extends GenericDao<Postiontemp, String> {

	public List<Postiontemp> findAcct();

	public void update(Postiontemp p);

	public List<Postiontemp> findybAcct();

}

