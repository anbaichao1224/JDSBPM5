package com.kzxd.abortprocess.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.kzxd.abortprocess.dao.AbortProcessDao;
import com.kzxd.abortprocess.module.AbortProcessBean;

public class AbortProcessDaoImpl extends GenericDaoHibernate<AbortProcessBean,String> implements
		AbortProcessDao {

	public AbortProcessDaoImpl(){
		super(AbortProcessBean.class);
	}

}
