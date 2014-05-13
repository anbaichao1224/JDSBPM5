package com.kzxd.rcap.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itjds.j2ee.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.kzxd.rcap.entity.rcap;
import com.kzxd.rcap.service.rcapMsg;
import com.kzxd.rcap.service.impl.rcapMsgImpl;
import com.opensymphony.xwork2.Action;

public class rcapAction implements Action{
	
	private rcapMsg rcMsg;
	
	public rcapMsg getRcMsg() {
		return rcMsg;
	}
	public void setRcMsg(rcapMsg rcMsg) {
		this.rcMsg = rcMsg;
	}
    //保存
	public String execute() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String  rztitle = request.getParameter("rzType");
		//获取pensonid
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		rcapMsg rcMsg = new rcapMsgImpl();
		rcap rc = new rcap();
		rc.setUuid(eventId);
		rc.setDateFrom(formatDate(from));
		rc.setDateTo(formatDate(to));
		rc.setRzTitle(rztitle);
		rc.setRzContent(content);
		rc.setPersonid(personId);
		List<rcap> rcap = new ArrayList<rcap>();
		rcap.add(rc);
		rcMsg.save(rcap);
		request.setAttribute("rcap", rcap);
		return SUCCESS;
	}
    //删除
   public String delete() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		rcapMsg rcMsg = new rcapMsgImpl();
		String uuid = request.getParameter("eventId");
		rcMsg.deletebyid(uuid);
		return SUCCESS;
	}
   //查找
   public String find() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String  rztitle = request.getParameter("rzType");
		if(personid==null||personid.equals("null")){
			HttpSession session = request.getSession();
			personid = session.getAttribute("personId").toString();
		}
		
		rcapMsg rcMsg = new rcapMsgImpl();
		rcap rc = new rcap();
		rc.setRzTitle(rztitle);
		List<rcap> rcap = new ArrayList<rcap>();
		rcap = rcMsg.findbyid(personid);
		StringBuffer strb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strb.append("<data>");
		for (Iterator iter = rcap.iterator(); iter.hasNext();) {
			rcap event = (rcap)iter.next();
			strb.append("<event id=\"" + event.getUuid() + "\">");
			strb.append("<start_date><![CDATA[" + sdf.format(event.getDateFrom()) + "]]></start_date>");
			strb.append("<end_date><![CDATA[" + sdf.format(event.getDateTo()) + "]]></end_date>");
			strb.append("<text><![CDATA[" + event.getRzContent()+ "]]></text>");
			strb.append("<type><![CDATA[" + event.getRzTitle() + "]]></type>");
			strb.append("</event>");
		}
		strb.append("</data>");
		response.getWriter().print(strb.toString());
		request.setAttribute("rcap", rcap);
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
	private String personid;

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
	
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	
	
}
