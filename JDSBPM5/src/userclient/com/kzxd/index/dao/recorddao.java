package com.kzxd.index.dao;

import java.util.Date;
import java.util.List;

import com.kzxd.index.entity.RecordData;

/**
 * 
 * @author tanrui
 *	dao�ӿ�
 */
public interface recorddao {
	//���������ˣ�ʱ�䣬Ȩ�޲�ѯ
	public List<RecordData> find(String personId,int ispass,Date endtime);
	
}
