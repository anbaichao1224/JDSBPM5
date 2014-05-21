package com.kzxd.zihao.dao;

import org.appfuse.dao.GenericDao;

import com.kzxd.zihao.entity.WenZhongZiHao;

public interface WenZhongZiHaoDao extends GenericDao<WenZhongZiHao, String> {
	
	public WenZhongZiHao getByWenZhongAndZiHao(String wenzhong,Integer zihao,String year);
    public abstract String getByZiHao(String paramString1, Integer paramInteger, String paramString2);
}
