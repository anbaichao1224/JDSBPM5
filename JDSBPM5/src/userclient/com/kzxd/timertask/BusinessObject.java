package com.kzxd.timertask;

import java.util.List;

import com.kzxd.xzsp.action.XZSPAction;
import com.kzxd.xzsp.action.XzspjkTempAction;
import com.kzxd.xzsp.action.XzspjkTempBFAction;
import com.kzxd.xzsp.util.XzspjkTempBFBean;
import com.kzxd.xzsp.util.XzspjkTempBean;

public class BusinessObject {
	 
	public void doIt() {
		
		XzspjkTempAction xtaction = new XzspjkTempAction();
		List<XzspjkTempBean> xtlist=xtaction.findAll();
		XzspjkTempBFAction xtbfaction = new XzspjkTempBFAction();
		
		XZSPAction action = new XZSPAction();		
		
		for(int i=0;i<xtlist.size();i++){
			
			int flag = action.fsXml(xtlist.get(i).getXmlstr(), xtlist.get(i).getXmlstr2());
			System.out.print("flag="+flag);
			if(flag==2){
				break;
			}else if(flag==0){				
				XzspjkTempBFBean xzbfBean=new XzspjkTempBFBean();
				xzbfBean.setUuid(xtlist.get(i).getUuid());
				xzbfBean.setXmlstr(xtlist.get(i).getXmlstr());
				xzbfBean.setXmlstr2(xtlist.get(i).getXmlstr2());
				xzbfBean.setBsnum(xtlist.get(i).getBsnum());
				xtbfaction.save(xzbfBean);
				xtaction.delete(xtlist.get(i));
			}else{
				xtaction.delete(xtlist.get(i));
			}
			
		}
		
		List<XzspjkTempBFBean> xtbflist=xtbfaction.findAll();
		
		for(int i=0;i<xtbflist.size();i++){
			int flag = action.fsXml(xtbflist.get(i).getXmlstr(), xtbflist.get(i).getXmlstr2());
			if(flag==2){
				break;
			}
		}
	}

}
