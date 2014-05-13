/**
 * $RCSfile: BPMClientFormDisplayAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/06/09 14:41:58 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.bpm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import kzxd.electronicfile.action.ReadXmlAction;

import com.opensymphony.xwork2.Action;




import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;

import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.UserClientException;

import net.kzxd.dj.bean.FdtOaDj;


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
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhangli
 * @version 2.0
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
		HttpServletRequest request = ServletActionContext.getRequest();
		/*if(swdjId!=null){
			
		
		fod = new SwdjAction().findById(swdjId);//("12b5f52-133a3ae316f-6eec174587f048c1f7f2137a117d426d");
		String mark = new ReadXmlAction().viewXML("Fdtnmswbl.fdtOaNmswblDAO.");
		marks = mark.split(",");
		request.setAttribute("marks", marks);
		request.setAttribute("fod", fod);
		}*/
		return Action.SUCCESS;
	}

	private String wjbt;
	
	
	private String swdjId;//登记id
	private String[] marks;//字段集合
	private FdtOaDj fod;
	public String getSwdjId() {
		return swdjId;
	}


	public void setSwdjId(String swdjId) {
		this.swdjId = swdjId;
	}


	public String getWjbt() {
		return wjbt;
	}


	public void setWjbt(String wjbt) {
		this.wjbt = wjbt;
	}


	public String[] getMarks() {
		return marks;
	}


	public void setMarks(String[] marks) {
		this.marks = marks;
	}


	public FdtOaDj getFod() {
		return fod;
	}


	public void setFod(FdtOaDj fod) {
		this.fod = fod;
	}
	
	
	
	
}
