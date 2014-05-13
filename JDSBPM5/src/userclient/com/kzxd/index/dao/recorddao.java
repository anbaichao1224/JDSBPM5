package com.kzxd.index.dao;

import java.util.Date;
import java.util.List;

import com.kzxd.index.entity.RecordData;

/**
 * 
 * @author tanrui
 *	dao接口
 */
public interface recorddao {
	//根据申请人，时间，权限查询
	public List<RecordData> find(String personId,int ispass,Date endtime);
	
}
