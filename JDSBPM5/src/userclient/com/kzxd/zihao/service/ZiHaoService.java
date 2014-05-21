package com.kzxd.zihao.service;

import java.util.List;

import org.appfuse.service.GenericManager;
public interface ZiHaoService extends GenericManager<com.kzxd.zihao.entity.ZiHao, String>{
	
	public List getByWZId(String wzid);
}
