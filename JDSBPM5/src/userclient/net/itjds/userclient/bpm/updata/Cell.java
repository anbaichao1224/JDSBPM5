/**
 * $RCSfile: Cell.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/06/09 14:44:35 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.bpm.updata;



import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.itjds.common.util.DateUtility;

import com.opensymphony.xwork2.ActionContext;



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
public class Cell  {
	
	
	private static final String combo="combo" ;
	private static final String htmleditor="htmleditor" ;
	private static final String textarea="textarea" ;
	private static final String date="date" ;
	private static final String text="text" ;
	private static final String time="time" ;
	private static final String number="number" ;

	private String key;
	private String express;
	private String value;
	private String type;
	private String formId;
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		if (type.equals(combo)){
			Object rawvalue= ActionContext.getContext().getValueStack().findValue(express,Map.class);
			Map<String ,String> rawMap=(Map<String ,String> )rawvalue;
			StringBuffer mapStr=new StringBuffer();
			mapStr.append("[");
			Iterator<String> it=rawMap.keySet().iterator();
			for(;it.hasNext();){
				String key=it.next();
				String value=rawMap.get(key);
				mapStr.append("{value:'"+key+"',text:'"+value+"'}");
				if (it.hasNext()){
					mapStr.append(",");
				}
			}
			mapStr.append("]");
			value =mapStr.toString();
		}else if(type.equals(date)){
			Date rawvalue=(Date) ActionContext.getContext().getValueStack().findValue(express,Date.class);
			value=DateUtility.formatDate(rawvalue, "yyyy-MM-dd");
		}else if(type.equals(number)){
			Number rawvalue= (Number) ActionContext.getContext().getValueStack().findValue(express,Number.class);
			value=rawvalue.toString();
		}else{
			value=ActionContext.getContext().getValueStack().findString(express);
		}
	
		
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}	
	


