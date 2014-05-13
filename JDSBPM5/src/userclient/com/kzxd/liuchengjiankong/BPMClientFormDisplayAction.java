package com.kzxd.liuchengjiankong;

import java.util.List;

import com.opensymphony.xwork2.Action;




import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;

import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.UserClientException;


/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: 
 * </p>
 * 
 */
public class BPMClientFormDisplayAction extends BPMActionBase {
	public BPMClientFormDisplayAction()  {
		super();
	}


	public FormClassBean getCurrForm() throws  BPMException, UserClientException{
	
	
		
		List<FormClassBean> formList=this.getActivityInst().getActivityDef().getAllDataFormDef();
		if (formList.size()==1){
			return formList.get(0);
		}
		for(int k=0;formList.size()>k;k++){
			FormClassBean formClassBean=formList.get(k);
			if (formClassBean.getId().equals(this.getFormID())){
				return formClassBean;
			}
		}	
		FormClassBean formClassBean=this.getActivityInst().getActivityDef().getMainFormDef();
		return formClassBean;
	}
	
	
	
	public String execute() throws Exception {
		yibanli = "y";
		
		return Action.SUCCESS;
	}

	private String yibanli;

	public String getYibanli() {
		return yibanli;
	}


	public void setYibanli(String yibanli) {
		this.yibanli = yibanli;
	}
	
	
	
}
