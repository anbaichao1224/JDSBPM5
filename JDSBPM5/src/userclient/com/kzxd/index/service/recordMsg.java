package com.kzxd.index.service;

import java.util.Date;
import java.util.List;

import com.kzxd.index.entity.RecordData;

public interface recordMsg {
	
	public List<RecordData> find(String personId,int ispass,Date endtime);
	
}
