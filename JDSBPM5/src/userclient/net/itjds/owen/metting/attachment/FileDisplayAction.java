package net.itjds.owen.metting.attachment;

import com.opensymphony.xwork2.Action;

import net.itjds.userclient.common.ActionBase;

public class FileDisplayAction implements Action {

	String filename;
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		return SUCCESS;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
