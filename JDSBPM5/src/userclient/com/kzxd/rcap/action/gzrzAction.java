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
import net.itjds.userclient.common.BPMActionBase;

import org.apache.struts2.ServletActionContext;

import com.kzxd.rcap.entity.gzrz;
import com.kzxd.rcap.service.gzrzMsg;
import com.kzxd.rcap.service.impl.gzrzMsgImpl;
import com.opensymphony.xwork2.Action;

public class gzrzAction extends BPMActionBase implements Action{
	
	private gzrzMsg rzMsg;
	
	public gzrzMsg getRzMsg() {
		return rzMsg;
	}
	public void setRzMsg(gzrzMsg rzMsg) {
		this.rzMsg = rzMsg;
	}
	
	private String ks;
	private String js;
	private String rzlx;
	private String rznr;
	public String getKs() {
		return ks;
	}
	public void setKs(String ks) {
		this.ks = ks;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getRzlx() {
		return rzlx;
	}
	public void setRzlx(String rzlx) {
		this.rzlx = rzlx;
	}
	public String getRznr() {
		return rznr;
	}
	public void setRznr(String rznr) {
		this.rznr = rznr;
	}
	//保存
	public String execute() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		//获取pensonid
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		gzrzMsg rzMsg = new gzrzMsgImpl();
		gzrz rz = new gzrz();
		rz.setUuid(eventId);
		rz.setDateFrom(formatDate(from));
		rz.setDateTo(formatDate(to));
		rz.setRzContent(content);
		rz.setRzType(rzType);
		rz.setPersonid(personId);
		List<gzrz> gzrz = new ArrayList<gzrz>();
		gzrz.add(rz);
		rzMsg.save(gzrz);
		request.setAttribute("gzrz", gzrz);
		return SUCCESS;
	}
    //删除
   public String delete() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		gzrzMsg rzMsg = new gzrzMsgImpl();
		String uuid = request.getParameter("eventId");
		rzMsg.deletebyid(uuid);
		return SUCCESS;
	}
   //查找
   public String find() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		response.setContentType("text/html;charset=utf-8");
		gzrzMsg rzMsg = new gzrzMsgImpl();
		gzrz rz = new gzrz();
		List<gzrz> gzrz = new ArrayList<gzrz>();
		gzrz = rzMsg.findbyid(personId);
		StringBuffer strb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strb.append("<data>");
		for (Iterator iter = gzrz.iterator(); iter.hasNext();) {
			gzrz event = (gzrz)iter.next();
			strb.append("<event id=\"" + event.getUuid() + "\">");
			strb.append("<start_date><![CDATA[" + sdf.format(event.getDateFrom()) + "]]></start_date>");
			strb.append("<end_date><![CDATA[" + sdf.format(event.getDateTo()) + "]]></end_date>");
			strb.append("<text><![CDATA[" + event.getRzContent()+ "]]></text>");
			strb.append("<type><![CDATA[" + event.getRzType() + "]]></type>");
			strb.append("</event>");
		}
		strb.append("</data>");
		response.getWriter().print(strb.toString());
		request.setAttribute("gzrz", gzrz);
		return null;
	}
   
    protected HttpServletResponse getResponse(){
		
		return ServletActionContext.getResponse();
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
