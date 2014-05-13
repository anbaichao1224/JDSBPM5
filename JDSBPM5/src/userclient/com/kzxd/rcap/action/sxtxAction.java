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

import com.kzxd.rcap.entity.sxtx;
import com.kzxd.rcap.service.sxtxMsg;
import com.kzxd.rcap.service.impl.sxtxMsgImpl;
import com.opensymphony.xwork2.Action;

public class sxtxAction implements Action{
	
	private sxtxMsg txMsg;
     
	public sxtxMsg getTxMsg() {
		return txMsg;
	}
	public void setTxMsg(sxtxMsg txMsg) {
		this.txMsg = txMsg;
	}
	//±£´æ
	public String execute() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		sxtxMsg txMsg = new sxtxMsgImpl();
		sxtx tx = new sxtx();
		tx.setUuid(new UUID().toString());
		tx.setDateFrom(formatDate(from));
		tx.setDateTo(formatDate(to));
		tx.setRzContent(content);
		tx.setRzType(rzType);
		tx.setPersonid(personId);
		List<sxtx> sxtx = new ArrayList<sxtx>();
		sxtx.add(tx);
		txMsg.save(sxtx);
		request.setAttribute("sxtx", sxtx);
		return SUCCESS;
	}
    //É¾³ý
   public String delete() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		sxtxMsg txMsg = new sxtxMsgImpl();
		String uuid = request.getParameter("eventId");
		txMsg.deletebyid(uuid);
		return SUCCESS;
	}
   //²éÕÒ
   public String find() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		sxtxMsg txMsg = new sxtxMsgImpl();
		sxtx tx = new sxtx();
		List<sxtx> sxtx = new ArrayList<sxtx>();
		sxtx = txMsg.findbyid(personId);
		StringBuffer strb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strb.append("<data>");
		for (Iterator iter = sxtx.iterator(); iter.hasNext();) {
			sxtx event = (sxtx)iter.next();
			strb.append("<event id=\"" + event.getUuid() + "\">");
			strb.append("<start_date><![CDATA[" + sdf.format(event.getDateFrom()) + "]]></start_date>");
			strb.append("<end_date><![CDATA[" + sdf.format(event.getDateTo()) + "]]></end_date>");
			strb.append("<text><![CDATA[" + event.getRzContent()+ "]]></text>");
			strb.append("<type><![CDATA[" + event.getRzType() + "]]></type>");
			strb.append("</event>");
		}
		strb.append("</data>");
		response.getWriter().print(strb.toString());
		request.setAttribute("sxtx", sxtx);
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
	
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
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
