package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Personsecure;

import org.appfuse.service.GenericManager;

public interface PersonsecureManager extends
		GenericManager<Personsecure, String> {

	public List findAll();

}