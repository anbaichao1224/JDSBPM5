package com.kzxd.rcap.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.itjds.j2ee.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.kzxd.rcap.entity.gzrdy;
import com.kzxd.rcap.service.gzrdyMsg;
import com.kzxd.rcap.service.impl.gzrdyMsgImpl;
import com.opensymphony.xwork2.Action;

public class gzrdyAction implements Action{
	
	private gzrdyMsg dyMsg;
	
    public gzrdyMsg getDyMsg() {
		return dyMsg;
	}
	public void setDyMsg(gzrdyMsg dyMsg) {
		this.dyMsg = dyMsg;
	}
	//±£´æ
	public String execute() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		gzrdyMsg dyMsg = new gzrdyMsgImpl();
		gzrdy dy = new gzrdy();
		dy.setUuid(eventId);
		dy.setDateFrom(formatDate(from));
		dy.setDateTo(formatDate(to));
		dy.setRzContent(content);
		dy.setRzType(rzType);
		List<gzrdy> gzrdy = new ArrayList<gzrdy>();
		gzrdy.add(dy);
		dyMsg.save(gzrdy);
		request.setAttribute("gzrdy", gzrdy);
		return SUCCESS;
	}
    //É¾³ý
   public String delete() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		gzrdyMsg dyMsg = new gzrdyMsgImpl();
		String uuid = request.getParameter("eventId");
		dyMsg.deletebyid(uuid);
		return SUCCESS;
	}
   //²éÕÒ
   public String find() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		gzrdyMsg dyMsg = new gzrdyMsgImpl();
		gzrdy dy = new gzrdy();
		List<gzrdy> gzrdy = new ArrayList<gzrdy>();
		gzrdy = dyMsg.findbyid();
		StringBuffer strb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strb.append("<data>");
		for (Iterator iter = gzrdy.iterator(); iter.hasNext();) {
			gzrdy event = (gzrdy)iter.next();
			strb.append("<event id=\"" + event.getUuid() + "\">");
			strb.append("<start_date><![CDATA[" + sdf.format(event.getDateFrom()) + "]]></start_date>");
			strb.append("<end_date><![CDATA[" + sdf.format(event.getDateTo()) + "]]></end_date>");
			strb.append("<text><![CDATA[" + event.getRzContent()+ "]]></text>");
			strb.append("<type><![CDATA[" + event.getRzType() + "]]></type>");
			strb.append("</event>");
		}
		strb.append("</data>");
		response.getWriter().print(strb.toString());
		request.setAttribute("gzrdy", gzrdy);
		return null;
	}
	
	public Date formatDate(String datestr) throws ParseException
    {
		 SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
         Date cDate = df.parse(datestr);
         return cDate;
    }
	
	private String eventId;
	private String from;
	private String to;
	private String content;
	private String rzType;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRzType() {
		return rzType;
	}

	public void setRzType(String rzType) {
		this.rzType = rzType;
	}
	
	
}
