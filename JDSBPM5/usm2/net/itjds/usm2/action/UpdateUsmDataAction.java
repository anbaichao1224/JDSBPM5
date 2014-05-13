package net.itjds.usm2.action;


import java.io.File;

import java.util.Iterator;

import net.itjds.usm2.UsmProxy;
import net.itjds.usm2.db.DbManager;
import net.itjds.usm2.db.UsmService;
import net.itjds.usm2.db.util.EsbUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUsmDataAction extends ActionSupport {
	public String jsonStr;
	
	private File file;

	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	
	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}


	public String execute() throws Exception {
	
			jsonStr="±£´æ³É¹¦";
			DbManager.getInstance().beginTransaction();
			Iterator<String > it= EsbUtil.getContextMap().keySet().iterator();
			for(;it.hasNext();){
				String key=it.next();
				UsmProxy proxy=(UsmProxy) EsbUtil.getContextMap().get(key);
				if (proxy!=null){
					UsmService service=(UsmService) EsbUtil.parExpression("$"+proxy.getServiceKey()+"UsmService");
					service.save(proxy);
				}
			}
			DbManager.getInstance().endTransaction(true);
			
		return Action.SUCCESS;
	}
	

	
}
