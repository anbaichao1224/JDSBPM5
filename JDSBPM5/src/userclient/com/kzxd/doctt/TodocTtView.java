package com.kzxd.doctt;

import net.itjds.userclient.common.BPMActionBase;


public class TodocTtView extends BPMActionBase {
	
	private String docid;
	
	
	public String getDocid() {
		return docid;
	}


	public void setDocid(String docid) {
		this.docid = docid;
	}


	public String execute() throws Exception {
		
		return "success";
	}
	
}
