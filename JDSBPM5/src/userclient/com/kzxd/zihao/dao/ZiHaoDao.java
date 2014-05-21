package com.kzxd.zihao.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.kzxd.zihao.entity.ZiHao;

public interface ZiHaoDao extends GenericDao<ZiHao, String>{
	
	public void update(ZiHao zihao);
	
	/**
	 * �������ֵõ�����ֺ�
	 * @param wenzhong
	 * @return
	 */
	public Integer getMaxZiHao(String wenzhong,String year);
	/**
	 * ����actid����ʵ��id�õ�zihao
	 * @param actid
	 * @return
	 */
	public ZiHao getByActid(String actid);
	
	public List getByWZId(String wzid);
	

    public abstract String getActdefIdByActid(String paramString);
}
