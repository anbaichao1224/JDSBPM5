package com.kzxd.abortprocess.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;

import com.kzxd.abortprocess.dao.AbortProcessDao;
import com.kzxd.abortprocess.module.AbortProcessBean;
import com.kzxd.abortprocess.service.AbortProcessManager;

public class AbortProcessManagerImpl extends GenericManagerImpl<AbortProcessBean,String> implements
		AbortProcessManager {

		AbortProcessDao abortdao;
		public AbortProcessManagerImpl(AbortProcessDao abortdao){
			super(abortdao);
			this.abortdao = abortdao;
			
		}

}
