package com.kzxd.index.service.impl;

import java.util.Date;
import java.util.List;

import com.kzxd.index.dao.recorddao;
import com.kzxd.index.dao.impl.recorddaoimpl;
import com.kzxd.index.entity.RecordData;
import com.kzxd.index.service.recordMsg;

public class recordMsgImpl implements recordMsg{
    
	recorddao zdao = new recorddaoimpl();
	
	
	public List<RecordData> find(String personId,int ispass,Date endtime){
		List<RecordData> data = null;
	    data = zdao.find(personId,ispass,endtime);
		return data;
	}
}
