package net.itjds.usm.persistence.service;

import java.util.List;

import net.itjds.usm.persistence.model.Baserole;

import org.appfuse.service.GenericManager;

public interface BaseroleManager extends
		GenericManager<Baserole, String> {

	public List findAll();

}